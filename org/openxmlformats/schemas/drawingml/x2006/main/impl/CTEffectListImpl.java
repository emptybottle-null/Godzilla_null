/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;

public class CTEffectListImpl
extends XmlComplexContentImpl
implements CTEffectList {
    private static final long serialVersionUID = 1L;
    private static final QName BLUR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blur");
    private static final QName FILLOVERLAY$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillOverlay");
    private static final QName GLOW$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "glow");
    private static final QName INNERSHDW$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "innerShdw");
    private static final QName OUTERSHDW$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "outerShdw");
    private static final QName PRSTSHDW$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstShdw");
    private static final QName REFLECTION$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "reflection");
    private static final QName SOFTEDGE$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "softEdge");

    public CTEffectListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlurEffect getBlur() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlurEffect cTBlurEffect = null;
            cTBlurEffect = (CTBlurEffect)this.get_store().find_element_user(BLUR$0, 0);
            if (cTBlurEffect == null) {
                return null;
            }
            return cTBlurEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlur() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLUR$0) != 0;
        }
    }

    @Override
    public void setBlur(CTBlurEffect cTBlurEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTBlurEffect, BLUR$0, 0, (short)1);
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
            cTBlurEffect = (CTBlurEffect)this.get_store().add_element_user(BLUR$0);
            return cTBlurEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlur() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLUR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFillOverlayEffect getFillOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFillOverlayEffect cTFillOverlayEffect = null;
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().find_element_user(FILLOVERLAY$2, 0);
            if (cTFillOverlayEffect == null) {
                return null;
            }
            return cTFillOverlayEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFillOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILLOVERLAY$2) != 0;
        }
    }

    @Override
    public void setFillOverlay(CTFillOverlayEffect cTFillOverlayEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTFillOverlayEffect, FILLOVERLAY$2, 0, (short)1);
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
            cTFillOverlayEffect = (CTFillOverlayEffect)this.get_store().add_element_user(FILLOVERLAY$2);
            return cTFillOverlayEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFillOverlay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILLOVERLAY$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGlowEffect getGlow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGlowEffect cTGlowEffect = null;
            cTGlowEffect = (CTGlowEffect)this.get_store().find_element_user(GLOW$4, 0);
            if (cTGlowEffect == null) {
                return null;
            }
            return cTGlowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGlow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GLOW$4) != 0;
        }
    }

    @Override
    public void setGlow(CTGlowEffect cTGlowEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTGlowEffect, GLOW$4, 0, (short)1);
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
            cTGlowEffect = (CTGlowEffect)this.get_store().add_element_user(GLOW$4);
            return cTGlowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGlow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GLOW$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInnerShadowEffect getInnerShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInnerShadowEffect cTInnerShadowEffect = null;
            cTInnerShadowEffect = (CTInnerShadowEffect)this.get_store().find_element_user(INNERSHDW$6, 0);
            if (cTInnerShadowEffect == null) {
                return null;
            }
            return cTInnerShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInnerShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INNERSHDW$6) != 0;
        }
    }

    @Override
    public void setInnerShdw(CTInnerShadowEffect cTInnerShadowEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTInnerShadowEffect, INNERSHDW$6, 0, (short)1);
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
            cTInnerShadowEffect = (CTInnerShadowEffect)this.get_store().add_element_user(INNERSHDW$6);
            return cTInnerShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInnerShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INNERSHDW$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOuterShadowEffect getOuterShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOuterShadowEffect cTOuterShadowEffect = null;
            cTOuterShadowEffect = (CTOuterShadowEffect)((Object)this.get_store().find_element_user(OUTERSHDW$8, 0));
            if (cTOuterShadowEffect == null) {
                return null;
            }
            return cTOuterShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOuterShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OUTERSHDW$8) != 0;
        }
    }

    @Override
    public void setOuterShdw(CTOuterShadowEffect cTOuterShadowEffect) {
        this.generatedSetterHelperImpl(cTOuterShadowEffect, OUTERSHDW$8, 0, (short)1);
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
            cTOuterShadowEffect = (CTOuterShadowEffect)((Object)this.get_store().add_element_user(OUTERSHDW$8));
            return cTOuterShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOuterShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OUTERSHDW$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetShadowEffect getPrstShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetShadowEffect cTPresetShadowEffect = null;
            cTPresetShadowEffect = (CTPresetShadowEffect)this.get_store().find_element_user(PRSTSHDW$10, 0);
            if (cTPresetShadowEffect == null) {
                return null;
            }
            return cTPresetShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrstShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTSHDW$10) != 0;
        }
    }

    @Override
    public void setPrstShdw(CTPresetShadowEffect cTPresetShadowEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTPresetShadowEffect, PRSTSHDW$10, 0, (short)1);
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
            cTPresetShadowEffect = (CTPresetShadowEffect)this.get_store().add_element_user(PRSTSHDW$10);
            return cTPresetShadowEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrstShdw() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTSHDW$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTReflectionEffect getReflection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTReflectionEffect cTReflectionEffect = null;
            cTReflectionEffect = (CTReflectionEffect)this.get_store().find_element_user(REFLECTION$12, 0);
            if (cTReflectionEffect == null) {
                return null;
            }
            return cTReflectionEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetReflection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REFLECTION$12) != 0;
        }
    }

    @Override
    public void setReflection(CTReflectionEffect cTReflectionEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTReflectionEffect, REFLECTION$12, 0, (short)1);
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
            cTReflectionEffect = (CTReflectionEffect)this.get_store().add_element_user(REFLECTION$12);
            return cTReflectionEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetReflection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REFLECTION$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSoftEdgesEffect getSoftEdge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSoftEdgesEffect cTSoftEdgesEffect = null;
            cTSoftEdgesEffect = (CTSoftEdgesEffect)this.get_store().find_element_user(SOFTEDGE$14, 0);
            if (cTSoftEdgesEffect == null) {
                return null;
            }
            return cTSoftEdgesEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSoftEdge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SOFTEDGE$14) != 0;
        }
    }

    @Override
    public void setSoftEdge(CTSoftEdgesEffect cTSoftEdgesEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTSoftEdgesEffect, SOFTEDGE$14, 0, (short)1);
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
            cTSoftEdgesEffect = (CTSoftEdgesEffect)this.get_store().add_element_user(SOFTEDGE$14);
            return cTSoftEdgesEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSoftEdge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SOFTEDGE$14, 0);
        }
    }
}

