package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RegisteredFunction(
        name = AssignInput.FUNCTION_NAME, mnemonics = AssignInput.FUNCTION_MNEMONICS, category = FunctionCategory.IO)
public class AssignInput implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ai";
    public static final String FUNCTION_NAME = "Assign Input";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String fileName = stackElement.getArgumentValue(1);
        if (fileName.length() == 0) {
            context.setInput(System.in);
        } else {
            try {
                InputStream is = new BufferedInputStream(new FileInputStream(fileName));
                context.setInput(is);
            } catch (FileNotFoundException e) {
                return new ExecutionResult(true, stackElement.getArgumentValue(2));
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}