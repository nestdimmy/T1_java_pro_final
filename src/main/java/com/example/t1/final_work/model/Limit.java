package com.example.t1.final_work.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limits")
public class Limit {

    @Id
    private UUID id = UUID.randomUUID();
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "current", nullable = false)
    private BigDecimal current;
    @OneToMany(mappedBy = "limit")
    private List<Transaction> transactions;
}
