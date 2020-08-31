package com.devin.micro.integral.domain.business;

import com.devin.micro.integral.extension.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        LOG.info("batch file upload end,result is {}",fileUrls);
        return fileUrls;
    }

}
