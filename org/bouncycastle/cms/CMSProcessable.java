/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.cms.CMSException;

public interface CMSProcessable {
    public void write(OutputStream var1) throws IOException, CMSException;

    public Object getContent();
}

