package com.yo1000.skeleton.controller.api.v1;

import com.yo1000.skeleton.ApplicationContext;
import com.yo1000.skeleton.TestContext;
import com.yo1000.skeleton.domain.model.SalesSummary;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 2015/07/06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationContext.class, TestContext.class})
@WebIntegrationTest({"server.port=55555"})
public class SalesResourceTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getSummary() throws Exception {
        SalesSummary[] items = this.getRestTemplate().getForObject(
                "http://localhost:55555/api/v1/store/{storeId}/sales/summary/{dateFrom}/{dateTo}",
                SalesSummary[].class, new HashMap<String, String>() {
                    {
                        this.put("storeId", "ST2");
                        this.put("dateFrom", "201412");
                        this.put("dateTo", "201506");
                    }
                }
        );

        Assert.assertThat(items, CoreMatchers.any(SalesSummary[].class));
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
