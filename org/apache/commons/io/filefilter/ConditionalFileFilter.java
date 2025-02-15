/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.util.List;
import org.apache.commons.io.filefilter.IOFileFilter;

public interface ConditionalFileFilter {
    public void addFileFilter(IOFileFilter var1);

    public List<IOFileFilter> getFileFilters();

    public boolean removeFileFilter(IOFileFilter var1);

    public void setFileFilters(List<IOFileFilter> var1);
}

