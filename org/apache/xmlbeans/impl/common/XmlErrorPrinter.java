/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.net.URI;
import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import org.apache.xmlbeans.XmlError;

public class XmlErrorPrinter
extends AbstractCollection {
    private boolean _noisy;
    private URI _baseURI;

    public XmlErrorPrinter(boolean noisy, URI baseURI) {
        this._noisy = noisy;
        this._baseURI = baseURI;
    }

    @Override
    public boolean add(Object o) {
        if (o instanceof XmlError) {
            XmlError err = (XmlError)o;
            if (err.getSeverity() == 0 || err.getSeverity() == 1) {
                System.err.println(err.toString(this._baseURI));
            } else if (this._noisy) {
                System.out.println(err.toString(this._baseURI));
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return Collections.EMPTY_LIST.iterator();
    }

    @Override
    public int size() {
        return 0;
    }
}

