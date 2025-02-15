/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model.hyperlinks;

import com.jediterm.terminal.ui.TerminalAction;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LinkInfo {
    private final Runnable myNavigateCallback;
    private final PopupMenuGroupProvider myPopupMenuGroupProvider;
    private final HoverConsumer myHoverConsumer;

    public LinkInfo(@NotNull Runnable navigateCallback) {
        if (navigateCallback == null) {
            LinkInfo.$$$reportNull$$$0(0);
        }
        this(navigateCallback, null, null);
    }

    private LinkInfo(@NotNull Runnable navigateCallback, @Nullable PopupMenuGroupProvider popupMenuGroupProvider, @Nullable HoverConsumer hoverConsumer) {
        if (navigateCallback == null) {
            LinkInfo.$$$reportNull$$$0(1);
        }
        this.myNavigateCallback = navigateCallback;
        this.myPopupMenuGroupProvider = popupMenuGroupProvider;
        this.myHoverConsumer = hoverConsumer;
    }

    public void navigate() {
        this.myNavigateCallback.run();
    }

    @Nullable
    public PopupMenuGroupProvider getPopupMenuGroupProvider() {
        return this.myPopupMenuGroupProvider;
    }

    @Nullable
    public HoverConsumer getHoverConsumer() {
        return this.myHoverConsumer;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "navigateCallback", "com/jediterm/terminal/model/hyperlinks/LinkInfo", "<init>"));
    }

    public static final class Builder {
        private Runnable myNavigateCallback;
        private PopupMenuGroupProvider myPopupMenuGroupProvider;
        private HoverConsumer myHoverConsumer;

        @NotNull
        public Builder setNavigateCallback(@NotNull Runnable navigateCallback) {
            if (navigateCallback == null) {
                Builder.$$$reportNull$$$0(0);
            }
            this.myNavigateCallback = navigateCallback;
            Builder builder = this;
            if (builder == null) {
                Builder.$$$reportNull$$$0(1);
            }
            return builder;
        }

        @NotNull
        public Builder setPopupMenuGroupProvider(@Nullable PopupMenuGroupProvider popupMenuGroupProvider) {
            this.myPopupMenuGroupProvider = popupMenuGroupProvider;
            Builder builder = this;
            if (builder == null) {
                Builder.$$$reportNull$$$0(2);
            }
            return builder;
        }

        @NotNull
        public Builder setHoverConsumer(@Nullable HoverConsumer hoverConsumer) {
            this.myHoverConsumer = hoverConsumer;
            Builder builder = this;
            if (builder == null) {
                Builder.$$$reportNull$$$0(3);
            }
            return builder;
        }

        @NotNull
        public LinkInfo build() {
            return new LinkInfo(this.myNavigateCallback, this.myPopupMenuGroupProvider, this.myHoverConsumer);
        }

        private static /* synthetic */ void $$$reportNull$$$0(int n) {
            RuntimeException runtimeException;
            Object[] objectArray;
            Object[] objectArray2;
            int n2;
            String string;
            switch (n) {
                default: {
                    string = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    string = "@NotNull method %s.%s must not return null";
                    break;
                }
            }
            switch (n) {
                default: {
                    n2 = 3;
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    n2 = 2;
                    break;
                }
            }
            Object[] objectArray3 = new Object[n2];
            switch (n) {
                default: {
                    objectArray2 = objectArray3;
                    objectArray3[0] = "navigateCallback";
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    objectArray2 = objectArray3;
                    objectArray3[0] = "com/jediterm/terminal/model/hyperlinks/LinkInfo$Builder";
                    break;
                }
            }
            switch (n) {
                default: {
                    objectArray = objectArray2;
                    objectArray2[1] = "com/jediterm/terminal/model/hyperlinks/LinkInfo$Builder";
                    break;
                }
                case 1: {
                    objectArray = objectArray2;
                    objectArray2[1] = "setNavigateCallback";
                    break;
                }
                case 2: {
                    objectArray = objectArray2;
                    objectArray2[1] = "setPopupMenuGroupProvider";
                    break;
                }
                case 3: {
                    objectArray = objectArray2;
                    objectArray2[1] = "setHoverConsumer";
                    break;
                }
            }
            switch (n) {
                default: {
                    objectArray = objectArray;
                    objectArray[2] = "setNavigateCallback";
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    break;
                }
            }
            String string2 = String.format(string, objectArray);
            switch (n) {
                default: {
                    runtimeException = new IllegalArgumentException(string2);
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    runtimeException = new IllegalStateException(string2);
                    break;
                }
            }
            throw runtimeException;
        }
    }

    public static interface HoverConsumer {
        public void onMouseEntered(@NotNull JComponent var1, @NotNull Rectangle var2);

        public void onMouseExited();
    }

    public static interface PopupMenuGroupProvider {
        @NotNull
        public List<TerminalAction> getPopupMenuGroup(@NotNull MouseEvent var1);
    }
}

