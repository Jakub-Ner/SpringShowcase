package com.portfolio.showcase_spring.Address;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepo extends CrudRepository<AddressEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE address SET vacant_count=vacant_count+:quantity WHERE id=:id AND vacant_count+:quantity >= 0", nativeQuery = true)
    int modifyVacantCount(@Param("id") Long id, @Param("quantity") Integer quantity);

    List<AddressEntity> findAllByOrderByIdAsc();
}
