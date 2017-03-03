package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

@RegisteredFunction(
        name = CharacterToDecimal.FUNCTION_NAME,
        mnemonics = CharacterToDecimal.FUNCTION_MNEMONICS,
        category = FunctionCategory.STRING)
public class CharacterToDecimal implements TracFunction {
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