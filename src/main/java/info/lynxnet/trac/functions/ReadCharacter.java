package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RegisteredFunction(
        name = ReadCharacter.FUNCTION_NAME,
        mnemonics = ReadCharacter.FUNCTION_MNEMONICS,
        category = FunctionCategory.IO)
public class ReadCharacter implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "rc";
    public static final String FUNCTION_NAME = "Read Character";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getInput()));
        StringBuilder result = new StringBuilder();
        if (context.getInput() == System.in) {
            System.out.print("\n>> ");
        }
        try {
            int character = reader.read();
            if (character > -1) {
                result.append(Character.toChars(character));
            } else {
                return new ExecutionResult(true, stackElement.getArgumentValue(1));
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return new ExecutionResult(true, stackElement.getArgumentValue(1));
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}