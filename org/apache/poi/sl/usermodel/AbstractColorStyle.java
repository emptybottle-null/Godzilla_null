/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.util.Objects;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.util.Internal;

@Internal
public abstract class AbstractColorStyle
implements ColorStyle {
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ColorStyle)) {
            return false;
        }
        return Objects.equals(DrawPaint.applyColorTransform(this), DrawPaint.applyColorTransform((ColorStyle)o));
    }

    public int hashCode() {
        return DrawPaint.applyColorTransform(this).hashCode();
    }
}

