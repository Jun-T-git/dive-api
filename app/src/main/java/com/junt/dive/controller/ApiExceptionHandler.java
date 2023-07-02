package com.junt.dive.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller層まで届いた例外をハンドリングするクラス。
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * JsonProcessingExceptionのハンドリング（500エラーレスポンスに変換）
     *
     * @param exception 例外オブジェクト
     * @param request リクエスト
     * @return レスポンス
     */
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonProcessingException(
            JsonProcessingException exception, WebRequest request
    ) {
       logger.info("JsonProcessing Failed.");
       return super.handleExceptionInternal(exception, null, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
