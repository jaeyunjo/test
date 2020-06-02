package com.sns.server.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ApiResponse {

    @ApiModelProperty(notes = "요청에 대 결과 데이터(리스트)")
    private List<?> list;

    @ApiModelProperty(notes = "요청에 대한 결과 데이터(오브젝트)")
    private Object data;

    @ApiModelProperty(notes = "HTTP response code가 2XX가 아닌, 에러에 관한 메세지")
    private String message;

    @ApiModelProperty(notes = "HTTP response code")
    private HttpStatus status;
}
