/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;

public interface CellRange<C extends Cell>
extends Iterable<C> {
    public int getWidth();

    public int getHeight();

    public int size();

    public String getReferenceText();

    public C getTopLeftCell();

    public C getCell(int var1, int var2);

    public C[] getFlattenedCells();

    public C[][] getCells();

    @Override
    public Iterator<C> iterator();
}

