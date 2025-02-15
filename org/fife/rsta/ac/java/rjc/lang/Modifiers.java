/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fife.rsta.ac.java.rjc.lang.Annotation;

public class Modifiers {
    public static final Integer ABSTRACT = 1024;
    public static final Integer FINAL = 16;
    public static final Integer INTERFACE = 512;
    public static final Integer NATIVE = 256;
    public static final Integer PRIVATE = 2;
    public static final Integer PROTECTED = 4;
    public static final Integer PUBLIC = 1;
    public static final Integer STATIC = 8;
    public static final Integer STRICTFP = 2048;
    public static final Integer SYNCHRONIZED = 32;
    public static final Integer TRANSIENT = 128;
    public static final Integer VOLATILE = 64;
    private List<Integer> modifiers = new ArrayList<Integer>(1);
    private List<Annotation> annotations = new ArrayList<Annotation>(0);
    private static final Map<Integer, String> MODIFIER_TEXT = new HashMap<Integer, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put(ABSTRACT, "abstract");
            this.put(FINAL, "final");
            this.put(INTERFACE, "interface");
            this.put(NATIVE, "native");
            this.put(PRIVATE, "private");
            this.put(PROTECTED, "protected");
            this.put(PUBLIC, "public");
            this.put(STATIC, "static");
            this.put(STRICTFP, "strictfp");
            this.put(SYNCHRONIZED, "synchronized");
            this.put(TRANSIENT, "transient");
            this.put(VOLATILE, "volatile");
        }
    };

    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
    }

    public boolean addModifier(int tokenType) {
        Integer key;
        switch (tokenType) {
            case 65537: {
                key = ABSTRACT;
                break;
            }
            case 65554: {
                key = FINAL;
                break;
            }
            case 65564: {
                key = INTERFACE;
                break;
            }
            case 65566: {
                key = NATIVE;
                break;
            }
            case 65569: {
                key = PRIVATE;
                break;
            }
            case 65570: {
                key = PROTECTED;
                break;
            }
            case 65571: {
                key = PUBLIC;
                break;
            }
            case 65574: {
                key = STATIC;
                break;
            }
            case 65575: {
                key = STRICTFP;
                break;
            }
            case 65578: {
                key = SYNCHRONIZED;
                break;
            }
            case 65582: {
                key = TRANSIENT;
                break;
            }
            case 65585: {
                key = VOLATILE;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid tokenType: " + tokenType);
            }
        }
        int pos = Collections.binarySearch(this.modifiers, key);
        if (pos < 0) {
            int insertionPoint = -(pos + 1);
            this.modifiers.add(insertionPoint, key);
        }
        return pos < 0;
    }

    private boolean containsModifier(Integer modifierKey) {
        return Collections.binarySearch(this.modifiers, modifierKey) >= 0;
    }

    public boolean isAbstract() {
        return this.containsModifier(ABSTRACT);
    }

    public boolean isFinal() {
        return this.containsModifier(FINAL);
    }

    public boolean isPrivate() {
        return this.containsModifier(PRIVATE);
    }

    public boolean isProtected() {
        return this.containsModifier(PROTECTED);
    }

    public boolean isPublic() {
        return this.containsModifier(PUBLIC);
    }

    public boolean isStatic() {
        return this.containsModifier(STATIC);
    }

    public String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < this.annotations.size(); ++i) {
            sb.append(this.annotations.get(i).toString());
            if (i >= this.annotations.size() - 1 && this.modifiers.size() <= 0) continue;
            sb.append(' ');
        }
        for (i = 0; i < this.modifiers.size(); ++i) {
            Integer modifier = this.modifiers.get(i);
            sb.append(MODIFIER_TEXT.get(modifier));
            if (i >= this.modifiers.size() - 1) continue;
            sb.append(' ');
        }
        return sb.toString();
    }
}

