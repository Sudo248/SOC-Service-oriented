package com.sudo248.authservice.repository;

import com.sudo248.authservice.repository.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT * FROM account WHERE account.phoneNumber = :phoneNumber LIMIT 1", nativeQuery = true)
    Account getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value =
            "SELECT " +
            "   CASE WHEN EXISTS(" +
            "       SELECT 1 FROM account" +
            "       WHERE account.phoneNumber = :phoneNumber " +
            "       LIMIT 1" +
            "   )" +
            "   THEN 'true'" +
            "   ELSE 'false'" +
            "   END"
            , nativeQuery = true)
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
