package com.yo1000.skeleton.domain.repository;

import com.yo1000.skeleton.domain.model.SalesSummary;
import com.yo1000.skeleton.domain.model.SearchCondition;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
public interface SalesRepository {
    public List<SalesSummary> findSummaryByYearMonth(SearchCondition condition);
}
