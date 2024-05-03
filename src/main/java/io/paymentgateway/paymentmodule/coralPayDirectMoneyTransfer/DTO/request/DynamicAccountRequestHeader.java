package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicAccountRequestHeader {

    private String clientId;
    private String requestType;

}
