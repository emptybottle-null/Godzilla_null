/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

public class JavaQNameHolder
extends XmlObjectBase {
    private QName _value;
    private static final NamespaceManager PRETTY_PREFIXER = new PrettyNamespaceManager();

    @Override
    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_QNAME;
    }

    @Override
    protected int get_wscanon_rule() {
        return 1;
    }

    @Override
    public String compute_text(NamespaceManager nsm) {
        if (nsm == null) {
            nsm = PRETTY_PREFIXER;
        }
        String namespace = this._value.getNamespaceURI();
        String localPart = this._value.getLocalPart();
        if (namespace == null || namespace.length() == 0) {
            return localPart;
        }
        String prefix = nsm.find_prefix_for_nsuri(namespace, null);
        assert (prefix != null);
        return "".equals(prefix) ? localPart : prefix + ":" + localPart;
    }

    public static QName validateLexical(String v, ValidationContext context, PrefixResolver resolver) {
        QName name;
        try {
            name = JavaQNameHolder.parse(v, resolver);
        } catch (XmlValueOutOfRangeException e) {
            context.invalid(e.getMessage());
            name = null;
        }
        return name;
    }

    private static QName parse(String v, PrefixResolver resolver) {
        String uri;
        String localname;
        String prefix;
        int start;
        int end;
        for (end = v.length(); end > 0 && XMLChar.isSpace(v.charAt(end - 1)); --end) {
        }
        for (start = 0; start < end && XMLChar.isSpace(v.charAt(start)); ++start) {
        }
        int firstcolon = v.indexOf(58, start);
        if (firstcolon >= 0) {
            prefix = v.substring(start, firstcolon);
            localname = v.substring(firstcolon + 1, end);
        } else {
            prefix = "";
            localname = v.substring(start, end);
        }
        if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix)) {
            throw new XmlValueOutOfRangeException("QName", new Object[]{"Prefix not a valid NCName in '" + v + "'"});
        }
        if (!XMLChar.isValidNCName(localname)) {
            throw new XmlValueOutOfRangeException("QName", new Object[]{"Localname not a valid NCName in '" + v + "'"});
        }
        String string = uri = resolver == null ? null : resolver.getNamespaceForPrefix(prefix);
        if (uri == null) {
            if (prefix.length() > 0) {
                throw new XmlValueOutOfRangeException("QName", new Object[]{"Can't resolve prefix '" + prefix + "'"});
            }
            uri = "";
        }
        if (prefix != null && prefix.length() > 0) {
            return new QName(uri, localname, prefix);
        }
        return new QName(uri, localname);
    }

    @Override
    protected void set_text(String s) {
        PrefixResolver resolver = NamespaceContext.getCurrent();
        if (resolver == null && this.has_store()) {
            resolver = this.get_store();
        }
        this._value = JavaQNameHolder.parse(s, resolver);
    }

    @Override
    protected void set_QName(QName name) {
        assert (name != null);
        if (this.has_store()) {
            this.get_store().find_prefix_for_nsuri(name.getNamespaceURI(), null);
        }
        this._value = name;
    }

    @Override
    protected void set_xmlanysimple(XmlAnySimpleType value) {
        this._value = JavaQNameHolder.parse(value.getStringValue(), NamespaceContext.getCurrent());
    }

    @Override
    protected void set_nil() {
        this._value = null;
    }

    @Override
    public QName getQNameValue() {
        this.check_dated();
        return this._value;
    }

    @Override
    protected boolean equal_to(XmlObject obj) {
        return this._value.equals(((XmlObjectBase)obj).qNameValue());
    }

    @Override
    protected int value_hash_code() {
        return this._value.hashCode();
    }

    private static class PrettyNamespaceManager
    implements NamespaceManager {
        private PrettyNamespaceManager() {
        }

        @Override
        public String find_prefix_for_nsuri(String nsuri, String suggested_prefix) {
            return QNameHelper.suggestPrefix(nsuri);
        }

        @Override
        public String getNamespaceForPrefix(String prefix) {
            throw new RuntimeException("Should not be called");
        }
    }
}

