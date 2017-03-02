package info.lynxnet.trac.functions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisteredFunction {
    String mnemonics();
    String name();
    String[] aliases() default {};
}
