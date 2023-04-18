package com.sudo248.authservice.service.impl;

import com.sudo248.authservice.contronller.dto.ChangePasswordDto;
import com.sudo248.authservice.contronller.dto.SignInDto;
import com.sudo248.authservice.contronller.dto.SignUpDto;
import com.sudo248.authservice.contronller.dto.TokenDto;
import com.sudo248.authservice.exception.PhoneNumberExistedException;
import com.sudo248.authservice.exception.PhoneNumberInvalidException;
import com.sudo248.authservice.exception.WrongPasswordException;
import com.sudo248.authservice.external.CommonService;
import com.sudo248.authservice.repository.AccountRepository;
import com.sudo248.authservice.repository.entity.Account;
import com.sudo248.authservice.service.AccountService;
import com.sudo248.authservice.service.OtpService;
import com.sudo248.authservice.service.model.AccountModel;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.exception.ApiException;
import com.sudo248.domain.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;
    private final CommonService commonService;

    private final OtpService otpService;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder encoder, ModelMapper mapper, CommonService commonService, @Qualifier("TwilioOtpService") OtpService otpService) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
        this.mapper = mapper;
        this.commonService = commonService;
        this.otpService = otpService;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> signIn(SignInDto signInDto) {
        return handleException(() -> {
            if (!accountRepository.existsByPhoneNumber(signInDto.getPhoneNumber())) {
                throw new PhoneNumberInvalidException();
            }
            AccountModel accountModel = mapper.map(accountRepository.getUserByPhoneNumber(signInDto.getPhoneNumber()), AccountModel.class);
            if (!encoder.matches(signInDto.getPassword(), accountModel.getPhoneNumber())) {
                throw new WrongPasswordException();
            }
//            if (!accountModel.isValidated()) {
//                var result = otpService.generateOtp(accountModel.getPhoneNumber());
//                if (result.getStatusCode() == HttpStatus.OK) {
//                    return BaseResponse.ok("Need to verify phone number " + accountModel.getPhoneNumber());
//                } else {
//                    return result;
//                }
//            }
            TokenDto token = new TokenDto(commonService.generateToken(accountModel.getUserId()));
            return BaseResponse.ok(token);
        });
    }

    @Override
    public ResponseEntity<BaseResponse<?>> signUp(SignUpDto signUpDto) {
        return handleException(() -> {
            var accountModel = signUpDto.toUserModel();
            log.info("accountModel: " + accountModel.toString());
            if (accountRepository.existsByPhoneNumber(accountModel.getPhoneNumber())) {
                throw new PhoneNumberExistedException();
            }
            saveAccount(accountModel);
//            var result = otpService.generateOtp(accountModel.getPhoneNumber());
//            if  (result.getStatusCode() == HttpStatus.OK) {
//                return BaseResponse.ok("Need to verify phone number " + accountModel.getPhoneNumber());
//            } else {
//                return result;
//            }
            return BaseResponse.ok("Need to verify phone number " + accountModel.getPhoneNumber());
        });
    }

    @Override
    public ResponseEntity<BaseResponse<?>> logOut(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> changePassword(String userId, ChangePasswordDto changePasswordDto) {
        return handleException(() -> {
            var accountModel = mapper.map(accountRepository.getReferenceById(userId), AccountModel.class);
            if (!encoder.matches(accountModel.getPassword(), changePasswordDto.getOldPassword())) {
                throw new WrongPasswordException();
            }
            accountModel.setPassword(changePasswordDto.getNewPassword());
            saveAccount(accountModel);
            return BaseResponse.ok("Change password success");
        });
    }

    private void saveAccount(AccountModel accountModel, boolean isHashPassword) throws ApiException {
        try {
            if (accountModel.getUserId() == null || accountModel.getUserId().isEmpty()) {
                accountModel.setUserId(Utils.createIdOrElse(accountModel.getUserId()));
            }
            if (isHashPassword) {
                String hashPassword = encoder.encode(accountModel.getPassword());
                accountModel.setPassword(hashPassword);
            }
            accountRepository.save(mapper.map(accountModel, Account.class));
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INSUFFICIENT_STORAGE, e.getMessage());
        }
    }

    private void saveAccount(AccountModel accountModel) throws ApiException {
        saveAccount(accountModel, true);
    }
}
