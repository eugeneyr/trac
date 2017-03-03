package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;

public interface ProcessorStep {
    boolean precondition(Context context);
    boolean isInitial();
    Class<? extends ProcessorStep> actionAndTransition(Context context);
}
