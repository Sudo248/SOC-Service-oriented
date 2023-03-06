package com.sudo248.domain.validator;

public class AuthValidator {
    private static final String PHONE_NUMBER_PATTERN = "(84|0[3|5|7|8|9])+([0-9]{8})\b";

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}
