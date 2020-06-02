package com.sns.server.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sns.server.enums.Gender;
import com.sns.server.enums.UserRole;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AccountDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {

        @Email(message = "이메일 형식에 맞게 정확히 입력해주세요.")
        private String email;

        @NotBlank(message = "영문 이름을 정확히 입력해주세요.")
        private String firstName;

        @NotBlank(message = "영문 성을 정확히 입력해주세요.")
        private String lastName;

        @NotBlank(message = "패스워드를 정확히 입력해주세요.")
        private String password;

        @Enumerated(EnumType.STRING)
        private Gender gender;

        @NotBlank(message = "생일 형식에 맞게 정확히 입력해주세요.")
        private String birth;

        @NotBlank(message = "전화번호 형식에 맞 정확히 입력해주세요.")
        private String tel;

        @Enumerated(value = EnumType.STRING)
        private UserRole userRole;


        private Long socialId;

        private String profileHref;

        @JsonCreator
        public Account convert() {
            return Account.builder()
                    .email(this.email)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .password(this.password)
                    .gender(this.gender)
                    .birth(this.birth)
                    .tel(this.tel)
                    .userRole(this.userRole)
                    .socialId(this.socialId)
                    .profileHref(this.profileHref)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Read {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private Gender gender;
        private String birth;
        private String tel;


        public static Read from(Account account) {
            return Read.builder()
                    .id(account.getId())
                    .email(account.getEmail())
                    .firstName(account.getFirstName())
                    .lastName(account.getLastName())
                    .gender(account.getGender())
                    .birth(account.getBirth())
                    .tel(account.getTel())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Update {

        @NotBlank(message = "영문 이름을 정확히 입력해주세요.")
        private String firstName;

        @NotBlank(message = "영문 성을 정확히 입력해주세요.")
        private String lastName;


        @NotBlank(message = "패스워드를 정확히 입력해주세요.")
        private String password;


        @NotBlank(message = "전화번호 형식에 맞 정확히 입력해주세요.")
        private String tel;
    }

}
