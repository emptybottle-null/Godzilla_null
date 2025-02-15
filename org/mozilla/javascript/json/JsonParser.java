/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.json;

import java.util.ArrayList;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class JsonParser {
    private Context cx;
    private Scriptable scope;
    private int pos;
    private int length;
    private String src;

    public JsonParser(Context cx, Scriptable scope) {
        this.cx = cx;
        this.scope = scope;
    }

    public synchronized Object parseValue(String json) throws ParseException {
        if (json == null) {
            throw new ParseException("Input string may not be null");
        }
        this.pos = 0;
        this.length = json.length();
        this.src = json;
        Object value = this.readValue();
        this.consumeWhitespace();
        if (this.pos < this.length) {
            throw new ParseException("Expected end of stream at char " + this.pos);
        }
        return value;
    }

    private Object readValue() throws ParseException {
        this.consumeWhitespace();
        if (this.pos < this.length) {
            char c = this.src.charAt(this.pos++);
            switch (c) {
                case '{': {
                    return this.readObject();
                }
                case '[': {
                    return this.readArray();
                }
                case 't': {
                    return this.readTrue();
                }
                case 'f': {
                    return this.readFalse();
                }
                case '\"': {
                    return this.readString();
                }
                case 'n': {
                    return this.readNull();
                }
                case '-': 
                case '0': 
                case '1': 
                case '2': 
                case '3': 
                case '4': 
                case '5': 
                case '6': 
                case '7': 
                case '8': 
                case '9': {
                    return this.readNumber(c);
                }
            }
            throw new ParseException("Unexpected token: " + c);
        }
        throw new ParseException("Empty JSON string");
    }

    private Object readObject() throws ParseException {
        this.consumeWhitespace();
        Scriptable object = this.cx.newObject(this.scope);
        if (this.pos < this.length && this.src.charAt(this.pos) == '}') {
            ++this.pos;
            return object;
        }
        boolean needsComma = false;
        while (this.pos < this.length) {
            char c = this.src.charAt(this.pos++);
            switch (c) {
                case '}': {
                    if (!needsComma) {
                        throw new ParseException("Unexpected comma in object literal");
                    }
                    return object;
                }
                case ',': {
                    if (!needsComma) {
                        throw new ParseException("Unexpected comma in object literal");
                    }
                    needsComma = false;
                    break;
                }
                case '\"': {
                    if (needsComma) {
                        throw new ParseException("Missing comma in object literal");
                    }
                    String id = this.readString();
                    this.consume(':');
                    Object value = this.readValue();
                    long index = ScriptRuntime.indexFromString(id);
                    if (index < 0L) {
                        object.put(id, object, value);
                    } else {
                        object.put((int)index, object, value);
                    }
                    needsComma = true;
                    break;
                }
                default: {
                    throw new ParseException("Unexpected token in object literal");
                }
            }
            this.consumeWhitespace();
        }
        throw new ParseException("Unterminated object literal");
    }

    private Object readArray() throws ParseException {
        this.consumeWhitespace();
        if (this.pos < this.length && this.src.charAt(this.pos) == ']') {
            ++this.pos;
            return this.cx.newArray(this.scope, 0);
        }
        ArrayList<Object> list = new ArrayList<Object>();
        boolean needsComma = false;
        while (this.pos < this.length) {
            char c = this.src.charAt(this.pos);
            switch (c) {
                case ']': {
                    if (!needsComma) {
                        throw new ParseException("Unexpected comma in array literal");
                    }
                    ++this.pos;
                    return this.cx.newArray(this.scope, list.toArray());
                }
                case ',': {
                    if (!needsComma) {
                        throw new ParseException("Unexpected comma in array literal");
                    }
                    needsComma = false;
                    ++this.pos;
                    break;
                }
                default: {
                    if (needsComma) {
                        throw new ParseException("Missing comma in array literal");
                    }
                    list.add(this.readValue());
                    needsComma = true;
                }
            }
            this.consumeWhitespace();
        }
        throw new ParseException("Unterminated array literal");
    }

    private String readString() throws ParseException {
        int stringStart = this.pos;
        while (this.pos < this.length) {
            char c;
            if ((c = this.src.charAt(this.pos++)) <= '\u001f') {
                throw new ParseException("String contains control character");
            }
            if (c == '\\') break;
            if (c != '\"') continue;
            return this.src.substring(stringStart, this.pos - 1);
        }
        StringBuilder b = new StringBuilder();
        block12: while (this.pos < this.length) {
            assert (this.src.charAt(this.pos - 1) == '\\');
            b.append(this.src, stringStart, this.pos - 1);
            if (this.pos >= this.length) {
                throw new ParseException("Unterminated string");
            }
            char c = this.src.charAt(this.pos++);
            switch (c) {
                case '\"': {
                    b.append('\"');
                    break;
                }
                case '\\': {
                    b.append('\\');
                    break;
                }
                case '/': {
                    b.append('/');
                    break;
                }
                case 'b': {
                    b.append('\b');
                    break;
                }
                case 'f': {
                    b.append('\f');
                    break;
                }
                case 'n': {
                    b.append('\n');
                    break;
                }
                case 'r': {
                    b.append('\r');
                    break;
                }
                case 't': {
                    b.append('\t');
                    break;
                }
                case 'u': {
                    if (this.length - this.pos < 5) {
                        throw new ParseException("Invalid character code: \\u" + this.src.substring(this.pos));
                    }
                    int code = this.fromHex(this.src.charAt(this.pos + 0)) << 12 | this.fromHex(this.src.charAt(this.pos + 1)) << 8 | this.fromHex(this.src.charAt(this.pos + 2)) << 4 | this.fromHex(this.src.charAt(this.pos + 3));
                    if (code < 0) {
                        throw new ParseException("Invalid character code: " + this.src.substring(this.pos, this.pos + 4));
                    }
                    this.pos += 4;
                    b.append((char)code);
                    break;
                }
                default: {
                    throw new ParseException("Unexpected character in string: '\\" + c + "'");
                }
            }
            stringStart = this.pos;
            while (this.pos < this.length) {
                if ((c = this.src.charAt(this.pos++)) <= '\u001f') {
                    throw new ParseException("String contains control character");
                }
                if (c == '\\') continue block12;
                if (c != '\"') continue;
                b.append(this.src, stringStart, this.pos - 1);
                return b.toString();
            }
        }
        throw new ParseException("Unterminated string literal");
    }

    private int fromHex(char c) {
        return c >= '0' && c <= '9' ? c - 48 : (c >= 'A' && c <= 'F' ? c - 65 + 10 : (c >= 'a' && c <= 'f' ? c - 97 + 10 : -1));
    }

    private Number readNumber(char c) throws ParseException {
        String num;
        double dval;
        int ival;
        assert (c == '-' || c >= '0' && c <= '9');
        int numberStart = this.pos - 1;
        if (c == '-' && ((c = this.nextOrNumberError(numberStart)) < '0' || c > '9')) {
            throw this.numberError(numberStart, this.pos);
        }
        if (c != '0') {
            this.readDigits();
        }
        if (this.pos < this.length && (c = this.src.charAt(this.pos)) == '.') {
            ++this.pos;
            c = this.nextOrNumberError(numberStart);
            if (c < '0' || c > '9') {
                throw this.numberError(numberStart, this.pos);
            }
            this.readDigits();
        }
        if (this.pos < this.length && ((c = this.src.charAt(this.pos)) == 'e' || c == 'E')) {
            ++this.pos;
            c = this.nextOrNumberError(numberStart);
            if (c == '-' || c == '+') {
                c = this.nextOrNumberError(numberStart);
            }
            if (c < '0' || c > '9') {
                throw this.numberError(numberStart, this.pos);
            }
            this.readDigits();
        }
        if ((double)(ival = (int)(dval = Double.parseDouble(num = this.src.substring(numberStart, this.pos)))) == dval) {
            return ival;
        }
        return dval;
    }

    private ParseException numberError(int start, int end) {
        return new ParseException("Unsupported number format: " + this.src.substring(start, end));
    }

    private char nextOrNumberError(int numberStart) throws ParseException {
        if (this.pos >= this.length) {
            throw this.numberError(numberStart, this.length);
        }
        return this.src.charAt(this.pos++);
    }

    private void readDigits() {
        char c;
        while (this.pos < this.length && (c = this.src.charAt(this.pos)) >= '0' && c <= '9') {
            ++this.pos;
        }
    }

    private Boolean readTrue() throws ParseException {
        if (this.length - this.pos < 3 || this.src.charAt(this.pos) != 'r' || this.src.charAt(this.pos + 1) != 'u' || this.src.charAt(this.pos + 2) != 'e') {
            throw new ParseException("Unexpected token: t");
        }
        this.pos += 3;
        return Boolean.TRUE;
    }

    private Boolean readFalse() throws ParseException {
        if (this.length - this.pos < 4 || this.src.charAt(this.pos) != 'a' || this.src.charAt(this.pos + 1) != 'l' || this.src.charAt(this.pos + 2) != 's' || this.src.charAt(this.pos + 3) != 'e') {
            throw new ParseException("Unexpected token: f");
        }
        this.pos += 4;
        return Boolean.FALSE;
    }

    private Object readNull() throws ParseException {
        if (this.length - this.pos < 3 || this.src.charAt(this.pos) != 'u' || this.src.charAt(this.pos + 1) != 'l' || this.src.charAt(this.pos + 2) != 'l') {
            throw new ParseException("Unexpected token: n");
        }
        this.pos += 3;
        return null;
    }

    private void consumeWhitespace() {
        block3: while (this.pos < this.length) {
            char c = this.src.charAt(this.pos);
            switch (c) {
                case '\t': 
                case '\n': 
                case '\r': 
                case ' ': {
                    ++this.pos;
                    continue block3;
                }
            }
            return;
        }
    }

    private void consume(char token) throws ParseException {
        char c;
        this.consumeWhitespace();
        if (this.pos >= this.length) {
            throw new ParseException("Expected " + token + " but reached end of stream");
        }
        if ((c = this.src.charAt(this.pos++)) == token) {
            return;
        }
        throw new ParseException("Expected " + token + " found " + c);
    }

    public static class ParseException
    extends Exception {
        static final long serialVersionUID = 4804542791749920772L;

        ParseException(String message) {
            super(message);
        }

        ParseException(Exception cause) {
            super(cause);
        }
    }
}

