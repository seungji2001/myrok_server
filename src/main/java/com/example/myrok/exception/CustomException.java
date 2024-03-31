package com.example.myrok.exception;

import com.example.myrok.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
  private ErrorCode errorCode;
  private HttpStatus httpStatus;

  public CustomException(ErrorCode errorCode){
    super(errorCode.getMessage());
  }
}
