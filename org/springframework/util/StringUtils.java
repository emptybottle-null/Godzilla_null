/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

public abstract class StringUtils {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String FOLDER_SEPARATOR = "/";
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = '.';

    @Deprecated
    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }

    public static boolean hasLength(@Nullable CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasLength(@Nullable String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean hasText(@Nullable CharSequence str) {
        return str != null && str.length() > 0 && StringUtils.containsText(str);
    }

    public static boolean hasText(@Nullable String str) {
        return str != null && !str.isEmpty() && StringUtils.containsText(str);
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; ++i) {
            if (Character.isWhitespace(str.charAt(i))) continue;
            return true;
        }
        return false;
    }

    public static boolean containsWhitespace(@Nullable CharSequence str) {
        if (!StringUtils.hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) continue;
            return true;
        }
        return false;
    }

    public static boolean containsWhitespace(@Nullable String str) {
        return StringUtils.containsWhitespace((CharSequence)str);
    }

    public static String trimWhitespace(String str) {
        int beginIndex;
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        int endIndex = str.length() - 1;
        for (beginIndex = 0; beginIndex <= endIndex && Character.isWhitespace(str.charAt(beginIndex)); ++beginIndex) {
        }
        while (endIndex > beginIndex && Character.isWhitespace(str.charAt(endIndex))) {
            --endIndex;
        }
        return str.substring(beginIndex, endIndex + 1);
    }

    public static String trimAllWhitespace(String str) {
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < len; ++i) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c)) continue;
            sb.append(c);
        }
        return sb.toString();
    }

    public static String trimLeadingWhitespace(String str) {
        int beginIdx;
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        for (beginIdx = 0; beginIdx < str.length() && Character.isWhitespace(str.charAt(beginIdx)); ++beginIdx) {
        }
        return str.substring(beginIdx);
    }

    public static String trimTrailingWhitespace(String str) {
        int endIdx;
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        for (endIdx = str.length() - 1; endIdx >= 0 && Character.isWhitespace(str.charAt(endIdx)); --endIdx) {
        }
        return str.substring(0, endIdx + 1);
    }

    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        int beginIdx;
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        for (beginIdx = 0; beginIdx < str.length() && leadingCharacter == str.charAt(beginIdx); ++beginIdx) {
        }
        return str.substring(beginIdx);
    }

    public static String trimTrailingCharacter(String str, char trailingCharacter) {
        int endIdx;
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        for (endIdx = str.length() - 1; endIdx >= 0 && trailingCharacter == str.charAt(endIdx); --endIdx) {
        }
        return str.substring(0, endIdx + 1);
    }

    public static boolean matchesCharacter(@Nullable String str, char singleCharacter) {
        return str != null && str.length() == 1 && str.charAt(0) == singleCharacter;
    }

    public static boolean startsWithIgnoreCase(@Nullable String str, @Nullable String prefix) {
        return str != null && prefix != null && str.length() >= prefix.length() && str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static boolean endsWithIgnoreCase(@Nullable String str, @Nullable String suffix) {
        return str != null && suffix != null && str.length() >= suffix.length() && str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length());
    }

    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        if (index + substring.length() > str.length()) {
            return false;
        }
        for (int i = 0; i < substring.length(); ++i) {
            if (str.charAt(index + i) == substring.charAt(i)) continue;
            return false;
        }
        return true;
    }

    public static int countOccurrencesOf(String str, String sub) {
        int idx;
        if (!StringUtils.hasLength(str) || !StringUtils.hasLength(sub)) {
            return 0;
        }
        int count = 0;
        int pos = 0;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    public static String replace(String inString, String oldPattern, @Nullable String newPattern) {
        if (!StringUtils.hasLength(inString) || !StringUtils.hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            return inString;
        }
        int capacity = inString.length();
        if (newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }
        StringBuilder sb = new StringBuilder(capacity);
        int pos = 0;
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString, pos, index);
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString, pos, inString.length());
        return sb.toString();
    }

    public static String delete(String inString, String pattern) {
        return StringUtils.replace(inString, pattern, "");
    }

    public static String deleteAny(String inString, @Nullable String charsToDelete) {
        if (!StringUtils.hasLength(inString) || !StringUtils.hasLength(charsToDelete)) {
            return inString;
        }
        int lastCharIndex = 0;
        char[] result = new char[inString.length()];
        for (int i = 0; i < inString.length(); ++i) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) != -1) continue;
            result[lastCharIndex++] = c;
        }
        if (lastCharIndex == inString.length()) {
            return inString;
        }
        return new String(result, 0, lastCharIndex);
    }

    @Nullable
    public static String quote(@Nullable String str) {
        return str != null ? "'" + str + "'" : null;
    }

    @Nullable
    public static Object quoteIfString(@Nullable Object obj) {
        return obj instanceof String ? StringUtils.quote((String)obj) : obj;
    }

    public static String unqualify(String qualifiedName) {
        return StringUtils.unqualify(qualifiedName, '.');
    }

    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }

    public static String capitalize(String str) {
        return StringUtils.changeFirstCharacterCase(str, true);
    }

    public static String uncapitalize(String str) {
        return StringUtils.changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        char updatedChar;
        if (!StringUtils.hasLength(str)) {
            return str;
        }
        char baseChar = str.charAt(0);
        if (baseChar == (updatedChar = capitalize ? Character.toUpperCase(baseChar) : Character.toLowerCase(baseChar))) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars);
    }

    @Nullable
    public static String getFilename(@Nullable String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
    }

    @Nullable
    public static String getFilenameExtension(@Nullable String path) {
        if (path == null) {
            return null;
        }
        int extIndex = path.lastIndexOf(46);
        if (extIndex == -1) {
            return null;
        }
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return path.substring(extIndex + 1);
    }

    public static String stripFilenameExtension(String path) {
        int extIndex = path.lastIndexOf(46);
        if (extIndex == -1) {
            return path;
        }
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return path;
        }
        return path.substring(0, extIndex);
    }

    public static String applyRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
                newPath = newPath + FOLDER_SEPARATOR;
            }
            return newPath + relativePath;
        }
        return relativePath;
    }

    public static String cleanPath(String path) {
        int i;
        if (!StringUtils.hasLength(path)) {
            return path;
        }
        String normalizedPath = StringUtils.replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
        String pathToUse = normalizedPath;
        if (pathToUse.indexOf(46) == -1) {
            return pathToUse;
        }
        int prefixIndex = pathToUse.indexOf(58);
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            if (prefix.contains(FOLDER_SEPARATOR)) {
                prefix = "";
            } else {
                pathToUse = pathToUse.substring(prefixIndex + 1);
            }
        }
        if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
            prefix = prefix + FOLDER_SEPARATOR;
            pathToUse = pathToUse.substring(1);
        }
        String[] pathArray = StringUtils.delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
        ArrayDeque<String> pathElements = new ArrayDeque<String>(pathArray.length);
        int tops = 0;
        for (i = pathArray.length - 1; i >= 0; --i) {
            String element = pathArray[i];
            if (CURRENT_PATH.equals(element)) continue;
            if (TOP_PATH.equals(element)) {
                ++tops;
                continue;
            }
            if (tops > 0) {
                --tops;
                continue;
            }
            pathElements.addFirst(element);
        }
        if (pathArray.length == pathElements.size()) {
            return normalizedPath;
        }
        for (i = 0; i < tops; ++i) {
            pathElements.addFirst(TOP_PATH);
        }
        if (pathElements.size() == 1 && ((String)pathElements.getLast()).isEmpty() && !prefix.endsWith(FOLDER_SEPARATOR)) {
            pathElements.addFirst(CURRENT_PATH);
        }
        String joined = StringUtils.collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
        return prefix.isEmpty() ? joined : prefix + joined;
    }

    public static boolean pathEquals(String path1, String path2) {
        return StringUtils.cleanPath(path1).equals(StringUtils.cleanPath(path2));
    }

    public static String uriDecode(String source, Charset charset) {
        int length = source.length();
        if (length == 0) {
            return source;
        }
        Assert.notNull((Object)charset, "Charset must not be null");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(length);
        boolean changed = false;
        for (int i = 0; i < length; ++i) {
            char ch = source.charAt(i);
            if (ch == '%') {
                if (i + 2 < length) {
                    char hex1 = source.charAt(i + 1);
                    char hex2 = source.charAt(i + 2);
                    int u = Character.digit(hex1, 16);
                    int l = Character.digit(hex2, 16);
                    if (u == -1 || l == -1) {
                        throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
                    }
                    baos.write((char)((u << 4) + l));
                    i += 2;
                    changed = true;
                    continue;
                }
                throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
            }
            baos.write(ch);
        }
        return changed ? StreamUtils.copyToString(baos, charset) : source;
    }

    @Nullable
    public static Locale parseLocale(String localeValue) {
        String[] tokens = StringUtils.tokenizeLocaleSource(localeValue);
        if (tokens.length == 1) {
            StringUtils.validateLocalePart(localeValue);
            Locale resolved = Locale.forLanguageTag(localeValue);
            if (resolved.getLanguage().length() > 0) {
                return resolved;
            }
        }
        return StringUtils.parseLocaleTokens(localeValue, tokens);
    }

    @Nullable
    public static Locale parseLocaleString(String localeString) {
        return StringUtils.parseLocaleTokens(localeString, StringUtils.tokenizeLocaleSource(localeString));
    }

    private static String[] tokenizeLocaleSource(String localeSource) {
        return StringUtils.tokenizeToStringArray(localeSource, "_ ", false, false);
    }

    @Nullable
    private static Locale parseLocaleTokens(String localeString, String[] tokens) {
        int endIndexOfCountryCode;
        String language = tokens.length > 0 ? tokens[0] : "";
        String country = tokens.length > 1 ? tokens[1] : "";
        StringUtils.validateLocalePart(language);
        StringUtils.validateLocalePart(country);
        String variant = "";
        if (tokens.length > 2 && (variant = StringUtils.trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode = localeString.indexOf(country, language.length()) + country.length()))).startsWith("_")) {
            variant = StringUtils.trimLeadingCharacter(variant, '_');
        }
        if (variant.isEmpty() && country.startsWith("#")) {
            variant = country;
            country = "";
        }
        return language.length() > 0 ? new Locale(language, country, variant) : null;
    }

    private static void validateLocalePart(String localePart) {
        for (int i = 0; i < localePart.length(); ++i) {
            char ch = localePart.charAt(i);
            if (ch == ' ' || ch == '_' || ch == '-' || ch == '#' || Character.isLetterOrDigit(ch)) continue;
            throw new IllegalArgumentException("Locale part \"" + localePart + "\" contains invalid characters");
        }
    }

    @Deprecated
    public static String toLanguageTag(Locale locale) {
        return locale.getLanguage() + (StringUtils.hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
    }

    public static TimeZone parseTimeZoneString(String timeZoneString) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
        if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
            throw new IllegalArgumentException("Invalid time zone specification '" + timeZoneString + "'");
        }
        return timeZone;
    }

    public static String[] toStringArray(@Nullable Collection<String> collection) {
        return !CollectionUtils.isEmpty(collection) ? collection.toArray(EMPTY_STRING_ARRAY) : EMPTY_STRING_ARRAY;
    }

    public static String[] toStringArray(@Nullable Enumeration<String> enumeration) {
        return enumeration != null ? StringUtils.toStringArray(Collections.list(enumeration)) : EMPTY_STRING_ARRAY;
    }

    public static String[] addStringToArray(@Nullable String[] array, String str) {
        if (ObjectUtils.isEmpty(array)) {
            return new String[]{str};
        }
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }

    @Nullable
    public static String[] concatenateStringArrays(@Nullable String[] array1, @Nullable String[] array2) {
        if (ObjectUtils.isEmpty(array1)) {
            return array2;
        }
        if (ObjectUtils.isEmpty(array2)) {
            return array1;
        }
        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }

    @Deprecated
    @Nullable
    public static String[] mergeStringArrays(@Nullable String[] array1, @Nullable String[] array2) {
        if (ObjectUtils.isEmpty(array1)) {
            return array2;
        }
        if (ObjectUtils.isEmpty(array2)) {
            return array1;
        }
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(array1));
        for (String str : array2) {
            if (result.contains(str)) continue;
            result.add(str);
        }
        return StringUtils.toStringArray(result);
    }

    public static String[] sortStringArray(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return array;
        }
        Arrays.sort(array);
        return array;
    }

    public static String[] trimArrayElements(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return array;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            String element = array[i];
            result[i] = element != null ? element.trim() : null;
        }
        return result;
    }

    public static String[] removeDuplicateStrings(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return array;
        }
        LinkedHashSet<String> set = new LinkedHashSet<String>(Arrays.asList(array));
        return StringUtils.toStringArray(set);
    }

    @Nullable
    public static String[] split(@Nullable String toSplit, @Nullable String delimiter) {
        if (!StringUtils.hasLength(toSplit) || !StringUtils.hasLength(delimiter)) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }
        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[]{beforeDelimiter, afterDelimiter};
    }

    @Nullable
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
        return StringUtils.splitArrayElementsIntoProperties(array, delimiter, null);
    }

    @Nullable
    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter, @Nullable String charsToDelete) {
        if (ObjectUtils.isEmpty(array)) {
            return null;
        }
        Properties result = new Properties();
        for (String element : array) {
            String[] splittedElement;
            if (charsToDelete != null) {
                element = StringUtils.deleteAny(element, charsToDelete);
            }
            if ((splittedElement = StringUtils.split(element, delimiter)) == null) continue;
            result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
        }
        return result;
    }

    public static String[] tokenizeToStringArray(@Nullable String str, String delimiters) {
        return StringUtils.tokenizeToStringArray(str, delimiters, true, true);
    }

    public static String[] tokenizeToStringArray(@Nullable String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
        if (str == null) {
            return EMPTY_STRING_ARRAY;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        ArrayList<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (ignoreEmptyTokens && token.length() <= 0) continue;
            tokens.add(token);
        }
        return StringUtils.toStringArray(tokens);
    }

    public static String[] delimitedListToStringArray(@Nullable String str, @Nullable String delimiter) {
        return StringUtils.delimitedListToStringArray(str, delimiter, null);
    }

    public static String[] delimitedListToStringArray(@Nullable String str, @Nullable String delimiter, @Nullable String charsToDelete) {
        if (str == null) {
            return EMPTY_STRING_ARRAY;
        }
        if (delimiter == null) {
            return new String[]{str};
        }
        ArrayList<String> result = new ArrayList<String>();
        if (delimiter.isEmpty()) {
            for (int i = 0; i < str.length(); ++i) {
                result.add(StringUtils.deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        } else {
            int delPos;
            int pos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(StringUtils.deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                result.add(StringUtils.deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return StringUtils.toStringArray(result);
    }

    public static String[] commaDelimitedListToStringArray(@Nullable String str) {
        return StringUtils.delimitedListToStringArray(str, ",");
    }

    public static Set<String> commaDelimitedListToSet(@Nullable String str) {
        String[] tokens = StringUtils.commaDelimitedListToStringArray(str);
        return new LinkedHashSet<String>(Arrays.asList(tokens));
    }

    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim, String prefix, String suffix) {
        if (CollectionUtils.isEmpty(coll)) {
            return "";
        }
        int totalLength = coll.size() * (prefix.length() + suffix.length()) + (coll.size() - 1) * delim.length();
        for (Object element : coll) {
            totalLength += String.valueOf(element).length();
        }
        StringBuilder sb = new StringBuilder(totalLength);
        Iterator<?> it = coll.iterator();
        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (!it.hasNext()) continue;
            sb.append(delim);
        }
        return sb.toString();
    }

    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim, "", "");
    }

    public static String collectionToCommaDelimitedString(@Nullable Collection<?> coll) {
        return StringUtils.collectionToDelimitedString(coll, ",");
    }

    public static String arrayToDelimitedString(@Nullable Object[] arr, String delim) {
        if (ObjectUtils.isEmpty(arr)) {
            return "";
        }
        if (arr.length == 1) {
            return ObjectUtils.nullSafeToString(arr[0]);
        }
        StringJoiner sj = new StringJoiner(delim);
        for (Object elem : arr) {
            sj.add(String.valueOf(elem));
        }
        return sj.toString();
    }

    public static String arrayToCommaDelimitedString(@Nullable Object[] arr) {
        return StringUtils.arrayToDelimitedString(arr, ",");
    }
}

