package com.example.t1.final_work.repository;

import com.example.t1.final_work.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query(
            value = "SELECT sum(t.amount) from Transaction t" +
                    " where t.limit.id = :limitId" +
                    " and t.status = com.example.t1.final_work.model.TransactionStatus.RESERVED"
    )
    Optional<BigDecimal> countReservedTransactionsSum(@Param("limitId") UUID limitId);

    Optional<Transaction> findByTransactionId(String transactionId);
}
