// Generated from Lang2.g4 by ANTLR 4.3

package my.lang2.gen;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Lang2Parser}.
 */
public interface Lang2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Lang2Parser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(@NotNull Lang2Parser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lang2Parser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(@NotNull Lang2Parser.PrintContext ctx);

	/**
	 * Enter a parse tree produced by {@link Lang2Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull Lang2Parser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lang2Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull Lang2Parser.CompilationUnitContext ctx);

	/**
	 * Enter a parse tree produced by {@link Lang2Parser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull Lang2Parser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lang2Parser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull Lang2Parser.VariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link Lang2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull Lang2Parser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Lang2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull Lang2Parser.ValueContext ctx);
}