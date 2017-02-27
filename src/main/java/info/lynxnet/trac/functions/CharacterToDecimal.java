package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

public class CharacterToDecimal implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "cd";
    public static final String FUNCTION_NAME = "Character to Decimal";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.STRING;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String string = stackElement.getArgumentValue(1);
        StringBuilder sb = new StringBuilder();
        if (string.length() > 0) {
            sb.append(Character.codePointAt(string, 0));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}