package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.Form;
import info.lynxnet.trac.StackElement;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;

@RegisteredFunction(
        name = FetchBlock.FUNCTION_NAME,
        mnemonics = FetchBlock.FUNCTION_MNEMONICS,
        category = FunctionCategory.STORAGE)
public class FetchBlock implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "fb";
    public static final String FUNCTION_NAME = "Fetch Block";

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 1) {
            String fileFormName = stackElement.getArgumentValue(1);
            Form fileForm = context.getFormStorage().get(fileFormName);
            if (fileForm != null && Files.exists(FileSystems.getDefault().getPath(fileForm.getBody().toString()))) {
                try (ObjectInputStream ois = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(fileForm.getBody().toString())))) {
                    Form[] forms = (Form[]) ois.readObject();
                    for (Form form: forms) {
                        context.getFormStorage().put(form.getName(), form);
                    }
                } catch (Throwable t) {
                    t.printStackTrace(System.err);
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}