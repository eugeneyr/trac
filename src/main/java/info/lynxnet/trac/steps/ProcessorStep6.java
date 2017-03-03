package info.lynxnet.trac.steps;

import info.lynxnet.trac.Constants;
import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

public class ProcessorStep6 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        if (context.getActiveString().indexOf(Constants.NEUTRAL_FUNCTION_MARKER) == 0) {
            // beginning of a neutral function
            StackElement current = new StackElement(false, context.getNeutralString().length());
            context.getCallStack().push(current);
            context.getActiveString().delete(0, Constants.NEUTRAL_FUNCTION_MARKER.length());
            return ProcessorStep1.class;
        } else {
            return ProcessorStep7.class;
        }
    }
}
