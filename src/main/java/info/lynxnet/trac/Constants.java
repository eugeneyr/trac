package info.lynxnet.trac;

public interface Constants {
    String ACTIVE_FUNCTION_MARKER = "#(";
    String NEUTRAL_FUNCTION_MARKER = "##(";
    char OPENING_BRACKET = '(';
    char CLOSING_BRACKET = ')';
    char METACHARACTER = '\'';
    String INITIAL_ACTIVE_STRING = "#(ps,(\n))#(ps,#(rs))";
    String DEFAULT_TRAC_DATA_DIR = ".tracdata";
    String BLOCK_SUBDIR = "blockstorage";
}
