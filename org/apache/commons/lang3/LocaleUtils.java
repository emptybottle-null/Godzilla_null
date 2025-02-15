/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.StringUtils;

public class LocaleUtils {
    private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap<String, List<Locale>>();
    private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap<String, List<Locale>>();

    public static List<Locale> availableLocaleList() {
        return SyncAvoid.AVAILABLE_LOCALE_LIST;
    }

    public static Set<Locale> availableLocaleSet() {
        return SyncAvoid.AVAILABLE_LOCALE_SET;
    }

    public static List<Locale> countriesByLanguage(String languageCode) {
        if (languageCode == null) {
            return Collections.emptyList();
        }
        List<Locale> countries = (ArrayList)cCountriesByLanguage.get(languageCode);
        if (countries == null) {
            countries = new ArrayList();
            List<Locale> locales = LocaleUtils.availableLocaleList();
            for (Locale locale : locales) {
                if (!languageCode.equals(locale.getLanguage()) || locale.getCountry().isEmpty() || !locale.getVariant().isEmpty()) continue;
                countries.add(locale);
            }
            countries = Collections.unmodifiableList(countries);
            cCountriesByLanguage.putIfAbsent(languageCode, countries);
            countries = (List)cCountriesByLanguage.get(languageCode);
        }
        return countries;
    }

    public static boolean isAvailableLocale(Locale locale) {
        return LocaleUtils.availableLocaleList().contains(locale);
    }

    private static boolean isISO3166CountryCode(String str) {
        return StringUtils.isAllUpperCase(str) && str.length() == 2;
    }

    private static boolean isISO639LanguageCode(String str) {
        return StringUtils.isAllLowerCase(str) && (str.length() == 2 || str.length() == 3);
    }

    private static boolean isNumericAreaCode(String str) {
        return StringUtils.isNumeric(str) && str.length() == 3;
    }

    public static List<Locale> languagesByCountry(String countryCode) {
        if (countryCode == null) {
            return Collections.emptyList();
        }
        List<Locale> langs = (ArrayList)cLanguagesByCountry.get(countryCode);
        if (langs == null) {
            langs = new ArrayList();
            List<Locale> locales = LocaleUtils.availableLocaleList();
            for (Locale locale : locales) {
                if (!countryCode.equals(locale.getCountry()) || !locale.getVariant().isEmpty()) continue;
                langs.add(locale);
            }
            langs = Collections.unmodifiableList(langs);
            cLanguagesByCountry.putIfAbsent(countryCode, langs);
            langs = (List)cLanguagesByCountry.get(countryCode);
        }
        return langs;
    }

    public static List<Locale> localeLookupList(Locale locale) {
        return LocaleUtils.localeLookupList(locale, locale);
    }

    public static List<Locale> localeLookupList(Locale locale, Locale defaultLocale) {
        ArrayList<Locale> list = new ArrayList<Locale>(4);
        if (locale != null) {
            list.add(locale);
            if (!locale.getVariant().isEmpty()) {
                list.add(new Locale(locale.getLanguage(), locale.getCountry()));
            }
            if (!locale.getCountry().isEmpty()) {
                list.add(new Locale(locale.getLanguage(), ""));
            }
            if (!list.contains(defaultLocale)) {
                list.add(defaultLocale);
            }
        }
        return Collections.unmodifiableList(list);
    }

    private static Locale parseLocale(String str) {
        if (LocaleUtils.isISO639LanguageCode(str)) {
            return new Locale(str);
        }
        String[] segments = str.split("_", -1);
        String language = segments[0];
        if (segments.length == 2) {
            String country = segments[1];
            if (LocaleUtils.isISO639LanguageCode(language) && LocaleUtils.isISO3166CountryCode(country) || LocaleUtils.isNumericAreaCode(country)) {
                return new Locale(language, country);
            }
        } else if (segments.length == 3) {
            String country = segments[1];
            String variant = segments[2];
            if (LocaleUtils.isISO639LanguageCode(language) && (country.isEmpty() || LocaleUtils.isISO3166CountryCode(country) || LocaleUtils.isNumericAreaCode(country)) && !variant.isEmpty()) {
                return new Locale(language, country, variant);
            }
        }
        throw new IllegalArgumentException("Invalid locale format: " + str);
    }

    public static Locale toLocale(Locale locale) {
        return locale != null ? locale : Locale.getDefault();
    }

    public static Locale toLocale(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return new Locale("", "");
        }
        if (str.contains("#")) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        int len = str.length();
        if (len < 2) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        char ch0 = str.charAt(0);
        if (ch0 == '_') {
            if (len < 3) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            char ch1 = str.charAt(1);
            char ch2 = str.charAt(2);
            if (!Character.isUpperCase(ch1) || !Character.isUpperCase(ch2)) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (len == 3) {
                return new Locale("", str.substring(1, 3));
            }
            if (len < 5) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (str.charAt(3) != '_') {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            return new Locale("", str.substring(1, 3), str.substring(4));
        }
        return LocaleUtils.parseLocale(str);
    }

    static class SyncAvoid {
        private static final List<Locale> AVAILABLE_LOCALE_LIST;
        private static final Set<Locale> AVAILABLE_LOCALE_SET;

        SyncAvoid() {
        }

        static {
            ArrayList<Locale> list = new ArrayList<Locale>(Arrays.asList(Locale.getAvailableLocales()));
            AVAILABLE_LOCALE_LIST = Collections.unmodifiableList(list);
            AVAILABLE_LOCALE_SET = Collections.unmodifiableSet(new HashSet<Locale>(list));
        }
    }
}

