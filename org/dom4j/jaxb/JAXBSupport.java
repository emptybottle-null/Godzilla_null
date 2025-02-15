/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.jaxb;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;

abstract class JAXBSupport {
    private String contextPath;
    private ClassLoader classloader;
    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public JAXBSupport(String contextPath) {
        this.contextPath = contextPath;
    }

    public JAXBSupport(String contextPath, ClassLoader classloader) {
        this.contextPath = contextPath;
        this.classloader = classloader;
    }

    protected Element marshal(javax.xml.bind.Element element) throws JAXBException {
        DOMDocument doc = new DOMDocument();
        this.getMarshaller().marshal((Object)element, doc);
        return doc.getRootElement();
    }

    protected javax.xml.bind.Element unmarshal(Element element) throws JAXBException {
        StreamSource source = new StreamSource(new StringReader(element.asXML()));
        return (javax.xml.bind.Element)this.getUnmarshaller().unmarshal(source);
    }

    private Marshaller getMarshaller() throws JAXBException {
        if (this.marshaller == null) {
            this.marshaller = this.getContext().createMarshaller();
        }
        return this.marshaller;
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        if (this.unmarshaller == null) {
            this.unmarshaller = this.getContext().createUnmarshaller();
        }
        return this.unmarshaller;
    }

    private JAXBContext getContext() throws JAXBException {
        if (this.jaxbContext == null) {
            this.jaxbContext = this.classloader == null ? JAXBContext.newInstance(this.contextPath) : JAXBContext.newInstance(this.contextPath, this.classloader);
        }
        return this.jaxbContext;
    }
}

