package com.ms.mscreditappraiser.utils.exception;

public class StatusCodeNotFoundClientException extends Exception{
    public StatusCodeNotFoundClientException(){
        super("Client data not found for the CPF entered");
    }
}
