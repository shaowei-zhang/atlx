package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id //设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置自增
    @Column(name = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column(name = "account",unique = true)
    private String account;//账号，登录时唯一认证
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;//头像
    @Column(name = "salt")
    private String salt;//盐值
    @Column(name = "username")
    private String username;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "roleId",columnDefinition = "int default 1")//数据库数据role的id为1的是普通用户
    private Integer roleId;

    @Transient
    private Role role;

}
