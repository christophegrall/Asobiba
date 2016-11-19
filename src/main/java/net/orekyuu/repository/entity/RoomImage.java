package net.orekyuu.repository.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.util.Objects;

@Entity(immutable = true)
@Table(name = "room_image")
public class RoomImage {

    @Id
    @Column(name = "room_id")
    private final Long roomId;

    @Column(name = "data")
    private final byte[] data;

    public RoomImage(Long roomId, byte[] data) {
        this.roomId = roomId;
        this.data = data;
    }

    public Long getRoomId() {
        return roomId;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomImage roomImage = (RoomImage) o;
        return Objects.equals(roomId, roomImage.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
