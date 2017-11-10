package com.wuqinghua.taotao.common.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuqinghua.taotao.common.utils.JsonUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/4.
 * 淘淘响应的结果类型
 */
public class TaoTaoResult {

    private Integer status;  //响应的业务状态码

    private String msg; //响应的消息

    private Object data; //响应的数据

    public TaoTaoResult() {
    }

    public TaoTaoResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

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


    private static ObjectMapper mapper = new ObjectMapper();

    public static TaoTaoResult format(String json, Class<?> clazz) {
        try {
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = mapper.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = mapper.readValue(data.asText(), clazz);
            } else if (data.isArray() && data.size() > 0) {
                obj = mapper.readValue(data.traverse(), mapper.getTypeFactory()
                        .constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (IOException e) {
            return null;
        }

    }

    private static TaoTaoResult build(int status, String msg, Object obj) {
        return new TaoTaoResult(status, msg, obj);
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
