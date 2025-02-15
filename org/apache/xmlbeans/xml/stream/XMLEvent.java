/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.xml.stream;

import org.apache.xmlbeans.xml.stream.Location;
import org.apache.xmlbeans.xml.stream.XMLName;

public interface XMLEvent {
    public static final int XML_EVENT = 1;
    public static final int START_ELEMENT = 2;
    public static final int END_ELEMENT = 4;
    public static final int PROCESSING_INSTRUCTION = 8;
    public static final int CHARACTER_DATA = 16;
    public static final int COMMENT = 32;
    public static final int SPACE = 64;
    public static final int NULL_ELEMENT = 128;
    public static final int START_DOCUMENT = 256;
    public static final int END_DOCUMENT = 512;
    public static final int START_PREFIX_MAPPING = 1024;
    public static final int END_PREFIX_MAPPING = 2048;
    public static final int CHANGE_PREFIX_MAPPING = 4096;
    public static final int ENTITY_REFERENCE = 8192;

    public int getType();

    public XMLName getSchemaType();

    public String getTypeAsString();

    public XMLName getName();

    public boolean hasName();

    public Location getLocation();

    public boolean isStartElement();

    public boolean isEndElement();

    public boolean isEntityReference();

    public boolean isStartPrefixMapping();

    public boolean isEndPrefixMapping();

    public boolean isChangePrefixMapping();

    public boolean isProcessingInstruction();

    public boolean isCharacterData();

    public boolean isSpace();

    public boolean isNull();

    public boolean isStartDocument();

    public boolean isEndDocument();
}

