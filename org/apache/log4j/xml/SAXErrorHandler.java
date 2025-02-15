/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.xml;

import org.apache.log4j.helpers.LogLog;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler
implements ErrorHandler {
    public void error(SAXParseException ex) {
        SAXErrorHandler.emitMessage("Continuable parsing error ", ex);
    }

    public void fatalError(SAXParseException ex) {
        SAXErrorHandler.emitMessage("Fatal parsing error ", ex);
    }

    public void warning(SAXParseException ex) {
        SAXErrorHandler.emitMessage("Parsing warning ", ex);
    }

    private static void emitMessage(String msg, SAXParseException ex) {
        LogLog.warn(msg + ex.getLineNumber() + " and column " + ex.getColumnNumber());
        LogLog.warn(ex.getMessage(), ex.getException());
    }
}

