package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 酒店、宾馆收藏记录
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 酒店、宾馆收藏记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotelCollection")
public class HotelCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type= IdType.AUTO)
    private Integer id;

    private Integer hotelId;
    private String account;
    @Transient
    @TableField(exist = false)
    private Hotel hotel;
}
