package com.backendprogramming.projectbuilding.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final AppUserRepository repository;
	
	@Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = repository.findByUsername(username);
    	//log.info("username is :" + curruser.getUsername());
    	//log.info("pwHash is :" + curruser.getPasswordHash());
    	//curruser.setPasswordHash("$2a$12$N/yX/WDXAj28j.6SOgnmmefKckgQ1E2Ot92VVpXaJPnvhzmvmNe7O");
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    } 

}