package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 美食收藏记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodCollection")
public class FoodCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer foodLocalId;

    private String account;

    //连表查询，导入foodLocal类
    @Transient
    @TableField(exist = false)
    private FoodLocal foodLocal;

}
