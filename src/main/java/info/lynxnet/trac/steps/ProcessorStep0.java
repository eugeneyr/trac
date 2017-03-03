package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;

public class ProcessorStep0 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return true;
    }

    @Override
    public boolean isInitial() {
        return true;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        context.getNeutralString().setLength(0);
        context.getActiveString().setLength(0);
        context.getActiveString().append(context.getInitialActiveString());
        return ProcessorStep1.class;
    }
}
