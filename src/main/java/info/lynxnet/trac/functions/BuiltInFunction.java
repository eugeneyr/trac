package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

public interface BuiltInFunction {
    String getMnemonics();
    String getCategory();
    String getName();
    ExecutionResult execute(StackElement stackElement, Context context);
}
