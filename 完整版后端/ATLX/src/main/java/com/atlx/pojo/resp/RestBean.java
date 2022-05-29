package com.atlx.pojo.resp;

import com.atlx.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class RestBean<T> {
    int code;//状态码
    String reason;//状态描述
    T data;//数据

    public RestBean(int code, String reason, T data) {
        this.code = code;
        this.reason = reason;
        this.data = data;
    }
}
