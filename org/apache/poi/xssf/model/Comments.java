/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.util.Iterator;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFComment;

public interface Comments {
    public int getNumberOfComments();

    public int getNumberOfAuthors();

    public String getAuthor(long var1);

    public int findAuthor(String var1);

    public XSSFComment findCellComment(CellAddress var1);

    public boolean removeComment(CellAddress var1);

    public Iterator<CellAddress> getCellAddresses();
}

