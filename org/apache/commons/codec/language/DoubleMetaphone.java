/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;

public class DoubleMetaphone
implements StringEncoder {
    private static final String VOWELS = "AEIOUY";
    private static final String[] SILENT_START = new String[]{"GN", "KN", "PN", "WR", "PS"};
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE = new String[]{"L", "R", "N", "M", "B", "H", "F", "V", "W", " "};
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = new String[]{"ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER"};
    private static final String[] L_T_K_S_N_M_B_Z = new String[]{"L", "T", "K", "S", "N", "M", "B", "Z"};
    private int maxCodeLen = 4;

    public String doubleMetaphone(String value) {
        return this.doubleMetaphone(value, false);
    }

    public String doubleMetaphone(String value, boolean alternate) {
        if ((value = this.cleanInput(value)) == null) {
            return null;
        }
        boolean slavoGermanic = this.isSlavoGermanic(value);
        int index = this.isSilentStart(value) ? 1 : 0;
        DoubleMetaphoneResult result = new DoubleMetaphoneResult(this.getMaxCodeLen());
        block25: while (!result.isComplete() && index <= value.length() - 1) {
            switch (value.charAt(index)) {
                case 'A': 
                case 'E': 
                case 'I': 
                case 'O': 
                case 'U': 
                case 'Y': {
                    index = this.handleAEIOUY(result, index);
                    continue block25;
                }
                case 'B': {
                    result.append('P');
                    index = this.charAt(value, index + 1) == 'B' ? index + 2 : index + 1;
                    continue block25;
                }
                case '\u00c7': {
                    result.append('S');
                    ++index;
                    continue block25;
                }
                case 'C': {
                    index = this.handleC(value, result, index);
                    continue block25;
                }
                case 'D': {
                    index = this.handleD(value, result, index);
                    continue block25;
                }
                case 'F': {
                    result.append('F');
                    index = this.charAt(value, index + 1) == 'F' ? index + 2 : index + 1;
                    continue block25;
                }
                case 'G': {
                    index = this.handleG(value, result, index, slavoGermanic);
                    continue block25;
                }
                case 'H': {
                    index = this.handleH(value, result, index);
                    continue block25;
                }
                case 'J': {
                    index = this.handleJ(value, result, index, slavoGermanic);
                    continue block25;
                }
                case 'K': {
                    result.append('K');
                    index = this.charAt(value, index + 1) == 'K' ? index + 2 : index + 1;
                    continue block25;
                }
                case 'L': {
                    index = this.handleL(value, result, index);
                    continue block25;
                }
                case 'M': {
                    result.append('M');
                    index = this.conditionM0(value, index) ? index + 2 : index + 1;
                    continue block25;
                }
                case 'N': {
                    result.append('N');
                    index = this.charAt(value, index + 1) == 'N' ? index + 2 : index + 1;
                    continue block25;
                }
                case '\u00d1': {
                    result.append('N');
                    ++index;
                    continue block25;
                }
                case 'P': {
                    index = this.handleP(value, result, index);
                    continue block25;
                }
                case 'Q': {
                    result.append('K');
                    index = this.charAt(value, index + 1) == 'Q' ? index + 2 : index + 1;
                    continue block25;
                }
                case 'R': {
                    index = this.handleR(value, result, index, slavoGermanic);
                    continue block25;
                }
                case 'S': {
                    index = this.handleS(value, result, index, slavoGermanic);
                    continue block25;
                }
                case 'T': {
                    index = this.handleT(value, result, index);
                    continue block25;
                }
                case 'V': {
                    result.append('F');
                    index = this.charAt(value, index + 1) == 'V' ? index + 2 : index + 1;
                    continue block25;
                }
                case 'W': {
                    index = this.handleW(value, result, index);
                    continue block25;
                }
                case 'X': {
                    index = this.handleX(value, result, index);
                    continue block25;
                }
                case 'Z': {
                    index = this.handleZ(value, result, index, slavoGermanic);
                    continue block25;
                }
            }
            ++index;
        }
        return alternate ? result.getAlternate() : result.getPrimary();
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
        }
        return this.doubleMetaphone((String)obj);
    }

    @Override
    public String encode(String value) {
        return this.doubleMetaphone(value);
    }

    public boolean isDoubleMetaphoneEqual(String value1, String value2) {
        return this.isDoubleMetaphoneEqual(value1, value2, false);
    }

    public boolean isDoubleMetaphoneEqual(String value1, String value2, boolean alternate) {
        return StringUtils.equals(this.doubleMetaphone(value1, alternate), this.doubleMetaphone(value2, alternate));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int maxCodeLen) {
        this.maxCodeLen = maxCodeLen;
    }

    private int handleAEIOUY(DoubleMetaphoneResult result, int index) {
        if (index == 0) {
            result.append('A');
        }
        return index + 1;
    }

    private int handleC(String value, DoubleMetaphoneResult result, int index) {
        if (this.conditionC0(value, index)) {
            result.append('K');
            index += 2;
        } else if (index == 0 && DoubleMetaphone.contains(value, index, 6, "CAESAR")) {
            result.append('S');
            index += 2;
        } else if (DoubleMetaphone.contains(value, index, 2, "CH")) {
            index = this.handleCH(value, result, index);
        } else if (DoubleMetaphone.contains(value, index, 2, "CZ") && !DoubleMetaphone.contains(value, index - 2, 4, "WICZ")) {
            result.append('S', 'X');
            index += 2;
        } else if (DoubleMetaphone.contains(value, index + 1, 3, "CIA")) {
            result.append('X');
            index += 3;
        } else {
            if (DoubleMetaphone.contains(value, index, 2, "CC") && (index != 1 || this.charAt(value, 0) != 'M')) {
                return this.handleCC(value, result, index);
            }
            if (DoubleMetaphone.contains(value, index, 2, "CK", "CG", "CQ")) {
                result.append('K');
                index += 2;
            } else if (DoubleMetaphone.contains(value, index, 2, "CI", "CE", "CY")) {
                if (DoubleMetaphone.contains(value, index, 3, "CIO", "CIE", "CIA")) {
                    result.append('S', 'X');
                } else {
                    result.append('S');
                }
                index += 2;
            } else {
                result.append('K');
                index = DoubleMetaphone.contains(value, index + 1, 2, " C", " Q", " G") ? (index += 3) : (DoubleMetaphone.contains(value, index + 1, 1, "C", "K", "Q") && !DoubleMetaphone.contains(value, index + 1, 2, "CE", "CI") ? (index += 2) : ++index);
            }
        }
        return index;
    }

    private int handleCC(String value, DoubleMetaphoneResult result, int index) {
        if (DoubleMetaphone.contains(value, index + 2, 1, "I", "E", "H") && !DoubleMetaphone.contains(value, index + 2, 2, "HU")) {
            if (index == 1 && this.charAt(value, index - 1) == 'A' || DoubleMetaphone.contains(value, index - 1, 5, "UCCEE", "UCCES")) {
                result.append("KS");
            } else {
                result.append('X');
            }
            index += 3;
        } else {
            result.append('K');
            index += 2;
        }
        return index;
    }

    private int handleCH(String value, DoubleMetaphoneResult result, int index) {
        if (index > 0 && DoubleMetaphone.contains(value, index, 4, "CHAE")) {
            result.append('K', 'X');
            return index + 2;
        }
        if (this.conditionCH0(value, index)) {
            result.append('K');
            return index + 2;
        }
        if (this.conditionCH1(value, index)) {
            result.append('K');
            return index + 2;
        }
        if (index > 0) {
            if (DoubleMetaphone.contains(value, 0, 2, "MC")) {
                result.append('K');
            } else {
                result.append('X', 'K');
            }
        } else {
            result.append('X');
        }
        return index + 2;
    }

    private int handleD(String value, DoubleMetaphoneResult result, int index) {
        if (DoubleMetaphone.contains(value, index, 2, "DG")) {
            if (DoubleMetaphone.contains(value, index + 2, 1, "I", "E", "Y")) {
                result.append('J');
                index += 3;
            } else {
                result.append("TK");
                index += 2;
            }
        } else if (DoubleMetaphone.contains(value, index, 2, "DT", "DD")) {
            result.append('T');
            index += 2;
        } else {
            result.append('T');
            ++index;
        }
        return index;
    }

    private int handleG(String value, DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        if (this.charAt(value, index + 1) == 'H') {
            index = this.handleGH(value, result, index);
        } else if (this.charAt(value, index + 1) == 'N') {
            if (index == 1 && this.isVowel(this.charAt(value, 0)) && !slavoGermanic) {
                result.append("KN", "N");
            } else if (!DoubleMetaphone.contains(value, index + 2, 2, "EY") && this.charAt(value, index + 1) != 'Y' && !slavoGermanic) {
                result.append("N", "KN");
            } else {
                result.append("KN");
            }
            index += 2;
        } else if (DoubleMetaphone.contains(value, index + 1, 2, "LI") && !slavoGermanic) {
            result.append("KL", "L");
            index += 2;
        } else if (index == 0 && (this.charAt(value, index + 1) == 'Y' || DoubleMetaphone.contains(value, index + 1, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            result.append('K', 'J');
            index += 2;
        } else if (!(!DoubleMetaphone.contains(value, index + 1, 2, "ER") && this.charAt(value, index + 1) != 'Y' || DoubleMetaphone.contains(value, 0, 6, "DANGER", "RANGER", "MANGER") || DoubleMetaphone.contains(value, index - 1, 1, "E", "I") || DoubleMetaphone.contains(value, index - 1, 3, "RGY", "OGY"))) {
            result.append('K', 'J');
            index += 2;
        } else if (DoubleMetaphone.contains(value, index + 1, 1, "E", "I", "Y") || DoubleMetaphone.contains(value, index - 1, 4, "AGGI", "OGGI")) {
            if (DoubleMetaphone.contains(value, 0, 4, "VAN ", "VON ") || DoubleMetaphone.contains(value, 0, 3, "SCH") || DoubleMetaphone.contains(value, index + 1, 2, "ET")) {
                result.append('K');
            } else if (DoubleMetaphone.contains(value, index + 1, 3, "IER")) {
                result.append('J');
            } else {
                result.append('J', 'K');
            }
            index += 2;
        } else if (this.charAt(value, index + 1) == 'G') {
            index += 2;
            result.append('K');
        } else {
            ++index;
            result.append('K');
        }
        return index;
    }

    private int handleGH(String value, DoubleMetaphoneResult result, int index) {
        if (index > 0 && !this.isVowel(this.charAt(value, index - 1))) {
            result.append('K');
            index += 2;
        } else if (index == 0) {
            if (this.charAt(value, index + 2) == 'I') {
                result.append('J');
            } else {
                result.append('K');
            }
            index += 2;
        } else if (index > 1 && DoubleMetaphone.contains(value, index - 2, 1, "B", "H", "D") || index > 2 && DoubleMetaphone.contains(value, index - 3, 1, "B", "H", "D") || index > 3 && DoubleMetaphone.contains(value, index - 4, 1, "B", "H")) {
            index += 2;
        } else {
            if (index > 2 && this.charAt(value, index - 1) == 'U' && DoubleMetaphone.contains(value, index - 3, 1, "C", "G", "L", "R", "T")) {
                result.append('F');
            } else if (index > 0 && this.charAt(value, index - 1) != 'I') {
                result.append('K');
            }
            index += 2;
        }
        return index;
    }

    private int handleH(String value, DoubleMetaphoneResult result, int index) {
        if ((index == 0 || this.isVowel(this.charAt(value, index - 1))) && this.isVowel(this.charAt(value, index + 1))) {
            result.append('H');
            index += 2;
        } else {
            ++index;
        }
        return index;
    }

    private int handleJ(String value, DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        if (DoubleMetaphone.contains(value, index, 4, "JOSE") || DoubleMetaphone.contains(value, 0, 4, "SAN ")) {
            if (index == 0 && this.charAt(value, index + 4) == ' ' || value.length() == 4 || DoubleMetaphone.contains(value, 0, 4, "SAN ")) {
                result.append('H');
            } else {
                result.append('J', 'H');
            }
            ++index;
        } else {
            if (index == 0 && !DoubleMetaphone.contains(value, index, 4, "JOSE")) {
                result.append('J', 'A');
            } else if (this.isVowel(this.charAt(value, index - 1)) && !slavoGermanic && (this.charAt(value, index + 1) == 'A' || this.charAt(value, index + 1) == 'O')) {
                result.append('J', 'H');
            } else if (index == value.length() - 1) {
                result.append('J', ' ');
            } else if (!DoubleMetaphone.contains(value, index + 1, 1, L_T_K_S_N_M_B_Z) && !DoubleMetaphone.contains(value, index - 1, 1, "S", "K", "L")) {
                result.append('J');
            }
            index = this.charAt(value, index + 1) == 'J' ? (index += 2) : ++index;
        }
        return index;
    }

    private int handleL(String value, DoubleMetaphoneResult result, int index) {
        if (this.charAt(value, index + 1) == 'L') {
            if (this.conditionL0(value, index)) {
                result.appendPrimary('L');
            } else {
                result.append('L');
            }
            index += 2;
        } else {
            ++index;
            result.append('L');
        }
        return index;
    }

    private int handleP(String value, DoubleMetaphoneResult result, int index) {
        if (this.charAt(value, index + 1) == 'H') {
            result.append('F');
            index += 2;
        } else {
            result.append('P');
            index = DoubleMetaphone.contains(value, index + 1, 1, "P", "B") ? index + 2 : index + 1;
        }
        return index;
    }

    private int handleR(String value, DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        if (index == value.length() - 1 && !slavoGermanic && DoubleMetaphone.contains(value, index - 2, 2, "IE") && !DoubleMetaphone.contains(value, index - 4, 2, "ME", "MA")) {
            result.appendAlternate('R');
        } else {
            result.append('R');
        }
        return this.charAt(value, index + 1) == 'R' ? index + 2 : index + 1;
    }

    private int handleS(String value, DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        if (DoubleMetaphone.contains(value, index - 1, 3, "ISL", "YSL")) {
            ++index;
        } else if (index == 0 && DoubleMetaphone.contains(value, index, 5, "SUGAR")) {
            result.append('X', 'S');
            ++index;
        } else if (DoubleMetaphone.contains(value, index, 2, "SH")) {
            if (DoubleMetaphone.contains(value, index + 1, 4, "HEIM", "HOEK", "HOLM", "HOLZ")) {
                result.append('S');
            } else {
                result.append('X');
            }
            index += 2;
        } else if (DoubleMetaphone.contains(value, index, 3, "SIO", "SIA") || DoubleMetaphone.contains(value, index, 4, "SIAN")) {
            if (slavoGermanic) {
                result.append('S');
            } else {
                result.append('S', 'X');
            }
            index += 3;
        } else if (index == 0 && DoubleMetaphone.contains(value, index + 1, 1, "M", "N", "L", "W") || DoubleMetaphone.contains(value, index + 1, 1, "Z")) {
            result.append('S', 'X');
            index = DoubleMetaphone.contains(value, index + 1, 1, "Z") ? index + 2 : index + 1;
        } else if (DoubleMetaphone.contains(value, index, 2, "SC")) {
            index = this.handleSC(value, result, index);
        } else {
            if (index == value.length() - 1 && DoubleMetaphone.contains(value, index - 2, 2, "AI", "OI")) {
                result.appendAlternate('S');
            } else {
                result.append('S');
            }
            index = DoubleMetaphone.contains(value, index + 1, 1, "S", "Z") ? index + 2 : index + 1;
        }
        return index;
    }

    private int handleSC(String value, DoubleMetaphoneResult result, int index) {
        if (this.charAt(value, index + 2) == 'H') {
            if (DoubleMetaphone.contains(value, index + 3, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (DoubleMetaphone.contains(value, index + 3, 2, "ER", "EN")) {
                    result.append("X", "SK");
                } else {
                    result.append("SK");
                }
            } else if (index == 0 && !this.isVowel(this.charAt(value, 3)) && this.charAt(value, 3) != 'W') {
                result.append('X', 'S');
            } else {
                result.append('X');
            }
        } else if (DoubleMetaphone.contains(value, index + 2, 1, "I", "E", "Y")) {
            result.append('S');
        } else {
            result.append("SK");
        }
        return index + 3;
    }

    private int handleT(String value, DoubleMetaphoneResult result, int index) {
        if (DoubleMetaphone.contains(value, index, 4, "TION")) {
            result.append('X');
            index += 3;
        } else if (DoubleMetaphone.contains(value, index, 3, "TIA", "TCH")) {
            result.append('X');
            index += 3;
        } else if (DoubleMetaphone.contains(value, index, 2, "TH") || DoubleMetaphone.contains(value, index, 3, "TTH")) {
            if (DoubleMetaphone.contains(value, index + 2, 2, "OM", "AM") || DoubleMetaphone.contains(value, 0, 4, "VAN ", "VON ") || DoubleMetaphone.contains(value, 0, 3, "SCH")) {
                result.append('T');
            } else {
                result.append('0', 'T');
            }
            index += 2;
        } else {
            result.append('T');
            index = DoubleMetaphone.contains(value, index + 1, 1, "T", "D") ? index + 2 : index + 1;
        }
        return index;
    }

    private int handleW(String value, DoubleMetaphoneResult result, int index) {
        if (DoubleMetaphone.contains(value, index, 2, "WR")) {
            result.append('R');
            index += 2;
        } else if (index == 0 && (this.isVowel(this.charAt(value, index + 1)) || DoubleMetaphone.contains(value, index, 2, "WH"))) {
            if (this.isVowel(this.charAt(value, index + 1))) {
                result.append('A', 'F');
            } else {
                result.append('A');
            }
            ++index;
        } else if (index == value.length() - 1 && this.isVowel(this.charAt(value, index - 1)) || DoubleMetaphone.contains(value, index - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") || DoubleMetaphone.contains(value, 0, 3, "SCH")) {
            result.appendAlternate('F');
            ++index;
        } else if (DoubleMetaphone.contains(value, index, 4, "WICZ", "WITZ")) {
            result.append("TS", "FX");
            index += 4;
        } else {
            ++index;
        }
        return index;
    }

    private int handleX(String value, DoubleMetaphoneResult result, int index) {
        if (index == 0) {
            result.append('S');
            ++index;
        } else {
            if (index != value.length() - 1 || !DoubleMetaphone.contains(value, index - 3, 3, "IAU", "EAU") && !DoubleMetaphone.contains(value, index - 2, 2, "AU", "OU")) {
                result.append("KS");
            }
            index = DoubleMetaphone.contains(value, index + 1, 1, "C", "X") ? index + 2 : index + 1;
        }
        return index;
    }

    private int handleZ(String value, DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        if (this.charAt(value, index + 1) == 'H') {
            result.append('J');
            index += 2;
        } else {
            if (DoubleMetaphone.contains(value, index + 1, 2, "ZO", "ZI", "ZA") || slavoGermanic && index > 0 && this.charAt(value, index - 1) != 'T') {
                result.append("S", "TS");
            } else {
                result.append('S');
            }
            index = this.charAt(value, index + 1) == 'Z' ? index + 2 : index + 1;
        }
        return index;
    }

    private boolean conditionC0(String value, int index) {
        if (DoubleMetaphone.contains(value, index, 4, "CHIA")) {
            return true;
        }
        if (index <= 1) {
            return false;
        }
        if (this.isVowel(this.charAt(value, index - 2))) {
            return false;
        }
        if (!DoubleMetaphone.contains(value, index - 1, 3, "ACH")) {
            return false;
        }
        char c = this.charAt(value, index + 2);
        return c != 'I' && c != 'E' || DoubleMetaphone.contains(value, index - 2, 6, "BACHER", "MACHER");
    }

    private boolean conditionCH0(String value, int index) {
        if (index != 0) {
            return false;
        }
        if (!DoubleMetaphone.contains(value, index + 1, 5, "HARAC", "HARIS") && !DoubleMetaphone.contains(value, index + 1, 3, "HOR", "HYM", "HIA", "HEM")) {
            return false;
        }
        return !DoubleMetaphone.contains(value, 0, 5, "CHORE");
    }

    private boolean conditionCH1(String value, int index) {
        return DoubleMetaphone.contains(value, 0, 4, "VAN ", "VON ") || DoubleMetaphone.contains(value, 0, 3, "SCH") || DoubleMetaphone.contains(value, index - 2, 6, "ORCHES", "ARCHIT", "ORCHID") || DoubleMetaphone.contains(value, index + 2, 1, "T", "S") || (DoubleMetaphone.contains(value, index - 1, 1, "A", "O", "U", "E") || index == 0) && (DoubleMetaphone.contains(value, index + 2, 1, L_R_N_M_B_H_F_V_W_SPACE) || index + 1 == value.length() - 1);
    }

    private boolean conditionL0(String value, int index) {
        if (index == value.length() - 3 && DoubleMetaphone.contains(value, index - 1, 4, "ILLO", "ILLA", "ALLE")) {
            return true;
        }
        return (DoubleMetaphone.contains(value, value.length() - 2, 2, "AS", "OS") || DoubleMetaphone.contains(value, value.length() - 1, 1, "A", "O")) && DoubleMetaphone.contains(value, index - 1, 4, "ALLE");
    }

    private boolean conditionM0(String value, int index) {
        if (this.charAt(value, index + 1) == 'M') {
            return true;
        }
        return DoubleMetaphone.contains(value, index - 1, 3, "UMB") && (index + 1 == value.length() - 1 || DoubleMetaphone.contains(value, index + 2, 2, "ER"));
    }

    private boolean isSlavoGermanic(String value) {
        return value.indexOf(87) > -1 || value.indexOf(75) > -1 || value.indexOf("CZ") > -1 || value.indexOf("WITZ") > -1;
    }

    private boolean isVowel(char ch) {
        return VOWELS.indexOf(ch) != -1;
    }

    private boolean isSilentStart(String value) {
        boolean result = false;
        for (String element : SILENT_START) {
            if (!value.startsWith(element)) continue;
            result = true;
            break;
        }
        return result;
    }

    private String cleanInput(String input) {
        if (input == null) {
            return null;
        }
        if ((input = input.trim()).length() == 0) {
            return null;
        }
        return input.toUpperCase(Locale.ENGLISH);
    }

    protected char charAt(String value, int index) {
        if (index < 0 || index >= value.length()) {
            return '\u0000';
        }
        return value.charAt(index);
    }

    protected static boolean contains(String value, int start, int length, String ... criteria) {
        boolean result = false;
        if (start >= 0 && start + length <= value.length()) {
            String target = value.substring(start, start + length);
            for (String element : criteria) {
                if (!target.equals(element)) continue;
                result = true;
                break;
            }
        }
        return result;
    }

    public class DoubleMetaphoneResult {
        private final StringBuilder primary;
        private final StringBuilder alternate;
        private final int maxLength;

        public DoubleMetaphoneResult(int maxLength) {
            this.primary = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.alternate = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.maxLength = maxLength;
        }

        public void append(char value) {
            this.appendPrimary(value);
            this.appendAlternate(value);
        }

        public void append(char primary, char alternate) {
            this.appendPrimary(primary);
            this.appendAlternate(alternate);
        }

        public void appendPrimary(char value) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(value);
            }
        }

        public void appendAlternate(char value) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(value);
            }
        }

        public void append(String value) {
            this.appendPrimary(value);
            this.appendAlternate(value);
        }

        public void append(String primary, String alternate) {
            this.appendPrimary(primary);
            this.appendAlternate(alternate);
        }

        public void appendPrimary(String value) {
            int addChars = this.maxLength - this.primary.length();
            if (value.length() <= addChars) {
                this.primary.append(value);
            } else {
                this.primary.append(value.substring(0, addChars));
            }
        }

        public void appendAlternate(String value) {
            int addChars = this.maxLength - this.alternate.length();
            if (value.length() <= addChars) {
                this.alternate.append(value);
            } else {
                this.alternate.append(value.substring(0, addChars));
            }
        }

        public String getPrimary() {
            return this.primary.toString();
        }

        public String getAlternate() {
            return this.alternate.toString();
        }

        public boolean isComplete() {
            return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
        }
    }
}

