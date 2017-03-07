package info.lynxnet.trac.functions;

import info.lynxnet.trac.*;

import java.util.List;
import java.util.Optional;

@RegisteredFunction(
        name = InitialMatch.FUNCTION_NAME,
        mnemonics = InitialMatch.FUNCTION_MNEMONICS,
        category = FunctionCategory.FORMS)
public class InitialMatch implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "in";
    public static final String FUNCTION_NAME = "Initial Match";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);
            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                List<FormElement> segmented = form.segment();
                Lexem valArg = stackElement.getArguments().get(2);

                Optional<FormElement> found = segmented.stream().filter(
                        x -> (x instanceof FormSegment)
                                && (x.getOffset() + ((FormSegment) x).getValue().length() > form.getPointer())
                                && ((FormSegment)x).getValue().contains(valArg.getValue())
                ).min((a, b) -> a.getOffset() - b.getOffset());
                if (!found.isPresent()) {
                    return new ExecutionResult(true, stackElement.getArgumentValue(3));
                }
                FormSegment foundSegment = (FormSegment) found.get();
                result.append(
                        form.getBody().substring(
                                form.getPointer(),
                                foundSegment.getOffset() + foundSegment.getValue().indexOf(valArg.getValue())));
                form.setPointer(
                        foundSegment.getOffset()
                                + foundSegment.getValue().indexOf(valArg.getValue()) + valArg.getValue().length());
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}