package com.byhiras.security.model.dao;

import javax.transaction.Transactional;

import org.exparity.expectamundo.Expectamundo;
import org.exparity.stub.random.RandomBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.byhiras.security.TestConfig;
import com.byhiras.security.model.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = TestConfig.class)
@Transactional
@Rollback(value=true)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test	
	public void canSaveAndRetrieveUser(){
		final User randomUser = RandomBuilder.aRandomInstanceOf(User.class);
		User savedUser = userRepository.save(randomUser);
		final User persistedUser = userRepository.findOne(savedUser.getId());
		final User expectedUser = Expectamundo.prototype(User.class);
		Expectamundo.expect(expectedUser.getUsername()).isEqualTo(randomUser.getUsername());
		Expectamundo.expect(expectedUser.getFirstname()).isEqualTo(randomUser.getFirstname());
		Expectamundo.expect(expectedUser.getFullname()).isEqualTo(randomUser.getFullname());
		Expectamundo.expect(expectedUser.getPassword()).isEqualTo(randomUser.getPassword());
		Expectamundo.expect(expectedUser.getReferralUsername()).isEqualTo(randomUser.getReferralUsername());
		Expectamundo.expect(expectedUser.getEmail()).isEqualTo(randomUser.getEmail());
		Expectamundo.expect(expectedUser.getSurname()).isEqualTo(randomUser.getSurname());
		Expectamundo.expect(expectedUser.isEnabled()).isEqualTo(randomUser.isEnabled());
		Expectamundo.expectThat(persistedUser).matches(expectedUser);		
	}

}
