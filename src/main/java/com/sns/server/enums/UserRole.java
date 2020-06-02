package com.sns.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum  UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String roleName;

    public boolean isCorrectName(String name) {
         return name.equalsIgnoreCase(this.roleName);
    }

    public static UserRole getRoleByName(String roleName) {
        // TODO: UserRole 관련 상세 exception 클래스 추가
        return Arrays.stream(UserRole.values())
                .filter(r -> r.isCorrectName(roleName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("검색된 권한이 없습니다."));
    }
}
