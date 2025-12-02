package com.example.t1.final_work.service.limit;

import com.example.t1.final_work.error_handling.exceptions.InvalidNewLimitException;
import com.example.t1.final_work.error_handling.exceptions.NotEnoughLimitException;
import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitValidator {

    private final TransactionRepository transactionRepository;

    public void validateOnLimitChange(Limit limit, BigDecimal amount) {
        Optional<BigDecimal> reservedSum = transactionRepository
                .countReservedTransactionsSum(limit.getId());

        if (reservedSum.isPresent()) {
            BigDecimal reserved = reservedSum.get();
            if (reserved.compareTo(amount) > 0) {
                throw new InvalidNewLimitException();
            }
        }
    }

    public void validateOnLimitReserve(Limit limit, BigDecimal amount) {
        BigDecimal newLimit = limit.getCurrent().subtract(amount);

        if (newLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughLimitException();
        }

        Optional<BigDecimal> reservedSum = transactionRepository
                .countReservedTransactionsSum(limit.getId());
        if (reservedSum.isPresent()) {
            BigDecimal reserved = reservedSum.get();
            if (newLimit.subtract(reserved).compareTo(BigDecimal.ZERO) < 0) {
                throw new NotEnoughLimitException();
            }
        }
    }
}
