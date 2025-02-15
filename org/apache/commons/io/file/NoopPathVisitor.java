/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.file;

import org.apache.commons.io.file.SimplePathVisitor;

public class NoopPathVisitor
extends SimplePathVisitor {
    public static final NoopPathVisitor INSTANCE = new NoopPathVisitor();
}

