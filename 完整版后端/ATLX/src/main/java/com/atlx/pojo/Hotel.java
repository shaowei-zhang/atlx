package com.atlx.pojo;

/**
 * @author zhangshaowei
 * @date 2022/5/22 12:54
 * @description
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 酒店信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;//酒店ID
    private Integer RegionId;
    private String hotelImg;//图片
    private String hotelName;//酒店名称
    private String hotelAddr;//酒店地址
    private String hotelRemark;//酒店描述
    @Column(name = "price",columnDefinition = "decimal default 0.00")
    private String price;//价格，只支持整钱

    @Transient
    @TableField(exist = false)
    private  Region region;

    @Transient
    @TableField(exist=false)
    private HotelMessage hotelMessage;


}
