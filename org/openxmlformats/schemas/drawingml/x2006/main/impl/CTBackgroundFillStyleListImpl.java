/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackgroundFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;

public class CTBackgroundFillStyleListImpl
extends XmlComplexContentImpl
implements CTBackgroundFillStyleList {
    private static final long serialVersionUID = 1L;
    private static final QName NOFILL$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "noFill");
    private static final QName SOLIDFILL$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "solidFill");
    private static final QName GRADFILL$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gradFill");
    private static final QName BLIPFILL$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blipFill");
    private static final QName PATTFILL$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pattFill");
    private static final QName GRPFILL$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "grpFill");

    public CTBackgroundFillStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTNoFillProperties> getNoFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NoFillList
            extends AbstractList<CTNoFillProperties> {
                NoFillList() {
                }

                @Override
                public CTNoFillProperties get(int n) {
                    return CTBackgroundFillStyleListImpl.this.getNoFillArray(n);
                }

                @Override
                public CTNoFillProperties set(int n, CTNoFillProperties cTNoFillProperties) {
                    CTNoFillProperties cTNoFillProperties2 = CTBackgroundFillStyleListImpl.this.getNoFillArray(n);
                    CTBackgroundFillStyleListImpl.this.setNoFillArray(n, cTNoFillProperties);
                    return cTNoFillProperties2;
                }

                @Override
                public void add(int n, CTNoFillProperties cTNoFillProperties) {
                    CTBackgroundFillStyleListImpl.this.insertNewNoFill(n).set(cTNoFillProperties);
                }

                @Override
                public CTNoFillProperties remove(int n) {
                    CTNoFillProperties cTNoFillProperties = CTBackgroundFillStyleListImpl.this.getNoFillArray(n);
                    CTBackgroundFillStyleListImpl.this.removeNoFill(n);
                    return cTNoFillProperties;
                }

                @Override
                public int size() {
                    return CTBackgroundFillStyleListImpl.this.sizeOfNoFillArray();
                }
            }
            return new NoFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTNoFillProperties[] getNoFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NOFILL$0, arrayList);
            CTNoFillProperties[] cTNoFillPropertiesArray = new CTNoFillProperties[arrayList.size()];
            arrayList.toArray(cTNoFillPropertiesArray);
            return cTNoFillPropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties getNoFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().find_element_user(NOFILL$0, n));
            if (cTNoFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNoFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOFILL$0);
        }
    }

    @Override
    public void setNoFillArray(CTNoFillProperties[] cTNoFillPropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTNoFillPropertiesArray, NOFILL$0);
    }

    @Override
    public void setNoFillArray(int n, CTNoFillProperties cTNoFillProperties) {
        this.generatedSetterHelperImpl(cTNoFillProperties, NOFILL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties insertNewNoFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().insert_element_user(NOFILL$0, n));
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties addNewNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().add_element_user(NOFILL$0));
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNoFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOFILL$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSolidColorFillProperties> getSolidFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SolidFillList
            extends AbstractList<CTSolidColorFillProperties> {
                SolidFillList() {
                }

                @Override
                public CTSolidColorFillProperties get(int n) {
                    return CTBackgroundFillStyleListImpl.this.getSolidFillArray(n);
                }

                @Override
                public CTSolidColorFillProperties set(int n, CTSolidColorFillProperties cTSolidColorFillProperties) {
                    CTSolidColorFillProperties cTSolidColorFillProperties2 = CTBackgroundFillStyleListImpl.this.getSolidFillArray(n);
                    CTBackgroundFillStyleListImpl.this.setSolidFillArray(n, cTSolidColorFillProperties);
                    return cTSolidColorFillProperties2;
                }

                @Override
                public void add(int n, CTSolidColorFillProperties cTSolidColorFillProperties) {
                    CTBackgroundFillStyleListImpl.this.insertNewSolidFill(n).set(cTSolidColorFillProperties);
                }

                @Override
                public CTSolidColorFillProperties remove(int n) {
                    CTSolidColorFillProperties cTSolidColorFillProperties = CTBackgroundFillStyleListImpl.this.getSolidFillArray(n);
                    CTBackgroundFillStyleListImpl.this.removeSolidFill(n);
                    return cTSolidColorFillProperties;
                }

                @Override
                public int size() {
                    return CTBackgroundFillStyleListImpl.this.sizeOfSolidFillArray();
                }
            }
            return new SolidFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSolidColorFillProperties[] getSolidFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SOLIDFILL$2, arrayList);
            CTSolidColorFillProperties[] cTSolidColorFillPropertiesArray = new CTSolidColorFillProperties[arrayList.size()];
            arrayList.toArray(cTSolidColorFillPropertiesArray);
            return cTSolidColorFillPropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties getSolidFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().find_element_user(SOLIDFILL$2, n));
            if (cTSolidColorFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSolidFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SOLIDFILL$2);
        }
    }

    @Override
    public void setSolidFillArray(CTSolidColorFillProperties[] cTSolidColorFillPropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSolidColorFillPropertiesArray, SOLIDFILL$2);
    }

    @Override
    public void setSolidFillArray(int n, CTSolidColorFillProperties cTSolidColorFillProperties) {
        this.generatedSetterHelperImpl(cTSolidColorFillProperties, SOLIDFILL$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties insertNewSolidFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().insert_element_user(SOLIDFILL$2, n));
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties addNewSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().add_element_user(SOLIDFILL$2));
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSolidFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SOLIDFILL$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGradientFillProperties> getGradFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GradFillList
            extends AbstractList<CTGradientFillProperties> {
                GradFillList() {
                }

                @Override
                public CTGradientFillProperties get(int n) {
                    return CTBackgroundFillStyleListImpl.this.getGradFillArray(n);
                }

                @Override
                public CTGradientFillProperties set(int n, CTGradientFillProperties cTGradientFillProperties) {
                    CTGradientFillProperties cTGradientFillProperties2 = CTBackgroundFillStyleListImpl.this.getGradFillArray(n);
                    CTBackgroundFillStyleListImpl.this.setGradFillArray(n, cTGradientFillProperties);
                    return cTGradientFillProperties2;
                }

                @Override
                public void add(int n, CTGradientFillProperties cTGradientFillProperties) {
                    CTBackgroundFillStyleListImpl.this.insertNewGradFill(n).set(cTGradientFillProperties);
                }

                @Override
                public CTGradientFillProperties remove(int n) {
                    CTGradientFillProperties cTGradientFillProperties = CTBackgroundFillStyleListImpl.this.getGradFillArray(n);
                    CTBackgroundFillStyleListImpl.this.removeGradFill(n);
                    return cTGradientFillProperties;
                }

                @Override
                public int size() {
                    return CTBackgroundFillStyleListImpl.this.sizeOfGradFillArray();
                }
            }
            return new GradFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGradientFillProperties[] getGradFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRADFILL$4, arrayList);
            CTGradientFillProperties[] cTGradientFillPropertiesArray = new CTGradientFillProperties[arrayList.size()];
            arrayList.toArray(cTGradientFillPropertiesArray);
            return cTGradientFillPropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties getGradFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().find_element_user(GRADFILL$4, n));
            if (cTGradientFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGradFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRADFILL$4);
        }
    }

    @Override
    public void setGradFillArray(CTGradientFillProperties[] cTGradientFillPropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGradientFillPropertiesArray, GRADFILL$4);
    }

    @Override
    public void setGradFillArray(int n, CTGradientFillProperties cTGradientFillProperties) {
        this.generatedSetterHelperImpl(cTGradientFillProperties, GRADFILL$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties insertNewGradFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().insert_element_user(GRADFILL$4, n));
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties addNewGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().add_element_user(GRADFILL$4));
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGradFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRADFILL$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBlipFillProperties> getBlipFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BlipFillList
            extends AbstractList<CTBlipFillProperties> {
                BlipFillList() {
                }

                @Override
                public CTBlipFillProperties get(int n) {
                    return CTBackgroundFillStyleListImpl.this.getBlipFillArray(n);
                }

                @Override
                public CTBlipFillProperties set(int n, CTBlipFillProperties cTBlipFillProperties) {
                    CTBlipFillProperties cTBlipFillProperties2 = CTBackgroundFillStyleListImpl.this.getBlipFillArray(n);
                    CTBackgroundFillStyleListImpl.this.setBlipFillArray(n, cTBlipFillProperties);
                    return cTBlipFillProperties2;
                }

                @Override
                public void add(int n, CTBlipFillProperties cTBlipFillProperties) {
                    CTBackgroundFillStyleListImpl.this.insertNewBlipFill(n).set(cTBlipFillProperties);
                }

                @Override
                public CTBlipFillProperties remove(int n) {
                    CTBlipFillProperties cTBlipFillProperties = CTBackgroundFillStyleListImpl.this.getBlipFillArray(n);
                    CTBackgroundFillStyleListImpl.this.removeBlipFill(n);
                    return cTBlipFillProperties;
                }

                @Override
                public int size() {
                    return CTBackgroundFillStyleListImpl.this.sizeOfBlipFillArray();
                }
            }
            return new BlipFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBlipFillProperties[] getBlipFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BLIPFILL$6, arrayList);
            CTBlipFillProperties[] cTBlipFillPropertiesArray = new CTBlipFillProperties[arrayList.size()];
            arrayList.toArray(cTBlipFillPropertiesArray);
            return cTBlipFillPropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties getBlipFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().find_element_user(BLIPFILL$6, n));
            if (cTBlipFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBlipFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLIPFILL$6);
        }
    }

    @Override
    public void setBlipFillArray(CTBlipFillProperties[] cTBlipFillPropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBlipFillPropertiesArray, BLIPFILL$6);
    }

    @Override
    public void setBlipFillArray(int n, CTBlipFillProperties cTBlipFillProperties) {
        this.generatedSetterHelperImpl(cTBlipFillProperties, BLIPFILL$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties insertNewBlipFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().insert_element_user(BLIPFILL$6, n));
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties addNewBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().add_element_user(BLIPFILL$6));
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBlipFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLIPFILL$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPatternFillProperties> getPattFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PattFillList
            extends AbstractList<CTPatternFillProperties> {
                PattFillList() {
                }

                @Override
                public CTPatternFillProperties get(int n) {
                    return CTBackgroundFillStyleListImpl.this.getPattFillArray(n);
                }

                @Override
                public CTPatternFillProperties set(int n, CTPatternFillProperties cTPatternFillProperties) {
                    CTPatternFillProperties cTPatternFillProperties2 = CTBackgroundFillStyleListImpl.this.getPattFillArray(n);
                    CTBackgroundFillStyleListImpl.this.setPattFillArray(n, cTPatternFillProperties);
                    return cTPatternFillProperties2;
                }

                @Override
                public void add(int n, CTPatternFillProperties cTPatternFillProperties) {
                    CTBackgroundFillStyleListImpl.this.insertNewPattFill(n).set(cTPatternFillProperties);
                }

                @Override
                public CTPatternFillProperties remove(int n) {
                    CTPatternFillProperties cTPatternFillProperties = CTBackgroundFillStyleListImpl.this.getPattFillArray(n);
                    CTBackgroundFillStyleListImpl.this.removePattFill(n);
                    return cTPatternFillProperties;
                }

                @Override
                public int size() {
                    return CTBackgroundFillStyleListImpl.this.sizeOfPattFillArray();
                }
            }
            return new PattFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPatternFillProperties[] getPattFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PATTFILL$8, arrayList);
            CTPatternFillProperties[] cTPatternFillPropertiesArray = new CTPatternFillProperties[arrayList.size()];
            arrayList.toArray(cTPatternFillPropertiesArray);
            return cTPatternFillPropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties getPattFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().find_element_user(PATTFILL$8, n));
            if (cTPatternFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPattFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATTFILL$8);
        }
    }

    @Override
    public void setPattFillArray(CTPatternFillProperties[] cTPatternFillPropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPatternFillPropertiesArray, PATTFILL$8);
    }

    @Override
    public void setPattFillArray(int n, CTPatternFillProperties cTPatternFillProperties) {
        this.generatedSetterHelperImpl(cTPatternFillProperties, PATTFILL$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties insertNewPattFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().insert_element_user(PATTFILL$8, n));
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties addNewPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().add_element_user(PATTFILL$8));
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePattFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATTFILL$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGroupFillProperties> getGrpFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GrpFillList
            extends AbstractList<CTGroupFillProperties> {
                GrpFillList() {
                }

                @Override
                public CTGroupFillProperties get(int n) {
                    return CTBackgroundFillStyleListImpl.this.getGrpFillArray(n);
                }

                @Override
                public CTGroupFillProperties set(int n, CTGroupFillProperties cTGroupFillProperties) {
                    CTGroupFillProperties cTGroupFillProperties2 = CTBackgroundFillStyleListImpl.this.getGrpFillArray(n);
                    CTBackgroundFillStyleListImpl.this.setGrpFillArray(n, cTGroupFillProperties);
                    return cTGroupFillProperties2;
                }

                @Override
                public void add(int n, CTGroupFillProperties cTGroupFillProperties) {
                    CTBackgroundFillStyleListImpl.this.insertNewGrpFill(n).set(cTGroupFillProperties);
                }

                @Override
                public CTGroupFillProperties remove(int n) {
                    CTGroupFillProperties cTGroupFillProperties = CTBackgroundFillStyleListImpl.this.getGrpFillArray(n);
                    CTBackgroundFillStyleListImpl.this.removeGrpFill(n);
                    return cTGroupFillProperties;
                }

                @Override
                public int size() {
                    return CTBackgroundFillStyleListImpl.this.sizeOfGrpFillArray();
                }
            }
            return new GrpFillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGroupFillProperties[] getGrpFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRPFILL$10, arrayList);
            CTGroupFillProperties[] cTGroupFillPropertiesArray = new CTGroupFillProperties[arrayList.size()];
            arrayList.toArray(cTGroupFillPropertiesArray);
            return cTGroupFillPropertiesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties getGrpFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().find_element_user(GRPFILL$10, n));
            if (cTGroupFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGrpFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRPFILL$10);
        }
    }

    @Override
    public void setGrpFillArray(CTGroupFillProperties[] cTGroupFillPropertiesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGroupFillPropertiesArray, GRPFILL$10);
    }

    @Override
    public void setGrpFillArray(int n, CTGroupFillProperties cTGroupFillProperties) {
        this.generatedSetterHelperImpl(cTGroupFillProperties, GRPFILL$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties insertNewGrpFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().insert_element_user(GRPFILL$10, n));
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties addNewGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().add_element_user(GRPFILL$10));
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGrpFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRPFILL$10, n);
        }
    }
}

