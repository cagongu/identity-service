package com.spring6framework.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring6framework.identityservice.entities.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
