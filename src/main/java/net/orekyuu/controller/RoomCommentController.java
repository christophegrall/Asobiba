package net.orekyuu.controller;

import net.orekyuu.controller.model.CommentMessageType;
import net.orekyuu.controller.model.CommentModel;
import net.orekyuu.controller.model.RoomModel;
import net.orekyuu.repository.entity.RoomComment;
import net.orekyuu.service.PushNotificationService;
import net.orekyuu.service.RoomCommentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class RoomCommentController {

    private final RoomCommentService commentService;
    private final PushNotificationService pushService;

    public RoomCommentController(
            PushNotificationService pushService,
            RoomCommentService commentService) {
        this.pushService = pushService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<RoomComment> roomComments(@RequestParam("roomId") long id) {
        return commentService.findByRoom(id);
    }

    @PostMapping
    public RoomComment create(@RequestBody CommentModel comment) {
        RoomComment result = commentService.createComment(comment.getRoomId(), comment.getUserName(), comment.getComment());
        return result;
    }

    @DeleteMapping("{id}")
    public RoomComment delete(@PathVariable("id") long id) {
        RoomComment result = commentService.delete(id);
        return result;
    }

    @GetMapping("{id}/stream")
    public SseEmitter sseEmitter(@PathVariable("id") long id) {
        return pushService.createRoomEmitter(id);
    }
}
