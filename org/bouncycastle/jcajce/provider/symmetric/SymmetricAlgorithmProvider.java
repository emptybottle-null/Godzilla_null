/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

abstract class SymmetricAlgorithmProvider
extends AlgorithmProvider {
    SymmetricAlgorithmProvider() {
    }

    protected void addCMacAlgorithm(ConfigurableProvider configurableProvider, String string, String string2, String string3) {
        configurableProvider.addAlgorithm("Mac." + string + "-CMAC", string2);
        configurableProvider.addAlgorithm("Alg.Alias.Mac." + string + "CMAC", string + "-CMAC");
        configurableProvider.addAlgorithm("KeyGenerator." + string + "-CMAC", string3);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + string + "CMAC", string + "-CMAC");
    }

    protected void addGMacAlgorithm(ConfigurableProvider configurableProvider, String string, String string2, String string3) {
        configurableProvider.addAlgorithm("Mac." + string + "-GMAC", string2);
        configurableProvider.addAlgorithm("Alg.Alias.Mac." + string + "GMAC", string + "-GMAC");
        configurableProvider.addAlgorithm("KeyGenerator." + string + "-GMAC", string3);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + string + "GMAC", string + "-GMAC");
    }

    protected void addPoly1305Algorithm(ConfigurableProvider configurableProvider, String string, String string2, String string3) {
        configurableProvider.addAlgorithm("Mac.POLY1305-" + string, string2);
        configurableProvider.addAlgorithm("Alg.Alias.Mac.POLY1305" + string, "POLY1305-" + string);
        configurableProvider.addAlgorithm("KeyGenerator.POLY1305-" + string, string3);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.POLY1305" + string, "POLY1305-" + string);
    }
}

