/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.apache.xmlbeans.impl.values.XmlObjectBase;

public class XmlComplexContentImpl
extends XmlObjectBase {
    private SchemaTypeImpl _schemaType;

    public XmlComplexContentImpl(SchemaType type) {
        this._schemaType = (SchemaTypeImpl)type;
        this.initComplexType(true, true);
    }

    @Override
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override
    public String compute_text(NamespaceManager nsm) {
        return null;
    }

    @Override
    protected final void set_String(String v) {
        assert (this._schemaType.getContentType() != 2);
        if (this._schemaType.getContentType() != 4 && !this._schemaType.isNoType()) {
            throw new IllegalArgumentException("Type does not allow for textual content: " + this._schemaType);
        }
        super.set_String(v);
    }

    @Override
    public void set_text(String str) {
        assert (this._schemaType.getContentType() == 4 || this._schemaType.isNoType());
    }

    @Override
    protected void update_from_complex_content() {
    }

    @Override
    public void set_nil() {
    }

    @Override
    public boolean equal_to(XmlObject complexObject) {
        return this._schemaType.equals(complexObject.schemaType());
    }

    @Override
    protected int value_hash_code() {
        throw new IllegalStateException("Complex types cannot be used as hash keys");
    }

    @Override
    public TypeStoreVisitor new_visitor() {
        return new SchemaTypeVisitorImpl(this._schemaType.getContentModel());
    }

    @Override
    public boolean is_child_element_order_sensitive() {
        return this.schemaType().isOrderSensitive();
    }

    @Override
    public int get_elementflags(QName eltName) {
        SchemaProperty prop = this.schemaType().getElementProperty(eltName);
        if (prop == null) {
            return 0;
        }
        if (prop.hasDefault() == 1 || prop.hasFixed() == 1 || prop.hasNillable() == 1) {
            return -1;
        }
        return (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4) | (prop.hasNillable() == 0 ? 0 : 1);
    }

    @Override
    public String get_default_attribute_text(QName attrName) {
        return super.get_default_attribute_text(attrName);
    }

    @Override
    public String get_default_element_text(QName eltName) {
        SchemaProperty prop = this.schemaType().getElementProperty(eltName);
        if (prop == null) {
            return "";
        }
        return prop.getDefaultText();
    }

    protected void unionArraySetterHelper(Object[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).objectSet(sources[i]);
        }
    }

    protected SimpleValue[] arraySetterHelper(int sourcesLength, QName elemName) {
        int m;
        SimpleValue[] dests = new SimpleValue[sourcesLength];
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > sourcesLength; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < sourcesLength; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            dests[i] = (SimpleValue)((Object)user);
        }
        return dests;
    }

    protected SimpleValue[] arraySetterHelper(int sourcesLength, QName elemName, QNameSet set) {
        int m;
        SimpleValue[] dests = new SimpleValue[sourcesLength];
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > sourcesLength; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < sourcesLength; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            dests[i] = (SimpleValue)((Object)user);
        }
        return dests;
    }

    protected void arraySetterHelper(boolean[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(float[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(double[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(byte[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(short[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(int[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(long[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(BigDecimal[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(BigInteger[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(String[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(byte[][] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(GDate[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(GDuration[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(Calendar[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(Date[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(QName[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(StringEnumAbstractBase[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(List[] sources, QName elemName) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(elemName); m > n; --m) {
            store.remove_element(elemName, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void unionArraySetterHelper(Object[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).objectSet(sources[i]);
        }
    }

    protected void arraySetterHelper(boolean[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(float[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(double[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(byte[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(short[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(int[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(long[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(BigDecimal[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(BigInteger[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(String[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(byte[][] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(GDate[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(GDuration[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(Calendar[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(Date[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(QName[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(StringEnumAbstractBase[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(List[] sources, QName elemName, QNameSet set) {
        int m;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = this.get_store();
        for (m = store.count_elements(set); m > n; --m) {
            store.remove_element(set, m - 1);
        }
        for (int i = 0; i < n; ++i) {
            TypeStoreUser user = i >= m ? store.add_element_user(elemName) : store.find_element_user(set, i);
            ((XmlObjectBase)user).set(sources[i]);
        }
    }

    protected void arraySetterHelper(XmlObject[] sources, QName elemName) {
        TypeStoreUser user;
        int j;
        TypeStoreUser current;
        int i;
        TypeStore store = this.get_store();
        if (sources == null || sources.length == 0) {
            for (int m = store.count_elements(elemName); m > 0; --m) {
                store.remove_element(elemName, 0);
            }
            return;
        }
        int m = store.count_elements(elemName);
        int startSrc = 0;
        int startDest = 0;
        for (i = 0; i < sources.length; ++i) {
            if (sources[i].isImmutable()) continue;
            XmlCursor c = sources[i].newCursor();
            if (c.toParent() && c.getObject() == this) {
                c.dispose();
                break;
            }
            c.dispose();
        }
        if (i < sources.length && (current = store.find_element_user(elemName, 0)) == sources[i]) {
            j = 0;
            for (j = 0; j < i; ++j) {
                user = store.insert_element_user(elemName, j);
                ((XmlObjectBase)user).set(sources[j]);
            }
            ++i;
            ++j;
            while (i < sources.length) {
                XmlCursor c;
                XmlCursor xmlCursor = c = sources[i].isImmutable() ? null : sources[i].newCursor();
                if (c != null && c.toParent() && c.getObject() == this) {
                    c.dispose();
                    current = store.find_element_user(elemName, j);
                    if (current != sources[i]) break;
                } else {
                    c.dispose();
                    TypeStoreUser user2 = store.insert_element_user(elemName, j);
                    ((XmlObjectBase)user2).set(sources[i]);
                }
                ++i;
                ++j;
            }
            startDest = j;
            startSrc = i;
            m = store.count_elements(elemName);
        }
        for (int j2 = i; j2 < sources.length; ++j2) {
            TypeStoreUser user3 = store.add_element_user(elemName);
            ((XmlObjectBase)user3).set(sources[j2]);
        }
        int n = i;
        while (m > n - startSrc + startDest) {
            store.remove_element(elemName, m - 1);
            --m;
        }
        i = startSrc;
        j = startDest;
        while (i < n) {
            user = j >= m ? store.add_element_user(elemName) : store.find_element_user(elemName, j);
            ((XmlObjectBase)user).set(sources[i]);
            ++i;
            ++j;
        }
    }

    protected void arraySetterHelper(XmlObject[] sources, QName elemName, QNameSet set) {
        TypeStoreUser user;
        int j;
        TypeStoreUser current;
        int i;
        TypeStore store = this.get_store();
        if (sources == null || sources.length == 0) {
            for (int m = store.count_elements(set); m > 0; --m) {
                store.remove_element(set, 0);
            }
            return;
        }
        int m = store.count_elements(set);
        int startSrc = 0;
        int startDest = 0;
        for (i = 0; i < sources.length; ++i) {
            if (sources[i].isImmutable()) continue;
            XmlCursor c = sources[i].newCursor();
            if (c.toParent() && c.getObject() == this) {
                c.dispose();
                break;
            }
            c.dispose();
        }
        if (i < sources.length && (current = store.find_element_user(set, 0)) == sources[i]) {
            j = 0;
            for (j = 0; j < i; ++j) {
                user = store.insert_element_user(set, elemName, j);
                ((XmlObjectBase)user).set(sources[j]);
            }
            ++i;
            ++j;
            while (i < sources.length) {
                XmlCursor c;
                XmlCursor xmlCursor = c = sources[i].isImmutable() ? null : sources[i].newCursor();
                if (c != null && c.toParent() && c.getObject() == this) {
                    c.dispose();
                    current = store.find_element_user(set, j);
                    if (current != sources[i]) break;
                } else {
                    c.dispose();
                    TypeStoreUser user2 = store.insert_element_user(set, elemName, j);
                    ((XmlObjectBase)user2).set(sources[i]);
                }
                ++i;
                ++j;
            }
            startDest = j;
            startSrc = i;
            m = store.count_elements(elemName);
        }
        for (int j2 = i; j2 < sources.length; ++j2) {
            TypeStoreUser user3 = store.add_element_user(elemName);
            ((XmlObjectBase)user3).set(sources[j2]);
        }
        int n = i;
        while (m > n - startSrc + startDest) {
            store.remove_element(set, m - 1);
            --m;
        }
        i = startSrc;
        j = startDest;
        while (i < n) {
            user = j >= m ? store.add_element_user(elemName) : store.find_element_user(set, j);
            ((XmlObjectBase)user).set(sources[i]);
            ++i;
            ++j;
        }
    }
}

