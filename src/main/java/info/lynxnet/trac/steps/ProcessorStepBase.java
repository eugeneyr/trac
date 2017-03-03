package info.lynxnet.trac.steps;

public abstract class ProcessorStepBase implements ProcessorStep {
    @Override
    public boolean isInitial() {
        return false;
    }
}
