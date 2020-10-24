package com.example.order.po;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商家实体类
 */
@Entity
public class BusinessInfo {

    @Id
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkerName() {
        return linkerName;
    }

    public void setLinkerName(String linkerName) {
        this.linkerName = linkerName;
    }

    public String getLinkerIdentify() {
        return linkerIdentify;
    }

    public void setLinkerIdentify(String linkerIdentify) {
        this.linkerIdentify = linkerIdentify;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sales=" + sales +
                ", score=" + score +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", linkerName='" + linkerName + '\'' +
                ", linkerIdentify='" + linkerIdentify + '\'' +
                ", scope='" + scope + '\'' +
                ", industryId='" + industryId + '\'' +
                '}';
    }
}
