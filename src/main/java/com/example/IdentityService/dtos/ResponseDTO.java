package com.example.IdentityService.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Getter
@Builder
@Setter
public class ResponseDTO<T> {
    private String message;
    private int status;
    private T data;

    public ResponseDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static <T> ResponseDTO success(T t) {
        return ResponseDTO.builder()
                .data(t)
                .message("OK")
                .status(HttpStatus.SC_OK)
                .build();
    }

    public ResponseDTO(String message, int status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ResponseDTO() {

    }
}
