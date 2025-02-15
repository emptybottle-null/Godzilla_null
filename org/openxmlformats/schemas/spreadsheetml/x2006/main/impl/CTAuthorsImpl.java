/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class CTAuthorsImpl
extends XmlComplexContentImpl
implements CTAuthors {
    private static final long serialVersionUID = 1L;
    private static final QName AUTHOR$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "author");

    public CTAuthorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getAuthorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AuthorList
            extends AbstractList<String> {
                AuthorList() {
                }

                @Override
                public String get(int n) {
                    return CTAuthorsImpl.this.getAuthorArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = CTAuthorsImpl.this.getAuthorArray(n);
                    CTAuthorsImpl.this.setAuthorArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    CTAuthorsImpl.this.insertAuthor(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = CTAuthorsImpl.this.getAuthorArray(n);
                    CTAuthorsImpl.this.removeAuthor(n);
                    return string;
                }

                @Override
                public int size() {
                    return CTAuthorsImpl.this.sizeOfAuthorArray();
                }
            }
            return new AuthorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getAuthorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTHOR$0, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAuthorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTHOR$0, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<STXstring> xgetAuthorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AuthorList
            extends AbstractList<STXstring> {
                AuthorList() {
                }

                @Override
                public STXstring get(int n) {
                    return CTAuthorsImpl.this.xgetAuthorArray(n);
                }

                @Override
                public STXstring set(int n, STXstring sTXstring) {
                    STXstring sTXstring2 = CTAuthorsImpl.this.xgetAuthorArray(n);
                    CTAuthorsImpl.this.xsetAuthorArray(n, sTXstring);
                    return sTXstring2;
                }

                @Override
                public void add(int n, STXstring sTXstring) {
                    CTAuthorsImpl.this.insertNewAuthor(n).set(sTXstring);
                }

                @Override
                public STXstring remove(int n) {
                    STXstring sTXstring = CTAuthorsImpl.this.xgetAuthorArray(n);
                    CTAuthorsImpl.this.removeAuthor(n);
                    return sTXstring;
                }

                @Override
                public int size() {
                    return CTAuthorsImpl.this.sizeOfAuthorArray();
                }
            }
            return new AuthorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public STXstring[] xgetAuthorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(AUTHOR$0, arrayList);
            STXstring[] sTXstringArray = new STXstring[arrayList.size()];
            arrayList.toArray(sTXstringArray);
            return sTXstringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STXstring xgetAuthorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_element_user(AUTHOR$0, n));
            if (sTXstring == null) {
                throw new IndexOutOfBoundsException();
            }
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAuthorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTHOR$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAuthorArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, AUTHOR$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAuthorArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(AUTHOR$0, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAuthorArray(STXstring[] sTXstringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(sTXstringArray, AUTHOR$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAuthorArray(int n, STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_element_user(AUTHOR$0, n));
            if (sTXstring2 == null) {
                throw new IndexOutOfBoundsException();
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAuthor(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(AUTHOR$0, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAuthor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(AUTHOR$0));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STXstring insertNewAuthor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().insert_element_user(AUTHOR$0, n));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STXstring addNewAuthor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().add_element_user(AUTHOR$0));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAuthor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTHOR$0, n);
        }
    }
}

