package backend.product.entity;

public class ProductStat01 {
    private String productId;
    private String productCatetory;
    private String eventType;
    private long total;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}