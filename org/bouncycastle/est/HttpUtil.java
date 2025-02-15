/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class HttpUtil {
    HttpUtil() {
    }

    static String mergeCSL(String string, Map<String, String> map) {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(string);
        stringWriter.write(32);
        boolean bl = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!bl) {
                bl = true;
            } else {
                stringWriter.write(44);
            }
            stringWriter.write(entry.getKey());
            stringWriter.write("=\"");
            stringWriter.write(entry.getValue());
            stringWriter.write(34);
        }
        return stringWriter.toString();
    }

    static Map<String, String> splitCSL(String string, String string2) {
        if ((string2 = string2.trim()).startsWith(string)) {
            string2 = string2.substring(string.length());
        }
        return new PartLexer(string2).Parse();
    }

    public static String[] append(String[] stringArray, String string) {
        if (stringArray == null) {
            return new String[]{string};
        }
        int n = stringArray.length;
        String[] stringArray2 = new String[n + 1];
        System.arraycopy(stringArray, 0, stringArray2, 0, n);
        stringArray2[n] = string;
        return stringArray2;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class Headers
    extends HashMap<String, String[]> {
        public String getFirstValue(String string) {
            String[] stringArray = this.getValues(string);
            if (stringArray != null && stringArray.length > 0) {
                return stringArray[0];
            }
            return null;
        }

        public String[] getValues(String string) {
            if ((string = this.actualKey(string)) == null) {
                return null;
            }
            return (String[])this.get(string);
        }

        private String actualKey(String string) {
            if (this.containsKey(string)) {
                return string;
            }
            for (String string2 : this.keySet()) {
                if (!string.equalsIgnoreCase(string2)) continue;
                return string2;
            }
            return null;
        }

        private boolean hasHeader(String string) {
            return this.actualKey(string) != null;
        }

        public void set(String string, String string2) {
            this.put(string, new String[]{string2});
        }

        public void add(String string, String string2) {
            this.put(string, HttpUtil.append((String[])this.get(string), string2));
        }

        public void ensureHeader(String string, String string2) {
            if (!this.containsKey(string)) {
                this.set(string, string2);
            }
        }

        @Override
        public Object clone() {
            Headers headers = new Headers();
            for (Map.Entry entry : this.entrySet()) {
                headers.put((String)entry.getKey(), this.copy((String[])entry.getValue()));
            }
            return headers;
        }

        private String[] copy(String[] stringArray) {
            String[] stringArray2 = new String[stringArray.length];
            System.arraycopy(stringArray, 0, stringArray2, 0, stringArray2.length);
            return stringArray2;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class PartLexer {
        private final String src;
        int last = 0;
        int p = 0;

        PartLexer(String string) {
            this.src = string;
        }

        Map<String, String> Parse() {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            String string = null;
            String string2 = null;
            while (this.p < this.src.length()) {
                this.skipWhiteSpace();
                string = this.consumeAlpha();
                if (string.length() == 0) {
                    throw new IllegalArgumentException("Expecting alpha label.");
                }
                this.skipWhiteSpace();
                if (!this.consumeIf('=')) {
                    throw new IllegalArgumentException("Expecting assign: '='");
                }
                this.skipWhiteSpace();
                if (!this.consumeIf('\"')) {
                    throw new IllegalArgumentException("Expecting start quote: '\"'");
                }
                this.discard();
                string2 = this.consumeUntil('\"');
                this.discard(1);
                hashMap.put(string, string2);
                this.skipWhiteSpace();
                if (!this.consumeIf(',')) break;
                this.discard();
            }
            return hashMap;
        }

        private String consumeAlpha() {
            char c = this.src.charAt(this.p);
            while (this.p < this.src.length() && (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) {
                ++this.p;
                c = this.src.charAt(this.p);
            }
            String string = this.src.substring(this.last, this.p);
            this.last = this.p;
            return string;
        }

        private void skipWhiteSpace() {
            while (this.p < this.src.length() && this.src.charAt(this.p) < '!') {
                ++this.p;
            }
            this.last = this.p;
        }

        private boolean consumeIf(char c) {
            if (this.p < this.src.length() && this.src.charAt(this.p) == c) {
                ++this.p;
                return true;
            }
            return false;
        }

        private String consumeUntil(char c) {
            while (this.p < this.src.length() && this.src.charAt(this.p) != c) {
                ++this.p;
            }
            String string = this.src.substring(this.last, this.p);
            this.last = this.p;
            return string;
        }

        private void discard() {
            this.last = this.p;
        }

        private void discard(int n) {
            this.p += n;
            this.last = this.p;
        }
    }
}

