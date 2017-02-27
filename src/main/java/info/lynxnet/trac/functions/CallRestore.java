package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.Lexem;
import info.lynxnet.trac.StackElement;

public class CallRestore implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "cr";
    public static final String FUNCTION_NAME = "Call Restore";

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
        if (stackElement.getArguments().size() > 1) {
            Lexem nameArg = stackElement.getArguments().get(1);
            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                form.setPointer(0);
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}