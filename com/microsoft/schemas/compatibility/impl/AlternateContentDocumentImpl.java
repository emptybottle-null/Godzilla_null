/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.compatibility.impl;

import com.microsoft.schemas.compatibility.AlternateContentDocument;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class AlternateContentDocumentImpl
extends XmlComplexContentImpl
implements AlternateContentDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ALTERNATECONTENT$0 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "AlternateContent");

    public AlternateContentDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AlternateContentDocument.AlternateContent getAlternateContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AlternateContentDocument.AlternateContent alternateContent = null;
            alternateContent = (AlternateContentDocument.AlternateContent)((Object)this.get_store().find_element_user(ALTERNATECONTENT$0, 0));
            if (alternateContent == null) {
                return null;
            }
            return alternateContent;
        }
    }

    @Override
    public void setAlternateContent(AlternateContentDocument.AlternateContent alternateContent) {
        this.generatedSetterHelperImpl(alternateContent, ALTERNATECONTENT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AlternateContentDocument.AlternateContent addNewAlternateContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AlternateContentDocument.AlternateContent alternateContent = null;
            alternateContent = (AlternateContentDocument.AlternateContent)((Object)this.get_store().add_element_user(ALTERNATECONTENT$0));
            return alternateContent;
        }
    }

    public static class AlternateContentImpl
    extends XmlComplexContentImpl
    implements AlternateContentDocument.AlternateContent {
        private static final long serialVersionUID = 1L;
        private static final QName CHOICE$0 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Choice");
        private static final QName FALLBACK$2 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Fallback");
        private static final QName IGNORABLE$4 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Ignorable");
        private static final QName MUSTUNDERSTAND$6 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "MustUnderstand");
        private static final QName PROCESSCONTENT$8 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "ProcessContent");

        public AlternateContentImpl(SchemaType schemaType) {
            super(schemaType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public List<AlternateContentDocument.AlternateContent.Choice> getChoiceList() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                final class ChoiceList
                extends AbstractList<AlternateContentDocument.AlternateContent.Choice> {
                    ChoiceList() {
                    }

                    @Override
                    public AlternateContentDocument.AlternateContent.Choice get(int n) {
                        return AlternateContentImpl.this.getChoiceArray(n);
                    }

                    @Override
                    public AlternateContentDocument.AlternateContent.Choice set(int n, AlternateContentDocument.AlternateContent.Choice choice) {
                        AlternateContentDocument.AlternateContent.Choice choice2 = AlternateContentImpl.this.getChoiceArray(n);
                        AlternateContentImpl.this.setChoiceArray(n, choice);
                        return choice2;
                    }

                    @Override
                    public void add(int n, AlternateContentDocument.AlternateContent.Choice choice) {
                        AlternateContentImpl.this.insertNewChoice(n).set(choice);
                    }

                    @Override
                    public AlternateContentDocument.AlternateContent.Choice remove(int n) {
                        AlternateContentDocument.AlternateContent.Choice choice = AlternateContentImpl.this.getChoiceArray(n);
                        AlternateContentImpl.this.removeChoice(n);
                        return choice;
                    }

                    @Override
                    public int size() {
                        return AlternateContentImpl.this.sizeOfChoiceArray();
                    }
                }
                return new ChoiceList();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Deprecated
        public AlternateContentDocument.AlternateContent.Choice[] getChoiceArray() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                ArrayList arrayList = new ArrayList();
                this.get_store().find_all_element_users(CHOICE$0, arrayList);
                AlternateContentDocument.AlternateContent.Choice[] choiceArray = new AlternateContentDocument.AlternateContent.Choice[arrayList.size()];
                arrayList.toArray(choiceArray);
                return choiceArray;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AlternateContentDocument.AlternateContent.Choice getChoiceArray(int n) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AlternateContentDocument.AlternateContent.Choice choice = null;
                choice = (AlternateContentDocument.AlternateContent.Choice)((Object)this.get_store().find_element_user(CHOICE$0, n));
                if (choice == null) {
                    throw new IndexOutOfBoundsException();
                }
                return choice;
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
                return this.get_store().count_elements(CHOICE$0);
            }
        }

        @Override
        public void setChoiceArray(AlternateContentDocument.AlternateContent.Choice[] choiceArray) {
            this.check_orphaned();
            this.arraySetterHelper(choiceArray, CHOICE$0);
        }

        @Override
        public void setChoiceArray(int n, AlternateContentDocument.AlternateContent.Choice choice) {
            this.generatedSetterHelperImpl(choice, CHOICE$0, n, (short)2);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AlternateContentDocument.AlternateContent.Choice insertNewChoice(int n) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AlternateContentDocument.AlternateContent.Choice choice = null;
                choice = (AlternateContentDocument.AlternateContent.Choice)((Object)this.get_store().insert_element_user(CHOICE$0, n));
                return choice;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AlternateContentDocument.AlternateContent.Choice addNewChoice() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AlternateContentDocument.AlternateContent.Choice choice = null;
                choice = (AlternateContentDocument.AlternateContent.Choice)((Object)this.get_store().add_element_user(CHOICE$0));
                return choice;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void removeChoice(int n) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(CHOICE$0, n);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AlternateContentDocument.AlternateContent.Fallback getFallback() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AlternateContentDocument.AlternateContent.Fallback fallback = null;
                fallback = (AlternateContentDocument.AlternateContent.Fallback)((Object)this.get_store().find_element_user(FALLBACK$2, 0));
                if (fallback == null) {
                    return null;
                }
                return fallback;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetFallback() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(FALLBACK$2) != 0;
            }
        }

        @Override
        public void setFallback(AlternateContentDocument.AlternateContent.Fallback fallback) {
            this.generatedSetterHelperImpl(fallback, FALLBACK$2, 0, (short)1);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AlternateContentDocument.AlternateContent.Fallback addNewFallback() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AlternateContentDocument.AlternateContent.Fallback fallback = null;
                fallback = (AlternateContentDocument.AlternateContent.Fallback)((Object)this.get_store().add_element_user(FALLBACK$2));
                return fallback;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetFallback() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(FALLBACK$2, 0);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getIgnorable() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue simpleValue = null;
                simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IGNORABLE$4));
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
        public XmlString xgetIgnorable() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlString xmlString = null;
                xmlString = (XmlString)((Object)this.get_store().find_attribute_user(IGNORABLE$4));
                return xmlString;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetIgnorable() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(IGNORABLE$4) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setIgnorable(String string) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue simpleValue = null;
                simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IGNORABLE$4));
                if (simpleValue == null) {
                    simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IGNORABLE$4));
                }
                simpleValue.setStringValue(string);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetIgnorable(XmlString xmlString) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlString xmlString2 = null;
                xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(IGNORABLE$4));
                if (xmlString2 == null) {
                    xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(IGNORABLE$4));
                }
                xmlString2.set(xmlString);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetIgnorable() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(IGNORABLE$4);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getMustUnderstand() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue simpleValue = null;
                simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$6));
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
        public XmlString xgetMustUnderstand() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlString xmlString = null;
                xmlString = (XmlString)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$6));
                return xmlString;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetMustUnderstand() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(MUSTUNDERSTAND$6) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setMustUnderstand(String string) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue simpleValue = null;
                simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$6));
                if (simpleValue == null) {
                    simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MUSTUNDERSTAND$6));
                }
                simpleValue.setStringValue(string);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetMustUnderstand(XmlString xmlString) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlString xmlString2 = null;
                xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$6));
                if (xmlString2 == null) {
                    xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(MUSTUNDERSTAND$6));
                }
                xmlString2.set(xmlString);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetMustUnderstand() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(MUSTUNDERSTAND$6);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getProcessContent() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue simpleValue = null;
                simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$8));
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
        public XmlString xgetProcessContent() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlString xmlString = null;
                xmlString = (XmlString)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$8));
                return xmlString;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetProcessContent() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(PROCESSCONTENT$8) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setProcessContent(String string) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue simpleValue = null;
                simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$8));
                if (simpleValue == null) {
                    simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PROCESSCONTENT$8));
                }
                simpleValue.setStringValue(string);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetProcessContent(XmlString xmlString) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlString xmlString2 = null;
                xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$8));
                if (xmlString2 == null) {
                    xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(PROCESSCONTENT$8));
                }
                xmlString2.set(xmlString);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetProcessContent() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(PROCESSCONTENT$8);
            }
        }

        public static class FallbackImpl
        extends XmlComplexContentImpl
        implements AlternateContentDocument.AlternateContent.Fallback {
            private static final long serialVersionUID = 1L;
            private static final QName IGNORABLE$0 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Ignorable");
            private static final QName MUSTUNDERSTAND$2 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "MustUnderstand");
            private static final QName PROCESSCONTENT$4 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "ProcessContent");

            public FallbackImpl(SchemaType schemaType) {
                super(schemaType);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IGNORABLE$0));
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
            public XmlString xgetIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(IGNORABLE$0));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean isSetIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    return this.get_store().find_attribute_user(IGNORABLE$0) != null;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setIgnorable(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IGNORABLE$0));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IGNORABLE$0));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetIgnorable(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(IGNORABLE$0));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(IGNORABLE$0));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void unsetIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    this.get_store().remove_attribute(IGNORABLE$0);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$2));
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
            public XmlString xgetMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$2));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean isSetMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    return this.get_store().find_attribute_user(MUSTUNDERSTAND$2) != null;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setMustUnderstand(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$2));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MUSTUNDERSTAND$2));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetMustUnderstand(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$2));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(MUSTUNDERSTAND$2));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void unsetMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    this.get_store().remove_attribute(MUSTUNDERSTAND$2);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$4));
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
            public XmlString xgetProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$4));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean isSetProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    return this.get_store().find_attribute_user(PROCESSCONTENT$4) != null;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setProcessContent(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$4));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PROCESSCONTENT$4));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetProcessContent(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$4));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(PROCESSCONTENT$4));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void unsetProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    this.get_store().remove_attribute(PROCESSCONTENT$4);
                }
            }
        }

        public static class ChoiceImpl
        extends XmlComplexContentImpl
        implements AlternateContentDocument.AlternateContent.Choice {
            private static final long serialVersionUID = 1L;
            private static final QName REQUIRES$0 = new QName("", "Requires");
            private static final QName IGNORABLE$2 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Ignorable");
            private static final QName MUSTUNDERSTAND$4 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "MustUnderstand");
            private static final QName PROCESSCONTENT$6 = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "ProcessContent");

            public ChoiceImpl(SchemaType schemaType) {
                super(schemaType);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getRequires() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REQUIRES$0));
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
            public XmlString xgetRequires() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(REQUIRES$0));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setRequires(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REQUIRES$0));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REQUIRES$0));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetRequires(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(REQUIRES$0));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(REQUIRES$0));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IGNORABLE$2));
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
            public XmlString xgetIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(IGNORABLE$2));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean isSetIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    return this.get_store().find_attribute_user(IGNORABLE$2) != null;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setIgnorable(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(IGNORABLE$2));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(IGNORABLE$2));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetIgnorable(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(IGNORABLE$2));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(IGNORABLE$2));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void unsetIgnorable() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    this.get_store().remove_attribute(IGNORABLE$2);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$4));
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
            public XmlString xgetMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$4));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean isSetMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    return this.get_store().find_attribute_user(MUSTUNDERSTAND$4) != null;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setMustUnderstand(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$4));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MUSTUNDERSTAND$4));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetMustUnderstand(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(MUSTUNDERSTAND$4));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(MUSTUNDERSTAND$4));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void unsetMustUnderstand() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    this.get_store().remove_attribute(MUSTUNDERSTAND$4);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public String getProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$6));
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
            public XmlString xgetProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString = null;
                    xmlString = (XmlString)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$6));
                    return xmlString;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean isSetProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    return this.get_store().find_attribute_user(PROCESSCONTENT$6) != null;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void setProcessContent(String string) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    SimpleValue simpleValue = null;
                    simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$6));
                    if (simpleValue == null) {
                        simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PROCESSCONTENT$6));
                    }
                    simpleValue.setStringValue(string);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void xsetProcessContent(XmlString xmlString) {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    XmlString xmlString2 = null;
                    xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(PROCESSCONTENT$6));
                    if (xmlString2 == null) {
                        xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(PROCESSCONTENT$6));
                    }
                    xmlString2.set(xmlString);
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void unsetProcessContent() {
                Object object = this.monitor();
                synchronized (object) {
                    this.check_orphaned();
                    this.get_store().remove_attribute(PROCESSCONTENT$6);
                }
            }
        }
    }
}

