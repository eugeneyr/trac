package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = TraceOff.FUNCTION_NAME,
        mnemonics = TraceOff.FUNCTION_MNEMONICS,
        category = FunctionCategory.DEBUG)
public class TraceOff implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "tf";
    public static final String FUNCTION_NAME = "Trace Off";


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
        context.setTrace(false);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}