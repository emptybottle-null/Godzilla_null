/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import util.functions;

public class Parameter
implements Serializable {
    public static final byte NEXT_PARAMETER = 3;
    public static final byte NEXT_VALUE = 2;
    protected HashMap<String, byte[]> hashMap = new HashMap();

    public String getParameterString(String key) {
        byte[] retByteArray = this.getParameterByteArray(key);
        if (retByteArray != null) {
            return new String(retByteArray);
        }
        return null;
    }

    public byte[] getParameterByteArray(String key) {
        byte[] retByteArray = this.hashMap.get(key);
        return retByteArray;
    }

    public Parameter addParameterString(String key, String value) {
        this.addParameterByteArray(key, value.getBytes());
        return this;
    }

    public synchronized Parameter addParameterByteArray(String key, byte[] value) {
        this.hashMap.put(key, value);
        return this;
    }

    public byte[] remove(String key) {
        byte[] ret = this.hashMap.remove(key);
        return ret;
    }

    public byte[] get(String key) {
        return this.getParameterByteArray(key);
    }

    public Parameter add(String key, String value) {
        this.addParameterString(key, value);
        return this;
    }

    public Parameter add(String key, byte[] value) {
        this.addParameterByteArray(key, value);
        return this;
    }

    public long getSize() {
        return this.hashMap.size();
    }

    public int len() {
        AtomicInteger len = new AtomicInteger();
        this.hashMap.forEach((k, v) -> {
            len.addAndGet(k.length());
            len.addAndGet(1);
            len.addAndGet(4);
            len.addAndGet(((byte[])v).length);
        });
        return len.get();
    }

    public static Parameter unSerialize(byte[] parameterByte) {
        return Parameter.unSerialize(new ByteArrayInputStream(parameterByte));
    }

    public static Parameter unSerialize(InputStream inputStream) {
        Parameter resParameter = new Parameter();
        ByteArrayOutputStream stringBuffer = new ByteArrayOutputStream();
        String key = null;
        byte[] lenBytes = new byte[4];
        byte[] data = null;
        try {
            byte tmpByte;
            while ((tmpByte = (byte)inputStream.read()) != -1) {
                if (tmpByte == 2) {
                    key = stringBuffer.toString();
                    inputStream.read(lenBytes);
                    int len = functions.bytesToInt(lenBytes);
                    data = new byte[len];
                    int readOneLen = 0;
                    while ((readOneLen += inputStream.read(data, readOneLen, data.length - readOneLen)) < data.length) {
                    }
                    resParameter.addParameterByteArray(key, data);
                    stringBuffer.reset();
                    continue;
                }
                if (tmpByte <= 32 || tmpByte > 126) break;
                stringBuffer.write(tmpByte);
            }
            stringBuffer.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resParameter.hashMap.size() > 0) {
            return resParameter;
        }
        return null;
    }

    public byte[] serialize() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(this.len());
        this.serialize(outputStream);
        return outputStream.toByteArray();
    }

    public void serialize(ByteArrayOutputStream outputStream) {
        Iterator<String> keys = this.hashMap.keySet().iterator();
        byte[] value = null;
        while (keys.hasNext()) {
            try {
                String key = keys.next();
                value = this.hashMap.get(key);
                outputStream.write(key.getBytes());
                outputStream.write(2);
                outputStream.write(functions.intToBytes(value.length));
                outputStream.write(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

