/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;

public interface SchemaProperty {
    public static final int NEVER = 0;
    public static final int VARIABLE = 1;
    public static final int CONSISTENTLY = 2;
    public static final int XML_OBJECT = 0;
    public static final int JAVA_FIRST_PRIMITIVE = 1;
    public static final int JAVA_BOOLEAN = 1;
    public static final int JAVA_FLOAT = 2;
    public static final int JAVA_DOUBLE = 3;
    public static final int JAVA_BYTE = 4;
    public static final int JAVA_SHORT = 5;
    public static final int JAVA_INT = 6;
    public static final int JAVA_LONG = 7;
    public static final int JAVA_LAST_PRIMITIVE = 7;
    public static final int JAVA_BIG_DECIMAL = 8;
    public static final int JAVA_BIG_INTEGER = 9;
    public static final int JAVA_STRING = 10;
    public static final int JAVA_BYTE_ARRAY = 11;
    public static final int JAVA_GDATE = 12;
    public static final int JAVA_GDURATION = 13;
    public static final int JAVA_DATE = 14;
    public static final int JAVA_QNAME = 15;
    public static final int JAVA_LIST = 16;
    public static final int JAVA_CALENDAR = 17;
    public static final int JAVA_ENUM = 18;
    public static final int JAVA_OBJECT = 19;
    public static final int JAVA_USER = 20;

    public SchemaType getContainerType();

    public QName getName();

    public QName[] acceptedNames();

    public String getJavaPropertyName();

    public boolean isReadOnly();

    public boolean isAttribute();

    public SchemaType getType();

    public SchemaType javaBasedOnType();

    public boolean extendsJavaSingleton();

    public boolean extendsJavaOption();

    public boolean extendsJavaArray();

    public int getJavaTypeCode();

    public QNameSet getJavaSetterDelimiter();

    public BigInteger getMinOccurs();

    public BigInteger getMaxOccurs();

    public int hasNillable();

    public int hasDefault();

    public int hasFixed();

    public String getDefaultText();

    public XmlAnySimpleType getDefaultValue();
}

