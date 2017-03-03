package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

@RegisteredFunction(
        name = PrintString.FUNCTION_NAME,
        mnemonics = PrintString.FUNCTION_MNEMONICS,
        category = FunctionCategory.IO)
public class PrintString implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ps";
    public static final String FUNCTION_NAME = "Print String";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.IO;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        for (Lexem lexem : stackElement.getArguments().subList(1, stackElement.getArguments().size())) {
            context.getOutput().print(lexem.getValue());
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}