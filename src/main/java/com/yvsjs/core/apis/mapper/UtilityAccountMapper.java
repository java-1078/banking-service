package com.yvsjs.core.apis.mapper;

import com.yvsjs.core.apis.dto.UtilityAccount;
import com.yvsjs.core.apis.entity.UtilityAccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UtilityAccountMapper extends BaseMapper<UtilityAccountEntity, UtilityAccount> {

    @Override
    public UtilityAccountEntity convertToEntity(UtilityAccount dto, Object... args) {
        UtilityAccountEntity entity = new UtilityAccountEntity();
        {
            if (dto != null) {
                BeanUtils.copyProperties(dto, entity);
            }
        }

        return entity;
    }

    @Override
    public UtilityAccount convertToDto(UtilityAccountEntity entity, Object... args) {
        UtilityAccount dto = new UtilityAccount();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
