package com.cloud.limit.dto;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2022/11/7 14:00
 * @mark CustInfo
 */
@Data
public class CreditLimit {

    private String applSeq;

    private PersonalInfo personalInfo;// 个人信息

    private SpouseInfo spouseInfo;// 配偶信息

    private ContactInfo contactInfo; // 联系人信息

    private Unit unit; // 单位信息

    private OccupationInfo occupationInfo; // 职业信息


    @Data
    class ContactInfo {
        private String custName;

        private String mobilePhone;

        private String idNo;

        private String isMarried;

    }


    @Data
    class Unit {

        private String unitName;

        private String unitAddress;

        private String unitMobilePhone;
    }


    @Data
    class OccupationInfo {

        private String occuTyp;

        private String occuLevel;

    }


}
