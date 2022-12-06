package com.tratao.xcore;

import com.tratao.xcore.request.RequestMethod;
import com.tratao.xcore.utils.TLog;
import org.junit.jupiter.api.Test;

public class BaseTests {

    @Test
    public void testRequest() throws Exception {
        System.out.println(BaseClient.getInstance().makeRequest("https://baidu.com", RequestMethod.GET));
    }

}
