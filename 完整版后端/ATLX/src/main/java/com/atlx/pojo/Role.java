package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * 岗位（角色）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity    //实体是持久性对象，是存储在数据库中的记录
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;//id--主键

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

//    @Column(name = "permissions")
//    @OneToMany(targetEntity = Permission.class)//配置一对多关系
    @Transient
    private List<Permission> permissions;//角色具有的权限
}
