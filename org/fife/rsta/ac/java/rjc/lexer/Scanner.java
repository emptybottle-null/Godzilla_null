/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lexer;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Stack;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import org.fife.rsta.ac.java.rjc.lexer.Offset;
import org.fife.rsta.ac.java.rjc.lexer.SourceCodeScanner;
import org.fife.rsta.ac.java.rjc.lexer.Token;
import org.fife.rsta.ac.java.rjc.lexer.TokenImpl;

public class Scanner {
    private static final boolean DEBUG = false;
    private SourceCodeScanner s;
    private Stack<Token> stack;
    private int typeArgLevel;
    private Document doc;
    private Token mostRecentToken;
    private Stack<Stack<Token>> resetPositions;
    private Stack<Token> currentResetTokenStack;
    private int currentResetStartOffset;

    public Scanner() {
        this((Reader)null);
    }

    public Scanner(List<Token> tokens) {
        this.stack = new Stack();
        for (int i = tokens.size() - 1; i >= 0; --i) {
            this.stack.push(tokens.get(i));
        }
    }

    public Scanner(Reader r) {
        this.s = r != null ? new SourceCodeScanner(r) : null;
        this.s.setKeepLastDocComment(true);
        this.stack = new Stack();
    }

    private void pushOntoStack(Token t) {
        if (t != null && !this.stack.isEmpty() && t.equals(this.stack.peek())) {
            System.err.println("ERROR: Token being duplicated: " + t);
            Thread.dumpStack();
            System.exit(5);
        } else if (t == null) {
            System.err.println("ERROR: null token pushed onto stack");
            Thread.dumpStack();
            System.exit(6);
        }
        this.stack.push(t);
    }

    public void decreaseTypeArgumentsLevel() {
        if (--this.typeArgLevel < 0) {
            throw new InternalError("typeArgLevel dipped below 0");
        }
    }

