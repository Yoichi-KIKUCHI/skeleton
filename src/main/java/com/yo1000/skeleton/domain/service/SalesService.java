package com.yo1000.skeleton.domain.service;

import com.yo1000.skeleton.domain.model.SalesSummary;
import com.yo1000.skeleton.domain.model.SearchCondition;
import com.yo1000.skeleton.domain.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
@Service
public class SalesService {
    private static final DateFormat YEARMONTH_FORMAT =
            new SimpleDateFormat("yyyyMM");

    @Autowired
    private SalesRepository salesRepository;

    public List<SalesSummary> getSummaryByYearMonth(String storeId,
                                                    String from,
                                                    String to,
                                                    String customerId) {
        Date fromDate = null;
        Date toDate = null;

        try {
            fromDate = YEARMONTH_FORMAT.parse(from);
            toDate = YEARMONTH_FORMAT.parse(to);
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        toDate = calendar.getTime();

        SearchCondition condition = new SearchCondition();
        condition.setStoreId(storeId);
        condition.setFrom(fromDate);
        condition.setTo(toDate);
        condition.setCustomerId(customerId);

        if (fromDate.after(toDate)) {
            throw new IllegalArgumentException("'from' is greater than 'to'.");
        }

        return this.getSalesRepository().findSummaryByYearMonth(condition);
    }

    protected SalesRepository getSalesRepository() {
        return salesRepository;
    }
}
