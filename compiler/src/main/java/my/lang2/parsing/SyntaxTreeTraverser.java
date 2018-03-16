package my.lang2.parsing;

import my.lang2.bytecodegeneration.instructions.Instruction;
import my.lang2.gen.Lang2Lexer;
import my.lang2.gen.Lang2Parser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.util.Queue;


public class SyntaxTreeTraverser {
    public Queue<Instruction> getInstructionsFromFile(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath);
        return getInstructions(charStream);
    }

    public Queue<Instruction> getInstructionsFromString(String script) throws IOException {
        char[] chars = script.toCharArray();
        CharStream charStream = new ANTLRInputStream(chars, chars.length);
        return getInstructions(charStream);
    }

    private Queue<Instruction> getInstructions(CharStream charStream) {
        Lang2Lexer lexer = new Lang2Lexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Lang2Parser parser = new Lang2Parser(tokenStream);
        L2TreeWalkListener listener = new L2TreeWalkListener();
        BaseErrorListener errorListener = new L2TreeWalkErrorListener();

        parser.addErrorListener(errorListener);
        parser.addParseListener(listener);
        parser.compilationUnit();
        return listener.getInstructionsQueue();
    }
}
