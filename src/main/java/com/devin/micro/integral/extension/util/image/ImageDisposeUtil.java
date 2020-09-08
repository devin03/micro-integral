package com.devin.micro.integral.extension.util.image;

import com.devin.micro.integral.extension.util.FileCreateNameUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片处理工具类
 * @author wangdongming
 * @date 2020/09/03
 */
@Component
public class ImageDisposeUtil {

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
     * base64字符串转换成MultipartFile文件
     * @param base64 图片base64字符串
     * @return MultipartFile
     * @author wangdongming
     * @date 2020/09/03
     */
    public static MultipartFile base64ToMultipart(String base64) {
        if (StringUtils.isBlank(base64)){
            return null;
        }
        try {
            String[] baseStr = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(baseStr[1]);
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodedMultipartFile(b, baseStr[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按照指定宽高对图片进行缩放
     * 比如：200 * 300
     * 若图片横比200小，高比300小，不变
     * 若图片横比200小，高比300大，高缩小到300，图片比例不变
     * 若图片横比200大，高比300小，横缩小到200，图片比例不变
     * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
     * @param sourceFile 图片的真实存储路径
     * @param width 指定宽
     * @param height 指定高
     * @return String
     * @author wangdongming
     * @date 2020/09/04
     */
    public static String thumbnailImage(String sourceFile,int width,int height){
        String [] sourceFiles = sourceFile.split("/");
        //源文件名
        String sourceFileName = sourceFiles[sourceFiles.length-1];
        //获取文件的后缀名
        String fileType = sourceFileName.substring(sourceFileName.indexOf("."));
        //工具类生成文件，目标文件名
        String fileName = FileCreateNameUtils.toCreateName() + "_thum" + fileType;
        //缩略图回显路径
        String thumbnailImageUrl = showUrl + "thumbnail/" + fileName;
        try {
            File file = new File(sourceFile);
            String thumbnailUploadUrl = uploadUrl + "thumbnail/" + fileName;
            Thumbnails.of(file).size(width,height).toFile(thumbnailUploadUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return thumbnailImageUrl;
    }

    /**
     * 按照指定宽高对图片进行缩放
     * 需要单独调用该方法或者先调用该方法，再调用图片上传
     * 如果先上传图片再调用该方法，会抛出异常
     * 比如：200 * 300
     * 若图片横比200小，高比300小，不变
     * 若图片横比200小，高比300大，高缩小到300，图片比例不变
     * 若图片横比200大，高比300小，横缩小到200，图片比例不变
     * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
     * @param file 图片
     * @param width 指定宽
     * @param height 指定高
     * @return String
     * @author wangdongming
     * @date 2020/09/04
     */
    public static String thumbnailImage(MultipartFile file,int width,int height){
        //工具类生成文件名
        String fileName = FileCreateNameUtils.toCreateName();
        //获取文件后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        fileName = fileName + "_thum" + fileType;
        //缩略图回显路径
        String thumbnailImageUrl = showUrl + "thumbnail/" + fileName;
        try {
            String thumbnailUploadUrl = uploadUrl + "thumbnail/" + fileName;
            Thumbnails.of(file.getInputStream()).size(width,height).toFile(thumbnailUploadUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return thumbnailImageUrl;
    }

    /**
     * 按照比例对图片进行缩放
     * @param file 图片
     * @param scale 缩放比例
     * @return String
     * @author wangdongming
     * @date 2020/09/04
     */
    public static String thumbnailImage(MultipartFile file,double scale){
        //工具类生成文件名
        String fileName = FileCreateNameUtils.toCreateName();
        //获取文件后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        fileName = fileName + "_thum" + fileType;
        //缩略图回显路径
        String thumbnailImageUrl = showUrl + "thumbnail/" + fileName;
        try {
            String thumbnailUploadUrl = uploadUrl + "thumbnail/" + fileName;
            Thumbnails.of(file.getInputStream()).scale(scale).toFile(thumbnailUploadUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return thumbnailImageUrl;
    }

    /**
     * 不按照比例，指定大小进行缩放
     * keepAspectRatio 指定是否按照比例缩放 默认是true：按照比例缩放的
     * @param file 图片文件
     * @param width 指定宽
     * @param height 指定高
     * @return String
     * @author wangdongming
     * @date 2020/09/07
     */
    public static String thumbnailImage(MultipartFile file,int width,int height,boolean keepAspectRatio){
        //工具类生成文件名
        String fileName = FileCreateNameUtils.toCreateName();
        //获取文件后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        fileName = fileName + "_thum" + fileType;
        //缩略图回显路径
        String thumbnailImageUrl = showUrl + "thumbnail/" + fileName;
        try {
            String thumbnailUploadUrl = uploadUrl + "thumbnail/" + fileName;
            Thumbnails.of(file.getInputStream()).size(width,height).keepAspectRatio(keepAspectRatio).toFile(thumbnailUploadUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return thumbnailImageUrl;
    }

    /**
     * 输出图片到流对象
     * @param file 图片文件
     * @param width 指定宽
     * @param height 指定高
     * @return OutputStream
     * @author wangdongming
     * @date 2020/09/07
     */
    public static OutputStream thumbnailImageToOutputStream(MultipartFile file, int width, int height){
        //工具类生成文件名
        String fileName = FileCreateNameUtils.toCreateName();
        //获取文件后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        fileName = fileName + "_thum" + fileType;
        OutputStream os = null;
        try {
            String thumbnailUploadUrl = uploadUrl + "thumbnail/" + fileName;
            os = new FileOutputStream(thumbnailUploadUrl);
            Thumbnails.of(file.getInputStream()).size(width,height).toOutputStream(os);
        }catch (Exception e){
            e.printStackTrace();
        }
        return os;
    }

    /**
     * 转化图像格式
     * @param file 图片文件
     * @param width 指定宽
     * @param height 指定高
     * @param imageType 文件类型，比如；png,gif
     * @author wangdongming
     * @date 2020/09/07
     */
    public static void thumbnailImage(MultipartFile file, int width, int height,String imageType){
        //工具类生成文件名
        String fileName = FileCreateNameUtils.toCreateName();
        try {
            String thumbnailUploadUrl = uploadUrl + "thumbnail/" + fileName;
            Thumbnails.of(file.getInputStream()).size(width,height).outputFormat(imageType).toFile(thumbnailUploadUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除图片
     * storePath 是文件路径还是目录路径
     * 如果是文件路径则删除该文件
     * 如果是目录路径则删除该目录下所有文件，包含该目录
     * @param storePath 存储路径
     * @author wangdongming
     * @date 2020/09/07
     */
    public static void deleteImg(String storePath){
        File fileOrDire = new File(uploadUrl + storePath);
        if (fileOrDire.exists()){
            if (fileOrDire.isDirectory()){
                //是目录路径，删除全部文件
                File[] files = fileOrDire.listFiles();
                for (File file : files){
                    file.delete();
                }
            }
            //是文件路径，删除该文件
            fileOrDire.delete();
        }
    }

}
