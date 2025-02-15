/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import org.apache.xmlbeans.xml.stream.XMLEvent;

public interface StartDocument
extends XMLEvent {
    public String getSystemId();

    public String getCharacterEncodingScheme();

    public boolean isStandalone();

    public String getVersion();
}

