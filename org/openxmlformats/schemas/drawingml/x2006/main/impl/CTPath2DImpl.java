/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
 *  org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;

public class CTPath2DImpl
extends XmlComplexContentImpl
implements CTPath2D {
    private static final long serialVersionUID = 1L;
    private static final QName CLOSE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "close");
    private static final QName MOVETO$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "moveTo");
    private static final QName LNTO$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnTo");
    private static final QName ARCTO$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "arcTo");
    private static final QName QUADBEZTO$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "quadBezTo");
    private static final QName CUBICBEZTO$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cubicBezTo");
    private static final QName W$12 = new QName("", "w");
    private static final QName H$14 = new QName("", "h");
    private static final QName FILL$16 = new QName("", "fill");
    private static final QName STROKE$18 = new QName("", "stroke");
    private static final QName EXTRUSIONOK$20 = new QName("", "extrusionOk");

    public CTPath2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2DClose> getCloseList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CloseList
            extends AbstractList<CTPath2DClose> {
                CloseList() {
                }

                @Override
                public CTPath2DClose get(int n) {
                    return CTPath2DImpl.this.getCloseArray(n);
                }

                @Override
                public CTPath2DClose set(int n, CTPath2DClose cTPath2DClose) {
                    CTPath2DClose cTPath2DClose2 = CTPath2DImpl.this.getCloseArray(n);
                    CTPath2DImpl.this.setCloseArray(n, cTPath2DClose);
                    return cTPath2DClose2;
                }

                @Override
                public void add(int n, CTPath2DClose cTPath2DClose) {
                    CTPath2DImpl.this.insertNewClose(n).set(cTPath2DClose);
                }

                @Override
                public CTPath2DClose remove(int n) {
                    CTPath2DClose cTPath2DClose = CTPath2DImpl.this.getCloseArray(n);
                    CTPath2DImpl.this.removeClose(n);
                    return cTPath2DClose;
                }

                @Override
                public int size() {
                    return CTPath2DImpl.this.sizeOfCloseArray();
                }
            }
            return new CloseList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2DClose[] getCloseArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLOSE$0, arrayList);
            CTPath2DClose[] cTPath2DCloseArray = new CTPath2DClose[arrayList.size()];
            arrayList.toArray(cTPath2DCloseArray);
            return cTPath2DCloseArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DClose getCloseArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DClose cTPath2DClose = null;
            cTPath2DClose = (CTPath2DClose)((Object)this.get_store().find_element_user(CLOSE$0, n));
            if (cTPath2DClose == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2DClose;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCloseArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLOSE$0);
        }
    }

    @Override
    public void setCloseArray(CTPath2DClose[] cTPath2DCloseArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPath2DCloseArray, CLOSE$0);
    }

    @Override
    public void setCloseArray(int n, CTPath2DClose cTPath2DClose) {
        this.generatedSetterHelperImpl(cTPath2DClose, CLOSE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DClose insertNewClose(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DClose cTPath2DClose = null;
            cTPath2DClose = (CTPath2DClose)((Object)this.get_store().insert_element_user(CLOSE$0, n));
            return cTPath2DClose;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DClose addNewClose() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DClose cTPath2DClose = null;
            cTPath2DClose = (CTPath2DClose)((Object)this.get_store().add_element_user(CLOSE$0));
            return cTPath2DClose;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClose(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLOSE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2DMoveTo> getMoveToList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MoveToList
            extends AbstractList<CTPath2DMoveTo> {
                MoveToList() {
                }

                @Override
                public CTPath2DMoveTo get(int n) {
                    return CTPath2DImpl.this.getMoveToArray(n);
                }

                @Override
                public CTPath2DMoveTo set(int n, CTPath2DMoveTo cTPath2DMoveTo) {
                    CTPath2DMoveTo cTPath2DMoveTo2 = CTPath2DImpl.this.getMoveToArray(n);
                    CTPath2DImpl.this.setMoveToArray(n, cTPath2DMoveTo);
                    return cTPath2DMoveTo2;
                }

                @Override
                public void add(int n, CTPath2DMoveTo cTPath2DMoveTo) {
                    CTPath2DImpl.this.insertNewMoveTo(n).set(cTPath2DMoveTo);
                }

                @Override
                public CTPath2DMoveTo remove(int n) {
                    CTPath2DMoveTo cTPath2DMoveTo = CTPath2DImpl.this.getMoveToArray(n);
                    CTPath2DImpl.this.removeMoveTo(n);
                    return cTPath2DMoveTo;
                }

                @Override
                public int size() {
                    return CTPath2DImpl.this.sizeOfMoveToArray();
                }
            }
            return new MoveToList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2DMoveTo[] getMoveToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MOVETO$2, arrayList);
            CTPath2DMoveTo[] cTPath2DMoveToArray = new CTPath2DMoveTo[arrayList.size()];
            arrayList.toArray(cTPath2DMoveToArray);
            return cTPath2DMoveToArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DMoveTo getMoveToArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DMoveTo cTPath2DMoveTo = null;
            cTPath2DMoveTo = (CTPath2DMoveTo)((Object)this.get_store().find_element_user(MOVETO$2, n));
            if (cTPath2DMoveTo == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2DMoveTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMoveToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVETO$2);
        }
    }

    @Override
    public void setMoveToArray(CTPath2DMoveTo[] cTPath2DMoveToArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPath2DMoveToArray, MOVETO$2);
    }

    @Override
    public void setMoveToArray(int n, CTPath2DMoveTo cTPath2DMoveTo) {
        this.generatedSetterHelperImpl(cTPath2DMoveTo, MOVETO$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DMoveTo insertNewMoveTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DMoveTo cTPath2DMoveTo = null;
            cTPath2DMoveTo = (CTPath2DMoveTo)((Object)this.get_store().insert_element_user(MOVETO$2, n));
            return cTPath2DMoveTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DMoveTo addNewMoveTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DMoveTo cTPath2DMoveTo = null;
            cTPath2DMoveTo = (CTPath2DMoveTo)((Object)this.get_store().add_element_user(MOVETO$2));
            return cTPath2DMoveTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMoveTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVETO$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2DLineTo> getLnToList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LnToList
            extends AbstractList<CTPath2DLineTo> {
                LnToList() {
                }

                @Override
                public CTPath2DLineTo get(int n) {
                    return CTPath2DImpl.this.getLnToArray(n);
                }

                @Override
                public CTPath2DLineTo set(int n, CTPath2DLineTo cTPath2DLineTo) {
                    CTPath2DLineTo cTPath2DLineTo2 = CTPath2DImpl.this.getLnToArray(n);
                    CTPath2DImpl.this.setLnToArray(n, cTPath2DLineTo);
                    return cTPath2DLineTo2;
                }

                @Override
                public void add(int n, CTPath2DLineTo cTPath2DLineTo) {
                    CTPath2DImpl.this.insertNewLnTo(n).set(cTPath2DLineTo);
                }

                @Override
                public CTPath2DLineTo remove(int n) {
                    CTPath2DLineTo cTPath2DLineTo = CTPath2DImpl.this.getLnToArray(n);
                    CTPath2DImpl.this.removeLnTo(n);
                    return cTPath2DLineTo;
                }

                @Override
                public int size() {
                    return CTPath2DImpl.this.sizeOfLnToArray();
                }
            }
            return new LnToList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2DLineTo[] getLnToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LNTO$4, arrayList);
            CTPath2DLineTo[] cTPath2DLineToArray = new CTPath2DLineTo[arrayList.size()];
            arrayList.toArray(cTPath2DLineToArray);
            return cTPath2DLineToArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DLineTo getLnToArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DLineTo cTPath2DLineTo = null;
            cTPath2DLineTo = (CTPath2DLineTo)((Object)this.get_store().find_element_user(LNTO$4, n));
            if (cTPath2DLineTo == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2DLineTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLnToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNTO$4);
        }
    }

    @Override
    public void setLnToArray(CTPath2DLineTo[] cTPath2DLineToArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPath2DLineToArray, LNTO$4);
    }

    @Override
    public void setLnToArray(int n, CTPath2DLineTo cTPath2DLineTo) {
        this.generatedSetterHelperImpl(cTPath2DLineTo, LNTO$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DLineTo insertNewLnTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DLineTo cTPath2DLineTo = null;
            cTPath2DLineTo = (CTPath2DLineTo)((Object)this.get_store().insert_element_user(LNTO$4, n));
            return cTPath2DLineTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DLineTo addNewLnTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DLineTo cTPath2DLineTo = null;
            cTPath2DLineTo = (CTPath2DLineTo)((Object)this.get_store().add_element_user(LNTO$4));
            return cTPath2DLineTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLnTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNTO$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2DArcTo> getArcToList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ArcToList
            extends AbstractList<CTPath2DArcTo> {
                ArcToList() {
                }

                @Override
                public CTPath2DArcTo get(int n) {
                    return CTPath2DImpl.this.getArcToArray(n);
                }

                @Override
                public CTPath2DArcTo set(int n, CTPath2DArcTo cTPath2DArcTo) {
                    CTPath2DArcTo cTPath2DArcTo2 = CTPath2DImpl.this.getArcToArray(n);
                    CTPath2DImpl.this.setArcToArray(n, cTPath2DArcTo);
                    return cTPath2DArcTo2;
                }

                @Override
                public void add(int n, CTPath2DArcTo cTPath2DArcTo) {
                    CTPath2DImpl.this.insertNewArcTo(n).set((XmlObject)cTPath2DArcTo);
                }

                @Override
                public CTPath2DArcTo remove(int n) {
                    CTPath2DArcTo cTPath2DArcTo = CTPath2DImpl.this.getArcToArray(n);
                    CTPath2DImpl.this.removeArcTo(n);
                    return cTPath2DArcTo;
                }

                @Override
                public int size() {
                    return CTPath2DImpl.this.sizeOfArcToArray();
                }
            }
            return new ArcToList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2DArcTo[] getArcToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ARCTO$6, arrayList);
            CTPath2DArcTo[] cTPath2DArcToArray = new CTPath2DArcTo[arrayList.size()];
            arrayList.toArray(cTPath2DArcToArray);
            return cTPath2DArcToArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DArcTo getArcToArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DArcTo cTPath2DArcTo = null;
            cTPath2DArcTo = (CTPath2DArcTo)this.get_store().find_element_user(ARCTO$6, n);
            if (cTPath2DArcTo == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2DArcTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfArcToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ARCTO$6);
        }
    }

    @Override
    public void setArcToArray(CTPath2DArcTo[] cTPath2DArcToArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPath2DArcToArray, ARCTO$6);
    }

    @Override
    public void setArcToArray(int n, CTPath2DArcTo cTPath2DArcTo) {
        this.generatedSetterHelperImpl((XmlObject)cTPath2DArcTo, ARCTO$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DArcTo insertNewArcTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DArcTo cTPath2DArcTo = null;
            cTPath2DArcTo = (CTPath2DArcTo)this.get_store().insert_element_user(ARCTO$6, n);
            return cTPath2DArcTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DArcTo addNewArcTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DArcTo cTPath2DArcTo = null;
            cTPath2DArcTo = (CTPath2DArcTo)this.get_store().add_element_user(ARCTO$6);
            return cTPath2DArcTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeArcTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ARCTO$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2DQuadBezierTo> getQuadBezToList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class QuadBezToList
            extends AbstractList<CTPath2DQuadBezierTo> {
                QuadBezToList() {
                }

                @Override
                public CTPath2DQuadBezierTo get(int n) {
                    return CTPath2DImpl.this.getQuadBezToArray(n);
                }

                @Override
                public CTPath2DQuadBezierTo set(int n, CTPath2DQuadBezierTo cTPath2DQuadBezierTo) {
                    CTPath2DQuadBezierTo cTPath2DQuadBezierTo2 = CTPath2DImpl.this.getQuadBezToArray(n);
                    CTPath2DImpl.this.setQuadBezToArray(n, cTPath2DQuadBezierTo);
                    return cTPath2DQuadBezierTo2;
                }

                @Override
                public void add(int n, CTPath2DQuadBezierTo cTPath2DQuadBezierTo) {
                    CTPath2DImpl.this.insertNewQuadBezTo(n).set(cTPath2DQuadBezierTo);
                }

                @Override
                public CTPath2DQuadBezierTo remove(int n) {
                    CTPath2DQuadBezierTo cTPath2DQuadBezierTo = CTPath2DImpl.this.getQuadBezToArray(n);
                    CTPath2DImpl.this.removeQuadBezTo(n);
                    return cTPath2DQuadBezierTo;
                }

                @Override
                public int size() {
                    return CTPath2DImpl.this.sizeOfQuadBezToArray();
                }
            }
            return new QuadBezToList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2DQuadBezierTo[] getQuadBezToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(QUADBEZTO$8, arrayList);
            CTPath2DQuadBezierTo[] cTPath2DQuadBezierToArray = new CTPath2DQuadBezierTo[arrayList.size()];
            arrayList.toArray(cTPath2DQuadBezierToArray);
            return cTPath2DQuadBezierToArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DQuadBezierTo getQuadBezToArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DQuadBezierTo cTPath2DQuadBezierTo = null;
            cTPath2DQuadBezierTo = (CTPath2DQuadBezierTo)((Object)this.get_store().find_element_user(QUADBEZTO$8, n));
            if (cTPath2DQuadBezierTo == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2DQuadBezierTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfQuadBezToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(QUADBEZTO$8);
        }
    }

    @Override
    public void setQuadBezToArray(CTPath2DQuadBezierTo[] cTPath2DQuadBezierToArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPath2DQuadBezierToArray, QUADBEZTO$8);
    }

    @Override
    public void setQuadBezToArray(int n, CTPath2DQuadBezierTo cTPath2DQuadBezierTo) {
        this.generatedSetterHelperImpl(cTPath2DQuadBezierTo, QUADBEZTO$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DQuadBezierTo insertNewQuadBezTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DQuadBezierTo cTPath2DQuadBezierTo = null;
            cTPath2DQuadBezierTo = (CTPath2DQuadBezierTo)((Object)this.get_store().insert_element_user(QUADBEZTO$8, n));
            return cTPath2DQuadBezierTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DQuadBezierTo addNewQuadBezTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DQuadBezierTo cTPath2DQuadBezierTo = null;
            cTPath2DQuadBezierTo = (CTPath2DQuadBezierTo)((Object)this.get_store().add_element_user(QUADBEZTO$8));
            return cTPath2DQuadBezierTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeQuadBezTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(QUADBEZTO$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath2DCubicBezierTo> getCubicBezToList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CubicBezToList
            extends AbstractList<CTPath2DCubicBezierTo> {
                CubicBezToList() {
                }

                @Override
                public CTPath2DCubicBezierTo get(int n) {
                    return CTPath2DImpl.this.getCubicBezToArray(n);
                }

                @Override
                public CTPath2DCubicBezierTo set(int n, CTPath2DCubicBezierTo cTPath2DCubicBezierTo) {
                    CTPath2DCubicBezierTo cTPath2DCubicBezierTo2 = CTPath2DImpl.this.getCubicBezToArray(n);
                    CTPath2DImpl.this.setCubicBezToArray(n, cTPath2DCubicBezierTo);
                    return cTPath2DCubicBezierTo2;
                }

                @Override
                public void add(int n, CTPath2DCubicBezierTo cTPath2DCubicBezierTo) {
                    CTPath2DImpl.this.insertNewCubicBezTo(n).set(cTPath2DCubicBezierTo);
                }

                @Override
                public CTPath2DCubicBezierTo remove(int n) {
                    CTPath2DCubicBezierTo cTPath2DCubicBezierTo = CTPath2DImpl.this.getCubicBezToArray(n);
                    CTPath2DImpl.this.removeCubicBezTo(n);
                    return cTPath2DCubicBezierTo;
                }

                @Override
                public int size() {
                    return CTPath2DImpl.this.sizeOfCubicBezToArray();
                }
            }
            return new CubicBezToList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath2DCubicBezierTo[] getCubicBezToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CUBICBEZTO$10, arrayList);
            CTPath2DCubicBezierTo[] cTPath2DCubicBezierToArray = new CTPath2DCubicBezierTo[arrayList.size()];
            arrayList.toArray(cTPath2DCubicBezierToArray);
            return cTPath2DCubicBezierToArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DCubicBezierTo getCubicBezToArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DCubicBezierTo cTPath2DCubicBezierTo = null;
            cTPath2DCubicBezierTo = (CTPath2DCubicBezierTo)((Object)this.get_store().find_element_user(CUBICBEZTO$10, n));
            if (cTPath2DCubicBezierTo == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath2DCubicBezierTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCubicBezToArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUBICBEZTO$10);
        }
    }

    @Override
    public void setCubicBezToArray(CTPath2DCubicBezierTo[] cTPath2DCubicBezierToArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPath2DCubicBezierToArray, CUBICBEZTO$10);
    }

    @Override
    public void setCubicBezToArray(int n, CTPath2DCubicBezierTo cTPath2DCubicBezierTo) {
        this.generatedSetterHelperImpl(cTPath2DCubicBezierTo, CUBICBEZTO$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DCubicBezierTo insertNewCubicBezTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DCubicBezierTo cTPath2DCubicBezierTo = null;
            cTPath2DCubicBezierTo = (CTPath2DCubicBezierTo)((Object)this.get_store().insert_element_user(CUBICBEZTO$10, n));
            return cTPath2DCubicBezierTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath2DCubicBezierTo addNewCubicBezTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath2DCubicBezierTo cTPath2DCubicBezierTo = null;
            cTPath2DCubicBezierTo = (CTPath2DCubicBezierTo)((Object)this.get_store().add_element_user(CUBICBEZTO$10));
            return cTPath2DCubicBezierTo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCubicBezTo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUBICBEZTO$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(W$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(W$12));
            }
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveCoordinate xgetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(W$12));
            if (sTPositiveCoordinate == null) {
                sTPositiveCoordinate = (STPositiveCoordinate)this.get_default_attribute_value(W$12);
            }
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(W$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setW(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(W$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(W$12));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetW(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(W$12));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(W$12));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(W$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(H$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(H$14));
            }
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveCoordinate xgetH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(H$14));
            if (sTPositiveCoordinate == null) {
                sTPositiveCoordinate = (STPositiveCoordinate)this.get_default_attribute_value(H$14);
            }
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(H$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setH(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(H$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(H$14));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetH(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(H$14));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(H$14));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(H$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPathFillMode.Enum getFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILL$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(FILL$16));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STPathFillMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPathFillMode xgetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPathFillMode sTPathFillMode = null;
            sTPathFillMode = (STPathFillMode)this.get_store().find_attribute_user(FILL$16);
            if (sTPathFillMode == null) {
                sTPathFillMode = (STPathFillMode)this.get_default_attribute_value(FILL$16);
            }
            return sTPathFillMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILL$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFill(STPathFillMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILL$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILL$16));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFill(STPathFillMode sTPathFillMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPathFillMode sTPathFillMode2 = null;
            sTPathFillMode2 = (STPathFillMode)this.get_store().find_attribute_user(FILL$16);
            if (sTPathFillMode2 == null) {
                sTPathFillMode2 = (STPathFillMode)this.get_store().add_attribute_user(FILL$16);
            }
            sTPathFillMode2.set((XmlObject)sTPathFillMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILL$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getStroke() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKE$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(STROKE$18));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetStroke() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(STROKE$18));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(STROKE$18);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStroke() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STROKE$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStroke(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKE$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STROKE$18));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStroke(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(STROKE$18));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(STROKE$18));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStroke() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STROKE$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getExtrusionOk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EXTRUSIONOK$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(EXTRUSIONOK$20));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetExtrusionOk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(EXTRUSIONOK$20));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(EXTRUSIONOK$20);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtrusionOk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EXTRUSIONOK$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setExtrusionOk(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EXTRUSIONOK$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EXTRUSIONOK$20));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetExtrusionOk(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(EXTRUSIONOK$20));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(EXTRUSIONOK$20));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtrusionOk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EXTRUSIONOK$20);
        }
    }
}

