package com.red.moon.bean.LogBean;

import java.util.Date;

/**
 * @author lihui
 *
 */
public class SysLog {
    /**
     * ID
     */
    private Long id;
    /**
     * 操作人
     */
    private String username;

    /**
     * 请求路径
     */
    private String actionUrl;
    /**
     * 操作内容
     */
    private String params;
    /**
     * 操作者ip
     */
    private String ip;
    /**
     * 操作时间
     */
    private Date date;
    /**
     * 执行描述（1:执行成功、0:执行失败）
     */
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}