package com.spring6framework.identityservice.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;

import com.spring6framework.identityservice.dto.request.UserCreationRequest;
import com.spring6framework.identityservice.dto.request.UserUpdateRequest;
import com.spring6framework.identityservice.dto.response.UserResponse;
import com.spring6framework.identityservice.entities.Role;
import com.spring6framework.identityservice.entities.User;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User userCreationRequestToUser(UserCreationRequest request);

    @Mapping(target = "roles", source = "roles")
    UserResponse userToUserResponse(User user);

    default Set<String> map(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // Bỏ qua các thuộc tính null
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
