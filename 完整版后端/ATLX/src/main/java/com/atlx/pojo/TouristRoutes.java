package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * 旅游路线
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "touristRoutes")
public class TouristRoutes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer RegionId;//地区ID
    private String touristName;
    private String startName;
    private String endName;
    private String passName;
    private String description;

    private String avatar;
    private String lnglat1;
    private String lnglat2;
    private String lnglat3;

}
