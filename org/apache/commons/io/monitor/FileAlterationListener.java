/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.monitor;

import java.io.File;
import org.apache.commons.io.monitor.FileAlterationObserver;

public interface FileAlterationListener {
    public void onStart(FileAlterationObserver var1);

    public void onDirectoryCreate(File var1);

    public void onDirectoryChange(File var1);

    public void onDirectoryDelete(File var1);

    public void onFileCreate(File var1);

    public void onFileChange(File var1);

    public void onFileDelete(File var1);

    public void onStop(FileAlterationObserver var1);
}

