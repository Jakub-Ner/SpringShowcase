package com.portfolio.showcase_spring.Address;

import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Service;

@Service
public class AddressService {
  private final AddressRepo addressRepo;

  public AddressService(AddressRepo addressRepo){
    this.addressRepo = addressRepo;
  }

  public Iterable<AddressEntity> findAll(){
    return addressRepo.findAll();
  }
  
  public void save(AddressEntity address){
      addressRepo.save(address);
  }

}

