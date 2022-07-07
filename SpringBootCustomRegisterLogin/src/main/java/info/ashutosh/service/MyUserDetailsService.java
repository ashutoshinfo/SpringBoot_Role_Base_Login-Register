package info.ashutosh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import info.ashutosh.model.User;
import info.ashutosh.reposetory.UserReposetory;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserReposetory reposetory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> findByemail = reposetory.findByEmail(username);

		findByemail.orElseThrow(() -> new UsernameNotFoundException("User not Found"));

		return new MyUserDetails(findByemail.get());
	}

} 
