package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 本地食物
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_local")
public class FoodLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Column(name = "region_id")
    private Integer RegionId;//地区ID

    @Column(name = "food_img")
    private String foodImg;

    @Column(name = "food_name")
    private String foodName;
//    private Integer foodClassId;

    @Column(name = "price",columnDefinition = "decimal default 0.00")
    private String price;//价格，只支持整钱

    @Column(name = "food_class")
    private String foodClass;
    @Column(name = "food_introduce")
    private String foodIntroduce;

    @Transient
    @TableField(exist = false)
    private Region region;

    @Transient
    @TableField(exist = false)
    private FoodMessage foodMessage;



}
