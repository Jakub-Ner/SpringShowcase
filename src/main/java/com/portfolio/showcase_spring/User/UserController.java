package com.portfolio.showcase_spring.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping(value="/all", produces="application/json")
  public Iterable<UserEntity> getAllUsers(){
    return userService.findAll();
  }
  
  @PostMapping(value="/one")
  public void addUser(@RequestBody UserEntity user){
    userService.save(user);
  }

  @GetMapping(value="/", produces="application/json")
  public String hello(){
    return "Hello";
  }

}

