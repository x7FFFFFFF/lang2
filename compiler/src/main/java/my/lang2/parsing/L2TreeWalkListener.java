package my.lang2.parsing;

import my.lang2.bytecodegeneration.instructions.Instruction;
import my.lang2.bytecodegeneration.instructions.PrintVariable;
import my.lang2.bytecodegeneration.instructions.VariableDeclaration;
import my.lang2.gen.Lang2BaseListener;
import my.lang2.gen.Lang2Parser;
import my.lang2.parsing.domain.Variable;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


public class L2TreeWalkListener extends Lang2BaseListener {

    Queue<Instruction> instructionsQueue = new ArrayDeque<>();
    Map<String, Variable> variables = new HashMap<>();

    public Queue<Instruction> getInstructionsQueue() {
        return instructionsQueue;
    }

    @Override
    public void exitVariable(@NotNull Lang2Parser.VariableContext ctx) {
        final TerminalNode varName = ctx.ID();
        final Lang2Parser.ValueContext varValue = ctx.value();
        final int varType = varValue.getStart().getType();
        final int varIndex = variables.size();
        final String varTextValue = varValue.getText();
        Variable var = new Variable(varIndex, varType, varTextValue);
        variables.put(varName.getText(), var);
        instructionsQueue.add(new VariableDeclaration(var));
        logVariableDeclarationStatementFound(varName, varValue);
    }

    @Override
    public void exitPrint(@NotNull Lang2Parser.PrintContext ctx) {
        final TerminalNode varName = ctx.ID();
        final boolean printedVarNotDeclared = !variables.containsKey(varName.getText());
        if (printedVarNotDeclared) {
            final String erroFormat = "ERROR: var '%s'  has not been declared.";
            System.out.printf(erroFormat, varName.getText());
            return;
        }
        final Variable variable = variables.get(varName.getText());
        instructionsQueue.add(new PrintVariable(variable));
        logPrintStatementFound(varName, variable);
    }

    private void logVariableDeclarationStatementFound(TerminalNode varName, Lang2Parser.ValueContext varValue) {
        final int line = varName.getSymbol().getLine();
        final String format = "Variable named '%s' with value of '%s' at line '%s' declared.\n";
        System.out.printf(format, varName, varValue.getText(), line);
    }

    private void logPrintStatementFound(TerminalNode varName, Variable variable) {
        final int line = varName.getSymbol().getLine();
        final String format = "Variable '%s' which has value of '%s' at line '%s' printed.'\n";
        System.out.printf(format,variable.getId(),variable.getValue(),line);
    }
}
