package com.atlx.pojo;

import lombok.Data;

import javax.persistence.*;

//角色权限关联表
@Data
@Entity
@Table(name = "rolePermission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "roleId")
    private Integer roleId;

    @Column(name = "permissionId")
    private String permissionId;
}
