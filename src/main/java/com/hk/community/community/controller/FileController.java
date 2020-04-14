package com.hk.community.community.controller;

import com.hk.community.community.dto.FileDTO;
import com.hk.community.community.dto.ResultDTO;
import com.hk.community.community.utils.FileNameUtils;
import com.hk.community.community.utils.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者: hekang
 * 时间: 2020-04-13 11:00
 * 描述:
 **/

@Controller
public class FileController {
    @Value("${web.upload-path}")
    String webPath;

    @Value("${local.upload-path}")
    String localPath;
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartRequest multipartRequest =(MultipartRequest)request;

        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String fileName = FileNameUtils.getFileName(file.getOriginalFilename());
        FileUtils.upload(file,localPath,fileName);
        FileDTO fileDTO = FileDTO.builder()
                .message("保存成功")
                .success(1)
                .url(webPath+fileName)
                .build();
        return fileDTO;
    }

    ;
}
