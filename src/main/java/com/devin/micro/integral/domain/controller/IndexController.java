package com.devin.micro.integral.domain.controller;

import com.devin.micro.integral.domain.business.FileHandleBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 页面controller
 * @author wangdongming
 * @date 2020/08/17
 */
@Controller
public class IndexController {

    @Autowired
    private FileHandleBusiness fileHandleBusiness;

    @RequestMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("messages", "test");
        return "uploadPic";
    }

    @RequestMapping("/uploadImage")
    public String handleFileUpload(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) throws Exception {
        String imageUrls = fileHandleBusiness.batchFileUploadHandle(request);
        redirectAttributes.addFlashAttribute("message", "成功上传!");
        System.out.println("上传的文件路径：" + imageUrls);
        String[] split = imageUrls.split(",");
        // 将文件传输成功之后的名字传回界面，用于展示图片
        model.addAttribute("picName", split[0]);
        model.addAttribute("picNameThumbnail", split[1]);
        return "uploadPic";
    }

}
