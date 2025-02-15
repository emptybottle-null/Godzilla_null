/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.httpProxy.server.request;

import com.httpProxy.server.ByteUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class HttpRequestParameter {
    private static String defaultBoundary = "--";
    private static byte[] crlf = "\r\n".getBytes();
    private boolean isMultipart = false;
    private String boundary;
    private HashMap<String, byte[]> parameter = new HashMap();

    public HttpRequestParameter() {
    }

    public HttpRequestParameter(boolean isMultipart, String boundary) {
        this.isMultipart = isMultipart;
        this.boundary = boundary;
    }

    public HttpRequestParameter decode(byte[] data) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        return this.decode(byteArrayInputStream);
    }

    public HttpRequestParameter decode(InputStream inputStream) {
        if (this.isMultipart) {
            this.decodeByMultipart(inputStream);
        } else {
            this.decodeByWwwForm(inputStream);
        }
        return this;
    }

    public HttpRequestParameter decodeByUrl(String url) {
        int index = url.indexOf("?");
        int splitIndex = 0;
        if (index != -1) {
            StringBuilder stringBuilder = new StringBuilder(url.substring(index + 1, url.length()));
            while ((index = stringBuilder.indexOf("=")) != -1) {
                splitIndex = stringBuilder.indexOf("&");
                splitIndex = splitIndex == -1 ? stringBuilder.length() : splitIndex;
                String name = stringBuilder.substring(0, index);
                String value = stringBuilder.substring(index + 1, splitIndex);
                stringBuilder.delete(0, splitIndex + 1);
                this.parameter.put(name, value.getBytes());
            }
        }
        return this;
    }

    public byte[] encode() {
        if (this.isMultipart) {
            return this.encodeByMultipart();
        }
        return this.encodeByWwwForm();
    }

    protected byte[] encodeByWwwForm() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Iterator<String> iterator = this.parameter.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            byte[] value = this.parameter.get(key);
            byte[] keyBytes = key.getBytes();
            byteArrayOutputStream.write(keyBytes, 0, keyBytes.length);
            byteArrayOutputStream.write(61);
            byteArrayOutputStream.write(value, 0, value.length);
            if (!iterator.hasNext()) continue;
            byteArrayOutputStream.write(38);
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] encodeByMultipart() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] lineBoundary = (defaultBoundary + this.boundary + "\r\n").getBytes();
        byte[] lineBoundaryEnd = (defaultBoundary + this.boundary + defaultBoundary + "\r\n").getBytes();
        String nameFormat = "Content-Disposition: form-data; name=\"%s\"\r\n";
        Iterator<String> iterator = this.parameter.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            byte[] value = this.parameter.get(key);
            byte[] keyBytes = String.format(nameFormat, key).getBytes();
            byteArrayOutputStream.write(lineBoundary, 0, lineBoundary.length);
            byteArrayOutputStream.write(keyBytes, 0, keyBytes.length);
            byteArrayOutputStream.write(crlf, 0, crlf.length);
            byteArrayOutputStream.write(value, 0, value.length);
            byteArrayOutputStream.write(crlf, 0, crlf.length);
            if (iterator.hasNext()) continue;
            byteArrayOutputStream.write(lineBoundaryEnd, 0, lineBoundaryEnd.length);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public HttpRequestParameter add(String name, byte[] value) {
        this.parameter.put(name, value);
        return this;
    }

    public HttpRequestParameter add(String name, String value) {
        return this.add(name, value.getBytes());
    }

    public HttpRequestParameter add(String name, Object value) {
        return this.add(name, value.toString());
    }

    protected void decodeByWwwForm(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder(new String(ByteUtil.readNextLine(inputStream)));
        int index = 0;
        int splitIndex = 0;
        while ((index = stringBuilder.indexOf("=")) != -1) {
            splitIndex = stringBuilder.indexOf("&");
            splitIndex = splitIndex == -1 ? stringBuilder.length() : splitIndex;
            String name = stringBuilder.substring(0, index);
            String value = stringBuilder.substring(index + 1, splitIndex);
            stringBuilder.delete(0, splitIndex + 1);
            this.parameter.put(name, value.getBytes());
        }
    }

    protected void decodeByMultipart(InputStream inputStream) {
        byte[] line = null;
        byte[] lineBoundary = (defaultBoundary + this.boundary + "\r\n").getBytes();
        byte[] lineBoundaryEnd = (defaultBoundary + this.boundary + defaultBoundary + "\r\n").getBytes();
        boolean hasNextLine = false;
        String lineString = null;
        int index = -1;
        block0: while (hasNextLine || Arrays.equals(line = ByteUtil.readNextLine(inputStream, true), lineBoundary)) {
            String name = null;
            ByteArrayOutputStream value = new ByteArrayOutputStream();
            hasNextLine = false;
            line = ByteUtil.readNextLine(inputStream);
            lineString = new String(line);
            index = lineString.indexOf("name=\"") + "name=\"".length();
            if (index == -1) break;
            name = lineString.substring(index, lineString.indexOf("\"", index));
            while (!Arrays.equals(line = ByteUtil.readNextLine(inputStream, true), crlf)) {
            }
            byte[] lastLine = null;
            while (true) {
                int lineSize;
                if ((lineSize = (line = ByteUtil.readNextLine(inputStream, true)).length) == lineBoundary.length && Arrays.equals(line, lineBoundary)) {
                    if (lastLine != null) {
                        value.write(lastLine, 0, lastLine.length - 2);
                        lastLine = null;
                    }
                    this.parameter.put(name, value.toByteArray());
                    hasNextLine = true;
                    continue block0;
                }
                if (lineSize == lineBoundaryEnd.length && Arrays.equals(line, lineBoundaryEnd)) {
                    if (lastLine != null) {
                        value.write(lastLine, 0, lastLine.length - 2);
                        lastLine = null;
                    }
                    this.parameter.put(name, value.toByteArray());
                    hasNextLine = false;
                    continue block0;
                }
                if (lastLine != null) {
                    value.write(lastLine, 0, lastLine.length);
                    lastLine = null;
                }
                lastLine = line;
            }
        }
    }

    public static String getDefaultBoundary() {
        return defaultBoundary;
    }

    public static void setDefaultBoundary(String defaultBoundary) {
        HttpRequestParameter.defaultBoundary = defaultBoundary;
    }

    public boolean isMultipart() {
        return this.isMultipart;
    }

    public void setMultipart(boolean multipart) {
        this.isMultipart = multipart;
    }

    public String getBoundary() {
        return this.boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public HashMap<String, byte[]> getParameter() {
        return this.parameter;
    }

    public void setParameter(HashMap<String, byte[]> parameter) {
        this.parameter = parameter;
    }

    public String toString() {
        return "HttpRequestParameter{parameter=" + this.parameter + '}';
    }
}

