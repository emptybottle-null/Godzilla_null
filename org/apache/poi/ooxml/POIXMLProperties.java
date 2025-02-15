/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Optional;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument;

public class POIXMLProperties {
    private OPCPackage pkg;
    private CoreProperties core;
    private ExtendedProperties ext;
    private CustomProperties cust;
    private PackagePart extPart;
    private PackagePart custPart;
    private static final PropertiesDocument NEW_EXT_INSTANCE = PropertiesDocument.Factory.newInstance();
    private static final org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument NEW_CUST_INSTANCE;

    public POIXMLProperties(OPCPackage docPackage) throws IOException, OpenXML4JException, XmlException {
        this.pkg = docPackage;
        this.core = new CoreProperties((PackagePropertiesPart)this.pkg.getPackageProperties());
        PackageRelationshipCollection extRel = this.pkg.getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties");
        if (extRel.size() == 1) {
            this.extPart = this.pkg.getPart(extRel.getRelationship(0));
            if (this.extPart == null) {
                this.ext = new ExtendedProperties((PropertiesDocument)NEW_EXT_INSTANCE.copy());
            } else {
                PropertiesDocument props = PropertiesDocument.Factory.parse(this.extPart.getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.ext = new ExtendedProperties(props);
            }
        } else {
            this.extPart = null;
            this.ext = new ExtendedProperties((PropertiesDocument)NEW_EXT_INSTANCE.copy());
        }
        PackageRelationshipCollection custRel = this.pkg.getRelationshipsByType("http://schemas.openxmlformats.org/officeDocument/2006/relationships/custom-properties");
        if (custRel.size() == 1) {
            this.custPart = this.pkg.getPart(custRel.getRelationship(0));
            if (this.custPart == null) {
                this.cust = new CustomProperties((org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument)NEW_CUST_INSTANCE.copy());
            } else {
                org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument props = PropertiesDocument.Factory.parse(this.custPart.getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.cust = new CustomProperties(props);
            }
        } else {
            this.custPart = null;
            this.cust = new CustomProperties((org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument)NEW_CUST_INSTANCE.copy());
        }
    }

    public CoreProperties getCoreProperties() {
        return this.core;
    }

    public ExtendedProperties getExtendedProperties() {
        return this.ext;
    }

    public CustomProperties getCustomProperties() {
        return this.cust;
    }

    protected PackagePart getThumbnailPart() {
        PackageRelationshipCollection rels = this.pkg.getRelationshipsByType("http://schemas.openxmlformats.org/package/2006/relationships/metadata/thumbnail");
        if (rels.size() == 1) {
            return this.pkg.getPart(rels.getRelationship(0));
        }
        return null;
    }

    public String getThumbnailFilename() {
        PackagePart tPart = this.getThumbnailPart();
        if (tPart == null) {
            return null;
        }
        String name = tPart.getPartName().getName();
        return name.substring(name.lastIndexOf(47));
    }

    public InputStream getThumbnailImage() throws IOException {
        PackagePart tPart = this.getThumbnailPart();
        if (tPart == null) {
            return null;
        }
        return tPart.getInputStream();
    }

    public void setThumbnail(String filename, InputStream imageData) throws IOException {
        PackagePart tPart = this.getThumbnailPart();
        if (tPart == null) {
            this.pkg.addThumbnail(filename, imageData);
        } else {
            String newType = ContentTypes.getContentTypeFromFileExtension(filename);
            if (!newType.equals(tPart.getContentType())) {
                throw new IllegalArgumentException("Can't set a Thumbnail of type " + newType + " when existing one is of a different type " + tPart.getContentType());
            }
            StreamHelper.copyStream(imageData, tPart.getOutputStream());
        }
    }

    public void commit() throws IOException {
        Throwable throwable;
        OutputStream out;
        PackagePartName prtname;
        if (this.extPart == null && this.ext != null && this.ext.props != null && !NEW_EXT_INSTANCE.toString().equals(this.ext.props.toString())) {
            try {
                prtname = PackagingURIHelper.createPartName("/docProps/app.xml");
                this.pkg.addRelationship(prtname, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties");
                this.extPart = this.pkg.createPart(prtname, "application/vnd.openxmlformats-officedocument.extended-properties+xml");
            } catch (InvalidFormatException e) {
                throw new POIXMLException(e);
            }
        }
        if (this.custPart == null && this.cust != null && this.cust.props != null && !NEW_CUST_INSTANCE.toString().equals(this.cust.props.toString())) {
            try {
                prtname = PackagingURIHelper.createPartName("/docProps/custom.xml");
                this.pkg.addRelationship(prtname, TargetMode.INTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/custom-properties");
                this.custPart = this.pkg.createPart(prtname, "application/vnd.openxmlformats-officedocument.custom-properties+xml");
            } catch (InvalidFormatException e) {
                throw new POIXMLException(e);
            }
        }
        if (this.extPart != null && this.ext != null && this.ext.props != null) {
            out = this.extPart.getOutputStream();
            throwable = null;
            try {
                if (this.extPart.getSize() > 0L) {
                    this.extPart.clear();
                }
                this.ext.props.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            } finally {
                if (out != null) {
                    if (throwable != null) {
                        try {
                            out.close();
                        } catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                    } else {
                        out.close();
                    }
                }
            }
        }
        if (this.custPart != null && this.cust != null && this.cust.props != null) {
            this.custPart.clear();
            out = this.custPart.getOutputStream();
            throwable = null;
            try {
                this.cust.props.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            } catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            } finally {
                if (out != null) {
                    if (throwable != null) {
                        try {
                            out.close();
                        } catch (Throwable throwable5) {
                            throwable.addSuppressed(throwable5);
                        }
                    } else {
                        out.close();
                    }
                }
            }
        }
    }

    static {
        NEW_EXT_INSTANCE.addNewProperties();
        NEW_CUST_INSTANCE = PropertiesDocument.Factory.newInstance();
        NEW_CUST_INSTANCE.addNewProperties();
    }

    public static class CustomProperties {
        public static final String FORMAT_ID = "{D5CDD505-2E9C-101B-9397-08002B2CF9AE}";
        private org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument props;
        private Integer lastPid = null;

        private CustomProperties(org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument props) {
            this.props = props;
        }

        public CTProperties getUnderlyingProperties() {
            return this.props.getProperties();
        }

        private CTProperty add(String name) {
            if (this.contains(name)) {
                throw new IllegalArgumentException("A property with this name already exists in the custom properties");
            }
            CTProperty p = this.props.getProperties().addNewProperty();
            int pid = this.nextPid();
            p.setPid(pid);
            p.setFmtid(FORMAT_ID);
            p.setName(name);
            return p;
        }

        public void addProperty(String name, String value) {
            CTProperty p = this.add(name);
            p.setLpwstr(value);
        }

        public void addProperty(String name, double value) {
            CTProperty p = this.add(name);
            p.setR8(value);
        }

        public void addProperty(String name, int value) {
            CTProperty p = this.add(name);
            p.setI4(value);
        }

        public void addProperty(String name, boolean value) {
            CTProperty p = this.add(name);
            p.setBool(value);
        }

        protected int nextPid() {
            int propid = this.lastPid == null ? this.getLastPid() : this.lastPid.intValue();
            int nextid = propid + 1;
            this.lastPid = nextid;
            return nextid;
        }

        protected int getLastPid() {
            int propid = 1;
            for (CTProperty p : this.props.getProperties().getPropertyList()) {
                if (p.getPid() <= propid) continue;
                propid = p.getPid();
            }
            return propid;
        }

        public boolean contains(String name) {
            for (CTProperty p : this.props.getProperties().getPropertyList()) {
                if (!p.getName().equals(name)) continue;
                return true;
            }
            return false;
        }

        public CTProperty getProperty(String name) {
            for (CTProperty p : this.props.getProperties().getPropertyList()) {
                if (!p.getName().equals(name)) continue;
                return p;
            }
            return null;
        }
    }

    public static class ExtendedProperties {
        private PropertiesDocument props;

        private ExtendedProperties(PropertiesDocument props) {
            this.props = props;
        }

        public org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties getUnderlyingProperties() {
            return this.props.getProperties();
        }

        public String getTemplate() {
            if (this.props.getProperties().isSetTemplate()) {
                return this.props.getProperties().getTemplate();
            }
            return null;
        }

        public void setTemplate(String template) {
            this.props.getProperties().setTemplate(template);
        }

        public String getManager() {
            if (this.props.getProperties().isSetManager()) {
                return this.props.getProperties().getManager();
            }
            return null;
        }

        public void setManager(String manager) {
            this.props.getProperties().setManager(manager);
        }

        public String getCompany() {
            if (this.props.getProperties().isSetCompany()) {
                return this.props.getProperties().getCompany();
            }
            return null;
        }

        public void setCompany(String company) {
            this.props.getProperties().setCompany(company);
        }

        public String getPresentationFormat() {
            if (this.props.getProperties().isSetPresentationFormat()) {
                return this.props.getProperties().getPresentationFormat();
            }
            return null;
        }

        public void setPresentationFormat(String presentationFormat) {
            this.props.getProperties().setPresentationFormat(presentationFormat);
        }

        public String getApplication() {
            if (this.props.getProperties().isSetApplication()) {
                return this.props.getProperties().getApplication();
            }
            return null;
        }

        public void setApplication(String application) {
            this.props.getProperties().setApplication(application);
        }

        public String getAppVersion() {
            if (this.props.getProperties().isSetAppVersion()) {
                return this.props.getProperties().getAppVersion();
            }
            return null;
        }

        public void setAppVersion(String appVersion) {
            this.props.getProperties().setAppVersion(appVersion);
        }

        public int getPages() {
            if (this.props.getProperties().isSetPages()) {
                return this.props.getProperties().getPages();
            }
            return -1;
        }

        public void setPages(int pages) {
            this.props.getProperties().setPages(pages);
        }

        public int getWords() {
            if (this.props.getProperties().isSetWords()) {
                return this.props.getProperties().getWords();
            }
            return -1;
        }

        public void setWords(int words) {
            this.props.getProperties().setWords(words);
        }

        public int getCharacters() {
            if (this.props.getProperties().isSetCharacters()) {
                return this.props.getProperties().getCharacters();
            }
            return -1;
        }

        public void setCharacters(int characters) {
            this.props.getProperties().setCharacters(characters);
        }

        public int getCharactersWithSpaces() {
            if (this.props.getProperties().isSetCharactersWithSpaces()) {
                return this.props.getProperties().getCharactersWithSpaces();
            }
            return -1;
        }

        public void setCharactersWithSpaces(int charactersWithSpaces) {
            this.props.getProperties().setCharactersWithSpaces(charactersWithSpaces);
        }

        public int getLines() {
            if (this.props.getProperties().isSetLines()) {
                return this.props.getProperties().getLines();
            }
            return -1;
        }

        public void setLines(int lines) {
            this.props.getProperties().setLines(lines);
        }

        public int getParagraphs() {
            if (this.props.getProperties().isSetParagraphs()) {
                return this.props.getProperties().getParagraphs();
            }
            return -1;
        }

        public void setParagraphs(int paragraphs) {
            this.props.getProperties().setParagraphs(paragraphs);
        }

        public int getSlides() {
            if (this.props.getProperties().isSetSlides()) {
                return this.props.getProperties().getSlides();
            }
            return -1;
        }

        public void setSlides(int slides) {
            this.props.getProperties().setSlides(slides);
        }

        public int getNotes() {
            if (this.props.getProperties().isSetNotes()) {
                return this.props.getProperties().getNotes();
            }
            return -1;
        }

        public void setNotes(int notes) {
            this.props.getProperties().setNotes(notes);
        }

        public int getTotalTime() {
            if (this.props.getProperties().isSetTotalTime()) {
                return this.props.getProperties().getTotalTime();
            }
            return -1;
        }

        public void setTotalTime(int totalTime) {
            this.props.getProperties().setTotalTime(totalTime);
        }

        public int getHiddenSlides() {
            if (this.props.getProperties().isSetHiddenSlides()) {
                return this.props.getProperties().getHiddenSlides();
            }
            return -1;
        }

        public void setHiddenSlides(int hiddenSlides) {
            this.props.getProperties().setHiddenSlides(hiddenSlides);
        }

        public int getMMClips() {
            if (this.props.getProperties().isSetMMClips()) {
                return this.props.getProperties().getMMClips();
            }
            return -1;
        }

        public void setMMClips(int mmClips) {
            this.props.getProperties().setMMClips(mmClips);
        }

        public String getHyperlinkBase() {
            if (this.props.getProperties().isSetHyperlinkBase()) {
                return this.props.getProperties().getHyperlinkBase();
            }
            return null;
        }

        public void setHyperlinkBase(String hyperlinkBase) {
            this.props.getProperties().setHyperlinkBase(hyperlinkBase);
        }
    }

    public static class CoreProperties {
        private PackagePropertiesPart part;

        private CoreProperties(PackagePropertiesPart part) {
            this.part = part;
        }

        public String getCategory() {
            return this.part.getCategoryProperty().orElse(null);
        }

        public void setCategory(String category) {
            this.part.setCategoryProperty(category);
        }

        public String getContentStatus() {
            return this.part.getContentStatusProperty().orElse(null);
        }

        public void setContentStatus(String contentStatus) {
            this.part.setContentStatusProperty(contentStatus);
        }

        public String getContentType() {
            return this.part.getContentTypeProperty().orElse(null);
        }

        public void setContentType(String contentType) {
            this.part.setContentTypeProperty(contentType);
        }

        public Date getCreated() {
            return this.part.getCreatedProperty().orElse(null);
        }

        public void setCreated(Optional<Date> date) {
            this.part.setCreatedProperty(date);
        }

        public void setCreated(String date) {
            this.part.setCreatedProperty(date);
        }

        public String getCreator() {
            return this.part.getCreatorProperty().orElse(null);
        }

        public void setCreator(String creator) {
            this.part.setCreatorProperty(creator);
        }

        public String getDescription() {
            return this.part.getDescriptionProperty().orElse(null);
        }

        public void setDescription(String description) {
            this.part.setDescriptionProperty(description);
        }

        public String getIdentifier() {
            return this.part.getIdentifierProperty().orElse(null);
        }

        public void setIdentifier(String identifier) {
            this.part.setIdentifierProperty(identifier);
        }

        public String getKeywords() {
            return this.part.getKeywordsProperty().orElse(null);
        }

        public void setKeywords(String keywords) {
            this.part.setKeywordsProperty(keywords);
        }

        public Date getLastPrinted() {
            return this.part.getLastPrintedProperty().orElse(null);
        }

        public void setLastPrinted(Optional<Date> date) {
            this.part.setLastPrintedProperty(date);
        }

        public void setLastPrinted(String date) {
            this.part.setLastPrintedProperty(date);
        }

        public String getLastModifiedByUser() {
            return this.part.getLastModifiedByProperty().orElse(null);
        }

        public void setLastModifiedByUser(String user) {
            this.part.setLastModifiedByProperty(user);
        }

        public Date getModified() {
            return this.part.getModifiedProperty().orElse(null);
        }

        public void setModified(Optional<Date> date) {
            this.part.setModifiedProperty(date);
        }

        public void setModified(String date) {
            this.part.setModifiedProperty(date);
        }

        public String getSubject() {
            return this.part.getSubjectProperty().orElse(null);
        }

        public void setSubjectProperty(String subject) {
            this.part.setSubjectProperty(subject);
        }

        public void setTitle(String title) {
            this.part.setTitleProperty(title);
        }

        public String getTitle() {
            return this.part.getTitleProperty().orElse(null);
        }

        public String getRevision() {
            return this.part.getRevisionProperty().orElse(null);
        }

        public void setRevision(String revision) {
            try {
                Long.valueOf(revision);
                this.part.setRevisionProperty(revision);
            } catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }

        public PackagePropertiesPart getUnderlyingProperties() {
            return this.part;
        }
    }
}

