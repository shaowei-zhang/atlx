package com.atlx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *后台子菜单栏
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sysChildrenMenu")
public class SysChildrenMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title" ,unique = true)
    private String title;

    @Column(name = "name" ,unique = true)
    private String name;

    private String icon;

    @Column(name = "component" ,unique = true)
    private String component;

    @Column(name = "path" ,unique = true)
    private String path;

    @Column(name = "fatherMenuId",unique = true)
    private Integer fatherMenuId;
}
