/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;

public class CTRubyImpl
extends XmlComplexContentImpl
implements CTRuby {
    private static final long serialVersionUID = 1L;
    private static final QName RUBYPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rubyPr");
    private static final QName RT$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rt");
    private static final QName RUBYBASE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rubyBase");

    public CTRubyImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyPr getRubyPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyPr cTRubyPr = null;
            cTRubyPr = (CTRubyPr)((Object)this.get_store().find_element_user(RUBYPR$0, 0));
            if (cTRubyPr == null) {
                return null;
            }
            return cTRubyPr;
        }
    }

    @Override
    public void setRubyPr(CTRubyPr cTRubyPr) {
        this.generatedSetterHelperImpl(cTRubyPr, RUBYPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyPr addNewRubyPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyPr cTRubyPr = null;
            cTRubyPr = (CTRubyPr)((Object)this.get_store().add_element_user(RUBYPR$0));
            return cTRubyPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyContent getRt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyContent cTRubyContent = null;
            cTRubyContent = (CTRubyContent)((Object)this.get_store().find_element_user(RT$2, 0));
            if (cTRubyContent == null) {
                return null;
            }
            return cTRubyContent;
        }
    }

    @Override
    public void setRt(CTRubyContent cTRubyContent) {
        this.generatedSetterHelperImpl(cTRubyContent, RT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyContent addNewRt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyContent cTRubyContent = null;
            cTRubyContent = (CTRubyContent)((Object)this.get_store().add_element_user(RT$2));
            return cTRubyContent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyContent getRubyBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyContent cTRubyContent = null;
            cTRubyContent = (CTRubyContent)((Object)this.get_store().find_element_user(RUBYBASE$4, 0));
            if (cTRubyContent == null) {
                return null;
            }
            return cTRubyContent;
        }
    }

    @Override
    public void setRubyBase(CTRubyContent cTRubyContent) {
        this.generatedSetterHelperImpl(cTRubyContent, RUBYBASE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRubyContent addNewRubyBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRubyContent cTRubyContent = null;
            cTRubyContent = (CTRubyContent)((Object)this.get_store().add_element_user(RUBYBASE$4));
            return cTRubyContent;
        }
    }
}

