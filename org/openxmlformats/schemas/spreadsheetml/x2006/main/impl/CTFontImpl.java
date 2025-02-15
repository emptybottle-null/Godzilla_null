/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;

public class CTFontImpl
extends XmlComplexContentImpl
implements CTFont {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "name");
    private static final QName CHARSET$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "charset");
    private static final QName FAMILY$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "family");
    private static final QName B$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "b");
    private static final QName I$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "i");
    private static final QName STRIKE$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "strike");
    private static final QName OUTLINE$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "outline");
    private static final QName SHADOW$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "shadow");
    private static final QName CONDENSE$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "condense");
    private static final QName EXTEND$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extend");
    private static final QName COLOR$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "color");
    private static final QName SZ$22 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sz");
    private static final QName U$24 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "u");
    private static final QName VERTALIGN$26 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "vertAlign");
    private static final QName SCHEME$28 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "scheme");

    public CTFontImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFontName> getNameList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NameList
            extends AbstractList<CTFontName> {
                NameList() {
                }

                @Override
                public CTFontName get(int n) {
                    return CTFontImpl.this.getNameArray(n);
                }

                @Override
                public CTFontName set(int n, CTFontName cTFontName) {
                    CTFontName cTFontName2 = CTFontImpl.this.getNameArray(n);
                    CTFontImpl.this.setNameArray(n, cTFontName);
                    return cTFontName2;
                }

                @Override
                public void add(int n, CTFontName cTFontName) {
                    CTFontImpl.this.insertNewName(n).set(cTFontName);
                }

                @Override
                public CTFontName remove(int n) {
                    CTFontName cTFontName = CTFontImpl.this.getNameArray(n);
                    CTFontImpl.this.removeName(n);
                    return cTFontName;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfNameArray();
                }
            }
            return new NameList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFontName[] getNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NAME$0, arrayList);
            CTFontName[] cTFontNameArray = new CTFontName[arrayList.size()];
            arrayList.toArray(cTFontNameArray);
            return cTFontNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontName getNameArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontName cTFontName = null;
            cTFontName = (CTFontName)((Object)this.get_store().find_element_user(NAME$0, n));
            if (cTFontName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFontName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NAME$0);
        }
    }

    @Override
    public void setNameArray(CTFontName[] cTFontNameArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFontNameArray, NAME$0);
    }

    @Override
    public void setNameArray(int n, CTFontName cTFontName) {
        this.generatedSetterHelperImpl(cTFontName, NAME$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontName insertNewName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontName cTFontName = null;
            cTFontName = (CTFontName)((Object)this.get_store().insert_element_user(NAME$0, n));
            return cTFontName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontName addNewName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontName cTFontName = null;
            cTFontName = (CTFontName)((Object)this.get_store().add_element_user(NAME$0));
            return cTFontName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NAME$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTIntProperty> getCharsetList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CharsetList
            extends AbstractList<CTIntProperty> {
                CharsetList() {
                }

                @Override
                public CTIntProperty get(int n) {
                    return CTFontImpl.this.getCharsetArray(n);
                }

                @Override
                public CTIntProperty set(int n, CTIntProperty cTIntProperty) {
                    CTIntProperty cTIntProperty2 = CTFontImpl.this.getCharsetArray(n);
                    CTFontImpl.this.setCharsetArray(n, cTIntProperty);
                    return cTIntProperty2;
                }

                @Override
                public void add(int n, CTIntProperty cTIntProperty) {
                    CTFontImpl.this.insertNewCharset(n).set(cTIntProperty);
                }

                @Override
                public CTIntProperty remove(int n) {
                    CTIntProperty cTIntProperty = CTFontImpl.this.getCharsetArray(n);
                    CTFontImpl.this.removeCharset(n);
                    return cTIntProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfCharsetArray();
                }
            }
            return new CharsetList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTIntProperty[] getCharsetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CHARSET$2, arrayList);
            CTIntProperty[] cTIntPropertyArray = new CTIntProperty[arrayList.size()];
            arrayList.toArray(cTIntPropertyArray);
            return cTIntPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIntProperty getCharsetArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIntProperty cTIntProperty = null;
            cTIntProperty = (CTIntProperty)((Object)this.get_store().find_element_user(CHARSET$2, n));
            if (cTIntProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTIntProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCharsetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHARSET$2);
        }
    }

    @Override
    public void setCharsetArray(CTIntProperty[] cTIntPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTIntPropertyArray, CHARSET$2);
    }

    @Override
    public void setCharsetArray(int n, CTIntProperty cTIntProperty) {
        this.generatedSetterHelperImpl(cTIntProperty, CHARSET$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIntProperty insertNewCharset(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIntProperty cTIntProperty = null;
            cTIntProperty = (CTIntProperty)((Object)this.get_store().insert_element_user(CHARSET$2, n));
            return cTIntProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIntProperty addNewCharset() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIntProperty cTIntProperty = null;
            cTIntProperty = (CTIntProperty)((Object)this.get_store().add_element_user(CHARSET$2));
            return cTIntProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCharset(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHARSET$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTIntProperty> getFamilyList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FamilyList
            extends AbstractList<CTIntProperty> {
                FamilyList() {
                }

                @Override
                public CTIntProperty get(int n) {
                    return CTFontImpl.this.getFamilyArray(n);
                }

                @Override
                public CTIntProperty set(int n, CTIntProperty cTIntProperty) {
                    CTIntProperty cTIntProperty2 = CTFontImpl.this.getFamilyArray(n);
                    CTFontImpl.this.setFamilyArray(n, cTIntProperty);
                    return cTIntProperty2;
                }

                @Override
                public void add(int n, CTIntProperty cTIntProperty) {
                    CTFontImpl.this.insertNewFamily(n).set(cTIntProperty);
                }

                @Override
                public CTIntProperty remove(int n) {
                    CTIntProperty cTIntProperty = CTFontImpl.this.getFamilyArray(n);
                    CTFontImpl.this.removeFamily(n);
                    return cTIntProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfFamilyArray();
                }
            }
            return new FamilyList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTIntProperty[] getFamilyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FAMILY$4, arrayList);
            CTIntProperty[] cTIntPropertyArray = new CTIntProperty[arrayList.size()];
            arrayList.toArray(cTIntPropertyArray);
            return cTIntPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIntProperty getFamilyArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIntProperty cTIntProperty = null;
            cTIntProperty = (CTIntProperty)((Object)this.get_store().find_element_user(FAMILY$4, n));
            if (cTIntProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTIntProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFamilyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FAMILY$4);
        }
    }

    @Override
    public void setFamilyArray(CTIntProperty[] cTIntPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTIntPropertyArray, FAMILY$4);
    }

    @Override
    public void setFamilyArray(int n, CTIntProperty cTIntProperty) {
        this.generatedSetterHelperImpl(cTIntProperty, FAMILY$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIntProperty insertNewFamily(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIntProperty cTIntProperty = null;
            cTIntProperty = (CTIntProperty)((Object)this.get_store().insert_element_user(FAMILY$4, n));
            return cTIntProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIntProperty addNewFamily() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIntProperty cTIntProperty = null;
            cTIntProperty = (CTIntProperty)((Object)this.get_store().add_element_user(FAMILY$4));
            return cTIntProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFamily(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FAMILY$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getBList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BList
            extends AbstractList<CTBooleanProperty> {
                BList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getBArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getBArray(n);
                    CTFontImpl.this.setBArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewB(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getBArray(n);
                    CTFontImpl.this.removeB(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfBArray();
                }
            }
            return new BList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getBArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(B$6, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getBArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(B$6, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(B$6);
        }
    }

    @Override
    public void setBArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, B$6);
    }

    @Override
    public void setBArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, B$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewB(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(B$6, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(B$6));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeB(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(B$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getIList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class IList
            extends AbstractList<CTBooleanProperty> {
                IList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getIArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getIArray(n);
                    CTFontImpl.this.setIArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewI(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getIArray(n);
                    CTFontImpl.this.removeI(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfIArray();
                }
            }
            return new IList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getIArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(I$8, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getIArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(I$8, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfIArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I$8);
        }
    }

    @Override
    public void setIArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, I$8);
    }

    @Override
    public void setIArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, I$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewI(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(I$8, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(I$8));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeI(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getStrikeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class StrikeList
            extends AbstractList<CTBooleanProperty> {
                StrikeList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getStrikeArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getStrikeArray(n);
                    CTFontImpl.this.setStrikeArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewStrike(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getStrikeArray(n);
                    CTFontImpl.this.removeStrike(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfStrikeArray();
                }
            }
            return new StrikeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getStrikeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(STRIKE$10, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getStrikeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(STRIKE$10, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfStrikeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STRIKE$10);
        }
    }

    @Override
    public void setStrikeArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, STRIKE$10);
    }

    @Override
    public void setStrikeArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, STRIKE$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewStrike(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(STRIKE$10, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewStrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(STRIKE$10));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStrike(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STRIKE$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getOutlineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OutlineList
            extends AbstractList<CTBooleanProperty> {
                OutlineList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getOutlineArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getOutlineArray(n);
                    CTFontImpl.this.setOutlineArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewOutline(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getOutlineArray(n);
                    CTFontImpl.this.removeOutline(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfOutlineArray();
                }
            }
            return new OutlineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getOutlineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OUTLINE$12, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getOutlineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(OUTLINE$12, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOutlineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OUTLINE$12);
        }
    }

    @Override
    public void setOutlineArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, OUTLINE$12);
    }

    @Override
    public void setOutlineArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, OUTLINE$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewOutline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(OUTLINE$12, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(OUTLINE$12));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOutline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OUTLINE$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getShadowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShadowList
            extends AbstractList<CTBooleanProperty> {
                ShadowList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getShadowArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getShadowArray(n);
                    CTFontImpl.this.setShadowArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewShadow(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getShadowArray(n);
                    CTFontImpl.this.removeShadow(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfShadowArray();
                }
            }
            return new ShadowList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getShadowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHADOW$14, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getShadowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(SHADOW$14, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfShadowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHADOW$14);
        }
    }

    @Override
    public void setShadowArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, SHADOW$14);
    }

    @Override
    public void setShadowArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, SHADOW$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewShadow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(SHADOW$14, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(SHADOW$14));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeShadow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHADOW$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getCondenseList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CondenseList
            extends AbstractList<CTBooleanProperty> {
                CondenseList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getCondenseArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getCondenseArray(n);
                    CTFontImpl.this.setCondenseArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewCondense(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getCondenseArray(n);
                    CTFontImpl.this.removeCondense(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfCondenseArray();
                }
            }
            return new CondenseList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getCondenseArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CONDENSE$16, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getCondenseArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(CONDENSE$16, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCondenseArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONDENSE$16);
        }
    }

    @Override
    public void setCondenseArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, CONDENSE$16);
    }

    @Override
    public void setCondenseArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, CONDENSE$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewCondense(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(CONDENSE$16, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewCondense() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(CONDENSE$16));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCondense(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONDENSE$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBooleanProperty> getExtendList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ExtendList
            extends AbstractList<CTBooleanProperty> {
                ExtendList() {
                }

                @Override
                public CTBooleanProperty get(int n) {
                    return CTFontImpl.this.getExtendArray(n);
                }

                @Override
                public CTBooleanProperty set(int n, CTBooleanProperty cTBooleanProperty) {
                    CTBooleanProperty cTBooleanProperty2 = CTFontImpl.this.getExtendArray(n);
                    CTFontImpl.this.setExtendArray(n, cTBooleanProperty);
                    return cTBooleanProperty2;
                }

                @Override
                public void add(int n, CTBooleanProperty cTBooleanProperty) {
                    CTFontImpl.this.insertNewExtend(n).set(cTBooleanProperty);
                }

                @Override
                public CTBooleanProperty remove(int n) {
                    CTBooleanProperty cTBooleanProperty = CTFontImpl.this.getExtendArray(n);
                    CTFontImpl.this.removeExtend(n);
                    return cTBooleanProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfExtendArray();
                }
            }
            return new ExtendList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBooleanProperty[] getExtendArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EXTEND$18, arrayList);
            CTBooleanProperty[] cTBooleanPropertyArray = new CTBooleanProperty[arrayList.size()];
            arrayList.toArray(cTBooleanPropertyArray);
            return cTBooleanPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty getExtendArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().find_element_user(EXTEND$18, n));
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfExtendArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTEND$18);
        }
    }

    @Override
    public void setExtendArray(CTBooleanProperty[] cTBooleanPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBooleanPropertyArray, EXTEND$18);
    }

    @Override
    public void setExtendArray(int n, CTBooleanProperty cTBooleanProperty) {
        this.generatedSetterHelperImpl(cTBooleanProperty, EXTEND$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty insertNewExtend(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().insert_element_user(EXTEND$18, n));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBooleanProperty addNewExtend() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBooleanProperty cTBooleanProperty = null;
            cTBooleanProperty = (CTBooleanProperty)((Object)this.get_store().add_element_user(EXTEND$18));
            return cTBooleanProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeExtend(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTEND$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTColor> getColorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColorList
            extends AbstractList<CTColor> {
                ColorList() {
                }

                @Override
                public CTColor get(int n) {
                    return CTFontImpl.this.getColorArray(n);
                }

                @Override
                public CTColor set(int n, CTColor cTColor) {
                    CTColor cTColor2 = CTFontImpl.this.getColorArray(n);
                    CTFontImpl.this.setColorArray(n, cTColor);
                    return cTColor2;
                }

                @Override
                public void add(int n, CTColor cTColor) {
                    CTFontImpl.this.insertNewColor(n).set(cTColor);
                }

                @Override
                public CTColor remove(int n) {
                    CTColor cTColor = CTFontImpl.this.getColorArray(n);
                    CTFontImpl.this.removeColor(n);
                    return cTColor;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfColorArray();
                }
            }
            return new ColorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTColor[] getColorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLOR$20, arrayList);
            CTColor[] cTColorArray = new CTColor[arrayList.size()];
            arrayList.toArray(cTColorArray);
            return cTColorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor getColorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().find_element_user(COLOR$20, n));
            if (cTColor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLOR$20);
        }
    }

    @Override
    public void setColorArray(CTColor[] cTColorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTColorArray, COLOR$20);
    }

    @Override
    public void setColorArray(int n, CTColor cTColor) {
        this.generatedSetterHelperImpl(cTColor, COLOR$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor insertNewColor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().insert_element_user(COLOR$20, n));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor addNewColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().add_element_user(COLOR$20));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeColor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLOR$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFontSize> getSzList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SzList
            extends AbstractList<CTFontSize> {
                SzList() {
                }

                @Override
                public CTFontSize get(int n) {
                    return CTFontImpl.this.getSzArray(n);
                }

                @Override
                public CTFontSize set(int n, CTFontSize cTFontSize) {
                    CTFontSize cTFontSize2 = CTFontImpl.this.getSzArray(n);
                    CTFontImpl.this.setSzArray(n, cTFontSize);
                    return cTFontSize2;
                }

                @Override
                public void add(int n, CTFontSize cTFontSize) {
                    CTFontImpl.this.insertNewSz(n).set(cTFontSize);
                }

                @Override
                public CTFontSize remove(int n) {
                    CTFontSize cTFontSize = CTFontImpl.this.getSzArray(n);
                    CTFontImpl.this.removeSz(n);
                    return cTFontSize;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfSzArray();
                }
            }
            return new SzList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFontSize[] getSzArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SZ$22, arrayList);
            CTFontSize[] cTFontSizeArray = new CTFontSize[arrayList.size()];
            arrayList.toArray(cTFontSizeArray);
            return cTFontSizeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontSize getSzArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontSize cTFontSize = null;
            cTFontSize = (CTFontSize)((Object)this.get_store().find_element_user(SZ$22, n));
            if (cTFontSize == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFontSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSzArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SZ$22);
        }
    }

    @Override
    public void setSzArray(CTFontSize[] cTFontSizeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFontSizeArray, SZ$22);
    }

    @Override
    public void setSzArray(int n, CTFontSize cTFontSize) {
        this.generatedSetterHelperImpl(cTFontSize, SZ$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontSize insertNewSz(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontSize cTFontSize = null;
            cTFontSize = (CTFontSize)((Object)this.get_store().insert_element_user(SZ$22, n));
            return cTFontSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontSize addNewSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontSize cTFontSize = null;
            cTFontSize = (CTFontSize)((Object)this.get_store().add_element_user(SZ$22));
            return cTFontSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSz(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SZ$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTUnderlineProperty> getUList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class UList
            extends AbstractList<CTUnderlineProperty> {
                UList() {
                }

                @Override
                public CTUnderlineProperty get(int n) {
                    return CTFontImpl.this.getUArray(n);
                }

                @Override
                public CTUnderlineProperty set(int n, CTUnderlineProperty cTUnderlineProperty) {
                    CTUnderlineProperty cTUnderlineProperty2 = CTFontImpl.this.getUArray(n);
                    CTFontImpl.this.setUArray(n, cTUnderlineProperty);
                    return cTUnderlineProperty2;
                }

                @Override
                public void add(int n, CTUnderlineProperty cTUnderlineProperty) {
                    CTFontImpl.this.insertNewU(n).set(cTUnderlineProperty);
                }

                @Override
                public CTUnderlineProperty remove(int n) {
                    CTUnderlineProperty cTUnderlineProperty = CTFontImpl.this.getUArray(n);
                    CTFontImpl.this.removeU(n);
                    return cTUnderlineProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfUArray();
                }
            }
            return new UList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTUnderlineProperty[] getUArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(U$24, arrayList);
            CTUnderlineProperty[] cTUnderlinePropertyArray = new CTUnderlineProperty[arrayList.size()];
            arrayList.toArray(cTUnderlinePropertyArray);
            return cTUnderlinePropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnderlineProperty getUArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnderlineProperty cTUnderlineProperty = null;
            cTUnderlineProperty = (CTUnderlineProperty)((Object)this.get_store().find_element_user(U$24, n));
            if (cTUnderlineProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTUnderlineProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfUArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(U$24);
        }
    }

    @Override
    public void setUArray(CTUnderlineProperty[] cTUnderlinePropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTUnderlinePropertyArray, U$24);
    }

    @Override
    public void setUArray(int n, CTUnderlineProperty cTUnderlineProperty) {
        this.generatedSetterHelperImpl(cTUnderlineProperty, U$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnderlineProperty insertNewU(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnderlineProperty cTUnderlineProperty = null;
            cTUnderlineProperty = (CTUnderlineProperty)((Object)this.get_store().insert_element_user(U$24, n));
            return cTUnderlineProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnderlineProperty addNewU() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnderlineProperty cTUnderlineProperty = null;
            cTUnderlineProperty = (CTUnderlineProperty)((Object)this.get_store().add_element_user(U$24));
            return cTUnderlineProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeU(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(U$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTVerticalAlignFontProperty> getVertAlignList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class VertAlignList
            extends AbstractList<CTVerticalAlignFontProperty> {
                VertAlignList() {
                }

                @Override
                public CTVerticalAlignFontProperty get(int n) {
                    return CTFontImpl.this.getVertAlignArray(n);
                }

                @Override
                public CTVerticalAlignFontProperty set(int n, CTVerticalAlignFontProperty cTVerticalAlignFontProperty) {
                    CTVerticalAlignFontProperty cTVerticalAlignFontProperty2 = CTFontImpl.this.getVertAlignArray(n);
                    CTFontImpl.this.setVertAlignArray(n, cTVerticalAlignFontProperty);
                    return cTVerticalAlignFontProperty2;
                }

                @Override
                public void add(int n, CTVerticalAlignFontProperty cTVerticalAlignFontProperty) {
                    CTFontImpl.this.insertNewVertAlign(n).set(cTVerticalAlignFontProperty);
                }

                @Override
                public CTVerticalAlignFontProperty remove(int n) {
                    CTVerticalAlignFontProperty cTVerticalAlignFontProperty = CTFontImpl.this.getVertAlignArray(n);
                    CTFontImpl.this.removeVertAlign(n);
                    return cTVerticalAlignFontProperty;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfVertAlignArray();
                }
            }
            return new VertAlignList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTVerticalAlignFontProperty[] getVertAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(VERTALIGN$26, arrayList);
            CTVerticalAlignFontProperty[] cTVerticalAlignFontPropertyArray = new CTVerticalAlignFontProperty[arrayList.size()];
            arrayList.toArray(cTVerticalAlignFontPropertyArray);
            return cTVerticalAlignFontPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalAlignFontProperty getVertAlignArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalAlignFontProperty cTVerticalAlignFontProperty = null;
            cTVerticalAlignFontProperty = (CTVerticalAlignFontProperty)((Object)this.get_store().find_element_user(VERTALIGN$26, n));
            if (cTVerticalAlignFontProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTVerticalAlignFontProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfVertAlignArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VERTALIGN$26);
        }
    }

    @Override
    public void setVertAlignArray(CTVerticalAlignFontProperty[] cTVerticalAlignFontPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTVerticalAlignFontPropertyArray, VERTALIGN$26);
    }

    @Override
    public void setVertAlignArray(int n, CTVerticalAlignFontProperty cTVerticalAlignFontProperty) {
        this.generatedSetterHelperImpl(cTVerticalAlignFontProperty, VERTALIGN$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalAlignFontProperty insertNewVertAlign(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalAlignFontProperty cTVerticalAlignFontProperty = null;
            cTVerticalAlignFontProperty = (CTVerticalAlignFontProperty)((Object)this.get_store().insert_element_user(VERTALIGN$26, n));
            return cTVerticalAlignFontProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalAlignFontProperty addNewVertAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalAlignFontProperty cTVerticalAlignFontProperty = null;
            cTVerticalAlignFontProperty = (CTVerticalAlignFontProperty)((Object)this.get_store().add_element_user(VERTALIGN$26));
            return cTVerticalAlignFontProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeVertAlign(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VERTALIGN$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFontScheme> getSchemeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SchemeList
            extends AbstractList<CTFontScheme> {
                SchemeList() {
                }

                @Override
                public CTFontScheme get(int n) {
                    return CTFontImpl.this.getSchemeArray(n);
                }

                @Override
                public CTFontScheme set(int n, CTFontScheme cTFontScheme) {
                    CTFontScheme cTFontScheme2 = CTFontImpl.this.getSchemeArray(n);
                    CTFontImpl.this.setSchemeArray(n, cTFontScheme);
                    return cTFontScheme2;
                }

                @Override
                public void add(int n, CTFontScheme cTFontScheme) {
                    CTFontImpl.this.insertNewScheme(n).set(cTFontScheme);
                }

                @Override
                public CTFontScheme remove(int n) {
                    CTFontScheme cTFontScheme = CTFontImpl.this.getSchemeArray(n);
                    CTFontImpl.this.removeScheme(n);
                    return cTFontScheme;
                }

                @Override
                public int size() {
                    return CTFontImpl.this.sizeOfSchemeArray();
                }
            }
            return new SchemeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFontScheme[] getSchemeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SCHEME$28, arrayList);
            CTFontScheme[] cTFontSchemeArray = new CTFontScheme[arrayList.size()];
            arrayList.toArray(cTFontSchemeArray);
            return cTFontSchemeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontScheme getSchemeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontScheme cTFontScheme = null;
            cTFontScheme = (CTFontScheme)((Object)this.get_store().find_element_user(SCHEME$28, n));
            if (cTFontScheme == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFontScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSchemeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEME$28);
        }
    }

    @Override
    public void setSchemeArray(CTFontScheme[] cTFontSchemeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFontSchemeArray, SCHEME$28);
    }

    @Override
    public void setSchemeArray(int n, CTFontScheme cTFontScheme) {
        this.generatedSetterHelperImpl(cTFontScheme, SCHEME$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontScheme insertNewScheme(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontScheme cTFontScheme = null;
            cTFontScheme = (CTFontScheme)((Object)this.get_store().insert_element_user(SCHEME$28, n));
            return cTFontScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontScheme addNewScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontScheme cTFontScheme = null;
            cTFontScheme = (CTFontScheme)((Object)this.get_store().add_element_user(SCHEME$28));
            return cTFontScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeScheme(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEME$28, n);
        }
    }
}

