/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.DelegateXmlObject;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public abstract class FilterXmlObject
implements XmlObject,
SimpleValue,
DelegateXmlObject {
    @Override
    public SchemaType schemaType() {
        return this.underlyingXmlObject().schemaType();
    }

    @Override
    public boolean validate() {
        return this.underlyingXmlObject().validate();
    }

    @Override
    public boolean validate(XmlOptions options) {
        return this.underlyingXmlObject().validate(options);
    }

    @Override
    public XmlObject[] selectPath(String path) {
        return this.underlyingXmlObject().selectPath(path);
    }

    @Override
    public XmlObject[] selectPath(String path, XmlOptions options) {
        return this.underlyingXmlObject().selectPath(path, options);
    }

    @Override
    public XmlObject[] execQuery(String query) {
        return this.underlyingXmlObject().execQuery(query);
    }

    @Override
    public XmlObject[] execQuery(String query, XmlOptions options) {
        return this.underlyingXmlObject().execQuery(query, options);
    }

    @Override
    public XmlObject changeType(SchemaType newType) {
        return this.underlyingXmlObject().changeType(newType);
    }

    @Override
    public boolean isNil() {
        return this.underlyingXmlObject().isNil();
    }

    @Override
    public void setNil() {
        this.underlyingXmlObject().setNil();
    }

    @Override
    public boolean isImmutable() {
        return this.underlyingXmlObject().isImmutable();
    }

    @Override
    public XmlObject set(XmlObject srcObj) {
        return this.underlyingXmlObject().set(srcObj);
    }

    @Override
    public XmlObject copy() {
        return this.underlyingXmlObject().copy();
    }

    @Override
    public XmlObject copy(XmlOptions options) {
        return this.underlyingXmlObject().copy(options);
    }

    @Override
    public boolean valueEquals(XmlObject obj) {
        return this.underlyingXmlObject().valueEquals(obj);
    }

    @Override
    public int valueHashCode() {
        return this.underlyingXmlObject().valueHashCode();
    }

    @Override
    public int compareTo(Object obj) {
        return this.underlyingXmlObject().compareTo(obj);
    }

    @Override
    public int compareValue(XmlObject obj) {
        return this.underlyingXmlObject().compareValue(obj);
    }

    @Override
    public Object monitor() {
        return this.underlyingXmlObject().monitor();
    }

    @Override
    public XmlDocumentProperties documentProperties() {
        return this.underlyingXmlObject().documentProperties();
    }

    @Override
    public XmlCursor newCursor() {
        return this.underlyingXmlObject().newCursor();
    }

    @Override
    public XMLInputStream newXMLInputStream() {
        return this.underlyingXmlObject().newXMLInputStream();
    }

    @Override
    public XMLStreamReader newXMLStreamReader() {
        return this.underlyingXmlObject().newXMLStreamReader();
    }

    @Override
    public String xmlText() {
        return this.underlyingXmlObject().xmlText();
    }

    @Override
    public InputStream newInputStream() {
        return this.underlyingXmlObject().newInputStream();
    }

    @Override
    public Reader newReader() {
        return this.underlyingXmlObject().newReader();
    }

    @Override
    public Node newDomNode() {
        return this.underlyingXmlObject().newDomNode();
    }

    @Override
    public Node getDomNode() {
        return this.underlyingXmlObject().getDomNode();
    }

    @Override
    public void save(ContentHandler ch, LexicalHandler lh) throws SAXException {
        this.underlyingXmlObject().save(ch, lh);
    }

    @Override
    public void save(File file) throws IOException {
        this.underlyingXmlObject().save(file);
    }

    @Override
    public void save(OutputStream os) throws IOException {
        this.underlyingXmlObject().save(os);
    }

    @Override
    public void save(Writer w) throws IOException {
        this.underlyingXmlObject().save(w);
    }

    @Override
    public XMLInputStream newXMLInputStream(XmlOptions options) {
        return this.underlyingXmlObject().newXMLInputStream(options);
    }

    @Override
    public XMLStreamReader newXMLStreamReader(XmlOptions options) {
        return this.underlyingXmlObject().newXMLStreamReader(options);
    }

    @Override
    public String xmlText(XmlOptions options) {
        return this.underlyingXmlObject().xmlText(options);
    }

    @Override
    public InputStream newInputStream(XmlOptions options) {
        return this.underlyingXmlObject().newInputStream(options);
    }

    @Override
    public Reader newReader(XmlOptions options) {
        return this.underlyingXmlObject().newReader(options);
    }

    @Override
    public Node newDomNode(XmlOptions options) {
        return this.underlyingXmlObject().newDomNode(options);
    }

    @Override
    public void save(ContentHandler ch, LexicalHandler lh, XmlOptions options) throws SAXException {
        this.underlyingXmlObject().save(ch, lh, options);
    }

    @Override
    public void save(File file, XmlOptions options) throws IOException {
        this.underlyingXmlObject().save(file, options);
    }

    @Override
    public void save(OutputStream os, XmlOptions options) throws IOException {
        this.underlyingXmlObject().save(os, options);
    }

    @Override
    public void save(Writer w, XmlOptions options) throws IOException {
        this.underlyingXmlObject().save(w, options);
    }

    @Override
    public SchemaType instanceType() {
        return ((SimpleValue)this.underlyingXmlObject()).instanceType();
    }

    @Override
    public String stringValue() {
        return ((SimpleValue)this.underlyingXmlObject()).stringValue();
    }

    @Override
    public boolean booleanValue() {
        return ((SimpleValue)this.underlyingXmlObject()).booleanValue();
    }

    @Override
    public byte byteValue() {
        return ((SimpleValue)this.underlyingXmlObject()).byteValue();
    }

    @Override
    public short shortValue() {
        return ((SimpleValue)this.underlyingXmlObject()).shortValue();
    }

    @Override
    public int intValue() {
        return ((SimpleValue)this.underlyingXmlObject()).intValue();
    }

    @Override
    public long longValue() {
        return ((SimpleValue)this.underlyingXmlObject()).longValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return ((SimpleValue)this.underlyingXmlObject()).bigIntegerValue();
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return ((SimpleValue)this.underlyingXmlObject()).bigDecimalValue();
    }

    @Override
    public float floatValue() {
        return ((SimpleValue)this.underlyingXmlObject()).floatValue();
    }

    @Override
    public double doubleValue() {
        return ((SimpleValue)this.underlyingXmlObject()).doubleValue();
    }

    @Override
    public byte[] byteArrayValue() {
        return ((SimpleValue)this.underlyingXmlObject()).byteArrayValue();
    }

    @Override
    public StringEnumAbstractBase enumValue() {
        return ((SimpleValue)this.underlyingXmlObject()).enumValue();
    }

    @Override
    public Calendar calendarValue() {
        return ((SimpleValue)this.underlyingXmlObject()).calendarValue();
    }

    @Override
    public Date dateValue() {
        return ((SimpleValue)this.underlyingXmlObject()).dateValue();
    }

    @Override
    public GDate gDateValue() {
        return ((SimpleValue)this.underlyingXmlObject()).gDateValue();
    }

    @Override
    public GDuration gDurationValue() {
        return ((SimpleValue)this.underlyingXmlObject()).gDurationValue();
    }

    @Override
    public QName qNameValue() {
        return ((SimpleValue)this.underlyingXmlObject()).qNameValue();
    }

    @Override
    public List listValue() {
        return ((SimpleValue)this.underlyingXmlObject()).listValue();
    }

    @Override
    public List xlistValue() {
        return ((SimpleValue)this.underlyingXmlObject()).xlistValue();
    }

    @Override
    public Object objectValue() {
        return ((SimpleValue)this.underlyingXmlObject()).objectValue();
    }

    @Override
    public void set(String obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(boolean v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(byte v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(short v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(int v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(long v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(BigInteger obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(BigDecimal obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(float v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(double v) {
        ((SimpleValue)this.underlyingXmlObject()).set(v);
    }

    @Override
    public void set(byte[] obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(StringEnumAbstractBase obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(Calendar obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(Date obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(GDateSpecification obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(GDurationSpecification obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(QName obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public void set(List obj) {
        ((SimpleValue)this.underlyingXmlObject()).set(obj);
    }

    @Override
    public String getStringValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getStringValue();
    }

    @Override
    public boolean getBooleanValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getBooleanValue();
    }

    @Override
    public byte getByteValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getByteValue();
    }

    @Override
    public short getShortValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getShortValue();
    }

    @Override
    public int getIntValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getIntValue();
    }

    @Override
    public long getLongValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getLongValue();
    }

    @Override
    public BigInteger getBigIntegerValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getBigIntegerValue();
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getBigDecimalValue();
    }

    @Override
    public float getFloatValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getFloatValue();
    }

    @Override
    public double getDoubleValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getDoubleValue();
    }

    @Override
    public byte[] getByteArrayValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getByteArrayValue();
    }

    @Override
    public StringEnumAbstractBase getEnumValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getEnumValue();
    }

    @Override
    public Calendar getCalendarValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getCalendarValue();
    }

    @Override
    public Date getDateValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getDateValue();
    }

    @Override
    public GDate getGDateValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getGDateValue();
    }

    @Override
    public GDuration getGDurationValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getGDurationValue();
    }

    @Override
    public QName getQNameValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getQNameValue();
    }

    @Override
    public List getListValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getListValue();
    }

    @Override
    public List xgetListValue() {
        return ((SimpleValue)this.underlyingXmlObject()).xgetListValue();
    }

    @Override
    public Object getObjectValue() {
        return ((SimpleValue)this.underlyingXmlObject()).getObjectValue();
    }

    @Override
    public void setStringValue(String obj) {
        ((SimpleValue)this.underlyingXmlObject()).setStringValue(obj);
    }

    @Override
    public void setBooleanValue(boolean v) {
        ((SimpleValue)this.underlyingXmlObject()).setBooleanValue(v);
    }

    @Override
    public void setByteValue(byte v) {
        ((SimpleValue)this.underlyingXmlObject()).setByteValue(v);
    }

    @Override
    public void setShortValue(short v) {
        ((SimpleValue)this.underlyingXmlObject()).setShortValue(v);
    }

    @Override
    public void setIntValue(int v) {
        ((SimpleValue)this.underlyingXmlObject()).setIntValue(v);
    }

    @Override
    public void setLongValue(long v) {
        ((SimpleValue)this.underlyingXmlObject()).setLongValue(v);
    }

    @Override
    public void setBigIntegerValue(BigInteger obj) {
        ((SimpleValue)this.underlyingXmlObject()).setBigIntegerValue(obj);
    }

    @Override
    public void setBigDecimalValue(BigDecimal obj) {
        ((SimpleValue)this.underlyingXmlObject()).setBigDecimalValue(obj);
    }

    @Override
    public void setFloatValue(float v) {
        ((SimpleValue)this.underlyingXmlObject()).setFloatValue(v);
    }

    @Override
    public void setDoubleValue(double v) {
        ((SimpleValue)this.underlyingXmlObject()).setDoubleValue(v);
    }

    @Override
    public void setByteArrayValue(byte[] obj) {
        ((SimpleValue)this.underlyingXmlObject()).setByteArrayValue(obj);
    }

    @Override
    public void setEnumValue(StringEnumAbstractBase obj) {
        ((SimpleValue)this.underlyingXmlObject()).setEnumValue(obj);
    }

    @Override
    public void setCalendarValue(Calendar obj) {
        ((SimpleValue)this.underlyingXmlObject()).setCalendarValue(obj);
    }

    @Override
    public void setDateValue(Date obj) {
        ((SimpleValue)this.underlyingXmlObject()).setDateValue(obj);
    }

    @Override
    public void setGDateValue(GDate obj) {
        ((SimpleValue)this.underlyingXmlObject()).setGDateValue(obj);
    }

    @Override
    public void setGDurationValue(GDuration obj) {
        ((SimpleValue)this.underlyingXmlObject()).setGDurationValue(obj);
    }

    @Override
    public void setQNameValue(QName obj) {
        ((SimpleValue)this.underlyingXmlObject()).setQNameValue(obj);
    }

    @Override
    public void setListValue(List obj) {
        ((SimpleValue)this.underlyingXmlObject()).setListValue(obj);
    }

    @Override
    public void setObjectValue(Object obj) {
        ((SimpleValue)this.underlyingXmlObject()).setObjectValue(obj);
    }

    @Override
    public void objectSet(Object obj) {
        ((SimpleValue)this.underlyingXmlObject()).objectSet(obj);
    }

    @Override
    public XmlObject[] selectChildren(QName elementName) {
        return this.underlyingXmlObject().selectChildren(elementName);
    }

    @Override
    public XmlObject[] selectChildren(String elementUri, String elementLocalName) {
        return this.underlyingXmlObject().selectChildren(elementUri, elementLocalName);
    }

    @Override
    public XmlObject[] selectChildren(QNameSet elementNameSet) {
        return this.underlyingXmlObject().selectChildren(elementNameSet);
    }

    @Override
    public XmlObject selectAttribute(QName attributeName) {
        return this.underlyingXmlObject().selectAttribute(attributeName);
    }

    @Override
    public XmlObject selectAttribute(String attributeUri, String attributeLocalName) {
        return this.underlyingXmlObject().selectAttribute(attributeUri, attributeLocalName);
    }

    @Override
    public XmlObject[] selectAttributes(QNameSet attributeNameSet) {
        return this.underlyingXmlObject().selectAttributes(attributeNameSet);
    }
}

