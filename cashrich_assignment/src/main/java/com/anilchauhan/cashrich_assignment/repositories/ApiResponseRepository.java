 package com.anilchauhan.cashrich_assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anilchauhan.cashrich_assignment.entities.ApiResponse;

@Repository
public interface ApiResponseRepository extends JpaRepository<ApiResponse, Long> {
	
}