/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAngle
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedAngle;

public class CTHslColorImpl
extends XmlComplexContentImpl
implements CTHslColor {
    private static final long serialVersionUID = 1L;
    private static final QName TINT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tint");
    private static final QName SHADE$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "shade");
    private static final QName COMP$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "comp");
    private static final QName INV$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "inv");
    private static final QName GRAY$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gray");
    private static final QName ALPHA$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alpha");
    private static final QName ALPHAOFF$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaOff");
    private static final QName ALPHAMOD$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaMod");
    private static final QName HUE$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hue");
    private static final QName HUEOFF$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hueOff");
    private static final QName HUEMOD$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hueMod");
    private static final QName SAT$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sat");
    private static final QName SATOFF$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "satOff");
    private static final QName SATMOD$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "satMod");
    private static final QName LUM$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lum");
    private static final QName LUMOFF$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lumOff");
    private static final QName LUMMOD$32 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lumMod");
    private static final QName RED$34 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "red");
    private static final QName REDOFF$36 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "redOff");
    private static final QName REDMOD$38 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "redMod");
    private static final QName GREEN$40 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "green");
    private static final QName GREENOFF$42 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "greenOff");
    private static final QName GREENMOD$44 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "greenMod");
    private static final QName BLUE$46 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blue");
    private static final QName BLUEOFF$48 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blueOff");
    private static final QName BLUEMOD$50 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blueMod");
    private static final QName GAMMA$52 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gamma");
    private static final QName INVGAMMA$54 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "invGamma");
    private static final QName HUE2$56 = new QName("", "hue");
    private static final QName SAT2$58 = new QName("", "sat");
    private static final QName LUM2$60 = new QName("", "lum");

    public CTHslColorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPositiveFixedPercentage> getTintList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TintList
            extends AbstractList<CTPositiveFixedPercentage> {
                TintList() {
                }

                @Override
                public CTPositiveFixedPercentage get(int n) {
                    return CTHslColorImpl.this.getTintArray(n);
                }

                @Override
                public CTPositiveFixedPercentage set(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
                    CTPositiveFixedPercentage cTPositiveFixedPercentage2 = CTHslColorImpl.this.getTintArray(n);
                    CTHslColorImpl.this.setTintArray(n, cTPositiveFixedPercentage);
                    return cTPositiveFixedPercentage2;
                }

                @Override
                public void add(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
                    CTHslColorImpl.this.insertNewTint(n).set(cTPositiveFixedPercentage);
                }

                @Override
                public CTPositiveFixedPercentage remove(int n) {
                    CTPositiveFixedPercentage cTPositiveFixedPercentage = CTHslColorImpl.this.getTintArray(n);
                    CTHslColorImpl.this.removeTint(n);
                    return cTPositiveFixedPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfTintArray();
                }
            }
            return new TintList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPositiveFixedPercentage[] getTintArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TINT$0, arrayList);
            CTPositiveFixedPercentage[] cTPositiveFixedPercentageArray = new CTPositiveFixedPercentage[arrayList.size()];
            arrayList.toArray(cTPositiveFixedPercentageArray);
            return cTPositiveFixedPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage getTintArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().find_element_user(TINT$0, n));
            if (cTPositiveFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTintArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TINT$0);
        }
    }

    @Override
    public void setTintArray(CTPositiveFixedPercentage[] cTPositiveFixedPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPositiveFixedPercentageArray, TINT$0);
    }

    @Override
    public void setTintArray(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
        this.generatedSetterHelperImpl(cTPositiveFixedPercentage, TINT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage insertNewTint(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().insert_element_user(TINT$0, n));
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage addNewTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().add_element_user(TINT$0));
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTint(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TINT$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPositiveFixedPercentage> getShadeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ShadeList
            extends AbstractList<CTPositiveFixedPercentage> {
                ShadeList() {
                }

                @Override
                public CTPositiveFixedPercentage get(int n) {
                    return CTHslColorImpl.this.getShadeArray(n);
                }

                @Override
                public CTPositiveFixedPercentage set(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
                    CTPositiveFixedPercentage cTPositiveFixedPercentage2 = CTHslColorImpl.this.getShadeArray(n);
                    CTHslColorImpl.this.setShadeArray(n, cTPositiveFixedPercentage);
                    return cTPositiveFixedPercentage2;
                }

                @Override
                public void add(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
                    CTHslColorImpl.this.insertNewShade(n).set(cTPositiveFixedPercentage);
                }

                @Override
                public CTPositiveFixedPercentage remove(int n) {
                    CTPositiveFixedPercentage cTPositiveFixedPercentage = CTHslColorImpl.this.getShadeArray(n);
                    CTHslColorImpl.this.removeShade(n);
                    return cTPositiveFixedPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfShadeArray();
                }
            }
            return new ShadeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPositiveFixedPercentage[] getShadeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHADE$2, arrayList);
            CTPositiveFixedPercentage[] cTPositiveFixedPercentageArray = new CTPositiveFixedPercentage[arrayList.size()];
            arrayList.toArray(cTPositiveFixedPercentageArray);
            return cTPositiveFixedPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage getShadeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().find_element_user(SHADE$2, n));
            if (cTPositiveFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfShadeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHADE$2);
        }
    }

    @Override
    public void setShadeArray(CTPositiveFixedPercentage[] cTPositiveFixedPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPositiveFixedPercentageArray, SHADE$2);
    }

    @Override
    public void setShadeArray(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
        this.generatedSetterHelperImpl(cTPositiveFixedPercentage, SHADE$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage insertNewShade(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().insert_element_user(SHADE$2, n));
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage addNewShade() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().add_element_user(SHADE$2));
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeShade(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHADE$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTComplementTransform> getCompList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CompList
            extends AbstractList<CTComplementTransform> {
                CompList() {
                }

                @Override
                public CTComplementTransform get(int n) {
                    return CTHslColorImpl.this.getCompArray(n);
                }

                @Override
                public CTComplementTransform set(int n, CTComplementTransform cTComplementTransform) {
                    CTComplementTransform cTComplementTransform2 = CTHslColorImpl.this.getCompArray(n);
                    CTHslColorImpl.this.setCompArray(n, cTComplementTransform);
                    return cTComplementTransform2;
                }

                @Override
                public void add(int n, CTComplementTransform cTComplementTransform) {
                    CTHslColorImpl.this.insertNewComp(n).set((XmlObject)cTComplementTransform);
                }

                @Override
                public CTComplementTransform remove(int n) {
                    CTComplementTransform cTComplementTransform = CTHslColorImpl.this.getCompArray(n);
                    CTHslColorImpl.this.removeComp(n);
                    return cTComplementTransform;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfCompArray();
                }
            }
            return new CompList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTComplementTransform[] getCompArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMP$4, arrayList);
            CTComplementTransform[] cTComplementTransformArray = new CTComplementTransform[arrayList.size()];
            arrayList.toArray(cTComplementTransformArray);
            return cTComplementTransformArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComplementTransform getCompArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComplementTransform cTComplementTransform = null;
            cTComplementTransform = (CTComplementTransform)this.get_store().find_element_user(COMP$4, n);
            if (cTComplementTransform == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTComplementTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCompArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMP$4);
        }
    }

    @Override
    public void setCompArray(CTComplementTransform[] cTComplementTransformArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTComplementTransformArray, COMP$4);
    }

    @Override
    public void setCompArray(int n, CTComplementTransform cTComplementTransform) {
        this.generatedSetterHelperImpl((XmlObject)cTComplementTransform, COMP$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComplementTransform insertNewComp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComplementTransform cTComplementTransform = null;
            cTComplementTransform = (CTComplementTransform)this.get_store().insert_element_user(COMP$4, n);
            return cTComplementTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComplementTransform addNewComp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComplementTransform cTComplementTransform = null;
            cTComplementTransform = (CTComplementTransform)this.get_store().add_element_user(COMP$4);
            return cTComplementTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeComp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMP$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTInverseTransform> getInvList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class InvList
            extends AbstractList<CTInverseTransform> {
                InvList() {
                }

                @Override
                public CTInverseTransform get(int n) {
                    return CTHslColorImpl.this.getInvArray(n);
                }

                @Override
                public CTInverseTransform set(int n, CTInverseTransform cTInverseTransform) {
                    CTInverseTransform cTInverseTransform2 = CTHslColorImpl.this.getInvArray(n);
                    CTHslColorImpl.this.setInvArray(n, cTInverseTransform);
                    return cTInverseTransform2;
                }

                @Override
                public void add(int n, CTInverseTransform cTInverseTransform) {
                    CTHslColorImpl.this.insertNewInv(n).set((XmlObject)cTInverseTransform);
                }

                @Override
                public CTInverseTransform remove(int n) {
                    CTInverseTransform cTInverseTransform = CTHslColorImpl.this.getInvArray(n);
                    CTHslColorImpl.this.removeInv(n);
                    return cTInverseTransform;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfInvArray();
                }
            }
            return new InvList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTInverseTransform[] getInvArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INV$6, arrayList);
            CTInverseTransform[] cTInverseTransformArray = new CTInverseTransform[arrayList.size()];
            arrayList.toArray(cTInverseTransformArray);
            return cTInverseTransformArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInverseTransform getInvArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInverseTransform cTInverseTransform = null;
            cTInverseTransform = (CTInverseTransform)this.get_store().find_element_user(INV$6, n);
            if (cTInverseTransform == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTInverseTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfInvArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INV$6);
        }
    }

    @Override
    public void setInvArray(CTInverseTransform[] cTInverseTransformArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTInverseTransformArray, INV$6);
    }

    @Override
    public void setInvArray(int n, CTInverseTransform cTInverseTransform) {
        this.generatedSetterHelperImpl((XmlObject)cTInverseTransform, INV$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInverseTransform insertNewInv(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInverseTransform cTInverseTransform = null;
            cTInverseTransform = (CTInverseTransform)this.get_store().insert_element_user(INV$6, n);
            return cTInverseTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInverseTransform addNewInv() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInverseTransform cTInverseTransform = null;
            cTInverseTransform = (CTInverseTransform)this.get_store().add_element_user(INV$6);
            return cTInverseTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInv(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INV$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGrayscaleTransform> getGrayList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GrayList
            extends AbstractList<CTGrayscaleTransform> {
                GrayList() {
                }

                @Override
                public CTGrayscaleTransform get(int n) {
                    return CTHslColorImpl.this.getGrayArray(n);
                }

                @Override
                public CTGrayscaleTransform set(int n, CTGrayscaleTransform cTGrayscaleTransform) {
                    CTGrayscaleTransform cTGrayscaleTransform2 = CTHslColorImpl.this.getGrayArray(n);
                    CTHslColorImpl.this.setGrayArray(n, cTGrayscaleTransform);
                    return cTGrayscaleTransform2;
                }

                @Override
                public void add(int n, CTGrayscaleTransform cTGrayscaleTransform) {
                    CTHslColorImpl.this.insertNewGray(n).set((XmlObject)cTGrayscaleTransform);
                }

                @Override
                public CTGrayscaleTransform remove(int n) {
                    CTGrayscaleTransform cTGrayscaleTransform = CTHslColorImpl.this.getGrayArray(n);
                    CTHslColorImpl.this.removeGray(n);
                    return cTGrayscaleTransform;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfGrayArray();
                }
            }
            return new GrayList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGrayscaleTransform[] getGrayArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRAY$8, arrayList);
            CTGrayscaleTransform[] cTGrayscaleTransformArray = new CTGrayscaleTransform[arrayList.size()];
            arrayList.toArray(cTGrayscaleTransformArray);
            return cTGrayscaleTransformArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrayscaleTransform getGrayArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrayscaleTransform cTGrayscaleTransform = null;
            cTGrayscaleTransform = (CTGrayscaleTransform)this.get_store().find_element_user(GRAY$8, n);
            if (cTGrayscaleTransform == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGrayscaleTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGrayArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRAY$8);
        }
    }

    @Override
    public void setGrayArray(CTGrayscaleTransform[] cTGrayscaleTransformArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTGrayscaleTransformArray, GRAY$8);
    }

    @Override
    public void setGrayArray(int n, CTGrayscaleTransform cTGrayscaleTransform) {
        this.generatedSetterHelperImpl((XmlObject)cTGrayscaleTransform, GRAY$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrayscaleTransform insertNewGray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrayscaleTransform cTGrayscaleTransform = null;
            cTGrayscaleTransform = (CTGrayscaleTransform)this.get_store().insert_element_user(GRAY$8, n);
            return cTGrayscaleTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrayscaleTransform addNewGray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrayscaleTransform cTGrayscaleTransform = null;
            cTGrayscaleTransform = (CTGrayscaleTransform)this.get_store().add_element_user(GRAY$8);
            return cTGrayscaleTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRAY$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPositiveFixedPercentage> getAlphaList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaList
            extends AbstractList<CTPositiveFixedPercentage> {
                AlphaList() {
                }

                @Override
                public CTPositiveFixedPercentage get(int n) {
                    return CTHslColorImpl.this.getAlphaArray(n);
                }

                @Override
                public CTPositiveFixedPercentage set(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
                    CTPositiveFixedPercentage cTPositiveFixedPercentage2 = CTHslColorImpl.this.getAlphaArray(n);
                    CTHslColorImpl.this.setAlphaArray(n, cTPositiveFixedPercentage);
                    return cTPositiveFixedPercentage2;
                }

                @Override
                public void add(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
                    CTHslColorImpl.this.insertNewAlpha(n).set(cTPositiveFixedPercentage);
                }

                @Override
                public CTPositiveFixedPercentage remove(int n) {
                    CTPositiveFixedPercentage cTPositiveFixedPercentage = CTHslColorImpl.this.getAlphaArray(n);
                    CTHslColorImpl.this.removeAlpha(n);
                    return cTPositiveFixedPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfAlphaArray();
                }
            }
            return new AlphaList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPositiveFixedPercentage[] getAlphaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHA$10, arrayList);
            CTPositiveFixedPercentage[] cTPositiveFixedPercentageArray = new CTPositiveFixedPercentage[arrayList.size()];
            arrayList.toArray(cTPositiveFixedPercentageArray);
            return cTPositiveFixedPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage getAlphaArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().find_element_user(ALPHA$10, n));
            if (cTPositiveFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHA$10);
        }
    }

    @Override
    public void setAlphaArray(CTPositiveFixedPercentage[] cTPositiveFixedPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPositiveFixedPercentageArray, ALPHA$10);
    }

    @Override
    public void setAlphaArray(int n, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
        this.generatedSetterHelperImpl(cTPositiveFixedPercentage, ALPHA$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage insertNewAlpha(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().insert_element_user(ALPHA$10, n));
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedPercentage addNewAlpha() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage)((Object)this.get_store().add_element_user(ALPHA$10));
            return cTPositiveFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlpha(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHA$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFixedPercentage> getAlphaOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaOffList
            extends AbstractList<CTFixedPercentage> {
                AlphaOffList() {
                }

                @Override
                public CTFixedPercentage get(int n) {
                    return CTHslColorImpl.this.getAlphaOffArray(n);
                }

                @Override
                public CTFixedPercentage set(int n, CTFixedPercentage cTFixedPercentage) {
                    CTFixedPercentage cTFixedPercentage2 = CTHslColorImpl.this.getAlphaOffArray(n);
                    CTHslColorImpl.this.setAlphaOffArray(n, cTFixedPercentage);
                    return cTFixedPercentage2;
                }

                @Override
                public void add(int n, CTFixedPercentage cTFixedPercentage) {
                    CTHslColorImpl.this.insertNewAlphaOff(n).set(cTFixedPercentage);
                }

                @Override
                public CTFixedPercentage remove(int n) {
                    CTFixedPercentage cTFixedPercentage = CTHslColorImpl.this.getAlphaOffArray(n);
                    CTHslColorImpl.this.removeAlphaOff(n);
                    return cTFixedPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfAlphaOffArray();
                }
            }
            return new AlphaOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFixedPercentage[] getAlphaOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAOFF$12, arrayList);
            CTFixedPercentage[] cTFixedPercentageArray = new CTFixedPercentage[arrayList.size()];
            arrayList.toArray(cTFixedPercentageArray);
            return cTFixedPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFixedPercentage getAlphaOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFixedPercentage cTFixedPercentage = null;
            cTFixedPercentage = (CTFixedPercentage)((Object)this.get_store().find_element_user(ALPHAOFF$12, n));
            if (cTFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAOFF$12);
        }
    }

    @Override
    public void setAlphaOffArray(CTFixedPercentage[] cTFixedPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFixedPercentageArray, ALPHAOFF$12);
    }

    @Override
    public void setAlphaOffArray(int n, CTFixedPercentage cTFixedPercentage) {
        this.generatedSetterHelperImpl(cTFixedPercentage, ALPHAOFF$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFixedPercentage insertNewAlphaOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFixedPercentage cTFixedPercentage = null;
            cTFixedPercentage = (CTFixedPercentage)((Object)this.get_store().insert_element_user(ALPHAOFF$12, n));
            return cTFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFixedPercentage addNewAlphaOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFixedPercentage cTFixedPercentage = null;
            cTFixedPercentage = (CTFixedPercentage)((Object)this.get_store().add_element_user(ALPHAOFF$12));
            return cTFixedPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAOFF$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPositivePercentage> getAlphaModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaModList
            extends AbstractList<CTPositivePercentage> {
                AlphaModList() {
                }

                @Override
                public CTPositivePercentage get(int n) {
                    return CTHslColorImpl.this.getAlphaModArray(n);
                }

                @Override
                public CTPositivePercentage set(int n, CTPositivePercentage cTPositivePercentage) {
                    CTPositivePercentage cTPositivePercentage2 = CTHslColorImpl.this.getAlphaModArray(n);
                    CTHslColorImpl.this.setAlphaModArray(n, cTPositivePercentage);
                    return cTPositivePercentage2;
                }

                @Override
                public void add(int n, CTPositivePercentage cTPositivePercentage) {
                    CTHslColorImpl.this.insertNewAlphaMod(n).set(cTPositivePercentage);
                }

                @Override
                public CTPositivePercentage remove(int n) {
                    CTPositivePercentage cTPositivePercentage = CTHslColorImpl.this.getAlphaModArray(n);
                    CTHslColorImpl.this.removeAlphaMod(n);
                    return cTPositivePercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfAlphaModArray();
                }
            }
            return new AlphaModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPositivePercentage[] getAlphaModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAMOD$14, arrayList);
            CTPositivePercentage[] cTPositivePercentageArray = new CTPositivePercentage[arrayList.size()];
            arrayList.toArray(cTPositivePercentageArray);
            return cTPositivePercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositivePercentage getAlphaModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositivePercentage cTPositivePercentage = null;
            cTPositivePercentage = (CTPositivePercentage)((Object)this.get_store().find_element_user(ALPHAMOD$14, n));
            if (cTPositivePercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAMOD$14);
        }
    }

    @Override
    public void setAlphaModArray(CTPositivePercentage[] cTPositivePercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPositivePercentageArray, ALPHAMOD$14);
    }

    @Override
    public void setAlphaModArray(int n, CTPositivePercentage cTPositivePercentage) {
        this.generatedSetterHelperImpl(cTPositivePercentage, ALPHAMOD$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositivePercentage insertNewAlphaMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositivePercentage cTPositivePercentage = null;
            cTPositivePercentage = (CTPositivePercentage)((Object)this.get_store().insert_element_user(ALPHAMOD$14, n));
            return cTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositivePercentage addNewAlphaMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositivePercentage cTPositivePercentage = null;
            cTPositivePercentage = (CTPositivePercentage)((Object)this.get_store().add_element_user(ALPHAMOD$14));
            return cTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAMOD$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPositiveFixedAngle> getHueList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HueList
            extends AbstractList<CTPositiveFixedAngle> {
                HueList() {
                }

                @Override
                public CTPositiveFixedAngle get(int n) {
                    return CTHslColorImpl.this.getHueArray(n);
                }

                @Override
                public CTPositiveFixedAngle set(int n, CTPositiveFixedAngle cTPositiveFixedAngle) {
                    CTPositiveFixedAngle cTPositiveFixedAngle2 = CTHslColorImpl.this.getHueArray(n);
                    CTHslColorImpl.this.setHueArray(n, cTPositiveFixedAngle);
                    return cTPositiveFixedAngle2;
                }

                @Override
                public void add(int n, CTPositiveFixedAngle cTPositiveFixedAngle) {
                    CTHslColorImpl.this.insertNewHue(n).set((XmlObject)cTPositiveFixedAngle);
                }

                @Override
                public CTPositiveFixedAngle remove(int n) {
                    CTPositiveFixedAngle cTPositiveFixedAngle = CTHslColorImpl.this.getHueArray(n);
                    CTHslColorImpl.this.removeHue(n);
                    return cTPositiveFixedAngle;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfHueArray();
                }
            }
            return new HueList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPositiveFixedAngle[] getHueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HUE$16, arrayList);
            CTPositiveFixedAngle[] cTPositiveFixedAngleArray = new CTPositiveFixedAngle[arrayList.size()];
            arrayList.toArray(cTPositiveFixedAngleArray);
            return cTPositiveFixedAngleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedAngle getHueArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedAngle cTPositiveFixedAngle = null;
            cTPositiveFixedAngle = (CTPositiveFixedAngle)this.get_store().find_element_user(HUE$16, n);
            if (cTPositiveFixedAngle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPositiveFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HUE$16);
        }
    }

    @Override
    public void setHueArray(CTPositiveFixedAngle[] cTPositiveFixedAngleArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPositiveFixedAngleArray, HUE$16);
    }

    @Override
    public void setHueArray(int n, CTPositiveFixedAngle cTPositiveFixedAngle) {
        this.generatedSetterHelperImpl((XmlObject)cTPositiveFixedAngle, HUE$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedAngle insertNewHue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedAngle cTPositiveFixedAngle = null;
            cTPositiveFixedAngle = (CTPositiveFixedAngle)this.get_store().insert_element_user(HUE$16, n);
            return cTPositiveFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveFixedAngle addNewHue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveFixedAngle cTPositiveFixedAngle = null;
            cTPositiveFixedAngle = (CTPositiveFixedAngle)this.get_store().add_element_user(HUE$16);
            return cTPositiveFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HUE$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAngle> getHueOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HueOffList
            extends AbstractList<CTAngle> {
                HueOffList() {
                }

                @Override
                public CTAngle get(int n) {
                    return CTHslColorImpl.this.getHueOffArray(n);
                }

                @Override
                public CTAngle set(int n, CTAngle cTAngle) {
                    CTAngle cTAngle2 = CTHslColorImpl.this.getHueOffArray(n);
                    CTHslColorImpl.this.setHueOffArray(n, cTAngle);
                    return cTAngle2;
                }

                @Override
                public void add(int n, CTAngle cTAngle) {
                    CTHslColorImpl.this.insertNewHueOff(n).set((XmlObject)cTAngle);
                }

                @Override
                public CTAngle remove(int n) {
                    CTAngle cTAngle = CTHslColorImpl.this.getHueOffArray(n);
                    CTHslColorImpl.this.removeHueOff(n);
                    return cTAngle;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfHueOffArray();
                }
            }
            return new HueOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAngle[] getHueOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HUEOFF$18, arrayList);
            CTAngle[] cTAngleArray = new CTAngle[arrayList.size()];
            arrayList.toArray(cTAngleArray);
            return cTAngleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAngle getHueOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAngle cTAngle = null;
            cTAngle = (CTAngle)this.get_store().find_element_user(HUEOFF$18, n);
            if (cTAngle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHueOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HUEOFF$18);
        }
    }

    @Override
    public void setHueOffArray(CTAngle[] cTAngleArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAngleArray, HUEOFF$18);
    }

    @Override
    public void setHueOffArray(int n, CTAngle cTAngle) {
        this.generatedSetterHelperImpl((XmlObject)cTAngle, HUEOFF$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAngle insertNewHueOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAngle cTAngle = null;
            cTAngle = (CTAngle)this.get_store().insert_element_user(HUEOFF$18, n);
            return cTAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAngle addNewHueOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAngle cTAngle = null;
            cTAngle = (CTAngle)this.get_store().add_element_user(HUEOFF$18);
            return cTAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHueOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HUEOFF$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPositivePercentage> getHueModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HueModList
            extends AbstractList<CTPositivePercentage> {
                HueModList() {
                }

                @Override
                public CTPositivePercentage get(int n) {
                    return CTHslColorImpl.this.getHueModArray(n);
                }

                @Override
                public CTPositivePercentage set(int n, CTPositivePercentage cTPositivePercentage) {
                    CTPositivePercentage cTPositivePercentage2 = CTHslColorImpl.this.getHueModArray(n);
                    CTHslColorImpl.this.setHueModArray(n, cTPositivePercentage);
                    return cTPositivePercentage2;
                }

                @Override
                public void add(int n, CTPositivePercentage cTPositivePercentage) {
                    CTHslColorImpl.this.insertNewHueMod(n).set(cTPositivePercentage);
                }

                @Override
                public CTPositivePercentage remove(int n) {
                    CTPositivePercentage cTPositivePercentage = CTHslColorImpl.this.getHueModArray(n);
                    CTHslColorImpl.this.removeHueMod(n);
                    return cTPositivePercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfHueModArray();
                }
            }
            return new HueModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPositivePercentage[] getHueModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HUEMOD$20, arrayList);
            CTPositivePercentage[] cTPositivePercentageArray = new CTPositivePercentage[arrayList.size()];
            arrayList.toArray(cTPositivePercentageArray);
            return cTPositivePercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositivePercentage getHueModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositivePercentage cTPositivePercentage = null;
            cTPositivePercentage = (CTPositivePercentage)((Object)this.get_store().find_element_user(HUEMOD$20, n));
            if (cTPositivePercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHueModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HUEMOD$20);
        }
    }

    @Override
    public void setHueModArray(CTPositivePercentage[] cTPositivePercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPositivePercentageArray, HUEMOD$20);
    }

    @Override
    public void setHueModArray(int n, CTPositivePercentage cTPositivePercentage) {
        this.generatedSetterHelperImpl(cTPositivePercentage, HUEMOD$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositivePercentage insertNewHueMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositivePercentage cTPositivePercentage = null;
            cTPositivePercentage = (CTPositivePercentage)((Object)this.get_store().insert_element_user(HUEMOD$20, n));
            return cTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositivePercentage addNewHueMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositivePercentage cTPositivePercentage = null;
            cTPositivePercentage = (CTPositivePercentage)((Object)this.get_store().add_element_user(HUEMOD$20));
            return cTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHueMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HUEMOD$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getSatList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SatList
            extends AbstractList<CTPercentage> {
                SatList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getSatArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getSatArray(n);
                    CTHslColorImpl.this.setSatArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewSat(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getSatArray(n);
                    CTHslColorImpl.this.removeSat(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfSatArray();
                }
            }
            return new SatList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getSatArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SAT$22, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getSatArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(SAT$22, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSatArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SAT$22);
        }
    }

    @Override
    public void setSatArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, SAT$22);
    }

    @Override
    public void setSatArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, SAT$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewSat(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(SAT$22, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewSat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(SAT$22));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSat(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SAT$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getSatOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SatOffList
            extends AbstractList<CTPercentage> {
                SatOffList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getSatOffArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getSatOffArray(n);
                    CTHslColorImpl.this.setSatOffArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewSatOff(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getSatOffArray(n);
                    CTHslColorImpl.this.removeSatOff(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfSatOffArray();
                }
            }
            return new SatOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getSatOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SATOFF$24, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getSatOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(SATOFF$24, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSatOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SATOFF$24);
        }
    }

    @Override
    public void setSatOffArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, SATOFF$24);
    }

    @Override
    public void setSatOffArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, SATOFF$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewSatOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(SATOFF$24, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewSatOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(SATOFF$24));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSatOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SATOFF$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getSatModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SatModList
            extends AbstractList<CTPercentage> {
                SatModList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getSatModArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getSatModArray(n);
                    CTHslColorImpl.this.setSatModArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewSatMod(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getSatModArray(n);
                    CTHslColorImpl.this.removeSatMod(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfSatModArray();
                }
            }
            return new SatModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getSatModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SATMOD$26, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getSatModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(SATMOD$26, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSatModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SATMOD$26);
        }
    }

    @Override
    public void setSatModArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, SATMOD$26);
    }

    @Override
    public void setSatModArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, SATMOD$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewSatMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(SATMOD$26, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewSatMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(SATMOD$26));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSatMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SATMOD$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getLumList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LumList
            extends AbstractList<CTPercentage> {
                LumList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getLumArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getLumArray(n);
                    CTHslColorImpl.this.setLumArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewLum(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getLumArray(n);
                    CTHslColorImpl.this.removeLum(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfLumArray();
                }
            }
            return new LumList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getLumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LUM$28, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getLumArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(LUM$28, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LUM$28);
        }
    }

    @Override
    public void setLumArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, LUM$28);
    }

    @Override
    public void setLumArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, LUM$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewLum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(LUM$28, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewLum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(LUM$28));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LUM$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getLumOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LumOffList
            extends AbstractList<CTPercentage> {
                LumOffList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getLumOffArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getLumOffArray(n);
                    CTHslColorImpl.this.setLumOffArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewLumOff(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getLumOffArray(n);
                    CTHslColorImpl.this.removeLumOff(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfLumOffArray();
                }
            }
            return new LumOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getLumOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LUMOFF$30, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getLumOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(LUMOFF$30, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLumOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LUMOFF$30);
        }
    }

    @Override
    public void setLumOffArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, LUMOFF$30);
    }

    @Override
    public void setLumOffArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, LUMOFF$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewLumOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(LUMOFF$30, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewLumOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(LUMOFF$30));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLumOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LUMOFF$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getLumModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LumModList
            extends AbstractList<CTPercentage> {
                LumModList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getLumModArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getLumModArray(n);
                    CTHslColorImpl.this.setLumModArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewLumMod(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getLumModArray(n);
                    CTHslColorImpl.this.removeLumMod(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfLumModArray();
                }
            }
            return new LumModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getLumModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LUMMOD$32, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getLumModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(LUMMOD$32, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfLumModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LUMMOD$32);
        }
    }

    @Override
    public void setLumModArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, LUMMOD$32);
    }

    @Override
    public void setLumModArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, LUMMOD$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewLumMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(LUMMOD$32, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewLumMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(LUMMOD$32));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLumMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LUMMOD$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getRedList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RedList
            extends AbstractList<CTPercentage> {
                RedList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getRedArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getRedArray(n);
                    CTHslColorImpl.this.setRedArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewRed(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getRedArray(n);
                    CTHslColorImpl.this.removeRed(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfRedArray();
                }
            }
            return new RedList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getRedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RED$34, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getRedArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(RED$34, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRedArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RED$34);
        }
    }

    @Override
    public void setRedArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, RED$34);
    }

    @Override
    public void setRedArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, RED$34, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewRed(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(RED$34, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewRed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(RED$34));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRed(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RED$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getRedOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RedOffList
            extends AbstractList<CTPercentage> {
                RedOffList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getRedOffArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getRedOffArray(n);
                    CTHslColorImpl.this.setRedOffArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewRedOff(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getRedOffArray(n);
                    CTHslColorImpl.this.removeRedOff(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfRedOffArray();
                }
            }
            return new RedOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getRedOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REDOFF$36, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getRedOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(REDOFF$36, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRedOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REDOFF$36);
        }
    }

    @Override
    public void setRedOffArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, REDOFF$36);
    }

    @Override
    public void setRedOffArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, REDOFF$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewRedOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(REDOFF$36, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewRedOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(REDOFF$36));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRedOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REDOFF$36, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getRedModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RedModList
            extends AbstractList<CTPercentage> {
                RedModList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getRedModArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getRedModArray(n);
                    CTHslColorImpl.this.setRedModArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewRedMod(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getRedModArray(n);
                    CTHslColorImpl.this.removeRedMod(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfRedModArray();
                }
            }
            return new RedModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getRedModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REDMOD$38, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getRedModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(REDMOD$38, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRedModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REDMOD$38);
        }
    }

    @Override
    public void setRedModArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, REDMOD$38);
    }

    @Override
    public void setRedModArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, REDMOD$38, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewRedMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(REDMOD$38, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewRedMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(REDMOD$38));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRedMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REDMOD$38, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getGreenList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GreenList
            extends AbstractList<CTPercentage> {
                GreenList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getGreenArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getGreenArray(n);
                    CTHslColorImpl.this.setGreenArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewGreen(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getGreenArray(n);
                    CTHslColorImpl.this.removeGreen(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfGreenArray();
                }
            }
            return new GreenList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getGreenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GREEN$40, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getGreenArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(GREEN$40, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGreenArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GREEN$40);
        }
    }

    @Override
    public void setGreenArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, GREEN$40);
    }

    @Override
    public void setGreenArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, GREEN$40, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewGreen(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(GREEN$40, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewGreen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(GREEN$40));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGreen(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GREEN$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getGreenOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GreenOffList
            extends AbstractList<CTPercentage> {
                GreenOffList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getGreenOffArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getGreenOffArray(n);
                    CTHslColorImpl.this.setGreenOffArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewGreenOff(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getGreenOffArray(n);
                    CTHslColorImpl.this.removeGreenOff(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfGreenOffArray();
                }
            }
            return new GreenOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getGreenOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GREENOFF$42, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getGreenOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(GREENOFF$42, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGreenOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GREENOFF$42);
        }
    }

    @Override
    public void setGreenOffArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, GREENOFF$42);
    }

    @Override
    public void setGreenOffArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, GREENOFF$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewGreenOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(GREENOFF$42, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewGreenOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(GREENOFF$42));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGreenOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GREENOFF$42, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getGreenModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GreenModList
            extends AbstractList<CTPercentage> {
                GreenModList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getGreenModArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getGreenModArray(n);
                    CTHslColorImpl.this.setGreenModArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewGreenMod(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getGreenModArray(n);
                    CTHslColorImpl.this.removeGreenMod(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfGreenModArray();
                }
            }
            return new GreenModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getGreenModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GREENMOD$44, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getGreenModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(GREENMOD$44, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGreenModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GREENMOD$44);
        }
    }

    @Override
    public void setGreenModArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, GREENMOD$44);
    }

    @Override
    public void setGreenModArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, GREENMOD$44, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewGreenMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(GREENMOD$44, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewGreenMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(GREENMOD$44));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGreenMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GREENMOD$44, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getBlueList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BlueList
            extends AbstractList<CTPercentage> {
                BlueList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getBlueArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getBlueArray(n);
                    CTHslColorImpl.this.setBlueArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewBlue(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getBlueArray(n);
                    CTHslColorImpl.this.removeBlue(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfBlueArray();
                }
            }
            return new BlueList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getBlueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BLUE$46, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getBlueArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(BLUE$46, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBlueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLUE$46);
        }
    }

    @Override
    public void setBlueArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, BLUE$46);
    }

    @Override
    public void setBlueArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, BLUE$46, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewBlue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(BLUE$46, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewBlue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(BLUE$46));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBlue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLUE$46, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getBlueOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BlueOffList
            extends AbstractList<CTPercentage> {
                BlueOffList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getBlueOffArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getBlueOffArray(n);
                    CTHslColorImpl.this.setBlueOffArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewBlueOff(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getBlueOffArray(n);
                    CTHslColorImpl.this.removeBlueOff(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfBlueOffArray();
                }
            }
            return new BlueOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getBlueOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BLUEOFF$48, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getBlueOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(BLUEOFF$48, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBlueOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLUEOFF$48);
        }
    }

    @Override
    public void setBlueOffArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, BLUEOFF$48);
    }

    @Override
    public void setBlueOffArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, BLUEOFF$48, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewBlueOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(BLUEOFF$48, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewBlueOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(BLUEOFF$48));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBlueOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLUEOFF$48, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPercentage> getBlueModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BlueModList
            extends AbstractList<CTPercentage> {
                BlueModList() {
                }

                @Override
                public CTPercentage get(int n) {
                    return CTHslColorImpl.this.getBlueModArray(n);
                }

                @Override
                public CTPercentage set(int n, CTPercentage cTPercentage) {
                    CTPercentage cTPercentage2 = CTHslColorImpl.this.getBlueModArray(n);
                    CTHslColorImpl.this.setBlueModArray(n, cTPercentage);
                    return cTPercentage2;
                }

                @Override
                public void add(int n, CTPercentage cTPercentage) {
                    CTHslColorImpl.this.insertNewBlueMod(n).set(cTPercentage);
                }

                @Override
                public CTPercentage remove(int n) {
                    CTPercentage cTPercentage = CTHslColorImpl.this.getBlueModArray(n);
                    CTHslColorImpl.this.removeBlueMod(n);
                    return cTPercentage;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfBlueModArray();
                }
            }
            return new BlueModList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPercentage[] getBlueModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BLUEMOD$50, arrayList);
            CTPercentage[] cTPercentageArray = new CTPercentage[arrayList.size()];
            arrayList.toArray(cTPercentageArray);
            return cTPercentageArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage getBlueModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().find_element_user(BLUEMOD$50, n));
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBlueModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLUEMOD$50);
        }
    }

    @Override
    public void setBlueModArray(CTPercentage[] cTPercentageArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPercentageArray, BLUEMOD$50);
    }

    @Override
    public void setBlueModArray(int n, CTPercentage cTPercentage) {
        this.generatedSetterHelperImpl(cTPercentage, BLUEMOD$50, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage insertNewBlueMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().insert_element_user(BLUEMOD$50, n));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPercentage addNewBlueMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPercentage cTPercentage = null;
            cTPercentage = (CTPercentage)((Object)this.get_store().add_element_user(BLUEMOD$50));
            return cTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBlueMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLUEMOD$50, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGammaTransform> getGammaList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GammaList
            extends AbstractList<CTGammaTransform> {
                GammaList() {
                }

                @Override
                public CTGammaTransform get(int n) {
                    return CTHslColorImpl.this.getGammaArray(n);
                }

                @Override
                public CTGammaTransform set(int n, CTGammaTransform cTGammaTransform) {
                    CTGammaTransform cTGammaTransform2 = CTHslColorImpl.this.getGammaArray(n);
                    CTHslColorImpl.this.setGammaArray(n, cTGammaTransform);
                    return cTGammaTransform2;
                }

                @Override
                public void add(int n, CTGammaTransform cTGammaTransform) {
                    CTHslColorImpl.this.insertNewGamma(n).set((XmlObject)cTGammaTransform);
                }

                @Override
                public CTGammaTransform remove(int n) {
                    CTGammaTransform cTGammaTransform = CTHslColorImpl.this.getGammaArray(n);
                    CTHslColorImpl.this.removeGamma(n);
                    return cTGammaTransform;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfGammaArray();
                }
            }
            return new GammaList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGammaTransform[] getGammaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GAMMA$52, arrayList);
            CTGammaTransform[] cTGammaTransformArray = new CTGammaTransform[arrayList.size()];
            arrayList.toArray(cTGammaTransformArray);
            return cTGammaTransformArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGammaTransform getGammaArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGammaTransform cTGammaTransform = null;
            cTGammaTransform = (CTGammaTransform)this.get_store().find_element_user(GAMMA$52, n);
            if (cTGammaTransform == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGammaTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGammaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GAMMA$52);
        }
    }

    @Override
    public void setGammaArray(CTGammaTransform[] cTGammaTransformArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTGammaTransformArray, GAMMA$52);
    }

    @Override
    public void setGammaArray(int n, CTGammaTransform cTGammaTransform) {
        this.generatedSetterHelperImpl((XmlObject)cTGammaTransform, GAMMA$52, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGammaTransform insertNewGamma(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGammaTransform cTGammaTransform = null;
            cTGammaTransform = (CTGammaTransform)this.get_store().insert_element_user(GAMMA$52, n);
            return cTGammaTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGammaTransform addNewGamma() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGammaTransform cTGammaTransform = null;
            cTGammaTransform = (CTGammaTransform)this.get_store().add_element_user(GAMMA$52);
            return cTGammaTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGamma(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GAMMA$52, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTInverseGammaTransform> getInvGammaList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class InvGammaList
            extends AbstractList<CTInverseGammaTransform> {
                InvGammaList() {
                }

                @Override
                public CTInverseGammaTransform get(int n) {
                    return CTHslColorImpl.this.getInvGammaArray(n);
                }

                @Override
                public CTInverseGammaTransform set(int n, CTInverseGammaTransform cTInverseGammaTransform) {
                    CTInverseGammaTransform cTInverseGammaTransform2 = CTHslColorImpl.this.getInvGammaArray(n);
                    CTHslColorImpl.this.setInvGammaArray(n, cTInverseGammaTransform);
                    return cTInverseGammaTransform2;
                }

                @Override
                public void add(int n, CTInverseGammaTransform cTInverseGammaTransform) {
                    CTHslColorImpl.this.insertNewInvGamma(n).set((XmlObject)cTInverseGammaTransform);
                }

                @Override
                public CTInverseGammaTransform remove(int n) {
                    CTInverseGammaTransform cTInverseGammaTransform = CTHslColorImpl.this.getInvGammaArray(n);
                    CTHslColorImpl.this.removeInvGamma(n);
                    return cTInverseGammaTransform;
                }

                @Override
                public int size() {
                    return CTHslColorImpl.this.sizeOfInvGammaArray();
                }
            }
            return new InvGammaList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTInverseGammaTransform[] getInvGammaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INVGAMMA$54, arrayList);
            CTInverseGammaTransform[] cTInverseGammaTransformArray = new CTInverseGammaTransform[arrayList.size()];
            arrayList.toArray(cTInverseGammaTransformArray);
            return cTInverseGammaTransformArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInverseGammaTransform getInvGammaArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInverseGammaTransform cTInverseGammaTransform = null;
            cTInverseGammaTransform = (CTInverseGammaTransform)this.get_store().find_element_user(INVGAMMA$54, n);
            if (cTInverseGammaTransform == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTInverseGammaTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfInvGammaArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INVGAMMA$54);
        }
    }

    @Override
    public void setInvGammaArray(CTInverseGammaTransform[] cTInverseGammaTransformArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTInverseGammaTransformArray, INVGAMMA$54);
    }

    @Override
    public void setInvGammaArray(int n, CTInverseGammaTransform cTInverseGammaTransform) {
        this.generatedSetterHelperImpl((XmlObject)cTInverseGammaTransform, INVGAMMA$54, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInverseGammaTransform insertNewInvGamma(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInverseGammaTransform cTInverseGammaTransform = null;
            cTInverseGammaTransform = (CTInverseGammaTransform)this.get_store().insert_element_user(INVGAMMA$54, n);
            return cTInverseGammaTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInverseGammaTransform addNewInvGamma() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInverseGammaTransform cTInverseGammaTransform = null;
            cTInverseGammaTransform = (CTInverseGammaTransform)this.get_store().add_element_user(INVGAMMA$54);
            return cTInverseGammaTransform;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInvGamma(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INVGAMMA$54, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getHue2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HUE2$56));
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
    public STPositiveFixedAngle xgetHue2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveFixedAngle sTPositiveFixedAngle = null;
            sTPositiveFixedAngle = (STPositiveFixedAngle)((Object)this.get_store().find_attribute_user(HUE2$56));
            return sTPositiveFixedAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHue2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HUE2$56));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HUE2$56));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHue2(STPositiveFixedAngle sTPositiveFixedAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveFixedAngle sTPositiveFixedAngle2 = null;
            sTPositiveFixedAngle2 = (STPositiveFixedAngle)((Object)this.get_store().find_attribute_user(HUE2$56));
            if (sTPositiveFixedAngle2 == null) {
                sTPositiveFixedAngle2 = (STPositiveFixedAngle)((Object)this.get_store().add_attribute_user(HUE2$56));
            }
            sTPositiveFixedAngle2.set(sTPositiveFixedAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSat2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SAT2$58));
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
    public STPercentage xgetSat2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(SAT2$58));
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSat2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SAT2$58));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SAT2$58));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSat2(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(SAT2$58));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(SAT2$58));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getLum2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LUM2$60));
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
    public STPercentage xgetLum2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(LUM2$60));
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLum2(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LUM2$60));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LUM2$60));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLum2(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(LUM2$60));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(LUM2$60));
            }
            sTPercentage2.set(sTPercentage);
        }
    }
}

