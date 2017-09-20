package com.brubix.brubixservice.controller.reference.country;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.loader.Loader;
import com.brubix.entity.reference.Country;
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
@RequestMapping(
        path = {"/reference"},
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE_TAG}, description = StringUtils.SPACE)
public class CountryLoaderController {

    private Loader<CountryForm.CountryData, Country> countryDataLoader;

    public CountryLoaderController(Loader<CountryForm.CountryData, Country> countryDataLoader) {
        this.countryDataLoader = countryDataLoader;
    }

    @PostMapping("/country")
    @ApiOperation(
            value = "Load countries with states",
            notes = "Load countries with states",
            code = 200,
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity loadCountries(
            @ApiParam(name = "Countries",
                    value = "List of countries to be loaded",
                    required = true) @RequestBody @Valid CountryForm countryForm) {
        countryDataLoader.load(countryForm.getCountries());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
