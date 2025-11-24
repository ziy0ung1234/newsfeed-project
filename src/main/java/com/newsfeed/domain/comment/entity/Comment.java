package com.newsfeed.domain.comment.entity;

import com.newsfeed.common.entity.*;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int depth;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",  nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="newsfeed_id",  nullable = false)
    private Newsfeed newsfeed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="parent_comment_id",  nullable = false)
    private Comment parentComment;

    public Comment(String content, int depth, User user, Newsfeed newsfeed, Comment parentComment) {
        this.content = content;
        this.depth = depth;
        this.user = user;
        this.newsfeed = newsfeed;
        this.parentComment = parentComment;
    }
}
