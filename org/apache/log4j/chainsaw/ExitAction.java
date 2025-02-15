/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

class ExitAction
extends AbstractAction {
    private static final Logger LOG = Logger.getLogger(ExitAction.class);
    public static final ExitAction INSTANCE = new ExitAction();

    private ExitAction() {
    }

    public void actionPerformed(ActionEvent aIgnore) {
        LOG.info("shutting down");
        System.exit(0);
    }
}

