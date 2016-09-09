package com.byhiras.auctionTracker.e2e.testing;

import java.math.BigDecimal;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byhiras.AuctionTrackerServer;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * {@link SpringBootContextLoader} and {@link IntegrationTest} being used for cucumber steps 
 * because cucumber-spring bootstrap does not support SpringBoot 1.4.0 test annotation yet 
 * @author Lee
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuctionTrackerServer.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest("server.port:9797")
public class AuctionTrackerSteps {
	
	@Given("^a bidder \"([^\"]*)\" registers at the auction and recieves paddle (\\d+):$")
	public void a_bidder_registers_at_the_auction_and_recieves_paddle(final String bidder, final Integer paddleNumber) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^the catelogue lists:$")
	public void the_catelogue_lists(final DataTable catelogue) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    throw new PendingException();
	}

	@When("^bidding on lot (\\d+) opens:$")
	public void bidding_on_lot_opens(final Long lot) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^a bidder bids (\\d+.\\d+) on lot (\\d+) using paddle (\\d+):$")
	public void a_bidder_bids_on_lot_using_paddle(final Double price, final Long lot, final Integer paddle) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^if the bidder \"([^\"]*)\" looks up lot (\\d+) bid history a matching bid is listed:$")
	public void if_the_bidder_looks_up_lot_bid_history_a_matching_bid_is_listed(final String bidder, final Long lot, final DataTable bids) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    throw new PendingException();
	}

	@Then("^if the bidder \"([^\"]*)\" looks up all lots they have placed a bid on at the auction a mathcing lot is listed:$")
	public void if_the_bidder_looks_up_all_lots_they_have_placed_a_bid_on_at_the_auction_a_mathcing_lot_is_listed(final String bidder, final DataTable lots) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    throw new PendingException();
	}

}
