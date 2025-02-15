/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.AbstractJFlexCTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

public class CSSTokenMaker
extends AbstractJFlexCTokenMaker {
    public static final int YYEOF = -1;
    public static final int CSS_C_STYLE_COMMENT = 5;
    public static final int LESS_EOL_COMMENT = 6;
    public static final int YYINITIAL = 0;
    public static final int CSS_STRING = 3;
    public static final int CSS_VALUE = 2;
    public static final int CSS_PROPERTY = 1;
    public static final int CSS_CHAR_LITERAL = 4;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001!\u00014\u0015\u0000\u0001!\u0001+\u00010\u0001\u001f\u0001-\u0001&\u0001(\u00011\u0001$\u00015\u0001\u0005\u0001,\u0001/\u0001\u0004\u0001\u0006\u0001\"\n\u0001\u0001\u0007\u0001 \u0001\u0000\u0001,\u00013\u0001)\u0001\u001d\u0006'\u0014\u0002\u0001*\u0001#\u0001*\u00013\u0001\u0003\u0001\u0000\u0001\u0011\u0001\u001c\u0001\r\u0001\u0010\u0001\u0016\u0001\u0013\u0001\u001b\u0001\f\u0001\u000e\u0001\u0002\u0001\u0018\u0001\u000f\u0001\u0017\u0001\u000b\u0001\t\u0001\u0015\u0001\u0002\u0001\b\u0001\u0012\u0001\n\u0001\u001a\u0001\u0019\u0001\u001e\u0001%\u0001\u0014\u0001\u0002\u0001.\u00013\u00012\u0001,\uff81\u0000";
    private static final char[] ZZ_CMAP = CSSTokenMaker.zzUnpackCMap("\t\u0000\u0001!\u00014\u0015\u0000\u0001!\u0001+\u00010\u0001\u001f\u0001-\u0001&\u0001(\u00011\u0001$\u00015\u0001\u0005\u0001,\u0001/\u0001\u0004\u0001\u0006\u0001\"\n\u0001\u0001\u0007\u0001 \u0001\u0000\u0001,\u00013\u0001)\u0001\u001d\u0006'\u0014\u0002\u0001*\u0001#\u0001*\u00013\u0001\u0003\u0001\u0000\u0001\u0011\u0001\u001c\u0001\r\u0001\u0010\u0001\u0016\u0001\u0013\u0001\u001b\u0001\f\u0001\u000e\u0001\u0002\u0001\u0018\u0001\u000f\u0001\u0017\u0001\u000b\u0001\t\u0001\u0015\u0001\u0002\u0001\b\u0001\u0012\u0001\n\u0001\u001a\u0001\u0019\u0001\u001e\u0001%\u0001\u0014\u0001\u0002\u0001.\u00013\u00012\u0001,\uff81\u0000");
    private static final int[] ZZ_ACTION = CSSTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0002\u0000\u0001\u0001\u0004\u0000\u0001\u0002\u0001\u0003\u0002\u0004\u0001\u0005\u0002\u0002\u0001\u0006\u0001\u0007\u0001\u0002\u0001\b\u0001\t\u0001\u0001\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0002\u0010\u0001\u0011\u0003\u000e\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\u0016\u0002\u0001\u0002\u0015\u0001\u0017\u0001\u0001\u0001\u0018\u0001\u0015\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u001c\u0001\u001d\u0001\u001e\u0001\u001b\u0001\u001f\u0001 \u0005\u001b\u0001!\u0004\u001b\u0001\"\u0002\u0000\u0001\u0003\u0003\u0000\u0001\u0004\r\u0000\n#\u0001\u0003\u0001$\u0001\u0003\u0001%\u0001&\u0002\u0000\u0001'\u0001(\u0001)\u0002\u0000\u0001\u0016\u0003\u0000\u0001\u0016\u0001*\u0001\u0000\u0001\u001c\u0001+\u001b\u0000\t#\r\u0000\u0001\u000f\u000e\u0000\u0001\u000f\f\u0000\t#\u0016\u0000\u0001,\u0002\u0000\u0001-\f\u0000\u0005#\u0001.\u0002#\u0005\u0000\u0001\u0010\u0019\u0000\u0006#\u0011\u0000\u0003#\f\u0000\u0002#\u0007\u0000\u0001#\t\u0000\u0001/\u0003\u0000";
    private static final int[] ZZ_ROWMAP = CSSTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u00006\u0000l\u0000\u00a2\u0000\u00d8\u0000\u010e\u0000\u0144\u0000\u017a\u0000\u01b0\u0000\u01e6\u0000\u021c\u0000\u0252\u0000\u0288\u0000\u02be\u0000\u017a\u0000\u02f4\u0000\u032a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u0360\u0000\u0396\u0000\u03cc\u0000\u017a\u0000\u0402\u0000\u0438\u0000\u046e\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u017a\u0000\u04a4\u0000\u04da\u0000\u0510\u0000\u0402\u0000\u0546\u0000\u017a\u0000\u057c\u0000\u017a\u0000\u05b2\u0000\u017a\u0000\u017a\u0000\u05e8\u0000\u061e\u0000\u017a\u0000\u017a\u0000\u0654\u0000\u017a\u0000\u017a\u0000\u068a\u0000\u06c0\u0000\u06f6\u0000\u072c\u0000\u0762\u0000\u017a\u0000\u0798\u0000\u07ce\u0000\u0804\u0000\u083a\u0000\u017a\u0000\u0870\u0000\u08a6\u0000\u017a\u0000\u08dc\u0000\u0912\u0000\u0948\u0000\u097e\u0000\u09b4\u0000\u09ea\u0000\u0a20\u0000\u0a56\u0000\u0a8c\u0000\u0ac2\u0000\u0af8\u0000\u0b2e\u0000\u0b64\u0000\u0b9a\u0000\u0bd0\u0000\u0c06\u0000\u0c3c\u0000\u0c72\u0000\u0ca8\u0000\u0cde\u0000\u0d14\u0000\u0d4a\u0000\u0d80\u0000\u0db6\u0000\u0dec\u0000\u0e22\u0000\u0e58\u0000\u0e8e\u0000\u0ec4\u0000\u0efa\u0000\u017a\u0000\u017a\u0000\u0f30\u0000\u0f66\u0000\u0f9c\u0000\u017a\u0000\u0fd2\u0000\u1008\u0000\u103e\u0000\u017a\u0000\u1074\u0000\u10aa\u0000\u10e0\u0000\u0546\u0000\u04da\u0000\u1116\u0000\u017a\u0000\u017a\u0000\u114c\u0000\u1182\u0000\u11b8\u0000\u11ee\u0000\u1224\u0000\u125a\u0000\u1290\u0000\u12c6\u0000\u12fc\u0000\u1332\u0000\u1368\u0000\u139e\u0000\u13d4\u0000\u140a\u0000\u1440\u0000\u1476\u0000\u14ac\u0000\u14e2\u0000\u1518\u0000\u154e\u0000\u1584\u0000\u15ba\u0000\u15f0\u0000\u1626\u0000\u165c\u0000\u1692\u0000\u16c8\u0000\u16fe\u0000\u1734\u0000\u176a\u0000\u17a0\u0000\u17d6\u0000\u180c\u0000\u1842\u0000\u1878\u0000\u18ae\u0000\u18e4\u0000\u191a\u0000\u1950\u0000\u1986\u0000\u19bc\u0000\u19f2\u0000\u1a28\u0000\u1a5e\u0000\u1a94\u0000\u1aca\u0000\u1b00\u0000\u1b36\u0000\u1b6c\u0000\u1ba2\u0000\u1bd8\u0000\u1c0e\u0000\u1c44\u0000\u1c7a\u0000\u1cb0\u0000\u1ce6\u0000\u1d1c\u0000\u1d52\u0000\u1d88\u0000\u1dbe\u0000\u1df4\u0000\u1e2a\u0000\u1e60\u0000\u1e96\u0000\u017a\u0000\u1ecc\u0000\u1f02\u0000\u1f38\u0000\u1f6e\u0000\u1fa4\u0000\u1fda\u0000\u2010\u0000\u2046\u0000\u207c\u0000\u20b2\u0000\u20e8\u0000\u211e\u0000\u2154\u0000\u218a\u0000\u21c0\u0000\u21f6\u0000\u222c\u0000\u2262\u0000\u2298\u0000\u22ce\u0000\u2304\u0000\u233a\u0000\u2370\u0000\u23a6\u0000\u23dc\u0000\u2412\u0000\u2448\u0000\u247e\u0000\u24b4\u0000\u24ea\u0000\u2520\u0000\u2556\u0000\u258c\u0000\u25c2\u0000\u25f8\u0000\u262e\u0000\u2664\u0000\u269a\u0000\u26d0\u0000\u2706\u0000\u273c\u0000\u2772\u0000\u27a8\u0000\u27de\u0000\u2814\u0000\u284a\u0000\u2880\u0000\u28b6\u0000\u28ec\u0000\u2922\u0000\u2958\u0000\u298e\u0000\u29c4\u0000\u29fa\u0000\u2a30\u0000\u2a66\u0000\u2a9c\u0000\u2ad2\u0000\u2b08\u0000\u2b3e\u0000\u2b74\u0000\u2baa\u0000\u2be0\u0000\u2c16\u0000\u0c72\u0000\u2c4c\u0000\u2c82\u0000\u2cb8\u0000\u2cee\u0000\u2d24\u0000\u2d5a\u0000\u2d90\u0000\u2dc6\u0000\u2dfc\u0000\u2e32\u0000\u2e68\u0000\u2e9e\u0000\u2ed4\u0000\u2f0a\u0000\u2f40\u0000\u2f76\u0000\u2fac\u0000\u2fe2\u0000\u3018\u0000\u304e\u0000\u3084\u0000\u30ba\u0000\u27de\u0000\u30f0\u0000\u2880\u0000\u3126\u0000\u315c\u0000\u3192\u0000\u31c8\u0000\u31fe\u0000\u3234\u0000\u326a\u0000\u32a0\u0000\u32d6\u0000\u330c\u0000\u3342\u0000\u3378\u0000\u33ae\u0000\u33e4\u0000\u341a\u0000\u3450\u0000\u3486\u0000\u34bc\u0000\u34f2\u0000\u3528\u0000\u355e\u0000\u3594\u0000\u35ca\u0000\u3600\u0000\u3636\u0000\u366c\u0000\u36a2\u0000\u36d8\u0000\u370e\u0000\u3744\u0000\u377a\u0000\u37b0\u0000\u37e6\u0000\u381c\u0000\u3852\u0000\u3888\u0000\u38be\u0000\u38f4\u0000\u392a\u0000\u3960\u0000\u3996\u0000\u39cc\u0000\u3a02\u0000\u3a38\u0000\u3a6e\u0000\u3aa4\u0000\u3ada\u0000\u3b10\u0000\u3b46\u0000\u3b7c\u0000\u3bb2\u0000\u3be8\u0000\u3c1e\u0000\u3c54\u0000\u3c8a\u0000\u3cc0\u0000\u3cf6\u0000\u3d2c\u0000\u3d62\u0000\u3d98\u0000\u3dce\u0000\u3e04\u0000\u3e3a\u0000\u3e70\u0000\u3ea6\u0000\u017a\u0000\u3edc\u0000\u3f12\u0000\u3f48";
    private static final int[] ZZ_TRANS = CSSTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\b\u0001\t\u0002\n\u0001\u000b\u0002\n\u0001\f\u0015\n\u0001\r\u0001\n\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\b\u0001\u000f\u0001\n\u0001\b\u0001\n\u0002\b\u0001\u000f\u0001\b\u0002\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0017\u0001\u0012\u0001\u0018\u0001\u000f\u0002\u0019\u0003\u001a\u0001\u001b\u0001\u001c\u0001\u001d\u0015\u001a\u0001\u001e\u0001\u001a\u0002\u0019\u0001\u0010\u0001\u001f\u0002\u0019\u0001\u001a\u0001\u0019\u0001\u001a\u0001 \u0005\u0019\u0001!\u0003\u0019\u0001\"\u0001\u0019\u0001#\u0001\u0019\u0001$\u0001%\u0002&\u0001'\u0001$\u0001\u0014\u0001$\u0015&\u0001(\u0001&\u0001)\u0001*\u0001\u0010\u0001+\u0001&\u0001,\u0001&\u0001$\u0001&\u0003$\u0001-\u0003$\u0001\u0014\u0001\u0015\u0001\u0016\u0001\"\u0001$\u0001.\u0001/#0\u00011\f0\u00012\u00030\u00013\u00010#4\u00011\r4\u00015\u00024\u00016\u00014\u00057\u00018\u00067\u00019\u00067\u0001:\n7\u0001;\u00157\u0001<\u00017\f=\u0001>\u0006=\u0001?\n=\u0001@\u0015=\u0001A\u0001=7\u0000\u0001\t\u0004\u0000\u0001\t\u0006\u0000\u0001B\u0001C\u0003\u0000\u0001D\u0002\u0000\u0001E\u0001F\u0001G\u000e\u0000\u0001D\u0010\u0000\u0004\n\u0001\u0000\u0001\n\u0001\u0000\u0015\n\u0001\u0000\u0001\n\u0006\u0000\u0001\n\u0001\u0000\u0001\n\u000f\u0000\u0001H\u0003\n\u0001\u0000\u0001\n\u0001\u0000\u0015\n\u0001\u0000\u0001\n\u0006\u0000\u0001\n\u0001\u0000\u0001\n\u0015\u0000\u0001I\u0001J\u0001K\u0001L\u0001M\u0001N\u0001O\u0001\u0000\u0001P\u0001Q\u0001R\u0001\u0000\u0001S\u0002\u0000\u0001T\u0002\u0000\u0001U\u001e\u0000\u0003V\u0003\u0000\u0003V\u0001W\u0001V\u0001X\u0001Y\u0001V\u0001Z\u0002V\u0001[\u0001V\u0001\\\u0001V\u0001]\u0001^\u0001_\u0003V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0001`\u0005a\u0001\u0000\u0005a\u0001b\u0002a\u0002b\u0001a\u0001b\u0002a\u0001b\u0005a\u0001b\u0001\u0000\u0001a\u0006\u0000\u0001a\u0001\u0000\u0001b/\u0000\u0001\u0010\u0019\u0000\u0001c\u001c\u0000\u0001d\u0014\u0000\u0004\u001a\u0001\u0000\u0001\u001c\u0001e\u0015\u001a\u0001\u0000\u0001\u001a\u0006\u0000\u0001\u001a\u0001\u0000\u0001\u001a\u000f\u0000\u0001\u001c\u0003\u001a\u0001\u0000\u0001\u001c\u0001f\u0015\u001a\u0001\u0000\u0001\u001a\u0006\u0000\u0001\u001a\u0001\u0000\u0001\u001a\u000f\u0000\u0004\u001c\u0001\u0000\u0001\u001c\u0001f\u0015\u001c\u0001\u0000\u0001\u001c\u0006\u0000\u0001\u001c\u0001\u0000\u0001\u001c\u0010\u0000\u0003g\u0003\u0000\u0015g\u0001\u0000\u0001g\u0006\u0000\u0001g\u0001\u0000\u0001g\u0013\u0000\u0001c\u001c\u0000\u0001h\u0015\u0000\u0005i\u0001\u0000\u0015i\u0001\u0000\u0001i\u0006\u0000\u0001i\u0001\u0000\u0001i\u000f\u0000\u0001%\u0004\u0000\u0001%\u0006\u0000\u0001j\u0001k\u0003\u0000\u0001l\u0002\u0000\u0001m\u0001n\u0001o\u000e\u0000\u0001l\u0011\u0000\u0003&\u0003\u0000\u0015&\u0001\u0000\u0001&\u0003\u0000\u0002&\u0001,\u0001&\u0001\u0000\u0001&\u000f\u0000\u0001%\u0003&\u0003\u0000\u0015&\u0001\u0000\u0001&\u0003\u0000\u0002&\u0001,\u0001&\u0001\u0000\u0001&\u000f\u0000\u0001p\u000b\u0000\u0001p\u0002\u0000\u0002p\u0001\u0000\u0001p\u0002\u0000\u0001p\u0005\u0000\u0001p\n\u0000\u0001p\u0010\u0000\u0003&\u0001c\u0002\u0000\u0015&\u0001\u0000\u0001&\u0003\u0000\u0001q\u0001&\u0001,\u0001&\u0001\u0000\u0001&\u001c\u0000\u0001r'\u0000#0\u0001\u0000\f0\u0001\u0000\u00030\u0001\u0000\u000104s\u0001\u0000\u0001s#4\u0001\u0000\r4\u0001\u0000\u00024\u0001\u0000\u00014\u00057\u0001\u0000\u00067\u0001\u0000\u00067\u0001\u0000\n7\u0001\u0000\u00157\u0001\u0000\u00017\"\u0000\u0001t\u001d\u0000\u0001u5\u0000\u0001v\u0003\u0000\u0001wE\u0000\u0001x\u0017\u0000\f=\u0001\u0000\u0006=\u0001\u0000\n=\u0001\u0000\u0015=\u0001\u0000\u0001=\n\u0000\u0001y5\u0000\u0001z\u0003\u0000\u0001{E\u0000\u0001|.\u0000\u0001D)\u0000\u0001D4\u0000\u0001D\u0002\u0000\u0001D\u0017\u0000\u0001D'\u0000\u0001D\r\u0000\u0001D\"\u0000\u0001D\u0004\u0000\u0001D\u001f\u0000\u0001H\u0003\n\u0001\u0000\u0001H\u0001\u0000\u0015\n\u0001\u0000\u0001\n\u0006\u0000\u0001\n\u0001D\u0001\n\u001f\u0000\u0001}\u0001\u0000\u0001~\b\u0000\u0001\u007f\"\u0000\u0001\u00807\u0000\u0001\u0081;\u0000\u0001\u0082-\u0000\u0001\u0083\u0001\u00844\u0000\u0001\u00858\u0000\u0001\u00867\u0000\u0001\u0087\u0002\u0000\u0001\u00882\u0000\u0001\u00894\u0000\u0001\u008a1\u0000\u0001\u008b\u0004\u0000\u0001\u008c2\u0000\u0001\u008d\u000b\u0000\u0001\u008e,\u0000\u0001\u008f(\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0015V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\tV\u0001\u0090\u000bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0004V\u0001\u0091\u0010V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000fV\u0001\u0092\u0005V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0001V\u0001\u0093\u0013V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0001V\u0001\u0094\u0013V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\tV\u0001\u0095\u000bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u0096\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u0097\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0006V\u0001\u0098\u000eV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0001`\u000b\u0000\u0001`\u0002\u0000\u0002`\u0001\u0000\u0001`\u0002\u0000\u0001`\u0005\u0000\u0001`\n\u0000\u0001`\u000f\u0000\u0004a\u0001\u0000\u0001a\u0001\u0000\u0015a\u0001\u0000\u0001a\u0006\u0000\u0001a\u0001\u0000\u0001a\u000f\u0000\u0001b\u0003a\u0001\u0000\u0001a\u0001\u0000\u0005a\u0001b\u0002a\u0002b\u0001a\u0001b\u0002a\u0001b\u0005a\u0001b\u0001\u0000\u0001a\u0006\u0000\u0001a\u0001\u0000\u0001b\u0015\u0000\u0001\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001\u0000\u0001\u00a0\u0001\u00a1\u0001\u00a2\u0001\u0000\u0001\u00a3\u0002\u0000\u0001\u00a4\u0002\u0000\u0001\u00a5\u0004\u0000\u0001\u00a6\u001e\u0000\u0001\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001\u0000\u0001\u00a0\u0001\u00a1\u0001\u00a2\u0001\u0000\u0001\u00a3\u0002\u0000\u0001\u00a4\u0002\u0000\u0001\u00a5\u001d\u0000\u0004g\u0001\u0000\u0001g\u0001\u0000\u0015g\u0001\u0000\u0001g\u0006\u0000\u0001g\u0001\u0000\u0001g\u000f\u0000\u0004i\u0001\u0000\u0001i\u0001\u0000\u0015i\u0001\u0000\u0001i\u0006\u0000\u0001i\u0001\u0000\u0001i%\u0000\u0001l)\u0000\u0001l4\u0000\u0001l\u0002\u0000\u0001l\u0017\u0000\u0001l'\u0000\u0001l\r\u0000\u0001l\"\u0000\u0001l\u0004\u0000\u0001l5\u0000\u0001\u00a7(\u0000\u0001\u00a8@\u0000\u0001\u00a9/\u0000\u0001\u00aaD\u0000\u0001\u00ab!\u0000\u0001\u00ac@\u0000\u0001\u00ad/\u0000\u0001\u00aeD\u0000\u0001\u00af*\u0000\u0001\u00b00\u0000\u0001\u00b1=\u0000\u0001\u00b2(\u0000\u0001\u0083;\u0000\u0001\u00b3.\u0000\u0001\u00b47\u0000\u0001\u00b57\u0000\u0001\u00b6B\u0000\u0001\u00b72\u0000\u0001\u00b8*\u0000\u0001\u00b95\u0000\u0001\u00ba\u0006\u0000\u0001\u00bb5\u0000\u0001\u008d-\u0000\u0001\u00bc8\u0000\u0001\u00bd0\u0000\u0001\u00be>\u0000\u0001\u00bf9\u0000\u0001\u00c02\u0000\u0001\u00c1$\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000fV\u0001\u00c2\u0005V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\tV\u0001\u00c3\u000bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\rV\u0001\u00c4\u0007V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0005V\u0001\u00c5\u000fV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0003V\u0001\u00c6\u0011V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0013V\u0001\u00c7\u0001V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\bV\u0001\u00c8\fV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\fV\u0001\u00c9\bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u00ca\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u001f\u0000\u0001\u00cb\u0001\u0000\u0001\u00cc\b\u0000\u0001\u00cd\"\u0000\u0001\u00ce7\u0000\u0001\u00cf;\u0000\u0001\u00d0-\u0000\u0001\u00d1\u0001\u00d24\u0000\u0001\u00d38\u0000\u0001\u00d47\u0000\u0001\u00d5\u0002\u0000\u0001\u00d62\u0000\u0001\u00d74\u0000\u0001\u00d81\u0000\u0001\u00d9\u0004\u0000\u0001\u00da2\u0000\u0001\u00db\u000b\u0000\u0001\u00dc,\u0000\u0001\u00ddE\u0000\u0001\u00a6,\u0000\u0001\u00de5\u0000\u0001\u00df'\u0000\u0001\u00e0D\u0000\u0001\u00a9%\u0000\u0001\u00e1D\u0000\u0001\u00e2'\u0000\u0001\u00e3D\u0000\u0001\u00ad%\u0000\u0001\u00e49\u0000\u0001\u00b73\u0000\u0001\u00e5@\u0000\u0001\u00e66\u0000\u0001\u00e7<\u0000\u0001\u00e8\u001e\u0000\u0001\u00e9G\u0000\u0001\u00ea,\u0000\u0001\u00eb@\u0000\u0001\u00b58\u0000\u0001\u00b5$\u0000\u0001\u00e79\u0000\u0001\u00ecA\u0000\u0001\u00ed-\u0000\u0001\u00bb?\u0000\u0001\u00ee#\u0000\u0001\u00ef9\u0000\u0001\u00f0(\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u00f1\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0001\u00f2\u0014V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0001V\u0001\u00f3\u0013V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0012V\u0001\u00f4\u0002V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0002V\u0001\u00f5\u0012V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u00f6\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0006V\u0001\u00f7\u000eV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000bV\u0001\u00f8\tV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0015V\u0001\u0000\u0001\u0092\u0006\u0000\u0001V\u0001\u0000\u0001V!\u0000\u0001\u00f90\u0000\u0001\u00fa=\u0000\u0001\u00fb(\u0000\u0001\u00d1;\u0000\u0001\u00fc.\u0000\u0001\u00fd7\u0000\u0001\u00fe7\u0000\u0001\u00ffB\u0000\u0001\u01002\u0000\u0001\u0101*\u0000\u0001\u01025\u0000\u0001\u0103\u0006\u0000\u0001\u01045\u0000\u0001\u00db-\u0000\u0001\u01058\u0000\u0001\u01060\u0000\u0001\u0107>\u0000\u0001\u01089\u0000\u0001\u01092\u0000\u0001\u010a,\u0000\u0001\u010b3\u0000\u0001\u00e0\n\u0000\u0001\u00a9E\u0000\u0001\u010c\u0014\u0000\u0002\u00e1\u0005\u010d\u0015\u00e1\u0001\u010d\u0001\u00e1\u0002\u010d\u0001\u0000\u0001\u00e1\u0001\u0000\u0001\u010d\u0001\u00e1\u0001\u010d\u0001\u00e1\u0005\u010d\u0001\u00e1\u0001\u0000\u0001\u010d\u0001\u0000\u0001\u010d\u0003\u0000\u0001\u010d\u0007\u0000\u0001\u00e3\n\u0000\u0001\u00adE\u0000\u0001\u010e\u0014\u0000\u0002\u00e4\u0005\u010f\u0015\u00e4\u0001\u010f\u0001\u00e4\u0002\u010f\u0001\u0000\u0001\u00e4\u0001\u0000\u0001\u010f\u0001\u00e4\u0001\u010f\u0001\u00e4\u0005\u010f\u0001\u00e4\u0001\u0000\u0001\u010f\u0001\u0000\u0001\u010f\u0003\u0000\u0001\u010f\u0012\u0000\u0001\u0110,\u0000\u0001\u01110\u0000\u0001\u0112G\u0000\u0001\u0083(\u0000\u0001\u0113\u0003\u0000\u0001\u0114\u0001\u0000\u0001\u0115.\u0000\u0001\u00b5E\u0000\u0001\u01166\u0000\u0001\u0117.\u0000\u0001\u00b52\u0000\u0001\u0116:\u0000\u0001\u00b5+\u0000\u0001\u0116,\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\nV\u0001\u0118\nV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\nV\u0001\u0119\nV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0001\u011a\u0014V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000fV\u0001\u011b\u0005V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0003V\u0001\u011c\u0001\u0000\u0001V\u0001\u0000\u0015V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\tV\u0001\u00f6\u000bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0001\u011d\u0014V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u0018\u0000\u0001\u01003\u0000\u0001\u011e@\u0000\u0001\u011f6\u0000\u0001\u0120<\u0000\u0001\u0121!\u0000\u0001f2\u0000\u0001\u0122G\u0000\u0001\u0123,\u0000\u0001\u0124@\u0000\u0001\u00fe8\u0000\u0001\u00fe$\u0000\u0001\u01209\u0000\u0001\u0125A\u0000\u0001\u0126-\u0000\u0001\u0104?\u0000\u0001\u0127#\u0000\u0001\u01289\u0000\u0001\u0129/\u0000\u0001\u012aO\u0000\u0001\u00e15\u0000\u0001\u00e4\u001d\u0000\u0001\u012b3\u0000\u0001\u01176\u0000\u0001\u0113\u0003\u0000\u0001\u0114;\u0000\u0001\u012c.\u0000\u0001\u012d:\u0000\u0001\u00be:\u0000\u0001\u012e5\u0000\u0001\u00b5 \u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\rV\u0001\u012f\u0007V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u011a\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0002V\u0001\u00f6\u0012V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u0130\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000bV\u0001\u012f\tV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\tV\u0001\u0131\u000bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V \u0000\u0001\u0132,\u0000\u0001\u01330\u0000\u0001\u0134G\u0000\u0001\u00d1(\u0000\u0001\u0135\u0003\u0000\u0001\u0136\u0001\u0000\u0001\u0137.\u0000\u0001\u00feE\u0000\u0001\u01386\u0000\u0001\u0139.\u0000\u0001\u00fe2\u0000\u0001\u0138:\u0000\u0001\u00fe+\u0000\u0001\u01385\u0000\u0001\u013a/\u0000\u0001\u013b5\u0000\u0001\u013c?\u0000\u0001\u013d7\u0000\u0001\u00b5&\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\tV\u0001\u013e\u000bV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0003V\u0001\u011a\u0011V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000fV\u0001\u013f\u0005V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u0018\u0000\u0001\u01403\u0000\u0001\u01396\u0000\u0001\u0135\u0003\u0000\u0001\u0136;\u0000\u0001\u0141.\u0000\u0001\u0142:\u0000\u0001\u0107:\u0000\u0001\u01435\u0000\u0001\u00fe0\u0000\u0001\u01443\u0000\u0001\u01450\u0000\u0001\u0146:\u0000\u0001\u012e'\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u0005V\u0001\u00c7\u000fV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u000f\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\u000eV\u0001\u0147\u0006V\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u0012\u0000\u0001\u01485\u0000\u0001\u0149?\u0000\u0001\u014a7\u0000\u0001\u00fe0\u0000\u0001\u014b8\u0000\u0001\u014c\u0007\u0000\u0001\u014d3\u0000\u0001\u014e\"\u0000\u0004V\u0001\u0000\u0001V\u0001\u0000\nV\u0001\u00f6\nV\u0001\u0000\u0001V\u0006\u0000\u0001V\u0001\u0000\u0001V\u001d\u0000\u0001\u014f0\u0000\u0001\u0150:\u0000\u0001\u01430\u0000\u0001\u01516\u0000\u0001\u01174\u0000\u0001\u00b0@\u0000\u0001\u0117.\u0000\u0001\u0152\u0007\u0000\u0001\u01533\u0000\u0001\u0154,\u0000\u0001\u01394\u0000\u0001\u00f9@\u0000\u0001\u0139 \u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = CSSTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0002\u0000\u0001\u0001\u0004\u0000\u0001\t\u0006\u0001\u0001\t\u0002\u0001\b\t\u0003\u0001\u0001\t\u0003\u0001\u0004\t\u0005\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0002\t\u0002\u0001\u0002\t\u0001\u0001\u0002\t\u0005\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0000\u0001\t\u0003\u0000\u0001\u0001\r\u0000\r\u0001\u0002\t\u0002\u0000\u0001\u0001\u0001\t\u0001\u0001\u0002\u0000\u0001\t\u0003\u0000\u0002\u0001\u0001\u0000\u0002\t\u001b\u0000\t\u0001\r\u0000\u0001\u0001\u000e\u0000\u0001\t\f\u0000\t\u0001\u0016\u0000\u0001\u0001\u0002\u0000\u0001\u0001\f\u0000\b\u0001\u0005\u0000\u0001\u0001\u0019\u0000\u0006\u0001\u0011\u0000\u0003\u0001\f\u0000\u0002\u0001\u0007\u0000\u0001\u0001\t\u0000\u0001\t\u0003\u0000";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;
    public static final int INTERNAL_CSS_PROPERTY = -1;
    public static final int INTERNAL_CSS_VALUE = -2;
    public static final int INTERNAL_CSS_STRING = -2048;
    public static final int INTERNAL_CSS_CHAR = -4096;
    public static final int INTERNAL_CSS_MLC = -6144;
    private int cssPrevState;
    private boolean highlightingLess;

    private static int[] zzUnpackAction() {
        int[] result = new int[340];
        int offset = 0;
        offset = CSSTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[340];
        int offset = 0;
        offset = CSSTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[16254];
        int offset = 0;
        offset = CSSTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[340];
        int offset = 0;
        offset = CSSTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public CSSTokenMaker() {
    }

    private void addEndToken(int tokenType) {
        this.addToken(this.zzMarkedPos, this.zzMarkedPos, tokenType);
    }

    private void addHyperlinkToken(int start, int end, int tokenType) {
        int so = start + this.offsetShift;
        this.addToken(this.zzBuffer, start, end, tokenType, so, true);
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
    public int getClosestStandardTokenTypeForInternalType(int type) {
        switch (type) {
            case -4096: 
            case -2048: {
                return 13;
            }
            case -6144: {
                return 2;
            }
        }
        return type;
    }

    public boolean getCurlyBracesDenoteCodeBlocks() {
        return true;
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"/*", "*/"};
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 6;
    }

    @Override
    public boolean getShouldIndentNextLineAfter(Token t) {
        if (t != null && t.length() == 1) {
            char ch = t.charAt(0);
            return ch == '{' || ch == '(';
        }
        return false;
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        this.cssPrevState = 0;
        int state = 0;
        switch (initialTokenType) {
            case 13: {
                state = 3;
                break;
            }
            case 14: {
                state = 4;
                break;
            }
            case 2: {
                state = 5;
                break;
            }
            case -1: {
                state = 1;
                break;
            }
            case -2: {
                state = 2;
                break;
            }
            default: {
                if (initialTokenType < -1024) {
                    int main = -(-initialTokenType & 0xFFFFFF00);
                    switch (main) {
                        default: {
                            state = 3;
                            break;
                        }
                        case -4096: {
                            state = 4;
                            break;
                        }
                        case -6144: {
                            state = 5;
                        }
                    }
                    this.cssPrevState = -initialTokenType & 0xFF;
                    break;
                }
                state = 0;
            }
        }
        this.start = text.offset;
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

    @Override
    public boolean isIdentifierChar(int languageIndex, char ch) {
        return Character.isLetterOrDigit(ch) || ch == '-' || ch == '.' || ch == '_';
    }

    public void setHighlightingLess(boolean highlightingLess) {
        this.highlightingLess = highlightingLess;
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

    public CSSTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public CSSTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 134) {
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
        block118: while (true) {
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
                case 1: {
                    this.addToken(20);
                }
                case 48: {
                    continue block118;
                }
                case 2: {
                    this.addToken(20);
                }
                case 49: {
                    continue block118;
                }
                case 25: {
                    this.addEndToken(-2);
                    return this.firstToken;
                }
                case 50: {
                    continue block118;
                }
                case 16: {
                    this.addToken(this.highlightingLess ? 6 : 20);
                }
                case 51: {
                    continue block118;
                }
                case 42: {
                    int temp;
                    if (this.highlightingLess) {
                        this.cssPrevState = this.zzLexicalState;
                        this.start = this.zzMarkedPos - 2;
                        this.yybegin(6);
                    } else {
                        temp = this.zzStartRead + 2;
                        this.addToken(this.zzStartRead, this.zzStartRead + 1, 20);
                        this.zzStartRead = temp;
                    }
                }
                case 52: {
                    continue block118;
                }
                case 9: {
                    this.addToken(22);
                    this.yybegin(1);
                }
                case 53: {
                    continue block118;
                }
                case 29: {
                    this.addToken(this.start, this.zzStartRead, 13);
                    this.yybegin(this.cssPrevState);
                }
                case 54: {
                    continue block118;
                }
                case 32: {
                    this.addToken(this.start, this.zzStartRead - 1, 14);
                    this.addEndToken(-4096 - this.cssPrevState);
                    return this.firstToken;
                }
                case 55: {
                    continue block118;
                }
                case 37: {
                    this.start = this.zzMarkedPos - 2;
                    this.cssPrevState = this.zzLexicalState;
                    this.yybegin(5);
                }
                case 56: {
                    continue block118;
                }
                case 34: {
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    switch (this.cssPrevState) {
                        case 1: {
                            this.addEndToken(-1);
                            break;
                        }
                        case 2: {
                            this.addEndToken(-2);
                            break;
                        }
                        default: {
                            this.addNullToken();
                        }
                    }
                    return this.firstToken;
                }
                case 57: {
                    continue block118;
                }
                case 31: {
                    this.addToken(this.start, this.zzStartRead, 14);
                    this.yybegin(this.cssPrevState);
                }
                case 58: {
                    continue block118;
                }
                case 43: {
                    this.addToken(this.start, this.zzStartRead + 1, 2);
                    this.yybegin(this.cssPrevState);
                }
                case 59: {
                    continue block118;
                }
                case 12: {
                    this.addToken(this.highlightingLess ? 22 : 20);
                }
                case 60: {
                    continue block118;
                }
                case 10: {
                    this.start = this.zzMarkedPos - 1;
                    this.cssPrevState = this.zzLexicalState;
                    this.yybegin(3);
                }
                case 61: {
                    continue block118;
                }
                case 17: {
                    this.addToken(23);
                    this.yybegin(2);
                }
                case 62: {
                    continue block118;
                }
                case 5: {
                    this.addToken(16);
                }
                case 63: {
                    continue block118;
                }
                case 23: {
                    this.addToken(23);
                    this.yybegin(1);
                }
                case 64: {
                    continue block118;
                }
                case 28: 
                case 65: {
                    continue block118;
                }
                case 46: {
                    this.addToken(18);
                }
                case 66: {
                    continue block118;
                }
                case 36: {
                    this.addToken(this.highlightingLess ? 19 : 17);
                }
                case 67: {
                    continue block118;
                }
                case 3: {
                    this.addToken(this.highlightingLess ? 10 : 20);
                }
                case 68: {
                    continue block118;
                }
                case 20: {
                    this.addEndToken(-1);
                    return this.firstToken;
                }
                case 69: {
                    continue block118;
                }
                case 7: {
                    this.addToken(21);
                }
                case 70: {
                    continue block118;
                }
                case 19: {
                    this.addToken(22);
                    this.yybegin(0);
                }
                case 71: {
                    continue block118;
                }
                case 47: {
                    this.addToken(24);
                }
                case 72: {
                    continue block118;
                }
                case 4: {
                    this.addToken(16);
                }
                case 73: {
                    continue block118;
                }
                case 35: {
                    this.addToken(this.highlightingLess ? 17 : 18);
                }
                case 74: {
                    continue block118;
                }
                case 24: {
                    int temp = this.zzMarkedPos - 2;
                    this.addToken(this.zzStartRead, temp, 8);
                    this.addToken(this.zzMarkedPos - 1, this.zzMarkedPos - 1, 22);
                    this.zzStartRead = this.zzCurrentPos = this.zzMarkedPos;
                }
                case 75: {
                    continue block118;
                }
                case 18: {
                    this.addToken(22);
                }
                case 76: {
                    continue block118;
                }
                case 22: {
                    this.addToken(10);
                }
                case 77: {
                    continue block118;
                }
                case 30: {
                    this.addToken(this.start, this.zzStartRead - 1, 13);
                    this.addEndToken(-2048 - this.cssPrevState);
                    return this.firstToken;
                }
                case 78: {
                    continue block118;
                }
                case 41: {
                    if (this.highlightingLess) {
                        this.addToken(6);
                    } else {
                        this.addToken(20);
                    }
                }
                case 79: {
                    continue block118;
                }
                case 33: {
                    this.addToken(this.start, this.zzStartRead - 1, 2);
                    this.addEndToken(-6144 - this.cssPrevState);
                    return this.firstToken;
                }
                case 80: {
                    continue block118;
                }
                case 26: {
                    this.addToken(22);
                }
                case 81: {
                    continue block118;
                }
                case 40: {
                    int temp;
                    if (this.highlightingLess) {
                        this.cssPrevState = this.zzLexicalState;
                        this.start = this.zzMarkedPos - 2;
                        this.yybegin(6);
                    } else {
                        temp = this.zzStartRead + 2;
                        this.addToken(this.zzStartRead, this.zzStartRead + 1, 20);
                        this.zzStartRead = temp;
                    }
                }
                case 82: {
                    continue block118;
                }
                case 39: {
                    this.addToken(this.highlightingLess ? 17 : 20);
                }
                case 83: {
                    continue block118;
                }
                case 14: {
                    this.addToken(20);
                }
                case 84: {
                    continue block118;
                }
                case 15: {
                    this.addToken(6);
                }
                case 85: {
                    continue block118;
                }
                case 44: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 2);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 2);
                    this.start = this.zzMarkedPos;
                }
                case 86: {
                    continue block118;
                }
                case 11: {
                    this.start = this.zzMarkedPos - 1;
                    this.cssPrevState = this.zzLexicalState;
                    this.yybegin(4);
                }
                case 87: {
                    continue block118;
                }
                case 6: {
                    this.addToken(22);
                }
                case 88: {
                    continue block118;
                }
                case 13: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 89: {
                    continue block118;
                }
                case 38: {
                    int temp;
                    if (this.highlightingLess) {
                        this.start = this.zzMarkedPos - 2;
                        this.yybegin(6);
                    } else {
                        temp = this.zzStartRead + 2;
                        this.addToken(this.zzStartRead, this.zzStartRead + 1, 20);
                        this.zzStartRead = temp;
                    }
                }
                case 90: {
                    continue block118;
                }
                case 8: {
                    this.addToken(23);
                }
                case 91: {
                    continue block118;
                }
                case 21: {
                    this.addToken(20);
                }
                case 92: {
                    continue block118;
                }
                case 45: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 1);
                    this.start = this.zzMarkedPos;
                }
                case 93: {
                    continue block118;
                }
                case 27: 
                case 94: {
                    continue block118;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 5: {
                        this.addToken(this.start, this.zzStartRead - 1, 2);
                        this.addEndToken(-6144 - this.cssPrevState);
                        return this.firstToken;
                    }
                    case 341: {
                        continue block118;
                    }
                    case 6: {
                        this.addToken(this.start, this.zzStartRead - 1, 1);
                        switch (this.cssPrevState) {
                            case 1: {
                                this.addEndToken(-1);
                                break;
                            }
                            case 2: {
                                this.addEndToken(-2);
                                break;
                            }
                            default: {
                                this.addNullToken();
                            }
                        }
                        return this.firstToken;
                    }
                    case 342: {
                        continue block118;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 343: {
                        continue block118;
                    }
                    case 3: {
                        this.addToken(this.start, this.zzStartRead - 1, 13);
                        this.addEndToken(-2048 - this.cssPrevState);
                        return this.firstToken;
                    }
                    case 344: {
                        continue block118;
                    }
                    case 2: {
                        this.addEndToken(-2);
                        return this.firstToken;
                    }
                    case 345: {
                        continue block118;
                    }
                    case 1: {
                        this.addEndToken(-1);
                        return this.firstToken;
                    }
                    case 346: {
                        continue block118;
                    }
                    case 4: {
                        this.addToken(this.start, this.zzStartRead - 1, 14);
                        this.addEndToken(-4096 - this.cssPrevState);
                        return this.firstToken;
                    }
                    case 347: {
                        continue block118;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

