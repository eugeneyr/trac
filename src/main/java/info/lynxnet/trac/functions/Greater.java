package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

@RegisteredFunction(
        name = Greater.FUNCTION_NAME,
        mnemonics = {Greater.FUNCTION_MNEMONICS, ">"},
        category = FunctionCategory.BOOLEAN)
public class Greater implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "gr";
    public static final String FUNCTION_NAME = "Greater";

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
        if (stackElement.getArguments().size() > 3) {
            PrefixedNumber a = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(1));
            PrefixedNumber b = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(2));
            String t = stackElement.getArgumentValue(3);
            String f = stackElement.getArgumentValue(4);
            sb.append(a.getNumber().compareTo(b.getNumber()) > 0? t : f);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}