/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.AttachedToolbarsType
 *  com.microsoft.schemas.office.visio.x2012.main.CustomMenusFileType
 *  com.microsoft.schemas.office.visio.x2012.main.CustomToolbarsFileType
 *  com.microsoft.schemas.office.visio.x2012.main.DynamicGridEnabledType
 *  com.microsoft.schemas.office.visio.x2012.main.GlueSettingsType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectBkgndsType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectMastersType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectShapesType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectStylesType
 *  com.microsoft.schemas.office.visio.x2012.main.SnapAnglesType
 *  com.microsoft.schemas.office.visio.x2012.main.SnapExtensionsType
 *  com.microsoft.schemas.office.visio.x2012.main.SnapSettingsType
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.AttachedToolbarsType;
import com.microsoft.schemas.office.visio.x2012.main.CustomMenusFileType;
import com.microsoft.schemas.office.visio.x2012.main.CustomToolbarsFileType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.DynamicGridEnabledType;
import com.microsoft.schemas.office.visio.x2012.main.GlueSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectBkgndsType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectMastersType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectShapesType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectStylesType;
import com.microsoft.schemas.office.visio.x2012.main.SnapAnglesType;
import com.microsoft.schemas.office.visio.x2012.main.SnapExtensionsType;
import com.microsoft.schemas.office.visio.x2012.main.SnapSettingsType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class DocumentSettingsTypeImpl
extends XmlComplexContentImpl
implements DocumentSettingsType {
    private static final long serialVersionUID = 1L;
    private static final QName GLUESETTINGS$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "GlueSettings");
    private static final QName SNAPSETTINGS$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "SnapSettings");
    private static final QName SNAPEXTENSIONS$4 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "SnapExtensions");
    private static final QName SNAPANGLES$6 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "SnapAngles");
    private static final QName DYNAMICGRIDENABLED$8 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "DynamicGridEnabled");
    private static final QName PROTECTSTYLES$10 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectStyles");
    private static final QName PROTECTSHAPES$12 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectShapes");
    private static final QName PROTECTMASTERS$14 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectMasters");
    private static final QName PROTECTBKGNDS$16 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "ProtectBkgnds");
    private static final QName CUSTOMMENUSFILE$18 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "CustomMenusFile");
    private static final QName CUSTOMTOOLBARSFILE$20 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "CustomToolbarsFile");
    private static final QName ATTACHEDTOOLBARS$22 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "AttachedToolbars");
    private static final QName TOPPAGE$24 = new QName("", "TopPage");
    private static final QName DEFAULTTEXTSTYLE$26 = new QName("", "DefaultTextStyle");
    private static final QName DEFAULTLINESTYLE$28 = new QName("", "DefaultLineStyle");
    private static final QName DEFAULTFILLSTYLE$30 = new QName("", "DefaultFillStyle");
    private static final QName DEFAULTGUIDESTYLE$32 = new QName("", "DefaultGuideStyle");

    public DocumentSettingsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GlueSettingsType getGlueSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GlueSettingsType glueSettingsType = null;
            glueSettingsType = (GlueSettingsType)this.get_store().find_element_user(GLUESETTINGS$0, 0);
            if (glueSettingsType == null) {
                return null;
            }
            return glueSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGlueSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GLUESETTINGS$0) != 0;
        }
    }

    @Override
    public void setGlueSettings(GlueSettingsType glueSettingsType) {
        this.generatedSetterHelperImpl((XmlObject)glueSettingsType, GLUESETTINGS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GlueSettingsType addNewGlueSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GlueSettingsType glueSettingsType = null;
            glueSettingsType = (GlueSettingsType)this.get_store().add_element_user(GLUESETTINGS$0);
            return glueSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGlueSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GLUESETTINGS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SnapSettingsType getSnapSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SnapSettingsType snapSettingsType = null;
            snapSettingsType = (SnapSettingsType)this.get_store().find_element_user(SNAPSETTINGS$2, 0);
            if (snapSettingsType == null) {
                return null;
            }
            return snapSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSnapSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SNAPSETTINGS$2) != 0;
        }
    }

    @Override
    public void setSnapSettings(SnapSettingsType snapSettingsType) {
        this.generatedSetterHelperImpl((XmlObject)snapSettingsType, SNAPSETTINGS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SnapSettingsType addNewSnapSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SnapSettingsType snapSettingsType = null;
            snapSettingsType = (SnapSettingsType)this.get_store().add_element_user(SNAPSETTINGS$2);
            return snapSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSnapSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SNAPSETTINGS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SnapExtensionsType getSnapExtensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SnapExtensionsType snapExtensionsType = null;
            snapExtensionsType = (SnapExtensionsType)this.get_store().find_element_user(SNAPEXTENSIONS$4, 0);
            if (snapExtensionsType == null) {
                return null;
            }
            return snapExtensionsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSnapExtensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SNAPEXTENSIONS$4) != 0;
        }
    }

    @Override
    public void setSnapExtensions(SnapExtensionsType snapExtensionsType) {
        this.generatedSetterHelperImpl((XmlObject)snapExtensionsType, SNAPEXTENSIONS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SnapExtensionsType addNewSnapExtensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SnapExtensionsType snapExtensionsType = null;
            snapExtensionsType = (SnapExtensionsType)this.get_store().add_element_user(SNAPEXTENSIONS$4);
            return snapExtensionsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSnapExtensions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SNAPEXTENSIONS$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SnapAnglesType getSnapAngles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SnapAnglesType snapAnglesType = null;
            snapAnglesType = (SnapAnglesType)this.get_store().find_element_user(SNAPANGLES$6, 0);
            if (snapAnglesType == null) {
                return null;
            }
            return snapAnglesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSnapAngles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SNAPANGLES$6) != 0;
        }
    }

    @Override
    public void setSnapAngles(SnapAnglesType snapAnglesType) {
        this.generatedSetterHelperImpl((XmlObject)snapAnglesType, SNAPANGLES$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SnapAnglesType addNewSnapAngles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SnapAnglesType snapAnglesType = null;
            snapAnglesType = (SnapAnglesType)this.get_store().add_element_user(SNAPANGLES$6);
            return snapAnglesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSnapAngles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SNAPANGLES$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DynamicGridEnabledType getDynamicGridEnabled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DynamicGridEnabledType dynamicGridEnabledType = null;
            dynamicGridEnabledType = (DynamicGridEnabledType)this.get_store().find_element_user(DYNAMICGRIDENABLED$8, 0);
            if (dynamicGridEnabledType == null) {
                return null;
            }
            return dynamicGridEnabledType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDynamicGridEnabled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DYNAMICGRIDENABLED$8) != 0;
        }
    }

    @Override
    public void setDynamicGridEnabled(DynamicGridEnabledType dynamicGridEnabledType) {
        this.generatedSetterHelperImpl((XmlObject)dynamicGridEnabledType, DYNAMICGRIDENABLED$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DynamicGridEnabledType addNewDynamicGridEnabled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DynamicGridEnabledType dynamicGridEnabledType = null;
            dynamicGridEnabledType = (DynamicGridEnabledType)this.get_store().add_element_user(DYNAMICGRIDENABLED$8);
            return dynamicGridEnabledType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDynamicGridEnabled() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DYNAMICGRIDENABLED$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectStylesType getProtectStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectStylesType protectStylesType = null;
            protectStylesType = (ProtectStylesType)this.get_store().find_element_user(PROTECTSTYLES$10, 0);
            if (protectStylesType == null) {
                return null;
            }
            return protectStylesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProtectStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROTECTSTYLES$10) != 0;
        }
    }

    @Override
    public void setProtectStyles(ProtectStylesType protectStylesType) {
        this.generatedSetterHelperImpl((XmlObject)protectStylesType, PROTECTSTYLES$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectStylesType addNewProtectStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectStylesType protectStylesType = null;
            protectStylesType = (ProtectStylesType)this.get_store().add_element_user(PROTECTSTYLES$10);
            return protectStylesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProtectStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROTECTSTYLES$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectShapesType getProtectShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectShapesType protectShapesType = null;
            protectShapesType = (ProtectShapesType)this.get_store().find_element_user(PROTECTSHAPES$12, 0);
            if (protectShapesType == null) {
                return null;
            }
            return protectShapesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProtectShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROTECTSHAPES$12) != 0;
        }
    }

    @Override
    public void setProtectShapes(ProtectShapesType protectShapesType) {
        this.generatedSetterHelperImpl((XmlObject)protectShapesType, PROTECTSHAPES$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectShapesType addNewProtectShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectShapesType protectShapesType = null;
            protectShapesType = (ProtectShapesType)this.get_store().add_element_user(PROTECTSHAPES$12);
            return protectShapesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProtectShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROTECTSHAPES$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectMastersType getProtectMasters() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectMastersType protectMastersType = null;
            protectMastersType = (ProtectMastersType)this.get_store().find_element_user(PROTECTMASTERS$14, 0);
            if (protectMastersType == null) {
                return null;
            }
            return protectMastersType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProtectMasters() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROTECTMASTERS$14) != 0;
        }
    }

    @Override
    public void setProtectMasters(ProtectMastersType protectMastersType) {
        this.generatedSetterHelperImpl((XmlObject)protectMastersType, PROTECTMASTERS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectMastersType addNewProtectMasters() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectMastersType protectMastersType = null;
            protectMastersType = (ProtectMastersType)this.get_store().add_element_user(PROTECTMASTERS$14);
            return protectMastersType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProtectMasters() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROTECTMASTERS$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectBkgndsType getProtectBkgnds() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectBkgndsType protectBkgndsType = null;
            protectBkgndsType = (ProtectBkgndsType)this.get_store().find_element_user(PROTECTBKGNDS$16, 0);
            if (protectBkgndsType == null) {
                return null;
            }
            return protectBkgndsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProtectBkgnds() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROTECTBKGNDS$16) != 0;
        }
    }

    @Override
    public void setProtectBkgnds(ProtectBkgndsType protectBkgndsType) {
        this.generatedSetterHelperImpl((XmlObject)protectBkgndsType, PROTECTBKGNDS$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ProtectBkgndsType addNewProtectBkgnds() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ProtectBkgndsType protectBkgndsType = null;
            protectBkgndsType = (ProtectBkgndsType)this.get_store().add_element_user(PROTECTBKGNDS$16);
            return protectBkgndsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProtectBkgnds() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROTECTBKGNDS$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CustomMenusFileType getCustomMenusFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CustomMenusFileType customMenusFileType = null;
            customMenusFileType = (CustomMenusFileType)this.get_store().find_element_user(CUSTOMMENUSFILE$18, 0);
            if (customMenusFileType == null) {
                return null;
            }
            return customMenusFileType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustomMenusFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMMENUSFILE$18) != 0;
        }
    }

    @Override
    public void setCustomMenusFile(CustomMenusFileType customMenusFileType) {
        this.generatedSetterHelperImpl((XmlObject)customMenusFileType, CUSTOMMENUSFILE$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CustomMenusFileType addNewCustomMenusFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CustomMenusFileType customMenusFileType = null;
            customMenusFileType = (CustomMenusFileType)this.get_store().add_element_user(CUSTOMMENUSFILE$18);
            return customMenusFileType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustomMenusFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMMENUSFILE$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CustomToolbarsFileType getCustomToolbarsFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CustomToolbarsFileType customToolbarsFileType = null;
            customToolbarsFileType = (CustomToolbarsFileType)this.get_store().find_element_user(CUSTOMTOOLBARSFILE$20, 0);
            if (customToolbarsFileType == null) {
                return null;
            }
            return customToolbarsFileType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustomToolbarsFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMTOOLBARSFILE$20) != 0;
        }
    }

    @Override
    public void setCustomToolbarsFile(CustomToolbarsFileType customToolbarsFileType) {
        this.generatedSetterHelperImpl((XmlObject)customToolbarsFileType, CUSTOMTOOLBARSFILE$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CustomToolbarsFileType addNewCustomToolbarsFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CustomToolbarsFileType customToolbarsFileType = null;
            customToolbarsFileType = (CustomToolbarsFileType)this.get_store().add_element_user(CUSTOMTOOLBARSFILE$20);
            return customToolbarsFileType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustomToolbarsFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMTOOLBARSFILE$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttachedToolbarsType getAttachedToolbars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttachedToolbarsType attachedToolbarsType = null;
            attachedToolbarsType = (AttachedToolbarsType)this.get_store().find_element_user(ATTACHEDTOOLBARS$22, 0);
            if (attachedToolbarsType == null) {
                return null;
            }
            return attachedToolbarsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAttachedToolbars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTACHEDTOOLBARS$22) != 0;
        }
    }

    @Override
    public void setAttachedToolbars(AttachedToolbarsType attachedToolbarsType) {
        this.generatedSetterHelperImpl((XmlObject)attachedToolbarsType, ATTACHEDTOOLBARS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttachedToolbarsType addNewAttachedToolbars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttachedToolbarsType attachedToolbarsType = null;
            attachedToolbarsType = (AttachedToolbarsType)this.get_store().add_element_user(ATTACHEDTOOLBARS$22);
            return attachedToolbarsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAttachedToolbars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTACHEDTOOLBARS$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getTopPage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TOPPAGE$24));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetTopPage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(TOPPAGE$24));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTopPage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TOPPAGE$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTopPage(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TOPPAGE$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TOPPAGE$24));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTopPage(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(TOPPAGE$24));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(TOPPAGE$24));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTopPage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TOPPAGE$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTTEXTSTYLE$26));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTTEXTSTYLE$26));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFAULTTEXTSTYLE$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultTextStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTTEXTSTYLE$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULTTEXTSTYLE$26));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultTextStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTTEXTSTYLE$26));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(DEFAULTTEXTSTYLE$26));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFAULTTEXTSTYLE$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDefaultLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTLINESTYLE$28));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetDefaultLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTLINESTYLE$28));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFAULTLINESTYLE$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultLineStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTLINESTYLE$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULTLINESTYLE$28));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultLineStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTLINESTYLE$28));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(DEFAULTLINESTYLE$28));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultLineStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFAULTLINESTYLE$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDefaultFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTFILLSTYLE$30));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetDefaultFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTFILLSTYLE$30));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFAULTFILLSTYLE$30) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultFillStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTFILLSTYLE$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULTFILLSTYLE$30));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultFillStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTFILLSTYLE$30));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(DEFAULTFILLSTYLE$30));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultFillStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFAULTFILLSTYLE$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDefaultGuideStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTGUIDESTYLE$32));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetDefaultGuideStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTGUIDESTYLE$32));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultGuideStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFAULTGUIDESTYLE$32) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefaultGuideStyle(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULTGUIDESTYLE$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULTGUIDESTYLE$32));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefaultGuideStyle(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(DEFAULTGUIDESTYLE$32));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(DEFAULTGUIDESTYLE$32));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultGuideStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFAULTGUIDESTYLE$32);
        }
    }
}

