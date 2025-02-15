/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class ContentTypeManager {
    public static final String CONTENT_TYPES_PART_NAME = "[Content_Types].xml";
    public static final String TYPES_NAMESPACE_URI = "http://schemas.openxmlformats.org/package/2006/content-types";
    private static final String TYPES_TAG_NAME = "Types";
    private static final String DEFAULT_TAG_NAME = "Default";
    private static final String EXTENSION_ATTRIBUTE_NAME = "Extension";
    private static final String CONTENT_TYPE_ATTRIBUTE_NAME = "ContentType";
    private static final String OVERRIDE_TAG_NAME = "Override";
    private static final String PART_NAME_ATTRIBUTE_NAME = "PartName";
    protected OPCPackage container;
    private TreeMap<String, String> defaultContentType;
    private TreeMap<PackagePartName, String> overrideContentType;

    public ContentTypeManager(InputStream in, OPCPackage pkg) throws InvalidFormatException {
        this.container = pkg;
        this.defaultContentType = new TreeMap();
        if (in != null) {
            try {
                this.parseContentTypesFile(in);
            } catch (InvalidFormatException e) {
                InvalidFormatException ex = new InvalidFormatException("Can't read content types part !");
                ex.initCause(e);
                throw ex;
            }
        }
    }

    public void addContentType(PackagePartName partName, String contentType) {
        boolean defaultCTExists = this.defaultContentType.containsValue(contentType);
        String extension = partName.getExtension().toLowerCase(Locale.ROOT);
        if (extension.length() == 0 || this.defaultContentType.containsKey(extension) && !defaultCTExists || !this.defaultContentType.containsKey(extension) && defaultCTExists) {
            this.addOverrideContentType(partName, contentType);
        } else if (!defaultCTExists) {
            this.addDefaultContentType(extension, contentType);
        }
    }

    private void addOverrideContentType(PackagePartName partName, String contentType) {
        if (this.overrideContentType == null) {
            this.overrideContentType = new TreeMap();
        }
        this.overrideContentType.put(partName, contentType);
    }

    private void addDefaultContentType(String extension, String contentType) {
        this.defaultContentType.put(extension.toLowerCase(Locale.ROOT), contentType);
    }

    public void removeContentType(PackagePartName partName) throws InvalidOperationException {
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (this.overrideContentType != null && this.overrideContentType.get(partName) != null) {
            this.overrideContentType.remove(partName);
            return;
        }
        String extensionToDelete = partName.getExtension();
        boolean deleteDefaultContentTypeFlag = true;
        if (this.container != null) {
            try {
                for (PackagePart part : this.container.getParts()) {
                    if (part.getPartName().equals(partName) || !part.getPartName().getExtension().equalsIgnoreCase(extensionToDelete)) continue;
                    deleteDefaultContentTypeFlag = false;
                    break;
                }
            } catch (InvalidFormatException e) {
                throw new InvalidOperationException(e.getMessage());
            }
        }
        if (deleteDefaultContentTypeFlag) {
            this.defaultContentType.remove(extensionToDelete);
        }
        if (this.container != null) {
            try {
                for (PackagePart part : this.container.getParts()) {
                    if (part.getPartName().equals(partName) || this.getContentType(part.getPartName()) != null) continue;
                    throw new InvalidOperationException("Rule M2.4 is not respected: Nor a default element or override element is associated with the part: " + part.getPartName().getName());
                }
            } catch (InvalidFormatException e) {
                throw new InvalidOperationException(e.getMessage());
            }
        }
    }

    public boolean isContentTypeRegister(String contentType) {
        if (contentType == null) {
            throw new IllegalArgumentException("contentType");
        }
        return this.defaultContentType.values().contains(contentType) || this.overrideContentType != null && this.overrideContentType.values().contains(contentType);
    }

    public String getContentType(PackagePartName partName) {
        if (partName == null) {
            throw new IllegalArgumentException("partName");
        }
        if (this.overrideContentType != null && this.overrideContentType.containsKey(partName)) {
            return this.overrideContentType.get(partName);
        }
        String extension = partName.getExtension().toLowerCase(Locale.ROOT);
        if (this.defaultContentType.containsKey(extension)) {
            return this.defaultContentType.get(extension);
        }
        if (this.container != null && this.container.getPart(partName) != null) {
            throw new OpenXML4JRuntimeException("Rule M2.4 exception : Part '" + partName + "' not found - this error should NEVER happen!\nCheck that your code is closing the open resources in the correct order prior to filing a bug report.\nIf you can provide the triggering file, then please raise a bug at https://bz.apache.org/bugzilla/enter_bug.cgi?product=POI and attach the file that triggers it, thanks!");
        }
        return null;
    }

    public void clearAll() {
        this.defaultContentType.clear();
        if (this.overrideContentType != null) {
            this.overrideContentType.clear();
        }
    }

    public void clearOverrideContentTypes() {
        if (this.overrideContentType != null) {
            this.overrideContentType.clear();
        }
    }

    private void parseContentTypesFile(InputStream in) throws InvalidFormatException {
        try {
            Document xmlContentTypetDoc = DocumentHelper.readDocument(in);
            NodeList defaultTypes = xmlContentTypetDoc.getDocumentElement().getElementsByTagNameNS(TYPES_NAMESPACE_URI, DEFAULT_TAG_NAME);
            int defaultTypeCount = defaultTypes.getLength();
            for (int i = 0; i < defaultTypeCount; ++i) {
                Element element = (Element)defaultTypes.item(i);
                String extension = element.getAttribute(EXTENSION_ATTRIBUTE_NAME);
                String contentType = element.getAttribute(CONTENT_TYPE_ATTRIBUTE_NAME);
                this.addDefaultContentType(extension, contentType);
            }
            NodeList overrideTypes = xmlContentTypetDoc.getDocumentElement().getElementsByTagNameNS(TYPES_NAMESPACE_URI, OVERRIDE_TAG_NAME);
            int overrideTypeCount = overrideTypes.getLength();
            for (int i = 0; i < overrideTypeCount; ++i) {
                Element element = (Element)overrideTypes.item(i);
                URI uri = new URI(element.getAttribute(PART_NAME_ATTRIBUTE_NAME));
                PackagePartName partName = PackagingURIHelper.createPartName(uri);
                String contentType = element.getAttribute(CONTENT_TYPE_ATTRIBUTE_NAME);
                this.addOverrideContentType(partName, contentType);
            }
        } catch (IOException | URISyntaxException | SAXException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public boolean save(OutputStream outStream) {
        Document xmlOutDoc = DocumentHelper.createDocument();
        Element typesElem = xmlOutDoc.createElementNS(TYPES_NAMESPACE_URI, TYPES_TAG_NAME);
        xmlOutDoc.appendChild(typesElem);
        for (Map.Entry<String, String> entry : this.defaultContentType.entrySet()) {
            this.appendDefaultType(typesElem, entry);
        }
        if (this.overrideContentType != null) {
            for (Map.Entry<Object, String> entry : this.overrideContentType.entrySet()) {
                this.appendSpecificTypes(typesElem, entry);
            }
        }
        xmlOutDoc.normalize();
        return this.saveImpl(xmlOutDoc, outStream);
    }

    private void appendSpecificTypes(Element root, Map.Entry<PackagePartName, String> entry) {
        Element specificType = root.getOwnerDocument().createElementNS(TYPES_NAMESPACE_URI, OVERRIDE_TAG_NAME);
        specificType.setAttribute(PART_NAME_ATTRIBUTE_NAME, entry.getKey().getName());
        specificType.setAttribute(CONTENT_TYPE_ATTRIBUTE_NAME, entry.getValue());
        root.appendChild(specificType);
    }

    private void appendDefaultType(Element root, Map.Entry<String, String> entry) {
        Element defaultType = root.getOwnerDocument().createElementNS(TYPES_NAMESPACE_URI, DEFAULT_TAG_NAME);
        defaultType.setAttribute(EXTENSION_ATTRIBUTE_NAME, entry.getKey());
        defaultType.setAttribute(CONTENT_TYPE_ATTRIBUTE_NAME, entry.getValue());
        root.appendChild(defaultType);
    }

    public abstract boolean saveImpl(Document var1, OutputStream var2);
}

