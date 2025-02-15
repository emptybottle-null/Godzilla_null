/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.xml.stream.XMLName;

public class XmlNameImpl
implements XMLName {
    private String namespaceUri = null;
    private String localName = null;
    private String prefix = null;
    private int hash = 0;

    public XmlNameImpl() {
    }

    public XmlNameImpl(String localName) {
        this.localName = localName;
    }

    public XmlNameImpl(String namespaceUri, String localName) {
        this.setNamespaceUri(namespaceUri);
        this.localName = localName;
    }

    public XmlNameImpl(String namespaceUri, String localName, String prefix) {
        this.setNamespaceUri(namespaceUri);
        this.localName = localName;
        this.prefix = prefix;
    }

    @Override
    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    @Override
    public String getLocalName() {
        return this.localName;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    public void setNamespaceUri(String namespaceUri) {
        this.hash = 0;
        if (namespaceUri != null && namespaceUri.equals("")) {
            return;
        }
        this.namespaceUri = namespaceUri;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
        this.hash = 0;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getQualifiedName() {
        if (this.prefix != null && this.prefix.length() > 0) {
            return this.prefix + ":" + this.localName;
        }
        return this.localName;
    }

    public String toString() {
        if (this.getNamespaceUri() != null) {
            return "['" + this.getNamespaceUri() + "']:" + this.getQualifiedName();
        }
        return this.getQualifiedName();
    }

    public final int hashCode() {
        int tmp_hash = this.hash;
        if (tmp_hash == 0) {
            tmp_hash = 17;
            if (this.namespaceUri != null) {
                tmp_hash = 37 * tmp_hash + this.namespaceUri.hashCode();
            }
            if (this.localName != null) {
                tmp_hash = 37 * tmp_hash + this.localName.hashCode();
            }
            this.hash = tmp_hash;
        }
        return tmp_hash;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XMLName) {
            XMLName name = (XMLName)obj;
            String lname = this.localName;
            if (!(lname != null ? lname.equals(name.getLocalName()) : name.getLocalName() == null)) {
                return false;
            }
            String uri = this.namespaceUri;
            return uri == null ? name.getNamespaceUri() == null : uri.equals(name.getNamespaceUri());
        }
        return false;
    }
}

