/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTControl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTPictureBaseImpl;

public class CTPictureImpl
extends CTPictureBaseImpl
implements CTPicture {
    private static final long serialVersionUID = 1L;
    private static final QName MOVIE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "movie");
    private static final QName CONTROL$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "control");

    public CTPictureImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel getMovie() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().find_element_user(MOVIE$0, 0));
            if (cTRel == null) {
                return null;
            }
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMovie() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MOVIE$0) != 0;
        }
    }

    @Override
    public void setMovie(CTRel cTRel) {
        this.generatedSetterHelperImpl(cTRel, MOVIE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRel addNewMovie() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRel cTRel = null;
            cTRel = (CTRel)((Object)this.get_store().add_element_user(MOVIE$0));
            return cTRel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMovie() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MOVIE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControl getControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControl cTControl = null;
            cTControl = (CTControl)this.get_store().find_element_user(CONTROL$2, 0);
            if (cTControl == null) {
                return null;
            }
            return cTControl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTROL$2) != 0;
        }
    }

    @Override
    public void setControl(CTControl cTControl) {
        this.generatedSetterHelperImpl((XmlObject)cTControl, CONTROL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControl addNewControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControl cTControl = null;
            cTControl = (CTControl)this.get_store().add_element_user(CONTROL$2);
            return cTControl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTROL$2, 0);
        }
    }
}

