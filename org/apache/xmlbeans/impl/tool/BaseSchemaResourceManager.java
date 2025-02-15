/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.tool.SchemaImportResolver;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public abstract class BaseSchemaResourceManager
extends SchemaImportResolver {
    private static final String USER_AGENT = "XMLBeans/" + XmlBeans.getVersion() + " (" + XmlBeans.getTitle() + ")";
    private String _defaultCopyDirectory;
    private DownloadedSchemasDocument _importsDoc;
    private Map _resourceForFilename = new HashMap();
    private Map _resourceForURL = new HashMap();
    private Map _resourceForNamespace = new HashMap();
    private Map _resourceForDigest = new HashMap();
    private Map _resourceForCacheEntry = new HashMap();
    private Set _redownloadSet = new HashSet();

    protected BaseSchemaResourceManager() {
    }

    protected final void init() {
        String defaultDir;
        if (this.fileExists(this.getIndexFilename())) {
            try {
                this._importsDoc = DownloadedSchemasDocument.Factory.parse(this.inputStreamForFile(this.getIndexFilename()));
            } catch (IOException e) {
                this._importsDoc = null;
            } catch (Exception e) {
                throw (IllegalStateException)new IllegalStateException("Problem reading xsdownload.xml: please fix or delete this file").initCause(e);
            }
        }
        if (this._importsDoc == null) {
            try {
                this._importsDoc = DownloadedSchemasDocument.Factory.parse("<dls:downloaded-schemas xmlns:dls='http://www.bea.com/2003/01/xmlbean/xsdownload' defaultDirectory='" + this.getDefaultSchemaDir() + "'/>");
            } catch (Exception e) {
                throw (IllegalStateException)new IllegalStateException().initCause(e);
            }
        }
        if ((defaultDir = this._importsDoc.getDownloadedSchemas().getDefaultDirectory()) == null) {
            defaultDir = this.getDefaultSchemaDir();
        }
        this._defaultCopyDirectory = defaultDir;
        DownloadedSchemaEntry[] entries = this._importsDoc.getDownloadedSchemas().getEntryArray();
        for (int i = 0; i < entries.length; ++i) {
            this.updateResource(entries[i]);
        }
    }

    public final void writeCache() throws IOException {
        InputStream input = this._importsDoc.newInputStream(new XmlOptions().setSavePrettyPrint());
        this.writeInputStreamToFile(input, this.getIndexFilename());
    }

    public final void processAll(boolean sync, boolean refresh, boolean imports) {
        this._redownloadSet = refresh ? new HashSet() : null;
        String[] allFilenames = this.getAllXSDFilenames();
        if (sync) {
            this.syncCacheWithLocalXsdFiles(allFilenames, false);
        }
        SchemaImportResolver.SchemaResource[] starters = this._resourceForFilename.values().toArray(new SchemaResource[0]);
        if (refresh) {
            this.redownloadEntries((SchemaResource[])starters);
        }
        if (imports) {
            this.resolveImports(starters);
        }
        this._redownloadSet = null;
    }

    public final void process(String[] uris, String[] filenames, boolean sync, boolean refresh, boolean imports) {
        SchemaResource resource;
        int i;
        this._redownloadSet = refresh ? new HashSet() : null;
        if (filenames.length > 0) {
            this.syncCacheWithLocalXsdFiles(filenames, true);
        } else if (sync) {
            this.syncCacheWithLocalXsdFiles(this.getAllXSDFilenames(), false);
        }
        HashSet<SchemaResource> starterset = new HashSet<SchemaResource>();
        for (i = 0; i < uris.length; ++i) {
            resource = (SchemaResource)this.lookupResource(null, uris[i]);
            if (resource == null) continue;
            starterset.add(resource);
        }
        for (i = 0; i < filenames.length; ++i) {
            resource = (SchemaResource)this._resourceForFilename.get(filenames);
            if (resource == null) continue;
            starterset.add(resource);
        }
        SchemaImportResolver.SchemaResource[] starters = starterset.toArray(new SchemaResource[0]);
        if (refresh) {
            this.redownloadEntries((SchemaResource[])starters);
        }
        if (imports) {
            this.resolveImports(starters);
        }
        this._redownloadSet = null;
    }

    public final void syncCacheWithLocalXsdFiles(String[] filenames, boolean deleteOnlyMentioned) {
        HashSet<SchemaResource> seenResources = new HashSet<SchemaResource>();
        HashSet<SchemaResource> vanishedResources = new HashSet<SchemaResource>();
        for (int i = 0; i < filenames.length; ++i) {
            String filename = filenames[i];
            SchemaResource resource = (SchemaResource)this._resourceForFilename.get(filename);
            if (resource != null) {
                if (this.fileExists(filename)) {
                    seenResources.add(resource);
                    continue;
                }
                vanishedResources.add(resource);
                continue;
            }
            String digest = null;
            try {
                String oldFilename;
                digest = this.shaDigestForFile(filename);
                resource = (SchemaResource)this._resourceForDigest.get(digest);
                if (resource != null && !this.fileExists(oldFilename = resource.getFilename())) {
                    this.warning("File " + filename + " is a rename of " + oldFilename);
                    resource.setFilename(filename);
                    seenResources.add(resource);
                    if (this._resourceForFilename.get(oldFilename) == resource) {
                        this._resourceForFilename.remove(oldFilename);
                    }
                    if (!this._resourceForFilename.containsKey(filename)) continue;
                    this._resourceForFilename.put(filename, resource);
                    continue;
                }
            } catch (IOException e) {
                // empty catch block
            }
            DownloadedSchemaEntry newEntry = this.addNewEntry();
            newEntry.setFilename(filename);
            this.warning("Caching information on new local file " + filename);
            if (digest != null) {
                newEntry.setSha1(digest);
            }
            seenResources.add(this.updateResource(newEntry));
        }
        if (deleteOnlyMentioned) {
            this.deleteResourcesInSet(vanishedResources, true);
        } else {
            this.deleteResourcesInSet(seenResources, false);
        }
    }

    private void redownloadEntries(SchemaResource[] resources) {
        for (int i = 0; i < resources.length; ++i) {
            this.redownloadResource(resources[i]);
        }
    }

    private void deleteResourcesInSet(Set seenResources, boolean setToDelete) {
        HashSet<DownloadedSchemaEntry> seenCacheEntries = new HashSet<DownloadedSchemaEntry>();
        for (SchemaResource resource : seenResources) {
            seenCacheEntries.add(resource._cacheEntry);
        }
        DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas = this._importsDoc.getDownloadedSchemas();
        for (int i = 0; i < downloadedSchemas.sizeOfEntryArray(); ++i) {
            DownloadedSchemaEntry cacheEntry = downloadedSchemas.getEntryArray(i);
            if (seenCacheEntries.contains(cacheEntry) != setToDelete) continue;
            SchemaResource resource = (SchemaResource)this._resourceForCacheEntry.get(cacheEntry);
            this.warning("Removing obsolete cache entry for " + resource.getFilename());
            if (resource != null) {
                this._resourceForCacheEntry.remove(cacheEntry);
                if (resource == this._resourceForFilename.get(resource.getFilename())) {
                    this._resourceForFilename.remove(resource.getFilename());
                }
                if (resource == this._resourceForDigest.get(resource.getSha1())) {
                    this._resourceForDigest.remove(resource.getSha1());
                }
                if (resource == this._resourceForNamespace.get(resource.getNamespace())) {
                    this._resourceForNamespace.remove(resource.getNamespace());
                }
                String[] urls = resource.getSchemaLocationArray();
                for (int j = 0; j < urls.length; ++j) {
                    if (resource != this._resourceForURL.get(urls[j])) continue;
                    this._resourceForURL.remove(urls[j]);
                }
            }
            downloadedSchemas.removeEntry(i);
            --i;
        }
    }

    private SchemaResource updateResource(DownloadedSchemaEntry entry) {
        String namespace;
        String digest;
        String filename = entry.getFilename();
        if (filename == null) {
            return null;
        }
        SchemaResource resource = new SchemaResource(entry);
        this._resourceForCacheEntry.put(entry, resource);
        if (!this._resourceForFilename.containsKey(filename)) {
            this._resourceForFilename.put(filename, resource);
        }
        if ((digest = resource.getSha1()) != null && !this._resourceForDigest.containsKey(digest)) {
            this._resourceForDigest.put(digest, resource);
        }
        if ((namespace = resource.getNamespace()) != null && !this._resourceForNamespace.containsKey(namespace)) {
            this._resourceForNamespace.put(namespace, resource);
        }
        String[] urls = resource.getSchemaLocationArray();
        for (int j = 0; j < urls.length; ++j) {
            if (this._resourceForURL.containsKey(urls[j])) continue;
            this._resourceForURL.put(urls[j], resource);
        }
        return resource;
    }

    private static DigestInputStream digestInputStream(InputStream input) {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw (IllegalStateException)new IllegalStateException().initCause(e);
        }
        DigestInputStream str = new DigestInputStream(input, sha);
        return str;
    }

    private DownloadedSchemaEntry addNewEntry() {
        return this._importsDoc.getDownloadedSchemas().addNewEntry();
    }

    @Override
    public SchemaImportResolver.SchemaResource lookupResource(String nsURI, String schemaLocation) {
        SchemaResource result = this.fetchFromCache(nsURI, schemaLocation);
        if (result != null) {
            if (this._redownloadSet != null) {
                this.redownloadResource(result);
            }
            return result;
        }
        if (schemaLocation == null) {
            this.warning("No cached schema for namespace '" + nsURI + "', and no url specified");
            return null;
        }
        result = this.copyOrIdentifyDuplicateURL(schemaLocation, nsURI);
        if (this._redownloadSet != null) {
            this._redownloadSet.add(result);
        }
        return result;
    }

    private SchemaResource fetchFromCache(String nsURI, String schemaLocation) {
        SchemaResource result;
        if (schemaLocation != null && (result = (SchemaResource)this._resourceForURL.get(schemaLocation)) != null) {
            return result;
        }
        if (nsURI != null && (result = (SchemaResource)this._resourceForNamespace.get(nsURI)) != null) {
            return result;
        }
        return null;
    }

    private String uniqueFilenameForURI(String schemaLocation) throws IOException, URISyntaxException {
        String localFilename = new URI(schemaLocation).getRawPath();
        int i = localFilename.lastIndexOf(47);
        if (i >= 0) {
            localFilename = localFilename.substring(i + 1);
        }
        if (localFilename.endsWith(".xsd")) {
            localFilename = localFilename.substring(0, localFilename.length() - 4);
        }
        if (localFilename.length() == 0) {
            localFilename = "schema";
        }
        String candidateFilename = localFilename;
        int suffix = 1;
        while (suffix < 1000) {
            String candidate = this._defaultCopyDirectory + "/" + candidateFilename + ".xsd";
            if (!this.fileExists(candidate)) {
                return candidate;
            }
            candidateFilename = localFilename + ++suffix;
        }
        throw new IOException("Problem with filename " + localFilename + ".xsd");
    }

    private void redownloadResource(SchemaResource resource) {
        if (this._redownloadSet != null) {
            if (this._redownloadSet.contains(resource)) {
                return;
            }
            this._redownloadSet.add(resource);
        }
        String filename = resource.getFilename();
        String schemaLocation = resource.getSchemaLocation();
        String digest = null;
        if (schemaLocation == null || filename == null) {
            return;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            URL url = new URL(schemaLocation);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent", USER_AGENT);
            conn.addRequestProperty("Accept", "application/xml, text/xml, */*");
            DigestInputStream input = BaseSchemaResourceManager.digestInputStream(conn.getInputStream());
            IOUtil.copyCompletely(input, buffer);
            digest = HexBin.bytesToString(input.getMessageDigest().digest());
        } catch (Exception e) {
            this.warning("Could not copy remote resource " + schemaLocation + ":" + e.getMessage());
            return;
        }
        if (digest.equals(resource.getSha1()) && this.fileExists(filename)) {
            this.warning("Resource " + filename + " is unchanged from " + schemaLocation + ".");
            return;
        }
        try {
            ByteArrayInputStream source = new ByteArrayInputStream(buffer.toByteArray());
            this.writeInputStreamToFile(source, filename);
        } catch (IOException e) {
            this.warning("Could not write to file " + filename + " for " + schemaLocation + ":" + e.getMessage());
            return;
        }
        this.warning("Refreshed " + filename + " from " + schemaLocation);
    }

    private SchemaResource copyOrIdentifyDuplicateURL(String schemaLocation, String namespace) {
        String digest;
        String targetFilename;
        try {
            targetFilename = this.uniqueFilenameForURI(schemaLocation);
        } catch (URISyntaxException e) {
            this.warning("Invalid URI '" + schemaLocation + "':" + e.getMessage());
            return null;
        } catch (IOException e) {
            this.warning("Could not create local file for " + schemaLocation + ":" + e.getMessage());
            return null;
        }
        try {
            URL url = new URL(schemaLocation);
            DigestInputStream input = BaseSchemaResourceManager.digestInputStream(url.openStream());
            this.writeInputStreamToFile(input, targetFilename);
            digest = HexBin.bytesToString(input.getMessageDigest().digest());
        } catch (Exception e) {
            this.warning("Could not copy remote resource " + schemaLocation + ":" + e.getMessage());
            return null;
        }
        SchemaResource result = (SchemaResource)this._resourceForDigest.get(digest);
        if (result != null) {
            this.deleteFile(targetFilename);
            result.addSchemaLocation(schemaLocation);
            if (!this._resourceForURL.containsKey(schemaLocation)) {
                this._resourceForURL.put(schemaLocation, result);
            }
            return result;
        }
        this.warning("Downloaded " + schemaLocation + " to " + targetFilename);
        DownloadedSchemaEntry newEntry = this.addNewEntry();
        newEntry.setFilename(targetFilename);
        newEntry.setSha1(digest);
        if (namespace != null) {
            newEntry.setNamespace(namespace);
        }
        newEntry.addSchemaLocation(schemaLocation);
        return this.updateResource(newEntry);
    }

    @Override
    public void reportActualNamespace(SchemaImportResolver.SchemaResource rresource, String actualNamespace) {
        SchemaResource resource = (SchemaResource)rresource;
        String oldNamespace = resource.getNamespace();
        if (oldNamespace != null && this._resourceForNamespace.get(oldNamespace) == resource) {
            this._resourceForNamespace.remove(oldNamespace);
        }
        if (!this._resourceForNamespace.containsKey(actualNamespace)) {
            this._resourceForNamespace.put(actualNamespace, resource);
        }
        resource.setNamespace(actualNamespace);
    }

    private String shaDigestForFile(String filename) throws IOException {
        DigestInputStream str = BaseSchemaResourceManager.digestInputStream(this.inputStreamForFile(filename));
        byte[] dummy = new byte[4096];
        int i = 1;
        while (i > 0) {
            i = str.read(dummy);
        }
        str.close();
        return HexBin.bytesToString(str.getMessageDigest().digest());
    }

    protected String getIndexFilename() {
        return "./xsdownload.xml";
    }

    protected String getDefaultSchemaDir() {
        return "./schema";
    }

    protected abstract void warning(String var1);

    protected abstract boolean fileExists(String var1);

    protected abstract InputStream inputStreamForFile(String var1) throws IOException;

    protected abstract void writeInputStreamToFile(InputStream var1, String var2) throws IOException;

    protected abstract void deleteFile(String var1);

    protected abstract String[] getAllXSDFilenames();

    private class SchemaResource
    implements SchemaImportResolver.SchemaResource {
        DownloadedSchemaEntry _cacheEntry;

        SchemaResource(DownloadedSchemaEntry entry) {
            this._cacheEntry = entry;
        }

        public void setFilename(String filename) {
            this._cacheEntry.setFilename(filename);
        }

        public String getFilename() {
            return this._cacheEntry.getFilename();
        }

        @Override
        public SchemaDocument.Schema getSchema() {
            if (!BaseSchemaResourceManager.this.fileExists(this.getFilename())) {
                BaseSchemaResourceManager.this.redownloadResource(this);
            }
            try {
                return SchemaDocument.Factory.parse(BaseSchemaResourceManager.this.inputStreamForFile(this.getFilename())).getSchema();
            } catch (Exception e) {
                return null;
            }
        }

        public String getSha1() {
            return this._cacheEntry.getSha1();
        }

        @Override
        public String getNamespace() {
            return this._cacheEntry.getNamespace();
        }

        public void setNamespace(String namespace) {
            this._cacheEntry.setNamespace(namespace);
        }

        @Override
        public String getSchemaLocation() {
            if (this._cacheEntry.sizeOfSchemaLocationArray() > 0) {
                return this._cacheEntry.getSchemaLocationArray(0);
            }
            return null;
        }

        public String[] getSchemaLocationArray() {
            return this._cacheEntry.getSchemaLocationArray();
        }

        public int hashCode() {
            return this.getFilename().hashCode();
        }

        public boolean equals(Object obj) {
            return this == obj || this.getFilename().equals(((SchemaResource)obj).getFilename());
        }

        public void addSchemaLocation(String schemaLocation) {
            this._cacheEntry.addSchemaLocation(schemaLocation);
        }
    }
}

