/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.httpProxy.server.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HttpRequestHeader {
    ArrayList<String[]> headers = new ArrayList();

    public HttpRequestHeader addHeader(String name, String value) {
        String[] header = new String[2];
        header[0] = name == null ? "" : name.trim();
        String string = header[1] = value == null ? "" : value.trim();
        if (name != null) {
            this.headers.add(header);
        }
        return this;
    }

    public HttpRequestHeader setHeader(String name, String value) {
        Iterator<String[]> headers = this.getHeaders().iterator();
        String[] kv = null;
        while (headers.hasNext() && !(kv = headers.next())[0].equals(name)) {
            kv = null;
        }
        if (kv != null) {
            this.getHeaders().remove(kv);
        }
        this.addHeader(name, value);
        return this;
    }

    public String getHeader(String key) {
        Iterator<String[]> headers = this.getHeaders().iterator();
        String[] kv = null;
        while (headers.hasNext() && !(kv = headers.next())[0].equals(key)) {
            kv = null;
        }
        if (kv != null) {
            return kv[1];
        }
        return null;
    }

    public HttpRequestHeader removeHeader(String name) {
        ArrayList<String[]> removeList = new ArrayList<String[]>();
        Iterator<String[]> headers = this.getHeaders().iterator();
        String[] kv = null;
        while (headers.hasNext()) {
            kv = headers.next();
            if (kv[0].equals(name)) {
                removeList.add(kv);
            }
            kv = null;
        }
        for (int i = 0; i < removeList.size(); ++i) {
            String[] s = (String[])removeList.get(i);
            this.getHeaders().remove(s);
        }
        return this;
    }

    public HttpRequestHeader setContentType(String value) {
        return this.setHeader("Content-Type", value);
    }

    public String decode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] ex : this.headers) {
            stringBuilder.append(ex[0]);
            stringBuilder.append(": ");
            stringBuilder.append(ex[1]);
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

    public List<String[]> getHeaders() {
        return this.headers;
    }

    public String toString() {
        return "HttpResponseHeader{headers=" + this.headers + '}';
    }
}

