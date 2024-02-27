package com.main.connect4shared.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String REGEX_USERNAME = "[a-zA-Z0-9]{3,32}";

    private static final String REGEX_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private static final String REGEX_PASSWORD = "[a-zA-Z0-9]{6,}";

    public static boolean validateUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    public static boolean validateEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX
                = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
}