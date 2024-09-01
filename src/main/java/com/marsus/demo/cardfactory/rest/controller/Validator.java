package com.marsus.demo.cardfactory.rest.controller;

import java.util.regex.Pattern;

/**
 * A utility validator class, handles various validation operations.
 */
public class Validator {

    /** Regular expression for OIB number */
    private static final String OIB_REGEXP = "^[0-9]{11}$";

    /**
     * Validate given  OIB number.
     *
     * @param oib number to validate
     * @return {@code true} if given OIB number is valid, {@code false} otherwise
     */
    public static boolean validateOib(final String oib) {

        if (oib == null) {
            return false;
        }

        return Pattern.compile(OIB_REGEXP).matcher(oib).matches();
    }
}
