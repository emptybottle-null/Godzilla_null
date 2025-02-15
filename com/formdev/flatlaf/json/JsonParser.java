/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.JsonHandler;
import com.formdev.flatlaf.json.Location;
import com.formdev.flatlaf.json.ParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

class JsonParser {
    private static final int MAX_NESTING_LEVEL = 1000;
    private static final int MIN_BUFFER_SIZE = 10;
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private final JsonHandler<Object, Object> handler;
    private Reader reader;
    private char[] buffer;
    private int bufferOffset;
    private int index;
    private int fill;
    private int line;
    private int lineOffset;
    private int current;
    private StringBuilder captureBuffer;
    private int captureStart;
    private int nestingLevel;

    public JsonParser(JsonHandler<?, ?> handler) {
        if (handler == null) {
            throw new NullPointerException("handler is null");
        }
        this.handler = handler;
        handler.parser = this;
    }

    public void parse(String string) {
        if (string == null) {
            throw new NullPointerException("string is null");
        }
        int bufferSize = Math.max(10, Math.min(1024, string.length()));
        try {
            this.parse(new StringReader(string), bufferSize);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void parse(Reader reader) throws IOException {
        this.parse(reader, 1024);
    }

    public void parse(Reader reader, int buffersize) throws IOException {
        if (reader == null) {
            throw new NullPointerException("reader is null");
        }
        if (buffersize <= 0) {
            throw new IllegalArgumentException("buffersize is zero or negative");
        }
        this.reader = reader;
        this.buffer = new char[buffersize];
        this.bufferOffset = 0;
        this.index = 0;
        this.fill = 0;
        this.line = 1;
        this.lineOffset = 0;
        this.current = 0;
        this.captureStart = -1;
        this.read();
        this.skipWhiteSpace();
        this.readValue();
        this.skipWhiteSpace();
        if (!this.isEndOfText()) {
            throw this.error("Unexpected character");
        }
    }

    private void readValue() throws IOException {
        switch (this.current) {
            case 110: {
                this.readNull();
                break;
            }
            case 116: {
                this.readTrue();
                break;
            }
            case 102: {
                this.readFalse();
                break;
            }
            case 34: {
                this.readString();
                break;
            }
            case 91: {
                this.readArray();
                break;
            }
            case 123: {
                this.readObject();
                break;
            }
            case 45: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                this.readNumber();
                break;
            }
            default: {
                throw this.expected("value");
            }
        }
    }

    private void readArray() throws IOException {
        Object array = this.handler.startArray();
        this.read();
        if (++this.nestingLevel > 1000) {
            throw this.error("Nesting too deep");
        }
        this.skipWhiteSpace();
        if (this.readChar(']')) {
            --this.nestingLevel;
            this.handler.endArray(array);
            return;
        }
        do {
            this.skipWhiteSpace();
            this.handler.startArrayValue(array);
            this.readValue();
            this.handler.endArrayValue(array);
            this.skipWhiteSpace();
        } while (this.readChar(','));
        if (!this.readChar(']')) {
            throw this.expected("',' or ']'");
        }
        --this.nestingLevel;
        this.handler.endArray(array);
    }

    private void readObject() throws IOException {
        Object object = this.handler.startObject();
        this.read();
        if (++this.nestingLevel > 1000) {
            throw this.error("Nesting too deep");
        }
        this.skipWhiteSpace();
        if (this.readChar('}')) {
            --this.nestingLevel;
            this.handler.endObject(object);
            return;
        }
        do {
            this.skipWhiteSpace();
            this.handler.startObjectName(object);
            String name = this.readName();
            this.handler.endObjectName(object, name);
            this.skipWhiteSpace();
            if (!this.readChar(':')) {
                throw this.expected("':'");
            }
            this.skipWhiteSpace();
            this.handler.startObjectValue(object, name);
            this.readValue();
            this.handler.endObjectValue(object, name);
            this.skipWhiteSpace();
        } while (this.readChar(','));
        if (!this.readChar('}')) {
            throw this.expected("',' or '}'");
        }
        --this.nestingLevel;
        this.handler.endObject(object);
    }

    private String readName() throws IOException {
        if (this.current != 34) {
            throw this.expected("name");
        }
        return this.readStringInternal();
    }

    private void readNull() throws IOException {
        this.handler.startNull();
        this.read();
        this.readRequiredChar('u');
        this.readRequiredChar('l');
        this.readRequiredChar('l');
        this.handler.endNull();
    }

    private void readTrue() throws IOException {
        this.handler.startBoolean();
        this.read();
        this.readRequiredChar('r');
        this.readRequiredChar('u');
        this.readRequiredChar('e');
        this.handler.endBoolean(true);
    }

    private void readFalse() throws IOException {
        this.handler.startBoolean();
        this.read();
        this.readRequiredChar('a');
        this.readRequiredChar('l');
        this.readRequiredChar('s');
        this.readRequiredChar('e');
        this.handler.endBoolean(false);
    }

    private void readRequiredChar(char ch) throws IOException {
        if (!this.readChar(ch)) {
            throw this.expected("'" + ch + "'");
        }
    }

    private void readString() throws IOException {
        this.handler.startString();
        this.handler.endString(this.readStringInternal());
    }

    private String readStringInternal() throws IOException {
        this.read();
        this.startCapture();
        while (this.current != 34) {
            if (this.current == 92) {
                this.pauseCapture();
                this.readEscape();
                this.startCapture();
                continue;
            }
            if (this.current < 32) {
                throw this.expected("valid string character");
            }
            this.read();
        }
        String string = this.endCapture();
        this.read();
        return string;
    }

    private void readEscape() throws IOException {
        this.read();
        switch (this.current) {
            case 34: 
            case 47: 
            case 92: {
                this.captureBuffer.append((char)this.current);
                break;
            }
            case 98: {
                this.captureBuffer.append('\b');
                break;
            }
            case 102: {
                this.captureBuffer.append('\f');
                break;
            }
            case 110: {
                this.captureBuffer.append('\n');
                break;
            }
            case 114: {
                this.captureBuffer.append('\r');
                break;
            }
            case 116: {
                this.captureBuffer.append('\t');
                break;
            }
            case 117: {
                char[] hexChars = new char[4];
                for (int i = 0; i < 4; ++i) {
                    this.read();
                    if (!this.isHexDigit()) {
                        throw this.expected("hexadecimal digit");
                    }
                    hexChars[i] = (char)this.current;
                }
                this.captureBuffer.append((char)Integer.parseInt(new String(hexChars), 16));
                break;
            }
            default: {
                throw this.expected("valid escape sequence");
            }
        }
        this.read();
    }

    private void readNumber() throws IOException {
        this.handler.startNumber();
        this.startCapture();
        this.readChar('-');
        int firstDigit = this.current;
        if (!this.readDigit()) {
            throw this.expected("digit");
        }
        if (firstDigit != 48) {
            while (this.readDigit()) {
            }
        }
        this.readFraction();
        this.readExponent();
        this.handler.endNumber(this.endCapture());
    }

    private boolean readFraction() throws IOException {
        if (!this.readChar('.')) {
            return false;
        }
        if (!this.readDigit()) {
            throw this.expected("digit");
        }
        while (this.readDigit()) {
        }
        return true;
    }

    private boolean readExponent() throws IOException {
        if (!this.readChar('e') && !this.readChar('E')) {
            return false;
        }
        if (!this.readChar('+')) {
            this.readChar('-');
        }
        if (!this.readDigit()) {
            throw this.expected("digit");
        }
        while (this.readDigit()) {
        }
        return true;
    }

    private boolean readChar(char ch) throws IOException {
        if (this.current != ch) {
            return false;
        }
        this.read();
        return true;
    }

    private boolean readDigit() throws IOException {
        if (!this.isDigit()) {
            return false;
        }
        this.read();
        return true;
    }

    private void skipWhiteSpace() throws IOException {
        while (this.isWhiteSpace()) {
            this.read();
        }
    }

    private void read() throws IOException {
        if (this.index == this.fill) {
            if (this.captureStart != -1) {
                this.captureBuffer.append(this.buffer, this.captureStart, this.fill - this.captureStart);
                this.captureStart = 0;
            }
            this.bufferOffset += this.fill;
            this.fill = this.reader.read(this.buffer, 0, this.buffer.length);
            this.index = 0;
            if (this.fill == -1) {
                this.current = -1;
                ++this.index;
                return;
            }
        }
        if (this.current == 10) {
            ++this.line;
            this.lineOffset = this.bufferOffset + this.index;
        }
        this.current = this.buffer[this.index++];
    }

    private void startCapture() {
        if (this.captureBuffer == null) {
            this.captureBuffer = new StringBuilder();
        }
        this.captureStart = this.index - 1;
    }

    private void pauseCapture() {
        int end = this.current == -1 ? this.index : this.index - 1;
        this.captureBuffer.append(this.buffer, this.captureStart, end - this.captureStart);
        this.captureStart = -1;
    }

    private String endCapture() {
        int start = this.captureStart;
        int end = this.index - 1;
        this.captureStart = -1;
        if (this.captureBuffer.length() > 0) {
            this.captureBuffer.append(this.buffer, start, end - start);
            String captured = this.captureBuffer.toString();
            this.captureBuffer.setLength(0);
            return captured;
        }
        return new String(this.buffer, start, end - start);
    }

    Location getLocation() {
        int offset = this.bufferOffset + this.index - 1;
        int column = offset - this.lineOffset + 1;
        return new Location(offset, this.line, column);
    }

    private ParseException expected(String expected) {
        if (this.isEndOfText()) {
            return this.error("Unexpected end of input");
        }
        return this.error("Expected " + expected);
    }

    private ParseException error(String message) {
        return new ParseException(message, this.getLocation());
    }

    private boolean isWhiteSpace() {
        return this.current == 32 || this.current == 9 || this.current == 10 || this.current == 13;
    }

    private boolean isDigit() {
        return this.current >= 48 && this.current <= 57;
    }

    private boolean isHexDigit() {
        return this.current >= 48 && this.current <= 57 || this.current >= 97 && this.current <= 102 || this.current >= 65 && this.current <= 70;
    }

    private boolean isEndOfText() {
        return this.current == -1;
    }
}

