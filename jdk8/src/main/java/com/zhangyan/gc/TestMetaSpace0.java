package com.zhangyan.gc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/12/02/11:17 上午
 * @Description:
 */
public class TestMetaSpace0 {

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            MyFile myFile = new MyFile();
            myFile.setFileName("abc" + i++);
            myFile.setFileSize("123" + i++);
            System.out.println(buildData(myFile));
        }


    }

    public static String buildData(Object bean) {
        try {
            SerializeConfig CONFIG = new SerializeConfig();
            CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSON.toJSONString(bean, CONFIG);
        } catch (Exception e) {
            return null;
        }
    }


}


class MyFile {
    private String fileName;

    private String fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
