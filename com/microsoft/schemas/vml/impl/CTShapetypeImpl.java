/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.CTCallout
 *  com.microsoft.schemas.office.office.CTClipPath
 *  com.microsoft.schemas.office.office.CTComplex
 *  com.microsoft.schemas.office.office.CTExtrusion
 *  com.microsoft.schemas.office.office.CTSignatureLine
 *  com.microsoft.schemas.office.office.CTSkew
 *  com.microsoft.schemas.office.office.STBWMode
 *  com.microsoft.schemas.office.office.STConnectorType
 *  com.microsoft.schemas.office.office.STHrAlign
 *  com.microsoft.schemas.office.office.STTrueFalse
 *  com.microsoft.schemas.office.office.STTrueFalseBlank
 *  com.microsoft.schemas.office.powerpoint.CTRel
 *  com.microsoft.schemas.office.word.CTAnchorLock
 *  com.microsoft.schemas.office.word.CTBorder
 *  com.microsoft.schemas.office.word.CTWrap
 *  com.microsoft.schemas.vml.CTImageData
 */
package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.office.CTCallout;
import com.microsoft.schemas.office.office.CTClipPath;
import com.microsoft.schemas.office.office.CTComplex;
import com.microsoft.schemas.office.office.CTExtrusion;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.office.office.CTSkew;
import com.microsoft.schemas.office.office.STBWMode;
import com.microsoft.schemas.office.office.STConnectorType;
import com.microsoft.schemas.office.office.STHrAlign;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.office.STTrueFalse;
import com.microsoft.schemas.office.office.STTrueFalseBlank;
import com.microsoft.schemas.office.powerpoint.CTRel;
import com.microsoft.schemas.office.word.CTAnchorLock;
import com.microsoft.schemas.office.word.CTBorder;
import com.microsoft.schemas.office.word.CTWrap;
import com.microsoft.schemas.vml.CTFill;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTHandles;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTShapetype;
import com.microsoft.schemas.vml.CTStroke;
import com.microsoft.schemas.vml.CTTextPath;
import com.microsoft.schemas.vml.CTTextbox;
import com.microsoft.schemas.vml.STColorType;
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

