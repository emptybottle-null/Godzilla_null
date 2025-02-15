/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.MemberBox;
import org.mozilla.javascript.NativeJavaMethod;

class BeanProperty {
    MemberBox getter;
    MemberBox setter;
    NativeJavaMethod setters;

    BeanProperty(MemberBox getter, MemberBox setter, NativeJavaMethod setters) {
        this.getter = getter;
        this.setter = setter;
        this.setters = setters;
    }
}

