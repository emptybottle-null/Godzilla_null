/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaOutsetEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBlendEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTEffectReference
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFillEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeOffsetEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaOutsetEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlendEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeOffsetEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType;

public class CTEffectContainerImpl
extends XmlComplexContentImpl
implements CTEffectContainer {
    private static final long serialVersionUID = 1L;
    private static final QName CONT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cont");
    private static final QName EFFECT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effect");
    private static final QName ALPHABILEVEL$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaBiLevel");
    private static final QName ALPHACEILING$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaCeiling");
    private static final QName ALPHAFLOOR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaFloor");
    private static final QName ALPHAINV$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaInv");
    private static final QName ALPHAMOD$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaMod");
    private static final QName ALPHAMODFIX$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaModFix");
    private static final QName ALPHAOUTSET$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaOutset");
    private static final QName ALPHAREPL$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "alphaRepl");
    private static final QName BILEVEL$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "biLevel");
    private static final QName BLEND$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blend");
    private static final QName BLUR$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blur");
    private static final QName CLRCHANGE$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "clrChange");
    private static final QName CLRREPL$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "clrRepl");
    private static final QName DUOTONE$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "duotone");
    private static final QName FILL$32 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fill");
    private static final QName FILLOVERLAY$34 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillOverlay");
    private static final QName GLOW$36 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "glow");
    private static final QName GRAYSCL$38 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "grayscl");
    private static final QName HSL$40 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "hsl");
    private static final QName INNERSHDW$42 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "innerShdw");
    private static final QName LUM$44 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lum");
    private static final QName OUTERSHDW$46 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "outerShdw");
    private static final QName PRSTSHDW$48 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstShdw");
    private static final QName REFLECTION$50 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "reflection");
    private static final QName RELOFF$52 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "relOff");
    private static final QName SOFTEDGE$54 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "softEdge");
    private static final QName TINT$56 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tint");
    private static final QName XFRM$58 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "xfrm");
    private static final QName TYPE$60 = new QName("", "type");
    private static final QName NAME$62 = new QName("", "name");

    public CTEffectContainerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEffectContainer> getContList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ContList
            extends AbstractList<CTEffectContainer> {
                ContList() {
                }

                @Override
                public CTEffectContainer get(int n) {
                    return CTEffectContainerImpl.this.getContArray(n);
                }

                @Override
                public CTEffectContainer set(int n, CTEffectContainer cTEffectContainer) {
                    CTEffectContainer cTEffectContainer2 = CTEffectContainerImpl.this.getContArray(n);
                    CTEffectContainerImpl.this.setContArray(n, cTEffectContainer);
                    return cTEffectContainer2;
                }

                @Override
                public void add(int n, CTEffectContainer cTEffectContainer) {
                    CTEffectContainerImpl.this.insertNewCont(n).set(cTEffectContainer);
                }

                @Override
                public CTEffectContainer remove(int n) {
                    CTEffectContainer cTEffectContainer = CTEffectContainerImpl.this.getContArray(n);
                    CTEffectContainerImpl.this.removeCont(n);
                    return cTEffectContainer;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfContArray();
                }
            }
            return new ContList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEffectContainer[] getContArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CONT$0, arrayList);
            CTEffectContainer[] cTEffectContainerArray = new CTEffectContainer[arrayList.size()];
            arrayList.toArray(cTEffectContainerArray);
            return cTEffectContainerArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer getContArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().find_element_user(CONT$0, n));
            if (cTEffectContainer == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfContArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONT$0);
        }
    }

    @Override
    public void setContArray(CTEffectContainer[] cTEffectContainerArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTEffectContainerArray, CONT$0);
    }

    @Override
    public void setContArray(int n, CTEffectContainer cTEffectContainer) {
        this.generatedSetterHelperImpl(cTEffectContainer, CONT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer insertNewCont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().insert_element_user(CONT$0, n));
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer addNewCont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().add_element_user(CONT$0));
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCont(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONT$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTEffectReference> getEffectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EffectList
            extends AbstractList<CTEffectReference> {
                EffectList() {
                }

                @Override
                public CTEffectReference get(int n) {
                    return CTEffectContainerImpl.this.getEffectArray(n);
                }

                @Override
                public CTEffectReference set(int n, CTEffectReference cTEffectReference) {
                    CTEffectReference cTEffectReference2 = CTEffectContainerImpl.this.getEffectArray(n);
                    CTEffectContainerImpl.this.setEffectArray(n, cTEffectReference);
                    return cTEffectReference2;
                }

                @Override
                public void add(int n, CTEffectReference cTEffectReference) {
                    CTEffectContainerImpl.this.insertNewEffect(n).set((XmlObject)cTEffectReference);
                }

                @Override
                public CTEffectReference remove(int n) {
                    CTEffectReference cTEffectReference = CTEffectContainerImpl.this.getEffectArray(n);
                    CTEffectContainerImpl.this.removeEffect(n);
                    return cTEffectReference;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfEffectArray();
                }
            }
            return new EffectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTEffectReference[] getEffectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EFFECT$2, arrayList);
            CTEffectReference[] cTEffectReferenceArray = new CTEffectReference[arrayList.size()];
            arrayList.toArray(cTEffectReferenceArray);
            return cTEffectReferenceArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectReference getEffectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectReference cTEffectReference = null;
            cTEffectReference = (CTEffectReference)this.get_store().find_element_user(EFFECT$2, n);
            if (cTEffectReference == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTEffectReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEffectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECT$2);
        }
    }

    @Override
    public void setEffectArray(CTEffectReference[] cTEffectReferenceArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTEffectReferenceArray, EFFECT$2);
    }

    @Override
    public void setEffectArray(int n, CTEffectReference cTEffectReference) {
        this.generatedSetterHelperImpl((XmlObject)cTEffectReference, EFFECT$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectReference insertNewEffect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectReference cTEffectReference = null;
            cTEffectReference = (CTEffectReference)this.get_store().insert_element_user(EFFECT$2, n);
            return cTEffectReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectReference addNewEffect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectReference cTEffectReference = null;
            cTEffectReference = (CTEffectReference)this.get_store().add_element_user(EFFECT$2);
            return cTEffectReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEffect(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECT$2, n);
        }
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
                    return CTEffectContainerImpl.this.getAlphaBiLevelArray(n);
                }

                @Override
                public CTAlphaBiLevelEffect set(int n, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
                    CTAlphaBiLevelEffect cTAlphaBiLevelEffect2 = CTEffectContainerImpl.this.getAlphaBiLevelArray(n);
                    CTEffectContainerImpl.this.setAlphaBiLevelArray(n, cTAlphaBiLevelEffect);
                    return cTAlphaBiLevelEffect2;
                }

                @Override
                public void add(int n, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaBiLevel(n).set((XmlObject)cTAlphaBiLevelEffect);
                }

                @Override
                public CTAlphaBiLevelEffect remove(int n) {
                    CTAlphaBiLevelEffect cTAlphaBiLevelEffect = CTEffectContainerImpl.this.getAlphaBiLevelArray(n);
                    CTEffectContainerImpl.this.removeAlphaBiLevel(n);
                    return cTAlphaBiLevelEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaBiLevelArray();
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
            this.get_store().find_all_element_users(ALPHABILEVEL$4, arrayList);
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
            cTAlphaBiLevelEffect = (CTAlphaBiLevelEffect)this.get_store().find_element_user(ALPHABILEVEL$4, n);
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
            return this.get_store().count_elements(ALPHABILEVEL$4);
        }
    }

    @Override
    public void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] cTAlphaBiLevelEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaBiLevelEffectArray, ALPHABILEVEL$4);
    }

    @Override
    public void setAlphaBiLevelArray(int n, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaBiLevelEffect, ALPHABILEVEL$4, n, (short)2);
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
            cTAlphaBiLevelEffect = (CTAlphaBiLevelEffect)this.get_store().insert_element_user(ALPHABILEVEL$4, n);
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
            cTAlphaBiLevelEffect = (CTAlphaBiLevelEffect)this.get_store().add_element_user(ALPHABILEVEL$4);
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
            this.get_store().remove_element(ALPHABILEVEL$4, n);
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
                    return CTEffectContainerImpl.this.getAlphaCeilingArray(n);
                }

                @Override
                public CTAlphaCeilingEffect set(int n, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
                    CTAlphaCeilingEffect cTAlphaCeilingEffect2 = CTEffectContainerImpl.this.getAlphaCeilingArray(n);
                    CTEffectContainerImpl.this.setAlphaCeilingArray(n, cTAlphaCeilingEffect);
                    return cTAlphaCeilingEffect2;
                }

                @Override
                public void add(int n, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaCeiling(n).set((XmlObject)cTAlphaCeilingEffect);
                }

                @Override
                public CTAlphaCeilingEffect remove(int n) {
                    CTAlphaCeilingEffect cTAlphaCeilingEffect = CTEffectContainerImpl.this.getAlphaCeilingArray(n);
                    CTEffectContainerImpl.this.removeAlphaCeiling(n);
                    return cTAlphaCeilingEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaCeilingArray();
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
            this.get_store().find_all_element_users(ALPHACEILING$6, arrayList);
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
            cTAlphaCeilingEffect = (CTAlphaCeilingEffect)this.get_store().find_element_user(ALPHACEILING$6, n);
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
            return this.get_store().count_elements(ALPHACEILING$6);
        }
    }

    @Override
    public void setAlphaCeilingArray(CTAlphaCeilingEffect[] cTAlphaCeilingEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaCeilingEffectArray, ALPHACEILING$6);
    }

    @Override
    public void setAlphaCeilingArray(int n, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaCeilingEffect, ALPHACEILING$6, n, (short)2);
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
            cTAlphaCeilingEffect = (CTAlphaCeilingEffect)this.get_store().insert_element_user(ALPHACEILING$6, n);
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
            cTAlphaCeilingEffect = (CTAlphaCeilingEffect)this.get_store().add_element_user(ALPHACEILING$6);
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
            this.get_store().remove_element(ALPHACEILING$6, n);
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
                    return CTEffectContainerImpl.this.getAlphaFloorArray(n);
                }

                @Override
                public CTAlphaFloorEffect set(int n, CTAlphaFloorEffect cTAlphaFloorEffect) {
                    CTAlphaFloorEffect cTAlphaFloorEffect2 = CTEffectContainerImpl.this.getAlphaFloorArray(n);
                    CTEffectContainerImpl.this.setAlphaFloorArray(n, cTAlphaFloorEffect);
                    return cTAlphaFloorEffect2;
                }

                @Override
                public void add(int n, CTAlphaFloorEffect cTAlphaFloorEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaFloor(n).set((XmlObject)cTAlphaFloorEffect);
                }

                @Override
                public CTAlphaFloorEffect remove(int n) {
                    CTAlphaFloorEffect cTAlphaFloorEffect = CTEffectContainerImpl.this.getAlphaFloorArray(n);
                    CTEffectContainerImpl.this.removeAlphaFloor(n);
                    return cTAlphaFloorEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaFloorArray();
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
            this.get_store().find_all_element_users(ALPHAFLOOR$8, arrayList);
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
            cTAlphaFloorEffect = (CTAlphaFloorEffect)this.get_store().find_element_user(ALPHAFLOOR$8, n);
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
            return this.get_store().count_elements(ALPHAFLOOR$8);
        }
    }

    @Override
    public void setAlphaFloorArray(CTAlphaFloorEffect[] cTAlphaFloorEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaFloorEffectArray, ALPHAFLOOR$8);
    }

    @Override
    public void setAlphaFloorArray(int n, CTAlphaFloorEffect cTAlphaFloorEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaFloorEffect, ALPHAFLOOR$8, n, (short)2);
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
            cTAlphaFloorEffect = (CTAlphaFloorEffect)this.get_store().insert_element_user(ALPHAFLOOR$8, n);
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
            cTAlphaFloorEffect = (CTAlphaFloorEffect)this.get_store().add_element_user(ALPHAFLOOR$8);
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
            this.get_store().remove_element(ALPHAFLOOR$8, n);
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
                    return CTEffectContainerImpl.this.getAlphaInvArray(n);
                }

                @Override
                public CTAlphaInverseEffect set(int n, CTAlphaInverseEffect cTAlphaInverseEffect) {
                    CTAlphaInverseEffect cTAlphaInverseEffect2 = CTEffectContainerImpl.this.getAlphaInvArray(n);
                    CTEffectContainerImpl.this.setAlphaInvArray(n, cTAlphaInverseEffect);
                    return cTAlphaInverseEffect2;
                }

                @Override
                public void add(int n, CTAlphaInverseEffect cTAlphaInverseEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaInv(n).set((XmlObject)cTAlphaInverseEffect);
                }

                @Override
                public CTAlphaInverseEffect remove(int n) {
                    CTAlphaInverseEffect cTAlphaInverseEffect = CTEffectContainerImpl.this.getAlphaInvArray(n);
                    CTEffectContainerImpl.this.removeAlphaInv(n);
                    return cTAlphaInverseEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaInvArray();
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
            this.get_store().find_all_element_users(ALPHAINV$10, arrayList);
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
            cTAlphaInverseEffect = (CTAlphaInverseEffect)this.get_store().find_element_user(ALPHAINV$10, n);
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
            return this.get_store().count_elements(ALPHAINV$10);
        }
    }

    @Override
    public void setAlphaInvArray(CTAlphaInverseEffect[] cTAlphaInverseEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaInverseEffectArray, ALPHAINV$10);
    }

    @Override
    public void setAlphaInvArray(int n, CTAlphaInverseEffect cTAlphaInverseEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaInverseEffect, ALPHAINV$10, n, (short)2);
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
            cTAlphaInverseEffect = (CTAlphaInverseEffect)this.get_store().insert_element_user(ALPHAINV$10, n);
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
            cTAlphaInverseEffect = (CTAlphaInverseEffect)this.get_store().add_element_user(ALPHAINV$10);
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
            this.get_store().remove_element(ALPHAINV$10, n);
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
                    return CTEffectContainerImpl.this.getAlphaModArray(n);
                }

                @Override
                public CTAlphaModulateEffect set(int n, CTAlphaModulateEffect cTAlphaModulateEffect) {
                    CTAlphaModulateEffect cTAlphaModulateEffect2 = CTEffectContainerImpl.this.getAlphaModArray(n);
                    CTEffectContainerImpl.this.setAlphaModArray(n, cTAlphaModulateEffect);
                    return cTAlphaModulateEffect2;
                }

                @Override
                public void add(int n, CTAlphaModulateEffect cTAlphaModulateEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaMod(n).set((XmlObject)cTAlphaModulateEffect);
                }

                @Override
                public CTAlphaModulateEffect remove(int n) {
                    CTAlphaModulateEffect cTAlphaModulateEffect = CTEffectContainerImpl.this.getAlphaModArray(n);
                    CTEffectContainerImpl.this.removeAlphaMod(n);
                    return cTAlphaModulateEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaModArray();
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
            this.get_store().find_all_element_users(ALPHAMOD$12, arrayList);
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
            cTAlphaModulateEffect = (CTAlphaModulateEffect)this.get_store().find_element_user(ALPHAMOD$12, n);
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
            return this.get_store().count_elements(ALPHAMOD$12);
        }
    }

    @Override
    public void setAlphaModArray(CTAlphaModulateEffect[] cTAlphaModulateEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaModulateEffectArray, ALPHAMOD$12);
    }

    @Override
    public void setAlphaModArray(int n, CTAlphaModulateEffect cTAlphaModulateEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaModulateEffect, ALPHAMOD$12, n, (short)2);
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
            cTAlphaModulateEffect = (CTAlphaModulateEffect)this.get_store().insert_element_user(ALPHAMOD$12, n);
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
            cTAlphaModulateEffect = (CTAlphaModulateEffect)this.get_store().add_element_user(ALPHAMOD$12);
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
            this.get_store().remove_element(ALPHAMOD$12, n);
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
                    return CTEffectContainerImpl.this.getAlphaModFixArray(n);
                }

                @Override
                public CTAlphaModulateFixedEffect set(int n, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
                    CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect2 = CTEffectContainerImpl.this.getAlphaModFixArray(n);
                    CTEffectContainerImpl.this.setAlphaModFixArray(n, cTAlphaModulateFixedEffect);
                    return cTAlphaModulateFixedEffect2;
                }

                @Override
                public void add(int n, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaModFix(n).set(cTAlphaModulateFixedEffect);
                }

                @Override
                public CTAlphaModulateFixedEffect remove(int n) {
                    CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect = CTEffectContainerImpl.this.getAlphaModFixArray(n);
                    CTEffectContainerImpl.this.removeAlphaModFix(n);
                    return cTAlphaModulateFixedEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaModFixArray();
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
            this.get_store().find_all_element_users(ALPHAMODFIX$14, arrayList);
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
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect)((Object)this.get_store().find_element_user(ALPHAMODFIX$14, n));
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
            return this.get_store().count_elements(ALPHAMODFIX$14);
        }
    }

    @Override
    public void setAlphaModFixArray(CTAlphaModulateFixedEffect[] cTAlphaModulateFixedEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTAlphaModulateFixedEffectArray, ALPHAMODFIX$14);
    }

    @Override
    public void setAlphaModFixArray(int n, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
        this.generatedSetterHelperImpl(cTAlphaModulateFixedEffect, ALPHAMODFIX$14, n, (short)2);
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
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect)((Object)this.get_store().insert_element_user(ALPHAMODFIX$14, n));
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
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect)((Object)this.get_store().add_element_user(ALPHAMODFIX$14));
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
            this.get_store().remove_element(ALPHAMODFIX$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTAlphaOutsetEffect> getAlphaOutsetList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AlphaOutsetList
            extends AbstractList<CTAlphaOutsetEffect> {
                AlphaOutsetList() {
                }

                @Override
                public CTAlphaOutsetEffect get(int n) {
                    return CTEffectContainerImpl.this.getAlphaOutsetArray(n);
                }

                @Override
                public CTAlphaOutsetEffect set(int n, CTAlphaOutsetEffect cTAlphaOutsetEffect) {
                    CTAlphaOutsetEffect cTAlphaOutsetEffect2 = CTEffectContainerImpl.this.getAlphaOutsetArray(n);
                    CTEffectContainerImpl.this.setAlphaOutsetArray(n, cTAlphaOutsetEffect);
                    return cTAlphaOutsetEffect2;
                }

                @Override
                public void add(int n, CTAlphaOutsetEffect cTAlphaOutsetEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaOutset(n).set((XmlObject)cTAlphaOutsetEffect);
                }

                @Override
                public CTAlphaOutsetEffect remove(int n) {
                    CTAlphaOutsetEffect cTAlphaOutsetEffect = CTEffectContainerImpl.this.getAlphaOutsetArray(n);
                    CTEffectContainerImpl.this.removeAlphaOutset(n);
                    return cTAlphaOutsetEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaOutsetArray();
                }
            }
            return new AlphaOutsetList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTAlphaOutsetEffect[] getAlphaOutsetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ALPHAOUTSET$16, arrayList);
            CTAlphaOutsetEffect[] cTAlphaOutsetEffectArray = new CTAlphaOutsetEffect[arrayList.size()];
            arrayList.toArray(cTAlphaOutsetEffectArray);
            return cTAlphaOutsetEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaOutsetEffect getAlphaOutsetArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaOutsetEffect cTAlphaOutsetEffect = null;
            cTAlphaOutsetEffect = (CTAlphaOutsetEffect)this.get_store().find_element_user(ALPHAOUTSET$16, n);
            if (cTAlphaOutsetEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTAlphaOutsetEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAlphaOutsetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALPHAOUTSET$16);
        }
    }

    @Override
    public void setAlphaOutsetArray(CTAlphaOutsetEffect[] cTAlphaOutsetEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaOutsetEffectArray, ALPHAOUTSET$16);
    }

    @Override
    public void setAlphaOutsetArray(int n, CTAlphaOutsetEffect cTAlphaOutsetEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaOutsetEffect, ALPHAOUTSET$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaOutsetEffect insertNewAlphaOutset(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaOutsetEffect cTAlphaOutsetEffect = null;
            cTAlphaOutsetEffect = (CTAlphaOutsetEffect)this.get_store().insert_element_user(ALPHAOUTSET$16, n);
            return cTAlphaOutsetEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAlphaOutsetEffect addNewAlphaOutset() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAlphaOutsetEffect cTAlphaOutsetEffect = null;
            cTAlphaOutsetEffect = (CTAlphaOutsetEffect)this.get_store().add_element_user(ALPHAOUTSET$16);
            return cTAlphaOutsetEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlphaOutset(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALPHAOUTSET$16, n);
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
                    return CTEffectContainerImpl.this.getAlphaReplArray(n);
                }

                @Override
                public CTAlphaReplaceEffect set(int n, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
                    CTAlphaReplaceEffect cTAlphaReplaceEffect2 = CTEffectContainerImpl.this.getAlphaReplArray(n);
                    CTEffectContainerImpl.this.setAlphaReplArray(n, cTAlphaReplaceEffect);
                    return cTAlphaReplaceEffect2;
                }

                @Override
                public void add(int n, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
                    CTEffectContainerImpl.this.insertNewAlphaRepl(n).set((XmlObject)cTAlphaReplaceEffect);
                }

                @Override
                public CTAlphaReplaceEffect remove(int n) {
                    CTAlphaReplaceEffect cTAlphaReplaceEffect = CTEffectContainerImpl.this.getAlphaReplArray(n);
                    CTEffectContainerImpl.this.removeAlphaRepl(n);
                    return cTAlphaReplaceEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfAlphaReplArray();
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
            this.get_store().find_all_element_users(ALPHAREPL$18, arrayList);
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
            cTAlphaReplaceEffect = (CTAlphaReplaceEffect)this.get_store().find_element_user(ALPHAREPL$18, n);
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
            return this.get_store().count_elements(ALPHAREPL$18);
        }
    }

    @Override
    public void setAlphaReplArray(CTAlphaReplaceEffect[] cTAlphaReplaceEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTAlphaReplaceEffectArray, ALPHAREPL$18);
    }

    @Override
    public void setAlphaReplArray(int n, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTAlphaReplaceEffect, ALPHAREPL$18, n, (short)2);
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
            cTAlphaReplaceEffect = (CTAlphaReplaceEffect)this.get_store().insert_element_user(ALPHAREPL$18, n);
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
            cTAlphaReplaceEffect = (CTAlphaReplaceEffect)this.get_store().add_element_user(ALPHAREPL$18);
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
            this.get_store().remove_element(ALPHAREPL$18, n);
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
                    return CTEffectContainerImpl.this.getBiLevelArray(n);
                }

                @Override
                public CTBiLevelEffect set(int n, CTBiLevelEffect cTBiLevelEffect) {
                    CTBiLevelEffect cTBiLevelEffect2 = CTEffectContainerImpl.this.getBiLevelArray(n);
                    CTEffectContainerImpl.this.setBiLevelArray(n, cTBiLevelEffect);
                    return cTBiLevelEffect2;
                }

                @Override
                public void add(int n, CTBiLevelEffect cTBiLevelEffect) {
                    CTEffectContainerImpl.this.insertNewBiLevel(n).set((XmlObject)cTBiLevelEffect);
                }

                @Override
                public CTBiLevelEffect remove(int n) {
                    CTBiLevelEffect cTBiLevelEffect = CTEffectContainerImpl.this.getBiLevelArray(n);
                    CTEffectContainerImpl.this.removeBiLevel(n);
                    return cTBiLevelEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfBiLevelArray();
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
            this.get_store().find_all_element_users(BILEVEL$20, arrayList);
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
            cTBiLevelEffect = (CTBiLevelEffect)this.get_store().find_element_user(BILEVEL$20, n);
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
            return this.get_store().count_elements(BILEVEL$20);
        }
    }

    @Override
    public void setBiLevelArray(CTBiLevelEffect[] cTBiLevelEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBiLevelEffectArray, BILEVEL$20);
    }

    @Override
    public void setBiLevelArray(int n, CTBiLevelEffect cTBiLevelEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTBiLevelEffect, BILEVEL$20, n, (short)2);
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
            cTBiLevelEffect = (CTBiLevelEffect)this.get_store().insert_element_user(BILEVEL$20, n);
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
            cTBiLevelEffect = (CTBiLevelEffect)this.get_store().add_element_user(BILEVEL$20);
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
            this.get_store().remove_element(BILEVEL$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBlendEffect> getBlendList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class BlendList
            extends AbstractList<CTBlendEffect> {
                BlendList() {
                }

                @Override
                public CTBlendEffect get(int n) {
                    return CTEffectContainerImpl.this.getBlendArray(n);
                }

                @Override
                public CTBlendEffect set(int n, CTBlendEffect cTBlendEffect) {
                    CTBlendEffect cTBlendEffect2 = CTEffectContainerImpl.this.getBlendArray(n);
                    CTEffectContainerImpl.this.setBlendArray(n, cTBlendEffect);
                    return cTBlendEffect2;
                }

                @Override
                public void add(int n, CTBlendEffect cTBlendEffect) {
                    CTEffectContainerImpl.this.insertNewBlend(n).set((XmlObject)cTBlendEffect);
                }

                @Override
                public CTBlendEffect remove(int n) {
                    CTBlendEffect cTBlendEffect = CTEffectContainerImpl.this.getBlendArray(n);
                    CTEffectContainerImpl.this.removeBlend(n);
                    return cTBlendEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfBlendArray();
                }
            }
            return new BlendList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBlendEffect[] getBlendArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(BLEND$22, arrayList);
            CTBlendEffect[] cTBlendEffectArray = new CTBlendEffect[arrayList.size()];
            arrayList.toArray(cTBlendEffectArray);
            return cTBlendEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlendEffect getBlendArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlendEffect cTBlendEffect = null;
            cTBlendEffect = (CTBlendEffect)this.get_store().find_element_user(BLEND$22, n);
            if (cTBlendEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBlendEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfBlendArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLEND$22);
        }
    }

    @Override
    public void setBlendArray(CTBlendEffect[] cTBlendEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBlendEffectArray, BLEND$22);
    }

    @Override
    public void setBlendArray(int n, CTBlendEffect cTBlendEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTBlendEffect, BLEND$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlendEffect insertNewBlend(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlendEffect cTBlendEffect = null;
            cTBlendEffect = (CTBlendEffect)this.get_store().insert_element_user(BLEND$22, n);
            return cTBlendEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlendEffect addNewBlend() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlendEffect cTBlendEffect = null;
            cTBlendEffect = (CTBlendEffect)this.get_store().add_element_user(BLEND$22);
            return cTBlendEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeBlend(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLEND$22, n);
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
                    return CTEffectContainerImpl.this.getBlurArray(n);
                }

                @Override
                public CTBlurEffect set(int n, CTBlurEffect cTBlurEffect) {
                    CTBlurEffect cTBlurEffect2 = CTEffectContainerImpl.this.getBlurArray(n);
                    CTEffectContainerImpl.this.setBlurArray(n, cTBlurEffect);
                    return cTBlurEffect2;
                }

                @Override
                public void add(int n, CTBlurEffect cTBlurEffect) {
                    CTEffectContainerImpl.this.insertNewBlur(n).set((XmlObject)cTBlurEffect);
                }

                @Override
                public CTBlurEffect remove(int n) {
                    CTBlurEffect cTBlurEffect = CTEffectContainerImpl.this.getBlurArray(n);
                    CTEffectContainerImpl.this.removeBlur(n);
                    return cTBlurEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfBlurArray();
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
            this.get_store().find_all_element_users(BLUR$24, arrayList);
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
            cTBlurEffect = (CTBlurEffect)this.get_store().find_element_user(BLUR$24, n);
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
            return this.get_store().count_elements(BLUR$24);
        }
    }

    @Override
    public void setBlurArray(CTBlurEffect[] cTBlurEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTBlurEffectArray, BLUR$24);
    }

    @Override
    public void setBlurArray(int n, CTBlurEffect cTBlurEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTBlurEffect, BLUR$24, n, (short)2);
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
            cTBlurEffect = (CTBlurEffect)this.get_store().insert_element_user(BLUR$24, n);
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
            cTBlurEffect = (CTBlurEffect)this.get_store().add_element_user(BLUR$24);
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
            this.get_store().remove_element(BLUR$24, n);
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
                    return CTEffectContainerImpl.this.getClrChangeArray(n);
                }

                @Override
                public CTColorChangeEffect set(int n, CTColorChangeEffect cTColorChangeEffect) {
                    CTColorChangeEffect cTColorChangeEffect2 = CTEffectContainerImpl.this.getClrChangeArray(n);
                    CTEffectContainerImpl.this.setClrChangeArray(n, cTColorChangeEffect);
                    return cTColorChangeEffect2;
                }

                @Override
                public void add(int n, CTColorChangeEffect cTColorChangeEffect) {
                    CTEffectContainerImpl.this.insertNewClrChange(n).set((XmlObject)cTColorChangeEffect);
                }

                @Override
                public CTColorChangeEffect remove(int n) {
                    CTColorChangeEffect cTColorChangeEffect = CTEffectContainerImpl.this.getClrChangeArray(n);
                    CTEffectContainerImpl.this.removeClrChange(n);
                    return cTColorChangeEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfClrChangeArray();
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
            this.get_store().find_all_element_users(CLRCHANGE$26, arrayList);
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
            cTColorChangeEffect = (CTColorChangeEffect)this.get_store().find_element_user(CLRCHANGE$26, n);
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
            return this.get_store().count_elements(CLRCHANGE$26);
        }
    }

    @Override
    public void setClrChangeArray(CTColorChangeEffect[] cTColorChangeEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTColorChangeEffectArray, CLRCHANGE$26);
    }

    @Override
    public void setClrChangeArray(int n, CTColorChangeEffect cTColorChangeEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTColorChangeEffect, CLRCHANGE$26, n, (short)2);
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
            cTColorChangeEffect = (CTColorChangeEffect)this.get_store().insert_element_user(CLRCHANGE$26, n);
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
            cTColorChangeEffect = (CTColorChangeEffect)this.get_store().add_element_user(CLRCHANGE$26);
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
            this.get_store().remove_element(CLRCHANGE$26, n);
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
                    return CTEffectContainerImpl.this.getClrReplArray(n);
                }

                @Override
                public CTColorReplaceEffect set(int n, CTColorReplaceEffect cTColorReplaceEffect) {
                    CTColorReplaceEffect cTColorReplaceEffect2 = CTEffectContainerImpl.this.getClrReplArray(n);
                    CTEffectContainerImpl.this.setClrReplArray(n, cTColorReplaceEffect);
                    return cTColorReplaceEffect2;
                }

                @Override
                public void add(int n, CTColorReplaceEffect cTColorReplaceEffect) {
                    CTEffectContainerImpl.this.insertNewClrRepl(n).set((XmlObject)cTColorReplaceEffect);
                }

                @Override
                public CTColorReplaceEffect remove(int n) {
                    CTColorReplaceEffect cTColorReplaceEffect = CTEffectContainerImpl.this.getClrReplArray(n);
                    CTEffectContainerImpl.this.removeClrRepl(n);
                    return cTColorReplaceEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfClrReplArray();
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
            this.get_store().find_all_element_users(CLRREPL$28, arrayList);
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
            cTColorReplaceEffect = (CTColorReplaceEffect)this.get_store().find_element_user(CLRREPL$28, n);
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
            return this.get_store().count_elements(CLRREPL$28);
        }
    }

    @Override
    public void setClrReplArray(CTColorReplaceEffect[] cTColorReplaceEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTColorReplaceEffectArray, CLRREPL$28);
    }

    @Override
    public void setClrReplArray(int n, CTColorReplaceEffect cTColorReplaceEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTColorReplaceEffect, CLRREPL$28, n, (short)2);
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
            cTColorReplaceEffect = (CTColorReplaceEffect)this.get_store().insert_element_user(CLRREPL$28, n);
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
            cTColorReplaceEffect = (CTColorReplaceEffect)this.get_store().add_element_user(CLRREPL$28);
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
            this.get_store().remove_element(CLRREPL$28, n);
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
                    return CTEffectContainerImpl.this.getDuotoneArray(n);
                }

                @Override
                public CTDuotoneEffect set(int n, CTDuotoneEffect cTDuotoneEffect) {
                    CTDuotoneEffect cTDuotoneEffect2 = CTEffectContainerImpl.this.getDuotoneArray(n);
                    CTEffectContainerImpl.this.setDuotoneArray(n, cTDuotoneEffect);
                    return cTDuotoneEffect2;
                }

                @Override
                public void add(int n, CTDuotoneEffect cTDuotoneEffect) {
                    CTEffectContainerImpl.this.insertNewDuotone(n).set(cTDuotoneEffect);
                }

                @Override
                public CTDuotoneEffect remove(int n) {
                    CTDuotoneEffect cTDuotoneEffect = CTEffectContainerImpl.this.getDuotoneArray(n);
                    CTEffectContainerImpl.this.removeDuotone(n);
                    return cTDuotoneEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfDuotoneArray();
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
            this.get_store().find_all_element_users(DUOTONE$30, arrayList);
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
            cTDuotoneEffect = (CTDuotoneEffect)((Object)this.get_store().find_element_user(DUOTONE$30, n));
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
            return this.get_store().count_elements(DUOTONE$30);
        }
    }

    @Override
    public void setDuotoneArray(CTDuotoneEffect[] cTDuotoneEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDuotoneEffectArray, DUOTONE$30);
    }

    @Override
    public void setDuotoneArray(int n, CTDuotoneEffect cTDuotoneEffect) {
        this.generatedSetterHelperImpl(cTDuotoneEffect, DUOTONE$30, n, (short)2);
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
            cTDuotoneEffect = (CTDuotoneEffect)((Object)this.get_store().insert_element_user(DUOTONE$30, n));
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
            cTDuotoneEffect = (CTDuotoneEffect)((Object)this.get_store().add_element_user(DUOTONE$30));
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
            this.get_store().remove_element(DUOTONE$30, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFillEffect> getFillList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FillList
            extends AbstractList<CTFillEffect> {
                FillList() {
                }

                @Override
                public CTFillEffect get(int n) {
                    return CTEffectContainerImpl.this.getFillArray(n);
                }

                @Override
                public CTFillEffect set(int n, CTFillEffect cTFillEffect) {
                    CTFillEffect cTFillEffect2 = CTEffectContainerImpl.this.getFillArray(n);
                    CTEffectContainerImpl.this.setFillArray(n, cTFillEffect);
                    return cTFillEffect2;
                }

                @Override
                public void add(int n, CTFillEffect cTFillEffect) {
                    CTEffectContainerImpl.this.insertNewFill(n).set((XmlObject)cTFillEffect);
                }

                @Override
                public CTFillEffect remove(int n) {
                    CTFillEffect cTFillEffect = CTEffectContainerImpl.this.getFillArray(n);
                    CTEffectContainerImpl.this.removeFill(n);
                    return cTFillEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfFillArray();
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
    public CTFillEffect[] getFillArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILL$32, arrayList);
            CTFillEffect[] cTFillEffectArray = new CTFillEffect[arrayList.size()];
            arrayList.toArray(cTFillEffectArray);
            return cTFillEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillEffect getFillArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillEffect cTFillEffect = null;
            cTFillEffect = (CTFillEffect)this.get_store().find_element_user(FILL$32, n);
            if (cTFillEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFillEffect;
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
            return this.get_store().count_elements(FILL$32);
        }
    }

    @Override
    public void setFillArray(CTFillEffect[] cTFillEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFillEffectArray, FILL$32);
    }

    @Override
    public void setFillArray(int n, CTFillEffect cTFillEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTFillEffect, FILL$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillEffect insertNewFill(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillEffect cTFillEffect = null;
            cTFillEffect = (CTFillEffect)this.get_store().insert_element_user(FILL$32, n);
            return cTFillEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillEffect addNewFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillEffect cTFillEffect = null;
            cTFillEffect = (CTFillEffect)this.get_store().add_element_user(FILL$32);
            return cTFillEffect;
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
            this.get_store().remove_element(FILL$32, n);
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
                    return CTEffectContainerImpl.this.getFillOverlayArray(n);
                }

                @Override
                public CTFillOverlayEffect set(int n, CTFillOverlayEffect cTFillOverlayEffect) {
                    CTFillOverlayEffect cTFillOverlayEffect2 = CTEffectContainerImpl.this.getFillOverlayArray(n);
                    CTEffectContainerImpl.this.setFillOverlayArray(n, cTFillOverlayEffect);
                    return cTFillOverlayEffect2;
                }

                @Override
                public void add(int n, CTFillOverlayEffect cTFillOverlayEffect) {
                    CTEffectContainerImpl.this.insertNewFillOverlay(n).set((XmlObject)cTFillOverlayEffect);
                }

                @Override
                public CTFillOverlayEffect remove(int n) {
                    CTFillOverlayEffect cTFillOverlayEffect = CTEffectContainerImpl.this.getFillOverlayArray(n);
                    CTEffectContainerImpl.this.removeFillOverlay(n);
                    return cTFillOverlayEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfFillOverlayArray();
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
            this.get_store().find_all_element_users(FILLOVERLAY$34, arrayList);
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
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().find_element_user(FILLOVERLAY$34, n);
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
            return this.get_store().count_elements(FILLOVERLAY$34);
        }
    }

    @Override
    public void setFillOverlayArray(CTFillOverlayEffect[] cTFillOverlayEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFillOverlayEffectArray, FILLOVERLAY$34);
    }

    @Override
    public void setFillOverlayArray(int n, CTFillOverlayEffect cTFillOverlayEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTFillOverlayEffect, FILLOVERLAY$34, n, (short)2);
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
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().insert_element_user(FILLOVERLAY$34, n);
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
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().add_element_user(FILLOVERLAY$34);
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
            this.get_store().remove_element(FILLOVERLAY$34, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTGlowEffect> getGlowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GlowList
            extends AbstractList<CTGlowEffect> {
                GlowList() {
                }

                @Override
                public CTGlowEffect get(int n) {
                    return CTEffectContainerImpl.this.getGlowArray(n);
                }

                @Override
                public CTGlowEffect set(int n, CTGlowEffect cTGlowEffect) {
                    CTGlowEffect cTGlowEffect2 = CTEffectContainerImpl.this.getGlowArray(n);
                    CTEffectContainerImpl.this.setGlowArray(n, cTGlowEffect);
                    return cTGlowEffect2;
                }

                @Override
                public void add(int n, CTGlowEffect cTGlowEffect) {
                    CTEffectContainerImpl.this.insertNewGlow(n).set((XmlObject)cTGlowEffect);
                }

                @Override
                public CTGlowEffect remove(int n) {
                    CTGlowEffect cTGlowEffect = CTEffectContainerImpl.this.getGlowArray(n);
                    CTEffectContainerImpl.this.removeGlow(n);
                    return cTGlowEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfGlowArray();
                }
            }
            return new GlowList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTGlowEffect[] getGlowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GLOW$36, arrayList);
            CTGlowEffect[] cTGlowEffectArray = new CTGlowEffect[arrayList.size()];
            arrayList.toArray(cTGlowEffectArray);
            return cTGlowEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGlowEffect getGlowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGlowEffect cTGlowEffect = null;
            cTGlowEffect = (CTGlowEffect)this.get_store().find_element_user(GLOW$36, n);
            if (cTGlowEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTGlowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGlowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GLOW$36);
        }
    }

    @Override
    public void setGlowArray(CTGlowEffect[] cTGlowEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTGlowEffectArray, GLOW$36);
    }

    @Override
    public void setGlowArray(int n, CTGlowEffect cTGlowEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTGlowEffect, GLOW$36, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGlowEffect insertNewGlow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGlowEffect cTGlowEffect = null;
            cTGlowEffect = (CTGlowEffect)this.get_store().insert_element_user(GLOW$36, n);
            return cTGlowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGlowEffect addNewGlow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGlowEffect cTGlowEffect = null;
            cTGlowEffect = (CTGlowEffect)this.get_store().add_element_user(GLOW$36);
            return cTGlowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGlow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GLOW$36, n);
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
                    return CTEffectContainerImpl.this.getGraysclArray(n);
                }

                @Override
                public CTGrayscaleEffect set(int n, CTGrayscaleEffect cTGrayscaleEffect) {
                    CTGrayscaleEffect cTGrayscaleEffect2 = CTEffectContainerImpl.this.getGraysclArray(n);
                    CTEffectContainerImpl.this.setGraysclArray(n, cTGrayscaleEffect);
                    return cTGrayscaleEffect2;
                }

                @Override
                public void add(int n, CTGrayscaleEffect cTGrayscaleEffect) {
                    CTEffectContainerImpl.this.insertNewGrayscl(n).set((XmlObject)cTGrayscaleEffect);
                }

                @Override
                public CTGrayscaleEffect remove(int n) {
                    CTGrayscaleEffect cTGrayscaleEffect = CTEffectContainerImpl.this.getGraysclArray(n);
                    CTEffectContainerImpl.this.removeGrayscl(n);
                    return cTGrayscaleEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfGraysclArray();
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
            this.get_store().find_all_element_users(GRAYSCL$38, arrayList);
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
            cTGrayscaleEffect = (CTGrayscaleEffect)this.get_store().find_element_user(GRAYSCL$38, n);
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
            return this.get_store().count_elements(GRAYSCL$38);
        }
    }

    @Override
    public void setGraysclArray(CTGrayscaleEffect[] cTGrayscaleEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTGrayscaleEffectArray, GRAYSCL$38);
    }

    @Override
    public void setGraysclArray(int n, CTGrayscaleEffect cTGrayscaleEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTGrayscaleEffect, GRAYSCL$38, n, (short)2);
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
            cTGrayscaleEffect = (CTGrayscaleEffect)this.get_store().insert_element_user(GRAYSCL$38, n);
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
            cTGrayscaleEffect = (CTGrayscaleEffect)this.get_store().add_element_user(GRAYSCL$38);
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
            this.get_store().remove_element(GRAYSCL$38, n);
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
                    return CTEffectContainerImpl.this.getHslArray(n);
                }

                @Override
                public CTHSLEffect set(int n, CTHSLEffect cTHSLEffect) {
                    CTHSLEffect cTHSLEffect2 = CTEffectContainerImpl.this.getHslArray(n);
                    CTEffectContainerImpl.this.setHslArray(n, cTHSLEffect);
                    return cTHSLEffect2;
                }

                @Override
                public void add(int n, CTHSLEffect cTHSLEffect) {
                    CTEffectContainerImpl.this.insertNewHsl(n).set((XmlObject)cTHSLEffect);
                }

                @Override
                public CTHSLEffect remove(int n) {
                    CTHSLEffect cTHSLEffect = CTEffectContainerImpl.this.getHslArray(n);
                    CTEffectContainerImpl.this.removeHsl(n);
                    return cTHSLEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfHslArray();
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
            this.get_store().find_all_element_users(HSL$40, arrayList);
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
            cTHSLEffect = (CTHSLEffect)this.get_store().find_element_user(HSL$40, n);
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
            return this.get_store().count_elements(HSL$40);
        }
    }

    @Override
    public void setHslArray(CTHSLEffect[] cTHSLEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTHSLEffectArray, HSL$40);
    }

    @Override
    public void setHslArray(int n, CTHSLEffect cTHSLEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTHSLEffect, HSL$40, n, (short)2);
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
            cTHSLEffect = (CTHSLEffect)this.get_store().insert_element_user(HSL$40, n);
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
            cTHSLEffect = (CTHSLEffect)this.get_store().add_element_user(HSL$40);
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
            this.get_store().remove_element(HSL$40, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTInnerShadowEffect> getInnerShdwList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class InnerShdwList
            extends AbstractList<CTInnerShadowEffect> {
                InnerShdwList() {
                }

                @Override
                public CTInnerShadowEffect get(int n) {
                    return CTEffectContainerImpl.this.getInnerShdwArray(n);
                }

                @Override
                public CTInnerShadowEffect set(int n, CTInnerShadowEffect cTInnerShadowEffect) {
                    CTInnerShadowEffect cTInnerShadowEffect2 = CTEffectContainerImpl.this.getInnerShdwArray(n);
                    CTEffectContainerImpl.this.setInnerShdwArray(n, cTInnerShadowEffect);
                    return cTInnerShadowEffect2;
                }

                @Override
                public void add(int n, CTInnerShadowEffect cTInnerShadowEffect) {
                    CTEffectContainerImpl.this.insertNewInnerShdw(n).set((XmlObject)cTInnerShadowEffect);
                }

                @Override
                public CTInnerShadowEffect remove(int n) {
                    CTInnerShadowEffect cTInnerShadowEffect = CTEffectContainerImpl.this.getInnerShdwArray(n);
                    CTEffectContainerImpl.this.removeInnerShdw(n);
                    return cTInnerShadowEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfInnerShdwArray();
                }
            }
            return new InnerShdwList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTInnerShadowEffect[] getInnerShdwArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INNERSHDW$42, arrayList);
            CTInnerShadowEffect[] cTInnerShadowEffectArray = new CTInnerShadowEffect[arrayList.size()];
            arrayList.toArray(cTInnerShadowEffectArray);
            return cTInnerShadowEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInnerShadowEffect getInnerShdwArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInnerShadowEffect cTInnerShadowEffect = null;
            cTInnerShadowEffect = (CTInnerShadowEffect)this.get_store().find_element_user(INNERSHDW$42, n);
            if (cTInnerShadowEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTInnerShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfInnerShdwArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INNERSHDW$42);
        }
    }

    @Override
    public void setInnerShdwArray(CTInnerShadowEffect[] cTInnerShadowEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTInnerShadowEffectArray, INNERSHDW$42);
    }

    @Override
    public void setInnerShdwArray(int n, CTInnerShadowEffect cTInnerShadowEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTInnerShadowEffect, INNERSHDW$42, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInnerShadowEffect insertNewInnerShdw(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInnerShadowEffect cTInnerShadowEffect = null;
            cTInnerShadowEffect = (CTInnerShadowEffect)this.get_store().insert_element_user(INNERSHDW$42, n);
            return cTInnerShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInnerShadowEffect addNewInnerShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInnerShadowEffect cTInnerShadowEffect = null;
            cTInnerShadowEffect = (CTInnerShadowEffect)this.get_store().add_element_user(INNERSHDW$42);
            return cTInnerShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInnerShdw(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INNERSHDW$42, n);
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
                    return CTEffectContainerImpl.this.getLumArray(n);
                }

                @Override
                public CTLuminanceEffect set(int n, CTLuminanceEffect cTLuminanceEffect) {
                    CTLuminanceEffect cTLuminanceEffect2 = CTEffectContainerImpl.this.getLumArray(n);
                    CTEffectContainerImpl.this.setLumArray(n, cTLuminanceEffect);
                    return cTLuminanceEffect2;
                }

                @Override
                public void add(int n, CTLuminanceEffect cTLuminanceEffect) {
                    CTEffectContainerImpl.this.insertNewLum(n).set((XmlObject)cTLuminanceEffect);
                }

                @Override
                public CTLuminanceEffect remove(int n) {
                    CTLuminanceEffect cTLuminanceEffect = CTEffectContainerImpl.this.getLumArray(n);
                    CTEffectContainerImpl.this.removeLum(n);
                    return cTLuminanceEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfLumArray();
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
            this.get_store().find_all_element_users(LUM$44, arrayList);
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
            cTLuminanceEffect = (CTLuminanceEffect)this.get_store().find_element_user(LUM$44, n);
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
            return this.get_store().count_elements(LUM$44);
        }
    }

    @Override
    public void setLumArray(CTLuminanceEffect[] cTLuminanceEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTLuminanceEffectArray, LUM$44);
    }

    @Override
    public void setLumArray(int n, CTLuminanceEffect cTLuminanceEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTLuminanceEffect, LUM$44, n, (short)2);
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
            cTLuminanceEffect = (CTLuminanceEffect)this.get_store().insert_element_user(LUM$44, n);
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
            cTLuminanceEffect = (CTLuminanceEffect)this.get_store().add_element_user(LUM$44);
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
            this.get_store().remove_element(LUM$44, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOuterShadowEffect> getOuterShdwList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OuterShdwList
            extends AbstractList<CTOuterShadowEffect> {
                OuterShdwList() {
                }

                @Override
                public CTOuterShadowEffect get(int n) {
                    return CTEffectContainerImpl.this.getOuterShdwArray(n);
                }

                @Override
                public CTOuterShadowEffect set(int n, CTOuterShadowEffect cTOuterShadowEffect) {
                    CTOuterShadowEffect cTOuterShadowEffect2 = CTEffectContainerImpl.this.getOuterShdwArray(n);
                    CTEffectContainerImpl.this.setOuterShdwArray(n, cTOuterShadowEffect);
                    return cTOuterShadowEffect2;
                }

                @Override
                public void add(int n, CTOuterShadowEffect cTOuterShadowEffect) {
                    CTEffectContainerImpl.this.insertNewOuterShdw(n).set(cTOuterShadowEffect);
                }

                @Override
                public CTOuterShadowEffect remove(int n) {
                    CTOuterShadowEffect cTOuterShadowEffect = CTEffectContainerImpl.this.getOuterShdwArray(n);
                    CTEffectContainerImpl.this.removeOuterShdw(n);
                    return cTOuterShadowEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfOuterShdwArray();
                }
            }
            return new OuterShdwList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOuterShadowEffect[] getOuterShdwArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OUTERSHDW$46, arrayList);
            CTOuterShadowEffect[] cTOuterShadowEffectArray = new CTOuterShadowEffect[arrayList.size()];
            arrayList.toArray(cTOuterShadowEffectArray);
            return cTOuterShadowEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOuterShadowEffect getOuterShdwArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOuterShadowEffect cTOuterShadowEffect = null;
            cTOuterShadowEffect = (CTOuterShadowEffect)((Object)this.get_store().find_element_user(OUTERSHDW$46, n));
            if (cTOuterShadowEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOuterShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOuterShdwArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OUTERSHDW$46);
        }
    }

    @Override
    public void setOuterShdwArray(CTOuterShadowEffect[] cTOuterShadowEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOuterShadowEffectArray, OUTERSHDW$46);
    }

    @Override
    public void setOuterShdwArray(int n, CTOuterShadowEffect cTOuterShadowEffect) {
        this.generatedSetterHelperImpl(cTOuterShadowEffect, OUTERSHDW$46, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOuterShadowEffect insertNewOuterShdw(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOuterShadowEffect cTOuterShadowEffect = null;
            cTOuterShadowEffect = (CTOuterShadowEffect)((Object)this.get_store().insert_element_user(OUTERSHDW$46, n));
            return cTOuterShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOuterShadowEffect addNewOuterShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOuterShadowEffect cTOuterShadowEffect = null;
            cTOuterShadowEffect = (CTOuterShadowEffect)((Object)this.get_store().add_element_user(OUTERSHDW$46));
            return cTOuterShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOuterShdw(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OUTERSHDW$46, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTPresetShadowEffect> getPrstShdwList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PrstShdwList
            extends AbstractList<CTPresetShadowEffect> {
                PrstShdwList() {
                }

                @Override
                public CTPresetShadowEffect get(int n) {
                    return CTEffectContainerImpl.this.getPrstShdwArray(n);
                }

                @Override
                public CTPresetShadowEffect set(int n, CTPresetShadowEffect cTPresetShadowEffect) {
                    CTPresetShadowEffect cTPresetShadowEffect2 = CTEffectContainerImpl.this.getPrstShdwArray(n);
                    CTEffectContainerImpl.this.setPrstShdwArray(n, cTPresetShadowEffect);
                    return cTPresetShadowEffect2;
                }

                @Override
                public void add(int n, CTPresetShadowEffect cTPresetShadowEffect) {
                    CTEffectContainerImpl.this.insertNewPrstShdw(n).set((XmlObject)cTPresetShadowEffect);
                }

                @Override
                public CTPresetShadowEffect remove(int n) {
                    CTPresetShadowEffect cTPresetShadowEffect = CTEffectContainerImpl.this.getPrstShdwArray(n);
                    CTEffectContainerImpl.this.removePrstShdw(n);
                    return cTPresetShadowEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfPrstShdwArray();
                }
            }
            return new PrstShdwList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTPresetShadowEffect[] getPrstShdwArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PRSTSHDW$48, arrayList);
            CTPresetShadowEffect[] cTPresetShadowEffectArray = new CTPresetShadowEffect[arrayList.size()];
            arrayList.toArray(cTPresetShadowEffectArray);
            return cTPresetShadowEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetShadowEffect getPrstShdwArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetShadowEffect cTPresetShadowEffect = null;
            cTPresetShadowEffect = (CTPresetShadowEffect)this.get_store().find_element_user(PRSTSHDW$48, n);
            if (cTPresetShadowEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTPresetShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPrstShdwArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTSHDW$48);
        }
    }

    @Override
    public void setPrstShdwArray(CTPresetShadowEffect[] cTPresetShadowEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTPresetShadowEffectArray, PRSTSHDW$48);
    }

    @Override
    public void setPrstShdwArray(int n, CTPresetShadowEffect cTPresetShadowEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTPresetShadowEffect, PRSTSHDW$48, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetShadowEffect insertNewPrstShdw(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetShadowEffect cTPresetShadowEffect = null;
            cTPresetShadowEffect = (CTPresetShadowEffect)this.get_store().insert_element_user(PRSTSHDW$48, n);
            return cTPresetShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetShadowEffect addNewPrstShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetShadowEffect cTPresetShadowEffect = null;
            cTPresetShadowEffect = (CTPresetShadowEffect)this.get_store().add_element_user(PRSTSHDW$48);
            return cTPresetShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePrstShdw(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTSHDW$48, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTReflectionEffect> getReflectionList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ReflectionList
            extends AbstractList<CTReflectionEffect> {
                ReflectionList() {
                }

                @Override
                public CTReflectionEffect get(int n) {
                    return CTEffectContainerImpl.this.getReflectionArray(n);
                }

                @Override
                public CTReflectionEffect set(int n, CTReflectionEffect cTReflectionEffect) {
                    CTReflectionEffect cTReflectionEffect2 = CTEffectContainerImpl.this.getReflectionArray(n);
                    CTEffectContainerImpl.this.setReflectionArray(n, cTReflectionEffect);
                    return cTReflectionEffect2;
                }

                @Override
                public void add(int n, CTReflectionEffect cTReflectionEffect) {
                    CTEffectContainerImpl.this.insertNewReflection(n).set((XmlObject)cTReflectionEffect);
                }

                @Override
                public CTReflectionEffect remove(int n) {
                    CTReflectionEffect cTReflectionEffect = CTEffectContainerImpl.this.getReflectionArray(n);
                    CTEffectContainerImpl.this.removeReflection(n);
                    return cTReflectionEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfReflectionArray();
                }
            }
            return new ReflectionList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTReflectionEffect[] getReflectionArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REFLECTION$50, arrayList);
            CTReflectionEffect[] cTReflectionEffectArray = new CTReflectionEffect[arrayList.size()];
            arrayList.toArray(cTReflectionEffectArray);
            return cTReflectionEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTReflectionEffect getReflectionArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTReflectionEffect cTReflectionEffect = null;
            cTReflectionEffect = (CTReflectionEffect)this.get_store().find_element_user(REFLECTION$50, n);
            if (cTReflectionEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTReflectionEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfReflectionArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REFLECTION$50);
        }
    }

    @Override
    public void setReflectionArray(CTReflectionEffect[] cTReflectionEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTReflectionEffectArray, REFLECTION$50);
    }

    @Override
    public void setReflectionArray(int n, CTReflectionEffect cTReflectionEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTReflectionEffect, REFLECTION$50, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTReflectionEffect insertNewReflection(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTReflectionEffect cTReflectionEffect = null;
            cTReflectionEffect = (CTReflectionEffect)this.get_store().insert_element_user(REFLECTION$50, n);
            return cTReflectionEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTReflectionEffect addNewReflection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTReflectionEffect cTReflectionEffect = null;
            cTReflectionEffect = (CTReflectionEffect)this.get_store().add_element_user(REFLECTION$50);
            return cTReflectionEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeReflection(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REFLECTION$50, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRelativeOffsetEffect> getRelOffList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RelOffList
            extends AbstractList<CTRelativeOffsetEffect> {
                RelOffList() {
                }

                @Override
                public CTRelativeOffsetEffect get(int n) {
                    return CTEffectContainerImpl.this.getRelOffArray(n);
                }

                @Override
                public CTRelativeOffsetEffect set(int n, CTRelativeOffsetEffect cTRelativeOffsetEffect) {
                    CTRelativeOffsetEffect cTRelativeOffsetEffect2 = CTEffectContainerImpl.this.getRelOffArray(n);
                    CTEffectContainerImpl.this.setRelOffArray(n, cTRelativeOffsetEffect);
                    return cTRelativeOffsetEffect2;
                }

                @Override
                public void add(int n, CTRelativeOffsetEffect cTRelativeOffsetEffect) {
                    CTEffectContainerImpl.this.insertNewRelOff(n).set((XmlObject)cTRelativeOffsetEffect);
                }

                @Override
                public CTRelativeOffsetEffect remove(int n) {
                    CTRelativeOffsetEffect cTRelativeOffsetEffect = CTEffectContainerImpl.this.getRelOffArray(n);
                    CTEffectContainerImpl.this.removeRelOff(n);
                    return cTRelativeOffsetEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfRelOffArray();
                }
            }
            return new RelOffList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRelativeOffsetEffect[] getRelOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(RELOFF$52, arrayList);
            CTRelativeOffsetEffect[] cTRelativeOffsetEffectArray = new CTRelativeOffsetEffect[arrayList.size()];
            arrayList.toArray(cTRelativeOffsetEffectArray);
            return cTRelativeOffsetEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeOffsetEffect getRelOffArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeOffsetEffect cTRelativeOffsetEffect = null;
            cTRelativeOffsetEffect = (CTRelativeOffsetEffect)this.get_store().find_element_user(RELOFF$52, n);
            if (cTRelativeOffsetEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRelativeOffsetEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRelOffArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RELOFF$52);
        }
    }

    @Override
    public void setRelOffArray(CTRelativeOffsetEffect[] cTRelativeOffsetEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTRelativeOffsetEffectArray, RELOFF$52);
    }

    @Override
    public void setRelOffArray(int n, CTRelativeOffsetEffect cTRelativeOffsetEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTRelativeOffsetEffect, RELOFF$52, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeOffsetEffect insertNewRelOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeOffsetEffect cTRelativeOffsetEffect = null;
            cTRelativeOffsetEffect = (CTRelativeOffsetEffect)this.get_store().insert_element_user(RELOFF$52, n);
            return cTRelativeOffsetEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeOffsetEffect addNewRelOff() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeOffsetEffect cTRelativeOffsetEffect = null;
            cTRelativeOffsetEffect = (CTRelativeOffsetEffect)this.get_store().add_element_user(RELOFF$52);
            return cTRelativeOffsetEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRelOff(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RELOFF$52, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSoftEdgesEffect> getSoftEdgeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SoftEdgeList
            extends AbstractList<CTSoftEdgesEffect> {
                SoftEdgeList() {
                }

                @Override
                public CTSoftEdgesEffect get(int n) {
                    return CTEffectContainerImpl.this.getSoftEdgeArray(n);
                }

                @Override
                public CTSoftEdgesEffect set(int n, CTSoftEdgesEffect cTSoftEdgesEffect) {
                    CTSoftEdgesEffect cTSoftEdgesEffect2 = CTEffectContainerImpl.this.getSoftEdgeArray(n);
                    CTEffectContainerImpl.this.setSoftEdgeArray(n, cTSoftEdgesEffect);
                    return cTSoftEdgesEffect2;
                }

                @Override
                public void add(int n, CTSoftEdgesEffect cTSoftEdgesEffect) {
                    CTEffectContainerImpl.this.insertNewSoftEdge(n).set((XmlObject)cTSoftEdgesEffect);
                }

                @Override
                public CTSoftEdgesEffect remove(int n) {
                    CTSoftEdgesEffect cTSoftEdgesEffect = CTEffectContainerImpl.this.getSoftEdgeArray(n);
                    CTEffectContainerImpl.this.removeSoftEdge(n);
                    return cTSoftEdgesEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfSoftEdgeArray();
                }
            }
            return new SoftEdgeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSoftEdgesEffect[] getSoftEdgeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SOFTEDGE$54, arrayList);
            CTSoftEdgesEffect[] cTSoftEdgesEffectArray = new CTSoftEdgesEffect[arrayList.size()];
            arrayList.toArray(cTSoftEdgesEffectArray);
            return cTSoftEdgesEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSoftEdgesEffect getSoftEdgeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSoftEdgesEffect cTSoftEdgesEffect = null;
            cTSoftEdgesEffect = (CTSoftEdgesEffect)this.get_store().find_element_user(SOFTEDGE$54, n);
            if (cTSoftEdgesEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSoftEdgesEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSoftEdgeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SOFTEDGE$54);
        }
    }

    @Override
    public void setSoftEdgeArray(CTSoftEdgesEffect[] cTSoftEdgesEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTSoftEdgesEffectArray, SOFTEDGE$54);
    }

    @Override
    public void setSoftEdgeArray(int n, CTSoftEdgesEffect cTSoftEdgesEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTSoftEdgesEffect, SOFTEDGE$54, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSoftEdgesEffect insertNewSoftEdge(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSoftEdgesEffect cTSoftEdgesEffect = null;
            cTSoftEdgesEffect = (CTSoftEdgesEffect)this.get_store().insert_element_user(SOFTEDGE$54, n);
            return cTSoftEdgesEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSoftEdgesEffect addNewSoftEdge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSoftEdgesEffect cTSoftEdgesEffect = null;
            cTSoftEdgesEffect = (CTSoftEdgesEffect)this.get_store().add_element_user(SOFTEDGE$54);
            return cTSoftEdgesEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSoftEdge(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SOFTEDGE$54, n);
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
                    return CTEffectContainerImpl.this.getTintArray(n);
                }

                @Override
                public CTTintEffect set(int n, CTTintEffect cTTintEffect) {
                    CTTintEffect cTTintEffect2 = CTEffectContainerImpl.this.getTintArray(n);
                    CTEffectContainerImpl.this.setTintArray(n, cTTintEffect);
                    return cTTintEffect2;
                }

                @Override
                public void add(int n, CTTintEffect cTTintEffect) {
                    CTEffectContainerImpl.this.insertNewTint(n).set((XmlObject)cTTintEffect);
                }

                @Override
                public CTTintEffect remove(int n) {
                    CTTintEffect cTTintEffect = CTEffectContainerImpl.this.getTintArray(n);
                    CTEffectContainerImpl.this.removeTint(n);
                    return cTTintEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfTintArray();
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
            this.get_store().find_all_element_users(TINT$56, arrayList);
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
            cTTintEffect = (CTTintEffect)this.get_store().find_element_user(TINT$56, n);
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
            return this.get_store().count_elements(TINT$56);
        }
    }

    @Override
    public void setTintArray(CTTintEffect[] cTTintEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTTintEffectArray, TINT$56);
    }

    @Override
    public void setTintArray(int n, CTTintEffect cTTintEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTTintEffect, TINT$56, n, (short)2);
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
            cTTintEffect = (CTTintEffect)this.get_store().insert_element_user(TINT$56, n);
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
            cTTintEffect = (CTTintEffect)this.get_store().add_element_user(TINT$56);
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
            this.get_store().remove_element(TINT$56, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTransformEffect> getXfrmList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class XfrmList
            extends AbstractList<CTTransformEffect> {
                XfrmList() {
                }

                @Override
                public CTTransformEffect get(int n) {
                    return CTEffectContainerImpl.this.getXfrmArray(n);
                }

                @Override
                public CTTransformEffect set(int n, CTTransformEffect cTTransformEffect) {
                    CTTransformEffect cTTransformEffect2 = CTEffectContainerImpl.this.getXfrmArray(n);
                    CTEffectContainerImpl.this.setXfrmArray(n, cTTransformEffect);
                    return cTTransformEffect2;
                }

                @Override
                public void add(int n, CTTransformEffect cTTransformEffect) {
                    CTEffectContainerImpl.this.insertNewXfrm(n).set((XmlObject)cTTransformEffect);
                }

                @Override
                public CTTransformEffect remove(int n) {
                    CTTransformEffect cTTransformEffect = CTEffectContainerImpl.this.getXfrmArray(n);
                    CTEffectContainerImpl.this.removeXfrm(n);
                    return cTTransformEffect;
                }

                @Override
                public int size() {
                    return CTEffectContainerImpl.this.sizeOfXfrmArray();
                }
            }
            return new XfrmList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTransformEffect[] getXfrmArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(XFRM$58, arrayList);
            CTTransformEffect[] cTTransformEffectArray = new CTTransformEffect[arrayList.size()];
            arrayList.toArray(cTTransformEffectArray);
            return cTTransformEffectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTransformEffect getXfrmArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTransformEffect cTTransformEffect = null;
            cTTransformEffect = (CTTransformEffect)this.get_store().find_element_user(XFRM$58, n);
            if (cTTransformEffect == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTransformEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfXfrmArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XFRM$58);
        }
    }

    @Override
    public void setXfrmArray(CTTransformEffect[] cTTransformEffectArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTTransformEffectArray, XFRM$58);
    }

    @Override
    public void setXfrmArray(int n, CTTransformEffect cTTransformEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTTransformEffect, XFRM$58, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTransformEffect insertNewXfrm(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTransformEffect cTTransformEffect = null;
            cTTransformEffect = (CTTransformEffect)this.get_store().insert_element_user(XFRM$58, n);
            return cTTransformEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTransformEffect addNewXfrm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTransformEffect cTTransformEffect = null;
            cTTransformEffect = (CTTransformEffect)this.get_store().add_element_user(XFRM$58);
            return cTTransformEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeXfrm(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XFRM$58, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEffectContainerType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$60));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(TYPE$60));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STEffectContainerType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEffectContainerType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEffectContainerType sTEffectContainerType = null;
            sTEffectContainerType = (STEffectContainerType)this.get_store().find_attribute_user(TYPE$60);
            if (sTEffectContainerType == null) {
                sTEffectContainerType = (STEffectContainerType)this.get_default_attribute_value(TYPE$60);
            }
            return sTEffectContainerType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPE$60) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STEffectContainerType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$60));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$60));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(STEffectContainerType sTEffectContainerType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEffectContainerType sTEffectContainerType2 = null;
            sTEffectContainerType2 = (STEffectContainerType)this.get_store().find_attribute_user(TYPE$60);
            if (sTEffectContainerType2 == null) {
                sTEffectContainerType2 = (STEffectContainerType)this.get_store().add_attribute_user(TYPE$60);
            }
            sTEffectContainerType2.set((XmlObject)sTEffectContainerType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPE$60);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$62));
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
    public XmlToken xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken xmlToken = null;
            xmlToken = (XmlToken)((Object)this.get_store().find_attribute_user(NAME$62));
            return xmlToken;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAME$62) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$62));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$62));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(XmlToken xmlToken) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken xmlToken2 = null;
            xmlToken2 = (XmlToken)((Object)this.get_store().find_attribute_user(NAME$62));
            if (xmlToken2 == null) {
                xmlToken2 = (XmlToken)((Object)this.get_store().add_attribute_user(NAME$62));
            }
            xmlToken2.set(xmlToken);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAME$62);
        }
    }
}

