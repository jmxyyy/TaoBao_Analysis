package backend.mapper;

import backend.entity.UserBehavior;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserBehaviorMapper {
    @Select("select * from user_behavior")
    List<UserBehavior> queryUserBehavior();
}
