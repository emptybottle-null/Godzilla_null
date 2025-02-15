/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.jsp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.fife.rsta.ac.jsp.JspCompletionProvider;
import org.fife.rsta.ac.jsp.TldAttribute;
import org.fife.rsta.ac.jsp.TldElement;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class TldFile {
    private JspCompletionProvider provider;
    private File jar;
    private List<TldElement> tldElems;

    public TldFile(JspCompletionProvider provider, File jar) throws IOException {
        this.provider = provider;
        this.jar = jar;
        this.tldElems = this.loadTldElems();
    }

    public List<ParameterizedCompletion.Parameter> getAttributesForTag(String tagName) {
        for (TldElement elem : this.tldElems) {
            if (!elem.getName().equals(tagName)) continue;
            return elem.getAttributes();
        }
        return null;
    }

    private String getChildText(Node elem) {
        StringBuilder sb = new StringBuilder();
        NodeList children = elem.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if (!(child instanceof Text)) continue;
            sb.append(child.getNodeValue());
        }
        return sb.toString();
    }

    public TldElement getElement(int index) {
        return this.tldElems.get(index);
    }

    public int getElementCount() {
        return this.tldElems.size();
    }

    private List<TldElement> loadTldElems() throws IOException {
        JarFile jar = new JarFile(this.jar);
        List<TldElement> elems = null;
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (!entry.getName().endsWith("tld")) continue;
            InputStream in = jar.getInputStream(entry);
            elems = this.parseTld(in);
            in.close();
        }
        jar.close();
        return elems;
    }

    private List<TldElement> parseTld(InputStream in) throws IOException {
        Document doc;
        ArrayList<TldElement> tldElems = new ArrayList<TldElement>();
        BufferedInputStream bin = new BufferedInputStream(in);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(bin);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
        Element root = doc.getDocumentElement();
        NodeList nl = root.getElementsByTagName("uri");
        if (nl.getLength() != 1) {
            throw new IOException("Expected 1 'uri' tag; found: " + nl.getLength());
        }
        nl = root.getElementsByTagName("tag");
        for (int i = 0; i < nl.getLength(); ++i) {
            Element elem = (Element)nl.item(i);
            String name = this.getChildText(elem.getElementsByTagName("name").item(0));
            String desc = this.getChildText(elem.getElementsByTagName("description").item(0));
            TldElement tldElem = new TldElement(this.provider, name, desc);
            tldElems.add(tldElem);
            NodeList attrNl = elem.getElementsByTagName("attribute");
            ArrayList<TldAttribute.TldAttributeParam> attrs = new ArrayList<TldAttribute.TldAttributeParam>(attrNl.getLength());
            for (int j = 0; j < attrNl.getLength(); ++j) {
                Element attrElem = (Element)attrNl.item(j);
                name = this.getChildText(attrElem.getElementsByTagName("name").item(0));
                desc = this.getChildText(attrElem.getElementsByTagName("description").item(0));
                boolean required = Boolean.parseBoolean(this.getChildText(attrElem.getElementsByTagName("required").item(0)));
                boolean rtexprValue = false;
                TldAttribute.TldAttributeParam param = new TldAttribute.TldAttributeParam(null, name, required, rtexprValue);
                param.setDescription(desc);
                attrs.add(param);
            }
            tldElem.setAttributes(attrs);
        }
        return tldElems;
    }
}

