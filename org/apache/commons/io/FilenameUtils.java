/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileSystem;
import org.apache.commons.io.IOCase;

public class FilenameUtils {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String EMPTY_STRING = "";
    private static final int NOT_FOUND = -1;
    public static final char EXTENSION_SEPARATOR = '.';
    public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';
    private static final char SYSTEM_SEPARATOR = File.separatorChar;
    private static final char OTHER_SEPARATOR = FilenameUtils.isSystemWindows() ? (char)47 : (char)92;
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
    private static final int IPV4_MAX_OCTET_VALUE = 255;
    private static final int IPV6_MAX_HEX_GROUPS = 8;
    private static final int IPV6_MAX_HEX_DIGITS_PER_GROUP = 4;
    private static final int MAX_UNSIGNED_SHORT = 65535;
    private static final int BASE_16 = 16;
    private static final Pattern REG_NAME_PART_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");

    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    private static boolean isSeparator(char ch) {
        return ch == '/' || ch == '\\';
    }

    public static String normalize(String fileName) {
        return FilenameUtils.doNormalize(fileName, SYSTEM_SEPARATOR, true);
    }

    public static String normalize(String fileName, boolean unixSeparator) {
        char separator = unixSeparator ? (char)'/' : '\\';
        return FilenameUtils.doNormalize(fileName, separator, true);
    }

