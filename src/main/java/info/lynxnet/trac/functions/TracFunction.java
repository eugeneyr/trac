package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

public interface TracFunction {
    ExecutionResult execute(StackElement stackElement, Context context);
}
