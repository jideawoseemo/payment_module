package io.paymentgateway.paymentmodule.CorallPayUSSDConnect.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoralPayCConnectAuthenticationReqest {

    private String Username;
    private String Password;

}
