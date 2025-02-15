/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.file;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import org.apache.commons.io.file.PathVisitor;

public abstract class SimplePathVisitor
extends SimpleFileVisitor<Path>
implements PathVisitor {
    protected SimplePathVisitor() {
    }
}

