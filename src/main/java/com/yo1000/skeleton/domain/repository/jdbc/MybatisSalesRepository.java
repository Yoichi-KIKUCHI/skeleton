package com.yo1000.skeleton.domain.repository.jdbc;

import com.yo1000.skeleton.domain.model.SalesSummary;
import com.yo1000.skeleton.domain.model.SearchCondition;
import com.yo1000.skeleton.domain.repository.SalesRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
@Repository
public class MybatisSalesRepository implements SalesRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisSalesRepository.class);

    @Autowired
    private SqlSessionTemplate template;

    @Override
    public List<SalesSummary> findSummaryByYearMonth(SearchCondition condition) {
        List<SalesSummary> items = this.<SalesSummary> getTemplate().selectList(
                "SalesRepository.findSummaryByYearMonthRange",
                condition);

        return items;
    }

    protected SqlSessionTemplate getTemplate() {
        return template;
    }
}
