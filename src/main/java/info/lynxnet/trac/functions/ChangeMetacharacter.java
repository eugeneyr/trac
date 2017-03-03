package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

@RegisteredFunction(
        name = ChangeMetacharacter.FUNCTION_NAME,
        mnemonics = ChangeMetacharacter.FUNCTION_MNEMONICS,
        category = FunctionCategory.IO)
public class ChangeMetacharacter implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "cm";
    public static final String FUNCTION_NAME = "Change Metacharacter";

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
        if (stackElement.getArguments().size() > 1) {
            Lexem arg = stackElement.getArguments().get(1);
            if (arg.getValue() != null && arg.getValue().length() > 0) {
                context.setMetacharacter(arg.getValue().charAt(0));
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}