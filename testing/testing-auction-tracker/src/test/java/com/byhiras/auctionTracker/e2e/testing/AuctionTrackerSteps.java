package com.byhiras.auctionTracker.e2e.testing;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byhiras.AuctionTrackerServer;

import cucumber.api.DataTable;
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
	
	@Given("^a registered user:$")
	public void a_registered_user(DataTable arg1) throws Throwable {

	}

	@Given("^an open aution item:$")
	public void an_open_aution_item(DataTable arg1) throws Throwable {

	}

	@When("^user \"([^\"]*)\" logs in with$")
	public void user_logs_in_with(String arg1, DataTable arg2) throws Throwable {

	}

	@When("^bids for auction item (\\d+) with:$")
	public void bids_for_auction_item_with(int arg1, DataTable arg2) throws Throwable {

	}

	@Then("^when user \"([^\"]*)\" lists all the bids for an item the result contains a bid matching:$")
	public void when_user_lists_all_the_bids_for_an_item_the_result_contains_a_bid_matching(String arg1, DataTable arg2) throws Throwable {

	}
}
