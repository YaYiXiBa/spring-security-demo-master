package org.zxp.securitydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zxp.securitydemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
