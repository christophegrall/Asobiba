package net.orekyuu.controller.model;

public class SseRoomMessage {

    private RoomMessageType type;
    private RoomModel data;

    public SseRoomMessage(RoomMessageType type, RoomModel data) {
        this.type = type;
        this.data = data;
    }

    public SseRoomMessage() {
    }

    public RoomMessageType getType() {
        return type;
    }

    public void setType(RoomMessageType type) {
        this.type = type;
    }

    public RoomModel getData() {
        return data;
    }

    public void setData(RoomModel data) {
        this.data = data;
    }
}
