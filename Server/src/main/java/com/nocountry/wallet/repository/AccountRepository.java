package com.nocountry.wallet.repository;

import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.utils.enumeration.CurrencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    @Query("select a from AccountEntity a where a.user.id = ?1")
    List<AccountEntity> findAccountsByUserId(Long id);

    @Query("select a from AccountEntity a where a.user.id = ?1 and a.currency = ?2")
    Optional<AccountEntity> findByUserByCurrency(Long id, CurrencyEnum currency);


    AccountEntity findByCurrencyAndUserId(Enum currency, Integer id);
}
