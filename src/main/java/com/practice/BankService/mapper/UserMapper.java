package com.practice.BankService.mapper;

import com.practice.BankService.dto.User;
import com.practice.BankService.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserMapper extends  BaseMapper<UserEntity, User>
{
private BankAccountMapper bankAccountMapper = new BankAccountMapper();
    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
       UserEntity entity= new UserEntity();
       if(dto!=null)
       {
           BeanUtils.copyProperties(dto,entity,"accounts");
           entity.setAccounts();
       }
        return entity;
    }

    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User dto = new User();
        if(entity != null)
        {
            BeanUtils.copyProperties(entity,dto,"accounts");
            dto.setBankAccounts();
        }
        return dto;
    }

}
