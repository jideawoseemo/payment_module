package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class FetchAccountTransaction {

    private String sessionId;
    private String accountNumber;
    private String tranRemarks;
    private String transactionAmount;
    private String settledAmount;
    private String feeAmount;
    private String vatAmount;
    private String currency;
    private String initiationTranRef;
    private String settlementId;
    private String sourceAccountNumber;
    private String sourceAccountName;
    private String sourceBankName;
    private String channelId;
    private String tranDateTime;
    private String retryCount;
    private String notificationStatus;
    private String tranDate;
    private String transactionDate;

}
