package com.xe.demo.api.wxpay;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-10-20.
 */
public class PayCommonUtil {
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + API_KEY);

        //算出摘要
        String mysign = MD5.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();

        //System.out.println(tenpaySign + "    " + mysign);
        return tenpaySign.equals(mysign);
    }

    /**
     * @author
     * @Description：sign签名
     * @param characterEncoding
     *            编码格式
     * @param parameters
     *            请求参数
     * @return
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        String sign = MD5.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * @author
     * @Description：将请求参数转换为xml格式的string
     * @param parameters
     *            请求参数
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length
     *            int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 签名工具
     *
     * @param characterEncoding 编码格式 UTF-8
     * @param parameters        请求参数
     * @return
     * @author wangkai
     * @date 2014-12-5下午2:29:34
     * @Description：sign签名
     */
    public static String createSign(String characterEncoding,
                                    Map<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, Object>> it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();//去掉带sign的项
            if (null != value && !"".equals(value) && !"sign".equals(key)
                    && !"key".equals(key)) {
                sb.append(key + "=" + value + "&");
            }
        }
        sb.append("key=IvofeVGC3NpjltvBpQuCu8rAJ8croFTd");
        //注意sign转为大写
        System.out.println(sb.toString());
        return MD5.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     *
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) {

        try {
            SortedMap<String, Object> map = XMLUtil.doXMLParseTwo(responseString);
            //Map<String, Object> map = XMLParser.getMapFromXML(responseString);
            //logger.debug(map.toString());
            String signFromAPIResponse = map.get("sign").toString();
            if ("".equals(signFromAPIResponse) || signFromAPIResponse == null) {
                //  logger.debug("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
                return false;
            }
            // logger.debug("服务器回包里面的签名是:" + signFromAPIResponse);
            //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
            map.put("sign", "");
            //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
            String signForAPIResponse = PayCommonUtil.createSign("UTF-8", map);
            if (!signForAPIResponse.equals(signFromAPIResponse)) {
                //签名验不过，表示这个API返回的数据有可能已经被篡改了
                //  logger.debug("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
                return false;
            }
            //  logger.debug("恭喜，API返回的数据签名验证通过!!!");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
