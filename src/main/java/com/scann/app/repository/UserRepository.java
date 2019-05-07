package com.scann.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scann.app.entity.UserApp;

public interface UserRepository extends JpaRepository<UserApp,Long> {
  public UserApp findByUsername(String username);
}
