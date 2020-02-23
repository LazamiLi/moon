package com.red.moon.service;

import com.red.moon.bean.LogBean.SysLog;

/**
 * @Author: lihui
 * @Date: 2020/2/21 16:29
 */
public interface ISysLogService {
    /**
     * 插入
     * @param log
     * @return
     */
    int insert(SysLog log);
}
