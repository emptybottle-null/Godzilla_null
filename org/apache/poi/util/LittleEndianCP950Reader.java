/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.StringUtil;

@Internal
public class LittleEndianCP950Reader
extends Reader {
    private static final POILogger LOGGER = POILogFactory.getLogger(LittleEndianCP950Reader.class);
    private static final char UNMAPPABLE = '?';
    private final ByteBuffer doubleByteBuffer = ByteBuffer.allocate(2);
    private final CharBuffer charBuffer = CharBuffer.allocate(2);
    private final CharsetDecoder decoder = StringUtil.BIG5.newDecoder();
    private static final char range1Low = '\u8140';
    private static final char range1High = '\u8dfe';
    private static final char range2Low = '\u8e40';
    private static final char range2High = '\ua0fe';
    private static final char range3Low = '\uc6a1';
    private static final char range3High = '\uc8fe';
    private static final char range4Low = '\ufa40';
    private static final char range4High = '\ufefe';
    private final byte[] data;
    private final int startOffset;
    private final int length;
    private int offset;
    private int trailing;
    private int leading;
    int cnt;

    public LittleEndianCP950Reader(byte[] data) {
        this(data, 0, data.length);
    }

    public LittleEndianCP950Reader(byte[] data, int offset, int length) {
        this.data = data;
        this.offset = this.startOffset = offset;
        this.length = length;
    }

    @Override
    public int read() {
        if (this.offset + 1 > this.data.length || this.offset - this.startOffset > this.length) {
            return -1;
        }
        this.trailing = this.data[this.offset++] & 0xFF;
        this.leading = this.data[this.offset++] & 0xFF;
        this.decoder.reset();
        if (this.leading < 129) {
            return this.trailing;
        }
        if (this.leading == 249) {
            return this.handleF9(this.trailing);
        }
        int ch = (this.leading << 8) + this.trailing;
        if (ch >= 33088 && ch <= 36350) {
            return this.handleRange1(this.leading, this.trailing);
        }
        if (ch >= 36416 && ch <= 41214) {
            return this.handleRange2(this.leading, this.trailing);
        }
        if (ch >= 50849 && ch <= 51454) {
            return this.handleRange3(this.leading, this.trailing);
        }
        if (ch >= 64064 && ch <= 65278) {
            return this.handleRange4(this.leading, this.trailing);
        }
        this.charBuffer.clear();
        this.doubleByteBuffer.clear();
        this.doubleByteBuffer.put((byte)this.leading);
        this.doubleByteBuffer.put((byte)this.trailing);
        this.doubleByteBuffer.flip();
        this.decoder.decode(this.doubleByteBuffer, this.charBuffer, true);
        this.charBuffer.flip();
        if (this.charBuffer.length() == 0) {
            LOGGER.log(5, "couldn't create char for: " + Integer.toString(this.leading & 0xFF, 16) + " " + Integer.toString(this.trailing & 0xFF, 16));
            return 63;
        }
        return Character.codePointAt(this.charBuffer, 0);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            int c = this.read();
            if (c == -1) {
                return i - off;
            }
            cbuf[i] = (char)c;
        }
        return len;
    }

    @Override
    public void close() {
    }

    private int handleRange1(int leading, int trailing) {
        return 61112 + 157 * (leading - 129) + (trailing < 128 ? trailing - 64 : trailing - 98);
    }

    private int handleRange2(int leading, int trailing) {
        return 58129 + 157 * (leading - 142) + (trailing < 128 ? trailing - 64 : trailing - 98);
    }

    private int handleRange3(int leading, int trailing) {
        return 63090 + 157 * (leading - 198) + (trailing < 128 ? trailing - 64 : trailing - 98);
    }

    private int handleRange4(int leading, int trailing) {
        return 57344 + 157 * (leading - 250) + (trailing < 128 ? trailing - 64 : trailing - 98);
    }

    private int handleF9(int trailing) {
        switch (trailing) {
            case 64: {
                return 32408;
            }
            case 65: {
                return 32411;
            }
            case 66: {
                return 32409;
            }
            case 67: {
                return 33248;
            }
            case 68: {
                return 33249;
            }
            case 69: {
                return 34374;
            }
            case 70: {
                return 34375;
            }
            case 71: {
                return 34376;
            }
            case 72: {
                return 35193;
            }
            case 73: {
                return 35194;
            }
            case 74: {
                return 35196;
            }
            case 75: {
                return 35195;
            }
            case 76: {
                return 35327;
            }
            case 77: {
                return 35736;
            }
            case 78: {
                return 35737;
            }
            case 79: {
                return 36517;
            }
            case 80: {
                return 36516;
            }
            case 81: {
                return 36515;
            }
            case 82: {
                return 37998;
            }
            case 83: {
                return 37997;
            }
            case 84: {
                return 37999;
            }
            case 85: {
                return 38001;
            }
            case 86: {
                return 38003;
            }
            case 87: {
                return 38729;
            }
            case 88: {
                return 39026;
            }
            case 89: {
                return 39263;
            }
            case 90: {
                return 40040;
            }
            case 91: {
                return 40046;
            }
            case 92: {
                return 40045;
            }
            case 93: {
                return 40459;
            }
            case 94: {
                return 40461;
            }
            case 95: {
                return 40464;
            }
            case 96: {
                return 40463;
            }
            case 97: {
                return 40466;
            }
            case 98: {
                return 40465;
            }
            case 99: {
                return 40609;
            }
            case 100: {
                return 40693;
            }
            case 101: {
                return 40713;
            }
            case 102: {
                return 40775;
            }
            case 103: {
                return 40824;
            }
            case 104: {
                return 40827;
            }
            case 105: {
                return 40826;
            }
            case 106: {
                return 40825;
            }
            case 107: {
                return 22302;
            }
            case 108: {
                return 28774;
            }
            case 109: {
                return 31855;
            }
            case 110: {
                return 34876;
            }
            case 111: {
                return 36274;
            }
            case 112: {
                return 36518;
            }
            case 113: {
                return 37315;
            }
            case 114: {
                return 38004;
            }
            case 115: {
                return 38008;
            }
            case 116: {
                return 38006;
            }
            case 117: {
                return 38005;
            }
            case 118: {
                return 39520;
            }
            case 119: {
                return 40052;
            }
            case 120: {
                return 40051;
            }
            case 121: {
                return 40049;
            }
            case 122: {
                return 40053;
            }
            case 123: {
                return 40468;
            }
            case 124: {
                return 40467;
            }
            case 125: {
                return 40694;
            }
            case 126: {
                return 40714;
            }
            case 161: {
                return 40868;
            }
            case 162: {
                return 28776;
            }
            case 163: {
                return 28773;
            }
            case 164: {
                return 31991;
            }
            case 165: {
                return 34410;
            }
            case 166: {
                return 34878;
            }
            case 167: {
                return 34877;
            }
            case 168: {
                return 34879;
            }
            case 169: {
                return 35742;
            }
            case 170: {
                return 35996;
            }
            case 171: {
                return 36521;
            }
            case 172: {
                return 36553;
            }
            case 173: {
                return 38731;
            }
            case 174: {
                return 39027;
            }
            case 175: {
                return 39028;
            }
            case 176: {
                return 39116;
            }
            case 177: {
                return 39265;
            }
            case 178: {
                return 39339;
            }
            case 179: {
                return 39524;
            }
            case 180: {
                return 39526;
            }
            case 181: {
                return 39527;
            }
            case 182: {
                return 39716;
            }
            case 183: {
                return 40469;
            }
            case 184: {
                return 40471;
            }
            case 185: {
                return 40776;
            }
            case 186: {
                return 25095;
            }
            case 187: {
                return 27422;
            }
            case 188: {
                return 29223;
            }
            case 189: {
                return 34380;
            }
            case 190: {
                return 36520;
            }
            case 191: {
                return 38018;
            }
            case 192: {
                return 38016;
            }
            case 193: {
                return 38017;
            }
            case 194: {
                return 39529;
            }
            case 195: {
                return 39528;
            }
            case 196: {
                return 39726;
            }
            case 197: {
                return 40473;
            }
            case 198: {
                return 29225;
            }
            case 199: {
                return 34379;
            }
            case 200: {
                return 35743;
            }
            case 201: {
                return 38019;
            }
            case 202: {
                return 40057;
            }
            case 203: {
                return 40631;
            }
            case 204: {
                return 30325;
            }
            case 205: {
                return 39531;
            }
            case 206: {
                return 40058;
            }
            case 207: {
                return 40477;
            }
            case 208: {
                return 28777;
            }
            case 209: {
                return 28778;
            }
            case 210: {
                return 40612;
            }
            case 211: {
                return 40830;
            }
            case 212: {
                return 40777;
            }
            case 213: {
                return 40856;
            }
            case 214: {
                return 30849;
            }
            case 215: {
                return 37561;
            }
            case 216: {
                return 35023;
            }
            case 217: {
                return 22715;
            }
            case 218: {
                return 24658;
            }
            case 219: {
                return 31911;
            }
            case 220: {
                return 23290;
            }
            case 221: {
                return 9556;
            }
            case 222: {
                return 9574;
            }
            case 223: {
                return 9559;
            }
            case 224: {
                return 9568;
            }
            case 225: {
                return 9580;
            }
            case 226: {
                return 9571;
            }
            case 227: {
                return 9562;
            }
            case 228: {
                return 9577;
            }
            case 229: {
                return 9565;
            }
            case 230: {
                return 9554;
            }
            case 231: {
                return 9572;
            }
            case 232: {
                return 9557;
            }
            case 233: {
                return 9566;
            }
            case 234: {
                return 9578;
            }
            case 235: {
                return 9569;
            }
            case 236: {
                return 9560;
            }
            case 237: {
                return 9575;
            }
            case 238: {
                return 9563;
            }
            case 239: {
                return 9555;
            }
            case 240: {
                return 9573;
            }
            case 241: {
                return 9558;
            }
            case 242: {
                return 9567;
            }
            case 243: {
                return 9579;
            }
            case 244: {
                return 9570;
            }
            case 245: {
                return 9561;
            }
            case 246: {
                return 9576;
            }
            case 247: {
                return 9564;
            }
            case 248: {
                return 9553;
            }
            case 249: {
                return 9552;
            }
            case 250: {
                return 9581;
            }
            case 251: {
                return 9582;
            }
            case 252: {
                return 9584;
            }
            case 253: {
                return 9583;
            }
            case 254: {
                return 9619;
            }
        }
        LOGGER.log(5, "couldn't create char for: f9 " + Integer.toString(trailing & 0xFF, 16));
        return 63;
    }
}

