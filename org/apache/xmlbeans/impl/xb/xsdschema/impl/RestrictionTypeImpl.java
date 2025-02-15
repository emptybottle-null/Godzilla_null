/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class RestrictionTypeImpl
extends AnnotatedImpl
implements RestrictionType {
    private static final long serialVersionUID = 1L;
    private static final QName GROUP$0 = new QName("http://www.w3.org/2001/XMLSchema", "group");
    private static final QName ALL$2 = new QName("http://www.w3.org/2001/XMLSchema", "all");
    private static final QName CHOICE$4 = new QName("http://www.w3.org/2001/XMLSchema", "choice");
    private static final QName SEQUENCE$6 = new QName("http://www.w3.org/2001/XMLSchema", "sequence");
    private static final QName SIMPLETYPE$8 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
    private static final QName MINEXCLUSIVE$10 = new QName("http://www.w3.org/2001/XMLSchema", "minExclusive");
    private static final QName MININCLUSIVE$12 = new QName("http://www.w3.org/2001/XMLSchema", "minInclusive");
    private static final QName MAXEXCLUSIVE$14 = new QName("http://www.w3.org/2001/XMLSchema", "maxExclusive");
    private static final QName MAXINCLUSIVE$16 = new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive");
    private static final QName TOTALDIGITS$18 = new QName("http://www.w3.org/2001/XMLSchema", "totalDigits");
    private static final QName FRACTIONDIGITS$20 = new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits");
    private static final QName LENGTH$22 = new QName("http://www.w3.org/2001/XMLSchema", "length");
    private static final QName MINLENGTH$24 = new QName("http://www.w3.org/2001/XMLSchema", "minLength");
    private static final QName MAXLENGTH$26 = new QName("http://www.w3.org/2001/XMLSchema", "maxLength");
    private static final QName ENUMERATION$28 = new QName("http://www.w3.org/2001/XMLSchema", "enumeration");
    private static final QName WHITESPACE$30 = new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace");
    private static final QName PATTERN$32 = new QName("http://www.w3.org/2001/XMLSchema", "pattern");
    private static final QName ATTRIBUTE$34 = new QName("http://www.w3.org/2001/XMLSchema", "attribute");
    private static final QName ATTRIBUTEGROUP$36 = new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup");
    private static final QName ANYATTRIBUTE$38 = new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute");
    private static final QName BASE$40 = new QName("", "base");

    public RestrictionTypeImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef getGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GroupRef target = null;
            target = (GroupRef)((Object)this.get_store().find_element_user(GROUP$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUP$0) != 0;
        }
    }

    @Override
    public void setGroup(GroupRef group) {
        this.generatedSetterHelperImpl(group, GROUP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef addNewGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GroupRef target = null;
            target = (GroupRef)((Object)this.get_store().add_element_user(GROUP$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUP$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All getAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().find_element_user(ALL$2, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALL$2) != 0;
        }
    }

    @Override
    public void setAll(All all) {
        this.generatedSetterHelperImpl(all, ALL$2, 0, (short)1);
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
            target = (All)((Object)this.get_store().add_element_user(ALL$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALL$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(CHOICE$4, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHOICE$4) != 0;
        }
    }

    @Override
    public void setChoice(ExplicitGroup choice) {
        this.generatedSetterHelperImpl(choice, CHOICE$4, 0, (short)1);
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
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(CHOICE$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHOICE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(SEQUENCE$6, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEQUENCE$6) != 0;
        }
    }

    @Override
    public void setSequence(ExplicitGroup sequence) {
        this.generatedSetterHelperImpl(sequence, SEQUENCE$6, 0, (short)1);
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
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(SEQUENCE$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEQUENCE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalSimpleType getSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalSimpleType target = null;
            target = (LocalSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$8, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIMPLETYPE$8) != 0;
        }
    }

    @Override
    public void setSimpleType(LocalSimpleType simpleType) {
        this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalSimpleType addNewSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalSimpleType target = null;
            target = (LocalSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIMPLETYPE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet[] getMinExclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(MINEXCLUSIVE$10, targetList);
            Facet[] result = new Facet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMinExclusiveArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MINEXCLUSIVE$10, i));
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
    public int sizeOfMinExclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MINEXCLUSIVE$10);
        }
    }

    @Override
    public void setMinExclusiveArray(Facet[] minExclusiveArray) {
        this.check_orphaned();
        this.arraySetterHelper(minExclusiveArray, MINEXCLUSIVE$10);
    }

    @Override
    public void setMinExclusiveArray(int i, Facet minExclusive) {
        this.generatedSetterHelperImpl(minExclusive, MINEXCLUSIVE$10, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet insertNewMinExclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().insert_element_user(MINEXCLUSIVE$10, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMinExclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MINEXCLUSIVE$10));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMinExclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MINEXCLUSIVE$10, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet[] getMinInclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(MININCLUSIVE$12, targetList);
            Facet[] result = new Facet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMinInclusiveArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MININCLUSIVE$12, i));
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
    public int sizeOfMinInclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MININCLUSIVE$12);
        }
    }

    @Override
    public void setMinInclusiveArray(Facet[] minInclusiveArray) {
        this.check_orphaned();
        this.arraySetterHelper(minInclusiveArray, MININCLUSIVE$12);
    }

    @Override
    public void setMinInclusiveArray(int i, Facet minInclusive) {
        this.generatedSetterHelperImpl(minInclusive, MININCLUSIVE$12, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet insertNewMinInclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().insert_element_user(MININCLUSIVE$12, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMinInclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MININCLUSIVE$12));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMinInclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MININCLUSIVE$12, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet[] getMaxExclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(MAXEXCLUSIVE$14, targetList);
            Facet[] result = new Facet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMaxExclusiveArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MAXEXCLUSIVE$14, i));
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
    public int sizeOfMaxExclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAXEXCLUSIVE$14);
        }
    }

    @Override
    public void setMaxExclusiveArray(Facet[] maxExclusiveArray) {
        this.check_orphaned();
        this.arraySetterHelper(maxExclusiveArray, MAXEXCLUSIVE$14);
    }

    @Override
    public void setMaxExclusiveArray(int i, Facet maxExclusive) {
        this.generatedSetterHelperImpl(maxExclusive, MAXEXCLUSIVE$14, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet insertNewMaxExclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().insert_element_user(MAXEXCLUSIVE$14, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMaxExclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MAXEXCLUSIVE$14));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMaxExclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAXEXCLUSIVE$14, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet[] getMaxInclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(MAXINCLUSIVE$16, targetList);
            Facet[] result = new Facet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMaxInclusiveArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MAXINCLUSIVE$16, i));
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
    public int sizeOfMaxInclusiveArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAXINCLUSIVE$16);
        }
    }

    @Override
    public void setMaxInclusiveArray(Facet[] maxInclusiveArray) {
        this.check_orphaned();
        this.arraySetterHelper(maxInclusiveArray, MAXINCLUSIVE$16);
    }

    @Override
    public void setMaxInclusiveArray(int i, Facet maxInclusive) {
        this.generatedSetterHelperImpl(maxInclusive, MAXINCLUSIVE$16, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet insertNewMaxInclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().insert_element_user(MAXINCLUSIVE$16, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMaxInclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MAXINCLUSIVE$16));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMaxInclusive(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAXINCLUSIVE$16, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TotalDigitsDocument.TotalDigits[] getTotalDigitsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(TOTALDIGITS$18, targetList);
            TotalDigitsDocument.TotalDigits[] result = new TotalDigitsDocument.TotalDigits[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TotalDigitsDocument.TotalDigits getTotalDigitsArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TotalDigitsDocument.TotalDigits target = null;
            target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().find_element_user(TOTALDIGITS$18, i));
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
    public int sizeOfTotalDigitsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TOTALDIGITS$18);
        }
    }

    @Override
    public void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArray) {
        this.check_orphaned();
        this.arraySetterHelper(totalDigitsArray, TOTALDIGITS$18);
    }

    @Override
    public void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits) {
        this.generatedSetterHelperImpl(totalDigits, TOTALDIGITS$18, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TotalDigitsDocument.TotalDigits insertNewTotalDigits(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TotalDigitsDocument.TotalDigits target = null;
            target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().insert_element_user(TOTALDIGITS$18, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TotalDigitsDocument.TotalDigits target = null;
            target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().add_element_user(TOTALDIGITS$18));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTotalDigits(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TOTALDIGITS$18, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet[] getFractionDigitsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(FRACTIONDIGITS$20, targetList);
            NumFacet[] result = new NumFacet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet getFractionDigitsArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().find_element_user(FRACTIONDIGITS$20, i));
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
    public int sizeOfFractionDigitsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FRACTIONDIGITS$20);
        }
    }

    @Override
    public void setFractionDigitsArray(NumFacet[] fractionDigitsArray) {
        this.check_orphaned();
        this.arraySetterHelper(fractionDigitsArray, FRACTIONDIGITS$20);
    }

    @Override
    public void setFractionDigitsArray(int i, NumFacet fractionDigits) {
        this.generatedSetterHelperImpl(fractionDigits, FRACTIONDIGITS$20, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet insertNewFractionDigits(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().insert_element_user(FRACTIONDIGITS$20, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet addNewFractionDigits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().add_element_user(FRACTIONDIGITS$20));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFractionDigits(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FRACTIONDIGITS$20, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet[] getLengthArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(LENGTH$22, targetList);
            NumFacet[] result = new NumFacet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet getLengthArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().find_element_user(LENGTH$22, i));
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
    public int sizeOfLengthArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LENGTH$22);
        }
    }

    @Override
    public void setLengthArray(NumFacet[] lengthArray) {
        this.check_orphaned();
        this.arraySetterHelper(lengthArray, LENGTH$22);
    }

    @Override
    public void setLengthArray(int i, NumFacet length) {
        this.generatedSetterHelperImpl(length, LENGTH$22, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet insertNewLength(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().insert_element_user(LENGTH$22, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet addNewLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().add_element_user(LENGTH$22));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLength(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LENGTH$22, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet[] getMinLengthArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(MINLENGTH$24, targetList);
            NumFacet[] result = new NumFacet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet getMinLengthArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().find_element_user(MINLENGTH$24, i));
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
    public int sizeOfMinLengthArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MINLENGTH$24);
        }
    }

    @Override
    public void setMinLengthArray(NumFacet[] minLengthArray) {
        this.check_orphaned();
        this.arraySetterHelper(minLengthArray, MINLENGTH$24);
    }

    @Override
    public void setMinLengthArray(int i, NumFacet minLength) {
        this.generatedSetterHelperImpl(minLength, MINLENGTH$24, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet insertNewMinLength(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().insert_element_user(MINLENGTH$24, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet addNewMinLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().add_element_user(MINLENGTH$24));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMinLength(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MINLENGTH$24, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet[] getMaxLengthArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(MAXLENGTH$26, targetList);
            NumFacet[] result = new NumFacet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet getMaxLengthArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().find_element_user(MAXLENGTH$26, i));
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
    public int sizeOfMaxLengthArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAXLENGTH$26);
        }
    }

    @Override
    public void setMaxLengthArray(NumFacet[] maxLengthArray) {
        this.check_orphaned();
        this.arraySetterHelper(maxLengthArray, MAXLENGTH$26);
    }

    @Override
    public void setMaxLengthArray(int i, NumFacet maxLength) {
        this.generatedSetterHelperImpl(maxLength, MAXLENGTH$26, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet insertNewMaxLength(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().insert_element_user(MAXLENGTH$26, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet addNewMaxLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().add_element_user(MAXLENGTH$26));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMaxLength(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAXLENGTH$26, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NoFixedFacet[] getEnumerationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ENUMERATION$28, targetList);
            NoFixedFacet[] result = new NoFixedFacet[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NoFixedFacet getEnumerationArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NoFixedFacet target = null;
            target = (NoFixedFacet)((Object)this.get_store().find_element_user(ENUMERATION$28, i));
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
    public int sizeOfEnumerationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENUMERATION$28);
        }
    }

    @Override
    public void setEnumerationArray(NoFixedFacet[] enumerationArray) {
        this.check_orphaned();
        this.arraySetterHelper(enumerationArray, ENUMERATION$28);
    }

    @Override
    public void setEnumerationArray(int i, NoFixedFacet enumeration) {
        this.generatedSetterHelperImpl(enumeration, ENUMERATION$28, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NoFixedFacet insertNewEnumeration(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NoFixedFacet target = null;
            target = (NoFixedFacet)((Object)this.get_store().insert_element_user(ENUMERATION$28, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NoFixedFacet addNewEnumeration() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NoFixedFacet target = null;
            target = (NoFixedFacet)((Object)this.get_store().add_element_user(ENUMERATION$28));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEnumeration(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENUMERATION$28, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public WhiteSpaceDocument.WhiteSpace[] getWhiteSpaceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(WHITESPACE$30, targetList);
            WhiteSpaceDocument.WhiteSpace[] result = new WhiteSpaceDocument.WhiteSpace[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public WhiteSpaceDocument.WhiteSpace getWhiteSpaceArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            WhiteSpaceDocument.WhiteSpace target = null;
            target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().find_element_user(WHITESPACE$30, i));
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
    public int sizeOfWhiteSpaceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WHITESPACE$30);
        }
    }

    @Override
    public void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArray) {
        this.check_orphaned();
        this.arraySetterHelper(whiteSpaceArray, WHITESPACE$30);
    }

    @Override
    public void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace) {
        this.generatedSetterHelperImpl(whiteSpace, WHITESPACE$30, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public WhiteSpaceDocument.WhiteSpace insertNewWhiteSpace(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            WhiteSpaceDocument.WhiteSpace target = null;
            target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().insert_element_user(WHITESPACE$30, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            WhiteSpaceDocument.WhiteSpace target = null;
            target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().add_element_user(WHITESPACE$30));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeWhiteSpace(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WHITESPACE$30, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PatternDocument.Pattern[] getPatternArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(PATTERN$32, targetList);
            PatternDocument.Pattern[] result = new PatternDocument.Pattern[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PatternDocument.Pattern getPatternArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PatternDocument.Pattern target = null;
            target = (PatternDocument.Pattern)((Object)this.get_store().find_element_user(PATTERN$32, i));
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
    public int sizeOfPatternArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATTERN$32);
        }
    }

    @Override
    public void setPatternArray(PatternDocument.Pattern[] patternArray) {
        this.check_orphaned();
        this.arraySetterHelper(patternArray, PATTERN$32);
    }

    @Override
    public void setPatternArray(int i, PatternDocument.Pattern pattern) {
        this.generatedSetterHelperImpl(pattern, PATTERN$32, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PatternDocument.Pattern insertNewPattern(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PatternDocument.Pattern target = null;
            target = (PatternDocument.Pattern)((Object)this.get_store().insert_element_user(PATTERN$32, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PatternDocument.Pattern addNewPattern() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PatternDocument.Pattern target = null;
            target = (PatternDocument.Pattern)((Object)this.get_store().add_element_user(PATTERN$32));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removePattern(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATTERN$32, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute[] getAttributeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTE$34, targetList);
            Attribute[] result = new Attribute[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute getAttributeArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute target = null;
            target = (Attribute)((Object)this.get_store().find_element_user(ATTRIBUTE$34, i));
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
    public int sizeOfAttributeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTE$34);
        }
    }

    @Override
    public void setAttributeArray(Attribute[] attributeArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeArray, ATTRIBUTE$34);
    }

    @Override
    public void setAttributeArray(int i, Attribute attribute) {
        this.generatedSetterHelperImpl(attribute, ATTRIBUTE$34, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute insertNewAttribute(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute target = null;
            target = (Attribute)((Object)this.get_store().insert_element_user(ATTRIBUTE$34, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute addNewAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute target = null;
            target = (Attribute)((Object)this.get_store().add_element_user(ATTRIBUTE$34));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttribute(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTE$34, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef[] getAttributeGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTEGROUP$36, targetList);
            AttributeGroupRef[] result = new AttributeGroupRef[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef getAttributeGroupArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttributeGroupRef target = null;
            target = (AttributeGroupRef)((Object)this.get_store().find_element_user(ATTRIBUTEGROUP$36, i));
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
    public int sizeOfAttributeGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTEGROUP$36);
        }
    }

    @Override
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeGroupArray, ATTRIBUTEGROUP$36);
    }

    @Override
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        this.generatedSetterHelperImpl(attributeGroup, ATTRIBUTEGROUP$36, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef insertNewAttributeGroup(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttributeGroupRef target = null;
            target = (AttributeGroupRef)((Object)this.get_store().insert_element_user(ATTRIBUTEGROUP$36, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef addNewAttributeGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttributeGroupRef target = null;
            target = (AttributeGroupRef)((Object)this.get_store().add_element_user(ATTRIBUTEGROUP$36));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttributeGroup(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTEGROUP$36, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard getAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard target = null;
            target = (Wildcard)((Object)this.get_store().find_element_user(ANYATTRIBUTE$38, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANYATTRIBUTE$38) != 0;
        }
    }

    @Override
    public void setAnyAttribute(Wildcard anyAttribute) {
        this.generatedSetterHelperImpl(anyAttribute, ANYATTRIBUTE$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard addNewAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard target = null;
            target = (Wildcard)((Object)this.get_store().add_element_user(ANYATTRIBUTE$38));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANYATTRIBUTE$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BASE$40));
            if (target == null) {
                return null;
            }
            return target.getQNameValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlQName xgetBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(BASE$40));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBase(QName base) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BASE$40));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(BASE$40));
            }
            target.setQNameValue(base);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBase(XmlQName base) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(BASE$40));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(BASE$40));
            }
            target.set(base);
        }
    }
}

