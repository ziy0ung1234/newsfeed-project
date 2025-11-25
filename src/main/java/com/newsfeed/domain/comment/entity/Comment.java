package com.newsfeed.common.entity;

import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int depth;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",  nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="newsfeed_id",  nullable = false)
    private Newsfeed newsfeedId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="parent_comment_id",  nullable = false)
    private Comment parentCommentId;

    public Comment(String content, int depth, User userId, Newsfeed newsfeedId, Comment parentCommentId) {
        this.content = content;
        this.depth = depth;
        this.userId = userId;
        this.newsfeedId = newsfeedId;
        this.parentCommentId = parentCommentId;

    }

}
