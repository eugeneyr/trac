package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.StackElement;

public class BooleanRotate implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "br";
    public static final String FUNCTION_NAME = "Boolean Rotate";

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
        if (stackElement.getArguments().size() > 1) {
            String a = NumericUtils.extractBoolean(stackElement.getArgumentValue(1));
            int bits = stackElement.getArgumentIntValue(2);
            sb.append(BooleanStringMath.rotate(a, bits));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}