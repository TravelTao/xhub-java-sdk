package com.tratao.payout;

import com.tratao.payout.emuns.FundsSource;
import com.tratao.payout.emuns.Relationship;
import com.tratao.payout.models.*;
import com.tratao.xcore.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() throws IOException {
        // change to your config
        // test resource add config.properties
        // appKey
        // secretKey
        // privateKey
        InputStream in = ClassLoader.getSystemResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(in);

        Config config = new Config(properties.getProperty("appKey"), properties.getProperty("secretKey"), properties.getProperty("privateKey"));
        client = new Client(config);
        client.setHost("https://api-sandbox.xcurrency.com");
    }

    @Test
    public void testRate() throws Exception {
        GetRateRequest request = new GetRateRequest();
        request.setSourceCurrency("USD");
        request.setTargetCurrency("CNY");
        RateResponseData response = client.getRate(request);

        Assertions.assertTrue(response.getRate() > 0);
    }

    @Test
    public void testPBCAreaList() {
        List<PBCAreaResponseData> data = client.getPBCAreaListData();

        Assertions.assertTrue(data.size() > 0);
    }

    @Test
    public void testOccupation() {
        List<OccupationResponseData> data = client.getOccupationData();

        Assertions.assertTrue(data.size() > 0);
    }

    @Test
    public void testCreateTransferSuccess() {
        CreateTransferRequest request = new CreateTransferRequest();
        request.setFundsSource(FundsSource.EMPLOYMENT);
        request.setRelationship(Relationship.SIBLING);
        request.setSourceCurrency("CNY");
        request.setTargetCurrency("CNY");
        request.setSourceAmount(50);
        request.setTargetAmount(50);
        request.setOrderNo("test_000211110001");
        request.setPurpose("Salary");

        PaymentStatus paymentStatus = client.createTransfer(request);
    }
}
