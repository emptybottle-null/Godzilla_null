/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

public interface CreationHelper {
    public RichTextString createRichTextString(String var1);

    public DataFormat createDataFormat();

    public Hyperlink createHyperlink(HyperlinkType var1);

    public FormulaEvaluator createFormulaEvaluator();

    public ExtendedColor createExtendedColor();

    public ClientAnchor createClientAnchor();

    public AreaReference createAreaReference(String var1);

    public AreaReference createAreaReference(CellReference var1, CellReference var2);
}

