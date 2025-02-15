/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;

public class CTDrawingImpl
extends XmlComplexContentImpl
implements CTDrawing {
    private static final long serialVersionUID = 1L;
    private static final QName TWOCELLANCHOR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "twoCellAnchor");
    private static final QName ONECELLANCHOR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "oneCellAnchor");
    private static final QName ABSOLUTEANCHOR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "absoluteAnchor");

    public CTDrawingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTwoCellAnchor> getTwoCellAnchorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TwoCellAnchorList
            extends AbstractList<CTTwoCellAnchor> {
                TwoCellAnchorList() {
                }

                @Override
                public CTTwoCellAnchor get(int n) {
                    return CTDrawingImpl.this.getTwoCellAnchorArray(n);
                }

                @Override
                public CTTwoCellAnchor set(int n, CTTwoCellAnchor cTTwoCellAnchor) {
                    CTTwoCellAnchor cTTwoCellAnchor2 = CTDrawingImpl.this.getTwoCellAnchorArray(n);
                    CTDrawingImpl.this.setTwoCellAnchorArray(n, cTTwoCellAnchor);
                    return cTTwoCellAnchor2;
                }

                @Override
                public void add(int n, CTTwoCellAnchor cTTwoCellAnchor) {
                    CTDrawingImpl.this.insertNewTwoCellAnchor(n).set(cTTwoCellAnchor);
                }

                @Override
                public CTTwoCellAnchor remove(int n) {
                    CTTwoCellAnchor cTTwoCellAnchor = CTDrawingImpl.this.getTwoCellAnchorArray(n);
                    CTDrawingImpl.this.removeTwoCellAnchor(n);
                    return cTTwoCellAnchor;
                }

                @Override
                public int size() {
                    return CTDrawingImpl.this.sizeOfTwoCellAnchorArray();
                }
            }
            return new TwoCellAnchorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTwoCellAnchor[] getTwoCellAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TWOCELLANCHOR$0, arrayList);
            CTTwoCellAnchor[] cTTwoCellAnchorArray = new CTTwoCellAnchor[arrayList.size()];
            arrayList.toArray(cTTwoCellAnchorArray);
            return cTTwoCellAnchorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwoCellAnchor getTwoCellAnchorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwoCellAnchor cTTwoCellAnchor = null;
            cTTwoCellAnchor = (CTTwoCellAnchor)((Object)this.get_store().find_element_user(TWOCELLANCHOR$0, n));
            if (cTTwoCellAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTwoCellAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTwoCellAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TWOCELLANCHOR$0);
        }
    }

    @Override
    public void setTwoCellAnchorArray(CTTwoCellAnchor[] cTTwoCellAnchorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTwoCellAnchorArray, TWOCELLANCHOR$0);
    }

    @Override
    public void setTwoCellAnchorArray(int n, CTTwoCellAnchor cTTwoCellAnchor) {
        this.generatedSetterHelperImpl(cTTwoCellAnchor, TWOCELLANCHOR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwoCellAnchor insertNewTwoCellAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwoCellAnchor cTTwoCellAnchor = null;
            cTTwoCellAnchor = (CTTwoCellAnchor)((Object)this.get_store().insert_element_user(TWOCELLANCHOR$0, n));
            return cTTwoCellAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTwoCellAnchor addNewTwoCellAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTwoCellAnchor cTTwoCellAnchor = null;
            cTTwoCellAnchor = (CTTwoCellAnchor)((Object)this.get_store().add_element_user(TWOCELLANCHOR$0));
            return cTTwoCellAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTwoCellAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TWOCELLANCHOR$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOneCellAnchor> getOneCellAnchorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OneCellAnchorList
            extends AbstractList<CTOneCellAnchor> {
                OneCellAnchorList() {
                }

                @Override
                public CTOneCellAnchor get(int n) {
                    return CTDrawingImpl.this.getOneCellAnchorArray(n);
                }

                @Override
                public CTOneCellAnchor set(int n, CTOneCellAnchor cTOneCellAnchor) {
                    CTOneCellAnchor cTOneCellAnchor2 = CTDrawingImpl.this.getOneCellAnchorArray(n);
                    CTDrawingImpl.this.setOneCellAnchorArray(n, cTOneCellAnchor);
                    return cTOneCellAnchor2;
                }

                @Override
                public void add(int n, CTOneCellAnchor cTOneCellAnchor) {
                    CTDrawingImpl.this.insertNewOneCellAnchor(n).set(cTOneCellAnchor);
                }

                @Override
                public CTOneCellAnchor remove(int n) {
                    CTOneCellAnchor cTOneCellAnchor = CTDrawingImpl.this.getOneCellAnchorArray(n);
                    CTDrawingImpl.this.removeOneCellAnchor(n);
                    return cTOneCellAnchor;
                }

                @Override
                public int size() {
                    return CTDrawingImpl.this.sizeOfOneCellAnchorArray();
                }
            }
            return new OneCellAnchorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOneCellAnchor[] getOneCellAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ONECELLANCHOR$2, arrayList);
            CTOneCellAnchor[] cTOneCellAnchorArray = new CTOneCellAnchor[arrayList.size()];
            arrayList.toArray(cTOneCellAnchorArray);
            return cTOneCellAnchorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOneCellAnchor getOneCellAnchorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOneCellAnchor cTOneCellAnchor = null;
            cTOneCellAnchor = (CTOneCellAnchor)((Object)this.get_store().find_element_user(ONECELLANCHOR$2, n));
            if (cTOneCellAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOneCellAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOneCellAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ONECELLANCHOR$2);
        }
    }

    @Override
    public void setOneCellAnchorArray(CTOneCellAnchor[] cTOneCellAnchorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOneCellAnchorArray, ONECELLANCHOR$2);
    }

    @Override
    public void setOneCellAnchorArray(int n, CTOneCellAnchor cTOneCellAnchor) {
        this.generatedSetterHelperImpl(cTOneCellAnchor, ONECELLANCHOR$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOneCellAnchor insertNewOneCellAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOneCellAnchor cTOneCellAnchor = null;
            cTOneCellAnchor = (CTOneCellAnchor)((Object)this.get_store().insert_element_user(ONECELLANCHOR$2, n));
            return cTOneCellAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOneCellAnchor addNewOneCellAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOneCellAnchor cTOneCellAnchor = null;
            cTOneCellAnchor = (CTOneCellAnchor)((Object)this.get_store().add_element_user(ONECELLANCHOR$2));
            return cTOneCellAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOneCellAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ONECELLANCHOR$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAbsoluteAnchor> getAbsoluteAnchorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AbsoluteAnchorList
            extends AbstractList<CTAbsoluteAnchor> {
                AbsoluteAnchorList() {
                }

                @Override
                public CTAbsoluteAnchor get(int n) {
                    return CTDrawingImpl.this.getAbsoluteAnchorArray(n);
                }

                @Override
                public CTAbsoluteAnchor set(int n, CTAbsoluteAnchor cTAbsoluteAnchor) {
                    CTAbsoluteAnchor cTAbsoluteAnchor2 = CTDrawingImpl.this.getAbsoluteAnchorArray(n);
                    CTDrawingImpl.this.setAbsoluteAnchorArray(n, cTAbsoluteAnchor);
                    return cTAbsoluteAnchor2;
                }

                @Override
                public void add(int n, CTAbsoluteAnchor cTAbsoluteAnchor) {
                    CTDrawingImpl.this.insertNewAbsoluteAnchor(n).set(cTAbsoluteAnchor);
                }

                @Override
                public CTAbsoluteAnchor remove(int n) {
                    CTAbsoluteAnchor cTAbsoluteAnchor = CTDrawingImpl.this.getAbsoluteAnchorArray(n);
                    CTDrawingImpl.this.removeAbsoluteAnchor(n);
                    return cTAbsoluteAnchor;
                }

                @Override
                public int size() {
                    return CTDrawingImpl.this.sizeOfAbsoluteAnchorArray();
                }
            }
            return new AbsoluteAnchorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAbsoluteAnchor[] getAbsoluteAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ABSOLUTEANCHOR$4, arrayList);
            CTAbsoluteAnchor[] cTAbsoluteAnchorArray = new CTAbsoluteAnchor[arrayList.size()];
            arrayList.toArray(cTAbsoluteAnchorArray);
            return cTAbsoluteAnchorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAbsoluteAnchor getAbsoluteAnchorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAbsoluteAnchor cTAbsoluteAnchor = null;
            cTAbsoluteAnchor = (CTAbsoluteAnchor)((Object)this.get_store().find_element_user(ABSOLUTEANCHOR$4, n));
            if (cTAbsoluteAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAbsoluteAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAbsoluteAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ABSOLUTEANCHOR$4);
        }
    }

    @Override
    public void setAbsoluteAnchorArray(CTAbsoluteAnchor[] cTAbsoluteAnchorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAbsoluteAnchorArray, ABSOLUTEANCHOR$4);
    }

    @Override
    public void setAbsoluteAnchorArray(int n, CTAbsoluteAnchor cTAbsoluteAnchor) {
        this.generatedSetterHelperImpl(cTAbsoluteAnchor, ABSOLUTEANCHOR$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAbsoluteAnchor insertNewAbsoluteAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAbsoluteAnchor cTAbsoluteAnchor = null;
            cTAbsoluteAnchor = (CTAbsoluteAnchor)((Object)this.get_store().insert_element_user(ABSOLUTEANCHOR$4, n));
            return cTAbsoluteAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAbsoluteAnchor addNewAbsoluteAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAbsoluteAnchor cTAbsoluteAnchor = null;
            cTAbsoluteAnchor = (CTAbsoluteAnchor)((Object)this.get_store().add_element_user(ABSOLUTEANCHOR$4));
            return cTAbsoluteAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAbsoluteAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ABSOLUTEANCHOR$4, n);
        }
    }
}

