package io.paymentgateway.paymentmodule;

import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.*;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.DynamicAccountResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.StaticAccountResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.TransactionPaymentNotificationResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.TransactionQueryResponse;
import io.paymentgateway.paymentmodule.exceptions.PaymentServiceException;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.interfaces.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
public class CoralPayDirectMoneyTransferTest {

    @Autowired
    private PaymentService paymentService;

    @Value("${CORALPAY.USERNAME}")
    private String username;

    TransactionQueryRequest queryRequest;
    @Value("${CORALPAY.PASSWORD}")
    private String password;
    @BeforeEach
    void SetUp() {

    }

    @Test
    void checkIfCoralPayExists() throws IOException {
        String url = "www.youtube.com";

    assertTrue(paymentService.checkServiceUp(url));

    }

    @Test
    void checkIfCoralPayDynamicServiceIsNotNull() {
        log.info(password+"      <<<<<<<<<<<<<<<<<<<<<<<<");
        log.info(username+"       ...............................................");

        String credentials = username + ":" + password;
        DynamicAccountRequest req = new DynamicAccountRequest();
        DynamicAccountRequestHeader requestHeader = new DynamicAccountRequestHeader();

        requestHeader.setClientId("b@b.com");
        requestHeader.setRequestType("Bank Transfer");

        req.setCustomerName("Johnson AY");
        req.setRequestHeader(requestHeader);
        req.setReferenceNumber("abc1409432072");
        req.setTransactionAmount("15000");
        log.info(credentials);
        log.info(username);
        log.info(password);
        DynamicAccountResponse response = paymentService.dynamicAccount(req);

        log.info(response.getResponseDetails().getClientID());
        log.info(response.toString());

        assertNotNull(response);
       assertEquals("Johnson AY",response.getAccountName());

    }

    @Test
    void checkIfCoralPayTransactionQueryIsNotNull() {

    TransactionQueryRequestHeader requestHeader = new TransactionQueryRequestHeader();

    requestHeader.setClientId("b@b.com");
    requestHeader.setRequestType("Bank Transfer");

        TransactionQueryRequest queryRequest = new TransactionQueryRequest();
    queryRequest.setRequestHeader(requestHeader);
    queryRequest.setReferenceNumber("abc1409432072");

        System.out.println("requestHeader---------------------->>" + requestHeader);

        TransactionQueryResponse response = paymentService.getTransactionDetails(queryRequest);
        log.info(response.toString());

        assertNotNull(response);
        //assertEquals("Success",response.getResponseDetails().);

    }

    @Test
    void checkIfCoralPayStaticServiceIsNotNull() {
        log.info(password+"      <<<<<<<<<<<<<<<<<<<<<<<<");
        log.info(username+"       ...............................................");

        String credentials = username + ":" + password;
        StaticAccountRequest req = new StaticAccountRequest();
        StaticAccountRequestHeader requestHeader = new StaticAccountRequestHeader();

        requestHeader.setClientId("b@b.com");
        requestHeader.setRequestType("Bank Transfer");

        req.setCustomerName("Bianca.co");
        req.setRequestHeader(requestHeader);
        req.setReferenceNumber("709032DCHJKWQ025");
        req.setCustomerID("Microwave");
        log.info(credentials);
        log.info(username);
        log.info(password);
        try {
            StaticAccountResponse response = paymentService.staticAccount(req);
            assertNotNull(response);
            assertEquals("Bianca.co",response.getAccountName());
        } catch (Exception e){
            e.printStackTrace();
            log.info(e.getLocalizedMessage());
        }

    }

    @Test
    void checkIfPaymentNotificationIsNotNull() {

        TransactionPaymentNotificationRequest paymentNotificationRequest = new TransactionPaymentNotificationRequest();

        paymentNotificationRequest.setCurrency("NGN");
        paymentNotificationRequest.setAccount_name("Oluwatosin Daniel 2");
        paymentNotificationRequest.setModule_value("3161b3628d23605f50f1db10a5001b3f68d9eecb35c3fa6d5fdaa97c8b08a1954b27b40553f4c9f3ae4f18d4e6accfe4f46201d2124152b79a3eced04149efdc");
        paymentNotificationRequest.setReferenceNumber("TED245091");
        paymentNotificationRequest.setOperationReference("8fb-8b5e-41d8-bb54-a6bff38ba82f");
        paymentNotificationRequest.setService_charge(50);
        paymentNotificationRequest.setSource_account_name("Abidemi Asunmo");
        paymentNotificationRequest.setTran_date_time("10-05-2023 12:09:50");
        paymentNotificationRequest.setSource_bank_name("N/A");
        paymentNotificationRequest.setSource_bank_code("N/A");
        paymentNotificationRequest.setTransaction_amount("1000.00");
        paymentNotificationRequest.setAccount_number("7003268673");
        paymentNotificationRequest.setSource_account_number("3333002345");

        TransactionPaymentNotificationResponse paymentNotificationResponse = paymentService.testPartnerRequest(paymentNotificationRequest);

        assertNotNull(paymentNotificationResponse);
        assertEquals("00",paymentNotificationResponse.getResponseCode());

    }


}
