/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.AbstractJFlexTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

public class FortranTokenMaker
extends AbstractJFlexTokenMaker {
    public static final int YYEOF = -1;
    public static final int STRING = 1;
    public static final int YYINITIAL = 0;
    public static final int CHAR = 2;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0002\u0001\u0001\u0001\u0000\u0001\u0002\u0013\u0000\u0001\u0002\u0001\u0005\u0001\b\u0001\u0000\u0001\u0006\u0001\u0000\u0001\f\u0001\t\u0002\u0000\u0001\u0003\u0003\u0000\u0001\u000e\u0001\r\u0001%\u0001(\u0001)\u0007\u0006\u0002\u0000\u0001\n\u0001\u000b\u0001\n\u0002\u0000\u0001\u0015\u0001\u001f\u0001\u0007\u0001\u0004\u0001\u0012\u0001\u0019\u0001\u0011\u0001!\u0001\u001b\u0001&\u0001\"\u0001\u000f\u0001\u001e\u0001\u0014\u0001\u0016\u0001\u001d\u0001\u0013\u0001\u0017\u0001\u001a\u0001\u0010\u0001\u0018\u0001\u0006\u0001$\u0001 \u0001#\u0001'\u0004\u0000\u0001\u0006\u0001\u0000\u0001\u0015\u0001\u001f\u0001\u001c\u0001\u0004\u0001\u0012\u0001\u0019\u0001\u0011\u0001!\u0001\u001b\u0001&\u0001\"\u0001\u000f\u0001\u001e\u0001\u0014\u0001\u0016\u0001\u001d\u0001\u0013\u0001\u0017\u0001\u001a\u0001\u0010\u0001\u0018\u0001\u0006\u0001$\u0001 \u0001#\u0001'\uff85\u0000";
    private static final char[] ZZ_CMAP = FortranTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0002\u0001\u0001\u0001\u0000\u0001\u0002\u0013\u0000\u0001\u0002\u0001\u0005\u0001\b\u0001\u0000\u0001\u0006\u0001\u0000\u0001\f\u0001\t\u0002\u0000\u0001\u0003\u0003\u0000\u0001\u000e\u0001\r\u0001%\u0001(\u0001)\u0007\u0006\u0002\u0000\u0001\n\u0001\u000b\u0001\n\u0002\u0000\u0001\u0015\u0001\u001f\u0001\u0007\u0001\u0004\u0001\u0012\u0001\u0019\u0001\u0011\u0001!\u0001\u001b\u0001&\u0001\"\u0001\u000f\u0001\u001e\u0001\u0014\u0001\u0016\u0001\u001d\u0001\u0013\u0001\u0017\u0001\u001a\u0001\u0010\u0001\u0018\u0001\u0006\u0001$\u0001 \u0001#\u0001'\u0004\u0000\u0001\u0006\u0001\u0000\u0001\u0015\u0001\u001f\u0001\u001c\u0001\u0004\u0001\u0012\u0001\u0019\u0001\u0011\u0001!\u0001\u001b\u0001&\u0001\"\u0001\u000f\u0001\u001e\u0001\u0014\u0001\u0016\u0001\u001d\u0001\u0013\u0001\u0017\u0001\u001a\u0001\u0010\u0001\u0018\u0001\u0006\u0001$\u0001 \u0001#\u0001'\uff85\u0000");
    private static final int[] ZZ_ACTION = FortranTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\u0002\u0001\u0005\u0001\b\u0001\t\u0001\n\u0001\u0002\u0001\n\u0014\u0002\u0001\u0001\u0001\u000b\u0001\f\u0001\u0001\u0001\r\u0001\u000e\u0007\u0002\u0001\u000f\u0010\u0002\u0007\u0000!\u0002\u0001\u000f\u0017\u0002\u0001\u000f\b\u0002\u0001\u000f\u0003\u0002\u0004\u0000\u0002\u000f\u0003\u0002\u0002\u000f\u0010\u0002\u0001\u000f\b\u0002\u0002\u000f\u0003\u0002\u0001\u000f\u0002\u0002\u0001\u000f\u0006\u0002\u0001\u000f\u0002\u0000\u0003\u0002\u0001\u000f\u0004\u0002\u0001\u000f\u0016\u0002\u0001\u0000\u0010\u0002\u0001\u0010\n\u0002\u0001\u000f";
    private static final int[] ZZ_ROWMAP = FortranTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000*\u0000T\u0000~\u0000~\u0000\u00a8\u0000~\u0000\u00d2\u0000~\u0000\u00fc\u0000\u0126\u0000~\u0000~\u0000\u0150\u0000\u0150\u0000~\u0000\u017a\u0000\u01a4\u0000\u01ce\u0000\u01f8\u0000\u0222\u0000\u024c\u0000\u0276\u0000\u02a0\u0000\u02ca\u0000\u02f4\u0000\u031e\u0000\u0348\u0000\u0372\u0000\u0126\u0000\u039c\u0000\u03c6\u0000\u03f0\u0000\u041a\u0000\u0444\u0000\u046e\u0000\u0498\u0000~\u0000~\u0000\u04c2\u0000~\u0000~\u0000\u04ec\u0000\u0516\u0000\u0540\u0000\u056a\u0000\u0594\u0000\u05be\u0000\u05e8\u0000\u0612\u0000\u063c\u0000\u0666\u0000\u0690\u0000\u06ba\u0000\u06e4\u0000\u070e\u0000\u0738\u0000\u0762\u0000\u078c\u0000\u07b6\u0000\u07e0\u0000\u080a\u0000\u0834\u0000\u085e\u0000\u0888\u0000\u08b2\u0000\u08dc\u0000\u0906\u0000\u0930\u0000\u095a\u0000\u0984\u0000\u09ae\u0000\u09d8\u0000\u0a02\u0000\u0a2c\u0000\u0a56\u0000\u0a80\u0000\u0aaa\u0000\u0ad4\u0000\u0afe\u0000\u0b28\u0000\u0b52\u0000\u0b7c\u0000\u0ba6\u0000\u0bd0\u0000\u0bfa\u0000\u0c24\u0000\u0c4e\u0000\u0c78\u0000\u0ca2\u0000\u0ccc\u0000\u0cf6\u0000\u0d20\u0000\u0d4a\u0000\u0d74\u0000\u0d9e\u0000\u0dc8\u0000\u0df2\u0000\u0e1c\u0000\u0e46\u0000\u0e70\u0000\u0e9a\u0000\u0ec4\u0000\u0eee\u0000\u0f18\u0000\u0f42\u0000\u0f6c\u0000\u0f96\u0000\u0fc0\u0000\u0fea\u0000\u1014\u0000\u103e\u0000\u1068\u0000\u1092\u0000\u10bc\u0000\u10e6\u0000\u1110\u0000\u113a\u0000\u1164\u0000\u118e\u0000\u11b8\u0000\u11e2\u0000\u120c\u0000\u1236\u0000\u1260\u0000\u128a\u0000\u12b4\u0000\u12de\u0000\u1308\u0000\u1332\u0000\u135c\u0000\u1386\u0000\u13b0\u0000\u13da\u0000\u1404\u0000\u142e\u0000\u1458\u0000\u1482\u0000\u14ac\u0000\u14d6\u0000\u1500\u0000\u152a\u0000\u1554\u0000\u157e\u0000\u15a8\u0000\u15d2\u0000\u15fc\u0000\u00fc\u0000\u1626\u0000\u1650\u0000\u167a\u0000\u16a4\u0000\u16ce\u0000\u16f8\u0000\u1722\u0000\u174c\u0000\u1776\u0000\u17a0\u0000\u17ca\u0000\u17f4\u0000\u181e\u0000\u1848\u0000\u1872\u0000\u189c\u0000\u18c6\u0000\u18f0\u0000\u191a\u0000\u1944\u0000\u196e\u0000\u1998\u0000\u19c2\u0000\u19ec\u0000\u1a16\u0000\u1a40\u0000\u1a6a\u0000\u16f8\u0000\u1a94\u0000\u1abe\u0000\u1ae8\u0000\u1b12\u0000\u1b3c\u0000\u1b66\u0000\u1b90\u0000\u1bba\u0000\u1be4\u0000\u1c0e\u0000\u1c38\u0000\u174c\u0000\u1c62\u0000\u1c8c\u0000\u1cb6\u0000\u1ce0\u0000\u1d0a\u0000\u1d34\u0000\u1d5e\u0000\u1d88\u0000\u1db2\u0000\u1ddc\u0000\u1e06\u0000\u1e30\u0000\u1e5a\u0000\u1e84\u0000\u1eae\u0000\u1ed8\u0000\u1f02\u0000\u1f2c\u0000\u1b12\u0000\u1f56\u0000\u1f80\u0000\u1faa\u0000\u1fd4\u0000\u1ffe\u0000\u2028\u0000\u2052\u0000\u207c\u0000\u20a6\u0000\u20d0\u0000\u20fa\u0000\u2124\u0000\u214e\u0000\u2178\u0000\u21a2\u0000\u21cc\u0000\u21f6\u0000\u2220\u0000\u224a\u0000\u2274\u0000\u229e\u0000\u22c8\u0000\u22f2\u0000\u231c\u0000\u2346\u0000\u2370\u0000\u239a\u0000\u23c4\u0000\u23ee\u0000\u2418\u0000\u2442\u0000\u246c\u0000\u2496\u0000\u24c0\u0000\u24ea\u0000\u2514\u0000\u253e\u0000\u2568\u0000~\u0000\u2592\u0000\u25bc\u0000\u25e6\u0000\u2610\u0000\u263a\u0000\u2664\u0000\u268e\u0000\u26b8\u0000\u26e2\u0000\u270c\u0000\u13da";
    private static final int[] ZZ_TRANS = FortranTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u000f\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\n\u0001\u0016\u0001\u0017\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u001c\u0001\u001d\u0001\u001e\u0001\u001f\u0001 \u0001!\u0002\n\u0001\"\u0001\n\u0001#\u0002\n\u0001$\u0002\n\u0001%\u0001&\u0006%\u0001'!%\u0001(\u0001)\u0007(\u0001* (,\u0000\u0001\u0006+\u0000\u0001+\u0001\u0000\u0001\n\u0001,\u0007\u0000\u0001-\u0001.\u0001\n\u0001/\u0001\n\u00010\u00011\u00012\u00013\u0001\n\u00014\u00015\u00016\u0001,\u00017\u00018\u00019\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u001b\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001:\u0007\u0000\u0001;\u0002\n\u0001<\u0002\n\u0001=\u0001>\u0003\n\u0001?\u0001\n\u0001:\u0001\n\u0001@\u0002\n\u0001A\u0001\n\u0001B\u0006\n\u000b\u0000\u0001\u0010-\u0000\u0001C\u0001D\u0001C\u0001E\u0001\u0000\u0001F\u0001G\u0001H\u0002\u0000\u0001I\u0014\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001J\u0001\n\u0001J\u0001K\u0003\n\u0001L\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001M\u0001\n\u0001N\t\n\u0001O\b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001P\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001Q\u0004\n\u0001R\u000b\n\u0001S\t\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001T\u0004\n\u0001U\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001:\u0007\u0000\u0001V\u0001W\u0003\n\u00010\u0005\n\u0001X\u0001Y\u0001:\u0001\n\u0001Z\u0001[\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000e\n\u0001O\f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\\\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001]\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001^\u0006\n\u0001_\u0001\n\u0001`\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001a\u0001\n\u0001b\u0001c\u0001d\u0003\n\u0001e\u0002\n\u0001f\u000e\n\u0004\u0000\u0001g\u0001\u0000\u0001\n\u0001h\u0007\u0000\u0005\n\u0001i\u0001j\u0003\n\u0001k\u0001l\u0001\n\u0001h\u0001\n\u0001m\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001n\u0001\n\u0001o\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001p\u0001q\u0004\n\u0001r\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001s\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001t\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001u\t\n\u0001v\b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001w\u0017\n\u0001%\u0001\u0000\u0006%\u0001\u0000!%\u0001(\u0001\u0000\u0007(\u0001\u0000 (\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001x\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001y\u0007\n\u0001@\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001z\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001M\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001{\u0003\n\u0001|\u0006\n\u0001}\t\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001U\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001:\u0007\u0000\u0001\n\u0001~\t\n\u0001X\u0001\n\u0001:\u0002\n\u0001[\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u007f\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u0080\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u0081\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0004\n\u0001c\u0007\n\u0001f\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u0082\t\n\u0001\u0083\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u0084\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u0085\u0001\u0086\u0004\n\u0001\u0087\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001]\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001[\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u0088\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u0089\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u008a\n\n\u0001]\u0004\n\u0001[\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u008b\u0005\n\u0001\u008c\u0003\n\u0001\u008d\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0004\n\u0001c\u0007\n\u0001K\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000e\n\u0001\u008e\f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u008f\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u00019\u0007\u0000\r\n\u00019\r\n\u0010\u0000\u0001\u0090\u0001\u0000\u0001\u0090.\u0000\u0001\u0091%\u0000\u0001\u0090(\u0000\u0001\u0090+\u0000\u0001\u0092,\u0000\u0001\u0090'\u0000\u0001\u0093\u0018\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u0094\u0001\n\u0001\u0094\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u0094\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u0095\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u008c\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u0096\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001K\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u0097\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001\u0098\u000f\n\u0004\u0000\u0001\u0099\u0001\u0000\u0002\n\u0007\u0000\u001b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u0082\u0001\n\u0001\u009a\f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001]\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u0082\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u009b\u0006\n\u0001z\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u009c\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001K\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u0082\t\n\u0001\u009d\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u009e\u0001\u0086\u0004\n\u0001\u009f\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001\u0094\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u00a0\u0004\n\u0001\u00a1\u000e\n\u0001\"\u0005\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u0094\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u00a2\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u00a3\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00a4\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001}\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00a5\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u0082\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u008a\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0010\n\u0001\u00a6\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001K\u0002\n\u0001\u008c\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u00010\u0006\n\u0001\u00a7\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0012\n\u0001\u00a8\b\n\u0004\u0000\u0001\u00a9\u0001\u0000\u0001\n\u0001\u00aa\u0007\u0000\u0001\n\u0001\u00ab\u0002\n\u0001\u00ac\b\n\u0001\u00aa\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0010\n\u0001[\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00ad\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00ae\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00af\u0007\n\u0001\u00b0\f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u00b1\u0001\u001a\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00b2\u0003\n\u0001\u00b3\u0004\n\u0001U\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0011\n\u0001\u00b4\t\n\u0004\u0000\u0001\u00b5\u0001\u0000\u0002\n\u0007\u0000\u001b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00b4\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00b6\u0007\u0000\r\n\u0001\u00b6\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u0086\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00b7\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00b8\b\n\u00019\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0011\n\u0001\u0082\t\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000f\n\u0001\u0094\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001\u008c\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u00b9\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00ba\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00bb\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000e\n\u0001\u0094\f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00bc\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0010\n\u00019\n\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u008a\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u00bd\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u0094\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00be\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u0086\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0011\n\u0001\u00bf\t\n\u0004\u0000\u0001\u0094\u0001\u0000\u0002\n\u0007\u0000\u001b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00bf\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u0094\b\n\u0001]\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00c0\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u0094\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u00c1\u0015\n\u0001\u00af\u0003\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0012\n\u0001\u0094\b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000e\n\u0001\u00c2\f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00ad\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u00c3\u0012\n\u000e\u0000\u0001\u00103\u0000\u0001\u00c4\u0015\u0000\u0001\u00904\u0000\u0001\u00c5\u001e\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00c6\f\n\u0001\u00c7\u0001\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00c8\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u0094\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00c9\u0017\n\u0004\u0000\u0001\u0097\u0001\u0000\u0002\n\u0007\u0000\n\n\u0001\u00ca\u0001\n\u0001\u00cb\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00cc\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u00cd\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00ce\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00af\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0011\n\u0001\u00cf\t\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00cf\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u00d0\u0011\n\u0004\u0000\u0001\u0094\u0001\u0000\u0002\n\u0007\u0000\u0001\u0094\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u0082\u0001\u00d1\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00d2\b\n\u0001\u00bd\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00d3\u0007\u0000\r\n\u0001\u00d3\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00d4\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u00d5\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u0082\t\n\u0001\u0094\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00d1\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00ad\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00d6\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00d7\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u00d8\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0011\n\u0001\u0094\t\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001K\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u0094\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00d9\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00da\u0007\u0000\r\n\u0001\u00da\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u00db\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0016\n\u0001\u0094\u0002\n\u0001\u0094\u0001\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u00dc\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0013\n\u0001\u00dd\u0007\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001]\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001]\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0019\n\u0001\u00c7\u0001\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00de\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u00df\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u0082\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00e0\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0019\n\u0001\u0094\u0001\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00e1\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00e2\u0005\n\u0001\u00e3\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00a9\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00e4\u0014\n\u0012\u0000\u0001\u00e51\u0000\u0001\u00c4\u0013\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u0080\u0007\u0000\r\n\u0001\u0080\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0016\n\u0001\u0094\u0004\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001\u00e6\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00cb\b\n\u0001\u00e7\u0005\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u00019\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\n\n\u0001\u0094\u0010\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00e8\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00e9\u0007\u0000\r\n\u0001\u00e9\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u001a\n\u0001\u0094\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001K\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u0094\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u008a\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u00ea\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u0082\u0007\u0000\r\n\u0001\u0082\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u00eb\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u00ec\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0002\n\u0001\u00ed\u0018\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00b8\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000f\n\u0001\u00ee\u000b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00e0\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\b\n\u0001\u00ef\u0012\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u00f0\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001\u00f1\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001\u00f2\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\u0082\u001a\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000b\n\u0001\u00ea\u000f\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00af\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00f3\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001\u00f4\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00f5\u0007\u0000\r\n\u0001\u00f5\r\n\u000e\u0000\u0001\u00f6\u001f\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\n\n\u0001\u00ed\u0010\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0012\n\u0001\u00f7\b\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00f8\u0007\u0000\r\n\u0001\u00f8\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00f9\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u00fa\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001\u00fb\u0011\n\u0004\u0000\u0001]\u0001\u0000\u0002\n\u0007\u0000\u001b\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00d1\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00f5\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001x\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u0094\u0003\n\u0001\u0094\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u000e\n\u0001\u00fc\f\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001\u00fd\u0007\u0000\r\n\u0001\u00fd\r\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0005\n\u0001[\u0015\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\t\n\u0001]\u0011\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u00ed\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u00b8\u0017\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001\u0082\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u00fe\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0007\n\u0001K\u0013\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0001\n\u0001\u00ff\u0019\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u0100\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0006\n\u0001\u00b7\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\u0003\n\u0001\u0101\u0002\n\u0001\u007f\u0014\n\u0004\u0000\u0001\n\u0001\u0000\u0002\n\u0007\u0000\f\n\u0001T\u000e\n\u0004\u0000\u0001\n\u0001\u0000\u0001\n\u0001]\u0007\u0000\r\n\u0001]\r\n";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = FortranTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0000\u0002\u0001\u0002\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\u0002\u0001\u0002\t\u0002\u0001\u0001\t\u0015\u0001\u0002\t\u0001\u0001\u0002\t\u0018\u0001\u0007\u0000F\u0001\u0004\u00000\u0001\u0002\u0000\u001f\u0001\u0001\u0000\u0010\u0001\u0001\t\u000b\u0001";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;

    private static int[] zzUnpackAction() {
        int[] result = new int[257];
        int offset = 0;
        offset = FortranTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    private static int[] zzUnpackRowMap() {
        int[] result = new int[257];
        int offset = 0;
        offset = FortranTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    private static int[] zzUnpackTrans() {
        int[] result = new int[10038];
        int offset = 0;
        offset = FortranTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do {
                result[j++] = --value;
            } while (--count > 0);
        }
        return j;
    }

    private static int[] zzUnpackAttribute() {
        int[] result = new int[257];
        int offset = 0;
        offset = FortranTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    public FortranTokenMaker() {
    }

    private void addToken(int tokenType) {
        this.addToken(this.zzStartRead, this.zzMarkedPos - 1, tokenType);
    }

    private void addToken(int start, int end, int tokenType) {
        int so = start + this.offsetShift;
        this.addToken(this.zzBuffer, start, end, tokenType, so);
    }

    @Override
    public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
        super.addToken(array, start, end, tokenType, startOffset);
        this.zzStartRead = this.zzMarkedPos;
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"!", null};
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        int state = 0;
        switch (initialTokenType) {
            case 13: {
                state = 1;
                this.start = text.offset;
                break;
            }
            case 14: {
                state = 2;
                this.start = text.offset;
                break;
            }
            default: {
                state = 0;
            }
        }
        this.s = text;
        try {
            this.yyreset(this.zzReader);
            this.yybegin(state);
            return this.yylex();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return new TokenImpl();
        }
    }

    private boolean zzRefill() {
        return this.zzCurrentPos >= this.s.offset + this.s.count;
    }

    public final void yyreset(Reader reader) {
        this.zzBuffer = this.s.array;
        this.zzStartRead = this.s.offset;
        this.zzEndRead = this.zzStartRead + this.s.count - 1;
        this.zzCurrentPos = this.zzMarkedPos = this.s.offset;
        this.zzLexicalState = 0;
        this.zzReader = reader;
        this.zzAtEOF = false;
    }

    public FortranTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public FortranTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 168) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                map[j++] = value;
            } while (--count > 0);
        }
        return map;
    }

    public final void yyclose() throws IOException {
        this.zzAtEOF = true;
        this.zzEndRead = this.zzStartRead;
        if (this.zzReader != null) {
            this.zzReader.close();
        }
    }

    public final int yystate() {
        return this.zzLexicalState;
    }

    @Override
    public final void yybegin(int newState) {
        this.zzLexicalState = newState;
    }

    public final String yytext() {
        return new String(this.zzBuffer, this.zzStartRead, this.zzMarkedPos - this.zzStartRead);
    }

    public final char yycharat(int pos) {
        return this.zzBuffer[this.zzStartRead + pos];
    }

    public final int yylength() {
        return this.zzMarkedPos - this.zzStartRead;
    }

    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[0];
        }
        throw new Error(message);
    }

    public void yypushback(int number) {
        if (number > this.yylength()) {
            this.zzScanError(2);
        }
        this.zzMarkedPos -= number;
    }

    public Token yylex() throws IOException {
        int zzEndReadL = this.zzEndRead;
        char[] zzBufferL = this.zzBuffer;
        char[] zzCMapL = ZZ_CMAP;
        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;
        block41: while (true) {
            int zzInput;
            int zzMarkedPosL = this.zzMarkedPos;
            int zzAction = -1;
            this.zzCurrentPos = this.zzStartRead = zzMarkedPosL;
            int zzCurrentPosL = this.zzStartRead;
            this.zzState = this.zzLexicalState;
            while (true) {
                if (zzCurrentPosL < zzEndReadL) {
                    zzInput = zzBufferL[zzCurrentPosL++];
                } else {
                    if (this.zzAtEOF) {
                        zzInput = -1;
                        break;
                    }
                    this.zzCurrentPos = zzCurrentPosL;
                    this.zzMarkedPos = zzMarkedPosL;
                    boolean eof = this.zzRefill();
                    zzCurrentPosL = this.zzCurrentPos;
                    zzMarkedPosL = this.zzMarkedPos;
                    zzBufferL = this.zzBuffer;
                    zzEndReadL = this.zzEndRead;
                    if (eof) {
                        zzInput = -1;
                        break;
                    }
                    zzInput = zzBufferL[zzCurrentPosL++];
                }
                int zzNext = zzTransL[zzRowMapL[this.zzState] + zzCMapL[zzInput]];
                if (zzNext == -1) break;
                this.zzState = zzNext;
                int zzAttributes = zzAttrL[this.zzState];
                if ((zzAttributes & 1) != 1) continue;
                zzAction = this.zzState;
                zzMarkedPosL = zzCurrentPosL;
                if ((zzAttributes & 8) == 8) break;
            }
            this.zzMarkedPos = zzMarkedPosL;
            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 15: {
                    this.addToken(6);
                }
                case 17: {
                    continue block41;
                }
                case 2: {
                    this.addToken(20);
                }
                case 18: {
                    continue block41;
                }
                case 4: {
                    this.addToken(21);
                }
                case 19: {
                    continue block41;
                }
                case 11: {
                    this.addToken(this.start, this.zzStartRead - 1, 13);
                    return this.firstToken;
                }
                case 20: {
                    continue block41;
                }
                case 13: {
                    this.addToken(this.start, this.zzStartRead - 1, 14);
                    return this.firstToken;
                }
                case 21: {
                    continue block41;
                }
                case 10: {
                    this.addToken(23);
                }
                case 22: {
                    continue block41;
                }
                case 5: {
                    if (this.zzStartRead == this.s.offset) {
                        this.addToken(this.zzStartRead, this.zzEndRead, 1);
                        this.addNullToken();
                        return this.firstToken;
                    }
                    this.addToken(20);
                }
                case 23: {
                    continue block41;
                }
                case 16: {
                    this.addToken(9);
                }
                case 24: {
                    continue block41;
                }
                case 8: {
                    this.start = this.zzMarkedPos - 1;
                    this.yybegin(1);
                }
                case 25: {
                    continue block41;
                }
                case 7: {
                    this.addToken(this.zzStartRead, this.zzEndRead, 1);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 26: {
                    continue block41;
                }
                case 6: {
                    if (this.zzStartRead == this.s.offset) {
                        this.addToken(this.zzStartRead, this.zzEndRead, 3);
                        this.addNullToken();
                        return this.firstToken;
                    }
                    this.addToken(20);
                }
                case 27: {
                    continue block41;
                }
                case 9: {
                    this.start = this.zzMarkedPos - 1;
                    this.yybegin(2);
                }
                case 28: {
                    continue block41;
                }
                case 14: {
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead, 14);
                }
                case 29: {
                    continue block41;
                }
                case 12: {
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead, 13);
                }
                case 30: {
                    continue block41;
                }
                case 3: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 31: {
                    continue block41;
                }
                case 1: 
                case 32: {
                    continue block41;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 13);
                        return this.firstToken;
                    }
                    case 258: {
                        continue block41;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 259: {
                        continue block41;
                    }
                    case 2: {
                        this.addToken(this.start, this.zzStartRead - 1, 14);
                        return this.firstToken;
                    }
                    case 260: {
                        continue block41;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

