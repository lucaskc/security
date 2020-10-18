package com.lucas.security.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.security.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
