package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.NumericUtils;
import info.lynxnet.trac.PrefixedNumber;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = ChangeRadix.FUNCTION_NAME,
        mnemonics = ChangeRadix.FUNCTION_MNEMONICS,
        category = FunctionCategory.STRING)
public class ChangeRadix implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "cx";
    public static final String FUNCTION_NAME = "Change Radix";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.STRING;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 3) {
            int radix1 = NumericUtils.parseRadix(stackElement.getArgumentValue(1));
            int radix2 = NumericUtils.parseRadix(stackElement.getArgumentValue(2));
            if (radix1 > 0 && radix2 > 0) {
                PrefixedNumber number = NumericUtils.getValueAsNumberInRadix(stackElement.getArgumentValue(3), stackElement.getArgumentValue(1));
                sb.append(number.getPrefix());
                if (number.getNumber() != null) {
                    sb.append(number.getNumber().toString(radix2));
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}