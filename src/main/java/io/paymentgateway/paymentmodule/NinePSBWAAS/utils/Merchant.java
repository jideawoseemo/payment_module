package io.paymentgateway.paymentmodule.NinePSBWAAS.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    private String merchantFeeAmount;
    private String merchantFeeAccount;
    private boolean isFee;

}
