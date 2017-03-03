package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.StackElement;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

@RegisteredFunction(
        name = EraseBlock.FUNCTION_NAME,
        mnemonics = EraseBlock.FUNCTION_MNEMONICS,
        category = FunctionCategory.STORAGE)
public class EraseBlock implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "eb";
    public static final String FUNCTION_NAME = "Erase Block";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.STORAGE;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 1) {
            String fileFormName = stackElement.getArgumentValue(1);
            Form fileForm = context.getFormStorage().get(fileFormName);
            if (fileForm != null) {
                try {
                    Files.deleteIfExists(FileSystems.getDefault().getPath(fileForm.getBody().toString()));
                    context.getFormStorage().remove(fileFormName);
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}