package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pv on 19/2/18.
 */
@Component("authenticationManager")
public class MyAuthenticationManager extends AbstractUserDetailsAuthenticationProvider {


	@Autowired UserRepository userRepository;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken myToken) throws AuthenticationException {
		com.example.demo.User user = userRepository.findByUsername((String)myToken.getPrincipal());
		if(user!=null && user.getPassword().equals(myToken.getCredentials()))
			return new User(user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>());

		throw new AuthenticationCredentialsNotFoundException("Auth failed");

	}
}
