package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
