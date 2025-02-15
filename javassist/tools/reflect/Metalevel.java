/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.reflect;

import javassist.tools.reflect.ClassMetaobject;
import javassist.tools.reflect.Metaobject;

public interface Metalevel {
    public ClassMetaobject _getClass();

    public Metaobject _getMetaobject();

    public void _setMetaobject(Metaobject var1);
}

