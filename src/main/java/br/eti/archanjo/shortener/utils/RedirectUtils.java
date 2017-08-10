package br.eti.archanjo.shortener.utils;

import org.apache.commons.lang.RandomStringUtils;

public class RedirectUtils {
    public static String generateUrlId() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
