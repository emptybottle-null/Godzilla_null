/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

public class SchemaAnnotationImpl
implements SchemaAnnotation {
    private SchemaContainer _container;
    private String[] _appInfoAsXml;
    private AppinfoDocument.Appinfo[] _appInfo;
    private String[] _documentationAsXml;
    private DocumentationDocument.Documentation[] _documentation;
    private SchemaAnnotation.Attribute[] _attributes;
    private String _filename;

    public void setFilename(String filename) {
        this._filename = filename;
    }

    @Override
    public String getSourceName() {
        return this._filename;
    }

    @Override
    public XmlObject[] getApplicationInformation() {
        if (this._appInfo == null) {
            int n = this._appInfoAsXml.length;
            this._appInfo = new AppinfoDocument.Appinfo[n];
            for (int i = 0; i < n; ++i) {
                String appInfo = this._appInfoAsXml[i];
                try {
                    this._appInfo[i] = AppinfoDocument.Factory.parse(appInfo).getAppinfo();
                    continue;
                } catch (XmlException e) {
                    this._appInfo[i] = AppinfoDocument.Factory.newInstance().getAppinfo();
                }
            }
        }
        return this._appInfo;
    }

    @Override
    public XmlObject[] getUserInformation() {
        if (this._documentation == null) {
            int n = this._documentationAsXml.length;
            this._documentation = new DocumentationDocument.Documentation[n];
            for (int i = 0; i < n; ++i) {
                String doc = this._documentationAsXml[i];
                try {
                    this._documentation[i] = DocumentationDocument.Factory.parse(doc).getDocumentation();
                    continue;
                } catch (XmlException e) {
                    this._documentation[i] = DocumentationDocument.Factory.newInstance().getDocumentation();
                }
            }
        }
        return this._documentation;
    }

    @Override
    public SchemaAnnotation.Attribute[] getAttributes() {
        return this._attributes;
    }

    @Override
    public int getComponentType() {
        return 8;
    }

    @Override
    public SchemaTypeSystem getTypeSystem() {
        return this._container != null ? this._container.getTypeSystem() : null;
    }

    SchemaContainer getContainer() {
        return this._container;
    }

    @Override
    public QName getName() {
        return null;
    }

    @Override
    public SchemaComponent.Ref getComponentRef() {
        return null;
    }

    public static SchemaAnnotationImpl getAnnotation(SchemaContainer c, Annotated elem) {
        AnnotationDocument.Annotation ann = elem.getAnnotation();
        return SchemaAnnotationImpl.getAnnotation(c, elem, ann);
    }

    public static SchemaAnnotationImpl getAnnotation(SchemaContainer c, XmlObject elem, AnnotationDocument.Annotation ann) {
        if (StscState.get().noAnn()) {
            return null;
        }
        SchemaAnnotationImpl result = new SchemaAnnotationImpl(c);
        ArrayList attrArray = new ArrayList(2);
        SchemaAnnotationImpl.addNoSchemaAttributes(elem, attrArray);
        if (ann == null) {
            if (attrArray.size() == 0) {
                return null;
            }
            result._appInfo = new AppinfoDocument.Appinfo[0];
            result._documentation = new DocumentationDocument.Documentation[0];
        } else {
            result._appInfo = ann.getAppinfoArray();
            result._documentation = ann.getDocumentationArray();
            SchemaAnnotationImpl.addNoSchemaAttributes(ann, attrArray);
        }
        result._attributes = attrArray.toArray(new AttributeImpl[attrArray.size()]);
        return result;
    }

    private static void addNoSchemaAttributes(XmlObject elem, List attrList) {
        XmlCursor cursor = elem.newCursor();
        boolean hasAttributes = cursor.toFirstAttribute();
        while (hasAttributes) {
            QName name = cursor.getName();
            String namespaceURI = name.getNamespaceURI();
            if (!"".equals(namespaceURI) && !"http://www.w3.org/2001/XMLSchema".equals(namespaceURI)) {
                String attValue = cursor.getTextValue();
                String prefix = attValue.indexOf(58) > 0 ? attValue.substring(0, attValue.indexOf(58)) : "";
                cursor.push();
                cursor.toParent();
                String valUri = cursor.namespaceForPrefix(prefix);
                cursor.pop();
                attrList.add(new AttributeImpl(name, attValue, valUri));
            }
            hasAttributes = cursor.toNextAttribute();
        }
        cursor.dispose();
    }

    private SchemaAnnotationImpl(SchemaContainer c) {
        this._container = c;
    }

    SchemaAnnotationImpl(SchemaContainer c, String[] aapStrings, String[] adocStrings, SchemaAnnotation.Attribute[] aat) {
        this._container = c;
        this._appInfoAsXml = aapStrings;
        this._documentationAsXml = adocStrings;
        this._attributes = aat;
    }

    static class AttributeImpl
    implements SchemaAnnotation.Attribute {
        private QName _name;
        private String _value;
        private String _valueUri;

        AttributeImpl(QName name, String value, String valueUri) {
            this._name = name;
            this._value = value;
            this._valueUri = valueUri;
        }

        @Override
        public QName getName() {
            return this._name;
        }

        @Override
        public String getValue() {
            return this._value;
        }

        @Override
        public String getValueUri() {
            return this._valueUri;
        }
    }
}

