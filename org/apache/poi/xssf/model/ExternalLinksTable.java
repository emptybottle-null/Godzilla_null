/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Name;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument;

public class ExternalLinksTable
extends POIXMLDocumentPart {
    private CTExternalLink link;

    public ExternalLinksTable() {
        this.link = CTExternalLink.Factory.newInstance();
        this.link.addNewExternalBook();
    }

    public ExternalLinksTable(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            ExternalLinkDocument doc = ExternalLinkDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.link = doc.getExternalLink();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream out) throws IOException {
        ExternalLinkDocument doc = ExternalLinkDocument.Factory.newInstance();
        doc.setExternalLink(this.link);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.writeTo(out);
        out.close();
    }

    public CTExternalLink getCTExternalLink() {
        return this.link;
    }

    public String getLinkedFileName() {
        String rId = this.link.getExternalBook().getId();
        PackageRelationship rel = this.getPackagePart().getRelationship(rId);
        if (rel != null && rel.getTargetMode() == TargetMode.EXTERNAL) {
            return rel.getTargetURI().toString();
        }
        return null;
    }

    public void setLinkedFileName(String target) {
        String rId = this.link.getExternalBook().getId();
        if (rId != null && !rId.isEmpty()) {
            this.getPackagePart().removeRelationship(rId);
        }
        PackageRelationship newRel = this.getPackagePart().addExternalRelationship(target, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/externalLinkPath");
        this.link.getExternalBook().setId(newRel.getId());
    }

    public List<String> getSheetNames() {
        CTExternalSheetName[] sheetNames = this.link.getExternalBook().getSheetNames().getSheetNameArray();
        ArrayList<String> names = new ArrayList<String>(sheetNames.length);
        for (CTExternalSheetName name : sheetNames) {
            names.add(name.getVal());
        }
        return names;
    }

    public List<Name> getDefinedNames() {
        CTExternalDefinedName[] extNames = this.link.getExternalBook().getDefinedNames().getDefinedNameArray();
        ArrayList<Name> names = new ArrayList<Name>(extNames.length);
        for (CTExternalDefinedName extName : extNames) {
            names.add(new ExternalName(extName));
        }
        return names;
    }

    protected class ExternalName
    implements Name {
        private CTExternalDefinedName name;

        protected ExternalName(CTExternalDefinedName name) {
            this.name = name;
        }

        @Override
        public String getNameName() {
            return this.name.getName();
        }

        @Override
        public void setNameName(String name) {
            this.name.setName(name);
        }

        @Override
        public String getSheetName() {
            int sheetId = this.getSheetIndex();
            if (sheetId >= 0) {
                return ExternalLinksTable.this.getSheetNames().get(sheetId);
            }
            return null;
        }

        @Override
        public int getSheetIndex() {
            if (this.name.isSetSheetId()) {
                return (int)this.name.getSheetId();
            }
            return -1;
        }

        @Override
        public void setSheetIndex(int sheetId) {
            this.name.setSheetId(sheetId);
        }

        @Override
        public String getRefersToFormula() {
            return this.name.getRefersTo().substring(1);
        }

        @Override
        public void setRefersToFormula(String formulaText) {
            this.name.setRefersTo('=' + formulaText);
        }

        @Override
        public boolean isFunctionName() {
            return false;
        }

        @Override
        public boolean isDeleted() {
            return false;
        }

        @Override
        public String getComment() {
            return null;
        }

        @Override
        public void setComment(String comment) {
            throw new IllegalStateException("Not Supported");
        }

        @Override
        public void setFunction(boolean value) {
            throw new IllegalStateException("Not Supported");
        }
    }
}

