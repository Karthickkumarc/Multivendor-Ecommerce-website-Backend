package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Deal;

public interface DealRepository extends JpaRepository<Deal,Long> {

}
