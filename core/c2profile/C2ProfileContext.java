/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile;

import core.c2profile.C2Profile;
import core.c2profile.RequestChannelType;
import core.c2profile.ResponseChannelType;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

public class C2ProfileContext {
    public RequestChannelType requestChannelType;
    public ResponseChannelType responseChannelType;
    public C2Profile c2Profile;

    public static void main(String[] args) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setProcessComments(true);
        dumperOptions.setPrettyFlow(true);
        Yaml yaml = new Yaml(dumperOptions);
        yaml.setBeanAccess(BeanAccess.FIELD);
        System.out.println(yaml.dumpAsMap(new C2ProfileContext()));
    }
}

