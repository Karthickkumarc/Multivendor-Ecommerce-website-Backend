package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.karthick.Model.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUserName(String username);
   Boolean existsByUserName(String username);
   
}
