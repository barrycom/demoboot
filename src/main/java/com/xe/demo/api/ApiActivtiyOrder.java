package com.xe.demo.api;

import com.google.gson.Gson;
import com.xe.demo.api.wxpay.CommonTools;
import com.xe.demo.api.wxpay.HttpUtil;
import com.xe.demo.api.wxpay.PayCommonUtil;
import com.xe.demo.api.wxpay.XMLUtil;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.PageAjax;
import com.xe.demo.common.utils.OpenIdUtil;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityOrder;
import com.xe.demo.service.ActivityOrderService;
import com.xe.demo.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-10-24.
 */
@RestController
@RequestMapping("/api")
public class ApiActivtiyOrder {

    @Autowired
    private ActivityOrderService activityOrderService;
    @Autowired
    private ActivityService activityService;
    @ApiOperation(value="新增活动订单", notes="新增活动订单")
    @RequestMapping(value = "addActivtiyOrder", method = RequestMethod.POST)
    public AjaxResult getActivtiyallType (@RequestBody ActivityOrder activityOrder) throws IOException, JDOMException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        /*activityOrder.setBuytime(df.format(new Date()));*/
        String orderNo="wx"+activityOrder.getUserid().substring(0,4)+"_"+System.currentTimeMillis();
        activityOrder.setId(orderNo);
        // activityOrder.setPaymemo("微信支付");
        activityOrder.setIszs("0");
        //应该是未支付目前假装支付了   activityOrder.setStatus("0");
        activityOrder.setStatus("0");

        activityOrder.setPaymoney(activityOrder.getOrdermoney());//此处真实不写
        activityOrder.setBuytime(df.format(new Date()));//此处真实不写
        activityOrder.setCreatetime(df.format(new Date()));
        activityOrderService.save(activityOrder);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(orderNo);


        String openid = activityOrder.getUserid();
        int fee = 0;
        //得到小程序传过来的价格，注意这里的价格必须为整数，1代表1分，所以传过来的值必须*100；
        if (null != activityOrder.getPaymoney()) {
            //fee = Integer.parseInt(String.valueOf(activityOrder.getPaymoney()))*100;
            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
            fee = Integer.parseInt(decimalFormat.format(activityOrder.getPaymoney().doubleValue()*100));
        }
        String did = orderNo;
        String title = "活动参加";
        String times = System.currentTimeMillis() + "";
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", "wxe2cc38ade8981367");
        packageParams.put("mch_id", "1499373952");
        packageParams.put("nonce_str", times);//时间戳
        packageParams.put("body", title);//支付主体
        packageParams.put("out_trade_no", did);//编号
        packageParams.put("total_fee", fee);//价格
        // packageParams.put("spbill_create_ip", getIp2(request));这里之前加了ip，但是总是获取sign失败，原因不明，之后就注释掉了
        packageParams.put("notify_url", "http://zallhy.mynatapp.cc/api/notify");//支付返回地址，不用纠结这个东西，我就是随便写了一个接口，内容什么都没有
        packageParams.put("trade_type", "JSAPI");//这个api有，固定的
        packageParams.put("openid", openid);//openid

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, "IvofeVGC3NpjltvBpQuCu8rAJ8croFTd");//最后这个是自己设置的32位密钥
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
        packageP.put("appId", "wxe2cc38ade8981367");
        packageP.put("nonceStr", times);//时间戳
        packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
        packageP.put("signType", "MD5");//paySign加密
        packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");


        //得到paySign
        String paySign = PayCommonUtil.createSign("UTF-8", packageP, "IvofeVGC3NpjltvBpQuCu8rAJ8croFTd");
        packageP.put("paySign", paySign);
        packageP.put("orderNo", orderNo);

        ajaxResult.setRetmsg("success");

     /*   Gson gson = new Gson();
        String json = gson.toJson(packageP);
*/
        ajaxResult.setData(packageP);

        String token= OpenIdUtil.getToken().get("access_token").toString();
        Activity activity=activityService.getActivityByid(activityOrder.getActivityid());
       //OpenIdUtil.sendMessage(token,activityOrder.getUserid(),activityOrder.getForm_id(),activity);

        //后面调用支付的。（暂时没有）
        return ajaxResult;

    }

    @ApiOperation(value="支付回调", notes="支付回调")
    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public AjaxResult notify(HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException {
        ServletInputStream in = null;
        AjaxResult ajaxResult=new AjaxResult();
        try {
            in = request.getInputStream();
            String xmlMsg = CommonTools.inputStream2String(in);
            SortedMap<String, Object> map = XMLUtil.doXMLParseTwo(xmlMsg);
            //安全校验
            if (!PayCommonUtil.checkIsSignValidFromResponseString(xmlMsg)) {
                // throw new ResponseException("返回签名错误", HttpStatus.INTERNAL_SERVER_ERROR)
                ajaxResult.setRetcode(-1);
                ajaxResult.setRetmsg("返回签名错误");
            }
            //系统订单号
            String out_trade_no=String.valueOf(map.get("out_trade_no"));
            //微信支付流水号
            String transaction_id=String.valueOf(map.get("transaction_id"));
            //修改订单状态
            ActivityOrder activityOrder= activityOrderService.selectOneById(out_trade_no);
            if(activityOrder!=null){
                activityOrder.setStatus("1");
                activityOrderService.update(activityOrder);
                String token= OpenIdUtil.getToken().get("access_token").toString();
                Activity activity=activityService.getActivityByid(activityOrder.getActivityid());
                OpenIdUtil.sendMessage(token,activityOrder.getUserid(),activityOrder.getForm_id(),activity);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
        }
        return ajaxResult;
    }

    @ApiOperation(value="发送模板消息", notes="获取活动订单")
    @RequestMapping(value = "sendtemplate", method = RequestMethod.POST)
    public AjaxResult sendtemplate(@ApiParam(value = "用户id", required = true) @RequestParam("memberid") String memberid,
                                          @ApiParam(value = "formid", required = true) @RequestParam("formid") String formid,
                                          @ApiParam(value = "orderNo", required = true) @RequestParam("orderNo") String orderNo){
        AjaxResult ajaxResult=new AjaxResult();
        ActivityOrder activityOrder= activityOrderService.selectOneById(orderNo);
        String token= OpenIdUtil.getToken().get("access_token").toString();
        Activity activity=activityService.getActivityByid(activityOrder.getActivityid());
        JSONObject jsonObject=OpenIdUtil.sendMessage(token,activityOrder.getUserid(),formid,activity);
        if(!jsonObject.get("errmsg").toString().equals("ok")){
            ajaxResult.setRetcode(-1);
            ajaxResult.setRetmsg("操作失败！");
        }

        return ajaxResult;
    }



    @ApiOperation(value="获取活动订单", notes="获取活动订单")
    @RequestMapping(value = "getActivtiyOrder", method = RequestMethod.POST)
    public PageAjax<Map> getActivtiyOrder(@ApiParam(value = "用户id", required = true) @RequestParam("memberid") String memberid,
                                          @ApiParam(value = "用户id", required = true) @RequestParam("status") String status,
                                          @ApiParam(value = "页数", required = true) @RequestParam("pageNo") int pageNo,
                                          @ApiParam(value = "显示数量", required = true) @RequestParam("pageSize") int pageSize) throws JDOMException {

        ActivityOrder activity=new ActivityOrder();
        if(!status.equals("null")){
            activity.setStatus(status);
        }
        PageAjax<ActivityOrder> page=new PageAjax<ActivityOrder>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        activity.setUserid(memberid);
        PageAjax<Map> activityOrder= activityOrderService.selectList(page,activity);
        return activityOrder;
    }


}
