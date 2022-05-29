package com.atlx.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 景区收藏记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scenicCollection")
public class ScenicCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer scenicId;
    private String account;

    @Transient
    @TableField(exist = false)
    private Scenic scenic;
}
