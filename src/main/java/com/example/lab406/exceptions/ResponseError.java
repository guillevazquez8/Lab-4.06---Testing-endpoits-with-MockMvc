package com.example.lab406.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseError {
    private String errorDescription;
    private String status;
}
