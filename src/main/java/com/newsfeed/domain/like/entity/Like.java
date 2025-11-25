package com.newsfeed.domain.like.entity;

import com.newsfeed.domain.comment.entity.Comment;
import com.newsfeed.common.entity.*;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * 유저가 뉴스피드 또는 댓글에 남긴 '좋아요' 정보를 저장하는 엔티티입니다.
 * 하나의 Like는 반드시 User와 연결되며, Newsfeed 또는 Comment 중 하나에만 매핑됩니다.
 * 게시물·댓글의 좋아요 수를 계산하거나 사용자 활동을 추적하기 위해 활용됩니다.
 */
@Getter
@Entity
@Table(name="likes")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Like extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="newsfeed_id" )
    private Newsfeed newsfeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    private Comment comment;

    public Like(User user, Newsfeed newsfeed, Comment comment) {
        this.user = user;
        this.newsfeed = newsfeed;
        this.comment = comment;
    }
}
