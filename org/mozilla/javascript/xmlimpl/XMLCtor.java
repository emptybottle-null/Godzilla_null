/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xmlimpl.XML;
import org.mozilla.javascript.xmlimpl.XMLList;
import org.mozilla.javascript.xmlimpl.XmlProcessor;

class XMLCtor
extends IdFunctionObject {
    static final long serialVersionUID = -8708195078359817341L;
    private static final Object XMLCTOR_TAG = "XMLCtor";
    private XmlProcessor options;
    private static final int Id_ignoreComments = 1;
    private static final int Id_ignoreProcessingInstructions = 2;
    private static final int Id_ignoreWhitespace = 3;
    private static final int Id_prettyIndent = 4;
    private static final int Id_prettyPrinting = 5;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int Id_defaultSettings = 1;
    private static final int Id_settings = 2;
    private static final int Id_setSettings = 3;
    private static final int MAX_FUNCTION_ID = 3;

    XMLCtor(XML xml, Object tag, int id, int arity) {
        super(xml, tag, id, arity);
        this.options = xml.getProcessor();
        this.activatePrototypeMap(3);
    }

    private void writeSetting(Scriptable target) {
        for (int i = 1; i <= 5; ++i) {
            int id = super.getMaxInstanceId() + i;
            String name = this.getInstanceIdName(id);
            Object value = this.getInstanceIdValue(id);
            ScriptableObject.putProperty(target, name, value);
        }
    }

    private void readSettings(Scriptable source) {
        block4: for (int i = 1; i <= 5; ++i) {
            int id = super.getMaxInstanceId() + i;
            String name = this.getInstanceIdName(id);
            Object value = ScriptableObject.getProperty(source, name);
            if (value == Scriptable.NOT_FOUND) continue;
            switch (i) {
                case 1: 
                case 2: 
                case 3: 
                case 5: {
                    if (value instanceof Boolean) break;
                    continue block4;
                }
                case 4: {
                    if (value instanceof Number) break;
                    continue block4;
                }
                default: {
                    throw new IllegalStateException();
                }
            }
            this.setInstanceIdValue(id, value);
        }
    }

    @Override
    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 5;
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        int attr;
        int id = 0;
        String X = null;
        switch (s.length()) {
            case 12: {
                X = "prettyIndent";
                id = 4;
                break;
            }
            case 14: {
                char c = s.charAt(0);
                if (c == 'i') {
                    X = "ignoreComments";
                    id = 1;
                    break;
                }
                if (c != 'p') break;
                X = "prettyPrinting";
                id = 5;
                break;
            }
            case 16: {
                X = "ignoreWhitespace";
                id = 3;
                break;
            }
            case 28: {
                X = "ignoreProcessingInstructions";
                id = 2;
                break;
            }
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        switch (id) {
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                attr = 6;
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
        return XMLCtor.instanceIdInfo(attr, super.getMaxInstanceId() + id);
    }

    @Override
    protected String getInstanceIdName(int id) {
        switch (id - super.getMaxInstanceId()) {
            case 1: {
                return "ignoreComments";
            }
            case 2: {
                return "ignoreProcessingInstructions";
            }
            case 3: {
                return "ignoreWhitespace";
            }
            case 4: {
                return "prettyIndent";
            }
            case 5: {
                return "prettyPrinting";
            }
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        switch (id - super.getMaxInstanceId()) {
            case 1: {
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreComments());
            }
            case 2: {
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreProcessingInstructions());
            }
            case 3: {
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreWhitespace());
            }
            case 4: {
                return ScriptRuntime.wrapInt(this.options.getPrettyIndent());
            }
            case 5: {
                return ScriptRuntime.wrapBoolean(this.options.isPrettyPrinting());
            }
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value) {
        switch (id - super.getMaxInstanceId()) {
            case 1: {
                this.options.setIgnoreComments(ScriptRuntime.toBoolean(value));
                return;
            }
            case 2: {
                this.options.setIgnoreProcessingInstructions(ScriptRuntime.toBoolean(value));
                return;
            }
            case 3: {
                this.options.setIgnoreWhitespace(ScriptRuntime.toBoolean(value));
                return;
            }
            case 4: {
                this.options.setPrettyIndent(ScriptRuntime.toInt32(value));
                return;
            }
            case 5: {
                this.options.setPrettyPrinting(ScriptRuntime.toBoolean(value));
                return;
            }
        }
        super.setInstanceIdValue(id, value);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block5: {
            id = 0;
            String X = null;
            int s_length = s.length();
            if (s_length == 8) {
                X = "settings";
                id = 2;
            } else if (s_length == 11) {
                X = "setSettings";
                id = 3;
            } else if (s_length == 15) {
                X = "defaultSettings";
                id = 1;
            }
            if (X == null || X == s || X.equals(s)) break block5;
            id = 0;
        }
        return id;
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 0;
                s = "defaultSettings";
                break;
            }
            case 2: {
                arity = 0;
                s = "settings";
                break;
            }
            case 3: {
                arity = 1;
                s = "setSettings";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(XMLCTOR_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(XMLCTOR_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                this.options.setDefault();
                Scriptable obj = cx.newObject(scope);
                this.writeSetting(obj);
                return obj;
            }
            case 2: {
                Scriptable obj = cx.newObject(scope);
                this.writeSetting(obj);
                return obj;
            }
            case 3: {
                if (args.length == 0 || args[0] == null || args[0] == Undefined.instance) {
                    this.options.setDefault();
                } else if (args[0] instanceof Scriptable) {
                    this.readSettings((Scriptable)args[0]);
                }
                return Undefined.instance;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    @Override
    public boolean hasInstance(Scriptable instance) {
        return instance instanceof XML || instance instanceof XMLList;
    }
}

