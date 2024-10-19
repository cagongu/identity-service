package com.spring6framework.identityservice.service;

import java.util.List;

import com.spring6framework.identityservice.dto.request.PermissionRequest;
import com.spring6framework.identityservice.dto.response.PermissionResponse;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
