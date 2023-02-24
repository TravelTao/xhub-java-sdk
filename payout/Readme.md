# xCurrency Hubs China Payout Java SDK
This SDK focus on the [API](https://docs.xcurrency.com/payout/en) integration of China Payout running on our xCurrency Hub platform. Based on our strong CNY distribution network, we can help you make an easier cross-border payout to China, whenever you are launching new products, expanding into China, or adding remittance modular into your existing operations.

You can apply our cutting-edge APIs for an enhanced customer experience, our China Payout solution supports [UnionPay Debit Cards] issued by more than 500 mainstream banks in China, whether it is China's four major state-owned banks, city commercial banks, and rural credit cooperatives in various places.

China Payout has two transaction Modes, the `targetCurrency` should be `CNY` for all of these modes.
- **Cross-Currency Transaction Mode** (example: USD/CNY)
  - **FX Trade** Availability Time: working day: 9:30--15:30 UTC+08:00 (Please contact your account manager if you have any questions)
  - **Transfer** Availability Time: working day: 9:30--15:30 UTC+08:00
- **Same Currency Transaction Mode**, the `sourceCurrency` should be `CNY` (example: CNY/CNY)
  - Transfer by realtime exchange rate when confirm to payee.
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
  impletement 'com.tratao:payout:0.0.1'
  
  // dependence the base lib
  implementation 'com.tratao:xcore:1.0.3'
  ```

## Usage
The following code example shows the steps to use xCurrency Hubs China Payout SDK for Java :

*Note: the `tradeId` should be store on your side system.*

1. load properties, or can get from system environment
2. set properties
3. init Client and set API Host (testing environment and production environment)
4. **Same Currency Transaction Mode** (example: CNY/CNY)
   1. create payment, this will get a `tradeId`, if not get `tradeId` mean that not create payment on xCurrency Hub platform, please check the message which from response and retry later.
   2. async confirm payment, when use async confirm method, xCurrency Hub will make a [callback notify](https://docs.xcurrency.com/payout/en#section/Developer-Guide/Notification) to your side.
5. **Cross-Currency Transaction Mode** (example: USD/CNY)
   1. get rate, get the quote price from xCurrency Hub.
   2. create payment, this will get a `tradeId`, if not get `tradeId` mean that not create payment on xCurrency Hub platform, please check the message which from response and retry later.
   3. async confirm payment, when use async confirm method, xCurrency Hub will make a [callback notify](https://docs.xcurrency.com/payout/en#section/Developer-Guide/Notification) to your side. 

    ```java
    import com.tratao.payout.Client;
    import com.tratao.payout.emuns.*;
    import com.tratao.payout.models.*;
    import com.tratao.xcore.Config;
    import com.tratao.xcore.utils.TLog;
    
    public class Main {
    
        /**
         * create payment
         * @param client
         * 
         * @return PaymentStatus
         */
        public static PaymentStatus createTransfer(Client client, boolean isSame) {
            CreateTransferRequest request = new CreateTransferRequest();
            request.setFundsSource(FundsSource.EMPLOYMENT);
            request.setRelationship(Relationship.SIBLING);
            request.setPurpose(FundsPurpose.FAMILY_SUPPORT);
            if (isSame) {
                // 4. Same Currency Transaction Mode
                request.setSourceCurrency("CNY");
                request.setTargetCurrency("CNY");
                request.setSourceAmount(50);
                request.setTargetAmount(50);
            } else {
                // 5. Cross-Currency Transaction Mode
                request.setSourceCurrency("USD");
                request.setTargetCurrency("CNY");
                request.setSourceAmount(50);
                // 5.1 get quote rate.
                GetRateRequest request = new GetRateRequest("USD", "CNY");
                RateResponseData responseData = client.getRate(request);
                if (responseData != null) {
                    request.setTargetAmount(50 * responseData.getRate());
                } else {
                    throw new RuntimeException("Not Rate support, please contact xCurrency Hub for help.");
                }
            }
            
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
            beneficiary.setName("测试");
            beneficiary.setCardNo("62356635666655665");
            beneficiary.setIdNumber("440902196503032365");
            beneficiary.setGender(Gender.MALE);
            beneficiary.setIdCategory(IDCategory.IDCARD);
            beneficiary.setIdIssueDate("2015-02-03");
            beneficiary.setIdExpiryDate("2035-02-03");
            beneficiary.setIddCode("86");
            beneficiary.setPhone("189111111111");
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
            // appkey=
            // secretKey=
            // privateKey=
            InputStream in = ClassLoader.getSystemResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(in);
            // enable log, production can remove this or set false.
            TLog.getInstance().setDebug(true);
            
            // 2. set properties for config
            Config config = new Config(properties.getProperty("appKey"), properties.getProperty("secretKey"), properties.getProperty("privateKey"));
            
            // 3. init Client and set API Host
            Client client = new Client(config);
            // sandbox host
            client.setHost("https://api-sandbox.xcurrency.com");
            
            // 4. Same Currency Transaction Mode
            // 4.1 create payment to xCurrency hub
            PaymentStatus paymentStatus = createTransfer(client, true);
            
            // 4.2 async confirm transfer to payee bank card. this will be made a callback to your side.
            if (paymentStatus.canConfirmTransfer()) {
                boolean result = confirmAsync(paymentStatus.getTradeId());
            }
    
            // 5. Cross-Currency Transaction Mode
            // 5.2 create payment to xCurrency hub
            // PaymentStatus paymentStatus = createTransfer(client, false);
    
            // 5.3 async confirm transfer to payee bank card. this will be made a callback to your side.
            // if (paymentStatus.canConfirmTransfer()) {
            //     boolean result = confirmAsync(paymentStatus.getTradeId());
            // }
        }
    }
    ```
6. get payment status, when create payment success, can get payment status by `tradeId`.
    ```java
    PaymentStatus status = client.getPaymentStatus(tradeId);
    ```
7. update payment, if want to change payment some info, this can use to update payee's info, or you can create a new payment instead.
   ```java
    boolean result = client.cancelTransfer(tradeId);
    ```
8. cancel payment, if do not transfer to payee yet, can cancel the payment.
    ```java
    UpdateTransferRequest request = new UpdateTransferRequest();
    // set the update payment data 
    // request.setXXX
    boolean result = client.updatePaymentInfo(request);
    ```
9. get PBCAreaList, the province, city, district/town data of china, which are used for payee address.
    ```java
    List<PBCAreaResponseData> data = client.getPBCAreaListData();
    ```
10. get OccupationList, for the sender's and payee's occupation.
    ```java
    List<OccupationResponseData> data = client.getOccupationData();
    ```
11. get simple balance
    ```java
    BalanceRequest request = new BalanceRequest("USD");
    BalanceResponseData data = client.getCurrencyBalance(request);
    ```
12. get all balance
    ```java
    List<BalanceResponseData> list = client.getAllBalance();
    ```
13. handle on notify callback by xCurrency Hub's side which is on create payment or use async confirm method.
    ```java
    import com.tratao.payout.models.NotifyData;
    import com.tratao.payout.models.NotifyResponseData;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    @RestController
    @RequestMapping(value = "/xcurrency")
    public class NotifyController {
    
        @PostMapping(value = "/webhook")
        public NotifyResponseData callback(@RequestBody NotifyData data) {
            if (data == null || data.getEvent() == null) {
                return new NotifyResponseData("400", "Bad request, no body data.");
            }
    
            switch (data.getEvent()) {
                case "payment":
                    // check the payment status
                    // callback data: { "event": "payment", "id": "xxxxxxx", "status": "", "message": ""}
                    switch (data.getStatus()) {
                        case "pending":
                            // no action, wait for xCurrency Hub's compliance team to review the sender and payee data, will be done at less 2 hours.
                            break;
                        case "awaiting_transfer":
                            // TODO:: use confirm API or async confirm API.
    
                            break;
                        case "transferring":
                            // no action
                            break;
                        case "completed":
                            // TODO:: success transfer to payee and complete the payment.
    
                            break;
                        case "failed":
                            // TODO:: transfer fail, can get the error message from data.getMessage().
                            // if not understand the error message can contact xCurrency Hub for help.
    
                            break;
                        case "rejected":
                            // TODO:: the sender or payee not pass review by xCurrency Hub compliance team.
                            // the reject reason can get from the data.getMessage()
                            // if not understand the error message can contact xCurrency Hub for help.
    
                            break;
                        case "canceled":
                            // cancel the payment
    
                            break;
                    }
    
                    break;
                case "balance":
                    // callback data: { "event": "balance", "id": "xxxxxxx", "amount": }
                    // xCurrency Hub online platform also has balance, so this useless.
                    // TODO:: when get balance notify, check the amount
                                    
                    break;
                default:
                    return new NotifyResponseData("400", "Not Found Event.");
            }
    
            return new NotifyResponseData("200", "");
        }
    
    }
    ```

## Changelog

### version 0.0.1 
- init project
- support payout api 

## License

[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Copyright (c) 2022-present, xCurrency Hub All rights reserved.