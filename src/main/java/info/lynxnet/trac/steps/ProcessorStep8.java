package info.lynxnet.trac.steps;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.FunctionEvaluator;
import info.lynxnet.trac.StackElement;
import info.lynxnet.trac.functions.ExecutionResult;

import java.io.IOException;

public class ProcessorStep8 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0
                && context.getActiveString().charAt(0) == ')';
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        if (precondition(context)) {
            // a closing parenthesis
            if (context.getCallStack().empty()) {
                return ProcessorStep0.class;
            }
            context.getActiveString().deleteCharAt(0);
            StackElement current = context.getCallStack().pop();
            current.completeArgument(context);
            context.getNeutralString().delete(
                    current.getOffset(), context.getNeutralString().length());
            if (context.isTrace()) {
                System.err.println(current.toString());
                try {
                    int character = System.in.read();
                    if (!Character.isWhitespace(character)) {
                        return ProcessorStep0.class;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ExecutionResult result = FunctionEvaluator.getInstance().evaluate(current, context);
            if (result.isActive()) {
                context.getActiveString().insert(0, result.getValue());
            } else {
                context.getNeutralString().append(result.getValue());
            }

            return ProcessorStep1.class;
        } else {
            return ProcessorStep9.class;
        }
    }
}
