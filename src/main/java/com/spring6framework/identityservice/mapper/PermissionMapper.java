package com.spring6framework.identityservice.mapper;

import org.mapstruct.Mapper;

import com.spring6framework.identityservice.dto.request.PermissionRequest;
import com.spring6framework.identityservice.dto.response.PermissionResponse;
import com.spring6framework.identityservice.entities.Permission;

@Mapper
public interface PermissionMapper {
    Permission permissionRequestToPermission(PermissionRequest request);

    PermissionResponse permissionToPermissionResponse(Permission permission);
}
