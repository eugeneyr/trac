package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.Lexem;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = CallCharacter.FUNCTION_NAME,
        mnemonics = CallCharacter.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class CallCharacter implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "cc";
    public static final String FUNCTION_NAME = "Call Character";


    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                if (form.getPointer() == form.getBody().length()) {
                    return new ExecutionResult(true, stackElement.getArgumentValue(2));
                }
                result.append(form.getBody().charAt(form.getPointer()));
                form.setPointer(form.getPointer() + 1);
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}