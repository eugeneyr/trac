package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.FunctionEvaluator;
import info.lynxnet.trac.StackElement;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@RegisteredFunction(
        name = Help.FUNCTION_NAME,
        mnemonics = Help.FUNCTION_MNEMONICS,
        category = FunctionCategory.DEBUG)
public class Help implements TracFunction {
    public static final String FUNCTION_MNEMONICS = "help";
    public static final String FUNCTION_NAME = "Help";

    private static final Properties helpProps = new Properties();

    static {
        try (InputStream is = Help.class.getResourceAsStream("/HelpMessages.xml")) {
            helpProps.loadFromXML(is);
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.DEBUG;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        Set<String> cats = new HashSet<>();
        String funcName = stackElement.getArgumentValue(1);
        if (helpProps.containsKey(funcName)) {
            sb.append(helpProps.getProperty(funcName));
        } else if (FunctionEvaluator.BUILTINS.containsKey(funcName)) {
            TracFunction func = FunctionEvaluator.BUILTINS.get(funcName);
            sb.append(String.format("#(%s)\t%s [%s]\n", func.getMnemonics(), func.getName(), func.getCategory()));
        } else {
            FunctionEvaluator.BUILTINS.values().stream().sorted((a, b) -> {
                int comp = a.getCategory().compareTo(b.getCategory());
                if (comp == 0) {
                    comp = a.getMnemonics().compareTo(b.getMnemonics());
                }
                return comp;
            }).forEach(x -> {
                if (!cats.contains(x.getCategory())) {
                    sb.append("\n * ").append(x.getCategory()).append(" *\n\n");
                    cats.add(x.getCategory());
                }
                sb.append(String.format("#(%s)\t%s\n", x.getMnemonics(), x.getName()));
            });
        }
        System.out.println(sb.toString());
        return new ExecutionResult(stackElement.isActive(), "");
    }
}