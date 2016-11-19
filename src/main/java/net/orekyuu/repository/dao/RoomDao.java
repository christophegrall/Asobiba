package net.orekyuu.repository.dao;

import net.orekyuu.repository.entity.Room;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface RoomDao {

    @Insert
    Result<Room> insert(Room room);

    @Delete
    Result<Room> delete(Room room);

    @Update
    Result<Room> update(Room room);

    @Select
    Optional<Room> findById(long id);

    @Select
    List<Room> all();
}
