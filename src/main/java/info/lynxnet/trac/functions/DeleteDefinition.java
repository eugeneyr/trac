package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = DeleteDefinition.FUNCTION_NAME,
        mnemonics = DeleteDefinition.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class DeleteDefinition implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "dd";
    public static final String FUNCTION_NAME = "Delete Definition";

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
        stackElement.getArguments().subList(1, stackElement.getArguments().size()).stream().forEach(
                x -> {
                    if (context.getFormStorage().containsKey(x.getValue())) {
                        context.getFormStorage().remove(x.getValue());
                    }
                }
        );
        return new ExecutionResult(stackElement.isActive(), "");
    }
}