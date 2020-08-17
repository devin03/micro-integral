package com.devin.micro.integral.extension.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件图片上传工具类
 * @author wangdongming
 * @date 2020/08/16
 */
@Component
public class FileUploadUtil {

    /**
     * #文件上传路径
     */
    private static String uploadUrl;
    /**
     * #文件回显路径
     */
    private static String showUrl;

    @Value("${app.conf.upload-url}")
    public void setUploadUrl(String uploadFileUrl){
        uploadUrl = uploadFileUrl;
    }

    @Value("${app.conf.show-url}")
    public void setShowUrl(String showFileUrl){
        showUrl = showFileUrl;
    }

    /**
     * 上传图片后返回文件名称用于存储数据库
     * @param file 上传图片列表
     * @return String
     * @author wangdongming
     * @date 2020/08/16
     */
    public static String uploadPics(List<MultipartFile> file) {
        int index = 0;
        StringBuffer sb = new StringBuffer();
        for (MultipartFile multipartFile : file) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                //工具类生成文件名
                String fileName = FileCreateNameUtils.toCreateName();
                //获取文件后缀名
                String fileType = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."));
                if (".GIF".equals(fileType.toUpperCase()) || ".PNG".equals(fileType.toUpperCase()) || ".JPG".equals(fileType.toUpperCase())){
                    File f = new File(uploadUrl + fileName + fileType);
                    //判断文件父目录是否存在
                    if (!f.getParentFile().exists()) {
                        f.getParentFile().mkdir();
                    }
                    //保存文件
                    multipartFile.transferTo(f);
                    sb.append(showUrl + fileName + fileType);
                    index++;
                    if (file.size() != index) {
                        sb.append(",");
                    }
                }else{
                    throw new IllegalArgumentException("不支持的文件类型，请重新上传");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
