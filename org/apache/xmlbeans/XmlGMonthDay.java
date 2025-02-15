/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface XmlGMonthDay
extends XmlAnySimpleType {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_gMonthDay");

    public Calendar getCalendarValue();

    public void setCalendarValue(Calendar var1);

    public GDate getGDateValue();

    public void setGDateValue(GDate var1);

    public Calendar calendarValue();

    public void set(Calendar var1);

    public GDate gDateValue();

    public void set(GDateSpecification var1);

    public static final class Factory {
        public static XmlGMonthDay newInstance() {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static XmlGMonthDay newInstance(XmlOptions options) {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static XmlGMonthDay newValue(Object obj) {
            return (XmlGMonthDay)type.newValue(obj);
        }

        public static XmlGMonthDay parse(String s) throws XmlException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(s, type, null);
        }

        public static XmlGMonthDay parse(String s, XmlOptions options) throws XmlException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(s, type, options);
        }

        public static XmlGMonthDay parse(File f) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(f, type, null);
        }

        public static XmlGMonthDay parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(f, type, options);
        }

        public static XmlGMonthDay parse(URL u) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static XmlGMonthDay parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static XmlGMonthDay parse(InputStream is) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static XmlGMonthDay parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static XmlGMonthDay parse(Reader r) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static XmlGMonthDay parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static XmlGMonthDay parse(Node node) throws XmlException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static XmlGMonthDay parse(Node node, XmlOptions options) throws XmlException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static XmlGMonthDay parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static XmlGMonthDay parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XmlGMonthDay parse(XMLStreamReader xsr) throws XmlException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(xsr, type, null);
        }

        public static XmlGMonthDay parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlGMonthDay)XmlBeans.getContextTypeLoader().parse(xsr, type, options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, options);
        }

        private Factory() {
        }
    }
}

