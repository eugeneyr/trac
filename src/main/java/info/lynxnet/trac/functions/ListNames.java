package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = ListNames.FUNCTION_NAME,
        mnemonics = ListNames.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class ListNames implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ln";
    public static final String FUNCTION_NAME = "List Names";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.FORMS;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String separator = stackElement.getArgumentValue(1);
        return new ExecutionResult(stackElement.isActive(), String.join(separator, context.getFormStorage().keySet()));
    }
}