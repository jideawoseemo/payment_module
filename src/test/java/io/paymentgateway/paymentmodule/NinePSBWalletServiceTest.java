package io.paymentgateway.paymentmodule;

import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request.NinePSBAuthenticateRequest;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request.NinePSBSingleWalletRequest;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request.NinePSBUpgradeStatusRequest;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request.NinePSBWalletEnquiryRequest;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response.NinePSBAuthenticateResponse;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response.NinePSBSingleWalletResponse;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response.NinePSBUpgradeStatusResponse;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response.NinePSBWalletEnquiryResponse;
import io.paymentgateway.paymentmodule.NinePSBWAAS.service.NinePSBWalletService;
import io.paymentgateway.paymentmodule.NinePSBWAAS.utils.Merchant;
import io.paymentgateway.paymentmodule.NinePSBWAAS.utils.TransactionType;
import io.paymentgateway.paymentmodule.exceptions.PaymentServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class NinePSBWalletServiceTest {

    @Autowired
    private NinePSBWalletService ninePSBWalletService;

    private NinePSBWalletEnquiryRequest enqrequest;

    private NinePSBSingleWalletRequest singleRequest = new NinePSBSingleWalletRequest();


    private NinePSBUpgradeStatusRequest upgradeStatus;


    @BeforeEach
    void SetUp() {

    }

    @Test
    void checkNinePSBAuthenticationWithNullRequest() throws PaymentServiceException {

       // assertEquals(null, ninePSBWalletService.authenticate(null));
        assertThrows(PaymentServiceException.class, () -> ninePSBWalletService.authenticate(null));
    }

    @Test
    void checkNinePSBAuthenticationWithEmptyRequest() throws PaymentServiceException {
        NinePSBAuthenticateRequest request = new NinePSBAuthenticateRequest();

        request.setClientId("waas");
        request.setClientSecret("cRAwnWElcNMUZpALdnlve6PubUkCPOQR");
        request.setUsername("karrabo");
        request.setPassword("ttwckoHfaNXIuEZEQbTx1shOz3lHHswTgoQEakkOAr5Te7L1HB");

        NinePSBAuthenticateResponse resp = ninePSBWalletService.authenticate(request);
        //assertThrows(PaymentServiceException.class, () -> ninePSBWalletService.authenticate(request));
        assertEquals("h", resp.getAccessToken());
    }

//    @Test
//    void checkNinePSBAuthenticationWithRealObject() {
//
//        NinePSBAuthenticateRequest req = new NinePSBAuthenticateRequest();
//
//
//
//    }

    @Test
    void checkOpenWalletWithNullRequest(){
        assertThrows(PaymentServiceException.class, () -> ninePSBWalletService.openWallet(null));
    }

    @Test
    void confirmWalletAccountIsValid() throws PaymentServiceException {
        this.enqrequest = new NinePSBWalletEnquiryRequest("1100014518");
        NinePSBWalletEnquiryResponse resp = ninePSBWalletService.walletEnquiry(enqrequest);
        assertNull(resp);
    }

    @Test
    void confirmSingleDebitWalletIsNotNull() throws PaymentServiceException {
        Merchant merchant = new Merchant("","",false);
        this.singleRequest = new NinePSBSingleWalletRequest();
        singleRequest.setMerchant(merchant);
        singleRequest.setAccountNo("1100025756");
        singleRequest.setNarration("MERCHANT_NAME/CREDIT_WALLET/1100025718/WAAS202511202217112977");
        singleRequest.setTransactionType(TransactionType.DEBIT_WALLET);
        singleRequest.setTransactionId("WAAS202511202217112977");
        singleRequest.setTotalAmount(1000.00);

        NinePSBSingleWalletResponse ninePSBSingleWalletResponse = ninePSBWalletService.debit_transfer(singleRequest);
        assertFalse(singleRequest.getMerchant().isFee());
        assertNotNull(singleRequest);
        assertNotNull(ninePSBSingleWalletResponse.getStatus());
        assertEquals("FAILED",ninePSBSingleWalletResponse.getStatus());
        assertEquals("SUCCESS", ninePSBSingleWalletResponse.getMessage());
    }

    @Test
    void confirmSingleCreditWalletIsNotNull() throws PaymentServiceException {
        Merchant merchant = new Merchant("","",false);
        this.singleRequest = new NinePSBSingleWalletRequest();
        singleRequest.setMerchant(merchant);
        singleRequest.setAccountNo("1100025756");
        singleRequest.setNarration("MERCHANT_NAME/CREDIT_WALLET/1100025718/WAAS202511202217112977");
        singleRequest.setTransactionType(TransactionType.CREDIT_WALLET);
        singleRequest.setTransactionId("WAAS202511202217112977");
        singleRequest.setTotalAmount(1000.00);
        NinePSBSingleWalletResponse ninePSBSingleWalletResponse = ninePSBWalletService.credit_transfer(singleRequest);

        assertFalse(singleRequest.getMerchant().isFee());
        assertNotNull(singleRequest);
        assertNotNull(ninePSBSingleWalletResponse.getStatus());
        assertEquals("FAILED",ninePSBSingleWalletResponse.getStatus());
        assertEquals("SUCCESS", ninePSBSingleWalletResponse.getMessage());
        assertNotNull(singleRequest);
    }

    @Test
    void checkUpgradeStatusIsNotNull() throws PaymentServiceException {

        this.upgradeStatus = new NinePSBUpgradeStatusRequest("1100015300");

        NinePSBUpgradeStatusResponse resp = ninePSBWalletService.upgrade_status(upgradeStatus);

        assertNotNull(resp);
        assertEquals("SUCCESS", resp.getMessage());
        assertEquals("FAILED", resp.getStatus());

    }



}
