/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.ltgfmt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface FileDesc
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(FileDesc.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("filedesc9392type");

    public Code getCode();

    public boolean isSetCode();

    public void setCode(Code var1);

    public Code addNewCode();

    public void unsetCode();

    public String getTsDir();

    public XmlToken xgetTsDir();

    public boolean isSetTsDir();

    public void setTsDir(String var1);

    public void xsetTsDir(XmlToken var1);

    public void unsetTsDir();

    public String getFolder();

    public XmlToken xgetFolder();

    public boolean isSetFolder();

    public void setFolder(String var1);

    public void xsetFolder(XmlToken var1);

    public void unsetFolder();

    public String getFileName();

    public XmlToken xgetFileName();

    public boolean isSetFileName();

    public void setFileName(String var1);

    public void xsetFileName(XmlToken var1);

    public void unsetFileName();

    public Role.Enum getRole();

    public Role xgetRole();

    public boolean isSetRole();

    public void setRole(Role.Enum var1);

    public void xsetRole(Role var1);

    public void unsetRole();

    public boolean getValidity();

    public XmlBoolean xgetValidity();

    public boolean isSetValidity();

    public void setValidity(boolean var1);

    public void xsetValidity(XmlBoolean var1);

    public void unsetValidity();

    public static final class Factory {
        public static FileDesc newInstance() {
            return (FileDesc)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static FileDesc newInstance(XmlOptions options) {
            return (FileDesc)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static FileDesc parse(String xmlAsString) throws XmlException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static FileDesc parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static FileDesc parse(File file) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static FileDesc parse(File file, XmlOptions options) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static FileDesc parse(URL u) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static FileDesc parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static FileDesc parse(InputStream is) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static FileDesc parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static FileDesc parse(Reader r) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static FileDesc parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static FileDesc parse(XMLStreamReader sr) throws XmlException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static FileDesc parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static FileDesc parse(Node node) throws XmlException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static FileDesc parse(Node node, XmlOptions options) throws XmlException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static FileDesc parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static FileDesc parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (FileDesc)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Role
    extends XmlToken {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Role.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("role21a8attrtype");
        public static final Enum SCHEMA = Enum.forString("schema");
        public static final Enum INSTANCE = Enum.forString("instance");
        public static final Enum RESOURCE = Enum.forString("resource");
        public static final int INT_SCHEMA = 1;
        public static final int INT_INSTANCE = 2;
        public static final int INT_RESOURCE = 3;

        public StringEnumAbstractBase enumValue();

        public void set(StringEnumAbstractBase var1);

        public static final class Factory {
            public static Role newValue(Object obj) {
                return (Role)type.newValue(obj);
            }

            public static Role newInstance() {
                return (Role)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Role newInstance(XmlOptions options) {
                return (Role)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static final class Enum
        extends StringEnumAbstractBase {
            static final int INT_SCHEMA = 1;
            static final int INT_INSTANCE = 2;
            static final int INT_RESOURCE = 3;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("schema", 1), new Enum("instance", 2), new Enum("resource", 3)});
            private static final long serialVersionUID = 1L;

            public static Enum forString(String s) {
                return (Enum)table.forString(s);
            }

            public static Enum forInt(int i) {
                return (Enum)table.forInt(i);
            }

            private Enum(String s, int i) {
                super(s, i);
            }

            private Object readResolve() {
                return Enum.forInt(this.intValue());
            }
        }
    }
}

