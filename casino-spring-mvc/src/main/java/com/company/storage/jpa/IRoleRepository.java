package com.company.storage.jpa;

import com.company.storage.models.StorageRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<StorageRole, Long> {

}
