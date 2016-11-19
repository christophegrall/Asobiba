package net.orekyuu.service;

import net.orekyuu.controller.model.CommentMessageType;
import net.orekyuu.controller.model.CommentModel;
import net.orekyuu.repository.dao.RoomCommentDao;
import net.orekyuu.repository.entity.RoomComment;
import org.seasar.doma.Delete;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoomCommentService {

    private final RoomCommentDao commentDao;
    private final PushNotificationService pushService;

    public RoomCommentService(
            PushNotificationService pushService,
            RoomCommentDao commentDao) {
        this.commentDao = commentDao;
        this.pushService = pushService;
    }

    public List<RoomComment> findByRoom(long roomId) {
        return commentDao.findByRoom(roomId);
    }

    public Optional<RoomComment> findById(long commentId) {
        return commentDao.findById(commentId);
    }

    @Transactional(readOnly = false)
    public RoomComment createComment(long roomId, String userName, String comment) {
        RoomComment roomComment = new RoomComment(null, roomId, userName, comment, LocalDateTime.now());
        pushService.pushAll(new CommentModel(roomComment), CommentMessageType.COMMENT_CREATE);
        return commentDao.insert(roomComment).getEntity();
    }

    @Transactional(readOnly = false)
    public RoomComment delete(long commentId) {
        RoomComment comment = new RoomComment(commentId, null, null, null, null);
        pushService.pushAll(new CommentModel(comment), CommentMessageType.COMMENT_DELETE);
        return commentDao.delete(comment).getEntity();
    }

}
