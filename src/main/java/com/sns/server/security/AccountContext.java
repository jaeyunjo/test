package com.sns.server.security;

import com.sns.server.account.Account;
import com.sns.server.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountContext extends User {

    @Getter
    private Account account;

    public AccountContext(String email, String password, String role) {
        super(email, password, parseAuthorities(role));
    }

    private AccountContext(Account account, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.account = account;
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account, account.getEmail(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role) {
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(String role) {
        return parseAuthorities(UserRole.getRoleByName(role));
    }
    public Account getAccount() {
        return account;
    }
}
