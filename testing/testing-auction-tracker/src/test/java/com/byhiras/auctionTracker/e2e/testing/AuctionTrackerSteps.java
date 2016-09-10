package com.byhiras.auctionTracker.e2e.testing;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.exparity.expectamundo.Expectamundo.expect;
import static org.exparity.expectamundo.Expectamundo.expectThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.exparity.expectamundo.Expectamundo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byhiras.AuctionTrackerServer;
import com.byhiras.bid.model.Bid;
import com.byhiras.bid.model.LotBids;
import com.byhiras.messaging.BidPublisher;
import com.byhiras.ref.ReferenceApi;
import com.byhiras.ref.builder.AuctionBuilder;
import com.byhiras.ref.builder.AuctionPaddleBuilder;
import com.byhiras.ref.builder.CatalogBuilder;
import com.byhiras.ref.builder.UserBuilder;
import com.byhiras.ref.model.Auction;
import com.byhiras.ref.model.Lot;
import com.byhiras.ref.model.User;
import com.byhiras.ref.repo.AuctionRepository;
import com.byhiras.ref.repo.UserRepository;
import com.byhiras.service.BiddingService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * {@link SpringBootContextLoader} and {@link IntegrationTest} being used for
 * cucumber steps because cucumber-spring bootstrap does not support SpringBoot
 * 1.4.0 test annotation yet
 * 
 * @author Lee
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuctionTrackerServer.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest("server.port:9797")
@Transactional
public class AuctionTrackerSteps {
	
	@Autowired
	private ReferenceApi referenceApi;
	
	@Autowired
	private BiddingService biddingService;
	
	@Autowired
	private BidPublisher bidPublisher;

	@Given("^a \"([^\"]*)\" auction with a catelogue list:$")
	public void a_auction_with_a_catelogue_list(final String auctionName, final List<Lot> lots) throws Throwable {
		final Auction auction = new AuctionBuilder()
									.withName(auctionName)
									.withCatalog(
											new CatalogBuilder()
												.withLots(lots)
												.build()
								)
								.build();
		final AuctionRepository repository = (AuctionRepository)referenceApi.getRepository(Auction.class);
		repository.save(auction);
	}	

	@Given("^a bidder \"([^\"]*)\" registers at the \"([^\"]*)\" auction and recieves paddle (\\d+):$")
	public void a_bidder_registers_at_the_auction_and_recieves_paddle(final String username, final String auctionName, final Integer paddleNumber)
			throws Throwable {
		final UserRepository userRepo = (UserRepository)referenceApi.getRepository(User.class);
		final User user = new UserBuilder().withUsername(username).build();
		userRepo.save(user);
		
		final AuctionRepository auctionRepo = (AuctionRepository)referenceApi.getRepository(Auction.class);
		final Auction auction = auctionRepo.findByName(auctionName);
		auction.addPaddle(
			new AuctionPaddleBuilder()
			.withPaddleNumber(paddleNumber)
			.withUser(user)
			.build()
		);
		auctionRepo.save(auction);
	}
	
	@When("^bidding on lot (\\d+) opens:$")
	public void bidding_on_lot_opens(Integer lot) throws Throwable {
		biddingService.openBiddingForLot(lot);
	}	

	@When("^bidder \"([^\"]*)\" bids \"([^\"]*)\" for lot (\\d+) using paddle (\\d+):$")
	public void bidder_bids_for_lot_using_paddle(String username, String price, Integer lot, Integer paddle) throws Throwable {
		final Bid bid = new Bid();
		bid.setLotNumber(lot);
		bid.setPaddleNumber(paddle);
		bid.setPrice(new BigDecimal(price));
		bidPublisher.publish(bid);
		Thread.sleep(10);
	}

	@Then("^if the bidder \"([^\"]*)\" looks up lot (\\d+) bid history the following matching bid is listed:$")
	public void if_the_bidder_looks_up_lot_bid_history_the_following_matching_bid_is_listed(String username, Integer lotNumber,
			final List<Bid> bids) throws Throwable {		
		final List<Bid> expectedBids = createExpectations(bids); 		
		assertBids(expectedBids, lotNumber);		
	}

	private void assertBids(final List<Bid> expectedBids, Integer lotNumber)
			throws InterruptedException, ExecutionException {
		final Future<Boolean> future = newFixedThreadPool(1).submit(() -> {
			while (true) {
				try {
					final LotBids actualBids = biddingService.findLotBidsByLot(lotNumber);						
					expectedBids.forEach(e -> expectThat(actualBids.getBids()).contains(e));
					return Boolean.TRUE;
				} catch (AssertionError ae) {
					Thread.sleep(1);
				}
			}
		});
		
		try {
			future.get(10, TimeUnit.SECONDS);
		} catch (TimeoutException to) {
			final LotBids actualBids = biddingService.findLotBidsByLot(lotNumber);						
			expectedBids.forEach(e -> expectThat(actualBids.getBids()).contains(e));		
		}
	}

	private List<Bid> createExpectations(List<Bid> bids) {		
		return bids.stream().map(this::createBidExpectaton).collect(Collectors.toList());		
	}
	
	public Bid createBidExpectaton(Bid bid){
		final Bid expectedBid = Expectamundo.prototype(Bid.class);
		expect(expectedBid.getPaddleNumber()).isEqualTo(bid.getPaddleNumber());
		expect(expectedBid.getPrice()).isComparableTo(bid.getPrice());
		return expectedBid;
	}
	
	@Then("^if the bidder \"([^\"]*)\" looks up the current highest bid for lot (\\d+) then the following matching bid will be returned:$")
	public void if_the_bidder_looks_up_the_current_highest_bid_for_lot_then_the_following_matching_bid_will_be_returned(String username, Integer lotNumber, List<Bid> bid) throws Throwable {
		assertTrue("Expecting one bid but list is null", Optional.ofNullable(bid).isPresent());
		assertTrue("Expecting only one bid", bid.size() == 1);
		final Bid expectedBid = createBidExpectaton(bid.get(0));
		final LotBids actualBids = biddingService.findLotBidsByLot(lotNumber);
		expectThat(actualBids.getCurrentHighestBid()).matches(expectedBid);
	}

}
