/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

public class WindowsBatchTokenMaker
extends AbstractTokenMaker {
    private static final String OPERATORS = "@:*<>=?";
    private int currentTokenStart;
    private int currentTokenType;
    private VariableType varType;

    @Override
    public void addToken(Segment segment, int start, int end, int tokenType, int startOffset) {
        switch (tokenType) {
            case 20: {
                int value = this.wordsToHighlight.get(segment, start, end);
                if (value == -1) break;
                tokenType = value;
            }
        }
        super.addToken(segment, start, end, tokenType, startOffset);
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"rem ", null};
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 20 || type == 17;
    }

    @Override
    public TokenMap getWordsToHighlight() {
        TokenMap tokenMap = new TokenMap(true);
        int reservedWord = 6;
        tokenMap.put("goto", reservedWord);
        tokenMap.put("if", reservedWord);
        tokenMap.put("shift", reservedWord);
        tokenMap.put("start", reservedWord);
        tokenMap.put("ansi.sys", reservedWord);
        tokenMap.put("append", reservedWord);
        tokenMap.put("arp", reservedWord);
        tokenMap.put("assign", reservedWord);
        tokenMap.put("assoc", reservedWord);
        tokenMap.put("at", reservedWord);
        tokenMap.put("attrib", reservedWord);
        tokenMap.put("break", reservedWord);
        tokenMap.put("cacls", reservedWord);
        tokenMap.put("call", reservedWord);
        tokenMap.put("cd", reservedWord);
        tokenMap.put("chcp", reservedWord);
        tokenMap.put("chdir", reservedWord);
        tokenMap.put("chkdsk", reservedWord);
        tokenMap.put("chknfts", reservedWord);
        tokenMap.put("choice", reservedWord);
        tokenMap.put("cls", reservedWord);
        tokenMap.put("cmd", reservedWord);
        tokenMap.put("color", reservedWord);
        tokenMap.put("comp", reservedWord);
        tokenMap.put("compact", reservedWord);
        tokenMap.put("control", reservedWord);
        tokenMap.put("convert", reservedWord);
        tokenMap.put("copy", reservedWord);
        tokenMap.put("ctty", reservedWord);
        tokenMap.put("date", reservedWord);
        tokenMap.put("debug", reservedWord);
        tokenMap.put("defrag", reservedWord);
        tokenMap.put("del", reservedWord);
        tokenMap.put("deltree", reservedWord);
        tokenMap.put("dir", reservedWord);
        tokenMap.put("diskcomp", reservedWord);
        tokenMap.put("diskcopy", reservedWord);
        tokenMap.put("do", reservedWord);
        tokenMap.put("doskey", reservedWord);
        tokenMap.put("dosshell", reservedWord);
        tokenMap.put("drivparm", reservedWord);
        tokenMap.put("echo", reservedWord);
        tokenMap.put("edit", reservedWord);
        tokenMap.put("edlin", reservedWord);
        tokenMap.put("emm386", reservedWord);
        tokenMap.put("erase", reservedWord);
        tokenMap.put("exist", reservedWord);
        tokenMap.put("exit", reservedWord);
        tokenMap.put("expand", reservedWord);
        tokenMap.put("extract", reservedWord);
        tokenMap.put("fasthelp", reservedWord);
        tokenMap.put("fc", reservedWord);
        tokenMap.put("fdisk", reservedWord);
        tokenMap.put("find", reservedWord);
        tokenMap.put("for", reservedWord);
        tokenMap.put("format", reservedWord);
        tokenMap.put("ftp", reservedWord);
        tokenMap.put("graftabl", reservedWord);
        tokenMap.put("help", reservedWord);
        tokenMap.put("ifshlp.sys", reservedWord);
        tokenMap.put("in", reservedWord);
        tokenMap.put("ipconfig", reservedWord);
        tokenMap.put("keyb", reservedWord);
        tokenMap.put("kill", reservedWord);
        tokenMap.put("label", reservedWord);
        tokenMap.put("lh", reservedWord);
        tokenMap.put("loadfix", reservedWord);
        tokenMap.put("loadhigh", reservedWord);
        tokenMap.put("lock", reservedWord);
        tokenMap.put("md", reservedWord);
        tokenMap.put("mem", reservedWord);
        tokenMap.put("mkdir", reservedWord);
        tokenMap.put("mklink", reservedWord);
        tokenMap.put("mode", reservedWord);
        tokenMap.put("more", reservedWord);
        tokenMap.put("move", reservedWord);
        tokenMap.put("msav", reservedWord);
        tokenMap.put("msd", reservedWord);
        tokenMap.put("mscdex", reservedWord);
        tokenMap.put("nbtstat", reservedWord);
        tokenMap.put("net", reservedWord);
        tokenMap.put("netstat", reservedWord);
        tokenMap.put("nlsfunc", reservedWord);
        tokenMap.put("not", reservedWord);
        tokenMap.put("nslookup", reservedWord);
        tokenMap.put("path", reservedWord);
        tokenMap.put("pathping", reservedWord);
        tokenMap.put("pause", reservedWord);
        tokenMap.put("ping", reservedWord);
        tokenMap.put("power", reservedWord);
        tokenMap.put("print", reservedWord);
        tokenMap.put("prompt", reservedWord);
        tokenMap.put("pushd", reservedWord);
        tokenMap.put("popd", reservedWord);
        tokenMap.put("qbasic", reservedWord);
        tokenMap.put("rd", reservedWord);
        tokenMap.put("ren", reservedWord);
        tokenMap.put("rename", reservedWord);
        tokenMap.put("rmdir", reservedWord);
        tokenMap.put("route", reservedWord);
        tokenMap.put("sc", reservedWord);
        tokenMap.put("scandisk", reservedWord);
        tokenMap.put("scandreg", reservedWord);
        tokenMap.put("set", reservedWord);
        tokenMap.put("setx", reservedWord);
        tokenMap.put("setver", reservedWord);
        tokenMap.put("share", reservedWord);
        tokenMap.put("shutdown", reservedWord);
        tokenMap.put("smartdrv", reservedWord);
        tokenMap.put("sort", reservedWord);
        tokenMap.put("subset", reservedWord);
        tokenMap.put("switches", reservedWord);
        tokenMap.put("sys", reservedWord);
        tokenMap.put("time", reservedWord);
        tokenMap.put("tracert", reservedWord);
        tokenMap.put("tree", reservedWord);
        tokenMap.put("type", reservedWord);
        tokenMap.put("undelete", reservedWord);
        tokenMap.put("unformat", reservedWord);
        tokenMap.put("unlock", reservedWord);
        tokenMap.put("ver", reservedWord);
        tokenMap.put("verify", reservedWord);
        tokenMap.put("vol", reservedWord);
        tokenMap.put("xcopy", reservedWord);
        return tokenMap;
    }

    @Override
    public Token getTokenList(Segment text, int startTokenType, int startOffset) {
        this.resetTokenList();
        char[] array = text.array;
        int offset = text.offset;
        int count = text.count;
        int end = offset + count;
        int newStartOffset = startOffset - offset;
        this.currentTokenStart = offset;
        this.currentTokenType = startTokenType;
        block43: for (int i = offset; i < end; ++i) {
            char c = array[i];
            switch (this.currentTokenType) {
                case 0: {
                    this.currentTokenStart = i;
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            this.currentTokenType = 21;
                            continue block43;
                        }
                        case '\"': {
                            this.currentTokenType = 37;
                            continue block43;
                        }
                        case '%': {
                            this.currentTokenType = 17;
                            continue block43;
                        }
                        case '(': 
                        case ')': {
                            this.addToken(text, this.currentTokenStart, i, 22, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        case ',': 
                        case ';': {
                            this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        case ':': {
                            if (this.firstToken == null) {
                                if (i < end - 1 && array[i + 1] == ':') {
                                    this.currentTokenType = 1;
                                    continue block43;
                                }
                                this.currentTokenType = 24;
                                continue block43;
                            }
                            this.currentTokenType = 20;
                            continue block43;
                        }
                    }
                    if (RSyntaxUtilities.isLetterOrDigit(c) || c == '\\') {
                        this.currentTokenType = 20;
                        continue block43;
                    }
                    int indexOf = OPERATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i, 23, newStartOffset + this.currentTokenStart);
                        this.currentTokenType = 0;
                        continue block43;
                    }
                    this.currentTokenType = 20;
                    continue block43;
                }
                case 21: {
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            continue block43;
                        }
                        case '\"': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 37;
                            continue block43;
                        }
                        case '%': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 17;
                            continue block43;
                        }
                        case '(': 
                        case ')': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 22, newStartOffset + i);
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        case ',': 
                        case ';': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 20, newStartOffset + i);
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        case ':': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            if (this.firstToken.getNextToken() == null) {
                                if (i < end - 1 && array[i + 1] == ':') {
                                    this.currentTokenType = 1;
                                    continue block43;
                                }
                                this.currentTokenType = 24;
                                continue block43;
                            }
                            this.currentTokenType = 20;
                            continue block43;
                        }
                    }
                    this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                    this.currentTokenStart = i;
                    if (RSyntaxUtilities.isLetterOrDigit(c) || c == '\\') {
                        this.currentTokenType = 20;
                        continue block43;
                    }
                    int indexOf = OPERATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i, 23, newStartOffset + this.currentTokenStart);
                        this.currentTokenType = 0;
                        continue block43;
                    }
                    this.currentTokenType = 20;
                    continue block43;
                }
                default: {
                    int indexOf;
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            if (!(i - this.currentTokenStart != 3 || array[i - 3] != 'r' && array[i - 3] != 'R' || array[i - 2] != 'e' && array[i - 2] != 'E' || array[i - 1] != 'm' && array[i - 1] != 'M')) {
                                this.currentTokenType = 1;
                                break;
                            }
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 21;
                            break;
                        }
                        case '\"': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 37;
                            break;
                        }
                        case '%': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 17;
                            break;
                        }
                        case '\\': {
                            if (i - this.currentTokenStart != 3 || array[i - 3] != 'r' && array[i - 3] != 'R' || array[i - 2] != 'e' && array[i - 2] != 'E' || array[i - 1] != 'm' && array[i - 1] != 'M') continue block43;
                            this.currentTokenType = 1;
                            break;
                        }
                        case '.': 
                        case '_': {
                            break;
                        }
                        case '(': 
                        case ')': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 22, newStartOffset + i);
                            this.currentTokenType = 0;
                            break;
                        }
                        case ',': 
                        case ';': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 20, newStartOffset + i);
                            this.currentTokenType = 0;
                            break;
                        }
                        default: {
                            if (RSyntaxUtilities.isLetterOrDigit(c)) continue block43;
                            if (c == '\\') break;
                            indexOf = OPERATORS.indexOf(c);
                            if (indexOf <= -1) continue block43;
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 23, newStartOffset + i);
                            this.currentTokenType = 0;
                            break;
                        }
                    }
                    continue block43;
                }
                case 1: {
                    i = end - 1;
                    this.addToken(text, this.currentTokenStart, i, 1, newStartOffset + this.currentTokenStart);
                    this.currentTokenType = 0;
                    continue block43;
                }
                case 24: {
                    i = end - 1;
                    this.addToken(text, this.currentTokenStart, i, 24, newStartOffset + this.currentTokenStart);
                    this.currentTokenType = 0;
                    continue block43;
                }
                case 37: {
                    if (c != '\"') continue block43;
                    this.addToken(text, this.currentTokenStart, i, 13, newStartOffset + this.currentTokenStart);
                    this.currentTokenStart = i + 1;
                    this.currentTokenType = 0;
                    continue block43;
                }
                case 17: {
                    if (i == this.currentTokenStart + 1) {
                        this.varType = VariableType.NORMAL_VAR;
                        switch (c) {
                            case '{': {
                                this.varType = VariableType.BRACKET_VAR;
                                continue block43;
                            }
                            case '~': {
                                this.varType = VariableType.TILDE_VAR;
                                continue block43;
                            }
                            case '%': {
                                this.varType = VariableType.DOUBLE_PERCENT_VAR;
                                continue block43;
                            }
                        }
                        if (RSyntaxUtilities.isLetter(c) || c == '_' || c == ' ') continue block43;
                        if (RSyntaxUtilities.isDigit(c)) {
                            this.addToken(text, this.currentTokenStart, i, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                        --i;
                        this.currentTokenType = 0;
                        continue block43;
                    }
                    switch (this.varType) {
                        case BRACKET_VAR: {
                            if (c != '}') continue block43;
                            this.addToken(text, this.currentTokenStart, i, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        case TILDE_VAR: {
                            if (RSyntaxUtilities.isLetterOrDigit(c)) continue block43;
                            this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                            --i;
                            this.currentTokenType = 0;
                            continue block43;
                        }
                        case DOUBLE_PERCENT_VAR: {
                            if (c == '%') {
                                if (i >= end - 1 || array[i + 1] != '%') continue block43;
                                this.addToken(text, this.currentTokenStart, ++i, 17, newStartOffset + this.currentTokenStart);
                                this.currentTokenType = 0;
                                continue block43;
                            }
                            if (RSyntaxUtilities.isLetterOrDigit(c) || c == ':' || c == '~' || c == ',' || c == '-') continue block43;
                            this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            --i;
                            continue block43;
                        }
                        default: {
                            if (c != '%') continue block43;
                            this.addToken(text, this.currentTokenStart, i, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                        }
                    }
                }
            }
        }
        if (this.currentTokenType != 0) {
            if (!(end - this.currentTokenStart != 3 || array[end - 3] != 'r' && array[end - 3] != 'R' || array[end - 2] != 'e' && array[end - 2] != 'E' || array[end - 1] != 'm' && array[end - 1] != 'M')) {
                this.currentTokenType = 1;
            }
            this.addToken(text, this.currentTokenStart, end - 1, this.currentTokenType, newStartOffset + this.currentTokenStart);
        }
        this.addNullToken();
        return this.firstToken;
    }

    private static enum VariableType {
        BRACKET_VAR,
        TILDE_VAR,
        NORMAL_VAR,
        DOUBLE_PERCENT_VAR;

    }
}

