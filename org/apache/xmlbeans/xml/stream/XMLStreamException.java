/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.xmlbeans.xml.stream.utils.NestedThrowable;

public class XMLStreamException
extends IOException
implements NestedThrowable {
    protected Throwable th;

    public XMLStreamException() {
    }

    public XMLStreamException(String msg) {
        super(msg);
    }

    public XMLStreamException(Throwable th) {
        this.th = th;
    }

    public XMLStreamException(String msg, Throwable th) {
        super(msg);
        this.th = th;
    }

    public Throwable getNestedException() {
        return this.getNested();
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (msg == null && this.th != null) {
            return this.th.getMessage();
        }
        return msg;
    }

    @Override
    public Throwable getNested() {
        return this.th;
    }

    @Override
    public String superToString() {
        return super.toString();
    }

    @Override
    public void superPrintStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
    }

    @Override
    public void superPrintStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
    }

    @Override
    public String toString() {
        return NestedThrowable.Util.toString(this);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        NestedThrowable.Util.printStackTrace((NestedThrowable)this, s);
    }

    @Override
    public void printStackTrace(PrintWriter w) {
        NestedThrowable.Util.printStackTrace((NestedThrowable)this, w);
    }

    @Override
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
}

