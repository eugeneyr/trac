package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = DeleteAll.FUNCTION_NAME,
        mnemonics = DeleteAll.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class DeleteAll implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "da";
    public static final String FUNCTION_NAME = "Delete All";

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
        context.getFormStorage().clear();
        return new ExecutionResult(stackElement.isActive(), "");
    }
}