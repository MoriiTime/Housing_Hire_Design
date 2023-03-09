package org.example.common;


public enum BaseResponseEnum implements BaseResponse{

    SUCCESS(0,"SUCCESS"),
    ERROR(1001,"ERROR"),
    UNEXPECTED(-1, "UNEXPECTED");

    private final int status;
    private final String msg;


    BaseResponseEnum(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public int getStatus(){
        return status;
    }
    public String getMsg(){
        return msg;
    }

}
