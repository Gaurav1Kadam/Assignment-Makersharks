package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.beans.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
}