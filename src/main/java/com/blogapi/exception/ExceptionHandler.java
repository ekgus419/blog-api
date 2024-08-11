package com.blogapi.exception;

import com.blogapi.dto.response.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public ResponseEntity<? super ResponseDto> handleException(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        StackTraceElement caller = stackTrace.length > 0 ? stackTrace[0] : null;
        String className = (caller != null) ? caller.getClassName() : "Unknown class";
        String methodName = (caller != null) ? caller.getMethodName() : "Unknown method";
        int lineNumber = (caller != null) ? caller.getLineNumber() : -1;

        logger.error("An error occurred in class: {}, method: {}, line: {}. Error: ", className, methodName, lineNumber, exception);

        return ResponseDto.databaseError();
    }

    public String handleFileUploadException(MultipartFile file, String savePath) {
        logger.error("Error occurred while uploading file: {}", file.getOriginalFilename());
        try {
            file.transferTo(new File(savePath));
        } catch (IOException e) {
            logger.error("Failed to save file to path: {}", savePath, e);
            return null;
        }
        return null;
    }

    public Resource handleFileRetrievalException(String filePath) {
        logger.error("Error occurred while retrieving file from path: {}", filePath);
        return null;
    }
}
