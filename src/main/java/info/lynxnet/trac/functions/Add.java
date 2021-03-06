package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

import java.math.BigInteger;

@RegisteredFunction(
        name = Add.FUNCTION_NAME, mnemonics = {Add.FUNCTION_MNEMONICS, "+"}, category = FunctionCategory.ARITHMETIC)
public class Add implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ad";
    public static final String FUNCTION_NAME = "Add";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            PrefixedNumber a = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(1));
            PrefixedNumber b = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(2));
            BigInteger sum = a.getNumber().add(b.getNumber());
            sb.append(a.getPrefix()).append(sum);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}