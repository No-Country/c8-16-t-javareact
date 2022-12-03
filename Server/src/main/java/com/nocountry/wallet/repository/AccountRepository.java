package com.nocountry.wallet.repository;

import com.nocountry.wallet.models.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    @Query("select a from AccountEntity a where a.user.id = ?1")
    List<AccountEntity> findAccountsByUserId(Long id);

    AccountEntity findByCurrencyAndUserId(Enum currency, Integer id);
}
