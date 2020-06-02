package com.sns.server.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByEmail(String email);

    Account findBySocialId(Long socialId);
}
