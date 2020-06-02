package com.sns.server.account;

import com.sns.server.common.ApiResponse;
import com.sns.server.security.AccountContext;
import com.sns.server.security.AccountContextService;
import com.sns.server.security.SecurityAccount;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AccountDtoValidator accountDtoValidator;
    private final AccountContextService accountContextService;

    public ResponseEntity<?> sendErrorResponse(Errors errors) {
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .message(errors.getAllErrors().get(0).getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }

    @PostMapping("/users")
    @CrossOrigin
    @ApiOperation(value = "회원가입")
    public ResponseEntity<?> create(@RequestBody @Valid final AccountDto.Create accountDto,
                                    @ApiIgnore Errors errors, BindingResult result) {
        accountDtoValidator.validate(result, errors);
        if (errors.hasErrors()) {
            return sendErrorResponse(errors);
        }

        accountService.create(accountDto);
        ApiResponse response = ApiResponse.builder()
                .message("회원가입이 완료되었습니다.")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/users/me")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @CrossOrigin
    @ApiOperation(value = "회원정보 요청")
    @ApiResponses({@io.swagger.annotations.ApiResponse(code = 400, message = "클라이언트에서 잘못된 요청함."),
            @io.swagger.annotations.ApiResponse(code = 401, message = "비인증된 클라이언트에서 요청함."),
            @io.swagger.annotations.ApiResponse(code = 403, message = "요청한 클라이언트는 서버에 접근할 권한이 없음."),
            @io.swagger.annotations.ApiResponse(code = 404, message = "클라이언트에서 요청했으나 찾으려는 사용가 존재하지 않음.")})
    public ResponseEntity get(Authentication principal) {
//        AccountContext accountContext = (AccountContext) accountContextService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));
//        log.info("ROLE:" + authentication.getAuthorities());
//        log.info("ACCOUNT:" + accountContext.getAccount());
//
//        ApiResponse response = ApiResponse.builder()
//                .data(AccountDto.Read.from(accountContext.getAccount()))
//                .status(HttpStatus.OK)
//                .build();
//        return ResponseEntity.status(response.getStatus()).body(response);
        return null;
    }

    @PutMapping("/users/update")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @CrossOrigin
    @ApiOperation(value = "회원정보 수정")
    public ResponseEntity<?> update(Authentication authentication,
                                    @RequestBody @Valid final AccountDto.Update accountDto,
                                    @ApiIgnore Errors errors, BindingResult result) {
        accountDtoValidator.validate(result, errors);
        if (errors.hasErrors()) {
            return sendErrorResponse(errors);
        }

        AccountContext accountContext = (AccountContext) accountContextService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));

        accountService.update(accountContext.getAccount().getId(), accountDto);
        ApiResponse response = ApiResponse.builder()
                .message("회원정보 수정이 완료되었습니다.")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/users/delete")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @CrossOrigin
    @ApiOperation(value = "회원탈퇴")
    public ResponseEntity delete(Authentication authentication) {
        AccountContext accountContext = (AccountContext) accountContextService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));
        accountService.delete(accountContext.getAccount().getId());
        ApiResponse response = ApiResponse.builder()
                .message("회원탈퇴가 완료되었습니다.")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
