package com.sns.server.history;


import com.sns.server.account.Account;
import com.sns.server.post.Post;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name = "history")
//@Getter
//@Setter
//@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "history")
    private Set<Post> posts = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;
}
