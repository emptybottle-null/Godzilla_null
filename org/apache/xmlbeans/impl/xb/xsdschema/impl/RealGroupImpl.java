/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RealGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.GroupImpl;

public class RealGroupImpl
extends GroupImpl
implements RealGroup {
    private static final long serialVersionUID = 1L;
    private static final QName ALL$0 = new QName("http://www.w3.org/2001/XMLSchema", "all");
    private static final QName CHOICE$2 = new QName("http://www.w3.org/2001/XMLSchema", "choice");
    private static final QName SEQUENCE$4 = new QName("http://www.w3.org/2001/XMLSchema", "sequence");

    public RealGroupImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All[] getAllArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ALL$0, targetList);
            All[] result = new All[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All getAllArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().find_element_user(ALL$0, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAllArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALL$0);
        }
    }

    @Override
    public void setAllArray(All[] allArray) {
        this.check_orphaned();
        this.arraySetterHelper(allArray, ALL$0);
    }

    @Override
    public void setAllArray(int i, All all) {
        this.generatedSetterHelperImpl(all, ALL$0, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All insertNewAll(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().insert_element_user(ALL$0, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All addNewAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().add_element_user(ALL$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAll(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALL$0, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup[] getChoiceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(CHOICE$2, targetList);
            ExplicitGroup[] result = new ExplicitGroup[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getChoiceArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(CHOICE$2, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfChoiceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHOICE$2);
        }
    }

    @Override
    public void setChoiceArray(ExplicitGroup[] choiceArray) {
        this.check_orphaned();
        this.arraySetterHelper(choiceArray, CHOICE$2);
    }

    @Override
    public void setChoiceArray(int i, ExplicitGroup choice) {
        this.generatedSetterHelperImpl(choice, CHOICE$2, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup insertNewChoice(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().insert_element_user(CHOICE$2, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup addNewChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(CHOICE$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeChoice(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHOICE$2, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup[] getSequenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(SEQUENCE$4, targetList);
            ExplicitGroup[] result = new ExplicitGroup[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getSequenceArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(SEQUENCE$4, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSequenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEQUENCE$4);
        }
    }

    @Override
    public void setSequenceArray(ExplicitGroup[] sequenceArray) {
        this.check_orphaned();
        this.arraySetterHelper(sequenceArray, SEQUENCE$4);
    }

    @Override
    public void setSequenceArray(int i, ExplicitGroup sequence) {
        this.generatedSetterHelperImpl(sequence, SEQUENCE$4, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup insertNewSequence(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().insert_element_user(SEQUENCE$4, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup addNewSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(SEQUENCE$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSequence(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEQUENCE$4, i);
        }
    }
}

