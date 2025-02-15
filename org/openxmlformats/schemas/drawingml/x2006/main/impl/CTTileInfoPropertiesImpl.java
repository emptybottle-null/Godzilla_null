/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

public class CTTileInfoPropertiesImpl
extends XmlComplexContentImpl
implements CTTileInfoProperties {
    private static final long serialVersionUID = 1L;
    private static final QName TX$0 = new QName("", "tx");
    private static final QName TY$2 = new QName("", "ty");
    private static final QName SX$4 = new QName("", "sx");
    private static final QName SY$6 = new QName("", "sy");
    private static final QName FLIP$8 = new QName("", "flip");
    private static final QName ALGN$10 = new QName("", "algn");

    public CTTileInfoPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TX$0));
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
    public STCoordinate xgetTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate = null;
            sTCoordinate = (STCoordinate)((Object)this.get_store().find_attribute_user(TX$0));
            return sTCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TX$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTx(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TX$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TX$0));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTx(STCoordinate sTCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate2 = null;
            sTCoordinate2 = (STCoordinate)((Object)this.get_store().find_attribute_user(TX$0));
            if (sTCoordinate2 == null) {
                sTCoordinate2 = (STCoordinate)((Object)this.get_store().add_attribute_user(TX$0));
            }
            sTCoordinate2.set(sTCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TX$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getTy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TY$2));
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
    public STCoordinate xgetTy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate = null;
            sTCoordinate = (STCoordinate)((Object)this.get_store().find_attribute_user(TY$2));
            return sTCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TY$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTy(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TY$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TY$2));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTy(STCoordinate sTCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate2 = null;
            sTCoordinate2 = (STCoordinate)((Object)this.get_store().find_attribute_user(TY$2));
            if (sTCoordinate2 == null) {
                sTCoordinate2 = (STCoordinate)((Object)this.get_store().add_attribute_user(TY$2));
            }
            sTCoordinate2.set(sTCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TY$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SX$4));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPercentage xgetSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(SX$4));
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SX$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SX$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SX$4));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSx(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(SX$4));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(SX$4));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SX$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SY$6));
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPercentage xgetSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(SY$6));
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SY$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSy(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SY$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SY$6));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSy(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(SY$6));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(SY$6));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SY$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTileFlipMode.Enum getFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLIP$8));
            if (simpleValue == null) {
                return null;
            }
            return (STTileFlipMode.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTileFlipMode xgetFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTileFlipMode sTTileFlipMode = null;
            sTTileFlipMode = (STTileFlipMode)((Object)this.get_store().find_attribute_user(FLIP$8));
            return sTTileFlipMode;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FLIP$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFlip(STTileFlipMode.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FLIP$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FLIP$8));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFlip(STTileFlipMode sTTileFlipMode) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTileFlipMode sTTileFlipMode2 = null;
            sTTileFlipMode2 = (STTileFlipMode)((Object)this.get_store().find_attribute_user(FLIP$8));
            if (sTTileFlipMode2 == null) {
                sTTileFlipMode2 = (STTileFlipMode)((Object)this.get_store().add_attribute_user(FLIP$8));
            }
            sTTileFlipMode2.set(sTTileFlipMode);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FLIP$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRectAlignment.Enum getAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$10));
            if (simpleValue == null) {
                return null;
            }
            return (STRectAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRectAlignment xgetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRectAlignment sTRectAlignment = null;
            sTRectAlignment = (STRectAlignment)((Object)this.get_store().find_attribute_user(ALGN$10));
            return sTRectAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALGN$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgn(STRectAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGN$10));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgn(STRectAlignment sTRectAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRectAlignment sTRectAlignment2 = null;
            sTRectAlignment2 = (STRectAlignment)((Object)this.get_store().find_attribute_user(ALGN$10));
            if (sTRectAlignment2 == null) {
                sTRectAlignment2 = (STRectAlignment)((Object)this.get_store().add_attribute_user(ALGN$10));
            }
            sTRectAlignment2.set(sTRectAlignment);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALGN$10);
        }
    }
}

