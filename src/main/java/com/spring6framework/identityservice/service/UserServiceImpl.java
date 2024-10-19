package com.spring6framework.identityservice.service;

import com.spring6framework.identityservice.dto.request.UserCreationRequest;
import com.spring6framework.identityservice.dto.request.UserUpdateRequest;
import com.spring6framework.identityservice.dto.response.UserResponse;
import com.spring6framework.identityservice.entities.Role;
import com.spring6framework.identityservice.entities.User;
import com.spring6framework.identityservice.exception.AppException;
import com.spring6framework.identityservice.exception.ErrorCode;
import com.spring6framework.identityservice.mapper.ProfileMapper;
import com.spring6framework.identityservice.mapper.UserMapper;
import com.spring6framework.identityservice.repository.RoleRepository;
import com.spring6framework.identityservice.repository.UserRepository;
import com.spring6framework.identityservice.repository.httpclient.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    ProfileMapper profileMapper;
    ProfileClient profileClient;

    @Override
    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.userCreationRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        try {
            Role role = roleRepository.findRoleByName("USER");
            roles.add(role);
            user.setRoles(roles);
        } catch (AppException exception) {
            throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
        }

        user = userRepository.save(user);


        var profileRequest = profileMapper.userCreationRequesttoProfileCreationRequest(request);
        profileRequest.setUserId(user.getId());

        profileClient.createProfile(profileRequest);

        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UUID userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        if (StringUtils.hasText(request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.userToUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse)
                .toList();
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserResponse getUser(UUID id) {
        log.info("In method get user by Id");
        return userMapper.userToUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.userToUserResponse(user);
    }
}
