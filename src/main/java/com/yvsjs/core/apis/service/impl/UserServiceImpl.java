package com.yvsjs.core.apis.service.impl;

import com.yvsjs.core.apis.dto.User;
import com.yvsjs.core.apis.entity.UserEntity;
import com.yvsjs.core.apis.mapper.UserMapper;
import com.yvsjs.core.apis.repository.UserRepository;
import com.yvsjs.core.apis.requests.UserCreationRequest;
import com.yvsjs.core.apis.response.UserCreationResponse;
import com.yvsjs.core.apis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User readUser(String identification) {
        UserEntity userEntity = userRepository.findByIdentificationNumber(identification)
                .orElseThrow(() -> new RuntimeException("IdentificationNumber not found: " + identification));
        return userMapper.convertToDto(userEntity);
    }

    @Override
    public List<User> readUsers(Pageable pageable) {
        return userMapper.convertToDtoList(userRepository.findAll(pageable).getContent());
    }

    @Override
    public UserCreationResponse createUser(UserCreationRequest userCreationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(userCreationRequest.getFirstname());
        userEntity.setLastname(userCreationRequest.getLastname());
        userEntity.setEmail(userCreationRequest.getEmail());
        userEntity.setIdentificationNumber(userCreationRequest.getIdentificationNumber());
        UserEntity updateUserEntity = userRepository.save(userEntity);
        UserCreationResponse userCreationResponse = new UserCreationResponse();
        userCreationResponse.setStatusCode(200);
        userCreationResponse.setMessage("User Created and the id :" + updateUserEntity.getId());
        return userCreationResponse;
    }

}
