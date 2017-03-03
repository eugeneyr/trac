package info.lynxnet.trac.steps;

import info.lynxnet.trac.Constants;
import info.lynxnet.trac.Context;

public class ProcessorStep3 extends ProcessorStepBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends ProcessorStep> actionAndTransition(Context context) {
        char ch = context.getActiveString().charAt(0);
        if (ch == Constants.OPENING_BRACKET) {
            // find the closing parenthesis, move what's between them to the neutral string and go back to State1
            int parenCount = 1;
            int pointer = 1;
            while (pointer < context.getActiveString().length()) {
                ch = context.getActiveString().charAt(pointer);
                switch (ch) {
                    case Constants.OPENING_BRACKET:
                        parenCount++;
                        break;
                    case Constants.CLOSING_BRACKET:
                        parenCount--;
                        if (parenCount < 0) {
                            // abort - unbalanced parentheses
                            return ProcessorStep0.class;
                        } else if (parenCount == 0) {
                            // found the matching parenthesis
                            context.getNeutralString().append(
                                    context.getActiveString().substring(1, pointer));
                            context.getActiveString().delete(0, pointer + 1);
                            return ProcessorStep1.class;
                        }
                        break;
                }
                pointer++;
            }
            // if we are here, the closing parenthesis search fell off the active string. Reset everything.
            return ProcessorStep0.class;
        }
        return ProcessorStep4.class;
    }
}
