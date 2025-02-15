/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.reflect;

import javassist.tools.reflect.ClassMetaobject;
import javassist.tools.reflect.Metalevel;
import javassist.tools.reflect.Metaobject;

public class Sample {
    private Metaobject _metaobject;
    private static ClassMetaobject _classobject;

    public Object trap(Object[] args, int identifier) throws Throwable {
        Metaobject mobj = this._metaobject;
        if (mobj == null) {
            return ClassMetaobject.invoke(this, identifier, args);
        }
        return mobj.trapMethodcall(identifier, args);
    }

    public static Object trapStatic(Object[] args, int identifier) throws Throwable {
        return _classobject.trapMethodcall(identifier, args);
    }

    public static Object trapRead(Object[] args, String name) {
        if (args[0] == null) {
            return _classobject.trapFieldRead(name);
        }
        return ((Metalevel)args[0])._getMetaobject().trapFieldRead(name);
    }

    public static Object trapWrite(Object[] args, String name) {
        Metalevel base = (Metalevel)args[0];
        if (base == null) {
            _classobject.trapFieldWrite(name, args[1]);
        } else {
            base._getMetaobject().trapFieldWrite(name, args[1]);
        }
        return null;
    }
}

