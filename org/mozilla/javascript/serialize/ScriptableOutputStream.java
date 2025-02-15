/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.serialize;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.UniqueTag;

public class ScriptableOutputStream
extends ObjectOutputStream {
    private Scriptable scope;
    private Map<Object, String> table;

    public ScriptableOutputStream(OutputStream out, Scriptable scope) throws IOException {
        super(out);
        this.scope = scope;
        this.table = new HashMap<Object, String>();
        this.table.put(scope, "");
        this.enableReplaceObject(true);
        this.excludeStandardObjectNames();
    }

    public void excludeAllIds(Object[] ids) {
        for (Object id : ids) {
            if (!(id instanceof String) || !(this.scope.get((String)id, this.scope) instanceof Scriptable)) continue;
            this.addExcludedName((String)id);
        }
    }

    public void addOptionalExcludedName(String name) {
        Object obj = ScriptableOutputStream.lookupQualifiedName(this.scope, name);
        if (obj != null && obj != UniqueTag.NOT_FOUND) {
            if (!(obj instanceof Scriptable)) {
                throw new IllegalArgumentException("Object for excluded name " + name + " is not a Scriptable, it is " + obj.getClass().getName());
            }
            this.table.put(obj, name);
        }
    }

    public void addExcludedName(String name) {
        Object obj = ScriptableOutputStream.lookupQualifiedName(this.scope, name);
        if (!(obj instanceof Scriptable)) {
            throw new IllegalArgumentException("Object for excluded name " + name + " not found.");
        }
        this.table.put(obj, name);
    }

    public boolean hasExcludedName(String name) {
        return this.table.get(name) != null;
    }

    public void removeExcludedName(String name) {
        this.table.remove(name);
    }

    public void excludeStandardObjectNames() {
        String[] names = new String[]{"Object", "Object.prototype", "Function", "Function.prototype", "String", "String.prototype", "Math", "Array", "Array.prototype", "Error", "Error.prototype", "Number", "Number.prototype", "Date", "Date.prototype", "RegExp", "RegExp.prototype", "Script", "Script.prototype", "Continuation", "Continuation.prototype"};
        for (int i = 0; i < names.length; ++i) {
            this.addExcludedName(names[i]);
        }
        String[] optionalNames = new String[]{"XML", "XML.prototype", "XMLList", "XMLList.prototype"};
        for (int i = 0; i < optionalNames.length; ++i) {
            this.addOptionalExcludedName(optionalNames[i]);
        }
    }

    static Object lookupQualifiedName(Scriptable scope, String qualifiedName) {
        String s;
        StringTokenizer st = new StringTokenizer(qualifiedName, ".");
        Object result = scope;
        while (st.hasMoreTokens() && (result = ScriptableObject.getProperty((Scriptable)result, s = st.nextToken())) != null && result instanceof Scriptable) {
        }
        return result;
    }

    @Override
    protected Object replaceObject(Object obj) throws IOException {
        String name = this.table.get(obj);
        if (name == null) {
            return obj;
        }
        return new PendingLookup(name);
    }

    static class PendingLookup
    implements Serializable {
        static final long serialVersionUID = -2692990309789917727L;
        private String name;

        PendingLookup(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }
    }
}