    public static String normalizeNoEndSeparator(String fileName) {
        return FilenameUtils.doNormalize(fileName, SYSTEM_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String fileName, boolean unixSeparator) {
        char separator = unixSeparator ? (char)'/' : '\\';
        return FilenameUtils.doNormalize(fileName, separator, false);
    }

    private static String doNormalize(String fileName, char separator, boolean keepSeparator) {
        int i;
        if (fileName == null) {
            return null;
        }
        FilenameUtils.requireNonNullChars(fileName);
        int size = fileName.length();
        if (size == 0) {
            return fileName;
        }
        int prefix = FilenameUtils.getPrefixLength(fileName);
        if (prefix < 0) {
            return null;
        }
        char[] array = new char[size + 2];
        fileName.getChars(0, fileName.length(), array, 0);
        char otherSeparator = separator == SYSTEM_SEPARATOR ? OTHER_SEPARATOR : SYSTEM_SEPARATOR;
        for (int i2 = 0; i2 < array.length; ++i2) {
            if (array[i2] != otherSeparator) continue;
            array[i2] = separator;
        }
        boolean lastIsDirectory = true;
        if (array[size - 1] != separator) {
            array[size++] = separator;
            lastIsDirectory = false;
        }
        int n = i = prefix != 0 ? prefix : 1;
        while (i < size) {
            if (array[i] == separator && array[i - 1] == separator) {
                System.arraycopy(array, i, array, i - 1, size - i);
                --size;
                --i;
            }
            ++i;
        }
        for (i = prefix + 1; i < size; ++i) {
            if (array[i] != separator || array[i - 1] != '.' || i != prefix + 1 && array[i - 2] != separator) continue;
            if (i == size - 1) {
                lastIsDirectory = true;
            }
            System.arraycopy(array, i + 1, array, i - 1, size - i);
            size -= 2;
            --i;
        }
        block3: for (i = prefix + 2; i < size; ++i) {
            if (array[i] != separator || array[i - 1] != '.' || array[i - 2] != '.' || i != prefix + 2 && array[i - 3] != separator) continue;
            if (i == prefix + 2) {
                return null;
            }
            if (i == size - 1) {
                lastIsDirectory = true;
            }
            for (int j = i - 4; j >= prefix; --j) {
                if (array[j] != separator) continue;
                System.arraycopy(array, i + 1, array, j + 1, size - i);
                size -= i - j;
                i = j + 1;
                continue block3;
            }
            System.arraycopy(array, i + 1, array, prefix, size - i);
            size -= i + 1 - prefix;
            i = prefix + 1;
        }
        if (size <= 0) {
            return EMPTY_STRING;
        }
        if (size <= prefix) {
            return new String(array, 0, size);
        }
        if (lastIsDirectory && keepSeparator) {
            return new String(array, 0, size);
        }
        return new String(array, 0, size - 1);
    }

    public static String concat(String basePath, String fullFileNameToAdd) {
        int prefix = FilenameUtils.getPrefixLength(fullFileNameToAdd);
        if (prefix < 0) {
            return null;
        }
        if (prefix > 0) {
            return FilenameUtils.normalize(fullFileNameToAdd);
        }
        if (basePath == null) {
            return null;
        }
        int len = basePath.length();
        if (len == 0) {
            return FilenameUtils.normalize(fullFileNameToAdd);
        }
        char ch = basePath.charAt(len - 1);
        if (FilenameUtils.isSeparator(ch)) {
            return FilenameUtils.normalize(basePath + fullFileNameToAdd);
        }
        return FilenameUtils.normalize(basePath + '/' + fullFileNameToAdd);
    }

    public static boolean directoryContains(String canonicalParent, String canonicalChild) {
        Objects.requireNonNull(canonicalParent, "canonicalParent");
        if (canonicalChild == null) {
            return false;
        }
        if (IOCase.SYSTEM.checkEquals(canonicalParent, canonicalChild)) {
            return false;
        }
        return IOCase.SYSTEM.checkStartsWith(canonicalChild, canonicalParent);
    }

    public static String separatorsToUnix(String path) {
        if (path == null || path.indexOf(92) == -1) {
            return path;
        }
        return path.replace('\\', '/');
    }

    public static String separatorsToWindows(String path) {
        if (path == null || path.indexOf(47) == -1) {
            return path;
        }
        return path.replace('/', '\\');
    }

    public static String separatorsToSystem(String path) {
        if (path == null) {
            return null;
        }
        return FilenameUtils.isSystemWindows() ? FilenameUtils.separatorsToWindows(path) : FilenameUtils.separatorsToUnix(path);
    }

    public static int getPrefixLength(String fileName) {
        if (fileName == null) {
            return -1;
        }
        int len = fileName.length();
        if (len == 0) {
            return 0;
        }
        char ch0 = fileName.charAt(0);
        if (ch0 == ':') {
            return -1;
        }
        if (len == 1) {
            if (ch0 == '~') {
                return 2;
            }
            return FilenameUtils.isSeparator(ch0) ? 1 : 0;
        }
        if (ch0 == '~') {
            int posUnix = fileName.indexOf(47, 1);
            int posWin = fileName.indexOf(92, 1);
            if (posUnix == -1 && posWin == -1) {
                return len + 1;
            }
            posUnix = posUnix == -1 ? posWin : posUnix;
            posWin = posWin == -1 ? posUnix : posWin;
            return Math.min(posUnix, posWin) + 1;
        }
        char ch1 = fileName.charAt(1);
        if (ch1 == ':') {
            if ((ch0 = Character.toUpperCase(ch0)) >= 'A' && ch0 <= 'Z') {
                if (len == 2 && !FileSystem.getCurrent().supportsDriveLetter()) {
                    return 0;
                }
                if (len == 2 || !FilenameUtils.isSeparator(fileName.charAt(2))) {
                    return 2;
                }
                return 3;
            }
            if (ch0 == '/') {
                return 1;
            }
            return -1;
        }
        if (!FilenameUtils.isSeparator(ch0) || !FilenameUtils.isSeparator(ch1)) {
            return FilenameUtils.isSeparator(ch0) ? 1 : 0;
        }
        int posUnix = fileName.indexOf(47, 2);
        int posWin = fileName.indexOf(92, 2);
        if (posUnix == -1 && posWin == -1 || posUnix == 2 || posWin == 2) {
            return -1;
        }
        posUnix = posUnix == -1 ? posWin : posUnix;
        posWin = posWin == -1 ? posUnix : posWin;
        int pos = Math.min(posUnix, posWin) + 1;
        String hostnamePart = fileName.substring(2, pos - 1);
        return FilenameUtils.isValidHostName(hostnamePart) ? pos : -1;
    }

    public static int indexOfLastSeparator(String fileName) {
        if (fileName == null) {
            return -1;
        }
        int lastUnixPos = fileName.lastIndexOf(47);
        int lastWindowsPos = fileName.lastIndexOf(92);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static int indexOfExtension(String fileName) throws IllegalArgumentException {
        int offset;
        if (fileName == null) {
            return -1;
        }
        if (FilenameUtils.isSystemWindows() && (offset = fileName.indexOf(58, FilenameUtils.getAdsCriticalOffset(fileName))) != -1) {
            throw new IllegalArgumentException("NTFS ADS separator (':') in file name is forbidden.");
        }
        int extensionPos = fileName.lastIndexOf(46);
        int lastSeparator = FilenameUtils.indexOfLastSeparator(fileName);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    public static String getPrefix(String fileName) {
        if (fileName == null) {
            return null;
        }
        int len = FilenameUtils.getPrefixLength(fileName);
        if (len < 0) {
            return null;
        }
        if (len > fileName.length()) {
            FilenameUtils.requireNonNullChars(fileName + '/');
            return fileName + '/';
        }
        String path = fileName.substring(0, len);
        FilenameUtils.requireNonNullChars(path);
        return path;
    }

    public static String getPath(String fileName) {
        return FilenameUtils.doGetPath(fileName, 1);
    }

    public static String getPathNoEndSeparator(String fileName) {
        return FilenameUtils.doGetPath(fileName, 0);
    }

    private static String doGetPath(String fileName, int separatorAdd) {
        if (fileName == null) {
            return null;
        }
        int prefix = FilenameUtils.getPrefixLength(fileName);
        if (prefix < 0) {
            return null;
        }
        int index = FilenameUtils.indexOfLastSeparator(fileName);
        int endIndex = index + separatorAdd;
        if (prefix >= fileName.length() || index < 0 || prefix >= endIndex) {
            return EMPTY_STRING;
        }
        String path = fileName.substring(prefix, endIndex);
        FilenameUtils.requireNonNullChars(path);
        return path;
    }

    public static String getFullPath(String fileName) {
        return FilenameUtils.doGetFullPath(fileName, true);
    }

    public static String getFullPathNoEndSeparator(String fileName) {
        return FilenameUtils.doGetFullPath(fileName, false);
    }

    private static String doGetFullPath(String fileName, boolean includeSeparator) {
        if (fileName == null) {
            return null;
        }
        int prefix = FilenameUtils.getPrefixLength(fileName);
        if (prefix < 0) {
            return null;
        }
        if (prefix >= fileName.length()) {
            if (includeSeparator) {
                return FilenameUtils.getPrefix(fileName);
            }
            return fileName;
        }
        int index = FilenameUtils.indexOfLastSeparator(fileName);
        if (index < 0) {
            return fileName.substring(0, prefix);
        }
        int end = index + (includeSeparator ? 1 : 0);
        if (end == 0) {
            ++end;
        }
        return fileName.substring(0, end);
    }

    public static String getName(String fileName) {
        if (fileName == null) {
            return null;
        }
        FilenameUtils.requireNonNullChars(fileName);
        int index = FilenameUtils.indexOfLastSeparator(fileName);
        return fileName.substring(index + 1);
    }

    private static void requireNonNullChars(String path) {
        if (path.indexOf(0) >= 0) {
            throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
        }
    }

    public static String getBaseName(String fileName) {
        return FilenameUtils.removeExtension(FilenameUtils.getName(fileName));
    }

    public static String getExtension(String fileName) throws IllegalArgumentException {
        if (fileName == null) {
            return null;
        }
        int index = FilenameUtils.indexOfExtension(fileName);
        if (index == -1) {
            return EMPTY_STRING;
        }
        return fileName.substring(index + 1);
    }

    private static int getAdsCriticalOffset(String fileName) {
        int offset1 = fileName.lastIndexOf(SYSTEM_SEPARATOR);
        int offset2 = fileName.lastIndexOf(OTHER_SEPARATOR);
        if (offset1 == -1) {
            if (offset2 == -1) {
                return 0;
            }
            return offset2 + 1;
        }
        if (offset2 == -1) {
            return offset1 + 1;
        }
        return Math.max(offset1, offset2) + 1;
    }

    public static String removeExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        FilenameUtils.requireNonNullChars(fileName);
        int index = FilenameUtils.indexOfExtension(fileName);
        if (index == -1) {
            return fileName;
        }
        return fileName.substring(0, index);
    }

    public static boolean equals(String fileName1, String fileName2) {
        return FilenameUtils.equals(fileName1, fileName2, false, IOCase.SENSITIVE);
    }

    public static boolean equalsOnSystem(String fileName1, String fileName2) {
        return FilenameUtils.equals(fileName1, fileName2, false, IOCase.SYSTEM);
    }

    public static boolean equalsNormalized(String fileName1, String fileName2) {
        return FilenameUtils.equals(fileName1, fileName2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String fileName1, String fileName2) {
        return FilenameUtils.equals(fileName1, fileName2, true, IOCase.SYSTEM);
    }

    public static boolean equals(String fileName1, String fileName2, boolean normalized, IOCase caseSensitivity) {
        if (fileName1 == null || fileName2 == null) {
            return fileName1 == null && fileName2 == null;
        }
        if (normalized) {
            if ((fileName1 = FilenameUtils.normalize(fileName1)) == null) {
                return false;
            }
            if ((fileName2 = FilenameUtils.normalize(fileName2)) == null) {
                return false;
            }
        }
        if (caseSensitivity == null) {
            caseSensitivity = IOCase.SENSITIVE;
        }
        return caseSensitivity.checkEquals(fileName1, fileName2);
    }

    public static boolean isExtension(String fileName, String extension) {
        if (fileName == null) {
            return false;
        }
        FilenameUtils.requireNonNullChars(fileName);
        if (extension == null || extension.isEmpty()) {
            return FilenameUtils.indexOfExtension(fileName) == -1;
        }
        String fileExt = FilenameUtils.getExtension(fileName);
        return fileExt.equals(extension);
    }

    public static boolean isExtension(String fileName, String ... extensions) {
        if (fileName == null) {
            return false;
        }
        FilenameUtils.requireNonNullChars(fileName);
        if (extensions == null || extensions.length == 0) {
            return FilenameUtils.indexOfExtension(fileName) == -1;
        }
        String fileExt = FilenameUtils.getExtension(fileName);
        for (String extension : extensions) {
            if (!fileExt.equals(extension)) continue;
            return true;
        }
        return false;
    }

    public static boolean isExtension(String fileName, Collection<String> extensions) {
        if (fileName == null) {
            return false;
        }
        FilenameUtils.requireNonNullChars(fileName);
        if (extensions == null || extensions.isEmpty()) {
            return FilenameUtils.indexOfExtension(fileName) == -1;
        }
        String fileExt = FilenameUtils.getExtension(fileName);
        for (String extension : extensions) {
            if (!fileExt.equals(extension)) continue;
            return true;
        }
        return false;
    }

    public static boolean wildcardMatch(String fileName, String wildcardMatcher) {
        return FilenameUtils.wildcardMatch(fileName, wildcardMatcher, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatchOnSystem(String fileName, String wildcardMatcher) {
        return FilenameUtils.wildcardMatch(fileName, wildcardMatcher, IOCase.SYSTEM);
    }

    public static boolean wildcardMatch(String fileName, String wildcardMatcher, IOCase caseSensitivity) {
        if (fileName == null && wildcardMatcher == null) {
            return true;
        }
        if (fileName == null || wildcardMatcher == null) {
            return false;
        }
        if (caseSensitivity == null) {
            caseSensitivity = IOCase.SENSITIVE;
        }
        String[] wcs = FilenameUtils.splitOnTokens(wildcardMatcher);
        boolean anyChars = false;
        int textIdx = 0;
        int wcsIdx = 0;
        ArrayDeque<int[]> backtrack = new ArrayDeque<int[]>(wcs.length);
        do {
            if (!backtrack.isEmpty()) {
                int[] array = (int[])backtrack.pop();
                wcsIdx = array[0];
                textIdx = array[1];
                anyChars = true;
            }
            while (wcsIdx < wcs.length) {
                if (wcs[wcsIdx].equals("?")) {
                    if (++textIdx > fileName.length()) break;
                    anyChars = false;
                } else if (wcs[wcsIdx].equals("*")) {
                    anyChars = true;
                    if (wcsIdx == wcs.length - 1) {
                        textIdx = fileName.length();
                    }
                } else {
                    if (anyChars) {
                        if ((textIdx = caseSensitivity.checkIndexOf(fileName, textIdx, wcs[wcsIdx])) == -1) break;
                        int repeat = caseSensitivity.checkIndexOf(fileName, textIdx + 1, wcs[wcsIdx]);
                        if (repeat >= 0) {
                            backtrack.push(new int[]{wcsIdx, repeat});
                        }
                    } else if (!caseSensitivity.checkRegionMatches(fileName, textIdx, wcs[wcsIdx])) break;
                    textIdx += wcs[wcsIdx].length();
                    anyChars = false;
                }
                ++wcsIdx;
            }
            if (wcsIdx != wcs.length || textIdx != fileName.length()) continue;
            return true;
        } while (!backtrack.isEmpty());
        return false;
    }

    static String[] splitOnTokens(String text) {
        if (text.indexOf(63) == -1 && text.indexOf(42) == -1) {
            return new String[]{text};
        }
        char[] array = text.toCharArray();
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder buffer = new StringBuilder();
        char prevChar = '\u0000';
        for (char ch : array) {
            if (ch == '?' || ch == '*') {
                if (buffer.length() != 0) {
                    list.add(buffer.toString());
                    buffer.setLength(0);
                }
                if (ch == '?') {
                    list.add("?");
                } else if (prevChar != '*') {
                    list.add("*");
                }
            } else {
                buffer.append(ch);
            }
            prevChar = ch;
        }
        if (buffer.length() != 0) {
            list.add(buffer.toString());
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }

    private static boolean isValidHostName(String name) {
        return FilenameUtils.isIPv6Address(name) || FilenameUtils.isRFC3986HostName(name);
    }

    private static boolean isIPv4Address(String name) {
        Matcher m = IPV4_PATTERN.matcher(name);
        if (!m.matches() || m.groupCount() != 4) {
            return false;
        }
        for (int i = 1; i <= 4; ++i) {
            String ipSegment = m.group(i);
            int iIpSegment = Integer.parseInt(ipSegment);
            if (iIpSegment > 255) {
                return false;
            }
            if (ipSegment.length() <= 1 || !ipSegment.startsWith("0")) continue;
            return false;
        }
        return true;
    }

    private static boolean isIPv6Address(String inet6Address) {
        boolean containsCompressedZeroes = inet6Address.contains("::");
        if (containsCompressedZeroes && inet6Address.indexOf("::") != inet6Address.lastIndexOf("::")) {
            return false;
        }
        if (inet6Address.startsWith(":") && !inet6Address.startsWith("::") || inet6Address.endsWith(":") && !inet6Address.endsWith("::")) {
            return false;
        }
        String[] octets = inet6Address.split(":");
        if (containsCompressedZeroes) {
            ArrayList<String> octetList = new ArrayList<String>(Arrays.asList(octets));
            if (inet6Address.endsWith("::")) {
                octetList.add(EMPTY_STRING);
            } else if (inet6Address.startsWith("::") && !octetList.isEmpty()) {
                octetList.remove(0);
            }
            octets = octetList.toArray(EMPTY_STRING_ARRAY);
        }
        if (octets.length > 8) {
            return false;
        }
        int validOctets = 0;
        int emptyOctets = 0;
        for (int index = 0; index < octets.length; ++index) {
            String octet = octets[index];
            if (octet.isEmpty()) {
                if (++emptyOctets > 1) {
                    return false;
                }
            } else {
                int octetInt;
                emptyOctets = 0;
                if (index == octets.length - 1 && octet.contains(".")) {
                    if (!FilenameUtils.isIPv4Address(octet)) {
                        return false;
                    }
                    validOctets += 2;
                    continue;
                }
                if (octet.length() > 4) {
                    return false;
                }
                try {
                    octetInt = Integer.parseInt(octet, 16);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (octetInt < 0 || octetInt > 65535) {
                    return false;
                }
            }
            ++validOctets;
        }
        return validOctets <= 8 && (validOctets >= 8 || containsCompressedZeroes);
    }

    private static boolean isRFC3986HostName(String name) {
        String[] parts = name.split("\\.", -1);
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].isEmpty()) {
                return i == parts.length - 1;
            }
            if (REG_NAME_PART_PATTERN.matcher(parts[i]).matches()) continue;
            return false;
        }
        return true;
    }
}

