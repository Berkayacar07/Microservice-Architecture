package com.example.studentservice.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
// tek tipte bir response yapısı oluştrup her response için bunu kullancaz
public class InternalApiResponse <T> {
    private T payload; // T ye genelde response nesneleri gelecek
    private FriendlyMessage friendlyMessage;
    private boolean hasError;
    private List<String> errorMessages;
    private HttpStatus httpStatus;
}
