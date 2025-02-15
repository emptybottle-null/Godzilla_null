/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class PackagingURIHelper {
    private static final POILogger _logger = POILogFactory.getLogger(PackagingURIHelper.class);
    private static URI packageRootUri;
    public static final String RELATIONSHIP_PART_EXTENSION_NAME = ".rels";
    public static final String RELATIONSHIP_PART_SEGMENT_NAME = "_rels";
    public static final String PACKAGE_PROPERTIES_SEGMENT_NAME = "docProps";
    public static final String PACKAGE_CORE_PROPERTIES_NAME = "core.xml";
    public static final char FORWARD_SLASH_CHAR = '/';
    public static final String FORWARD_SLASH_STRING = "/";
    public static final URI PACKAGE_RELATIONSHIPS_ROOT_URI;
    public static final PackagePartName PACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
    public static final URI CORE_PROPERTIES_URI;
    public static final PackagePartName CORE_PROPERTIES_PART_NAME;
    public static final URI PACKAGE_ROOT_URI;
    public static final PackagePartName PACKAGE_ROOT_PART_NAME;
    private static final Pattern missingAuthPattern;
    private static final char[] hexDigits;

    public static URI getPackageRootUri() {
        return packageRootUri;
    }

    public static boolean isRelationshipPartURI(URI partUri) {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        return partUri.getPath().matches(".*_rels.*.rels$");
    }

    public static String getFilename(URI uri) {
        if (uri != null) {
            int len;
            String path = uri.getPath();
            int num2 = len = path.length();
            while (--num2 >= 0) {
                char ch1 = path.charAt(num2);
                if (ch1 != '/') continue;
                return path.substring(num2 + 1, len);
            }
        }
        return "";
    }

    public static String getFilenameWithoutExtension(URI uri) {
        String filename = PackagingURIHelper.getFilename(uri);
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1) {
            return filename;
        }
        return filename.substring(0, dotIndex);
    }

    public static URI getPath(URI uri) {
        if (uri != null) {
            String path = uri.getPath();
            int num2 = path.length();
            while (--num2 >= 0) {
                char ch1 = path.charAt(num2);
                if (ch1 != '/') continue;
                try {
                    return new URI(path.substring(0, num2));
                } catch (URISyntaxException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static URI combine(URI prefix, URI suffix) {
        URI retUri;
        try {
            retUri = new URI(PackagingURIHelper.combine(prefix.getPath(), suffix.getPath()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Prefix and suffix can't be combine !");
        }
        return retUri;
    }

    public static String combine(String prefix, String suffix) {
        if (!prefix.endsWith(FORWARD_SLASH_STRING) && !suffix.startsWith(FORWARD_SLASH_STRING)) {
            return prefix + '/' + suffix;
        }
        if (prefix.endsWith(FORWARD_SLASH_STRING) ^ suffix.startsWith(FORWARD_SLASH_STRING)) {
            return prefix + suffix;
        }
        return "";
    }

    public static URI relativizeURI(URI sourceURI, URI targetURI, boolean msCompatible) {
        String fragment;
        int i;
        StringBuilder retVal = new StringBuilder();
        String[] segmentsSource = sourceURI.getPath().split(FORWARD_SLASH_STRING, -1);
        String[] segmentsTarget = targetURI.getPath().split(FORWARD_SLASH_STRING, -1);
        if (segmentsSource.length == 0) {
            throw new IllegalArgumentException("Can't relativize an empty source URI !");
        }
        if (segmentsTarget.length == 0) {
            throw new IllegalArgumentException("Can't relativize an empty target URI !");
        }
        if (sourceURI.toString().equals(FORWARD_SLASH_STRING)) {
            String path = targetURI.getPath();
            if (msCompatible && path.length() > 0 && path.charAt(0) == '/') {
                try {
                    targetURI = new URI(path.substring(1));
                } catch (Exception e) {
                    _logger.log(5, e);
                    return null;
                }
            }
            return targetURI;
        }
        int segmentsTheSame = 0;
        for (i = 0; i < segmentsSource.length && i < segmentsTarget.length && segmentsSource[i].equals(segmentsTarget[i]); ++i) {
            ++segmentsTheSame;
        }
        if ((segmentsTheSame == 0 || segmentsTheSame == 1) && segmentsSource[0].isEmpty() && segmentsTarget[0].isEmpty()) {
            for (i = 0; i < segmentsSource.length - 2; ++i) {
                retVal.append("../");
            }
            for (i = 0; i < segmentsTarget.length; ++i) {
                if (segmentsTarget[i].isEmpty()) continue;
                retVal.append(segmentsTarget[i]);
                if (i == segmentsTarget.length - 1) continue;
                retVal.append(FORWARD_SLASH_STRING);
            }
            try {
                return new URI(retVal.toString());
            } catch (Exception e) {
                _logger.log(5, e);
                return null;
            }
        }
        if (segmentsTheSame == segmentsSource.length && segmentsTheSame == segmentsTarget.length) {
            if (sourceURI.equals(targetURI)) {
                retVal.append(segmentsSource[segmentsSource.length - 1]);
            }
        } else {
            int j;
            if (segmentsTheSame == 1) {
                retVal.append(FORWARD_SLASH_STRING);
            } else {
                for (j = segmentsTheSame; j < segmentsSource.length - 1; ++j) {
                    retVal.append("../");
                }
            }
            for (j = segmentsTheSame; j < segmentsTarget.length; ++j) {
                if (retVal.length() > 0 && retVal.charAt(retVal.length() - 1) != '/') {
                    retVal.append(FORWARD_SLASH_STRING);
                }
                retVal.append(segmentsTarget[j]);
            }
        }
        if ((fragment = targetURI.getRawFragment()) != null) {
            retVal.append("#").append(fragment);
        }
        try {
            return new URI(retVal.toString());
        } catch (Exception e) {
            _logger.log(5, e);
            return null;
        }
    }

    public static URI relativizeURI(URI sourceURI, URI targetURI) {
        return PackagingURIHelper.relativizeURI(sourceURI, targetURI, false);
    }

    public static URI resolvePartUri(URI sourcePartUri, URI targetUri) {
        if (sourcePartUri == null || sourcePartUri.isAbsolute()) {
            throw new IllegalArgumentException("sourcePartUri invalid - " + sourcePartUri);
        }
        if (targetUri == null || targetUri.isAbsolute()) {
            throw new IllegalArgumentException("targetUri invalid - " + targetUri);
        }
        return sourcePartUri.resolve(targetUri);
    }

    public static URI getURIFromPath(String path) {
        URI retUri;
        try {
            retUri = PackagingURIHelper.toURI(path);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("path");
        }
        return retUri;
    }

    public static URI getSourcePartUriFromRelationshipPartUri(URI relationshipPartUri) {
        if (relationshipPartUri == null) {
            throw new IllegalArgumentException("Must not be null");
        }
        if (!PackagingURIHelper.isRelationshipPartURI(relationshipPartUri)) {
            throw new IllegalArgumentException("Must be a relationship part");
        }
        if (relationshipPartUri.compareTo(PACKAGE_RELATIONSHIPS_ROOT_URI) == 0) {
            return PACKAGE_ROOT_URI;
        }
        String filename = relationshipPartUri.getPath();
        String filenameWithoutExtension = PackagingURIHelper.getFilenameWithoutExtension(relationshipPartUri);
        filename = filename.substring(0, filename.length() - filenameWithoutExtension.length() - RELATIONSHIP_PART_EXTENSION_NAME.length());
        filename = filename.substring(0, filename.length() - RELATIONSHIP_PART_SEGMENT_NAME.length() - 1);
        filename = PackagingURIHelper.combine(filename, filenameWithoutExtension);
        return PackagingURIHelper.getURIFromPath(filename);
    }

    public static PackagePartName createPartName(URI partUri) throws InvalidFormatException {
        if (partUri == null) {
            throw new IllegalArgumentException("partName");
        }
        return new PackagePartName(partUri, true);
    }

    public static PackagePartName createPartName(String partName) throws InvalidFormatException {
        URI partNameURI;
        try {
            partNameURI = PackagingURIHelper.toURI(partName);
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage());
        }
        return PackagingURIHelper.createPartName(partNameURI);
    }

    public static PackagePartName createPartName(String partName, PackagePart relativePart) throws InvalidFormatException {
        URI newPartNameURI;
        try {
            newPartNameURI = PackagingURIHelper.resolvePartUri(relativePart.getPartName().getURI(), new URI(partName));
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage());
        }
        return PackagingURIHelper.createPartName(newPartNameURI);
    }

    public static PackagePartName createPartName(URI partName, PackagePart relativePart) throws InvalidFormatException {
        URI newPartNameURI = PackagingURIHelper.resolvePartUri(relativePart.getPartName().getURI(), partName);
        return PackagingURIHelper.createPartName(newPartNameURI);
    }

    public static boolean isValidPartName(URI partUri) {
        if (partUri == null) {
            throw new IllegalArgumentException("partUri");
        }
        try {
            PackagingURIHelper.createPartName(partUri);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String decodeURI(URI uri) {
        StringBuilder retVal = new StringBuilder(64);
        String uriStr = uri.toASCIIString();
        int length = uriStr.length();
        for (int i = 0; i < length; ++i) {
            char c = uriStr.charAt(i);
            if (c == '%') {
                if (length - i < 2) {
                    throw new IllegalArgumentException("The uri " + uriStr + " contain invalid encoded character !");
                }
                char decodedChar = (char)Integer.parseInt(uriStr.substring(i + 1, i + 3), 16);
                retVal.append(decodedChar);
                i += 2;
                continue;
            }
            retVal.append(c);
        }
        return retVal.toString();
    }

    public static PackagePartName getRelationshipPartName(PackagePartName partName) {
        PackagePartName retPartName;
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (PACKAGE_ROOT_URI.getPath().equals(partName.getURI().getPath())) {
            return PACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
        }
        if (partName.isRelationshipPartURI()) {
            throw new InvalidOperationException("Can't be a relationship part");
        }
        String fullPath = partName.getURI().getPath();
        String filename = PackagingURIHelper.getFilename(partName.getURI());
        fullPath = fullPath.substring(0, fullPath.length() - filename.length());
        fullPath = PackagingURIHelper.combine(fullPath, RELATIONSHIP_PART_SEGMENT_NAME);
        fullPath = PackagingURIHelper.combine(fullPath, filename);
        fullPath = fullPath + RELATIONSHIP_PART_EXTENSION_NAME;
        try {
            retPartName = PackagingURIHelper.createPartName(fullPath);
        } catch (InvalidFormatException e) {
            return null;
        }
        return retPartName;
    }

    public static URI toURI(String value) throws URISyntaxException {
        int fragmentIdx;
        if (value.contains("\\")) {
            value = value.replace('\\', '/');
        }
        if ((fragmentIdx = value.indexOf(35)) != -1) {
            String path = value.substring(0, fragmentIdx);
            String fragment = value.substring(fragmentIdx + 1);
            value = path + "#" + PackagingURIHelper.encode(fragment);
        }
        if (value.length() > 0) {
            char c;
            int idx;
            StringBuilder b = new StringBuilder();
            for (idx = value.length() - 1; idx >= 0 && (Character.isWhitespace(c = value.charAt(idx)) || c == '\u00a0'); --idx) {
                b.append(c);
            }
            if (b.length() > 0) {
                value = value.substring(0, idx + 1) + PackagingURIHelper.encode(b.reverse().toString());
            }
        }
        if (missingAuthPattern.matcher(value).matches()) {
            value = value + FORWARD_SLASH_STRING;
        }
        return new URI(value);
    }

    public static String encode(String s) {
        int n = s.length();
        if (n == 0) {
            return s;
        }
        ByteBuffer bb = ByteBuffer.wrap(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        while (bb.hasRemaining()) {
            int b = bb.get() & 0xFF;
            if (PackagingURIHelper.isUnsafe(b)) {
                sb.append('%');
                sb.append(hexDigits[b >> 4 & 0xF]);
                sb.append(hexDigits[b >> 0 & 0xF]);
                continue;
            }
            sb.append((char)b);
        }
        return sb.toString();
    }

    private static boolean isUnsafe(int ch) {
        return ch >= 128 || ch == 124 || Character.isWhitespace(ch);
    }

    static {
        URI uriPACKAGE_ROOT_URI = null;
        URI uriPACKAGE_RELATIONSHIPS_ROOT_URI = null;
        URI uriPACKAGE_PROPERTIES_URI = null;
        try {
            uriPACKAGE_ROOT_URI = new URI(FORWARD_SLASH_STRING);
            uriPACKAGE_RELATIONSHIPS_ROOT_URI = new URI("/_rels/.rels");
            packageRootUri = new URI(FORWARD_SLASH_STRING);
            uriPACKAGE_PROPERTIES_URI = new URI("/docProps/core.xml");
        } catch (URISyntaxException uRISyntaxException) {
            // empty catch block
        }
        PACKAGE_ROOT_URI = uriPACKAGE_ROOT_URI;
        PACKAGE_RELATIONSHIPS_ROOT_URI = uriPACKAGE_RELATIONSHIPS_ROOT_URI;
        CORE_PROPERTIES_URI = uriPACKAGE_PROPERTIES_URI;
        PackagePartName tmpPACKAGE_ROOT_PART_NAME = null;
        PackagePartName tmpPACKAGE_RELATIONSHIPS_ROOT_PART_NAME = null;
        PackagePartName tmpCORE_PROPERTIES_URI = null;
        try {
            tmpPACKAGE_RELATIONSHIPS_ROOT_PART_NAME = PackagingURIHelper.createPartName(PACKAGE_RELATIONSHIPS_ROOT_URI);
            tmpCORE_PROPERTIES_URI = PackagingURIHelper.createPartName(CORE_PROPERTIES_URI);
            tmpPACKAGE_ROOT_PART_NAME = new PackagePartName(PACKAGE_ROOT_URI, false);
        } catch (InvalidFormatException invalidFormatException) {
            // empty catch block
        }
        PACKAGE_RELATIONSHIPS_ROOT_PART_NAME = tmpPACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
        CORE_PROPERTIES_PART_NAME = tmpCORE_PROPERTIES_URI;
        PACKAGE_ROOT_PART_NAME = tmpPACKAGE_ROOT_PART_NAME;
        missingAuthPattern = Pattern.compile("\\w+://");
        hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }
}

