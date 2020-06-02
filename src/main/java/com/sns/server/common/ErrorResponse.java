package com.sns.server.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ErrorResponse {
    @ApiModelProperty(notes = "요청에 대햔 결과 데이터(단일 오브젝트인 경우)")
    private HttpStatus status;

    @ApiModelProperty(notes = "응답 HTTP STATUS CODE가 2XX가 아닌 경우 에러에 관한 메세지")
    private String message;
}

