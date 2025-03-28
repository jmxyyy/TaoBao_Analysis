package backend.product.entity;

public class ProductStat {
    private String productId;
    private String productCatetory;
    private String eventType;
    private long cnt;
    private long total_sales;
    private long total_favorites;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCatetory;
    }

    public void setProductCategory(String productCategory) {
        this.productCatetory = productCategory;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public long getTotalSales() {
        return total_sales;
    }

    public void setTotalSales(long total_sales) {
        this.total_sales = total_sales;
    }

    public long getTotalFav() {
        return total_favorites;
    }

    public void setTotalFav(long total_favorites) {
        this.total_favorites = total_favorites;
    }
}