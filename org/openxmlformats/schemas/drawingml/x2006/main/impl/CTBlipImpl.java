/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public class CTBlipImpl
extends XmlComplexContentImpl
implements CTBlip {
    private static final long serialVersionUID = 1L;
    private static final QName ALPHABILEVEL$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaBiLevel");
    private static final QName ALPHACEILING$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaCeiling");
    private static final QName ALPHAFLOOR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaFloor");
    private static final QName ALPHAINV$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaInv");
    private static final QName ALPHAMOD$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaMod");
    private static final QName ALPHAMODFIX$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaModFix");
    private static final QName ALPHAREPL$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaRepl");
    private static final QName BILEVEL$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "biLevel");
    private static final QName BLUR$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blur");
    private static final QName CLRCHANGE$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "clrChange");
    private static final QName CLRREPL$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "clrRepl");
    private static final QName DUOTONE$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "duotone");
    private static final QName FILLOVERLAY$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillOverlay");
    private static final QName GRAYSCL$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "grayscl");
    private static final QName HSL$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hsl");
    private static final QName LUM$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lum");
    private static final QName TINT$32 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tint");
    private static final QName EXTLST$34 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName EMBED$36 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "embed");
    private static final QName LINK$38 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "link");
    private static final QName CSTATE$40 = new QName("", "cstate");

    public CTBlipImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaBiLevelEffect> getAlphaBiLevelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaBiLevelList
            extends AbstractList<CTAlphaBiLevelEffect> {
                AlphaBiLevelList() {
                }

                @Override
                public CTAlphaBiLevelEffect get(int n) {
                    return CTBlipImpl.this.getAlphaBiLevelArray(n);
                }

                @Override
                public CTAlphaBiLevelEffect set(int n, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
                    CTAlphaBiLevelEffect cTAlphaBiLevelEffect2 = CTBlipImpl.this.getAlphaBiLevelArray(n);
                    CTBlipImpl.this.setAlphaBiLevelArray(n, cTAlphaBiLevelEffect);
                    return cTAlphaBiLevelEffect2;
                }

                @Override
                public void add(int n, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
                    CTBlipImpl.this.insertNewAlphaBiLevel(n).set((XmlObject)cTAlphaBiLevelEffect);
                }

                @Override
                public CTAlphaBiLevelEffect remove(int n) {
                    CTAlphaBiLevelEffect cTAlphaBiLevelEffect = CTBlipImpl.this.getAlphaBiLevelArray(n);
                    CTBlipImpl.this.removeAlphaBiLevel(n);
                    return cTAlphaBiLevelEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaBiLevelArray();
                }
            }
            return new AlphaBiLevelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaBiLevelEffect[] getAlphaBiLevelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHABILEVEL$0, arrayList);
            CTAlphaBiLevelEffect[] cTAlphaBiLevelEffectArray = new CTAlphaBiLevelEffect[arrayList.size()];
            arrayList.toArray(cTAlphaBiLevelEffectArray);
            return cTAlphaBiLevelEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaBiLevelEffect getAlphaBiLevelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaBiLevelEffect cTAlphaBiLevelEffect = null;
            cTAlphaBiLevelEffect = (CTAlphaBiLevelEffect)this.get_store().find_element_user(ALPHABILEVEL$0, n);
            if (cTAlphaBiLevelEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaBiLevelEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaBiLevelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHABILEVEL$0);
        }
    }

    @Override
    public void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] cTAlphaBiLevelEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaBiLevelEffectArray, ALPHABILEVEL$0);
    }

    @Override
    public void setAlphaBiLevelArray(int n, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaBiLevelEffect, ALPHABILEVEL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaBiLevelEffect insertNewAlphaBiLevel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaBiLevelEffect cTAlphaBiLevelEffect = null;
            cTAlphaBiLevelEffect = (CTAlphaBiLevelEffect)this.get_store().insert_element_user(ALPHABILEVEL$0, n);
            return cTAlphaBiLevelEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaBiLevelEffect addNewAlphaBiLevel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaBiLevelEffect cTAlphaBiLevelEffect = null;
            cTAlphaBiLevelEffect = (CTAlphaBiLevelEffect)this.get_store().add_element_user(ALPHABILEVEL$0);
            return cTAlphaBiLevelEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaBiLevel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHABILEVEL$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaCeilingEffect> getAlphaCeilingList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaCeilingList
            extends AbstractList<CTAlphaCeilingEffect> {
                AlphaCeilingList() {
                }

                @Override
                public CTAlphaCeilingEffect get(int n) {
                    return CTBlipImpl.this.getAlphaCeilingArray(n);
                }

                @Override
                public CTAlphaCeilingEffect set(int n, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
                    CTAlphaCeilingEffect cTAlphaCeilingEffect2 = CTBlipImpl.this.getAlphaCeilingArray(n);
                    CTBlipImpl.this.setAlphaCeilingArray(n, cTAlphaCeilingEffect);
                    return cTAlphaCeilingEffect2;
                }

                @Override
                public void add(int n, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
                    CTBlipImpl.this.insertNewAlphaCeiling(n).set((XmlObject)cTAlphaCeilingEffect);
                }

                @Override
                public CTAlphaCeilingEffect remove(int n) {
                    CTAlphaCeilingEffect cTAlphaCeilingEffect = CTBlipImpl.this.getAlphaCeilingArray(n);
                    CTBlipImpl.this.removeAlphaCeiling(n);
                    return cTAlphaCeilingEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaCeilingArray();
                }
            }
            return new AlphaCeilingList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaCeilingEffect[] getAlphaCeilingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHACEILING$2, arrayList);
            CTAlphaCeilingEffect[] cTAlphaCeilingEffectArray = new CTAlphaCeilingEffect[arrayList.size()];
            arrayList.toArray(cTAlphaCeilingEffectArray);
            return cTAlphaCeilingEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaCeilingEffect getAlphaCeilingArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaCeilingEffect cTAlphaCeilingEffect = null;
            cTAlphaCeilingEffect = (CTAlphaCeilingEffect)this.get_store().find_element_user(ALPHACEILING$2, n);
            if (cTAlphaCeilingEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaCeilingEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaCeilingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHACEILING$2);
        }
    }

    @Override
    public void setAlphaCeilingArray(CTAlphaCeilingEffect[] cTAlphaCeilingEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaCeilingEffectArray, ALPHACEILING$2);
    }

    @Override
    public void setAlphaCeilingArray(int n, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaCeilingEffect, ALPHACEILING$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaCeilingEffect insertNewAlphaCeiling(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaCeilingEffect cTAlphaCeilingEffect = null;
            cTAlphaCeilingEffect = (CTAlphaCeilingEffect)this.get_store().insert_element_user(ALPHACEILING$2, n);
            return cTAlphaCeilingEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaCeilingEffect addNewAlphaCeiling() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaCeilingEffect cTAlphaCeilingEffect = null;
            cTAlphaCeilingEffect = (CTAlphaCeilingEffect)this.get_store().add_element_user(ALPHACEILING$2);
            return cTAlphaCeilingEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaCeiling(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHACEILING$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaFloorEffect> getAlphaFloorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaFloorList
            extends AbstractList<CTAlphaFloorEffect> {
                AlphaFloorList() {
                }

                @Override
                public CTAlphaFloorEffect get(int n) {
                    return CTBlipImpl.this.getAlphaFloorArray(n);
                }

                @Override
                public CTAlphaFloorEffect set(int n, CTAlphaFloorEffect cTAlphaFloorEffect) {
                    CTAlphaFloorEffect cTAlphaFloorEffect2 = CTBlipImpl.this.getAlphaFloorArray(n);
                    CTBlipImpl.this.setAlphaFloorArray(n, cTAlphaFloorEffect);
                    return cTAlphaFloorEffect2;
                }

                @Override
                public void add(int n, CTAlphaFloorEffect cTAlphaFloorEffect) {
                    CTBlipImpl.this.insertNewAlphaFloor(n).set((XmlObject)cTAlphaFloorEffect);
                }

                @Override
                public CTAlphaFloorEffect remove(int n) {
                    CTAlphaFloorEffect cTAlphaFloorEffect = CTBlipImpl.this.getAlphaFloorArray(n);
                    CTBlipImpl.this.removeAlphaFloor(n);
                    return cTAlphaFloorEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaFloorArray();
                }
            }
            return new AlphaFloorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaFloorEffect[] getAlphaFloorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAFLOOR$4, arrayList);
            CTAlphaFloorEffect[] cTAlphaFloorEffectArray = new CTAlphaFloorEffect[arrayList.size()];
            arrayList.toArray(cTAlphaFloorEffectArray);
            return cTAlphaFloorEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaFloorEffect getAlphaFloorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaFloorEffect cTAlphaFloorEffect = null;
            cTAlphaFloorEffect = (CTAlphaFloorEffect)this.get_store().find_element_user(ALPHAFLOOR$4, n);
            if (cTAlphaFloorEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaFloorEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaFloorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAFLOOR$4);
        }
    }

    @Override
    public void setAlphaFloorArray(CTAlphaFloorEffect[] cTAlphaFloorEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaFloorEffectArray, ALPHAFLOOR$4);
    }

    @Override
    public void setAlphaFloorArray(int n, CTAlphaFloorEffect cTAlphaFloorEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaFloorEffect, ALPHAFLOOR$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaFloorEffect insertNewAlphaFloor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaFloorEffect cTAlphaFloorEffect = null;
            cTAlphaFloorEffect = (CTAlphaFloorEffect)this.get_store().insert_element_user(ALPHAFLOOR$4, n);
            return cTAlphaFloorEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaFloorEffect addNewAlphaFloor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaFloorEffect cTAlphaFloorEffect = null;
            cTAlphaFloorEffect = (CTAlphaFloorEffect)this.get_store().add_element_user(ALPHAFLOOR$4);
            return cTAlphaFloorEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaFloor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAFLOOR$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaInverseEffect> getAlphaInvList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaInvList
            extends AbstractList<CTAlphaInverseEffect> {
                AlphaInvList() {
                }

                @Override
                public CTAlphaInverseEffect get(int n) {
                    return CTBlipImpl.this.getAlphaInvArray(n);
                }

                @Override
                public CTAlphaInverseEffect set(int n, CTAlphaInverseEffect cTAlphaInverseEffect) {
                    CTAlphaInverseEffect cTAlphaInverseEffect2 = CTBlipImpl.this.getAlphaInvArray(n);
                    CTBlipImpl.this.setAlphaInvArray(n, cTAlphaInverseEffect);
                    return cTAlphaInverseEffect2;
                }

                @Override
                public void add(int n, CTAlphaInverseEffect cTAlphaInverseEffect) {
                    CTBlipImpl.this.insertNewAlphaInv(n).set((XmlObject)cTAlphaInverseEffect);
                }

                @Override
                public CTAlphaInverseEffect remove(int n) {
                    CTAlphaInverseEffect cTAlphaInverseEffect = CTBlipImpl.this.getAlphaInvArray(n);
                    CTBlipImpl.this.removeAlphaInv(n);
                    return cTAlphaInverseEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaInvArray();
                }
            }
            return new AlphaInvList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaInverseEffect[] getAlphaInvArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAINV$6, arrayList);
            CTAlphaInverseEffect[] cTAlphaInverseEffectArray = new CTAlphaInverseEffect[arrayList.size()];
            arrayList.toArray(cTAlphaInverseEffectArray);
            return cTAlphaInverseEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaInverseEffect getAlphaInvArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaInverseEffect cTAlphaInverseEffect = null;
            cTAlphaInverseEffect = (CTAlphaInverseEffect)this.get_store().find_element_user(ALPHAINV$6, n);
            if (cTAlphaInverseEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaInverseEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaInvArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAINV$6);
        }
    }

    @Override
    public void setAlphaInvArray(CTAlphaInverseEffect[] cTAlphaInverseEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaInverseEffectArray, ALPHAINV$6);
    }

    @Override
    public void setAlphaInvArray(int n, CTAlphaInverseEffect cTAlphaInverseEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaInverseEffect, ALPHAINV$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaInverseEffect insertNewAlphaInv(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaInverseEffect cTAlphaInverseEffect = null;
            cTAlphaInverseEffect = (CTAlphaInverseEffect)this.get_store().insert_element_user(ALPHAINV$6, n);
            return cTAlphaInverseEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaInverseEffect addNewAlphaInv() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaInverseEffect cTAlphaInverseEffect = null;
            cTAlphaInverseEffect = (CTAlphaInverseEffect)this.get_store().add_element_user(ALPHAINV$6);
            return cTAlphaInverseEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaInv(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAINV$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaModulateEffect> getAlphaModList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaModList
            extends AbstractList<CTAlphaModulateEffect> {
                AlphaModList() {
                }

                @Override
                public CTAlphaModulateEffect get(int n) {
                    return CTBlipImpl.this.getAlphaModArray(n);
                }

                @Override
                public CTAlphaModulateEffect set(int n, CTAlphaModulateEffect cTAlphaModulateEffect) {
                    CTAlphaModulateEffect cTAlphaModulateEffect2 = CTBlipImpl.this.getAlphaModArray(n);
                    CTBlipImpl.this.setAlphaModArray(n, cTAlphaModulateEffect);
                    return cTAlphaModulateEffect2;
                }

                @Override
                public void add(int n, CTAlphaModulateEffect cTAlphaModulateEffect) {
                    CTBlipImpl.this.insertNewAlphaMod(n).set((XmlObject)cTAlphaModulateEffect);
                }

                @Override
                public CTAlphaModulateEffect remove(int n) {
                    CTAlphaModulateEffect cTAlphaModulateEffect = CTBlipImpl.this.getAlphaModArray(n);
                    CTBlipImpl.this.removeAlphaMod(n);
                    return cTAlphaModulateEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaModArray();
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
    public CTAlphaModulateEffect[] getAlphaModArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAMOD$8, arrayList);
            CTAlphaModulateEffect[] cTAlphaModulateEffectArray = new CTAlphaModulateEffect[arrayList.size()];
            arrayList.toArray(cTAlphaModulateEffectArray);
            return cTAlphaModulateEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaModulateEffect getAlphaModArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaModulateEffect cTAlphaModulateEffect = null;
            cTAlphaModulateEffect = (CTAlphaModulateEffect)this.get_store().find_element_user(ALPHAMOD$8, n);
            if (cTAlphaModulateEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaModulateEffect;
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
            return this.get_store().count_elements(ALPHAMOD$8);
        }
    }

    @Override
    public void setAlphaModArray(CTAlphaModulateEffect[] cTAlphaModulateEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaModulateEffectArray, ALPHAMOD$8);
    }

    @Override
    public void setAlphaModArray(int n, CTAlphaModulateEffect cTAlphaModulateEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaModulateEffect, ALPHAMOD$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaModulateEffect insertNewAlphaMod(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaModulateEffect cTAlphaModulateEffect = null;
            cTAlphaModulateEffect = (CTAlphaModulateEffect)this.get_store().insert_element_user(ALPHAMOD$8, n);
            return cTAlphaModulateEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaModulateEffect addNewAlphaMod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaModulateEffect cTAlphaModulateEffect = null;
            cTAlphaModulateEffect = (CTAlphaModulateEffect)this.get_store().add_element_user(ALPHAMOD$8);
            return cTAlphaModulateEffect;
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
            this.get_store().remove_element(ALPHAMOD$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaModulateFixedEffect> getAlphaModFixList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaModFixList
            extends AbstractList<CTAlphaModulateFixedEffect> {
                AlphaModFixList() {
                }

                @Override
                public CTAlphaModulateFixedEffect get(int n) {
                    return CTBlipImpl.this.getAlphaModFixArray(n);
                }

                @Override
                public CTAlphaModulateFixedEffect set(int n, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
                    CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect2 = CTBlipImpl.this.getAlphaModFixArray(n);
                    CTBlipImpl.this.setAlphaModFixArray(n, cTAlphaModulateFixedEffect);
                    return cTAlphaModulateFixedEffect2;
                }

                @Override
                public void add(int n, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
                    CTBlipImpl.this.insertNewAlphaModFix(n).set(cTAlphaModulateFixedEffect);
                }

                @Override
                public CTAlphaModulateFixedEffect remove(int n) {
                    CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect = CTBlipImpl.this.getAlphaModFixArray(n);
                    CTBlipImpl.this.removeAlphaModFix(n);
                    return cTAlphaModulateFixedEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaModFixArray();
                }
            }
            return new AlphaModFixList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaModulateFixedEffect[] getAlphaModFixArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAMODFIX$10, arrayList);
            CTAlphaModulateFixedEffect[] cTAlphaModulateFixedEffectArray = new CTAlphaModulateFixedEffect[arrayList.size()];
            arrayList.toArray(cTAlphaModulateFixedEffectArray);
            return cTAlphaModulateFixedEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaModulateFixedEffect getAlphaModFixArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect = null;
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect)((Object)this.get_store().find_element_user(ALPHAMODFIX$10, n));
            if (cTAlphaModulateFixedEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaModulateFixedEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaModFixArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAMODFIX$10);
        }
    }

    @Override
    public void setAlphaModFixArray(CTAlphaModulateFixedEffect[] cTAlphaModulateFixedEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAlphaModulateFixedEffectArray, ALPHAMODFIX$10);
    }

    @Override
    public void setAlphaModFixArray(int n, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
        this.generatedSetterHelperImpl(cTAlphaModulateFixedEffect, ALPHAMODFIX$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaModulateFixedEffect insertNewAlphaModFix(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect = null;
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect)((Object)this.get_store().insert_element_user(ALPHAMODFIX$10, n));
            return cTAlphaModulateFixedEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaModulateFixedEffect addNewAlphaModFix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect = null;
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect)((Object)this.get_store().add_element_user(ALPHAMODFIX$10));
            return cTAlphaModulateFixedEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaModFix(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAMODFIX$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaReplaceEffect> getAlphaReplList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaReplList
            extends AbstractList<CTAlphaReplaceEffect> {
                AlphaReplList() {
                }

                @Override
                public CTAlphaReplaceEffect get(int n) {
                    return CTBlipImpl.this.getAlphaReplArray(n);
                }

                @Override
                public CTAlphaReplaceEffect set(int n, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
                    CTAlphaReplaceEffect cTAlphaReplaceEffect2 = CTBlipImpl.this.getAlphaReplArray(n);
                    CTBlipImpl.this.setAlphaReplArray(n, cTAlphaReplaceEffect);
                    return cTAlphaReplaceEffect2;
                }

                @Override
                public void add(int n, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
                    CTBlipImpl.this.insertNewAlphaRepl(n).set((XmlObject)cTAlphaReplaceEffect);
                }

                @Override
                public CTAlphaReplaceEffect remove(int n) {
                    CTAlphaReplaceEffect cTAlphaReplaceEffect = CTBlipImpl.this.getAlphaReplArray(n);
                    CTBlipImpl.this.removeAlphaRepl(n);
                    return cTAlphaReplaceEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfAlphaReplArray();
                }
            }
            return new AlphaReplList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaReplaceEffect[] getAlphaReplArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAREPL$12, arrayList);
            CTAlphaReplaceEffect[] cTAlphaReplaceEffectArray = new CTAlphaReplaceEffect[arrayList.size()];
            arrayList.toArray(cTAlphaReplaceEffectArray);
            return cTAlphaReplaceEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaReplaceEffect getAlphaReplArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaReplaceEffect cTAlphaReplaceEffect = null;
            cTAlphaReplaceEffect = (CTAlphaReplaceEffect)this.get_store().find_element_user(ALPHAREPL$12, n);
            if (cTAlphaReplaceEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaReplaceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaReplArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAREPL$12);
        }
    }

    @Override
    public void setAlphaReplArray(CTAlphaReplaceEffect[] cTAlphaReplaceEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaReplaceEffectArray, ALPHAREPL$12);
    }

    @Override
    public void setAlphaReplArray(int n, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaReplaceEffect, ALPHAREPL$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaReplaceEffect insertNewAlphaRepl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaReplaceEffect cTAlphaReplaceEffect = null;
            cTAlphaReplaceEffect = (CTAlphaReplaceEffect)this.get_store().insert_element_user(ALPHAREPL$12, n);
            return cTAlphaReplaceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaReplaceEffect addNewAlphaRepl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaReplaceEffect cTAlphaReplaceEffect = null;
            cTAlphaReplaceEffect = (CTAlphaReplaceEffect)this.get_store().add_element_user(ALPHAREPL$12);
            return cTAlphaReplaceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaRepl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAREPL$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBiLevelEffect> getBiLevelList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BiLevelList
            extends AbstractList<CTBiLevelEffect> {
                BiLevelList() {
                }

                @Override
                public CTBiLevelEffect get(int n) {
                    return CTBlipImpl.this.getBiLevelArray(n);
                }

                @Override
                public CTBiLevelEffect set(int n, CTBiLevelEffect cTBiLevelEffect) {
                    CTBiLevelEffect cTBiLevelEffect2 = CTBlipImpl.this.getBiLevelArray(n);
                    CTBlipImpl.this.setBiLevelArray(n, cTBiLevelEffect);
                    return cTBiLevelEffect2;
                }

                @Override
                public void add(int n, CTBiLevelEffect cTBiLevelEffect) {
                    CTBlipImpl.this.insertNewBiLevel(n).set((XmlObject)cTBiLevelEffect);
                }

                @Override
                public CTBiLevelEffect remove(int n) {
                    CTBiLevelEffect cTBiLevelEffect = CTBlipImpl.this.getBiLevelArray(n);
                    CTBlipImpl.this.removeBiLevel(n);
                    return cTBiLevelEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfBiLevelArray();
                }
            }
            return new BiLevelList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBiLevelEffect[] getBiLevelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BILEVEL$14, arrayList);
            CTBiLevelEffect[] cTBiLevelEffectArray = new CTBiLevelEffect[arrayList.size()];
            arrayList.toArray(cTBiLevelEffectArray);
            return cTBiLevelEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBiLevelEffect getBiLevelArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBiLevelEffect cTBiLevelEffect = null;
            cTBiLevelEffect = (CTBiLevelEffect)this.get_store().find_element_user(BILEVEL$14, n);
            if (cTBiLevelEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBiLevelEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBiLevelArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BILEVEL$14);
        }
    }

    @Override
    public void setBiLevelArray(CTBiLevelEffect[] cTBiLevelEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBiLevelEffectArray, BILEVEL$14);
    }

    @Override
    public void setBiLevelArray(int n, CTBiLevelEffect cTBiLevelEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTBiLevelEffect, BILEVEL$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBiLevelEffect insertNewBiLevel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBiLevelEffect cTBiLevelEffect = null;
            cTBiLevelEffect = (CTBiLevelEffect)this.get_store().insert_element_user(BILEVEL$14, n);
            return cTBiLevelEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBiLevelEffect addNewBiLevel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBiLevelEffect cTBiLevelEffect = null;
            cTBiLevelEffect = (CTBiLevelEffect)this.get_store().add_element_user(BILEVEL$14);
            return cTBiLevelEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBiLevel(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BILEVEL$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBlurEffect> getBlurList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BlurList
            extends AbstractList<CTBlurEffect> {
                BlurList() {
                }

                @Override
                public CTBlurEffect get(int n) {
                    return CTBlipImpl.this.getBlurArray(n);
                }

                @Override
                public CTBlurEffect set(int n, CTBlurEffect cTBlurEffect) {
                    CTBlurEffect cTBlurEffect2 = CTBlipImpl.this.getBlurArray(n);
                    CTBlipImpl.this.setBlurArray(n, cTBlurEffect);
                    return cTBlurEffect2;
                }

                @Override
                public void add(int n, CTBlurEffect cTBlurEffect) {
                    CTBlipImpl.this.insertNewBlur(n).set((XmlObject)cTBlurEffect);
                }

                @Override
                public CTBlurEffect remove(int n) {
                    CTBlurEffect cTBlurEffect = CTBlipImpl.this.getBlurArray(n);
                    CTBlipImpl.this.removeBlur(n);
                    return cTBlurEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfBlurArray();
                }
            }
            return new BlurList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBlurEffect[] getBlurArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BLUR$16, arrayList);
            CTBlurEffect[] cTBlurEffectArray = new CTBlurEffect[arrayList.size()];
            arrayList.toArray(cTBlurEffectArray);
            return cTBlurEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlurEffect getBlurArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlurEffect cTBlurEffect = null;
            cTBlurEffect = (CTBlurEffect)this.get_store().find_element_user(BLUR$16, n);
            if (cTBlurEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBlurEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBlurArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLUR$16);
        }
    }

    @Override
    public void setBlurArray(CTBlurEffect[] cTBlurEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBlurEffectArray, BLUR$16);
    }

    @Override
    public void setBlurArray(int n, CTBlurEffect cTBlurEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTBlurEffect, BLUR$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlurEffect insertNewBlur(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlurEffect cTBlurEffect = null;
            cTBlurEffect = (CTBlurEffect)this.get_store().insert_element_user(BLUR$16, n);
            return cTBlurEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlurEffect addNewBlur() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlurEffect cTBlurEffect = null;
            cTBlurEffect = (CTBlurEffect)this.get_store().add_element_user(BLUR$16);
            return cTBlurEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBlur(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLUR$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTColorChangeEffect> getClrChangeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClrChangeList
            extends AbstractList<CTColorChangeEffect> {
                ClrChangeList() {
                }

                @Override
                public CTColorChangeEffect get(int n) {
                    return CTBlipImpl.this.getClrChangeArray(n);
                }

                @Override
                public CTColorChangeEffect set(int n, CTColorChangeEffect cTColorChangeEffect) {
                    CTColorChangeEffect cTColorChangeEffect2 = CTBlipImpl.this.getClrChangeArray(n);
                    CTBlipImpl.this.setClrChangeArray(n, cTColorChangeEffect);
                    return cTColorChangeEffect2;
                }

                @Override
                public void add(int n, CTColorChangeEffect cTColorChangeEffect) {
                    CTBlipImpl.this.insertNewClrChange(n).set((XmlObject)cTColorChangeEffect);
                }

                @Override
                public CTColorChangeEffect remove(int n) {
                    CTColorChangeEffect cTColorChangeEffect = CTBlipImpl.this.getClrChangeArray(n);
                    CTBlipImpl.this.removeClrChange(n);
                    return cTColorChangeEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfClrChangeArray();
                }
            }
            return new ClrChangeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTColorChangeEffect[] getClrChangeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLRCHANGE$18, arrayList);
            CTColorChangeEffect[] cTColorChangeEffectArray = new CTColorChangeEffect[arrayList.size()];
            arrayList.toArray(cTColorChangeEffectArray);
            return cTColorChangeEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorChangeEffect getClrChangeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorChangeEffect cTColorChangeEffect = null;
            cTColorChangeEffect = (CTColorChangeEffect)this.get_store().find_element_user(CLRCHANGE$18, n);
            if (cTColorChangeEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTColorChangeEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfClrChangeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLRCHANGE$18);
        }
    }

    @Override
    public void setClrChangeArray(CTColorChangeEffect[] cTColorChangeEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTColorChangeEffectArray, CLRCHANGE$18);
    }

    @Override
    public void setClrChangeArray(int n, CTColorChangeEffect cTColorChangeEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTColorChangeEffect, CLRCHANGE$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorChangeEffect insertNewClrChange(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorChangeEffect cTColorChangeEffect = null;
            cTColorChangeEffect = (CTColorChangeEffect)this.get_store().insert_element_user(CLRCHANGE$18, n);
            return cTColorChangeEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorChangeEffect addNewClrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorChangeEffect cTColorChangeEffect = null;
            cTColorChangeEffect = (CTColorChangeEffect)this.get_store().add_element_user(CLRCHANGE$18);
            return cTColorChangeEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClrChange(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLRCHANGE$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTColorReplaceEffect> getClrReplList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ClrReplList
            extends AbstractList<CTColorReplaceEffect> {
                ClrReplList() {
                }

                @Override
                public CTColorReplaceEffect get(int n) {
                    return CTBlipImpl.this.getClrReplArray(n);
                }

                @Override
                public CTColorReplaceEffect set(int n, CTColorReplaceEffect cTColorReplaceEffect) {
                    CTColorReplaceEffect cTColorReplaceEffect2 = CTBlipImpl.this.getClrReplArray(n);
                    CTBlipImpl.this.setClrReplArray(n, cTColorReplaceEffect);
                    return cTColorReplaceEffect2;
                }

                @Override
                public void add(int n, CTColorReplaceEffect cTColorReplaceEffect) {
                    CTBlipImpl.this.insertNewClrRepl(n).set((XmlObject)cTColorReplaceEffect);
                }

                @Override
                public CTColorReplaceEffect remove(int n) {
                    CTColorReplaceEffect cTColorReplaceEffect = CTBlipImpl.this.getClrReplArray(n);
                    CTBlipImpl.this.removeClrRepl(n);
                    return cTColorReplaceEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfClrReplArray();
                }
            }
            return new ClrReplList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTColorReplaceEffect[] getClrReplArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CLRREPL$20, arrayList);
            CTColorReplaceEffect[] cTColorReplaceEffectArray = new CTColorReplaceEffect[arrayList.size()];
            arrayList.toArray(cTColorReplaceEffectArray);
            return cTColorReplaceEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorReplaceEffect getClrReplArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorReplaceEffect cTColorReplaceEffect = null;
            cTColorReplaceEffect = (CTColorReplaceEffect)this.get_store().find_element_user(CLRREPL$20, n);
            if (cTColorReplaceEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTColorReplaceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfClrReplArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLRREPL$20);
        }
    }

    @Override
    public void setClrReplArray(CTColorReplaceEffect[] cTColorReplaceEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTColorReplaceEffectArray, CLRREPL$20);
    }

    @Override
    public void setClrReplArray(int n, CTColorReplaceEffect cTColorReplaceEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTColorReplaceEffect, CLRREPL$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorReplaceEffect insertNewClrRepl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorReplaceEffect cTColorReplaceEffect = null;
            cTColorReplaceEffect = (CTColorReplaceEffect)this.get_store().insert_element_user(CLRREPL$20, n);
            return cTColorReplaceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorReplaceEffect addNewClrRepl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorReplaceEffect cTColorReplaceEffect = null;
            cTColorReplaceEffect = (CTColorReplaceEffect)this.get_store().add_element_user(CLRREPL$20);
            return cTColorReplaceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeClrRepl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLRREPL$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDuotoneEffect> getDuotoneList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DuotoneList
            extends AbstractList<CTDuotoneEffect> {
                DuotoneList() {
                }

                @Override
                public CTDuotoneEffect get(int n) {
                    return CTBlipImpl.this.getDuotoneArray(n);
                }

                @Override
                public CTDuotoneEffect set(int n, CTDuotoneEffect cTDuotoneEffect) {
                    CTDuotoneEffect cTDuotoneEffect2 = CTBlipImpl.this.getDuotoneArray(n);
                    CTBlipImpl.this.setDuotoneArray(n, cTDuotoneEffect);
                    return cTDuotoneEffect2;
                }

                @Override
                public void add(int n, CTDuotoneEffect cTDuotoneEffect) {
                    CTBlipImpl.this.insertNewDuotone(n).set(cTDuotoneEffect);
                }

                @Override
                public CTDuotoneEffect remove(int n) {
                    CTDuotoneEffect cTDuotoneEffect = CTBlipImpl.this.getDuotoneArray(n);
                    CTBlipImpl.this.removeDuotone(n);
                    return cTDuotoneEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfDuotoneArray();
                }
            }
            return new DuotoneList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDuotoneEffect[] getDuotoneArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DUOTONE$22, arrayList);
            CTDuotoneEffect[] cTDuotoneEffectArray = new CTDuotoneEffect[arrayList.size()];
            arrayList.toArray(cTDuotoneEffectArray);
            return cTDuotoneEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDuotoneEffect getDuotoneArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDuotoneEffect cTDuotoneEffect = null;
            cTDuotoneEffect = (CTDuotoneEffect)((Object)this.get_store().find_element_user(DUOTONE$22, n));
            if (cTDuotoneEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDuotoneEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDuotoneArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DUOTONE$22);
        }
    }

    @Override
    public void setDuotoneArray(CTDuotoneEffect[] cTDuotoneEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDuotoneEffectArray, DUOTONE$22);
    }

    @Override
    public void setDuotoneArray(int n, CTDuotoneEffect cTDuotoneEffect) {
        this.generatedSetterHelperImpl(cTDuotoneEffect, DUOTONE$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDuotoneEffect insertNewDuotone(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDuotoneEffect cTDuotoneEffect = null;
            cTDuotoneEffect = (CTDuotoneEffect)((Object)this.get_store().insert_element_user(DUOTONE$22, n));
            return cTDuotoneEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDuotoneEffect addNewDuotone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDuotoneEffect cTDuotoneEffect = null;
            cTDuotoneEffect = (CTDuotoneEffect)((Object)this.get_store().add_element_user(DUOTONE$22));
            return cTDuotoneEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDuotone(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DUOTONE$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFillOverlayEffect> getFillOverlayList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FillOverlayList
            extends AbstractList<CTFillOverlayEffect> {
                FillOverlayList() {
                }

                @Override
                public CTFillOverlayEffect get(int n) {
                    return CTBlipImpl.this.getFillOverlayArray(n);
                }

                @Override
                public CTFillOverlayEffect set(int n, CTFillOverlayEffect cTFillOverlayEffect) {
                    CTFillOverlayEffect cTFillOverlayEffect2 = CTBlipImpl.this.getFillOverlayArray(n);
                    CTBlipImpl.this.setFillOverlayArray(n, cTFillOverlayEffect);
                    return cTFillOverlayEffect2;
                }

                @Override
                public void add(int n, CTFillOverlayEffect cTFillOverlayEffect) {
                    CTBlipImpl.this.insertNewFillOverlay(n).set((XmlObject)cTFillOverlayEffect);
                }

                @Override
                public CTFillOverlayEffect remove(int n) {
                    CTFillOverlayEffect cTFillOverlayEffect = CTBlipImpl.this.getFillOverlayArray(n);
                    CTBlipImpl.this.removeFillOverlay(n);
                    return cTFillOverlayEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfFillOverlayArray();
                }
            }
            return new FillOverlayList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFillOverlayEffect[] getFillOverlayArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILLOVERLAY$24, arrayList);
            CTFillOverlayEffect[] cTFillOverlayEffectArray = new CTFillOverlayEffect[arrayList.size()];
            arrayList.toArray(cTFillOverlayEffectArray);
            return cTFillOverlayEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillOverlayEffect getFillOverlayArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillOverlayEffect cTFillOverlayEffect = null;
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().find_element_user(FILLOVERLAY$24, n);
            if (cTFillOverlayEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFillOverlayEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFillOverlayArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILLOVERLAY$24);
        }
    }

    @Override
    public void setFillOverlayArray(CTFillOverlayEffect[] cTFillOverlayEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFillOverlayEffectArray, FILLOVERLAY$24);
    }

    @Override
    public void setFillOverlayArray(int n, CTFillOverlayEffect cTFillOverlayEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTFillOverlayEffect, FILLOVERLAY$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillOverlayEffect insertNewFillOverlay(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillOverlayEffect cTFillOverlayEffect = null;
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().insert_element_user(FILLOVERLAY$24, n);
            return cTFillOverlayEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillOverlayEffect addNewFillOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillOverlayEffect cTFillOverlayEffect = null;
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().add_element_user(FILLOVERLAY$24);
            return cTFillOverlayEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFillOverlay(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILLOVERLAY$24, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGrayscaleEffect> getGraysclList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GraysclList
            extends AbstractList<CTGrayscaleEffect> {
                GraysclList() {
                }

                @Override
                public CTGrayscaleEffect get(int n) {
                    return CTBlipImpl.this.getGraysclArray(n);
                }

                @Override
                public CTGrayscaleEffect set(int n, CTGrayscaleEffect cTGrayscaleEffect) {
                    CTGrayscaleEffect cTGrayscaleEffect2 = CTBlipImpl.this.getGraysclArray(n);
                    CTBlipImpl.this.setGraysclArray(n, cTGrayscaleEffect);
                    return cTGrayscaleEffect2;
                }

                @Override
                public void add(int n, CTGrayscaleEffect cTGrayscaleEffect) {
                    CTBlipImpl.this.insertNewGrayscl(n).set((XmlObject)cTGrayscaleEffect);
                }

                @Override
                public CTGrayscaleEffect remove(int n) {
                    CTGrayscaleEffect cTGrayscaleEffect = CTBlipImpl.this.getGraysclArray(n);
                    CTBlipImpl.this.removeGrayscl(n);
                    return cTGrayscaleEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfGraysclArray();
                }
            }
            return new GraysclList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGrayscaleEffect[] getGraysclArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRAYSCL$26, arrayList);
            CTGrayscaleEffect[] cTGrayscaleEffectArray = new CTGrayscaleEffect[arrayList.size()];
            arrayList.toArray(cTGrayscaleEffectArray);
            return cTGrayscaleEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrayscaleEffect getGraysclArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrayscaleEffect cTGrayscaleEffect = null;
            cTGrayscaleEffect = (CTGrayscaleEffect)this.get_store().find_element_user(GRAYSCL$26, n);
            if (cTGrayscaleEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGrayscaleEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGraysclArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRAYSCL$26);
        }
    }

    @Override
    public void setGraysclArray(CTGrayscaleEffect[] cTGrayscaleEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTGrayscaleEffectArray, GRAYSCL$26);
    }

    @Override
    public void setGraysclArray(int n, CTGrayscaleEffect cTGrayscaleEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTGrayscaleEffect, GRAYSCL$26, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrayscaleEffect insertNewGrayscl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrayscaleEffect cTGrayscaleEffect = null;
            cTGrayscaleEffect = (CTGrayscaleEffect)this.get_store().insert_element_user(GRAYSCL$26, n);
            return cTGrayscaleEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGrayscaleEffect addNewGrayscl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGrayscaleEffect cTGrayscaleEffect = null;
            cTGrayscaleEffect = (CTGrayscaleEffect)this.get_store().add_element_user(GRAYSCL$26);
            return cTGrayscaleEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGrayscl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRAYSCL$26, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTHSLEffect> getHslList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HslList
            extends AbstractList<CTHSLEffect> {
                HslList() {
                }

                @Override
                public CTHSLEffect get(int n) {
                    return CTBlipImpl.this.getHslArray(n);
                }

                @Override
                public CTHSLEffect set(int n, CTHSLEffect cTHSLEffect) {
                    CTHSLEffect cTHSLEffect2 = CTBlipImpl.this.getHslArray(n);
                    CTBlipImpl.this.setHslArray(n, cTHSLEffect);
                    return cTHSLEffect2;
                }

                @Override
                public void add(int n, CTHSLEffect cTHSLEffect) {
                    CTBlipImpl.this.insertNewHsl(n).set((XmlObject)cTHSLEffect);
                }

                @Override
                public CTHSLEffect remove(int n) {
                    CTHSLEffect cTHSLEffect = CTBlipImpl.this.getHslArray(n);
                    CTBlipImpl.this.removeHsl(n);
                    return cTHSLEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfHslArray();
                }
            }
            return new HslList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTHSLEffect[] getHslArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HSL$28, arrayList);
            CTHSLEffect[] cTHSLEffectArray = new CTHSLEffect[arrayList.size()];
            arrayList.toArray(cTHSLEffectArray);
            return cTHSLEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHSLEffect getHslArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHSLEffect cTHSLEffect = null;
            cTHSLEffect = (CTHSLEffect)this.get_store().find_element_user(HSL$28, n);
            if (cTHSLEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTHSLEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHslArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HSL$28);
        }
    }

    @Override
    public void setHslArray(CTHSLEffect[] cTHSLEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTHSLEffectArray, HSL$28);
    }

    @Override
    public void setHslArray(int n, CTHSLEffect cTHSLEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTHSLEffect, HSL$28, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHSLEffect insertNewHsl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHSLEffect cTHSLEffect = null;
            cTHSLEffect = (CTHSLEffect)this.get_store().insert_element_user(HSL$28, n);
            return cTHSLEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHSLEffect addNewHsl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHSLEffect cTHSLEffect = null;
            cTHSLEffect = (CTHSLEffect)this.get_store().add_element_user(HSL$28);
            return cTHSLEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHsl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HSL$28, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTLuminanceEffect> getLumList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class LumList
            extends AbstractList<CTLuminanceEffect> {
                LumList() {
                }

                @Override
                public CTLuminanceEffect get(int n) {
                    return CTBlipImpl.this.getLumArray(n);
                }

                @Override
                public CTLuminanceEffect set(int n, CTLuminanceEffect cTLuminanceEffect) {
                    CTLuminanceEffect cTLuminanceEffect2 = CTBlipImpl.this.getLumArray(n);
                    CTBlipImpl.this.setLumArray(n, cTLuminanceEffect);
                    return cTLuminanceEffect2;
                }

                @Override
                public void add(int n, CTLuminanceEffect cTLuminanceEffect) {
                    CTBlipImpl.this.insertNewLum(n).set((XmlObject)cTLuminanceEffect);
                }

                @Override
                public CTLuminanceEffect remove(int n) {
                    CTLuminanceEffect cTLuminanceEffect = CTBlipImpl.this.getLumArray(n);
                    CTBlipImpl.this.removeLum(n);
                    return cTLuminanceEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfLumArray();
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
    public CTLuminanceEffect[] getLumArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(LUM$30, arrayList);
            CTLuminanceEffect[] cTLuminanceEffectArray = new CTLuminanceEffect[arrayList.size()];
            arrayList.toArray(cTLuminanceEffectArray);
            return cTLuminanceEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLuminanceEffect getLumArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLuminanceEffect cTLuminanceEffect = null;
            cTLuminanceEffect = (CTLuminanceEffect)this.get_store().find_element_user(LUM$30, n);
            if (cTLuminanceEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTLuminanceEffect;
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
            return this.get_store().count_elements(LUM$30);
        }
    }

    @Override
    public void setLumArray(CTLuminanceEffect[] cTLuminanceEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTLuminanceEffectArray, LUM$30);
    }

    @Override
    public void setLumArray(int n, CTLuminanceEffect cTLuminanceEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTLuminanceEffect, LUM$30, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLuminanceEffect insertNewLum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLuminanceEffect cTLuminanceEffect = null;
            cTLuminanceEffect = (CTLuminanceEffect)this.get_store().insert_element_user(LUM$30, n);
            return cTLuminanceEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLuminanceEffect addNewLum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLuminanceEffect cTLuminanceEffect = null;
            cTLuminanceEffect = (CTLuminanceEffect)this.get_store().add_element_user(LUM$30);
            return cTLuminanceEffect;
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
            this.get_store().remove_element(LUM$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTintEffect> getTintList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TintList
            extends AbstractList<CTTintEffect> {
                TintList() {
                }

                @Override
                public CTTintEffect get(int n) {
                    return CTBlipImpl.this.getTintArray(n);
                }

                @Override
                public CTTintEffect set(int n, CTTintEffect cTTintEffect) {
                    CTTintEffect cTTintEffect2 = CTBlipImpl.this.getTintArray(n);
                    CTBlipImpl.this.setTintArray(n, cTTintEffect);
                    return cTTintEffect2;
                }

                @Override
                public void add(int n, CTTintEffect cTTintEffect) {
                    CTBlipImpl.this.insertNewTint(n).set((XmlObject)cTTintEffect);
                }

                @Override
                public CTTintEffect remove(int n) {
                    CTTintEffect cTTintEffect = CTBlipImpl.this.getTintArray(n);
                    CTBlipImpl.this.removeTint(n);
                    return cTTintEffect;
                }

                @Override
                public int size() {
                    return CTBlipImpl.this.sizeOfTintArray();
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
    public CTTintEffect[] getTintArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TINT$32, arrayList);
            CTTintEffect[] cTTintEffectArray = new CTTintEffect[arrayList.size()];
            arrayList.toArray(cTTintEffectArray);
            return cTTintEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTintEffect getTintArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTintEffect cTTintEffect = null;
            cTTintEffect = (CTTintEffect)this.get_store().find_element_user(TINT$32, n);
            if (cTTintEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTintEffect;
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
            return this.get_store().count_elements(TINT$32);
        }
    }

    @Override
    public void setTintArray(CTTintEffect[] cTTintEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTTintEffectArray, TINT$32);
    }

    @Override
    public void setTintArray(int n, CTTintEffect cTTintEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTTintEffect, TINT$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTintEffect insertNewTint(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTintEffect cTTintEffect = null;
            cTTintEffect = (CTTintEffect)this.get_store().insert_element_user(TINT$32, n);
            return cTTintEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTintEffect addNewTint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTintEffect cTTintEffect = null;
            cTTintEffect = (CTTintEffect)this.get_store().add_element_user(TINT$32);
            return cTTintEffect;
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
            this.get_store().remove_element(TINT$32, n);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$34, 0));
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
            return this.get_store().count_elements(EXTLST$34) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$34, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$34));
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
            this.get_store().remove_element(EXTLST$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getEmbed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EMBED$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(EMBED$36));
            }
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
    public STRelationshipId xgetEmbed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId = null;
            sTRelationshipId = (STRelationshipId)((Object)this.get_store().find_attribute_user(EMBED$36));
            if (sTRelationshipId == null) {
                sTRelationshipId = (STRelationshipId)this.get_default_attribute_value(EMBED$36);
            }
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEmbed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EMBED$36) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEmbed(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EMBED$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EMBED$36));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEmbed(STRelationshipId sTRelationshipId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId2 = null;
            sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().find_attribute_user(EMBED$36));
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().add_attribute_user(EMBED$36));
            }
            sTRelationshipId2.set(sTRelationshipId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmbed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EMBED$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINK$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(LINK$38));
            }
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
    public STRelationshipId xgetLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId = null;
            sTRelationshipId = (STRelationshipId)((Object)this.get_store().find_attribute_user(LINK$38));
            if (sTRelationshipId == null) {
                sTRelationshipId = (STRelationshipId)this.get_default_attribute_value(LINK$38);
            }
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LINK$38) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLink(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINK$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LINK$38));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLink(STRelationshipId sTRelationshipId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId2 = null;
            sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().find_attribute_user(LINK$38));
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().add_attribute_user(LINK$38));
            }
            sTRelationshipId2.set(sTRelationshipId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LINK$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlipCompression.Enum getCstate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CSTATE$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(CSTATE$40));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STBlipCompression.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBlipCompression xgetCstate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlipCompression sTBlipCompression = null;
            sTBlipCompression = (STBlipCompression)this.get_store().find_attribute_user(CSTATE$40);
            if (sTBlipCompression == null) {
                sTBlipCompression = (STBlipCompression)this.get_default_attribute_value(CSTATE$40);
            }
            return sTBlipCompression;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCstate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CSTATE$40) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCstate(STBlipCompression.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CSTATE$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CSTATE$40));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCstate(STBlipCompression sTBlipCompression) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBlipCompression sTBlipCompression2 = null;
            sTBlipCompression2 = (STBlipCompression)this.get_store().find_attribute_user(CSTATE$40);
            if (sTBlipCompression2 == null) {
                sTBlipCompression2 = (STBlipCompression)this.get_store().add_attribute_user(CSTATE$40);
            }
            sTBlipCompression2.set((XmlObject)sTBlipCompression);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCstate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CSTATE$40);
        }
    }
}

