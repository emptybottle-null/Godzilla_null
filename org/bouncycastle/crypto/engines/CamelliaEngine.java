/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class CamelliaEngine
implements BlockCipher {
    private boolean initialised = false;
    private boolean _keyIs128;
    private static final int BLOCK_SIZE = 16;
    private static final int MASK8 = 255;
    private int[] subkey = new int[96];
    private int[] kw = new int[8];
    private int[] ke = new int[12];
    private int[] state = new int[4];
    private static final int[] SIGMA = new int[]{-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    private static final int[] SBOX1_1110 = new int[]{0x70707000, -2105376256, 0x2C2C2C00, -320017408, -1280068864, 0x27272700, -1061109760, -437918464, -454761472, -2054847232, 0x57575700, 0x35353500, -353703424, 0xC0C0C00, -1364283904, 0x41414100, 0x23232300, -269488384, 0x6B6B6B00, -1819045120, 0x45454500, 0x19191900, -1515870976, 0x21212100, -303174400, 0xE0E0E00, 0x4F4F4F00, 0x4E4E4E00, 0x1D1D1D00, 0x65656500, -1835888128, -1111638784, -2038004224, -1195853824, -1347440896, -1886417152, 0x7C7C7C00, -336860416, 0x1F1F1F00, -825307648, 0x3E3E3E00, 0x30303000, -589505536, 0x5F5F5F00, 0x5E5E5E00, -976894720, 0xB0B0B00, 0x1A1A1A00, -1499027968, -505290496, 0x39393900, -892679680, -707406592, 0x47474700, 0x5D5D5D00, 0x3D3D3D00, -640034560, 0x1010100, 0x5A5A5A00, -690563584, 0x51515100, 0x56565600, 0x6C6C6C00, 0x4D4D4D00, -1953789184, 0xD0D0D00, -1701144064, 0x66666600, -67372288, -858993664, -1330597888, 0x2D2D2D00, 0x74747400, 0x12121200, 0x2B2B2B00, 0x20202000, -252645376, -1313754880, -2071690240, -1717987072, -538976512, 0x4C4C4C00, -875836672, -1027423744, 0x34343400, 0x7E7E7E00, 0x76767600, 0x5050500, 0x6D6D6D00, -1212696832, -1448498944, 0x31313100, -774778624, 0x17171700, 0x4040400, -673720576, 0x14141400, 0x58585800, 0x3A3A3A00, 0x61616100, -555819520, 0x1B1B1B00, 0x11111100, 0x1C1C1C00, 0x32323200, 0xF0F0F00, -1667458048, 0x16161600, 0x53535300, 0x18181800, -218959360, 0x22222200, -16843264, 0x44444400, -808464640, -1296911872, -1010580736, -1246382848, 0x7A7A7A00, -1852731136, 0x24242400, 0x8080800, -387389440, -1465341952, 0x60606000, -50529280, 0x69696900, 0x50505000, -1431655936, -791621632, -1600086016, 0x7D7D7D00, -1583243008, -1987475200, 0x62626200, -1751673088, 0x54545400, 0x5B5B5B00, 0x1E1E1E00, -1785359104, -522133504, -256, 0x64646400, -757935616, 0x10101000, -993737728, 0, 0x48484800, -1549556992, -134744320, 0x75757500, -606348544, -1970632192, 0x3030300, -421075456, -623191552, 0x9090900, 0x3F3F3F00, -572662528, -1802202112, -2021161216, 0x5C5C5C00, -2088533248, 0x2020200, -842150656, 0x4A4A4A00, -1869574144, 0x33333300, 0x73737300, 0x67676700, -151587328, -202116352, -1650615040, 0x7F7F7F00, -1077952768, -488447488, 0x52525200, -1684301056, -656877568, 0x26262600, -926365696, 0x37373700, -960051712, 0x3B3B3B00, -2122219264, -1768516096, 0x6F6F6F00, 0x4B4B4B00, 0x13131300, -1094795776, 0x63636300, 0x2E2E2E00, -370546432, 0x79797900, -1482184960, -1936946176, -1616929024, 0x6E6E6E00, -1128481792, -1903260160, 0x29292900, -168430336, -101058304, -1229539840, 0x2F2F2F00, -33686272, -1263225856, 0x59595900, 0x78787800, -1734830080, 0x6060600, 0x6A6A6A00, -404232448, 0x46464600, 0x71717100, -1162167808, -724249600, 0x25252500, -1414812928, 0x42424200, -2004318208, -1566400000, -1920103168, -84215296, 0x72727200, 0x7070700, -1179010816, 0x55555500, -117901312, -286331392, -1397969920, 0xA0A0A00, 0x36363600, 0x49494900, 0x2A2A2A00, 0x68686800, 0x3C3C3C00, 0x38383800, -235802368, -1532713984, 0x40404000, 0x28282800, -741092608, 0x7B7B7B00, -1145324800, -909522688, 0x43434300, -1044266752, 0x15151500, -471604480, -1381126912, -185273344, 0x77777700, -943208704, -2139062272, -1633772032};
    private static final int[] SBOX4_4404 = new int[]{0x70700070, 0x2C2C002C, -1280114509, -1061158720, -454819612, 0x57570057, -353763094, -1364328274, 0x23230023, 0x6B6B006B, 0x45450045, -1515913051, -303234835, 0x4F4F004F, 0x1D1D001D, -1835925358, -2038038394, -1347485521, 0x7C7C007C, 0x1F1F001F, 0x3E3E003E, -589561636, 0x5E5E005E, 0xB0B000B, -1499070298, 0x39390039, -707460907, 0x5D5D005D, -640089895, 0x5A5A005A, 0x51510051, 0x6C6C006C, -1953824629, -1701183334, -67436293, -1330642768, 0x74740074, 0x2B2B002B, -252706576, -2071723900, -539033377, -875888437, 0x34340034, 0x76760076, 0x6D6D006D, -1448542039, -774831919, 0x4040004, 0x14140014, 0x3A3A003A, -555876130, 0x11110011, 0x32320032, -1667497828, 0x53530053, -219021070, -16908034, -808517425, -1010630461, 0x7A7A007A, 0x24240024, -387448600, 0x60600060, 0x69690069, -1431699286, -1600126816, -1583284063, 0x62620062, 0x54540054, 0x1E1E001E, -522190624, 0x64640064, 0x10100010, 0, -1549598557, 0x75750075, -1970667382, -421134106, 0x9090009, -572718883, -2021195641, -2088566653, -842202931, -1869610864, 0x73730073, -151650058, -1650655075, -1078001473, 0x52520052, -656932648, -926416696, -960102202, -2122252159, 0x6F6F006F, 0x13130013, 0x63630063, -370605847, -1482227545, -1616969569, -1128529732, 0x29290029, -101121799, 0x2F2F002F, -1263271756, 0x78780078, 0x6060006, -404291353, 0x71710071, -724303660, -1414856533, -2004352888, -1920139123, 0x72720072, -1179057991, -117964552, -1398013780, 0x36360036, 0x2A2A002A, 0x3C3C003C, -235863823, 0x40400040, -741146413, -1145372485, 0x43430043, 0x15150015, -1381171027, 0x77770077, -2139094912, -2105409406, -320077588, 0x27270027, -437976859, -2054881147, 0x35350035, 0xC0C000C, 0x41410041, -269549329, -1819082605, 0x19190019, 0x21210021, 0xE0E000E, 0x4E4E004E, 0x65650065, -1111686979, -1195900744, -1886453617, -336920341, -825360178, 0x30300030, 0x5F5F005F, -976944955, 0x1A1A001A, -505347871, -892731190, 0x47470047, 0x3D3D003D, 0x1010001, -690618154, 0x56560056, 0x4D4D004D, 0xD0D000D, 0x66660066, -859045684, 0x2D2D002D, 0x12120012, 0x20200020, -1313800015, -1718026087, 0x4C4C004C, -1027473214, 0x7E7E007E, 0x5050005, -1212743497, 0x31310031, 0x17170017, -673775401, 0x58580058, 0x61610061, 0x1B1B001B, 0x1C1C001C, 0xF0F000F, 0x16160016, 0x18180018, 0x22220022, 0x44440044, -1296957262, -1246429003, -1852768111, 0x8080008, -1465384792, -50593540, 0x50500050, -791674672, 0x7D7D007D, -1987510135, -1751711593, 0x5B5B005B, -1785397099, -65281, -757989166, -993787708, 0x48480048, -134807305, -606404389, 0x3030003, -623247142, 0x3F3F003F, -1802239852, 0x5C5C005C, 0x2020002, 0x4A4A004A, 0x33330033, 0x67670067, -202178317, 0x7F7F007F, -488505118, -1684340581, 0x26260026, 0x37370037, 0x3B3B003B, -1768554346, 0x4B4B004B, -1094844226, 0x2E2E002E, 0x79790079, -1936981876, 0x6E6E006E, -1903296370, -168492811, -1229586250, -33750787, 0x59590059, -1734868840, 0x6A6A006A, 0x46460046, -1162215238, 0x25250025, 0x42420042, -1566441310, -84279046, 0x7070007, 0x55550055, -286392082, 0xA0A000A, 0x49490049, 0x68680068, 0x38380038, -1532755804, 0x28280028, 0x7B7B007B, -909573943, -1044315967, -471662365, -185335564, -943259449, -1633812322};
    private static final int[] SBOX2_0222 = new int[]{0xE0E0E0, 328965, 0x585858, 0xD9D9D9, 0x676767, 0x4E4E4E, 0x818181, 0xCBCBCB, 0xC9C9C9, 723723, 0xAEAEAE, 0x6A6A6A, 0xD5D5D5, 0x181818, 0x5D5D5D, 0x828282, 0x464646, 0xDFDFDF, 0xD6D6D6, 0x272727, 0x8A8A8A, 0x323232, 0x4B4B4B, 0x424242, 0xDBDBDB, 0x1C1C1C, 0x9E9E9E, 0x9C9C9C, 0x3A3A3A, 0xCACACA, 0x252525, 0x7B7B7B, 855309, 0x717171, 0x5F5F5F, 0x1F1F1F, 0xF8F8F8, 0xD7D7D7, 0x3E3E3E, 0x9D9D9D, 0x7C7C7C, 0x606060, 0xB9B9B9, 0xBEBEBE, 0xBCBCBC, 0x8B8B8B, 0x161616, 0x343434, 0x4D4D4D, 0xC3C3C3, 0x727272, 0x959595, 0xABABAB, 0x8E8E8E, 0xBABABA, 0x7A7A7A, 0xB3B3B3, 131586, 0xB4B4B4, 0xADADAD, 0xA2A2A2, 0xACACAC, 0xD8D8D8, 0x9A9A9A, 0x171717, 0x1A1A1A, 0x353535, 0xCCCCCC, 0xF7F7F7, 0x999999, 0x616161, 0x5A5A5A, 0xE8E8E8, 0x242424, 0x565656, 0x404040, 0xE1E1E1, 0x636363, 592137, 0x333333, 0xBFBFBF, 0x989898, 0x979797, 0x858585, 0x686868, 0xFCFCFC, 0xECECEC, 657930, 0xDADADA, 0x6F6F6F, 0x535353, 0x626262, 0xA3A3A3, 0x2E2E2E, 526344, 0xAFAFAF, 0x282828, 0xB0B0B0, 0x747474, 0xC2C2C2, 0xBDBDBD, 0x363636, 0x222222, 0x383838, 0x646464, 0x1E1E1E, 0x393939, 0x2C2C2C, 0xA6A6A6, 0x303030, 0xE5E5E5, 0x444444, 0xFDFDFD, 0x888888, 0x9F9F9F, 0x656565, 0x878787, 0x6B6B6B, 0xF4F4F4, 0x232323, 0x484848, 0x101010, 0xD1D1D1, 0x515151, 0xC0C0C0, 0xF9F9F9, 0xD2D2D2, 0xA0A0A0, 0x555555, 0xA1A1A1, 0x414141, 0xFAFAFA, 0x434343, 0x131313, 0xC4C4C4, 0x2F2F2F, 0xA8A8A8, 0xB6B6B6, 0x3C3C3C, 0x2B2B2B, 0xC1C1C1, 0xFFFFFF, 0xC8C8C8, 0xA5A5A5, 0x202020, 0x898989, 0, 0x909090, 0x474747, 0xEFEFEF, 0xEAEAEA, 0xB7B7B7, 0x151515, 394758, 0xCDCDCD, 0xB5B5B5, 0x121212, 0x7E7E7E, 0xBBBBBB, 0x292929, 986895, 0xB8B8B8, 460551, 263172, 0x9B9B9B, 0x949494, 0x212121, 0x666666, 0xE6E6E6, 0xCECECE, 0xEDEDED, 0xE7E7E7, 0x3B3B3B, 0xFEFEFE, 0x7F7F7F, 0xC5C5C5, 0xA4A4A4, 0x373737, 0xB1B1B1, 0x4C4C4C, 0x919191, 0x6E6E6E, 0x8D8D8D, 0x767676, 197379, 0x2D2D2D, 0xDEDEDE, 0x969696, 0x262626, 0x7D7D7D, 0xC6C6C6, 0x5C5C5C, 0xD3D3D3, 0xF2F2F2, 0x4F4F4F, 0x191919, 0x3F3F3F, 0xDCDCDC, 0x797979, 0x1D1D1D, 0x525252, 0xEBEBEB, 0xF3F3F3, 0x6D6D6D, 0x5E5E5E, 0xFBFBFB, 0x696969, 0xB2B2B2, 0xF0F0F0, 0x313131, 789516, 0xD4D4D4, 0xCFCFCF, 0x8C8C8C, 0xE2E2E2, 0x757575, 0xA9A9A9, 0x4A4A4A, 0x575757, 0x848484, 0x111111, 0x454545, 0x1B1B1B, 0xF5F5F5, 0xE4E4E4, 921102, 0x737373, 0xAAAAAA, 0xF1F1F1, 0xDDDDDD, 0x595959, 0x141414, 0x6C6C6C, 0x929292, 0x545454, 0xD0D0D0, 0x787878, 0x707070, 0xE3E3E3, 0x494949, 0x808080, 0x505050, 0xA7A7A7, 0xF6F6F6, 0x777777, 0x939393, 0x868686, 0x838383, 0x2A2A2A, 0xC7C7C7, 0x5B5B5B, 0xE9E9E9, 0xEEEEEE, 0x8F8F8F, 65793, 0x3D3D3D};
    private static final int[] SBOX3_3033 = new int[]{0x38003838, 0x41004141, 0x16001616, 0x76007676, -654255655, -1828678765, 0x60006060, -234818830, 0x72007272, -1040137534, -1426019413, -1711236454, 0x75007575, 0x6000606, 0x57005757, -1610571616, -1862233711, -150931465, -1258244683, -922695223, -1577016670, -1946121076, -771697966, -1879011184, -167708938, 0x7000707, -1493129305, 0x27002727, -1912566130, -1308577102, 0x49004949, -570368290, 0x43004343, 0x5C005C5C, -687810601, -956250169, 0x3E003E3E, -184486411, -1895788657, 0x67006767, 0x1F001F1F, 0x18001818, 0x6E006E6E, -1358909521, 0x2F002F2F, -503258398, -2063563387, 0xD000D0D, 0x53005353, -268373776, -1677681508, 0x65006565, -369038614, -1560239197, -1375686994, -1644126562, -335483668, -2147450752, 0x2D002D2D, 0x6B006B6B, -1476351832, 0x2B002B2B, 0x36003636, -1509906778, -989805115, -2046785914, 0x4D004D4D, 0x33003333, -50266627, 0x66006666, 0x58005858, -1778346346, 0x3A003A3A, 0x9000909, -1795123819, 0x10001010, 0x78007878, -671033128, 0x42004242, -872362804, -285151249, 0x26002626, -452925979, 0x61006161, 0x1A001A1A, 0x3F003F3F, 0x3B003B3B, -2113895806, -1241467210, -620700709, -738143020, -1744791400, -402593560, -1962898549, 0x2000202, -352261141, 0xA000A0A, 0x2C002C2C, 0x1D001D1D, -1342132048, 0x6F006F6F, -1929343603, -2013230968, 0xE000E0E, 0x19001919, -2030008441, 0x4E004E4E, 0xB000B0B, -1459574359, 0xC000C0C, 0x79007979, 0x11001111, 0x7F007F7F, 0x22002222, -419371033, 0x59005959, -520035871, -637478182, 0x3D003D3D, -939472696, 0x12001212, 0x4000404, 0x74007474, 0x54005454, 0x30003030, 0x7E007E7E, -1275022156, 0x28002828, 0x55005555, 0x68006868, 0x50005050, -1107247426, -805252912, -1006582588, 0x31003131, -889140277, 0x2A002A2A, -1392464467, 0xF000F0F, -905917750, 0x70007070, -16711681, 0x32003232, 0x69006969, 0x8000808, 0x62006262, 0, 0x24002424, -788475439, -83821573, -1174357318, -318706195, 0x45004545, -2130673279, 0x73007373, 0x6D006D6D, -2080340860, -1627349089, -301928722, 0x4A004A4A, -1023360061, 0x2E002E2E, -1056915007, 0x1000101, -436148506, 0x25002525, 0x48004848, -1728013927, -1191134791, -1291799629, 0x7B007B7B, -117376519, -838807858, -1090469953, -553590817, 0x71007171, 0x29002929, -855585331, 0x6C006C6C, 0x13001313, 0x64006464, -1694458981, 0x63006363, -1660904035, -1073692480, 0x4B004B4B, -1224689737, -1526684251, -1996453495, 0x5F005F5F, -1325354575, 0x17001717, -201263884, -1140802372, -754920493, 0x46004646, -822030385, 0x37003737, 0x5E005E5E, 0x47004747, -1811901292, -100599046, -67044100, 0x5B005B5B, -1761568873, -33489154, 0x5A005A5A, -1409241940, 0x3C003C3C, 0x4C004C4C, 0x3000303, 0x35003535, -218041357, 0x23002323, -1207912264, 0x5D005D5D, 0x6A006A6A, -1845456238, -721365547, 0x21002121, 0x44004444, 0x51005151, -973027642, 0x7D007D7D, 0x39003939, -2097118333, -603923236, -1442796886, 0x7C007C7C, 0x77007777, 0x56005656, 0x5000505, 0x1B001B1B, -1543461724, 0x15001515, 0x34003434, 0x1E001E1E, 0x1C001C1C, -134153992, 0x52005252, 0x20002020, 0x14001414, -385816087, -1124024899, -587145763, -469703452, -1593794143, -536813344, -1979676022, -251596303, -704588074, 0x7A007A7A, -1157579845, -486480925, 0x40004040, 0x4F004F4F};

    private static int rightRotate(int n, int n2) {
        return (n >>> n2) + (n << 32 - n2);
    }

    private static int leftRotate(int n, int n2) {
        return (n << n2) + (n >>> 32 - n2);
    }

    private static void roldq(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[0 + n3] = nArray[0 + n2] << n | nArray[1 + n2] >>> 32 - n;
        nArray2[1 + n3] = nArray[1 + n2] << n | nArray[2 + n2] >>> 32 - n;
        nArray2[2 + n3] = nArray[2 + n2] << n | nArray[3 + n2] >>> 32 - n;
        nArray2[3 + n3] = nArray[3 + n2] << n | nArray[0 + n2] >>> 32 - n;
        nArray[0 + n2] = nArray2[0 + n3];
        nArray[1 + n2] = nArray2[1 + n3];
        nArray[2 + n2] = nArray2[2 + n3];
        nArray[3 + n2] = nArray2[3 + n3];
    }

    private static void decroldq(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[2 + n3] = nArray[0 + n2] << n | nArray[1 + n2] >>> 32 - n;
        nArray2[3 + n3] = nArray[1 + n2] << n | nArray[2 + n2] >>> 32 - n;
        nArray2[0 + n3] = nArray[2 + n2] << n | nArray[3 + n2] >>> 32 - n;
        nArray2[1 + n3] = nArray[3 + n2] << n | nArray[0 + n2] >>> 32 - n;
        nArray[0 + n2] = nArray2[2 + n3];
        nArray[1 + n2] = nArray2[3 + n3];
        nArray[2 + n2] = nArray2[0 + n3];
        nArray[3 + n2] = nArray2[1 + n3];
    }

    private static void roldqo32(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[0 + n3] = nArray[1 + n2] << n - 32 | nArray[2 + n2] >>> 64 - n;
        nArray2[1 + n3] = nArray[2 + n2] << n - 32 | nArray[3 + n2] >>> 64 - n;
        nArray2[2 + n3] = nArray[3 + n2] << n - 32 | nArray[0 + n2] >>> 64 - n;
        nArray2[3 + n3] = nArray[0 + n2] << n - 32 | nArray[1 + n2] >>> 64 - n;
        nArray[0 + n2] = nArray2[0 + n3];
        nArray[1 + n2] = nArray2[1 + n3];
        nArray[2 + n2] = nArray2[2 + n3];
        nArray[3 + n2] = nArray2[3 + n3];
    }

    private static void decroldqo32(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[2 + n3] = nArray[1 + n2] << n - 32 | nArray[2 + n2] >>> 64 - n;
        nArray2[3 + n3] = nArray[2 + n2] << n - 32 | nArray[3 + n2] >>> 64 - n;
        nArray2[0 + n3] = nArray[3 + n2] << n - 32 | nArray[0 + n2] >>> 64 - n;
        nArray2[1 + n3] = nArray[0 + n2] << n - 32 | nArray[1 + n2] >>> 64 - n;
        nArray[0 + n2] = nArray2[2 + n3];
        nArray[1 + n2] = nArray2[3 + n3];
        nArray[2 + n2] = nArray2[0 + n3];
        nArray[3 + n2] = nArray2[1 + n3];
    }

    private int bytes2int(byte[] byArray, int n) {
        int n2 = 0;
        for (int i = 0; i < 4; ++i) {
            n2 = (n2 << 8) + (byArray[i + n] & 0xFF);
        }
        return n2;
    }

    private void int2bytes(int n, byte[] byArray, int n2) {
        for (int i = 0; i < 4; ++i) {
            byArray[3 - i + n2] = (byte)n;
            n >>>= 8;
        }
    }

    private void camelliaF2(int[] nArray, int[] nArray2, int n) {
        int n2 = nArray[0] ^ nArray2[0 + n];
        int n3 = SBOX4_4404[n2 & 0xFF];
        n3 ^= SBOX3_3033[n2 >>> 8 & 0xFF];
        n3 ^= SBOX2_0222[n2 >>> 16 & 0xFF];
        int n4 = nArray[1] ^ nArray2[1 + n];
        int n5 = SBOX1_1110[n4 & 0xFF];
        n5 ^= SBOX4_4404[n4 >>> 8 & 0xFF];
        n5 ^= SBOX3_3033[n4 >>> 16 & 0xFF];
        nArray[2] = nArray[2] ^ ((n3 ^= SBOX1_1110[n2 >>> 24 & 0xFF]) ^ (n5 ^= SBOX2_0222[n4 >>> 24 & 0xFF]));
        nArray[3] = nArray[3] ^ (n3 ^ n5 ^ CamelliaEngine.rightRotate(n3, 8));
        n2 = nArray[2] ^ nArray2[2 + n];
        n3 = SBOX4_4404[n2 & 0xFF];
        n3 ^= SBOX3_3033[n2 >>> 8 & 0xFF];
        n3 ^= SBOX2_0222[n2 >>> 16 & 0xFF];
        n4 = nArray[3] ^ nArray2[3 + n];
        n5 = SBOX1_1110[n4 & 0xFF];
        n5 ^= SBOX4_4404[n4 >>> 8 & 0xFF];
        n5 ^= SBOX3_3033[n4 >>> 16 & 0xFF];
        nArray[0] = nArray[0] ^ ((n3 ^= SBOX1_1110[n2 >>> 24 & 0xFF]) ^ (n5 ^= SBOX2_0222[n4 >>> 24 & 0xFF]));
        nArray[1] = nArray[1] ^ (n3 ^ n5 ^ CamelliaEngine.rightRotate(n3, 8));
    }

    private void camelliaFLs(int[] nArray, int[] nArray2, int n) {
        nArray[1] = nArray[1] ^ CamelliaEngine.leftRotate(nArray[0] & nArray2[0 + n], 1);
        nArray[0] = nArray[0] ^ (nArray2[1 + n] | nArray[1]);
        nArray[2] = nArray[2] ^ (nArray2[3 + n] | nArray[3]);
        nArray[3] = nArray[3] ^ CamelliaEngine.leftRotate(nArray2[2 + n] & nArray[2], 1);
    }

    private void setKey(boolean bl, byte[] byArray) {
        int n;
        int[] nArray = new int[8];
        int[] nArray2 = new int[4];
        int[] nArray3 = new int[4];
        int[] nArray4 = new int[4];
        switch (byArray.length) {
            case 16: {
                this._keyIs128 = true;
                nArray[0] = this.bytes2int(byArray, 0);
                nArray[1] = this.bytes2int(byArray, 4);
                nArray[2] = this.bytes2int(byArray, 8);
                nArray[3] = this.bytes2int(byArray, 12);
                nArray[7] = 0;
                nArray[6] = 0;
                nArray[5] = 0;
                nArray[4] = 0;
                break;
            }
            case 24: {
                nArray[0] = this.bytes2int(byArray, 0);
                nArray[1] = this.bytes2int(byArray, 4);
                nArray[2] = this.bytes2int(byArray, 8);
                nArray[3] = this.bytes2int(byArray, 12);
                nArray[4] = this.bytes2int(byArray, 16);
                nArray[5] = this.bytes2int(byArray, 20);
                nArray[6] = ~nArray[4];
                nArray[7] = ~nArray[5];
                this._keyIs128 = false;
                break;
            }
            case 32: {
                nArray[0] = this.bytes2int(byArray, 0);
                nArray[1] = this.bytes2int(byArray, 4);
                nArray[2] = this.bytes2int(byArray, 8);
                nArray[3] = this.bytes2int(byArray, 12);
                nArray[4] = this.bytes2int(byArray, 16);
                nArray[5] = this.bytes2int(byArray, 20);
                nArray[6] = this.bytes2int(byArray, 24);
                nArray[7] = this.bytes2int(byArray, 28);
                this._keyIs128 = false;
                break;
            }
            default: {
                throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
            }
        }
        for (n = 0; n < 4; ++n) {
            nArray2[n] = nArray[n] ^ nArray[n + 4];
        }
        this.camelliaF2(nArray2, SIGMA, 0);
        for (n = 0; n < 4; ++n) {
            int n2 = n;
            nArray2[n2] = nArray2[n2] ^ nArray[n];
        }
        this.camelliaF2(nArray2, SIGMA, 4);
        if (this._keyIs128) {
            if (bl) {
                this.kw[0] = nArray[0];
                this.kw[1] = nArray[1];
                this.kw[2] = nArray[2];
                this.kw[3] = nArray[3];
                CamelliaEngine.roldq(15, nArray, 0, this.subkey, 4);
                CamelliaEngine.roldq(30, nArray, 0, this.subkey, 12);
                CamelliaEngine.roldq(15, nArray, 0, nArray4, 0);
                this.subkey[18] = nArray4[2];
                this.subkey[19] = nArray4[3];
                CamelliaEngine.roldq(17, nArray, 0, this.ke, 4);
                CamelliaEngine.roldq(17, nArray, 0, this.subkey, 24);
                CamelliaEngine.roldq(17, nArray, 0, this.subkey, 32);
                this.subkey[0] = nArray2[0];
                this.subkey[1] = nArray2[1];
                this.subkey[2] = nArray2[2];
                this.subkey[3] = nArray2[3];
                CamelliaEngine.roldq(15, nArray2, 0, this.subkey, 8);
                CamelliaEngine.roldq(15, nArray2, 0, this.ke, 0);
                CamelliaEngine.roldq(15, nArray2, 0, nArray4, 0);
                this.subkey[16] = nArray4[0];
                this.subkey[17] = nArray4[1];
                CamelliaEngine.roldq(15, nArray2, 0, this.subkey, 20);
                CamelliaEngine.roldqo32(34, nArray2, 0, this.subkey, 28);
                CamelliaEngine.roldq(17, nArray2, 0, this.kw, 4);
            } else {
                this.kw[4] = nArray[0];
                this.kw[5] = nArray[1];
                this.kw[6] = nArray[2];
                this.kw[7] = nArray[3];
                CamelliaEngine.decroldq(15, nArray, 0, this.subkey, 28);
                CamelliaEngine.decroldq(30, nArray, 0, this.subkey, 20);
                CamelliaEngine.decroldq(15, nArray, 0, nArray4, 0);
                this.subkey[16] = nArray4[0];
                this.subkey[17] = nArray4[1];
                CamelliaEngine.decroldq(17, nArray, 0, this.ke, 0);
                CamelliaEngine.decroldq(17, nArray, 0, this.subkey, 8);
                CamelliaEngine.decroldq(17, nArray, 0, this.subkey, 0);
                this.subkey[34] = nArray2[0];
                this.subkey[35] = nArray2[1];
                this.subkey[32] = nArray2[2];
                this.subkey[33] = nArray2[3];
                CamelliaEngine.decroldq(15, nArray2, 0, this.subkey, 24);
                CamelliaEngine.decroldq(15, nArray2, 0, this.ke, 4);
                CamelliaEngine.decroldq(15, nArray2, 0, nArray4, 0);
                this.subkey[18] = nArray4[2];
                this.subkey[19] = nArray4[3];
                CamelliaEngine.decroldq(15, nArray2, 0, this.subkey, 12);
                CamelliaEngine.decroldqo32(34, nArray2, 0, this.subkey, 4);
                CamelliaEngine.roldq(17, nArray2, 0, this.kw, 0);
            }
        } else {
            for (n = 0; n < 4; ++n) {
                nArray3[n] = nArray2[n] ^ nArray[n + 4];
            }
            this.camelliaF2(nArray3, SIGMA, 8);
            if (bl) {
                this.kw[0] = nArray[0];
                this.kw[1] = nArray[1];
                this.kw[2] = nArray[2];
                this.kw[3] = nArray[3];
                CamelliaEngine.roldqo32(45, nArray, 0, this.subkey, 16);
                CamelliaEngine.roldq(15, nArray, 0, this.ke, 4);
                CamelliaEngine.roldq(17, nArray, 0, this.subkey, 32);
                CamelliaEngine.roldqo32(34, nArray, 0, this.subkey, 44);
                CamelliaEngine.roldq(15, nArray, 4, this.subkey, 4);
                CamelliaEngine.roldq(15, nArray, 4, this.ke, 0);
                CamelliaEngine.roldq(30, nArray, 4, this.subkey, 24);
                CamelliaEngine.roldqo32(34, nArray, 4, this.subkey, 36);
                CamelliaEngine.roldq(15, nArray2, 0, this.subkey, 8);
                CamelliaEngine.roldq(30, nArray2, 0, this.subkey, 20);
                this.ke[8] = nArray2[1];
                this.ke[9] = nArray2[2];
                this.ke[10] = nArray2[3];
                this.ke[11] = nArray2[0];
                CamelliaEngine.roldqo32(49, nArray2, 0, this.subkey, 40);
                this.subkey[0] = nArray3[0];
                this.subkey[1] = nArray3[1];
                this.subkey[2] = nArray3[2];
                this.subkey[3] = nArray3[3];
                CamelliaEngine.roldq(30, nArray3, 0, this.subkey, 12);
                CamelliaEngine.roldq(30, nArray3, 0, this.subkey, 28);
                CamelliaEngine.roldqo32(51, nArray3, 0, this.kw, 4);
            } else {
                this.kw[4] = nArray[0];
                this.kw[5] = nArray[1];
                this.kw[6] = nArray[2];
                this.kw[7] = nArray[3];
                CamelliaEngine.decroldqo32(45, nArray, 0, this.subkey, 28);
                CamelliaEngine.decroldq(15, nArray, 0, this.ke, 4);
                CamelliaEngine.decroldq(17, nArray, 0, this.subkey, 12);
                CamelliaEngine.decroldqo32(34, nArray, 0, this.subkey, 0);
                CamelliaEngine.decroldq(15, nArray, 4, this.subkey, 40);
                CamelliaEngine.decroldq(15, nArray, 4, this.ke, 8);
                CamelliaEngine.decroldq(30, nArray, 4, this.subkey, 20);
                CamelliaEngine.decroldqo32(34, nArray, 4, this.subkey, 8);
                CamelliaEngine.decroldq(15, nArray2, 0, this.subkey, 36);
                CamelliaEngine.decroldq(30, nArray2, 0, this.subkey, 24);
                this.ke[2] = nArray2[1];
                this.ke[3] = nArray2[2];
                this.ke[0] = nArray2[3];
                this.ke[1] = nArray2[0];
                CamelliaEngine.decroldqo32(49, nArray2, 0, this.subkey, 4);
                this.subkey[46] = nArray3[0];
                this.subkey[47] = nArray3[1];
                this.subkey[44] = nArray3[2];
                this.subkey[45] = nArray3[3];
                CamelliaEngine.decroldq(30, nArray3, 0, this.subkey, 32);
                CamelliaEngine.decroldq(30, nArray3, 0, this.subkey, 16);
                CamelliaEngine.roldqo32(51, nArray3, 0, this.kw, 0);
            }
        }
    }

    private int processBlock128(byte[] byArray, int n, byte[] byArray2, int n2) {
        for (int i = 0; i < 4; ++i) {
            this.state[i] = this.bytes2int(byArray, n + i * 4);
            int n3 = i;
            this.state[n3] = this.state[n3] ^ this.kw[i];
        }
        this.camelliaF2(this.state, this.subkey, 0);
        this.camelliaF2(this.state, this.subkey, 4);
        this.camelliaF2(this.state, this.subkey, 8);
        this.camelliaFLs(this.state, this.ke, 0);
        this.camelliaF2(this.state, this.subkey, 12);
        this.camelliaF2(this.state, this.subkey, 16);
        this.camelliaF2(this.state, this.subkey, 20);
        this.camelliaFLs(this.state, this.ke, 4);
        this.camelliaF2(this.state, this.subkey, 24);
        this.camelliaF2(this.state, this.subkey, 28);
        this.camelliaF2(this.state, this.subkey, 32);
        this.state[2] = this.state[2] ^ this.kw[4];
        this.state[3] = this.state[3] ^ this.kw[5];
        this.state[0] = this.state[0] ^ this.kw[6];
        this.state[1] = this.state[1] ^ this.kw[7];
        this.int2bytes(this.state[2], byArray2, n2);
        this.int2bytes(this.state[3], byArray2, n2 + 4);
        this.int2bytes(this.state[0], byArray2, n2 + 8);
        this.int2bytes(this.state[1], byArray2, n2 + 12);
        return 16;
    }

    private int processBlock192or256(byte[] byArray, int n, byte[] byArray2, int n2) {
        for (int i = 0; i < 4; ++i) {
            this.state[i] = this.bytes2int(byArray, n + i * 4);
            int n3 = i;
            this.state[n3] = this.state[n3] ^ this.kw[i];
        }
        this.camelliaF2(this.state, this.subkey, 0);
        this.camelliaF2(this.state, this.subkey, 4);
        this.camelliaF2(this.state, this.subkey, 8);
        this.camelliaFLs(this.state, this.ke, 0);
        this.camelliaF2(this.state, this.subkey, 12);
        this.camelliaF2(this.state, this.subkey, 16);
        this.camelliaF2(this.state, this.subkey, 20);
        this.camelliaFLs(this.state, this.ke, 4);
        this.camelliaF2(this.state, this.subkey, 24);
        this.camelliaF2(this.state, this.subkey, 28);
        this.camelliaF2(this.state, this.subkey, 32);
        this.camelliaFLs(this.state, this.ke, 8);
        this.camelliaF2(this.state, this.subkey, 36);
        this.camelliaF2(this.state, this.subkey, 40);
        this.camelliaF2(this.state, this.subkey, 44);
        this.state[2] = this.state[2] ^ this.kw[4];
        this.state[3] = this.state[3] ^ this.kw[5];
        this.state[0] = this.state[0] ^ this.kw[6];
        this.state[1] = this.state[1] ^ this.kw[7];
        this.int2bytes(this.state[2], byArray2, n2);
        this.int2bytes(this.state[3], byArray2, n2 + 4);
        this.int2bytes(this.state[0], byArray2, n2 + 8);
        this.int2bytes(this.state[1], byArray2, n2 + 12);
        return 16;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        this.setKey(bl, ((KeyParameter)cipherParameters).getKey());
        this.initialised = true;
    }

    public String getAlgorithmName() {
        return "Camellia";
    }

    public int getBlockSize() {
        return 16;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (!this.initialised) {
            throw new IllegalStateException("Camellia engine not initialised");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this._keyIs128) {
            return this.processBlock128(byArray, n, byArray2, n2);
        }
        return this.processBlock192or256(byArray, n, byArray2, n2);
    }

    public void reset() {
    }
}

