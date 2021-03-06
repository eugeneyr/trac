package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.Lexem;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = CallRestore.FUNCTION_NAME,
        mnemonics = CallRestore.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class CallRestore implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "cr";
    public static final String FUNCTION_NAME = "Call Restore";

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