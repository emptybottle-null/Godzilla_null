/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFName
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextInput
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextInput;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;

public class CTFFDataImpl
extends XmlComplexContentImpl
implements CTFFData {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "name");
    private static final QName ENABLED$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "enabled");
    private static final QName CALCONEXIT$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "calcOnExit");
    private static final QName ENTRYMACRO$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "entryMacro");
    private static final QName EXITMACRO$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "exitMacro");
    private static final QName HELPTEXT$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "helpText");
    private static final QName STATUSTEXT$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "statusText");
    private static final QName CHECKBOX$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "checkBox");
    private static final QName DDLIST$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ddList");
    private static final QName TEXTINPUT$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textInput");

    public CTFFDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFFName> getNameList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NameList
            extends AbstractList<CTFFName> {
                NameList() {
                }

                @Override
                public CTFFName get(int n) {
                    return CTFFDataImpl.this.getNameArray(n);
                }

                @Override
                public CTFFName set(int n, CTFFName cTFFName) {
                    CTFFName cTFFName2 = CTFFDataImpl.this.getNameArray(n);
                    CTFFDataImpl.this.setNameArray(n, cTFFName);
                    return cTFFName2;
                }

                @Override
                public void add(int n, CTFFName cTFFName) {
                    CTFFDataImpl.this.insertNewName(n).set((XmlObject)cTFFName);
                }

                @Override
                public CTFFName remove(int n) {
                    CTFFName cTFFName = CTFFDataImpl.this.getNameArray(n);
                    CTFFDataImpl.this.removeName(n);
                    return cTFFName;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfNameArray();
                }
            }
            return new NameList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFFName[] getNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NAME$0, arrayList);
            CTFFName[] cTFFNameArray = new CTFFName[arrayList.size()];
            arrayList.toArray(cTFFNameArray);
            return cTFFNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFName getNameArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFName cTFFName = null;
            cTFFName = (CTFFName)this.get_store().find_element_user(NAME$0, n);
            if (cTFFName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFFName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NAME$0);
        }
    }

    @Override
    public void setNameArray(CTFFName[] cTFFNameArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFFNameArray, NAME$0);
    }

    @Override
    public void setNameArray(int n, CTFFName cTFFName) {
        this.generatedSetterHelperImpl((XmlObject)cTFFName, NAME$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFName insertNewName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFName cTFFName = null;
            cTFFName = (CTFFName)this.get_store().insert_element_user(NAME$0, n);
            return cTFFName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFName addNewName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFName cTFFName = null;
            cTFFName = (CTFFName)this.get_store().add_element_user(NAME$0);
            return cTFFName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NAME$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getEnabledList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EnabledList
            extends AbstractList<CTOnOff> {
                EnabledList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTFFDataImpl.this.getEnabledArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTFFDataImpl.this.getEnabledArray(n);
                    CTFFDataImpl.this.setEnabledArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTFFDataImpl.this.insertNewEnabled(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTFFDataImpl.this.getEnabledArray(n);
                    CTFFDataImpl.this.removeEnabled(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfEnabledArray();
                }
            }
            return new EnabledList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getEnabledArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENABLED$2, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getEnabledArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(ENABLED$2, n));
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEnabledArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENABLED$2);
        }
    }

    @Override
    public void setEnabledArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, ENABLED$2);
    }

    @Override
    public void setEnabledArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, ENABLED$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewEnabled(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(ENABLED$2, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewEnabled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(ENABLED$2));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEnabled(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENABLED$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOnOff> getCalcOnExitList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CalcOnExitList
            extends AbstractList<CTOnOff> {
                CalcOnExitList() {
                }

                @Override
                public CTOnOff get(int n) {
                    return CTFFDataImpl.this.getCalcOnExitArray(n);
                }

                @Override
                public CTOnOff set(int n, CTOnOff cTOnOff) {
                    CTOnOff cTOnOff2 = CTFFDataImpl.this.getCalcOnExitArray(n);
                    CTFFDataImpl.this.setCalcOnExitArray(n, cTOnOff);
                    return cTOnOff2;
                }

                @Override
                public void add(int n, CTOnOff cTOnOff) {
                    CTFFDataImpl.this.insertNewCalcOnExit(n).set(cTOnOff);
                }

                @Override
                public CTOnOff remove(int n) {
                    CTOnOff cTOnOff = CTFFDataImpl.this.getCalcOnExitArray(n);
                    CTFFDataImpl.this.removeCalcOnExit(n);
                    return cTOnOff;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfCalcOnExitArray();
                }
            }
            return new CalcOnExitList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOnOff[] getCalcOnExitArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CALCONEXIT$4, arrayList);
            CTOnOff[] cTOnOffArray = new CTOnOff[arrayList.size()];
            arrayList.toArray(cTOnOffArray);
            return cTOnOffArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getCalcOnExitArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(CALCONEXIT$4, n));
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCalcOnExitArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CALCONEXIT$4);
        }
    }

    @Override
    public void setCalcOnExitArray(CTOnOff[] cTOnOffArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOnOffArray, CALCONEXIT$4);
    }

    @Override
    public void setCalcOnExitArray(int n, CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, CALCONEXIT$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff insertNewCalcOnExit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().insert_element_user(CALCONEXIT$4, n));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewCalcOnExit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(CALCONEXIT$4));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCalcOnExit(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CALCONEXIT$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMacroName> getEntryMacroList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EntryMacroList
            extends AbstractList<CTMacroName> {
                EntryMacroList() {
                }

                @Override
                public CTMacroName get(int n) {
                    return CTFFDataImpl.this.getEntryMacroArray(n);
                }

                @Override
                public CTMacroName set(int n, CTMacroName cTMacroName) {
                    CTMacroName cTMacroName2 = CTFFDataImpl.this.getEntryMacroArray(n);
                    CTFFDataImpl.this.setEntryMacroArray(n, cTMacroName);
                    return cTMacroName2;
                }

                @Override
                public void add(int n, CTMacroName cTMacroName) {
                    CTFFDataImpl.this.insertNewEntryMacro(n).set((XmlObject)cTMacroName);
                }

                @Override
                public CTMacroName remove(int n) {
                    CTMacroName cTMacroName = CTFFDataImpl.this.getEntryMacroArray(n);
                    CTFFDataImpl.this.removeEntryMacro(n);
                    return cTMacroName;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfEntryMacroArray();
                }
            }
            return new EntryMacroList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMacroName[] getEntryMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENTRYMACRO$6, arrayList);
            CTMacroName[] cTMacroNameArray = new CTMacroName[arrayList.size()];
            arrayList.toArray(cTMacroNameArray);
            return cTMacroNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMacroName getEntryMacroArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMacroName cTMacroName = null;
            cTMacroName = (CTMacroName)this.get_store().find_element_user(ENTRYMACRO$6, n);
            if (cTMacroName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMacroName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEntryMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENTRYMACRO$6);
        }
    }

    @Override
    public void setEntryMacroArray(CTMacroName[] cTMacroNameArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTMacroNameArray, ENTRYMACRO$6);
    }

    @Override
    public void setEntryMacroArray(int n, CTMacroName cTMacroName) {
        this.generatedSetterHelperImpl((XmlObject)cTMacroName, ENTRYMACRO$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMacroName insertNewEntryMacro(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMacroName cTMacroName = null;
            cTMacroName = (CTMacroName)this.get_store().insert_element_user(ENTRYMACRO$6, n);
            return cTMacroName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMacroName addNewEntryMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMacroName cTMacroName = null;
            cTMacroName = (CTMacroName)this.get_store().add_element_user(ENTRYMACRO$6);
            return cTMacroName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEntryMacro(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENTRYMACRO$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMacroName> getExitMacroList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ExitMacroList
            extends AbstractList<CTMacroName> {
                ExitMacroList() {
                }

                @Override
                public CTMacroName get(int n) {
                    return CTFFDataImpl.this.getExitMacroArray(n);
                }

                @Override
                public CTMacroName set(int n, CTMacroName cTMacroName) {
                    CTMacroName cTMacroName2 = CTFFDataImpl.this.getExitMacroArray(n);
                    CTFFDataImpl.this.setExitMacroArray(n, cTMacroName);
                    return cTMacroName2;
                }

                @Override
                public void add(int n, CTMacroName cTMacroName) {
                    CTFFDataImpl.this.insertNewExitMacro(n).set((XmlObject)cTMacroName);
                }

                @Override
                public CTMacroName remove(int n) {
                    CTMacroName cTMacroName = CTFFDataImpl.this.getExitMacroArray(n);
                    CTFFDataImpl.this.removeExitMacro(n);
                    return cTMacroName;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfExitMacroArray();
                }
            }
            return new ExitMacroList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMacroName[] getExitMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EXITMACRO$8, arrayList);
            CTMacroName[] cTMacroNameArray = new CTMacroName[arrayList.size()];
            arrayList.toArray(cTMacroNameArray);
            return cTMacroNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMacroName getExitMacroArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMacroName cTMacroName = null;
            cTMacroName = (CTMacroName)this.get_store().find_element_user(EXITMACRO$8, n);
            if (cTMacroName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMacroName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfExitMacroArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXITMACRO$8);
        }
    }

    @Override
    public void setExitMacroArray(CTMacroName[] cTMacroNameArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTMacroNameArray, EXITMACRO$8);
    }

    @Override
    public void setExitMacroArray(int n, CTMacroName cTMacroName) {
        this.generatedSetterHelperImpl((XmlObject)cTMacroName, EXITMACRO$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMacroName insertNewExitMacro(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMacroName cTMacroName = null;
            cTMacroName = (CTMacroName)this.get_store().insert_element_user(EXITMACRO$8, n);
            return cTMacroName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMacroName addNewExitMacro() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMacroName cTMacroName = null;
            cTMacroName = (CTMacroName)this.get_store().add_element_user(EXITMACRO$8);
            return cTMacroName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeExitMacro(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXITMACRO$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFFHelpText> getHelpTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HelpTextList
            extends AbstractList<CTFFHelpText> {
                HelpTextList() {
                }

                @Override
                public CTFFHelpText get(int n) {
                    return CTFFDataImpl.this.getHelpTextArray(n);
                }

                @Override
                public CTFFHelpText set(int n, CTFFHelpText cTFFHelpText) {
                    CTFFHelpText cTFFHelpText2 = CTFFDataImpl.this.getHelpTextArray(n);
                    CTFFDataImpl.this.setHelpTextArray(n, cTFFHelpText);
                    return cTFFHelpText2;
                }

                @Override
                public void add(int n, CTFFHelpText cTFFHelpText) {
                    CTFFDataImpl.this.insertNewHelpText(n).set((XmlObject)cTFFHelpText);
                }

                @Override
                public CTFFHelpText remove(int n) {
                    CTFFHelpText cTFFHelpText = CTFFDataImpl.this.getHelpTextArray(n);
                    CTFFDataImpl.this.removeHelpText(n);
                    return cTFFHelpText;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfHelpTextArray();
                }
            }
            return new HelpTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFFHelpText[] getHelpTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(HELPTEXT$10, arrayList);
            CTFFHelpText[] cTFFHelpTextArray = new CTFFHelpText[arrayList.size()];
            arrayList.toArray(cTFFHelpTextArray);
            return cTFFHelpTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFHelpText getHelpTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFHelpText cTFFHelpText = null;
            cTFFHelpText = (CTFFHelpText)this.get_store().find_element_user(HELPTEXT$10, n);
            if (cTFFHelpText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFFHelpText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHelpTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HELPTEXT$10);
        }
    }

    @Override
    public void setHelpTextArray(CTFFHelpText[] cTFFHelpTextArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFFHelpTextArray, HELPTEXT$10);
    }

    @Override
    public void setHelpTextArray(int n, CTFFHelpText cTFFHelpText) {
        this.generatedSetterHelperImpl((XmlObject)cTFFHelpText, HELPTEXT$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFHelpText insertNewHelpText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFHelpText cTFFHelpText = null;
            cTFFHelpText = (CTFFHelpText)this.get_store().insert_element_user(HELPTEXT$10, n);
            return cTFFHelpText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFHelpText addNewHelpText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFHelpText cTFFHelpText = null;
            cTFFHelpText = (CTFFHelpText)this.get_store().add_element_user(HELPTEXT$10);
            return cTFFHelpText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeHelpText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HELPTEXT$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFFStatusText> getStatusTextList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class StatusTextList
            extends AbstractList<CTFFStatusText> {
                StatusTextList() {
                }

                @Override
                public CTFFStatusText get(int n) {
                    return CTFFDataImpl.this.getStatusTextArray(n);
                }

                @Override
                public CTFFStatusText set(int n, CTFFStatusText cTFFStatusText) {
                    CTFFStatusText cTFFStatusText2 = CTFFDataImpl.this.getStatusTextArray(n);
                    CTFFDataImpl.this.setStatusTextArray(n, cTFFStatusText);
                    return cTFFStatusText2;
                }

                @Override
                public void add(int n, CTFFStatusText cTFFStatusText) {
                    CTFFDataImpl.this.insertNewStatusText(n).set((XmlObject)cTFFStatusText);
                }

                @Override
                public CTFFStatusText remove(int n) {
                    CTFFStatusText cTFFStatusText = CTFFDataImpl.this.getStatusTextArray(n);
                    CTFFDataImpl.this.removeStatusText(n);
                    return cTFFStatusText;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfStatusTextArray();
                }
            }
            return new StatusTextList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFFStatusText[] getStatusTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(STATUSTEXT$12, arrayList);
            CTFFStatusText[] cTFFStatusTextArray = new CTFFStatusText[arrayList.size()];
            arrayList.toArray(cTFFStatusTextArray);
            return cTFFStatusTextArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFStatusText getStatusTextArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFStatusText cTFFStatusText = null;
            cTFFStatusText = (CTFFStatusText)this.get_store().find_element_user(STATUSTEXT$12, n);
            if (cTFFStatusText == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFFStatusText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfStatusTextArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STATUSTEXT$12);
        }
    }

    @Override
    public void setStatusTextArray(CTFFStatusText[] cTFFStatusTextArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFFStatusTextArray, STATUSTEXT$12);
    }

    @Override
    public void setStatusTextArray(int n, CTFFStatusText cTFFStatusText) {
        this.generatedSetterHelperImpl((XmlObject)cTFFStatusText, STATUSTEXT$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFStatusText insertNewStatusText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFStatusText cTFFStatusText = null;
            cTFFStatusText = (CTFFStatusText)this.get_store().insert_element_user(STATUSTEXT$12, n);
            return cTFFStatusText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFStatusText addNewStatusText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFStatusText cTFFStatusText = null;
            cTFFStatusText = (CTFFStatusText)this.get_store().add_element_user(STATUSTEXT$12);
            return cTFFStatusText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeStatusText(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STATUSTEXT$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFFCheckBox> getCheckBoxList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CheckBoxList
            extends AbstractList<CTFFCheckBox> {
                CheckBoxList() {
                }

                @Override
                public CTFFCheckBox get(int n) {
                    return CTFFDataImpl.this.getCheckBoxArray(n);
                }

                @Override
                public CTFFCheckBox set(int n, CTFFCheckBox cTFFCheckBox) {
                    CTFFCheckBox cTFFCheckBox2 = CTFFDataImpl.this.getCheckBoxArray(n);
                    CTFFDataImpl.this.setCheckBoxArray(n, cTFFCheckBox);
                    return cTFFCheckBox2;
                }

                @Override
                public void add(int n, CTFFCheckBox cTFFCheckBox) {
                    CTFFDataImpl.this.insertNewCheckBox(n).set(cTFFCheckBox);
                }

                @Override
                public CTFFCheckBox remove(int n) {
                    CTFFCheckBox cTFFCheckBox = CTFFDataImpl.this.getCheckBoxArray(n);
                    CTFFDataImpl.this.removeCheckBox(n);
                    return cTFFCheckBox;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfCheckBoxArray();
                }
            }
            return new CheckBoxList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFFCheckBox[] getCheckBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CHECKBOX$14, arrayList);
            CTFFCheckBox[] cTFFCheckBoxArray = new CTFFCheckBox[arrayList.size()];
            arrayList.toArray(cTFFCheckBoxArray);
            return cTFFCheckBoxArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFCheckBox getCheckBoxArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFCheckBox cTFFCheckBox = null;
            cTFFCheckBox = (CTFFCheckBox)((Object)this.get_store().find_element_user(CHECKBOX$14, n));
            if (cTFFCheckBox == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFFCheckBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCheckBoxArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHECKBOX$14);
        }
    }

    @Override
    public void setCheckBoxArray(CTFFCheckBox[] cTFFCheckBoxArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTFFCheckBoxArray, CHECKBOX$14);
    }

    @Override
    public void setCheckBoxArray(int n, CTFFCheckBox cTFFCheckBox) {
        this.generatedSetterHelperImpl(cTFFCheckBox, CHECKBOX$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFCheckBox insertNewCheckBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFCheckBox cTFFCheckBox = null;
            cTFFCheckBox = (CTFFCheckBox)((Object)this.get_store().insert_element_user(CHECKBOX$14, n));
            return cTFFCheckBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFCheckBox addNewCheckBox() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFCheckBox cTFFCheckBox = null;
            cTFFCheckBox = (CTFFCheckBox)((Object)this.get_store().add_element_user(CHECKBOX$14));
            return cTFFCheckBox;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCheckBox(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHECKBOX$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFFDDList> getDdListList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DdListList
            extends AbstractList<CTFFDDList> {
                DdListList() {
                }

                @Override
                public CTFFDDList get(int n) {
                    return CTFFDataImpl.this.getDdListArray(n);
                }

                @Override
                public CTFFDDList set(int n, CTFFDDList cTFFDDList) {
                    CTFFDDList cTFFDDList2 = CTFFDataImpl.this.getDdListArray(n);
                    CTFFDataImpl.this.setDdListArray(n, cTFFDDList);
                    return cTFFDDList2;
                }

                @Override
                public void add(int n, CTFFDDList cTFFDDList) {
                    CTFFDataImpl.this.insertNewDdList(n).set((XmlObject)cTFFDDList);
                }

                @Override
                public CTFFDDList remove(int n) {
                    CTFFDDList cTFFDDList = CTFFDataImpl.this.getDdListArray(n);
                    CTFFDataImpl.this.removeDdList(n);
                    return cTFFDDList;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfDdListArray();
                }
            }
            return new DdListList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFFDDList[] getDdListArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DDLIST$16, arrayList);
            CTFFDDList[] cTFFDDListArray = new CTFFDDList[arrayList.size()];
            arrayList.toArray(cTFFDDListArray);
            return cTFFDDListArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFDDList getDdListArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFDDList cTFFDDList = null;
            cTFFDDList = (CTFFDDList)this.get_store().find_element_user(DDLIST$16, n);
            if (cTFFDDList == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFFDDList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDdListArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DDLIST$16);
        }
    }

    @Override
    public void setDdListArray(CTFFDDList[] cTFFDDListArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFFDDListArray, DDLIST$16);
    }

    @Override
    public void setDdListArray(int n, CTFFDDList cTFFDDList) {
        this.generatedSetterHelperImpl((XmlObject)cTFFDDList, DDLIST$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFDDList insertNewDdList(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFDDList cTFFDDList = null;
            cTFFDDList = (CTFFDDList)this.get_store().insert_element_user(DDLIST$16, n);
            return cTFFDDList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFDDList addNewDdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFDDList cTFFDDList = null;
            cTFFDDList = (CTFFDDList)this.get_store().add_element_user(DDLIST$16);
            return cTFFDDList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDdList(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DDLIST$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFFTextInput> getTextInputList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TextInputList
            extends AbstractList<CTFFTextInput> {
                TextInputList() {
                }

                @Override
                public CTFFTextInput get(int n) {
                    return CTFFDataImpl.this.getTextInputArray(n);
                }

                @Override
                public CTFFTextInput set(int n, CTFFTextInput cTFFTextInput) {
                    CTFFTextInput cTFFTextInput2 = CTFFDataImpl.this.getTextInputArray(n);
                    CTFFDataImpl.this.setTextInputArray(n, cTFFTextInput);
                    return cTFFTextInput2;
                }

                @Override
                public void add(int n, CTFFTextInput cTFFTextInput) {
                    CTFFDataImpl.this.insertNewTextInput(n).set((XmlObject)cTFFTextInput);
                }

                @Override
                public CTFFTextInput remove(int n) {
                    CTFFTextInput cTFFTextInput = CTFFDataImpl.this.getTextInputArray(n);
                    CTFFDataImpl.this.removeTextInput(n);
                    return cTFFTextInput;
                }

                @Override
                public int size() {
                    return CTFFDataImpl.this.sizeOfTextInputArray();
                }
            }
            return new TextInputList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFFTextInput[] getTextInputArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TEXTINPUT$18, arrayList);
            CTFFTextInput[] cTFFTextInputArray = new CTFFTextInput[arrayList.size()];
            arrayList.toArray(cTFFTextInputArray);
            return cTFFTextInputArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFTextInput getTextInputArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFTextInput cTFFTextInput = null;
            cTFFTextInput = (CTFFTextInput)this.get_store().find_element_user(TEXTINPUT$18, n);
            if (cTFFTextInput == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFFTextInput;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTextInputArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTINPUT$18);
        }
    }

    @Override
    public void setTextInputArray(CTFFTextInput[] cTFFTextInputArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFFTextInputArray, TEXTINPUT$18);
    }

    @Override
    public void setTextInputArray(int n, CTFFTextInput cTFFTextInput) {
        this.generatedSetterHelperImpl((XmlObject)cTFFTextInput, TEXTINPUT$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFTextInput insertNewTextInput(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFTextInput cTFFTextInput = null;
            cTFFTextInput = (CTFFTextInput)this.get_store().insert_element_user(TEXTINPUT$18, n);
            return cTFFTextInput;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFFTextInput addNewTextInput() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFFTextInput cTFFTextInput = null;
            cTFFTextInput = (CTFFTextInput)this.get_store().add_element_user(TEXTINPUT$18);
            return cTFFTextInput;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTextInput(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTINPUT$18, n);
        }
    }
}

