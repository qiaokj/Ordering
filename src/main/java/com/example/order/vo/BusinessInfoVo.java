package com.example.order.vo;

import java.io.Serializable;

public class BusinessInfoVo implements Serializable {

    private String id;

    private String name;

    // 销量
    private Integer sales;

    // 评分
    private Double score;

    /**
     * 实体店地址
     */
    private String address;

    /**
     * 责任人(店主)电话
     */
    private String phone;

    /**
     * 责任人(店主)名称
     */
    private String linkerName;

    /**
     * 责任人(店主)证件号码
     */
    private String linkerIdentify;

    // 经营范围
    private String scope;

    // 工商注册号
    private String industryId;
}
