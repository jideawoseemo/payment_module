package io.paymentgateway.paymentmodule.CorallPayUSSDConnect.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoralPayCConnectAuthenticationResponse {

    private String Token;
    private String Key;
    private String Status;

}
