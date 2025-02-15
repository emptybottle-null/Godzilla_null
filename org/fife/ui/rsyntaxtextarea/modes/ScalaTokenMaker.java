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

public class ScalaTokenMaker
extends AbstractJFlexCTokenMaker {
    public static final int YYEOF = -1;
    public static final int EOL_COMMENT = 3;
    public static final int MULTILINE_STRING_DOUBLE = 1;
    public static final int YYINITIAL = 0;
    public static final int MLC = 2;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0019\u0001\u0014\u0001\u0000\u0001\u0019\u0013\u0000\u0001\u0019\u0001\u001b\u0001\u0015\u0001\u001b\u0001\u0001\u0001\u001b\u0001\u001b\u0001\u0012\u0002\u0004\u0001\u0018\u0001\u000e\u0001\u001b\u0001\u000e\u0001\u0011\u0001\u0017\u0001\n\t\u0003\u0001\"\u0001\u001b\u0001\u0000\u0001\u001b\u0001\u0000\u0002\u001b\u0003\b\u0001\u000f\u0001\f\u0001\u000f\u0005\u0001\u0001\u0006\u0006\u0001\u0001*\u0007\u0001\u0001\u0004\u0001\u0013\u0001\u0004\u0001\u0000\u0001\u0005\u0001\u0016\u0001$\u0001%\u0001\t\u0001\u0010\u0001\r\u0001 \u00011\u0001\u001c\u0001!\u0001.\u00010\u0001\u0007\u0001+\u0001(\u0001'\u0001\u001e\u0001\u0002\u0001&\u0001\u001f\u0001\u001d\u0001-\u0001/\u0001#\u0001\u000b\u0001)\u0001,\u0001\u001a\u0001\u0000\u0001\u001a\u0001\u001b\uff81\u0000";
    private static final char[] ZZ_CMAP = ScalaTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0019\u0001\u0014\u0001\u0000\u0001\u0019\u0013\u0000\u0001\u0019\u0001\u001b\u0001\u0015\u0001\u001b\u0001\u0001\u0001\u001b\u0001\u001b\u0001\u0012\u0002\u0004\u0001\u0018\u0001\u000e\u0001\u001b\u0001\u000e\u0001\u0011\u0001\u0017\u0001\n\t\u0003\u0001\"\u0001\u001b\u0001\u0000\u0001\u001b\u0001\u0000\u0002\u001b\u0003\b\u0001\u000f\u0001\f\u0001\u000f\u0005\u0001\u0001\u0006\u0006\u0001\u0001*\u0007\u0001\u0001\u0004\u0001\u0013\u0001\u0004\u0001\u0000\u0001\u0005\u0001\u0016\u0001$\u0001%\u0001\t\u0001\u0010\u0001\r\u0001 \u00011\u0001\u001c\u0001!\u0001.\u00010\u0001\u0007\u0001+\u0001(\u0001'\u0001\u001e\u0001\u0002\u0001&\u0001\u001f\u0001\u001d\u0001-\u0001/\u0001#\u0001\u000b\u0001)\u0001,\u0001\u001a\u0001\u0000\u0001\u001a\u0001\u001b\uff81\u0000");
    private static final int[] ZZ_ACTION = ScalaTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0000\u0001\u0001\u0002\u0000\u0002\u0002\u0001\u0003\u0001\u0004\u0002\u0002\u0001\u0003\u0003\u0002\u0001\u0005\u0001\u0006\u0001\u0007\u0002\u0002\u0001\b\r\u0002\u0001\u0001\u0001\t\u0001\n\u0002\u0001\u0001\u000b\u0005\u0001\u0001\f\u0003\u0001\u0001\u0002\u0001\u0003\u0001\u0000\u0002\r\u0003\u0002\u0001\u0000\u0003\u0002\u0001\u000e\u0001\u000f\u0001\u0005\u0002\u0007\u0001\u0010\u0001\u0007\u0001\u0011\u0001\u0012\u0016\u0002\u0001\t\u0001\u0000\u0001\u0013\b\u0000\u0001\u0002\u0001\r\u0001\u0000\u0004\u0002\u0001\u0014\u0001\u0002\u0001\u0010\u0001\u0015\u0001\u0016\t\u0002\u0001\u000e\n\u0002\u0001\u0017\b\u0000\u0011\u0002\u0002\u0000\u0001\u0018\u0002\u0000\u0001\u0019\u0004\u0002\u0001\u000e\u0006\u0002\u0004\u0000\u0003\u0002";
    private static final int[] ZZ_ROWMAP = ScalaTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u00002\u0000d\u0000\u0096\u0000\u00c8\u0000\u00fa\u0000\u012c\u0000\u00c8\u0000\u015e\u0000\u0190\u0000\u01c2\u0000\u01f4\u0000\u0226\u0000\u0258\u0000\u028a\u0000\u00c8\u0000\u02bc\u0000\u02ee\u0000\u0320\u0000\u0352\u0000\u0384\u0000\u03b6\u0000\u03e8\u0000\u041a\u0000\u044c\u0000\u047e\u0000\u04b0\u0000\u04e2\u0000\u0514\u0000\u0546\u0000\u0578\u0000\u05aa\u0000\u05dc\u0000\u060e\u0000\u0640\u0000\u00c8\u0000\u0672\u0000\u06a4\u0000\u00c8\u0000\u06d6\u0000\u0708\u0000\u073a\u0000\u076c\u0000\u079e\u0000\u00c8\u0000\u07d0\u0000\u0802\u0000\u0834\u0000\u0866\u0000\u00c8\u0000\u0898\u0000\u00c8\u0000\u08ca\u0000\u08fc\u0000\u092e\u0000\u0960\u0000\u0992\u0000\u09c4\u0000\u09f6\u0000\u0a28\u0000\u00fa\u0000\u00c8\u0000\u0a5a\u0000\u0a8c\u0000\u0abe\u0000\u0af0\u0000\u0b22\u0000\u00c8\u0000\u00c8\u0000\u0b54\u0000\u0b86\u0000\u0bb8\u0000\u0bea\u0000\u0c1c\u0000\u0c4e\u0000\u0c80\u0000\u0cb2\u0000\u0ce4\u0000\u0d16\u0000\u0d48\u0000\u0d7a\u0000\u0dac\u0000\u0dde\u0000\u0e10\u0000\u0e42\u0000\u0e74\u0000\u0ea6\u0000\u0ed8\u0000\u0f0a\u0000\u0f3c\u0000\u0f6e\u0000\u00c8\u0000\u0fa0\u0000\u00c8\u0000\u0fd2\u0000\u1004\u0000\u1036\u0000\u1068\u0000\u109a\u0000\u10cc\u0000\u10fe\u0000\u1130\u0000\u1162\u0000\u1194\u0000\u11c6\u0000\u11f8\u0000\u122a\u0000\u125c\u0000\u128e\u0000\u0992\u0000\u12c0\u0000\u00c8\u0000\u00c8\u0000\u00c8\u0000\u12f2\u0000\u1324\u0000\u1356\u0000\u1388\u0000\u13ba\u0000\u13ec\u0000\u141e\u0000\u1450\u0000\u1482\u0000\u14b4\u0000\u14e6\u0000\u1518\u0000\u154a\u0000\u157c\u0000\u15ae\u0000\u15e0\u0000\u1612\u0000\u1644\u0000\u1676\u0000\u16a8\u0000\u00c8\u0000\u16da\u0000\u170c\u0000\u173e\u0000\u1770\u0000\u17a2\u0000\u17d4\u0000\u1806\u0000\u1838\u0000\u186a\u0000\u189c\u0000\u18ce\u0000\u1900\u0000\u1932\u0000\u1964\u0000\u1996\u0000\u19c8\u0000\u19fa\u0000\u1a2c\u0000\u1a5e\u0000\u1a90\u0000\u1ac2\u0000\u1af4\u0000\u1b26\u0000\u1b58\u0000\u1b8a\u0000\u1bbc\u0000\u1bee\u0000\u1c20\u0000\u1c52\u0000\u1c84\u0000\u1cb6\u0000\u1ce8\u0000\u1d1a\u0000\u1d4c\u0000\u1d7e\u0000\u1db0\u0000\u1de2\u0000\u1e14\u0000\u1e46\u0000\u1e78\u0000\u1eaa\u0000\u1edc\u0000\u1f0e\u0000\u1c20\u0000\u1f40\u0000\u1cb6\u0000\u1f72\u0000\u1fa4\u0000\u1fd6";
    private static final int[] ZZ_TRANS = ScalaTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0005\u0002\u0006\u0001\u0007\u0001\b\u0002\u0006\u0001\t\u0001\u0006\u0001\n\u0001\u000b\u0002\u0006\u0001\f\u0001\u0005\u0001\u0006\u0001\r\u0001\u000e\u0001\u000f\u0001\u0005\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0005\u0001\u0014\u0001\b\u0001\u0005\u0001\u0006\u0001\u0015\u0001\u0016\u0001\u0017\u0001\u0018\u0001\u0019\u0001\u0005\u0001\u001a\u0001\u001b\u0001\u0006\u0001\u001c\u0001\u001d\u0001\u001e\u0001\u001f\u0001\u0006\u0001 \u0003\u0006\u0001!\u0002\u0006\u0013\"\u0001#\u0001$\u0001%\u001c\"\u0014&\u0001'\u0003&\u0001(\u0003&\u0001)\u0003&\u0001*\u0002&\u0001+\u000e&\u0014,\u0001-\u0007,\u0001.\u0003,\u0001/\u0002,\u00010\u000e,3\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0003\u0000\u0001\u0007\u0002\u0000\u00022\u0002\u0000\u0001\u0007\u0001\u0000\u00023\u0001\u0000\u00024\u00015\u000e\u0000\u00014\u0012\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u00016\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u00017\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u00018\r\u0006\u0003\u0000\u0001\u0007\u0002\u0000\u00022\u0002\u0000\u0001\u0007\u00019\u00023\u0001\u0000\u00024\u00015\u000e\u0000\u00014\u0012\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001:\u0003\u0006\u0001;\u0002\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001<\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0004\u0006\u0001=\n\u0006\u0003\u0000\u00015\u0006\u0000\u00015'\u0000\u0012\u000f\u0001>\u0001?\u001e\u000f\u0013@\u0001A\u0001@\u0001B\u001c@\u0016C\u0001\u0000\u001bC\u0017\u0000\u0001D\u0001E2\u0000\u0001\u0014\u0019\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001F\u0005\u0006\u0001\u0000\u0003\u0006\u0001G\u0002\u0006\u0001H\b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001I\u0001\u0006\u0001J\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001K\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\n\u0006\u0001L\u0004\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001M\u0001\u0000\u0001\u0006\u0001N\u0002\u0006\u0001O\n\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0004\u0006\u0001=\u0001\u0006\u0001\u0000\b\u0006\u0001P\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001Q\u0004\u0006\u0001R\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0002\u0006\u0001S\f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001T\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0002\u0006\u0001U\t\u0006\u0001V\u0002\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001W\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\n\u0006\u0001X\u0004\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001Y\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001Z\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001[\r\u0006\u0013\"\u0003\u0000\u001c\"\u0014\\\u0001\u0000\u001d\\\u0015\u0000\u0001]\u001c\u0000\u0014&\u0001\u0000\u0003&\u0001\u0000\u0003&\u0001\u0000\u0003&\u0001\u0000\u0002&\u0001\u0000\u000e&\u0017\u0000\u0001^7\u0000\u0001_1\u0000\u0001`\u0003\u0000\u0001a3\u0000\u0001b\u000e\u0000\u0014,\u0001\u0000\u0007,\u0001\u0000\u0003,\u0001\u0000\u0002,\u0001\u0000\u000e,\u001d\u0000\u0001c1\u0000\u0001d\u0003\u0000\u0001e3\u0000\u0001f\u000e\u0000\u0001g\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001g\u0002\u0006\u0001\u0000\u0007g\u0001\u0000\u0002g\u0006\u0006\u0001g\u000f\u0006\u0003\u0000\u0001h\u0006\u0000\u0001h\u0003\u0000\u0001i&\u0000\u00015\u0006\u0000\u00015\u0001\u0000\u00023\u0001\u0000\u00024\u000f\u0000\u00014\u0012\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\t\u0006\u0001j\u0005\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001k\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001l\u0001\u0006\u0001m\u0002\u0006\u0001\u0000\u000f\u0006\u0003\u0000\u0001n\u0004\u0000\u0003n\u0001\u0000\u0002n\u0001\u0000\u0002n\u000f\u0000\u0001n\u0003\u0000\u0002n\r\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0003\u0006\u0001m\u0002\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001o\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0004\u0006\u0001=\u0001\u0006\u0001\u0000\u000f\u0006\u0014\u000f\u0001\u0000\u001d\u000f\u0013@\u0001A\u0001@\u0001p0@\u0001\u0000\u001d@\u0015\u0000\u0001q\u001c\u0000\u0016C\u0001r\u001bC\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001s\u0001\u0000\u0003\u0006\u0001t\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001u\u0004\u0006\u0001=\u0003\u0006\u0001m\u0004\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0002\u0006\u0001m\u0003\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0003\u0006\u0001v\u0004\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001w\u0001\u0000\u0004\u0006\u0001x\n\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001y\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0002\u0006\u0001z\u0003\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0005\u0006\u0001{\t\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001:\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001|\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0002\u0006\u0001}\u0003\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001~\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001\u007f\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0003\u0006\u0001\u0080\u0002\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0001\u0006\u0001\u0081\u0001\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001\u0082\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000b\u0006\u0001\u0083\u0003\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u0084\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001=\u000e\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001\u0085\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u0086\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001l\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001=\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001=\u000b\u0006\u0015\u0000\u0001\u00879\u0000\u0001\u00882\u0000\u0001\u0089\u001a\u0000\u0001\u008aM\u0000\u0001\u008b+\u0000\u0001\u008c2\u0000\u0001\u008d\u001a\u0000\u0001\u008eM\u0000\u0001\u008f\u000e\u0000\u0001g\r\u0000\u0001g\u0003\u0000\u0007g\u0001\u0000\u0002g\u0006\u0000\u0001g\u0012\u0000\u0001h\u0006\u0000\u0001h\u0004\u0000\u00024\u000f\u0000\u00014\u0014\u0000\u0001h\u0006\u0000\u0001h(\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0006\u0006\u0001=\b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0003\u0006\u0001s\u0002\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0003\u0006\u0001\u007f\u0004\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001=\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u0090\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0003\u0006\u0001=\u0002\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0004\u0006\u0001W\n\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001\u0091\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\r\u0006\u0001\u0092\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\f\u0006\u0001\u0093\u0002\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001\u0094\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001\u0095\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u0096\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001\u0097\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0007\u0006\u0001\u0098\u0007\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001\u0099\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0004\u0006\u0001\u009a\n\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001m\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001=\u0005\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001\u009b\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\n\u0006\u0001\u009c\u0004\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\n\u0006\u0001\u009d\u0004\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u009e\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u009f\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001=\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001\u00a0\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u001e\u0000\u0001\u00a15\u0000\u0001\u00a2\u001c\u0000\u0001\u00895\u0000\u0001\u00a3>\u0000\u0001\u00a45\u0000\u0001\u00a5\u001c\u0000\u0001\u008d5\u0000\u0001\u00a6!\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0005\u0006\u0001\u00a7\t\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001=\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001\u00a8\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001\u00a9\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u00aa\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001\u00a0\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001=\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001\u00ab\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0004\u0006\u0001\u00ac\n\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001\u00ad\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u0091\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u00ae\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001\u00af\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u00b0\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0003\u0006\u0001\u0091\u0004\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u00b1\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0001\u0006\u0001=\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u001f\u0000\u0001\u0089\u0002\u0000\u0001\u00a2&\u0000\u0001\u00b2\u001b\u0000\u0003\u00a3\u0001\u00b3\t\u00a3\u0001\u00b3\u0002\u00a3\u0002\u00b3\u0004\u0000\u0001\u00a3\u0001\u00b3\u0002\u0000\u0001\u00b3\u0006\u00a3\u0001\u00b3\u000f\u00a3\u001f\u0000\u0001\u008d\u0002\u0000\u0001\u00a5&\u0000\u0001\u00b4\u001b\u0000\u0003\u00a6\u0001\u00b5\t\u00a6\u0001\u00b5\u0002\u00a6\u0002\u00b5\u0004\u0000\u0001\u00a6\u0001\u00b5\u0002\u0000\u0001\u00b5\u0006\u00a6\u0001\u00b5\u000f\u00a6\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0001\u0006\u0001s\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000e\u0006\u0001m\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001m\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0003\u0006\u0001\u00b6\u0004\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0001\u0006\u0001j\u0006\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\b\u0006\u0001m\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0003\u0006\u0001u\u0004\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0001\u009e\r\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0003\u0006\u0001\u00b7\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u0005\u0006\u0001=\t\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0005\u0006\u0001\u00b8\u0001\u0000\u000f\u0006\u0017\u0000\u0001\u00a31\u0000\u0001\u00a6\u001b\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0002\u0006\u000b\u0000\u0001\u0006\u0001\u0095\u0004\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\u0007\u0006\u0001s\u0001\u0000\u0002\u0006\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u00011\b\u0006\u0001\u0000\u0001\u0006\u0001m\u000b\u0000\u0006\u0006\u0001\u0000\u000f\u0006";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = ScalaTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0000\u0001\u0001\u0002\u0000\u0001\t\u0002\u0001\u0001\t\u0007\u0001\u0001\t\u0013\u0001\u0001\t\u0002\u0001\u0001\t\u0005\u0001\u0001\t\u0004\u0001\u0001\t\u0001\u0000\u0001\t\u0004\u0001\u0001\u0000\u0004\u0001\u0001\t\u0005\u0001\u0002\t\u0016\u0001\u0001\t\u0001\u0000\u0001\t\b\u0000\u0002\u0001\u0001\u0000\u0006\u0001\u0003\t\u0014\u0001\u0001\t\b\u0000\u0011\u0001\u0002\u0000\u0001\u0001\u0002\u0000\f\u0001\u0004\u0000\u0003\u0001";
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
        int[] result = new int[184];
        int offset = 0;
        offset = ScalaTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[184];
        int offset = 0;
        offset = ScalaTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[8200];
        int offset = 0;
        offset = ScalaTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[184];
        int offset = 0;
        offset = ScalaTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public ScalaTokenMaker() {
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
        this.addToken(this.zzBuffer, start, end, tokenType, so, false);
    }

    @Override
    public void addToken(char[] array, int start, int end, int tokenType, int startOffset, boolean hyperlink) {
        super.addToken(array, start, end, tokenType, startOffset, hyperlink);
        this.zzStartRead = this.zzMarkedPos;
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"//", null};
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        int state = 0;
        switch (initialTokenType) {
            case 13: {
                state = 1;
                break;
            }
            case 2: {
                state = 2;
                break;
            }
            default: {
                state = 0;
            }
        }
        this.s = text;
        this.start = text.offset;
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

    public ScalaTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public ScalaTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 150) {
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
        block60: while (true) {
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
                case 6: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 26: {
                    continue block60;
                }
                case 15: {
                    this.addToken(14);
                }
                case 27: {
                    continue block60;
                }
                case 21: {
                    this.start = this.zzMarkedPos - 3;
                    this.yybegin(1);
                }
                case 28: {
                    continue block60;
                }
                case 19: {
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead + 1, 2);
                }
                case 29: {
                    continue block60;
                }
                case 18: {
                    this.start = this.zzMarkedPos - 2;
                    this.yybegin(2);
                }
                case 30: {
                    continue block60;
                }
                case 8: {
                    this.addToken(21);
                }
                case 31: {
                    continue block60;
                }
                case 20: {
                    this.addToken(12);
                }
                case 32: {
                    continue block60;
                }
                case 13: {
                    this.addToken(11);
                }
                case 33: {
                    continue block60;
                }
                case 14: {
                    this.addToken(6);
                }
                case 34: {
                    continue block60;
                }
                case 4: {
                    this.addToken(22);
                }
                case 35: {
                    continue block60;
                }
                case 22: {
                    this.addToken(15);
                }
                case 36: {
                    continue block60;
                }
                case 9: 
                case 37: {
                    continue block60;
                }
                case 2: {
                    this.addToken(20);
                }
                case 38: {
                    continue block60;
                }
                case 12: {
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 39: {
                    continue block60;
                }
                case 17: {
                    this.start = this.zzMarkedPos - 2;
                    this.yybegin(3);
                }
                case 40: {
                    continue block60;
                }
                case 23: {
                    this.addToken(this.start, this.zzStartRead + 2, 13);
                    this.yybegin(0);
                }
                case 41: {
                    continue block60;
                }
                case 5: {
                    this.addToken(38);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 42: {
                    continue block60;
                }
                case 7: {
                    this.addToken(37);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 43: {
                    continue block60;
                }
                case 16: {
                    this.addToken(13);
                }
                case 44: {
                    continue block60;
                }
                case 10: {
                    this.addToken(this.start, this.zzStartRead - 1, 13);
                    return this.firstToken;
                }
                case 45: {
                    continue block60;
                }
                case 25: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 1);
                    this.start = this.zzMarkedPos;
                }
                case 46: {
                    continue block60;
                }
                case 24: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 2);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 2);
                    this.start = this.zzMarkedPos;
                }
                case 47: {
                    continue block60;
                }
                case 3: {
                    this.addToken(10);
                }
                case 48: {
                    continue block60;
                }
                case 1: 
                case 49: {
                    continue block60;
                }
                case 11: {
                    this.addToken(this.start, this.zzStartRead - 1, 2);
                    return this.firstToken;
                }
                case 50: {
                    continue block60;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 3: {
                        this.addToken(this.start, this.zzStartRead - 1, 1);
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 185: {
                        continue block60;
                    }
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 13);
                        return this.firstToken;
                    }
                    case 186: {
                        continue block60;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 187: {
                        continue block60;
                    }
                    case 2: {
                        this.addToken(this.start, this.zzStartRead - 1, 2);
                        return this.firstToken;
                    }
                    case 188: {
                        continue block60;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

