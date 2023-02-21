package com.example.algamoneyapi.repository;

import com.example.algamoneyapi.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractAuditableRepository<User, Long> {
}
