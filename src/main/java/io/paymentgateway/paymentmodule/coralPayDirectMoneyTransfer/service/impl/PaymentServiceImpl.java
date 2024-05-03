package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.impl;

import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.DynamicAccountRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.StaticAccountRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.TransactionPaymentNotificationRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.TransactionQueryRequest;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.DynamicAccountResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.StaticAccountResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.TransactionPaymentNotificationResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.TransactionQueryResponse;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RestClient restClient;

//    @Value("${CORALPAY.USERNAME}")
    private String username = "b@b.com";

//    @Value("${CORALPAY.PASSWORD}")
    private String password = "3bedf945c074b5d1cbe2b555f0c0cbc2f6c40505dcf2205b9d221edf4c0f98e33316f68f9a0f5227dfc4850aa3bacbf96c1f002f1abf58343ddf98a1852871c3";

    private String accountNumber = "7003268673";

    private String accountName = "Oluwatosin Daniel 2";

    private String transactionAmount = "1000.00";

    private final String referenceNumber = "abc1409432072";

    String credentials = username + ":" + password;
    String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());
    @Override
    public boolean checkServiceUp(String url) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(url);
        return	inetAddress.isReachable(5000);
    }

    @Override
    public DynamicAccountResponse dynamicAccount(DynamicAccountRequest accountRequest) {

        String password = hashValue(username, this.referenceNumber);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        System.out.println("  ======================================  "+username);
        System.out.println("   **********************************************  "+password);

        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/dynamicAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(accountRequest)
                .retrieve()
                .body(DynamicAccountResponse.class);

    }

    @Override
    public StaticAccountResponse staticAccount(StaticAccountRequest staticAccountRequest) {
        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/staticAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(staticAccountRequest)
                .retrieve()
                .body(StaticAccountResponse.class);
    }

    @Override
    public TransactionQueryResponse getTransactionDetails(TransactionQueryRequest queryRequest) {

        String password = hashValue(username, this.referenceNumber);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());
        System.out.println("   **********************************************  "+hashValue(username,this.referenceNumber));
        return restClient.post().
                uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/getTransactionDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Basic "+base64Credentials)
                .body(queryRequest)
                .retrieve()
                .body(TransactionQueryResponse.class);

    }

    @Override
    public TransactionPaymentNotificationResponse testPartnerRequest(TransactionPaymentNotificationRequest notificationRequest) {

        String module_value = hashValueForNotification(accountNumber,accountName,transactionAmount);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        System.out.println("  ======================================  "+username);
        System.out.println("   **********************************************  "+password);
        System.out.println("   **********************************************  "+module_value);

        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/testPartnerRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(notificationRequest)
                .retrieve()
                .body(TransactionPaymentNotificationResponse.class);

    }

    private String hashValue(String username, String referenceNumber) {
        String password1 = referenceNumber+":"+username;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password1.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash value not accepted");
        }
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte

    }

    private String hashValueForNotification(String accountNumber, String accountName,String transactionAmount) {
        String password1 = accountNumber+""+accountName+""+transactionAmount;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password1.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash value not accepted");
        }
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte

    }

}
