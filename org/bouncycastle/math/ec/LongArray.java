/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.util.Arrays;

class LongArray
implements Cloneable {
    private static final short[] INTERLEAVE2_TABLE = new short[]{0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, 1024, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, 16384, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    private static final int[] INTERLEAVE3_TABLE = new int[]{0, 1, 8, 9, 64, 65, 72, 73, 512, 513, 520, 521, 576, 577, 584, 585, 4096, 4097, 4104, 4105, 4160, 4161, 4168, 4169, 4608, 4609, 4616, 4617, 4672, 4673, 4680, 4681, 32768, 32769, 32776, 32777, 32832, 32833, 32840, 32841, 33280, 33281, 33288, 33289, 33344, 33345, 33352, 33353, 36864, 36865, 36872, 36873, 36928, 36929, 36936, 36937, 37376, 37377, 37384, 37385, 37440, 37441, 37448, 37449, 262144, 262145, 262152, 262153, 262208, 262209, 262216, 262217, 262656, 262657, 262664, 262665, 262720, 262721, 262728, 262729, 266240, 266241, 266248, 266249, 266304, 266305, 266312, 266313, 266752, 266753, 266760, 266761, 266816, 266817, 266824, 266825, 294912, 294913, 294920, 294921, 294976, 294977, 294984, 294985, 295424, 295425, 295432, 295433, 295488, 295489, 295496, 295497, 299008, 299009, 299016, 299017, 299072, 299073, 299080, 299081, 299520, 299521, 299528, 299529, 299584, 299585, 299592, 299593};
    private static final int[] INTERLEAVE4_TABLE = new int[]{0, 1, 16, 17, 256, 257, 272, 273, 4096, 4097, 4112, 4113, 4352, 4353, 4368, 4369, 65536, 65537, 65552, 65553, 65792, 65793, 65808, 65809, 69632, 69633, 69648, 69649, 69888, 69889, 69904, 69905, 0x100000, 0x100001, 0x100010, 0x100011, 0x100100, 0x100101, 0x100110, 0x100111, 0x101000, 0x101001, 0x101010, 0x101011, 0x101100, 0x101101, 0x101110, 0x101111, 0x110000, 0x110001, 0x110010, 0x110011, 0x110100, 0x110101, 0x110110, 0x110111, 0x111000, 0x111001, 0x111010, 0x111011, 0x111100, 0x111101, 0x111110, 0x111111, 0x1000000, 0x1000001, 0x1000010, 0x1000011, 0x1000100, 0x1000101, 0x1000110, 0x1000111, 0x1001000, 0x1001001, 0x1001010, 0x1001011, 0x1001100, 0x1001101, 0x1001110, 0x1001111, 0x1010000, 0x1010001, 0x1010010, 0x1010011, 0x1010100, 0x1010101, 0x1010110, 0x1010111, 0x1011000, 0x1011001, 0x1011010, 0x1011011, 0x1011100, 0x1011101, 0x1011110, 0x1011111, 0x1100000, 0x1100001, 0x1100010, 0x1100011, 0x1100100, 0x1100101, 0x1100110, 0x1100111, 0x1101000, 0x1101001, 0x1101010, 0x1101011, 0x1101100, 0x1101101, 0x1101110, 0x1101111, 0x1110000, 0x1110001, 0x1110010, 0x1110011, 0x1110100, 0x1110101, 0x1110110, 0x1110111, 0x1111000, 0x1111001, 0x1111010, 0x1111011, 0x1111100, 0x1111101, 0x1111110, 0x1111111, 0x10000000, 0x10000001, 0x10000010, 0x10000011, 0x10000100, 0x10000101, 0x10000110, 0x10000111, 0x10001000, 0x10001001, 0x10001010, 0x10001011, 0x10001100, 0x10001101, 0x10001110, 0x10001111, 0x10010000, 0x10010001, 0x10010010, 0x10010011, 0x10010100, 0x10010101, 0x10010110, 0x10010111, 0x10011000, 0x10011001, 0x10011010, 0x10011011, 0x10011100, 0x10011101, 0x10011110, 0x10011111, 0x10100000, 0x10100001, 0x10100010, 0x10100011, 0x10100100, 0x10100101, 0x10100110, 0x10100111, 0x10101000, 0x10101001, 0x10101010, 0x10101011, 0x10101100, 0x10101101, 0x10101110, 0x10101111, 0x10110000, 0x10110001, 0x10110010, 0x10110011, 0x10110100, 0x10110101, 0x10110110, 0x10110111, 0x10111000, 0x10111001, 0x10111010, 0x10111011, 0x10111100, 0x10111101, 0x10111110, 0x10111111, 0x11000000, 0x11000001, 0x11000010, 0x11000011, 0x11000100, 0x11000101, 0x11000110, 0x11000111, 0x11001000, 0x11001001, 0x11001010, 0x11001011, 0x11001100, 0x11001101, 0x11001110, 0x11001111, 0x11010000, 0x11010001, 0x11010010, 0x11010011, 0x11010100, 0x11010101, 0x11010110, 0x11010111, 0x11011000, 0x11011001, 0x11011010, 0x11011011, 0x11011100, 0x11011101, 0x11011110, 0x11011111, 0x11100000, 0x11100001, 0x11100010, 0x11100011, 0x11100100, 0x11100101, 0x11100110, 0x11100111, 0x11101000, 0x11101001, 0x11101010, 0x11101011, 0x11101100, 0x11101101, 0x11101110, 0x11101111, 0x11110000, 0x11110001, 0x11110010, 0x11110011, 0x11110100, 0x11110101, 0x11110110, 0x11110111, 0x11111000, 0x11111001, 0x11111010, 0x11111011, 0x11111100, 0x11111101, 0x11111110, 0x11111111};
    private static final int[] INTERLEAVE5_TABLE = new int[]{0, 1, 32, 33, 1024, 1025, 1056, 1057, 32768, 32769, 32800, 32801, 33792, 33793, 33824, 33825, 0x100000, 0x100001, 0x100020, 0x100021, 0x100400, 0x100401, 1049632, 1049633, 0x108000, 0x108001, 1081376, 1081377, 1082368, 1082369, 1082400, 1082401, 0x2000000, 0x2000001, 0x2000020, 0x2000021, 0x2000400, 33555457, 0x2000420, 33555489, 0x2008000, 33587201, 0x2008020, 33587233, 33588224, 33588225, 33588256, 33588257, 0x2100000, 0x2100001, 0x2100020, 0x2100021, 34604032, 34604033, 34604064, 34604065, 34635776, 34635777, 34635808, 34635809, 34636800, 34636801, 34636832, 34636833, 0x40000000, 0x40000001, 0x40000020, 1073741857, 0x40000400, 0x40000401, 0x40000420, 1073742881, 0x40008000, 1073774593, 1073774624, 1073774625, 0x40008400, 1073775617, 1073775648, 1073775649, 0x40100000, 0x40100001, 1074790432, 1074790433, 0x40100400, 0x40100401, 1074791456, 1074791457, 1074823168, 1074823169, 1074823200, 1074823201, 1074824192, 1074824193, 1074824224, 1074824225, 0x42000000, 1107296257, 0x42000020, 1107296289, 0x42000400, 1107297281, 0x42000420, 1107297313, 1107329024, 1107329025, 1107329056, 1107329057, 1107330048, 1107330049, 1107330080, 1107330081, 1108344832, 1108344833, 1108344864, 1108344865, 1108345856, 1108345857, 1108345888, 1108345889, 1108377600, 1108377601, 1108377632, 1108377633, 1108378624, 1108378625, 1108378656, 1108378657};
    private static final long[] INTERLEAVE7_TABLE = new long[]{0L, 1L, 128L, 129L, 16384L, 16385L, 16512L, 16513L, 0x200000L, 0x200001L, 0x200080L, 2097281L, 0x204000L, 2113537L, 2113664L, 2113665L, 0x10000000L, 0x10000001L, 0x10000080L, 0x10000081L, 0x10004000L, 0x10004001L, 268451968L, 268451969L, 0x10200000L, 0x10200001L, 270532736L, 270532737L, 270548992L, 270548993L, 270549120L, 270549121L, 0x800000000L, 0x800000001L, 0x800000080L, 0x800000081L, 0x800004000L, 34359754753L, 0x800004080L, 34359754881L, 0x800200000L, 34361835521L, 0x800200080L, 34361835649L, 34361851904L, 34361851905L, 34361852032L, 34361852033L, 0x810000000L, 0x810000001L, 0x810000080L, 0x810000081L, 34628190208L, 34628190209L, 34628190336L, 34628190337L, 34630270976L, 34630270977L, 34630271104L, 34630271105L, 34630287360L, 34630287361L, 34630287488L, 34630287489L, 0x40000000000L, 0x40000000001L, 0x40000000080L, 4398046511233L, 0x40000004000L, 0x40000004001L, 0x40000004080L, 4398046527617L, 0x40000200000L, 4398048608257L, 4398048608384L, 4398048608385L, 0x40000204000L, 4398048624641L, 4398048624768L, 4398048624769L, 0x40010000000L, 0x40010000001L, 4398314946688L, 4398314946689L, 0x40010004000L, 0x40010004001L, 4398314963072L, 4398314963073L, 4398317043712L, 4398317043713L, 4398317043840L, 4398317043841L, 4398317060096L, 4398317060097L, 4398317060224L, 4398317060225L, 0x40800000000L, 4432406249473L, 0x40800000080L, 4432406249601L, 0x40800004000L, 4432406265857L, 0x40800004080L, 4432406265985L, 4432408346624L, 4432408346625L, 4432408346752L, 4432408346753L, 4432408363008L, 4432408363009L, 4432408363136L, 4432408363137L, 4432674684928L, 4432674684929L, 4432674685056L, 4432674685057L, 4432674701312L, 4432674701313L, 4432674701440L, 4432674701441L, 4432676782080L, 4432676782081L, 4432676782208L, 4432676782209L, 4432676798464L, 4432676798465L, 4432676798592L, 4432676798593L, 0x2000000000000L, 0x2000000000001L, 0x2000000000080L, 562949953421441L, 0x2000000004000L, 562949953437697L, 562949953437824L, 562949953437825L, 0x2000000200000L, 0x2000000200001L, 0x2000000200080L, 562949955518593L, 0x2000000204000L, 562949955534849L, 562949955534976L, 562949955534977L, 0x2000010000000L, 0x2000010000001L, 562950221856896L, 562950221856897L, 562950221873152L, 562950221873153L, 562950221873280L, 562950221873281L, 0x2000010200000L, 0x2000010200001L, 562950223954048L, 562950223954049L, 562950223970304L, 562950223970305L, 562950223970432L, 562950223970433L, 0x2000800000000L, 562984313159681L, 0x2000800000080L, 562984313159809L, 562984313176064L, 562984313176065L, 562984313176192L, 562984313176193L, 0x2000800200000L, 562984315256833L, 0x2000800200080L, 562984315256961L, 562984315273216L, 562984315273217L, 562984315273344L, 562984315273345L, 562984581595136L, 562984581595137L, 562984581595264L, 562984581595265L, 562984581611520L, 562984581611521L, 562984581611648L, 562984581611649L, 562984583692288L, 562984583692289L, 562984583692416L, 562984583692417L, 562984583708672L, 562984583708673L, 562984583708800L, 562984583708801L, 0x2040000000000L, 567347999932417L, 567347999932544L, 567347999932545L, 0x2040000004000L, 567347999948801L, 567347999948928L, 567347999948929L, 0x2040000200000L, 567348002029569L, 567348002029696L, 567348002029697L, 0x2040000204000L, 567348002045953L, 567348002046080L, 567348002046081L, 567348268367872L, 567348268367873L, 567348268368000L, 567348268368001L, 567348268384256L, 567348268384257L, 567348268384384L, 567348268384385L, 567348270465024L, 567348270465025L, 567348270465152L, 567348270465153L, 567348270481408L, 567348270481409L, 567348270481536L, 567348270481537L, 567382359670784L, 567382359670785L, 567382359670912L, 567382359670913L, 567382359687168L, 567382359687169L, 567382359687296L, 567382359687297L, 567382361767936L, 567382361767937L, 567382361768064L, 567382361768065L, 567382361784320L, 567382361784321L, 567382361784448L, 567382361784449L, 567382628106240L, 567382628106241L, 567382628106368L, 567382628106369L, 567382628122624L, 567382628122625L, 567382628122752L, 567382628122753L, 567382630203392L, 567382630203393L, 567382630203520L, 567382630203521L, 567382630219776L, 567382630219777L, 567382630219904L, 567382630219905L, 0x100000000000000L, 0x100000000000001L, 0x100000000000080L, 0x100000000000081L, 0x100000000004000L, 0x100000000004001L, 72057594037944448L, 72057594037944449L, 0x100000000200000L, 0x100000000200001L, 72057594040025216L, 72057594040025217L, 72057594040041472L, 72057594040041473L, 72057594040041600L, 72057594040041601L, 0x100000010000000L, 0x100000010000001L, 0x100000010000080L, 0x100000010000081L, 0x100000010004000L, 0x100000010004001L, 72057594306379904L, 72057594306379905L, 0x100000010200000L, 0x100000010200001L, 72057594308460672L, 72057594308460673L, 72057594308476928L, 72057594308476929L, 72057594308477056L, 72057594308477057L, 0x100000800000000L, 0x100000800000001L, 0x100000800000080L, 0x100000800000081L, 72057628397682688L, 72057628397682689L, 72057628397682816L, 72057628397682817L, 72057628399763456L, 72057628399763457L, 72057628399763584L, 72057628399763585L, 72057628399779840L, 72057628399779841L, 72057628399779968L, 72057628399779969L, 0x100000810000000L, 0x100000810000001L, 0x100000810000080L, 0x100000810000081L, 72057628666118144L, 72057628666118145L, 72057628666118272L, 72057628666118273L, 72057628668198912L, 72057628668198913L, 72057628668199040L, 72057628668199041L, 72057628668215296L, 72057628668215297L, 72057628668215424L, 72057628668215425L, 0x100040000000000L, 0x100040000000001L, 72061992084439168L, 72061992084439169L, 0x100040000004000L, 0x100040000004001L, 72061992084455552L, 72061992084455553L, 72061992086536192L, 72061992086536193L, 72061992086536320L, 72061992086536321L, 72061992086552576L, 72061992086552577L, 72061992086552704L, 72061992086552705L, 0x100040010000000L, 0x100040010000001L, 72061992352874624L, 72061992352874625L, 0x100040010004000L, 0x100040010004001L, 72061992352891008L, 72061992352891009L, 72061992354971648L, 72061992354971649L, 72061992354971776L, 72061992354971777L, 72061992354988032L, 72061992354988033L, 72061992354988160L, 72061992354988161L, 72062026444177408L, 72062026444177409L, 72062026444177536L, 72062026444177537L, 72062026444193792L, 72062026444193793L, 72062026444193920L, 72062026444193921L, 72062026446274560L, 72062026446274561L, 72062026446274688L, 72062026446274689L, 72062026446290944L, 72062026446290945L, 72062026446291072L, 72062026446291073L, 72062026712612864L, 72062026712612865L, 72062026712612992L, 72062026712612993L, 72062026712629248L, 72062026712629249L, 72062026712629376L, 72062026712629377L, 72062026714710016L, 72062026714710017L, 72062026714710144L, 72062026714710145L, 72062026714726400L, 72062026714726401L, 72062026714726528L, 72062026714726529L, 0x102000000000000L, 0x102000000000001L, 72620543991349376L, 72620543991349377L, 72620543991365632L, 72620543991365633L, 72620543991365760L, 72620543991365761L, 0x102000000200000L, 0x102000000200001L, 72620543993446528L, 72620543993446529L, 72620543993462784L, 72620543993462785L, 72620543993462912L, 72620543993462913L, 0x102000010000000L, 0x102000010000001L, 72620544259784832L, 72620544259784833L, 72620544259801088L, 72620544259801089L, 72620544259801216L, 72620544259801217L, 0x102000010200000L, 0x102000010200001L, 72620544261881984L, 72620544261881985L, 72620544261898240L, 72620544261898241L, 72620544261898368L, 72620544261898369L, 72620578351087616L, 72620578351087617L, 72620578351087744L, 72620578351087745L, 72620578351104000L, 72620578351104001L, 72620578351104128L, 72620578351104129L, 72620578353184768L, 72620578353184769L, 72620578353184896L, 72620578353184897L, 72620578353201152L, 72620578353201153L, 72620578353201280L, 72620578353201281L, 72620578619523072L, 72620578619523073L, 72620578619523200L, 72620578619523201L, 72620578619539456L, 72620578619539457L, 72620578619539584L, 72620578619539585L, 72620578621620224L, 72620578621620225L, 72620578621620352L, 72620578621620353L, 72620578621636608L, 72620578621636609L, 72620578621636736L, 72620578621636737L, 72624942037860352L, 72624942037860353L, 72624942037860480L, 72624942037860481L, 72624942037876736L, 72624942037876737L, 72624942037876864L, 72624942037876865L, 72624942039957504L, 72624942039957505L, 72624942039957632L, 72624942039957633L, 72624942039973888L, 72624942039973889L, 72624942039974016L, 72624942039974017L, 72624942306295808L, 72624942306295809L, 72624942306295936L, 72624942306295937L, 72624942306312192L, 72624942306312193L, 72624942306312320L, 72624942306312321L, 72624942308392960L, 72624942308392961L, 72624942308393088L, 72624942308393089L, 72624942308409344L, 72624942308409345L, 72624942308409472L, 72624942308409473L, 72624976397598720L, 72624976397598721L, 72624976397598848L, 72624976397598849L, 72624976397615104L, 72624976397615105L, 72624976397615232L, 72624976397615233L, 72624976399695872L, 72624976399695873L, 72624976399696000L, 72624976399696001L, 72624976399712256L, 72624976399712257L, 72624976399712384L, 72624976399712385L, 72624976666034176L, 72624976666034177L, 72624976666034304L, 72624976666034305L, 72624976666050560L, 72624976666050561L, 72624976666050688L, 72624976666050689L, 72624976668131328L, 72624976668131329L, 72624976668131456L, 72624976668131457L, 72624976668147712L, 72624976668147713L, 72624976668147840L, 72624976668147841L};
    private static final String ZEROES = "0000000000000000000000000000000000000000000000000000000000000000";
    static final byte[] bitLengths = new byte[]{0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    private long[] m_ints;

    public LongArray(int n) {
        this.m_ints = new long[n];
    }

    public LongArray(long[] lArray) {
        this.m_ints = lArray;
    }

    public LongArray(long[] lArray, int n, int n2) {
        if (n == 0 && n2 == lArray.length) {
            this.m_ints = lArray;
        } else {
            this.m_ints = new long[n2];
            System.arraycopy(lArray, n, this.m_ints, 0, n2);
        }
    }

    public LongArray(BigInteger bigInteger) {
        int n;
        int n2;
        if (bigInteger == null || bigInteger.signum() < 0) {
            throw new IllegalArgumentException("invalid F2m field value");
        }
        if (bigInteger.signum() == 0) {
            this.m_ints = new long[]{0L};
            return;
        }
        byte[] byArray = bigInteger.toByteArray();
        int n3 = byArray.length;
        int n4 = 0;
        if (byArray[0] == 0) {
            --n3;
            n4 = 1;
        }
        int n5 = (n3 + 7) / 8;
        this.m_ints = new long[n5];
        int n6 = n5 - 1;
        int n7 = n3 % 8 + n4;
        long l = 0L;
        if (n4 < n7) {
            for (n2 = n4; n2 < n7; ++n2) {
                l <<= 8;
                n = byArray[n2] & 0xFF;
                l |= (long)n;
            }
            this.m_ints[n6--] = l;
        }
        while (n6 >= 0) {
            l = 0L;
            for (n = 0; n < 8; ++n) {
                l <<= 8;
                int n8 = byArray[n2++] & 0xFF;
                l |= (long)n8;
            }
            this.m_ints[n6] = l;
            --n6;
        }
    }

    public boolean isOne() {
        long[] lArray = this.m_ints;
        if (lArray[0] != 1L) {
            return false;
        }
        for (int i = 1; i < lArray.length; ++i) {
            if (lArray[i] == 0L) continue;
            return false;
        }
        return true;
    }

    public boolean isZero() {
        long[] lArray = this.m_ints;
        for (int i = 0; i < lArray.length; ++i) {
            if (lArray[i] == 0L) continue;
            return false;
        }
        return true;
    }

    public int getUsedLength() {
        return this.getUsedLengthFrom(this.m_ints.length);
    }

    public int getUsedLengthFrom(int n) {
        long[] lArray = this.m_ints;
        if ((n = Math.min(n, lArray.length)) < 1) {
            return 0;
        }
        if (lArray[0] != 0L) {
            while (lArray[--n] == 0L) {
            }
            return n + 1;
        }
        do {
            if (lArray[--n] == 0L) continue;
            return n + 1;
        } while (n > 0);
        return 0;
    }

    public int degree() {
        long l;
        int n = this.m_ints.length;
        do {
            if (n != 0) continue;
            return 0;
        } while ((l = this.m_ints[--n]) == 0L);
        return (n << 6) + LongArray.bitLength(l);
    }

    private int degreeFrom(int n) {
        long l;
        int n2 = n + 62 >>> 6;
        do {
            if (n2 != 0) continue;
            return 0;
        } while ((l = this.m_ints[--n2]) == 0L);
        return (n2 << 6) + LongArray.bitLength(l);
    }

    private static int bitLength(long l) {
        int n;
        int n2;
        int n3 = (int)(l >>> 32);
        if (n3 == 0) {
            n3 = (int)l;
            n2 = 0;
        } else {
            n2 = 32;
        }
        int n4 = n3 >>> 16;
        int n5 = n4 == 0 ? ((n4 = n3 >>> 8) == 0 ? bitLengths[n3] : 8 + bitLengths[n4]) : ((n = n4 >>> 8) == 0 ? 16 + bitLengths[n4] : 24 + bitLengths[n]);
        return n2 + n5;
    }

    private long[] resizedInts(int n) {
        long[] lArray = new long[n];
        System.arraycopy(this.m_ints, 0, lArray, 0, Math.min(this.m_ints.length, n));
        return lArray;
    }

    public BigInteger toBigInteger() {
        int n;
        int n2;
        int n3 = this.getUsedLength();
        if (n3 == 0) {
            return ECConstants.ZERO;
        }
        long l = this.m_ints[n3 - 1];
        byte[] byArray = new byte[8];
        int n4 = 0;
        boolean bl = false;
        for (n2 = 7; n2 >= 0; --n2) {
            byte by = (byte)(l >>> 8 * n2);
            if (!bl && by == 0) continue;
            bl = true;
            byArray[n4++] = by;
        }
        n2 = 8 * (n3 - 1) + n4;
        byte[] byArray2 = new byte[n2];
        for (n = 0; n < n4; ++n) {
            byArray2[n] = byArray[n];
        }
        for (n = n3 - 2; n >= 0; --n) {
            long l2 = this.m_ints[n];
            for (int i = 7; i >= 0; --i) {
                byArray2[n4++] = (byte)(l2 >>> 8 * i);
            }
        }
        return new BigInteger(1, byArray2);
    }

    private static long shiftUp(long[] lArray, int n, int n2, int n3) {
        int n4 = 64 - n3;
        long l = 0L;
        for (int i = 0; i < n2; ++i) {
            long l2 = lArray[n + i];
            lArray[n + i] = l2 << n3 | l;
            l = l2 >>> n4;
        }
        return l;
    }

    private static long shiftUp(long[] lArray, int n, long[] lArray2, int n2, int n3, int n4) {
        int n5 = 64 - n4;
        long l = 0L;
        for (int i = 0; i < n3; ++i) {
            long l2 = lArray[n + i];
            lArray2[n2 + i] = l2 << n4 | l;
            l = l2 >>> n5;
        }
        return l;
    }

    public LongArray addOne() {
        if (this.m_ints.length == 0) {
            return new LongArray(new long[]{1L});
        }
        int n = Math.max(1, this.getUsedLength());
        long[] lArray = this.resizedInts(n);
        lArray[0] = lArray[0] ^ 1L;
        return new LongArray(lArray);
    }

    private void addShiftedByBitsSafe(LongArray longArray, int n, int n2) {
        int n3 = n + 63 >>> 6;
        int n4 = n2 >>> 6;
        int n5 = n2 & 0x3F;
        if (n5 == 0) {
            LongArray.add(this.m_ints, n4, longArray.m_ints, 0, n3);
            return;
        }
        long l = LongArray.addShiftedUp(this.m_ints, n4, longArray.m_ints, 0, n3, n5);
        if (l != 0L) {
            int n6 = n3 + n4;
            this.m_ints[n6] = this.m_ints[n6] ^ l;
        }
    }

    private static long addShiftedUp(long[] lArray, int n, long[] lArray2, int n2, int n3, int n4) {
        int n5 = 64 - n4;
        long l = 0L;
        for (int i = 0; i < n3; ++i) {
            long l2 = lArray2[n2 + i];
            int n6 = n + i;
            lArray[n6] = lArray[n6] ^ (l2 << n4 | l);
            l = l2 >>> n5;
        }
        return l;
    }

    private static long addShiftedDown(long[] lArray, int n, long[] lArray2, int n2, int n3, int n4) {
        int n5 = 64 - n4;
        long l = 0L;
        int n6 = n3;
        while (--n6 >= 0) {
            long l2 = lArray2[n2 + n6];
            int n7 = n + n6;
            lArray[n7] = lArray[n7] ^ (l2 >>> n4 | l);
            l = l2 << n5;
        }
        return l;
    }

    public void addShiftedByWords(LongArray longArray, int n) {
        int n2 = longArray.getUsedLength();
        if (n2 == 0) {
            return;
        }
        int n3 = n2 + n;
        if (n3 > this.m_ints.length) {
            this.m_ints = this.resizedInts(n3);
        }
        LongArray.add(this.m_ints, n, longArray.m_ints, 0, n2);
    }

    private static void add(long[] lArray, int n, long[] lArray2, int n2, int n3) {
        for (int i = 0; i < n3; ++i) {
            int n4 = n + i;
            lArray[n4] = lArray[n4] ^ lArray2[n2 + i];
        }
    }

    private static void add(long[] lArray, int n, long[] lArray2, int n2, long[] lArray3, int n3, int n4) {
        for (int i = 0; i < n4; ++i) {
            lArray3[n3 + i] = lArray[n + i] ^ lArray2[n2 + i];
        }
    }

    private static void addBoth(long[] lArray, int n, long[] lArray2, int n2, long[] lArray3, int n3, int n4) {
        for (int i = 0; i < n4; ++i) {
            int n5 = n + i;
            lArray[n5] = lArray[n5] ^ (lArray2[n2 + i] ^ lArray3[n3 + i]);
        }
    }

    private static void distribute(long[] lArray, int n, int n2, int n3, int n4) {
        for (int i = 0; i < n4; ++i) {
            long l = lArray[n + i];
            int n5 = n2 + i;
            lArray[n5] = lArray[n5] ^ l;
            int n6 = n3 + i;
            lArray[n6] = lArray[n6] ^ l;
        }
    }

    public int getLength() {
        return this.m_ints.length;
    }

    private static void flipWord(long[] lArray, int n, int n2, long l) {
        int n3 = n + (n2 >>> 6);
        int n4 = n2 & 0x3F;
        if (n4 == 0) {
            int n5 = n3;
            lArray[n5] = lArray[n5] ^ l;
        } else {
            int n6 = n3++;
            lArray[n6] = lArray[n6] ^ l << n4;
            if ((l >>>= 64 - n4) != 0L) {
                int n7 = n3;
                lArray[n7] = lArray[n7] ^ l;
            }
        }
    }

    public boolean testBitZero() {
        return this.m_ints.length > 0 && (this.m_ints[0] & 1L) != 0L;
    }

    private static boolean testBit(long[] lArray, int n, int n2) {
        int n3 = n2 >>> 6;
        int n4 = n2 & 0x3F;
        long l = 1L << n4;
        return (lArray[n + n3] & l) != 0L;
    }

    private static void flipBit(long[] lArray, int n, int n2) {
        int n3 = n2 >>> 6;
        int n4 = n2 & 0x3F;
        long l = 1L << n4;
        int n5 = n + n3;
        lArray[n5] = lArray[n5] ^ l;
    }

    private static void multiplyWord(long l, long[] lArray, int n, long[] lArray2, int n2) {
        if ((l & 1L) != 0L) {
            LongArray.add(lArray2, n2, lArray, 0, n);
        }
        int n3 = 1;
        while ((l >>>= 1) != 0L) {
            long l2;
            if ((l & 1L) != 0L && (l2 = LongArray.addShiftedUp(lArray2, n2, lArray, 0, n, n3)) != 0L) {
                int n4 = n2 + n;
                lArray2[n4] = lArray2[n4] ^ l2;
            }
            ++n3;
        }
    }

    public LongArray modMultiplyLD(LongArray longArray, int n, int[] nArray) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9 = this.degree();
        if (n9 == 0) {
            return this;
        }
        int n10 = longArray.degree();
        if (n10 == 0) {
            return longArray;
        }
        LongArray longArray2 = this;
        LongArray longArray3 = longArray;
        if (n9 > n10) {
            longArray2 = longArray;
            longArray3 = this;
            n8 = n9;
            n9 = n10;
            n10 = n8;
        }
        n8 = n9 + 63 >>> 6;
        int n11 = n10 + 63 >>> 6;
        int n12 = n9 + n10 + 62 >>> 6;
        if (n8 == 1) {
            long l = longArray2.m_ints[0];
            if (l == 1L) {
                return longArray3;
            }
            long[] lArray = new long[n12];
            LongArray.multiplyWord(l, longArray3.m_ints, n11, lArray, 0);
            return LongArray.reduceResult(lArray, 0, n12, n, nArray);
        }
        int n13 = n10 + 7 + 63 >>> 6;
        int[] nArray2 = new int[16];
        long[] lArray = new long[n13 << 4];
        nArray2[1] = n7 = n13;
        System.arraycopy(longArray3.m_ints, 0, lArray, n7, n11);
        for (int i = 2; i < 16; ++i) {
            nArray2[i] = n7 += n13;
            if ((i & 1) == 0) {
                LongArray.shiftUp(lArray, n7 >>> 1, lArray, n7, n13, 1);
                continue;
            }
            LongArray.add(lArray, n13, lArray, n7 - n13, lArray, n7, n13);
        }
        long[] lArray2 = new long[lArray.length];
        LongArray.shiftUp(lArray, 0, lArray2, 0, lArray.length, 4);
        long[] lArray3 = longArray2.m_ints;
        long[] lArray4 = new long[n12];
        int n14 = 15;
        for (n6 = 56; n6 >= 0; n6 -= 8) {
            for (n5 = 1; n5 < n8; n5 += 2) {
                n4 = (int)(lArray3[n5] >>> n6);
                n3 = n4 & n14;
                n2 = n4 >>> 4 & n14;
                LongArray.addBoth(lArray4, n5 - 1, lArray, nArray2[n3], lArray2, nArray2[n2], n13);
            }
            LongArray.shiftUp(lArray4, 0, n12, 8);
        }
        for (n6 = 56; n6 >= 0; n6 -= 8) {
            for (n5 = 0; n5 < n8; n5 += 2) {
                n4 = (int)(lArray3[n5] >>> n6);
                n3 = n4 & n14;
                n2 = n4 >>> 4 & n14;
                LongArray.addBoth(lArray4, n5, lArray, nArray2[n3], lArray2, nArray2[n2], n13);
            }
            if (n6 <= 0) continue;
            LongArray.shiftUp(lArray4, 0, n12, 8);
        }
        return LongArray.reduceResult(lArray4, 0, n12, n, nArray);
    }

    public LongArray modMultiply(LongArray longArray, int n, int[] nArray) {
        int n2;
        int n3;
        int n4;
        int n5 = this.degree();
        if (n5 == 0) {
            return this;
        }
        int n6 = longArray.degree();
        if (n6 == 0) {
            return longArray;
        }
        LongArray longArray2 = this;
        LongArray longArray3 = longArray;
        if (n5 > n6) {
            longArray2 = longArray;
            longArray3 = this;
            n4 = n5;
            n5 = n6;
            n6 = n4;
        }
        n4 = n5 + 63 >>> 6;
        int n7 = n6 + 63 >>> 6;
        int n8 = n5 + n6 + 62 >>> 6;
        if (n4 == 1) {
            long l = longArray2.m_ints[0];
            if (l == 1L) {
                return longArray3;
            }
            long[] lArray = new long[n8];
            LongArray.multiplyWord(l, longArray3.m_ints, n7, lArray, 0);
            return LongArray.reduceResult(lArray, 0, n8, n, nArray);
        }
        int n9 = n6 + 7 + 63 >>> 6;
        int[] nArray2 = new int[16];
        long[] lArray = new long[n9 << 4];
        nArray2[1] = n3 = n9;
        System.arraycopy(longArray3.m_ints, 0, lArray, n3, n7);
        for (int i = 2; i < 16; ++i) {
            nArray2[i] = n3 += n9;
            if ((i & 1) == 0) {
                LongArray.shiftUp(lArray, n3 >>> 1, lArray, n3, n9, 1);
                continue;
            }
            LongArray.add(lArray, n9, lArray, n3 - n9, lArray, n3, n9);
        }
        long[] lArray2 = new long[lArray.length];
        LongArray.shiftUp(lArray, 0, lArray2, 0, lArray.length, 4);
        long[] lArray3 = longArray2.m_ints;
        long[] lArray4 = new long[n8 << 3];
        int n10 = 15;
        block1: for (n2 = 0; n2 < n4; ++n2) {
            long l = lArray3[n2];
            int n11 = n2;
            while (true) {
                int n12 = (int)l & n10;
                int n13 = (int)(l >>>= 4) & n10;
                LongArray.addBoth(lArray4, n11, lArray, nArray2[n12], lArray2, nArray2[n13], n9);
                if ((l >>>= 4) == 0L) continue block1;
                n11 += n8;
            }
        }
        n2 = lArray4.length;
        while ((n2 -= n8) != 0) {
            LongArray.addShiftedUp(lArray4, n2 - n8, lArray4, n2, n8, 8);
        }
        return LongArray.reduceResult(lArray4, 0, n8, n, nArray);
    }

    public LongArray modMultiplyAlt(LongArray longArray, int n, int[] nArray) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6 = this.degree();
        if (n6 == 0) {
            return this;
        }
        int n7 = longArray.degree();
        if (n7 == 0) {
            return longArray;
        }
        LongArray longArray2 = this;
        LongArray longArray3 = longArray;
        if (n6 > n7) {
            longArray2 = longArray;
            longArray3 = this;
            n5 = n6;
            n6 = n7;
            n7 = n5;
        }
        n5 = n6 + 63 >>> 6;
        int n8 = n7 + 63 >>> 6;
        int n9 = n6 + n7 + 62 >>> 6;
        if (n5 == 1) {
            long l = longArray2.m_ints[0];
            if (l == 1L) {
                return longArray3;
            }
            long[] lArray = new long[n9];
            LongArray.multiplyWord(l, longArray3.m_ints, n8, lArray, 0);
            return LongArray.reduceResult(lArray, 0, n9, n, nArray);
        }
        int n10 = 4;
        int n11 = 16;
        int n12 = 64;
        int n13 = 8;
        int n14 = n12 < 64 ? n11 : n11 - 1;
        int n15 = n7 + n14 + 63 >>> 6;
        int n16 = n15 * n13;
        int n17 = n10 * n13;
        int[] nArray2 = new int[1 << n10];
        nArray2[0] = n4 = n5;
        nArray2[1] = n4 += n16;
        for (int i = 2; i < nArray2.length; ++i) {
            nArray2[i] = n4 += n9;
        }
        n4 += n9;
        long[] lArray = new long[++n4];
        LongArray.interleave(longArray2.m_ints, 0, lArray, 0, n5, n10);
        int n18 = n5;
        System.arraycopy(longArray3.m_ints, 0, lArray, n18, n8);
        for (n3 = 1; n3 < n13; ++n3) {
            LongArray.shiftUp(lArray, n5, lArray, n18 += n15, n15, n3);
        }
        n18 = (1 << n10) - 1;
        n3 = 0;
        while (true) {
            n2 = 0;
            block3: do {
                long l = lArray[n2] >>> n3;
                int n19 = 0;
                int n20 = n5;
                while (true) {
                    int n21;
                    if ((n21 = (int)l & n18) != 0) {
                        LongArray.add(lArray, n2 + nArray2[n21], lArray, n20, n15);
                    }
                    if (++n19 == n13) continue block3;
                    n20 += n15;
                    l >>>= n10;
                }
            } while (++n2 < n5);
            if ((n3 += n17) >= n12) {
                if (n3 >= 64) break;
                n3 = 64 - n10;
                n18 &= n18 << n12 - n3;
            }
            LongArray.shiftUp(lArray, n5, n16, n13);
        }
        n2 = nArray2.length;
        while (--n2 > 1) {
            if (((long)n2 & 1L) == 0L) {
                LongArray.addShiftedUp(lArray, nArray2[n2 >>> 1], lArray, nArray2[n2], n9, n11);
                continue;
            }
            LongArray.distribute(lArray, nArray2[n2], nArray2[n2 - 1], nArray2[1], n9);
        }
        return LongArray.reduceResult(lArray, nArray2[1], n9, n, nArray);
    }

    public LongArray modReduce(int n, int[] nArray) {
        long[] lArray = Arrays.clone(this.m_ints);
        int n2 = LongArray.reduceInPlace(lArray, 0, lArray.length, n, nArray);
        return new LongArray(lArray, 0, n2);
    }

    public LongArray multiply(LongArray longArray, int n, int[] nArray) {
        int n2;
        int n3;
        int n4;
        int n5 = this.degree();
        if (n5 == 0) {
            return this;
        }
        int n6 = longArray.degree();
        if (n6 == 0) {
            return longArray;
        }
        LongArray longArray2 = this;
        LongArray longArray3 = longArray;
        if (n5 > n6) {
            longArray2 = longArray;
            longArray3 = this;
            n4 = n5;
            n5 = n6;
            n6 = n4;
        }
        n4 = n5 + 63 >>> 6;
        int n7 = n6 + 63 >>> 6;
        int n8 = n5 + n6 + 62 >>> 6;
        if (n4 == 1) {
            long l = longArray2.m_ints[0];
            if (l == 1L) {
                return longArray3;
            }
            long[] lArray = new long[n8];
            LongArray.multiplyWord(l, longArray3.m_ints, n7, lArray, 0);
            return new LongArray(lArray, 0, n8);
        }
        int n9 = n6 + 7 + 63 >>> 6;
        int[] nArray2 = new int[16];
        long[] lArray = new long[n9 << 4];
        nArray2[1] = n3 = n9;
        System.arraycopy(longArray3.m_ints, 0, lArray, n3, n7);
        for (int i = 2; i < 16; ++i) {
            nArray2[i] = n3 += n9;
            if ((i & 1) == 0) {
                LongArray.shiftUp(lArray, n3 >>> 1, lArray, n3, n9, 1);
                continue;
            }
            LongArray.add(lArray, n9, lArray, n3 - n9, lArray, n3, n9);
        }
        long[] lArray2 = new long[lArray.length];
        LongArray.shiftUp(lArray, 0, lArray2, 0, lArray.length, 4);
        long[] lArray3 = longArray2.m_ints;
        long[] lArray4 = new long[n8 << 3];
        int n10 = 15;
        block1: for (n2 = 0; n2 < n4; ++n2) {
            long l = lArray3[n2];
            int n11 = n2;
            while (true) {
                int n12 = (int)l & n10;
                int n13 = (int)(l >>>= 4) & n10;
                LongArray.addBoth(lArray4, n11, lArray, nArray2[n12], lArray2, nArray2[n13], n9);
                if ((l >>>= 4) == 0L) continue block1;
                n11 += n8;
            }
        }
        n2 = lArray4.length;
        while ((n2 -= n8) != 0) {
            LongArray.addShiftedUp(lArray4, n2 - n8, lArray4, n2, n8, 8);
        }
        return new LongArray(lArray4, 0, n8);
    }

    public void reduce(int n, int[] nArray) {
        long[] lArray = this.m_ints;
        int n2 = LongArray.reduceInPlace(lArray, 0, lArray.length, n, nArray);
        if (n2 < lArray.length) {
            this.m_ints = new long[n2];
            System.arraycopy(lArray, 0, this.m_ints, 0, n2);
        }
    }

    private static LongArray reduceResult(long[] lArray, int n, int n2, int n3, int[] nArray) {
        int n4 = LongArray.reduceInPlace(lArray, n, n2, n3, nArray);
        return new LongArray(lArray, n, n4);
    }

    private static int reduceInPlace(long[] lArray, int n, int n2, int n3, int[] nArray) {
        int n4;
        int n5 = n3 + 63 >>> 6;
        if (n2 < n5) {
            return n2;
        }
        int n6 = Math.min(n2 << 6, (n3 << 1) - 1);
        for (n4 = (n2 << 6) - n6; n4 >= 64; n4 -= 64) {
            --n2;
        }
        int n7 = nArray.length;
        int n8 = nArray[n7 - 1];
        int n9 = n7 > 1 ? nArray[n7 - 2] : 0;
        int n10 = Math.max(n3, n8 + 64);
        int n11 = n4 + Math.min(n6 - n10, n3 - n9) >> 6;
        if (n11 > 1) {
            int n12 = n2 - n11;
            LongArray.reduceVectorWise(lArray, n, n2, n12, n3, nArray);
            while (n2 > n12) {
                lArray[n + --n2] = 0L;
            }
            n6 = n12 << 6;
        }
        if (n6 > n10) {
            LongArray.reduceWordWise(lArray, n, n2, n10, n3, nArray);
            n6 = n10;
        }
        if (n6 > n3) {
            LongArray.reduceBitWise(lArray, n, n6, n3, nArray);
        }
        return n5;
    }

    private static void reduceBitWise(long[] lArray, int n, int n2, int n3, int[] nArray) {
        while (--n2 >= n3) {
            if (!LongArray.testBit(lArray, n, n2)) continue;
            LongArray.reduceBit(lArray, n, n2, n3, nArray);
        }
    }

    private static void reduceBit(long[] lArray, int n, int n2, int n3, int[] nArray) {
        LongArray.flipBit(lArray, n, n2);
        int n4 = n2 - n3;
        int n5 = nArray.length;
        while (--n5 >= 0) {
            LongArray.flipBit(lArray, n, nArray[n5] + n4);
        }
        LongArray.flipBit(lArray, n, n4);
    }

    private static void reduceWordWise(long[] lArray, int n, int n2, int n3, int n4, int[] nArray) {
        int n5 = n3 >>> 6;
        while (--n2 > n5) {
            long l = lArray[n + n2];
            if (l == 0L) continue;
            lArray[n + n2] = 0L;
            LongArray.reduceWord(lArray, n, n2 << 6, l, n4, nArray);
        }
        int n6 = n3 & 0x3F;
        long l = lArray[n + n5] >>> n6;
        if (l != 0L) {
            int n7 = n + n5;
            lArray[n7] = lArray[n7] ^ l << n6;
            LongArray.reduceWord(lArray, n, n3, l, n4, nArray);
        }
    }

    private static void reduceWord(long[] lArray, int n, int n2, long l, int n3, int[] nArray) {
        int n4 = n2 - n3;
        int n5 = nArray.length;
        while (--n5 >= 0) {
            LongArray.flipWord(lArray, n, n4 + nArray[n5], l);
        }
        LongArray.flipWord(lArray, n, n4, l);
    }

    private static void reduceVectorWise(long[] lArray, int n, int n2, int n3, int n4, int[] nArray) {
        int n5 = (n3 << 6) - n4;
        int n6 = nArray.length;
        while (--n6 >= 0) {
            LongArray.flipVector(lArray, n, lArray, n + n3, n2 - n3, n5 + nArray[n6]);
        }
        LongArray.flipVector(lArray, n, lArray, n + n3, n2 - n3, n5);
    }

    private static void flipVector(long[] lArray, int n, long[] lArray2, int n2, int n3, int n4) {
        n += n4 >>> 6;
        if ((n4 &= 0x3F) == 0) {
            LongArray.add(lArray, n, lArray2, n2, n3);
        } else {
            long l = LongArray.addShiftedDown(lArray, n + 1, lArray2, n2, n3, 64 - n4);
            int n5 = n;
            lArray[n5] = lArray[n5] ^ l;
        }
    }

    public LongArray modSquare(int n, int[] nArray) {
        int n2 = this.getUsedLength();
        if (n2 == 0) {
            return this;
        }
        int n3 = n2 << 1;
        long[] lArray = new long[n3];
        int n4 = 0;
        while (n4 < n3) {
            long l = this.m_ints[n4 >>> 1];
            lArray[n4++] = LongArray.interleave2_32to64((int)l);
            lArray[n4++] = LongArray.interleave2_32to64((int)(l >>> 32));
        }
        return new LongArray(lArray, 0, LongArray.reduceInPlace(lArray, 0, lArray.length, n, nArray));
    }

    public LongArray modSquareN(int n, int n2, int[] nArray) {
        int n3 = this.getUsedLength();
        if (n3 == 0) {
            return this;
        }
        int n4 = n2 + 63 >>> 6;
        long[] lArray = new long[n4 << 1];
        System.arraycopy(this.m_ints, 0, lArray, 0, n3);
        while (--n >= 0) {
            LongArray.squareInPlace(lArray, n3, n2, nArray);
            n3 = LongArray.reduceInPlace(lArray, 0, lArray.length, n2, nArray);
        }
        return new LongArray(lArray, 0, n3);
    }

    public LongArray square(int n, int[] nArray) {
        int n2 = this.getUsedLength();
        if (n2 == 0) {
            return this;
        }
        int n3 = n2 << 1;
        long[] lArray = new long[n3];
        int n4 = 0;
        while (n4 < n3) {
            long l = this.m_ints[n4 >>> 1];
            lArray[n4++] = LongArray.interleave2_32to64((int)l);
            lArray[n4++] = LongArray.interleave2_32to64((int)(l >>> 32));
        }
        return new LongArray(lArray, 0, lArray.length);
    }

    private static void squareInPlace(long[] lArray, int n, int n2, int[] nArray) {
        int n3 = n << 1;
        while (--n >= 0) {
            long l = lArray[n];
            lArray[--n3] = LongArray.interleave2_32to64((int)(l >>> 32));
            lArray[--n3] = LongArray.interleave2_32to64((int)l);
        }
    }

    private static void interleave(long[] lArray, int n, long[] lArray2, int n2, int n3, int n4) {
        switch (n4) {
            case 3: {
                LongArray.interleave3(lArray, n, lArray2, n2, n3);
                break;
            }
            case 5: {
                LongArray.interleave5(lArray, n, lArray2, n2, n3);
                break;
            }
            case 7: {
                LongArray.interleave7(lArray, n, lArray2, n2, n3);
                break;
            }
            default: {
                LongArray.interleave2_n(lArray, n, lArray2, n2, n3, bitLengths[n4] - 1);
            }
        }
    }

    private static void interleave3(long[] lArray, int n, long[] lArray2, int n2, int n3) {
        for (int i = 0; i < n3; ++i) {
            lArray2[n2 + i] = LongArray.interleave3(lArray[n + i]);
        }
    }

    private static long interleave3(long l) {
        long l2 = l & Long.MIN_VALUE;
        return l2 | LongArray.interleave3_21to63((int)l & 0x1FFFFF) | LongArray.interleave3_21to63((int)(l >>> 21) & 0x1FFFFF) << 1 | LongArray.interleave3_21to63((int)(l >>> 42) & 0x1FFFFF) << 2;
    }

    private static long interleave3_21to63(int n) {
        int n2 = INTERLEAVE3_TABLE[n & 0x7F];
        int n3 = INTERLEAVE3_TABLE[n >>> 7 & 0x7F];
        int n4 = INTERLEAVE3_TABLE[n >>> 14];
        return ((long)n4 & 0xFFFFFFFFL) << 42 | ((long)n3 & 0xFFFFFFFFL) << 21 | (long)n2 & 0xFFFFFFFFL;
    }

    private static void interleave5(long[] lArray, int n, long[] lArray2, int n2, int n3) {
        for (int i = 0; i < n3; ++i) {
            lArray2[n2 + i] = LongArray.interleave5(lArray[n + i]);
        }
    }

    private static long interleave5(long l) {
        return LongArray.interleave3_13to65((int)l & 0x1FFF) | LongArray.interleave3_13to65((int)(l >>> 13) & 0x1FFF) << 1 | LongArray.interleave3_13to65((int)(l >>> 26) & 0x1FFF) << 2 | LongArray.interleave3_13to65((int)(l >>> 39) & 0x1FFF) << 3 | LongArray.interleave3_13to65((int)(l >>> 52) & 0x1FFF) << 4;
    }

    private static long interleave3_13to65(int n) {
        int n2 = INTERLEAVE5_TABLE[n & 0x7F];
        int n3 = INTERLEAVE5_TABLE[n >>> 7];
        return ((long)n3 & 0xFFFFFFFFL) << 35 | (long)n2 & 0xFFFFFFFFL;
    }

    private static void interleave7(long[] lArray, int n, long[] lArray2, int n2, int n3) {
        for (int i = 0; i < n3; ++i) {
            lArray2[n2 + i] = LongArray.interleave7(lArray[n + i]);
        }
    }

    private static long interleave7(long l) {
        long l2 = l & Long.MIN_VALUE;
        return l2 | INTERLEAVE7_TABLE[(int)l & 0x1FF] | INTERLEAVE7_TABLE[(int)(l >>> 9) & 0x1FF] << 1 | INTERLEAVE7_TABLE[(int)(l >>> 18) & 0x1FF] << 2 | INTERLEAVE7_TABLE[(int)(l >>> 27) & 0x1FF] << 3 | INTERLEAVE7_TABLE[(int)(l >>> 36) & 0x1FF] << 4 | INTERLEAVE7_TABLE[(int)(l >>> 45) & 0x1FF] << 5 | INTERLEAVE7_TABLE[(int)(l >>> 54) & 0x1FF] << 6;
    }

    private static void interleave2_n(long[] lArray, int n, long[] lArray2, int n2, int n3, int n4) {
        for (int i = 0; i < n3; ++i) {
            lArray2[n2 + i] = LongArray.interleave2_n(lArray[n + i], n4);
        }
    }

    private static long interleave2_n(long l, int n) {
        while (n > 1) {
            n -= 2;
            l = LongArray.interleave4_16to64((int)l & 0xFFFF) | LongArray.interleave4_16to64((int)(l >>> 16) & 0xFFFF) << 1 | LongArray.interleave4_16to64((int)(l >>> 32) & 0xFFFF) << 2 | LongArray.interleave4_16to64((int)(l >>> 48) & 0xFFFF) << 3;
        }
        if (n > 0) {
            l = LongArray.interleave2_32to64((int)l) | LongArray.interleave2_32to64((int)(l >>> 32)) << 1;
        }
        return l;
    }

    private static long interleave4_16to64(int n) {
        int n2 = INTERLEAVE4_TABLE[n & 0xFF];
        int n3 = INTERLEAVE4_TABLE[n >>> 8];
        return ((long)n3 & 0xFFFFFFFFL) << 32 | (long)n2 & 0xFFFFFFFFL;
    }

    private static long interleave2_32to64(int n) {
        int n2 = INTERLEAVE2_TABLE[n & 0xFF] | INTERLEAVE2_TABLE[n >>> 8 & 0xFF] << 16;
        int n3 = INTERLEAVE2_TABLE[n >>> 16 & 0xFF] | INTERLEAVE2_TABLE[n >>> 24] << 16;
        return ((long)n3 & 0xFFFFFFFFL) << 32 | (long)n2 & 0xFFFFFFFFL;
    }

    public LongArray modInverse(int n, int[] nArray) {
        int n2 = this.degree();
        if (n2 == 0) {
            throw new IllegalStateException();
        }
        if (n2 == 1) {
            return this;
        }
        LongArray longArray = (LongArray)this.clone();
        int n3 = n + 63 >>> 6;
        LongArray longArray2 = new LongArray(n3);
        LongArray.reduceBit(longArray2.m_ints, 0, n, n, nArray);
        LongArray longArray3 = new LongArray(n3);
        longArray3.m_ints[0] = 1L;
        LongArray longArray4 = new LongArray(n3);
        int[] nArray2 = new int[]{n2, n + 1};
        LongArray[] longArrayArray = new LongArray[]{longArray, longArray2};
        int[] nArray3 = new int[]{1, 0};
        LongArray[] longArrayArray2 = new LongArray[]{longArray3, longArray4};
        int n4 = 1;
        int n5 = nArray2[n4];
        int n6 = nArray3[n4];
        int n7 = n5 - nArray2[1 - n4];
        while (true) {
            if (n7 < 0) {
                n7 = -n7;
                nArray2[n4] = n5;
                nArray3[n4] = n6;
                n4 = 1 - n4;
                n5 = nArray2[n4];
                n6 = nArray3[n4];
            }
            longArrayArray[n4].addShiftedByBitsSafe(longArrayArray[1 - n4], nArray2[1 - n4], n7);
            int n8 = longArrayArray[n4].degreeFrom(n5);
            if (n8 == 0) {
                return longArrayArray2[1 - n4];
            }
            int n9 = nArray3[1 - n4];
            longArrayArray2[n4].addShiftedByBitsSafe(longArrayArray2[1 - n4], n9, n7);
            if ((n9 += n7) > n6) {
                n6 = n9;
            } else if (n9 == n6) {
                n6 = longArrayArray2[n4].degreeFrom(n6);
            }
            n7 += n8 - n5;
            n5 = n8;
        }
    }

    public boolean equals(Object object) {
        if (!(object instanceof LongArray)) {
            return false;
        }
        LongArray longArray = (LongArray)object;
        int n = this.getUsedLength();
        if (longArray.getUsedLength() != n) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            if (this.m_ints[i] == longArray.m_ints[i]) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int n = this.getUsedLength();
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            long l = this.m_ints[i];
            n2 *= 31;
            n2 ^= (int)l;
            n2 *= 31;
            n2 ^= (int)(l >>> 32);
        }
        return n2;
    }

    public Object clone() {
        return new LongArray(Arrays.clone(this.m_ints));
    }

    public String toString() {
        int n = this.getUsedLength();
        if (n == 0) {
            return "0";
        }
        StringBuffer stringBuffer = new StringBuffer(Long.toBinaryString(this.m_ints[--n]));
        while (--n >= 0) {
            String string = Long.toBinaryString(this.m_ints[n]);
            int n2 = string.length();
            if (n2 < 64) {
                stringBuffer.append(ZEROES.substring(n2));
            }
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }
}

