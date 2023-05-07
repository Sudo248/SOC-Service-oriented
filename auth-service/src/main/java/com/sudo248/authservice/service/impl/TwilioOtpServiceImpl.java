package com.sudo248.authservice.service.impl;

import com.sudo248.authservice.contronller.dto.*;
import com.sudo248.authservice.exception.PhoneNumberInvalidException;
import com.sudo248.authservice.exception.UserException;
import com.sudo248.authservice.internal.UserService;
import com.sudo248.authservice.repository.AccountRepository;
import com.sudo248.authservice.repository.entity.Gender;
import com.sudo248.authservice.service.OtpService;
import com.sudo248.authservice.service.model.AccountModel;
import com.sudo248.authservice.utils.TokenUtils;
import com.sudo248.domain.base.BaseResponse;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Qualifier("TwilioOtpService")
@Slf4j
public class TwilioOtpServiceImpl implements OtpService {

    @Value("${twilio.account-sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth-token}")
    private String twilioAuthToken;

    @Value("${twilio.verify-SOC}")
    private String verifySoc;

    private final TokenUtils tokenUtils;
    private final UserService userService;

    private final AccountRepository accountRepository;

    private final ModelMapper mapper;

    public TwilioOtpServiceImpl(TokenUtils tokenUtils, UserService userService, AccountRepository accountRepository, ModelMapper mapper) {
        this.tokenUtils = tokenUtils;

        this.userService = userService;
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
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Verification failed with status: " + status != null ? status : e.getMessage());
            }
            AccountModel accountModel = mapper.map(accountRepository.getAccountByPhoneNumber(verifyDto.getPhoneNumber()), AccountModel.class);
            accountRepository.validate(accountModel.getUserId());

            UserDto userDto = new UserDto();
            userDto.setUserId(accountModel.getUserId());
            userDto.setPhone(accountModel.getPhoneNumber());
            AddressDto addressDto = new AddressDto();
            addressDto.setLocation(new LocationDto());
            userDto.setAddress(addressDto);
            userDto.setGender(Gender.OTHER);

            ResponseEntity<BaseResponse<?>> response = userService.createUser(userDto);

            if (response == null) {
                throw new UserException();
            }

            TokenDto token = new TokenDto(tokenUtils.generateToken(accountModel.getUserId()));

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
