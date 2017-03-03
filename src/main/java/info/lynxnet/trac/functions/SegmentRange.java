package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

import java.util.OptionalInt;

@RegisteredFunction(
        name = SegmentRange.FUNCTION_NAME,
        mnemonics = SegmentRange.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class SegmentRange implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "sr";
    public static final String FUNCTION_NAME = "Segment Range";

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
        int range = 0;
        if (stackElement.getArguments().size() > 1) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                OptionalInt max = form.getMarkers().stream().mapToInt(FormMarker::getOrdinal).max();
                if (max.isPresent()) {
                    range = max.getAsInt();
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), Integer.toString(range));
    }
}