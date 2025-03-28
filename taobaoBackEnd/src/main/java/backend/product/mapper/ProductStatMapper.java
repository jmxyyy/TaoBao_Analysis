package backend.product.mapper;

import backend.product.entity.ProductStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductStatMapper {
    @Select("select * from product_stat order by productCategory")
    List<ProductStat> queryProductStat();
}
