/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationCache;
import org.apache.poi.ss.formula.IEvaluationListener;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.util.Internal;

@Internal
public final class CollaboratingWorkbooksEnvironment {
    public static final CollaboratingWorkbooksEnvironment EMPTY = new CollaboratingWorkbooksEnvironment();
    private final Map<String, WorkbookEvaluator> _evaluatorsByName;
    private final WorkbookEvaluator[] _evaluators;
    private boolean _unhooked;

    private CollaboratingWorkbooksEnvironment() {
        this._evaluatorsByName = Collections.emptyMap();
        this._evaluators = new WorkbookEvaluator[0];
    }

    public static void setup(String[] workbookNames, WorkbookEvaluator[] evaluators) {
        int nItems = workbookNames.length;
        if (evaluators.length != nItems) {
            throw new IllegalArgumentException("Number of workbook names is " + nItems + " but number of evaluators is " + evaluators.length);
        }
        if (nItems < 1) {
            throw new IllegalArgumentException("Must provide at least one collaborating worbook");
        }
        new CollaboratingWorkbooksEnvironment(workbookNames, evaluators, nItems);
    }

    public static void setup(Map<String, WorkbookEvaluator> evaluatorsByName) {
        if (evaluatorsByName.size() < 1) {
            throw new IllegalArgumentException("Must provide at least one collaborating worbook");
        }
        WorkbookEvaluator[] evaluators = evaluatorsByName.values().toArray(new WorkbookEvaluator[0]);
        new CollaboratingWorkbooksEnvironment(evaluatorsByName, evaluators);
    }

    public static void setupFormulaEvaluator(Map<String, FormulaEvaluator> evaluators) {
        HashMap<String, WorkbookEvaluator> evaluatorsByName = new HashMap<String, WorkbookEvaluator>(evaluators.size());
        for (Map.Entry<String, FormulaEvaluator> swb : evaluators.entrySet()) {
            String wbName = swb.getKey();
            FormulaEvaluator eval = swb.getValue();
            if (eval instanceof WorkbookEvaluatorProvider) {
                evaluatorsByName.put(wbName, ((WorkbookEvaluatorProvider)((Object)eval))._getWorkbookEvaluator());
                continue;
            }
            throw new IllegalArgumentException("Formula Evaluator " + eval + " provides no WorkbookEvaluator access");
        }
        CollaboratingWorkbooksEnvironment.setup(evaluatorsByName);
    }

    private CollaboratingWorkbooksEnvironment(String[] workbookNames, WorkbookEvaluator[] evaluators, int nItems) {
        this(CollaboratingWorkbooksEnvironment.toUniqueMap(workbookNames, evaluators, nItems), evaluators);
    }

    private static Map<String, WorkbookEvaluator> toUniqueMap(String[] workbookNames, WorkbookEvaluator[] evaluators, int nItems) {
        HashMap<String, WorkbookEvaluator> evaluatorsByName = new HashMap<String, WorkbookEvaluator>(nItems * 3 / 2);
        for (int i = 0; i < nItems; ++i) {
            String wbName = workbookNames[i];
            WorkbookEvaluator wbEval = evaluators[i];
            if (evaluatorsByName.containsKey(wbName)) {
                throw new IllegalArgumentException("Duplicate workbook name '" + wbName + "'");
            }
            evaluatorsByName.put(wbName, wbEval);
        }
        return evaluatorsByName;
    }

    private CollaboratingWorkbooksEnvironment(Map<String, WorkbookEvaluator> evaluatorsByName, WorkbookEvaluator[] evaluators) {
        IdentityHashMap<WorkbookEvaluator, String> uniqueEvals = new IdentityHashMap<WorkbookEvaluator, String>(evaluators.length);
        for (Map.Entry<String, WorkbookEvaluator> me : evaluatorsByName.entrySet()) {
            String uniEval = uniqueEvals.put(me.getValue(), me.getKey());
            if (uniEval == null) continue;
            String msg = "Attempted to register same workbook under names '" + uniEval + "' and '" + me.getKey() + "'";
            throw new IllegalArgumentException(msg);
        }
        this.unhookOldEnvironments(evaluators);
        CollaboratingWorkbooksEnvironment.hookNewEnvironment(evaluators, this);
        this._unhooked = false;
        this._evaluators = (WorkbookEvaluator[])evaluators.clone();
        this._evaluatorsByName = evaluatorsByName;
    }

    private static void hookNewEnvironment(WorkbookEvaluator[] evaluators, CollaboratingWorkbooksEnvironment env) {
        int nItems = evaluators.length;
        IEvaluationListener evalListener = evaluators[0].getEvaluationListener();
        for (WorkbookEvaluator evaluator : evaluators) {
            if (evalListener == evaluator.getEvaluationListener()) continue;
            throw new RuntimeException("Workbook evaluators must all have the same evaluation listener");
        }
        EvaluationCache cache = new EvaluationCache(evalListener);
        for (int i = 0; i < nItems; ++i) {
            evaluators[i].attachToEnvironment(env, cache, i);
        }
    }

    private void unhookOldEnvironments(WorkbookEvaluator[] evaluators) {
        HashSet<CollaboratingWorkbooksEnvironment> oldEnvs = new HashSet<CollaboratingWorkbooksEnvironment>();
        for (WorkbookEvaluator evaluator : evaluators) {
            oldEnvs.add(evaluator.getEnvironment());
        }
        CollaboratingWorkbooksEnvironment[] oldCWEs = new CollaboratingWorkbooksEnvironment[oldEnvs.size()];
        oldEnvs.toArray(oldCWEs);
        for (CollaboratingWorkbooksEnvironment oldCWE : oldCWEs) {
            oldCWE.unhook();
        }
    }

    private void unhook() {
        if (this._evaluators.length < 1) {
            return;
        }
        for (WorkbookEvaluator evaluator : this._evaluators) {
            evaluator.detachFromEnvironment();
        }
        this._unhooked = true;
    }

    public WorkbookEvaluator getWorkbookEvaluator(String workbookName) throws WorkbookNotFoundException {
        if (this._unhooked) {
            throw new IllegalStateException("This environment has been unhooked");
        }
        WorkbookEvaluator result = this._evaluatorsByName.get(workbookName);
        if (result == null) {
            StringBuilder sb = new StringBuilder(256);
            sb.append("Could not resolve external workbook name '").append(workbookName).append("'.");
            if (this._evaluators.length < 1) {
                sb.append(" Workbook environment has not been set up.");
            } else {
                sb.append(" The following workbook names are valid: (");
                Iterator<String> i = this._evaluatorsByName.keySet().iterator();
                int count = 0;
                while (i.hasNext()) {
                    if (count++ > 0) {
                        sb.append(", ");
                    }
                    sb.append("'").append(i.next()).append("'");
                }
                sb.append(")");
            }
            throw new WorkbookNotFoundException(sb.toString());
        }
        return result;
    }

    public static final class WorkbookNotFoundException
    extends Exception {
        private static final long serialVersionUID = 8787784539811167941L;

        WorkbookNotFoundException(String msg) {
            super(msg);
        }
    }
}

