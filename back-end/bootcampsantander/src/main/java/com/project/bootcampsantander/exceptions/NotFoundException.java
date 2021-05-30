package com.project.bootcampsantander.exceptions;

import com.project.bootcampsantander.util.MessageUtils;

public class NotFoundException extends RuntimeException {

    public NotFoundException(){
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
