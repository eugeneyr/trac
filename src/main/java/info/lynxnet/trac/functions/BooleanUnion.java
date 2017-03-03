package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = BooleanUnion.FUNCTION_NAME,
        mnemonics = {BooleanUnion.FUNCTION_MNEMONICS, "|"},
        category = FunctionCategory.BOOLEAN)
public class BooleanUnion implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "bu";
    public static final String FUNCTION_NAME = "Boolean Union";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.BOOLEAN;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            String a = NumericUtils.extractBoolean(stackElement.getArgumentValue(1));
            String b = NumericUtils.extractBoolean(stackElement.getArgumentValue(2));
            sb.append(BooleanStringMath.union(a, b));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}