package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;

public class ProcessorStep2 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0
                && Character.isSpaceChar(context.getActiveString().charAt(0))
                && context.getActiveString().charAt(0) != ' ';
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        char ch = context.getActiveString().charAt(0);
        if (Character.isSpaceChar(ch) && ch != ' ') {
            context.getActiveString().deleteCharAt(0);
            return ProcessorStep1.class;
        }
        return ProcessorStep3.class;
    }
}
