package net.orekyuu.repository.dao;

import net.orekyuu.repository.entity.RoomComment;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface RoomCommentDao {

    @Insert
    Result<RoomComment> insert(RoomComment comment);

    @Update
    Result<RoomComment> update(RoomComment comment);

    @Delete
    Result<RoomComment> delete(RoomComment comment);

    @Select
    List<RoomComment> findByRoom(long roomId);

    @Select
    Optional<RoomComment> findById(long commentId);

    @Delete(sqlFile = true)
    int deleteByRoom(long roomId);
}
