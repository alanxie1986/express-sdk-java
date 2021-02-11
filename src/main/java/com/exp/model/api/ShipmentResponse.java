package com.exp.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {
    public ShipmentResponse(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code; //消息码
    private String msg; //提示信息

    private ResponseOrderData data;
    private Identity identity;
    public boolean isSuccess(){
        return ApiConstants.SUCCESS.equals(this.getCode());
    }

}
