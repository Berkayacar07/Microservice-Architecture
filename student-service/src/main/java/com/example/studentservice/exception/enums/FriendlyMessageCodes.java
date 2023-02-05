package com.example.studentservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{
    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
    STUDENT_NOT_CREATED_EXCEPTION(1500), //Hata classı oldugu için 1500 de başlattık
    STUDENT_SUCCESSFULLY_CREATED(1501),
    STUDENT_NOT_FOUND_EXCEPTION(1502),
    STUDENT_SUCCESSFULLY_UPDATED(1503),
    STUDENT_ALREADY_DELETED(1504),
    STUDENT_SUCCESSFULLY_DELETED(1505);

    private final int value;

    FriendlyMessageCodes(int value) {
        this.value = value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
