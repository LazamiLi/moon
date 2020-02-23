package com.red.moon.utils;

/**
 * @Author: lihui
 * @Date: 2020/2/21 9:16
 *
 */
public class ResultUtil {
    private static Long successCode = 1L;
    private static String successMsg = "成功";

    private static Long failCode = 0L;
    private static String failMsg = "失败";


    public static Result success(){
        return new Result(successCode,successMsg,null);
    }
    public static Result success(Object data){
        return new Result(successCode,successMsg,data);
    }


    public static Result fail(){
        return new Result(failCode,failMsg,null);
    }
    public static Result fail(Object data){
        return new Result(failCode,failMsg,data);
    }

    public static Result customResult(Long customCode,String customMsg,Object data){
        return new Result(customCode,customMsg,data);
    }


}
