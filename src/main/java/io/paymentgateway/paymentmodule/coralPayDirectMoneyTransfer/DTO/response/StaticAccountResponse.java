package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaticAccountResponse {

    private StaticAccountResponseDetails responseDetails;
    private String accountName;
    private String accountNumber;
    private String operationReference;
    private String nameOfBank;

}
