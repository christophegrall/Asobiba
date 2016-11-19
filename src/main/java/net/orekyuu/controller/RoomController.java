package net.orekyuu.controller;

import net.orekyuu.controller.model.RoomMessageType;
import net.orekyuu.controller.model.RoomModel;
import net.orekyuu.repository.entity.Room;
import net.orekyuu.repository.entity.RoomName;
import net.orekyuu.service.PushNotificationService;
import net.orekyuu.service.RoomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final PushNotificationService pushService;

    public RoomController(
            RoomService roomService,
            PushNotificationService pushService) {
        this.roomService = roomService;
        this.pushService = pushService;
    }

    private final List<SseEmitter> lobbyEmitters = new CopyOnWriteArrayList<>();

    @GetMapping
    public List<Room> all() {
        return roomService.findAll();
    }

    @GetMapping("{id}")
    public Room find(@PathVariable("id") long id) {
        return roomService.find(id).orElseThrow(RoomNotFoundException::new);
    }

    @PostMapping
    public Room create(@RequestBody RoomModel room) {
        Room result = roomService.createRoom(room.getTitle(), RoomName.valueOf(room.getRoomName()));
        pushService.pushAll(new RoomModel(result), RoomMessageType.ROOM_CREATE);
        return result;
    }

    @DeleteMapping("{id}")
    public Room delete(@PathVariable("id") long id) {
        Room room = roomService.deleteRoom(id);
        pushService.pushAll(new RoomModel(room), RoomMessageType.ROOM_DELETE);
        return room;
    }

    @PutMapping
    public Room update(@RequestBody RoomModel room) {
        Room room1 = roomService.updateRoomTitle(room.getRoomId(), room.getTitle())
                .orElseThrow(RoomNotFoundException::new);

        pushService.pushAll(new RoomModel(room1), RoomMessageType.ROOM_UPDATE);
        return room1;
    }

    @GetMapping("stream")
    public SseEmitter sseEmitter() {
        return pushService.createLobbyEmitter();
    }
}
