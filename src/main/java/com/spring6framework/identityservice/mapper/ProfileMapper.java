package com.spring6framework.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.spring6framework.identityservice.dto.request.ProfileCreationRequest;
import com.spring6framework.identityservice.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "userId", ignore = true)
    ProfileCreationRequest userCreationRequesttoProfileCreationRequest(UserCreationRequest request);
}
