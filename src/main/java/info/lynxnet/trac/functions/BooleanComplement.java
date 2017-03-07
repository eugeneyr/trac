package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = BooleanComplement.FUNCTION_NAME,
        mnemonics = {BooleanComplement.FUNCTION_MNEMONICS, "~"},
        category = FunctionCategory.BOOLEAN)
public class BooleanComplement implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "bc";
    public static final String FUNCTION_NAME = "Boolean Complement";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 1) {
            String a = NumericUtils.extractBoolean(stackElement.getArgumentValue(1));
            sb.append(BooleanStringMath.complement(a));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}