package com.shiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiva.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
