package com.yash.SpringSecurity.Service;

import com.yash.SpringSecurity.Model.UserPrincipal;
import com.yash.SpringSecurity.Model.Users;
import com.yash.SpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);

        if(user == null){
            System.out.println("User not found with given name.");
            throw new UsernameNotFoundException("User not found.");
        }


        return new UserPrincipal(user);
    }
}
