/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STGuid;

public class CTTableStyleImpl
extends XmlComplexContentImpl
implements CTTableStyle {
    private static final long serialVersionUID = 1L;
    private static final QName TBLBG$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tblBg");
    private static final QName WHOLETBL$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "wholeTbl");
    private static final QName BAND1H$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "band1H");
    private static final QName BAND2H$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "band2H");
    private static final QName BAND1V$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "band1V");
    private static final QName BAND2V$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "band2V");
    private static final QName LASTCOL$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lastCol");
    private static final QName FIRSTCOL$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "firstCol");
    private static final QName LASTROW$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lastRow");
    private static final QName SECELL$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "seCell");
    private static final QName SWCELL$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "swCell");
    private static final QName FIRSTROW$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "firstRow");
    private static final QName NECELL$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "neCell");
    private static final QName NWCELL$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "nwCell");
    private static final QName EXTLST$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName STYLEID$30 = new QName("", "styleId");
    private static final QName STYLENAME$32 = new QName("", "styleName");

    public CTTableStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableBackgroundStyle getTblBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableBackgroundStyle cTTableBackgroundStyle = null;
            cTTableBackgroundStyle = (CTTableBackgroundStyle)this.get_store().find_element_user(TBLBG$0, 0);
            if (cTTableBackgroundStyle == null) {
                return null;
            }
            return cTTableBackgroundStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLBG$0) != 0;
        }
    }

    @Override
    public void setTblBg(CTTableBackgroundStyle cTTableBackgroundStyle) {
        this.generatedSetterHelperImpl((XmlObject)cTTableBackgroundStyle, TBLBG$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableBackgroundStyle addNewTblBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableBackgroundStyle cTTableBackgroundStyle = null;
            cTTableBackgroundStyle = (CTTableBackgroundStyle)this.get_store().add_element_user(TBLBG$0);
            return cTTableBackgroundStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblBg() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLBG$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getWholeTbl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(WHOLETBL$2, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWholeTbl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WHOLETBL$2) != 0;
        }
    }

    @Override
    public void setWholeTbl(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, WHOLETBL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewWholeTbl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(WHOLETBL$2));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWholeTbl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WHOLETBL$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getBand1H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(BAND1H$4, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBand1H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAND1H$4) != 0;
        }
    }

    @Override
    public void setBand1H(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, BAND1H$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewBand1H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(BAND1H$4));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBand1H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAND1H$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getBand2H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(BAND2H$6, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBand2H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAND2H$6) != 0;
        }
    }

    @Override
    public void setBand2H(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, BAND2H$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewBand2H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(BAND2H$6));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBand2H() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAND2H$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getBand1V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(BAND1V$8, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBand1V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAND1V$8) != 0;
        }
    }

    @Override
    public void setBand1V(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, BAND1V$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewBand1V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(BAND1V$8));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBand1V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAND1V$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getBand2V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(BAND2V$10, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBand2V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BAND2V$10) != 0;
        }
    }

    @Override
    public void setBand2V(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, BAND2V$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewBand2V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(BAND2V$10));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBand2V() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BAND2V$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getLastCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(LASTCOL$12, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLastCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LASTCOL$12) != 0;
        }
    }

    @Override
    public void setLastCol(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, LASTCOL$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewLastCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(LASTCOL$12));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLastCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LASTCOL$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getFirstCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(FIRSTCOL$14, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFirstCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FIRSTCOL$14) != 0;
        }
    }

    @Override
    public void setFirstCol(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, FIRSTCOL$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewFirstCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(FIRSTCOL$14));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFirstCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FIRSTCOL$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getLastRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(LASTROW$16, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLastRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LASTROW$16) != 0;
        }
    }

    @Override
    public void setLastRow(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, LASTROW$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewLastRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(LASTROW$16));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLastRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LASTROW$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getSeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(SECELL$18, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SECELL$18) != 0;
        }
    }

    @Override
    public void setSeCell(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, SECELL$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewSeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(SECELL$18));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SECELL$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getSwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(SWCELL$20, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SWCELL$20) != 0;
        }
    }

    @Override
    public void setSwCell(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, SWCELL$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewSwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(SWCELL$20));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SWCELL$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getFirstRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(FIRSTROW$22, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFirstRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FIRSTROW$22) != 0;
        }
    }

    @Override
    public void setFirstRow(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, FIRSTROW$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewFirstRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(FIRSTROW$22));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFirstRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FIRSTROW$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getNeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(NECELL$24, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NECELL$24) != 0;
        }
    }

    @Override
    public void setNeCell(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, NECELL$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewNeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(NECELL$24));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NECELL$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle getNwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().find_element_user(NWCELL$26, 0));
            if (cTTablePartStyle == null) {
                return null;
            }
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NWCELL$26) != 0;
        }
    }

    @Override
    public void setNwCell(CTTablePartStyle cTTablePartStyle) {
        this.generatedSetterHelperImpl(cTTablePartStyle, NWCELL$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePartStyle addNewNwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePartStyle cTTablePartStyle = null;
            cTTablePartStyle = (CTTablePartStyle)((Object)this.get_store().add_element_user(NWCELL$26));
            return cTTablePartStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNwCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NWCELL$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$28, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
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
            return this.get_store().count_elements(EXTLST$28) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$28));
            return cTOfficeArtExtensionList;
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
            this.get_store().remove_element(EXTLST$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStyleId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLEID$30));
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
    public STGuid xgetStyleId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGuid sTGuid = null;
            sTGuid = (STGuid)((Object)this.get_store().find_attribute_user(STYLEID$30));
            return sTGuid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStyleId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLEID$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STYLEID$30));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStyleId(STGuid sTGuid) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGuid sTGuid2 = null;
            sTGuid2 = (STGuid)((Object)this.get_store().find_attribute_user(STYLEID$30));
            if (sTGuid2 == null) {
                sTGuid2 = (STGuid)((Object)this.get_store().add_attribute_user(STYLEID$30));
            }
            sTGuid2.set(sTGuid);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStyleName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLENAME$32));
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
    public XmlString xgetStyleName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(STYLENAME$32));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStyleName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STYLENAME$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STYLENAME$32));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStyleName(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(STYLENAME$32));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(STYLENAME$32));
            }
            xmlString2.set(xmlString);
        }
    }
}

