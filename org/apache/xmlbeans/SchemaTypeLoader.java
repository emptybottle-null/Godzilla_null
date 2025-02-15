/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

public interface SchemaTypeLoader {
    public SchemaType findType(QName var1);

    public SchemaType findDocumentType(QName var1);

    public SchemaType findAttributeType(QName var1);

    public SchemaGlobalElement findElement(QName var1);

    public SchemaGlobalAttribute findAttribute(QName var1);

    public SchemaModelGroup findModelGroup(QName var1);

    public SchemaAttributeGroup findAttributeGroup(QName var1);

    public boolean isNamespaceDefined(String var1);

    public SchemaType.Ref findTypeRef(QName var1);

    public SchemaType.Ref findDocumentTypeRef(QName var1);

    public SchemaType.Ref findAttributeTypeRef(QName var1);

    public SchemaGlobalElement.Ref findElementRef(QName var1);

    public SchemaGlobalAttribute.Ref findAttributeRef(QName var1);

    public SchemaModelGroup.Ref findModelGroupRef(QName var1);

    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName var1);

    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName var1);

    public SchemaType typeForSignature(String var1);

    public SchemaType typeForClassname(String var1);

    public InputStream getSourceAsStream(String var1);

    public String compilePath(String var1, XmlOptions var2) throws XmlException;

    public String compileQuery(String var1, XmlOptions var2) throws XmlException;

    public XmlObject newInstance(SchemaType var1, XmlOptions var2);

    public XmlObject parse(String var1, SchemaType var2, XmlOptions var3) throws XmlException;

    public XmlObject parse(File var1, SchemaType var2, XmlOptions var3) throws XmlException, IOException;

    public XmlObject parse(URL var1, SchemaType var2, XmlOptions var3) throws XmlException, IOException;

    public XmlObject parse(InputStream var1, SchemaType var2, XmlOptions var3) throws XmlException, IOException;

    public XmlObject parse(XMLStreamReader var1, SchemaType var2, XmlOptions var3) throws XmlException;

    public XmlObject parse(Reader var1, SchemaType var2, XmlOptions var3) throws XmlException, IOException;

    public XmlObject parse(Node var1, SchemaType var2, XmlOptions var3) throws XmlException;

    public XmlObject parse(XMLInputStream var1, SchemaType var2, XmlOptions var3) throws XmlException, XMLStreamException;

    public XmlSaxHandler newXmlSaxHandler(SchemaType var1, XmlOptions var2);

    public DOMImplementation newDomImplementation(XmlOptions var1);

    public XMLInputStream newValidatingXMLInputStream(XMLInputStream var1, SchemaType var2, XmlOptions var3) throws XmlException, XMLStreamException;
}

