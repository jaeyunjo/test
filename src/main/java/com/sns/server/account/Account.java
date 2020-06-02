package com.sns.server.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sns.server.enums.Gender;
import com.sns.server.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Where(clause = "deleted IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty
    @JsonIgnore
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotEmpty
    private String birth;

    @NotEmpty
    private String tel;

    @Column(name = "user_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

//    @Column(name = "social_provider")
//    @Enumerated(value = EnumType.STRING)
//    private SocialProviders socialProvider;

    @Column(name = "social_id")
    private Long socialId;

    @Column(name = "profile_href")
    private String profileHref;

//    @OneToMany(mappedBy = "account")
//    private Set<Token> token;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    private LocalDateTime deleted;

    @Builder
    public Account(String email, String firstName, String lastName,
                   String password, Gender gender, String birth, String tel,
                   UserRole userRole, Long socialId, String profileHref,
                   LocalDateTime deleted) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.tel = tel;
        this.userRole = userRole;
        this.socialId = socialId;
        this.profileHref = profileHref;
        this.deleted = deleted;
    }
}
