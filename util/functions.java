/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import core.ApplicationContext;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JComboBox;
import util.Log;
import util.http.Http;

public class functions {
    private static final char[] toBase64 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final char[] toBase64URL = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
    private static final double TOOLSKIT_WIDTH = 1920.0;
    private static final double TOOLSKIT_HEIGHT = 1080.0;
    private static double CURRENT_WIDTH = 1920.0;
    private static double CURRENT_HEIGHT = 1080.0;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getNetworSpeedk(long size) {
        if (size < 1024L) {
            return size + "B";
        }
        if ((size /= 1024L) < 1024L) {
            return size + "KB";
        }
        if ((size /= 1024L) < 1024L) {
            return (size *= 100L) / 100L + "." + size % 100L + "MB";
        }
        size = size * 100L / 1024L;
        return size / 100L + "." + size % 100L + "GB";
    }

    public static void concatMap(Map<String, List<String>> receiveMap, Map<String, List<String>> map) {
        Iterator<String> iterator = map.keySet().iterator();
        String key = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            receiveMap.put(key, map.get(key));
        }
    }

    public static boolean isMatch(String s, String p, boolean us) {
        return us ? functions.isMatch(s, p) : functions.isMatch(s.toLowerCase(), p.toLowerCase());
    }

    public static String SHA(byte[] data, String strType) {
        String strResult = null;
        if (data != null && data.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(data);
                byte[] byteBuffer = messageDigest.digest();
                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; ++i) {
                    String hex = Integer.toHexString(0xFF & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException var8) {
                var8.printStackTrace();
            }
        }
        return strResult;
    }

    public static boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
                continue;
            }
            if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j++;
                iIndex = i;
                continue;
            }
            if (starIndex == -1) {
                return false;
            }
            j = starIndex + 1;
            i = iIndex + 1;
            ++iIndex;
        }
        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }
        return j == p.length();
    }

    public static void setWindowSize(Window window, int width, int height) {
        window.setSize((int)((double)width / 1920.0 * CURRENT_WIDTH), (int)((double)height / 1080.0 * CURRENT_HEIGHT));
    }

    public static byte[] HMACSHA256(byte[] data, byte[] key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data);
        return array;
    }

    public static void fireActionEventByJComboBox(JComboBox comboBox) {
        try {
            comboBox.setSelectedIndex(0);
        } catch (Exception var2) {
            Log.error(var2);
        }
    }

    public static String readCString(ByteBuffer buff) {
        byte c;
        StringBuilder stringBuilder = new StringBuilder();
        while ((c = buff.get()) != 0) {
            stringBuilder.append((char)c);
        }
        return stringBuilder.toString();
    }

    public static byte[] ipToByteArray(String paramString) {
        String[] array2 = paramString.split("\\.");
        byte[] array = new byte[4];
        for (int i = 0; i < array2.length; ++i) {
            array[i] = (byte)Integer.parseInt(array2[i]);
        }
        return array;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static byte[] shortToByteArray(short s) {
        byte[] targets = new byte[2];
        for (int i = 0; i < 2; ++i) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte)(s >>> offset & 0xFF);
        }
        return targets;
    }

    public static int random(int a, int b) {
        int temp = 0;
        if (b >= 1 && a <= b) {
            if (a == b) {
                return a;
            }
            try {
                if (a > b) {
                    temp = new Random().nextInt(a - b);
                    return temp + b;
                }
                temp = new Random().nextInt(b - a);
                return temp + a;
            } catch (Exception var4) {
                Log.error(var4);
                return temp + a;
            }
        }
        return 0;
    }

    public static String endTrim(String value) {
        int i;
        int b = 0;
        char[] arrayOfChar = value.toCharArray();
        for (i = value.length(); b < i && arrayOfChar[i - 1] <= ' '; --i) {
        }
        return b <= 0 && i >= arrayOfChar.length ? value : value.substring(b, i);
    }

    public static String startTrim(String value) {
        int b;
        int i = value.length();
        char[] arrayOfChar = value.toCharArray();
        for (b = 0; b < i && arrayOfChar[b] <= ' '; b = (int)((byte)(b + 1))) {
        }
        return b <= 0 && i >= arrayOfChar.length ? value : value.substring(b, i);
    }

    public static byte[] intToBytes(int value) {
        byte[] src = new byte[]{(byte)(value & 0xFF), (byte)(value >> 8 & 0xFF), (byte)(value >> 16 & 0xFF), (byte)(value >> 24 & 0xFF)};
        return src;
    }

    public static String getJarFileByClass(Class cs) {
        String tmpString;
        String fileString = null;
        if (cs != null && (tmpString = cs.getProtectionDomain().getCodeSource().getLocation().getFile()).endsWith(".jar")) {
            try {
                fileString = URLDecoder.decode(tmpString, "utf-8");
            } catch (UnsupportedEncodingException var4) {
                Log.error(var4);
                fileString = URLDecoder.decode(tmpString);
            }
        }
        return fileString;
    }

    public static String byteArrayToHexPrefix(byte[] bytes, String prefix) {
        String strHex = "";
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < bytes.length; ++n) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append(prefix);
            sb.append(strHex.length() == 1 ? "0" + strHex : strHex);
        }
        return sb.toString().trim();
    }

    public static String byteArrayToHex(byte[] bytes) {
        return functions.byteArrayToHexPrefix(bytes, "");
    }

    public static byte[] hexToByte(String hex) {
        int m = 0;
        int n = 0;
        int byteLen = hex.length() / 2;
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; ++i) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = (byte)intVal;
        }
        return ret;
    }

    public static boolean isGzipStream(byte[] data) {
        if (data != null && data.length >= 2) {
            int ss = data[0] & 0xFF | (data[1] & 0xFF) << 8;
            return ss == 35615;
        }
        return false;
    }

    public static Class loadClass(ClassLoader loader, String className) {
        try {
            return loader.loadClass(className);
        } catch (Exception var3) {
            return null;
        }
    }

    public static boolean appendFile(File file, byte[] content) {
        try {
            boolean var4;
            try (FileOutputStream fileOutputStream = new FileOutputStream(file, true);){
                fileOutputStream.write(content);
                var4 = true;
            }
            return var4;
        } catch (Throwable var16) {
            var16.printStackTrace();
            return false;
        }
    }

    public static String readFileBottomLine(File file, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));){
            ArrayList<Object> arrayList = new ArrayList<Object>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
            if (arrayList.size() > number) {
                arrayList.subList(arrayList.size() - 1 - number, arrayList.size()).forEach(v -> {
                    stringBuilder.append(v);
                    stringBuilder.append('\n');
                });
            } else {
                arrayList.forEach(v -> {
                    stringBuilder.append(v);
                    stringBuilder.append('\n');
                });
            }
        } catch (Exception exception) {
            // empty catch block
        }
        return stringBuilder.toString();
    }

    public static Object concatArrays(Object array1, int array1_Start, int array1_End, Object array2, int array2_Start, int array2_End) {
        if (array1.getClass().isArray() && array2.getClass().isArray()) {
            if (array1_Start >= 0 && array1_Start >= 0 && array2_End >= 0 && array2_Start >= 0) {
                int array1len = array1_Start != array1_End ? array1_End - array1_Start + 1 : 0;
                int array2len = array2_Start != array2_End ? array2_End - array2_Start + 1 : 0;
                int maxLen = array1len + array2len;
                byte[] data = new byte[maxLen];
                System.arraycopy(array1, array1_Start, data, 0, array1len);
                System.arraycopy(array2, array2_Start, data, array1len, array2len);
                return data;
            }
            return null;
        }
        return null;
    }

    public static boolean delFiles(File file) {
        boolean result = false;
        try {
            if (file.isDirectory()) {
                File[] childrenFiles;
                File[] var3 = childrenFiles = file.listFiles();
                int var4 = childrenFiles.length;
                for (int var5 = 0; var5 < var4; ++var5) {
                    File childFile = var3[var5];
                    result = functions.delFiles(childFile);
                    if (result) continue;
                    return result;
                }
            }
            result = file.delete();
        } catch (Exception var7) {
            var7.printStackTrace();
        }
        return result;
    }

    public static void addShutdownHook(final Class<?> cls, final Object object) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    cls.getMethod("Tclose", null).invoke(object, null);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }
        }));
    }

    public static short bytesToShort(byte[] bytes) {
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public static int stringToint(String intString) {
        return functions.stringToint(intString, 0);
    }

    public static int stringToint(String intString, int defaultValue) {
        try {
            return Integer.parseInt(intString.trim());
        } catch (Exception var3) {
            return defaultValue;
        }
    }

    public static Long stringToLong(String intString, long defaultValue) {
        try {
            return Long.parseLong(intString.trim());
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    public static byte[] readInputStream(InputStream inputStream) {
        byte[] temp = new byte[5120];
        int readOneNum = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((readOneNum = inputStream.read(temp)) != -1) {
                bos.write(temp, 0, readOneNum);
            }
        } catch (Exception e) {
            Log.error(e);
        }
        return bos.toByteArray();
    }

    public static HashMap<String, String> matcherTwoChild(String data, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(data);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        while (m.find()) {
            try {
                String v1 = m.group(1);
                String v2 = m.group(2);
                hashMap.put(v1, v2);
            } catch (Exception var8) {
                Log.error(var8);
            }
        }
        return hashMap;
    }

    public static short[] toShortArray(byte[] src) {
        int count = src.length >> 1;
        short[] dest = new short[count];
        for (int i = 0; i < count; ++i) {
            dest[i] = (short)(src[i * 2] << 8 | src[2 * i + 1] & 0xFF);
        }
        return dest;
    }

    public static byte[] stringToByteArray(String data, String encodng) {
        try {
            return data.getBytes(encodng);
        } catch (Exception var3) {
            return data.getBytes();
        }
    }

    public static String formatDir(String dirString) {
        if (dirString != null && dirString.length() > 0) {
            dirString = dirString.trim();
            if (!(dirString = dirString.replaceAll("\\\\+", "/").replaceAll("/+", "/").trim()).substring(dirString.length() - 1).equals("/")) {
                dirString = dirString + "/";
            }
            return dirString;
        }
        return "";
    }

    public static boolean filePutContent(String file, byte[] data) {
        return functions.filePutContent(new File(file), data);
    }

    public static boolean filePutContent(File file, byte[] data) {
        boolean state = false;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
            state = true;
        } catch (Exception var4) {
            Log.error(var4);
            state = false;
        }
        return state;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append(str.charAt(random.nextInt(52)));
        str = str + "0123456789";
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String concatCookie(String oldCookie, String newCookie) {
        String[] temB;
        int i;
        oldCookie = oldCookie + ";";
        newCookie = newCookie + ";";
        StringBuffer cookieBuffer = new StringBuffer();
        HashMap<String, String> cookieMap = new HashMap<String, String>();
        String[] tmpA = oldCookie.split(";");
        for (i = 0; i < tmpA.length; ++i) {
            temB = tmpA[i].split("=");
            cookieMap.put(temB[0], temB[1]);
        }
        tmpA = newCookie.split(";");
        for (i = 0; i < tmpA.length; ++i) {
            temB = tmpA[i].split("=");
            cookieMap.put(temB[0], temB[1]);
        }
        for (String keyString : cookieMap.keySet()) {
            cookieBuffer.append(keyString);
            cookieBuffer.append("=");
            cookieBuffer.append((String)cookieMap.get(keyString));
            cookieBuffer.append(";");
        }
        return cookieBuffer.toString();
    }

    public static Method getMethodByClass(Class cs, String methodName, Class ... parameters) {
        Method method = null;
        while (cs != null) {
            try {
                method = cs.getDeclaredMethod(methodName, parameters);
                method.setAccessible(true);
                cs = null;
            } catch (Exception var5) {
                cs = cs.getSuperclass();
            }
        }
        return method;
    }

    public static Object getFieldValue(Object obj, String fieldName) throws Exception {
        Field f = null;
        if (obj instanceof Field) {
            f = (Field)obj;
        } else {
            Object method = null;
            Class<?> cs = obj.getClass();
            while (cs != null) {
                try {
                    f = cs.getDeclaredField(fieldName);
                    cs = null;
                } catch (Exception var6) {
                    cs = cs.getSuperclass();
                }
            }
        }
        f.setAccessible(true);
        return f.get(obj);
    }

    public static Object invoke(Object obj, String methodName, Object ... parameters) {
        try {
            ArrayList classes = new ArrayList();
            if (parameters != null) {
                for (int i = 0; i < parameters.length; ++i) {
                    Object o1 = parameters[i];
                    if (o1 != null) {
                        classes.add(o1.getClass());
                        continue;
                    }
                    classes.add(null);
                }
            }
            Method method = functions.getMethodByClass(obj.getClass(), methodName, classes.toArray(new Class[0]));
            return method.invoke(obj, parameters);
        } catch (Exception var6) {
            return null;
        }
    }

    public static String md5(String s) {
        return functions.byteArrayToHex(functions.md5(s.getBytes()));
    }

    public static byte[] readInputStreamAutoClose(InputStream inputStream) {
        byte[] ret = new byte[]{};
        try {
            ret = functions.readInputStream(inputStream);
            inputStream.close();
            return ret;
        } catch (IOException var3) {
            Log.error(var3);
            throw new RuntimeException(var3);
        }
    }

    public static byte[] md5(byte[] data) {
        byte[] ret = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(data, 0, data.length);
            ret = m.digest();
        } catch (NoSuchAlgorithmException var3) {
            Log.error(var3);
        }
        return ret;
    }

    public static String getCurrentTime() {
        return DATE_FORMAT.format(new Date());
    }

    public static byte[] base64Encode(byte[] src) {
        int b1;
        int b0;
        int off = 0;
        int end = src.length;
        byte[] dst = new byte[4 * ((src.length + 2) / 3)];
        int linemax = -1;
        boolean doPadding = true;
        char[] base64 = toBase64;
        int sp = off;
        int slen = (end - off) / 3 * 3;
        int sl = off + slen;
        if (linemax > 0 && slen > linemax / 4 * 3) {
            slen = linemax / 4 * 3;
        }
        int dp = 0;
        while (sp < sl) {
            b0 = Math.min(sp + slen, sl);
            b1 = sp;
            int dp0 = dp;
            while (b1 < b0) {
                int bits = (src[b1++] & 0xFF) << 16 | (src[b1++] & 0xFF) << 8 | src[b1++] & 0xFF;
                dst[dp0++] = (byte)base64[bits >>> 18 & 0x3F];
                dst[dp0++] = (byte)base64[bits >>> 12 & 0x3F];
                dst[dp0++] = (byte)base64[bits >>> 6 & 0x3F];
                dst[dp0++] = (byte)base64[bits & 0x3F];
            }
            b1 = (b0 - sp) / 3 * 4;
            dp += b1;
            sp = b0;
        }
        if (sp < end) {
            b0 = src[sp++] & 0xFF;
            dst[dp++] = (byte)base64[b0 >> 2];
            if (sp == end) {
                dst[dp++] = (byte)base64[b0 << 4 & 0x3F];
                if (doPadding) {
                    dst[dp++] = 61;
                    dst[dp++] = 61;
                }
            } else {
                b1 = src[sp++] & 0xFF;
                dst[dp++] = (byte)base64[b0 << 4 & 0x3F | b1 >> 4];
                dst[dp++] = (byte)base64[b1 << 2 & 0x3F];
                if (doPadding) {
                    dst[dp++] = 61;
                }
            }
        }
        return dst;
    }

    public static String base64EncodeToString(byte[] bytes) {
        return new String(functions.base64Encode(bytes));
    }

    public static String base64DecodeToString(String base64Str) {
        return new String(functions.base64Decode(base64Str));
    }

    public static byte[] base64Decode(String base64Str) {
        if (base64Str != null && !base64Str.isEmpty()) {
            byte[] src = (base64Str = base64Str.replace("\r", "").replace("\n", "").replace("\\/", "/").replace("\\\\", "\\")).getBytes();
            if (src.length == 0) {
                return src;
            }
            int sp = 0;
            int sl = src.length;
            int paddings = 0;
            int len = sl - sp;
            if (src[sl - 1] == 61) {
                ++paddings;
                if (src[sl - 2] == 61) {
                    ++paddings;
                }
            }
            if (paddings == 0 && (len & 3) != 0) {
                paddings = 4 - (len & 3);
            }
            byte[] dst = new byte[3 * ((len + 3) / 4) - paddings];
            int[] base64 = new int[256];
            Arrays.fill(base64, -1);
            int dp = 0;
            while (dp < toBase64.length) {
                base64[functions.toBase64[dp]] = dp++;
            }
            base64[61] = -2;
            dp = 0;
            int bits = 0;
            int shiftto = 18;
            while (sp < sl) {
                int b = src[sp++] & 0xFF;
                if ((b = base64[b]) < 0 && b == -2) {
                    if ((shiftto != 6 || sp != sl && src[sp++] == 61) && shiftto != 18) break;
                    throw new IllegalArgumentException("Input byte array has wrong 4-byte ending unit");
                }
                bits |= b << shiftto;
                if ((shiftto -= 6) >= 0) continue;
                dst[dp++] = (byte)(bits >> 16);
                dst[dp++] = (byte)(bits >> 8);
                dst[dp++] = (byte)bits;
                shiftto = 18;
                bits = 0;
            }
            if (shiftto == 6) {
                dst[dp++] = (byte)(bits >> 16);
            } else if (shiftto == 0) {
                dst[dp++] = (byte)(bits >> 16);
                dst[dp++] = (byte)(bits >> 8);
            } else if (shiftto == 12) {
                throw new IllegalArgumentException("Last unit does not have enough valid bits");
            }
            if (dp != dst.length) {
                byte[] arrayOfByte = new byte[dp];
                System.arraycopy(dst, 0, arrayOfByte, 0, Math.min(dst.length, dp));
                dst = arrayOfByte;
            }
            return dst;
        }
        return new byte[0];
    }

    public static String subMiddleStr(String data, String leftStr, String rightStr) {
        int leftIndex = data.indexOf(leftStr);
        int rightIndex = data.indexOf(rightStr, leftIndex += leftStr.length());
        return leftIndex != -1 && rightIndex != -1 ? data.substring(leftIndex, rightIndex) : null;
    }

    public static byte[] getResourceAsByteArray(Class cl, String name) {
        InputStream inputStream = cl.getResourceAsStream(name);
        byte[] data = null;
        data = functions.readInputStream(inputStream);
        try {
            inputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
        return data;
    }

    public static byte[] getResourceAsByteArray(Object o, String name) {
        return functions.getResourceAsByteArray(o.getClass(), name);
    }

    public static boolean saveDataViewToCsv(Vector columnVector, Vector dataRows, String saveFile) {
        boolean state = false;
        try {
            Object valueObject;
            int i;
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            int columnNum = columnVector.size();
            int cob = 44;
            int newLine = 10;
            int rowNum = dataRows.size();
            new StringBuilder();
            for (i = 0; i < columnNum - 1; ++i) {
                valueObject = columnVector.get(i);
                fileOutputStream.write(functions.formatStringByCsv(valueObject.toString()).getBytes());
                fileOutputStream.write(cob);
            }
            valueObject = columnVector.get(columnNum - 1);
            fileOutputStream.write(functions.formatStringByCsv(valueObject.toString()).getBytes());
            fileOutputStream.write(newLine);
            for (i = 0; i < rowNum; ++i) {
                Vector row = (Vector)dataRows.get(i);
                for (int j = 0; j < columnNum - 1; ++j) {
                    valueObject = row.get(j);
                    fileOutputStream.write(functions.formatStringByCsv(String.valueOf(valueObject)).getBytes());
                    fileOutputStream.write(cob);
                }
                valueObject = row.get(columnNum - 1);
                fileOutputStream.write(functions.formatStringByCsv(String.valueOf(valueObject)).getBytes());
                fileOutputStream.write(newLine);
            }
            fileOutputStream.close();
            state = true;
        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return state;
    }

    public static String stringToUnicode(String unicode2) {
        char[] chars = unicode2.toCharArray();
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < chars.length; ++i) {
            builder.append("\\");
            int numU = random.nextInt(10) + 1;
            for (int j = 0; j < numU; ++j) {
                builder.append("u");
            }
            String hx = Integer.toString(chars[i], 16);
            if (hx.length() < 4) {
                builder.append("0000".substring(hx.length())).append(hx);
                continue;
            }
            builder.append(hx);
        }
        return builder.toString();
    }

    public static String unicodeToString(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder temBuilder = null;
        int index = 0;
        boolean isUn = false;
        char currentChar = '0';
        char nextChar = '0';
        char[] temChars = new char[4];
        String temStr = null;
        block0: while (index < chars.length) {
            currentChar = chars[index];
            ++index;
            if (currentChar == '\\') {
                temBuilder = new StringBuilder();
                temBuilder.append('\\');
                while (index + 1 < chars.length) {
                    nextChar = chars[index];
                    ++index;
                    if (nextChar == '\\') {
                        --index;
                        stringBuilder.append(temBuilder.toString());
                        continue block0;
                    }
                    temBuilder.append(nextChar);
                    if (nextChar == 'u') {
                        isUn = true;
                        continue;
                    }
                    if (isUn) {
                        if (index + 3 - 1 < chars.length) {
                            temChars[0] = nextChar;
                            temChars[1] = chars[index];
                            temChars[2] = chars[++index];
                            temChars[3] = chars[++index];
                            ++index;
                            temStr = new String(temChars);
                            temBuilder.append(temStr, 1, temChars.length);
                            for (int i = 0; i < temChars.length; ++i) {
                                char fixChar = temChars[i];
                                if (!(fixChar >= '0' && fixChar <= '9' || fixChar >= 'A' && fixChar <= 'F' || fixChar >= 'a' && fixChar <= 'f')) {
                                    isUn = false;
                                    break;
                                }
                                isUn = true;
                            }
                            if (isUn) {
                                stringBuilder.append((char)Integer.parseInt(new String(temChars), 16));
                                isUn = false;
                                continue;
                            }
                            stringBuilder.append(temBuilder.toString());
                            continue;
                        }
                        isUn = false;
                        stringBuilder.append(temBuilder.toString());
                        continue;
                    }
                    isUn = false;
                    stringBuilder.append(temBuilder.toString());
                }
                continue;
            }
            stringBuilder.append(currentChar);
        }
        return stringBuilder.toString();
    }

    public static boolean sleep(int time) {
        boolean state = false;
        try {
            Thread.sleep(time);
            state = true;
        } catch (InterruptedException var3) {
            Log.error(var3);
        }
        return state;
    }

    public static String toString(Object object) {
        return object == null ? "null" : object.toString();
    }

    public static String getLastFileName(String file) {
        String[] fs = functions.formatDir(file).split("/");
        return fs[fs.length - 1];
    }

    private static String formatStringByCsv(String string) {
        string = string.replace("\"", "\"\"");
        return "\"" + string + "\"";
    }

    public static int byteToInt2(byte[] b) {
        int mask = 255;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < b.length; ++i) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }

    public static int bytesToInt(byte[] bytes) {
        int i = bytes[0] & 0xFF | (bytes[1] & 0xFF) << 8 | (bytes[2] & 0xFF) << 16 | (bytes[3] & 0xFF) << 24;
        return i;
    }

    public static byte[] gzipE(byte[] data) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            gzipOutputStream.write(data);
            gzipOutputStream.close();
            return outputStream.toByteArray();
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static byte[] gzipD(byte[] data) {
        if (data.length == 0) {
            return data;
        }
        try {
            ByteArrayInputStream tStream = new ByteArrayInputStream(data);
            GZIPInputStream inputStream = new GZIPInputStream((InputStream)tStream, data.length);
            return functions.readInputStream(inputStream);
        } catch (Exception var3) {
            if (data.length < 200) {
                Log.error(new String(data));
            }
            throw new RuntimeException(var3);
        }
    }

    public static int randomInt(int max, int min) {
        return min + (int)(Math.random() * (double)(max - min + 1));
    }

    public static void openBrowseUrl(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create(url);
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }

    public static String joinCmdArgs(String[] commands) {
        StringBuilder cmd = new StringBuilder();
        boolean flag = false;
        String[] var3 = commands;
        int var4 = commands.length;
        for (int var5 = 0; var5 < var4; ++var5) {
            String s = var3[var5];
            if (flag) {
                cmd.append(' ');
            } else {
                flag = true;
            }
            if (s.indexOf(32) < 0 && s.indexOf(9) < 0) {
                cmd.append(s);
                continue;
            }
            if (s.charAt(0) != '\"') {
                cmd.append('\"').append(s);
                if (s.endsWith("\\")) {
                    cmd.append("\\");
                }
                cmd.append('\"');
                continue;
            }
            cmd.append(s);
        }
        return cmd.toString();
    }

    public static String[] SplitArgs(String input) {
        return functions.SplitArgs(input, Integer.MAX_VALUE, false);
    }

    public static String[] SplitArgs(String input, int maxParts, boolean removeAllEscapeSequences) {
        StringBuilder chars = new StringBuilder(input.trim());
        ArrayList<String> fragments = new ArrayList<String>();
        int parts = 0;
        int nextFragmentStart = 0;
        boolean inBounds = false;
        for (int i = 0; i < chars.length(); ++i) {
            char c = chars.charAt(i);
            if (c == '\\') {
                if (!removeAllEscapeSequences && (i + 1 >= chars.length() || !functions.isEscapeable(chars.charAt(i + 1)))) continue;
                chars.deleteCharAt(i);
                continue;
            }
            if (c != '\"' || !(inBounds ? i + 1 == chars.length() || functions.isSpace(chars.charAt(i + 1)) : i == nextFragmentStart)) {
                if (inBounds || !functions.isSpace(c)) continue;
                functions.AddFragment(fragments, chars, nextFragmentStart, i);
                nextFragmentStart = i + 1;
                if (++parts + 1 < maxParts) continue;
                break;
            }
            inBounds = !inBounds;
            chars.deleteCharAt(i);
            --i;
        }
        if (nextFragmentStart < chars.length()) {
            functions.AddFragment(fragments, chars, nextFragmentStart, -1);
        }
        return fragments.toArray(new String[0]);
    }

    private static boolean isSpace(char c) {
        return c == ' ' || c == '\t';
    }

    private static boolean isEscapeable(char c) {
        switch (c) {
            default: 
        }
        return false;
    }

    public static LinkedList<String> stringToIps(String str) {
        String[] strIps;
        LinkedList<String> ips = new LinkedList<String>();
        String[] array = strIps = str.split("\n");
        int length = strIps.length;
        for (int i = 0; i < length; ++i) {
            String ip2;
            String[] iph;
            String stringa = array[i];
            String string = stringa.trim();
            if (functions.isIPv4LiteralAddress(string)) {
                ips.add(string);
                continue;
            }
            if (string.lastIndexOf("-") != -1) {
                iph = string.split("-");
                if (!functions.isIPv4LiteralAddress(iph[0])) continue;
                ip2 = iph[0];
                String[] ipx = ip2.split("\\.");
                Integer start = Integer.parseInt(ipx[3]);
                Integer end = Integer.parseInt(iph[1]);
                while (start <= end) {
                    String ip = ipx[0] + "." + ipx[1] + "." + ipx[2] + "." + start.toString();
                    ips.add(ip);
                    start = start + 1;
                }
                continue;
            }
            if (string.lastIndexOf("/") != -1) {
                iph = string.split("/");
                if (functions.isIPv4LiteralAddress(iph[0])) {
                    Integer mask = Integer.parseInt(iph[1]);
                    if (mask > 32 || mask < 1) continue;
                    ips.addAll(functions.maskToIps(iph[0], mask));
                    continue;
                }
                try {
                    ip2 = InetAddress.getByName(iph[0]).getHostAddress();
                    Integer mask2 = Integer.parseInt(iph[1]);
                    if (mask2 > 32 || mask2 < 1) continue;
                    ips.addAll(functions.maskToIps(ip2, mask2));
                } catch (Exception var14) {
                    Log.error(var14);
                }
                continue;
            }
            if (string.equals("")) continue;
            ips.add(string);
        }
        return ips;
    }

    public static LinkedList<String> maskToIps(String ip, Integer m) {
        LinkedList<String> i = new LinkedList<String>();
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            int address = inetAddress.hashCode();
            Integer n = 32 - m;
            int startIp = address & -1 << n;
            int endIp = address | -1 >>> m;
            ++startIp;
            --endIp;
            while (startIp <= endIp) {
                byte[] startaddr = functions.getAddress(startIp);
                InetAddress from = InetAddress.getByAddress(startaddr);
                String fromIp = from.getHostAddress();
                i.add(fromIp);
                ++startIp;
            }
        } catch (Exception var11) {
            Log.error(var11);
        }
        return i;
    }

    public static byte[] getAddress(int intIp) {
        byte[] addr = new byte[]{(byte)(intIp >>> 24 & 0xFF), (byte)(intIp >>> 16 & 0xFF), (byte)(intIp >>> 8 & 0xFF), (byte)(intIp & 0xFF)};
        return addr;
    }

    public static LinkedList<Integer> stringToPorts(String str) {
        String[] ports = str.split(",");
        HashSet<Integer> portset = new HashSet<Integer>();
        String[] array = ports;
        int length = ports.length;
        for (int i = 0; i < length; ++i) {
            String stringa = array[i];
            String string = stringa.trim();
            if (string.lastIndexOf("-") != -1) {
                String[] strPorts = string.split("-");
                Integer startPort = Integer.parseInt(strPorts[0]);
                Integer endPort = Integer.parseInt(strPorts[1]);
                while (startPort <= endPort) {
                    if (startPort >= 0 && startPort <= 65535) {
                        portset.add(startPort);
                    }
                    startPort = startPort + 1;
                }
                continue;
            }
            try {
                Integer port = Integer.parseInt(string);
                if (port < 0 || port > 65535) continue;
                portset.add(port);
                continue;
            } catch (Exception exception) {
                // empty catch block
            }
        }
        LinkedList<Integer> portList = new LinkedList<Integer>(portset);
        return portList;
    }

    public static byte[] textToNumericFormatV4(String src) {
        byte[] res = new byte[4];
        long tmpValue = 0L;
        int currByte = 0;
        boolean newOctet = true;
        int len = src.length();
        if (len != 0 && len <= 15) {
            for (int i = 0; i < len; ++i) {
                char c = src.charAt(i);
                if (c == '.') {
                    if (newOctet || tmpValue < 0L || tmpValue > 255L || currByte == 3) {
                        return null;
                    }
                    res[currByte++] = (byte)(tmpValue & 0xFFL);
                    tmpValue = 0L;
                    newOctet = true;
                    continue;
                }
                int digit = Character.digit(c, 10);
                if (digit < 0) {
                    return null;
                }
                tmpValue *= 10L;
                tmpValue += (long)digit;
                newOctet = false;
            }
            if (!newOctet && tmpValue >= 0L && tmpValue < 1L << (4 - currByte) * 8) {
                switch (currByte) {
                    case 0: {
                        res[0] = (byte)(tmpValue >> 24 & 0xFFL);
                    }
                    case 1: {
                        res[1] = (byte)(tmpValue >> 16 & 0xFFL);
                    }
                    case 2: {
                        res[2] = (byte)(tmpValue >> 8 & 0xFFL);
                    }
                    case 3: {
                        res[3] = (byte)(tmpValue >> 0 & 0xFFL);
                    }
                }
                return res;
            }
            return null;
        }
        return null;
    }

    public static boolean isIPv4LiteralAddress(String src) {
        return functions.textToNumericFormatV4(src) != null;
    }

    private static void AddFragment(List<String> fragments, StringBuilder chars, int start, int end) {
        if (end > start || end < 0) {
            if (end < 0) {
                end = chars.length();
            }
            String fragment = chars.substring(start, end);
            fragments.add(fragment);
        }
    }

    public static void dup2(InputStream inputStream, OutputStream outputStream) throws Exception {
        byte[] readData = new byte[5120];
        int readSize = -1;
        while ((readSize = inputStream.read(readData)) != -1) {
            outputStream.write(readData, 0, readSize);
            Thread.sleep(10L);
        }
    }

    public static String printStackTrace(Throwable e) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        e.printStackTrace(printStream);
        printStream.flush();
        printStream.close();
        return new String(stream.toByteArray());
    }

    public static File getCurrentJarFile() {
        String jarFileString = functions.getJarFileByClass(ApplicationContext.class);
        return jarFileString != null ? new File(jarFileString) : null;
    }

    public static byte[] httpReqest(String urlString, String method, HashMap<String, String> headers, byte[] data) {
        byte[] result = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setDoInput(true);
            httpConn.setDoOutput(!"GET".equals(method.toUpperCase()));
            httpConn.setConnectTimeout(3000);
            httpConn.setReadTimeout(3000);
            httpConn.setRequestMethod(method.toUpperCase());
            Http.addHttpHeader(httpConn, headers);
            if (httpConn.getDoOutput() && data != null) {
                httpConn.getOutputStream().write(data);
            }
            InputStream inputStream = httpConn.getInputStream();
            result = functions.readInputStream(inputStream);
        } catch (Exception var8) {
            Log.error(var8);
        }
        return result;
    }

    static {
        double _CURRENT_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        double _CURRENT_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        if (_CURRENT_HEIGHT > 1080.0 && _CURRENT_WIDTH > 1920.0) {
            CURRENT_WIDTH = _CURRENT_WIDTH;
            CURRENT_HEIGHT = _CURRENT_HEIGHT;
        }
    }
}

