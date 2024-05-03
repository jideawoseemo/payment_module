package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicAccountResponseDetails {

    private String clientID;
    private String referenceNumber;
    private String responseCode;
    private String responseMessage;


}
