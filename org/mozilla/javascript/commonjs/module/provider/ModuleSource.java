/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.io.Reader;
import java.io.Serializable;
import java.net.URI;

public class ModuleSource
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Reader reader;
    private final Object securityDomain;
    private final URI uri;
    private final URI base;
    private final Object validator;

    public ModuleSource(Reader reader, Object securityDomain, URI uri, URI base, Object validator) {
        this.reader = reader;
        this.securityDomain = securityDomain;
        this.uri = uri;
        this.base = base;
        this.validator = validator;
    }

    public Reader getReader() {
        return this.reader;
    }

    public Object getSecurityDomain() {
        return this.securityDomain;
    }

    public URI getUri() {
        return this.uri;
    }

    public URI getBase() {
        return this.base;
    }

    public Object getValidator() {
        return this.validator;
    }
}

