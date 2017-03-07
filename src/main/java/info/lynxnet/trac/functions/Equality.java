package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = Equality.FUNCTION_NAME,
        mnemonics = {Equality.FUNCTION_MNEMONICS, "="},
        category = FunctionCategory.BOOLEAN)
public class Equality implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "eq";
    public static final String FUNCTION_NAME = "Equiality";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 1) {
            String a = stackElement.getArgumentValue(1);
            String b = stackElement.getArgumentValue(2);
            String t = stackElement.getArgumentValue(3);
            String f = stackElement.getArgumentValue(4);
            sb.append(a.equals(b)? t : f);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}