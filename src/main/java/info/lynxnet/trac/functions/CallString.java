package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

import java.util.List;

@RegisteredFunction(
        name = CallString.FUNCTION_NAME,
        mnemonics = CallString.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class CallString implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "cl";
    public static final String FUNCTION_NAME = "Call String";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                List<FormElement> segmented = form.segment();

                for (FormElement element: segmented) {
                    if (element instanceof FormSegment) {
                        result.append(((FormSegment) element).getValue());
                    } else if (element instanceof FormMarker) {
                        result.append(stackElement.getArgumentValue(((FormMarker) element).getOrdinal() + 1));
                    }
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}