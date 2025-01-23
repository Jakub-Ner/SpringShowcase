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

    @PostMapping("/{id}/decrement/{decrement}")
    public Integer decrementVacantCount(@PathVariable("id") Long id, @PathVariable("decrement") Integer decrement) {
        return addressService.decreaseVacantNumber(id, decrement);
    }

}
