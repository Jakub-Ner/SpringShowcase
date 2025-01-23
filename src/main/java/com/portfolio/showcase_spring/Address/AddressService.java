package com.portfolio.showcase_spring.Address;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AddressService {
  private final AddressRepo addressRepo;

  public AddressService(AddressRepo addressRepo){
    this.addressRepo = addressRepo;
  }

  public Iterable<AddressEntity> findAll(){
    return addressRepo.findAllByOrderByIdAsc();

  }

  public void initVacantNumber(AddressEntity address){
    if (address.getVacantCount() == null){
      address.setVacantCount(new Random().nextInt(5, 10));
    }
  }

  public Integer decreaseVacantNumber(Long addressId, int decrement){
    var address = addressRepo.findById(addressId).orElse(null);
    if (address == null) return null;
    return addressRepo.decrementCount(address.getId(), decrement);
  }

  public void save(AddressEntity address){
      addressRepo.save(address);
  }

}

