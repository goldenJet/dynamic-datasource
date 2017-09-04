package com.example.demo.repository;

import com.example.demo.config.annotation.TargetDataSource;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Jet.chen on 2017/9/4.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findById(Long id);

    @TargetDataSource("test2")
    @Query(value = "select * from usr where id = ?1", nativeQuery = true)
    User findByIdFromTest2(Long id);
}
