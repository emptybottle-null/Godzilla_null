/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.OutputStream;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;

public final class DefaultMarshaller
implements PartMarshaller {
    @Override
    public boolean marshall(PackagePart part, OutputStream out) throws OpenXML4JException {
        return part.save(out);
    }
}

