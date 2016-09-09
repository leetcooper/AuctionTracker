package com.byhiras.service;

import static org.springframework.util.StringUtils.hasText;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.byhiras.security.model.User;
import com.byhiras.security.model.dao.UserRepository;
import com.byhiras.service.exception.UserDoesNotExistException;
import com.byhiras.service.exception.UserExistsException;

@Service
public class RegisterServiceImpl implements RegisterService{
	private static final Logger LOG = LoggerFactory.getLogger(RegisterServiceImpl.class);
	private final UserRepository userSessionRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Inject
	public RegisterServiceImpl(final UserRepository userSessionRepository, final PasswordEncoder passwordEncoder){
		this.userSessionRepository = userSessionRepository;
		this.passwordEncoder = passwordEncoder;
	}
		
	public User register(final String username, final String password, final String email, final String referralUser) throws UserExistsException {
		LOG.info("Registering: User[{}]", username);
		
		User existingUser = userSessionRepository.findByEmail(email);		
		if(existingUser != null){
			throw new UserExistsException();
		}		
		existingUser = userSessionRepository.findByUsername(username);		
		if(existingUser != null){
			throw new UserExistsException();
		}
		if(hasText(referralUser) && userSessionRepository.findByUsername(referralUser) == null){
			throw new UserDoesNotExistException();
		}
		
		final User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(false);
		return userSessionRepository.save(user);
	}
	
}
