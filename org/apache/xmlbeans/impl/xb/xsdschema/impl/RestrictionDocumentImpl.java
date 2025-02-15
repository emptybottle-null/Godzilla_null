/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class RestrictionDocumentImpl
extends XmlComplexContentImpl
implements RestrictionDocument {
    private static final long serialVersionUID = 1L;
    private static final QName RESTRICTION$0 = new QName("http://www.w3.org/2001/XMLSchema", "restriction");

    public RestrictionDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RestrictionDocument.Restriction getRestriction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RestrictionDocument.Restriction target = null;
            target = (RestrictionDocument.Restriction)((Object)this.get_store().find_element_user(RESTRICTION$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setRestriction(RestrictionDocument.Restriction restriction) {
        this.generatedSetterHelperImpl(restriction, RESTRICTION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RestrictionDocument.Restriction addNewRestriction() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RestrictionDocument.Restriction target = null;
            target = (RestrictionDocument.Restriction)((Object)this.get_store().add_element_user(RESTRICTION$0));
            return target;
        }
    }

    public static class RestrictionImpl
    extends AnnotatedImpl
    implements RestrictionDocument.Restriction {
        private static final long serialVersionUID = 1L;
        private static final QName SIMPLETYPE$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
        private static final QName MINEXCLUSIVE$2 = new QName("http://www.w3.org/2001/XMLSchema", "minExclusive");
        private static final QName MININCLUSIVE$4 = new QName("http://www.w3.org/2001/XMLSchema", "minInclusive");
        private static final QName MAXEXCLUSIVE$6 = new QName("http://www.w3.org/2001/XMLSchema", "maxExclusive");
        private static final QName MAXINCLUSIVE$8 = new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive");
        private static final QName TOTALDIGITS$10 = new QName("http://www.w3.org/2001/XMLSchema", "totalDigits");
        private static final QName FRACTIONDIGITS$12 = new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits");
        private static final QName LENGTH$14 = new QName("http://www.w3.org/2001/XMLSchema", "length");
        private static final QName MINLENGTH$16 = new QName("http://www.w3.org/2001/XMLSchema", "minLength");
        private static final QName MAXLENGTH$18 = new QName("http://www.w3.org/2001/XMLSchema", "maxLength");
        private static final QName ENUMERATION$20 = new QName("http://www.w3.org/2001/XMLSchema", "enumeration");
        private static final QName WHITESPACE$22 = new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace");
        private static final QName PATTERN$24 = new QName("http://www.w3.org/2001/XMLSchema", "pattern");
        private static final QName BASE$26 = new QName("", "base");

        public RestrictionImpl(SchemaType sType) {
            super(sType);
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
                target = (LocalSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$0, 0));
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
                return this.get_store().count_elements(SIMPLETYPE$0) != 0;
            }
        }

        @Override
        public void setSimpleType(LocalSimpleType simpleType) {
            this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$0, 0, (short)1);
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
                target = (LocalSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$0));
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
                this.get_store().remove_element(SIMPLETYPE$0, 0);
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
                this.get_store().find_all_element_users(MINEXCLUSIVE$2, targetList);
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
                target = (Facet)((Object)this.get_store().find_element_user(MINEXCLUSIVE$2, i));
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
                return this.get_store().count_elements(MINEXCLUSIVE$2);
            }
        }

        @Override
        public void setMinExclusiveArray(Facet[] minExclusiveArray) {
            this.check_orphaned();
            this.arraySetterHelper(minExclusiveArray, MINEXCLUSIVE$2);
        }

        @Override
        public void setMinExclusiveArray(int i, Facet minExclusive) {
            this.generatedSetterHelperImpl(minExclusive, MINEXCLUSIVE$2, i, (short)2);
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
                target = (Facet)((Object)this.get_store().insert_element_user(MINEXCLUSIVE$2, i));
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
                target = (Facet)((Object)this.get_store().add_element_user(MINEXCLUSIVE$2));
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
                this.get_store().remove_element(MINEXCLUSIVE$2, i);
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
                this.get_store().find_all_element_users(MININCLUSIVE$4, targetList);
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
                target = (Facet)((Object)this.get_store().find_element_user(MININCLUSIVE$4, i));
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
                return this.get_store().count_elements(MININCLUSIVE$4);
            }
        }

        @Override
        public void setMinInclusiveArray(Facet[] minInclusiveArray) {
            this.check_orphaned();
            this.arraySetterHelper(minInclusiveArray, MININCLUSIVE$4);
        }

        @Override
        public void setMinInclusiveArray(int i, Facet minInclusive) {
            this.generatedSetterHelperImpl(minInclusive, MININCLUSIVE$4, i, (short)2);
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
                target = (Facet)((Object)this.get_store().insert_element_user(MININCLUSIVE$4, i));
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
                target = (Facet)((Object)this.get_store().add_element_user(MININCLUSIVE$4));
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
                this.get_store().remove_element(MININCLUSIVE$4, i);
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
                this.get_store().find_all_element_users(MAXEXCLUSIVE$6, targetList);
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
                target = (Facet)((Object)this.get_store().find_element_user(MAXEXCLUSIVE$6, i));
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
                return this.get_store().count_elements(MAXEXCLUSIVE$6);
            }
        }

        @Override
        public void setMaxExclusiveArray(Facet[] maxExclusiveArray) {
            this.check_orphaned();
            this.arraySetterHelper(maxExclusiveArray, MAXEXCLUSIVE$6);
        }

        @Override
        public void setMaxExclusiveArray(int i, Facet maxExclusive) {
            this.generatedSetterHelperImpl(maxExclusive, MAXEXCLUSIVE$6, i, (short)2);
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
                target = (Facet)((Object)this.get_store().insert_element_user(MAXEXCLUSIVE$6, i));
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
                target = (Facet)((Object)this.get_store().add_element_user(MAXEXCLUSIVE$6));
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
                this.get_store().remove_element(MAXEXCLUSIVE$6, i);
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
                this.get_store().find_all_element_users(MAXINCLUSIVE$8, targetList);
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
                target = (Facet)((Object)this.get_store().find_element_user(MAXINCLUSIVE$8, i));
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
                return this.get_store().count_elements(MAXINCLUSIVE$8);
            }
        }

        @Override
        public void setMaxInclusiveArray(Facet[] maxInclusiveArray) {
            this.check_orphaned();
            this.arraySetterHelper(maxInclusiveArray, MAXINCLUSIVE$8);
        }

        @Override
        public void setMaxInclusiveArray(int i, Facet maxInclusive) {
            this.generatedSetterHelperImpl(maxInclusive, MAXINCLUSIVE$8, i, (short)2);
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
                target = (Facet)((Object)this.get_store().insert_element_user(MAXINCLUSIVE$8, i));
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
                target = (Facet)((Object)this.get_store().add_element_user(MAXINCLUSIVE$8));
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
                this.get_store().remove_element(MAXINCLUSIVE$8, i);
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
                this.get_store().find_all_element_users(TOTALDIGITS$10, targetList);
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
                target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().find_element_user(TOTALDIGITS$10, i));
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
                return this.get_store().count_elements(TOTALDIGITS$10);
            }
        }

        @Override
        public void setTotalDigitsArray(TotalDigitsDocument.TotalDigits[] totalDigitsArray) {
            this.check_orphaned();
            this.arraySetterHelper(totalDigitsArray, TOTALDIGITS$10);
        }

        @Override
        public void setTotalDigitsArray(int i, TotalDigitsDocument.TotalDigits totalDigits) {
            this.generatedSetterHelperImpl(totalDigits, TOTALDIGITS$10, i, (short)2);
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
                target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().insert_element_user(TOTALDIGITS$10, i));
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
                target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().add_element_user(TOTALDIGITS$10));
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
                this.get_store().remove_element(TOTALDIGITS$10, i);
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
                this.get_store().find_all_element_users(FRACTIONDIGITS$12, targetList);
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
                target = (NumFacet)((Object)this.get_store().find_element_user(FRACTIONDIGITS$12, i));
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
                return this.get_store().count_elements(FRACTIONDIGITS$12);
            }
        }

        @Override
        public void setFractionDigitsArray(NumFacet[] fractionDigitsArray) {
            this.check_orphaned();
            this.arraySetterHelper(fractionDigitsArray, FRACTIONDIGITS$12);
        }

        @Override
        public void setFractionDigitsArray(int i, NumFacet fractionDigits) {
            this.generatedSetterHelperImpl(fractionDigits, FRACTIONDIGITS$12, i, (short)2);
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
                target = (NumFacet)((Object)this.get_store().insert_element_user(FRACTIONDIGITS$12, i));
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
                target = (NumFacet)((Object)this.get_store().add_element_user(FRACTIONDIGITS$12));
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
                this.get_store().remove_element(FRACTIONDIGITS$12, i);
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
                this.get_store().find_all_element_users(LENGTH$14, targetList);
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
                target = (NumFacet)((Object)this.get_store().find_element_user(LENGTH$14, i));
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
                return this.get_store().count_elements(LENGTH$14);
            }
        }

        @Override
        public void setLengthArray(NumFacet[] lengthArray) {
            this.check_orphaned();
            this.arraySetterHelper(lengthArray, LENGTH$14);
        }

        @Override
        public void setLengthArray(int i, NumFacet length) {
            this.generatedSetterHelperImpl(length, LENGTH$14, i, (short)2);
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
                target = (NumFacet)((Object)this.get_store().insert_element_user(LENGTH$14, i));
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
                target = (NumFacet)((Object)this.get_store().add_element_user(LENGTH$14));
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
                this.get_store().remove_element(LENGTH$14, i);
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
                this.get_store().find_all_element_users(MINLENGTH$16, targetList);
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
                target = (NumFacet)((Object)this.get_store().find_element_user(MINLENGTH$16, i));
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
                return this.get_store().count_elements(MINLENGTH$16);
            }
        }

        @Override
        public void setMinLengthArray(NumFacet[] minLengthArray) {
            this.check_orphaned();
            this.arraySetterHelper(minLengthArray, MINLENGTH$16);
        }

        @Override
        public void setMinLengthArray(int i, NumFacet minLength) {
            this.generatedSetterHelperImpl(minLength, MINLENGTH$16, i, (short)2);
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
                target = (NumFacet)((Object)this.get_store().insert_element_user(MINLENGTH$16, i));
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
                target = (NumFacet)((Object)this.get_store().add_element_user(MINLENGTH$16));
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
                this.get_store().remove_element(MINLENGTH$16, i);
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
                this.get_store().find_all_element_users(MAXLENGTH$18, targetList);
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
                target = (NumFacet)((Object)this.get_store().find_element_user(MAXLENGTH$18, i));
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
                return this.get_store().count_elements(MAXLENGTH$18);
            }
        }

        @Override
        public void setMaxLengthArray(NumFacet[] maxLengthArray) {
            this.check_orphaned();
            this.arraySetterHelper(maxLengthArray, MAXLENGTH$18);
        }

        @Override
        public void setMaxLengthArray(int i, NumFacet maxLength) {
            this.generatedSetterHelperImpl(maxLength, MAXLENGTH$18, i, (short)2);
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
                target = (NumFacet)((Object)this.get_store().insert_element_user(MAXLENGTH$18, i));
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
                target = (NumFacet)((Object)this.get_store().add_element_user(MAXLENGTH$18));
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
                this.get_store().remove_element(MAXLENGTH$18, i);
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
                this.get_store().find_all_element_users(ENUMERATION$20, targetList);
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
                target = (NoFixedFacet)((Object)this.get_store().find_element_user(ENUMERATION$20, i));
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
                return this.get_store().count_elements(ENUMERATION$20);
            }
        }

        @Override
        public void setEnumerationArray(NoFixedFacet[] enumerationArray) {
            this.check_orphaned();
            this.arraySetterHelper(enumerationArray, ENUMERATION$20);
        }

        @Override
        public void setEnumerationArray(int i, NoFixedFacet enumeration) {
            this.generatedSetterHelperImpl(enumeration, ENUMERATION$20, i, (short)2);
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
                target = (NoFixedFacet)((Object)this.get_store().insert_element_user(ENUMERATION$20, i));
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
                target = (NoFixedFacet)((Object)this.get_store().add_element_user(ENUMERATION$20));
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
                this.get_store().remove_element(ENUMERATION$20, i);
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
                this.get_store().find_all_element_users(WHITESPACE$22, targetList);
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
                target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().find_element_user(WHITESPACE$22, i));
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
                return this.get_store().count_elements(WHITESPACE$22);
            }
        }

        @Override
        public void setWhiteSpaceArray(WhiteSpaceDocument.WhiteSpace[] whiteSpaceArray) {
            this.check_orphaned();
            this.arraySetterHelper(whiteSpaceArray, WHITESPACE$22);
        }

        @Override
        public void setWhiteSpaceArray(int i, WhiteSpaceDocument.WhiteSpace whiteSpace) {
            this.generatedSetterHelperImpl(whiteSpace, WHITESPACE$22, i, (short)2);
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
                target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().insert_element_user(WHITESPACE$22, i));
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
                target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().add_element_user(WHITESPACE$22));
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
                this.get_store().remove_element(WHITESPACE$22, i);
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
                this.get_store().find_all_element_users(PATTERN$24, targetList);
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
                target = (PatternDocument.Pattern)((Object)this.get_store().find_element_user(PATTERN$24, i));
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
                return this.get_store().count_elements(PATTERN$24);
            }
        }

        @Override
        public void setPatternArray(PatternDocument.Pattern[] patternArray) {
            this.check_orphaned();
            this.arraySetterHelper(patternArray, PATTERN$24);
        }

        @Override
        public void setPatternArray(int i, PatternDocument.Pattern pattern) {
            this.generatedSetterHelperImpl(pattern, PATTERN$24, i, (short)2);
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
                target = (PatternDocument.Pattern)((Object)this.get_store().insert_element_user(PATTERN$24, i));
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
                target = (PatternDocument.Pattern)((Object)this.get_store().add_element_user(PATTERN$24));
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
                this.get_store().remove_element(PATTERN$24, i);
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(BASE$26));
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
                target = (XmlQName)((Object)this.get_store().find_attribute_user(BASE$26));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetBase() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(BASE$26) != null;
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(BASE$26));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(BASE$26));
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
                target = (XmlQName)((Object)this.get_store().find_attribute_user(BASE$26));
                if (target == null) {
                    target = (XmlQName)((Object)this.get_store().add_attribute_user(BASE$26));
                }
                target.set(base);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetBase() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(BASE$26);
            }
        }
    }
}

