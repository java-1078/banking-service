package com.practice.BankService.service;

import com.practice.BankService.dto.User;
import com.practice.BankService.entity.UserEntity;
import com.practice.BankService.mapper.UserMapper;
import com.practice.BankService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserMapper userMapper = new UserMapper();
    private final UserRepository userRepository;
    public User readUser(String identification)
    {
        UserEntity userEntity= userRepository.findByIdentificationNumber(identification).get();
        return userMapper.convertToDto(userEntity);

    }
    public List<User> readUsers(Pageable pageable) //?
    {
        return
                userMapper.convertToDtoList(userRepository.findAll(pageable).getContent());

    }



}
