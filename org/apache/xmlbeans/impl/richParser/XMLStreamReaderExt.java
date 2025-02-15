/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.richParser;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlCalendar;

public interface XMLStreamReaderExt
extends XMLStreamReader {
    public static final int WS_PRESERVE = 1;
    public static final int WS_REPLACE = 2;
    public static final int WS_COLLAPSE = 3;

    public String getStringValue() throws XMLStreamException;

    public String getStringValue(int var1) throws XMLStreamException;

    public boolean getBooleanValue() throws XMLStreamException;

    public byte getByteValue() throws XMLStreamException;

    public short getShortValue() throws XMLStreamException;

    public int getIntValue() throws XMLStreamException;

    public long getLongValue() throws XMLStreamException;

    public BigInteger getBigIntegerValue() throws XMLStreamException;

    public BigDecimal getBigDecimalValue() throws XMLStreamException;

    public float getFloatValue() throws XMLStreamException;

    public double getDoubleValue() throws XMLStreamException;

    public InputStream getHexBinaryValue() throws XMLStreamException;

    public InputStream getBase64Value() throws XMLStreamException;

    public XmlCalendar getCalendarValue() throws XMLStreamException;

    public Date getDateValue() throws XMLStreamException;

    public GDate getGDateValue() throws XMLStreamException;

    public GDuration getGDurationValue() throws XMLStreamException;

    public QName getQNameValue() throws XMLStreamException;

    public String getAttributeStringValue(int var1) throws XMLStreamException;

    public String getAttributeStringValue(int var1, int var2) throws XMLStreamException;

    public boolean getAttributeBooleanValue(int var1) throws XMLStreamException;

    public byte getAttributeByteValue(int var1) throws XMLStreamException;

    public short getAttributeShortValue(int var1) throws XMLStreamException;

    public int getAttributeIntValue(int var1) throws XMLStreamException;

    public long getAttributeLongValue(int var1) throws XMLStreamException;

    public BigInteger getAttributeBigIntegerValue(int var1) throws XMLStreamException;

    public BigDecimal getAttributeBigDecimalValue(int var1) throws XMLStreamException;

    public float getAttributeFloatValue(int var1) throws XMLStreamException;

    public double getAttributeDoubleValue(int var1) throws XMLStreamException;

    public InputStream getAttributeHexBinaryValue(int var1) throws XMLStreamException;

    public InputStream getAttributeBase64Value(int var1) throws XMLStreamException;

    public XmlCalendar getAttributeCalendarValue(int var1) throws XMLStreamException;

    public Date getAttributeDateValue(int var1) throws XMLStreamException;

    public GDate getAttributeGDateValue(int var1) throws XMLStreamException;

    public GDuration getAttributeGDurationValue(int var1) throws XMLStreamException;

    public QName getAttributeQNameValue(int var1) throws XMLStreamException;

    public String getAttributeStringValue(String var1, String var2) throws XMLStreamException;

    public String getAttributeStringValue(String var1, String var2, int var3) throws XMLStreamException;

    public boolean getAttributeBooleanValue(String var1, String var2) throws XMLStreamException;

    public byte getAttributeByteValue(String var1, String var2) throws XMLStreamException;

    public short getAttributeShortValue(String var1, String var2) throws XMLStreamException;

    public int getAttributeIntValue(String var1, String var2) throws XMLStreamException;

    public long getAttributeLongValue(String var1, String var2) throws XMLStreamException;

    public BigInteger getAttributeBigIntegerValue(String var1, String var2) throws XMLStreamException;

    public BigDecimal getAttributeBigDecimalValue(String var1, String var2) throws XMLStreamException;

    public float getAttributeFloatValue(String var1, String var2) throws XMLStreamException;

    public double getAttributeDoubleValue(String var1, String var2) throws XMLStreamException;

    public InputStream getAttributeHexBinaryValue(String var1, String var2) throws XMLStreamException;

    public InputStream getAttributeBase64Value(String var1, String var2) throws XMLStreamException;

    public XmlCalendar getAttributeCalendarValue(String var1, String var2) throws XMLStreamException;

    public Date getAttributeDateValue(String var1, String var2) throws XMLStreamException;

    public GDate getAttributeGDateValue(String var1, String var2) throws XMLStreamException;

    public GDuration getAttributeGDurationValue(String var1, String var2) throws XMLStreamException;

    public QName getAttributeQNameValue(String var1, String var2) throws XMLStreamException;

    public void setDefaultValue(String var1) throws XMLStreamException;
}

