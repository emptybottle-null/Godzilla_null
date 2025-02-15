/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.v8dtoa;

import java.util.Arrays;

public class FastDtoaBuilder {
    final char[] chars = new char[25];
    int end = 0;
    int point;
    boolean formatted = false;
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    void append(char c) {
        this.chars[this.end++] = c;
    }

    void decreaseLast() {
        int n = this.end - 1;
        this.chars[n] = (char)(this.chars[n] - '\u0001');
    }

    public void reset() {
        this.end = 0;
        this.formatted = false;
    }

    public String toString() {
        return "[chars:" + new String(this.chars, 0, this.end) + ", point:" + this.point + "]";
    }

    public String format() {
        if (!this.formatted) {
            int firstDigit = this.chars[0] == '-' ? 1 : 0;
            int decPoint = this.point - firstDigit;
            if (decPoint < -5 || decPoint > 21) {
                this.toExponentialFormat(firstDigit, decPoint);
            } else {
                this.toFixedFormat(firstDigit, decPoint);
            }
            this.formatted = true;
        }
        return new String(this.chars, 0, this.end);
    }

    private void toFixedFormat(int firstDigit, int decPoint) {
        if (this.point < this.end) {
            if (decPoint > 0) {
                System.arraycopy(this.chars, this.point, this.chars, this.point + 1, this.end - this.point);
                this.chars[this.point] = 46;
                ++this.end;
            } else {
                int target = firstDigit + 2 - decPoint;
                System.arraycopy(this.chars, firstDigit, this.chars, target, this.end - firstDigit);
                this.chars[firstDigit] = 48;
                this.chars[firstDigit + 1] = 46;
                if (decPoint < 0) {
                    Arrays.fill(this.chars, firstDigit + 2, target, '0');
                }
                this.end += 2 - decPoint;
            }
        } else if (this.point > this.end) {
            Arrays.fill(this.chars, this.end, this.point, '0');
            this.end += this.point - this.end;
        }
    }

    private void toExponentialFormat(int firstDigit, int decPoint) {
        if (this.end - firstDigit > 1) {
            int dot = firstDigit + 1;
            System.arraycopy(this.chars, dot, this.chars, dot + 1, this.end - dot);
            this.chars[dot] = 46;
            ++this.end;
        }
        this.chars[this.end++] = 101;
        int sign = 43;
        int exp = decPoint - 1;
        if (exp < 0) {
            sign = 45;
            exp = -exp;
        }
        this.chars[this.end++] = sign;
        int charPos = exp > 99 ? this.end + 2 : (exp > 9 ? this.end + 1 : this.end);
        this.end = charPos + 1;
        do {
            int r = exp % 10;
            this.chars[charPos--] = digits[r];
        } while ((exp /= 10) != 0);
    }
}

