package com.elhagez.flightreservaion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elhagez.flightreservaion.entities.User;
import com.elhagez.flightreservaion.repos.UserRespository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRespository userRespository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRespository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found for email"+username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail()
				,user.getPassword(),user.getRoles());
	}

}
