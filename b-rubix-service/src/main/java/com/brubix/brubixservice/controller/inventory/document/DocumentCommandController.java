package com.brubix.brubixservice.controller.inventory.document;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.inventory.document.DocumentCommandHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = {"/documents"})
@Api(tags = {ApplicationConstant.DOCUMENTS}, description = StringUtils.SPACE)
public class DocumentCommandController {


    private DocumentCommandHandler documentCommandHandler;

    public DocumentCommandController(DocumentCommandHandler documentCommandHandler) {
        this.documentCommandHandler = documentCommandHandler;
    }

    @PostMapping(path = "/{uid}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @ApiOperation(
            value = "Upload document",
            notes = "Upload document for human/non human actors, " +
                    "Currently supports document upload for SCHOOL by school code",
            code = 204, response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_DOCUMENT_FILE_UPLOADS, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_FILE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity uploadDocument(
            @ApiParam(name = "uid", value = "Any uid of the entity for which " +
                    "document getting uploaded",
                    required = true)
            @PathVariable(value = "uid") String uid,

            @ApiParam(name = "Document details", value = "Document details")
            @RequestPart(value = "documents") DocumentForm documentForm,

            @ApiParam(name = "attachments", value = "attachments")
            @RequestPart(value = "attachments") List<MultipartFile> attachments) {

        documentForm.setUid(uid);
        documentForm.setAttachments(attachments);
        documentCommandHandler.upload(documentForm);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}