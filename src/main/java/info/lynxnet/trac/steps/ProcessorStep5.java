package info.lynxnet.trac.steps;

import info.lynxnet.trac.Constants;
import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

public class ProcessorStep5 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        if (context.getActiveString().indexOf(Constants.ACTIVE_FUNCTION_MARKER) == 0) {
            // beginning of an active function
            StackElement current = new StackElement(true, context.getNeutralString().length());
            context.getCallStack().push(current);
            context.getActiveString().delete(0, Constants.ACTIVE_FUNCTION_MARKER.length());
            return ProcessorStep1.class;
        } else {
            return ProcessorStep6.class;
        }
    }
}
