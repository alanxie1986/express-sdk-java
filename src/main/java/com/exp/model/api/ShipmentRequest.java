package com.exp.model.api;

import com.exp.model.dto.FreightPackage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 接口请求消息体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {

    //身份认证信息
    private Identity identity;

    //发送地址信息
    private LocationInfo locationInfo;

    //发送渠道
    private String serviceCode ;

    //包裹信息 （长宽高 体积 重量）
    private List<FreightPackage> packageInfo;

}
