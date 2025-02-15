/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.JavaSourceCompletion;

interface MemberCompletion
extends JavaSourceCompletion {
    public String getEnclosingClassName(boolean var1);

    public String getSignature();

    public String getType();

    public boolean isDeprecated();

    public static interface Data
    extends IconFactory.IconData {
        public String getEnclosingClassName(boolean var1);

        public String getSignature();

        public String getSummary();

        public String getType();

        public boolean isConstructor();
    }
}

