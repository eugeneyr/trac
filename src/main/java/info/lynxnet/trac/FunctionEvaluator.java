package info.lynxnet.trac;

import info.lynxnet.trac.functions.RegisteredFunction;
import info.lynxnet.trac.functions.TracFunction;
import info.lynxnet.trac.functions.ExecutionResult;
import org.reflections.*;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionEvaluator {
    public static Map<String, TracFunction> BUILTINS = new HashMap<>();

    static {
        registerFunctions();
    }

    private static void registerFunctions() {
        Reflections refs = new Reflections("info.lynxnet.trac.functions");
        Set<Class<? extends TracFunction>> funcClasses = refs.getSubTypesOf(TracFunction.class);
        for (Class<? extends TracFunction> clz : funcClasses) {
            try {
                Annotation[] annots =  clz.getAnnotationsByType(RegisteredFunction.class);
                if (annots.length > 0) {
                    RegisteredFunction annot = (RegisteredFunction) annots[0];
                    TracFunction func = clz.newInstance();
                    for (String mnemo : annot.mnemonics()) {
                        if (BUILTINS.containsKey(mnemo)) {
                            System.err.println(
                                    String.format("Warning: mnemonics collision detected for #(%s)", mnemo));
                        }
                        BUILTINS.put(mnemo, func);
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace(System.err);
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private FunctionEvaluator() {
    }

    private static FunctionEvaluator instance;

    public static synchronized FunctionEvaluator getInstance() {
        if (instance == null) {
            instance = new FunctionEvaluator();
        }
        return instance;
    }

    public ExecutionResult evaluate(StackElement stackElement, Context context) {
        TracFunction function = BUILTINS.get(stackElement.getArguments().get(0).getValue());
        if (function != null) {
            return function.execute(stackElement, context);
        } else {
            System.err.println(String.format("Function not found: %s", stackElement.getArguments().get(0).getValue()));
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}
