package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaticAccountRequest {

    private StaticAccountRequestHeader requestHeader;
    private String customerName;
    private String referenceNumber;
    private String customerID;

}
