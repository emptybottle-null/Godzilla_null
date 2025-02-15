/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.GeneratedJavaParserConstants;
import com.github.javaparser.GeneratedJavaParserTokenManagerBase;
import com.github.javaparser.JavaToken;
import com.github.javaparser.SimpleCharStream;
import com.github.javaparser.Token;
import com.github.javaparser.TokenMgrException;
import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.CommentsCollection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GeneratedJavaParserTokenManager
implements GeneratedJavaParserConstants {
    private List<JavaToken> tokens = new ArrayList<JavaToken>();
    private CommentsCollection commentsCollection = new CommentsCollection();
    private JavaToken homeToken;
    private Stack<Token> tokenWorkStack = new Stack();
    private boolean storeTokens;
    private boolean yieldSupported = false;
    static final long[] jjbitVec0 = new long[]{0L, 0L, 0x100000020L, 0L};
    static final long[] jjbitVec1 = new long[]{0L, 0L, 1L, 0L};
    static final long[] jjbitVec2 = new long[]{16384L, 0L, 0L, 0L};
    static final long[] jjbitVec3 = new long[]{144036023255039L, 0x180000000L, 0L, 0L};
    static final long[] jjbitVec4 = new long[]{1L, 0L, 0L, 0L};
    static final long[] jjbitVec5 = new long[]{0L, 0L, 0L, Long.MIN_VALUE};
    static final long[] jjbitVec6 = new long[]{-2L, -1L, -1L, -1L};
    static final long[] jjbitVec8 = new long[]{0L, 0L, -1L, -1L};
    static final long[] jjbitVec9 = new long[]{-4503598551400446L, -8193L, -17388175097857L, 1297036696969281535L};
    static final long[] jjbitVec10 = new long[]{0L, 0L, 297242231151001600L, -36028797027352577L};
    static final long[] jjbitVec11 = new long[]{-1L, -1L, -1L, 88094074470339L};
    static final long[] jjbitVec12 = new long[]{0L, -4837147474772623360L, -17179879616L, -18014398509481985L};
    static final long[] jjbitVec13 = new long[]{-1L, -1L, -1021L, -1L};
    static final long[] jjbitVec14 = new long[]{-281474976710657L, -8547991553L, 33023L, 0x707FFFFFF0000L};
    static final long[] jjbitVec15 = new long[]{-4294965248L, -351843720886273L, -1L, -7205547885240254465L};
    static final long[] jjbitVec16 = new long[]{0xFFFFFFFD0000L, -8192L, 563224831328255L, 301749971126844416L};
    static final long[] jjbitVec17 = new long[]{1168302407679L, 8791831609343L, 4602678814877679616L, 0L};
    static final long[] jjbitVec18 = new long[]{2594073385365405680L, -562932790263808L, 2577745637692514273L, 1733604397398638592L};
    static final long[] jjbitVec19 = new long[]{247132830528276448L, 7881300924956672L, 2589004636761079776L, 144678150914244608L};
    static final long[] jjbitVec20 = new long[]{2589004636760940512L, 562965791113216L, 288167810662516712L, 0x200000000010000L};
    static final long[] jjbitVec21 = new long[]{2594071186342010848L, 0x307000000L, 2589567586714640353L, 1688863818907648L};
    static final long[] jjbitVec22 = new long[]{2882303761516978144L, -288230361111969792L, 3457638613854978016L, 127L};
    static final long[] jjbitVec23 = new long[]{-9219431387180826626L, 127L, 2309762420256548246L, 0xF000005FL};
    static final long[] jjbitVec24 = new long[]{1L, 0x1FFFFFFFFEFFL, 7936L, 0L};
    static final long[] jjbitVec25 = new long[]{-9223363240761753601L, -8514196127940608L, -4294950909L, -576460752303480641L};
    static final long[] jjbitVec26 = new long[]{-1L, -3263218177L, 9168765891372858879L, -8388803L};
    static final long[] jjbitVec27 = new long[]{-12713985L, 0x7FFFFFFL, -4294901761L, 0x3F3FFFFFFFFFFFFFL};
    static final long[] jjbitVec28 = new long[]{-1L, -105553116266497L, -4160749570L, 144053615424700415L};
    static final long[] jjbitVec29 = new long[]{1125895612129279L, 527761286627327L, 0xFFFFFFFFFFFFFL, 0x18800000L};
    static final long[] jjbitVec30 = new long[]{-4294967296L, 0xFFFFFFFFFFFFFFL, -274877906944097L, 0x3FFFFFFFFFFFFFL};
    static final long[] jjbitVec31 = new long[]{Integer.MAX_VALUE, 8796093022142464L, -263882790666241L, 1023L};
    static final long[] jjbitVec32 = new long[]{-4286578689L, 0x1FFFFFL, 0x8000000000L, 0L};
    static final long[] jjbitVec33 = new long[]{0xFFFFFFFFFFFE0L, 4064L, -288019261329244168L, 0x3FFFFFFFFFL};
    static final long[] jjbitVec34 = new long[]{0xFFFFFFFFFL, 4611686018360336384L, 511L, 28110114275721216L};
    static final long[] jjbitVec35 = new long[]{-1L, -1L, -1L, 0L};
    static final long[] jjbitVec36 = new long[]{-3233808385L, 0x3FFFFFFFAAFF3F3FL, 0x5FDFFFFFFFFFFFFFL, 2295745090394464220L};
    static final long[] jjbitVec37 = new long[]{Long.MIN_VALUE, -9222809086900305919L, -3758161920L, 0L};
    static final long[] jjbitVec38 = new long[]{-864764451093480316L, -4294949920L, 511L, 0L};
    static final long[] jjbitVec39 = new long[]{-140737488355329L, -2147483649L, -1L, 3509778554814463L};
    static final long[] jjbitVec40 = new long[]{-245465970900993L, 0x80FFFFFFFFFFL, 0x7F7F7F7F007FFFFFL, 0x7F7F7F7FL};
    static final long[] jjbitVec41 = new long[]{0x800000000000L, 0L, 0L, 0L};
    static final long[] jjbitVec42 = new long[]{2251241253188403424L, -2L, -4823449601L, -576460752303423489L};
    static final long[] jjbitVec43 = new long[]{-422212465066016L, -1L, 0x7FFFFFF00007FFFL, -281474976710656L};
    static final long[] jjbitVec44 = new long[]{-1L, -1L, 0x3FFFFFFFFFFFFFL, 0L};
    static final long[] jjbitVec45 = new long[]{-1L, -1L, -1L, 0x7FFFFFFFFFFL};
    static final long[] jjbitVec46 = new long[]{-1L, -1L, 8191L, 0x3FFFFFFFFFFF0000L};
    static final long[] jjbitVec47 = new long[]{13198434443263L, -9223231299366420481L, -3221225473L, 0xFFFFFFFFFFFFL};
    static final long[] jjbitVec48 = new long[]{-12893290496L, -1L, 0xFF7FFFFFFFF9FFL, -36028797018963968L};
    static final long[] jjbitVec49 = new long[]{72057628397664187L, 0xFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFCL, 2953235455648202752L};
    static final long[] jjbitVec50 = new long[]{-281200098804736L, 2305843004918726783L, 0x7FFFFFFFFFFF0L, 8935422993945886720L};
    static final long[] jjbitVec51 = new long[]{0x1FFFFFFFFFFL, -4287426849551675401L, 4495436853045886975L, 7890092085477381L};
    static final long[] jjbitVec52 = new long[]{-141291530846594L, -281200233021441L, -1L, 0x7FFFFFFFFL};
    static final long[] jjbitVec53 = new long[]{-1L, -1L, -281406257233921L, 0xFFFFFFFFFFFF87FL};
    static final long[] jjbitVec54 = new long[]{-1L, -211106232532993L, -1L, 0x3FFFFFFL};
    static final long[] jjbitVec55 = new long[]{6881498030004502655L, -37L, 0x3FFFFFFFFFFFFL, -524288L};
    static final long[] jjbitVec56 = new long[]{0x3FFFFFFFFFFFFFFFL, -65536L, -196609L, 0x1FFF0000000000FFL};
    static final long[] jjbitVec57 = new long[]{0x18000000000000L, -9286475208138752L, -1L, 0x1FFFFFFFFFFFFFFFL};
    static final long[] jjbitVec58 = new long[]{-8646911293141286896L, -274743689218L, Long.MAX_VALUE, 425688104188L};
    static final long[] jjbitVec59 = new long[]{0L, 0L, 297277419818057727L, -36028797027352577L};
    static final long[] jjbitVec60 = new long[]{-1L, -4836865999795912705L, -17179879616L, -18014398509481985L};
    static final long[] jjbitVec61 = new long[]{-1L, -1L, -773L, -1L};
    static final long[] jjbitVec62 = new long[]{-281474976710657L, -8547991553L, -4611686018427485953L, 1979120929931446L};
    static final long[] jjbitVec63 = new long[]{-3892377537L, -65970697666561L, -1L, -6917531227739127809L};
    static final long[] jjbitVec64 = new long[]{-32768L, -6145L, 0x3FFFFFFFFFFFFL, 0x43FFFFFFFFFFFFFL};
    static final long[] jjbitVec65 = new long[]{0x3FFFFFFFFFFFL, 0x7FF0FFFFFFFL, 4602678814877679616L, -1048576L};
    static final long[] jjbitVec66 = new long[]{-1L, -281681135140865L, -881018876128026641L, 1733885649045453215L};
    static final long[] jjbitVec67 = new long[]{-3211631683292264466L, 18014125208779143L, -869759877059461138L, -143270973599040577L};
    static final long[] jjbitVec68 = new long[]{-869759877059600402L, 844217442122143L, -4323518207764871188L, 144396388183129543L};
    static final long[] jjbitVec69 = new long[]{-2017614832085377041L, 281264647060959L, -869196927105900561L, 1970115463626207L};
    static final long[] jjbitVec70 = new long[]{-139281L, -287949109465154081L, 3457638613854978028L, 3658904103781503L};
    static final long[] jjbitVec71 = new long[]{-8646911284551352322L, 0x3FF7FFFL, 4323434403644581270L, 0xF3FF3F5FL};
    static final long[] jjbitVec72 = new long[]{-4422530440275951615L, -527765581332737L, 2305843009196916703L, 64L};
    static final long[] jjbitVec73 = new long[]{-1L, -64513L, -3221225473L, -576460752303480641L};
    static final long[] jjbitVec74 = new long[]{-12713985L, 0xE7FFFFFFL, -4294901761L, 0x3F3FFFFFFFFFFFFFL};
    static final long[] jjbitVec75 = new long[]{9007194961862655L, 0xDDFFF000FFFFFL, -1L, 0x3FF388FFFFFL};
    static final long[] jjbitVec76 = new long[]{-4227893248L, 0xFFFFFFFFFFFFFFL, -272678883688449L, 0x3FFFFFFFFFFFFFL};
    static final long[] jjbitVec77 = new long[]{0xFFF0FFF7FFFFFFFL, 8796093022207936L, -263882790666241L, 0x3FF03FFL};
    static final long[] jjbitVec78 = new long[]{-4026531841L, -6917529029788565505L, 4611405093273535487L, 0L};
    static final long[] jjbitVec79 = new long[]{-1L, 4494803601395711L, -1L, 0xFFFFFFFFFFFFFL};
    static final long[] jjbitVec80 = new long[]{0xFFFFFFFFFFFFFFL, 0x3FFFFFFFFFFFE3FFL, 511L, 288230376151121920L};
    static final long[] jjbitVec81 = new long[]{-1L, -1L, -1L, -288230376151711745L};
    static final long[] jjbitVec82 = new long[]{-9223235697412868096L, -9222527753657516031L, -3758161920L, 562821641207808L};
    static final long[] jjbitVec83 = new long[]{-140737488355329L, -2147483649L, -1L, 0xFF81FFFFFFFFFL};
    static final long[] jjbitVec84 = new long[]{-245465970900993L, -9223230199854792705L, 0x7F7F7F7F007FFFFFL, -2155905153L};
    static final long[] jjbitVec85 = new long[]{2251518330118602976L, -2L, -4722786305L, -576460752303423489L};
    static final long[] jjbitVec86 = new long[]{0xFFFFFFF1FFFL, -4615908143078047745L, -1L, 0x3FFFFFFFFFFFFL};
    static final long[] jjbitVec87 = new long[]{0x10000FFFFFFFFFFL, 0xFFFFFFFFFFFFFL, -1L, 2954361351327121471L};
    static final long[] jjbitVec88 = new long[]{-211106232532993L, 0x1FFFFFFF000FFFFFL, -1L, 9223372032626884609L};
    static final long[] jjbitVec89 = new long[]{0x7FFFFFFFFFFFFFL, -252201583360655361L, -1L, 35184368733388807L};
    static final long[] jjbitVec90 = new long[]{-141291530846594L, -281200233021441L, -1L, 0x3FF37FFFFFFFFFFL};
    static final long[] jjbitVec91 = new long[]{6881498031078244479L, -37L, 0x3FFFFFFFFFFFFL, -524288L};
    static final long[] jjbitVec92 = new long[]{7036870122864639L, -9286475208138752L, -1L, -6917529027641081857L};
    static final long[] jjbitVec93 = new long[]{-8646911293074243568L, -274743689218L, Long.MAX_VALUE, 1008806742219095292L};
    public static final String[] jjstrLiteralImages = new String[]{"", null, null, null, null, null, null, null, null, null, null, "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while", "yield", "requires", "to", "with", "open", "opens", "uses", "module", "exports", "provides", "transitive", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "(", ")", "{", "}", "[", "]", ";", ",", ".", "@", "=", "<", "!", "~", "?", ":", "->", "==", ">=", "<=", "!=", "&&", "||", "++", "--", "+", "-", "*", "/", "&", "|", "^", "%", "<<", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>=", "...", "::", ">>>", ">>", ">", "\u001a"};
    static final int[] jjnextStates = new int[]{62, 63, 64, 65, 66, 67, 68, 69, 10, 75, 76, 77, 83, 84, 85, 56, 60, 28, 29, 36, 37, 13, 15, 26, 96, 100, 103, 107, 111, 114, 118, 131, 3, 4, 5, 10, 8, 10, 11, 7, 8, 10, 11, 28, 29, 39, 36, 37, 72, 10, 74, 71, 72, 10, 74, 80, 10, 82, 79, 80, 10, 82, 86, 89, 10, 87, 88, 89, 10, 92, 10, 94, 91, 92, 10, 94, 98, 99, 64, 101, 102, 64, 105, 106, 64, 120, 121, 122, 124, 125, 126, 129, 130, 10, 133, 134, 135, 136, 139, 140, 10, 30, 38, 40, 3, 4, 6, 7, 8, 9, 16, 17, 19, 62, 63, 65, 66, 67, 68, 70, 71, 72, 73, 75, 76, 78, 79, 80, 81, 83, 84, 87, 88, 90, 91, 92, 93, 101, 102, 105, 106, 112, 113, 116, 117, 127, 128, 129, 130, 137, 138, 139, 140};
    int curLexState = 0;
    int defaultLexState = 0;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;
    public static final String[] lexStateNames = new String[]{"DEFAULT", "IN_JAVADOC_COMMENT", "IN_MULTI_LINE_COMMENT", "IN_TEXT_BLOCK"};
    public static final int[] jjnewLexState = new int[]{-1, -1, -1, -1, -1, -1, 1, 2, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    static final long[] jjtoToken = new long[]{-2047L, -3573407745L, 524287L};
    static final long[] jjtoSkip = new long[]{830L, 0L, 0L};
    static final long[] jjtoSpecial = new long[]{830L, 0L, 0L};
    static final long[] jjtoMore = new long[]{1216L, 0x14000000L, 0L};
    protected SimpleCharStream input_stream;
    private final int[] jjrounds = new int[141];
    private final int[] jjstateSet = new int[282];
    private final StringBuilder jjimage;
    private StringBuilder image = this.jjimage = new StringBuilder();
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;

    void reset() {
        this.tokens = new ArrayList<JavaToken>();
        this.commentsCollection = new CommentsCollection();
        this.homeToken = null;
    }

    List<JavaToken> getTokens() {
        if (this.storeTokens) {
            return this.tokens;
        }
        return null;
    }

    CommentsCollection getCommentsCollection() {
        return this.commentsCollection;
    }

    JavaToken getHomeToken() {
        return this.homeToken;
    }

    public void setStoreTokens(boolean storeTokens) {
        this.storeTokens = storeTokens;
    }

    public void setYieldSupported() {
        this.yieldSupported = true;
    }

    private void CommonTokenAction(Token token) {
        do {
            this.tokenWorkStack.push(token);
        } while ((token = token.specialToken) != null);
        while (!this.tokenWorkStack.empty()) {
            token = this.tokenWorkStack.pop();
            token.javaToken = new JavaToken(token, this.tokens);
            if (this.storeTokens) {
                this.tokens.add(token.javaToken);
            }
            if (this.homeToken == null) {
                this.homeToken = token.javaToken;
            }
            if (!TokenTypes.isComment(token.kind)) continue;
            Comment comment = GeneratedJavaParserTokenManagerBase.createCommentFromToken(token);
            this.commentsCollection.addComment(comment);
        }
    }

    private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1, long active2) {
        switch (pos) {
            case 0: {
                if ((active1 & 0x4000000L) != 0L) {
                    return 141;
                }
                if ((active0 & 0xFFFFFFFFFFFFF800L) != 0L || (active1 & 0x7FFL) != 0L) {
                    this.jjmatchedKind = 93;
                    return 142;
                }
                if ((active1 & 0x10000000000L) != 0L || (active2 & 0x2000L) != 0L) {
                    return 2;
                }
                if ((active0 & 0x80L) != 0L || (active1 & 0x1000000000000000L) != 0L || (active2 & 0x20L) != 0L) {
                    return 56;
                }
                return -1;
            }
            case 1: {
                if ((active1 & 0x4000000L) != 0L) {
                    if (this.jjmatchedPos != 1) {
                        this.jjmatchedKind = 89;
                        this.jjmatchedPos = 1;
                    }
                    return -1;
                }
                if ((active0 & 0x401800000L) != 0L || (active1 & 4L) != 0L) {
                    return 142;
                }
                if ((active0 & 0x80L) != 0L) {
                    return 58;
                }
                if ((active0 & 0xFFFFFFFBFE7FF800L) != 0L || (active1 & 0x7FBL) != 0L) {
                    if (this.jjmatchedPos != 1) {
                        this.jjmatchedKind = 93;
                        this.jjmatchedPos = 1;
                    }
                    return 142;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0xEFFFFB3AFF7FF800L) != 0L || (active1 & 0x7FBL) != 0L) {
                    if (this.jjmatchedPos != 2) {
                        this.jjmatchedKind = 93;
                        this.jjmatchedPos = 2;
                    }
                    return 142;
                }
                if ((active1 & 0x4000000L) != 0L) {
                    if (this.jjmatchedPos < 1) {
                        this.jjmatchedKind = 89;
                        this.jjmatchedPos = 1;
                    }
                    return -1;
                }
                if ((active0 & 0x100004C100000000L) != 0L) {
                    return 142;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x2880090206058000L) != 0L || (active1 & 0x78L) != 0L) {
                    return 142;
                }
                if ((active0 & 0xC77FF2B8F97A7800L) != 0L || (active1 & 0x783L) != 0L) {
                    if (this.jjmatchedPos != 3) {
                        this.jjmatchedKind = 93;
                        this.jjmatchedPos = 3;
                    }
                    return 142;
                }
                return -1;
            }
            case 4: {
                if ((active0 & 0x446DF2B809603800L) != 0L || (active1 & 0x782L) != 0L) {
                    if (this.jjmatchedPos != 4) {
                        this.jjmatchedKind = 93;
                        this.jjmatchedPos = 4;
                    }
                    return 142;
                }
                if ((active0 & 0x83120000F01A4000L) != 0L || (active1 & 0x21L) != 0L) {
                    return 142;
                }
                return -1;
            }
            case 5: {
                if ((active0 & 0x225821001001000L) != 0L || (active1 & 0x80L) != 0L) {
                    return 142;
                }
                if ((active0 & 0x444870A848602800L) != 0L || (active1 & 0x702L) != 0L) {
                    this.jjmatchedKind = 93;
                    this.jjmatchedPos = 5;
                    return 142;
                }
                return -1;
            }
            case 6: {
                if ((active0 & 0x300048402000L) != 0L || (active1 & 0x100L) != 0L) {
                    return 142;
                }
                if ((active0 & 0x444840A800200800L) != 0L || (active1 & 0x602L) != 0L) {
                    this.jjmatchedKind = 93;
                    this.jjmatchedPos = 6;
                    return 142;
                }
                return -1;
            }
            case 7: {
                if ((active0 & 0x44040A800000000L) != 0L || (active1 & 0x400L) != 0L) {
                    this.jjmatchedKind = 93;
                    this.jjmatchedPos = 7;
                    return 142;
                }
                if ((active0 & 0x4008000000200800L) != 0L || (active1 & 0x202L) != 0L) {
                    return 142;
                }
                return -1;
            }
            case 8: {
                if ((active0 & 0x40002800000000L) != 0L || (active1 & 0x400L) != 0L) {
                    this.jjmatchedKind = 93;
                    this.jjmatchedPos = 8;
                    return 142;
                }
                if ((active0 & 0x400408000000000L) != 0L) {
                    return 142;
                }
                return -1;
            }
            case 9: {
                if ((active0 & 0x2800000000L) != 0L || (active1 & 0x400L) != 0L) {
                    return 142;
                }
                if ((active0 & 0x40000000000000L) != 0L) {
                    this.jjmatchedKind = 93;
                    this.jjmatchedPos = 9;
                    return 142;
                }
                return -1;
            }
            case 10: {
                if ((active0 & 0x40000000000000L) != 0L) {
                    this.jjmatchedKind = 93;
                    this.jjmatchedPos = 10;
                    return 142;
                }
                return -1;
            }
        }
        return -1;
    }

    private final int jjStartNfa_0(int pos, long active0, long active1, long active2) {
        return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0, active1, active2), pos + 1);
    }

    private int jjStopAtPos(int pos, int kind) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        return pos + 1;
    }

    private int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case 10: {
                return this.jjStopAtPos(0, 3);
            }
            case 13: {
                this.jjmatchedKind = 4;
                return this.jjMoveStringLiteralDfa1_0(4L, 0L, 0L);
            }
            case 26: {
                return this.jjStopAtPos(0, 146);
            }
            case 33: {
                this.jjmatchedKind = 108;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x10000000000000L, 0L);
            }
            case 34: {
                return this.jjMoveStringLiteralDfa1_0(0L, 0x4000000L, 0L);
            }
            case 37: {
                this.jjmatchedKind = 128;
                return this.jjMoveStringLiteralDfa1_0(0L, 0L, 512L);
            }
            case 38: {
                this.jjmatchedKind = 125;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x20000000000000L, 64L);
            }
            case 40: {
                return this.jjStopAtPos(0, 96);
            }
            case 41: {
                return this.jjStopAtPos(0, 97);
            }
            case 42: {
                this.jjmatchedKind = 123;
                return this.jjMoveStringLiteralDfa1_0(0L, 0L, 16L);
            }
            case 43: {
                this.jjmatchedKind = 121;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x80000000000000L, 4L);
            }
            case 44: {
                return this.jjStopAtPos(0, 103);
            }
            case 45: {
                this.jjmatchedKind = 122;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x101000000000000L, 8L);
            }
            case 46: {
                this.jjmatchedKind = 104;
                return this.jjMoveStringLiteralDfa1_0(0L, 0L, 8192L);
            }
            case 47: {
                this.jjmatchedKind = 124;
                return this.jjMoveStringLiteralDfa1_0(128L, 0L, 32L);
            }
            case 58: {
                this.jjmatchedKind = 111;
                return this.jjMoveStringLiteralDfa1_0(0L, 0L, 16384L);
            }
            case 59: {
                return this.jjStopAtPos(0, 102);
            }
            case 60: {
                this.jjmatchedKind = 107;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x8000000000000L, 1026L);
            }
            case 61: {
                this.jjmatchedKind = 106;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x2000000000000L, 0L);
            }
            case 62: {
                this.jjmatchedKind = 145;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x4000000000000L, 104448L);
            }
            case 63: {
                return this.jjStopAtPos(0, 110);
            }
            case 64: {
                return this.jjStopAtPos(0, 105);
            }
            case 91: {
                return this.jjStopAtPos(0, 100);
            }
            case 93: {
                return this.jjStopAtPos(0, 101);
            }
            case 94: {
                this.jjmatchedKind = 127;
                return this.jjMoveStringLiteralDfa1_0(0L, 0L, 256L);
            }
            case 97: {
                return this.jjMoveStringLiteralDfa1_0(6144L, 0L, 0L);
            }
            case 98: {
                return this.jjMoveStringLiteralDfa1_0(57344L, 0L, 0L);
            }
            case 99: {
                return this.jjMoveStringLiteralDfa1_0(0x3F0000L, 0L, 0L);
            }
            case 100: {
                return this.jjMoveStringLiteralDfa1_0(0x1C00000L, 0L, 0L);
            }
            case 101: {
                return this.jjMoveStringLiteralDfa1_0(0xE000000L, 256L, 0L);
            }
            case 102: {
                return this.jjMoveStringLiteralDfa1_0(0x1F0000000L, 0L, 0L);
            }
            case 103: {
                return this.jjMoveStringLiteralDfa1_0(0x200000000L, 0L, 0L);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa1_0(0xFC00000000L, 0L, 0L);
            }
            case 108: {
                return this.jjMoveStringLiteralDfa1_0(0x10000000000L, 0L, 0L);
            }
            case 109: {
                return this.jjMoveStringLiteralDfa1_0(0L, 128L, 0L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa1_0(0xE0000000000L, 0L, 0L);
            }
            case 111: {
                return this.jjMoveStringLiteralDfa1_0(0L, 48L, 0L);
            }
            case 112: {
                return this.jjMoveStringLiteralDfa1_0(0xF00000000000L, 512L, 0L);
            }
            case 114: {
                return this.jjMoveStringLiteralDfa1_0(0x1000000000000L, 2L, 0L);
            }
            case 115: {
                return this.jjMoveStringLiteralDfa1_0(0x7E000000000000L, 0L, 0L);
            }
            case 116: {
                return this.jjMoveStringLiteralDfa1_0(2269814212194729984L, 1028L, 0L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa1_0(0L, 64L, 0L);
            }
            case 118: {
                return this.jjMoveStringLiteralDfa1_0(0x6000000000000000L, 0L, 0L);
            }
            case 119: {
                return this.jjMoveStringLiteralDfa1_0(Long.MIN_VALUE, 8L, 0L);
            }
            case 121: {
                return this.jjMoveStringLiteralDfa1_0(0L, 1L, 0L);
            }
            case 123: {
                return this.jjStopAtPos(0, 98);
            }
            case 124: {
                this.jjmatchedKind = 126;
                return this.jjMoveStringLiteralDfa1_0(0L, 0x40000000000000L, 128L);
            }
            case 125: {
                return this.jjStopAtPos(0, 99);
            }
            case 126: {
                return this.jjStopAtPos(0, 109);
            }
        }
        return this.jjMoveNfa_0(0, 0);
    }

    private int jjMoveStringLiteralDfa1_0(long active0, long active1, long active2) {
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(0, active0, active1, active2);
            return 1;
        }
        switch (this.curChar) {
            case 10: {
                if ((active0 & 4L) == 0L) break;
                return this.jjStopAtPos(1, 2);
            }
            case 34: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x4000000L, active2, 0L);
            }
            case 38: {
                if ((active1 & 0x20000000000000L) == 0L) break;
                return this.jjStopAtPos(1, 117);
            }
            case 42: {
                if ((active0 & 0x80L) == 0L) break;
                return this.jjStartNfaWithStates_0(1, 7, 58);
            }
            case 43: {
                if ((active1 & 0x80000000000000L) == 0L) break;
                return this.jjStopAtPos(1, 119);
            }
            case 45: {
                if ((active1 & 0x100000000000000L) == 0L) break;
                return this.jjStopAtPos(1, 120);
            }
            case 46: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 8192L);
            }
            case 58: {
                if ((active2 & 0x4000L) == 0L) break;
                return this.jjStopAtPos(1, 142);
            }
            case 60: {
                if ((active2 & 2L) != 0L) {
                    this.jjmatchedKind = 129;
                    this.jjmatchedPos = 1;
                }
                return this.jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 1024L);
            }
            case 61: {
                if ((active1 & 0x2000000000000L) != 0L) {
                    return this.jjStopAtPos(1, 113);
                }
                if ((active1 & 0x4000000000000L) != 0L) {
                    return this.jjStopAtPos(1, 114);
                }
                if ((active1 & 0x8000000000000L) != 0L) {
                    return this.jjStopAtPos(1, 115);
                }
                if ((active1 & 0x10000000000000L) != 0L) {
                    return this.jjStopAtPos(1, 116);
                }
                if ((active2 & 4L) != 0L) {
                    return this.jjStopAtPos(1, 130);
                }
                if ((active2 & 8L) != 0L) {
                    return this.jjStopAtPos(1, 131);
                }
                if ((active2 & 0x10L) != 0L) {
                    return this.jjStopAtPos(1, 132);
                }
                if ((active2 & 0x20L) != 0L) {
                    return this.jjStopAtPos(1, 133);
                }
                if ((active2 & 0x40L) != 0L) {
                    return this.jjStopAtPos(1, 134);
                }
                if ((active2 & 0x80L) != 0L) {
                    return this.jjStopAtPos(1, 135);
                }
                if ((active2 & 0x100L) != 0L) {
                    return this.jjStopAtPos(1, 136);
                }
                if ((active2 & 0x200L) == 0L) break;
                return this.jjStopAtPos(1, 137);
            }
            case 62: {
                if ((active1 & 0x1000000000000L) != 0L) {
                    return this.jjStopAtPos(1, 112);
                }
                if ((active2 & 0x10000L) != 0L) {
                    this.jjmatchedKind = 144;
                    this.jjmatchedPos = 1;
                }
                return this.jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0L, active2, 38912L);
            }
            case 97: {
                return this.jjMoveStringLiteralDfa2_0(active0, 19791477932032L, active1, 0L, active2, 0L);
            }
            case 98: {
                return this.jjMoveStringLiteralDfa2_0(active0, 2048L, active1, 0L, active2, 0L);
            }
            case 101: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x1040000400000L, active1, 2L, active2, 0L);
            }
            case 102: {
                if ((active0 & 0x400000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(1, 34, 142);
            }
            case 104: {
                return this.jjMoveStringLiteralDfa2_0(active0, -8970607507768344576L, active1, 0L, active2, 0L);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x60000000L, active1, 9L, active2, 0L);
            }
            case 108: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x82080000L, active1, 0L, active2, 0L);
            }
            case 109: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x1800000000L, active1, 0L, active2, 0L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0xE004000000L, active1, 0L, active2, 0L);
            }
            case 111: {
                if ((active0 & 0x800000L) != 0L) {
                    this.jjmatchedKind = 23;
                    this.jjmatchedPos = 1;
                } else if ((active1 & 4L) != 0L) {
                    return this.jjStartNfaWithStates_0(1, 66, 142);
                }
                return this.jjMoveStringLiteralDfa2_0(active0, 6917530140057542656L, active1, 128L, active2, 0L);
            }
            case 112: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0L, active1, 48L, active2, 0L);
            }
            case 114: {
                return this.jjMoveStringLiteralDfa2_0(active0, 2017718186178265088L, active1, 1536L, active2, 0L);
            }
            case 115: {
                return this.jjMoveStringLiteralDfa2_0(active0, 4096L, active1, 64L, active2, 0L);
            }
            case 116: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0xC000000000000L, active1, 0L, active2, 0L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x10880000000000L, active1, 0L, active2, 0L);
            }
            case 119: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x20000000000000L, active1, 0L, active2, 0L);
            }
            case 120: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x8000000L, active1, 256L, active2, 0L);
            }
            case 121: {
                return this.jjMoveStringLiteralDfa2_0(active0, 0x40000000008000L, active1, 0L, active2, 0L);
            }
            case 124: {
                if ((active1 & 0x40000000000000L) == 0L) break;
                return this.jjStopAtPos(1, 118);
            }
        }
        return this.jjStartNfa_0(0, active0, active1, active2);
    }

    private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1, long old2, long active2) {
        if (((active0 &= old0) | (active1 &= old1) | (active2 &= old2)) == 0L) {
            return this.jjStartNfa_0(0, old0, old1, old2);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(1, active0, active1, active2);
            return 2;
        }
        switch (this.curChar) {
            case 34: {
                if ((active1 & 0x4000000L) == 0L) break;
                return this.jjStopAtPos(2, 90);
            }
            case 46: {
                if ((active2 & 0x2000L) == 0L) break;
                return this.jjStopAtPos(2, 141);
            }
            case 61: {
                if ((active2 & 0x400L) != 0L) {
                    return this.jjStopAtPos(2, 138);
                }
                if ((active2 & 0x800L) == 0L) break;
                return this.jjStopAtPos(2, 139);
            }
            case 62: {
                if ((active2 & 0x8000L) != 0L) {
                    this.jjmatchedKind = 143;
                    this.jjmatchedPos = 2;
                }
                return this.jjMoveStringLiteralDfa3_0(active0, 0L, active1, 0L, active2, 4096L);
            }
            case 97: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0x4040000000C0000L, active1, 1024L, active2, 0L);
            }
            case 98: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0x800000000000L, active1, 0L, active2, 0L);
            }
            case 99: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0x100000000000L, active1, 0L, active2, 0L);
            }
            case 100: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0L, active1, 128L, active2, 0L);
            }
            case 101: {
                return this.jjMoveStringLiteralDfa3_0(active0, 16384L, active1, 113L, active2, 0L);
            }
            case 102: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0x400000L, active1, 0L, active2, 0L);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa3_0(active0, -6872457846995288064L, active1, 0L, active2, 0L);
            }
            case 108: {
                return this.jjMoveStringLiteralDfa3_0(active0, 4611694814788845568L, active1, 0L, active2, 0L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa3_0(active0, 18015499634868224L, active1, 0L, active2, 0L);
            }
            case 111: {
                return this.jjMoveStringLiteralDfa3_0(active0, 633320845090816L, active1, 512L, active2, 0L);
            }
            case 112: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0x10001800000000L, active1, 256L, active2, 0L);
            }
            case 113: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0L, active1, 2L, active2, 0L);
            }
            case 114: {
                if ((active0 & 0x100000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(2, 32, 142);
                }
                return this.jjMoveStringLiteralDfa3_0(active0, 0x308000000000000L, active1, 0L, active2, 0L);
            }
            case 115: {
                return this.jjMoveStringLiteralDfa3_0(active0, 137472579584L, active1, 0L, active2, 0L);
            }
            case 116: {
                if ((active0 & 0x4000000000L) != 0L) {
                    this.jjmatchedKind = 38;
                    this.jjmatchedPos = 2;
                }
                return this.jjMoveStringLiteralDfa3_0(active0, 284232480096256L, active1, 8L, active2, 0L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa3_0(active0, 0x800000005000000L, active1, 0L, active2, 0L);
            }
            case 119: {
                if ((active0 & 0x40000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(2, 42, 142);
            }
            case 121: {
                if ((active0 & 0x1000000000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(2, 60, 142);
            }
        }
        return this.jjStartNfa_0(1, active0, active1, active2);
    }

    private int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1, long old2, long active2) {
        if (((active0 &= old0) | (active1 &= old1) | (active2 &= old2)) == 0L) {
            return this.jjStartNfa_0(1, old0, old1, old2);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(2, active0, active1, active2);
            return 3;
        }
        switch (this.curChar) {
            case 61: {
                if ((active2 & 0x1000L) == 0L) break;
                return this.jjStopAtPos(3, 140);
            }
            case 97: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x40000000E0404000L, active1, 0L, active2, 0L);
            }
            case 98: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x1000000L, active1, 0L, active2, 0L);
            }
            case 99: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x40000000020000L, active1, 0L, active2, 0L);
            }
            case 100: {
                if ((active0 & 0x2000000000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(3, 61, 142);
            }
            case 101: {
                if ((active0 & 0x8000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 15, 142);
                }
                if ((active0 & 0x10000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 16, 142);
                }
                if ((active0 & 0x2000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 25, 142);
                }
                if ((active0 & 0x800000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 59, 142);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 0x10008008001000L, active1, 0L, active2, 0L);
            }
            case 103: {
                if ((active0 & 0x10000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(3, 40, 142);
            }
            case 104: {
                if ((active1 & 8L) == 0L) break;
                return this.jjStartNfaWithStates_0(3, 67, 142);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x8020000000000L, active1, 0L, active2, 0L);
            }
            case 107: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x100000000000L, active1, 0L, active2, 0L);
            }
            case 108: {
                if ((active0 & 0x80000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 43, 142);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, -9223231265006673920L, active1, 1L, active2, 0L);
            }
            case 109: {
                if ((active0 & 0x4000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(3, 26, 142);
            }
            case 110: {
                if ((active1 & 0x10L) != 0L) {
                    this.jjmatchedKind = 68;
                    this.jjmatchedPos = 3;
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 0x400000000000000L, active1, 1056L, active2, 0L);
            }
            case 111: {
                if ((active0 & 0x200000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 33, 142);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 0x300001000000000L, active1, 256L, active2, 0L);
            }
            case 114: {
                if ((active0 & 0x40000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 18, 142);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 0x2000000000000L, active1, 0L, active2, 0L);
            }
            case 115: {
                if ((active0 & 0x80000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 55, 142);
                }
                if ((active1 & 0x40L) != 0L) {
                    return this.jjStartNfaWithStates_0(3, 70, 142);
                }
                return this.jjMoveStringLiteralDfa4_0(active0, 0x10180000L, active1, 0L, active2, 0L);
            }
            case 116: {
                return this.jjMoveStringLiteralDfa4_0(active0, 10203605346813952L, active1, 0L, active2, 0L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x1000000000000L, active1, 130L, active2, 0L);
            }
            case 118: {
                return this.jjMoveStringLiteralDfa4_0(active0, 0x200000000000L, active1, 512L, active2, 0L);
            }
        }
        return this.jjStartNfa_0(2, active0, active1, active2);
    }

    private int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1, long old2, long active2) {
        if (((active0 &= old0) | (active1 &= old1) | (active2 &= old2)) == 0L) {
            return this.jjStartNfa_0(2, old0, old1, old2);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(3, active0, active1, 0L);
            return 4;
        }
        switch (this.curChar) {
            case 97: {
                return this.jjMoveStringLiteralDfa5_0(active0, 0x302000000000L, active1, 0L);
            }
            case 99: {
                return this.jjMoveStringLiteralDfa5_0(active0, 0x28000000000000L, active1, 0L);
            }
            case 100: {
                if ((active1 & 1L) == 0L) break;
                return this.jjStartNfaWithStates_0(4, 64, 142);
            }
            case 101: {
                if ((active0 & 0x10000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 28, 142);
                }
                if ((active0 & Long.MIN_VALUE) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 63, 142);
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 70403103924224L, active1, 0L);
            }
            case 104: {
                if ((active0 & 0x20000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 17, 142);
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 0x40000000000000L, active1, 0L);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa5_0(active0, 1266637397295104L, active1, 514L);
            }
            case 107: {
                if ((active0 & 0x4000L) == 0L) break;
                return this.jjStartNfaWithStates_0(4, 14, 142);
            }
            case 108: {
                if ((active0 & 0x20000000L) != 0L) {
                    this.jjmatchedKind = 29;
                    this.jjmatchedPos = 4;
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 0x41000000L, active1, 128L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa5_0(active0, 0x8000000L, active1, 0L);
            }
            case 114: {
                if ((active0 & 0x10000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 52, 142);
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 282093452007424L, active1, 256L);
            }
            case 115: {
                if ((active0 & 0x80000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 19, 142);
                }
                if ((active1 & 0x20L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 69, 142);
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 0x400000000000000L, active1, 1024L);
            }
            case 116: {
                if ((active0 & 0x100000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 20, 142);
                }
                if ((active0 & 0x80000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 31, 142);
                }
                if ((active0 & 0x2000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(4, 49, 142);
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 0x4000000000000000L, active1, 0L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa5_0(active0, 0x400000L, active1, 0L);
            }
            case 118: {
                return this.jjMoveStringLiteralDfa5_0(active0, 0x20000000000L, active1, 0L);
            }
            case 119: {
                if ((active0 & 0x100000000000000L) != 0L) {
                    this.jjmatchedKind = 56;
                    this.jjmatchedPos = 4;
                }
                return this.jjMoveStringLiteralDfa5_0(active0, 0x200000000000000L, active1, 0L);
            }
        }
        return this.jjStartNfa_0(3, active0, active1, 0L);
    }

    private int jjMoveStringLiteralDfa5_0(long old0, long active0, long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0L) {
            return this.jjStartNfa_0(3, old0, old1, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(4, active0, active1, 0L);
            return 5;
        }
        switch (this.curChar) {
            case 97: {
                return this.jjMoveStringLiteralDfa6_0(active0, 10240L, active1, 0L);
            }
            case 99: {
                if ((active0 & 0x800000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 47, 142);
                }
                if ((active0 & 0x4000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 50, 142);
                }
                return this.jjMoveStringLiteralDfa6_0(active0, 0x400000000000L, active1, 0L);
            }
            case 100: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x8000000L, active1, 512L);
            }
            case 101: {
                if ((active0 & 0x1000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 24, 142);
                }
                if ((active0 & 0x20000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 41, 142);
                }
                if ((active1 & 0x80L) == 0L) break;
                return this.jjStartNfaWithStates_0(5, 71, 142);
            }
            case 102: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x8000000000L, active1, 0L);
            }
            case 103: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x100000000000L, active1, 0L);
            }
            case 104: {
                if ((active0 & 0x20000000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(5, 53, 142);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x4400000000000000L, active1, 1024L);
            }
            case 108: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x40400000L, active1, 0L);
            }
            case 109: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x800000000L, active1, 0L);
            }
            case 110: {
                if ((active0 & 0x1000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 48, 142);
                }
                return this.jjMoveStringLiteralDfa6_0(active0, 0x2000200000L, active1, 0L);
            }
            case 114: {
                return this.jjMoveStringLiteralDfa6_0(active0, 0x40000000000000L, active1, 2L);
            }
            case 115: {
                if ((active0 & 0x200000000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(5, 57, 142);
            }
            case 116: {
                if ((active0 & 0x1000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 12, 142);
                }
                if ((active0 & 0x1000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(5, 36, 142);
                }
                return this.jjMoveStringLiteralDfa6_0(active0, 0x8200000000000L, active1, 256L);
            }
        }
        return this.jjStartNfa_0(4, active0, active1, 0L);
    }

    private int jjMoveStringLiteralDfa6_0(long old0, long active0, long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0L) {
            return this.jjStartNfa_0(4, old0, old1, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(5, active0, active1, 0L);
            return 6;
        }
        switch (this.curChar) {
            case 97: {
                return this.jjMoveStringLiteralDfa7_0(active0, 0x8000000000L, active1, 0L);
            }
            case 99: {
                return this.jjMoveStringLiteralDfa7_0(active0, 0x2000000800L, active1, 0L);
            }
            case 101: {
                if ((active0 & 0x100000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(6, 44, 142);
                }
                if ((active0 & 0x200000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(6, 45, 142);
                }
                return this.jjMoveStringLiteralDfa7_0(active0, 0x400000800000000L, active1, 514L);
            }
            case 102: {
                return this.jjMoveStringLiteralDfa7_0(active0, 0x8000000000000L, active1, 0L);
            }
            case 108: {
                return this.jjMoveStringLiteralDfa7_0(active0, 0x4000000000000000L, active1, 0L);
            }
            case 110: {
                if ((active0 & 0x2000L) == 0L) break;
                return this.jjStartNfaWithStates_0(6, 13, 142);
            }
            case 111: {
                return this.jjMoveStringLiteralDfa7_0(active0, 0x40000000000000L, active1, 0L);
            }
            case 115: {
                if ((active0 & 0x8000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(6, 27, 142);
                }
                if ((active1 & 0x100L) == 0L) break;
                return this.jjStartNfaWithStates_0(6, 72, 142);
            }
            case 116: {
                if ((active0 & 0x400000L) != 0L) {
                    return this.jjStartNfaWithStates_0(6, 22, 142);
                }
                return this.jjMoveStringLiteralDfa7_0(active0, 0x400000000000L, active1, 1024L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa7_0(active0, 0x200000L, active1, 0L);
            }
            case 121: {
                if ((active0 & 0x40000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(6, 30, 142);
            }
        }
        return this.jjStartNfa_0(5, active0, active1, 0L);
    }

    private int jjMoveStringLiteralDfa7_0(long old0, long active0, long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0L) {
            return this.jjStartNfa_0(5, old0, old1, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(6, active0, active1, 0L);
            return 7;
        }
        switch (this.curChar) {
            case 99: {
                return this.jjMoveStringLiteralDfa8_0(active0, 0x8000000000L, active1, 0L);
            }
            case 101: {
                if ((active0 & 0x200000L) != 0L) {
                    return this.jjStartNfaWithStates_0(7, 21, 142);
                }
                if ((active0 & 0x4000000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(7, 62, 142);
                }
                return this.jjMoveStringLiteralDfa8_0(active0, 0x402000000000L, active1, 0L);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa8_0(active0, 0L, active1, 1024L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa8_0(active0, 0x440000800000000L, active1, 0L);
            }
            case 112: {
                if ((active0 & 0x8000000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(7, 51, 142);
            }
            case 115: {
                if ((active1 & 2L) != 0L) {
                    return this.jjStartNfaWithStates_0(7, 65, 142);
                }
                if ((active1 & 0x200L) == 0L) break;
                return this.jjStartNfaWithStates_0(7, 73, 142);
            }
            case 116: {
                if ((active0 & 0x800L) == 0L) break;
                return this.jjStartNfaWithStates_0(7, 11, 142);
            }
        }
        return this.jjStartNfa_0(6, active0, active1, 0L);
    }

    private int jjMoveStringLiteralDfa8_0(long old0, long active0, long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0L) {
            return this.jjStartNfa_0(6, old0, old1, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(7, active0, active1, 0L);
            return 8;
        }
        switch (this.curChar) {
            case 100: {
                if ((active0 & 0x400000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(8, 46, 142);
            }
            case 101: {
                if ((active0 & 0x8000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(8, 39, 142);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa9_0(active0, 0x40000000000000L, active1, 0L);
            }
            case 111: {
                return this.jjMoveStringLiteralDfa9_0(active0, 0x2000000000L, active1, 0L);
            }
            case 116: {
                if ((active0 & 0x400000000000000L) != 0L) {
                    return this.jjStartNfaWithStates_0(8, 58, 142);
                }
                return this.jjMoveStringLiteralDfa9_0(active0, 0x800000000L, active1, 0L);
            }
            case 118: {
                return this.jjMoveStringLiteralDfa9_0(active0, 0L, active1, 1024L);
            }
        }
        return this.jjStartNfa_0(7, active0, active1, 0L);
    }

    private int jjMoveStringLiteralDfa9_0(long old0, long active0, long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0L) {
            return this.jjStartNfa_0(7, old0, old1, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(8, active0, active1, 0L);
            return 9;
        }
        switch (this.curChar) {
            case 101: {
                if ((active1 & 0x400L) == 0L) break;
                return this.jjStartNfaWithStates_0(9, 74, 142);
            }
            case 102: {
                if ((active0 & 0x2000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(9, 37, 142);
            }
            case 115: {
                if ((active0 & 0x800000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(9, 35, 142);
            }
            case 122: {
                return this.jjMoveStringLiteralDfa10_0(active0, 0x40000000000000L, active1, 0L);
            }
        }
        return this.jjStartNfa_0(8, active0, active1, 0L);
    }

    private int jjMoveStringLiteralDfa10_0(long old0, long active0, long old1, long active1) {
        if (((active0 &= old0) | (active1 &= old1)) == 0L) {
            return this.jjStartNfa_0(8, old0, old1, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(9, active0, 0L, 0L);
            return 10;
        }
        switch (this.curChar) {
            case 101: {
                return this.jjMoveStringLiteralDfa11_0(active0, 0x40000000000000L);
            }
        }
        return this.jjStartNfa_0(9, active0, 0L, 0L);
    }

    private int jjMoveStringLiteralDfa11_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(9, old0, 0L, 0L);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(10, active0, 0L, 0L);
            return 11;
        }
        switch (this.curChar) {
            case 100: {
                if ((active0 & 0x40000000000000L) == 0L) break;
                return this.jjStartNfaWithStates_0(11, 54, 142);
            }
        }
        return this.jjStartNfa_0(10, active0, 0L, 0L);
    }

    private int jjStartNfaWithStates_0(int pos, int kind, int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_0(state, pos + 1);
    }

    private int jjMoveNfa_0(int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 141;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < 64) {
                long l = 1L << this.curChar;
                block211: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x3FF000000000000L & l) != 0L) {
                                if (kind > 76) {
                                    kind = 76;
                                }
                                this.jjCheckNAddStates(0, 14);
                            } else if ((0x100001200L & l) != 0L) {
                                if (kind > 1) {
                                    kind = 1;
                                }
                            } else if (this.curChar == 47) {
                                this.jjAddStates(15, 16);
                            } else if (this.curChar == 36) {
                                if (kind > 93) {
                                    kind = 93;
                                }
                                this.jjCheckNAddTwoStates(43, 49);
                            } else if (this.curChar == 34) {
                                this.jjCheckNAddStates(17, 20);
                            } else if (this.curChar == 39) {
                                this.jjAddStates(21, 23);
                            } else if (this.curChar == 46) {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                            }
                            if (this.curChar != 48) break;
                            this.jjAddStates(24, 31);
                            break;
                        }
                        case 43: 
                        case 142: {
                            if ((0x3FF00100FFFC1FFL & l) == 0L) continue block211;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 141: {
                            if ((0xFFFFFFFBFFFFDBFFL & l) != 0L) {
                                this.jjCheckNAddStates(17, 20);
                                break;
                            }
                            if (this.curChar != 34 || kind <= 89) continue block211;
                            kind = 89;
                            break;
                        }
                        case 56: {
                            if (this.curChar == 42) {
                                this.jjstateSet[this.jjnewStateCnt++] = 58;
                                break;
                            }
                            if (this.curChar != 47) break;
                            if (kind > 5) {
                                kind = 5;
                            }
                            this.jjCheckNAdd(57);
                            break;
                        }
                        case 1: {
                            if (this.curChar != 46) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 2;
                            break;
                        }
                        case 2: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(32, 35);
                            break;
                        }
                        case 3: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(3, 4);
                            break;
                        }
                        case 4: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddTwoStates(5, 10);
                            break;
                        }
                        case 6: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjCheckNAdd(7);
                            break;
                        }
                        case 7: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(36, 38);
                            break;
                        }
                        case 8: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(8, 9);
                            break;
                        }
                        case 9: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddTwoStates(7, 10);
                            break;
                        }
                        case 11: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(39, 42);
                            break;
                        }
                        case 12: {
                            if (this.curChar != 39) break;
                            this.jjAddStates(21, 23);
                            break;
                        }
                        case 13: {
                            if ((0xFFFFFF7FFFFFDBFFL & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 14: {
                            if (this.curChar != 39 || kind <= 88) continue block211;
                            kind = 88;
                            break;
                        }
                        case 16: {
                            if ((0x8400000000L & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 17: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(18, 14);
                            break;
                        }
                        case 18: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 19: {
                            if ((0xF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 20;
                            break;
                        }
                        case 20: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(18);
                            break;
                        }
                        case 22: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 23;
                            break;
                        }
                        case 23: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 24;
                            break;
                        }
                        case 24: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 25;
                            break;
                        }
                        case 25: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 27: {
                            if (this.curChar != 34) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 28: {
                            if ((0xFFFFFFFBFFFFDBFFL & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 30: {
                            if ((0x8400000000L & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 32: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 33;
                            break;
                        }
                        case 33: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 34;
                            break;
                        }
                        case 34: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 35;
                            break;
                        }
                        case 35: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 37: {
                            if (this.curChar != 34 || kind <= 89) continue block211;
                            kind = 89;
                            break;
                        }
                        case 38: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(43, 47);
                            break;
                        }
                        case 39: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 40: {
                            if ((0xF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 41;
                            break;
                        }
                        case 41: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(39);
                            break;
                        }
                        case 42: {
                            if (this.curChar != 36) continue block211;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 45: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 46;
                            break;
                        }
                        case 46: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 47;
                            break;
                        }
                        case 47: 
                        case 53: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(48);
                            break;
                        }
                        case 48: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 51: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 52;
                            break;
                        }
                        case 52: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 53;
                            break;
                        }
                        case 55: {
                            if (this.curChar != 47) break;
                            this.jjAddStates(15, 16);
                            break;
                        }
                        case 57: {
                            if ((0xFFFFFFFFFFFFDBFFL & l) == 0L) continue block211;
                            if (kind > 5) {
                                kind = 5;
                            }
                            this.jjCheckNAdd(57);
                            break;
                        }
                        case 58: {
                            if (this.curChar != 42) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 59;
                            break;
                        }
                        case 59: {
                            if ((0xFFFF7FFFFFFFFFFFL & l) == 0L || kind <= 6) continue block211;
                            kind = 6;
                            break;
                        }
                        case 60: {
                            if (this.curChar != 42) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 58;
                            break;
                        }
                        case 61: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAddStates(0, 14);
                            break;
                        }
                        case 62: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(62, 63);
                            break;
                        }
                        case 63: 
                        case 99: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(64);
                            break;
                        }
                        case 65: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(65, 66);
                            break;
                        }
                        case 66: 
                        case 110: {
                            if ((0x3FF000000000000L & l) == 0L || kind <= 76) continue block211;
                            kind = 76;
                            break;
                        }
                        case 67: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(67, 68);
                            break;
                        }
                        case 68: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(69, 10);
                            break;
                        }
                        case 70: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjCheckNAdd(71);
                            break;
                        }
                        case 71: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(48, 50);
                            break;
                        }
                        case 72: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(72, 73);
                            break;
                        }
                        case 73: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(71, 10);
                            break;
                        }
                        case 74: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(51, 54);
                            break;
                        }
                        case 75: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(75, 76);
                            break;
                        }
                        case 76: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(77);
                            break;
                        }
                        case 78: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjCheckNAdd(79);
                            break;
                        }
                        case 79: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(55, 57);
                            break;
                        }
                        case 80: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(80, 81);
                            break;
                        }
                        case 81: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddTwoStates(79, 10);
                            break;
                        }
                        case 82: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(58, 61);
                            break;
                        }
                        case 83: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(83, 84);
                            break;
                        }
                        case 84: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(85);
                            break;
                        }
                        case 85: {
                            if (this.curChar != 46) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(62, 64);
                            break;
                        }
                        case 86: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(65, 68);
                            break;
                        }
                        case 87: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(87, 88);
                            break;
                        }
                        case 88: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddTwoStates(89, 10);
                            break;
                        }
                        case 90: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjCheckNAdd(91);
                            break;
                        }
                        case 91: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(69, 71);
                            break;
                        }
                        case 92: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(92, 93);
                            break;
                        }
                        case 93: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddTwoStates(91, 10);
                            break;
                        }
                        case 94: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(72, 75);
                            break;
                        }
                        case 95: {
                            if (this.curChar != 48) break;
                            this.jjAddStates(24, 31);
                            break;
                        }
                        case 97: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(76, 78);
                            break;
                        }
                        case 98: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(98, 99);
                            break;
                        }
                        case 100: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(79, 81);
                            break;
                        }
                        case 101: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(101, 102);
                            break;
                        }
                        case 102: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(64);
                            break;
                        }
                        case 104: {
                            if ((0x3000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(82, 84);
                            break;
                        }
                        case 105: {
                            if ((0x3000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(105, 106);
                            break;
                        }
                        case 106: {
                            if ((0x3000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(64);
                            break;
                        }
                        case 108: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAddTwoStates(109, 110);
                            break;
                        }
                        case 109: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(109, 110);
                            break;
                        }
                        case 111: {
                            if ((0xFF000000000000L & l) == 0L) continue block211;
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAddTwoStates(112, 113);
                            break;
                        }
                        case 112: {
                            if ((0xFF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(112, 113);
                            break;
                        }
                        case 113: {
                            if ((0xFF000000000000L & l) == 0L || kind <= 76) continue block211;
                            kind = 76;
                            break;
                        }
                        case 115: {
                            if ((0x3000000000000L & l) == 0L) continue block211;
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAddTwoStates(116, 117);
                            break;
                        }
                        case 116: {
                            if ((0x3000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(116, 117);
                            break;
                        }
                        case 117: {
                            if ((0x3000000000000L & l) == 0L || kind <= 76) continue block211;
                            kind = 76;
                            break;
                        }
                        case 119: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(85, 87);
                            break;
                        }
                        case 120: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(120, 121);
                            break;
                        }
                        case 121: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(122);
                            break;
                        }
                        case 122: {
                            if (this.curChar != 46) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 123;
                            break;
                        }
                        case 123: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(88, 90);
                            break;
                        }
                        case 124: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(124, 125);
                            break;
                        }
                        case 125: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAdd(126);
                            break;
                        }
                        case 127: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 128;
                            break;
                        }
                        case 128: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(91, 93);
                            break;
                        }
                        case 129: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(129, 130);
                            break;
                        }
                        case 130: 
                        case 140: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAdd(10);
                            break;
                        }
                        case 132: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddStates(94, 97);
                            break;
                        }
                        case 133: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(133, 134);
                            break;
                        }
                        case 134: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(135, 136);
                            break;
                        }
                        case 135: {
                            if (this.curChar != 46) break;
                            this.jjCheckNAdd(136);
                            break;
                        }
                        case 137: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 138;
                            break;
                        }
                        case 138: {
                            if ((0x3FF000000000000L & l) == 0L) continue block211;
                            if (kind > 81) {
                                kind = 81;
                            }
                            this.jjCheckNAddStates(98, 100);
                            break;
                        }
                        case 139: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(139, 140);
                            break;
                        }
                    }
                } while (i != startsAt);
            } else if (this.curChar < 128) {
                long l = 1L << (this.curChar & 0x3F);
                block212: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0L) {
                                if (kind > 93) {
                                    kind = 93;
                                }
                                this.jjCheckNAddTwoStates(43, 49);
                                break;
                            }
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 50;
                            break;
                        }
                        case 142: {
                            if ((0x87FFFFFE87FFFFFEL & l) != 0L) {
                                if (kind > 93) {
                                    kind = 93;
                                }
                                this.jjCheckNAddTwoStates(43, 49);
                                break;
                            }
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 44;
                            break;
                        }
                        case 141: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) != 0L) {
                                this.jjCheckNAddStates(17, 20);
                            } else if (this.curChar == 92) {
                                this.jjstateSet[this.jjnewStateCnt++] = 31;
                            }
                            if (this.curChar != 92) break;
                            this.jjAddStates(101, 103);
                            break;
                        }
                        case 3: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(104, 105);
                            break;
                        }
                        case 5: {
                            if ((0x2000000020L & l) == 0L) break;
                            this.jjAddStates(106, 107);
                            break;
                        }
                        case 8: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(108, 109);
                            break;
                        }
                        case 10: {
                            if ((0x5000000050L & l) == 0L || kind <= 81) continue block212;
                            kind = 81;
                            break;
                        }
                        case 13: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 15: {
                            if (this.curChar != 92) break;
                            this.jjAddStates(110, 112);
                            break;
                        }
                        case 16: {
                            if ((0x14404410000000L & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 21: {
                            if (this.curChar != 117) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 22;
                            break;
                        }
                        case 22: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 23;
                            break;
                        }
                        case 23: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 24;
                            break;
                        }
                        case 24: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 25;
                            break;
                        }
                        case 25: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAdd(14);
                            break;
                        }
                        case 26: {
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 21;
                            break;
                        }
                        case 28: {
                            if ((0xFFFFFFFFEFFFFFFFL & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 29: {
                            if (this.curChar != 92) break;
                            this.jjAddStates(101, 103);
                            break;
                        }
                        case 30: {
                            if ((0x14404410000000L & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 31: {
                            if (this.curChar != 117) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 32;
                            break;
                        }
                        case 32: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 33;
                            break;
                        }
                        case 33: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 34;
                            break;
                        }
                        case 34: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 35;
                            break;
                        }
                        case 35: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 36: {
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 31;
                            break;
                        }
                        case 42: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0L) continue block212;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 43: {
                            if ((0x87FFFFFE87FFFFFEL & l) == 0L) continue block212;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 44: {
                            if (this.curChar != 117) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 45;
                            break;
                        }
                        case 45: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 46;
                            break;
                        }
                        case 46: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 47;
                            break;
                        }
                        case 47: 
                        case 53: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAdd(48);
                            break;
                        }
                        case 48: {
                            if ((0x7E0000007EL & l) == 0L) continue block212;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 49: {
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 44;
                            break;
                        }
                        case 50: {
                            if (this.curChar != 117) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 51;
                            break;
                        }
                        case 51: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 52;
                            break;
                        }
                        case 52: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 53;
                            break;
                        }
                        case 54: {
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 50;
                            break;
                        }
                        case 57: {
                            if (kind > 5) {
                                kind = 5;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 57;
                            break;
                        }
                        case 59: {
                            if (kind <= 6) break;
                            kind = 6;
                            break;
                        }
                        case 62: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(113, 114);
                            break;
                        }
                        case 64: {
                            if ((0x100000001000L & l) == 0L || kind <= 75) continue block212;
                            kind = 75;
                            break;
                        }
                        case 65: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(115, 116);
                            break;
                        }
                        case 67: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(117, 118);
                            break;
                        }
                        case 69: {
                            if ((0x2000000020L & l) == 0L) break;
                            this.jjAddStates(119, 120);
                            break;
                        }
                        case 72: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(121, 122);
                            break;
                        }
                        case 75: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(123, 124);
                            break;
                        }
                        case 77: {
                            if ((0x2000000020L & l) == 0L) break;
                            this.jjAddStates(125, 126);
                            break;
                        }
                        case 80: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(127, 128);
                            break;
                        }
                        case 83: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(129, 130);
                            break;
                        }
                        case 87: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(131, 132);
                            break;
                        }
                        case 89: {
                            if ((0x2000000020L & l) == 0L) break;
                            this.jjAddStates(133, 134);
                            break;
                        }
                        case 92: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(135, 136);
                            break;
                        }
                        case 96: {
                            if ((0x100000001000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 97;
                            break;
                        }
                        case 97: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAddStates(76, 78);
                            break;
                        }
                        case 98: {
                            if ((0x7E8000007EL & l) == 0L) break;
                            this.jjCheckNAddTwoStates(98, 99);
                            break;
                        }
                        case 99: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAdd(64);
                            break;
                        }
                        case 101: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(137, 138);
                            break;
                        }
                        case 103: {
                            if ((0x400000004L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 104;
                            break;
                        }
                        case 105: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(139, 140);
                            break;
                        }
                        case 107: {
                            if ((0x100000001000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 108;
                            break;
                        }
                        case 108: {
                            if ((0x7E0000007EL & l) == 0L) continue block212;
                            if (kind > 76) {
                                kind = 76;
                            }
                            this.jjCheckNAddTwoStates(109, 110);
                            break;
                        }
                        case 109: {
                            if ((0x7E8000007EL & l) == 0L) break;
                            this.jjCheckNAddTwoStates(109, 110);
                            break;
                        }
                        case 110: {
                            if ((0x7E0000007EL & l) == 0L || kind <= 76) continue block212;
                            kind = 76;
                            break;
                        }
                        case 112: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(141, 142);
                            break;
                        }
                        case 114: {
                            if ((0x400000004L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 115;
                            break;
                        }
                        case 116: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(143, 144);
                            break;
                        }
                        case 118: {
                            if ((0x100000001000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(119, 122);
                            break;
                        }
                        case 119: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAddStates(85, 87);
                            break;
                        }
                        case 120: {
                            if ((0x7E8000007EL & l) == 0L) break;
                            this.jjCheckNAddTwoStates(120, 121);
                            break;
                        }
                        case 121: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAdd(122);
                            break;
                        }
                        case 123: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAddStates(88, 90);
                            break;
                        }
                        case 124: {
                            if ((0x7E8000007EL & l) == 0L) break;
                            this.jjCheckNAddTwoStates(124, 125);
                            break;
                        }
                        case 125: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAdd(126);
                            break;
                        }
                        case 126: {
                            if ((0x1000000010000L & l) == 0L) break;
                            this.jjAddStates(145, 146);
                            break;
                        }
                        case 129: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(147, 148);
                            break;
                        }
                        case 131: {
                            if ((0x100000001000000L & l) == 0L) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 132;
                            break;
                        }
                        case 132: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAddStates(94, 97);
                            break;
                        }
                        case 133: {
                            if ((0x7E8000007EL & l) == 0L) break;
                            this.jjCheckNAddTwoStates(133, 134);
                            break;
                        }
                        case 134: {
                            if ((0x7E0000007EL & l) == 0L) break;
                            this.jjCheckNAddTwoStates(135, 136);
                            break;
                        }
                        case 136: {
                            if ((0x1000000010000L & l) == 0L) break;
                            this.jjAddStates(149, 150);
                            break;
                        }
                        case 139: {
                            if (this.curChar != 95) break;
                            this.jjAddStates(151, 152);
                            break;
                        }
                    }
                } while (i != startsAt);
            } else {
                int hiByte = this.curChar >> 8;
                int i1 = hiByte >> 6;
                long l1 = 1L << (hiByte & 0x3F);
                int i2 = (this.curChar & 0xFF) >> 6;
                long l2 = 1L << (this.curChar & 0x3F);
                block213: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if (GeneratedJavaParserTokenManager.jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 1) {
                                kind = 1;
                            }
                            if (!GeneratedJavaParserTokenManager.jjCanMove_2(hiByte, i1, i2, l1, l2)) continue block213;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 43: 
                        case 142: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_3(hiByte, i1, i2, l1, l2)) continue block213;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 28: 
                        case 141: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_1(hiByte, i1, i2, l1, l2)) continue block213;
                            this.jjCheckNAddStates(17, 20);
                            break;
                        }
                        case 13: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_1(hiByte, i1, i2, l1, l2)) continue block213;
                            this.jjstateSet[this.jjnewStateCnt++] = 14;
                            break;
                        }
                        case 42: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_2(hiByte, i1, i2, l1, l2)) continue block213;
                            if (kind > 93) {
                                kind = 93;
                            }
                            this.jjCheckNAddTwoStates(43, 49);
                            break;
                        }
                        case 57: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_1(hiByte, i1, i2, l1, l2)) continue block213;
                            if (kind > 5) {
                                kind = 5;
                            }
                            this.jjstateSet[this.jjnewStateCnt++] = 57;
                            break;
                        }
                        case 59: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_1(hiByte, i1, i2, l1, l2) || kind <= 6) continue block213;
                            kind = 6;
                            break;
                        }
                        default: {
                            if (i1 != 0 && l1 != 0L && i2 != 0 && l2 != 0L) continue block213;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            i = this.jjnewStateCnt;
            this.jjnewStateCnt = startsAt;
            startsAt = 141 - this.jjnewStateCnt;
            if (i == startsAt) {
                return curPos;
            }
            try {
                this.curChar = this.input_stream.readChar();
            } catch (IOException e) {
                return curPos;
            }
        }
    }

    private int jjMoveStringLiteralDfa0_1() {
        switch (this.curChar) {
            case 42: {
                return this.jjMoveStringLiteralDfa1_1(256L);
            }
        }
        return 1;
    }

    private int jjMoveStringLiteralDfa1_1(long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            return 1;
        }
        switch (this.curChar) {
            case 47: {
                if ((active0 & 0x100L) == 0L) break;
                return this.jjStopAtPos(1, 8);
            }
            default: {
                return 2;
            }
        }
        return 2;
    }

    private int jjMoveStringLiteralDfa0_2() {
        switch (this.curChar) {
            case 42: {
                return this.jjMoveStringLiteralDfa1_2(512L);
            }
        }
        return 1;
    }

    private int jjMoveStringLiteralDfa1_2(long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            return 1;
        }
        switch (this.curChar) {
            case 47: {
                if ((active0 & 0x200L) == 0L) break;
                return this.jjStopAtPos(1, 9);
            }
            default: {
                return 2;
            }
        }
        return 2;
    }

    private final int jjStopStringLiteralDfa_3(int pos, long active0, long active1) {
        switch (pos) {
            case 0: {
                if ((active1 & 0x8000000L) != 0L) {
                    this.jjmatchedKind = 92;
                    return -1;
                }
                return -1;
            }
            case 1: {
                if ((active1 & 0x8000000L) != 0L) {
                    if (this.jjmatchedPos == 0) {
                        this.jjmatchedKind = 92;
                        this.jjmatchedPos = 0;
                    }
                    return -1;
                }
                return -1;
            }
        }
        return -1;
    }

    private final int jjStartNfa_3(int pos, long active0, long active1) {
        return this.jjMoveNfa_3(this.jjStopStringLiteralDfa_3(pos, active0, active1), pos + 1);
    }

    private int jjMoveStringLiteralDfa0_3() {
        switch (this.curChar) {
            case 34: {
                return this.jjMoveStringLiteralDfa1_3(0x8000000L);
            }
        }
        return this.jjMoveNfa_3(0, 0);
    }

    private int jjMoveStringLiteralDfa1_3(long active1) {
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_3(0, 0L, active1);
            return 1;
        }
        switch (this.curChar) {
            case 34: {
                return this.jjMoveStringLiteralDfa2_3(active1, 0x8000000L);
            }
        }
        return this.jjStartNfa_3(0, 0L, active1);
    }

    private int jjMoveStringLiteralDfa2_3(long old1, long active1) {
        if ((active1 &= old1) == 0L) {
            return this.jjStartNfa_3(0, 0L, old1);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_3(1, 0L, active1);
            return 2;
        }
        switch (this.curChar) {
            case 34: {
                if ((active1 & 0x8000000L) == 0L) break;
                return this.jjStopAtPos(2, 91);
            }
        }
        return this.jjStartNfa_3(1, 0L, active1);
    }

    private int jjMoveNfa_3(int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 3;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < 64) {
                long l = 1L << this.curChar;
                block14: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if (kind <= 92) break;
                            kind = 92;
                            break;
                        }
                        case 1: {
                            if (this.curChar != 34 || kind <= 92) continue block14;
                            kind = 92;
                            break;
                        }
                    }
                } while (i != startsAt);
            } else if (this.curChar < 128) {
                long l = 1L << (this.curChar & 0x3F);
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if (kind > 92) {
                                kind = 92;
                            }
                            if (this.curChar != 92) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 1;
                            break;
                        }
                        case 2: {
                            if (kind <= 92) break;
                            kind = 92;
                            break;
                        }
                    }
                } while (i != startsAt);
            } else {
                int hiByte = this.curChar >> 8;
                int i1 = hiByte >> 6;
                long l1 = 1L << (hiByte & 0x3F);
                int i2 = (this.curChar & 0xFF) >> 6;
                long l2 = 1L << (this.curChar & 0x3F);
                block16: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if (!GeneratedJavaParserTokenManager.jjCanMove_1(hiByte, i1, i2, l1, l2) || kind <= 92) continue block16;
                            kind = 92;
                            break;
                        }
                        default: {
                            if (i1 != 0 && l1 != 0L && i2 != 0 && l2 != 0L) continue block16;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            i = this.jjnewStateCnt;
            this.jjnewStateCnt = startsAt;
            startsAt = 3 - this.jjnewStateCnt;
            if (i == startsAt) {
                return curPos;
            }
            try {
                this.curChar = this.input_stream.readChar();
            } catch (IOException e) {
                return curPos;
            }
        }
    }

    protected Token jjFillToken() {
        String im = jjstrLiteralImages[this.jjmatchedKind];
        String curTokenImage = im == null ? this.input_stream.getImage() : im;
        int beginLine = this.input_stream.getBeginLine();
        int beginColumn = this.input_stream.getBeginColumn();
        int endLine = this.input_stream.getEndLine();
        int endColumn = this.input_stream.getEndColumn();
        Token t = Token.newToken(this.jjmatchedKind);
        t.kind = this.jjmatchedKind;
        t.image = curTokenImage;
        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;
        return t;
    }

    private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0: {
                return (jjbitVec0[i2] & l2) != 0L;
            }
            case 22: {
                return (jjbitVec1[i2] & l2) != 0L;
            }
            case 24: {
                return (jjbitVec2[i2] & l2) != 0L;
            }
            case 32: {
                return (jjbitVec3[i2] & l2) != 0L;
            }
            case 48: {
                return (jjbitVec4[i2] & l2) != 0L;
            }
            case 254: {
                return (jjbitVec5[i2] & l2) != 0L;
            }
        }
        return false;
    }

    private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0: {
                return (jjbitVec8[i2] & l2) != 0L;
            }
        }
        return (jjbitVec6[i1] & l1) != 0L;
    }

    private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0: {
                return (jjbitVec10[i2] & l2) != 0L;
            }
            case 2: {
                return (jjbitVec11[i2] & l2) != 0L;
            }
            case 3: {
                return (jjbitVec12[i2] & l2) != 0L;
            }
            case 4: {
                return (jjbitVec13[i2] & l2) != 0L;
            }
            case 5: {
                return (jjbitVec14[i2] & l2) != 0L;
            }
            case 6: {
                return (jjbitVec15[i2] & l2) != 0L;
            }
            case 7: {
                return (jjbitVec16[i2] & l2) != 0L;
            }
            case 8: {
                return (jjbitVec17[i2] & l2) != 0L;
            }
            case 9: {
                return (jjbitVec18[i2] & l2) != 0L;
            }
            case 10: {
                return (jjbitVec19[i2] & l2) != 0L;
            }
            case 11: {
                return (jjbitVec20[i2] & l2) != 0L;
            }
            case 12: {
                return (jjbitVec21[i2] & l2) != 0L;
            }
            case 13: {
                return (jjbitVec22[i2] & l2) != 0L;
            }
            case 14: {
                return (jjbitVec23[i2] & l2) != 0L;
            }
            case 15: {
                return (jjbitVec24[i2] & l2) != 0L;
            }
            case 16: {
                return (jjbitVec25[i2] & l2) != 0L;
            }
            case 18: {
                return (jjbitVec26[i2] & l2) != 0L;
            }
            case 19: {
                return (jjbitVec27[i2] & l2) != 0L;
            }
            case 20: {
                return (jjbitVec6[i2] & l2) != 0L;
            }
            case 22: {
                return (jjbitVec28[i2] & l2) != 0L;
            }
            case 23: {
                return (jjbitVec29[i2] & l2) != 0L;
            }
            case 24: {
                return (jjbitVec30[i2] & l2) != 0L;
            }
            case 25: {
                return (jjbitVec31[i2] & l2) != 0L;
            }
            case 26: {
                return (jjbitVec32[i2] & l2) != 0L;
            }
            case 27: {
                return (jjbitVec33[i2] & l2) != 0L;
            }
            case 28: {
                return (jjbitVec34[i2] & l2) != 0L;
            }
            case 29: {
                return (jjbitVec35[i2] & l2) != 0L;
            }
            case 31: {
                return (jjbitVec36[i2] & l2) != 0L;
            }
            case 32: {
                return (jjbitVec37[i2] & l2) != 0L;
            }
            case 33: {
                return (jjbitVec38[i2] & l2) != 0L;
            }
            case 44: {
                return (jjbitVec39[i2] & l2) != 0L;
            }
            case 45: {
                return (jjbitVec40[i2] & l2) != 0L;
            }
            case 46: {
                return (jjbitVec41[i2] & l2) != 0L;
            }
            case 48: {
                return (jjbitVec42[i2] & l2) != 0L;
            }
            case 49: {
                return (jjbitVec43[i2] & l2) != 0L;
            }
            case 77: {
                return (jjbitVec44[i2] & l2) != 0L;
            }
            case 159: {
                return (jjbitVec45[i2] & l2) != 0L;
            }
            case 164: {
                return (jjbitVec46[i2] & l2) != 0L;
            }
            case 166: {
                return (jjbitVec47[i2] & l2) != 0L;
            }
            case 167: {
                return (jjbitVec48[i2] & l2) != 0L;
            }
            case 168: {
                return (jjbitVec49[i2] & l2) != 0L;
            }
            case 169: {
                return (jjbitVec50[i2] & l2) != 0L;
            }
            case 170: {
                return (jjbitVec51[i2] & l2) != 0L;
            }
            case 171: {
                return (jjbitVec52[i2] & l2) != 0L;
            }
            case 215: {
                return (jjbitVec53[i2] & l2) != 0L;
            }
            case 250: {
                return (jjbitVec54[i2] & l2) != 0L;
            }
            case 251: {
                return (jjbitVec55[i2] & l2) != 0L;
            }
            case 253: {
                return (jjbitVec56[i2] & l2) != 0L;
            }
            case 254: {
                return (jjbitVec57[i2] & l2) != 0L;
            }
            case 255: {
                return (jjbitVec58[i2] & l2) != 0L;
            }
        }
        return (jjbitVec9[i1] & l1) != 0L;
    }

    private static final boolean jjCanMove_3(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0: {
                return (jjbitVec59[i2] & l2) != 0L;
            }
            case 2: {
                return (jjbitVec11[i2] & l2) != 0L;
            }
            case 3: {
                return (jjbitVec60[i2] & l2) != 0L;
            }
            case 4: {
                return (jjbitVec61[i2] & l2) != 0L;
            }
            case 5: {
                return (jjbitVec62[i2] & l2) != 0L;
            }
            case 6: {
                return (jjbitVec63[i2] & l2) != 0L;
            }
            case 7: {
                return (jjbitVec64[i2] & l2) != 0L;
            }
            case 8: {
                return (jjbitVec65[i2] & l2) != 0L;
            }
            case 9: {
                return (jjbitVec66[i2] & l2) != 0L;
            }
            case 10: {
                return (jjbitVec67[i2] & l2) != 0L;
            }
            case 11: {
                return (jjbitVec68[i2] & l2) != 0L;
            }
            case 12: {
                return (jjbitVec69[i2] & l2) != 0L;
            }
            case 13: {
                return (jjbitVec70[i2] & l2) != 0L;
            }
            case 14: {
                return (jjbitVec71[i2] & l2) != 0L;
            }
            case 15: {
                return (jjbitVec72[i2] & l2) != 0L;
            }
            case 16: {
                return (jjbitVec73[i2] & l2) != 0L;
            }
            case 18: {
                return (jjbitVec26[i2] & l2) != 0L;
            }
            case 19: {
                return (jjbitVec74[i2] & l2) != 0L;
            }
            case 20: {
                return (jjbitVec6[i2] & l2) != 0L;
            }
            case 22: {
                return (jjbitVec28[i2] & l2) != 0L;
            }
            case 23: {
                return (jjbitVec75[i2] & l2) != 0L;
            }
            case 24: {
                return (jjbitVec76[i2] & l2) != 0L;
            }
            case 25: {
                return (jjbitVec77[i2] & l2) != 0L;
            }
            case 26: {
                return (jjbitVec78[i2] & l2) != 0L;
            }
            case 27: {
                return (jjbitVec79[i2] & l2) != 0L;
            }
            case 28: {
                return (jjbitVec80[i2] & l2) != 0L;
            }
            case 29: {
                return (jjbitVec81[i2] & l2) != 0L;
            }
            case 31: {
                return (jjbitVec36[i2] & l2) != 0L;
            }
            case 32: {
                return (jjbitVec82[i2] & l2) != 0L;
            }
            case 33: {
                return (jjbitVec38[i2] & l2) != 0L;
            }
            case 44: {
                return (jjbitVec83[i2] & l2) != 0L;
            }
            case 45: {
                return (jjbitVec84[i2] & l2) != 0L;
            }
            case 46: {
                return (jjbitVec41[i2] & l2) != 0L;
            }
            case 48: {
                return (jjbitVec85[i2] & l2) != 0L;
            }
            case 49: {
                return (jjbitVec43[i2] & l2) != 0L;
            }
            case 77: {
                return (jjbitVec44[i2] & l2) != 0L;
            }
            case 159: {
                return (jjbitVec45[i2] & l2) != 0L;
            }
            case 164: {
                return (jjbitVec46[i2] & l2) != 0L;
            }
            case 166: {
                return (jjbitVec86[i2] & l2) != 0L;
            }
            case 167: {
                return (jjbitVec48[i2] & l2) != 0L;
            }
            case 168: {
                return (jjbitVec87[i2] & l2) != 0L;
            }
            case 169: {
                return (jjbitVec88[i2] & l2) != 0L;
            }
            case 170: {
                return (jjbitVec89[i2] & l2) != 0L;
            }
            case 171: {
                return (jjbitVec90[i2] & l2) != 0L;
            }
            case 215: {
                return (jjbitVec53[i2] & l2) != 0L;
            }
            case 250: {
                return (jjbitVec54[i2] & l2) != 0L;
            }
            case 251: {
                return (jjbitVec91[i2] & l2) != 0L;
            }
            case 253: {
                return (jjbitVec56[i2] & l2) != 0L;
            }
            case 254: {
                return (jjbitVec92[i2] & l2) != 0L;
            }
            case 255: {
                return (jjbitVec93[i2] & l2) != 0L;
            }
        }
        return (jjbitVec9[i1] & l1) != 0L;
    }

    public Token getNextToken() {
        Token specialToken = null;
        int curPos = 0;
        block12: while (true) {
            Token matchedToken;
            try {
                this.curChar = this.input_stream.beginToken();
            } catch (Exception e) {
                this.jjmatchedKind = 0;
                this.jjmatchedPos = -1;
                matchedToken = this.jjFillToken();
                matchedToken.specialToken = specialToken;
                this.CommonTokenAction(matchedToken);
                return matchedToken;
            }
            this.image = this.jjimage;
            this.image.setLength(0);
            this.jjimageLen = 0;
            while (true) {
                switch (this.curLexState) {
                    case 0: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_0();
                        break;
                    }
                    case 1: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_1();
                        if (this.jjmatchedPos != 0 || this.jjmatchedKind <= 10) break;
                        this.jjmatchedKind = 10;
                        break;
                    }
                    case 2: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_2();
                        if (this.jjmatchedPos != 0 || this.jjmatchedKind <= 10) break;
                        this.jjmatchedKind = 10;
                        break;
                    }
                    case 3: {
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        this.jjmatchedPos = 0;
                        curPos = this.jjMoveStringLiteralDfa0_3();
                    }
                }
                if (this.jjmatchedKind == Integer.MAX_VALUE) break block12;
                if (this.jjmatchedPos + 1 < curPos) {
                    this.input_stream.backup(curPos - this.jjmatchedPos - 1);
                }
                if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) {
                    matchedToken = this.jjFillToken();
                    matchedToken.specialToken = specialToken;
                    this.TokenLexicalActions(matchedToken);
                    if (jjnewLexState[this.jjmatchedKind] != -1) {
                        this.curLexState = jjnewLexState[this.jjmatchedKind];
                    }
                    this.CommonTokenAction(matchedToken);
                    return matchedToken;
                }
                if ((jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) {
                    if ((jjtoSpecial[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) {
                        matchedToken = this.jjFillToken();
                        if (specialToken == null) {
                            specialToken = matchedToken;
                        } else {
                            matchedToken.specialToken = specialToken;
                            specialToken = specialToken.next = matchedToken;
                        }
                        this.SkipLexicalActions(matchedToken);
                    } else {
                        this.SkipLexicalActions(null);
                    }
                    if (jjnewLexState[this.jjmatchedKind] == -1) continue block12;
                    this.curLexState = jjnewLexState[this.jjmatchedKind];
                    continue block12;
                }
                this.MoreLexicalActions();
                if (jjnewLexState[this.jjmatchedKind] != -1) {
                    this.curLexState = jjnewLexState[this.jjmatchedKind];
                }
                curPos = 0;
                this.jjmatchedKind = Integer.MAX_VALUE;
                try {
                    this.curChar = this.input_stream.readChar();
                } catch (IOException e) {
                    // empty catch block
                    break block12;
                }
            }
            break;
        }
        int error_line = this.input_stream.getEndLine();
        int error_column = this.input_stream.getEndColumn();
        String error_after = null;
        boolean EOFSeen = false;
        try {
            this.input_stream.readChar();
            this.input_stream.backup(1);
        } catch (IOException e1) {
            EOFSeen = true;
            String string = error_after = curPos <= 1 ? "" : this.input_stream.getImage();
            if (this.curChar == 10 || this.curChar == 13) {
                ++error_line;
                error_column = 0;
            }
            ++error_column;
        }
        if (!EOFSeen) {
            this.input_stream.backup(1);
            error_after = curPos <= 1 ? "" : this.input_stream.getImage();
        }
        throw new TokenMgrException(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
    }

    void SkipLexicalActions(Token matchedToken) {
        switch (this.jjmatchedKind) {
            default: 
        }
    }

    void MoreLexicalActions() {
        this.lengthOfMatch = this.jjmatchedPos + 1;
        this.jjimageLen += this.lengthOfMatch;
        switch (this.jjmatchedKind) {
            case 6: {
                this.image.append(this.input_stream.getSuffix(this.jjimageLen));
                this.jjimageLen = 0;
                this.input_stream.backup(1);
                break;
            }
        }
    }

    void TokenLexicalActions(Token matchedToken) {
        switch (this.jjmatchedKind) {
            case 64: {
                this.image.append(jjstrLiteralImages[64]);
                this.lengthOfMatch = jjstrLiteralImages[64].length();
                if (this.yieldSupported) break;
                matchedToken.kind = 93;
                break;
            }
            case 143: {
                this.image.append(jjstrLiteralImages[143]);
                this.lengthOfMatch = jjstrLiteralImages[143].length();
                matchedToken.kind = 145;
                matchedToken.realKind = 143;
                this.input_stream.backup(2);
                break;
            }
            case 144: {
                this.image.append(jjstrLiteralImages[144]);
                this.lengthOfMatch = jjstrLiteralImages[144].length();
                matchedToken.kind = 145;
                matchedToken.realKind = 144;
                this.input_stream.backup(1);
                break;
            }
        }
    }

    private void jjCheckNAdd(int state) {
        if (this.jjrounds[state] != this.jjround) {
            this.jjstateSet[this.jjnewStateCnt++] = state;
            this.jjrounds[state] = this.jjround;
        }
    }

    private void jjAddStates(int start, int end) {
        do {
            this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
        } while (start++ != end);
    }

    private void jjCheckNAddTwoStates(int state1, int state2) {
        this.jjCheckNAdd(state1);
        this.jjCheckNAdd(state2);
    }

    private void jjCheckNAddStates(int start, int end) {
        do {
            this.jjCheckNAdd(jjnextStates[start]);
        } while (start++ != end);
    }

    public GeneratedJavaParserTokenManager(SimpleCharStream stream) {
        this.input_stream = stream;
    }

    public GeneratedJavaParserTokenManager(SimpleCharStream stream, int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }

    public void ReInit(SimpleCharStream stream) {
        this.jjnewStateCnt = 0;
        this.jjmatchedPos = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        this.ReInitRounds();
    }

    private void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 141;
        while (i-- > 0) {
            this.jjrounds[i] = Integer.MIN_VALUE;
        }
    }

    public void ReInit(SimpleCharStream stream, int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }

    public void SwitchTo(int lexState) {
        if (lexState >= 4 || lexState < 0) {
            throw new TokenMgrException("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }
}

