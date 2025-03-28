package backend.mapper;

import backend.entity.HourlyBehavior;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HourlyBehaviorMapper {
    @Select("select * from hourly_behavior order by hour")
    List<HourlyBehavior> queryHourlyBehavior();
}
