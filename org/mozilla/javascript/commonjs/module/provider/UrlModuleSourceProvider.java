/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.commonjs.module.provider.DefaultUrlConnectionExpiryCalculator;
import org.mozilla.javascript.commonjs.module.provider.ModuleSource;
import org.mozilla.javascript.commonjs.module.provider.ModuleSourceProviderBase;
import org.mozilla.javascript.commonjs.module.provider.ParsedContentType;
import org.mozilla.javascript.commonjs.module.provider.UrlConnectionExpiryCalculator;
import org.mozilla.javascript.commonjs.module.provider.UrlConnectionSecurityDomainProvider;

public class UrlModuleSourceProvider
extends ModuleSourceProviderBase {
    private static final long serialVersionUID = 1L;
    private final Iterable<URI> privilegedUris;
    private final Iterable<URI> fallbackUris;
    private final UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider;
    private final UrlConnectionExpiryCalculator urlConnectionExpiryCalculator;

    public UrlModuleSourceProvider(Iterable<URI> privilegedUris, Iterable<URI> fallbackUris) {
        this(privilegedUris, fallbackUris, new DefaultUrlConnectionExpiryCalculator(), null);
    }

    public UrlModuleSourceProvider(Iterable<URI> privilegedUris, Iterable<URI> fallbackUris, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator, UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider) {
        this.privilegedUris = privilegedUris;
        this.fallbackUris = fallbackUris;
        this.urlConnectionExpiryCalculator = urlConnectionExpiryCalculator;
        this.urlConnectionSecurityDomainProvider = urlConnectionSecurityDomainProvider;
    }

    @Override
    protected ModuleSource loadFromPrivilegedLocations(String moduleId, Object validator) throws IOException, URISyntaxException {
        return this.loadFromPathList(moduleId, validator, this.privilegedUris);
    }

    @Override
    protected ModuleSource loadFromFallbackLocations(String moduleId, Object validator) throws IOException, URISyntaxException {
        return this.loadFromPathList(moduleId, validator, this.fallbackUris);
    }

    private ModuleSource loadFromPathList(String moduleId, Object validator, Iterable<URI> paths) throws IOException, URISyntaxException {
        if (paths == null) {
            return null;
        }
        for (URI path : paths) {
            ModuleSource moduleSource = this.loadFromUri(path.resolve(moduleId), path, validator);
            if (moduleSource == null) continue;
            return moduleSource;
        }
        return null;
    }

    @Override
    protected ModuleSource loadFromUri(URI uri, URI base, Object validator) throws IOException, URISyntaxException {
        URI fullUri = new URI(uri + ".js");
        ModuleSource source = this.loadFromActualUri(fullUri, base, validator);
        return source != null ? source : this.loadFromActualUri(uri, base, validator);
    }

    protected ModuleSource loadFromActualUri(URI uri, URI base, Object validator) throws IOException {
        URLValidator applicableValidator;
        URLValidator uriValidator;
        URL url = new URL(base == null ? null : base.toURL(), uri.toString());
        long request_time = System.currentTimeMillis();
        URLConnection urlConnection = this.openUrlConnection(url);
        URLValidator uRLValidator = validator instanceof URLValidator ? ((uriValidator = (URLValidator)validator).appliesTo(uri) ? uriValidator : null) : (applicableValidator = null);
        if (applicableValidator != null) {
            applicableValidator.applyConditionals(urlConnection);
        }
        try {
            urlConnection.connect();
            if (applicableValidator != null && applicableValidator.updateValidator(urlConnection, request_time, this.urlConnectionExpiryCalculator)) {
                this.close(urlConnection);
                return NOT_MODIFIED;
            }
            return new ModuleSource(UrlModuleSourceProvider.getReader(urlConnection), this.getSecurityDomain(urlConnection), uri, base, new URLValidator(uri, urlConnection, request_time, this.urlConnectionExpiryCalculator));
        } catch (FileNotFoundException e) {
            return null;
        } catch (RuntimeException e) {
            this.close(urlConnection);
            throw e;
        } catch (IOException e) {
            this.close(urlConnection);
            throw e;
        }
    }

    private static Reader getReader(URLConnection urlConnection) throws IOException {
        return new InputStreamReader(urlConnection.getInputStream(), UrlModuleSourceProvider.getCharacterEncoding(urlConnection));
    }

    private static String getCharacterEncoding(URLConnection urlConnection) {
        ParsedContentType pct = new ParsedContentType(urlConnection.getContentType());
        String encoding = pct.getEncoding();
        if (encoding != null) {
            return encoding;
        }
        String contentType = pct.getContentType();
        if (contentType != null && contentType.startsWith("text/")) {
            return "8859_1";
        }
        return "utf-8";
    }

    private Object getSecurityDomain(URLConnection urlConnection) {
        return this.urlConnectionSecurityDomainProvider == null ? null : this.urlConnectionSecurityDomainProvider.getSecurityDomain(urlConnection);
    }

    private void close(URLConnection urlConnection) {
        try {
            urlConnection.getInputStream().close();
        } catch (IOException e) {
            this.onFailedClosingUrlConnection(urlConnection, e);
        }
    }

    protected void onFailedClosingUrlConnection(URLConnection urlConnection, IOException cause) {
    }

    protected URLConnection openUrlConnection(URL url) throws IOException {
        return url.openConnection();
    }

    @Override
    protected boolean entityNeedsRevalidation(Object validator) {
        return !(validator instanceof URLValidator) || ((URLValidator)validator).entityNeedsRevalidation();
    }

    private static class URLValidator
    implements Serializable {
        private static final long serialVersionUID = 1L;
        private final URI uri;
        private final long lastModified;
        private final String entityTags;
        private long expiry;

        public URLValidator(URI uri, URLConnection urlConnection, long request_time, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            this.uri = uri;
            this.lastModified = urlConnection.getLastModified();
            this.entityTags = this.getEntityTags(urlConnection);
            this.expiry = this.calculateExpiry(urlConnection, request_time, urlConnectionExpiryCalculator);
        }

        boolean updateValidator(URLConnection urlConnection, long request_time, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) throws IOException {
            boolean isResourceChanged = this.isResourceChanged(urlConnection);
            if (!isResourceChanged) {
                this.expiry = this.calculateExpiry(urlConnection, request_time, urlConnectionExpiryCalculator);
            }
            return isResourceChanged;
        }

        private boolean isResourceChanged(URLConnection urlConnection) throws IOException {
            if (urlConnection instanceof HttpURLConnection) {
                return ((HttpURLConnection)urlConnection).getResponseCode() == 304;
            }
            return this.lastModified == urlConnection.getLastModified();
        }

        private long calculateExpiry(URLConnection urlConnection, long request_time, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            long explicitExpiry;
            String cacheControl = urlConnection.getHeaderField("Cache-Control");
            if (cacheControl != null) {
                if (cacheControl.indexOf("max-age=2592000") != -1) {
                    return 0L;
                }
                int max_age = this.getMaxAge(cacheControl);
                if (-1 != max_age) {
                    long response_time = System.currentTimeMillis();
                    long apparent_age = Math.max(0L, response_time - urlConnection.getDate());
                    long corrected_received_age = Math.max(apparent_age, (long)urlConnection.getHeaderFieldInt("Age", 0) * 1000L);
                    long response_delay = response_time - request_time;
                    long corrected_initial_age = corrected_received_age + response_delay;
                    long creation_time = response_time - corrected_initial_age;
                    return (long)max_age * 1000L + creation_time;
                }
            }
            if ((explicitExpiry = urlConnection.getHeaderFieldDate("Expires", -1L)) != -1L) {
                return explicitExpiry;
            }
            return urlConnectionExpiryCalculator == null ? 0L : urlConnectionExpiryCalculator.calculateExpiry(urlConnection);
        }

        private int getMaxAge(String cacheControl) {
            int maxAgeIndex = cacheControl.indexOf("max-age");
            if (maxAgeIndex == -1) {
                return -1;
            }
            int eq = cacheControl.indexOf(61, maxAgeIndex + 7);
            if (eq == -1) {
                return -1;
            }
            int comma = cacheControl.indexOf(44, eq + 1);
            String strAge = comma == -1 ? cacheControl.substring(eq + 1) : cacheControl.substring(eq + 1, comma);
            try {
                return Integer.parseInt(strAge);
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        private String getEntityTags(URLConnection urlConnection) {
            List<String> etags = urlConnection.getHeaderFields().get("ETag");
            if (etags == null || etags.isEmpty()) {
                return null;
            }
            StringBuilder b = new StringBuilder();
            Iterator<String> it = etags.iterator();
            b.append(it.next());
            while (it.hasNext()) {
                b.append(", ").append(it.next());
            }
            return b.toString();
        }

        boolean appliesTo(URI uri) {
            return this.uri.equals(uri);
        }

        void applyConditionals(URLConnection urlConnection) {
            if (this.lastModified != 0L) {
                urlConnection.setIfModifiedSince(this.lastModified);
            }
            if (this.entityTags != null && this.entityTags.length() > 0) {
                urlConnection.addRequestProperty("If-None-Match", this.entityTags);
            }
        }

        boolean entityNeedsRevalidation() {
            return System.currentTimeMillis() > this.expiry;
        }
    }
}

