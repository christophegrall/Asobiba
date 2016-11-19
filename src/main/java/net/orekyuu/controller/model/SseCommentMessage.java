package net.orekyuu.controller.model;

public class SseCommentMessage {

    private CommentMessageType type;
    private CommentModel data;

    public SseCommentMessage(CommentMessageType type, CommentModel data) {
        this.type = type;
        this.data = data;
    }

    public SseCommentMessage() {
    }

    public CommentMessageType getType() {
        return type;
    }

    public void setType(CommentMessageType type) {
        this.type = type;
    }

    public CommentModel getData() {
        return data;
    }

    public void setData(CommentModel data) {
        this.data = data;
    }
}
