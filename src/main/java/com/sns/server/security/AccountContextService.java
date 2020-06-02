package com.sns.server.security;

import com.sns.server.account.Account;
import com.sns.server.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountContextService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public AccountContext loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if(account == null) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }
        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account) {
        return AccountContext.fromAccountModel(account);
    }
}
