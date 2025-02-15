/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

public class CTDrawingImpl
extends XmlComplexContentImpl
implements CTDrawing {
    private static final long serialVersionUID = 1L;
    private static final QName ANCHOR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "anchor");
    private static final QName INLINE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "inline");

    public CTDrawingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAnchor> getAnchorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AnchorList
            extends AbstractList<CTAnchor> {
                AnchorList() {
                }

                @Override
                public CTAnchor get(int n) {
                    return CTDrawingImpl.this.getAnchorArray(n);
                }

                @Override
                public CTAnchor set(int n, CTAnchor cTAnchor) {
                    CTAnchor cTAnchor2 = CTDrawingImpl.this.getAnchorArray(n);
                    CTDrawingImpl.this.setAnchorArray(n, cTAnchor);
                    return cTAnchor2;
                }

                @Override
                public void add(int n, CTAnchor cTAnchor) {
                    CTDrawingImpl.this.insertNewAnchor(n).set(cTAnchor);
                }

                @Override
                public CTAnchor remove(int n) {
                    CTAnchor cTAnchor = CTDrawingImpl.this.getAnchorArray(n);
                    CTDrawingImpl.this.removeAnchor(n);
                    return cTAnchor;
                }

                @Override
                public int size() {
                    return CTDrawingImpl.this.sizeOfAnchorArray();
                }
            }
            return new AnchorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAnchor[] getAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ANCHOR$0, arrayList);
            CTAnchor[] cTAnchorArray = new CTAnchor[arrayList.size()];
            arrayList.toArray(cTAnchorArray);
            return cTAnchorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchor getAnchorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchor cTAnchor = null;
            cTAnchor = (CTAnchor)((Object)this.get_store().find_element_user(ANCHOR$0, n));
            if (cTAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAnchorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANCHOR$0);
        }
    }

    @Override
    public void setAnchorArray(CTAnchor[] cTAnchorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAnchorArray, ANCHOR$0);
    }

    @Override
    public void setAnchorArray(int n, CTAnchor cTAnchor) {
        this.generatedSetterHelperImpl(cTAnchor, ANCHOR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchor insertNewAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchor cTAnchor = null;
            cTAnchor = (CTAnchor)((Object)this.get_store().insert_element_user(ANCHOR$0, n));
            return cTAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchor addNewAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchor cTAnchor = null;
            cTAnchor = (CTAnchor)((Object)this.get_store().add_element_user(ANCHOR$0));
            return cTAnchor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAnchor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANCHOR$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTInline> getInlineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class InlineList
            extends AbstractList<CTInline> {
                InlineList() {
                }

                @Override
                public CTInline get(int n) {
                    return CTDrawingImpl.this.getInlineArray(n);
                }

                @Override
                public CTInline set(int n, CTInline cTInline) {
                    CTInline cTInline2 = CTDrawingImpl.this.getInlineArray(n);
                    CTDrawingImpl.this.setInlineArray(n, cTInline);
                    return cTInline2;
                }

                @Override
                public void add(int n, CTInline cTInline) {
                    CTDrawingImpl.this.insertNewInline(n).set(cTInline);
                }

                @Override
                public CTInline remove(int n) {
                    CTInline cTInline = CTDrawingImpl.this.getInlineArray(n);
                    CTDrawingImpl.this.removeInline(n);
                    return cTInline;
                }

                @Override
                public int size() {
                    return CTDrawingImpl.this.sizeOfInlineArray();
                }
            }
            return new InlineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTInline[] getInlineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INLINE$2, arrayList);
            CTInline[] cTInlineArray = new CTInline[arrayList.size()];
            arrayList.toArray(cTInlineArray);
            return cTInlineArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInline getInlineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInline cTInline = null;
            cTInline = (CTInline)((Object)this.get_store().find_element_user(INLINE$2, n));
            if (cTInline == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTInline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfInlineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INLINE$2);
        }
    }

    @Override
    public void setInlineArray(CTInline[] cTInlineArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTInlineArray, INLINE$2);
    }

    @Override
    public void setInlineArray(int n, CTInline cTInline) {
        this.generatedSetterHelperImpl(cTInline, INLINE$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInline insertNewInline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInline cTInline = null;
            cTInline = (CTInline)((Object)this.get_store().insert_element_user(INLINE$2, n));
            return cTInline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInline addNewInline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInline cTInline = null;
            cTInline = (CTInline)((Object)this.get_store().add_element_user(INLINE$2));
            return cTInline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INLINE$2, n);
        }
    }
}

