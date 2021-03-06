package com.xe.demo.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.qiniu.util.StringUtils;

import com.xe.demo.api.wxpay.CommonTools;
import com.xe.demo.api.wxpay.HttpUtil;
import com.xe.demo.api.wxpay.PayCommonUtil;
import com.xe.demo.api.wxpay.XMLUtil;
import com.xe.demo.common.hx.api.IMUserAPI;
import com.xe.demo.common.pojo.AjaxResult;
import com.xe.demo.common.pojo.SzmData;
import com.xe.demo.common.utils.*;
import com.xe.demo.mapper.*;
import com.xe.demo.model.*;
import com.xe.demo.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


import io.swagger.annotations.Authorization;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017-10-13.
 */
@RestController
@RequestMapping("/api")
public class ApiMember {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private RegionsMapper regionsMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private DynamicTypeService dynamicTypeService;
    @Autowired
    private DynamicTypeMapper dynamicTypeMapper;
    @Autowired
    private UploadUtil uploadUtil;
    @Autowired
    private UserCollecTindustryService userCollecTindustryService;
    @Autowired
    private UserCollecTindustryMapper userCollecTindustryMapper;
    @Autowired
    private MemberBuyService memberBuyService;
    @Autowired
    private MemberBuyMapper memberBuyMapper;
    @Autowired
    private SendcardlogService sendcardlogService;
    @Autowired
    private DaysignService daysignService;
    @Autowired
    private IMUserAPI iMUserAPI;
    private static final String STATUC_SUCCESS = "SUCCESS";

