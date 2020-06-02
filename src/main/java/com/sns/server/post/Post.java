package com.sns.server.post;

import com.sns.server.account.Account;
import com.sns.server.hashtag.HashTag;
import com.sns.server.love.Love;
import com.sns.server.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "post")
@Where(clause = "deleted IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "post")
    private Set<Reply> reply;

//    @ManyToOne
//    private History history;

    @ManyToMany
    @JoinTable(name = "post_hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private Set<HashTag> hashTags;

    @OneToMany(mappedBy = "post")
    private Set<Love> loves;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    private LocalDateTime deleted;
}
