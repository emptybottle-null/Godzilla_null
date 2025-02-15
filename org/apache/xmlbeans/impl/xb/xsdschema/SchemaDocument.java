/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlLanguage;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.OpenAttrs;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface SchemaDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SchemaDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("schema0782doctype");

    public Schema getSchema();

    public void setSchema(Schema var1);

    public Schema addNewSchema();

    public static final class Factory {
        public static SchemaDocument newInstance() {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static SchemaDocument newInstance(XmlOptions options) {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static SchemaDocument parse(String xmlAsString) throws XmlException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static SchemaDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static SchemaDocument parse(File file) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static SchemaDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static SchemaDocument parse(URL u) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static SchemaDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static SchemaDocument parse(InputStream is) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static SchemaDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static SchemaDocument parse(Reader r) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static SchemaDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static SchemaDocument parse(XMLStreamReader sr) throws XmlException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static SchemaDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static SchemaDocument parse(Node node) throws XmlException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static SchemaDocument parse(Node node, XmlOptions options) throws XmlException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static SchemaDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static SchemaDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (SchemaDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Schema
    extends OpenAttrs {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Schema.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("schemad77felemtype");

        public IncludeDocument.Include[] getIncludeArray();

        public IncludeDocument.Include getIncludeArray(int var1);

        public int sizeOfIncludeArray();

        public void setIncludeArray(IncludeDocument.Include[] var1);

        public void setIncludeArray(int var1, IncludeDocument.Include var2);

        public IncludeDocument.Include insertNewInclude(int var1);

        public IncludeDocument.Include addNewInclude();

        public void removeInclude(int var1);

        public ImportDocument.Import[] getImportArray();

        public ImportDocument.Import getImportArray(int var1);

        public int sizeOfImportArray();

        public void setImportArray(ImportDocument.Import[] var1);

        public void setImportArray(int var1, ImportDocument.Import var2);

        public ImportDocument.Import insertNewImport(int var1);

        public ImportDocument.Import addNewImport();

        public void removeImport(int var1);

        public RedefineDocument.Redefine[] getRedefineArray();

        public RedefineDocument.Redefine getRedefineArray(int var1);

        public int sizeOfRedefineArray();

        public void setRedefineArray(RedefineDocument.Redefine[] var1);

        public void setRedefineArray(int var1, RedefineDocument.Redefine var2);

        public RedefineDocument.Redefine insertNewRedefine(int var1);

        public RedefineDocument.Redefine addNewRedefine();

        public void removeRedefine(int var1);

        public AnnotationDocument.Annotation[] getAnnotationArray();

        public AnnotationDocument.Annotation getAnnotationArray(int var1);

        public int sizeOfAnnotationArray();

        public void setAnnotationArray(AnnotationDocument.Annotation[] var1);

        public void setAnnotationArray(int var1, AnnotationDocument.Annotation var2);

        public AnnotationDocument.Annotation insertNewAnnotation(int var1);

        public AnnotationDocument.Annotation addNewAnnotation();

        public void removeAnnotation(int var1);

        public TopLevelSimpleType[] getSimpleTypeArray();

        public TopLevelSimpleType getSimpleTypeArray(int var1);

        public int sizeOfSimpleTypeArray();

        public void setSimpleTypeArray(TopLevelSimpleType[] var1);

        public void setSimpleTypeArray(int var1, TopLevelSimpleType var2);

        public TopLevelSimpleType insertNewSimpleType(int var1);

        public TopLevelSimpleType addNewSimpleType();

        public void removeSimpleType(int var1);

        public TopLevelComplexType[] getComplexTypeArray();

        public TopLevelComplexType getComplexTypeArray(int var1);

        public int sizeOfComplexTypeArray();

        public void setComplexTypeArray(TopLevelComplexType[] var1);

        public void setComplexTypeArray(int var1, TopLevelComplexType var2);

        public TopLevelComplexType insertNewComplexType(int var1);

        public TopLevelComplexType addNewComplexType();

        public void removeComplexType(int var1);

        public NamedGroup[] getGroupArray();

        public NamedGroup getGroupArray(int var1);

        public int sizeOfGroupArray();

        public void setGroupArray(NamedGroup[] var1);

        public void setGroupArray(int var1, NamedGroup var2);

        public NamedGroup insertNewGroup(int var1);

        public NamedGroup addNewGroup();

        public void removeGroup(int var1);

        public NamedAttributeGroup[] getAttributeGroupArray();

        public NamedAttributeGroup getAttributeGroupArray(int var1);

        public int sizeOfAttributeGroupArray();

        public void setAttributeGroupArray(NamedAttributeGroup[] var1);

        public void setAttributeGroupArray(int var1, NamedAttributeGroup var2);

        public NamedAttributeGroup insertNewAttributeGroup(int var1);

        public NamedAttributeGroup addNewAttributeGroup();

        public void removeAttributeGroup(int var1);

        public TopLevelElement[] getElementArray();

        public TopLevelElement getElementArray(int var1);

        public int sizeOfElementArray();

        public void setElementArray(TopLevelElement[] var1);

        public void setElementArray(int var1, TopLevelElement var2);

        public TopLevelElement insertNewElement(int var1);

        public TopLevelElement addNewElement();

        public void removeElement(int var1);

        public TopLevelAttribute[] getAttributeArray();

        public TopLevelAttribute getAttributeArray(int var1);

        public int sizeOfAttributeArray();

        public void setAttributeArray(TopLevelAttribute[] var1);

        public void setAttributeArray(int var1, TopLevelAttribute var2);

        public TopLevelAttribute insertNewAttribute(int var1);

        public TopLevelAttribute addNewAttribute();

        public void removeAttribute(int var1);

        public NotationDocument.Notation[] getNotationArray();

        public NotationDocument.Notation getNotationArray(int var1);

        public int sizeOfNotationArray();

        public void setNotationArray(NotationDocument.Notation[] var1);

        public void setNotationArray(int var1, NotationDocument.Notation var2);

        public NotationDocument.Notation insertNewNotation(int var1);

        public NotationDocument.Notation addNewNotation();

        public void removeNotation(int var1);

        public String getTargetNamespace();

        public XmlAnyURI xgetTargetNamespace();

        public boolean isSetTargetNamespace();

        public void setTargetNamespace(String var1);

        public void xsetTargetNamespace(XmlAnyURI var1);

        public void unsetTargetNamespace();

        public String getVersion();

        public XmlToken xgetVersion();

        public boolean isSetVersion();

        public void setVersion(String var1);

        public void xsetVersion(XmlToken var1);

        public void unsetVersion();

        public Object getFinalDefault();

        public FullDerivationSet xgetFinalDefault();

        public boolean isSetFinalDefault();

        public void setFinalDefault(Object var1);

        public void xsetFinalDefault(FullDerivationSet var1);

        public void unsetFinalDefault();

        public Object getBlockDefault();

        public BlockSet xgetBlockDefault();

        public boolean isSetBlockDefault();

        public void setBlockDefault(Object var1);

        public void xsetBlockDefault(BlockSet var1);

        public void unsetBlockDefault();

        public FormChoice.Enum getAttributeFormDefault();

        public FormChoice xgetAttributeFormDefault();

        public boolean isSetAttributeFormDefault();

        public void setAttributeFormDefault(FormChoice.Enum var1);

        public void xsetAttributeFormDefault(FormChoice var1);

        public void unsetAttributeFormDefault();

        public FormChoice.Enum getElementFormDefault();

        public FormChoice xgetElementFormDefault();

        public boolean isSetElementFormDefault();

        public void setElementFormDefault(FormChoice.Enum var1);

        public void xsetElementFormDefault(FormChoice var1);

        public void unsetElementFormDefault();

        public String getId();

        public XmlID xgetId();

        public boolean isSetId();

        public void setId(String var1);

        public void xsetId(XmlID var1);

        public void unsetId();

        public String getLang();

        public XmlLanguage xgetLang();

        public boolean isSetLang();

        public void setLang(String var1);

        public void xsetLang(XmlLanguage var1);

        public void unsetLang();

        public static final class Factory {
            public static Schema newInstance() {
                return (Schema)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Schema newInstance(XmlOptions options) {
                return (Schema)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

