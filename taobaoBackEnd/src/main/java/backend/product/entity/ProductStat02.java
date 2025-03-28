package backend.product.entity;

import java.util.List;

public class ProductStat02 {
    private List<String> productCategoryList;
    private List<List<Long>> eventCnt;

    public List<String> getProductCategoryList() {
        return productCategoryList;
    }

    public void setproductCategoryList(List<String> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public List<List<Long>> getEventCnt() {
        return eventCnt;
    }

    public void setEventCnt(List<List<Long>> eventCnt) {
        this.eventCnt = eventCnt;
    }
}
