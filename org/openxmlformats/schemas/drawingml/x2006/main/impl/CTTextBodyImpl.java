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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;

public class CTTextBodyImpl
extends XmlComplexContentImpl
implements CTTextBody {
    private static final long serialVersionUID = 1L;
    private static final QName BODYPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "bodyPr");
    private static final QName LSTSTYLE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lstStyle");
    private static final QName P$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "p");

    public CTTextBodyImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBodyProperties getBodyPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBodyProperties cTTextBodyProperties = null;
            cTTextBodyProperties = (CTTextBodyProperties)((Object)this.get_store().find_element_user(BODYPR$0, 0));
            if (cTTextBodyProperties == null) {
                return null;
            }
            return cTTextBodyProperties;
        }
    }

    @Override
    public void setBodyPr(CTTextBodyProperties cTTextBodyProperties) {
        this.generatedSetterHelperImpl(cTTextBodyProperties, BODYPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBodyProperties addNewBodyPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBodyProperties cTTextBodyProperties = null;
            cTTextBodyProperties = (CTTextBodyProperties)((Object)this.get_store().add_element_user(BODYPR$0));
            return cTTextBodyProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle getLstStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().find_element_user(LSTSTYLE$2, 0));
            if (cTTextListStyle == null) {
                return null;
            }
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLstStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LSTSTYLE$2) != 0;
        }
    }

    @Override
    public void setLstStyle(CTTextListStyle cTTextListStyle) {
        this.generatedSetterHelperImpl(cTTextListStyle, LSTSTYLE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle addNewLstStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().add_element_user(LSTSTYLE$2));
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLstStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LSTSTYLE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTextParagraph> getPList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PList
            extends AbstractList<CTTextParagraph> {
                PList() {
                }

                @Override
                public CTTextParagraph get(int n) {
                    return CTTextBodyImpl.this.getPArray(n);
                }

                @Override
                public CTTextParagraph set(int n, CTTextParagraph cTTextParagraph) {
                    CTTextParagraph cTTextParagraph2 = CTTextBodyImpl.this.getPArray(n);
                    CTTextBodyImpl.this.setPArray(n, cTTextParagraph);
                    return cTTextParagraph2;
                }

                @Override
                public void add(int n, CTTextParagraph cTTextParagraph) {
                    CTTextBodyImpl.this.insertNewP(n).set(cTTextParagraph);
                }

                @Override
                public CTTextParagraph remove(int n) {
                    CTTextParagraph cTTextParagraph = CTTextBodyImpl.this.getPArray(n);
                    CTTextBodyImpl.this.removeP(n);
                    return cTTextParagraph;
                }

                @Override
                public int size() {
                    return CTTextBodyImpl.this.sizeOfPArray();
                }
            }
            return new PList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTextParagraph[] getPArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(P$4, arrayList);
            CTTextParagraph[] cTTextParagraphArray = new CTTextParagraph[arrayList.size()];
            arrayList.toArray(cTTextParagraphArray);
            return cTTextParagraphArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraph getPArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraph cTTextParagraph = null;
            cTTextParagraph = (CTTextParagraph)((Object)this.get_store().find_element_user(P$4, n));
            if (cTTextParagraph == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTextParagraph;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(P$4);
        }
    }

    @Override
    public void setPArray(CTTextParagraph[] cTTextParagraphArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextParagraphArray, P$4);
    }

    @Override
    public void setPArray(int n, CTTextParagraph cTTextParagraph) {
        this.generatedSetterHelperImpl(cTTextParagraph, P$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraph insertNewP(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraph cTTextParagraph = null;
            cTTextParagraph = (CTTextParagraph)((Object)this.get_store().insert_element_user(P$4, n));
            return cTTextParagraph;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextParagraph addNewP() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextParagraph cTTextParagraph = null;
            cTTextParagraph = (CTTextParagraph)((Object)this.get_store().add_element_user(P$4));
            return cTTextParagraph;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeP(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(P$4, n);
        }
    }
}

