package com.ms.mscreditappraiser.utils.exception;

import lombok.Getter;

public class ErrorInCommunicationMsException extends Exception{
    @Getter
    private Integer statusCode;

    public ErrorInCommunicationMsException(String msg, Integer statusCode){
        super(msg);
        this.statusCode = statusCode;
    }
}
