/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

public interface XmlFactoryHook {
    public XmlObject newInstance(SchemaTypeLoader var1, SchemaType var2, XmlOptions var3);

    public XmlObject parse(SchemaTypeLoader var1, String var2, SchemaType var3, XmlOptions var4) throws XmlException;

    public XmlObject parse(SchemaTypeLoader var1, InputStream var2, SchemaType var3, XmlOptions var4) throws XmlException, IOException;

    public XmlObject parse(SchemaTypeLoader var1, XMLStreamReader var2, SchemaType var3, XmlOptions var4) throws XmlException;

    public XmlObject parse(SchemaTypeLoader var1, Reader var2, SchemaType var3, XmlOptions var4) throws XmlException, IOException;

    public XmlObject parse(SchemaTypeLoader var1, Node var2, SchemaType var3, XmlOptions var4) throws XmlException;

    public XmlObject parse(SchemaTypeLoader var1, XMLInputStream var2, SchemaType var3, XmlOptions var4) throws XmlException, XMLStreamException;

    public XmlSaxHandler newXmlSaxHandler(SchemaTypeLoader var1, SchemaType var2, XmlOptions var3);

    public DOMImplementation newDomImplementation(SchemaTypeLoader var1, XmlOptions var2);

    public static final class ThreadContext {
        private static ThreadLocal threadHook = new ThreadLocal();

        public static void clearThreadLocals() {
            threadHook.remove();
        }

        public static XmlFactoryHook getHook() {
            SoftReference softRef = (SoftReference)threadHook.get();
            return softRef == null ? null : (XmlFactoryHook)softRef.get();
        }

        public static void setHook(XmlFactoryHook hook) {
            threadHook.set(new SoftReference<XmlFactoryHook>(hook));
        }

        private ThreadContext() {
        }
    }
}

