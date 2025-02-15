/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.v8dtoa;

import org.mozilla.javascript.v8dtoa.DiyFp;

public class CachedPowers {
    static final double kD_1_LOG2_10 = 0.30102999566398114;
    static final int CACHED_POWERS_SPACING = 8;
    static final CachedPower[] CACHED_POWERS = new CachedPower[]{new CachedPower(-1865951482774665761L, -1087, -308), new CachedPower(-6093090917745768758L, -1060, -300), new CachedPower(-38366372719436721L, -1034, -292), new CachedPower(-4731433901725329908L, -1007, -284), new CachedPower(-8228041688891786180L, -980, -276), new CachedPower(-3219690930897053053L, -954, -268), new CachedPower(-7101705404292871755L, -927, -260), new CachedPower(-1541319077368263733L, -901, -252), new CachedPower(-5851220927660403859L, -874, -244), new CachedPower(-9062348037703676329L, -847, -236), new CachedPower(-4462904269766699465L, -821, -228), new CachedPower(-8027971522334779313L, -794, -220), new CachedPower(-2921563150702462265L, -768, -212), new CachedPower(-6879582898840692748L, -741, -204), new CachedPower(-1210330751515841307L, -715, -196), new CachedPower(-5604615407819967858L, -688, -188), new CachedPower(-8878612607581929669L, -661, -180), new CachedPower(-4189117143640191558L, -635, -172), new CachedPower(-7823984217374209642L, -608, -164), new CachedPower(-2617598379430861436L, -582, -156), new CachedPower(-6653111496142234890L, -555, -148), new CachedPower(-872862063775190746L, -529, -140), new CachedPower(-5353181642124984136L, -502, -132), new CachedPower(-8691279853972075893L, -475, -124), new CachedPower(-3909969587797413805L, -449, -116), new CachedPower(-7616003081050118571L, -422, -108), new CachedPower(-2307682335666372931L, -396, -100), new CachedPower(-6422206049907525489L, -369, -92), new CachedPower(-528786136287117932L, -343, -84), new CachedPower(-5096825099203863601L, -316, -76), new CachedPower(-8500279345513818773L, -289, -68), new CachedPower(-3625356651333078602L, -263, -60), new CachedPower(-7403949918844649556L, -236, -52), new CachedPower(-1991698500497491194L, -210, -44), new CachedPower(-6186779746782440749L, -183, -36), new CachedPower(-177973607073265138L, -157, -28), new CachedPower(-4835449396872013077L, -130, -20), new CachedPower(-8305539271883716404L, -103, -12), new CachedPower(-3335171328526686932L, -77, -4), new CachedPower(-7187745005283311616L, -50, 4), new CachedPower(-1669528073709551616L, -24, 12), new CachedPower(-5946744073709551616L, 3, 20), new CachedPower(-9133518327554766460L, 30, 28), new CachedPower(-4568956265895094861L, 56, 36), new CachedPower(-8106986416796705680L, 83, 44), new CachedPower(-3039304518611664792L, 109, 52), new CachedPower(-6967307053960650171L, 136, 60), new CachedPower(-1341049929119499481L, 162, 68), new CachedPower(-5702008784649933400L, 189, 76), new CachedPower(-8951176327949752869L, 216, 84), new CachedPower(-4297245513042813542L, 242, 92), new CachedPower(-7904546130479028392L, 269, 100), new CachedPower(-2737644984756826646L, 295, 108), new CachedPower(-6742553186979055798L, 322, 116), new CachedPower(-1006140569036166267L, 348, 124), new CachedPower(-5452481866653427593L, 375, 132), new CachedPower(-8765264286586255934L, 402, 140), new CachedPower(-4020214983419339459L, 428, 148), new CachedPower(-7698142301602209613L, 455, 156), new CachedPower(-2430079312244744221L, 481, 164), new CachedPower(-6513398903789220827L, 508, 172), new CachedPower(-664674077828931748L, 534, 180), new CachedPower(-5198069505264599346L, 561, 188), new CachedPower(-8575712306248138270L, 588, 196), new CachedPower(-3737760522056206171L, 614, 204), new CachedPower(-7487697328667536417L, 641, 212), new CachedPower(-2116491865831296966L, 667, 220), new CachedPower(-6279758049420528746L, 694, 228), new CachedPower(-316522074587315140L, 720, 236), new CachedPower(-4938676049251384304L, 747, 244), new CachedPower(-8382449121214030822L, 774, 252), new CachedPower(-3449775934753242068L, 800, 260), new CachedPower(-7273132090830278359L, 827, 268), new CachedPower(-1796764746270372707L, 853, 276), new CachedPower(-6041542782089432023L, 880, 284), new CachedPower(-9204148869281624187L, 907, 292), new CachedPower(-4674203974643163859L, 933, 300), new CachedPower(-8185402070463610993L, 960, 308), new CachedPower(-3156152948152813503L, 986, 316), new CachedPower(-7054365918152680535L, 1013, 324), new CachedPower(-1470777745987373095L, 1039, 332), new CachedPower(-5798663540173640085L, 1066, 340)};
    static final int GRISU_CACHE_MAX_DISTANCE = 27;
    static final int GRISU_CACHE_OFFSET = 308;

    static int getCachedPower(int e, int alpha, int gamma, DiyFp c_mk) {
        int kQ = 64;
        double k = Math.ceil((double)(alpha - e + kQ - 1) * 0.30102999566398114);
        int index = (308 + (int)k - 1) / 8 + 1;
        CachedPower cachedPower = CACHED_POWERS[index];
        c_mk.setF(cachedPower.significand);
        c_mk.setE(cachedPower.binaryExponent);
        assert (alpha <= c_mk.e() + e && c_mk.e() + e <= gamma);
        return cachedPower.decimalExponent;
    }

    static class CachedPower {
        long significand;
        short binaryExponent;
        short decimalExponent;

        CachedPower(long significand, short binaryExponent, short decimalExponent) {
            this.significand = significand;
            this.binaryExponent = binaryExponent;
            this.decimalExponent = decimalExponent;
        }
    }
}

