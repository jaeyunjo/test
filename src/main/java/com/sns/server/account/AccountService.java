package com.sns.server.account;

import com.sns.server.enums.UserRole;
import com.sns.server.exceptions.account.AccountNotFoundException;
import com.sns.server.exceptions.account.EmailConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    // private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account create(AccountDto.Create accountDto) {
        Account account = findByEmail(accountDto.getEmail());
        if (account != null) {
            throw new EmailConflictException();
        }
        return accountRepository.save(AccountDto.Create.builder()
                .email(accountDto.getEmail())
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .gender(accountDto.getGender())
                .birth(accountDto.getBirth())
                .tel(accountDto.getTel())
                .userRole(UserRole.ROLE_USER)
                .socialId(null)
                .profileHref(null)
                .build()
                .convert());
    }

    @Transactional
    public Account update(Long id, AccountDto.Update accountDto) {
        // TODO: 비밀번호 변경을 분리
        // TODO: 회원정보 변경 분리(firstName, lastName, tel)
        get(id).setFirstName(accountDto.getFirstName());
        get(id).setLastName(accountDto.getLastName());
        get(id).setPassword(passwordEncoder.encode(accountDto.getPassword()));
        get(id).setTel(accountDto.getTel());

        return get(id);
    }

    @Transactional
    public Account delete(Long id) {
        get(id).setDeleted(LocalDateTime.now());
        return get(id);
    }

    @Transactional(readOnly = true)
    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

}
