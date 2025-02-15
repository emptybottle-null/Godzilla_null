/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.regexp;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.regexp.NativeRegExp;
import org.mozilla.javascript.regexp.RegExpImpl;

class NativeRegExpCtor
extends BaseFunction {
    static final long serialVersionUID = -5733330028285400526L;
    private static final int Id_multiline = 1;
    private static final int Id_STAR = 2;
    private static final int Id_input = 3;
    private static final int Id_UNDERSCORE = 4;
    private static final int Id_lastMatch = 5;
    private static final int Id_AMPERSAND = 6;
    private static final int Id_lastParen = 7;
    private static final int Id_PLUS = 8;
    private static final int Id_leftContext = 9;
    private static final int Id_BACK_QUOTE = 10;
    private static final int Id_rightContext = 11;
    private static final int Id_QUOTE = 12;
    private static final int DOLLAR_ID_BASE = 12;
    private static final int Id_DOLLAR_1 = 13;
    private static final int Id_DOLLAR_2 = 14;
    private static final int Id_DOLLAR_3 = 15;
    private static final int Id_DOLLAR_4 = 16;
    private static final int Id_DOLLAR_5 = 17;
    private static final int Id_DOLLAR_6 = 18;
    private static final int Id_DOLLAR_7 = 19;
    private static final int Id_DOLLAR_8 = 20;
    private static final int Id_DOLLAR_9 = 21;
    private static final int MAX_INSTANCE_ID = 21;
    private int multilineAttr = 4;
    private int starAttr = 4;
    private int inputAttr = 4;
    private int underscoreAttr = 4;

    NativeRegExpCtor() {
    }

    @Override
    public String getFunctionName() {
        return "RegExp";
    }

    @Override
    public int getLength() {
        return 2;
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (args.length > 0 && args[0] instanceof NativeRegExp && (args.length == 1 || args[1] == Undefined.instance)) {
            return args[0];
        }
        return this.construct(cx, scope, args);
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        NativeRegExp re = new NativeRegExp();
        re.compile(cx, scope, args);
        ScriptRuntime.setBuiltinProtoAndParent(re, scope, TopLevel.Builtins.RegExp);
        return re;
    }

    private static RegExpImpl getImpl() {
        Context cx = Context.getCurrentContext();
        return (RegExpImpl)ScriptRuntime.getRegExpProxy(cx);
    }

    @Override
    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 21;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected int findInstanceIdInfo(String s) {
        int attr;
        int id;
        block34: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 2: {
                    switch (s.charAt(1)) {
                        case '&': {
                            if (s.charAt(0) != '$') break;
                            id = 6;
                            break block34;
                        }
                        case '\'': {
                            if (s.charAt(0) != '$') break;
                            id = 12;
                            break block34;
                        }
                        case '*': {
                            if (s.charAt(0) != '$') break;
                            id = 2;
                            break block34;
                        }
                        case '+': {
                            if (s.charAt(0) != '$') break;
                            id = 8;
                            break block34;
                        }
                        case '1': {
                            if (s.charAt(0) != '$') break;
                            id = 13;
                            break block34;
                        }
                        case '2': {
                            if (s.charAt(0) != '$') break;
                            id = 14;
                            break block34;
                        }
                        case '3': {
                            if (s.charAt(0) != '$') break;
                            id = 15;
                            break block34;
                        }
                        case '4': {
                            if (s.charAt(0) != '$') break;
                            id = 16;
                            break block34;
                        }
                        case '5': {
                            if (s.charAt(0) != '$') break;
                            id = 17;
                            break block34;
                        }
                        case '6': {
                            if (s.charAt(0) != '$') break;
                            id = 18;
                            break block34;
                        }
                        case '7': {
                            if (s.charAt(0) != '$') break;
                            id = 19;
                            break block34;
                        }
                        case '8': {
                            if (s.charAt(0) != '$') break;
                            id = 20;
                            break block34;
                        }
                        case '9': {
                            if (s.charAt(0) != '$') break;
                            id = 21;
                            break block34;
                        }
                        case '_': {
                            if (s.charAt(0) != '$') break;
                            id = 4;
                            break block34;
                        }
                        case '`': {
                            if (s.charAt(0) != '$') break;
                            id = 10;
                            break block34;
                        }
                    }
                    break;
                }
                case 5: {
                    X = "input";
                    id = 3;
                    break;
                }
                case 9: {
                    char c = s.charAt(4);
                    if (c == 'M') {
                        X = "lastMatch";
                        id = 5;
                        break;
                    }
                    if (c == 'P') {
                        X = "lastParen";
                        id = 7;
                        break;
                    }
                    if (c != 'i') break;
                    X = "multiline";
                    id = 1;
                    break;
                }
                case 11: {
                    X = "leftContext";
                    id = 9;
                    break;
                }
                case 12: {
                    X = "rightContext";
                    id = 11;
                }
            }
            if (X != null && X != s && !X.equals(s)) {
                id = 0;
            }
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        switch (id) {
            case 1: {
                attr = this.multilineAttr;
                return NativeRegExpCtor.instanceIdInfo(attr, super.getMaxInstanceId() + id);
            }
            case 2: {
                attr = this.starAttr;
                return NativeRegExpCtor.instanceIdInfo(attr, super.getMaxInstanceId() + id);
            }
            case 3: {
                attr = this.inputAttr;
                return NativeRegExpCtor.instanceIdInfo(attr, super.getMaxInstanceId() + id);
            }
            case 4: {
                attr = this.underscoreAttr;
                return NativeRegExpCtor.instanceIdInfo(attr, super.getMaxInstanceId() + id);
            }
        }
        attr = 5;
        return NativeRegExpCtor.instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }

    @Override
    protected String getInstanceIdName(int id) {
        int shifted = id - super.getMaxInstanceId();
        if (1 <= shifted && shifted <= 21) {
            switch (shifted) {
                case 1: {
                    return "multiline";
                }
                case 2: {
                    return "$*";
                }
                case 3: {
                    return "input";
                }
                case 4: {
                    return "$_";
                }
                case 5: {
                    return "lastMatch";
                }
                case 6: {
                    return "$&";
                }
                case 7: {
                    return "lastParen";
                }
                case 8: {
                    return "$+";
                }
                case 9: {
                    return "leftContext";
                }
                case 10: {
                    return "$`";
                }
                case 11: {
                    return "rightContext";
                }
                case 12: {
                    return "$'";
                }
            }
            int substring_number = shifted - 12 - 1;
            char[] buf = new char[]{'$', (char)(49 + substring_number)};
            return new String(buf);
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        int shifted = id - super.getMaxInstanceId();
        if (1 <= shifted && shifted <= 21) {
            Object stringResult;
            RegExpImpl impl = NativeRegExpCtor.getImpl();
            switch (shifted) {
                case 1: 
                case 2: {
                    return ScriptRuntime.wrapBoolean(impl.multiline);
                }
                case 3: 
                case 4: {
                    stringResult = impl.input;
                    break;
                }
                case 5: 
                case 6: {
                    stringResult = impl.lastMatch;
                    break;
                }
                case 7: 
                case 8: {
                    stringResult = impl.lastParen;
                    break;
                }
                case 9: 
                case 10: {
                    stringResult = impl.leftContext;
                    break;
                }
                case 11: 
                case 12: {
                    stringResult = impl.rightContext;
                    break;
                }
                default: {
                    int substring_number = shifted - 12 - 1;
                    stringResult = impl.getParenSubString(substring_number);
                    break;
                }
            }
            return stringResult == null ? "" : stringResult.toString();
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value) {
        int shifted = id - super.getMaxInstanceId();
        switch (shifted) {
            case 1: 
            case 2: {
                NativeRegExpCtor.getImpl().multiline = ScriptRuntime.toBoolean(value);
                return;
            }
            case 3: 
            case 4: {
                NativeRegExpCtor.getImpl().input = ScriptRuntime.toString(value);
                return;
            }
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                return;
            }
        }
        int substring_number = shifted - 12 - 1;
        if (0 <= substring_number && substring_number <= 8) {
            return;
        }
        super.setInstanceIdValue(id, value);
    }

    @Override
    protected void setInstanceIdAttributes(int id, int attr) {
        int shifted = id - super.getMaxInstanceId();
        switch (shifted) {
            case 1: {
                this.multilineAttr = attr;
                return;
            }
            case 2: {
                this.starAttr = attr;
                return;
            }
            case 3: {
                this.inputAttr = attr;
                return;
            }
            case 4: {
                this.underscoreAttr = attr;
                return;
            }
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                return;
            }
        }
        int substring_number = shifted - 12 - 1;
        if (0 <= substring_number && substring_number <= 8) {
            return;
        }
        super.setInstanceIdAttributes(id, attr);
    }
}

