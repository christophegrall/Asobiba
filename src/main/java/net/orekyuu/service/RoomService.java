package net.orekyuu.service;

import net.orekyuu.repository.dao.RoomCommentDao;
import net.orekyuu.repository.dao.RoomDao;
import net.orekyuu.repository.dao.RoomImageDao;
import net.orekyuu.repository.entity.Room;
import net.orekyuu.repository.entity.RoomImage;
import net.orekyuu.repository.entity.RoomName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoomService {

    private final RoomDao roomDao;
    private final RoomCommentDao commentDao;
    private final RoomImageDao imageDao;

    public RoomService(RoomDao roomDao, RoomCommentDao commentDao, RoomImageDao imageDao) {
        this.roomDao = roomDao;
        this.commentDao = commentDao;
        this.imageDao = imageDao;
    }

    public List<Room> findAll() {
        return roomDao.all();
    }

    public Optional<Room> find(long id) {
        return roomDao.findById(id);
    }

    @Transactional(readOnly = false)
    public Room createRoom(String title, RoomName roomName) {
        Room room = new Room(null, title, roomName);
        return roomDao.insert(room).getEntity();
    }

    @Transactional(readOnly = false)
    public Optional<Room> updateRoomTitle(long roomId, String title) {
        return roomDao.findById(roomId)
                .map(room -> new Room(roomId, title, room.getRoomName()))
                .map(room -> roomDao.update(room).getEntity());
    }

    @Transactional(readOnly = false)
    public Room deleteRoom(long roomId) {
        commentDao.deleteByRoom(roomId);
        imageDao.delete(new RoomImage(roomId, null));
        return roomDao.delete(new Room(roomId, null, null)).getEntity();
    }
}
