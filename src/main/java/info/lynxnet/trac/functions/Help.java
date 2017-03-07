package info.lynxnet.trac.functions;

import info.lynxnet.trac.Context;
import info.lynxnet.trac.FunctionEvaluator;
import info.lynxnet.trac.StackElement;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

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

    private RegisteredFunction getAnnotation(TracFunction func) {
        Annotation[] annots = func.getClass().getAnnotationsByType(RegisteredFunction.class);
        if (annots.length > 0) {
            return (RegisteredFunction) annots[0];
        }
        return null;
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
            RegisteredFunction annot = getAnnotation(func);
            if (annot != null) {
                String[] mnemos = Arrays.asList(
                        annot.mnemonics()).stream()
                        .map(x -> String.format("#(%s)", x))
                        .collect(Collectors.toList()).toArray(new String[annot.mnemonics().length]);
                sb.append(String.join(", ", mnemos))
                        .append('\t').append(annot.name()).append(" [").append(annot.category()).append("]\n");
            }
        } else {
            FunctionEvaluator.BUILTINS.values().stream().collect(Collectors.toSet()).stream().sorted((a, b) -> {
                RegisteredFunction annotA = getAnnotation(a);
                RegisteredFunction annotB = getAnnotation(b);
                int comp = (annotA != null && annotB != null) ? annotA.category().compareTo(annotB.category()) : -1;
                if (comp == 0) {
                    comp = annotA.mnemonics()[0].compareTo(annotB.mnemonics()[0]);
                }
                return comp;
            }).forEach(x -> {
                RegisteredFunction annot = getAnnotation(x);
                if (annot != null) {
                    if (!cats.contains(annot.category())) {
                        sb.append("\n * ").append(annot.category()).append(" *\n\n");
                        cats.add(annot.category());
                    }
                    String[] mnemos = Arrays.asList(
                            annot.mnemonics()).stream()
                            .map(m -> String.format("#(%s)", m))
                            .collect(Collectors.toList()).toArray(new String[annot.mnemonics().length]);
                    sb.append(String.join(", ", mnemos))
                            .append("\t\t").append(annot.name()).append("\n");
                }
            });
        }
        System.out.println(sb.toString());
        return new ExecutionResult(stackElement.isActive(), "");
    }
}