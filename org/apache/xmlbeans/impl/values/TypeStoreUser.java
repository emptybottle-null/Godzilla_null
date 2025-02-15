/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;

public interface TypeStoreUser {
    public void attach_store(TypeStore var1);

    public SchemaType get_schema_type();

    public TypeStore get_store();

    public void invalidate_value();

    public boolean uses_invalidate_value();

    public String build_text(NamespaceManager var1);

    public boolean build_nil();

    public void invalidate_nilvalue();

    public void invalidate_element_order();

    public void validate_now();

    public void disconnect_store();

    public TypeStoreUser create_element_user(QName var1, QName var2);

    public TypeStoreUser create_attribute_user(QName var1);

    public SchemaType get_element_type(QName var1, QName var2);

    public SchemaType get_attribute_type(QName var1);

    public String get_default_element_text(QName var1);

    public String get_default_attribute_text(QName var1);

    public int get_elementflags(QName var1);

    public int get_attributeflags(QName var1);

    public SchemaField get_attribute_field(QName var1);

    public boolean is_child_element_order_sensitive();

    public QNameSet get_element_ending_delimiters(QName var1);

    public TypeStoreVisitor new_visitor();
}

