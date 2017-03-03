package info.lynxnet.trac.steps;

import info.lynxnet.trac.Constants;
import info.lynxnet.trac.Context;

public class ProcessorStep7 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0
                && context.getActiveString().charAt(0) == '#'
                && context.getActiveString().indexOf(Constants.ACTIVE_FUNCTION_MARKER) != 0
                && context.getActiveString().indexOf(Constants.NEUTRAL_FUNCTION_MARKER) != 0;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        if (precondition(context)) {
            // a # that does not start a function
            context.getActiveString().deleteCharAt(0);
            context.getNeutralString().append('#');
            return ProcessorStep1.class;
        } else {
            return ProcessorStep8.class;
        }
    }
}
