/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import org.apache.commons.logging.Log;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleSaxErrorHandler
implements ErrorHandler {
    private final Log logger;

    public SimpleSaxErrorHandler(Log logger) {
        this.logger = logger;
    }

    @Override
    public void warning(SAXParseException ex) throws SAXException {
        this.logger.warn("Ignored XML validation warning", ex);
    }

    @Override
    public void error(SAXParseException ex) throws SAXException {
        throw ex;
    }

    @Override
    public void fatalError(SAXParseException ex) throws SAXException {
        throw ex;
    }
}

