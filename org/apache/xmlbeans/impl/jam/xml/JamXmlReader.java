/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.xml;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.CachedClassBuilder;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotation;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MInvokable;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;
import org.apache.xmlbeans.impl.jam.mutable.MSourcePosition;
import org.apache.xmlbeans.impl.jam.xml.JamXmlElements;

class JamXmlReader
implements JamXmlElements {
    private XMLStreamReader mIn;
    private CachedClassBuilder mCache;
    private ElementContext mContext;

    public JamXmlReader(CachedClassBuilder cache, InputStream in, ElementContext ctx) throws XMLStreamException {
        this(cache, XMLInputFactory.newInstance().createXMLStreamReader(in), ctx);
    }

    public JamXmlReader(CachedClassBuilder cache, Reader in, ElementContext ctx) throws XMLStreamException {
        this(cache, XMLInputFactory.newInstance().createXMLStreamReader(in), ctx);
    }

    public JamXmlReader(CachedClassBuilder cache, XMLStreamReader in, ElementContext ctx) {
        if (cache == null) {
            throw new IllegalArgumentException("null cache");
        }
        if (in == null) {
            throw new IllegalArgumentException("null cache");
        }
        if (ctx == null) {
            throw new IllegalArgumentException("null ctx");
        }
        this.mIn = in;
        this.mCache = cache;
        this.mContext = ctx;
    }

    public void read() throws XMLStreamException {
        this.nextElement();
        this.assertStart("jam-service");
        this.nextElement();
        while ("class".equals(this.getElementName())) {
            this.readClass();
        }
        this.assertEnd("jam-service");
    }

    private void readClass() throws XMLStreamException {
        this.assertStart("class");
        this.nextElement();
        String clazzName = this.assertCurrentString("name");
        int dot = clazzName.lastIndexOf(46);
        String pkgName = "";
        if (dot != -1) {
            pkgName = clazzName.substring(0, dot);
            clazzName = clazzName.substring(dot + 1);
        }
        MClass clazz = this.mCache.createClassToBuild(pkgName, clazzName, null);
        clazz.setIsInterface(this.assertCurrentBoolean("is-interface"));
        clazz.setModifiers(this.assertCurrentInt("modifiers"));
        String supername = this.checkCurrentString("superclass");
        if (supername != null) {
            clazz.setSuperclass(supername);
        }
        while ((supername = this.checkCurrentString("interface")) != null) {
            clazz.addInterface(supername);
        }
        while ("field".equals(this.getElementName())) {
            this.readField(clazz);
        }
        while ("constructor".equals(this.getElementName())) {
            this.readConstructor(clazz);
        }
        while ("method".equals(this.getElementName())) {
            this.readMethod(clazz);
        }
        this.readAnnotatedElement(clazz);
        this.assertEnd("class");
        ((ClassImpl)clazz).setState(6);
        this.nextElement();
    }

    private void readField(MClass clazz) throws XMLStreamException {
        this.assertStart("field");
        MField field = clazz.addNewField();
        this.nextElement();
        field.setSimpleName(this.assertCurrentString("name"));
        field.setModifiers(this.assertCurrentInt("modifiers"));
        field.setType(this.assertCurrentString("type"));
        this.readAnnotatedElement(field);
        this.assertEnd("field");
        this.nextElement();
    }

    private void readConstructor(MClass clazz) throws XMLStreamException {
        this.assertStart("constructor");
        MConstructor ctor = clazz.addNewConstructor();
        this.nextElement();
        this.readInvokableContents(ctor);
        this.assertEnd("constructor");
        this.nextElement();
    }

    private void readMethod(MClass clazz) throws XMLStreamException {
        this.assertStart("method");
        MMethod method = clazz.addNewMethod();
        this.nextElement();
        method.setSimpleName(this.assertCurrentString("name"));
        method.setReturnType(this.assertCurrentString("return-type"));
        this.readInvokableContents(method);
        this.assertEnd("method");
        this.nextElement();
    }

    private void readSourcePosition(MAnnotatedElement element) throws XMLStreamException {
        this.assertStart("source-position");
        MSourcePosition pos = element.createSourcePosition();
        this.nextElement();
        if ("line".equals(this.getElementName())) {
            pos.setLine(this.assertCurrentInt("line"));
        }
        if ("column".equals(this.getElementName())) {
            pos.setColumn(this.assertCurrentInt("column"));
        }
        if ("source-uri".equals(this.getElementName())) {
            try {
                pos.setSourceURI(new URI(this.assertCurrentString("source-uri")));
            } catch (URISyntaxException use) {
                throw new XMLStreamException(use);
            }
        }
        this.assertEnd("source-position");
        this.nextElement();
    }

    private void readInvokableContents(MInvokable out) throws XMLStreamException {
        out.setModifiers(this.assertCurrentInt("modifiers"));
        while ("parameter".equals(this.getElementName())) {
            this.nextElement();
            MParameter param = out.addNewParameter();
            param.setSimpleName(this.assertCurrentString("name"));
            param.setType(this.assertCurrentString("type"));
            this.readAnnotatedElement(param);
            this.assertEnd("parameter");
            this.nextElement();
        }
        this.readAnnotatedElement(out);
    }

    private void readAnnotatedElement(MAnnotatedElement element) throws XMLStreamException {
        while ("annotation".equals(this.getElementName())) {
            this.nextElement();
            MAnnotation ann = element.addLiteralAnnotation(this.assertCurrentString("name"));
            while ("annotation-value".equals(this.getElementName())) {
                this.nextElement();
                String name = this.assertCurrentString("name");
                String type = this.assertCurrentString("type");
                JClass jclass = this.mContext.getClassLoader().loadClass(type);
                if (jclass.isArrayType()) {
                    ArrayList<String> list = new ArrayList<String>();
                    while ("value".equals(this.getElementName())) {
                        String value = this.assertCurrentString("value");
                        list.add(value);
                    }
                    String[] vals = new String[list.size()];
                    list.toArray(vals);
                    ann.setSimpleValue(name, vals, jclass);
                } else {
                    String value = this.assertCurrentString("value");
                    ann.setSimpleValue(name, value, jclass);
                }
                this.assertEnd("annotation-value");
                this.nextElement();
            }
            this.assertEnd("annotation");
            this.nextElement();
        }
        if ("comment".equals(this.getElementName())) {
            element.createComment().setText(this.mIn.getElementText());
            this.assertEnd("comment");
            this.nextElement();
        }
        if ("source-position".equals(this.getElementName())) {
            this.readSourcePosition(element);
        }
    }

    private void assertStart(String named) throws XMLStreamException {
        if (!this.mIn.isStartElement() || !named.equals(this.getElementName())) {
            this.error("expected to get a <" + named + ">, ");
        }
    }

    private void assertEnd(String named) throws XMLStreamException {
        if (!this.mIn.isEndElement() || !named.equals(this.getElementName())) {
            this.error("expected to get a </" + named + ">, ");
        }
    }

    private String checkCurrentString(String named) throws XMLStreamException {
        if (named.equals(this.getElementName())) {
            String val = this.mIn.getElementText();
            this.assertEnd(named);
            this.nextElement();
            return val;
        }
        return null;
    }

    private String assertCurrentString(String named) throws XMLStreamException {
        this.assertStart(named);
        String val = this.mIn.getElementText();
        this.assertEnd(named);
        this.nextElement();
        return val;
    }

    private int assertCurrentInt(String named) throws XMLStreamException {
        this.assertStart(named);
        String val = this.mIn.getElementText();
        this.assertEnd(named);
        this.nextElement();
        return Integer.valueOf(val);
    }

    private boolean assertCurrentBoolean(String named) throws XMLStreamException {
        this.assertStart(named);
        String val = this.mIn.getElementText();
        this.assertEnd(named);
        this.nextElement();
        return Boolean.valueOf(val);
    }

    private void error(String message) throws XMLStreamException {
        StringWriter out = new StringWriter();
        out.write("<");
        out.write(this.mIn.getLocalName());
        out.write("> line:");
        out.write("" + this.mIn.getLocation().getLineNumber());
        out.write(" col:");
        out.write("" + this.mIn.getLocation().getColumnNumber());
        out.write("]");
        throw new XMLStreamException(message + ":\n " + out.toString());
    }

    private void nextElement() throws XMLStreamException {
        do {
            if (this.mIn.next() != -1) continue;
            throw new XMLStreamException("Unexpected end of file");
        } while (!this.mIn.isEndElement() && !this.mIn.isStartElement());
    }

    private String getElementName() {
        return this.mIn.getLocalName();
    }
}

