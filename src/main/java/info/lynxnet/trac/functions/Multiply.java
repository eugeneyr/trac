package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.PrefixedNumber;
import info.lynxnet.trac.StackElement;

import java.math.BigInteger;

@RegisteredFunction(
        name = Multiply.FUNCTION_NAME,
        mnemonics = {Multiply.FUNCTION_MNEMONICS, "*"},
        category = FunctionCategory.ARITHMETIC)
public class Multiply implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ml";
    public static final String FUNCTION_NAME = "Multiply";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            PrefixedNumber a = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(1));
            PrefixedNumber b = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(2));
            BigInteger result = a.getNumber().multiply(b.getNumber());
            sb.append(a.getPrefix()).append(result);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}