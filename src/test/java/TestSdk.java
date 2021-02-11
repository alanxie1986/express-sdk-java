import com.exp.api.ExpressClient;
import com.exp.model.api.*;
import com.exp.model.dto.FreightAddress;
import com.exp.model.dto.FreightPackage;
import com.exp.model.type.ServiceCode;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TestSdk {

    //快递接口的地址
    private String host = "https://post.digitalsystem.net/express/api";
    //接口用户号
    private String accountNumber = "1612549086370B1D5";
    //接口用户密码
    private String password = "test";
    //接口用户秘钥
    private String accessKey = "5c8e3808-70c6-4ecd-8f60-8c29a1d88ed6";
    //标签文件保存的磁盘路径
    private String labelFilePath = "/tmp";

    //演示接口使用方法
    @Test
    public void testExpressProcess() throws Exception {
        //首先实例化client
        ExpressClient client = new ExpressClient(host);

        //构建获取标签接口请求参数
        ShipmentRequest shipmentRequest = ShipmentRequest.builder()
                .identity(    //用户与认证信息
                        client.constructIdentity(accountNumber, password, accessKey)  //获取identity信息体
                )
                .serviceCode(ServiceCode.UPS_GROUND.name()) //使用的快递渠道
                .locationInfo(
                        LocationInfo.builder()
                                .sendFrom(  //发件人地址
                                        FreightAddress.builder().country("US") //发件人 国家编号
                                                .city("HACIENDA HEIGHTS")    //发件人 城市
                                                .name("Tom")      //发件人 名字
                                                .province("CA")     //发件人 州编号 这个例子是加州
                                                .addressLine1("16274 Gale Avenue")  //街道地址
                                                .company("SICOTAS") //发件人 公司
                                                .phone("6037505150")  //发件人 电话
                                                .zipcode("91745") //发件人 邮政编码
                                                .build()
                                )
                                .sendTo(   //收件人地址
                                        FreightAddress.builder().country("US") //收件人 国家编号
                                                .city("New York")           //收件人 城市
                                                .name("Carlin Chan")        //收件人 名字
                                                .province("NY")             //收件人 州编号 这个例子是纽约
                                                .addressLine1("12w W 32nd St")  //收件人 街道地址
                                                .company("XA")       //收件人 公司
                                                .phone("9908978792")    //收件人 电话
                                                .zipcode("10001")       //收件人 邮政编码
                                                .build()
                                ).build()
                )
                .packageInfo(   //包裹信息
                        Arrays.asList( //包裹可以有多个，所以使用数组表示，一个包裹会返回一个标签
                                //第一个包裹
                                FreightPackage.builder()
                                        .weight("10")   //重量 单位为 磅 LB
                                        .length("10")   //长度  单位为英寸 inch
                                        .width("10")   //宽度  单位为英寸 inch
                                        .height("10")   //高度  单位为英寸 inch
                                        .declaredValue("100")//包裹声明价值
                                        .referance1("for the kids 1") //标签附加说明
                                        .build(),
                                //第二个包裹
                                FreightPackage.builder()
                                        .weight("20")   //重量 单位为 磅 LB
                                        .length("15")   //长度  单位为英寸 inch
                                        .width("15")   //宽度  单位为英寸 inch
                                        .height("15")   //高度  单位为英寸 inch
                                        .declaredValue("100")//包裹声明价值
                                        .referance1("for the kids 2") //标签附加说明
                                        .build())
                )
                .build();

        //调用获取标签接口
        ShipmentResponse shipmentResponse = client.shipment(shipmentRequest);

        //假定返回的是成功结果
        Assert.assertEquals(shipmentResponse.getCode(),"SUCCESS");

        ResponseOrderData orderData = shipmentResponse.getData();
        String orderNumber = orderData.getOrderNumber();

        //保存label文件
        client.saveLabelFile(orderData,labelFilePath);

        List<ResponsePackageData> packageDataList = orderData.getPackages();
        for (ResponsePackageData packageData : packageDataList) {
            String fileName = packageData.getLabelFileName();
            File labelFile = new File(new StringBuilder(labelFilePath).append(File.separator)
                    .append(orderNumber).append(File.separator).append(fileName).toString());
            //验证保存文件成功
            Assert.assertTrue(labelFile.exists());
        }

        //构建查询订单参数
        QueryOrderRequest queryOrderRequest = QueryOrderRequest.builder()
                .identity(
                        //用户与认证信息
                        client.constructIdentity(accountNumber, password, accessKey)  //获取identity信息体
                )
                .orderNumber(orderNumber)   //订单编号
                .build();
        //调用查询订单接口
        QueryOrderResponse queryOrderResponse = client.orderDetail(queryOrderRequest);

        //假定返回的是成功结果
        Assert.assertEquals(queryOrderResponse.getCode(),"SUCCESS");

        //构建查询订单参数
        CancelOrderRequest cancelOrderRequest = CancelOrderRequest.builder()
                .identity(
                        //用户与认证信息
                        client.constructIdentity(accountNumber, password, accessKey)  //获取identity信息体
                )
                .orderNumber(orderNumber)   //订单编号
                .build();

        //调用取消订单接口
        CancelOrderResponse cancelOrderResponse = client.cancelOrder(cancelOrderRequest);

        //假定返回的是成功结果
        Assert.assertEquals(cancelOrderResponse.getCode(),"SUCCESS");

    }
}
