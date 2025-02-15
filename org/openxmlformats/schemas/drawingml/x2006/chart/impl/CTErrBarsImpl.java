/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTErrDir
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTErrValType
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrValType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTErrBarsImpl
extends XmlComplexContentImpl
implements CTErrBars {
    private static final long serialVersionUID = 1L;
    private static final QName ERRDIR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "errDir");
    private static final QName ERRBARTYPE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "errBarType");
    private static final QName ERRVALTYPE$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "errValType");
    private static final QName NOENDCAP$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "noEndCap");
    private static final QName PLUS$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "plus");
    private static final QName MINUS$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "minus");
    private static final QName VAL$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "val");
    private static final QName SPPR$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName EXTLST$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTErrBarsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrDir getErrDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrDir cTErrDir = null;
            cTErrDir = (CTErrDir)this.get_store().find_element_user(ERRDIR$0, 0);
            if (cTErrDir == null) {
                return null;
            }
            return cTErrDir;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetErrDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ERRDIR$0) != 0;
        }
    }

    @Override
    public void setErrDir(CTErrDir cTErrDir) {
        this.generatedSetterHelperImpl((XmlObject)cTErrDir, ERRDIR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrDir addNewErrDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrDir cTErrDir = null;
            cTErrDir = (CTErrDir)this.get_store().add_element_user(ERRDIR$0);
            return cTErrDir;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetErrDir() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ERRDIR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrBarType getErrBarType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrBarType cTErrBarType = null;
            cTErrBarType = (CTErrBarType)this.get_store().find_element_user(ERRBARTYPE$2, 0);
            if (cTErrBarType == null) {
                return null;
            }
            return cTErrBarType;
        }
    }

    @Override
    public void setErrBarType(CTErrBarType cTErrBarType) {
        this.generatedSetterHelperImpl((XmlObject)cTErrBarType, ERRBARTYPE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrBarType addNewErrBarType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrBarType cTErrBarType = null;
            cTErrBarType = (CTErrBarType)this.get_store().add_element_user(ERRBARTYPE$2);
            return cTErrBarType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrValType getErrValType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrValType cTErrValType = null;
            cTErrValType = (CTErrValType)this.get_store().find_element_user(ERRVALTYPE$4, 0);
            if (cTErrValType == null) {
                return null;
            }
            return cTErrValType;
        }
    }

    @Override
    public void setErrValType(CTErrValType cTErrValType) {
        this.generatedSetterHelperImpl((XmlObject)cTErrValType, ERRVALTYPE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTErrValType addNewErrValType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTErrValType cTErrValType = null;
            cTErrValType = (CTErrValType)this.get_store().add_element_user(ERRVALTYPE$4);
            return cTErrValType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getNoEndCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(NOENDCAP$6, 0));
            if (cTBoolean == null) {
                return null;
            }
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoEndCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOENDCAP$6) != 0;
        }
    }

    @Override
    public void setNoEndCap(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, NOENDCAP$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewNoEndCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(NOENDCAP$6));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoEndCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOENDCAP$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource getPlus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().find_element_user(PLUS$8, 0));
            if (cTNumDataSource == null) {
                return null;
            }
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPlus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PLUS$8) != 0;
        }
    }

    @Override
    public void setPlus(CTNumDataSource cTNumDataSource) {
        this.generatedSetterHelperImpl(cTNumDataSource, PLUS$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource addNewPlus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().add_element_user(PLUS$8));
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPlus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PLUS$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource getMinus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().find_element_user(MINUS$10, 0));
            if (cTNumDataSource == null) {
                return null;
            }
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MINUS$10) != 0;
        }
    }

    @Override
    public void setMinus(CTNumDataSource cTNumDataSource) {
        this.generatedSetterHelperImpl(cTNumDataSource, MINUS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumDataSource addNewMinus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumDataSource cTNumDataSource = null;
            cTNumDataSource = (CTNumDataSource)((Object)this.get_store().add_element_user(MINUS$10));
            return cTNumDataSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinus() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MINUS$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(VAL$12, 0));
            if (cTDouble == null) {
                return null;
            }
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VAL$12) != 0;
        }
    }

    @Override
    public void setVal(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, VAL$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(VAL$12));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VAL$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$14, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPPR$14) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$14));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPPR$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$16, 0));
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$16) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$16));
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$16, 0);
        }
    }
}

