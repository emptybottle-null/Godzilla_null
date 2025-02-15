/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import javassist.ClassPath;

final class ClassPathList {
    ClassPathList next;
    ClassPath path;

    ClassPathList(ClassPath p, ClassPathList n) {
        this.next = n;
        this.path = p;
    }
}

