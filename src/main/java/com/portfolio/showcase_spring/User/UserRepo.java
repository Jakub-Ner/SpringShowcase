package com.portfolio.showcase_spring.User;

import org.springframework.data.repository.CrudRepository;
public interface UserRepo extends CrudRepository<UserEntity, Integer> {}
