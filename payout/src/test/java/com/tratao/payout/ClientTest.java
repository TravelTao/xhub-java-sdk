package com.tratao.payout;

import com.tratao.payout.emuns.*;
import com.tratao.payout.models.*;
import com.tratao.xcore.Config;
import com.tratao.xcore.utils.TLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        TLog.getInstance().setDebug(true);

        Config config = new Config(properties.getProperty("appKey"), properties.getProperty("secretKey"), properties.getProperty("privateKey"));
        client = new Client(config);
        client.setHost("https://api-sandbox.xcurrency.com");
    }

    @Test
    public void rate() throws Exception {
        GetRateRequest request = new GetRateRequest();
        request.setSourceCurrency("USD");
        request.setTargetCurrency("CNY");
        RateResponseData response = client.getRate(request);

        Assertions.assertTrue(response.getRate() > 0);
    }

    @Test
    public void pBCAreaList() {
        List<PBCAreaResponseData> data = client.getPBCAreaListData();

        Assertions.assertTrue(data.size() > 0);
    }

    @Test
    public void occupation() {
        List<OccupationResponseData> data = client.getOccupationData();

        Assertions.assertTrue(data.size() > 0);
    }

    @Test
    public void createTransfer() {
        CreateTransferRequest request = new CreateTransferRequest();
        request.setFundsSource(FundsSource.EMPLOYMENT);
        request.setRelationship(Relationship.SIBLING);
        request.setPurpose(FundsPurpose.FAMILY_SUPPORT);
        request.setSourceCurrency("USD");
        request.setTargetCurrency("CNY");
        request.setSourceAmount(50);
        if (request.getSourceCurrency().equals("CNY")) {
            request.setTargetAmount(request.getSourceAmount());
        } else {
            GetRateRequest rateRequest = new GetRateRequest(request.getSourceCurrency(), request.getTargetCurrency());
            RateResponseData responseData = client.getRate(rateRequest);
            if (responseData != null) {
                request.setTargetAmount(request.getSourceAmount() * responseData.getRate());
            } else {
                throw new RuntimeException("Not Rate support, please contact xCurrency Hub for help.");
            }
        }

        // generate a order no
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String orderNo = formatDate.format(date);
        request.setOrderNo(orderNo);

        Payer payer = new Payer();
        payer.setFirstName("test");
        payer.setLastName("test");
        payer.setIdCategory(IDCategory.PASSPORT);
        payer.setGender(Gender.MALE);
        payer.setIdCountry("SG");
        payer.setIdNumber("T456666325");
        payer.setNationality("SG");
        payer.setOccupation("Professor");
        payer.setPhone("0236658996");
        payer.setIddCode("61");
        payer.setBirthday("1966-03-03");
        payer.setAccountNumber("1258566545");
        Address address = new Address();
        address.setAddress1("Blk 505 Jurong West St 51 #01-186");
        address.setCity("SGP");
        address.setDistrict("");
        address.setCountry("SG");
        address.setPostCode("426536");
        payer.setAddress(address);
        request.setPayer(payer);

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBirthday("1986-03-03");
        beneficiary.setName("测试账户");
        beneficiary.setCardNo("6210281010000460");
        beneficiary.setIdNumber("330725198009210417");
        beneficiary.setGender(Gender.MALE);
        beneficiary.setIdCategory(IDCategory.IDCARD);
        beneficiary.setIdIssueDate("2015-02-03");
        beneficiary.setIdExpiryDate("2035-02-03");
        beneficiary.setIddCode("86");
        beneficiary.setPhone("133333333333");
        Address beneAddress = new Address();
        beneAddress.setAddress1("测试路测试楼 3 号 0101 房");
        beneAddress.setCity("珠海");
        beneAddress.setDistrict("香洲区");
        beneAddress.setState("广东省");
        beneficiary.setAddress(beneAddress);
        beneficiary.setOccupation("Professor");
        request.setBeneficiary(beneficiary);

        PaymentStatus paymentStatus = client.createTransfer(request);

        System.out.println(paymentStatus);
        Assertions.assertTrue(paymentStatus.isSuccess());
    }

    @Test
    public void transferAsync() {

        boolean success = client.asyncConfirmTransfer("xxxxxxxxxxxxxxxxxxxxxxxx");

        Assertions.assertTrue(success);
    }

    @Test
    public void transfer() {
        PaymentStatus status = client.confirmTransfer("xxxxxxxxxxxxxxxxxxxxxxxx");

        Assertions.assertTrue(status.isCompleted());
    }

    @Test
    public void status() {
        PaymentStatus status = client.getPaymentStatus("xxxxxxxxxxxxxxxxxxxxxxxx");

        System.out.println(status);
        Assertions.assertTrue(status.getStatus() != null);
    }
}
