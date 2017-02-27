package info.lynxnet.trac.states;

import info.lynxnet.trac.Constants;
import info.lynxnet.trac.Context;

public class InterpreterState7 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0
                && context.getActiveString().charAt(0) == '#'
                && context.getActiveString().indexOf(Constants.ACTIVE_FUNCTION_MARKER) != 0
                && context.getActiveString().indexOf(Constants.NEUTRAL_FUNCTION_MARKER) != 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        if (precondition(context)) {
            // a # that does not start a function
            context.getActiveString().deleteCharAt(0);
            context.getNeutralString().append('#');
            return InterpreterState1.class;
        } else {
            return InterpreterState8.class;
        }
    }
}
