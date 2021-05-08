package io.rl.tm_api.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import io.rl.tm_api.entity.User;

public interface UserRepository extends Repository<User, Integer> {
    
    Optional<User> findByUsername(String username);

    User findById(int id);

    // @Query("select u from user u")
    List<User> findAll();

    User save(User user);

    User deleteById(int id);

}
