package com.groupad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.Address;

/**
 * @author Boho Kim
 *
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

	public List<Address> findByEmail(String email);

}
