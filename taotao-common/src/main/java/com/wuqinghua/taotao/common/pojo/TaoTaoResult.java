package com.wuqinghua.taotao.common.pojo;

/**
 * Created by wuqinghua on 17/11/4.
 * 淘淘响应的结果类型
 */
public class TaoTaoResult {

    private Integer status;  //响应的业务状态码

    private String msg; //响应的消息

    private Object data; //响应的数据


    public static TaoTaoResult defaultOK() {
        TaoTaoResult result = new TaoTaoResult();
        result.setStatus(200);
        result.setMsg("success");
        return result;
    }

    public static TaoTaoResult defaultOK(Object data) {
        TaoTaoResult result = new TaoTaoResult();
        result.setStatus(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static TaoTaoResult defaultFaild(String msg) {
        TaoTaoResult result = new TaoTaoResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
