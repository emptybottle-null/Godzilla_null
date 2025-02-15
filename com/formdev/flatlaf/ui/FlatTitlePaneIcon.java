/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.ScaledImageIcon;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FlatTitlePaneIcon
extends ScaledImageIcon {
    private final List<Image> images;

    public static Icon create(List<Image> images, Dimension size) {
        ArrayList<Image> allImages = new ArrayList<Image>();
        for (Image image : images) {
            if (MultiResolutionImageSupport.isMultiResolutionImage(image)) {
                allImages.addAll(MultiResolutionImageSupport.getResolutionVariants(image));
                continue;
            }
            allImages.add(image);
        }
        allImages.sort((image1, image2) -> image1.getWidth(null) - image2.getWidth(null));
        return new FlatTitlePaneIcon(allImages, size);
    }

    private FlatTitlePaneIcon(List<Image> images, Dimension size) {
        super(new ImageIcon(images.get(0)), size.width, size.height);
        this.images = images;
    }

    @Override
    protected Image getResolutionVariant(int destImageWidth, int destImageHeight) {
        for (Image image : this.images) {
            if (destImageWidth > image.getWidth(null) || destImageHeight > image.getHeight(null)) continue;
            return image;
        }
        return this.images.get(this.images.size() - 1);
    }
}

