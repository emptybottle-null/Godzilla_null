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

public class LuaTokenMaker
extends AbstractJFlexTokenMaker {
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int LONGSTRING = 2;
    public static final int LINECOMMENT = 3;
    public static final int MLC = 1;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0004\u0001\u0003\u0001\u0000\u0001\u0004\u0013\u0000\u0001\u0004\u0001\u0000\u0001\u0007\u0001\u0017\u0001\u0000\u0001\u0017\u0001\u0000\u0001\u0005\u0002\u0016\u0001\u0017\u0001\r\u0001\u0000\u0001\n\u0001\u000b\u0001\u0017\n\u0002\u0002\u0000\u0001\u0018\u0001\u001a\u0001\u0019\u0002\u0000\u0004\u0001\u0001\f\u0001\u0001\u0001,\u0001\u0001\u00010\u0002\u0001\u0001*\u0001\u0001\u0001(\u00011\u0002\u0001\u0001.\u0001/\u0001\u0001\u0001)\u0001-\u0004\u0001\u0001\b\u0001\u0006\u0001\t\u0001\u0017\u0001+\u0001\u0000\u0001\u0013\u0001\u001f\u0001\"\u0001\u001d\u0001\u0011\u0001\u0012\u0001#\u0001%\u0001!\u0001\u0001\u0001 \u0001\u0014\u0001'\u0001\u001c\u0001\u001e\u0001$\u00014\u0001\u000f\u0001\u0015\u0001\u000e\u0001\u0010\u00012\u0001&\u00013\u00015\u0001\u0001\u0001\u0016\u0001\u0000\u0001\u0016\u0001\u001b\uff81\u0000";
    private static final char[] ZZ_CMAP = LuaTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0004\u0001\u0003\u0001\u0000\u0001\u0004\u0013\u0000\u0001\u0004\u0001\u0000\u0001\u0007\u0001\u0017\u0001\u0000\u0001\u0017\u0001\u0000\u0001\u0005\u0002\u0016\u0001\u0017\u0001\r\u0001\u0000\u0001\n\u0001\u000b\u0001\u0017\n\u0002\u0002\u0000\u0001\u0018\u0001\u001a\u0001\u0019\u0002\u0000\u0004\u0001\u0001\f\u0001\u0001\u0001,\u0001\u0001\u00010\u0002\u0001\u0001*\u0001\u0001\u0001(\u00011\u0002\u0001\u0001.\u0001/\u0001\u0001\u0001)\u0001-\u0004\u0001\u0001\b\u0001\u0006\u0001\t\u0001\u0017\u0001+\u0001\u0000\u0001\u0013\u0001\u001f\u0001\"\u0001\u001d\u0001\u0011\u0001\u0012\u0001#\u0001%\u0001!\u0001\u0001\u0001 \u0001\u0014\u0001'\u0001\u001c\u0001\u001e\u0001$\u00014\u0001\u000f\u0001\u0015\u0001\u000e\u0001\u0010\u00012\u0001&\u00013\u00015\u0001\u0001\u0001\u0016\u0001\u0000\u0001\u0016\u0001\u001b\uff81\u0000");
    private static final int[] ZZ_ACTION = LuaTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0004\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0002\u0007\u0001\b\u0001\u0001\u0001\b\b\u0001\u0002\b\u000e\u0001\u0001\t\u0001\n\u0001\t\u0001\u000b\u0002\t\u0001\f\u0002\u0002\u0001\r\u0001\u0005\u0001\u0006\u0001\u000e\u0001\u000f\u0001\u0010\u0001\b\u0011\u0001\u0003\u0000\u0003\u0001\u0001\u0011\u0001\b\u0001\u0001\u0001\u0011\n\u0001\u0001\u0012\u0002\u0001\u0001\u0013\u0001\u0014\u0001\r\u0001\u000e\u0001\u0000\u0014\u0001\u0004\u0000\f\u0001\u0001\u0015\u0001\u0016\t\u0001\u0001\u0011\u0002\u0001\u0001\u0012\u0003\u0001\u0004\u0000\u0006\u0001\u0001\u0017\u000b\u0001\u0001\u0017\u0002\u0000\b\u0001\u0002\u0000\r\u0001";
    private static final int[] ZZ_ROWMAP = LuaTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u00006\u0000l\u0000\u00a2\u0000\u00d8\u0000\u010e\u0000\u0144\u0000\u00d8\u0000\u017a\u0000\u01b0\u0000\u01e6\u0000\u021c\u0000\u00d8\u0000\u0252\u0000\u0288\u0000\u00d8\u0000\u02be\u0000\u02f4\u0000\u032a\u0000\u0360\u0000\u0396\u0000\u03cc\u0000\u0402\u0000\u0438\u0000\u046e\u0000\u04a4\u0000\u04a4\u0000\u04da\u0000\u0510\u0000\u0546\u0000\u057c\u0000\u05b2\u0000\u05e8\u0000\u061e\u0000\u0654\u0000\u068a\u0000\u06c0\u0000\u06f6\u0000\u072c\u0000\u0762\u0000\u0798\u0000\u00d8\u0000\u07ce\u0000\u00d8\u0000\u0804\u0000\u083a\u0000\u00d8\u0000\u0870\u0000\u08a6\u0000\u00d8\u0000\u08dc\u0000\u0912\u0000\u00d8\u0000\u00d8\u0000\u0948\u0000\u097e\u0000\u09b4\u0000\u09ea\u0000\u0a20\u0000\u0a56\u0000\u0a8c\u0000\u0ac2\u0000\u0af8\u0000\u0b2e\u0000\u0b64\u0000\u0b9a\u0000\u0bd0\u0000\u0c06\u0000\u0c3c\u0000\u0c72\u0000\u0ca8\u0000\u0cde\u0000\u0d14\u0000\u0d4a\u0000\u0d80\u0000\u0db6\u0000\u0dec\u0000\u0e22\u0000\u0e58\u0000\u0e8e\u0000\u010e\u0000\u0ec4\u0000\u010e\u0000\u0efa\u0000\u0f30\u0000\u0f66\u0000\u0f9c\u0000\u0fd2\u0000\u1008\u0000\u103e\u0000\u1074\u0000\u10aa\u0000\u10e0\u0000\u010e\u0000\u1116\u0000\u114c\u0000\u00d8\u0000\u00d8\u0000\u01b0\u0000\u01e6\u0000\u1182\u0000\u11b8\u0000\u11ee\u0000\u1224\u0000\u125a\u0000\u1290\u0000\u12c6\u0000\u12fc\u0000\u1332\u0000\u1368\u0000\u139e\u0000\u13d4\u0000\u140a\u0000\u1440\u0000\u1476\u0000\u14ac\u0000\u14e2\u0000\u1518\u0000\u154e\u0000\u1584\u0000\u15ba\u0000\u15f0\u0000\u1626\u0000\u165c\u0000\u1692\u0000\u16c8\u0000\u16fe\u0000\u1734\u0000\u176a\u0000\u17a0\u0000\u17d6\u0000\u180c\u0000\u1842\u0000\u1878\u0000\u18ae\u0000\u18e4\u0000\u191a\u0000\u00d8\u0000\u010e\u0000\u1950\u0000\u1986\u0000\u19bc\u0000\u19f2\u0000\u1a28\u0000\u1a5e\u0000\u1a94\u0000\u1aca\u0000\u1b00\u0000\u1b36\u0000\u1b6c\u0000\u1ba2\u0000\u1bd8\u0000\u1c0e\u0000\u1c44\u0000\u1c7a\u0000\u1cb0\u0000\u1ce6\u0000\u1d1c\u0000\u1d52\u0000\u1d88\u0000\u1dbe\u0000\u1df4\u0000\u1e2a\u0000\u1e60\u0000\u1e96\u0000\u010e\u0000\u1ecc\u0000\u1f02\u0000\u1f38\u0000\u1f6e\u0000\u1fa4\u0000\u1fda\u0000\u2010\u0000\u2046\u0000\u207c\u0000\u20b2\u0000\u20e8\u0000\u00d8\u0000\u211e\u0000\u2154\u0000\u218a\u0000\u21c0\u0000\u21f6\u0000\u222c\u0000\u2262\u0000\u2298\u0000\u22ce\u0000\u2304\u0000\u233a\u0000\u2370\u0000\u23a6\u0000\u23dc\u0000\u2412\u0000\u2448\u0000\u247e\u0000\u24b4\u0000\u24ea\u0000\u2520\u0000\u2556\u0000\u258c\u0000\u25c2\u0000\u25f8\u0000\u262e";
    private static final int[] ZZ_TRANS = LuaTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u0005\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0006\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0017\u0001\u0018\u0001\r\u0001\u0010\u0001\u0019\u0001\u001a\u0002\u001b\u0001\u001c\u0001\u001d\u0001\u001e\u0001\u001f\u0001\u0006\u0001 \u0001!\u0001\"\u0001#\u0001\u0006\u0001$\u0001%\u0001&\u0002\u0006\u0001'\u0007\u0006\u0001(\u0002\u0006\u0003)\u0001*\u0005)\u0001+/)\u0001,\u0005)\u0001-,)\u0003.\u0001/2.7\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u00010\u0001\u0007\b\u0000\u0001\u0007\u00011\u0001\u0000\u00030\u00011\u00040\u0006\u0000\u001a0\u0004\u0000\u0001\t1\u0000\u0003\n\u0001\u0000\u0001\n\u00012\u00013/\n\u0003\u000b\u0001\u0000\u0002\u000b\u00014\u00015.\u000b\b\u0000\u000167\u0000\u00017-\u0000\u0001\u0007\b\u0000\u00018+\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u00019\u0006\u0006\u0006\u0000\u0002\u0006\u0001:\u0006\u0006\u0001;\u000f\u0006\u0001<\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001=\u0001\u0006\u0001>\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001?\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001@\u0004\u0006\u0001A\u0001\u0006\u0006\u0000\u0001B\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001C\u0002\u0006\u0001D\u0002\u0006\u0006\u0000\u0002\u0006\u0001E\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0007\u0006\u0001F\u0006\u0000\u0001G\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001H\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001I\u0004\u0006\u0006\u0000\u001a\u0006\u0011\u0000\u0001J\u0003\u0000\u0001K\u0004\u0000\u0001\u0010\u0001\u0000\u0001L3\u0000\u0001\u0010\u001c\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001M\u0004\u0006\u0006\u0000\u0002\u0006\u0001N\u0002\u0006\u0001O\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001P\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001Q\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001R\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001S\u0003\u0006\u0006\u0000\u0001S\u0007\u0006\u0001T\u0011\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001U\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001V\u0004\u0006\u0006\u0000\u0002\u0006\u0001W\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001X\u0003\u0006\u0001Y\u0002\u0006\u0006\u0000\u0006\u0006\u0001Z\u0013\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\t\u0006\u0001[\u0010\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001\\\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\r\u0006\u0001]\f\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0010\u0006\u0001^\u0001_\b\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\b\u0006\u0001`\u0011\u0006\u0003)\u0001\u0000\u0005)\u0001\u0000,)\t\u0000\u0001a5\u0000\u0001b,\u0000\u0003.\u0001\u00002.\u0001\u0000\u00020\t\u0000\u00010\u0001\u0000\b0\u0006\u0000\u001a0\u0001\u0000\u00020\u0007\u0000\u00010\u0001\u0000\n0\u0006\u0000\u001a0\u0003\n\u0001\u0000\u0001\n\u0001c\u00013/\n\u0003\u000b\u0001\u0000\u0002\u000b\u00014\u0001d.\u000b\b\u0000\u0001e8\u0000\u0001\u0010+\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001f\u0005\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0007\u0006\u0001g\u0006\u0000\u0001h\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001i\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\b\u0006\u0001j\u0011\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001k\u0007\u0006\u0006\u0000\b\u0006\u0001l\u000f\u0006\u0001m\u0001\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\n\u0006\u0001n\u000f\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001o\u0007\u0006\u0006\u0000\b\u0006\u0001p\u0011\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001q\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0007\u0006\u0001r\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001\u0006\u0001S\u0018\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001s\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001t\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001S\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0007\u0006\u0001u\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001\u0006\u0001Q\u0018\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001v\u0002\u0006\u0006\u0000\u0006\u0006\u0001w\u0013\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001x\u0005\u0006\u0001y\u0001\u0006\u0006\u0000\u001a\u0006\u001e\u0000\u0001z%\u0000\u0001{7\u0000\u0001|\u0002\u0000\u0001}#\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0017\u0006\u0001~\u0002\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001Q\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001S\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001\u007f\u0003\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0080\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001Y\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001\u0081\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001x\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0082\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u0083\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u0084\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u0085\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u0086\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001\u0006\u0001\u0087\u0018\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u000e\u0006\u0001\u0088\u000b\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0089\u0001\u0000\b\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0006\u0006\u0001Z\u0013\u0006\b\u0000\u0001\u008a.\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u008b\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u008c\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001\u008d\u0005\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001S\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001^\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001\u008e\u0005\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u008f\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001\u0090\u0005\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0091\u0003\u0006\u0001\u0092\u0006\u0000\u0007\u0006\u0001\u0092\u0012\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001O\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u0093\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001\u0094\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0095\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0006\u0006\u0001\u0096\u0013\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0007\u0006\u0001f\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0097\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001\u0006\u0001\u0098\u0018\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001O\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001\u0099\u0003\u0006\u0006\u0000\u000b\u0006\u0001\u009a\u000e\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u009b\u0004\u0006\u0006\u0000\u001a\u0006\u0012\u0000\u0001\u009c2\u0000\u0001\u009dM\u0000\u0001\u009e5\u0000\u0001\u009f\u000f\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001^\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u00a0\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00a1\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001\u00a2\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001S\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001~\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u00a3\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001\u00a4\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001\u00a5\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001\u00a0\u0005\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u000e\u0006\u0001\u00a6\u000b\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0012\u0006\u0001\u00a7\u0007\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u00a8\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u000b\u0006\u0001\u00a9\u000e\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001i\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00aa\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u00ab\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0018\u0006\u0001\u00ac\u0001\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001~\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0006\u0006\u0001\u00ad\u0013\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001^\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u00ae\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u00af\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001~\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001\u007f\u0002\u0006\u0001g\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u00b0\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u00b1\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0006\u0006\u0001~\u0013\u0006\u0019\u0000\u0001\u00b2=\u0000\u0001\u00b33\u0000\u0001\u00b4'\u0000\u0001\u009c%\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001j\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0004\u0006\u0001S\u0015\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u00b5\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0007\u0006\u0001^\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0001^\u0001\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001S\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0013\u0006\u0001\u00b6\u0006\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u00b7\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0003\u0006\u0001\u00b8\u0016\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001S\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001j\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001\u00b9\u0005\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0004\u0006\u0001^\u0015\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001S\u0003\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0005\u0006\u0001\u00ba\u0014\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001\u00bb\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u00bc\u0007\u0006\u0006\u0000\u001a\u0006\u001c\u0000\u0001\u00bd*\u0000\u0001\u00be%\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0006\u0006\u0001\u00bf\u0013\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0014\u0006\u0001\u00c0\u0005\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0001\u00c1\u0019\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0094\u0004\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00a4\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0002\u0006\u0001i\u0017\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0016\u0006\u0001^\u0003\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00c2\u0002\u0006\u0006\u0000\u001a\u0006#\u0000\u0001\u009c!\u0000\u0001\u009c'\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u00c3\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0015\u0006\u0001\u00c4\u0004\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0007\u0006\u0001^\u0012\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u00c5\u0007\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0007\u0006\u0001\u00c6\u0012\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\f\u0006\u0001^\r\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00c7\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00c8\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0003\u0006\u0001\u00a0\u0016\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u00c9\u0006\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0003\u0006\u0001\u00ca\u0016\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u00cb\u0002\u0006\u0006\u0000\u001a\u0006\u0001\u0000\u0002\u0006\t\u0000\u0001\u0006\u0001\u0000\b\u0006\u0006\u0000\u0007\u0006\u0001j\u0012\u0006";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = LuaTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0004\u0000\u0001\t\u0002\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0001\u0001\t\u0019\u0001\u0001\t\u0001\u0001\u0001\t\u0002\u0001\u0001\t\u0002\u0001\u0001\t\u0002\u0001\u0002\t\u0013\u0001\u0003\u0000\u0014\u0001\u0002\t\u0002\u0001\u0001\u0000\u0014\u0001\u0004\u0000\f\u0001\u0001\t\u0011\u0001\u0004\u0000\u0012\u0001\u0001\t\u0002\u0000\b\u0001\u0002\u0000\r\u0001";
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
        int[] result = new int[203];
        int offset = 0;
        offset = LuaTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[203];
        int offset = 0;
        offset = LuaTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[9828];
        int offset = 0;
        offset = LuaTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[203];
        int offset = 0;
        offset = LuaTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public LuaTokenMaker() {
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
        return new String[]{"--", null};
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        int state = 0;
        switch (initialTokenType) {
            case 2: {
                state = 1;
                this.start = text.offset;
                break;
            }
            case 13: {
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

    public LuaTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public LuaTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 164) {
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
        block57: while (true) {
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
                case 3: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 24: {
                    continue block57;
                }
                case 13: {
                    this.addToken(14);
                }
                case 25: {
                    continue block57;
                }
                case 15: {
                    this.start = this.zzMarkedPos - 2;
                    this.yybegin(2);
                }
                case 26: {
                    continue block57;
                }
                case 19: {
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead + 1, 2);
                }
                case 27: {
                    continue block57;
                }
                case 4: {
                    this.addToken(21);
                }
                case 28: {
                    continue block57;
                }
                case 2: {
                    this.addToken(11);
                }
                case 29: {
                    continue block57;
                }
                case 17: {
                    this.addToken(6);
                }
                case 30: {
                    continue block57;
                }
                case 21: {
                    this.start = this.zzMarkedPos - 4;
                    this.yybegin(1);
                }
                case 31: {
                    continue block57;
                }
                case 7: {
                    this.addToken(22);
                }
                case 32: {
                    continue block57;
                }
                case 1: {
                    this.addToken(20);
                }
                case 33: {
                    continue block57;
                }
                case 18: {
                    this.addToken(8);
                }
                case 34: {
                    continue block57;
                }
                case 5: {
                    this.addToken(38);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 35: {
                    continue block57;
                }
                case 6: {
                    this.addToken(37);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 36: {
                    continue block57;
                }
                case 23: {
                    this.addToken(16);
                }
                case 37: {
                    continue block57;
                }
                case 22: {
                    this.addToken(9);
                }
                case 38: {
                    continue block57;
                }
                case 20: {
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead + 1, 13);
                }
                case 39: {
                    continue block57;
                }
                case 14: {
                    this.addToken(13);
                }
                case 40: {
                    continue block57;
                }
                case 11: {
                    this.addToken(this.start, this.zzStartRead - 1, 13);
                    return this.firstToken;
                }
                case 41: {
                    continue block57;
                }
                case 12: {
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    return this.firstToken;
                }
                case 42: {
                    continue block57;
                }
                case 8: {
                    this.addToken(23);
                }
                case 43: {
                    continue block57;
                }
                case 16: {
                    this.start = this.zzMarkedPos - 2;
                    this.yybegin(3);
                }
                case 44: {
                    continue block57;
                }
                case 9: 
                case 45: {
                    continue block57;
                }
                case 10: {
                    this.addToken(this.start, this.zzStartRead - 1, 2);
                    return this.firstToken;
                }
                case 46: {
                    continue block57;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 204: {
                        continue block57;
                    }
                    case 2: {
                        this.addToken(this.start, this.zzStartRead - 1, 13);
                        return this.firstToken;
                    }
                    case 205: {
                        continue block57;
                    }
                    case 3: {
                        this.addToken(this.start, this.zzStartRead - 1, 1);
                        return this.firstToken;
                    }
                    case 206: {
                        continue block57;
                    }
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 2);
                        return this.firstToken;
                    }
                    case 207: {
                        continue block57;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

