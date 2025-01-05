package org.zxp.securitydemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result <T>{
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    public static  <T> Result<T> failure(T data){
        return new Result<>(500, "操作失败", data);
    }
}
