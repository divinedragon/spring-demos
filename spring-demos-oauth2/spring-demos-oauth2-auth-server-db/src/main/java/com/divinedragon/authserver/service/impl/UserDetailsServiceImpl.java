package com.divinedragon.authserver.service.impl;

import static java.util.Collections.singletonList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

import com.divinedragon.authserver.domain.Account;
import com.divinedragon.authserver.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Account account = accountRepository.findOneByEmail(username);

        if (account == null) {
            throw new UsernameNotFoundException("Email or Password incorrect");
        }

        if (!account.isVerified()) {
            throw new UserDeniedAuthorizationException("Account not verified");
        }

        final List<GrantedAuthority> authorities = singletonList(new SimpleGrantedAuthority(account.getRole()));

        return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
