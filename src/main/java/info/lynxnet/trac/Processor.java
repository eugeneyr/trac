package info.lynxnet.trac;

import info.lynxnet.trac.steps.ProcessorStep;
import info.lynxnet.trac.steps.ProcessorStepBase;
import org.reflections.*;

import java.util.*;

public class Processor {
    private ProcessorStep initialState;
    private List<ProcessorStep> processorSteps = new ArrayList<>();
    private Map<Class<? extends ProcessorStep>, ProcessorStep> stateCache = new HashMap<>();

    private Context context;

    public List<ProcessorStep> getProcessorSteps() {
        return processorSteps;
    }

    public Processor(String[] commandLine) {
        context = new Context(commandLine);
        loadStates();
    }

    private void loadStates() {
        Reflections refs = new Reflections("info.lynxnet.trac.steps");
        Set<Class<? extends ProcessorStepBase>> stateClasses = refs.getSubTypesOf(ProcessorStepBase.class);
        for (Class<? extends ProcessorStepBase> clz : stateClasses) {
            try {
                ProcessorStep state = clz.newInstance();
                this.processorSteps.add(state);
                this.stateCache.put(clz, state);
                if (state.isInitial()) {
                    this.initialState = state;
                }
            } catch (InstantiationException e) {
                e.printStackTrace(System.out);
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public int run() {
        if (this.initialState == null) {
            System.err.println("Invalid state machine configuration: missing initial state");
            return -1;
        }
        if (context.getOutput() == System.out && context.getInput() == System.in) {
            System.out.println("Type\n\t#(help)'\nto get the overview of all available functions.\n" +
                    "Type\n" +
                    "\t#(help,fn)'\n" +
                    "to get the detailed info on a function \"fn\".");
        }
        ProcessorStep state = this.initialState;
        while (!context.shouldExit()) {
            try {
                Class<? extends ProcessorStep> nextStateClass = state.actionAndTransition(context);
                if (nextStateClass == null) {
                    System.err.println(String.format("FATAL: State %s transitioned to null", state.getClass().getName()));
                    context.setExitCode(-1);
                    context.setExit(true);
                }
                ProcessorStep nextState = this.stateCache.get(nextStateClass);
                if (nextState == null) {
                    System.err.println(String.format("FATAL: State %s was not pre-instantiated", nextStateClass.getName()));
                    context.setExitCode(-1);
                    context.setExit(true);
                }
                state = nextState;
            } catch (Throwable e) {
                e.printStackTrace(System.err);
                state = initialState;
            }
        }
        context.setInput(System.in);
        context.setOutput(System.out);
        return context.getExitCode();
    }

    public static void main(String[] args) {
        Processor sm = new Processor(args);
        int exitStatus = sm.run();
        System.exit(exitStatus);
    }
}
