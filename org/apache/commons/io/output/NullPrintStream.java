/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.PrintStream;
import org.apache.commons.io.output.NullOutputStream;

public class NullPrintStream
extends PrintStream {
    public static final NullPrintStream NULL_PRINT_STREAM = new NullPrintStream();

    public NullPrintStream() {
        super(NullOutputStream.NULL_OUTPUT_STREAM);
    }
}

