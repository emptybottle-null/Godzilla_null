/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

public class UnixShellTokenMaker
extends AbstractTokenMaker {
    private static final String OPERATORS = "=|><&";
    private static final String SEPARATORS = "()[]";
    private static final String SEPARATORS2 = ".,;";
    private static final String shellVariables = "#-?$!*@_";
    private int currentTokenStart;
    private int currentTokenType;

    @Override
    public void addToken(Segment segment, int start, int end, int tokenType, int startOffset) {
        switch (tokenType) {
            case 20: {
                int value = this.wordsToHighlight.get(segment, start, end);
                if (value == -1) break;
                tokenType = value;
                break;
            }
            case 1: 
            case 10: 
            case 13: 
            case 14: 
            case 15: 
            case 17: 
            case 21: 
            case 22: 
            case 23: 
            case 24: {
                break;
            }
            default: {
                tokenType = 20;
            }
        }
        super.addToken(segment, start, end, tokenType, startOffset);
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"#", null};
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 20 || type == 17;
    }

    @Override
    public TokenMap getWordsToHighlight() {
        TokenMap tokenMap = new TokenMap();
        int reservedWord = 6;
        tokenMap.put("case", reservedWord);
        tokenMap.put("do", reservedWord);
        tokenMap.put("done", reservedWord);
        tokenMap.put("elif", reservedWord);
        tokenMap.put("else", reservedWord);
        tokenMap.put("esac", reservedWord);
        tokenMap.put("fi", reservedWord);
        tokenMap.put("for", reservedWord);
        tokenMap.put("if", reservedWord);
        tokenMap.put("in", reservedWord);
        tokenMap.put("select", reservedWord);
        tokenMap.put("then", reservedWord);
        tokenMap.put("until", reservedWord);
        tokenMap.put("while", reservedWord);
        int function = 8;
        tokenMap.put("addbib", function);
        tokenMap.put("admin", function);
        tokenMap.put("alias", function);
        tokenMap.put("apropos", function);
        tokenMap.put("ar", function);
        tokenMap.put("at", function);
        tokenMap.put("awk", function);
        tokenMap.put("banner", function);
        tokenMap.put("basename", function);
        tokenMap.put("batch", function);
        tokenMap.put("bg", function);
        tokenMap.put("biff", function);
        tokenMap.put("bin-mail", function);
        tokenMap.put("binmail", function);
        tokenMap.put("break", function);
        tokenMap.put("cal", function);
        tokenMap.put("calendar", function);
        tokenMap.put("cancel", function);
        tokenMap.put("cat", function);
        tokenMap.put("cb", function);
        tokenMap.put("cc", function);
        tokenMap.put("cd", function);
        tokenMap.put("cdc", function);
        tokenMap.put("chdir", function);
        tokenMap.put("checkeq", function);
        tokenMap.put("checknr", function);
        tokenMap.put("chfn", function);
        tokenMap.put("chgrp", function);
        tokenMap.put("chmod", function);
        tokenMap.put("chown", function);
        tokenMap.put("chsh", function);
        tokenMap.put("clear", function);
        tokenMap.put("cmp", function);
        tokenMap.put("colcrt", function);
        tokenMap.put("comb", function);
        tokenMap.put("comm", function);
        tokenMap.put("command", function);
        tokenMap.put("compress", function);
        tokenMap.put("continue", function);
        tokenMap.put("cp", function);
        tokenMap.put("cpio", function);
        tokenMap.put("cpp", function);
        tokenMap.put("crontab", function);
        tokenMap.put("csh", function);
        tokenMap.put("ctags", function);
        tokenMap.put("curl", function);
        tokenMap.put("cut", function);
        tokenMap.put("cvs", function);
        tokenMap.put("date", function);
        tokenMap.put("dbx", function);
        tokenMap.put("delta", function);
        tokenMap.put("deroff", function);
        tokenMap.put("df", function);
        tokenMap.put("diff", function);
        tokenMap.put("dtree", function);
        tokenMap.put("du", function);
        tokenMap.put("e", function);
        tokenMap.put("echo", function);
        tokenMap.put("ed", function);
        tokenMap.put("edit", function);
        tokenMap.put("enscript", function);
        tokenMap.put("eqn", function);
        tokenMap.put("error", function);
        tokenMap.put("eval", function);
        tokenMap.put("ex", function);
        tokenMap.put("exec", function);
        tokenMap.put("exit", function);
        tokenMap.put("expand", function);
        tokenMap.put("export", function);
        tokenMap.put("expr", function);
        tokenMap.put("false", function);
        tokenMap.put("fc", function);
        tokenMap.put("fg", function);
        tokenMap.put("file", function);
        tokenMap.put("find", function);
        tokenMap.put("finger", function);
        tokenMap.put("fmt", function);
        tokenMap.put("fmt_mail", function);
        tokenMap.put("fold", function);
        tokenMap.put("ftp", function);
        tokenMap.put("function", function);
        tokenMap.put("gcore", function);
        tokenMap.put("get", function);
        tokenMap.put("getopts", function);
        tokenMap.put("gprof", function);
        tokenMap.put("grep", function);
        tokenMap.put("groups", function);
        tokenMap.put("gunzip", function);
        tokenMap.put("gzip", function);
        tokenMap.put("hashcheck", function);
        tokenMap.put("hashmake", function);
        tokenMap.put("head", function);
        tokenMap.put("help", function);
        tokenMap.put("history", function);
        tokenMap.put("imake", function);
        tokenMap.put("indent", function);
        tokenMap.put("install", function);
        tokenMap.put("jobs", function);
        tokenMap.put("join", function);
        tokenMap.put("kill", function);
        tokenMap.put("last", function);
        tokenMap.put("ld", function);
        tokenMap.put("leave", function);
        tokenMap.put("less", function);
        tokenMap.put("let", function);
        tokenMap.put("lex", function);
        tokenMap.put("lint", function);
        tokenMap.put("ln", function);
        tokenMap.put("login", function);
        tokenMap.put("look", function);
        tokenMap.put("lookbib", function);
        tokenMap.put("lorder", function);
        tokenMap.put("lp", function);
        tokenMap.put("lpq", function);
        tokenMap.put("lpr", function);
        tokenMap.put("lprm", function);
        tokenMap.put("ls", function);
        tokenMap.put("mail", function);
        tokenMap.put("Mail", function);
        tokenMap.put("make", function);
        tokenMap.put("man", function);
        tokenMap.put("md", function);
        tokenMap.put("mesg", function);
        tokenMap.put("mkdir", function);
        tokenMap.put("mkstr", function);
        tokenMap.put("more", function);
        tokenMap.put("mount", function);
        tokenMap.put("mv", function);
        tokenMap.put("nawk", function);
        tokenMap.put("neqn", function);
        tokenMap.put("nice", function);
        tokenMap.put("nm", function);
        tokenMap.put("nroff", function);
        tokenMap.put("od", function);
        tokenMap.put("page", function);
        tokenMap.put("passwd", function);
        tokenMap.put("paste", function);
        tokenMap.put("pr", function);
        tokenMap.put("print", function);
        tokenMap.put("printf", function);
        tokenMap.put("printenv", function);
        tokenMap.put("prof", function);
        tokenMap.put("prs", function);
        tokenMap.put("prt", function);
        tokenMap.put("ps", function);
        tokenMap.put("ptx", function);
        tokenMap.put("pwd", function);
        tokenMap.put("quota", function);
        tokenMap.put("ranlib", function);
        tokenMap.put("rcp", function);
        tokenMap.put("rcs", function);
        tokenMap.put("rcsdiff", function);
        tokenMap.put("read", function);
        tokenMap.put("readonly", function);
        tokenMap.put("red", function);
        tokenMap.put("return", function);
        tokenMap.put("rev", function);
        tokenMap.put("rlogin", function);
        tokenMap.put("rm", function);
        tokenMap.put("rmdel", function);
        tokenMap.put("rmdir", function);
        tokenMap.put("roffbib", function);
        tokenMap.put("rsh", function);
        tokenMap.put("rup", function);
        tokenMap.put("ruptime", function);
        tokenMap.put("rusers", function);
        tokenMap.put("rwall", function);
        tokenMap.put("rwho", function);
        tokenMap.put("sact", function);
        tokenMap.put("sccs", function);
        tokenMap.put("sccsdiff", function);
        tokenMap.put("script", function);
        tokenMap.put("sed", function);
        tokenMap.put("set", function);
        tokenMap.put("setgroups", function);
        tokenMap.put("setsenv", function);
        tokenMap.put("sh", function);
        tokenMap.put("shift", function);
        tokenMap.put("size", function);
        tokenMap.put("sleep", function);
        tokenMap.put("sort", function);
        tokenMap.put("sortbib", function);
        tokenMap.put("spell", function);
        tokenMap.put("split", function);
        tokenMap.put("ssh", function);
        tokenMap.put("strings", function);
        tokenMap.put("strip", function);
        tokenMap.put("stty", function);
        tokenMap.put("su", function);
        tokenMap.put("sudo", function);
        tokenMap.put("symorder", function);
        tokenMap.put("tabs", function);
        tokenMap.put("tail", function);
        tokenMap.put("talk", function);
        tokenMap.put("tar", function);
        tokenMap.put("tbl", function);
        tokenMap.put("tee", function);
        tokenMap.put("telnet", function);
        tokenMap.put("test", function);
        tokenMap.put("tftp", function);
        tokenMap.put("time", function);
        tokenMap.put("times", function);
        tokenMap.put("touch", function);
        tokenMap.put("trap", function);
        tokenMap.put("troff", function);
        tokenMap.put("true", function);
        tokenMap.put("tsort", function);
        tokenMap.put("tty", function);
        tokenMap.put("type", function);
        tokenMap.put("typeset", function);
        tokenMap.put("ue", function);
        tokenMap.put("ul", function);
        tokenMap.put("ulimit", function);
        tokenMap.put("umask", function);
        tokenMap.put("unalias", function);
        tokenMap.put("uncompress", function);
        tokenMap.put("unexpand", function);
        tokenMap.put("unget", function);
        tokenMap.put("unifdef", function);
        tokenMap.put("uniq", function);
        tokenMap.put("units", function);
        tokenMap.put("unset", function);
        tokenMap.put("uptime", function);
        tokenMap.put("users", function);
        tokenMap.put("uucp", function);
        tokenMap.put("uudecode", function);
        tokenMap.put("uuencode", function);
        tokenMap.put("uulog", function);
        tokenMap.put("uuname", function);
        tokenMap.put("uusend", function);
        tokenMap.put("uux", function);
        tokenMap.put("vacation", function);
        tokenMap.put("val", function);
        tokenMap.put("vedit", function);
        tokenMap.put("vgrind", function);
        tokenMap.put("vi", function);
        tokenMap.put("view", function);
        tokenMap.put("vtroff", function);
        tokenMap.put("w", function);
        tokenMap.put("wait", function);
        tokenMap.put("wall", function);
        tokenMap.put("wc", function);
        tokenMap.put("wait", function);
        tokenMap.put("what", function);
        tokenMap.put("whatis", function);
        tokenMap.put("whence", function);
        tokenMap.put("whereis", function);
        tokenMap.put("which", function);
        tokenMap.put("who", function);
        tokenMap.put("whoami", function);
        tokenMap.put("write", function);
        tokenMap.put("xargs", function);
        tokenMap.put("xstr", function);
        tokenMap.put("yacc", function);
        tokenMap.put("yes", function);
        tokenMap.put("zcat", function);
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
        boolean backslash = false;
        block60: for (int i = offset; i < end; ++i) {
            char c = array[i];
            switch (this.currentTokenType) {
                case 0: {
                    this.currentTokenStart = i;
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            this.currentTokenType = 21;
                            continue block60;
                        }
                        case '`': {
                            if (backslash) {
                                this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                                backslash = false;
                                continue block60;
                            }
                            this.currentTokenType = 15;
                            continue block60;
                        }
                        case '\"': {
                            if (backslash) {
                                this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                                backslash = false;
                                continue block60;
                            }
                            this.currentTokenType = 13;
                            continue block60;
                        }
                        case '\'': {
                            if (backslash) {
                                this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                                backslash = false;
                                continue block60;
                            }
                            this.currentTokenType = 14;
                            continue block60;
                        }
                        case '\\': {
                            this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            backslash = !backslash;
                            continue block60;
                        }
                        case '$': {
                            if (backslash) {
                                this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                                backslash = false;
                                continue block60;
                            }
                            this.currentTokenType = 17;
                            continue block60;
                        }
                        case '#': {
                            backslash = false;
                            this.currentTokenType = 1;
                            continue block60;
                        }
                    }
                    if (RSyntaxUtilities.isDigit(c)) {
                        this.currentTokenType = 10;
                        continue block60;
                    }
                    if (RSyntaxUtilities.isLetter(c) || c == '/' || c == '_') {
                        this.currentTokenType = 20;
                        continue block60;
                    }
                    int indexOf = OPERATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i, 23, newStartOffset + this.currentTokenStart);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i, 22, newStartOffset + this.currentTokenStart);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS2.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    this.currentTokenType = 20;
                    continue block60;
                }
                case 21: {
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            continue block60;
                        }
                        case '\\': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 20, newStartOffset + i);
                            this.currentTokenType = 0;
                            backslash = true;
                            continue block60;
                        }
                        case '`': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 15;
                            backslash = false;
                            continue block60;
                        }
                        case '\"': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 13;
                            backslash = false;
                            continue block60;
                        }
                        case '\'': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 14;
                            backslash = false;
                            continue block60;
                        }
                        case '$': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 17;
                            backslash = false;
                            continue block60;
                        }
                        case '#': {
                            this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 1;
                            continue block60;
                        }
                    }
                    this.addToken(text, this.currentTokenStart, i - 1, 21, newStartOffset + this.currentTokenStart);
                    this.currentTokenStart = i;
                    if (RSyntaxUtilities.isDigit(c)) {
                        this.currentTokenType = 10;
                        continue block60;
                    }
                    if (RSyntaxUtilities.isLetter(c) || c == '/' || c == '_') {
                        this.currentTokenType = 20;
                        continue block60;
                    }
                    int indexOf = OPERATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, i, i, 23, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, i, i, 22, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS2.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, i, i, 20, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    this.currentTokenType = 20;
                    continue block60;
                }
                default: {
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 21;
                            continue block60;
                        }
                        case '/': {
                            this.addToken(text, this.currentTokenStart, i, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i + 1;
                            this.currentTokenType = 0;
                            continue block60;
                        }
                        case '`': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 15;
                            backslash = false;
                            continue block60;
                        }
                        case '\"': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 13;
                            backslash = false;
                            continue block60;
                        }
                        case '\'': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 14;
                            backslash = false;
                            continue block60;
                        }
                        case '\\': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 20, newStartOffset + i);
                            this.currentTokenType = 0;
                            backslash = true;
                            continue block60;
                        }
                        case '$': {
                            this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 17;
                            backslash = false;
                            continue block60;
                        }
                        case '=': {
                            this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 23, newStartOffset + i);
                            this.currentTokenType = 0;
                            continue block60;
                        }
                    }
                    if (RSyntaxUtilities.isLetterOrDigit(c) || c == '/' || c == '_') continue block60;
                    int indexOf = OPERATORS.indexOf(c);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                        this.addToken(text, i, i, 23, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS.indexOf(c, 0);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                        this.addToken(text, i, i, 22, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS2.indexOf(c, 0);
                    if (indexOf <= -1) continue block60;
                    this.addToken(text, this.currentTokenStart, i - 1, 20, newStartOffset + this.currentTokenStart);
                    this.addToken(text, i, i, 20, newStartOffset + i);
                    this.currentTokenType = 0;
                    continue block60;
                }
                case 10: {
                    switch (c) {
                        case '\t': 
                        case ' ': {
                            this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 21;
                            continue block60;
                        }
                        case '`': {
                            this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 15;
                            backslash = false;
                            continue block60;
                        }
                        case '\"': {
                            this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 13;
                            backslash = false;
                            continue block60;
                        }
                        case '\'': {
                            this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 14;
                            backslash = false;
                            continue block60;
                        }
                        case '$': {
                            this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 17;
                            backslash = false;
                            continue block60;
                        }
                        case '\\': {
                            this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                            this.addToken(text, i, i, 20, newStartOffset + i);
                            this.currentTokenType = 0;
                            backslash = true;
                            continue block60;
                        }
                    }
                    if (RSyntaxUtilities.isDigit(c)) continue block60;
                    int indexOf = OPERATORS.indexOf(c);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                        this.addToken(text, i, i, 23, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS.indexOf(c);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                        this.addToken(text, i, i, 22, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    indexOf = SEPARATORS2.indexOf(c);
                    if (indexOf > -1) {
                        this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                        this.addToken(text, i, i, 20, newStartOffset + i);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    this.addToken(text, this.currentTokenStart, i - 1, 10, newStartOffset + this.currentTokenStart);
                    --i;
                    this.currentTokenType = 0;
                    continue block60;
                }
                case 17: {
                    if (c == '{') {
                        while (++i < end) {
                            if (array[i] != '}') continue;
                            this.addToken(text, this.currentTokenStart, i, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 0;
                            break;
                        }
                        if (i != end) continue block60;
                        this.addToken(text, this.currentTokenStart, end - 1, 17, newStartOffset + this.currentTokenStart);
                        this.currentTokenType = 0;
                        continue block60;
                    }
                    while (i < end) {
                        c = array[i];
                        if (!RSyntaxUtilities.isLetterOrDigit(c) && shellVariables.indexOf(c) == -1 && c != '_') {
                            this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                            --i;
                            this.currentTokenType = 0;
                            break;
                        }
                        ++i;
                    }
                    if (i != end) continue block60;
                    this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                    this.currentTokenType = 0;
                    continue block60;
                }
                case 1: {
                    if (c == '!') {
                        this.currentTokenType = 24;
                    }
                    i = end - 1;
                    this.addToken(text, this.currentTokenStart, i, this.currentTokenType, newStartOffset + this.currentTokenStart);
                    this.currentTokenType = 0;
                    continue block60;
                }
                case 14: {
                    if (c == '\\') {
                        backslash = !backslash;
                        continue block60;
                    }
                    if (c == '\'' && !backslash) {
                        this.addToken(text, this.currentTokenStart, i, 14, newStartOffset + this.currentTokenStart);
                        this.currentTokenStart = i + 1;
                        this.currentTokenType = 0;
                    }
                    backslash = false;
                    continue block60;
                }
                case 15: {
                    switch (c) {
                        case '\\': {
                            backslash = !backslash;
                            break;
                        }
                        case '`': {
                            if (!backslash) {
                                this.addToken(text, this.currentTokenStart, i, 15, newStartOffset + this.currentTokenStart);
                                this.currentTokenType = 0;
                                break;
                            }
                            backslash = false;
                            break;
                        }
                        case '$': {
                            if (backslash) {
                                backslash = false;
                                break;
                            }
                            this.addToken(text, this.currentTokenStart, i - 1, 15, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 17;
                            this.currentTokenStart = i;
                            if (i < end - 1 && array[i + 1] == '{') {
                                ++i;
                                while (++i < end) {
                                    if (array[i] != '}') continue;
                                    this.addToken(text, this.currentTokenStart, i, 17, newStartOffset + this.currentTokenStart);
                                    if (++i < end) {
                                        c = array[i];
                                        if (c == '`') {
                                            this.addToken(text, i, i, 15, newStartOffset + i);
                                            this.currentTokenType = 0;
                                            break;
                                        }
                                        this.currentTokenStart = i--;
                                        this.currentTokenType = 15;
                                        break;
                                    }
                                    this.currentTokenStart = i;
                                    this.currentTokenType = 15;
                                    break;
                                }
                                if (i == end) {
                                    this.addToken(text, this.currentTokenStart, end - 1, 17, newStartOffset + this.currentTokenStart);
                                    this.currentTokenStart = end;
                                    this.currentTokenType = 15;
                                    break;
                                }
                            }
                            if (this.currentTokenType == 0) continue block60;
                            if (this.currentTokenType == 15) break;
                            while (++i < end) {
                                c = array[i];
                                if (RSyntaxUtilities.isLetterOrDigit(c) || shellVariables.indexOf(c) != -1 || c == '_') continue;
                                this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                                if (c == '`') {
                                    this.addToken(text, i, i, 15, newStartOffset + i);
                                    this.currentTokenType = 0;
                                    break;
                                }
                                this.currentTokenStart = i--;
                                this.currentTokenType = 15;
                                break;
                            }
                            if (i != end) continue block60;
                            this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 15;
                            break;
                        }
                        default: {
                            backslash = false;
                            break;
                        }
                    }
                    continue block60;
                }
                case 13: {
                    switch (c) {
                        case '\\': {
                            backslash = !backslash;
                            continue block60;
                        }
                        case '\"': {
                            if (!backslash) {
                                this.addToken(text, this.currentTokenStart, i, 13, newStartOffset + this.currentTokenStart);
                                this.currentTokenType = 0;
                                continue block60;
                            }
                            backslash = false;
                            continue block60;
                        }
                        case '$': {
                            if (backslash) {
                                backslash = false;
                                continue block60;
                            }
                            this.addToken(text, this.currentTokenStart, i - 1, 13, newStartOffset + this.currentTokenStart);
                            this.currentTokenType = 17;
                            this.currentTokenStart = i;
                            if (i < end - 1 && array[i + 1] == '{') {
                                ++i;
                                while (++i < end) {
                                    if (array[i] != '}') continue;
                                    this.addToken(text, this.currentTokenStart, i, 17, newStartOffset + this.currentTokenStart);
                                    if (++i < end) {
                                        c = array[i];
                                        if (c == '\"') {
                                            this.addToken(text, i, i, 13, newStartOffset + i);
                                            this.currentTokenType = 0;
                                            break;
                                        }
                                        this.currentTokenStart = i--;
                                        this.currentTokenType = 13;
                                        break;
                                    }
                                    this.currentTokenStart = i;
                                    this.currentTokenType = 13;
                                    break;
                                }
                                if (i == end) {
                                    this.addToken(text, this.currentTokenStart, end - 1, 17, newStartOffset + this.currentTokenStart);
                                    this.currentTokenStart = end;
                                    this.currentTokenType = 13;
                                    continue block60;
                                }
                            }
                            if (this.currentTokenType == 0 || this.currentTokenType == 13) continue block60;
                            while (++i < end) {
                                c = array[i];
                                if (RSyntaxUtilities.isLetterOrDigit(c) || shellVariables.indexOf(c) != -1 || c == '_') continue;
                                this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                                if (c == '\"') {
                                    this.addToken(text, i, i, 13, newStartOffset + i);
                                    this.currentTokenType = 0;
                                    break;
                                }
                                this.currentTokenStart = i--;
                                this.currentTokenType = 13;
                                break;
                            }
                            if (i != end) continue block60;
                            this.addToken(text, this.currentTokenStart, i - 1, 17, newStartOffset + this.currentTokenStart);
                            this.currentTokenStart = i;
                            this.currentTokenType = 13;
                            continue block60;
                        }
                        default: {
                            backslash = false;
                        }
                    }
                }
            }
        }
        switch (this.currentTokenType) {
            case 13: 
            case 14: 
            case 15: {
                this.addToken(text, this.currentTokenStart, end - 1, this.currentTokenType, newStartOffset + this.currentTokenStart);
                break;
            }
            case 0: {
                this.addNullToken();
                break;
            }
            default: {
                this.addToken(text, this.currentTokenStart, end - 1, this.currentTokenType, newStartOffset + this.currentTokenStart);
                this.addNullToken();
            }
        }
        return this.firstToken;
    }
}

