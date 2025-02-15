/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.util.Internal;

@Internal
public interface WorkbookEvaluatorProvider {
    public WorkbookEvaluator _getWorkbookEvaluator();
}