    //@Authorization("需token")
    @ApiOperation(value = "根据ID获取用户信息", notes = "根据ID获取用户信息")
    @RequestMapping(value = "getMember", method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMember(@RequestParam(value = "memberId", required = false) String memberId) {
        JsonResult r = new JsonResult();
        Condition condition = new Condition(Activity.class);
        condition.createCriteria().andCondition("id = '" + memberId + "'");
        AjaxResult aa = new AjaxResult();
        aa.setData(memberMapper.selectByExample(condition));
        aa.setRetmsg("succ");
        return aa;
    }

    // @Authorization("需token")
    @ApiOperation(value = "根据小写的openId获取用户name", notes = "根据小写的openId获取用户name")
    @RequestMapping(value = "getMemberLowerCase", method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMemberLowerCase(@RequestParam(value = "memberId", required = false) String memberId) {
        AjaxResult aa = new AjaxResult();
        JsonResult r = new JsonResult();
        Condition condition = new Condition(Activity.class);
        condition.createCriteria().andCondition("id = '" + convertString(memberId) + "'");
        aa.setData(memberMapper.selectByExample(condition).get(0));
        return aa;
    }

    // @Authorization("需token")
    @ApiOperation(value = "修改名片", notes = "修改名片")
    @RequestMapping(value = "updateMember", method = RequestMethod.POST)
    public AjaxResult updateMember(@ApiParam(value = "真实姓名", required = true) @RequestParam("name") String name,
                                   @ApiParam(value = "行业", required = true) @RequestParam("trade") String trade,
                                   @ApiParam(value = "公司名称", required = true) @RequestParam("corporatename") String corporatename,
                                   @ApiParam(value = "职位", required = true) @RequestParam("profession") String profession,
                                   @ApiParam(value = "工作地区", required = true) @RequestParam("region") String region,
                                   @ApiParam(value = "微信号", required = true) @RequestParam("wxno") String wxno,
                                   @ApiParam(value = "手机号", required = true) @RequestParam("mobile") String mobile,
                                   @ApiParam(value = "邮箱", required = true) @RequestParam("email") String email,
                                   @ApiParam(value = "ID", required = true) @RequestParam("id") String id) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setCorporatename(corporatename);
        member.setProfession(profession);
        member.setWxno(wxno);
        member.setMobile(mobile);
        member.setEmail(email);

        member.setTrade(trade);
        Condition condition = new Condition(DynamicType.class);
        DynamicType industry = dynamicTypeService.queryOne(Integer.parseInt(trade));
        member.setTradename(industry.getDynamicname());

        member.setRegion(region);
        /*Condition condition2=new Condition(Regions.class);
        condition2.createCriteria().andCondition("id",region);
        Regions regions= regionsMapper.selectByPrimaryKey(condition2);*/
        String[] regionName = region.split(",");
        member.setRegionname(regionName[0] + regionName[1]);

        /*MemberInfo memberInfo=new MemberInfo();
        memberInfo.setMemberid(id);
        memberInfo = memberInfoMapper.selectOne(memberInfo);
        if(memberInfo == null){
            memberInfo.setRealname(realname);
            memberInfoService.insert(memberInfo);
        }else{
            memberInfo.setRealname(realname);
            memberInfoService.update(memberInfo);
        }
*/
        memberService.update(member);
        AjaxResult aa = new AjaxResult();
        aa.setData(memberService.queryOne(member));
        aa.setRetmsg("succ");
        return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value = "编辑主页", notes = "编辑主页")
    @RequestMapping(value = "updateHomePage", method = RequestMethod.POST)
    public AjaxResult updateHomePage(@ApiParam(value = "个人介绍", required = true) @RequestParam("personalinfo") String personalinfo,
                                     @ApiParam(value = "我的资源", required = true) @RequestParam("resources") String resources,
                                     @ApiParam(value = "ID", required = true) @RequestParam("id") String id) {
        Member member = new Member();
        member.setId(id);
        member.setPersonalinfo(personalinfo);
        member.setResources(resources);

        memberService.update(member);
        AjaxResult aa = new AjaxResult();
        aa.setData(memberService.queryOne(member));
        aa.setRetmsg("succ");
        return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value = "获取用户选择的行业", notes = "获取用户选择的行业")
    @ResponseBody
    @RequestMapping(value = "getMemberDynamicType", method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMemberDynamicType(@RequestParam(value = "memberId", required = false) String memberId) {//int pageNo,int pageSize
        List<DynamicType> list = dynamicTypeService.getMemberDynamicType(memberId);
        list.stream().forEach(i -> {
            if (StringUtils.isNullOrEmpty(i.getUserId())) {
                i.setChosen(false);
            } else {
                i.setChosen(true);
            }
        });
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(list);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value = "根据用户ID获取活动", notes = "根据用户ID获取活动")
    @RequestMapping(value = "getActivtiyByMemberId", method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getActivtiyByMemberId(@RequestParam String memberId) {
        JsonResult r = new JsonResult();
        List<Activity> list = activityMapper.getActivtiyByMemberId(memberId);
        list.stream().forEach(i -> {
            if (!StringUtils.isNullOrEmpty(i.getBuynum())) {
                i.setFinishimg("/images/my/my_act_icon_finish.png");
            }
        });
        AjaxResult aa = new AjaxResult();
        aa.setData(list);
        aa.setRetmsg("succ");
        return aa;
    }


    @ApiOperation(value = "注册用户", notes = "注册用户")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AjaxResult register(@RequestBody Member member) {
        AjaxResult ajaxResult = new AjaxResult();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        member.setRegtime(df.format(new Date()));
        member.setIsblock("0");
        member.setOpenid(member.getId().toLowerCase());
        member.setIshy("0");
        member.setState("0");
        int cout=100;
        Long l = new Long((long)103);
        Object  usernames = iMUserAPI.getIMUsersBatch(l,null);
        JSONObject  json = JSONObject.fromObject(usernames);
        List li=(List)json.get("entities");
       if(li.size()>=100){
           ajaxResult.setRetcode(-1);
           return ajaxResult;
       }
        ajaxResult.setData(memberService.save(member));
        ajaxResult.setRetmsg("success");
     /*   Map map=new HashMap();
        map.put("username",member.getId());
        map.put("password","123456");
        map.put("nickname",member.getName());*/
     /*   User user=new User();
        user.setUsername(member.getOpenid());
        user.setPassword("123456");
        RegisterUsers registerUsers=new RegisterUsers();
        registerUsers.add(0,user);*/
        //  iMUserAPI.createNewIMUserSingle(registerUsers);
        return ajaxResult;
    }

    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @RequestMapping(value = "sendCode", method = {RequestMethod.POST, RequestMethod.GET})
    public AjaxResult sendCode(String mobile) {
        //模拟验证码发送
        Integer code = (int) ((Math.random() * 9 + 1) * 1000);


        String str = "mobile=" + mobile + "&tpl_id=65434&tpl_value=%23code%23%3d" + code + "&key=020930c0b71490ffaf532c559b14a30e&dtype=json";
        String jsonObject = com.xe.demo.common.utils.HttpUtil.get("http://v.juhe.cn/sms/send", str);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(code);
        ajaxResult.setRetmsg("success");
        return ajaxResult;
    }

    //@Authorization("需token")
    @ApiOperation(value = "实名认证", notes = "实名认证")
    @RequestMapping(value = "realNameVerMember", method = RequestMethod.POST)
    public AjaxResult realNameVerMember(HttpServletRequest request, @ApiParam(value = "真实姓名", required = true) @RequestParam("realname") String realname,
                                        @ApiParam(value = "身份证号", required = true) @RequestParam("cardno") String cardno,
                                        @ApiParam(value = "身份证正面图片", required = true) @RequestParam("cardfront") String cardfront,
                                        @ApiParam(value = "身份证反面图片", required = true) @RequestParam("cardback") String cardback,
                                        @ApiParam(value = "用户ID", required = true) @RequestParam("memberid") String memberid) throws IOException {
        AjaxResult aa = new AjaxResult();
        MemberInfo m = new MemberInfo();
        m.setMemberid(memberid);
        MemberInfo m2 = memberInfoMapper.selectOne(m);
        m.setRealname(realname);
        m.setCardback(cardback);
        m.setCardfront(cardfront);
        m.setMemberid(memberid);
        m.setCardno(cardno);
        m.setIspass("0");
        m.setRemark("");

        if (m2 == null) {
            m.setCreatetime(DateUtil.getCurDateTime());
            memberInfoService.insert(m);
        } else {
            m.setId(m2.getId());
            m.setRegtime(DateUtil.getCurDateTime());
            memberInfoService.update(m);
        }

        Member member = memberService.queryByID(memberid);
        member.setRealname(realname);
        Map map = new HashMap();
        map.put("member", member);
        map.put("memberInfo", m);
        aa.setData(map);
        aa.setRetmsg("succ");
        return aa;
    }

    // @Authorization("需token")
    @ApiOperation(value = "实名认证", notes = "实名认证")
    @RequestMapping(value = "getMemberInfo", method = RequestMethod.POST)
    public AjaxResult getMemberInfo(HttpServletRequest request,
                                    @ApiParam(value = "用户ID", required = true) @RequestParam("memberid") String memberid) throws IOException {
        AjaxResult aa = new AjaxResult();
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberid(memberid);
        memberInfo = memberInfoMapper.selectOne(memberInfo);
        if (memberInfo == null) {
            aa.setData(new MemberInfo());
        } else {
            aa.setData(memberInfo);
        }
        aa.setRetmsg("succ");
        return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value = "用户发送名片记录", notes = "用户发送名片记录")
    @RequestMapping(value = "sendcardlog", method = RequestMethod.POST)
    public AjaxResult sendcardlog(HttpServletRequest request,
                                  @ApiParam(value = "发送用户ID", required = true) @RequestParam("sendid") String sendid,
                                  @ApiParam(value = "接收用户ID", required = true) @RequestParam("receiveid") String receiveid) throws IOException {
        AjaxResult aa = new AjaxResult();
        Sendcardlog sendcardlog = new Sendcardlog();
        sendcardlog.setSendid(sendid);
        sendcardlog.setReceiveid(receiveid);
        sendcardlog.setCreatetime(DateUtil.getCurDateTime());
        aa.setRetcode(sendcardlogService.insert(sendcardlog));
        aa.setRetmsg("succ");
        return aa;
    }


    @ApiOperation(value = "获取所有用户除了自己", notes = "获取所有用户除了自己")
    @RequestMapping(value = "getMemberListNoMy", method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "query", name = "memberId", value = "用户ID", required = true, dataType = "String")
    public AjaxResult getMemberListNoMy(@RequestParam(value = "memberId", required = false) String memberId) {
        JsonResult r = new JsonResult();
        Condition condition = new Condition(Activity.class);
        condition.createCriteria().andCondition("id != '" + memberId + "'");
        AjaxResult aa = new AjaxResult();
        List<Member> memberList = memberMapper.selectByExample(condition);
        List<SzmData> szmDataList = new ArrayList<SzmData>();
        SzmData A = new SzmData("A", null);
        SzmData B = new SzmData("B", null);
        SzmData C = new SzmData("C", null);
        SzmData D = new SzmData("D", null);
        SzmData E = new SzmData("E", null);
        SzmData F = new SzmData("F", null);
        SzmData G = new SzmData("G", null);
        SzmData H = new SzmData("H", null);
        SzmData I = new SzmData("I", null);
        SzmData J = new SzmData("J", null);
        SzmData K = new SzmData("K", null);
        SzmData L = new SzmData("L", null);
        SzmData M = new SzmData("M", null);
        SzmData N = new SzmData("N", null);
        SzmData O = new SzmData("O", null);
        SzmData P = new SzmData("P", null);
        SzmData Q = new SzmData("Q", null);
        SzmData R = new SzmData("R", null);
        SzmData S = new SzmData("S", null);
        SzmData T = new SzmData("T", null);
        SzmData U = new SzmData("U", null);
        SzmData V = new SzmData("V", null);
        SzmData W = new SzmData("W", null);
        SzmData X = new SzmData("X", null);
        SzmData Y = new SzmData("Y", null);
        SzmData Z = new SzmData("Z", null);
        szmDataList.add(A);
        szmDataList.add(B);
        szmDataList.add(C);
        szmDataList.add(D);
        szmDataList.add(E);
        szmDataList.add(F);
        szmDataList.add(G);
        szmDataList.add(H);
        szmDataList.add(I);
        szmDataList.add(J);
        szmDataList.add(K);
        szmDataList.add(L);
        szmDataList.add(M);
        szmDataList.add(N);
        szmDataList.add(O);
        szmDataList.add(P);

        szmDataList.add(Q);
        szmDataList.add(R);
        szmDataList.add(S);
        szmDataList.add(T);

        szmDataList.add(U);
        szmDataList.add(V);
        szmDataList.add(W);
        szmDataList.add(X);

        szmDataList.add(Y);
        szmDataList.add(Z);

        for (int j = 0; j < szmDataList.size(); j++) {
            List<Member> members = new ArrayList<Member>();
            for (int i = 0; i < memberList.size(); i++) {
                ChineseCharToEn cte = new ChineseCharToEn();
                String szm = cte.getAllFirstLetter(memberList.get(i).getName().substring(0, 1).toUpperCase());
                String dd = memberList.get(i).getIshy();
                boolean falg = memberList.get(i).getIshy().equals("0") ? true : false;
                szmDataList.get(j).setIshy(falg);
                if (szmDataList.get(j).getAlphabet().equals(szm)) {
                    members.add(memberList.get(i));
                }


            }
            szmDataList.get(j).setMembers(members);

        }


        aa.setData(szmDataList);
        aa.setRetmsg("succ");
        return aa;
    }

    //@Authorization("需token")
    @ApiOperation(value = "用户中心关注行业", notes = "用户中心关注行业")
    @ResponseBody
    @RequestMapping(value = "memberIndexChooseDyn", method = RequestMethod.POST)
    public AjaxResult memberIndexChooseDyn(@ApiParam(value = "用户id", required = true) @RequestParam("memberid") String memberid,
                                           @ApiParam(value = "行业id", required = true) @RequestParam("dynamictypeid") String dynamictypeid) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Condition condition = new Condition(UserCollecTindustry.class);
            condition.createCriteria().andCondition("userid= '" + memberid + "'");
            userCollecTindustryMapper.deleteByExample(condition);
            Arrays.stream(dynamictypeid.split(",")).forEach(i -> {
                UserCollecTindustry userCollecTindustry = new UserCollecTindustry();
                userCollecTindustry.setDynamicwzid(Integer.parseInt(i));
                userCollecTindustry.setUserid(memberid);
                userCollecTindustryService.insert(userCollecTindustry);
                ajaxResult.setRetcode(1);
                ajaxResult.setRetmsg("succ");
            });
        } catch (Exception e) {
            ajaxResult.setRetcode(0);
        }

        return ajaxResult;
    }

    @ApiOperation(value = "修改formId", notes = "修改名片")
    @RequestMapping(value = "updateformId", method = RequestMethod.POST)
    public AjaxResult updateformId(@ApiParam(value = "用户id", required = true) @RequestParam("memberid") String memberid, @ApiParam(value = "formId", required = true) @RequestParam("formId") String formId) {
        AjaxResult ajaxResult=new AjaxResult();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        Daysign daysign=new Daysign();
        daysign.setUserId(memberid);
        daysign.setCreatTime(df.format(new Date()));
        daysign.setSorce(1);
        daysignService.save(daysign);
        Member member = new Member();
        member.setId(memberid);
        member.setFormId(formId);
        member.setFormTime(CommonTools.getCurrTimenohh());

        return memberService.update(member);
    }

    //@Authorization("需token")
    @ApiOperation(value = "购买服务", notes = "购买服务")
    @ResponseBody
    @RequestMapping(value = "memberBuy", method = RequestMethod.POST)
    public AjaxResult memberBuy(@ApiParam(value = "用户id", required = true) @RequestParam("memberid") String memberid,
                                @ApiParam(value = "购买价格", required = true) @RequestParam("price") String price,
                                @ApiParam(value = "购买月数", required = true) @RequestParam("buymonth") String buymonth) throws ParseException, IOException, JDOMException {
        AjaxResult ajaxResult = new AjaxResult();

        Member member = new Member();
        MemberBuy memberBuy = new MemberBuy();
        HashMap hashMap = new HashMap();
        //购买表  productid 1 一个月 3三个月 6六个月 12十二个月
        memberBuy.setBuyprice(price);
        memberBuy.setBuystate("0");
        memberBuy.setProductid(buymonth);
        memberBuy.setUserid(memberid);
        memberBuy.setOrderno("wx" + memberid.substring(0, 4) + "_" + System.currentTimeMillis());  //随机数订单号
        memberBuy.setBuytime(DateUtil.getCurDate().toString());
        if (memberBuyService.insert(memberBuy) > 0) {
            //改用户表 ishy buytime buyendtime
            //如果还是会员
            member = memberService.queryByID(memberid);
            if ("1".equals(member.getIshy())) {
                member.setViptimeend(compDateMonth(member.getViptimeend(), buymonth));
            } else {
                //如果不是会员
                member.setIshy("1");
                member.setViptimestart(DateUtil.getCurDate().toString());
                member.setViptimeend(compDateMonth(DateUtil.getCurDate().toString(), buymonth));
            }
            memberService.update(member);
            String openid = memberid;
            int fee = 0;
            //得到小程序传过来的价格，注意这里的价格必须为整数，1代表1分，所以传过来的值必须*100；
            if (null != memberBuy.getBuyprice()) {
                //fee = Integer.parseInt(String.valueOf(activityOrder.getPaymoney()))*100;
                DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                fee = Integer.parseInt(decimalFormat.format(Double.parseDouble(memberBuy.getBuyprice()) * 100));
            }
            String did = memberBuy.getOrderno();
            String title = "购买会员";
            String times = System.currentTimeMillis() + "";
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", "wxe2cc38ade8981367");
            packageParams.put("mch_id", "1499373952");
            packageParams.put("nonce_str", times);//时间戳
            packageParams.put("body", title);//支付主体
            packageParams.put("out_trade_no", did);//编号
            packageParams.put("total_fee", fee);//价格
            // packageParams.put("spbill_create_ip", getIp2(request));这里之前加了ip，但是总是获取sign失败，原因不明，之后就注释掉了
            packageParams.put("notify_url", "http://yiyequan.qubanvip.cn/api/membernotify");//支付返回地址，不用纠结这个东西，我就是随便写了一个接口，内容什么都没有 yiyequan.qubanvip.cn
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
            ajaxResult.setRetmsg("success");
               /*Gson gson = new Gson();
            String json = gson.toJson(packageP);*/
            //ajaxResult.setData(packageP);
            hashMap.put("packageP", packageP);
            String token = OpenIdUtil.getToken().get("access_token").toString();
            //Activity activity=activityService.getActivityByid(activityOrder.getActivityid());
            // OpenIdUtil.sendMessage(token,activityOrder.getUserid(),activityOrder.getForm_id(),activity);
        }

        hashMap.put("member", member);
        hashMap.put("pushPrce", "¥" + price);
        ajaxResult.setData(hashMap);
        ajaxResult.setRetcode(1);
        ajaxResult.setRetmsg("succ");
        return ajaxResult;
    }


    @ApiOperation(value = "支付回调", notes = "支付回调")
    @ResponseBody
    @RequestMapping(value = "membernotify", method = RequestMethod.POST)
    public String notify(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException, ParseException {
        ServletInputStream in = null;
        AjaxResult ajaxResult = new AjaxResult();
        String out_trade_no ="";
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
            String resXml = "";
            String returnCode = (String) map.get("return_code");
            if("SUCCESS".equals(returnCode)){
                    //通知微信服务器已经支付成功
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                //系统订单号
                out_trade_no = String.valueOf(map.get("out_trade_no"));
                //微信支付流水号
                String transaction_id = String.valueOf(map.get("transaction_id"));
                //修改订单状态
                MemberBuy memberBuy = new MemberBuy();
                memberBuy.setOrderno(out_trade_no);
                memberBuy = memberBuyService.queryOne(memberBuy);
                if (memberBuy != null) {
                    memberBuy.setBuystate("1");
                    memberBuyService.update(memberBuy);
                    Member member = new Member();
                    member = memberService.queryByID(memberBuy.getUserid());
                    if (member != null) {
                        member.setIshy("1");
                        member.setViptimestart(DateUtil.getCurDate().toString());
                        //member.setViptimeend(compDateMonth(DateUtil.getCurDate().toString(),memberBuy.getProductid()));
                        if (member.getViptimeend() != null || !member.getViptimeend().equals("")) {
                            member.setViptimeend(compDateMonth(member.getViptimeend(), memberBuy.getProductid()));
                        } else {
                            member.setViptimeend(compDateMonth(DateUtil.getCurDate().toString(), memberBuy.getProductid()));
                        }
                        memberService.update(member);
                        ajaxResult.setData(member);
                    }
                }
            }else{
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            System.out.println(resXml);
            System.out.println("微信支付回调数据结束"+out_trade_no);

            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
       return null;
    }

    /**
     * //加减月份
     *
     * @param month
     * @return
     */
    public static String compDateMonth(String dateString, String month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(sdf.parse(dateString));
        gc.add(2, +Integer.parseInt(month));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(gc.getTime());
    }

    /* public static void main(String[] args) throws ParseException {

         System.out.print(compDateMonth("2017-10-19 00:00:00","1"));
     }*/
    //@Authorization("需token")
    @ApiOperation(value = "添加好友", notes = "添加好友")
    @RequestMapping(value = "addFriend", method = RequestMethod.POST)
    public AjaxResult addFriend(HttpServletRequest request, @ApiParam(value = "我", required = true) @RequestParam("my") String my,
                                @ApiParam(value = "你", required = true) @RequestParam("yuo") String you
    ) throws IOException {
        iMUserAPI.addFriendSingle(my, you);
        AjaxResult aa = new AjaxResult();
        aa.setRetmsg("succ");
        return aa;
    }

    private static String convertString(String str) {

        char[] ch = str.toCharArray();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < ch.length; i++) {
            sbf.append(charToLowerCase(ch[i]));
        }
        return sbf.toString();
    }

    /***转小写**/
    private static char charToLowerCase(char ch) {
        if (ch <= 90 && ch >= 65) {
            ch += 32;
        }
        return ch;
    }

    public static void main(String[] args) {

        convertString("AAA");
        convertString("AAA");
    }


    @ApiOperation(value = "发送Im消息", notes = "发送Im消息")
    @ResponseBody
    @RequestMapping(value = "sendIm", method = RequestMethod.POST)
    public net.sf.json.JSONObject sendMessage(String openid, String form_id, String nickname, String textType, String message) {
       int tcount=(int)iMUserAPI.getOfflineMsgCount(openid);
       return null;
    }

    @ApiOperation(value = "得到离线消息数", notes = "得到离线消息数")
    @RequestMapping(value = "getOfflineMsgCount", method = RequestMethod.POST)
    public AjaxResult getOfflineMsgCount(@ApiParam(value = "用户名", required = true) @RequestParam("userName") String userName) throws IOException {
        AjaxResult aa = new AjaxResult();
        Object object=iMUserAPI.getOfflineMsgCount(userName);
        //aa.setData();
        aa.setRetmsg("succ");
        return aa;
    }

}
