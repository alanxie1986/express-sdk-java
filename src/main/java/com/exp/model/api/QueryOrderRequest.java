package com.exp.model.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口请求消息体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrderRequest {

    //身份认证信息
    private Identity identity;

    //订单编号
    private String orderNumber;

}
