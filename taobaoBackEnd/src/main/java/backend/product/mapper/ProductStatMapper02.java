package backend.product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import backend.product.entity.ProductStat;

@Mapper
public interface ProductStatMapper02 {
    // @Select("select * from product_stat order by productCategory limit 100000")
    @Select("SELECT productId, productCategory, SUM(CASE WHEN event_type = 'buy' THEN CAST(cnt AS UNSIGNED) ELSE 0 END) AS total_sales, SUM(CASE WHEN event_type = 'fav' THEN CAST(cnt AS UNSIGNED) ELSE 0 END) AS total_favorites " +
            "FROM product_stat GROUP BY productId, productCategory "
            //"ORDER BY total_sales DESC, total_favorites DESC limit 500;"
    )
    List<ProductStat> queryProductStat02();
}
    
    

