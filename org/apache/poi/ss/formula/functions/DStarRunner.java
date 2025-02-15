/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.function.Supplier;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.DGet;
import org.apache.poi.ss.formula.functions.DMax;
import org.apache.poi.ss.formula.functions.DMin;
import org.apache.poi.ss.formula.functions.DSum;
import org.apache.poi.ss.formula.functions.Function3Arg;
import org.apache.poi.ss.formula.functions.IDStarAlgorithm;
import org.apache.poi.ss.util.NumberComparer;

public final class DStarRunner
implements Function3Arg {
    private final DStarAlgorithmEnum algoType;

    public DStarRunner(DStarAlgorithmEnum algorithm) {
        this.algoType = algorithm;
    }

    @Override
    public final ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length == 3) {
            return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval database, ValueEval filterColumn, ValueEval conditionDatabase) {
        int fc;
        if (!(database instanceof AreaEval) || !(conditionDatabase instanceof AreaEval)) {
            return ErrorEval.VALUE_INVALID;
        }
        AreaEval db = (AreaEval)database;
        AreaEval cdb = (AreaEval)conditionDatabase;
        try {
            filterColumn = OperandResolver.getSingleValue(filterColumn, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        try {
            fc = DStarRunner.getColumnForName(filterColumn, db);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        if (fc == -1) {
            return ErrorEval.VALUE_INVALID;
        }
        IDStarAlgorithm algorithm = this.algoType.newInstance();
        int height = db.getHeight();
        for (int row = 1; row < height; ++row) {
            ValueEval currentValueEval;
            boolean shouldContinue;
            boolean matches;
            try {
                matches = DStarRunner.fullfillsConditions(db, row, cdb);
            } catch (EvaluationException e) {
                return ErrorEval.VALUE_INVALID;
            }
            if (matches && !(shouldContinue = algorithm.processMatch(currentValueEval = DStarRunner.resolveReference(db, row, fc)))) break;
        }
        return algorithm.getResult();
    }

    private static int getColumnForName(ValueEval nameValueEval, AreaEval db) throws EvaluationException {
        if (nameValueEval instanceof NumericValueEval) {
            int columnNo = OperandResolver.coerceValueToInt(nameValueEval) - 1;
            if (columnNo < 0 || columnNo >= db.getWidth()) {
                return -1;
            }
            return columnNo;
        }
        String name = OperandResolver.coerceValueToString(nameValueEval);
        return DStarRunner.getColumnForString(db, name);
    }

    private static int getColumnForString(AreaEval db, String name) {
        int resultColumn = -1;
        int width = db.getWidth();
        for (int column = 0; column < width; ++column) {
            String columnName;
            ValueEval columnNameValueEval = DStarRunner.resolveReference(db, 0, column);
            if (columnNameValueEval instanceof BlankEval || columnNameValueEval instanceof ErrorEval || !name.equalsIgnoreCase(columnName = OperandResolver.coerceValueToString(columnNameValueEval))) continue;
            resultColumn = column;
            break;
        }
        return resultColumn;
    }

    private static boolean fullfillsConditions(AreaEval db, int row, AreaEval cdb) throws EvaluationException {
        int height = cdb.getHeight();
        for (int conditionRow = 1; conditionRow < height; ++conditionRow) {
            boolean matches = true;
            int width = cdb.getWidth();
            for (int column = 0; column < width; ++column) {
                boolean columnCondition = true;
                ValueEval condition = DStarRunner.resolveReference(cdb, conditionRow, column);
                if (condition instanceof BlankEval) continue;
                ValueEval targetHeader = DStarRunner.resolveReference(cdb, 0, column);
                if (!(targetHeader instanceof StringValueEval)) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                if (DStarRunner.getColumnForName(targetHeader, db) == -1) {
                    columnCondition = false;
                }
                if (columnCondition) {
                    ValueEval value = DStarRunner.resolveReference(db, row, DStarRunner.getColumnForName(targetHeader, db));
                    if (DStarRunner.testNormalCondition(value, condition)) continue;
                    matches = false;
                    break;
                }
                if (OperandResolver.coerceValueToString(condition).isEmpty()) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                throw new NotImplementedException("D* function with formula conditions");
            }
            if (!matches) continue;
            return true;
        }
        return false;
    }

    private static boolean testNormalCondition(ValueEval value, ValueEval condition) throws EvaluationException {
        if (condition instanceof StringEval) {
            String conditionString = ((StringEval)condition).getStringValue();
            if (conditionString.startsWith("<")) {
                String number = conditionString.substring(1);
                if (number.startsWith("=")) {
                    number = number.substring(1);
                    return DStarRunner.testNumericCondition(value, operator.smallerEqualThan, number);
                }
                return DStarRunner.testNumericCondition(value, operator.smallerThan, number);
            }
            if (conditionString.startsWith(">")) {
                String number = conditionString.substring(1);
                if (number.startsWith("=")) {
                    number = number.substring(1);
                    return DStarRunner.testNumericCondition(value, operator.largerEqualThan, number);
                }
                return DStarRunner.testNumericCondition(value, operator.largerThan, number);
            }
            if (conditionString.startsWith("=")) {
                boolean itsANumber;
                String stringOrNumber = conditionString.substring(1);
                if (stringOrNumber.isEmpty()) {
                    return value instanceof BlankEval;
                }
                try {
                    Integer.parseInt(stringOrNumber);
                    itsANumber = true;
                } catch (NumberFormatException e) {
                    try {
                        Double.parseDouble(stringOrNumber);
                        itsANumber = true;
                    } catch (NumberFormatException e2) {
                        itsANumber = false;
                    }
                }
                if (itsANumber) {
                    return DStarRunner.testNumericCondition(value, operator.equal, stringOrNumber);
                }
                String valueString = value instanceof BlankEval ? "" : OperandResolver.coerceValueToString(value);
                return stringOrNumber.equals(valueString);
            }
            if (conditionString.isEmpty()) {
                return value instanceof StringEval;
            }
            String valueString = value instanceof BlankEval ? "" : OperandResolver.coerceValueToString(value);
            return valueString.startsWith(conditionString);
        }
        if (condition instanceof NumericValueEval) {
            double conditionNumber = ((NumericValueEval)condition).getNumberValue();
            Double valueNumber = DStarRunner.getNumberFromValueEval(value);
            return valueNumber != null && conditionNumber == valueNumber;
        }
        if (condition instanceof ErrorEval) {
            if (value instanceof ErrorEval) {
                return ((ErrorEval)condition).getErrorCode() == ((ErrorEval)value).getErrorCode();
            }
            return false;
        }
        return false;
    }

    private static boolean testNumericCondition(ValueEval valueEval, operator op, String condition) throws EvaluationException {
        double conditionValue;
        if (!(valueEval instanceof NumericValueEval)) {
            return false;
        }
        double value = ((NumericValueEval)valueEval).getNumberValue();
        try {
            conditionValue = Integer.parseInt(condition);
        } catch (NumberFormatException e) {
            try {
                conditionValue = Double.parseDouble(condition);
            } catch (NumberFormatException e2) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
        int result = NumberComparer.compare(value, conditionValue);
        switch (op) {
            case largerThan: {
                return result > 0;
            }
            case largerEqualThan: {
                return result >= 0;
            }
            case smallerThan: {
                return result < 0;
            }
            case smallerEqualThan: {
                return result <= 0;
            }
            case equal: {
                return result == 0;
            }
        }
        return false;
    }

    private static Double getNumberFromValueEval(ValueEval value) {
        if (value instanceof NumericValueEval) {
            return ((NumericValueEval)value).getNumberValue();
        }
        if (value instanceof StringValueEval) {
            String stringValue = ((StringValueEval)value).getStringValue();
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        return null;
    }

    private static ValueEval resolveReference(AreaEval db, int dbRow, int dbCol) {
        try {
            return OperandResolver.getSingleValue(db.getValue(dbRow, dbCol), db.getFirstRow() + dbRow, db.getFirstColumn() + dbCol);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static enum operator {
        largerThan,
        largerEqualThan,
        smallerThan,
        smallerEqualThan,
        equal;

    }

    public static enum DStarAlgorithmEnum {
        DGET(DGet::new),
        DMIN(DMin::new),
        DMAX(DMax::new),
        DSUM(DSum::new);

        private final Supplier<IDStarAlgorithm> implSupplier;

        private DStarAlgorithmEnum(Supplier<IDStarAlgorithm> implSupplier) {
            this.implSupplier = implSupplier;
        }

        public IDStarAlgorithm newInstance() {
            return this.implSupplier.get();
        }
    }
}

