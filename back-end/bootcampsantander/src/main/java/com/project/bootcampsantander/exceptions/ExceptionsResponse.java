package com.project.bootcampsantander.exceptions;

//classe criada somente para passar a mensagem pro exception
public class ExceptionsResponse {

    private String message;

    public ExceptionsResponse(String message){

        this.message = message;
    }

    public String getMessage() {

        return message;
    }

}
