

package com.brubix.brubixservice.exception.error;

import com.brubix.brubixservice.exception.validation.FieldErrorDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.brubix.brubixservice.constant.ApplicationConstant.ERROR_INFO_URL;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String code;
    private String message;
    private List<FieldErrorDetail> fieldErrors;


    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public String getInfo() {
        return String.format("%s/%s", ERROR_INFO_URL, code);
    }

}
