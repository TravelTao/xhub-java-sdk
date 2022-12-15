package com.tratao.payout;

import com.tratao.payout.models.CreateTransferResponseData;
import com.tratao.payout.models.RequestResponse;
import com.tratao.xcore.Config;

public class Client {
    private Config config;

    public Client(Config config) {
        this.config = config;
    }

    public RequestResponse<CreateTransferResponseData> createTransfer() {


        return new RequestResponse<>();
    }

}
