/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.tools.ant.BuildException
 *  org.apache.tools.ant.DirectoryScanner
 *  org.apache.tools.ant.Project
 *  org.apache.tools.ant.Task
 *  org.apache.tools.ant.types.FileSet
 */
package org.springframework.cglib.transform;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

public abstract class AbstractProcessTask
extends Task {
    private Vector filesets = new Vector();

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    protected Collection getFiles() {
        HashMap<String, File> fileMap = new HashMap<String, File>();
        Project p = this.getProject();
        for (int i = 0; i < this.filesets.size(); ++i) {
            FileSet fs = (FileSet)this.filesets.elementAt(i);
            DirectoryScanner ds = fs.getDirectoryScanner(p);
            String[] srcFiles = ds.getIncludedFiles();
            File dir = fs.getDir(p);
            for (int j = 0; j < srcFiles.length; ++j) {
                File src = new File(dir, srcFiles[j]);
                fileMap.put(src.getAbsolutePath(), src);
            }
        }
        return fileMap.values();
    }

    public void execute() throws BuildException {
        this.beforeExecute();
        Iterator it = this.getFiles().iterator();
        while (it.hasNext()) {
            try {
                this.processFile((File)it.next());
            } catch (Exception e) {
                throw new BuildException((Throwable)e);
            }
        }
    }

    protected void beforeExecute() throws BuildException {
    }

    protected abstract void processFile(File var1) throws Exception;
}

