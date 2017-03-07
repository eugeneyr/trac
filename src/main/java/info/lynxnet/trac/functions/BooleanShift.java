package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = BooleanShift.FUNCTION_NAME,
        mnemonics = {BooleanShift.FUNCTION_MNEMONICS, ">>"},
        category = FunctionCategory.BOOLEAN)
public class BooleanShift implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "bs";
    public static final String FUNCTION_NAME = "Boolean Shift";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 1) {
            String a = NumericUtils.extractBoolean(stackElement.getArgumentValue(1));
            int bits = stackElement.getArgumentIntValue(2);
            sb.append(BooleanStringMath.shift(a, bits));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}