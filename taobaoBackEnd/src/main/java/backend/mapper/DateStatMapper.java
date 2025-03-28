package backend.mapper;

import backend.entity.DateStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DateStatMapper {
    @Select("select * from date_stat order by date")
    List<DateStat> queryDateStat();
}
