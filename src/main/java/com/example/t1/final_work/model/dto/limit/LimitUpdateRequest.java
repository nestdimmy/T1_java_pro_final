package com.example.t1.final_work.model.dto.limit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LimitUpdateRequest {
    private BigDecimal newAmount;
}
