package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by pv on 19/2/18.
 */
@RestController
public class TestLoginController {

	@Autowired MyAuthenticationManager authenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody LoginInput loginInput){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null && !((String)auth.getPrincipal()).equals("anonymousUser") && auth.isAuthenticated())
			return "Authenticated. ";

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginInput.getUsername(), loginInput.getPassword());

		auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if(!auth.isAuthenticated()) return "Hard luck!";

		SecurityContextHolder.getContext().setAuthentication(auth);
		return "Authenticated. ";
	}
}
