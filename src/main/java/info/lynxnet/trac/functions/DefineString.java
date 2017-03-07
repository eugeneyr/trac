package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.Lexem;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = DefineString.FUNCTION_NAME,
        mnemonics = DefineString.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class DefineString implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ds";
    public static final String FUNCTION_NAME = "Define String";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);
            Lexem bodyArg = stackElement.getArguments().get(2);
            Form form = new Form(nameArg.getValue(), new StringBuilder(bodyArg.getValue()));
            context.getFormStorage().put(form.getName(), form);
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}