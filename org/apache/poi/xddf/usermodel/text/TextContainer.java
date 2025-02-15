/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.Optional;
import java.util.function.Function;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public interface TextContainer {
    public <R> Optional<R> findDefinedParagraphProperty(Function<CTTextParagraphProperties, Boolean> var1, Function<CTTextParagraphProperties, R> var2);

    public <R> Optional<R> findDefinedRunProperty(Function<CTTextCharacterProperties, Boolean> var1, Function<CTTextCharacterProperties, R> var2);
}

