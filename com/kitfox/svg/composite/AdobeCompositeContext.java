/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.composite;

import java.awt.CompositeContext;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class AdobeCompositeContext
implements CompositeContext {
    final int compositeType;
    final float extraAlpha;
    float[] rgba_src = new float[4];
    float[] rgba_dstIn = new float[4];
    float[] rgba_dstOut = new float[4];

    public AdobeCompositeContext(int compositeType, float extraAlpha) {
        this.compositeType = compositeType;
        this.extraAlpha = extraAlpha;
        this.rgba_dstOut[3] = 1.0f;
    }

    @Override
    public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
        int width = src.getWidth();
        int height = src.getHeight();
        for (int j = 0; j < height; ++j) {
            block4: for (int i = 0; i < width; ++i) {
                src.getPixel(i, j, this.rgba_src);
                dstIn.getPixel(i, j, this.rgba_dstIn);
                if (this.rgba_src[3] == 0.0f) continue;
                float alpha = this.rgba_src[3];
                switch (this.compositeType) {
                    default: {
                        this.rgba_dstOut[0] = this.rgba_src[0] * alpha + this.rgba_dstIn[0] * (1.0f - alpha);
                        this.rgba_dstOut[1] = this.rgba_src[1] * alpha + this.rgba_dstIn[1] * (1.0f - alpha);
                        this.rgba_dstOut[2] = this.rgba_src[2] * alpha + this.rgba_dstIn[2] * (1.0f - alpha);
                        continue block4;
                    }
                    case 1: {
                        this.rgba_dstOut[0] = this.rgba_src[0] * this.rgba_dstIn[0] * alpha + this.rgba_dstIn[0] * (1.0f - alpha);
                        this.rgba_dstOut[1] = this.rgba_src[1] * this.rgba_dstIn[1] * alpha + this.rgba_dstIn[1] * (1.0f - alpha);
                        this.rgba_dstOut[2] = this.rgba_src[2] * this.rgba_dstIn[2] * alpha + this.rgba_dstIn[2] * (1.0f - alpha);
                    }
                }
            }
        }
    }

    @Override
    public void dispose() {
    }
}

