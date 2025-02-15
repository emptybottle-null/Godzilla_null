/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;

public class XSSFIgnoredErrorHelper {
    public static boolean isSet(IgnoredErrorType errorType, CTIgnoredError error) {
        switch (errorType) {
            case CALCULATED_COLUMN: {
                return error.isSetCalculatedColumn();
            }
            case EMPTY_CELL_REFERENCE: {
                return error.isSetEmptyCellReference();
            }
            case EVALUATION_ERROR: {
                return error.isSetEvalError();
            }
            case FORMULA: {
                return error.isSetFormula();
            }
            case FORMULA_RANGE: {
                return error.isSetFormulaRange();
            }
            case LIST_DATA_VALIDATION: {
                return error.isSetListDataValidation();
            }
            case NUMBER_STORED_AS_TEXT: {
                return error.isSetNumberStoredAsText();
            }
            case TWO_DIGIT_TEXT_YEAR: {
                return error.isSetTwoDigitTextYear();
            }
            case UNLOCKED_FORMULA: {
                return error.isSetUnlockedFormula();
            }
        }
        throw new IllegalStateException();
    }

    public static void set(IgnoredErrorType errorType, CTIgnoredError error) {
        switch (errorType) {
            case CALCULATED_COLUMN: {
                error.setCalculatedColumn(true);
                break;
            }
            case EMPTY_CELL_REFERENCE: {
                error.setEmptyCellReference(true);
                break;
            }
            case EVALUATION_ERROR: {
                error.setEvalError(true);
                break;
            }
            case FORMULA: {
                error.setFormula(true);
                break;
            }
            case FORMULA_RANGE: {
                error.setFormulaRange(true);
                break;
            }
            case LIST_DATA_VALIDATION: {
                error.setListDataValidation(true);
                break;
            }
            case NUMBER_STORED_AS_TEXT: {
                error.setNumberStoredAsText(true);
                break;
            }
            case TWO_DIGIT_TEXT_YEAR: {
                error.setTwoDigitTextYear(true);
                break;
            }
            case UNLOCKED_FORMULA: {
                error.setUnlockedFormula(true);
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    public static void addIgnoredErrors(CTIgnoredError err, String ref, IgnoredErrorType ... ignoredErrorTypes) {
        err.setSqref(Collections.singletonList(ref));
        for (IgnoredErrorType errType : ignoredErrorTypes) {
            XSSFIgnoredErrorHelper.set(errType, err);
        }
    }

    public static Set<IgnoredErrorType> getErrorTypes(CTIgnoredError err) {
        LinkedHashSet<IgnoredErrorType> result = new LinkedHashSet<IgnoredErrorType>();
        for (IgnoredErrorType errType : IgnoredErrorType.values()) {
            if (!XSSFIgnoredErrorHelper.isSet(errType, err)) continue;
            result.add(errType);
        }
        return result;
    }
}

