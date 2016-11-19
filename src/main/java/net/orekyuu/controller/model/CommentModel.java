package net.orekyuu.controller.model;

import net.orekyuu.repository.entity.RoomComment;

import java.time.LocalDateTime;

public class CommentModel {

    private Long commentId;
    private Long roomId;
    private String comment;
    private String userName;
    private LocalDateTime commentedAt;

    public CommentModel(RoomComment comment) {
        commentId = comment.getCommentId();
        roomId = comment.getRoomId();
        this.comment = comment.getComment();
        userName = comment.getUserName();
        commentedAt = comment.getCommentedAt();
    }

    public CommentModel() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }
}
