package com.yo1000.skeleton.domain.model;

import java.util.Date;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
public class SearchCondition {
    private Date from;
    private Date to;
    private String storeId;
    private String customerId;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
