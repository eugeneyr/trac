package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Lexem;
import info.lynxnet.trac.StackElement;

public class ProcessorStep4 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        char ch = context.getActiveString().charAt(0);
        if (ch == ',') {
            // end of a function argument and the beginning of the next one
            if (!context.getCallStack().empty()) {
                StackElement current = context.getCallStack().peek();
                if (current.getArguments().isEmpty()) {
                    current.getArguments().add(new Lexem(current.getOffset(), ""));
                }

                Lexem prevLexem = current.getArguments().get(current.getArguments().size() - 1);
                if (!prevLexem.isCompleted()) {
                    prevLexem.setValue(context.getNeutralString().substring(prevLexem.getOffset()));
                    prevLexem.setCompleted(true);
                }
                current.completeArgument(context);
                current.getArguments().add(new Lexem(context.getNeutralString().length()));
            }
            context.getActiveString().deleteCharAt(0);
            return ProcessorStep1.class;
        } else {
            return ProcessorStep5.class;
        }
    }
}
