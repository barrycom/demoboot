package com.xe.demo.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Component
public class UploadUtil {
    String ACCESS_KEY = "wjwYxf8h5jQOC8QrWhnoBBXNdczuSiMzKycJB5WN";
    String SECRET_KEY = "lOei7RqoxaGGUBdO48G2f20EdCRa0SZWMYOO8xM2";
    //要上传的空间--
    String bucketname = "zall-haihy";
    private String host = "https://image.zallhy.com/";
    /**
     * 上传
     * @param request
     * @return
     * @throws Exception
     */
    public String upload(HttpServletRequest request, String docBase, String path) throws IOException {
		String filepath = "";
		 //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
        	//转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
            	//取得上传文件
                MultipartFile myFile = multiRequest.getFile(iter.next());
                if (myFile != null) {
                /*    System.out.println(myFile.getOriginalFilename());
                    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    //图片名称
                    String name = df.format(new Date());

                    Random r = new Random();
                    for(int i = 0 ;i<3 ;i++){
                        name += r.nextInt(10);
                    }
                    //
                    String ext = FilenameUtils.getExtension(myFile.getOriginalFilename());
                    //保存图片       File位置 （全路径）   /upload/fileName.jpg
                    String url = request.getSession().getServletContext().getRealPath(path);
                    //相对路径
                    String xdpath = "/"+name + "." + ext;
                    File file = new File(url+DateUtil.getCurDate("yyyyMMdd"));
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    myFile.transferTo(new File(url+DateUtil.getCurDate("yyyyMMdd")+xdpath));
                    filepath = path+DateUtil.getCurDate("yyyyMMdd")+xdpath;*/
                    //json.put("success", "/static/img/upload/phono/"+path);

                    Configuration cfg = new Configuration(Zone.autoZone());
                    UploadManager uploadManager = new UploadManager(cfg);
                    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
                    String upToken = auth.uploadToken(bucketname);
                    String key = null;
                    Response qresponse;
                    try {
                        qresponse = uploadManager.put(myFile.getInputStream(),key,upToken,null, null);
                        //解析上传成功的结果
                        DefaultPutRet putRet = new Gson().fromJson(qresponse.bodyString(), DefaultPutRet.class);
                        System.out.println(putRet.key);
                        System.out.println(putRet.hash);
                        System.out.print(host+putRet.key);
                      /*  result.setUrl(host+putRet.key);
                        String gsonString = new Gson().toJson(result);
                        user.setAvtar("http://ooprvk5m6.bkt.clouddn.com/"+putRet.key);
                        userService.update(user);
                        request.getSession().setAttribute("currentUser",userService.findUser(user));
                        ResponseUtil.write(gsonString,response);*/
                        filepath = host+putRet.key;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }
            }
        }
		return filepath;
	}

    /**
     * 获取文件绝对路径
     * @param request
     * @param host
     * @return
     */
    public static String getFileUrl(HttpServletRequest request, String host) {
        String path = request.getScheme() + "://" + host + request.getContextPath() + "/";
        int port = request.getServerPort();
        if (80 != port) {
            path = request.getScheme() + "://" + host + ":" + request.getServerPort() + request.getContextPath() + "/";
        }
        return path;
    }

    /**
     * 文件路径
     * @param type
     * @return
     */
    public String getFilePath(String filepath, String filename) {
    	StringBuilder sb = new StringBuilder(filepath).append(DateUtil.getCurDate("yyyyMMdd")).append("/").append(filename);
        return sb.toString();
    }
    
    /**
     * 文件重命名
     * @param suffixes
     * @return
     */
    public String getNewName(String suffixes){
    	return UUID.randomUUID().toString() + suffixes;
    }

    /**
     * 删除文件
     * @param path
     */
    public static void delFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
