package io.paymentgateway.paymentmodule.NinePSBWAAS.service;

import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request.*;
import io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response.*;
import io.paymentgateway.paymentmodule.exceptions.PaymentServiceException;

public interface NinePSBWalletService {

    NinePSBAuthenticateResponse authenticate(NinePSBAuthenticateRequest authRequest) throws PaymentServiceException;


    NinePSBOpenWalletResponse openWallet(NinePSBOpenWalletRequest openWalletRequest) throws PaymentServiceException;

    NinePSBWalletEnquiryResponse walletEnquiry(NinePSBWalletEnquiryRequest enquiryRequest) throws PaymentServiceException;

    NinePSBSingleWalletResponse debit_transfer(NinePSBSingleWalletRequest walletRequest) throws PaymentServiceException;

    NinePSBSingleWalletResponse credit_transfer(NinePSBSingleWalletRequest singlecreditRequest) throws PaymentServiceException;

    NinePSBUpgradeStatusResponse  upgrade_status(NinePSBUpgradeStatusRequest upgradeRequest) throws PaymentServiceException;

}
