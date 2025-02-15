/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MultiResolutionImageSupport {
    public static boolean isAvailable() {
        return false;
    }

    public static boolean isMultiResolutionImage(Image image) {
        return false;
    }

    public static Image create(int baseImageIndex, Image ... resolutionVariants) {
        return resolutionVariants[baseImageIndex];
    }

    public static Image create(int baseImageIndex, Dimension[] dimensions, Function<Dimension, Image> producer) {
        return producer.apply(dimensions[baseImageIndex]);
    }

    public static Image map(Image image, Function<Image, Image> mapper) {
        return mapper.apply(image);
    }

    public static Image getResolutionVariant(Image image, int destImageWidth, int destImageHeight) {
        return image;
    }

    public static List<Image> getResolutionVariants(Image image) {
        return Collections.singletonList(image);
    }
}

