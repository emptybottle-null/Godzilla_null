/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SchemaDependencies {
    private Map _dependencies = new HashMap();
    private Map _contributions = new HashMap();

    void registerDependency(String source, String target) {
        HashSet<String> depSet = (HashSet<String>)this._dependencies.get(target);
        if (depSet == null) {
            depSet = new HashSet<String>();
            this._dependencies.put(target, depSet);
        }
        depSet.add(source);
    }

    Set computeTransitiveClosure(List modifiedNamespaces) {
        ArrayList<String> nsList = new ArrayList<String>(modifiedNamespaces);
        HashSet<String> result = new HashSet<String>(modifiedNamespaces);
        for (int i = 0; i < nsList.size(); ++i) {
            Set deps = (Set)this._dependencies.get(nsList.get(i));
            if (deps == null) continue;
            for (String ns : deps) {
                if (result.contains(ns)) continue;
                nsList.add(ns);
                result.add(ns);
            }
        }
        return result;
    }

    SchemaDependencies() {
    }

    SchemaDependencies(SchemaDependencies base, Set updatedNs) {
        for (String target : base._dependencies.keySet()) {
            if (updatedNs.contains(target)) continue;
            HashSet<String> depSet = new HashSet<String>();
            this._dependencies.put(target, depSet);
            Set baseDepSet = (Set)base._dependencies.get(target);
            for (String source : baseDepSet) {
                if (updatedNs.contains(source)) continue;
                depSet.add(source);
            }
        }
        for (String ns : base._contributions.keySet()) {
            if (updatedNs.contains(ns)) continue;
            ArrayList fileList = new ArrayList();
            this._contributions.put(ns, fileList);
            List baseFileList = (List)base._contributions.get(ns);
            Iterator it2 = baseFileList.iterator();
            while (it2.hasNext()) {
                fileList.add(it2.next());
            }
        }
    }

    void registerContribution(String ns, String fileURL) {
        ArrayList<String> fileList = (ArrayList<String>)this._contributions.get(ns);
        if (fileList == null) {
            fileList = new ArrayList<String>();
            this._contributions.put(ns, fileList);
        }
        fileList.add(fileURL);
    }

    boolean isFileRepresented(String fileURL) {
        for (List fileList : this._contributions.values()) {
            if (!fileList.contains(fileURL)) continue;
            return true;
        }
        return false;
    }

    List getFilesTouched(Set updatedNs) {
        ArrayList result = new ArrayList();
        Iterator it = updatedNs.iterator();
        while (it.hasNext()) {
            result.addAll((List)this._contributions.get(it.next()));
        }
        return result;
    }

    List getNamespacesTouched(Set modifiedFiles) {
        ArrayList<String> result = new ArrayList<String>();
        for (String ns : this._contributions.keySet()) {
            List files = (List)this._contributions.get(ns);
            for (int i = 0; i < files.size(); ++i) {
                if (!modifiedFiles.contains(files.get(i))) continue;
                result.add(ns);
            }
        }
        return result;
    }
}

