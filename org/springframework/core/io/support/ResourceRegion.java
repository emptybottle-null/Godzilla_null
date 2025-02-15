/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class ResourceRegion {
    private final Resource resource;
    private final long position;
    private final long count;

    public ResourceRegion(Resource resource, long position, long count) {
        Assert.notNull((Object)resource, "Resource must not be null");
        Assert.isTrue(position >= 0L, "'position' must be larger than or equal to 0");
        Assert.isTrue(count >= 0L, "'count' must be larger than or equal to 0");
        this.resource = resource;
        this.position = position;
        this.count = count;
    }

    public Resource getResource() {
        return this.resource;
    }

    public long getPosition() {
        return this.position;
    }

    public long getCount() {
        return this.count;
    }
}

