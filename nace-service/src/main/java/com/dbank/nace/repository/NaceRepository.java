package com.dbank.nace.repository;

import com.dbank.nace.entity.NaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaceRepository extends JpaRepository<NaceData,Long> {
    NaceData findNaceDataByOrderId(Long id);
}
