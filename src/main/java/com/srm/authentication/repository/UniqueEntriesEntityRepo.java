package com.srm.authentication.repository;

import com.srm.authentication.Entity.UniqueEntriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniqueEntriesEntityRepo extends JpaRepository<UniqueEntriesEntity, Long> {}
