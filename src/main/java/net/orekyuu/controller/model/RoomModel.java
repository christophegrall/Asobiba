package net.orekyuu.controller.model;

import net.orekyuu.repository.entity.Room;

public class RoomModel {

    private Long roomId;
    private String title;
    private String roomName;

    public RoomModel() {
    }

    public RoomModel(Room room) {
        roomId = room.getRoomId();
        title = room.getTitle();
        if (room.getRoomName() != null) {
            roomName = room.getRoomName().name();
        }
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