public class CTShapetypeImpl
extends XmlComplexContentImpl
implements CTShapetype {
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
    private static final QName COMPLEX$46 = new QName("urn:schemas-microsoft-com:office:office", "complex");
    private static final QName ID$48 = new QName("", "id");
    private static final QName STYLE$50 = new QName("", "style");
    private static final QName HREF$52 = new QName("", "href");
    private static final QName TARGET$54 = new QName("", "target");
    private static final QName CLASS1$56 = new QName("", "class");
    private static final QName TITLE$58 = new QName("", "title");
    private static final QName ALT$60 = new QName("", "alt");
    private static final QName COORDSIZE$62 = new QName("", "coordsize");
    private static final QName COORDORIGIN$64 = new QName("", "coordorigin");
    private static final QName WRAPCOORDS$66 = new QName("", "wrapcoords");
    private static final QName PRINT$68 = new QName("", "print");
    private static final QName SPID$70 = new QName("urn:schemas-microsoft-com:office:office", "spid");
    private static final QName ONED$72 = new QName("urn:schemas-microsoft-com:office:office", "oned");
    private static final QName REGROUPID$74 = new QName("urn:schemas-microsoft-com:office:office", "regroupid");
    private static final QName DOUBLECLICKNOTIFY$76 = new QName("urn:schemas-microsoft-com:office:office", "doubleclicknotify");
    private static final QName BUTTON$78 = new QName("urn:schemas-microsoft-com:office:office", "button");
    private static final QName USERHIDDEN$80 = new QName("urn:schemas-microsoft-com:office:office", "userhidden");
    private static final QName BULLET$82 = new QName("urn:schemas-microsoft-com:office:office", "bullet");
    private static final QName HR$84 = new QName("urn:schemas-microsoft-com:office:office", "hr");
    private static final QName HRSTD$86 = new QName("urn:schemas-microsoft-com:office:office", "hrstd");
    private static final QName HRNOSHADE$88 = new QName("urn:schemas-microsoft-com:office:office", "hrnoshade");
    private static final QName HRPCT$90 = new QName("urn:schemas-microsoft-com:office:office", "hrpct");
    private static final QName HRALIGN$92 = new QName("urn:schemas-microsoft-com:office:office", "hralign");
    private static final QName ALLOWINCELL$94 = new QName("urn:schemas-microsoft-com:office:office", "allowincell");
    private static final QName ALLOWOVERLAP$96 = new QName("urn:schemas-microsoft-com:office:office", "allowoverlap");
    private static final QName USERDRAWN$98 = new QName("urn:schemas-microsoft-com:office:office", "userdrawn");
    private static final QName BORDERTOPCOLOR$100 = new QName("urn:schemas-microsoft-com:office:office", "bordertopcolor");
    private static final QName BORDERLEFTCOLOR$102 = new QName("urn:schemas-microsoft-com:office:office", "borderleftcolor");
    private static final QName BORDERBOTTOMCOLOR$104 = new QName("urn:schemas-microsoft-com:office:office", "borderbottomcolor");
    private static final QName BORDERRIGHTCOLOR$106 = new QName("urn:schemas-microsoft-com:office:office", "borderrightcolor");
    private static final QName DGMLAYOUT$108 = new QName("urn:schemas-microsoft-com:office:office", "dgmlayout");
    private static final QName DGMNODEKIND$110 = new QName("urn:schemas-microsoft-com:office:office", "dgmnodekind");
    private static final QName DGMLAYOUTMRU$112 = new QName("urn:schemas-microsoft-com:office:office", "dgmlayoutmru");
    private static final QName INSETMODE$114 = new QName("urn:schemas-microsoft-com:office:office", "insetmode");
    private static final QName CHROMAKEY$116 = new QName("", "chromakey");
    private static final QName FILLED$118 = new QName("", "filled");
    private static final QName FILLCOLOR$120 = new QName("", "fillcolor");
    private static final QName OPACITY$122 = new QName("", "opacity");
    private static final QName STROKED$124 = new QName("", "stroked");
    private static final QName STROKECOLOR$126 = new QName("", "strokecolor");
    private static final QName STROKEWEIGHT$128 = new QName("", "strokeweight");
    private static final QName INSETPEN$130 = new QName("", "insetpen");
    private static final QName SPT$132 = new QName("urn:schemas-microsoft-com:office:office", "spt");
    private static final QName CONNECTORTYPE$134 = new QName("urn:schemas-microsoft-com:office:office", "connectortype");
    private static final QName BWMODE$136 = new QName("urn:schemas-microsoft-com:office:office", "bwmode");
    private static final QName BWPURE$138 = new QName("urn:schemas-microsoft-com:office:office", "bwpure");
    private static final QName BWNORMAL$140 = new QName("urn:schemas-microsoft-com:office:office", "bwnormal");
    private static final QName FORCEDASH$142 = new QName("urn:schemas-microsoft-com:office:office", "forcedash");
    private static final QName OLEICON$144 = new QName("urn:schemas-microsoft-com:office:office", "oleicon");
    private static final QName OLE$146 = new QName("urn:schemas-microsoft-com:office:office", "ole");
    private static final QName PREFERRELATIVE$148 = new QName("urn:schemas-microsoft-com:office:office", "preferrelative");
    private static final QName CLIPTOWRAP$150 = new QName("urn:schemas-microsoft-com:office:office", "cliptowrap");
    private static final QName CLIP$152 = new QName("urn:schemas-microsoft-com:office:office", "clip");
    private static final QName ADJ$154 = new QName("", "adj");
    private static final QName PATH2$156 = new QName("", "path");
    private static final QName MASTER$158 = new QName("urn:schemas-microsoft-com:office:office", "master");

    public CTShapetypeImpl(SchemaType schemaType) {
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
                    return CTShapetypeImpl.this.getPathArray(n);
                }

                @Override
                public CTPath set(int n, CTPath cTPath) {
                    CTPath cTPath2 = CTShapetypeImpl.this.getPathArray(n);
                    CTShapetypeImpl.this.setPathArray(n, cTPath);
                    return cTPath2;
                }

                @Override
                public void add(int n, CTPath cTPath) {
                    CTShapetypeImpl.this.insertNewPath(n).set(cTPath);
                }

                @Override
                public CTPath remove(int n) {
                    CTPath cTPath = CTShapetypeImpl.this.getPathArray(n);
                    CTShapetypeImpl.this.removePath(n);
                    return cTPath;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfPathArray();
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
                    return CTShapetypeImpl.this.getFormulasArray(n);
                }

                @Override
                public CTFormulas set(int n, CTFormulas cTFormulas) {
                    CTFormulas cTFormulas2 = CTShapetypeImpl.this.getFormulasArray(n);
                    CTShapetypeImpl.this.setFormulasArray(n, cTFormulas);
                    return cTFormulas2;
                }

                @Override
                public void add(int n, CTFormulas cTFormulas) {
                    CTShapetypeImpl.this.insertNewFormulas(n).set(cTFormulas);
                }

                @Override
                public CTFormulas remove(int n) {
                    CTFormulas cTFormulas = CTShapetypeImpl.this.getFormulasArray(n);
                    CTShapetypeImpl.this.removeFormulas(n);
                    return cTFormulas;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfFormulasArray();
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
                    return CTShapetypeImpl.this.getHandlesArray(n);
                }

                @Override
                public CTHandles set(int n, CTHandles cTHandles) {
                    CTHandles cTHandles2 = CTShapetypeImpl.this.getHandlesArray(n);
                    CTShapetypeImpl.this.setHandlesArray(n, cTHandles);
                    return cTHandles2;
                }

                @Override
                public void add(int n, CTHandles cTHandles) {
                    CTShapetypeImpl.this.insertNewHandles(n).set(cTHandles);
                }

                @Override
                public CTHandles remove(int n) {
                    CTHandles cTHandles = CTShapetypeImpl.this.getHandlesArray(n);
                    CTShapetypeImpl.this.removeHandles(n);
                    return cTHandles;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfHandlesArray();
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
                    return CTShapetypeImpl.this.getFillArray(n);
                }

                @Override
                public CTFill set(int n, CTFill cTFill) {
                    CTFill cTFill2 = CTShapetypeImpl.this.getFillArray(n);
                    CTShapetypeImpl.this.setFillArray(n, cTFill);
                    return cTFill2;
                }

                @Override
                public void add(int n, CTFill cTFill) {
                    CTShapetypeImpl.this.insertNewFill(n).set(cTFill);
                }

                @Override
                public CTFill remove(int n) {
                    CTFill cTFill = CTShapetypeImpl.this.getFillArray(n);
                    CTShapetypeImpl.this.removeFill(n);
                    return cTFill;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfFillArray();
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
                    return CTShapetypeImpl.this.getStrokeArray(n);
                }

                @Override
                public CTStroke set(int n, CTStroke cTStroke) {
                    CTStroke cTStroke2 = CTShapetypeImpl.this.getStrokeArray(n);
                    CTShapetypeImpl.this.setStrokeArray(n, cTStroke);
                    return cTStroke2;
                }

                @Override
                public void add(int n, CTStroke cTStroke) {
                    CTShapetypeImpl.this.insertNewStroke(n).set(cTStroke);
                }

                @Override
                public CTStroke remove(int n) {
                    CTStroke cTStroke = CTShapetypeImpl.this.getStrokeArray(n);
                    CTShapetypeImpl.this.removeStroke(n);
                    return cTStroke;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfStrokeArray();
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
                    return CTShapetypeImpl.this.getShadowArray(n);
                }

                @Override
                public CTShadow set(int n, CTShadow cTShadow) {
                    CTShadow cTShadow2 = CTShapetypeImpl.this.getShadowArray(n);
                    CTShapetypeImpl.this.setShadowArray(n, cTShadow);
                    return cTShadow2;
                }

                @Override
                public void add(int n, CTShadow cTShadow) {
                    CTShapetypeImpl.this.insertNewShadow(n).set(cTShadow);
                }

                @Override
                public CTShadow remove(int n) {
                    CTShadow cTShadow = CTShapetypeImpl.this.getShadowArray(n);
                    CTShapetypeImpl.this.removeShadow(n);
                    return cTShadow;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfShadowArray();
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
                    return CTShapetypeImpl.this.getTextboxArray(n);
                }

                @Override
                public CTTextbox set(int n, CTTextbox cTTextbox) {
                    CTTextbox cTTextbox2 = CTShapetypeImpl.this.getTextboxArray(n);
                    CTShapetypeImpl.this.setTextboxArray(n, cTTextbox);
                    return cTTextbox2;
                }

                @Override
                public void add(int n, CTTextbox cTTextbox) {
                    CTShapetypeImpl.this.insertNewTextbox(n).set(cTTextbox);
                }

                @Override
                public CTTextbox remove(int n) {
                    CTTextbox cTTextbox = CTShapetypeImpl.this.getTextboxArray(n);
                    CTShapetypeImpl.this.removeTextbox(n);
                    return cTTextbox;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfTextboxArray();
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
                    return CTShapetypeImpl.this.getTextpathArray(n);
                }

                @Override
                public CTTextPath set(int n, CTTextPath cTTextPath) {
                    CTTextPath cTTextPath2 = CTShapetypeImpl.this.getTextpathArray(n);
                    CTShapetypeImpl.this.setTextpathArray(n, cTTextPath);
                    return cTTextPath2;
                }

                @Override
                public void add(int n, CTTextPath cTTextPath) {
                    CTShapetypeImpl.this.insertNewTextpath(n).set(cTTextPath);
                }

                @Override
                public CTTextPath remove(int n) {
                    CTTextPath cTTextPath = CTShapetypeImpl.this.getTextpathArray(n);
                    CTShapetypeImpl.this.removeTextpath(n);
                    return cTTextPath;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfTextpathArray();
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
                    return CTShapetypeImpl.this.getImagedataArray(n);
                }

                @Override
                public CTImageData set(int n, CTImageData cTImageData) {
                    CTImageData cTImageData2 = CTShapetypeImpl.this.getImagedataArray(n);
                    CTShapetypeImpl.this.setImagedataArray(n, cTImageData);
                    return cTImageData2;
                }

                @Override
                public void add(int n, CTImageData cTImageData) {
                    CTShapetypeImpl.this.insertNewImagedata(n).set((XmlObject)cTImageData);
                }

                @Override
                public CTImageData remove(int n) {
                    CTImageData cTImageData = CTShapetypeImpl.this.getImagedataArray(n);
                    CTShapetypeImpl.this.removeImagedata(n);
                    return cTImageData;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfImagedataArray();
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
                    return CTShapetypeImpl.this.getSkewArray(n);
                }

                @Override
                public CTSkew set(int n, CTSkew cTSkew) {
                    CTSkew cTSkew2 = CTShapetypeImpl.this.getSkewArray(n);
                    CTShapetypeImpl.this.setSkewArray(n, cTSkew);
                    return cTSkew2;
                }

                @Override
                public void add(int n, CTSkew cTSkew) {
                    CTShapetypeImpl.this.insertNewSkew(n).set((XmlObject)cTSkew);
                }

                @Override
                public CTSkew remove(int n) {
                    CTSkew cTSkew = CTShapetypeImpl.this.getSkewArray(n);
                    CTShapetypeImpl.this.removeSkew(n);
                    return cTSkew;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfSkewArray();
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
                    return CTShapetypeImpl.this.getExtrusionArray(n);
                }

                @Override
                public CTExtrusion set(int n, CTExtrusion cTExtrusion) {
                    CTExtrusion cTExtrusion2 = CTShapetypeImpl.this.getExtrusionArray(n);
                    CTShapetypeImpl.this.setExtrusionArray(n, cTExtrusion);
                    return cTExtrusion2;
                }

                @Override
                public void add(int n, CTExtrusion cTExtrusion) {
                    CTShapetypeImpl.this.insertNewExtrusion(n).set((XmlObject)cTExtrusion);
                }

                @Override
                public CTExtrusion remove(int n) {
                    CTExtrusion cTExtrusion = CTShapetypeImpl.this.getExtrusionArray(n);
                    CTShapetypeImpl.this.removeExtrusion(n);
                    return cTExtrusion;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfExtrusionArray();
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
                    return CTShapetypeImpl.this.getCalloutArray(n);
                }

                @Override
                public CTCallout set(int n, CTCallout cTCallout) {
                    CTCallout cTCallout2 = CTShapetypeImpl.this.getCalloutArray(n);
                    CTShapetypeImpl.this.setCalloutArray(n, cTCallout);
                    return cTCallout2;
                }

                @Override
                public void add(int n, CTCallout cTCallout) {
                    CTShapetypeImpl.this.insertNewCallout(n).set((XmlObject)cTCallout);
                }

                @Override
                public CTCallout remove(int n) {
                    CTCallout cTCallout = CTShapetypeImpl.this.getCalloutArray(n);
                    CTShapetypeImpl.this.removeCallout(n);
                    return cTCallout;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfCalloutArray();
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
                    return CTShapetypeImpl.this.getLockArray(n);
                }

                @Override
                public CTLock set(int n, CTLock cTLock) {
                    CTLock cTLock2 = CTShapetypeImpl.this.getLockArray(n);
                    CTShapetypeImpl.this.setLockArray(n, cTLock);
                    return cTLock2;
                }

                @Override
                public void add(int n, CTLock cTLock) {
                    CTShapetypeImpl.this.insertNewLock(n).set(cTLock);
                }

                @Override
                public CTLock remove(int n) {
                    CTLock cTLock = CTShapetypeImpl.this.getLockArray(n);
                    CTShapetypeImpl.this.removeLock(n);
                    return cTLock;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfLockArray();
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
                    return CTShapetypeImpl.this.getClippathArray(n);
                }

                @Override
                public CTClipPath set(int n, CTClipPath cTClipPath) {
                    CTClipPath cTClipPath2 = CTShapetypeImpl.this.getClippathArray(n);
                    CTShapetypeImpl.this.setClippathArray(n, cTClipPath);
                    return cTClipPath2;
                }

                @Override
                public void add(int n, CTClipPath cTClipPath) {
                    CTShapetypeImpl.this.insertNewClippath(n).set((XmlObject)cTClipPath);
                }

                @Override
                public CTClipPath remove(int n) {
                    CTClipPath cTClipPath = CTShapetypeImpl.this.getClippathArray(n);
                    CTShapetypeImpl.this.removeClippath(n);
                    return cTClipPath;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfClippathArray();
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
                    return CTShapetypeImpl.this.getSignaturelineArray(n);
                }

                @Override
                public CTSignatureLine set(int n, CTSignatureLine cTSignatureLine) {
                    CTSignatureLine cTSignatureLine2 = CTShapetypeImpl.this.getSignaturelineArray(n);
                    CTShapetypeImpl.this.setSignaturelineArray(n, cTSignatureLine);
                    return cTSignatureLine2;
                }

                @Override
                public void add(int n, CTSignatureLine cTSignatureLine) {
                    CTShapetypeImpl.this.insertNewSignatureline(n).set((XmlObject)cTSignatureLine);
                }

                @Override
                public CTSignatureLine remove(int n) {
                    CTSignatureLine cTSignatureLine = CTShapetypeImpl.this.getSignaturelineArray(n);
                    CTShapetypeImpl.this.removeSignatureline(n);
                    return cTSignatureLine;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfSignaturelineArray();
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
                    return CTShapetypeImpl.this.getWrapArray(n);
                }

                @Override
                public CTWrap set(int n, CTWrap cTWrap) {
                    CTWrap cTWrap2 = CTShapetypeImpl.this.getWrapArray(n);
                    CTShapetypeImpl.this.setWrapArray(n, cTWrap);
                    return cTWrap2;
                }

                @Override
                public void add(int n, CTWrap cTWrap) {
                    CTShapetypeImpl.this.insertNewWrap(n).set((XmlObject)cTWrap);
                }

                @Override
                public CTWrap remove(int n) {
                    CTWrap cTWrap = CTShapetypeImpl.this.getWrapArray(n);
                    CTShapetypeImpl.this.removeWrap(n);
                    return cTWrap;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfWrapArray();
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
                    return CTShapetypeImpl.this.getAnchorlockArray(n);
                }

                @Override
                public CTAnchorLock set(int n, CTAnchorLock cTAnchorLock) {
                    CTAnchorLock cTAnchorLock2 = CTShapetypeImpl.this.getAnchorlockArray(n);
                    CTShapetypeImpl.this.setAnchorlockArray(n, cTAnchorLock);
                    return cTAnchorLock2;
                }

                @Override
                public void add(int n, CTAnchorLock cTAnchorLock) {
                    CTShapetypeImpl.this.insertNewAnchorlock(n).set((XmlObject)cTAnchorLock);
                }

                @Override
                public CTAnchorLock remove(int n) {
                    CTAnchorLock cTAnchorLock = CTShapetypeImpl.this.getAnchorlockArray(n);
                    CTShapetypeImpl.this.removeAnchorlock(n);
                    return cTAnchorLock;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfAnchorlockArray();
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
                    return CTShapetypeImpl.this.getBordertopArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTShapetypeImpl.this.getBordertopArray(n);
                    CTShapetypeImpl.this.setBordertopArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTShapetypeImpl.this.insertNewBordertop(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTShapetypeImpl.this.getBordertopArray(n);
                    CTShapetypeImpl.this.removeBordertop(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfBordertopArray();
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
                    return CTShapetypeImpl.this.getBorderbottomArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTShapetypeImpl.this.getBorderbottomArray(n);
                    CTShapetypeImpl.this.setBorderbottomArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTShapetypeImpl.this.insertNewBorderbottom(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTShapetypeImpl.this.getBorderbottomArray(n);
                    CTShapetypeImpl.this.removeBorderbottom(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfBorderbottomArray();
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
                    return CTShapetypeImpl.this.getBorderleftArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTShapetypeImpl.this.getBorderleftArray(n);
                    CTShapetypeImpl.this.setBorderleftArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTShapetypeImpl.this.insertNewBorderleft(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTShapetypeImpl.this.getBorderleftArray(n);
                    CTShapetypeImpl.this.removeBorderleft(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfBorderleftArray();
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
                    return CTShapetypeImpl.this.getBorderrightArray(n);
                }

                @Override
                public CTBorder set(int n, CTBorder cTBorder) {
                    CTBorder cTBorder2 = CTShapetypeImpl.this.getBorderrightArray(n);
                    CTShapetypeImpl.this.setBorderrightArray(n, cTBorder);
                    return cTBorder2;
                }

                @Override
                public void add(int n, CTBorder cTBorder) {
                    CTShapetypeImpl.this.insertNewBorderright(n).set((XmlObject)cTBorder);
                }

                @Override
                public CTBorder remove(int n) {
                    CTBorder cTBorder = CTShapetypeImpl.this.getBorderrightArray(n);
                    CTShapetypeImpl.this.removeBorderright(n);
                    return cTBorder;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfBorderrightArray();
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
                    return CTShapetypeImpl.this.getClientDataArray(n);
                }

                @Override
                public CTClientData set(int n, CTClientData cTClientData) {
                    CTClientData cTClientData2 = CTShapetypeImpl.this.getClientDataArray(n);
                    CTShapetypeImpl.this.setClientDataArray(n, cTClientData);
                    return cTClientData2;
                }

                @Override
                public void add(int n, CTClientData cTClientData) {
                    CTShapetypeImpl.this.insertNewClientData(n).set(cTClientData);
                }

                @Override
                public CTClientData remove(int n) {
                    CTClientData cTClientData = CTShapetypeImpl.this.getClientDataArray(n);
                    CTShapetypeImpl.this.removeClientData(n);
                    return cTClientData;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfClientDataArray();
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
                    return CTShapetypeImpl.this.getTextdataArray(n);
                }

                @Override
                public CTRel set(int n, CTRel cTRel) {
                    CTRel cTRel2 = CTShapetypeImpl.this.getTextdataArray(n);
                    CTShapetypeImpl.this.setTextdataArray(n, cTRel);
                    return cTRel2;
                }

                @Override
                public void add(int n, CTRel cTRel) {
                    CTShapetypeImpl.this.insertNewTextdata(n).set((XmlObject)cTRel);
                }

                @Override
                public CTRel remove(int n) {
                    CTRel cTRel = CTShapetypeImpl.this.getTextdataArray(n);
                    CTShapetypeImpl.this.removeTextdata(n);
                    return cTRel;
                }

                @Override
                public int size() {
                    return CTShapetypeImpl.this.sizeOfTextdataArray();
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
    public CTComplex getComplex() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComplex cTComplex = null;
            cTComplex = (CTComplex)this.get_store().find_element_user(COMPLEX$46, 0);
            if (cTComplex == null) {
                return null;
            }
            return cTComplex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetComplex() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMPLEX$46) != 0;
        }
    }

    @Override
    public void setComplex(CTComplex cTComplex) {
        this.generatedSetterHelperImpl((XmlObject)cTComplex, COMPLEX$46, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComplex addNewComplex() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComplex cTComplex = null;
            cTComplex = (CTComplex)this.get_store().add_element_user(COMPLEX$46);
            return cTComplex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetComplex() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMPLEX$46, 0);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$48));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ID$48));
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
            return this.get_store().find_attribute_user(ID$48) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$48));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ID$48));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ID$48));
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
            this.get_store().remove_attribute(ID$48);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLE$50));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(STYLE$50));
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
            return this.get_store().find_attribute_user(STYLE$50) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLE$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STYLE$50));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(STYLE$50));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(STYLE$50));
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
            this.get_store().remove_attribute(STYLE$50);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HREF$52));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(HREF$52));
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
            return this.get_store().find_attribute_user(HREF$52) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HREF$52));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HREF$52));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(HREF$52));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(HREF$52));
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
            this.get_store().remove_attribute(HREF$52);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGET$54));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TARGET$54));
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
            return this.get_store().find_attribute_user(TARGET$54) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGET$54));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TARGET$54));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TARGET$54));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TARGET$54));
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
            this.get_store().remove_attribute(TARGET$54);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLASS1$56));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(CLASS1$56));
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
            return this.get_store().find_attribute_user(CLASS1$56) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLASS1$56));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CLASS1$56));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(CLASS1$56));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(CLASS1$56));
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
            this.get_store().remove_attribute(CLASS1$56);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TITLE$58));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(TITLE$58));
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
            return this.get_store().find_attribute_user(TITLE$58) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TITLE$58));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TITLE$58));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(TITLE$58));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(TITLE$58));
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
            this.get_store().remove_attribute(TITLE$58);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALT$60));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ALT$60));
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
            return this.get_store().find_attribute_user(ALT$60) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALT$60));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALT$60));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ALT$60));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ALT$60));
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
            this.get_store().remove_attribute(ALT$60);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDSIZE$62));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(COORDSIZE$62));
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
            return this.get_store().find_attribute_user(COORDSIZE$62) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDSIZE$62));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COORDSIZE$62));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(COORDSIZE$62));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(COORDSIZE$62));
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
            this.get_store().remove_attribute(COORDSIZE$62);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDORIGIN$64));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(COORDORIGIN$64));
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
            return this.get_store().find_attribute_user(COORDORIGIN$64) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COORDORIGIN$64));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COORDORIGIN$64));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(COORDORIGIN$64));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(COORDORIGIN$64));
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
            this.get_store().remove_attribute(COORDORIGIN$64);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAPCOORDS$66));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(WRAPCOORDS$66));
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
            return this.get_store().find_attribute_user(WRAPCOORDS$66) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAPCOORDS$66));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(WRAPCOORDS$66));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(WRAPCOORDS$66));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(WRAPCOORDS$66));
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
            this.get_store().remove_attribute(WRAPCOORDS$66);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRINT$68));
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
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(PRINT$68));
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
            return this.get_store().find_attribute_user(PRINT$68) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRINT$68));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PRINT$68));
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
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(PRINT$68));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(PRINT$68));
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
            this.get_store().remove_attribute(PRINT$68);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPID$70));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(SPID$70));
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
            return this.get_store().find_attribute_user(SPID$70) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPID$70));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPID$70));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(SPID$70));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(SPID$70));
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
            this.get_store().remove_attribute(SPID$70);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ONED$72));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ONED$72);
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
            return this.get_store().find_attribute_user(ONED$72) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ONED$72));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ONED$72));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ONED$72);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(ONED$72);
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
            this.get_store().remove_attribute(ONED$72);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REGROUPID$74));
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
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(REGROUPID$74));
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
            return this.get_store().find_attribute_user(REGROUPID$74) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REGROUPID$74));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REGROUPID$74));
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
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(REGROUPID$74));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(REGROUPID$74));
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
            this.get_store().remove_attribute(REGROUPID$74);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$76));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$76);
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
            return this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$76) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$76));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DOUBLECLICKNOTIFY$76));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(DOUBLECLICKNOTIFY$76);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(DOUBLECLICKNOTIFY$76);
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
            this.get_store().remove_attribute(DOUBLECLICKNOTIFY$76);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BUTTON$78));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BUTTON$78);
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
            return this.get_store().find_attribute_user(BUTTON$78) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BUTTON$78));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BUTTON$78));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BUTTON$78);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(BUTTON$78);
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
            this.get_store().remove_attribute(BUTTON$78);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERHIDDEN$80));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERHIDDEN$80);
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
            return this.get_store().find_attribute_user(USERHIDDEN$80) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERHIDDEN$80));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(USERHIDDEN$80));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERHIDDEN$80);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(USERHIDDEN$80);
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
            this.get_store().remove_attribute(USERHIDDEN$80);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BULLET$82));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BULLET$82);
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
            return this.get_store().find_attribute_user(BULLET$82) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BULLET$82));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BULLET$82));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(BULLET$82);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(BULLET$82);
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
            this.get_store().remove_attribute(BULLET$82);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HR$84));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HR$84);
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
            return this.get_store().find_attribute_user(HR$84) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HR$84));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HR$84));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HR$84);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(HR$84);
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
            this.get_store().remove_attribute(HR$84);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRSTD$86));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRSTD$86);
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
            return this.get_store().find_attribute_user(HRSTD$86) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRSTD$86));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRSTD$86));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRSTD$86);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(HRSTD$86);
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
            this.get_store().remove_attribute(HRSTD$86);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRNOSHADE$88));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRNOSHADE$88);
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
            return this.get_store().find_attribute_user(HRNOSHADE$88) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRNOSHADE$88));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRNOSHADE$88));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(HRNOSHADE$88);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(HRNOSHADE$88);
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
            this.get_store().remove_attribute(HRNOSHADE$88);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRPCT$90));
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
            xmlFloat = (XmlFloat)((Object)this.get_store().find_attribute_user(HRPCT$90));
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
            return this.get_store().find_attribute_user(HRPCT$90) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRPCT$90));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRPCT$90));
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
            xmlFloat2 = (XmlFloat)((Object)this.get_store().find_attribute_user(HRPCT$90));
            if (xmlFloat2 == null) {
                xmlFloat2 = (XmlFloat)((Object)this.get_store().add_attribute_user(HRPCT$90));
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
            this.get_store().remove_attribute(HRPCT$90);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRALIGN$92));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(HRALIGN$92));
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
            sTHrAlign = (STHrAlign)this.get_store().find_attribute_user(HRALIGN$92);
            if (sTHrAlign == null) {
                sTHrAlign = (STHrAlign)this.get_default_attribute_value(HRALIGN$92);
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
            return this.get_store().find_attribute_user(HRALIGN$92) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HRALIGN$92));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HRALIGN$92));
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
            sTHrAlign2 = (STHrAlign)this.get_store().find_attribute_user(HRALIGN$92);
            if (sTHrAlign2 == null) {
                sTHrAlign2 = (STHrAlign)this.get_store().add_attribute_user(HRALIGN$92);
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
            this.get_store().remove_attribute(HRALIGN$92);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWINCELL$94));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWINCELL$94);
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
            return this.get_store().find_attribute_user(ALLOWINCELL$94) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWINCELL$94));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALLOWINCELL$94));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWINCELL$94);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(ALLOWINCELL$94);
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
            this.get_store().remove_attribute(ALLOWINCELL$94);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$96));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWOVERLAP$96);
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
            return this.get_store().find_attribute_user(ALLOWOVERLAP$96) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$96));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALLOWOVERLAP$96));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(ALLOWOVERLAP$96);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(ALLOWOVERLAP$96);
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
            this.get_store().remove_attribute(ALLOWOVERLAP$96);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERDRAWN$98));
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
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERDRAWN$98);
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
            return this.get_store().find_attribute_user(USERDRAWN$98) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERDRAWN$98));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(USERDRAWN$98));
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
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(USERDRAWN$98);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(USERDRAWN$98);
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
            this.get_store().remove_attribute(USERDRAWN$98);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$100));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$100));
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
            return this.get_store().find_attribute_user(BORDERTOPCOLOR$100) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$100));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERTOPCOLOR$100));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERTOPCOLOR$100));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERTOPCOLOR$100));
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
            this.get_store().remove_attribute(BORDERTOPCOLOR$100);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$102));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$102));
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
            return this.get_store().find_attribute_user(BORDERLEFTCOLOR$102) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$102));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERLEFTCOLOR$102));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERLEFTCOLOR$102));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERLEFTCOLOR$102));
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
            this.get_store().remove_attribute(BORDERLEFTCOLOR$102);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$104));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$104));
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
            return this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$104) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$104));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERBOTTOMCOLOR$104));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERBOTTOMCOLOR$104));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERBOTTOMCOLOR$104));
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
            this.get_store().remove_attribute(BORDERBOTTOMCOLOR$104);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$106));
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
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$106));
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
            return this.get_store().find_attribute_user(BORDERRIGHTCOLOR$106) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$106));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BORDERRIGHTCOLOR$106));
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
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(BORDERRIGHTCOLOR$106));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(BORDERRIGHTCOLOR$106));
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
            this.get_store().remove_attribute(BORDERRIGHTCOLOR$106);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUT$108));
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
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUT$108));
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
            return this.get_store().find_attribute_user(DGMLAYOUT$108) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUT$108));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DGMLAYOUT$108));
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
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUT$108));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(DGMLAYOUT$108));
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
            this.get_store().remove_attribute(DGMLAYOUT$108);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMNODEKIND$110));
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
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMNODEKIND$110));
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
            return this.get_store().find_attribute_user(DGMNODEKIND$110) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMNODEKIND$110));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DGMNODEKIND$110));
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
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMNODEKIND$110));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(DGMNODEKIND$110));
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
            this.get_store().remove_attribute(DGMNODEKIND$110);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$112));
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
            xmlInteger = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$112));
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
            return this.get_store().find_attribute_user(DGMLAYOUTMRU$112) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$112));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DGMLAYOUTMRU$112));
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
            xmlInteger2 = (XmlInteger)((Object)this.get_store().find_attribute_user(DGMLAYOUTMRU$112));
            if (xmlInteger2 == null) {
                xmlInteger2 = (XmlInteger)((Object)this.get_store().add_attribute_user(DGMLAYOUTMRU$112));
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
            this.get_store().remove_attribute(DGMLAYOUTMRU$112);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETMODE$114));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(INSETMODE$114));
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
            sTInsetMode = (STInsetMode)((Object)this.get_store().find_attribute_user(INSETMODE$114));
            if (sTInsetMode == null) {
                sTInsetMode = (STInsetMode)this.get_default_attribute_value(INSETMODE$114);
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
            return this.get_store().find_attribute_user(INSETMODE$114) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETMODE$114));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INSETMODE$114));
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
            sTInsetMode2 = (STInsetMode)((Object)this.get_store().find_attribute_user(INSETMODE$114));
            if (sTInsetMode2 == null) {
                sTInsetMode2 = (STInsetMode)((Object)this.get_store().add_attribute_user(INSETMODE$114));
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
            this.get_store().remove_attribute(INSETMODE$114);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getChromakey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CHROMAKEY$116));
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
    public STColorType xgetChromakey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType = null;
            sTColorType = (STColorType)((Object)this.get_store().find_attribute_user(CHROMAKEY$116));
            return sTColorType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetChromakey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CHROMAKEY$116) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setChromakey(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CHROMAKEY$116));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CHROMAKEY$116));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetChromakey(STColorType sTColorType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType2 = null;
            sTColorType2 = (STColorType)((Object)this.get_store().find_attribute_user(CHROMAKEY$116));
            if (sTColorType2 == null) {
                sTColorType2 = (STColorType)((Object)this.get_store().add_attribute_user(CHROMAKEY$116));
            }
            sTColorType2.set(sTColorType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetChromakey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CHROMAKEY$116);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLED$118));
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
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(FILLED$118));
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
            return this.get_store().find_attribute_user(FILLED$118) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLED$118));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILLED$118));
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
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(FILLED$118));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(FILLED$118));
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
            this.get_store().remove_attribute(FILLED$118);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLCOLOR$120));
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
            sTColorType = (STColorType)((Object)this.get_store().find_attribute_user(FILLCOLOR$120));
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
            return this.get_store().find_attribute_user(FILLCOLOR$120) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FILLCOLOR$120));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FILLCOLOR$120));
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
            sTColorType2 = (STColorType)((Object)this.get_store().find_attribute_user(FILLCOLOR$120));
            if (sTColorType2 == null) {
                sTColorType2 = (STColorType)((Object)this.get_store().add_attribute_user(FILLCOLOR$120));
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
            this.get_store().remove_attribute(FILLCOLOR$120);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OPACITY$122));
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
    public XmlString xgetOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(OPACITY$122));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(OPACITY$122) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOpacity(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OPACITY$122));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OPACITY$122));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOpacity(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(OPACITY$122));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(OPACITY$122));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOpacity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(OPACITY$122);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getStroked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKED$124));
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
    public STTrueFalse xgetStroked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(STROKED$124));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStroked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STROKED$124) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStroked(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKED$124));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STROKED$124));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStroked(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(STROKED$124));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(STROKED$124));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStroked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STROKED$124);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStrokecolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKECOLOR$126));
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
    public STColorType xgetStrokecolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType = null;
            sTColorType = (STColorType)((Object)this.get_store().find_attribute_user(STROKECOLOR$126));
            return sTColorType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStrokecolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STROKECOLOR$126) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStrokecolor(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKECOLOR$126));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STROKECOLOR$126));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStrokecolor(STColorType sTColorType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STColorType sTColorType2 = null;
            sTColorType2 = (STColorType)((Object)this.get_store().find_attribute_user(STROKECOLOR$126));
            if (sTColorType2 == null) {
                sTColorType2 = (STColorType)((Object)this.get_store().add_attribute_user(STROKECOLOR$126));
            }
            sTColorType2.set(sTColorType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrokecolor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STROKECOLOR$126);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStrokeweight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKEWEIGHT$128));
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
    public XmlString xgetStrokeweight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(STROKEWEIGHT$128));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStrokeweight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STROKEWEIGHT$128) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStrokeweight(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STROKEWEIGHT$128));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STROKEWEIGHT$128));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStrokeweight(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(STROKEWEIGHT$128));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(STROKEWEIGHT$128));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrokeweight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STROKEWEIGHT$128);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETPEN$130));
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
    public STTrueFalse xgetInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (STTrueFalse)((Object)this.get_store().find_attribute_user(INSETPEN$130));
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(INSETPEN$130) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setInsetpen(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INSETPEN$130));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INSETPEN$130));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetInsetpen(STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().find_attribute_user(INSETPEN$130));
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (STTrueFalse)((Object)this.get_store().add_attribute_user(INSETPEN$130));
            }
            sTTrueFalse2.set(sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInsetpen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(INSETPEN$130);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public float getSpt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPT$132));
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
    public XmlFloat xgetSpt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat = null;
            xmlFloat = (XmlFloat)((Object)this.get_store().find_attribute_user(SPT$132));
            return xmlFloat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SPT$132) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSpt(float f) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPT$132));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPT$132));
            }
            simpleValue.setFloatValue(f);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSpt(XmlFloat xmlFloat) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlFloat xmlFloat2 = null;
            xmlFloat2 = (XmlFloat)((Object)this.get_store().find_attribute_user(SPT$132));
            if (xmlFloat2 == null) {
                xmlFloat2 = (XmlFloat)((Object)this.get_store().add_attribute_user(SPT$132));
            }
            xmlFloat2.set(xmlFloat);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SPT$132);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STConnectorType.Enum getConnectortype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CONNECTORTYPE$134));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(CONNECTORTYPE$134));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STConnectorType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STConnectorType xgetConnectortype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STConnectorType sTConnectorType = null;
            sTConnectorType = (STConnectorType)this.get_store().find_attribute_user(CONNECTORTYPE$134);
            if (sTConnectorType == null) {
                sTConnectorType = (STConnectorType)this.get_default_attribute_value(CONNECTORTYPE$134);
            }
            return sTConnectorType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetConnectortype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CONNECTORTYPE$134) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setConnectortype(STConnectorType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CONNECTORTYPE$134));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CONNECTORTYPE$134));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetConnectortype(STConnectorType sTConnectorType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STConnectorType sTConnectorType2 = null;
            sTConnectorType2 = (STConnectorType)this.get_store().find_attribute_user(CONNECTORTYPE$134);
            if (sTConnectorType2 == null) {
                sTConnectorType2 = (STConnectorType)this.get_store().add_attribute_user(CONNECTORTYPE$134);
            }
            sTConnectorType2.set((XmlObject)sTConnectorType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetConnectortype() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CONNECTORTYPE$134);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBWMode.Enum getBwmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWMODE$136));
            if (simpleValue == null) {
                return null;
            }
            return (STBWMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBWMode xgetBwmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBWMode sTBWMode = null;
            sTBWMode = (STBWMode)this.get_store().find_attribute_user(BWMODE$136);
            return sTBWMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBwmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BWMODE$136) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBwmode(STBWMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWMODE$136));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BWMODE$136));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBwmode(STBWMode sTBWMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBWMode sTBWMode2 = null;
            sTBWMode2 = (STBWMode)this.get_store().find_attribute_user(BWMODE$136);
            if (sTBWMode2 == null) {
                sTBWMode2 = (STBWMode)this.get_store().add_attribute_user(BWMODE$136);
            }
            sTBWMode2.set((XmlObject)sTBWMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBwmode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BWMODE$136);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBWMode.Enum getBwpure() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWPURE$138));
            if (simpleValue == null) {
                return null;
            }
            return (STBWMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBWMode xgetBwpure() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBWMode sTBWMode = null;
            sTBWMode = (STBWMode)this.get_store().find_attribute_user(BWPURE$138);
            return sTBWMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBwpure() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BWPURE$138) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBwpure(STBWMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWPURE$138));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BWPURE$138));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBwpure(STBWMode sTBWMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBWMode sTBWMode2 = null;
            sTBWMode2 = (STBWMode)this.get_store().find_attribute_user(BWPURE$138);
            if (sTBWMode2 == null) {
                sTBWMode2 = (STBWMode)this.get_store().add_attribute_user(BWPURE$138);
            }
            sTBWMode2.set((XmlObject)sTBWMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBwpure() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BWPURE$138);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBWMode.Enum getBwnormal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWNORMAL$140));
            if (simpleValue == null) {
                return null;
            }
            return (STBWMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBWMode xgetBwnormal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBWMode sTBWMode = null;
            sTBWMode = (STBWMode)this.get_store().find_attribute_user(BWNORMAL$140);
            return sTBWMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBwnormal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BWNORMAL$140) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBwnormal(STBWMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BWNORMAL$140));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BWNORMAL$140));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBwnormal(STBWMode sTBWMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBWMode sTBWMode2 = null;
            sTBWMode2 = (STBWMode)this.get_store().find_attribute_user(BWNORMAL$140);
            if (sTBWMode2 == null) {
                sTBWMode2 = (STBWMode)this.get_store().add_attribute_user(BWNORMAL$140);
            }
            sTBWMode2.set((XmlObject)sTBWMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBwnormal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BWNORMAL$140);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORCEDASH$142));
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
    public com.microsoft.schemas.office.office.STTrueFalse xgetForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(FORCEDASH$142);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FORCEDASH$142) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setForcedash(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORCEDASH$142));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FORCEDASH$142));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetForcedash(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(FORCEDASH$142);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(FORCEDASH$142);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetForcedash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FORCEDASH$142);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getOleicon() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OLEICON$144));
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
    public com.microsoft.schemas.office.office.STTrueFalse xgetOleicon() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(OLEICON$144);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOleicon() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(OLEICON$144) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOleicon(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OLEICON$144));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OLEICON$144));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOleicon(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(OLEICON$144);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(OLEICON$144);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOleicon() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(OLEICON$144);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank.Enum getOle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OLE$146));
            if (simpleValue == null) {
                return null;
            }
            return (STTrueFalseBlank.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalseBlank xgetOle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank = null;
            sTTrueFalseBlank = (STTrueFalseBlank)this.get_store().find_attribute_user(OLE$146);
            return sTTrueFalseBlank;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(OLE$146) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setOle(STTrueFalseBlank.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(OLE$146));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(OLE$146));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetOle(STTrueFalseBlank sTTrueFalseBlank) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTrueFalseBlank sTTrueFalseBlank2 = null;
            sTTrueFalseBlank2 = (STTrueFalseBlank)this.get_store().find_attribute_user(OLE$146);
            if (sTTrueFalseBlank2 == null) {
                sTTrueFalseBlank2 = (STTrueFalseBlank)this.get_store().add_attribute_user(OLE$146);
            }
            sTTrueFalseBlank2.set((XmlObject)sTTrueFalseBlank);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(OLE$146);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getPreferrelative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PREFERRELATIVE$148));
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
    public com.microsoft.schemas.office.office.STTrueFalse xgetPreferrelative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(PREFERRELATIVE$148);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPreferrelative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PREFERRELATIVE$148) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPreferrelative(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PREFERRELATIVE$148));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PREFERRELATIVE$148));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPreferrelative(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(PREFERRELATIVE$148);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(PREFERRELATIVE$148);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPreferrelative() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PREFERRELATIVE$148);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getCliptowrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLIPTOWRAP$150));
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
    public com.microsoft.schemas.office.office.STTrueFalse xgetCliptowrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(CLIPTOWRAP$150);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCliptowrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CLIPTOWRAP$150) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCliptowrap(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLIPTOWRAP$150));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CLIPTOWRAP$150));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCliptowrap(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(CLIPTOWRAP$150);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(CLIPTOWRAP$150);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCliptowrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CLIPTOWRAP$150);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTrueFalse.Enum getClip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLIP$152));
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
    public com.microsoft.schemas.office.office.STTrueFalse xgetClip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse = null;
            sTTrueFalse = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(CLIP$152);
            return sTTrueFalse;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CLIP$152) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClip(STTrueFalse.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLIP$152));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CLIP$152));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetClip(com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            com.microsoft.schemas.office.office.STTrueFalse sTTrueFalse2 = null;
            sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().find_attribute_user(CLIP$152);
            if (sTTrueFalse2 == null) {
                sTTrueFalse2 = (com.microsoft.schemas.office.office.STTrueFalse)this.get_store().add_attribute_user(CLIP$152);
            }
            sTTrueFalse2.set((XmlObject)sTTrueFalse);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CLIP$152);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAdj() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ADJ$154));
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
    public XmlString xgetAdj() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ADJ$154));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAdj() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ADJ$154) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAdj(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ADJ$154));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ADJ$154));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAdj(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ADJ$154));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ADJ$154));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAdj() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ADJ$154);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getPath2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PATH2$156));
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
    public XmlString xgetPath2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(PATH2$156));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPath2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PATH2$156) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPath2(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PATH2$156));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PATH2$156));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPath2(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(PATH2$156));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(PATH2$156));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPath2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PATH2$156);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MASTER$158));
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
    public XmlString xgetMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(MASTER$158));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MASTER$158) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaster(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MASTER$158));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MASTER$158));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaster(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(MASTER$158));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(MASTER$158));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MASTER$158);
        }
    }
}

