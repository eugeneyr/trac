package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;

public class ProcessorStep9 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        if (precondition(context)) {
            // a # that does not start a function
            char ch = context.getActiveString().charAt(0);
            context.getActiveString().deleteCharAt(0);
            context.getNeutralString().append(ch);
            return ProcessorStep1.class;
        } else {
            return ProcessorStep0.class;
        }
    }
}
