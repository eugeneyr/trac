package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.PrefixedNumber;
import info.lynxnet.trac.StackElement;

import java.math.BigInteger;

@RegisteredFunction(
        name = Divide.FUNCTION_NAME,
        mnemonics = {Divide.FUNCTION_MNEMONICS, "/"},
        category = FunctionCategory.ARITHMETIC)
public class Divide implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "dv";
    public static final String FUNCTION_NAME = "Divide";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            PrefixedNumber a = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(1));
            PrefixedNumber b = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(2));
            if (b.getNumber().equals(BigInteger.ZERO)) {
                return new ExecutionResult(true, stackElement.getArgumentValue(3));
            }
            BigInteger result = a.getNumber().divide(b.getNumber());
            sb.append(a.getPrefix()).append(result);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}