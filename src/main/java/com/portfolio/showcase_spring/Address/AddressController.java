package com.portfolio.showcase_spring.Address;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    public Iterable<AddressEntity> getAllUsers() {
        return addressService.findAll();
    }

    @PostMapping("/{id}/modify/{quantity}")
    public void modifyVacantCount(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        addressService.publishOrder(id, quantity);
    }

}
