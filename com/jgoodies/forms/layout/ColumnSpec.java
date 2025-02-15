/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.layout;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.FormSpecParser;
import com.jgoodies.forms.layout.LayoutMap;
import com.jgoodies.forms.layout.Size;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class ColumnSpec
extends FormSpec {
    public static final FormSpec.DefaultAlignment LEFT = FormSpec.LEFT_ALIGN;
    public static final FormSpec.DefaultAlignment CENTER = FormSpec.CENTER_ALIGN;
    public static final FormSpec.DefaultAlignment RIGHT = FormSpec.RIGHT_ALIGN;
    public static final FormSpec.DefaultAlignment FILL = FormSpec.FILL_ALIGN;
    public static final FormSpec.DefaultAlignment NONE = FormSpec.NO_ALIGN;
    public static final FormSpec.DefaultAlignment DEFAULT = FILL;
    private static final Map<String, ColumnSpec> CACHE = new HashMap<String, ColumnSpec>();

    public ColumnSpec(FormSpec.DefaultAlignment defaultAlignment, Size size, double resizeWeight) {
        super(defaultAlignment, size, resizeWeight);
    }

    public ColumnSpec(Size size) {
        super(DEFAULT, size, 0.0);
    }

    private ColumnSpec(String encodedDescription) {
        super(DEFAULT, encodedDescription);
    }

    public static ColumnSpec createGap(ConstantSize gapWidth) {
        return new ColumnSpec(DEFAULT, gapWidth, 0.0);
    }

    public static ColumnSpec decode(String encodedColumnSpec) {
        return ColumnSpec.decode(encodedColumnSpec, LayoutMap.getRoot());
    }

    public static ColumnSpec decode(String encodedColumnSpec, LayoutMap layoutMap) {
        Preconditions.checkNotBlank(encodedColumnSpec, "The encoded column specification must not be null, empty or whitespace.");
        Preconditions.checkNotNull(layoutMap, "The LayoutMap must not be null.");
        String trimmed = encodedColumnSpec.trim();
        String lower = trimmed.toLowerCase(Locale.ENGLISH);
        return ColumnSpec.decodeExpanded(layoutMap.expand(lower, true));
    }

    static ColumnSpec decodeExpanded(String expandedTrimmedLowerCaseSpec) {
        ColumnSpec spec = CACHE.get(expandedTrimmedLowerCaseSpec);
        if (spec == null) {
            spec = new ColumnSpec(expandedTrimmedLowerCaseSpec);
            CACHE.put(expandedTrimmedLowerCaseSpec, spec);
        }
        return spec;
    }

    public static ColumnSpec[] decodeSpecs(String encodedColumnSpecs) {
        return ColumnSpec.decodeSpecs(encodedColumnSpecs, LayoutMap.getRoot());
    }

    public static ColumnSpec[] decodeSpecs(String encodedColumnSpecs, LayoutMap layoutMap) {
        return FormSpecParser.parseColumnSpecs(encodedColumnSpecs, layoutMap);
    }

    @Override
    protected boolean isHorizontal() {
        return true;
    }
}

