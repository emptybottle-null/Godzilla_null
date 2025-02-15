/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.layout;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.layout.BoundedSize;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.PrototypeSize;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Size;
import com.jgoodies.forms.layout.Sizes;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public abstract class FormSpec
implements Serializable {
    static final DefaultAlignment LEFT_ALIGN = new DefaultAlignment("left");
    static final DefaultAlignment RIGHT_ALIGN = new DefaultAlignment("right");
    static final DefaultAlignment TOP_ALIGN = new DefaultAlignment("top");
    static final DefaultAlignment BOTTOM_ALIGN = new DefaultAlignment("bottom");
    static final DefaultAlignment CENTER_ALIGN = new DefaultAlignment("center");
    static final DefaultAlignment FILL_ALIGN = new DefaultAlignment("fill");
    static final DefaultAlignment NO_ALIGN = new DefaultAlignment("none");
    private static final DefaultAlignment[] VALUES = new DefaultAlignment[]{LEFT_ALIGN, RIGHT_ALIGN, TOP_ALIGN, BOTTOM_ALIGN, CENTER_ALIGN, FILL_ALIGN, NO_ALIGN};
    public static final double NO_GROW = 0.0;
    public static final double DEFAULT_GROW = 1.0;
    private static final Pattern TOKEN_SEPARATOR_PATTERN = Pattern.compile(":");
    private static final Pattern BOUNDS_SEPARATOR_PATTERN = Pattern.compile("\\s*,\\s*");
    private DefaultAlignment defaultAlignment;
    private boolean defaultAlignmentExplicitlySet;
    private Size size;
    private double resizeWeight;

    protected FormSpec(DefaultAlignment defaultAlignment, Size size, double resizeWeight) {
        Preconditions.checkNotNull(size, "The size must not be null.");
        Preconditions.checkArgument(resizeWeight >= 0.0, "The resize weight must be non-negative.");
        this.defaultAlignment = defaultAlignment;
        this.size = size;
        this.resizeWeight = resizeWeight;
    }

    protected FormSpec(DefaultAlignment defaultAlignment, String encodedDescription) {
        this(defaultAlignment, Sizes.DEFAULT, 0.0);
        this.parseAndInitValues(encodedDescription.toLowerCase(Locale.ENGLISH));
    }

    public final DefaultAlignment getDefaultAlignment() {
        return this.defaultAlignment;
    }

    public final boolean getDefaultAlignmentExplictlySet() {
        return this.defaultAlignmentExplicitlySet;
    }

    public final Size getSize() {
        return this.size;
    }

    public final double getResizeWeight() {
        return this.resizeWeight;
    }

    final boolean canGrow() {
        return this.getResizeWeight() != 0.0;
    }

    abstract boolean isHorizontal();

    void setDefaultAlignment(DefaultAlignment defaultAlignment) {
        this.defaultAlignment = defaultAlignment;
        this.defaultAlignmentExplicitlySet = true;
    }

    void setSize(Size size) {
        this.size = size;
    }

    void setResizeWeight(double resizeWeight) {
        this.resizeWeight = resizeWeight;
    }

    private void parseAndInitValues(String encodedDescription) {
        Preconditions.checkNotBlank(encodedDescription, "The encoded form specification must not be null, empty or whitespace.");
        String[] token = TOKEN_SEPARATOR_PATTERN.split(encodedDescription);
        Preconditions.checkArgument(token.length > 0, "The form spec must not be empty.");
        int nextIndex = 0;
        String next = token[nextIndex++];
        DefaultAlignment alignment = DefaultAlignment.valueOf(next, this.isHorizontal());
        if (alignment != null) {
            this.setDefaultAlignment(alignment);
            Preconditions.checkArgument(token.length > 1, "The form spec must provide a size.");
            next = token[nextIndex++];
        }
        this.setSize(this.parseSize(next));
        if (nextIndex < token.length) {
            this.setResizeWeight(FormSpec.parseResizeWeight(token[nextIndex]));
        }
    }

    private Size parseSize(String token) {
        if (token.startsWith("[") && token.endsWith("]")) {
            return this.parseBoundedSize(token);
        }
        if (token.startsWith("max(") && token.endsWith(")")) {
            return this.parseOldBoundedSize(token, false);
        }
        if (token.startsWith("min(") && token.endsWith(")")) {
            return this.parseOldBoundedSize(token, true);
        }
        return this.parseAtomicSize(token);
    }

    private Size parseBoundedSize(String token) {
        String content = token.substring(1, token.length() - 1);
        String[] subtoken = BOUNDS_SEPARATOR_PATTERN.split(content);
        Size basis = null;
        Size lower = null;
        Size upper = null;
        if (subtoken.length == 2) {
            Size size1 = this.parseAtomicSize(subtoken[0]);
            Size size2 = this.parseAtomicSize(subtoken[1]);
            if (FormSpec.isConstant(size1)) {
                if (FormSpec.isConstant(size2)) {
                    lower = size1;
                    basis = size2;
                    upper = size2;
                } else {
                    lower = size1;
                    basis = size2;
                }
            } else {
                basis = size1;
                upper = size2;
            }
        } else if (subtoken.length == 3) {
            lower = this.parseAtomicSize(subtoken[0]);
            basis = this.parseAtomicSize(subtoken[1]);
            upper = this.parseAtomicSize(subtoken[2]);
        }
        if ((lower == null || FormSpec.isConstant(lower)) && (upper == null || FormSpec.isConstant(upper))) {
            return new BoundedSize(basis, lower, upper);
        }
        throw new IllegalArgumentException("Illegal bounded size '" + token + "'. Must be one of:" + "\n[<constant size>,<logical size>]                 // lower bound" + "\n[<logical size>,<constant size>]                 // upper bound" + "\n[<constant size>,<logical size>,<constant size>] // lower and upper bound." + "\nExamples:" + "\n[50dlu,pref]                                     // lower bound" + "\n[pref,200dlu]                                    // upper bound" + "\n[50dlu,pref,200dlu]                              // lower and upper bound.");
    }

    private Size parseOldBoundedSize(String token, boolean setMax) {
        int semicolonIndex = token.indexOf(59);
        String sizeToken1 = token.substring(4, semicolonIndex);
        String sizeToken2 = token.substring(semicolonIndex + 1, token.length() - 1);
        Size size1 = this.parseAtomicSize(sizeToken1);
        Size size2 = this.parseAtomicSize(sizeToken2);
        if (FormSpec.isConstant(size1)) {
            if (size2 instanceof Sizes.ComponentSize) {
                return new BoundedSize(size2, setMax ? null : size1, setMax ? size1 : null);
            }
            throw new IllegalArgumentException("Bounded sizes must not be both constants.");
        }
        if (FormSpec.isConstant(size2)) {
            return new BoundedSize(size1, setMax ? null : size2, setMax ? size2 : null);
        }
        throw new IllegalArgumentException("Bounded sizes must not be both logical.");
    }

    private Size parseAtomicSize(String token) {
        String trimmedToken = token.trim();
        if (trimmedToken.startsWith("'") && trimmedToken.endsWith("'")) {
            int length = trimmedToken.length();
            if (length < 2) {
                throw new IllegalArgumentException("Missing closing \"'\" for prototype.");
            }
            return new PrototypeSize(trimmedToken.substring(1, length - 1));
        }
        Sizes.ComponentSize componentSize = Sizes.ComponentSize.valueOf(trimmedToken);
        if (componentSize != null) {
            return componentSize;
        }
        return ConstantSize.valueOf(trimmedToken, this.isHorizontal());
    }

    private static double parseResizeWeight(String token) {
        if (token.equals("g") || token.equals("grow")) {
            return 1.0;
        }
        if (token.equals("n") || token.equals("nogrow") || token.equals("none")) {
            return 0.0;
        }
        if ((token.startsWith("grow(") || token.startsWith("g(")) && token.endsWith(")")) {
            int leftParen = token.indexOf(40);
            int rightParen = token.indexOf(41);
            String substring = token.substring(leftParen + 1, rightParen);
            return Double.parseDouble(substring);
        }
        throw new IllegalArgumentException("The resize argument '" + token + "' is invalid. " + " Must be one of: grow, g, none, n, grow(<double>), g(<double>)");
    }

    private static boolean isConstant(Size aSize) {
        return aSize instanceof ConstantSize || aSize instanceof PrototypeSize;
    }

    public final String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.defaultAlignment);
        buffer.append(":");
        buffer.append(this.size.toString());
        buffer.append(':');
        if (this.resizeWeight == 0.0) {
            buffer.append("noGrow");
        } else if (this.resizeWeight == 1.0) {
            buffer.append("grow");
        } else {
            buffer.append("grow(");
            buffer.append(this.resizeWeight);
            buffer.append(')');
        }
        return buffer.toString();
    }

    public final String toShortString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.defaultAlignment.abbreviation());
        buffer.append(":");
        buffer.append(this.size.toString());
        buffer.append(':');
        if (this.resizeWeight == 0.0) {
            buffer.append("n");
        } else if (this.resizeWeight == 1.0) {
            buffer.append("g");
        } else {
            buffer.append("g(");
            buffer.append(this.resizeWeight);
            buffer.append(')');
        }
        return buffer.toString();
    }

    public final String encode() {
        DefaultAlignment alignmentDefault;
        StringBuffer buffer = new StringBuffer();
        DefaultAlignment defaultAlignment = alignmentDefault = this.isHorizontal() ? ColumnSpec.DEFAULT : RowSpec.DEFAULT;
        if (!alignmentDefault.equals(this.defaultAlignment)) {
            buffer.append(this.defaultAlignment.abbreviation());
            buffer.append(":");
        }
        buffer.append(this.size.encode());
        if (this.resizeWeight != 0.0) {
            if (this.resizeWeight == 1.0) {
                buffer.append(':');
                buffer.append("g");
            } else {
                buffer.append(':');
                buffer.append("g(");
                buffer.append(this.resizeWeight);
                buffer.append(')');
            }
        }
        return buffer.toString();
    }

    final int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
        return this.size.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure);
    }

    public static final class DefaultAlignment
    implements Serializable {
        private final transient String name;
        private static int nextOrdinal = 0;
        private final int ordinal = nextOrdinal++;

        private DefaultAlignment(String name) {
            this.name = name;
        }

        private static DefaultAlignment valueOf(String str, boolean isHorizontal) {
            if (str.equals("f") || str.equals("fill")) {
                return FILL_ALIGN;
            }
            if (str.equals("c") || str.equals("center")) {
                return CENTER_ALIGN;
            }
            if (isHorizontal) {
                if (str.equals("r") || str.equals("right")) {
                    return RIGHT_ALIGN;
                }
                if (str.equals("l") || str.equals("left")) {
                    return LEFT_ALIGN;
                }
                if (str.equals("none")) {
                    return NO_ALIGN;
                }
                return null;
            }
            if (str.equals("t") || str.equals("top")) {
                return TOP_ALIGN;
            }
            if (str.equals("b") || str.equals("bottom")) {
                return BOTTOM_ALIGN;
            }
            return null;
        }

        public String toString() {
            return this.name;
        }

        public char abbreviation() {
            return this.name.charAt(0);
        }

        private Object readResolve() {
            return VALUES[this.ordinal];
        }
    }
}

