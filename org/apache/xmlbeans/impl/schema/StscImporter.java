/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.common.XmlEncodingSniffer;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class StscImporter {
    private static final String PROJECT_URL_PREFIX = "project://local";

    public static SchemaToProcess[] resolveImportsAndIncludes(SchemaDocument.Schema[] startWith, boolean forceSrcSave) {
        DownloadTable engine = new DownloadTable(startWith);
        return engine.resolveImportsAndIncludes(forceSrcSave);
    }

    private static String baseURLForDoc(XmlObject obj) {
        String path = obj.documentProperties().getSourceName();
        if (path == null) {
            return null;
        }
        if (path.startsWith("/")) {
            return PROJECT_URL_PREFIX + path.replace('\\', '/');
        }
        int colon = path.indexOf(58);
        if (colon > 1 && path.substring(0, colon).matches("^\\w+$")) {
            return path;
        }
        return "project://local/" + path.replace('\\', '/');
    }

    private static URI parseURI(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new URI(s);
        } catch (URISyntaxException syntax) {
            return null;
        }
    }

    public static URI resolve(URI base, String child) throws URISyntaxException {
        URI childUri = new URI(child);
        URI ruri = base.resolve(childUri);
        if (childUri.equals(ruri) && !childUri.isAbsolute() && (base.getScheme().equals("jar") || base.getScheme().equals("zip"))) {
            String r = base.toString();
            int lastslash = r.lastIndexOf(47);
            int exclPointSlashIndex = (r = r.substring(0, lastslash) + "/" + childUri).lastIndexOf("!/");
            if (exclPointSlashIndex > 0) {
                int slashDotDotIndex = r.indexOf("/..", exclPointSlashIndex);
                while (slashDotDotIndex > 0) {
                    int prevSlashIndex = r.lastIndexOf("/", slashDotDotIndex - 1);
                    if (prevSlashIndex >= exclPointSlashIndex) {
                        String temp = r.substring(slashDotDotIndex + 3);
                        r = r.substring(0, prevSlashIndex).concat(temp);
                    }
                    slashDotDotIndex = r.indexOf("/..", exclPointSlashIndex);
                }
            }
            return URI.create(r);
        }
        if ("file".equals(ruri.getScheme()) && !child.equals(ruri) && base.getPath().startsWith("//") && !ruri.getPath().startsWith("//")) {
            String path = "///".concat(ruri.getPath());
            try {
                ruri = new URI("file", null, path, ruri.getQuery(), ruri.getFragment());
            } catch (URISyntaxException uris) {
                // empty catch block
            }
        }
        return ruri;
    }

    public static class DownloadTable {
        private Map schemaByNsLocPair = new HashMap();
        private Map schemaByDigestKey = new HashMap();
        private LinkedList scanNeeded = new LinkedList();
        private Set emptyNamespaceSchemas = new HashSet();
        private Map scannedAlready = new HashMap();
        private Set failedDownloads = new HashSet();

        private SchemaDocument.Schema downloadSchema(XmlObject referencedBy, String targetNamespace, String locationURL) {
            SchemaDocument.Schema result;
            if (locationURL == null) {
                return null;
            }
            StscState state = StscState.get();
            URI baseURI = StscImporter.parseURI(StscImporter.baseURLForDoc(referencedBy));
            String absoluteURL = null;
            try {
                absoluteURL = baseURI == null ? locationURL : StscImporter.resolve(baseURI, locationURL).toString();
            } catch (URISyntaxException e) {
                state.error("Could not find resource - invalid location URL: " + e.getMessage(), 56, referencedBy);
                return null;
            }
            if (state.isFileProcessed(absoluteURL)) {
                return null;
            }
            if (absoluteURL != null && targetNamespace != null && (result = (SchemaDocument.Schema)this.schemaByNsLocPair.get(new NsLocPair(targetNamespace, absoluteURL))) != null) {
                return result;
            }
            if (targetNamespace != null && !targetNamespace.equals("")) {
                if (!state.shouldDownloadURI(absoluteURL) && (result = (SchemaDocument.Schema)this.schemaByNsLocPair.get(new NsLocPair(targetNamespace, null))) != null) {
                    return result;
                }
                if (state.linkerDefinesNamespace(targetNamespace)) {
                    return null;
                }
            }
            if (absoluteURL != null && (result = (SchemaDocument.Schema)this.schemaByNsLocPair.get(new NsLocPair(null, absoluteURL))) != null) {
                return result;
            }
            if (absoluteURL == null) {
                state.error("Could not find resource - no valid location URL.", 56, referencedBy);
                return null;
            }
            if (this.previouslyFailedToDownload(absoluteURL)) {
                return null;
            }
            if (!state.shouldDownloadURI(absoluteURL)) {
                state.error("Could not load resource \"" + absoluteURL + "\" (network downloads disabled).", 56, referencedBy);
                this.addFailedDownload(absoluteURL);
                return null;
            }
            try {
                block23: {
                    SchemaDocument.Schema result2;
                    block22: {
                        String shortname;
                        XmlObject xdoc;
                        block21: {
                            xdoc = DownloadTable.downloadDocument(state.getS4SLoader(), targetNamespace, absoluteURL);
                            result2 = this.findMatchByDigest(xdoc);
                            shortname = state.relativize(absoluteURL);
                            if (result2 == null) break block21;
                            String dupname = state.relativize(result2.documentProperties().getSourceName());
                            if (dupname != null) {
                                state.info(shortname + " is the same as " + dupname + " (ignoring the duplicate file)");
                            } else {
                                state.info(shortname + " is the same as another schema");
                            }
                            break block22;
                        }
                        XmlOptions voptions = new XmlOptions();
                        voptions.setErrorListener(state.getErrorListener());
                        if (!(xdoc instanceof SchemaDocument) || !xdoc.validate(voptions)) break block23;
                        SchemaDocument sDoc = (SchemaDocument)xdoc;
                        result2 = sDoc.getSchema();
                        state.info("Loading referenced file " + shortname);
                    }
                    NsLocPair key = new NsLocPair(DownloadTable.emptyStringIfNull(result2.getTargetNamespace()), absoluteURL);
                    this.addSuccessfulDownload(key, result2);
                    return result2;
                }
                state.error("Referenced document is not a valid schema", 56, referencedBy);
            } catch (MalformedURLException malformed) {
                state.error("URL \"" + absoluteURL + "\" is not well-formed", 56, referencedBy);
            } catch (IOException connectionProblem) {
                state.error(connectionProblem.toString(), 56, referencedBy);
            } catch (XmlException e) {
                state.error("Problem parsing referenced XML resource - " + e.getMessage(), 56, referencedBy);
            }
            this.addFailedDownload(absoluteURL);
            return null;
        }

        static XmlObject downloadDocument(SchemaTypeLoader loader, String namespace, String absoluteURL) throws MalformedURLException, IOException, XmlException {
            StscState state = StscState.get();
            EntityResolver resolver = state.getEntityResolver();
            if (resolver != null) {
                InputSource source = null;
                try {
                    source = resolver.resolveEntity(namespace, absoluteURL);
                } catch (SAXException e) {
                    throw new XmlException(e);
                }
                if (source != null) {
                    state.addSourceUri(absoluteURL, null);
                    Reader reader = source.getCharacterStream();
                    if (reader != null) {
                        reader = DownloadTable.copySchemaSource(absoluteURL, reader, state);
                        XmlOptions options = new XmlOptions();
                        options.setLoadLineNumbers();
                        options.setDocumentSourceName(absoluteURL);
                        return loader.parse(reader, null, options);
                    }
                    InputStream bytes = source.getByteStream();
                    if (bytes != null) {
                        bytes = DownloadTable.copySchemaSource(absoluteURL, bytes, state);
                        String encoding = source.getEncoding();
                        XmlOptions options = new XmlOptions();
                        options.setLoadLineNumbers();
                        options.setLoadMessageDigest();
                        options.setDocumentSourceName(absoluteURL);
                        if (encoding != null) {
                            options.setCharacterEncoding(encoding);
                        }
                        return loader.parse(bytes, null, options);
                    }
                    String urlToLoad = source.getSystemId();
                    if (urlToLoad == null) {
                        throw new IOException("EntityResolver unable to resolve " + absoluteURL + " (for namespace " + namespace + ")");
                    }
                    DownloadTable.copySchemaSource(absoluteURL, state, false);
                    XmlOptions options = new XmlOptions();
                    options.setLoadLineNumbers();
                    options.setLoadMessageDigest();
                    options.setDocumentSourceName(absoluteURL);
                    URL urlDownload = new URL(urlToLoad);
                    return loader.parse(urlDownload, null, options);
                }
            }
            state.addSourceUri(absoluteURL, null);
            DownloadTable.copySchemaSource(absoluteURL, state, false);
            XmlOptions options = new XmlOptions();
            options.setLoadLineNumbers();
            options.setLoadMessageDigest();
            URL urlDownload = new URL(absoluteURL);
            return loader.parse(urlDownload, null, options);
        }

        private void addSuccessfulDownload(NsLocPair key, SchemaDocument.Schema schema) {
            NsLocPair key2;
            byte[] digest = schema.documentProperties().getMessageDigest();
            if (digest == null) {
                StscState.get().addSchemaDigest(null);
            } else {
                DigestKey dk = new DigestKey(digest);
                if (!this.schemaByDigestKey.containsKey(dk)) {
                    this.schemaByDigestKey.put(new DigestKey(digest), schema);
                    StscState.get().addSchemaDigest(digest);
                }
            }
            this.schemaByNsLocPair.put(key, schema);
            NsLocPair key1 = new NsLocPair(key.getNamespaceURI(), null);
            if (!this.schemaByNsLocPair.containsKey(key1)) {
                this.schemaByNsLocPair.put(key1, schema);
            }
            if (!this.schemaByNsLocPair.containsKey(key2 = new NsLocPair(null, key.getLocationURL()))) {
                this.schemaByNsLocPair.put(key2, schema);
            }
        }

        private SchemaDocument.Schema findMatchByDigest(XmlObject original) {
            byte[] digest = original.documentProperties().getMessageDigest();
            if (digest == null) {
                return null;
            }
            return (SchemaDocument.Schema)this.schemaByDigestKey.get(new DigestKey(digest));
        }

        private void addFailedDownload(String locationURL) {
            this.failedDownloads.add(locationURL);
        }

        private boolean previouslyFailedToDownload(String locationURL) {
            return this.failedDownloads.contains(locationURL);
        }

        private static boolean nullableStringsMatch(String s1, String s2) {
            if (s1 == null && s2 == null) {
                return true;
            }
            if (s1 == null || s2 == null) {
                return false;
            }
            return s1.equals(s2);
        }

        private static String emptyStringIfNull(String s) {
            if (s == null) {
                return "";
            }
            return s;
        }

        private SchemaToProcess addScanNeeded(SchemaToProcess stp) {
            if (!this.scannedAlready.containsKey(stp)) {
                this.scannedAlready.put(stp, stp);
                this.scanNeeded.add(stp);
                return stp;
            }
            return (SchemaToProcess)this.scannedAlready.get(stp);
        }

        private void addEmptyNamespaceSchema(SchemaDocument.Schema s) {
            this.emptyNamespaceSchemas.add(s);
        }

        private void usedEmptyNamespaceSchema(SchemaDocument.Schema s) {
            this.emptyNamespaceSchemas.remove(s);
        }

        private boolean fetchRemainingEmptyNamespaceSchemas() {
            if (this.emptyNamespaceSchemas.isEmpty()) {
                return false;
            }
            for (SchemaDocument.Schema schema : this.emptyNamespaceSchemas) {
                this.addScanNeeded(new SchemaToProcess(schema, null));
            }
            this.emptyNamespaceSchemas.clear();
            return true;
        }

        private boolean hasNextToScan() {
            return !this.scanNeeded.isEmpty();
        }

        private SchemaToProcess nextToScan() {
            SchemaToProcess next = (SchemaToProcess)this.scanNeeded.removeFirst();
            return next;
        }

        public DownloadTable(SchemaDocument.Schema[] startWith) {
            for (int i = 0; i < startWith.length; ++i) {
                String targetNamespace = startWith[i].getTargetNamespace();
                NsLocPair key = new NsLocPair(targetNamespace, StscImporter.baseURLForDoc(startWith[i]));
                this.addSuccessfulDownload(key, startWith[i]);
                if (targetNamespace != null) {
                    this.addScanNeeded(new SchemaToProcess(startWith[i], null));
                    continue;
                }
                this.addEmptyNamespaceSchema(startWith[i]);
            }
        }

        public SchemaToProcess[] resolveImportsAndIncludes(boolean forceSave) {
            StscState state = StscState.get();
            ArrayList<SchemaToProcess> result = new ArrayList<SchemaToProcess>();
            boolean hasRedefinitions = false;
            block0: while (true) {
                if (this.hasNextToScan()) {
                    SchemaToProcess s;
                    int i;
                    SchemaToProcess stp = this.nextToScan();
                    String uri = stp.getSourceName();
                    state.addSourceUri(uri, null);
                    result.add(stp);
                    DownloadTable.copySchemaSource(uri, state, forceSave);
                    ImportDocument.Import[] imports = stp.getSchema().getImportArray();
                    for (int i2 = 0; i2 < imports.length; ++i2) {
                        SchemaDocument.Schema imported = this.downloadSchema(imports[i2], DownloadTable.emptyStringIfNull(imports[i2].getNamespace()), imports[i2].getSchemaLocation());
                        if (imported == null) continue;
                        if (!DownloadTable.nullableStringsMatch(imported.getTargetNamespace(), imports[i2].getNamespace())) {
                            StscState.get().error("Imported schema has a target namespace \"" + imported.getTargetNamespace() + "\" that does not match the specified \"" + imports[i2].getNamespace() + "\"", 4, (XmlObject)imports[i2]);
                            continue;
                        }
                        this.addScanNeeded(new SchemaToProcess(imported, null));
                    }
                    IncludeDocument.Include[] includes = stp.getSchema().getIncludeArray();
                    String sourceNamespace = stp.getChameleonNamespace();
                    if (sourceNamespace == null) {
                        sourceNamespace = DownloadTable.emptyStringIfNull(stp.getSchema().getTargetNamespace());
                    }
                    for (i = 0; i < includes.length; ++i) {
                        SchemaDocument.Schema included = this.downloadSchema(includes[i], null, includes[i].getSchemaLocation());
                        if (included == null) continue;
                        if (DownloadTable.emptyStringIfNull(included.getTargetNamespace()).equals(sourceNamespace)) {
                            s = this.addScanNeeded(new SchemaToProcess(included, null));
                            stp.addInclude(s);
                            continue;
                        }
                        if (included.getTargetNamespace() != null) {
                            StscState.get().error("Included schema has a target namespace \"" + included.getTargetNamespace() + "\" that does not match the source namespace \"" + sourceNamespace + "\"", 4, (XmlObject)includes[i]);
                            continue;
                        }
                        s = this.addScanNeeded(new SchemaToProcess(included, sourceNamespace));
                        stp.addInclude(s);
                        this.usedEmptyNamespaceSchema(included);
                    }
                    RedefineDocument.Redefine[] redefines = stp.getSchema().getRedefineArray();
                    sourceNamespace = stp.getChameleonNamespace();
                    if (sourceNamespace == null) {
                        sourceNamespace = DownloadTable.emptyStringIfNull(stp.getSchema().getTargetNamespace());
                    }
                    i = 0;
                    while (true) {
                        if (i >= redefines.length) continue block0;
                        SchemaDocument.Schema redefined = this.downloadSchema(redefines[i], null, redefines[i].getSchemaLocation());
                        if (redefined != null) {
                            if (DownloadTable.emptyStringIfNull(redefined.getTargetNamespace()).equals(sourceNamespace)) {
                                s = this.addScanNeeded(new SchemaToProcess(redefined, null));
                                stp.addRedefine(s, redefines[i]);
                                hasRedefinitions = true;
                            } else if (redefined.getTargetNamespace() != null) {
                                StscState.get().error("Redefined schema has a target namespace \"" + redefined.getTargetNamespace() + "\" that does not match the source namespace \"" + sourceNamespace + "\"", 4, (XmlObject)redefines[i]);
                            } else {
                                s = this.addScanNeeded(new SchemaToProcess(redefined, sourceNamespace));
                                stp.addRedefine(s, redefines[i]);
                                this.usedEmptyNamespaceSchema(redefined);
                                hasRedefinitions = true;
                            }
                        }
                        ++i;
                    }
                }
                if (!this.fetchRemainingEmptyNamespaceSchemas()) break;
            }
            if (hasRedefinitions) {
                for (int i = 0; i < result.size(); ++i) {
                    SchemaToProcess schemaToProcess = (SchemaToProcess)result.get(i);
                    schemaToProcess.buildIndirectReferences();
                }
            }
            return result.toArray(new SchemaToProcess[result.size()]);
        }

        private static Reader copySchemaSource(String url, Reader reader, StscState state) {
            if (state.getSchemasDir() == null) {
                return reader;
            }
            String schemalocation = state.sourceNameForUri(url);
            File targetFile = new File(state.getSchemasDir(), schemalocation);
            if (targetFile.exists()) {
                return reader;
            }
            try {
                File parentDir = new File(targetFile.getParent());
                IOUtil.createDir(parentDir, null);
                CharArrayReader car = DownloadTable.copy(reader);
                XmlEncodingSniffer xes = new XmlEncodingSniffer(car, null);
                OutputStreamWriter out = new OutputStreamWriter((OutputStream)new FileOutputStream(targetFile), xes.getXmlEncoding());
                IOUtil.copyCompletely(car, out);
                car.reset();
                return car;
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                return reader;
            }
        }

        private static InputStream copySchemaSource(String url, InputStream bytes, StscState state) {
            if (state.getSchemasDir() == null) {
                return bytes;
            }
            String schemalocation = state.sourceNameForUri(url);
            File targetFile = new File(state.getSchemasDir(), schemalocation);
            if (targetFile.exists()) {
                return bytes;
            }
            try {
                File parentDir = new File(targetFile.getParent());
                IOUtil.createDir(parentDir, null);
                ByteArrayInputStream bais = DownloadTable.copy(bytes);
                FileOutputStream out = new FileOutputStream(targetFile);
                IOUtil.copyCompletely(bais, out);
                bais.reset();
                return bais;
            } catch (IOException e) {
                System.err.println("IO Error " + e);
                return bytes;
            }
        }

        private static void copySchemaSource(String urlLoc, StscState state, boolean forceCopy) {
            if (state.getSchemasDir() != null) {
                String schemalocation = state.sourceNameForUri(urlLoc);
                File targetFile = new File(state.getSchemasDir(), schemalocation);
                if (forceCopy || !targetFile.exists()) {
                    try {
                        File parentDir = new File(targetFile.getParent());
                        IOUtil.createDir(parentDir, null);
                        InputStream in = null;
                        URL url = new URL(urlLoc);
                        try {
                            in = url.openStream();
                        } catch (FileNotFoundException fnfe) {
                            if (forceCopy && targetFile.exists()) {
                                targetFile.delete();
                            }
                            throw fnfe;
                        }
                        if (in != null) {
                            FileOutputStream out = new FileOutputStream(targetFile);
                            IOUtil.copyCompletely(in, out);
                        }
                    } catch (IOException e) {
                        System.err.println("IO Error " + e);
                    }
                }
            }
        }

        private static ByteArrayInputStream copy(InputStream is) throws IOException {
            int bytesRead;
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((bytesRead = is.read(buf, 0, 1024)) > 0) {
                baos.write(buf, 0, bytesRead);
            }
            return new ByteArrayInputStream(baos.toByteArray());
        }

        private static CharArrayReader copy(Reader is) throws IOException {
            int bytesRead;
            char[] buf = new char[1024];
            CharArrayWriter baos = new CharArrayWriter();
            while ((bytesRead = is.read(buf, 0, 1024)) > 0) {
                baos.write(buf, 0, bytesRead);
            }
            return new CharArrayReader(baos.toCharArray());
        }

        private static class DigestKey {
            byte[] _digest;
            int _hashCode;

            DigestKey(byte[] digest) {
                this._digest = digest;
                for (int i = 0; i < 4 && i < digest.length; ++i) {
                    this._hashCode <<= 8;
                    this._hashCode += digest[i];
                }
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof DigestKey)) {
                    return false;
                }
                return Arrays.equals(this._digest, ((DigestKey)o)._digest);
            }

            public int hashCode() {
                return this._hashCode;
            }
        }

        private static class NsLocPair {
            private String namespaceURI;
            private String locationURL;

            public NsLocPair(String namespaceURI, String locationURL) {
                this.namespaceURI = namespaceURI;
                this.locationURL = locationURL;
            }

            public String getNamespaceURI() {
                return this.namespaceURI;
            }

            public String getLocationURL() {
                return this.locationURL;
            }

            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof NsLocPair)) {
                    return false;
                }
                NsLocPair nsLocPair = (NsLocPair)o;
                if (this.locationURL != null ? !this.locationURL.equals(nsLocPair.locationURL) : nsLocPair.locationURL != null) {
                    return false;
                }
                return !(this.namespaceURI != null ? !this.namespaceURI.equals(nsLocPair.namespaceURI) : nsLocPair.namespaceURI != null);
            }

            public int hashCode() {
                int result = this.namespaceURI != null ? this.namespaceURI.hashCode() : 0;
                result = 29 * result + (this.locationURL != null ? this.locationURL.hashCode() : 0);
                return result;
            }
        }
    }

    public static class SchemaToProcess {
        private SchemaDocument.Schema schema;
        private String chameleonNamespace;
        private List includes;
        private List redefines;
        private List redefineObjects;
        private Set indirectIncludes;
        private Set indirectIncludedBy;

        public SchemaToProcess(SchemaDocument.Schema schema, String chameleonNamespace) {
            this.schema = schema;
            this.chameleonNamespace = chameleonNamespace;
        }

        public SchemaDocument.Schema getSchema() {
            return this.schema;
        }

        public String getSourceName() {
            return this.schema.documentProperties().getSourceName();
        }

        public String getChameleonNamespace() {
            return this.chameleonNamespace;
        }

        public List getRedefines() {
            return this.redefines;
        }

        public List getRedefineObjects() {
            return this.redefineObjects;
        }

        private void addInclude(SchemaToProcess include) {
            if (this.includes == null) {
                this.includes = new ArrayList();
            }
            this.includes.add(include);
        }

        private void addRedefine(SchemaToProcess redefine, RedefineDocument.Redefine object) {
            if (this.redefines == null || this.redefineObjects == null) {
                this.redefines = new ArrayList();
                this.redefineObjects = new ArrayList();
            }
            this.redefines.add(redefine);
            this.redefineObjects.add(object);
        }

        private void buildIndirectReferences() {
            SchemaToProcess schemaToProcess;
            int i;
            if (this.includes != null) {
                for (i = 0; i < this.includes.size(); ++i) {
                    schemaToProcess = (SchemaToProcess)this.includes.get(i);
                    this.addIndirectIncludes(schemaToProcess);
                }
            }
            if (this.redefines != null) {
                for (i = 0; i < this.redefines.size(); ++i) {
                    schemaToProcess = (SchemaToProcess)this.redefines.get(i);
                    this.addIndirectIncludes(schemaToProcess);
                }
            }
        }

        private void addIndirectIncludes(SchemaToProcess schemaToProcess) {
            if (this.indirectIncludes == null) {
                this.indirectIncludes = new HashSet();
            }
            this.indirectIncludes.add(schemaToProcess);
            if (schemaToProcess.indirectIncludedBy == null) {
                schemaToProcess.indirectIncludedBy = new HashSet();
            }
            schemaToProcess.indirectIncludedBy.add(this);
            SchemaToProcess.addIndirectIncludesHelper(this, schemaToProcess);
            if (this.indirectIncludedBy != null) {
                for (SchemaToProcess stp : this.indirectIncludedBy) {
                    stp.indirectIncludes.add(schemaToProcess);
                    schemaToProcess.indirectIncludedBy.add(stp);
                    SchemaToProcess.addIndirectIncludesHelper(stp, schemaToProcess);
                }
            }
        }

        private static void addIndirectIncludesHelper(SchemaToProcess including, SchemaToProcess schemaToProcess) {
            if (schemaToProcess.indirectIncludes != null) {
                for (SchemaToProcess stp : schemaToProcess.indirectIncludes) {
                    including.indirectIncludes.add(stp);
                    stp.indirectIncludedBy.add(including);
                }
            }
        }

        public boolean indirectIncludes(SchemaToProcess schemaToProcess) {
            return this.indirectIncludes != null && this.indirectIncludes.contains(schemaToProcess);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof SchemaToProcess)) {
                return false;
            }
            SchemaToProcess schemaToProcess = (SchemaToProcess)o;
            if (this.chameleonNamespace != null ? !this.chameleonNamespace.equals(schemaToProcess.chameleonNamespace) : schemaToProcess.chameleonNamespace != null) {
                return false;
            }
            return this.schema == schemaToProcess.schema;
        }

        public int hashCode() {
            int result = this.schema.hashCode();
            result = 29 * result + (this.chameleonNamespace != null ? this.chameleonNamespace.hashCode() : 0);
            return result;
        }
    }
}

