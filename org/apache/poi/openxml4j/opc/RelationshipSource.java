/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.TargetMode;

public interface RelationshipSource {
    public PackageRelationship addRelationship(PackagePartName var1, TargetMode var2, String var3);

    public PackageRelationship addRelationship(PackagePartName var1, TargetMode var2, String var3, String var4);

    public PackageRelationship addExternalRelationship(String var1, String var2);

    public PackageRelationship addExternalRelationship(String var1, String var2, String var3);

    public void clearRelationships();

    public void removeRelationship(String var1);

    public PackageRelationshipCollection getRelationships() throws InvalidFormatException, OpenXML4JException;

    public PackageRelationship getRelationship(String var1);

    public PackageRelationshipCollection getRelationshipsByType(String var1) throws InvalidFormatException, IllegalArgumentException, OpenXML4JException;

    public boolean hasRelationships();

    public boolean isRelationshipExists(PackageRelationship var1);
}

