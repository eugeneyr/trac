package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.Lexem;
import info.lynxnet.trac.StackElement;

public class CallNCharacters implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "cn";
    public static final String FUNCTION_NAME = "Call N Characters";

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
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 3) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                int length = stackElement.getArgumentIntValue(2);
                int newOffset = form.getPointer() + length;
                if (newOffset > form.getBody().length() || newOffset < 0) {
                    return new ExecutionResult(true, stackElement.getArgumentValue(3));
                }

                int start = Math.min(form.getPointer(), newOffset);
                int end = Math.max(form.getPointer(), newOffset);

                result.append(form.getBody().substring(start, end));
                form.setPointer(form.getPointer() + length);
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}