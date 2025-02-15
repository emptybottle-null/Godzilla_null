/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model.hyperlinks;

import com.google.common.collect.Lists;
import com.jediterm.terminal.model.hyperlinks.LinkResultItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class LinkResult {
    private final LinkResultItem myItem;
    private List<LinkResultItem> myItemList;

    public LinkResult(@NotNull LinkResultItem item) {
        if (item == null) {
            LinkResult.$$$reportNull$$$0(0);
        }
        this.myItem = item;
        this.myItemList = null;
    }

    public LinkResult(@NotNull List<LinkResultItem> itemList) {
        if (itemList == null) {
            LinkResult.$$$reportNull$$$0(1);
        }
        this.myItemList = itemList;
        this.myItem = null;
    }

    public List<LinkResultItem> getItems() {
        if (this.myItemList == null) {
            this.myItemList = Lists.newArrayList(this.myItem);
        }
        return this.myItemList;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2 = new Object[3];
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[0] = "item";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[0] = "itemList";
                break;
            }
        }
        objectArray[1] = "com/jediterm/terminal/model/hyperlinks/LinkResult";
        objectArray[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

