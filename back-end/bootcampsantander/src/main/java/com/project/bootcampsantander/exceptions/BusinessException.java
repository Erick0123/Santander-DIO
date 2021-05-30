package com.project.bootcampsantander.exceptions;

//RuntimeException diz para o java que isso é uma excessão que pode dar erro
public class BusinessException extends RuntimeException {

    //construtor é o que receberá a messangem que deu erro
    public BusinessException(String message) {

        //Depois passa essa mensagem pro pai dele(RuntimeException)
        // para o pai dele propagar essa messagem pra mim
        super(message);
    }
}
