package com.example.blogheavenapplication.repositories;

import com.example.blogheavenapplication.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// AddressReopository - Handtaget till address tabellen
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
