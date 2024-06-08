package com.example.blogheavenapplication.services;

import com.example.blogheavenapplication.entities.Address;
import com.example.blogheavenapplication.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Klass: AddressService - Logiken för att hantera adresser.
@Service
public class AddressService implements AddressServiceInterface {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

}
