package backend.product.mapper;

import backend.product.entity.ProductStat01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductStatMapper01 {
    @Select("SELECT 'pv' AS event_type, productId, productCategory, total " +
            "FROM (SELECT productId, productCategory, SUM(CAST(cnt AS UNSIGNED)) AS total " +
            "FROM product_stat WHERE event_type = 'pv' " +
            "GROUP BY productId, productCategory ORDER BY total DESC LIMIT 1) AS pv_stats " +
            "UNION ALL " +
            "SELECT 'fav' AS event_type, productId, productCategory, total " +
            "FROM (SELECT productId, productCategory, SUM(CAST(cnt AS UNSIGNED)) AS total " +
            "FROM product_stat WHERE event_type = 'fav' " +
            "GROUP BY productId, productCategory ORDER BY total DESC LIMIT 1) AS fav_stats " +
            "UNION ALL " +
            "SELECT 'cart' AS event_type, productId, productCategory, total " +
            "FROM (SELECT productId, productCategory, SUM(CAST(cnt AS UNSIGNED)) AS total " +
            "FROM product_stat WHERE event_type = 'cart' " +
            "GROUP BY productId, productCategory ORDER BY total DESC LIMIT 1) AS cart_stats;"
    )
    List<ProductStat01> queryProductStat01();
}