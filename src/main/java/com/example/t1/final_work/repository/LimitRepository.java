package com.example.t1.final_work.repository;

import com.example.t1.final_work.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface LimitRepository extends JpaRepository<Limit, UUID> {

    Optional<Limit> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Limit l SET l.current = l.current - :amount WHERE l.id = :id")
    void decreaseLimit(@Param("id") UUID id,
                       @Param("amount") BigDecimal amount
    );

    @Modifying
    @Transactional
    @Query("UPDATE Limit l SET l.current = :defaultValue")
    void resetLimitToDefault(@Param("defaultValue") BigDecimal defaultAmount);
}
