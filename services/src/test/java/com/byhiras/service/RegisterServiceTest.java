package com.byhiras.service;

import static org.exparity.stub.random.RandomBuilder.aRandomString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.byhiras.security.model.User;
import com.byhiras.security.model.dao.UserRepository;
import com.byhiras.service.exception.UserDoesNotExistException;
import com.byhiras.service.exception.UserExistsException;

public class RegisterServiceTest {
	@Test
	public void canRegister() {
		final UserRepository userSessionRepository = mock(UserRepository.class);
		final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
		final RegisterService registerService = new RegisterServiceImpl(userSessionRepository, passwordEncoder);
		final String referralUser = aRandomString();
		when(userSessionRepository.findByUsername(referralUser)).thenReturn(new User());
		registerService.register(aRandomString(), "password", "email", referralUser);
		verify(passwordEncoder, times(1)).encode("password");
		verify(userSessionRepository, times(1)).save(any(User.class));
	}

	@Test(expected = UserExistsException.class)
	public void failWithEmailExistsException() {
		final String email = "test@email.com";
		final UserRepository userSessionRepository = mock(UserRepository.class);
		when(userSessionRepository.findByEmail(email)).thenReturn(new User());
		final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
		final RegisterService registerService = new RegisterServiceImpl(userSessionRepository, passwordEncoder);
		registerService.register(aRandomString(), "password", email, aRandomString());
	}

	@Test(expected = UserExistsException.class)
	public void failWithUserExistsException() {
		final String username = "test@email.com";
		final UserRepository userSessionRepository = mock(UserRepository.class);
		when(userSessionRepository.findByUsername(username)).thenReturn(new User());
		final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
		final RegisterService registerService = new RegisterServiceImpl(userSessionRepository, passwordEncoder);
		registerService.register(username, "password", aRandomString(), aRandomString());
	}
	
	@Test(expected = UserDoesNotExistException.class)
	public void failWithUserDoesNotExistsException() {
		final String username = "test@email.com";
		final String referal = "referal@email.com";
		final UserRepository userSessionRepository = mock(UserRepository.class);
		when(userSessionRepository.findByUsername(username)).thenReturn(null);
		when(userSessionRepository.findByUsername(referal)).thenReturn(null);
		final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
		final RegisterService registerService = new RegisterServiceImpl(userSessionRepository, passwordEncoder);
		registerService.register(username, "password", aRandomString(), referal);
	}
	
	@Test
	public void canRegisterWithNullReferral() {
		final UserRepository userSessionRepository = mock(UserRepository.class);
		final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
		final RegisterService registerService = new RegisterServiceImpl(userSessionRepository, passwordEncoder);
		final String referralUser = null;		
		registerService.register(aRandomString(), "password", "email", referralUser);
		verify(passwordEncoder, times(1)).encode("password");
		verify(userSessionRepository, times(1)).save(any(User.class));
	}	
}
