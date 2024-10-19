package com.spring6framework.identityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring6framework.identityservice.dto.request.PermissionRequest;
import com.spring6framework.identityservice.dto.response.PermissionResponse;
import com.spring6framework.identityservice.entities.Permission;
import com.spring6framework.identityservice.mapper.PermissionMapper;
import com.spring6framework.identityservice.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.permissionRequestToPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.permissionToPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::permissionToPermissionResponse)
                .toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
