package com.portfolio.showcase_spring.Address;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value="/all", produces="application/json")
    public Iterable<AddressEntity> getAllUsers(){
        return addressService.findAll();
    }
}
