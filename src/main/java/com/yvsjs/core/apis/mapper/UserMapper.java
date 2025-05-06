package com.yvsjs.core.apis.mapper;

import com.yvsjs.core.apis.dto.User;
import com.yvsjs.core.apis.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper extends BaseMapper<UserEntity, User> {

    private final BankAccountMapper bankAccountMapper;

    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
        UserEntity entity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity, "accounts");
            entity.setAccounts(bankAccountMapper.convertToEntityList(dto.getBankAccounts()));
        }
        return entity;
    }

    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User dto = new User();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto, "accounts");
            dto.setBankAccounts(bankAccountMapper.convertToDtoList(entity.getAccounts()));
        }
        return dto;
    }

}
