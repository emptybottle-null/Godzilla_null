/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.ResourceDirectory;
import com.kichik.pecoff4j.ResourceEntry;
import java.util.ArrayList;
import java.util.List;

public class ResourceHelper {
    public static ResourceEntry[] findResources(ResourceDirectory rd, int type) {
        return ResourceHelper.findResources(rd, type, -1, -1);
    }

    public static ResourceEntry[] findResources(ResourceDirectory rd, int type, int name) {
        return ResourceHelper.findResources(rd, type, name, -1);
    }

    public static ResourceEntry[] findResources(ResourceDirectory rd, int type, int name, int lang) {
        ArrayList<ResourceEntry> entries = new ArrayList<ResourceEntry>();
        if (rd != null) {
            ResourceHelper.findResources(rd, type, name, lang, entries);
        }
        return entries.toArray(new ResourceEntry[0]);
    }

    private static void findResources(ResourceDirectory parent, int type, int name, int language, List<ResourceEntry> entries) {
        int id = type;
        if (id == -1) {
            id = name;
        }
        if (id == -1) {
            id = language;
        }
        for (int i = 0; i < parent.size(); ++i) {
            ResourceEntry e = parent.get(i);
            if (id != -1 && id != e.getId()) continue;
            if (e.getData() != null) {
                entries.add(e);
                continue;
            }
            ResourceDirectory rd = e.getDirectory();
            if (rd == null) continue;
            if (type != -1) {
                type = -1;
            } else if (name != -1) {
                name = -1;
            } else {
                language = -1;
            }
            ResourceHelper.findResources(rd, type, name, language, entries);
        }
    }

    public static void addResource(int type, int name, int lang, byte[] data) {
    }
}

