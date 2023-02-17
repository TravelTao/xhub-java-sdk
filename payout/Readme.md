# xCurrency Hubs China Payout Project
This SDK focus on the [API](https://docs.xcurrency.com/payout/en) integration of China Payout running on our xCurrency Hub platform. Based on our strong CNY distribution network, we can help you make an easier cross-border payout to China, whenever you are launching new products, expanding into China, or adding remittance modular into your existing operations.

You can apply our cutting-edge APIs for an enhanced customer experience, our China Payout solution supports [UnionPay Debit Cards] issued by more than 500 mainstream banks in China, whether it is China's four major state-owned banks, city commercial banks, and rural credit cooperatives in various places.

China Payout has two transaction Modes, the `targetCurrency` should be `CNY`
- **Cross-Currency Transaction Mode** (example: USD/CNY)
  - **FX Trade** Availability Time: working day: 9:30--15:30 UTC+08:00 (Please contact your account manager if you have any questions)
  - **Transfer** Availability Time: working day: 9:30--15:30 UTC+08:00
- **Same Currency Transaction Mode**, the `sourceCurrency` should be `CNY` (example: CNY/CNY)
  - **FX Trade** Availability Time: working day: 10:00--14:30 UTC+08:00 (Please contact your account manager if you have any questions)
  - **Transfer** Availability Time: 7x24hr

## Installation
- maven
    ```xml
    <dependency>
       <groupId>com.tratao</groupId>
       <artifactId>payout</artifactId>
       <version>Use the version shown in the maven badge</version>
    </dependency>
    ```
- gradle
  ```groovy
  impletement 'com.tratao:payout:$VERSION'
  ```

## Usage
The following code example shows the steps to use xCurrency Hubs China Payout SDK for Java :
1. load properties, or can get from system environment
2. set properties
3. init Client and set API Host (testing environment and production environment)
4. create payment, this will get a `tradeId`, if not get `tradeId` mean that not create payment on xCurrency Hub platform, please check the message from response and retry later.
5. confirm payment, when use async confirm method, xCurrency Hub will make a [callback notify](https://docs.xcurrency.com/payout/en#section/Developer-Guide/Notification) to your side.
6. update payment, this can use to update payee's info, or you can create a new payment instead.
7. get rate, get the quote price from xCurrency Hub.

```java
import com.tratao.payout.Client;
import com.tratao.payout.emuns.*;
import com.tratao.payout.models.*;
import com.tratao.xcore.Config;

public class Main {

    /**
     * create the Same Currency Transaction Mode payment
     * @param client
     * 
     * @return PaymentStatus
     */
    public static PaymentStatus createTransfer(Client client) {
        CreateTransferRequest request = new CreateTransferRequest();
        request.setFundsSource(FundsSource.EMPLOYMENT);
        request.setRelationship(Relationship.SIBLING);
        request.setPurpose(FundsPurpose.FAMILY_SUPPORT);
        // Same Currency Transaction Mode
        request.setSourceCurrency("CNY");
        request.setTargetCurrency("CNY");
        request.setSourceAmount(50);
        request.setTargetAmount(50);
        request.setOrderNo("test_000211110001");
    
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
        request.setPayer(payer);
    
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBirthday("1986-03-03");
        beneficiary.setName("测试");
        beneficiary.setCardNo("62356635666655665");
        beneficiary.setIdNumber("440902196503032365");
        beneficiary.setGender(Gender.MALE);
        beneficiary.setIdCategory(IDCategory.IDCARD);
        beneficiary.setIdIssueDate("2015-02-03");
        beneficiary.setIdExpiryDate("2035-02-03");
        beneficiary.setIddCode("86");
        beneficiary.setPhone("189111111111");
        Address address = new Address();
        address.setAddress1("测试路测试楼 3 号 0101 房");
        address.setCity("珠海");
        address.setDistrict("香洲区");
        address.setState("广东省");
        beneficiary.setAddress(address);
        beneficiary.setOccupation("Professor");
        request.setBeneficiary(beneficiary);
    
        PaymentStatus paymentStatus = client.createTransfer(request);
    
        System.out.println(paymentStatus);
        
        return paymentStatus;
    }

    /**
     * Async confirm transfer to payee.
     * @param tradeId 
     * @return true or false
     */
    public static boolean confirmAsync(String tradeId) {
        return client.asyncConfirmTransfer(tradeId);
    }

    public static void main(String[] args) {
        // 1. load properties from resource
        InputStream in = ClassLoader.getSystemResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(in);
        
        // 2. set properties for config
        Config config = new Config(properties.getProperty("appKey"), properties.getProperty("secretKey"), properties.getProperty("privateKey"));
        
        // 3. init Client and set API Host
        Client client = new Client(config);
        client.setHost("https://api-sandbox.xcurrency.com");
        
        // 4. create payment to xCurrency hub
        PaymentStatus paymentStatus = createTransfer(client);
        
        // 5. confirm transfer to payee bank card or wallet. 
        if (paymentStatus.canConfirmTransfer()) {
            boolean result = confirmAsync(paymentStatus.getTradeId());
        }
    }
}

```

## Changelog

Detailed changes for each release are documented in the release notes.

## License

[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Copyright (c) 2022-present, xCurrency Hub All rights reserved.