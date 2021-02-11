package com.exp.model.api;

public class CancelOrderResponse {
    public CancelOrderResponse(String code, String msg){
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseOrderData getData() {
        return data;
    }

    public void setData(ResponseOrderData data) {
        this.data = data;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
}
