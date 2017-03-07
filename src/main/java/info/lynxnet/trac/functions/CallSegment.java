package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

@RegisteredFunction(
        name = CallSegment.FUNCTION_NAME,
        mnemonics = CallSegment.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class CallSegment implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "cs";
    public static final String FUNCTION_NAME = "Call Segment";

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
                int markerOffset = form.getClosestMarkerOffset(form.getPointer());
                result.append(form.getBody().substring(form.getPointer(), markerOffset));
                form.setPointer(markerOffset);
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}