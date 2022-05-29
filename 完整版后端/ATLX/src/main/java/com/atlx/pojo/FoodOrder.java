package com.atlx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 美食订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foodOrder")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "number" ,unique = true)
    private String number;//订单编号
    private Integer foodLocalId;//当地美食ID
    private Date placeOrderDatetime;//下单时间
    private Integer userId;//下单者的id

    //支付状态
    private String state;
    //支付时间
    private String paymentTime;
    //支付宝订单流水号
    private String alipayNo;

    @Transient
    private FoodLocal foodLocal;

    @Transient
    private User user;

}
