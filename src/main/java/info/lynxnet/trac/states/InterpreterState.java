package info.lynxnet.trac.states;

import info.lynxnet.trac.Context;

public interface InterpreterState {
    boolean precondition(Context context);
    boolean isInitial();
    Class<? extends InterpreterState> actionAndTransition(Context context);
}
