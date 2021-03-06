package com.brubix.reference.controller.language;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.language.LanguageCommandHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/languages",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.LANGUAGE_TAG}, description = StringUtils.SPACE)
public class LanguageCommandController {

    private LanguageCommandHandler languageCommandHandler;

    public LanguageCommandController(LanguageCommandHandler languageCommandHandler) {
        this.languageCommandHandler = languageCommandHandler;
    }


    @PostMapping(path = "")
    @ApiOperation(
            value = "Create reference languages in system",
            notes = "Create reference languages in system",
            code = 204,
            response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity saveLanguages(
            @ApiParam(name = "Languages",
                    value = "List of languages to be saved",
                    required = true) @RequestBody @Valid LanguageRequest languageForm) {
        languageCommandHandler.save(languageForm.getLanguages());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
