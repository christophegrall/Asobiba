package net.orekyuu.controller.model;

public class SseRoomImageMessage {

    private RoomImageMessageType type;
    private long roomId;

    public RoomImageMessageType getType() {
        return type;
    }

    public void setType(RoomImageMessageType type) {
        this.type = type;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
