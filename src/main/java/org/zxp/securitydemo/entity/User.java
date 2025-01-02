package org.zxp.securitydemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.lang.annotation.Target;

@Data
public class User {
    @TableId(value="id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
}
