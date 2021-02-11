package com.exp.model.type;

public enum FreightStatus {
    PENDDING, //订单初始化状态
    PAYED,  //支付成功
    PAY_FAIL, //支付失败
    LABEL,    //成功下载Lable
    PICKUP,   //收件成功
    CANCEL_REQ, //用户请求取消
    CANCEL_CONFIRM, //确认取消
    DELIVER;  //已发送
}
