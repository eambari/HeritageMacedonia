package com.heritage.mkheritage.repository;

import com.heritage.mkheritage.model.HeritageSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeritageSiteRepository extends JpaRepository<HeritageSite, Long> {
    List<HeritageSite> findByNameContaining(String name);
}