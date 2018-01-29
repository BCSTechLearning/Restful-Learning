package com.rd.error;

public class ResourceNotFoundException extends RuntimeException {

    private Long resourceId;
    public Integer status;

    public ResourceNotFoundException(Long resourceId,Integer status,String message){
        super(message);
        this.status = status;
        this.resourceId = resourceId;
    }
}
