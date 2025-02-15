/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.util.Properties;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Environment
extends ScriptableObject {
    static final long serialVersionUID = -430727378460177065L;
    private Environment thePrototypeInstance = null;

    public static void defineClass(ScriptableObject scope) {
        try {
            ScriptableObject.defineClass(scope, Environment.class);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public String getClassName() {
        return "Environment";
    }

    public Environment() {
        if (this.thePrototypeInstance == null) {
            this.thePrototypeInstance = this;
        }
    }

    public Environment(ScriptableObject scope) {
        this.setParentScope(scope);
        Object ctor = ScriptRuntime.getTopLevelProp(scope, "Environment");
        if (ctor != null && ctor instanceof Scriptable) {
            Scriptable s = (Scriptable)ctor;
            this.setPrototype((Scriptable)s.get("prototype", s));
        }
    }

    @Override
    public boolean has(String name, Scriptable start) {
        if (this == this.thePrototypeInstance) {
            return super.has(name, start);
        }
        return System.getProperty(name) != null;
    }

    @Override
    public Object get(String name, Scriptable start) {
        if (this == this.thePrototypeInstance) {
            return super.get(name, start);
        }
        String result = System.getProperty(name);
        if (result != null) {
            return ScriptRuntime.toObject(this.getParentScope(), result);
        }
        return Scriptable.NOT_FOUND;
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
        if (this == this.thePrototypeInstance) {
            super.put(name, start, value);
        } else {
            System.getProperties().put(name, ScriptRuntime.toString(value));
        }
    }

    private Object[] collectIds() {
        Properties props = System.getProperties();
        return props.keySet().toArray();
    }

    @Override
    public Object[] getIds() {
        if (this == this.thePrototypeInstance) {
            return super.getIds();
        }
        return this.collectIds();
    }

    @Override
    public Object[] getAllIds() {
        if (this == this.thePrototypeInstance) {
            return super.getAllIds();
        }
        return this.collectIds();
    }
}

