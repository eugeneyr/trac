package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.StackElement;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@RegisteredFunction(
        name = LoadFile.FUNCTION_NAME,
        mnemonics = LoadFile.FUNCTION_MNEMONICS,
        category = FunctionCategory.IO)
public class LoadFile implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "ld";
    public static final String FUNCTION_NAME = "Load File";

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
        Path path = FileSystems.getDefault().getPath(fileName);
        if (Files.exists(path)) {
            try {
                return new ExecutionResult(stackElement.isActive(), new String(Files.readAllBytes(path)));
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
        return new ExecutionResult(true, stackElement.getArgumentValue(2));
    }
}