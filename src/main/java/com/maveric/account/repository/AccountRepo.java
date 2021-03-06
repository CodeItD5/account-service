package com.maveric.account.repository;

import com.maveric.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepo extends MongoRepository<Account, String> {
    Page<Account> findByCustomerId(String customerId, Pageable pageable);

    Optional<Account> findByCustomerIdAndId(String customerId, String accountId);

}
