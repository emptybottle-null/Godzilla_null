/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.function.FunctionDataBuilder;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.util.IOUtils;

final class FunctionMetadataReader {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final String METADATA_FILE_NAME = "functionMetadata.txt";
    private static final String METADATA_FILE_NAME_CETAB = "functionMetadataCetab.txt";
    private static final String ELLIPSIS = "...";
    private static final Pattern TAB_DELIM_PATTERN = Pattern.compile("\t");
    private static final Pattern SPACE_DELIM_PATTERN = Pattern.compile(" ");
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final String[] DIGIT_ENDING_FUNCTION_NAMES = new String[]{"LOG10", "ATAN2", "DAYS360", "SUMXMY2", "SUMX2MY2", "SUMX2PY2", "A1.R1C1"};
    private static final Set<String> DIGIT_ENDING_FUNCTION_NAMES_SET = new HashSet<String>(Arrays.asList(DIGIT_ENDING_FUNCTION_NAMES));

    FunctionMetadataReader() {
    }

    public static FunctionMetadataRegistry createRegistry() {
        FunctionDataBuilder fdb = new FunctionDataBuilder(800);
        FunctionMetadataReader.readResourceFile(fdb, METADATA_FILE_NAME);
        return fdb.build();
    }

    public static FunctionMetadataRegistry createRegistryCetab() {
        FunctionDataBuilder fdb = new FunctionDataBuilder(800);
        FunctionMetadataReader.readResourceFile(fdb, METADATA_FILE_NAME_CETAB);
        return fdb.build();
    }

    private static void readResourceFile(FunctionDataBuilder fdb, String resourceFile) {
        try (InputStream is = FunctionMetadataReader.class.getResourceAsStream(resourceFile);){
            if (is == null) {
                throw new RuntimeException("resource '" + resourceFile + "' not found");
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));){
                String line;
                while ((line = br.readLine()) != null) {
                    String trimLine;
                    if (line.length() < 1 || line.charAt(0) == '#' || (trimLine = line.trim()).length() < 1) continue;
                    FunctionMetadataReader.processLine(fdb, line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processLine(FunctionDataBuilder fdb, String line) {
        Object[] parts = TAB_DELIM_PATTERN.split(line, -2);
        if (parts.length != 8) {
            throw new RuntimeException("Bad line format '" + line + "' - expected 8 data fields delimited by tab, but had " + parts.length + ": " + Arrays.toString(parts));
        }
        int functionIndex = FunctionMetadataReader.parseInt(parts[0]);
        String functionName = parts[1];
        int minParams = FunctionMetadataReader.parseInt(parts[2]);
        int maxParams = FunctionMetadataReader.parseInt(parts[3]);
        byte returnClassCode = FunctionMetadataReader.parseReturnTypeCode((String)parts[4]);
        byte[] parameterClassCodes = FunctionMetadataReader.parseOperandTypeCodes((String)parts[5]);
        boolean hasNote = ((String)parts[7]).length() > 0;
        FunctionMetadataReader.validateFunctionName(functionName);
        fdb.add(functionIndex, functionName, minParams, maxParams, returnClassCode, parameterClassCodes, hasNote);
    }

    private static byte parseReturnTypeCode(String code) {
        if (code.length() == 0) {
            return 0;
        }
        return FunctionMetadataReader.parseOperandTypeCode(code);
    }

    private static byte[] parseOperandTypeCodes(String codes) {
        int nItems;
        if (codes.length() < 1) {
            return EMPTY_BYTE_ARRAY;
        }
        if (FunctionMetadataReader.isDash(codes)) {
            return EMPTY_BYTE_ARRAY;
        }
        String[] array = SPACE_DELIM_PATTERN.split(codes);
        if (ELLIPSIS.equals(array[(nItems = array.length) - 1])) {
            --nItems;
        }
        byte[] result = IOUtils.safelyAllocate(nItems, 100000);
        for (int i = 0; i < nItems; ++i) {
            result[i] = FunctionMetadataReader.parseOperandTypeCode(array[i]);
        }
        return result;
    }

    private static boolean isDash(String codes) {
        return codes.length() == 1 && codes.charAt(0) == '-';
    }

    private static byte parseOperandTypeCode(String code) {
        if (code.length() != 1) {
            throw new RuntimeException("Bad operand type code format '" + code + "' expected single char");
        }
        switch (code.charAt(0)) {
            case 'V': {
                return 32;
            }
            case 'R': {
                return 0;
            }
            case 'A': {
                return 64;
            }
        }
        throw new IllegalArgumentException("Unexpected operand type code '" + code + "' (" + code.charAt(0) + ")");
    }

    private static void validateFunctionName(String functionName) {
        int len = functionName.length();
        int ix = len - 1;
        if (!Character.isDigit(functionName.charAt(ix))) {
            return;
        }
        while (ix >= 0 && Character.isDigit(functionName.charAt(ix))) {
            --ix;
        }
        if (DIGIT_ENDING_FUNCTION_NAMES_SET.contains(functionName)) {
            return;
        }
        throw new RuntimeException("Invalid function name '" + functionName + "' (is footnote number incorrectly appended)");
    }

    private static int parseInt(String valStr) {
        try {
            return Integer.parseInt(valStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Value '" + valStr + "' could not be parsed as an integer");
        }
    }
}

