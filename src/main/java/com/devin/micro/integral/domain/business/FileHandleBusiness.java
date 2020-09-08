package com.devin.micro.integral.domain.business;

import com.devin.micro.integral.extension.util.FileUploadUtil;
import com.devin.micro.integral.extension.util.image.ImageDisposeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件处理逻辑
 * @author wangdongming
 * @date 2020/08/16
 */
@Service
public class FileHandleBusiness {

    public static final Logger LOG = LoggerFactory.getLogger(FileHandleBusiness.class);

    /**
     * #文件上传路径
     */
    private static String uploadUrl;

    @Value("${app.conf.upload-url}")
    public void setUploadUrl(String uploadFileUrl){
        uploadUrl = uploadFileUrl;
    }

    /**
     * 批量文件上传处理
     * @param request
     * @return String
     * @author wangdongming
     * @date 2020/08/16
     */
    public String batchFileUploadHandle(HttpServletRequest request){
        LOG.info("batch file upload start...");
        List<MultipartFile> list = ((MultipartHttpServletRequest)request).getFiles("file");
        if (CollectionUtils.isEmpty(list)){
            LOG.info("batch file upload file is null");
            return null;
        }
        String fileUrls = FileUploadUtil.uploadPics(list);
        String [] sourceFiles = fileUrls.split("/");
        //文件名
        String sourceFileName = sourceFiles[sourceFiles.length-1];
        String sourceFileUrl = uploadUrl + sourceFileName;
        String thumbnailFileUrls = ImageDisposeUtil.thumbnailImage(sourceFileUrl, 200, 300);
        LOG.info("batch file upload end,result fileUrls is {},thumbnailFileUrls is {}",fileUrls,thumbnailFileUrls);
        return fileUrls + "," + thumbnailFileUrls;
    }

}
