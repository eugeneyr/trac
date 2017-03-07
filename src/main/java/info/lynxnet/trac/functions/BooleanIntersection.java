package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = BooleanIntersection.FUNCTION_NAME,
        mnemonics = {BooleanIntersection.FUNCTION_MNEMONICS, "&"},
        category = FunctionCategory.BOOLEAN)
public class BooleanIntersection implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "bi";
    public static final String FUNCTION_NAME = "Boolean Intersection";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            String a = NumericUtils.extractBoolean(stackElement.getArgumentValue(1));
            String b = NumericUtils.extractBoolean(stackElement.getArgumentValue(2));
            sb.append(BooleanStringMath.intersection(a, b));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}