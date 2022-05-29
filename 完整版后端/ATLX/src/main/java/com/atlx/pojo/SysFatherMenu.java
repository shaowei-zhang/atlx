package com.atlx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台父菜单栏
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sysFatherMenu")
public class SysFatherMenu{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title" ,unique = true)
    private String title;

    @Column(name = "name" ,unique = true)
    private String name;

    private String icon;

    @Transient //使得创建表时，该字段不进行持久化（即被忽略）
    private List<SysChildrenMenu> children;
}