    public Offset createOffset(int offs) {
        if (this.doc != null) {
            try {
                return new DocumentOffset(this.doc.createPosition(offs));
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return () -> offs;
    }

    private void debugPrintToken(Token t) {
    }

    public int getColumn() {
        return this.s.getColumn();
    }

    public String getLastDocComment() {
        return this.s.getLastDocComment();
    }

    public int getLine() {
        return this.s.getLine();
    }

    public Token getMostRecentToken() {
        return this.mostRecentToken;
    }

    public int getOffset() {
        return this.s.getOffset();
    }

    public void eatParenPairs() throws IOException {
        Token t = this.yylex();
        if (t == null || t.getType() != 0x800001) {
            throw new InternalError("'(' expected, found: " + t);
        }
        int blockDepth = 0;
        int parenDepth = 1;
        while ((t = this.yylex()) != null) {
            int type = t.getType();
            switch (type) {
                case 0x800003: {
                    ++blockDepth;
                    break;
                }
                case 0x800004: {
                    blockDepth = Math.max(blockDepth - 1, 0);
                    break;
                }
                case 0x800001: {
                    if (blockDepth != 0) break;
                    ++parenDepth;
                    break;
                }
                case 0x800002: {
                    if (blockDepth != 0 || --parenDepth != 0) break;
                    return;
                }
            }
        }
    }

    public void eatThroughNext(int tokenType) throws IOException {
        Token t;
        while ((t = this.yylex()) != null && t.getType() != tokenType) {
        }
    }

    public void eatThroughNextSkippingBlocks(int tokenType) throws IOException {
        Token t;
        int blockDepth = 0;
        while ((t = this.yylex()) != null) {
            int type = t.getType();
            if (type == 0x800003) {
                ++blockDepth;
                continue;
            }
            if (type == 0x800004) {
                --blockDepth;
                continue;
            }
            if (type != tokenType || blockDepth > 0) continue;
            return;
        }
    }

    public Token eatThroughNextSkippingBlocks(int tokenType1, int tokenType2) throws IOException {
        Token t;
        int blockDepth = 0;
        while ((t = this.yylex()) != null) {
            int type = t.getType();
            if (type == 0x800003) {
                ++blockDepth;
                continue;
            }
            if (type == 0x800004) {
                --blockDepth;
                continue;
            }
            if (type != tokenType1 && type != tokenType2 || blockDepth > 0) continue;
            return t;
        }
        return null;
    }

    public Token eatThroughNextSkippingBlocksAndStuffInParens(int tokenType1, int tokenType2) throws IOException {
        Token t;
        int blockDepth = 0;
        int parenDepth = 0;
        block6: while ((t = this.yylex()) != null) {
            int type = t.getType();
            switch (type) {
                case 0x800003: {
                    ++blockDepth;
                    continue block6;
                }
                case 0x800004: {
                    --blockDepth;
                    continue block6;
                }
                case 0x800001: {
                    ++parenDepth;
                    continue block6;
                }
                case 0x800002: {
                    --parenDepth;
                    continue block6;
                }
            }
            if (type != tokenType1 && type != tokenType2 || blockDepth > 0 || parenDepth > 0) continue;
            return t;
        }
        return null;
    }

    public void eatUntilNext(int type1, int type2) throws IOException {
        Token t;
        while ((t = this.yylex()) != null) {
            int type = t.getType();
            if (type != type1 && type != type2) continue;
            this.yyPushback(t);
            break;
        }
    }

    public void eatUntilNext(int type1, int type2, int type3) throws IOException {
        Token t;
        while ((t = this.yylex()) != null) {
            int type = t.getType();
            if (type != type1 && type != type2 && type != type3) continue;
            this.yyPushback(t);
            break;
        }
    }

    public int getTypeArgumentsLevel() {
        return this.typeArgLevel;
    }

    public void increaseTypeArgumentsLevel() {
        ++this.typeArgLevel;
    }

    public void markResetPosition() {
        if (this.s != null) {
            if (this.resetPositions == null) {
                this.resetPositions = new Stack();
            }
            this.currentResetTokenStack = new Stack();
            this.resetPositions.push(this.currentResetTokenStack);
            this.currentResetStartOffset = this.s.getOffset();
        }
    }

    public void resetToLastMarkedPosition() {
        if (this.s != null) {
            Token t;
            if (this.currentResetTokenStack == null) {
                throw new InternalError("No resetTokenStack!");
            }
            while (!this.stack.isEmpty() && (t = this.stack.peek()).getOffset() >= this.currentResetStartOffset) {
                this.stack.pop();
            }
            while (!this.currentResetTokenStack.isEmpty()) {
                t = this.currentResetTokenStack.pop();
                this.stack.push(t);
            }
            this.resetPositions.pop();
            this.currentResetTokenStack = this.resetPositions.isEmpty() ? null : this.resetPositions.peek();
            this.currentResetStartOffset = -1;
        }
    }

    public void clearResetPosition() {
        if (this.s != null) {
            if (this.currentResetTokenStack == null) {
                throw new InternalError("No resetTokenStack!");
            }
            this.resetPositions.pop();
            this.currentResetTokenStack = this.resetPositions.isEmpty() ? null : this.resetPositions.peek();
            this.currentResetStartOffset = -1;
        }
    }

    public void setDocument(Document doc) {
        this.doc = doc;
    }

    public int skipBracketPairs() throws IOException {
        int count = 0;
        while (this.yyPeekCheckType() == 0x800005 && this.yyPeekCheckType(2) == 0x800006) {
            this.yylex();
            this.yylex();
            ++count;
        }
        return count;
    }

    public Token yylex() throws IOException {
        String lexeme;
        Token t = this.stack.isEmpty() ? (this.s != null ? this.s.yylex() : null) : this.stack.pop();
        if (this.typeArgLevel > 0 && t != null && t.isOperator() && (lexeme = t.getLexeme()).length() > 1) {
            char ch = lexeme.charAt(0);
            if (ch == '<') {
                TokenImpl rest = null;
                switch (t.getType()) {
                    case 0x1000009: {
                        rest = new TokenImpl(0x2000001, "=", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                        break;
                    }
                    case 0x1000018: {
                        rest = new TokenImpl(0x1000003, "<", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                        break;
                    }
                    case 0x2000023: {
                        rest = new TokenImpl(0x1000009, "<=", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                    }
                }
                this.stack.push(rest);
                t = new TokenImpl(0x1000003, "<", t.getLine(), t.getColumn(), t.getOffset());
            } else if (ch == '>') {
                TokenImpl rest = null;
                switch (t.getType()) {
                    case 0x100000A: {
                        rest = new TokenImpl(0x2000001, "=", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                        break;
                    }
                    case 0x1000019: {
                        rest = new TokenImpl(0x1000002, ">", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                        break;
                    }
                    case 0x100001A: {
                        rest = new TokenImpl(0x1000019, ">>", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                        break;
                    }
                    case 0x2000024: {
                        rest = new TokenImpl(0x100000A, ">=", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                        break;
                    }
                    case 0x2000025: {
                        rest = new TokenImpl(0x2000024, ">>=", t.getLine(), t.getColumn() + 1, t.getOffset() + 1);
                    }
                }
                this.stack.push(rest);
                t = new TokenImpl(0x1000002, ">", t.getLine(), t.getColumn(), t.getOffset());
            }
        }
        this.debugPrintToken(t);
        if (this.currentResetTokenStack != null) {
            this.currentResetTokenStack.push(t);
        }
        if (t != null) {
            this.mostRecentToken = t;
        }
        return t;
    }

    public Token yylexNonNull(String error) throws IOException {
        Token t = this.yylex();
        if (t == null) {
            throw new EOFException(error);
        }
        return t;
    }

    public Token yylexNonNull(int type, String error) throws IOException {
        return this.yylexNonNull(type, -1, error);
    }

    public Token yylexNonNull(int type1, int type2, String error) throws IOException {
        return this.yylexNonNull(type1, type2, -1, error);
    }

    public Token yylexNonNull(int type1, int type2, int type3, String error) throws IOException {
        Token t = this.yylex();
        if (t == null) {
            throw new IOException(error);
        }
        if (!(t.getType() == type1 || type2 != -1 && t.getType() == type2 || type3 != -1 && t.getType() == type3)) {
            throw new IOException(error + ", found '" + t.getLexeme() + "'");
        }
        return t;
    }

    public Token yyPeek() throws IOException {
        Token t = this.yylex();
        if (t != null) {
            this.pushOntoStack(t);
        }
        return t;
    }

    public Token yyPeek(int depth) throws IOException {
        if (depth < 1) {
            throw new IllegalArgumentException("depth must be >= 1");
        }
        Stack<Token> read = new Stack<Token>();
        for (int i = 0; i < depth; ++i) {
            Token t = this.yylex();
            if (t == null) {
                while (!read.isEmpty()) {
                    this.yyPushback((Token)read.pop());
                }
                return null;
            }
            read.push(t);
        }
        Token t = (Token)read.peek();
        while (!read.isEmpty()) {
            this.yyPushback((Token)read.pop());
        }
        return t;
    }

    public int yyPeekCheckType() throws IOException {
        Token t = this.yyPeek();
        return t != null ? t.getType() : -1;
    }

    public int yyPeekCheckType(int index) throws IOException {
        Token t = this.yyPeek(index);
        return t != null ? t.getType() : -1;
    }

    public Token yyPeekNonNull(String error) throws IOException {
        Token t = this.yyPeek();
        if (t == null) {
            throw new IOException(error);
        }
        return t;
    }

    public Token yyPeekNonNull(int type, String error) throws IOException {
        return this.yyPeekNonNull(type, -1, error);
    }

    public Token yyPeekNonNull(int type1, int type2, String error) throws IOException {
        return this.yyPeekNonNull(type1, type2, -1, error);
    }

    public Token yyPeekNonNull(int type1, int type2, int type3, String error) throws IOException {
        Token t = this.yyPeek();
        if (t == null) {
            throw new IOException(error);
        }
        if (!(t.getType() == type1 || type2 != -1 && t.getType() == type2 || type3 != -1 && t.getType() == type3)) {
            throw new IOException(error + ", found '" + t.getLexeme() + "'");
        }
        return t;
    }

    public void yyPushback(Token t) {
        if (t != null) {
            this.pushOntoStack(t);
        }
    }

    private static class DocumentOffset
    implements Offset {
        public Position pos;

        public DocumentOffset(Position pos) {
            this.pos = pos;
        }

        @Override
        public int getOffset() {
            return this.pos.getOffset();
        }
    }
}

