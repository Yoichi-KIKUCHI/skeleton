package com.yo1000.skeleton.controller.api.v1;

import com.yo1000.skeleton.domain.model.SalesSummary;
import com.yo1000.skeleton.domain.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
@RestController
@RequestMapping("api/v1/store/{storeId}/sales")
public class SalesResource {
    @Autowired
    private SalesService salesService;

    @RequestMapping(
            value = "summary/{from}/{to}",
            method = RequestMethod.GET,
//            consumes = "application/json", // TODO ブラウザでリクエスト確認しづらいため、一時的にコメントアウト
            produces = "application/json")
    public List<SalesSummary> getSummary(@PathVariable String storeId,
                                         @PathVariable String from,
                                         @PathVariable String to,
                                         @RequestParam(required = false) String customerId) {

        return this.getSalesService().getSummaryByYearMonth(storeId, from, to, customerId);
    }

    protected SalesService getSalesService() {
        return salesService;
    }
}