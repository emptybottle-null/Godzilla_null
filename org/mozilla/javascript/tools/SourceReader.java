/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.commonjs.module.provider.ParsedContentType;

public class SourceReader {
    public static URL toUrl(String path) {
        if (path.indexOf(58) >= 2) {
            try {
                return new URL(path);
            } catch (MalformedURLException malformedURLException) {
                // empty catch block
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Object readFileOrUrl(String path, boolean convertToString, String defaultEncoding) throws IOException {
        Object result;
        byte[] data;
        String contentType;
        String encoding;
        URL url = SourceReader.toUrl(path);
        InputStream is = null;
        int capacityHint = 0;
        try {
            if (url == null) {
                File file = new File(path);
                encoding = null;
                contentType = null;
                capacityHint = (int)file.length();
                is = new FileInputStream(file);
            } else {
                URLConnection uc = url.openConnection();
                is = uc.getInputStream();
                if (convertToString) {
                    ParsedContentType pct = new ParsedContentType(uc.getContentType());
                    contentType = pct.getContentType();
                    encoding = pct.getEncoding();
                } else {
                    encoding = null;
                    contentType = null;
                }
                capacityHint = uc.getContentLength();
                if (capacityHint > 0x100000) {
                    capacityHint = -1;
                }
            }
            if (capacityHint <= 0) {
                capacityHint = 4096;
            }
            data = Kit.readStream(is, capacityHint);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        if (!convertToString) {
            result = data;
        } else {
            String strResult;
            if (encoding == null) {
                if (data.length > 3 && data[0] == -1 && data[1] == -2 && data[2] == 0 && data[3] == 0) {
                    encoding = "UTF-32LE";
                } else if (data.length > 3 && data[0] == 0 && data[1] == 0 && data[2] == -2 && data[3] == -1) {
                    encoding = "UTF-32BE";
                } else if (data.length > 2 && data[0] == -17 && data[1] == -69 && data[2] == -65) {
                    encoding = "UTF-8";
                } else if (data.length > 1 && data[0] == -1 && data[1] == -2) {
                    encoding = "UTF-16LE";
                } else if (data.length > 1 && data[0] == -2 && data[1] == -1) {
                    encoding = "UTF-16BE";
                } else {
                    encoding = defaultEncoding;
                    if (encoding == null) {
                        encoding = url == null ? System.getProperty("file.encoding") : (contentType != null && contentType.startsWith("application/") ? "UTF-8" : "US-ASCII");
                    }
                }
            }
            if ((strResult = new String(data, encoding)).length() > 0 && strResult.charAt(0) == '\ufeff') {
                strResult = strResult.substring(1);
            }
            result = strResult;
        }
        return result;
    }
}

