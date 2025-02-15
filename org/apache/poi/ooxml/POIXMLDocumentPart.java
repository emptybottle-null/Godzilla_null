/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFRelation;

public class POIXMLDocumentPart {
    private static final POILogger logger = POILogFactory.getLogger(POIXMLDocumentPart.class);
    private String coreDocumentRel = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument";
    private PackagePart packagePart;
    private POIXMLDocumentPart parent;
    private Map<String, RelationPart> relations = new LinkedHashMap<String, RelationPart>();
    private boolean isCommitted = false;
    private int relationCounter;

    @Removal(version="5.0.0")
    @Deprecated
    public boolean isCommited() {
        return this.isCommitted();
    }

    public boolean isCommitted() {
        return this.isCommitted;
    }

    @Removal(version="5.0.0")
    @Deprecated
    public void setCommited(boolean isCommitted) {
        this.isCommitted = isCommitted;
    }

    public void setCommitted(boolean isCommitted) {
        this.isCommitted = isCommitted;
    }

    int incrementRelationCounter() {
        ++this.relationCounter;
        return this.relationCounter;
    }

    int decrementRelationCounter() {
        --this.relationCounter;
        return this.relationCounter;
    }

    int getRelationCounter() {
        return this.relationCounter;
    }

    public POIXMLDocumentPart(OPCPackage pkg) {
        this(pkg, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument");
    }

    public POIXMLDocumentPart(OPCPackage pkg, String coreDocumentRel) {
        this(POIXMLDocumentPart.getPartFromOPCPackage(pkg, coreDocumentRel));
        this.coreDocumentRel = coreDocumentRel;
    }

    public POIXMLDocumentPart() {
    }

    public POIXMLDocumentPart(PackagePart part) {
        this(null, part);
    }

    public POIXMLDocumentPart(POIXMLDocumentPart parent, PackagePart part) {
        this.packagePart = part;
        this.parent = parent;
    }

    protected final void rebase(OPCPackage pkg) throws InvalidFormatException {
        PackageRelationshipCollection cores = this.packagePart.getRelationshipsByType(this.coreDocumentRel);
        if (cores.size() != 1) {
            throw new IllegalStateException("Tried to rebase using " + this.coreDocumentRel + " but found " + cores.size() + " parts of the right type");
        }
        this.packagePart = this.packagePart.getRelatedPart(cores.getRelationship(0));
    }

    public final PackagePart getPackagePart() {
        return this.packagePart;
    }

    public final List<POIXMLDocumentPart> getRelations() {
        ArrayList l = new ArrayList();
        for (RelationPart rp : this.relations.values()) {
            l.add(rp.getDocumentPart());
        }
        return Collections.unmodifiableList(l);
    }

    public final List<RelationPart> getRelationParts() {
        ArrayList<RelationPart> l = new ArrayList<RelationPart>(this.relations.values());
        return Collections.unmodifiableList(l);
    }

    public final POIXMLDocumentPart getRelationById(String id) {
        RelationPart rp = this.getRelationPartById(id);
        return rp == null ? null : (POIXMLDocumentPart)rp.getDocumentPart();
    }

    public final RelationPart getRelationPartById(String id) {
        return this.relations.get(id);
    }

    public final String getRelationId(POIXMLDocumentPart part) {
        for (RelationPart rp : this.relations.values()) {
            if (rp.getDocumentPart() != part) continue;
            return rp.getRelationship().getId();
        }
        return null;
    }

    public final RelationPart addRelation(String relId, POIXMLRelation relationshipType, POIXMLDocumentPart part) {
        PackageRelationship pr = this.packagePart.findExistingRelation(part.getPackagePart());
        if (pr == null) {
            PackagePartName ppn = part.getPackagePart().getPartName();
            String relType = relationshipType.getRelation();
            pr = this.packagePart.addRelationship(ppn, TargetMode.INTERNAL, relType, relId);
        }
        this.addRelation(pr, part);
        return new RelationPart(pr, part);
    }

    private void addRelation(PackageRelationship pr, POIXMLDocumentPart part) {
        this.relations.put(pr.getId(), new RelationPart(pr, part));
        part.incrementRelationCounter();
    }

    protected final void removeRelation(POIXMLDocumentPart part) {
        this.removeRelation(part, true);
    }

    protected final boolean removeRelation(POIXMLDocumentPart part, boolean removeUnusedParts) {
        String id = this.getRelationId(part);
        return this.removeRelation(id, removeUnusedParts);
    }

    protected final void removeRelation(String partId) {
        this.removeRelation(partId, true);
    }

    private final boolean removeRelation(String partId, boolean removeUnusedParts) {
        RelationPart rp = this.relations.get(partId);
        if (rp == null) {
            return false;
        }
        Object part = rp.getDocumentPart();
        ((POIXMLDocumentPart)part).decrementRelationCounter();
        this.getPackagePart().removeRelationship(partId);
        this.relations.remove(partId);
        if (removeUnusedParts && ((POIXMLDocumentPart)part).getRelationCounter() == 0) {
            try {
                ((POIXMLDocumentPart)part).onDocumentRemove();
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
            this.getPackagePart().getPackage().removePart(((POIXMLDocumentPart)part).getPackagePart());
        }
        return true;
    }

    public final POIXMLDocumentPart getParent() {
        return this.parent;
    }

    public String toString() {
        return this.packagePart == null ? "" : this.packagePart.toString();
    }

    protected void commit() throws IOException {
    }

    protected final void onSave(Set<PackagePart> alreadySaved) throws IOException {
        if (this.isCommitted) {
            return;
        }
        this.prepareForCommit();
        this.commit();
        alreadySaved.add(this.getPackagePart());
        for (RelationPart rp : this.relations.values()) {
            Object p = rp.getDocumentPart();
            if (alreadySaved.contains(((POIXMLDocumentPart)p).getPackagePart())) continue;
            ((POIXMLDocumentPart)p).onSave(alreadySaved);
        }
    }

    protected void prepareForCommit() {
        PackagePart part = this.getPackagePart();
        if (part != null) {
            part.clear();
        }
    }

    public final POIXMLDocumentPart createRelationship(POIXMLRelation descriptor, POIXMLFactory factory) {
        return this.createRelationship(descriptor, factory, -1, false).getDocumentPart();
    }

    public final POIXMLDocumentPart createRelationship(POIXMLRelation descriptor, POIXMLFactory factory, int idx) {
        return this.createRelationship(descriptor, factory, idx, false).getDocumentPart();
    }

    protected final int getNextPartNumber(POIXMLRelation descriptor, int minIdx) {
        OPCPackage pkg = this.packagePart.getPackage();
        try {
            String name = descriptor.getDefaultFileName();
            if (name.equals(descriptor.getFileName(9999))) {
                PackagePartName ppName = PackagingURIHelper.createPartName(name);
                if (pkg.containPart(ppName)) {
                    return -1;
                }
                return 0;
            }
            int maxIdx = minIdx + pkg.getParts().size();
            for (int idx = minIdx < 0 ? 1 : minIdx; idx <= maxIdx; ++idx) {
                name = descriptor.getFileName(idx);
                PackagePartName ppName = PackagingURIHelper.createPartName(name);
                if (pkg.containPart(ppName)) continue;
                return idx;
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
        return -1;
    }

    public final RelationPart createRelationship(POIXMLRelation descriptor, POIXMLFactory factory, int idx, boolean noRelation) {
        try {
            PackagePartName ppName = PackagingURIHelper.createPartName(descriptor.getFileName(idx));
            PackageRelationship rel = null;
            PackagePart part = this.packagePart.getPackage().createPart(ppName, descriptor.getContentType());
            if (!noRelation) {
                rel = this.packagePart.addRelationship(ppName, TargetMode.INTERNAL, descriptor.getRelation());
            }
            POIXMLDocumentPart doc = factory.newDocumentPart(descriptor);
            doc.packagePart = part;
            doc.parent = this;
            if (!noRelation) {
                this.addRelation(rel, doc);
            }
            return new RelationPart(rel, doc);
        } catch (PartAlreadyExistsException pae) {
            throw pae;
        } catch (Exception e) {
            throw new POIXMLException(e);
        }
    }

    protected void read(POIXMLFactory factory, Map<PackagePart, POIXMLDocumentPart> context) throws OpenXML4JException {
        PackagePart pp = this.getPackagePart();
        if (pp.getContentType().equals(XWPFRelation.GLOSSARY_DOCUMENT.getContentType())) {
            logger.log(5, "POI does not currently support template.main+xml (glossary) parts.  Skipping this part for now.");
            return;
        }
        POIXMLDocumentPart otherChild = context.put(pp, this);
        if (otherChild != null && otherChild != this) {
            throw new POIXMLException("Unique PackagePart-POIXMLDocumentPart relation broken!");
        }
        if (!pp.hasRelationships()) {
            return;
        }
        PackageRelationshipCollection rels = this.packagePart.getRelationships();
        ArrayList<POIXMLDocumentPart> readLater = new ArrayList<POIXMLDocumentPart>();
        for (PackageRelationship rel : rels) {
            if (rel.getTargetMode() != TargetMode.INTERNAL) continue;
            URI uri = rel.getTargetURI();
            PackagePartName relName = uri.getRawFragment() != null ? PackagingURIHelper.createPartName(uri.getPath()) : PackagingURIHelper.createPartName(uri);
            PackagePart p = this.packagePart.getPackage().getPart(relName);
            if (p == null) {
                logger.log(7, "Skipped invalid entry " + rel.getTargetURI());
                continue;
            }
            POIXMLDocumentPart childPart = context.get(p);
            if (childPart == null) {
                childPart = factory.createDocumentPart(this, p);
                if (this instanceof XDDFChart && childPart instanceof XSSFWorkbook) {
                    ((XDDFChart)this).setWorkbook((XSSFWorkbook)childPart);
                }
                childPart.parent = this;
                context.put(p, childPart);
                readLater.add(childPart);
            }
            this.addRelation(rel, childPart);
        }
        for (POIXMLDocumentPart childPart : readLater) {
            childPart.read(factory, context);
        }
    }

    protected PackagePart getTargetPart(PackageRelationship rel) throws InvalidFormatException {
        return this.getPackagePart().getRelatedPart(rel);
    }

    protected void onDocumentCreate() throws IOException {
    }

    protected void onDocumentRead() throws IOException {
    }

    protected void onDocumentRemove() throws IOException {
    }

    @Internal
    @Deprecated
    public static void _invokeOnDocumentRead(POIXMLDocumentPart part) throws IOException {
        part.onDocumentRead();
    }

    private static PackagePart getPartFromOPCPackage(OPCPackage pkg, String coreDocumentRel) {
        PackageRelationship coreRel = pkg.getRelationshipsByType(coreDocumentRel).getRelationship(0);
        if (coreRel != null) {
            PackagePart pp = pkg.getPart(coreRel);
            if (pp == null) {
                IOUtils.closeQuietly(pkg);
                throw new POIXMLException("OOXML file structure broken/invalid - core document '" + coreRel.getTargetURI() + "' not found.");
            }
            return pp;
        }
        coreRel = pkg.getRelationshipsByType("http://purl.oclc.org/ooxml/officeDocument/relationships/officeDocument").getRelationship(0);
        if (coreRel != null) {
            IOUtils.closeQuietly(pkg);
            throw new POIXMLException("Strict OOXML isn't currently supported, please see bug #57699");
        }
        IOUtils.closeQuietly(pkg);
        throw new POIXMLException("OOXML file structure broken/invalid - no core document found!");
    }

    public static class RelationPart {
        private final PackageRelationship relationship;
        private final POIXMLDocumentPart documentPart;

        RelationPart(PackageRelationship relationship, POIXMLDocumentPart documentPart) {
            this.relationship = relationship;
            this.documentPart = documentPart;
        }

        public PackageRelationship getRelationship() {
            return this.relationship;
        }

        public <T extends POIXMLDocumentPart> T getDocumentPart() {
            return (T)this.documentPart;
        }
    }
}

