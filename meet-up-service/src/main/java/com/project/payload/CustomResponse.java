package com.project.payload;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomResponse {


    private String message;
    private Object data;
}
