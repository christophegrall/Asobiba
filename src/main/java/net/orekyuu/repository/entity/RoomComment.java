package net.orekyuu.repository.entity;

import org.seasar.doma.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(immutable = true)
@Table(name = "room_comments")
public class RoomComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private final Long commentId;
    @Column(name = "room_id")
    private final Long roomId;
    @Column(name = "comment")
    private final String comment;
    @Column(name = "user_name")
    private final String userName;
    @Column(name = "commented_at")
    private final LocalDateTime commentedAt;

    public RoomComment(Long commentId, Long roomId, String userName, String comment, LocalDateTime commentedAt) {
        this.commentId = commentId;
        this.roomId = roomId;
        this.comment = comment;
        this.userName = userName;
        this.commentedAt = commentedAt;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomComment that = (RoomComment) o;
        return Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
