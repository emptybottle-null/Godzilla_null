/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;

public interface TypeStore
extends NamespaceManager {
    public static final int WS_UNSPECIFIED = 0;
    public static final int WS_PRESERVE = 1;
    public static final int WS_REPLACE = 2;
    public static final int WS_COLLAPSE = 3;
    public static final int NILLABLE = 1;
    public static final int HASDEFAULT = 2;
    public static final int FIXED = 4;

    public XmlCursor new_cursor();

    public void validate(ValidatorListener var1);

    public SchemaTypeLoader get_schematypeloader();

    public TypeStoreUser change_type(SchemaType var1);

    public TypeStoreUser substitute(QName var1, SchemaType var2);

    public boolean is_attribute();

    public QName get_xsi_type();

    public void invalidate_text();

    public String fetch_text(int var1);

    public void store_text(String var1);

    public String compute_default_text();

    public int compute_flags();

    public boolean validate_on_set();

    public SchemaField get_schema_field();

    public void invalidate_nil();

    public boolean find_nil();

    public int count_elements(QName var1);

    public int count_elements(QNameSet var1);

    public TypeStoreUser find_element_user(QName var1, int var2);

    public TypeStoreUser find_element_user(QNameSet var1, int var2);

    public void find_all_element_users(QName var1, List var2);

    public void find_all_element_users(QNameSet var1, List var2);

    public TypeStoreUser insert_element_user(QName var1, int var2);

    public TypeStoreUser insert_element_user(QNameSet var1, QName var2, int var3);

    public TypeStoreUser add_element_user(QName var1);

    public void remove_element(QName var1, int var2);

    public void remove_element(QNameSet var1, int var2);

    public TypeStoreUser find_attribute_user(QName var1);

    public TypeStoreUser add_attribute_user(QName var1);

    public void remove_attribute(QName var1);

    public TypeStoreUser copy_contents_from(TypeStore var1);

    public TypeStoreUser copy(SchemaTypeLoader var1, SchemaType var2, XmlOptions var3);

    public void array_setter(XmlObject[] var1, QName var2);

    public void visit_elements(TypeStoreVisitor var1);

    public XmlObject[] exec_query(String var1, XmlOptions var2) throws XmlException;

    public Object get_root_object();

    public XmlLocale get_locale();
}

