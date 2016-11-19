package net.orekyuu.repository.dao;

import net.orekyuu.repository.entity.Room;
import net.orekyuu.repository.entity.RoomImage;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.Optional;

@Dao
@ConfigAutowireable
public interface RoomImageDao {

    @Insert
    Result<RoomImage> insert(RoomImage roomImage);

    @Delete
    Result<RoomImage> delete(RoomImage roomImage);

    @Update
    Result<RoomImage> update(RoomImage roomImage);

    @Select
    Optional<RoomImage> findByRoom(long roomId);
}
