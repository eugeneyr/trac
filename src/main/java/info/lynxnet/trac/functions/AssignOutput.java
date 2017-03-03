package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@RegisteredFunction(
        name = AssignOutput.FUNCTION_NAME, mnemonics = AssignOutput.FUNCTION_MNEMONICS, category = FunctionCategory.IO)
public class AssignOutput implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ao";
    public static final String FUNCTION_NAME = "Assign Output";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.IO;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String fileName = stackElement.getArgumentValue(1);
        if (fileName.length() == 0) {
            context.setInput(System.in);
        } else {
            try {
                PrintStream os = new PrintStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                context.setOutput(os);
            } catch (FileNotFoundException e) {
                return new ExecutionResult(true, stackElement.getArgumentValue(2));
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}