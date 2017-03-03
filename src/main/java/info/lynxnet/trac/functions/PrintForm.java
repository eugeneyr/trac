package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

import java.util.List;

@RegisteredFunction(
        name = PrintForm.FUNCTION_NAME,
        mnemonics = PrintForm.FUNCTION_MNEMONICS,
        category = FunctionCategory.DEBUG)
public class PrintForm implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "pf";
    public static final String FUNCTION_NAME = "Print Form";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.DEBUG;
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
                List<FormElement> segmented = form.segment();
                if (form.getPointer() == 0) {
                    context.getOutput().print("<^>");
                }
                for (FormElement element: segmented) {
                    if (element instanceof FormSegment) {
                        if (form.getPointer() > element.getOffset()
                                && form.getPointer() <= element.getOffset() + ((FormSegment) element).getValue().length()) {
                            int relPos = form.getPointer() - element.getOffset();
                            context.getOutput().print(((FormSegment)element).getValue().substring(0, relPos));
                            context.getOutput().print("<^>");
                            context.getOutput().print(((FormSegment)element).getValue().substring(relPos));
                        } else {
                            context.getOutput().print(((FormSegment) element).getValue());
                        }
                    } else if (element instanceof FormMarker) {
                        context.getOutput().print(String.format("<%d>", ((FormMarker) element).getOrdinal()));
                    }
                }
            }
            context.getOutput().println();
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}