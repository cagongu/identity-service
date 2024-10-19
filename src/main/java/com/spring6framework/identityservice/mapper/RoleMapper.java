package com.spring6framework.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.spring6framework.identityservice.dto.request.RoleRequest;
import com.spring6framework.identityservice.dto.response.RoleResponse;
import com.spring6framework.identityservice.entities.Role;

@Mapper
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role roleRequestToRole(RoleRequest request);

    RoleResponse roleToRoleResponse(Role role);
}
