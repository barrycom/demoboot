package com.xe.demo.api.wxpay;

import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017-10-20.
 */
@RestController
@RequestMapping("/api/wxpay")
public class WxPayApi {

    @Value("${wxAppId}")
    private String wxAppid;
    @Value("${mchId}")
    private String mchId;
    @Value("${apiKey}")
    private String apiKey;

    @ApiOperation(value="微信支付", notes="微信支付")
    @RequestMapping(value = "doOrder", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "用户openId", value = "openId", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "价格", value = "price", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "订单编号", value = "orderId", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "订单名称（标题)", value = "title", required = true, dataType = "String")
    })
    public void doOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //得到openid
        String openid = request.getParameter("openid");
        int fee = 0;
        //得到小程序传过来的价格，注意这里的价格必须为整数，1代表1分，所以传过来的值必须*100；
        if (null != request.getParameter("price")) {
            fee = Integer.parseInt(request.getParameter("price").toString());

        }
        System.out.println(request.getParameter("price"));
        System.out.println(fee);
        //订单编号
        String did = request.getParameter("orderId");
        //订单标题
        String title = request.getParameter("title");
        //时间戳
        String times = System.currentTimeMillis() + "";
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", wxAppid);
        packageParams.put("mch_id", mchId);
        packageParams.put("nonce_str", times);//时间戳
        packageParams.put("body", title);//支付主体
        packageParams.put("out_trade_no", did);//编号
        packageParams.put("total_fee", fee);//价格
        // packageParams.put("spbill_create_ip", getIp2(request));这里之前加了ip，但是总是获取sign失败，原因不明，之后就注释掉了
        packageParams.put("notify_url", "/notify");//支付返回地址，不用纠结这个东西，我就是随便写了一个接口，内容什么都没有
        packageParams.put("trade_type", "JSAPI");//这个api有，固定的
        packageParams.put("openid", openid);//openid
        //获取sign
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, apiKey);//最后这个是自己设置的32位密钥
        packageParams.put("sign", sign);
        //转成XML
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
        //得到含有prepay_id的XML
        String resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
        System.out.println(resXml);
        //解析XML存入Map
        Map map = XMLUtil.doXMLParse(resXml);
        System.out.println(map);
        // String return_code = (String) map.get("return_code");
        //得到prepay_id
        String prepay_id = (String) map.get("prepay_id");
        SortedMap<Object, Object> packageP = new TreeMap<Object, Object>();
        packageP.put("appId", "wxa**********2e2");//！！！注意，这里是appId,上面是appid，真怀疑写这个东西的人。。。
        packageP.put("nonceStr", times);//时间戳
        packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
        packageP.put("signType", "MD5");//paySign加密
        packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
        //得到paySign
        String paySign = PayCommonUtil.createSign("UTF-8", packageP, "x********************************4");
        packageP.put("paySign", paySign);
        //将packageP数据返回给小程序
        Gson gson = new Gson();
        String json = gson.toJson(packageP);
        PrintWriter pw = response.getWriter();
        System.out.println(json);
        pw.write(json);
        pw.close();

    }
}
