/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.CTCallout
 *  com.microsoft.schemas.office.office.CTClipPath
 *  com.microsoft.schemas.office.office.CTDiagram
 *  com.microsoft.schemas.office.office.CTExtrusion
 *  com.microsoft.schemas.office.office.CTSignatureLine
 *  com.microsoft.schemas.office.office.CTSkew
 *  com.microsoft.schemas.office.office.STHrAlign
 *  com.microsoft.schemas.office.office.STTrueFalse
 *  com.microsoft.schemas.office.powerpoint.CTRel
 *  com.microsoft.schemas.office.word.CTAnchorLock
 *  com.microsoft.schemas.office.word.CTBorder
 *  com.microsoft.schemas.office.word.CTWrap
 *  com.microsoft.schemas.vml.CTArc
 *  com.microsoft.schemas.vml.CTCurve
 *  com.microsoft.schemas.vml.CTImage
 *  com.microsoft.schemas.vml.CTImageData
 *  com.microsoft.schemas.vml.CTLine
 *  com.microsoft.schemas.vml.CTOval
 *  com.microsoft.schemas.vml.CTPolyLine
 *  com.microsoft.schemas.vml.CTRoundRect
 *  com.microsoft.schemas.vml.STEditAs
 */
package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.office.CTCallout;
import com.microsoft.schemas.office.office.CTClipPath;
import com.microsoft.schemas.office.office.CTDiagram;
import com.microsoft.schemas.office.office.CTExtrusion;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.office.office.CTSkew;
import com.microsoft.schemas.office.office.STHrAlign;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.office.STTrueFalse;
import com.microsoft.schemas.office.powerpoint.CTRel;
import com.microsoft.schemas.office.word.CTAnchorLock;
import com.microsoft.schemas.office.word.CTBorder;
import com.microsoft.schemas.office.word.CTWrap;
import com.microsoft.schemas.vml.CTArc;
import com.microsoft.schemas.vml.CTCurve;
import com.microsoft.schemas.vml.CTFill;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTHandles;
import com.microsoft.schemas.vml.CTImage;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTLine;
import com.microsoft.schemas.vml.CTOval;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTPolyLine;
import com.microsoft.schemas.vml.CTRect;
import com.microsoft.schemas.vml.CTRoundRect;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.CTShapetype;
import com.microsoft.schemas.vml.CTStroke;
import com.microsoft.schemas.vml.CTTextPath;
import com.microsoft.schemas.vml.CTTextbox;
import com.microsoft.schemas.vml.STColorType;
import com.microsoft.schemas.vml.STEditAs;
import com.microsoft.schemas.vml.STTrueFalse;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTGroupImpl
extends XmlComplexContentImpl
implements CTGroup {
    private static final long serialVersionUID = 1L;
    private static final QName PATH$0 = new QName("urn:schemas-microsoft-com:vml", "path");
    private static final QName FORMULAS$2 = new QName("urn:schemas-microsoft-com:vml", "formulas");
    private static final QName HANDLES$4 = new QName("urn:schemas-microsoft-com:vml", "handles");
    private static final QName FILL$6 = new QName("urn:schemas-microsoft-com:vml", "fill");
    private static final QName STROKE$8 = new QName("urn:schemas-microsoft-com:vml", "stroke");
    private static final QName SHADOW$10 = new QName("urn:schemas-microsoft-com:vml", "shadow");
    private static final QName TEXTBOX$12 = new QName("urn:schemas-microsoft-com:vml", "textbox");
    private static final QName TEXTPATH$14 = new QName("urn:schemas-microsoft-com:vml", "textpath");
    private static final QName IMAGEDATA$16 = new QName("urn:schemas-microsoft-com:vml", "imagedata");
    private static final QName SKEW$18 = new QName("urn:schemas-microsoft-com:office:office", "skew");
    private static final QName EXTRUSION$20 = new QName("urn:schemas-microsoft-com:office:office", "extrusion");
    private static final QName CALLOUT$22 = new QName("urn:schemas-microsoft-com:office:office", "callout");
    private static final QName LOCK$24 = new QName("urn:schemas-microsoft-com:office:office", "lock");
    private static final QName CLIPPATH$26 = new QName("urn:schemas-microsoft-com:office:office", "clippath");
    private static final QName SIGNATURELINE$28 = new QName("urn:schemas-microsoft-com:office:office", "signatureline");
    private static final QName WRAP$30 = new QName("urn:schemas-microsoft-com:office:word", "wrap");
    private static final QName ANCHORLOCK$32 = new QName("urn:schemas-microsoft-com:office:word", "anchorlock");
    private static final QName BORDERTOP$34 = new QName("urn:schemas-microsoft-com:office:word", "bordertop");
    private static final QName BORDERBOTTOM$36 = new QName("urn:schemas-microsoft-com:office:word", "borderbottom");
    private static final QName BORDERLEFT$38 = new QName("urn:schemas-microsoft-com:office:word", "borderleft");
    private static final QName BORDERRIGHT$40 = new QName("urn:schemas-microsoft-com:office:word", "borderright");
    private static final QName CLIENTDATA$42 = new QName("urn:schemas-microsoft-com:office:excel", "ClientData");
    private static final QName TEXTDATA$44 = new QName("urn:schemas-microsoft-com:office:powerpoint", "textdata");
    private static final QName GROUP$46 = new QName("urn:schemas-microsoft-com:vml", "group");
    private static final QName SHAPE$48 = new QName("urn:schemas-microsoft-com:vml", "shape");
    private static final QName SHAPETYPE$50 = new QName("urn:schemas-microsoft-com:vml", "shapetype");
    private static final QName ARC$52 = new QName("urn:schemas-microsoft-com:vml", "arc");
    private static final QName CURVE$54 = new QName("urn:schemas-microsoft-com:vml", "curve");
    private static final QName IMAGE$56 = new QName("urn:schemas-microsoft-com:vml", "image");
    private static final QName LINE$58 = new QName("urn:schemas-microsoft-com:vml", "line");
    private static final QName OVAL$60 = new QName("urn:schemas-microsoft-com:vml", "oval");
    private static final QName POLYLINE$62 = new QName("urn:schemas-microsoft-com:vml", "polyline");
    private static final QName RECT$64 = new QName("urn:schemas-microsoft-com:vml", "rect");
    private static final QName ROUNDRECT$66 = new QName("urn:schemas-microsoft-com:vml", "roundrect");
    private static final QName DIAGRAM$68 = new QName("urn:schemas-microsoft-com:office:office", "diagram");
    private static final QName ID$70 = new QName("", "id");
    private static final QName STYLE$72 = new QName("", "style");
    private static final QName HREF$74 = new QName("", "href");
    private static final QName TARGET$76 = new QName("", "target");
    private static final QName CLASS1$78 = new QName("", "class");
    private static final QName TITLE$80 = new QName("", "title");
    private static final QName ALT$82 = new QName("", "alt");
    private static final QName COORDSIZE$84 = new QName("", "coordsize");
    private static final QName COORDORIGIN$86 = new QName("", "coordorigin");
    private static final QName WRAPCOORDS$88 = new QName("", "wrapcoords");
    private static final QName PRINT$90 = new QName("", "print");
    private static final QName SPID$92 = new QName("urn:schemas-microsoft-com:office:office", "spid");
    private static final QName ONED$94 = new QName("urn:schemas-microsoft-com:office:office", "oned");
    private static final QName REGROUPID$96 = new QName("urn:schemas-microsoft-com:office:office", "regroupid");
    private static final QName DOUBLECLICKNOTIFY$98 = new QName("urn:schemas-microsoft-com:office:office", "doubleclicknotify");
    private static final QName BUTTON$100 = new QName("urn:schemas-microsoft-com:office:office", "button");
    private static final QName USERHIDDEN$102 = new QName("urn:schemas-microsoft-com:office:office", "userhidden");
    private static final QName BULLET$104 = new QName("urn:schemas-microsoft-com:office:office", "bullet");
    private static final QName HR$106 = new QName("urn:schemas-microsoft-com:office:office", "hr");
    private static final QName HRSTD$108 = new QName("urn:schemas-microsoft-com:office:office", "hrstd");
    private static final QName HRNOSHADE$110 = new QName("urn:schemas-microsoft-com:office:office", "hrnoshade");
    private static final QName HRPCT$112 = new QName("urn:schemas-microsoft-com:office:office", "hrpct");
    private static final QName HRALIGN$114 = new QName("urn:schemas-microsoft-com:office:office", "hralign");
    private static final QName ALLOWINCELL$116 = new QName("urn:schemas-microsoft-com:office:office", "allowincell");
    private static final QName ALLOWOVERLAP$118 = new QName("urn:schemas-microsoft-com:office:office", "allowoverlap");
    private static final QName USERDRAWN$120 = new QName("urn:schemas-microsoft-com:office:office", "userdrawn");
    private static final QName BORDERTOPCOLOR$122 = new QName("urn:schemas-microsoft-com:office:office", "bordertopcolor");
    private static final QName BORDERLEFTCOLOR$124 = new QName("urn:schemas-microsoft-com:office:office", "borderleftcolor");
    private static final QName BORDERBOTTOMCOLOR$126 = new QName("urn:schemas-microsoft-com:office:office", "borderbottomcolor");
    private static final QName BORDERRIGHTCOLOR$128 = new QName("urn:schemas-microsoft-com:office:office", "borderrightcolor");
    private static final QName DGMLAYOUT$130 = new QName("urn:schemas-microsoft-com:office:office", "dgmlayout");
    private static final QName DGMNODEKIND$132 = new QName("urn:schemas-microsoft-com:office:office", "dgmnodekind");
    private static final QName DGMLAYOUTMRU$134 = new QName("urn:schemas-microsoft-com:office:office", "dgmlayoutmru");
    private static final QName INSETMODE$136 = new QName("urn:schemas-microsoft-com:office:office", "insetmode");
    private static final QName FILLED$138 = new QName("", "filled");
    private static final QName FILLCOLOR$140 = new QName("", "fillcolor");
    private static final QName EDITAS$142 = new QName("", "editas");
    private static final QName TABLEPROPERTIES$144 = new QName("urn:schemas-microsoft-com:office:office", "tableproperties");
    private static final QName TABLELIMITS$146 = new QName("urn:schemas-microsoft-com:office:office", "tablelimits");

    public CTGroupImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPath> getPathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PathList
            extends AbstractList<CTPath> {
                PathList() {
                }

                @Override
                public CTPath get(int n) {
                    return CTGroupImpl.this.getPathArray(n);
                }

                @Override
                public CTPath set(int n, CTPath cTPath) {
                    CTPath cTPath2 = CTGroupImpl.this.getPathArray(n);
                    CTGroupImpl.this.setPathArray(n, cTPath);
                    return cTPath2;
                }

                @Override
                public void add(int n, CTPath cTPath) {
                    CTGroupImpl.this.insertNewPath(n).set(cTPath);
                }

                @Override
                public CTPath remove(int n) {
                    CTPath cTPath = CTGroupImpl.this.getPathArray(n);
                    CTGroupImpl.this.removePath(n);
                    return cTPath;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfPathArray();
                }
            }
            return new PathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPath[] getPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PATH$0, arrayList);
            CTPath[] cTPathArray = new CTPath[arrayList.size()];
            arrayList.toArray(cTPathArray);
            return cTPathArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath getPathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath cTPath = null;
            cTPath = (CTPath)((Object)this.get_store().find_element_user(PATH$0, n));
            if (cTPath == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATH$0);
        }
    }

    @Override
    public void setPathArray(CTPath[] cTPathArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPathArray, PATH$0);
    }

    @Override
    public void setPathArray(int n, CTPath cTPath) {
        this.generatedSetterHelperImpl(cTPath, PATH$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath insertNewPath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath cTPath = null;
            cTPath = (CTPath)((Object)this.get_store().insert_element_user(PATH$0, n));
            return cTPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPath addNewPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPath cTPath = null;
            cTPath = (CTPath)((Object)this.get_store().add_element_user(PATH$0));
            return cTPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATH$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFormulas> getFormulasList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FormulasList
            extends AbstractList<CTFormulas> {
                FormulasList() {
                }

                @Override
                public CTFormulas get(int n) {
                    return CTGroupImpl.this.getFormulasArray(n);
                }

                @Override
                public CTFormulas set(int n, CTFormulas cTFormulas) {
                    CTFormulas cTFormulas2 = CTGroupImpl.this.getFormulasArray(n);
                    CTGroupImpl.this.setFormulasArray(n, cTFormulas);
                    return cTFormulas2;
                }

                @Override
                public void add(int n, CTFormulas cTFormulas) {
                    CTGroupImpl.this.insertNewFormulas(n).set(cTFormulas);
                }

                @Override
                public CTFormulas remove(int n) {
                    CTFormulas cTFormulas = CTGroupImpl.this.getFormulasArray(n);
                    CTGroupImpl.this.removeFormulas(n);
                    return cTFormulas;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfFormulasArray();
                }
            }
            return new FormulasList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFormulas[] getFormulasArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FORMULAS$2, arrayList);
            CTFormulas[] cTFormulasArray = new CTFormulas[arrayList.size()];
            arrayList.toArray(cTFormulasArray);
            return cTFormulasArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFormulas getFormulasArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFormulas cTFormulas = null;
            cTFormulas = (CTFormulas)((Object)this.get_store().find_element_user(FORMULAS$2, n));
            if (cTFormulas == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFormulas;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFormulasArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FORMULAS$2);
        }
    }

    @Override
    public void setFormulasArray(CTFormulas[] cTFormulasArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFormulasArray, FORMULAS$2);
    }

    @Override
    public void setFormulasArray(int n, CTFormulas cTFormulas) {
        this.generatedSetterHelperImpl(cTFormulas, FORMULAS$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFormulas insertNewFormulas(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFormulas cTFormulas = null;
            cTFormulas = (CTFormulas)((Object)this.get_store().insert_element_user(FORMULAS$2, n));
            return cTFormulas;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFormulas addNewFormulas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFormulas cTFormulas = null;
            cTFormulas = (CTFormulas)((Object)this.get_store().add_element_user(FORMULAS$2));
            return cTFormulas;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFormulas(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FORMULAS$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHandles> getHandlesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HandlesList
            extends AbstractList<CTHandles> {
                HandlesList() {
                }

                @Override
                public CTHandles get(int n) {
                    return CTGroupImpl.this.getHandlesArray(n);
                }

                @Override
                public CTHandles set(int n, CTHandles cTHandles) {
                    CTHandles cTHandles2 = CTGroupImpl.this.getHandlesArray(n);
                    CTGroupImpl.this.setHandlesArray(n, cTHandles);
                    return cTHandles2;
                }

                @Override
                public void add(int n, CTHandles cTHandles) {
                    CTGroupImpl.this.insertNewHandles(n).set(cTHandles);
                }

                @Override
                public CTHandles remove(int n) {
                    CTHandles cTHandles = CTGroupImpl.this.getHandlesArray(n);
                    CTGroupImpl.this.removeHandles(n);
                    return cTHandles;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfHandlesArray();
                }
            }
            return new HandlesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHandles[] getHandlesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HANDLES$4, arrayList);
            CTHandles[] cTHandlesArray = new CTHandles[arrayList.size()];
            arrayList.toArray(cTHandlesArray);
            return cTHandlesArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHandles getHandlesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHandles cTHandles = null;
            cTHandles = (CTHandles)((Object)this.get_store().find_element_user(HANDLES$4, n));
            if (cTHandles == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHandles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHandlesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HANDLES$4);
        }
    }

    @Override
    public void setHandlesArray(CTHandles[] cTHandlesArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHandlesArray, HANDLES$4);
    }

    @Override
    public void setHandlesArray(int n, CTHandles cTHandles) {
        this.generatedSetterHelperImpl(cTHandles, HANDLES$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHandles insertNewHandles(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHandles cTHandles = null;
            cTHandles = (CTHandles)((Object)this.get_store().insert_element_user(HANDLES$4, n));
            return cTHandles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHandles addNewHandles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHandles cTHandles = null;
            cTHandles = (CTHandles)((Object)this.get_store().add_element_user(HANDLES$4));
            return cTHandles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHandles(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HANDLES$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFill> getFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FillList
            extends AbstractList<CTFill> {
                FillList() {
                }

                @Override
                public CTFill get(int n) {
                    return CTGroupImpl.this.getFillArray(n);
                }

                @Override
                public CTFill set(int n, CTFill cTFill) {
                    CTFill cTFill2 = CTGroupImpl.this.getFillArray(n);
                    CTGroupImpl.this.setFillArray(n, cTFill);
                    return cTFill2;
                }

                @Override
                public void add(int n, CTFill cTFill) {
                    CTGroupImpl.this.insertNewFill(n).set(cTFill);
                }

                @Override
                public CTFill remove(int n) {
                    CTFill cTFill = CTGroupImpl.this.getFillArray(n);
                    CTGroupImpl.this.removeFill(n);
                    return cTFill;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfFillArray();
                }
            }
            return new FillList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFill[] getFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILL$6, arrayList);
            CTFill[] cTFillArray = new CTFill[arrayList.size()];
            arrayList.toArray(cTFillArray);
            return cTFillArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill getFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().find_element_user(FILL$6, n));
            if (cTFill == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILL$6);
        }
    }

    @Override
    public void setFillArray(CTFill[] cTFillArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFillArray, FILL$6);
    }

    @Override
    public void setFillArray(int n, CTFill cTFill) {
        this.generatedSetterHelperImpl(cTFill, FILL$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill insertNewFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().insert_element_user(FILL$6, n));
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill addNewFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().add_element_user(FILL$6));
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILL$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTStroke> getStrokeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class StrokeList
            extends AbstractList<CTStroke> {
                StrokeList() {
                }

                @Override
                public CTStroke get(int n) {
                    return CTGroupImpl.this.getStrokeArray(n);
                }

                @Override
                public CTStroke set(int n, CTStroke cTStroke) {
                    CTStroke cTStroke2 = CTGroupImpl.this.getStrokeArray(n);
                    CTGroupImpl.this.setStrokeArray(n, cTStroke);
                    return cTStroke2;
                }

                @Override
                public void add(int n, CTStroke cTStroke) {
                    CTGroupImpl.this.insertNewStroke(n).set(cTStroke);
                }

                @Override
                public CTStroke remove(int n) {
                    CTStroke cTStroke = CTGroupImpl.this.getStrokeArray(n);
                    CTGroupImpl.this.removeStroke(n);
                    return cTStroke;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfStrokeArray();
                }
            }
            return new StrokeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTStroke[] getStrokeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(STROKE$8, arrayList);
            CTStroke[] cTStrokeArray = new CTStroke[arrayList.size()];
            arrayList.toArray(cTStrokeArray);
            return cTStrokeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStroke getStrokeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStroke cTStroke = null;
            cTStroke = (CTStroke)((Object)this.get_store().find_element_user(STROKE$8, n));
            if (cTStroke == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTStroke;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfStrokeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STROKE$8);
        }
    }

    @Override
    public void setStrokeArray(CTStroke[] cTStrokeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTStrokeArray, STROKE$8);
    }

    @Override
    public void setStrokeArray(int n, CTStroke cTStroke) {
        this.generatedSetterHelperImpl(cTStroke, STROKE$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStroke insertNewStroke(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStroke cTStroke = null;
            cTStroke = (CTStroke)((Object)this.get_store().insert_element_user(STROKE$8, n));
            return cTStroke;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStroke addNewStroke() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStroke cTStroke = null;
            cTStroke = (CTStroke)((Object)this.get_store().add_element_user(STROKE$8));
            return cTStroke;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStroke(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STROKE$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTShadow> getShadowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShadowList
            extends AbstractList<CTShadow> {
                ShadowList() {
                }

                @Override
                public CTShadow get(int n) {
                    return CTGroupImpl.this.getShadowArray(n);
                }

                @Override
                public CTShadow set(int n, CTShadow cTShadow) {
                    CTShadow cTShadow2 = CTGroupImpl.this.getShadowArray(n);
                    CTGroupImpl.this.setShadowArray(n, cTShadow);
                    return cTShadow2;
                }

                @Override
                public void add(int n, CTShadow cTShadow) {
                    CTGroupImpl.this.insertNewShadow(n).set(cTShadow);
                }

                @Override
                public CTShadow remove(int n) {
                    CTShadow cTShadow = CTGroupImpl.this.getShadowArray(n);
                    CTGroupImpl.this.removeShadow(n);
                    return cTShadow;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfShadowArray();
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
    public CTShadow[] getShadowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHADOW$10, arrayList);
            CTShadow[] cTShadowArray = new CTShadow[arrayList.size()];
            arrayList.toArray(cTShadowArray);
            return cTShadowArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShadow getShadowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShadow cTShadow = null;
            cTShadow = (CTShadow)((Object)this.get_store().find_element_user(SHADOW$10, n));
            if (cTShadow == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTShadow;
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
            return this.get_store().count_elements(SHADOW$10);
        }
    }

    @Override
    public void setShadowArray(CTShadow[] cTShadowArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTShadowArray, SHADOW$10);
    }

    @Override
    public void setShadowArray(int n, CTShadow cTShadow) {
        this.generatedSetterHelperImpl(cTShadow, SHADOW$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShadow insertNewShadow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShadow cTShadow = null;
            cTShadow = (CTShadow)((Object)this.get_store().insert_element_user(SHADOW$10, n));
            return cTShadow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShadow addNewShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShadow cTShadow = null;
            cTShadow = (CTShadow)((Object)this.get_store().add_element_user(SHADOW$10));
            return cTShadow;
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
            this.get_store().remove_element(SHADOW$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTextbox> getTextboxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextboxList
            extends AbstractList<CTTextbox> {
                TextboxList() {
                }

                @Override
                public CTTextbox get(int n) {
                    return CTGroupImpl.this.getTextboxArray(n);
                }

                @Override
                public CTTextbox set(int n, CTTextbox cTTextbox) {
                    CTTextbox cTTextbox2 = CTGroupImpl.this.getTextboxArray(n);
                    CTGroupImpl.this.setTextboxArray(n, cTTextbox);
                    return cTTextbox2;
                }

                @Override
                public void add(int n, CTTextbox cTTextbox) {
                    CTGroupImpl.this.insertNewTextbox(n).set(cTTextbox);
                }

                @Override
                public CTTextbox remove(int n) {
                    CTTextbox cTTextbox = CTGroupImpl.this.getTextboxArray(n);
                    CTGroupImpl.this.removeTextbox(n);
                    return cTTextbox;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfTextboxArray();
                }
            }
            return new TextboxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTextbox[] getTextboxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTBOX$12, arrayList);
            CTTextbox[] cTTextboxArray = new CTTextbox[arrayList.size()];
            arrayList.toArray(cTTextboxArray);
            return cTTextboxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextbox getTextboxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextbox cTTextbox = null;
            cTTextbox = (CTTextbox)((Object)this.get_store().find_element_user(TEXTBOX$12, n));
            if (cTTextbox == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTextbox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextboxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTBOX$12);
        }
    }

    @Override
    public void setTextboxArray(CTTextbox[] cTTextboxArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextboxArray, TEXTBOX$12);
    }

    @Override
    public void setTextboxArray(int n, CTTextbox cTTextbox) {
        this.generatedSetterHelperImpl(cTTextbox, TEXTBOX$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextbox insertNewTextbox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextbox cTTextbox = null;
            cTTextbox = (CTTextbox)((Object)this.get_store().insert_element_user(TEXTBOX$12, n));
            return cTTextbox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextbox addNewTextbox() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextbox cTTextbox = null;
            cTTextbox = (CTTextbox)((Object)this.get_store().add_element_user(TEXTBOX$12));
            return cTTextbox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTextbox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTBOX$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTextPath> getTextpathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextpathList
            extends AbstractList<CTTextPath> {
                TextpathList() {
                }

                @Override
                public CTTextPath get(int n) {
                    return CTGroupImpl.this.getTextpathArray(n);
                }

                @Override
                public CTTextPath set(int n, CTTextPath cTTextPath) {
                    CTTextPath cTTextPath2 = CTGroupImpl.this.getTextpathArray(n);
                    CTGroupImpl.this.setTextpathArray(n, cTTextPath);
                    return cTTextPath2;
                }

                @Override
                public void add(int n, CTTextPath cTTextPath) {
                    CTGroupImpl.this.insertNewTextpath(n).set(cTTextPath);
                }

                @Override
                public CTTextPath remove(int n) {
                    CTTextPath cTTextPath = CTGroupImpl.this.getTextpathArray(n);
                    CTGroupImpl.this.removeTextpath(n);
                    return cTTextPath;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfTextpathArray();
                }
            }
            return new TextpathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTextPath[] getTextpathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTPATH$14, arrayList);
            CTTextPath[] cTTextPathArray = new CTTextPath[arrayList.size()];
            arrayList.toArray(cTTextPathArray);
            return cTTextPathArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextPath getTextpathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextPath cTTextPath = null;
            cTTextPath = (CTTextPath)((Object)this.get_store().find_element_user(TEXTPATH$14, n));
            if (cTTextPath == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTextPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextpathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTPATH$14);
        }
    }

    @Override
    public void setTextpathArray(CTTextPath[] cTTextPathArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextPathArray, TEXTPATH$14);
    }

    @Override
    public void setTextpathArray(int n, CTTextPath cTTextPath) {
        this.generatedSetterHelperImpl(cTTextPath, TEXTPATH$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextPath insertNewTextpath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextPath cTTextPath = null;
            cTTextPath = (CTTextPath)((Object)this.get_store().insert_element_user(TEXTPATH$14, n));
            return cTTextPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextPath addNewTextpath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextPath cTTextPath = null;
            cTTextPath = (CTTextPath)((Object)this.get_store().add_element_user(TEXTPATH$14));
            return cTTextPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTextpath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTPATH$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTImageData> getImagedataList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ImagedataList
            extends AbstractList<CTImageData> {
                ImagedataList() {
                }

                @Override
                public CTImageData get(int n) {
                    return CTGroupImpl.this.getImagedataArray(n);
                }

                @Override
                public CTImageData set(int n, CTImageData cTImageData) {
                    CTImageData cTImageData2 = CTGroupImpl.this.getImagedataArray(n);
                    CTGroupImpl.this.setImagedataArray(n, cTImageData);
                    return cTImageData2;
                }

                @Override
                public void add(int n, CTImageData cTImageData) {
                    CTGroupImpl.this.insertNewImagedata(n).set((XmlObject)cTImageData);
                }

                @Override
                public CTImageData remove(int n) {
                    CTImageData cTImageData = CTGroupImpl.this.getImagedataArray(n);
                    CTGroupImpl.this.removeImagedata(n);
                    return cTImageData;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfImagedataArray();
                }
            }
            return new ImagedataList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTImageData[] getImagedataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(IMAGEDATA$16, arrayList);
            CTImageData[] cTImageDataArray = new CTImageData[arrayList.size()];
            arrayList.toArray(cTImageDataArray);
            return cTImageDataArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTImageData getImagedataArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTImageData cTImageData = null;
            cTImageData = (CTImageData)this.get_store().find_element_user(IMAGEDATA$16, n);
            if (cTImageData == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTImageData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfImagedataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IMAGEDATA$16);
        }
    }

    @Override
    public void setImagedataArray(CTImageData[] cTImageDataArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTImageDataArray, IMAGEDATA$16);
    }

    @Override
    public void setImagedataArray(int n, CTImageData cTImageData) {
        this.generatedSetterHelperImpl((XmlObject)cTImageData, IMAGEDATA$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTImageData insertNewImagedata(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTImageData cTImageData = null;
            cTImageData = (CTImageData)this.get_store().insert_element_user(IMAGEDATA$16, n);
            return cTImageData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTImageData addNewImagedata() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTImageData cTImageData = null;
            cTImageData = (CTImageData)this.get_store().add_element_user(IMAGEDATA$16);
            return cTImageData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeImagedata(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IMAGEDATA$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSkew> getSkewList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SkewList
            extends AbstractList<CTSkew> {
                SkewList() {
                }

                @Override
                public CTSkew get(int n) {
                    return CTGroupImpl.this.getSkewArray(n);
                }

                @Override
                public CTSkew set(int n, CTSkew cTSkew) {
                    CTSkew cTSkew2 = CTGroupImpl.this.getSkewArray(n);
                    CTGroupImpl.this.setSkewArray(n, cTSkew);
                    return cTSkew2;
                }

                @Override
                public void add(int n, CTSkew cTSkew) {
                    CTGroupImpl.this.insertNewSkew(n).set((XmlObject)cTSkew);
                }

                @Override
                public CTSkew remove(int n) {
                    CTSkew cTSkew = CTGroupImpl.this.getSkewArray(n);
                    CTGroupImpl.this.removeSkew(n);
                    return cTSkew;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfSkewArray();
                }
            }
            return new SkewList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSkew[] getSkewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SKEW$18, arrayList);
            CTSkew[] cTSkewArray = new CTSkew[arrayList.size()];
            arrayList.toArray(cTSkewArray);
            return cTSkewArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSkew getSkewArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSkew cTSkew = null;
            cTSkew = (CTSkew)this.get_store().find_element_user(SKEW$18, n);
            if (cTSkew == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSkew;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSkewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SKEW$18);
        }
    }

    @Override
    public void setSkewArray(CTSkew[] cTSkewArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSkewArray, SKEW$18);
    }

    @Override
    public void setSkewArray(int n, CTSkew cTSkew) {
        this.generatedSetterHelperImpl((XmlObject)cTSkew, SKEW$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSkew insertNewSkew(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSkew cTSkew = null;
            cTSkew = (CTSkew)this.get_store().insert_element_user(SKEW$18, n);
            return cTSkew;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSkew addNewSkew() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSkew cTSkew = null;
            cTSkew = (CTSkew)this.get_store().add_element_user(SKEW$18);
            return cTSkew;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSkew(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SKEW$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTExtrusion> getExtrusionList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ExtrusionList
            extends AbstractList<CTExtrusion> {
                ExtrusionList() {
                }

                @Override
                public CTExtrusion get(int n) {
                    return CTGroupImpl.this.getExtrusionArray(n);
                }

                @Override
                public CTExtrusion set(int n, CTExtrusion cTExtrusion) {
                    CTExtrusion cTExtrusion2 = CTGroupImpl.this.getExtrusionArray(n);
                    CTGroupImpl.this.setExtrusionArray(n, cTExtrusion);
                    return cTExtrusion2;
                }

                @Override
                public void add(int n, CTExtrusion cTExtrusion) {
                    CTGroupImpl.this.insertNewExtrusion(n).set((XmlObject)cTExtrusion);
                }

                @Override
                public CTExtrusion remove(int n) {
                    CTExtrusion cTExtrusion = CTGroupImpl.this.getExtrusionArray(n);
                    CTGroupImpl.this.removeExtrusion(n);
                    return cTExtrusion;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfExtrusionArray();
                }
            }
            return new ExtrusionList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTExtrusion[] getExtrusionArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EXTRUSION$20, arrayList);
            CTExtrusion[] cTExtrusionArray = new CTExtrusion[arrayList.size()];
            arrayList.toArray(cTExtrusionArray);
            return cTExtrusionArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtrusion getExtrusionArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtrusion cTExtrusion = null;
            cTExtrusion = (CTExtrusion)this.get_store().find_element_user(EXTRUSION$20, n);
            if (cTExtrusion == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTExtrusion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfExtrusionArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTRUSION$20);
        }
    }

    @Override
    public void setExtrusionArray(CTExtrusion[] cTExtrusionArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTExtrusionArray, EXTRUSION$20);
    }

    @Override
    public void setExtrusionArray(int n, CTExtrusion cTExtrusion) {
        this.generatedSetterHelperImpl((XmlObject)cTExtrusion, EXTRUSION$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtrusion insertNewExtrusion(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtrusion cTExtrusion = null;
            cTExtrusion = (CTExtrusion)this.get_store().insert_element_user(EXTRUSION$20, n);
            return cTExtrusion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtrusion addNewExtrusion() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtrusion cTExtrusion = null;
            cTExtrusion = (CTExtrusion)this.get_store().add_element_user(EXTRUSION$20);
            return cTExtrusion;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeExtrusion(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTRUSION$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCallout> getCalloutList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CalloutList
            extends AbstractList<CTCallout> {
                CalloutList() {
                }

                @Override
                public CTCallout get(int n) {
                    return CTGroupImpl.this.getCalloutArray(n);
                }

                @Override
                public CTCallout set(int n, CTCallout cTCallout) {
                    CTCallout cTCallout2 = CTGroupImpl.this.getCalloutArray(n);
                    CTGroupImpl.this.setCalloutArray(n, cTCallout);
                    return cTCallout2;
                }

                @Override
                public void add(int n, CTCallout cTCallout) {
                    CTGroupImpl.this.insertNewCallout(n).set((XmlObject)cTCallout);
                }

                @Override
                public CTCallout remove(int n) {
                    CTCallout cTCallout = CTGroupImpl.this.getCalloutArray(n);
                    CTGroupImpl.this.removeCallout(n);
                    return cTCallout;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfCalloutArray();
                }
            }
            return new CalloutList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCallout[] getCalloutArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CALLOUT$22, arrayList);
            CTCallout[] cTCalloutArray = new CTCallout[arrayList.size()];
            arrayList.toArray(cTCalloutArray);
            return cTCalloutArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCallout getCalloutArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCallout cTCallout = null;
            cTCallout = (CTCallout)this.get_store().find_element_user(CALLOUT$22, n);
            if (cTCallout == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCallout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCalloutArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CALLOUT$22);
        }
    }

    @Override
    public void setCalloutArray(CTCallout[] cTCalloutArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCalloutArray, CALLOUT$22);
    }

    @Override
    public void setCalloutArray(int n, CTCallout cTCallout) {
        this.generatedSetterHelperImpl((XmlObject)cTCallout, CALLOUT$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCallout insertNewCallout(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCallout cTCallout = null;
            cTCallout = (CTCallout)this.get_store().insert_element_user(CALLOUT$22, n);
            return cTCallout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCallout addNewCallout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCallout cTCallout = null;
            cTCallout = (CTCallout)this.get_store().add_element_user(CALLOUT$22);
            return cTCallout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCallout(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CALLOUT$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLock> getLockList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LockList
            extends AbstractList<CTLock> {
                LockList() {
                }

                @Override
                public CTLock get(int n) {
                    return CTGroupImpl.this.getLockArray(n);
                }

                @Override
                public CTLock set(int n, CTLock cTLock) {
                    CTLock cTLock2 = CTGroupImpl.this.getLockArray(n);
                    CTGroupImpl.this.setLockArray(n, cTLock);
                    return cTLock2;
                }

                @Override
                public void add(int n, CTLock cTLock) {
                    CTGroupImpl.this.insertNewLock(n).set(cTLock);
                }

                @Override
                public CTLock remove(int n) {
                    CTLock cTLock = CTGroupImpl.this.getLockArray(n);
                    CTGroupImpl.this.removeLock(n);
                    return cTLock;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfLockArray();
                }
            }
            return new LockList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLock[] getLockArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LOCK$24, arrayList);
            CTLock[] cTLockArray = new CTLock[arrayList.size()];
            arrayList.toArray(cTLockArray);
            return cTLockArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLock getLockArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLock cTLock = null;
            cTLock = (CTLock)((Object)this.get_store().find_element_user(LOCK$24, n));
            if (cTLock == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLockArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LOCK$24);
        }
    }

    @Override
    public void setLockArray(CTLock[] cTLockArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTLockArray, LOCK$24);
    }

    @Override
    public void setLockArray(int n, CTLock cTLock) {
        this.generatedSetterHelperImpl(cTLock, LOCK$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLock insertNewLock(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLock cTLock = null;
            cTLock = (CTLock)((Object)this.get_store().insert_element_user(LOCK$24, n));
            return cTLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLock addNewLock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLock cTLock = null;
            cTLock = (CTLock)((Object)this.get_store().add_element_user(LOCK$24));
            return cTLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLock(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LOCK$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTClipPath> getClippathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClippathList
            extends AbstractList<CTClipPath> {
                ClippathList() {
                }

                @Override
                public CTClipPath get(int n) {
                    return CTGroupImpl.this.getClippathArray(n);
                }

                @Override
                public CTClipPath set(int n, CTClipPath cTClipPath) {
                    CTClipPath cTClipPath2 = CTGroupImpl.this.getClippathArray(n);
                    CTGroupImpl.this.setClippathArray(n, cTClipPath);
                    return cTClipPath2;
                }

                @Override
                public void add(int n, CTClipPath cTClipPath) {
                    CTGroupImpl.this.insertNewClippath(n).set((XmlObject)cTClipPath);
                }

                @Override
                public CTClipPath remove(int n) {
                    CTClipPath cTClipPath = CTGroupImpl.this.getClippathArray(n);
                    CTGroupImpl.this.removeClippath(n);
                    return cTClipPath;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfClippathArray();
                }
            }
            return new ClippathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTClipPath[] getClippathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLIPPATH$26, arrayList);
            CTClipPath[] cTClipPathArray = new CTClipPath[arrayList.size()];
            arrayList.toArray(cTClipPathArray);
            return cTClipPathArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTClipPath getClippathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTClipPath cTClipPath = null;
            cTClipPath = (CTClipPath)this.get_store().find_element_user(CLIPPATH$26, n);
            if (cTClipPath == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTClipPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfClippathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLIPPATH$26);
        }
    }

    @Override
    public void setClippathArray(CTClipPath[] cTClipPathArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTClipPathArray, CLIPPATH$26);
    }

    @Override
    public void setClippathArray(int n, CTClipPath cTClipPath) {
        this.generatedSetterHelperImpl((XmlObject)cTClipPath, CLIPPATH$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTClipPath insertNewClippath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTClipPath cTClipPath = null;
            cTClipPath = (CTClipPath)this.get_store().insert_element_user(CLIPPATH$26, n);
            return cTClipPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTClipPath addNewClippath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTClipPath cTClipPath = null;
            cTClipPath = (CTClipPath)this.get_store().add_element_user(CLIPPATH$26);
            return cTClipPath;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClippath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLIPPATH$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSignatureLine> getSignaturelineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SignaturelineList
            extends AbstractList<CTSignatureLine> {
                SignaturelineList() {
                }

                @Override
                public CTSignatureLine get(int n) {
                    return CTGroupImpl.this.getSignaturelineArray(n);
                }

                @Override
                public CTSignatureLine set(int n, CTSignatureLine cTSignatureLine) {
                    CTSignatureLine cTSignatureLine2 = CTGroupImpl.this.getSignaturelineArray(n);
                    CTGroupImpl.this.setSignaturelineArray(n, cTSignatureLine);
                    return cTSignatureLine2;
                }

                @Override
                public void add(int n, CTSignatureLine cTSignatureLine) {
                    CTGroupImpl.this.insertNewSignatureline(n).set((XmlObject)cTSignatureLine);
                }

                @Override
                public CTSignatureLine remove(int n) {
                    CTSignatureLine cTSignatureLine = CTGroupImpl.this.getSignaturelineArray(n);
                    CTGroupImpl.this.removeSignatureline(n);
                    return cTSignatureLine;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfSignaturelineArray();
                }
            }
            return new SignaturelineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSignatureLine[] getSignaturelineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SIGNATURELINE$28, arrayList);
            CTSignatureLine[] cTSignatureLineArray = new CTSignatureLine[arrayList.size()];
            arrayList.toArray(cTSignatureLineArray);
            return cTSignatureLineArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureLine getSignaturelineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureLine cTSignatureLine = null;
            cTSignatureLine = (CTSignatureLine)this.get_store().find_element_user(SIGNATURELINE$28, n);
            if (cTSignatureLine == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSignatureLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSignaturelineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNATURELINE$28);
        }
    }

    @Override
    public void setSignaturelineArray(CTSignatureLine[] cTSignatureLineArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSignatureLineArray, SIGNATURELINE$28);
    }

    @Override
    public void setSignaturelineArray(int n, CTSignatureLine cTSignatureLine) {
        this.generatedSetterHelperImpl((XmlObject)cTSignatureLine, SIGNATURELINE$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureLine insertNewSignatureline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureLine cTSignatureLine = null;
            cTSignatureLine = (CTSignatureLine)this.get_store().insert_element_user(SIGNATURELINE$28, n);
            return cTSignatureLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureLine addNewSignatureline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureLine cTSignatureLine = null;
            cTSignatureLine = (CTSignatureLine)this.get_store().add_element_user(SIGNATURELINE$28);
            return cTSignatureLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSignatureline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNATURELINE$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTWrap> getWrapList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class WrapList
            extends AbstractList<CTWrap> {
                WrapList() {
                }

                @Override
                public CTWrap get(int n) {
                    return CTGroupImpl.this.getWrapArray(n);
                }

                @Override
                public CTWrap set(int n, CTWrap cTWrap) {
                    CTWrap cTWrap2 = CTGroupImpl.this.getWrapArray(n);
                    CTGroupImpl.this.setWrapArray(n, cTWrap);
                    return cTWrap2;
                }

                @Override
                public void add(int n, CTWrap cTWrap) {
                    CTGroupImpl.this.insertNewWrap(n).set((XmlObject)cTWrap);
                }

                @Override
                public CTWrap remove(int n) {
                    CTWrap cTWrap = CTGroupImpl.this.getWrapArray(n);
                    CTGroupImpl.this.removeWrap(n);
                    return cTWrap;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfWrapArray();
                }
            }
            return new WrapList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTWrap[] getWrapArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(WRAP$30, arrayList);
            CTWrap[] cTWrapArray = new CTWrap[arrayList.size()];
            arrayList.toArray(cTWrapArray);
            return cTWrapArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrap getWrapArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrap cTWrap = null;
            cTWrap = (CTWrap)this.get_store().find_element_user(WRAP$30, n);
            if (cTWrap == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTWrap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfWrapArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRAP$30);
        }
    }

    @Override
    public void setWrapArray(CTWrap[] cTWrapArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTWrapArray, WRAP$30);
    }

    @Override
    public void setWrapArray(int n, CTWrap cTWrap) {
        this.generatedSetterHelperImpl((XmlObject)cTWrap, WRAP$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrap insertNewWrap(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrap cTWrap = null;
            cTWrap = (CTWrap)this.get_store().insert_element_user(WRAP$30, n);
            return cTWrap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrap addNewWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrap cTWrap = null;
            cTWrap = (CTWrap)this.get_store().add_element_user(WRAP$30);
            return cTWrap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeWrap(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRAP$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAnchorLock> getAnchorlockList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AnchorlockList
            extends AbstractList<CTAnchorLock> {
                AnchorlockList() {
                }

                @Override
                public CTAnchorLock get(int n) {
                    return CTGroupImpl.this.getAnchorlockArray(n);
                }

                @Override
                public CTAnchorLock set(int n, CTAnchorLock cTAnchorLock) {
                    CTAnchorLock cTAnchorLock2 = CTGroupImpl.this.getAnchorlockArray(n);
                    CTGroupImpl.this.setAnchorlockArray(n, cTAnchorLock);
                    return cTAnchorLock2;
                }

                @Override
                public void add(int n, CTAnchorLock cTAnchorLock) {
                    CTGroupImpl.this.insertNewAnchorlock(n).set((XmlObject)cTAnchorLock);
                }

                @Override
                public CTAnchorLock remove(int n) {
                    CTAnchorLock cTAnchorLock = CTGroupImpl.this.getAnchorlockArray(n);
                    CTGroupImpl.this.removeAnchorlock(n);
                    return cTAnchorLock;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfAnchorlockArray();
                }
            }
            return new AnchorlockList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAnchorLock[] getAnchorlockArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ANCHORLOCK$32, arrayList);
            CTAnchorLock[] cTAnchorLockArray = new CTAnchorLock[arrayList.size()];
            arrayList.toArray(cTAnchorLockArray);
            return cTAnchorLockArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchorLock getAnchorlockArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchorLock cTAnchorLock = null;
            cTAnchorLock = (CTAnchorLock)this.get_store().find_element_user(ANCHORLOCK$32, n);
            if (cTAnchorLock == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAnchorLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAnchorlockArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANCHORLOCK$32);
        }
    }

    @Override
    public void setAnchorlockArray(CTAnchorLock[] cTAnchorLockArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAnchorLockArray, ANCHORLOCK$32);
    }

    @Override
    public void setAnchorlockArray(int n, CTAnchorLock cTAnchorLock) {
        this.generatedSetterHelperImpl((XmlObject)cTAnchorLock, ANCHORLOCK$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchorLock insertNewAnchorlock(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchorLock cTAnchorLock = null;
            cTAnchorLock = (CTAnchorLock)this.get_store().insert_element_user(ANCHORLOCK$32, n);
            return cTAnchorLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchorLock addNewAnchorlock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchorLock cTAnchorLock = null;
            cTAnchorLock = (CTAnchorLock)this.get_store().add_element_user(ANCHORLOCK$32);
            return cTAnchorLock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAnchorlock(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANCHORLOCK$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBorder> getBordertopList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BordertopList
            extends AbstractList<CTBorder> {
                BordertopList() {
                }

                @Override
                public CTBorder get(int n) {
                    return CTGroupImpl.this.getBordertopArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTGroupImpl.this.getBordertopArray(n);
                    CTGroupImpl.this.setBordertopArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTGroupImpl.this.insertNewBordertop(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTGroupImpl.this.getBordertopArray(n);
                    CTGroupImpl.this.removeBordertop(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfBordertopArray();
                }
            }
            return new BordertopList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBorder[] getBordertopArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BORDERTOP$34, arrayList);
            CTBorder[] cTBorderArray = new CTBorder[arrayList.size()];
            arrayList.toArray(cTBorderArray);
            return cTBorderArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBordertopArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().find_element_user(BORDERTOP$34, n);
            if (cTBorder == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBordertopArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERTOP$34);
        }
    }

    @Override
    public void setBordertopArray(CTBorder[] cTBorderArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBorderArray, BORDERTOP$34);
    }

    @Override
    public void setBordertopArray(int n, CTBorder cTBorder) {
        this.generatedSetterHelperImpl((XmlObject)cTBorder, BORDERTOP$34, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder insertNewBordertop(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().insert_element_user(BORDERTOP$34, n);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBordertop() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().add_element_user(BORDERTOP$34);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBordertop(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERTOP$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBorder> getBorderbottomList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BorderbottomList
            extends AbstractList<CTBorder> {
                BorderbottomList() {
                }

                @Override
                public CTBorder get(int n) {
                    return CTGroupImpl.this.getBorderbottomArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTGroupImpl.this.getBorderbottomArray(n);
                    CTGroupImpl.this.setBorderbottomArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTGroupImpl.this.insertNewBorderbottom(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTGroupImpl.this.getBorderbottomArray(n);
                    CTGroupImpl.this.removeBorderbottom(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfBorderbottomArray();
                }
            }
            return new BorderbottomList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBorder[] getBorderbottomArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BORDERBOTTOM$36, arrayList);
            CTBorder[] cTBorderArray = new CTBorder[arrayList.size()];
            arrayList.toArray(cTBorderArray);
            return cTBorderArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBorderbottomArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().find_element_user(BORDERBOTTOM$36, n);
            if (cTBorder == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBorderbottomArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERBOTTOM$36);
        }
    }

    @Override
    public void setBorderbottomArray(CTBorder[] cTBorderArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBorderArray, BORDERBOTTOM$36);
    }

    @Override
    public void setBorderbottomArray(int n, CTBorder cTBorder) {
        this.generatedSetterHelperImpl((XmlObject)cTBorder, BORDERBOTTOM$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder insertNewBorderbottom(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().insert_element_user(BORDERBOTTOM$36, n);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBorderbottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().add_element_user(BORDERBOTTOM$36);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBorderbottom(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERBOTTOM$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBorder> getBorderleftList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BorderleftList
            extends AbstractList<CTBorder> {
                BorderleftList() {
                }

                @Override
                public CTBorder get(int n) {
                    return CTGroupImpl.this.getBorderleftArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTGroupImpl.this.getBorderleftArray(n);
                    CTGroupImpl.this.setBorderleftArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTGroupImpl.this.insertNewBorderleft(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTGroupImpl.this.getBorderleftArray(n);
                    CTGroupImpl.this.removeBorderleft(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfBorderleftArray();
                }
            }
            return new BorderleftList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBorder[] getBorderleftArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BORDERLEFT$38, arrayList);
            CTBorder[] cTBorderArray = new CTBorder[arrayList.size()];
            arrayList.toArray(cTBorderArray);
            return cTBorderArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBorderleftArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().find_element_user(BORDERLEFT$38, n);
            if (cTBorder == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBorderleftArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERLEFT$38);
        }
    }

    @Override
    public void setBorderleftArray(CTBorder[] cTBorderArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBorderArray, BORDERLEFT$38);
    }

    @Override
    public void setBorderleftArray(int n, CTBorder cTBorder) {
        this.generatedSetterHelperImpl((XmlObject)cTBorder, BORDERLEFT$38, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder insertNewBorderleft(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().insert_element_user(BORDERLEFT$38, n);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBorderleft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().add_element_user(BORDERLEFT$38);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBorderleft(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERLEFT$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBorder> getBorderrightList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BorderrightList
            extends AbstractList<CTBorder> {
                BorderrightList() {
                }

                @Override
                public CTBorder get(int n) {
                    return CTGroupImpl.this.getBorderrightArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTGroupImpl.this.getBorderrightArray(n);
                    CTGroupImpl.this.setBorderrightArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTGroupImpl.this.insertNewBorderright(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTGroupImpl.this.getBorderrightArray(n);
                    CTGroupImpl.this.removeBorderright(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfBorderrightArray();
                }
            }
            return new BorderrightList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBorder[] getBorderrightArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BORDERRIGHT$40, arrayList);
            CTBorder[] cTBorderArray = new CTBorder[arrayList.size()];
            arrayList.toArray(cTBorderArray);
            return cTBorderArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBorderrightArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().find_element_user(BORDERRIGHT$40, n);
            if (cTBorder == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBorderrightArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERRIGHT$40);
        }
    }

    @Override
    public void setBorderrightArray(CTBorder[] cTBorderArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBorderArray, BORDERRIGHT$40);
    }

    @Override
    public void setBorderrightArray(int n, CTBorder cTBorder) {
        this.generatedSetterHelperImpl((XmlObject)cTBorder, BORDERRIGHT$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder insertNewBorderright(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().insert_element_user(BORDERRIGHT$40, n);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBorderright() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)this.get_store().add_element_user(BORDERRIGHT$40);
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBorderright(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERRIGHT$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTClientData> getClientDataList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClientDataList
            extends AbstractList<CTClientData> {
                ClientDataList() {
                }

                @Override
                public CTClientData get(int n) {
                    return CTGroupImpl.this.getClientDataArray(n);
                }

                @Override
                public CTClientData set(int n, CTClientData cTClientData) {
                    CTClientData cTClientData2 = CTGroupImpl.this.getClientDataArray(n);
                    CTGroupImpl.this.setClientDataArray(n, cTClientData);
                    return cTClientData2;
                }

                @Override
                public void add(int n, CTClientData cTClientData) {
                    CTGroupImpl.this.insertNewClientData(n).set(cTClientData);
                }

                @Override
                public CTClientData remove(int n) {
                    CTClientData cTClientData = CTGroupImpl.this.getClientDataArray(n);
                    CTGroupImpl.this.removeClientData(n);
                    return cTClientData;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfClientDataArray();
                }
            }
            return new ClientDataList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTClientData[] getClientDataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLIENTDATA$42, arrayList);
            CTClientData[] cTClientDataArray = new CTClientData[arrayList.size()];
            arrayList.toArray(cTClientDataArray);
            return cTClientDataArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTClientData getClientDataArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTClientData cTClientData = null;
            cTClientData = (CTClientData)((Object)this.get_store().find_element_user(CLIENTDATA$42, n));
            if (cTClientData == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTClientData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfClientDataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLIENTDATA$42);
        }
    }

    @Override
    public void setClientDataArray(CTClientData[] cTClientDataArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTClientDataArray, CLIENTDATA$42);
    }

    @Override
    public void setClientDataArray(int n, CTClientData cTClientData) {
        this.generatedSetterHelperImpl(cTClientData, CLIENTDATA$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTClientData insertNewClientData(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTClientData cTClientData = null;
            cTClientData = (CTClientData)((Object)this.get_store().insert_element_user(CLIENTDATA$42, n));
            return cTClientData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTClientData addNewClientData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTClientData cTClientData = null;
            cTClientData = (CTClientData)((Object)this.get_store().add_element_user(CLIENTDATA$42));
            return cTClientData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClientData(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLIENTDATA$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRel> getTextdataList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextdataList
            extends AbstractList<CTRel> {
                TextdataList() {
                }

                @Override
                public CTRel get(int n) {
                    return CTGroupImpl.this.getTextdataArray(n);
                }

                @Override
                public CTRel set(int n, CTRel cTRel) {
                    CTRel cTRel2 = CTGroupImpl.this.getTextdataArray(n);
                    CTGroupImpl.this.setTextdataArray(n, cTRel);
                    return cTRel2;
                }

                @Override
                public void add(int n, CTRel cTRel) {
                    CTGroupImpl.this.insertNewTextdata(n).set((XmlObject)cTRel);
                }

                @Override
                public CTRel remove(int n) {
                    CTRel cTRel = CTGroupImpl.this.getTextdataArray(n);
                    CTGroupImpl.this.removeTextdata(n);
                    return cTRel;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfTextdataArray();
                }
            }
            return new TextdataList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRel[] getTextdataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTDATA$44, arrayList);
            CTRel[] cTRelArray = new CTRel[arrayList.size()];
            arrayList.toArray(cTRelArray);
            return cTRelArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel getTextdataArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)this.get_store().find_element_user(TEXTDATA$44, n);
            if (cTRel == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextdataArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTDATA$44);
        }
    }

    @Override
    public void setTextdataArray(CTRel[] cTRelArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTRelArray, TEXTDATA$44);
    }

    @Override
    public void setTextdataArray(int n, CTRel cTRel) {
        this.generatedSetterHelperImpl((XmlObject)cTRel, TEXTDATA$44, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel insertNewTextdata(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)this.get_store().insert_element_user(TEXTDATA$44, n);
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel addNewTextdata() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)this.get_store().add_element_user(TEXTDATA$44);
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTextdata(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTDATA$44, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGroup> getGroupList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GroupList
            extends AbstractList<CTGroup> {
                GroupList() {
                }

                @Override
                public CTGroup get(int n) {
                    return CTGroupImpl.this.getGroupArray(n);
                }

                @Override
                public CTGroup set(int n, CTGroup cTGroup) {
                    CTGroup cTGroup2 = CTGroupImpl.this.getGroupArray(n);
                    CTGroupImpl.this.setGroupArray(n, cTGroup);
                    return cTGroup2;
                }

                @Override
                public void add(int n, CTGroup cTGroup) {
                    CTGroupImpl.this.insertNewGroup(n).set(cTGroup);
                }

                @Override
                public CTGroup remove(int n) {
                    CTGroup cTGroup = CTGroupImpl.this.getGroupArray(n);
                    CTGroupImpl.this.removeGroup(n);
                    return cTGroup;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfGroupArray();
                }
            }
            return new GroupList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGroup[] getGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GROUP$46, arrayList);
            CTGroup[] cTGroupArray = new CTGroup[arrayList.size()];
            arrayList.toArray(cTGroupArray);
            return cTGroupArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroup getGroupArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroup cTGroup = null;
            cTGroup = (CTGroup)((Object)this.get_store().find_element_user(GROUP$46, n));
            if (cTGroup == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGroup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUP$46);
        }
    }

    @Override
    public void setGroupArray(CTGroup[] cTGroupArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTGroupArray, GROUP$46);
    }

    @Override
    public void setGroupArray(int n, CTGroup cTGroup) {
        this.generatedSetterHelperImpl(cTGroup, GROUP$46, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroup insertNewGroup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroup cTGroup = null;
            cTGroup = (CTGroup)((Object)this.get_store().insert_element_user(GROUP$46, n));
            return cTGroup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroup addNewGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroup cTGroup = null;
            cTGroup = (CTGroup)((Object)this.get_store().add_element_user(GROUP$46));
            return cTGroup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGroup(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUP$46, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTShape> getShapeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShapeList
            extends AbstractList<CTShape> {
                ShapeList() {
                }

                @Override
                public CTShape get(int n) {
                    return CTGroupImpl.this.getShapeArray(n);
                }

                @Override
                public CTShape set(int n, CTShape cTShape) {
                    CTShape cTShape2 = CTGroupImpl.this.getShapeArray(n);
                    CTGroupImpl.this.setShapeArray(n, cTShape);
                    return cTShape2;
                }

                @Override
                public void add(int n, CTShape cTShape) {
                    CTGroupImpl.this.insertNewShape(n).set(cTShape);
                }

                @Override
                public CTShape remove(int n) {
                    CTShape cTShape = CTGroupImpl.this.getShapeArray(n);
                    CTGroupImpl.this.removeShape(n);
                    return cTShape;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfShapeArray();
                }
            }
            return new ShapeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTShape[] getShapeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHAPE$48, arrayList);
            CTShape[] cTShapeArray = new CTShape[arrayList.size()];
            arrayList.toArray(cTShapeArray);
            return cTShapeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape getShapeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().find_element_user(SHAPE$48, n));
            if (cTShape == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfShapeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHAPE$48);
        }
    }

    @Override
    public void setShapeArray(CTShape[] cTShapeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTShapeArray, SHAPE$48);
    }

    @Override
    public void setShapeArray(int n, CTShape cTShape) {
        this.generatedSetterHelperImpl(cTShape, SHAPE$48, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape insertNewShape(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().insert_element_user(SHAPE$48, n));
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape addNewShape() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().add_element_user(SHAPE$48));
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeShape(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHAPE$48, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTShapetype> getShapetypeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShapetypeList
            extends AbstractList<CTShapetype> {
                ShapetypeList() {
                }

                @Override
                public CTShapetype get(int n) {
                    return CTGroupImpl.this.getShapetypeArray(n);
                }

                @Override
                public CTShapetype set(int n, CTShapetype cTShapetype) {
                    CTShapetype cTShapetype2 = CTGroupImpl.this.getShapetypeArray(n);
                    CTGroupImpl.this.setShapetypeArray(n, cTShapetype);
                    return cTShapetype2;
                }

                @Override
                public void add(int n, CTShapetype cTShapetype) {
                    CTGroupImpl.this.insertNewShapetype(n).set(cTShapetype);
                }

                @Override
                public CTShapetype remove(int n) {
                    CTShapetype cTShapetype = CTGroupImpl.this.getShapetypeArray(n);
                    CTGroupImpl.this.removeShapetype(n);
                    return cTShapetype;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfShapetypeArray();
                }
            }
            return new ShapetypeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTShapetype[] getShapetypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHAPETYPE$50, arrayList);
            CTShapetype[] cTShapetypeArray = new CTShapetype[arrayList.size()];
            arrayList.toArray(cTShapetypeArray);
            return cTShapetypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapetype getShapetypeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapetype cTShapetype = null;
            cTShapetype = (CTShapetype)((Object)this.get_store().find_element_user(SHAPETYPE$50, n));
            if (cTShapetype == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTShapetype;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfShapetypeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHAPETYPE$50);
        }
    }

    @Override
    public void setShapetypeArray(CTShapetype[] cTShapetypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTShapetypeArray, SHAPETYPE$50);
    }

    @Override
    public void setShapetypeArray(int n, CTShapetype cTShapetype) {
        this.generatedSetterHelperImpl(cTShapetype, SHAPETYPE$50, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapetype insertNewShapetype(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapetype cTShapetype = null;
            cTShapetype = (CTShapetype)((Object)this.get_store().insert_element_user(SHAPETYPE$50, n));
            return cTShapetype;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapetype addNewShapetype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapetype cTShapetype = null;
            cTShapetype = (CTShapetype)((Object)this.get_store().add_element_user(SHAPETYPE$50));
            return cTShapetype;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeShapetype(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHAPETYPE$50, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTArc> getArcList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ArcList
            extends AbstractList<CTArc> {
                ArcList() {
                }

                @Override
                public CTArc get(int n) {
                    return CTGroupImpl.this.getArcArray(n);
                }

                @Override
                public CTArc set(int n, CTArc cTArc) {
                    CTArc cTArc2 = CTGroupImpl.this.getArcArray(n);
                    CTGroupImpl.this.setArcArray(n, cTArc);
                    return cTArc2;
                }

                @Override
                public void add(int n, CTArc cTArc) {
                    CTGroupImpl.this.insertNewArc(n).set((XmlObject)cTArc);
                }

                @Override
                public CTArc remove(int n) {
                    CTArc cTArc = CTGroupImpl.this.getArcArray(n);
                    CTGroupImpl.this.removeArc(n);
                    return cTArc;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfArcArray();
                }
            }
            return new ArcList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTArc[] getArcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ARC$52, arrayList);
            CTArc[] cTArcArray = new CTArc[arrayList.size()];
            arrayList.toArray(cTArcArray);
            return cTArcArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArc getArcArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArc cTArc = null;
            cTArc = (CTArc)this.get_store().find_element_user(ARC$52, n);
            if (cTArc == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTArc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfArcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ARC$52);
        }
    }

    @Override
    public void setArcArray(CTArc[] cTArcArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTArcArray, ARC$52);
    }

    @Override
    public void setArcArray(int n, CTArc cTArc) {
        this.generatedSetterHelperImpl((XmlObject)cTArc, ARC$52, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArc insertNewArc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArc cTArc = null;
            cTArc = (CTArc)this.get_store().insert_element_user(ARC$52, n);
            return cTArc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTArc addNewArc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTArc cTArc = null;
            cTArc = (CTArc)this.get_store().add_element_user(ARC$52);
            return cTArc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeArc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ARC$52, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCurve> getCurveList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CurveList
            extends AbstractList<CTCurve> {
                CurveList() {
                }

                @Override
                public CTCurve get(int n) {
                    return CTGroupImpl.this.getCurveArray(n);
                }

                @Override
                public CTCurve set(int n, CTCurve cTCurve) {
                    CTCurve cTCurve2 = CTGroupImpl.this.getCurveArray(n);
                    CTGroupImpl.this.setCurveArray(n, cTCurve);
                    return cTCurve2;
                }

                @Override
                public void add(int n, CTCurve cTCurve) {
                    CTGroupImpl.this.insertNewCurve(n).set((XmlObject)cTCurve);
                }

                @Override
                public CTCurve remove(int n) {
                    CTCurve cTCurve = CTGroupImpl.this.getCurveArray(n);
                    CTGroupImpl.this.removeCurve(n);
                    return cTCurve;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfCurveArray();
                }
            }
            return new CurveList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCurve[] getCurveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CURVE$54, arrayList);
            CTCurve[] cTCurveArray = new CTCurve[arrayList.size()];
            arrayList.toArray(cTCurveArray);
            return cTCurveArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCurve getCurveArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCurve cTCurve = null;
            cTCurve = (CTCurve)this.get_store().find_element_user(CURVE$54, n);
            if (cTCurve == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCurve;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCurveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CURVE$54);
        }
    }

    @Override
    public void setCurveArray(CTCurve[] cTCurveArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTCurveArray, CURVE$54);
    }

    @Override
    public void setCurveArray(int n, CTCurve cTCurve) {
        this.generatedSetterHelperImpl((XmlObject)cTCurve, CURVE$54, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCurve insertNewCurve(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCurve cTCurve = null;
            cTCurve = (CTCurve)this.get_store().insert_element_user(CURVE$54, n);
            return cTCurve;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCurve addNewCurve() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCurve cTCurve = null;
            cTCurve = (CTCurve)this.get_store().add_element_user(CURVE$54);
            return cTCurve;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCurve(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CURVE$54, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTImage> getImageList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ImageList
            extends AbstractList<CTImage> {
                ImageList() {
                }

                @Override
                public CTImage get(int n) {
                    return CTGroupImpl.this.getImageArray(n);
                }

                @Override
                public CTImage set(int n, CTImage cTImage) {
                    CTImage cTImage2 = CTGroupImpl.this.getImageArray(n);
                    CTGroupImpl.this.setImageArray(n, cTImage);
                    return cTImage2;
                }

                @Override
                public void add(int n, CTImage cTImage) {
                    CTGroupImpl.this.insertNewImage(n).set((XmlObject)cTImage);
                }

                @Override
                public CTImage remove(int n) {
                    CTImage cTImage = CTGroupImpl.this.getImageArray(n);
                    CTGroupImpl.this.removeImage(n);
                    return cTImage;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfImageArray();
                }
            }
            return new ImageList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTImage[] getImageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(IMAGE$56, arrayList);
            CTImage[] cTImageArray = new CTImage[arrayList.size()];
            arrayList.toArray(cTImageArray);
            return cTImageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTImage getImageArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTImage cTImage = null;
            cTImage = (CTImage)this.get_store().find_element_user(IMAGE$56, n);
            if (cTImage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTImage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfImageArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IMAGE$56);
        }
    }

    @Override
    public void setImageArray(CTImage[] cTImageArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTImageArray, IMAGE$56);
    }

    @Override
    public void setImageArray(int n, CTImage cTImage) {
        this.generatedSetterHelperImpl((XmlObject)cTImage, IMAGE$56, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTImage insertNewImage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTImage cTImage = null;
            cTImage = (CTImage)this.get_store().insert_element_user(IMAGE$56, n);
            return cTImage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTImage addNewImage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTImage cTImage = null;
            cTImage = (CTImage)this.get_store().add_element_user(IMAGE$56);
            return cTImage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeImage(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IMAGE$56, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLine> getLineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LineList
            extends AbstractList<CTLine> {
                LineList() {
                }

                @Override
                public CTLine get(int n) {
                    return CTGroupImpl.this.getLineArray(n);
                }

                @Override
                public CTLine set(int n, CTLine cTLine) {
                    CTLine cTLine2 = CTGroupImpl.this.getLineArray(n);
                    CTGroupImpl.this.setLineArray(n, cTLine);
                    return cTLine2;
                }

                @Override
                public void add(int n, CTLine cTLine) {
                    CTGroupImpl.this.insertNewLine(n).set((XmlObject)cTLine);
                }

                @Override
                public CTLine remove(int n) {
                    CTLine cTLine = CTGroupImpl.this.getLineArray(n);
                    CTGroupImpl.this.removeLine(n);
                    return cTLine;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfLineArray();
                }
            }
            return new LineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTLine[] getLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LINE$58, arrayList);
            CTLine[] cTLineArray = new CTLine[arrayList.size()];
            arrayList.toArray(cTLineArray);
            return cTLineArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLine getLineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLine cTLine = null;
            cTLine = (CTLine)this.get_store().find_element_user(LINE$58, n);
            if (cTLine == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LINE$58);
        }
    }

    @Override
    public void setLineArray(CTLine[] cTLineArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTLineArray, LINE$58);
    }

    @Override
    public void setLineArray(int n, CTLine cTLine) {
        this.generatedSetterHelperImpl((XmlObject)cTLine, LINE$58, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLine insertNewLine(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLine cTLine = null;
            cTLine = (CTLine)this.get_store().insert_element_user(LINE$58, n);
            return cTLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLine addNewLine() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLine cTLine = null;
            cTLine = (CTLine)this.get_store().add_element_user(LINE$58);
            return cTLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLine(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LINE$58, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOval> getOvalList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OvalList
            extends AbstractList<CTOval> {
                OvalList() {
                }

                @Override
                public CTOval get(int n) {
                    return CTGroupImpl.this.getOvalArray(n);
                }

                @Override
                public CTOval set(int n, CTOval cTOval) {
                    CTOval cTOval2 = CTGroupImpl.this.getOvalArray(n);
                    CTGroupImpl.this.setOvalArray(n, cTOval);
                    return cTOval2;
                }

                @Override
                public void add(int n, CTOval cTOval) {
                    CTGroupImpl.this.insertNewOval(n).set((XmlObject)cTOval);
                }

                @Override
                public CTOval remove(int n) {
                    CTOval cTOval = CTGroupImpl.this.getOvalArray(n);
                    CTGroupImpl.this.removeOval(n);
                    return cTOval;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfOvalArray();
                }
            }
            return new OvalList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOval[] getOvalArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OVAL$60, arrayList);
            CTOval[] cTOvalArray = new CTOval[arrayList.size()];
            arrayList.toArray(cTOvalArray);
            return cTOvalArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOval getOvalArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOval cTOval = null;
            cTOval = (CTOval)this.get_store().find_element_user(OVAL$60, n);
            if (cTOval == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOval;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOvalArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OVAL$60);
        }
    }

    @Override
    public void setOvalArray(CTOval[] cTOvalArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTOvalArray, OVAL$60);
    }

    @Override
    public void setOvalArray(int n, CTOval cTOval) {
        this.generatedSetterHelperImpl((XmlObject)cTOval, OVAL$60, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOval insertNewOval(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOval cTOval = null;
            cTOval = (CTOval)this.get_store().insert_element_user(OVAL$60, n);
            return cTOval;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOval addNewOval() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOval cTOval = null;
            cTOval = (CTOval)this.get_store().add_element_user(OVAL$60);
            return cTOval;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOval(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OVAL$60, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPolyLine> getPolylineList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PolylineList
            extends AbstractList<CTPolyLine> {
                PolylineList() {
                }

                @Override
                public CTPolyLine get(int n) {
                    return CTGroupImpl.this.getPolylineArray(n);
                }

                @Override
                public CTPolyLine set(int n, CTPolyLine cTPolyLine) {
                    CTPolyLine cTPolyLine2 = CTGroupImpl.this.getPolylineArray(n);
                    CTGroupImpl.this.setPolylineArray(n, cTPolyLine);
                    return cTPolyLine2;
                }

                @Override
                public void add(int n, CTPolyLine cTPolyLine) {
                    CTGroupImpl.this.insertNewPolyline(n).set((XmlObject)cTPolyLine);
                }

                @Override
                public CTPolyLine remove(int n) {
                    CTPolyLine cTPolyLine = CTGroupImpl.this.getPolylineArray(n);
                    CTGroupImpl.this.removePolyline(n);
                    return cTPolyLine;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfPolylineArray();
                }
            }
            return new PolylineList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPolyLine[] getPolylineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(POLYLINE$62, arrayList);
            CTPolyLine[] cTPolyLineArray = new CTPolyLine[arrayList.size()];
            arrayList.toArray(cTPolyLineArray);
            return cTPolyLineArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPolyLine getPolylineArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPolyLine cTPolyLine = null;
            cTPolyLine = (CTPolyLine)this.get_store().find_element_user(POLYLINE$62, n);
            if (cTPolyLine == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPolyLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPolylineArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(POLYLINE$62);
        }
    }

    @Override
    public void setPolylineArray(CTPolyLine[] cTPolyLineArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPolyLineArray, POLYLINE$62);
    }

    @Override
    public void setPolylineArray(int n, CTPolyLine cTPolyLine) {
        this.generatedSetterHelperImpl((XmlObject)cTPolyLine, POLYLINE$62, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPolyLine insertNewPolyline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPolyLine cTPolyLine = null;
            cTPolyLine = (CTPolyLine)this.get_store().insert_element_user(POLYLINE$62, n);
            return cTPolyLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPolyLine addNewPolyline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPolyLine cTPolyLine = null;
            cTPolyLine = (CTPolyLine)this.get_store().add_element_user(POLYLINE$62);
            return cTPolyLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePolyline(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(POLYLINE$62, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRect> getRectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RectList
            extends AbstractList<CTRect> {
                RectList() {
                }

                @Override
                public CTRect get(int n) {
                    return CTGroupImpl.this.getRectArray(n);
                }

                @Override
                public CTRect set(int n, CTRect cTRect) {
                    CTRect cTRect2 = CTGroupImpl.this.getRectArray(n);
                    CTGroupImpl.this.setRectArray(n, cTRect);
                    return cTRect2;
                }

                @Override
                public void add(int n, CTRect cTRect) {
                    CTGroupImpl.this.insertNewRect(n).set(cTRect);
                }

                @Override
                public CTRect remove(int n) {
                    CTRect cTRect = CTGroupImpl.this.getRectArray(n);
                    CTGroupImpl.this.removeRect(n);
                    return cTRect;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfRectArray();
                }
            }
            return new RectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRect[] getRectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RECT$64, arrayList);
            CTRect[] cTRectArray = new CTRect[arrayList.size()];
            arrayList.toArray(cTRectArray);
            return cTRectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRect getRectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRect cTRect = null;
            cTRect = (CTRect)((Object)this.get_store().find_element_user(RECT$64, n));
            if (cTRect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RECT$64);
        }
    }

    @Override
    public void setRectArray(CTRect[] cTRectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRectArray, RECT$64);
    }

    @Override
    public void setRectArray(int n, CTRect cTRect) {
        this.generatedSetterHelperImpl(cTRect, RECT$64, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRect insertNewRect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRect cTRect = null;
            cTRect = (CTRect)((Object)this.get_store().insert_element_user(RECT$64, n));
            return cTRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRect addNewRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRect cTRect = null;
            cTRect = (CTRect)((Object)this.get_store().add_element_user(RECT$64));
            return cTRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RECT$64, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRoundRect> getRoundrectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RoundrectList
            extends AbstractList<CTRoundRect> {
                RoundrectList() {
                }

                @Override
                public CTRoundRect get(int n) {
                    return CTGroupImpl.this.getRoundrectArray(n);
                }

                @Override
                public CTRoundRect set(int n, CTRoundRect cTRoundRect) {
                    CTRoundRect cTRoundRect2 = CTGroupImpl.this.getRoundrectArray(n);
                    CTGroupImpl.this.setRoundrectArray(n, cTRoundRect);
                    return cTRoundRect2;
                }

                @Override
                public void add(int n, CTRoundRect cTRoundRect) {
                    CTGroupImpl.this.insertNewRoundrect(n).set((XmlObject)cTRoundRect);
                }

                @Override
                public CTRoundRect remove(int n) {
                    CTRoundRect cTRoundRect = CTGroupImpl.this.getRoundrectArray(n);
                    CTGroupImpl.this.removeRoundrect(n);
                    return cTRoundRect;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfRoundrectArray();
                }
            }
            return new RoundrectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRoundRect[] getRoundrectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROUNDRECT$66, arrayList);
            CTRoundRect[] cTRoundRectArray = new CTRoundRect[arrayList.size()];
            arrayList.toArray(cTRoundRectArray);
            return cTRoundRectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRoundRect getRoundrectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRoundRect cTRoundRect = null;
            cTRoundRect = (CTRoundRect)this.get_store().find_element_user(ROUNDRECT$66, n);
            if (cTRoundRect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRoundRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRoundrectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROUNDRECT$66);
        }
    }

    @Override
    public void setRoundrectArray(CTRoundRect[] cTRoundRectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTRoundRectArray, ROUNDRECT$66);
    }

    @Override
    public void setRoundrectArray(int n, CTRoundRect cTRoundRect) {
        this.generatedSetterHelperImpl((XmlObject)cTRoundRect, ROUNDRECT$66, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRoundRect insertNewRoundrect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRoundRect cTRoundRect = null;
            cTRoundRect = (CTRoundRect)this.get_store().insert_element_user(ROUNDRECT$66, n);
            return cTRoundRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRoundRect addNewRoundrect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRoundRect cTRoundRect = null;
            cTRoundRect = (CTRoundRect)this.get_store().add_element_user(ROUNDRECT$66);
            return cTRoundRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRoundrect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROUNDRECT$66, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDiagram> getDiagramList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DiagramList
            extends AbstractList<CTDiagram> {
                DiagramList() {
                }

                @Override
                public CTDiagram get(int n) {
                    return CTGroupImpl.this.getDiagramArray(n);
                }

                @Override
                public CTDiagram set(int n, CTDiagram cTDiagram) {
                    CTDiagram cTDiagram2 = CTGroupImpl.this.getDiagramArray(n);
                    CTGroupImpl.this.setDiagramArray(n, cTDiagram);
                    return cTDiagram2;
                }

                @Override
                public void add(int n, CTDiagram cTDiagram) {
                    CTGroupImpl.this.insertNewDiagram(n).set((XmlObject)cTDiagram);
                }

                @Override
                public CTDiagram remove(int n) {
                    CTDiagram cTDiagram = CTGroupImpl.this.getDiagramArray(n);
                    CTGroupImpl.this.removeDiagram(n);
                    return cTDiagram;
                }

                @Override
                public int size() {
                    return CTGroupImpl.this.sizeOfDiagramArray();
                }
            }
            return new DiagramList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDiagram[] getDiagramArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DIAGRAM$68, arrayList);
            CTDiagram[] cTDiagramArray = new CTDiagram[arrayList.size()];
            arrayList.toArray(cTDiagramArray);
            return cTDiagramArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDiagram getDiagramArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDiagram cTDiagram = null;
            cTDiagram = (CTDiagram)this.get_store().find_element_user(DIAGRAM$68, n);
            if (cTDiagram == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDiagram;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDiagramArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIAGRAM$68);
        }
    }

    @Override
    public void setDiagramArray(CTDiagram[] cTDiagramArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTDiagramArray, DIAGRAM$68);
    }

    @Override
    public void setDiagramArray(int n, CTDiagram cTDiagram) {
        this.generatedSetterHelperImpl((XmlObject)cTDiagram, DIAGRAM$68, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDiagram insertNewDiagram(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDiagram cTDiagram = null;
            cTDiagram = (CTDiagram)this.get_store().insert_element_user(DIAGRAM$68, n);
            return cTDiagram;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDiagram addNewDiagram() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDiagram cTDiagram = null;
            cTDiagram = (CTDiagram)this.get_store().add_element_user(DIAGRAM$68);
            return cTDiagram;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDiagram(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIAGRAM$68, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$70));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ID$70));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$70) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$70));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$70));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ID$70));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ID$70));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$70);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLE$72));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(STYLE$72));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STYLE$72) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStyle(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLE$72));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STYLE$72));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStyle(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(STYLE$72));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(STYLE$72));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STYLE$72);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HREF$74));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(HREF$74));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HREF$74) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHref(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HREF$74));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HREF$74));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHref(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(HREF$74));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(HREF$74));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HREF$74);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGET$76));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TARGET$76));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TARGET$76) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTarget(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGET$76));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TARGET$76));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTarget(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TARGET$76));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TARGET$76));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TARGET$76);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getClass1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLASS1$78));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetClass1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(CLASS1$78));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClass1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CLASS1$78) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClass1(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLASS1$78));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CLASS1$78));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetClass1(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(CLASS1$78));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(CLASS1$78));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClass1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CLASS1$78);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TITLE$80));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TITLE$80));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TITLE$80) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTitle(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TITLE$80));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TITLE$80));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTitle(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TITLE$80));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TITLE$80));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TITLE$80);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAlt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALT$82));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetAlt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ALT$82));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALT$82) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlt(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALT$82));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALT$82));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlt(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ALT$82));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ALT$82));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALT$82);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCoordsize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDSIZE$84));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetCoordsize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(COORDSIZE$84));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCoordsize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COORDSIZE$84) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCoordsize(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDSIZE$84));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COORDSIZE$84));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCoordsize(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(COORDSIZE$84));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(COORDSIZE$84));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCoordsize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COORDSIZE$84);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getCoordorigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDORIGIN$86));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetCoordorigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(COORDORIGIN$86));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCoordorigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COORDORIGIN$86) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCoordorigin(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDORIGIN$86));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COORDORIGIN$86));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCoordorigin(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(COORDORIGIN$86));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(COORDORIGIN$86));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCoordorigin() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COORDORIGIN$86);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getWrapcoords() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAPCOORDS$88));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetWrapcoords() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(WRAPCOORDS$88));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapcoords() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(WRAPCOORDS$88) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWrapcoords(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAPCOORDS$88));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(WRAPCOORDS$88));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWrapcoords(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(WRAPCOORDS$88));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(WRAPCOORDS$88));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapcoords() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(WRAPCOORDS$88);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getPrint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRINT$90));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetPrint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(PRINT$90));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PRINT$90) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPrint(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRINT$90));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PRINT$90));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPrint(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(PRINT$90));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(PRINT$90));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PRINT$90);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSpid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPID$92));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetSpid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(SPID$92));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SPID$92) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSpid(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPID$92));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPID$92));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSpid(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(SPID$92));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(SPID$92));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SPID$92);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getOned() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ONED$94));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetOned() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ONED$94);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOned() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ONED$94) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOned(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ONED$94));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ONED$94));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOned(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ONED$94);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(ONED$94);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOned() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ONED$94);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getRegroupid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REGROUPID$96));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetRegroupid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(REGROUPID$96));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRegroupid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REGROUPID$96) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRegroupid(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REGROUPID$96));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REGROUPID$96));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRegroupid(XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(REGROUPID$96));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(REGROUPID$96));
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRegroupid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REGROUPID$96);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getDoubleclicknotify() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$98));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetDoubleclicknotify() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$98);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDoubleclicknotify() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$98) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDoubleclicknotify(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$98));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DOUBLECLICKNOTIFY$98));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDoubleclicknotify(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$98);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(DOUBLECLICKNOTIFY$98);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDoubleclicknotify() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DOUBLECLICKNOTIFY$98);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getButton() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BUTTON$100));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetButton() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BUTTON$100);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetButton() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BUTTON$100) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setButton(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BUTTON$100));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BUTTON$100));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetButton(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BUTTON$100);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(BUTTON$100);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetButton() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BUTTON$100);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getUserhidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERHIDDEN$102));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetUserhidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERHIDDEN$102);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUserhidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(USERHIDDEN$102) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUserhidden(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERHIDDEN$102));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(USERHIDDEN$102));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUserhidden(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERHIDDEN$102);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(USERHIDDEN$102);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUserhidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(USERHIDDEN$102);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getBullet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BULLET$104));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetBullet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BULLET$104);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBullet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BULLET$104) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBullet(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BULLET$104));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BULLET$104));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBullet(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BULLET$104);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(BULLET$104);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBullet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BULLET$104);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getHr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HR$106));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetHr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HR$106);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HR$106) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHr(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HR$106));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HR$106));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHr(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HR$106);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(HR$106);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HR$106);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getHrstd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRSTD$108));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetHrstd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRSTD$108);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHrstd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HRSTD$108) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHrstd(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRSTD$108));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRSTD$108));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHrstd(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRSTD$108);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(HRSTD$108);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHrstd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HRSTD$108);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getHrnoshade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRNOSHADE$110));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetHrnoshade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRNOSHADE$110);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHrnoshade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HRNOSHADE$110) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHrnoshade(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRNOSHADE$110));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRNOSHADE$110));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHrnoshade(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRNOSHADE$110);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(HRNOSHADE$110);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHrnoshade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HRNOSHADE$110);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float getHrpct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRPCT$112));
            if (simpleValue == null) {
                return 0.0f;
            }
            return simpleValue.getFloatValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlFloat xgetHrpct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat = null;
            xmlFloat = (XmlFloat)((Object)this.get_store().find_attribute_user(HRPCT$112));
            return xmlFloat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHrpct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HRPCT$112) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHrpct(float f) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRPCT$112));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRPCT$112));
            }
            simpleValue.setFloatValue(f);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHrpct(XmlFloat xmlFloat) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat2 = null;
            xmlFloat2 = (XmlFloat)((Object)this.get_store().find_attribute_user(HRPCT$112));
            if (xmlFloat2 == null) {
                xmlFloat2 = (XmlFloat)((Object)this.get_store().add_attribute_user(HRPCT$112));
            }
            xmlFloat2.set(xmlFloat);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHrpct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HRPCT$112);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHrAlign.Enum getHralign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRALIGN$114));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(HRALIGN$114));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STHrAlign.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHrAlign xgetHralign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHrAlign sTHrAlign = null;
            sTHrAlign = (STHrAlign)this.get_store().find_attribute_user(HRALIGN$114);
            if (sTHrAlign == null) {
                sTHrAlign = (STHrAlign)this.get_default_attribute_value(HRALIGN$114);
            }
            return sTHrAlign;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHralign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HRALIGN$114) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHralign(STHrAlign.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRALIGN$114));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRALIGN$114));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHralign(STHrAlign sTHrAlign) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHrAlign sTHrAlign2 = null;
            sTHrAlign2 = (STHrAlign)this.get_store().find_attribute_user(HRALIGN$114);
            if (sTHrAlign2 == null) {
                sTHrAlign2 = (STHrAlign)this.get_store().add_attribute_user(HRALIGN$114);
            }
            sTHrAlign2.set((XmlObject)sTHrAlign);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHralign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HRALIGN$114);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getAllowincell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWINCELL$116));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetAllowincell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWINCELL$116);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAllowincell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALLOWINCELL$116) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAllowincell(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWINCELL$116));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALLOWINCELL$116));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAllowincell(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWINCELL$116);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(ALLOWINCELL$116);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAllowincell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALLOWINCELL$116);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getAllowoverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$118));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetAllowoverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWOVERLAP$118);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAllowoverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALLOWOVERLAP$118) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAllowoverlap(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$118));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALLOWOVERLAP$118));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAllowoverlap(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWOVERLAP$118);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(ALLOWOVERLAP$118);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAllowoverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALLOWOVERLAP$118);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getUserdrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERDRAWN$120));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public com.microsoft.schemas.office.office.STTrueFalse xgetUserdrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERDRAWN$120);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUserdrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(USERDRAWN$120) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUserdrawn(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERDRAWN$120));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(USERDRAWN$120));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUserdrawn(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERDRAWN$120);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(USERDRAWN$120);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUserdrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(USERDRAWN$120);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getBordertopcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$122));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetBordertopcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$122));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBordertopcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BORDERTOPCOLOR$122) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBordertopcolor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$122));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERTOPCOLOR$122));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBordertopcolor(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$122));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERTOPCOLOR$122));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBordertopcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BORDERTOPCOLOR$122);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getBorderleftcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$124));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetBorderleftcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$124));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBorderleftcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BORDERLEFTCOLOR$124) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBorderleftcolor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$124));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERLEFTCOLOR$124));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBorderleftcolor(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$124));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERLEFTCOLOR$124));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBorderleftcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BORDERLEFTCOLOR$124);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getBorderbottomcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$126));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetBorderbottomcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$126));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBorderbottomcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$126) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBorderbottomcolor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$126));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERBOTTOMCOLOR$126));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBorderbottomcolor(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$126));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERBOTTOMCOLOR$126));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBorderbottomcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BORDERBOTTOMCOLOR$126);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getBorderrightcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$128));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetBorderrightcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$128));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBorderrightcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BORDERRIGHTCOLOR$128) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBorderrightcolor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$128));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERRIGHTCOLOR$128));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBorderrightcolor(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$128));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERRIGHTCOLOR$128));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBorderrightcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BORDERRIGHTCOLOR$128);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDgmlayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUT$130));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetDgmlayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUT$130));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDgmlayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DGMLAYOUT$130) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDgmlayout(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUT$130));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DGMLAYOUT$130));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDgmlayout(XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUT$130));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(DGMLAYOUT$130));
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDgmlayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DGMLAYOUT$130);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDgmnodekind() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMNODEKIND$132));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetDgmnodekind() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMNODEKIND$132));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDgmnodekind() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DGMNODEKIND$132) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDgmnodekind(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMNODEKIND$132));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DGMNODEKIND$132));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDgmnodekind(XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMNODEKIND$132));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(DGMNODEKIND$132));
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDgmnodekind() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DGMNODEKIND$132);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getDgmlayoutmru() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$134));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInteger xgetDgmlayoutmru() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger = null;
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$134));
            return xmlInteger;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDgmlayoutmru() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DGMLAYOUTMRU$134) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDgmlayoutmru(BigInteger bigInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$134));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DGMLAYOUTMRU$134));
            }
            simpleValue.setBigIntegerValue(bigInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDgmlayoutmru(XmlInteger xmlInteger) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInteger xmlInteger2 = null;
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$134));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(DGMLAYOUTMRU$134));
            }
            xmlInteger2.set(xmlInteger);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDgmlayoutmru() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DGMLAYOUTMRU$134);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STInsetMode.Enum getInsetmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETMODE$136));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(INSETMODE$136));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STInsetMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STInsetMode xgetInsetmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STInsetMode sTInsetMode = null;
            sTInsetMode = (STInsetMode)((Object)this.get_store().find_attribute_user(INSETMODE$136));
            if (sTInsetMode == null) {
                sTInsetMode = (STInsetMode)this.get_default_attribute_value(INSETMODE$136);
            }
            return sTInsetMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInsetmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(INSETMODE$136) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInsetmode(STInsetMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETMODE$136));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INSETMODE$136));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetInsetmode(STInsetMode sTInsetMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STInsetMode sTInsetMode2 = null;
            sTInsetMode2 = (STInsetMode)((Object)this.get_store().find_attribute_user(INSETMODE$136));
            if (sTInsetMode2 == null) {
                sTInsetMode2 = (STInsetMode)((Object)this.get_store().add_attribute_user(INSETMODE$136));
            }
            sTInsetMode2.set(sTInsetMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInsetmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(INSETMODE$136);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getFilled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLED$138));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalse.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse xgetFilled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(FILLED$138));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFilled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILLED$138) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFilled(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLED$138));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILLED$138));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFilled(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(FILLED$138));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(FILLED$138));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFilled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILLED$138);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFillcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLCOLOR$140));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STColorType xgetFillcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType = null;
            sTColorType = (STColorType)((Object)this.get_store().find_attribute_user(FILLCOLOR$140));
            return sTColorType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFillcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FILLCOLOR$140) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFillcolor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLCOLOR$140));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILLCOLOR$140));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFillcolor(STColorType sTColorType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType2 = null;
            sTColorType2 = (STColorType)((Object)this.get_store().find_attribute_user(FILLCOLOR$140));
            if (sTColorType2 == null) {
                sTColorType2 = (STColorType)((Object)this.get_store().add_attribute_user(FILLCOLOR$140));
            }
            sTColorType2.set(sTColorType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFillcolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FILLCOLOR$140);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEditAs.Enum getEditas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EDITAS$142));
            if (simpleValue == null) {
                return null;
            }
            return (STEditAs.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEditAs xgetEditas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEditAs sTEditAs = null;
            sTEditAs = (STEditAs)this.get_store().find_attribute_user(EDITAS$142);
            return sTEditAs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEditas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EDITAS$142) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEditas(STEditAs.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EDITAS$142));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EDITAS$142));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEditas(STEditAs sTEditAs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEditAs sTEditAs2 = null;
            sTEditAs2 = (STEditAs)this.get_store().find_attribute_user(EDITAS$142);
            if (sTEditAs2 == null) {
                sTEditAs2 = (STEditAs)this.get_store().add_attribute_user(EDITAS$142);
            }
            sTEditAs2.set((XmlObject)sTEditAs);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEditas() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EDITAS$142);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTableproperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TABLEPROPERTIES$144));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetTableproperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TABLEPROPERTIES$144));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTableproperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TABLEPROPERTIES$144) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTableproperties(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TABLEPROPERTIES$144));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TABLEPROPERTIES$144));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTableproperties(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TABLEPROPERTIES$144));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TABLEPROPERTIES$144));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTableproperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TABLEPROPERTIES$144);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTablelimits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TABLELIMITS$146));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetTablelimits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TABLELIMITS$146));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTablelimits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TABLELIMITS$146) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTablelimits(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TABLELIMITS$146));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TABLELIMITS$146));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTablelimits(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TABLELIMITS$146));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TABLELIMITS$146));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTablelimits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TABLELIMITS$146);
        }
    }
}

