package com.byhiras.api.service;

import static com.byhiras.testutils.model.RegistrationModelHelper.aValidRandomEmail;
import static com.byhiras.testutils.model.RegistrationModelHelper.aValidRandomPassword;
import static com.byhiras.testutils.model.RegistrationModelHelper.aValidRandomUsername;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byhiras.TradeServer;
import com.byhiras.api.client.ClientFactory;
import com.byhiras.api.model.RegisterFormatError;
import com.byhiras.api.model.RegisterRequest;
import com.byhiras.api.model.RegisterResponse;
import com.byhiras.api.model.ServerDetails;
import com.byhiras.api.model.ServerDetailsBuilder;
import com.byhiras.api.model.UserError;
import com.byhiras.api.model.builder.RegisterRequestBuilder;
import com.byhiras.marshalling.MarshallerFactory;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = TradeServer.class)  
@WebAppConfiguration  
@IntegrationTest("server.port:0")
public class RegisterRestServiceTest {
	
	private RegisterAPIService registerService;
	
    @Value("${local.server.port}")
    private int port;
    
    @Before
    public void before(){
		final ServerDetails serverDetails = 
				new ServerDetailsBuilder()
				.withPort(port)
				.build();    	
		ClientFactory clientFactory = ClientFactory
				.factoryInstance(
						ClientFactory.ClientType.REST, 
						serverDetails, new MarshallerFactory()
				); 
		registerService = clientFactory.getRegisterService();
    }
    
    @Test
    public void canRegister() {    
		registerService.register(
								new RegisterRequestBuilder()
									.withUsername(aValidRandomUsername())
									.withEmail(aValidRandomEmail())
									.withPassword(aValidRandomPassword())												
									.build()
								 );		    	
    }
    
    @Test
    public void canRegisterwithRefferral() {   
		RegisterRequest request =  new RegisterRequestBuilder()
		.withUsername(aValidRandomUsername())
		.withEmail(aValidRandomEmail())
		.withPassword(aValidRandomPassword())												
		.build();    	
		registerService.register(request);		
		registerService.register(
				new RegisterRequestBuilder()
					.withUsername(aValidRandomUsername())
					.withEmail(aValidRandomEmail())
					.withPassword(aValidRandomPassword())	
					.withReferralUsername(request.getUsername())
					.build()
		);		    	
    }    
    
    @Test
    public void failsRegisterWhenEmailNotPresent() {    
		RegisterResponse response = registerService.register(
											new RegisterRequestBuilder()												
												.withPassword(aValidRandomPassword())
												.build()
								 );
		assertTrue("Email Validation Check Failure", response.getErrors().containsCode(RegisterFormatError.RFE01));
    }
    
    @Test
    public void failsRegisterWhenPasswordNotPresent() {    
		RegisterResponse response = registerService.register(
											new RegisterRequestBuilder()												
												.withEmail(aValidRandomEmail())
												.build()
								 );
		
		assertTrue("Password Validation Check Failure", response.getErrors().containsCode(RegisterFormatError.RFE02));
    } 
    
    @Test
    public void failsRegisterWhenNotProperiesPresent() {    
		RegisterResponse response = registerService.register(
											new RegisterRequestBuilder()												
												.build()
								 );
		
		assertTrue("Email Validation Check Failure", response.getErrors().containsCode(RegisterFormatError.RFE01));
		assertTrue("Password Validation Check Failure", response.getErrors().containsCode(RegisterFormatError.RFE02));
    }
    
    @Test
    public void failsRegisterWhenEmailIsNotCorrectFormat() {    
		RegisterResponse response = registerService.register(					
											new RegisterRequestBuilder()
												.withEmail("xxx")
												.withPassword(aValidRandomPassword())
												.build()
								 );
		
		assertTrue("Email Pattern Check Failure", response.getErrors().containsCode(RegisterFormatError.RFE03));	
    }
    
    @Test
    public void failsRegisterWhenPasswordIsNotCorrectFormat() {    
		RegisterResponse response = registerService.register(					
											new RegisterRequestBuilder()
												.withEmail(aValidRandomEmail())
												.withPassword("pass")
												.build()
								 );
		
		assertTrue("Password Pattern Check Failure", response.getErrors().containsCode(RegisterFormatError.RFE04));	
    }    
    
    @Test
    public void failsRegistrationWithDuplicateEmail() {
    	final String username = aValidRandomUsername();
    	final String username2 = aValidRandomUsername();
    	final String email = aValidRandomEmail();
		RegisterRequest registerRequest = new RegisterRequestBuilder()
		.withUsername(username)		
		.withEmail(email)
		.withPassword(aValidRandomPassword())
		.build();
		RegisterRequest registerRequest2 = new RegisterRequestBuilder()
		.withUsername(username2)		
		.withEmail(email)
		.withPassword(aValidRandomPassword())
		.build();
		RegisterResponse response = registerService.register(registerRequest2);		
    	assertThat(response.getEmail(), notNullValue());
    	response = registerService.register(registerRequest);
    	assertTrue("Email Exists Check Failure", response.getErrors().containsCode(UserError.UE01));
    }
    

}
