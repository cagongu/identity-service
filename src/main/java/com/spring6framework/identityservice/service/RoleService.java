package com.spring6framework.identityservice.service;

import java.util.List;

import com.spring6framework.identityservice.dto.request.RoleRequest;
import com.spring6framework.identityservice.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    public void delete(String role);
}
