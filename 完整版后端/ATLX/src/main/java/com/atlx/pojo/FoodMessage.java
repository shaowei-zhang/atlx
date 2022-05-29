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
 * 美食留言或评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodMessage")
public class FoodMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "commentDate" ,columnDefinition = "datetime")
    private Date commentDate;//评论时间

    @Column(name = "comment" ,columnDefinition = "text")
    private String comment;//评论

    private String account;//用户名

    private Integer foodLocalId;
    @Transient
    @TableField(exist=false)
    private FoodLocal foodLocal;
}
