package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.interfaces;

import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.DynamicAccountRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.StaticAccountRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.TransactionPaymentNotificationRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.TransactionQueryRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.DynamicAccountResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.StaticAccountResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.TransactionPaymentNotificationResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.TransactionQueryResponse;

import java.io.IOException;

public interface PaymentService {

   boolean checkServiceUp(String url) throws IOException;

   DynamicAccountResponse dynamicAccount(DynamicAccountRequest accountRequest);

   StaticAccountResponse staticAccount(StaticAccountRequest staticAccountRequest);

   TransactionQueryResponse getTransactionDetails(TransactionQueryRequest queryRequest);

   TransactionPaymentNotificationResponse testPartnerRequest(TransactionPaymentNotificationRequest notificationRequest);

}
