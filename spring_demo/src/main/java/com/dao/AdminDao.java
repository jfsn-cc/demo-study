package com.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dao.mapper.UmsAdminMapper;
import com.model.UmsAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @创建人 ly
 * @时间 03-27
 * @描述
 */
@Repository
@RequiredArgsConstructor
public class AdminDao {
    private final UmsAdminMapper adminMapper;

    public UmsAdmin getByName(String userName) {
        LambdaQueryWrapper<UmsAdmin> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UmsAdmin::getUsername, userName);
        return adminMapper.selectOne(queryWrapper);
    }
}
