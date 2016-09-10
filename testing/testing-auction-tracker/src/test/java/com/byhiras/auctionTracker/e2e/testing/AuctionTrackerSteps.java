package com.byhiras.auctionTracker.e2e.testing;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byhiras.AuctionTrackerServer;
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

import cucumber.api.DataTable;
import cucumber.api.PendingException;
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
public class AuctionTrackerSteps {
	
	@Autowired
	private ReferenceApi referenceApi;

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
	public void bidding_on_lot_opens(int arg1) throws Throwable {
		
	}	

	@When("^bidder \"([^\"]*)\" bids (\\d+)\\.(\\d+) for lot (\\d+) using paddle (\\d+):$")
	public void bidder_bids_for_lot_using_paddle(String arg1, int arg2, int arg3, int arg4, int arg5) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^if the bidder \"([^\"]*)\" looks up lot (\\d+) bid history the following matching bid is listed:$")
	public void if_the_bidder_looks_up_lot_bid_history_the_following_matching_bid_is_listed(String arg1, int arg2,
			DataTable arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		throw new PendingException();
	}

	@Then("^if the bidder \"([^\"]*)\" looks up all lots they have placed at the \"([^\"]*)\" auction the following matching lot is listed:$")
	public void if_the_bidder_looks_up_all_lots_they_have_placed_at_the_auction_the_following_matching_lot_is_listed(
			String arg1, String arg2, DataTable arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		throw new PendingException();
	}

}
