/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatResult;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Date1904Support;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.ExcelGeneralNumberFormat;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.FractionFormat;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class DataFormatter
implements Observer {
    private static final String defaultFractionWholePartFormat = "#";
    private static final String defaultFractionFractionPartFormat = "#/##";
    private static final Pattern numPattern = Pattern.compile("[0#]+");
    private static final Pattern daysAsText = Pattern.compile("([d]{3,})", 2);
    private static final Pattern amPmPattern = Pattern.compile("(([AP])[M/P]*)", 2);
    private static final Pattern rangeConditionalPattern = Pattern.compile(".*\\[\\s*(>|>=|<|<=|=)\\s*[0-9]*\\.*[0-9].*");
    private static final Pattern localePatternGroup = Pattern.compile("(\\[\\$[^-\\]]*-[0-9A-Z]+])");
    private static final Pattern colorPattern = Pattern.compile("(\\[BLACK])|(\\[BLUE])|(\\[CYAN])|(\\[GREEN])|(\\[MAGENTA])|(\\[RED])|(\\[WHITE])|(\\[YELLOW])|(\\[COLOR\\s*\\d])|(\\[COLOR\\s*[0-5]\\d])", 2);
    private static final Pattern fractionPattern = Pattern.compile("(?:([#\\d]+)\\s+)?(#+)\\s*/\\s*([#\\d]+)");
    private static final Pattern fractionStripper = Pattern.compile("(\"[^\"]*\")|([^ ?#\\d/]+)");
    private static final Pattern alternateGrouping = Pattern.compile("([#0]([^.#0])[#0]{3})");
    private static final String invalidDateTimeString;
    private DecimalFormatSymbols decimalSymbols;
    private DateFormatSymbols dateSymbols;
    private DateFormat defaultDateformat;
    private Format generalNumberFormat;
    private Format defaultNumFormat;
    private final Map<String, Format> formats = new HashMap<String, Format>();
    private final boolean emulateCSV;
    private Locale locale;
    private boolean localeIsAdapting = true;
    private final LocaleChangeObservable localeChangedObservable = new LocaleChangeObservable();
    private static POILogger logger;

    public DataFormatter() {
        this(false);
    }

    public DataFormatter(boolean emulateCSV) {
        this(LocaleUtil.getUserLocale(), true, emulateCSV);
    }

    public DataFormatter(Locale locale) {
        this(locale, false);
    }

    public DataFormatter(Locale locale, boolean emulateCSV) {
        this(locale, false, emulateCSV);
    }

    public DataFormatter(Locale locale, boolean localeIsAdapting, boolean emulateCSV) {
        this.localeChangedObservable.addObserver(this);
        this.localeChangedObservable.checkForLocaleChange(locale);
        this.localeIsAdapting = localeIsAdapting;
        this.emulateCSV = emulateCSV;
    }

    private Format getFormat(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        if (cell == null) {
            return null;
        }
        ExcelNumberFormat numFmt = ExcelNumberFormat.from(cell, cfEvaluator);
        if (numFmt == null) {
            return null;
        }
        int formatIndex = numFmt.getIdx();
        String formatStr = numFmt.getFormat();
        if (formatStr == null || formatStr.trim().length() == 0) {
            return null;
        }
        return this.getFormat(cell.getNumericCellValue(), formatIndex, formatStr, this.isDate1904(cell));
    }

    private boolean isDate1904(Cell cell) {
        if (cell != null && cell.getSheet().getWorkbook() instanceof Date1904Support) {
            return ((Date1904Support)((Object)cell.getSheet().getWorkbook())).isDate1904();
        }
        return false;
    }

    private Format getFormat(double cellValue, int formatIndex, String formatStrIn, boolean use1904Windowing) {
        Format format;
        this.localeChangedObservable.checkForLocaleChange();
        String formatStr = formatStrIn;
        if (formatStr.contains(";") && (formatStr.indexOf(59) != formatStr.lastIndexOf(59) || rangeConditionalPattern.matcher(formatStr).matches())) {
            try {
                CellFormat cfmt = CellFormat.getInstance(this.locale, formatStr);
                Comparable<Double> cellValueO = cellValue;
                if (DateUtil.isADateFormat(formatIndex, formatStr) && cellValueO != 0.0) {
                    cellValueO = DateUtil.getJavaDate(cellValue, use1904Windowing);
                }
                return new CellFormatResultWrapper(cfmt.apply(cellValueO));
            } catch (Exception e) {
                logger.log(5, "Formatting failed for format " + formatStr + ", falling back", e);
            }
        }
        if (this.emulateCSV && cellValue == 0.0 && formatStr.contains(defaultFractionWholePartFormat) && !formatStr.contains("0")) {
            formatStr = formatStr.replaceAll(defaultFractionWholePartFormat, "");
        }
        if ((format = this.formats.get(formatStr)) != null) {
            return format;
        }
        if ("General".equalsIgnoreCase(formatStr) || "@".equals(formatStr)) {
            return this.generalNumberFormat;
        }
        format = this.createFormat(cellValue, formatIndex, formatStr);
        this.formats.put(formatStr, format);
        return format;
    }

    public Format createFormat(Cell cell) {
        short formatIndex = cell.getCellStyle().getDataFormat();
        String formatStr = cell.getCellStyle().getDataFormatString();
        return this.createFormat(cell.getNumericCellValue(), formatIndex, formatStr);
    }

    private Format createFormat(double cellValue, int formatIndex, String sFormat) {
        String nFormatStr;
        String colour;
        int at;
        this.localeChangedObservable.checkForLocaleChange();
        String formatStr = sFormat;
        Matcher colourM = colorPattern.matcher(formatStr);
        while (colourM.find() && (at = formatStr.indexOf(colour = colourM.group())) != -1 && !(nFormatStr = formatStr.substring(0, at) + formatStr.substring(at + colour.length())).equals(formatStr)) {
            formatStr = nFormatStr;
            colourM = colorPattern.matcher(formatStr);
        }
        Matcher m = localePatternGroup.matcher(formatStr);
        while (m.find()) {
            String match = m.group();
            String symbol = match.substring(match.indexOf(36) + 1, match.indexOf(45));
            if (symbol.indexOf(36) > -1) {
                symbol = symbol.substring(0, symbol.indexOf(36)) + '\\' + symbol.substring(symbol.indexOf(36));
            }
            formatStr = m.replaceAll(symbol);
            m = localePatternGroup.matcher(formatStr);
        }
        if (formatStr == null || formatStr.trim().length() == 0) {
            return this.getDefaultFormat(cellValue);
        }
        if ("General".equalsIgnoreCase(formatStr) || "@".equals(formatStr)) {
            return this.generalNumberFormat;
        }
        if (DateUtil.isADateFormat(formatIndex, formatStr) && DateUtil.isValidExcelDate(cellValue)) {
            return this.createDateFormat(formatStr, cellValue);
        }
        if (formatStr.contains("#/") || formatStr.contains("?/")) {
            String[] chunks;
            for (String chunk1 : chunks = formatStr.split(";")) {
                String chunk = chunk1.replaceAll("\\?", defaultFractionWholePartFormat);
                Matcher matcher = fractionStripper.matcher(chunk);
                chunk = matcher.replaceAll(" ");
                Matcher fractionMatcher = fractionPattern.matcher(chunk = chunk.replaceAll(" +", " "));
                if (!fractionMatcher.find()) continue;
                String wholePart = fractionMatcher.group(1) == null ? "" : defaultFractionWholePartFormat;
                return new FractionFormat(wholePart, fractionMatcher.group(3));
            }
            return new FractionFormat(defaultFractionWholePartFormat, defaultFractionFractionPartFormat);
        }
        if (numPattern.matcher(formatStr).find()) {
            return this.createNumberFormat(formatStr, cellValue);
        }
        if (this.emulateCSV) {
            return new ConstantStringFormat(this.cleanFormatForNumber(formatStr));
        }
        return null;
    }

    private Format createDateFormat(String pFormatStr, double cellValue) {
        String formatStr = pFormatStr;
        formatStr = formatStr.replaceAll("\\\\-", "-");
        formatStr = formatStr.replaceAll("\\\\,", ",");
        formatStr = formatStr.replaceAll("\\\\\\.", ".");
        formatStr = formatStr.replaceAll("\\\\ ", " ");
        formatStr = formatStr.replaceAll("\\\\/", "/");
        formatStr = formatStr.replaceAll(";@", "");
        formatStr = formatStr.replaceAll("\"/\"", "/");
        formatStr = formatStr.replace("\"\"", "'");
        formatStr = formatStr.replaceAll("\\\\T", "'T'");
        boolean hasAmPm = false;
        Matcher amPmMatcher = amPmPattern.matcher(formatStr);
        while (amPmMatcher.find()) {
            formatStr = amPmMatcher.replaceAll("@");
            hasAmPm = true;
            amPmMatcher = amPmPattern.matcher(formatStr);
        }
        Matcher dateMatcher = daysAsText.matcher(formatStr = formatStr.replaceAll("@", "a"));
        if (dateMatcher.find()) {
            String match = dateMatcher.group(0).toUpperCase(Locale.ROOT).replaceAll("D", "E");
            formatStr = dateMatcher.replaceAll(match);
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = formatStr.toCharArray();
        boolean mIsMonth = true;
        ArrayList<Integer> ms = new ArrayList<Integer>();
        boolean isElapsed = false;
        block3: for (int j = 0; j < chars.length; ++j) {
            char c = chars[j];
            if (c == '\'') {
                sb.append(c);
                ++j;
                while (j < chars.length) {
                    c = chars[j];
                    sb.append(c);
                    if (c == '\'') continue block3;
                    ++j;
                }
                continue;
            }
            if (c == '[' && !isElapsed) {
                isElapsed = true;
                mIsMonth = false;
                sb.append(c);
                continue;
            }
            if (c == ']' && isElapsed) {
                isElapsed = false;
                sb.append(c);
                continue;
            }
            if (isElapsed) {
                if (c == 'h' || c == 'H') {
                    sb.append('H');
                    continue;
                }
                if (c == 'm' || c == 'M') {
                    sb.append('m');
                    continue;
                }
                if (c == 's' || c == 'S') {
                    sb.append('s');
                    continue;
                }
                sb.append(c);
                continue;
            }
            if (c == 'h' || c == 'H') {
                mIsMonth = false;
                if (hasAmPm) {
                    sb.append('h');
                    continue;
                }
                sb.append('H');
                continue;
            }
            if (c == 'm' || c == 'M') {
                if (mIsMonth) {
                    sb.append('M');
                    ms.add(sb.length() - 1);
                    continue;
                }
                sb.append('m');
                continue;
            }
            if (c == 's' || c == 'S') {
                sb.append('s');
                Iterator iterator = ms.iterator();
                while (iterator.hasNext()) {
                    int index = (Integer)iterator.next();
                    if (sb.charAt(index) != 'M') continue;
                    sb.replace(index, index + 1, "m");
                }
                mIsMonth = true;
                ms.clear();
                continue;
            }
            if (Character.isLetter(c)) {
                mIsMonth = true;
                ms.clear();
                if (c == 'y' || c == 'Y') {
                    sb.append('y');
                    continue;
                }
                if (c == 'd' || c == 'D') {
                    sb.append('d');
                    continue;
                }
                sb.append(c);
                continue;
            }
            if (Character.isWhitespace(c)) {
                ms.clear();
            }
            sb.append(c);
        }
        formatStr = sb.toString();
        try {
            return new ExcelStyleDateFormatter(formatStr, this.dateSymbols);
        } catch (IllegalArgumentException iae) {
            logger.log(1, "Formatting failed for format " + formatStr + ", falling back", iae);
            return this.getDefaultFormat(cellValue);
        }
    }

    private String cleanFormatForNumber(String formatStr) {
        char c;
        int i;
        StringBuilder sb = new StringBuilder(formatStr);
        if (this.emulateCSV) {
            for (i = 0; i < sb.length(); ++i) {
                c = sb.charAt(i);
                if (c != '_' && c != '*' && c != '?' || i > 0 && sb.charAt(i - 1) == '\\') continue;
                if (c == '?') {
                    sb.setCharAt(i, ' ');
                    continue;
                }
                if (i >= sb.length() - 1) continue;
                if (c == '_') {
                    sb.setCharAt(i + 1, ' ');
                } else {
                    sb.deleteCharAt(i + 1);
                }
                sb.deleteCharAt(i);
                --i;
            }
        } else {
            for (i = 0; i < sb.length(); ++i) {
                c = sb.charAt(i);
                if (c != '_' && c != '*' || i > 0 && sb.charAt(i - 1) == '\\') continue;
                if (i < sb.length() - 1) {
                    sb.deleteCharAt(i + 1);
                }
                sb.deleteCharAt(i);
                --i;
            }
        }
        for (i = 0; i < sb.length(); ++i) {
            c = sb.charAt(i);
            if (c == '\\' || c == '\"') {
                sb.deleteCharAt(i);
                --i;
                continue;
            }
            if (c != '+' || i <= 0 || sb.charAt(i - 1) != 'E') continue;
            sb.deleteCharAt(i);
            --i;
        }
        return sb.toString();
    }

    private Format createNumberFormat(String formatStr, double cellValue) {
        char grouping;
        String format = this.cleanFormatForNumber(formatStr);
        DecimalFormatSymbols symbols = this.decimalSymbols;
        Matcher agm = alternateGrouping.matcher(format);
        if (agm.find() && (grouping = agm.group(2).charAt(0)) != ',') {
            symbols = DecimalFormatSymbols.getInstance(this.locale);
            symbols.setGroupingSeparator(grouping);
            String oldPart = agm.group(1);
            String newPart = oldPart.replace(grouping, ',');
            format = format.replace(oldPart, newPart);
        }
        try {
            return new InternalDecimalFormatWithScale(format, symbols);
        } catch (IllegalArgumentException iae) {
            logger.log(1, "Formatting failed for format " + formatStr + ", falling back", iae);
            return this.getDefaultFormat(cellValue);
        }
    }

    public Format getDefaultFormat(Cell cell) {
        return this.getDefaultFormat(cell.getNumericCellValue());
    }

    private Format getDefaultFormat(double cellValue) {
        this.localeChangedObservable.checkForLocaleChange();
        if (this.defaultNumFormat != null) {
            return this.defaultNumFormat;
        }
        return this.generalNumberFormat;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String performDateFormatting(Date d, Format dateFormat) {
        Format df;
        Format format = df = dateFormat != null ? dateFormat : this.defaultDateformat;
        synchronized (format) {
            return df.format(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String getFormattedDateString(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        if (cell == null) {
            return null;
        }
        Format dateFormat = this.getFormat(cell, cfEvaluator);
        if (dateFormat == null) {
            if (this.defaultDateformat == null) {
                DateFormatSymbols sym = DateFormatSymbols.getInstance(LocaleUtil.getUserLocale());
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", sym);
                sdf.setTimeZone(LocaleUtil.getUserTimeZone());
                dateFormat = sdf;
            } else {
                dateFormat = this.defaultNumFormat;
            }
        }
        Format format = dateFormat;
        synchronized (format) {
            if (dateFormat instanceof ExcelStyleDateFormatter) {
                ((ExcelStyleDateFormatter)dateFormat).setDateToBeFormatted(cell.getNumericCellValue());
            }
            Date d = cell.getDateCellValue();
            return this.performDateFormatting(d, dateFormat);
        }
    }

    private String getFormattedNumberString(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        if (cell == null) {
            return null;
        }
        Format numberFormat = this.getFormat(cell, cfEvaluator);
        double d = cell.getNumericCellValue();
        if (numberFormat == null) {
            return String.valueOf(d);
        }
        String formatted = numberFormat.format(d);
        return formatted.replaceFirst("E(\\d)", "E+$1");
    }

    public String formatRawCellContents(double value, int formatIndex, String formatString) {
        return this.formatRawCellContents(value, formatIndex, formatString, false);
    }

    public String formatRawCellContents(double value, int formatIndex, String formatString, boolean use1904Windowing) {
        Format numberFormat;
        this.localeChangedObservable.checkForLocaleChange();
        if (DateUtil.isADateFormat(formatIndex, formatString)) {
            if (DateUtil.isValidExcelDate(value)) {
                Format dateFormat = this.getFormat(value, formatIndex, formatString, use1904Windowing);
                if (dateFormat instanceof ExcelStyleDateFormatter) {
                    ((ExcelStyleDateFormatter)dateFormat).setDateToBeFormatted(value);
                }
                Date d = DateUtil.getJavaDate(value, use1904Windowing);
                return this.performDateFormatting(d, dateFormat);
            }
            if (this.emulateCSV) {
                return invalidDateTimeString;
            }
        }
        if ((numberFormat = this.getFormat(value, formatIndex, formatString, use1904Windowing)) == null) {
            return String.valueOf(value);
        }
        String textValue = NumberToTextConverter.toText(value);
        String result = textValue.indexOf(69) > -1 ? numberFormat.format(value) : numberFormat.format(new BigDecimal(textValue));
        if (result.indexOf(69) > -1 && !result.contains("E-")) {
            result = result.replaceFirst("E", "E+");
        }
        return result;
    }

    public String formatCellValue(Cell cell) {
        return this.formatCellValue(cell, null);
    }

    public String formatCellValue(Cell cell, FormulaEvaluator evaluator) {
        return this.formatCellValue(cell, evaluator, null);
    }

    public String formatCellValue(Cell cell, FormulaEvaluator evaluator, ConditionalFormattingEvaluator cfEvaluator) {
        this.localeChangedObservable.checkForLocaleChange();
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            if (evaluator == null) {
                return cell.getCellFormula();
            }
            cellType = evaluator.evaluateFormulaCell(cell);
        }
        switch (cellType) {
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell, cfEvaluator)) {
                    return this.getFormattedDateString(cell, cfEvaluator);
                }
                return this.getFormattedNumberString(cell, cfEvaluator);
            }
            case STRING: {
                return cell.getRichStringCellValue().getString();
            }
            case BOOLEAN: {
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            }
            case BLANK: {
                return "";
            }
            case ERROR: {
                return FormulaError.forInt(cell.getErrorCellValue()).getString();
            }
        }
        throw new RuntimeException("Unexpected celltype (" + (Object)((Object)cellType) + ")");
    }

    public void setDefaultNumberFormat(Format format) {
        for (Map.Entry<String, Format> entry : this.formats.entrySet()) {
            if (entry.getValue() != this.generalNumberFormat) continue;
            entry.setValue(format);
        }
        this.defaultNumFormat = format;
    }

    public void addFormat(String excelFormatStr, Format format) {
        this.formats.put(excelFormatStr, format);
    }

    private static DecimalFormat createIntegerOnlyFormat(String fmt) {
        DecimalFormatSymbols dsf = DecimalFormatSymbols.getInstance(Locale.ROOT);
        DecimalFormat result = new DecimalFormat(fmt, dsf);
        result.setParseIntegerOnly(true);
        return result;
    }

    public static void setExcelStyleRoundingMode(DecimalFormat format) {
        DataFormatter.setExcelStyleRoundingMode(format, RoundingMode.HALF_UP);
    }

    public static void setExcelStyleRoundingMode(DecimalFormat format, RoundingMode roundingMode) {
        format.setRoundingMode(roundingMode);
    }

    public Observable getLocaleChangedObservable() {
        return this.localeChangedObservable;
    }

    @Override
    public void update(Observable observable, Object localeObj) {
        if (!(localeObj instanceof Locale)) {
            return;
        }
        Locale newLocale = (Locale)localeObj;
        if (!this.localeIsAdapting || newLocale.equals(this.locale)) {
            return;
        }
        this.locale = newLocale;
        this.dateSymbols = DateFormatSymbols.getInstance(this.locale);
        this.decimalSymbols = DecimalFormatSymbols.getInstance(this.locale);
        this.generalNumberFormat = new ExcelGeneralNumberFormat(this.locale);
        this.defaultDateformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", this.dateSymbols);
        this.defaultDateformat.setTimeZone(LocaleUtil.getUserTimeZone());
        this.formats.clear();
        Format zipFormat = ZipPlusFourFormat.instance;
        this.addFormat("00000\\-0000", zipFormat);
        this.addFormat("00000-0000", zipFormat);
        Format phoneFormat = PhoneFormat.instance;
        this.addFormat("[<=9999999]###\\-####;\\(###\\)\\ ###\\-####", phoneFormat);
        this.addFormat("[<=9999999]###-####;(###) ###-####", phoneFormat);
        this.addFormat("###\\-####;\\(###\\)\\ ###\\-####", phoneFormat);
        this.addFormat("###-####;(###) ###-####", phoneFormat);
        Format ssnFormat = SSNFormat.instance;
        this.addFormat("000\\-00\\-0000", ssnFormat);
        this.addFormat("000-00-0000", ssnFormat);
    }

    static /* synthetic */ DecimalFormat access$400(String x0) {
        return DataFormatter.createIntegerOnlyFormat(x0);
    }

    static {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 255; ++i) {
            buf.append('#');
        }
        invalidDateTimeString = buf.toString();
        logger = POILogFactory.getLogger(DataFormatter.class);
    }

    private final class CellFormatResultWrapper
    extends Format {
        private final CellFormatResult result;

        private CellFormatResultWrapper(CellFormatResult result) {
            this.result = result;
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            if (DataFormatter.this.emulateCSV) {
                return toAppendTo.append(this.result.text);
            }
            return toAppendTo.append(this.result.text.trim());
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return null;
        }
    }

    private static final class ConstantStringFormat
    extends Format {
        private static final DecimalFormat df = DataFormatter.access$400("##########");
        private final String str;

        public ConstantStringFormat(String s) {
            this.str = s;
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(this.str);
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    private static final class PhoneFormat
    extends Format {
        public static final Format instance = new PhoneFormat();
        private static final DecimalFormat df = DataFormatter.access$400("##########");

        private PhoneFormat() {
        }

        public static String format(Number num) {
            String result = df.format(num);
            StringBuilder sb = new StringBuilder();
            int len = result.length();
            if (len <= 4) {
                return result;
            }
            String seg3 = result.substring(len - 4, len);
            String seg2 = result.substring(Math.max(0, len - 7), len - 4);
            String seg1 = result.substring(Math.max(0, len - 10), Math.max(0, len - 7));
            if (seg1.trim().length() > 0) {
                sb.append('(').append(seg1).append(") ");
            }
            if (seg2.trim().length() > 0) {
                sb.append(seg2).append('-');
            }
            sb.append(seg3);
            return sb.toString();
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(PhoneFormat.format((Number)obj));
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    private static final class ZipPlusFourFormat
    extends Format {
        public static final Format instance = new ZipPlusFourFormat();
        private static final DecimalFormat df = DataFormatter.access$400("000000000");

        private ZipPlusFourFormat() {
        }

        public static String format(Number num) {
            String result = df.format(num);
            return result.substring(0, 5) + '-' + result.substring(5, 9);
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(ZipPlusFourFormat.format((Number)obj));
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    private static final class SSNFormat
    extends Format {
        public static final Format instance = new SSNFormat();
        private static final DecimalFormat df = DataFormatter.access$400("000000000");

        private SSNFormat() {
        }

        public static String format(Number num) {
            String result = df.format(num);
            return result.substring(0, 3) + '-' + result.substring(3, 5) + '-' + result.substring(5, 9);
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(SSNFormat.format((Number)obj));
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            return df.parseObject(source, pos);
        }
    }

    private static class InternalDecimalFormatWithScale
    extends Format {
        private static final Pattern endsWithCommas = Pattern.compile("(,+)$");
        private BigDecimal divider;
        private static final BigDecimal ONE_THOUSAND = new BigDecimal(1000);
        private final DecimalFormat df;

        private static String trimTrailingCommas(String s) {
            return s.replaceAll(",+$", "");
        }

        public InternalDecimalFormatWithScale(String pattern, DecimalFormatSymbols symbols) {
            this.df = new DecimalFormat(InternalDecimalFormatWithScale.trimTrailingCommas(pattern), symbols);
            DataFormatter.setExcelStyleRoundingMode(this.df);
            Matcher endsWithCommasMatcher = endsWithCommas.matcher(pattern);
            if (endsWithCommasMatcher.find()) {
                String commas = endsWithCommasMatcher.group(1);
                BigDecimal temp = BigDecimal.ONE;
                for (int i = 0; i < commas.length(); ++i) {
                    temp = temp.multiply(ONE_THOUSAND);
                }
                this.divider = temp;
            } else {
                this.divider = null;
            }
        }

        private Object scaleInput(Object obj) {
            if (this.divider != null) {
                if (obj instanceof BigDecimal) {
                    obj = ((BigDecimal)obj).divide(this.divider, RoundingMode.HALF_UP);
                } else if (obj instanceof Double) {
                    obj = (Double)obj / this.divider.doubleValue();
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            return obj;
        }

        @Override
        public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
            obj = this.scaleInput(obj);
            return this.df.format(obj, toAppendTo, pos);
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            throw new UnsupportedOperationException();
        }
    }

    private class LocaleChangeObservable
    extends Observable {
        private LocaleChangeObservable() {
        }

        void checkForLocaleChange() {
            this.checkForLocaleChange(LocaleUtil.getUserLocale());
        }

        void checkForLocaleChange(Locale newLocale) {
            if (!DataFormatter.this.localeIsAdapting) {
                return;
            }
            if (newLocale.equals(DataFormatter.this.locale)) {
                return;
            }
            super.setChanged();
            this.notifyObservers(newLocale);
        }
    }
}

