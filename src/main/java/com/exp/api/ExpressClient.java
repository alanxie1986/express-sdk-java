package com.exp.api;

import com.alibaba.fastjson.JSON;
import com.exp.model.api.*;
import com.exp.util.HttpUtil;
import com.exp.util.MD5Util;
import com.exp.util.RandomUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class ExpressClient {

    public ExpressClient(String host) {
        this.host = host;
    }


    private String host;

    private String shipmentUri = "/shipment";

    private String orderDetailUri = "/orderDetail";

    private String cancelOrderUri = "/cancelOrder";

    /**
     * 下订单并获取标签
     * @param request
     * @return
     */
    public ShipmentResponse shipment(ShipmentRequest request){
        ShipmentResponse response = new ShipmentResponse(ApiConstants.SUCCESS,"OK");
        String res = HttpUtil.post(host.concat(shipmentUri), JSON.toJSONString(request));
        if(res != null){
            response = JSON.parseObject(res,ShipmentResponse.class);
        }else {
            response.setCode(ApiConstants.FAIL_SYSTEM_ERR);
        }
        return response;
    }

    /**
     * 获取订单详细信息
     * @param request
     * @return
     */
    public QueryOrderResponse orderDetail(QueryOrderRequest request){
        QueryOrderResponse response = new QueryOrderResponse(ApiConstants.SUCCESS,"OK");
        String res = HttpUtil.post(host.concat(orderDetailUri),JSON.toJSONString(request));
        if(res != null){
            response = JSON.parseObject(res,QueryOrderResponse.class);
        }else {
            response.setCode(ApiConstants.FAIL_SYSTEM_ERR);
        }
        return response;
    }

    /**
     * 取消订单
     * @param request
     * @return
     */
    public CancelOrderResponse cancelOrder(CancelOrderRequest request){
        CancelOrderResponse response = new CancelOrderResponse(ApiConstants.SUCCESS,"OK");
        String res = HttpUtil.post(host.concat(cancelOrderUri),JSON.toJSONString(request));
        if(res != null){
            response = JSON.parseObject(res,CancelOrderResponse.class);
        }else {
            response.setCode(ApiConstants.FAIL_SYSTEM_ERR);
        }
        return response;
    }

    /**
     * 构建用户认证信息
     * @param accountNumber 用户号
     * @param password  密码
     * @param accessKey 秘钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Identity constructIdentity(String accountNumber, String password, String accessKey) throws NoSuchAlgorithmException {
        String requestId = System.currentTimeMillis()+ RandomUtils.generateRandomString(5);
        String ca = new StringBuilder("accountNumber=").append(accountNumber).append("&")
                .append("password=").append(password).append("&")
                .append("requestId=").append(requestId).append("&")
                .append("accessKey=").append(accessKey).toString();
        String accessToken = MD5Util.calculateMd5(ca).toUpperCase();
        Identity identity = Identity.builder()
                .accessToken(accessToken)
                .accountNumber(accountNumber)
                .requestId(requestId)
                .password(password)
                .build();
        return identity;
    }

    /**
     * 保存标签文件
     *
     * @param data     接口返回的订单数据
     * @param filePath 标签文件保存到磁盘的路径
     */
    public void saveLabelFile(ResponseOrderData data, String filePath) throws IOException {
        List<ResponsePackageData> packageDataList = data.getPackages();
        for (ResponsePackageData packageData : packageDataList) {
            String fileName = packageData.getLabelFileName();
            String labelData = packageData.getLabelFileData();
            byte[] fileData = Base64.getDecoder().decode(labelData);
            File labelFile = new File(new StringBuilder(filePath).append(File.separator)
                    .append(data.getOrderNumber()).append(File.separator).append(fileName).toString());
            FileUtils.writeByteArrayToFile(labelFile, fileData);
        }
    }

}
