# Another TRAC Interpreter

Yep. A quirky, obscure programming language born in 1964 just refuses to die, nevermind that nobody codes in TRAC anymore, anywhere. A possible next big thing in computing
A brainchild of Calvin Mooers, popularized almost by accident by Charles Wetherell in his "Etudes for Programmers".

This implementation follows the specification from Wetherell's book (I found it much more concise and easier to comprehend than Mooers' original paper)
 and includes all additional built-in functions proposed by Wetherell with the exteption of #(sp,N) and #(rp) as having too dubious a value in the world of modern file systems.

I also added a basic-y help system that can be invoked by typing
 #(help)`
or
 #(help,<function_name>)`
in the interpreter REPL.

## What's still missing?

A more comprehensive help system. A unit test suite. More non-canonical built-in functions that actually let you do stuff.

