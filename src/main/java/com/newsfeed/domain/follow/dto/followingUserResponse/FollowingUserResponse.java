package com.newsfeed.domain.follow.dto.followingUserResponse;

import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import lombok.Getter;
import org.springframework.data.domain.ManagedTypes;

import java.time.LocalDateTime;

@Getter
public class FollowingUserResponse  {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    private FollowingUserResponse(Long id, String title, String content, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static FollowingUserResponse of(Newsfeed newsfeed) {
        return new FollowingUserResponse(
                newsfeed.getId(),
                newsfeed.getTitle(),
                newsfeed.getContent(),
                newsfeed.getCreatedAt(),
                newsfeed.getModifiedAt()
        );
    }

}
