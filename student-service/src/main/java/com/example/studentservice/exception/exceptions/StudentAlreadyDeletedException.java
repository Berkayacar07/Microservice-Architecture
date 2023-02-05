package com.example.studentservice.exception.exceptions;

import com.example.studentservice.enums.Language;
import com.example.studentservice.exception.enums.IFriendlyMessageCode;
import com.example.studentservice.exception.utils.FriendlyMessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StudentAlreadyDeletedException extends RuntimeException{
    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;

    public StudentAlreadyDeletedException(Language language, IFriendlyMessageCode friendlyMessageCode,
                                          String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[ProductAlreadyDeleted] -> message: {} developer message : {}",
                FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode), message);
    }
}

