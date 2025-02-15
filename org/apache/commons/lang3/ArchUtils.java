/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.arch.Processor;

public class ArchUtils {
    private static final Map<String, Processor> ARCH_TO_PROCESSOR = new HashMap<String, Processor>();

    private static void init() {
        ArchUtils.init_X86_32Bit();
        ArchUtils.init_X86_64Bit();
        ArchUtils.init_IA64_32Bit();
        ArchUtils.init_IA64_64Bit();
        ArchUtils.init_PPC_32Bit();
        ArchUtils.init_PPC_64Bit();
    }

    private static void init_X86_32Bit() {
        Processor processor = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        ArchUtils.addProcessors(processor, "x86", "i386", "i486", "i586", "i686", "pentium");
    }

    private static void init_X86_64Bit() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.X86);
        ArchUtils.addProcessors(processor, "x86_64", "amd64", "em64t", "universal");
    }

    private static void init_IA64_32Bit() {
        Processor processor = new Processor(Processor.Arch.BIT_32, Processor.Type.IA_64);
        ArchUtils.addProcessors(processor, "ia64_32", "ia64n");
    }

    private static void init_IA64_64Bit() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.IA_64);
        ArchUtils.addProcessors(processor, "ia64", "ia64w");
    }

    private static void init_PPC_32Bit() {
        Processor processor = new Processor(Processor.Arch.BIT_32, Processor.Type.PPC);
        ArchUtils.addProcessors(processor, "ppc", "power", "powerpc", "power_pc", "power_rs");
    }

    private static void init_PPC_64Bit() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.PPC);
        ArchUtils.addProcessors(processor, "ppc64", "power64", "powerpc64", "power_pc64", "power_rs64");
    }

    private static void addProcessor(String key, Processor processor) {
        if (ARCH_TO_PROCESSOR.containsKey(key)) {
            throw new IllegalStateException("Key " + key + " already exists in processor map");
        }
        ARCH_TO_PROCESSOR.put(key, processor);
    }

    private static void addProcessors(Processor processor, String ... keys) {
        Stream.of(keys).forEach(e -> ArchUtils.addProcessor(e, processor));
    }

    public static Processor getProcessor() {
        return ArchUtils.getProcessor(SystemUtils.OS_ARCH);
    }

    public static Processor getProcessor(String value) {
        return ARCH_TO_PROCESSOR.get(value);
    }

    static {
        ArchUtils.init();
    }
}

