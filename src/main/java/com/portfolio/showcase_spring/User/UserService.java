package com.portfolio.showcase_spring.User;

import com.portfolio.showcase_spring.Address.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final AddressService addressService;

    public UserService(UserRepo userRepo, AddressService addressService) {
        this.userRepo = userRepo;
        this.addressService = addressService;
    }


    public List<UserEntity> fetchUsersFrom3rdParty(String url) {
        RestTemplate restTemplate = new RestTemplate();
        UserEntity[] users = restTemplate.getForObject(url, UserEntity[].class);
        if (users == null) return new ArrayList<>();
        return Arrays.asList(users);
    }

    public UserEntity findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public void save(UserEntity user) {
        user.setId(null);
        addressService.initVacantNumber(user.getAddress());
        userRepo.save(user);
    }

    public Iterable<UserEntity> findAll() {
        return userRepo.findAll();
    }
}
