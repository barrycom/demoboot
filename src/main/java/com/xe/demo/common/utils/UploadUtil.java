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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Component
public class UploadUtil {

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
                    System.out.println(myFile.getOriginalFilename());
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
                    filepath = path+DateUtil.getCurDate("yyyyMMdd")+xdpath;
                    //json.put("success", "/static/img/upload/phono/"+path);
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
