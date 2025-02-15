/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import org.dom4j.ElementPath;
import org.dom4j.io.DispatchHandler;

class PruningDispatchHandler
extends DispatchHandler {
    PruningDispatchHandler() {
    }

    @Override
    public void onEnd(ElementPath elementPath) {
        super.onEnd(elementPath);
        if (this.getActiveHandlerCount() == 0) {
            elementPath.getCurrent().detach();
        }
    }
}

