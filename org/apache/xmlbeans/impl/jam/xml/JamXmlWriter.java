/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.xml;

import java.io.Writer;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.apache.xmlbeans.impl.jam.JAnnotatedElement;
import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JComment;
import org.apache.xmlbeans.impl.jam.JConstructor;
import org.apache.xmlbeans.impl.jam.JField;
import org.apache.xmlbeans.impl.jam.JInvokable;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JParameter;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.apache.xmlbeans.impl.jam.xml.JamXmlElements;

class JamXmlWriter
implements JamXmlElements {
    private XMLStreamWriter mOut;
    private boolean mInBody = false;
    private boolean mWriteSourceURI = false;

    public JamXmlWriter(Writer out) throws XMLStreamException {
        if (out == null) {
            throw new IllegalArgumentException("null out");
        }
        this.mOut = XMLOutputFactory.newInstance().createXMLStreamWriter(out);
    }

    public JamXmlWriter(XMLStreamWriter out) {
        if (out == null) {
            throw new IllegalArgumentException("null out");
        }
        this.mOut = out;
    }

    public void begin() throws XMLStreamException {
        if (this.mInBody) {
            throw new XMLStreamException("begin() already called");
        }
        this.mOut.writeStartElement("jam-service");
        this.mInBody = true;
    }

    public void end() throws XMLStreamException {
        if (!this.mInBody) {
            throw new XMLStreamException("begin() never called");
        }
        this.mOut.writeEndElement();
        this.mInBody = false;
    }

    public void write(JClass clazz) throws XMLStreamException {
        int i;
        this.assertStarted();
        this.mOut.writeStartElement("class");
        this.writeValueElement("name", clazz.getFieldDescriptor());
        this.writeValueElement("is-interface", clazz.isInterface());
        this.writeModifiers(clazz.getModifiers());
        JClass sc = clazz.getSuperclass();
        if (sc != null) {
            this.writeValueElement("superclass", sc.getFieldDescriptor());
        }
        this.writeClassList("interface", clazz.getInterfaces());
        JField[] f = clazz.getDeclaredFields();
        for (i = 0; i < f.length; ++i) {
            this.write(f[i]);
        }
        JConstructor[] c = clazz.getConstructors();
        for (i = 0; i < c.length; ++i) {
            this.write(c[i]);
        }
        JMethod[] m = clazz.getDeclaredMethods();
        for (i = 0; i < m.length; ++i) {
            this.write(m[i]);
        }
        this.writeAnnotatedElement(clazz);
        this.mOut.writeEndElement();
    }

    private void write(JMethod method) throws XMLStreamException {
        this.mOut.writeStartElement("method");
        this.writeValueElement("name", method.getSimpleName());
        this.writeValueElement("return-type", method.getReturnType().getFieldDescriptor());
        this.writeInvokable(method);
        this.mOut.writeEndElement();
    }

    private void write(JConstructor ctor) throws XMLStreamException {
        this.mOut.writeStartElement("constructor");
        this.writeInvokable(ctor);
        this.mOut.writeEndElement();
    }

    private void write(JField field) throws XMLStreamException {
        this.mOut.writeStartElement("field");
        this.writeValueElement("name", field.getSimpleName());
        this.writeModifiers(field.getModifiers());
        this.writeValueElement("type", field.getType().getFieldDescriptor());
        this.writeAnnotatedElement(field);
        this.mOut.writeEndElement();
    }

    private void writeInvokable(JInvokable ji) throws XMLStreamException {
        this.writeModifiers(ji.getModifiers());
        JParameter[] params = ji.getParameters();
        for (int i = 0; i < params.length; ++i) {
            this.mOut.writeStartElement("parameter");
            this.writeValueElement("name", params[i].getSimpleName());
            this.writeValueElement("type", params[i].getType().getFieldDescriptor());
            this.writeAnnotatedElement(params[i]);
            this.mOut.writeEndElement();
        }
        this.writeAnnotatedElement(ji);
    }

    private void writeClassList(String elementName, JClass[] clazzes) throws XMLStreamException {
        for (int i = 0; i < clazzes.length; ++i) {
            this.mOut.writeStartElement(elementName);
            this.mOut.writeCharacters(clazzes[i].getFieldDescriptor());
            this.mOut.writeEndElement();
        }
    }

    private void writeModifiers(int mods) throws XMLStreamException {
        this.mOut.writeStartElement("modifiers");
        this.mOut.writeCharacters(String.valueOf(mods));
        this.mOut.writeEndElement();
    }

    private void writeValueElement(String elementName, boolean b) throws XMLStreamException {
        this.mOut.writeStartElement(elementName);
        this.mOut.writeCharacters(String.valueOf(b));
        this.mOut.writeEndElement();
    }

    private void writeValueElement(String elementName, int x) throws XMLStreamException {
        this.mOut.writeStartElement(elementName);
        this.mOut.writeCharacters(String.valueOf(x));
        this.mOut.writeEndElement();
    }

    private void writeValueElement(String elementName, String val) throws XMLStreamException {
        this.mOut.writeStartElement(elementName);
        this.mOut.writeCharacters(val);
        this.mOut.writeEndElement();
    }

    private void writeValueElement(String elementName, String[] vals) throws XMLStreamException {
        for (int i = 0; i < vals.length; ++i) {
            this.writeValueElement(elementName, vals[i]);
        }
    }

    private void writeAnnotatedElement(JAnnotatedElement ae) throws XMLStreamException {
        JSourcePosition pos;
        String text;
        JAnnotation[] anns = ae.getAnnotations();
        for (int i = 0; i < anns.length; ++i) {
            this.writeAnnotation(anns[i]);
        }
        JComment jc = ae.getComment();
        if (jc != null && (text = jc.getText()) != null && (text = text.trim()).length() > 0) {
            this.mOut.writeStartElement("comment");
            this.mOut.writeCData(jc.getText());
            this.mOut.writeEndElement();
        }
        if ((pos = ae.getSourcePosition()) != null) {
            this.mOut.writeStartElement("source-position");
            if (pos.getLine() != -1) {
                this.writeValueElement("line", pos.getLine());
            }
            if (pos.getColumn() != -1) {
                this.writeValueElement("column", pos.getColumn());
            }
            if (this.mWriteSourceURI && pos.getSourceURI() != null) {
                this.writeValueElement("source-uri", pos.getSourceURI().toString());
            }
            this.mOut.writeEndElement();
        }
    }

    private void writeAnnotation(JAnnotation ann) throws XMLStreamException {
        this.mOut.writeStartElement("annotation");
        this.writeValueElement("name", ann.getQualifiedName());
        JAnnotationValue[] values = ann.getValues();
        for (int i = 0; i < values.length; ++i) {
            this.writeAnnotationValue(values[i]);
        }
        this.mOut.writeEndElement();
    }

    private void writeAnnotationValue(JAnnotationValue val) throws XMLStreamException {
        this.mOut.writeStartElement("annotation-value");
        this.writeValueElement("name", val.getName());
        this.writeValueElement("type", val.getType().getFieldDescriptor());
        if (val.getType().isArrayType()) {
            this.writeValueElement("value", val.asStringArray());
        } else {
            this.writeValueElement("value", val.asString());
        }
        this.mOut.writeEndElement();
    }

    private void assertStarted() throws XMLStreamException {
        if (!this.mInBody) {
            throw new XMLStreamException("begin() not called");
        }
    }
}

