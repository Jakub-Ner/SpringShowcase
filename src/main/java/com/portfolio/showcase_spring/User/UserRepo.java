package com.portfolio.showcase_spring.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAllByOrderByIdAsc();
}
