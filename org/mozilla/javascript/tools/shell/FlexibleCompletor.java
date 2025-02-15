/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

class FlexibleCompletor
implements InvocationHandler {
    private Method completeMethod;
    private Scriptable global;

    FlexibleCompletor(Class<?> completorClass, Scriptable global) throws NoSuchMethodException {
        this.global = global;
        this.completeMethod = completorClass.getMethod("complete", String.class, Integer.TYPE, List.class);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.equals(this.completeMethod)) {
            int result = this.complete((String)args[0], (Integer)args[1], (List)args[2]);
            return result;
        }
        throw new NoSuchMethodError(method.toString());
    }

    public int complete(String buffer, int cursor, List<String> candidates) {
        char c;
        int m;
        for (m = cursor - 1; m >= 0 && (Character.isJavaIdentifierPart(c = buffer.charAt(m)) || c == '.'); --m) {
        }
        String namesAndDots = buffer.substring(m + 1, cursor);
        String[] names = namesAndDots.split("\\.", -1);
        Scriptable obj = this.global;
        for (int i = 0; i < names.length - 1; ++i) {
            Object val = obj.get(names[i], this.global);
            if (!(val instanceof Scriptable)) {
                return buffer.length();
            }
            obj = (Scriptable)val;
        }
        Object[] ids = obj instanceof ScriptableObject ? ((ScriptableObject)obj).getAllIds() : obj.getIds();
        String lastPart = names[names.length - 1];
        for (int i = 0; i < ids.length; ++i) {
            String id;
            if (!(ids[i] instanceof String) || !(id = (String)ids[i]).startsWith(lastPart)) continue;
            if (obj.get(id, obj) instanceof Function) {
                id = id + "(";
            }
            candidates.add(id);
        }
        return buffer.length() - lastPart.length();
    }
}

