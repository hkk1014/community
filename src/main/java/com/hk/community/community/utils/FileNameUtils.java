package com.hk.community.community.utils;

import java.util.UUID;

/**
 * 作者: hekang
 * 时间: 2020-04-13 22:31
 * 描述:
 **/
public class FileNameUtils {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return UUID.randomUUID().toString().replace("-","") + FileNameUtils.getSuffix(fileOriginName);
    }

}
