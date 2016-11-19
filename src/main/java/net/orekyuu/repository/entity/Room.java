package net.orekyuu.repository.entity;

import org.seasar.doma.*;

import java.util.Objects;

@Entity(immutable = true)
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private final Long roomId;
    @Column(name = "title")
    private final String title;
    @Column(name = "room_name")
    private final RoomName roomName;

    public Room(Long roomId, String title, RoomName roomName) {
        this.roomId = roomId;
        this.title = title;
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getTitle() {
        return title;
    }

    public RoomName getRoomName() {
        return roomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
