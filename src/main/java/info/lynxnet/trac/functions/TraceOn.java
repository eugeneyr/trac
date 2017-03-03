package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = TraceOn.FUNCTION_NAME,
        mnemonics = TraceOn.FUNCTION_MNEMONICS,
        category = FunctionCategory.DEBUG)
public class TraceOn implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "tn";
    public static final String FUNCTION_NAME = "Trace On";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.DEBUG;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.setTrace(true);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}