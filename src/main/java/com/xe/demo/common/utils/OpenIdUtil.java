package com.xe.demo.common.utils;

/**
 * Created by Administrator on 2017-10-24.
 */
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.xe.demo.model.Activity;
import com.xe.demo.model.ActivityOrder;
import net.sf.json.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xsx
 */
public class OpenIdUtil {



    public static String oauth2GetOpenid(String code) {
        String appid="wxe2cc38ade8981367";
        String appsecret="de892e18e425f89860f8981cfdd3ea84";


        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpUtil.get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject  json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        String Openid =String.valueOf(json.get("openid"));
        //System.out.println(Openid);
        return Openid;
    }

    public static JSONObject getSessionKeyOropenid(String code){
        //微信端登录code值
         String appid="wxe2cc38ade8981367";
         String appsecret="de892e18e425f89860f8981cfdd3ea84";
        String wxCode = code;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        String grant_type = "authorization_code";
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        String data= HttpUtil.get(requestUrl, params);
        JSONObject  json = JSONObject.fromObject(data);
    /*    String session_key =String.valueOf(json.get("session_key"));*/
        return json;
    }


    public static JSONObject sendMessage(String token,String openid,String form_id,Activity activity){
        //微信端登录code值
     /*   String appid="wx7d6fcdecb6fd652c";
        String appsecret="87f5b9ccdd49ef185600addb5faf2833";
        //String wxCode = code;*/
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+token;


        ModelMessage modelMessage=new ModelMessage();


        KeyWordModel keyWordModel1=new KeyWordModel();
        keyWordModel1.setColor("#173177");
        keyWordModel1.setValue(activity.getActivityname());

        KeyWordModel keyWordModel2=new KeyWordModel();
        keyWordModel2.setColor("#173177");
        keyWordModel2.setValue(activity.getActivitysdate());


        KeyWordModel keyWordModel3=new KeyWordModel();
        keyWordModel3.setColor("#173177");
        keyWordModel3.setValue(activity.getActivityaddr());


        KeyWordModel keyWordModel4=new KeyWordModel();
        keyWordModel4.setColor("#173177");
        keyWordModel4.setValue(activity.getActivityidmemo());

        KeyWordModel keyWordModel5=new KeyWordModel();
        keyWordModel5.setColor("#173177");
        keyWordModel5.setValue("等待您的参加");

        modelMessage.setKeyword1(keyWordModel1);
        modelMessage.setKeyword2(keyWordModel2);
        modelMessage.setKeyword3(keyWordModel3);
        modelMessage.setKeyword4(keyWordModel4);
        modelMessage.setKeyword5(keyWordModel5);

     //   requestUrlParam.put("data",ModelMessage);

        NewOrdersTemplate newOrdersTemplate=new NewOrdersTemplate();
        newOrdersTemplate.setTouser(openid);
        newOrdersTemplate.setForm_id(form_id);
        newOrdersTemplate.setTemplate_id("Jnd99okWzFtuYHKD8GFZFU48KeIu6aD-NfFoUSD2hNk");
        newOrdersTemplate.setData(modelMessage);
        String jsonString = JSONObject.fromObject(newOrdersTemplate).toString().replace("day", "Day");
        String data= HttpUtil.sendPostUrl(requestUrl,jsonString,"utf-8");


        JSONObject  json = JSONObject.fromObject(data);
    /*    String session_key =String.valueOf(json.get("session_key"));*/
        return json;
    }





    public static JSONObject getToken(){
        //微信端登录code值
        String appid="wxe2cc38ade8981367";
        String appsecret="de892e18e425f89860f8981cfdd3ea84";
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        String grant_type = "client_credential";
        String params = "appid=" + appid + "&secret=" + appsecret + "&grant_type=" + grant_type;
        String data= HttpUtil.get(requestUrl, params);
        JSONObject  json = JSONObject.fromObject(data);
    /*    String session_key =String.valueOf(json.get("session_key"));*/
        return json;
    }

    /**
     * 解密用户敏感数据获取用户信息
     * @param sessionKey 数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     * @return
     */
    public static JSONObject getUserInfo(String encryptedData,String sessionKey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.fromObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}