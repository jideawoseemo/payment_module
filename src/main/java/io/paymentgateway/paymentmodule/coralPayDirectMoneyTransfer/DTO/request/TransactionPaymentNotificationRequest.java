package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPaymentNotificationRequest {

        private String account_number;
        private String source_account_number;
        private String source_account_name;
        private String transaction_amount;
        private String source_bank_code;
        private String source_bank_name;
        private String tran_date_time;
        private Integer service_charge;
        private String referenceNumber;
        private String account_name;
        private String currency;
        private String operationReference;
        private String module_value;

}
