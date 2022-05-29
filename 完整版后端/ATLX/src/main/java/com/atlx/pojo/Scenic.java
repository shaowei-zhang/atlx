package com.atlx.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Type;

/**
 * 景区信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scenic")
public class Scenic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type=IdType.AUTO)
    private Integer id;

    @Column(name="region_id")
    private Integer RegionId;//地区ID

    @Column(name="scenic_img")
    private String scenicImg;
    @Column(name="scenic_name")
    private String scenicName;

    @Column(name="scenic_introduce")
    private String scenicIntroduce;

    @Column(name = "price",columnDefinition = "decimal default 0.00")
    private String price;//价格，只支持整钱
    @Transient
    @TableField(exist = false)
    private Region region;
    @Transient
    @TableField(exist=false)
    private ScenicMessage scenicMessage;

}
