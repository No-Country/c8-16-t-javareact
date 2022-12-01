package com.nocountry.wallet.repository;

import com.nocountry.wallet.models.entity.FixedTermDepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedTermDepositRepository extends JpaRepository<FixedTermDepositEntity, Long> {

}
