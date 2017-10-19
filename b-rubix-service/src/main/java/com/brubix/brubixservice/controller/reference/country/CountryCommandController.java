package com.brubix.brubixservice.controller.reference.country;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.country.CountryCommandHandler;
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

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = "/countries",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
public class CountryCommandController {

    private CountryCommandHandler countryCommandHandler;

    public CountryCommandController(CountryCommandHandler countryCommandHandler) {
        this.countryCommandHandler = countryCommandHandler;
    }

    @PostMapping(path = "")
    @ApiOperation(
            value = "Save countries with states",
            notes = "Save countries with states",
            code = 204,
            response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity saveCountries(
            @ApiParam(name = "Countries",
                    value = "List of countries to be saved",
                    required = true) @RequestBody @Valid CountryForm countryForm) {
        countryCommandHandler.save(countryForm.getCountries());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
