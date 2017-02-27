package info.lynxnet.trac.states;

public abstract class InterpreterStateBase implements InterpreterState {
    @Override
    public boolean isInitial() {
        return false;
    }
}
