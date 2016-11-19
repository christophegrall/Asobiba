package net.orekyuu.service;

import net.orekyuu.controller.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PushNotificationService {

    private final List<SseEmitter> lobbyEmitters = new CopyOnWriteArrayList<>();
    private final Map<Long, List<SseEmitter>> roomEmitters = new ConcurrentHashMap<>();

    public SseEmitter createLobbyEmitter() {
        SseEmitter emitter = new SseEmitter(-1L);
        lobbyEmitters.add(emitter);
        emitter.onCompletion(() -> lobbyEmitters.remove(emitter));
        return emitter;
    }

    public SseEmitter createRoomEmitter(long roomId) {
        SseEmitter emitter = new SseEmitter(-1L);
        if (!roomEmitters.containsKey(roomId)) {
            roomEmitters.put(roomId, new CopyOnWriteArrayList<>());
        }
        roomEmitters.get(roomId).add(emitter);

        emitter.onCompletion(() -> {
            List<SseEmitter> emitters = roomEmitters.get(roomId);
            if (emitters != null) {
                emitters.remove(emitter);
                if (emitters.isEmpty()) {
                    roomEmitters.remove(roomId);
                }
            }

        });
        return emitter;
    }

    public void pushAll(RoomModel model, RoomMessageType type) {
        lobbyEmitters.forEach(it -> {
            try {
                it.send(new SseRoomMessage(type, model));
            } catch (IOException ignored) {
                it.complete();
                lobbyEmitters.remove(it);
            }
        });

        List<SseEmitter> roomEmitters = this.roomEmitters.get(model.getRoomId());
        if (roomEmitters == null) {
            return;
        }

        roomEmitters.forEach(it -> {
            try {
                it.send(new SseRoomMessage(type, model));
            } catch (IOException ignored) {
                it.complete();
                roomEmitters.remove(it);
            }
        });
    }

    public void pushAll(CommentModel model, CommentMessageType type) {
        List<SseEmitter> roomEmitters = this.roomEmitters.get(model.getRoomId());
        if (roomEmitters == null) {
            return;
        }

        roomEmitters.forEach(it -> {
            try {
                it.send(new SseCommentMessage(type, model));
            } catch (IOException ignored) {
                it.complete();
                roomEmitters.remove(it);
            }
        });
    }

    public void pushAll(RoomImageMessageType type, long roomId) {
        SseRoomImageMessage message = new SseRoomImageMessage();
        message.setType(type);
        message.setRoomId(roomId);
        lobbyEmitters.forEach(it -> {
            try {
                it.send(message);
            } catch (IOException ignored) {
                it.complete();
                lobbyEmitters.remove(it);
            }
        });
    }
}
