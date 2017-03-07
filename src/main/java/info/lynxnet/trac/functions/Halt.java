package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = Halt.FUNCTION_NAME,
        mnemonics = {Halt.FUNCTION_MNEMONICS, "exit"},
        category = FunctionCategory.IO)
public class Halt implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "hl";
    public static final String FUNCTION_NAME = "Halt";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.setExitCode(stackElement.getArgumentIntValue(1));
        context.setExit(true);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}