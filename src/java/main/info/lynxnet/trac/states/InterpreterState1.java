package info.lynxnet.trac.states;

import info.lynxnet.trac.Context;

public class InterpreterState1 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return true;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        if (context.getActiveString().length() == 0) {
            return InterpreterState0.class;
        }
        return InterpreterState2.class;
    }
}
