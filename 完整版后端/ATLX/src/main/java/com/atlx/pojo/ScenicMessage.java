package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 景区留言或评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scenicMessage")
public class ScenicMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "commentDate", columnDefinition = "datetime")
    private Date commentDate;//评论时间

    @Column(name = "comment", columnDefinition = "text")
    private String comment;//评论

    private String account;//用户名

    private Integer scenicId;

    @Transient
    @TableField(exist=false)
    private Scenic scenic;

}
