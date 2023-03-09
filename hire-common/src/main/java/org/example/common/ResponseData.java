package org.example.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.exception.BizException;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseData<T> {

    private T data;
    private final int status;
    private String statusMsg;

    private ResponseData(int status) {
        this.status = status;
    }

    private ResponseData(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ResponseData(int status, String statusMsg, T data) {
        this.status = status;
        this.statusMsg = statusMsg;
        this.data = data;
    }

    private ResponseData(int status, String statusMsg) {
        this.status = status;
        this.statusMsg = statusMsg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == BaseResponseEnum.SUCCESS.getStatus();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public static <T> ResponseData<T> Create(BaseResponse e) {
        return new ResponseData<>(e.getStatus(), e.getMsg());
    }

    public static <T> ResponseData<T> Create(BizException e) {
        return new ResponseData<>(e.getErrorCode(), e.getErrorMsg());
    }

    public static <T> ResponseData<T> Success() {
        return new ResponseData<>(BaseResponseEnum.SUCCESS.getStatus());
    }

    public static <T> ResponseData<T> Success(String msg) {
        return new ResponseData<>(BaseResponseEnum.SUCCESS.getStatus(), msg);
    }

    public static <T> ResponseData<T> Success(T data) {
        return new ResponseData<>(BaseResponseEnum.SUCCESS.getStatus(), data);
    }

    public static <T> ResponseData<T> Success(String msg, T data) {
        return new ResponseData<>(BaseResponseEnum.SUCCESS.getStatus(), msg, data);
    }


    public static <T> ResponseData<T> Error() {
        return new ResponseData<>(BaseResponseEnum.ERROR.getStatus(), BaseResponseEnum.ERROR.getMsg());
    }


    public static <T> ResponseData<T> Error(String errorMessage) {
        return new ResponseData<>(BaseResponseEnum.ERROR.getStatus(), errorMessage);
    }

    public static <T> ResponseData<T> Error(int errorCode, String errorMessage) {
        return new ResponseData<>(errorCode, errorMessage);
    }
}
