package com.vip.own.entity;

import lombok.Data;

/**
 * Created by dacheng.liu on 2017/5/26.
 */
@Data
public class MothBillsEntity {
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
    private String checkId;
    private String dateTime;
    private String monthBillNo;
    private String currFee;
    private String currNeedPayFee;
    private String currNeedPayInterest;
    private String currNeedPayCapital;
    private String currNeedPayTotal;
    private String currInterest;
    private String currCapital;
    private String currYesFee;
    private String currYesInterest;
    private String currYesCapital;
    private String currYesTotal;
    private String lastRepayTime;
    private String lateDays;
    private String overDueStatus;
    private String payOffStatus;
    private String currYesStagingAmount;
    private String currCanStagingAmount;
    private String currNoStagingAmount;
    private String uid;
}
