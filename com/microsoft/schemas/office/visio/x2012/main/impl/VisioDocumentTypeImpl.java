/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.ColorsType
 *  com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType
 *  com.microsoft.schemas.office.visio.x2012.main.EventListType
 *  com.microsoft.schemas.office.visio.x2012.main.FaceNamesType
 *  com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType
 *  com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ColorsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType;
import com.microsoft.schemas.office.visio.x2012.main.EventListType;
import com.microsoft.schemas.office.visio.x2012.main.FaceNamesType;
import com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType;
import com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetsType;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class VisioDocumentTypeImpl
extends XmlComplexContentImpl
implements VisioDocumentType {
    private static final long serialVersionUID = 1L;
    private static final QName DOCUMENTSETTINGS$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "DocumentSettings");
    private static final QName COLORS$2 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Colors");
    private static final QName FACENAMES$4 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "FaceNames");
    private static final QName STYLESHEETS$6 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "StyleSheets");
    private static final QName DOCUMENTSHEET$8 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "DocumentSheet");
    private static final QName EVENTLIST$10 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "EventList");
    private static final QName HEADERFOOTER$12 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "HeaderFooter");
    private static final QName PUBLISHSETTINGS$14 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "PublishSettings");

    public VisioDocumentTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DocumentSettingsType getDocumentSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DocumentSettingsType documentSettingsType = null;
            documentSettingsType = (DocumentSettingsType)((Object)this.get_store().find_element_user(DOCUMENTSETTINGS$0, 0));
            if (documentSettingsType == null) {
                return null;
            }
            return documentSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocumentSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCUMENTSETTINGS$0) != 0;
        }
    }

    @Override
    public void setDocumentSettings(DocumentSettingsType documentSettingsType) {
        this.generatedSetterHelperImpl(documentSettingsType, DOCUMENTSETTINGS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DocumentSettingsType addNewDocumentSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DocumentSettingsType documentSettingsType = null;
            documentSettingsType = (DocumentSettingsType)((Object)this.get_store().add_element_user(DOCUMENTSETTINGS$0));
            return documentSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocumentSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCUMENTSETTINGS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ColorsType getColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ColorsType colorsType = null;
            colorsType = (ColorsType)this.get_store().find_element_user(COLORS$2, 0);
            if (colorsType == null) {
                return null;
            }
            return colorsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLORS$2) != 0;
        }
    }

    @Override
    public void setColors(ColorsType colorsType) {
        this.generatedSetterHelperImpl((XmlObject)colorsType, COLORS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ColorsType addNewColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ColorsType colorsType = null;
            colorsType = (ColorsType)this.get_store().add_element_user(COLORS$2);
            return colorsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLORS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FaceNamesType getFaceNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FaceNamesType faceNamesType = null;
            faceNamesType = (FaceNamesType)this.get_store().find_element_user(FACENAMES$4, 0);
            if (faceNamesType == null) {
                return null;
            }
            return faceNamesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFaceNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FACENAMES$4) != 0;
        }
    }

    @Override
    public void setFaceNames(FaceNamesType faceNamesType) {
        this.generatedSetterHelperImpl((XmlObject)faceNamesType, FACENAMES$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FaceNamesType addNewFaceNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FaceNamesType faceNamesType = null;
            faceNamesType = (FaceNamesType)this.get_store().add_element_user(FACENAMES$4);
            return faceNamesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFaceNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FACENAMES$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public StyleSheetsType getStyleSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            StyleSheetsType styleSheetsType = null;
            styleSheetsType = (StyleSheetsType)((Object)this.get_store().find_element_user(STYLESHEETS$6, 0));
            if (styleSheetsType == null) {
                return null;
            }
            return styleSheetsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyleSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLESHEETS$6) != 0;
        }
    }

    @Override
    public void setStyleSheets(StyleSheetsType styleSheetsType) {
        this.generatedSetterHelperImpl(styleSheetsType, STYLESHEETS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public StyleSheetsType addNewStyleSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            StyleSheetsType styleSheetsType = null;
            styleSheetsType = (StyleSheetsType)((Object)this.get_store().add_element_user(STYLESHEETS$6));
            return styleSheetsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyleSheets() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLESHEETS$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DocumentSheetType getDocumentSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DocumentSheetType documentSheetType = null;
            documentSheetType = (DocumentSheetType)this.get_store().find_element_user(DOCUMENTSHEET$8, 0);
            if (documentSheetType == null) {
                return null;
            }
            return documentSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocumentSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCUMENTSHEET$8) != 0;
        }
    }

    @Override
    public void setDocumentSheet(DocumentSheetType documentSheetType) {
        this.generatedSetterHelperImpl((XmlObject)documentSheetType, DOCUMENTSHEET$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DocumentSheetType addNewDocumentSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DocumentSheetType documentSheetType = null;
            documentSheetType = (DocumentSheetType)this.get_store().add_element_user(DOCUMENTSHEET$8);
            return documentSheetType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocumentSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCUMENTSHEET$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EventListType getEventList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EventListType eventListType = null;
            eventListType = (EventListType)this.get_store().find_element_user(EVENTLIST$10, 0);
            if (eventListType == null) {
                return null;
            }
            return eventListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEventList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EVENTLIST$10) != 0;
        }
    }

    @Override
    public void setEventList(EventListType eventListType) {
        this.generatedSetterHelperImpl((XmlObject)eventListType, EVENTLIST$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EventListType addNewEventList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EventListType eventListType = null;
            eventListType = (EventListType)this.get_store().add_element_user(EVENTLIST$10);
            return eventListType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEventList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EVENTLIST$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public HeaderFooterType getHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            HeaderFooterType headerFooterType = null;
            headerFooterType = (HeaderFooterType)this.get_store().find_element_user(HEADERFOOTER$12, 0);
            if (headerFooterType == null) {
                return null;
            }
            return headerFooterType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HEADERFOOTER$12) != 0;
        }
    }

    @Override
    public void setHeaderFooter(HeaderFooterType headerFooterType) {
        this.generatedSetterHelperImpl((XmlObject)headerFooterType, HEADERFOOTER$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public HeaderFooterType addNewHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            HeaderFooterType headerFooterType = null;
            headerFooterType = (HeaderFooterType)this.get_store().add_element_user(HEADERFOOTER$12);
            return headerFooterType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HEADERFOOTER$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PublishSettingsType getPublishSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PublishSettingsType publishSettingsType = null;
            publishSettingsType = (PublishSettingsType)this.get_store().find_element_user(PUBLISHSETTINGS$14, 0);
            if (publishSettingsType == null) {
                return null;
            }
            return publishSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPublishSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PUBLISHSETTINGS$14) != 0;
        }
    }

    @Override
    public void setPublishSettings(PublishSettingsType publishSettingsType) {
        this.generatedSetterHelperImpl((XmlObject)publishSettingsType, PUBLISHSETTINGS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PublishSettingsType addNewPublishSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PublishSettingsType publishSettingsType = null;
            publishSettingsType = (PublishSettingsType)this.get_store().add_element_user(PUBLISHSETTINGS$14);
            return publishSettingsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPublishSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PUBLISHSETTINGS$14, 0);
        }
    }
}

