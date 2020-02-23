package com.red.moon.bean.configbean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lihui
 * @Date: 2020/2/21 9:43
 */
@Configuration
@ConfigurationProperties(prefix = "com.file")
public class FileUploadBean {
    private String position;

    @Override
    public String toString() {
        return "FileUploadBean{" +
                "position='" + position + '\'' +
                '}';
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
