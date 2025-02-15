/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.apache.commons.logging.Log;

public class SimpleTransformErrorListener
implements ErrorListener {
    private final Log logger;

    public SimpleTransformErrorListener(Log logger) {
        this.logger = logger;
    }

    @Override
    public void warning(TransformerException ex) throws TransformerException {
        this.logger.warn("XSLT transformation warning", ex);
    }

    @Override
    public void error(TransformerException ex) throws TransformerException {
        this.logger.error("XSLT transformation error", ex);
    }

    @Override
    public void fatalError(TransformerException ex) throws TransformerException {
        throw ex;
    }
}

