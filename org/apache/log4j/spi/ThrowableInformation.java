/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import java.io.Serializable;
import org.apache.log4j.Category;
import org.apache.log4j.DefaultThrowableRenderer;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;

public class ThrowableInformation
implements Serializable {
    static final long serialVersionUID = -4748765566864322735L;
    private transient Throwable throwable;
    private transient Category category;
    private String[] rep;

    public ThrowableInformation(Throwable throwable) {
        this.throwable = throwable;
    }

    public ThrowableInformation(Throwable throwable, Category category) {
        this.throwable = throwable;
        this.category = category;
    }

    public ThrowableInformation(String[] r) {
        if (r != null) {
            this.rep = (String[])r.clone();
        }
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public synchronized String[] getThrowableStrRep() {
        if (this.rep == null) {
            LoggerRepository repo;
            ThrowableRenderer renderer = null;
            if (this.category != null && (repo = this.category.getLoggerRepository()) instanceof ThrowableRendererSupport) {
                renderer = ((ThrowableRendererSupport)((Object)repo)).getThrowableRenderer();
            }
            this.rep = renderer == null ? DefaultThrowableRenderer.render(this.throwable) : renderer.doRender(this.throwable);
        }
        return (String[])this.rep.clone();
    }
}

