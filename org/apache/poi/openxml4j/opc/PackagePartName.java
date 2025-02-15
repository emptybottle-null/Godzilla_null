/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public final class PackagePartName
implements Comparable<PackagePartName> {
    private final URI partNameURI;
    private static final String RFC3986_PCHAR_SUB_DELIMS = "!$&'()*+,;=";
    private static final String RFC3986_PCHAR_UNRESERVED_SUP = "-._~";
    private static final String RFC3986_PCHAR_AUTHORIZED_SUP = ":@";
    private final boolean isRelationship;

    PackagePartName(URI uri, boolean checkConformance) throws InvalidFormatException {
        if (checkConformance) {
            PackagePartName.throwExceptionIfInvalidPartUri(uri);
        } else if (!PackagingURIHelper.PACKAGE_ROOT_URI.equals(uri)) {
            throw new OpenXML4JRuntimeException("OCP conformance must be check for ALL part name except special cases : ['/']");
        }
        this.partNameURI = uri;
        this.isRelationship = this.isRelationshipPartURI(this.partNameURI);
    }

    PackagePartName(String partName, boolean checkConformance) throws InvalidFormatException {
        URI partURI;
        try {
            partURI = new URI(partName);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("partName argmument is not a valid OPC part name !");
        }
        if (checkConformance) {
            PackagePartName.throwExceptionIfInvalidPartUri(partURI);
        } else if (!PackagingURIHelper.PACKAGE_ROOT_URI.equals(partURI)) {
            throw new OpenXML4JRuntimeException("OCP conformance must be check for ALL part name except special cases : ['/']");
        }
        this.partNameURI = partURI;
        this.isRelationship = this.isRelationshipPartURI(this.partNameURI);
    }

    private boolean isRelationshipPartURI(URI partUri) {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        return partUri.getPath().matches("^.*/_rels/.*\\.rels$");
    }

    public boolean isRelationshipPartURI() {
        return this.isRelationship;
    }

    private static void throwExceptionIfInvalidPartUri(URI partUri) throws InvalidFormatException {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        PackagePartName.throwExceptionIfEmptyURI(partUri);
        PackagePartName.throwExceptionIfAbsoluteUri(partUri);
        PackagePartName.throwExceptionIfPartNameNotStartsWithForwardSlashChar(partUri);
        PackagePartName.throwExceptionIfPartNameEndsWithForwardSlashChar(partUri);
        PackagePartName.throwExceptionIfPartNameHaveInvalidSegments(partUri);
    }

    private static void throwExceptionIfEmptyURI(URI partURI) throws InvalidFormatException {
        if (partURI == null) {
            throw new IllegalArgumentException("partURI");
        }
        String uriPath = partURI.getPath();
        if (uriPath.length() == 0 || uriPath.length() == 1 && uriPath.charAt(0) == '/') {
            throw new InvalidFormatException("A part name shall not be empty [M1.1]: " + partURI.getPath());
        }
    }

    private static void throwExceptionIfPartNameHaveInvalidSegments(URI partUri) throws InvalidFormatException {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        String[] segments = partUri.toASCIIString().replaceFirst("^/", "").split("/");
        if (segments.length < 1) {
            throw new InvalidFormatException("A part name shall not have empty segments [M1.3]: " + partUri.getPath());
        }
        for (String seg : segments) {
            if (seg == null || seg.isEmpty()) {
                throw new InvalidFormatException("A part name shall not have empty segments [M1.3]: " + partUri.getPath());
            }
            if (seg.endsWith(".")) {
                throw new InvalidFormatException("A segment shall not end with a dot ('.') character [M1.9]: " + partUri.getPath());
            }
            if (seg.replaceAll("\\\\.", "").isEmpty()) {
                throw new InvalidFormatException("A segment shall include at least one non-dot character. [M1.10]: " + partUri.getPath());
            }
            PackagePartName.checkPCharCompliance(seg);
        }
    }

    private static void checkPCharCompliance(String segment) throws InvalidFormatException {
        int length = segment.length();
        for (int i = 0; i < length; ++i) {
            char c = segment.charAt(i);
            if (PackagePartName.isDigitOrLetter(c) || RFC3986_PCHAR_UNRESERVED_SUP.indexOf(c) > -1 || RFC3986_PCHAR_AUTHORIZED_SUP.indexOf(c) > -1 || RFC3986_PCHAR_SUB_DELIMS.indexOf(c) > -1) continue;
            if (c != '%') {
                throw new InvalidFormatException("A segment shall not hold any characters other than pchar characters. [M1.6]");
            }
            if (length - i < 2 || !PackagePartName.isHexDigit(segment.charAt(i + 1)) || !PackagePartName.isHexDigit(segment.charAt(i + 2))) {
                throw new InvalidFormatException("The segment " + segment + " contain invalid encoded character !");
            }
            char decodedChar = (char)Integer.parseInt(segment.substring(i + 1, i + 3), 16);
            i += 2;
            if (decodedChar == '/' || decodedChar == '\\') {
                throw new InvalidFormatException("A segment shall not contain percent-encoded forward slash ('/'), or backward slash ('') characters. [M1.7]");
            }
            if (!PackagePartName.isDigitOrLetter(decodedChar) && RFC3986_PCHAR_UNRESERVED_SUP.indexOf(decodedChar) <= -1) continue;
            throw new InvalidFormatException("A segment shall not contain percent-encoded unreserved characters. [M1.8]");
        }
    }

    private static void throwExceptionIfPartNameNotStartsWithForwardSlashChar(URI partUri) throws InvalidFormatException {
        String uriPath = partUri.getPath();
        if (uriPath.length() > 0 && uriPath.charAt(0) != '/') {
            throw new InvalidFormatException("A part name shall start with a forward slash ('/') character [M1.4]: " + partUri.getPath());
        }
    }

    private static void throwExceptionIfPartNameEndsWithForwardSlashChar(URI partUri) throws InvalidFormatException {
        String uriPath = partUri.getPath();
        if (uriPath.length() > 0 && uriPath.charAt(uriPath.length() - 1) == '/') {
            throw new InvalidFormatException("A part name shall not have a forward slash as the last character [M1.5]: " + partUri.getPath());
        }
    }

    private static void throwExceptionIfAbsoluteUri(URI partUri) throws InvalidFormatException {
        if (partUri.isAbsolute()) {
            throw new InvalidFormatException("Absolute URI forbidden: " + partUri);
        }
    }

    @Override
    public int compareTo(PackagePartName other) {
        return PackagePartName.compare(this, other);
    }

    public String getExtension() {
        int i;
        String fragment = this.partNameURI.getPath();
        if (fragment.length() > 0 && (i = fragment.lastIndexOf(".")) > -1) {
            return fragment.substring(i + 1);
        }
        return "";
    }

    public String getName() {
        return this.getURI().toASCIIString();
    }

    public boolean equals(Object other) {
        return other instanceof PackagePartName && PackagePartName.compare(this.getName(), ((PackagePartName)other).getName()) == 0;
    }

    public int hashCode() {
        return this.getName().toLowerCase(Locale.ROOT).hashCode();
    }

    public String toString() {
        return this.getName();
    }

    public URI getURI() {
        return this.partNameURI;
    }

    public static int compare(PackagePartName obj1, PackagePartName obj2) {
        return PackagePartName.compare(obj1 == null ? null : obj1.getName(), obj2 == null ? null : obj2.getName());
    }

    public static int compare(String str1, String str2) {
        if (str1 == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        if (str1.equalsIgnoreCase(str2)) {
            return 0;
        }
        String name1 = str1.toLowerCase(Locale.ROOT);
        String name2 = str2.toLowerCase(Locale.ROOT);
        int len1 = name1.length();
        int len2 = name2.length();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < len1 && idx2 < len2) {
            char c1 = name1.charAt(idx1++);
            char c2 = name2.charAt(idx2++);
            if (Character.isDigit(c1) && Character.isDigit(c2)) {
                BigInteger b2;
                int beg1 = idx1 - 1;
                while (idx1 < len1 && Character.isDigit(name1.charAt(idx1))) {
                    ++idx1;
                }
                int beg2 = idx2 - 1;
                while (idx2 < len2 && Character.isDigit(name2.charAt(idx2))) {
                    ++idx2;
                }
                BigInteger b1 = new BigInteger(name1.substring(beg1, idx1));
                int cmp = b1.compareTo(b2 = new BigInteger(name2.substring(beg2, idx2)));
                if (cmp == 0) continue;
                return cmp;
            }
            if (c1 == c2) continue;
            return c1 - c2;
        }
        return len1 - len2;
    }

    private static boolean isDigitOrLetter(char c) {
        return c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }

    private static boolean isHexDigit(char c) {
        return c >= '0' && c <= '9' || c >= 'A' && c <= 'F' || c >= 'a' && c <= 'f';
    }
}

