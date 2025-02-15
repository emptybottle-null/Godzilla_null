/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.compiler.KeywordTable;
import javassist.compiler.Token;
import javassist.compiler.TokenId;

public class Lex
implements TokenId {
    private int lastChar = -1;
    private StringBuffer textBuffer = new StringBuffer();
    private Token currentToken = new Token();
    private Token lookAheadTokens = null;
    private String input;
    private int position;
    private int maxlen;
    private int lineNumber;
    private static final int[] equalOps = new int[]{350, 0, 0, 0, 351, 352, 0, 0, 0, 353, 354, 0, 355, 0, 356, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 357, 358, 359, 0};
    private static final KeywordTable ktable = new KeywordTable();

    public Lex(String s) {
        this.input = s;
        this.position = 0;
        this.maxlen = s.length();
        this.lineNumber = 0;
    }

    public int get() {
        Token t;
        if (this.lookAheadTokens == null) {
            return this.get(this.currentToken);
        }
        this.currentToken = t = this.lookAheadTokens;
        this.lookAheadTokens = this.lookAheadTokens.next;
        return t.tokenId;
    }

    public int lookAhead() {
        return this.lookAhead(0);
    }

    public int lookAhead(int i) {
        Token tk = this.lookAheadTokens;
        if (tk == null) {
            this.lookAheadTokens = tk = this.currentToken;
            tk.next = null;
            this.get(tk);
        }
        while (i-- > 0) {
            if (tk.next == null) {
                Token tk2;
                tk.next = tk2 = new Token();
                this.get(tk2);
            }
            tk = tk.next;
        }
        this.currentToken = tk;
        return tk.tokenId;
    }

    public String getString() {
        return this.currentToken.textValue;
    }

    public long getLong() {
        return this.currentToken.longValue;
    }

    public double getDouble() {
        return this.currentToken.doubleValue;
    }

    private int get(Token token) {
        int t;
        while ((t = this.readLine(token)) == 10) {
        }
        token.tokenId = t;
        return t;
    }

    private int readLine(Token token) {
        int c = this.getNextNonWhiteChar();
        if (c < 0) {
            return c;
        }
        if (c == 10) {
            ++this.lineNumber;
            return 10;
        }
        if (c == 39) {
            return this.readCharConst(token);
        }
        if (c == 34) {
            return this.readStringL(token);
        }
        if (48 <= c && c <= 57) {
            return this.readNumber(c, token);
        }
        if (c == 46) {
            c = this.getc();
            if (48 <= c && c <= 57) {
                StringBuffer tbuf = this.textBuffer;
                tbuf.setLength(0);
                tbuf.append('.');
                return this.readDouble(tbuf, c, token);
            }
            this.ungetc(c);
            return this.readSeparator(46);
        }
        if (Character.isJavaIdentifierStart((char)c)) {
            return this.readIdentifier(c, token);
        }
        return this.readSeparator(c);
    }

    private int getNextNonWhiteChar() {
        int c;
        block0: do {
            if ((c = this.getc()) != 47) continue;
            c = this.getc();
            if (c == 47) {
                while ((c = this.getc()) != 10 && c != 13 && c != -1) {
                }
                continue;
            }
            if (c == 42) {
                while ((c = this.getc()) != -1) {
                    if (c != 42) continue;
                    c = this.getc();
                    if (c == 47) {
                        c = 32;
                        continue block0;
                    }
                    this.ungetc(c);
                }
            } else {
                this.ungetc(c);
                c = 47;
            }
        } while (Lex.isBlank(c));
        return c;
    }

    private int readCharConst(Token token) {
        int c;
        int value = 0;
        while ((c = this.getc()) != 39) {
            if (c == 92) {
                value = this.readEscapeChar();
                continue;
            }
            if (c < 32) {
                if (c == 10) {
                    ++this.lineNumber;
                }
                return 500;
            }
            value = c;
        }
        token.longValue = value;
        return 401;
    }

    private int readEscapeChar() {
        int c = this.getc();
        if (c == 110) {
            c = 10;
        } else if (c == 116) {
            c = 9;
        } else if (c == 114) {
            c = 13;
        } else if (c == 102) {
            c = 12;
        } else if (c == 10) {
            ++this.lineNumber;
        }
        return c;
    }

    private int readStringL(Token token) {
        int c;
        StringBuffer tbuf = this.textBuffer;
        tbuf.setLength(0);
        while (true) {
            if ((c = this.getc()) != 34) {
                if (c == 92) {
                    c = this.readEscapeChar();
                } else if (c == 10 || c < 0) {
                    ++this.lineNumber;
                    return 500;
                }
                tbuf.append((char)c);
                continue;
            }
            while (true) {
                if ((c = this.getc()) == 10) {
                    ++this.lineNumber;
                    continue;
                }
                if (!Lex.isBlank(c)) break;
            }
            if (c != 34) break;
        }
        this.ungetc(c);
        token.textValue = tbuf.toString();
        return 406;
    }

    private int readNumber(int c, Token token) {
        long value = 0L;
        int c2 = this.getc();
        if (c == 48) {
            if (c2 == 88 || c2 == 120) {
                while (true) {
                    if (48 <= (c = this.getc()) && c <= 57) {
                        value = value * 16L + (long)(c - 48);
                        continue;
                    }
                    if (65 <= c && c <= 70) {
                        value = value * 16L + (long)(c - 65 + 10);
                        continue;
                    }
                    if (97 > c || c > 102) break;
                    value = value * 16L + (long)(c - 97 + 10);
                }
                token.longValue = value;
                if (c == 76 || c == 108) {
                    return 403;
                }
                this.ungetc(c);
                return 402;
            }
            if (48 <= c2 && c2 <= 55) {
                value = c2 - 48;
                while (48 <= (c = this.getc()) && c <= 55) {
                    value = value * 8L + (long)(c - 48);
                }
                token.longValue = value;
                if (c == 76 || c == 108) {
                    return 403;
                }
                this.ungetc(c);
                return 402;
            }
        }
        value = c - 48;
        while (48 <= c2 && c2 <= 57) {
            value = value * 10L + (long)c2 - 48L;
            c2 = this.getc();
        }
        token.longValue = value;
        if (c2 == 70 || c2 == 102) {
            token.doubleValue = value;
            return 404;
        }
        if (c2 == 69 || c2 == 101 || c2 == 68 || c2 == 100 || c2 == 46) {
            StringBuffer tbuf = this.textBuffer;
            tbuf.setLength(0);
            tbuf.append(value);
            return this.readDouble(tbuf, c2, token);
        }
        if (c2 == 76 || c2 == 108) {
            return 403;
        }
        this.ungetc(c2);
        return 402;
    }

    private int readDouble(StringBuffer sbuf, int c, Token token) {
        if (c != 69 && c != 101 && c != 68 && c != 100) {
            sbuf.append((char)c);
            while (48 <= (c = this.getc()) && c <= 57) {
                sbuf.append((char)c);
            }
        }
        if (c == 69 || c == 101) {
            sbuf.append((char)c);
            c = this.getc();
            if (c == 43 || c == 45) {
                sbuf.append((char)c);
                c = this.getc();
            }
            while (48 <= c && c <= 57) {
                sbuf.append((char)c);
                c = this.getc();
            }
        }
        try {
            token.doubleValue = Double.parseDouble(sbuf.toString());
        } catch (NumberFormatException e) {
            return 500;
        }
        if (c == 70 || c == 102) {
            return 404;
        }
        if (c != 68 && c != 100) {
            this.ungetc(c);
        }
        return 405;
    }

    private int readSeparator(int c) {
        int c2;
        if (33 <= c && c <= 63) {
            int t = equalOps[c - 33];
            if (t == 0) {
                return c;
            }
            c2 = this.getc();
            if (c == c2) {
                switch (c) {
                    case 61: {
                        return 358;
                    }
                    case 43: {
                        return 362;
                    }
                    case 45: {
                        return 363;
                    }
                    case 38: {
                        return 369;
                    }
                    case 60: {
                        int c3 = this.getc();
                        if (c3 == 61) {
                            return 365;
                        }
                        this.ungetc(c3);
                        return 364;
                    }
                    case 62: {
                        int c3 = this.getc();
                        if (c3 == 61) {
                            return 367;
                        }
                        if (c3 == 62) {
                            c3 = this.getc();
                            if (c3 == 61) {
                                return 371;
                            }
                            this.ungetc(c3);
                            return 370;
                        }
                        this.ungetc(c3);
                        return 366;
                    }
                }
            } else if (c2 == 61) {
                return t;
            }
        } else if (c == 94) {
            c2 = this.getc();
            if (c2 == 61) {
                return 360;
            }
        } else if (c == 124) {
            c2 = this.getc();
            if (c2 == 61) {
                return 361;
            }
            if (c2 == 124) {
                return 368;
            }
        } else {
            return c;
        }
        this.ungetc(c2);
        return c;
    }

    private int readIdentifier(int c, Token token) {
        StringBuffer tbuf = this.textBuffer;
        tbuf.setLength(0);
        do {
            tbuf.append((char)c);
        } while (Character.isJavaIdentifierPart((char)(c = this.getc())));
        this.ungetc(c);
        String name = tbuf.toString();
        int t = ktable.lookup(name);
        if (t >= 0) {
            return t;
        }
        token.textValue = name;
        return 400;
    }

    private static boolean isBlank(int c) {
        return c == 32 || c == 9 || c == 12 || c == 13 || c == 10;
    }

    private static boolean isDigit(int c) {
        return 48 <= c && c <= 57;
    }

    private void ungetc(int c) {
        this.lastChar = c;
    }

    public String getTextAround() {
        int end;
        int begin = this.position - 10;
        if (begin < 0) {
            begin = 0;
        }
        if ((end = this.position + 10) > this.maxlen) {
            end = this.maxlen;
        }
        return this.input.substring(begin, end);
    }

    private int getc() {
        if (this.lastChar < 0) {
            if (this.position < this.maxlen) {
                return this.input.charAt(this.position++);
            }
            return -1;
        }
        int c = this.lastChar;
        this.lastChar = -1;
        return c;
    }

    static {
        ktable.append("abstract", 300);
        ktable.append("boolean", 301);
        ktable.append("break", 302);
        ktable.append("byte", 303);
        ktable.append("case", 304);
        ktable.append("catch", 305);
        ktable.append("char", 306);
        ktable.append("class", 307);
        ktable.append("const", 308);
        ktable.append("continue", 309);
        ktable.append("default", 310);
        ktable.append("do", 311);
        ktable.append("double", 312);
        ktable.append("else", 313);
        ktable.append("extends", 314);
        ktable.append("false", 411);
        ktable.append("final", 315);
        ktable.append("finally", 316);
        ktable.append("float", 317);
        ktable.append("for", 318);
        ktable.append("goto", 319);
        ktable.append("if", 320);
        ktable.append("implements", 321);
        ktable.append("import", 322);
        ktable.append("instanceof", 323);
        ktable.append("int", 324);
        ktable.append("interface", 325);
        ktable.append("long", 326);
        ktable.append("native", 327);
        ktable.append("new", 328);
        ktable.append("null", 412);
        ktable.append("package", 329);
        ktable.append("private", 330);
        ktable.append("protected", 331);
        ktable.append("public", 332);
        ktable.append("return", 333);
        ktable.append("short", 334);
        ktable.append("static", 335);
        ktable.append("strictfp", 347);
        ktable.append("super", 336);
        ktable.append("switch", 337);
        ktable.append("synchronized", 338);
        ktable.append("this", 339);
        ktable.append("throw", 340);
        ktable.append("throws", 341);
        ktable.append("transient", 342);
        ktable.append("true", 410);
        ktable.append("try", 343);
        ktable.append("void", 344);
        ktable.append("volatile", 345);
        ktable.append("while", 346);
    }
}

