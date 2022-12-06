package com.tratao.xcore;

import com.tratao.xcore.request.RequestMethod;
import com.tratao.xcore.utils.TLog;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseTests {

    @Test
    public void testRequest() throws Exception {
        System.out.println(BaseClient.getInstance().makeRequest("https://baidu.com", RequestMethod.GET));
    }

    @Test
    public void testMultiple() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            service.submit(() -> {
                try {
                    System.out.println("Timestamp " + new Date().getTime() + ": " + BaseClient.getInstance().makeRequest("https://baidu.com", RequestMethod.GET));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }

        Thread.sleep(10 * 1000);
    }

}
