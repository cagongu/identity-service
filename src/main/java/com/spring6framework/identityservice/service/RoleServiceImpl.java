package com.spring6framework.identityservice.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring6framework.identityservice.repository.PermissionRepository;
import com.spring6framework.identityservice.repository.RoleRepository;
import com.spring6framework.identityservice.dto.request.RoleRequest;
import com.spring6framework.identityservice.dto.response.RoleResponse;
import com.spring6framework.identityservice.mapper.RoleMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.roleRequestToRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.roleToRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::roleToRoleResponse)
                .toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
