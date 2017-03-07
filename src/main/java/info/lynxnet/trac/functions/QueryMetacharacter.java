package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = QueryMetacharacter.FUNCTION_NAME,
        mnemonics = QueryMetacharacter.FUNCTION_MNEMONICS,
        category = FunctionCategory.STRING)
public class QueryMetacharacter implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "qm";
    public static final String FUNCTION_NAME = "Query Metacharacter";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        return new ExecutionResult(
                stackElement.isActive(),
                Character.toString(context.getMetacharacter()));
    }
}