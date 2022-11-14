package com.nocountry.wallet.repository;



import com.nocountry.wallet.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    public Set<RoleEntity> findByName(String roleName);
}
