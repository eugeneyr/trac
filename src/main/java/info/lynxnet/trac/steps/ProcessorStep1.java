package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;

public class ProcessorStep1 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return true;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        if (context.getActiveString().length() == 0) {
            return ProcessorStep0.class;
        }
        return ProcessorStep2.class;
    }
}
