package com.example.myrok.dto.exception;

import com.example.myrok.type.ErrorCode;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ArgumentNotValidExceptionDto extends ExceptionDto {
    public final Map<String, String> errorFields;

    public ArgumentNotValidExceptionDto(
            final MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        super(ErrorCode.MISMATCH_PARAMETER_TYPE);
        this.errorFields = new HashMap<>();
        methodArgumentNotValidException.getBindingResult()
                .getAllErrors().forEach(
                        e -> this.errorFields.put(
                                ((FieldError) e).getField(),
                                e.getDefaultMessage()
                        )
                );
    }

}