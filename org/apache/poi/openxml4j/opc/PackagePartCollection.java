/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;

public final class PackagePartCollection
implements Serializable {
    private static final long serialVersionUID = 2515031135957635517L;
    private final Set<String> registerPartNameStr = new HashSet<String>();
    private final TreeMap<String, PackagePart> packagePartLookup = new TreeMap(PackagePartName::compare);

    public PackagePart put(PackagePartName partName, PackagePart part) {
        String ppName = partName.getName();
        StringBuilder concatSeg = new StringBuilder();
        String delim = "(?=[/.])";
        for (String seg : ppName.split("(?=[/.])")) {
            concatSeg.append(seg);
            if (!this.registerPartNameStr.contains(concatSeg.toString())) continue;
            throw new InvalidOperationException("You can't add a part with a part name derived from another part ! [M1.11]");
        }
        this.registerPartNameStr.add(ppName);
        return this.packagePartLookup.put(ppName, part);
    }

    public PackagePart remove(PackagePartName key) {
        if (key == null) {
            return null;
        }
        String ppName = key.getName();
        PackagePart pp = this.packagePartLookup.remove(ppName);
        if (pp != null) {
            this.registerPartNameStr.remove(ppName);
        }
        return pp;
    }

    public Collection<PackagePart> sortedValues() {
        return Collections.unmodifiableCollection(this.packagePartLookup.values());
    }

    public boolean containsKey(PackagePartName partName) {
        return partName != null && this.packagePartLookup.containsKey(partName.getName());
    }

    public PackagePart get(PackagePartName partName) {
        return partName == null ? null : this.packagePartLookup.get(partName.getName());
    }

    public int size() {
        return this.packagePartLookup.size();
    }

    public int getUnusedPartIndex(String nameTemplate) throws InvalidFormatException {
        if (nameTemplate == null || !nameTemplate.contains("#")) {
            throw new InvalidFormatException("name template must not be null and contain an index char (#)");
        }
        Pattern pattern = Pattern.compile(nameTemplate.replace("#", "([0-9]+)"));
        ToIntFunction<String> indexFromName = name -> {
            Matcher m = pattern.matcher((CharSequence)name);
            return m.matches() ? Integer.parseInt(m.group(1)) : 0;
        };
        return this.packagePartLookup.keySet().stream().mapToInt(indexFromName).collect(SparseBitSet::new, SparseBitSet::set, (s1, s2) -> s1.or((SparseBitSet)s2)).nextClearBit(1);
    }
}

