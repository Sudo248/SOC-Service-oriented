package com.sudo248.authservice.repository;

import com.sudo248.authservice.repository.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT * FROM accounts WHERE accounts.phone_number = :phoneNumber LIMIT 1", nativeQuery = true)
    Account getAccountByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Transactional
    @Query(value =
            "SELECT " +
            "   CASE WHEN EXISTS(" +
            "       SELECT 1 FROM accounts" +
            "       WHERE accounts.phone_number = :phoneNumber " +
            "       LIMIT 1" +
            "   )" +
            "   THEN 'true'" +
            "   ELSE 'false'" +
            "   END"
            , nativeQuery = true)
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET accounts.is_validated=true WHERE accounts.user_id = :userId", nativeQuery = true)
    void validate(@Param("userId") String userId);
}
