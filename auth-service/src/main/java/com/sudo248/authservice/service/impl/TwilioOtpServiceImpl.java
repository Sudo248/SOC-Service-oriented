package com.sudo248.authservice.service.impl;

import com.sudo248.authservice.contronller.dto.TokenDto;
import com.sudo248.authservice.contronller.dto.VerifyDto;
import com.sudo248.authservice.exception.PhoneNumberInvalidException;
import com.sudo248.authservice.external.CommonService;
import com.sudo248.authservice.repository.AccountRepository;
import com.sudo248.authservice.service.OtpService;
import com.sudo248.authservice.service.model.AccountModel;
import com.sudo248.domain.base.BaseResponse;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Qualifier("TwilioOtpService")
@Slf4j
public class TwilioOtpServiceImpl implements OtpService {
//    @Value("twilio.account-sid")
    private String twilioAccountSid = "ACed4c91e6e02bca812042c5c7404cd3f2";

//    @Value("twilio.auth-token")
    private String twilioAuthToken = "7accf5a756e1a97497c0b91f6cddd242";

//    @Value("twilio.verify-SOC")
    private String verifySoc = "VAb9142a28d258999a4a19f665b1d950d2";

    private final CommonService commonService;
    private final AccountRepository accountRepository;

    private final ModelMapper mapper;

    public TwilioOtpServiceImpl(CommonService commonService, AccountRepository accountRepository, ModelMapper mapper) {
        this.commonService = commonService;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> generateOtp(String phoneNumber) {
        return handleException(() -> {
            Twilio.init(twilioAccountSid, twilioAuthToken);
            Verification verification = Verification.creator(
                    verifySoc,
                    formatPhoneNumber(phoneNumber),
                    "sms"
            ).create();
            String status = verification.getStatus();
            log.info("Twilio generate status: " + status);
            return BaseResponse.ok("OTP has been generated successfully");
        });
    }

    @Override
    public ResponseEntity<BaseResponse<?>> verifyOtp(VerifyDto verifyDto) {
        return handleException(() -> {
            Twilio.init(twilioAccountSid, twilioAuthToken);
            String status = "";
            try {
                VerificationCheck verificationCheck = VerificationCheck.creator(verifySoc, verifyDto.getOtp())
                        .setTo(verifyDto.getPhoneNumber())
                        .create();
                status = verificationCheck.getStatus();
                log.info("Twilio verify status: " + status);
            } catch (Exception e) {
                log.error(e.toString());
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Verification failed with status: " + status);
            }
            AccountModel accountModel = mapper.map(accountRepository.getUserByPhoneNumber(verifyDto.getPhoneNumber()), AccountModel.class);
            TokenDto token = new TokenDto(commonService.generateToken(accountModel.getUserId()));
            return BaseResponse.ok(token);
        });
    }

    private String formatPhoneNumber(String phoneNumber) throws PhoneNumberInvalidException {
        if (phoneNumber.startsWith("0")) {
            return "+84"+phoneNumber.substring(1);
        } else if (phoneNumber.startsWith("+84")) {
            return phoneNumber;
        }
        throw new PhoneNumberInvalidException();
    }
}
