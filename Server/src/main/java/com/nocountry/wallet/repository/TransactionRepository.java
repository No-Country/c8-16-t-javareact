package com.nocountry.wallet.repository;

import com.nocountry.wallet.models.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {


    @Query("select t from TransactionEntity t where t.id = ?1")
    Optional<TransactionEntity> findById(Long id);

    @Modifying
    @Transactional
    @Query("update TransactionEntity u set u.description = :description where u.id = :id")
    void updateDescription(@Param(value = "id") Long id, @Param(value = "description") String description);

    @Query("select t from TransactionEntity t where t.account.id in ?1 order by t.transactionDate DESC")
    Page<TransactionEntity> findByAccountIds(Collection<Integer> account_ids, Pageable pageable);
}
