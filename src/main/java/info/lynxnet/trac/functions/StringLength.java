package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = StringLength.FUNCTION_NAME,
        mnemonics = StringLength.FUNCTION_MNEMONICS,
        category = FunctionCategory.STRING)
public class StringLength implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "sl";
    public static final String FUNCTION_NAME = "String Length";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String string = stackElement.getArgumentValue(1);
        return new ExecutionResult(stackElement.isActive(), Integer.toString(string.length()));
    }
}