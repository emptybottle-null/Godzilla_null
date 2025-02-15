/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import java.io.IOException;

public interface CharStream {
    public char readChar() throws IOException;

    public int getBeginColumn();

    public int getBeginLine();

    public int getEndColumn();

    public int getEndLine();

    public void backup(int var1);

    public char beginToken() throws IOException;

    public String getImage();

    public char[] getSuffix(int var1);

    public void done();

    public int getTabSize();

    public void setTabSize(int var1);

    public boolean isTrackLineColumn();

    public void setTrackLineColumn(boolean var1);
}

