package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchAccountTransactionRequest {

    private String accountNumber;
    private String fromDate;
    private String toDate;
    private String authToken;

}
