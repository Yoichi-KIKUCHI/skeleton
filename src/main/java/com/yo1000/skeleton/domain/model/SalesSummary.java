package com.yo1000.skeleton.domain.model;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
public class SalesSummary {
    private String storeId;
    private String storeName;
    private Integer salesCount;
    private Long salesTotal;
    private Double ageAverage;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Long getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(Long salesTotal) {
        this.salesTotal = salesTotal;
    }

    public Double getAgeAverage() {
        return ageAverage;
    }

    public void setAgeAverage(Double ageAverage) {
        this.ageAverage = ageAverage;
    }
}
