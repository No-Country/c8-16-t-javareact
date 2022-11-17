package com.nocountry.wallet.models.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "fixed_term_deposits")
public class FixedTermDepositEntity {

    @Column(nullable = false)
    private Double amount;

    @Id
    private Long userId;

    @Id
    private Long accountId;

    @Column(nullable = false)
    private Double interest;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate closingDate;

    // TODO: timestamp?, join a user y account?
}
