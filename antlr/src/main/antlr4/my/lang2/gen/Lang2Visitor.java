// Generated from Lang2.g4 by ANTLR 4.3

package my.lang2.gen;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Lang2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Lang2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Lang2Parser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull Lang2Parser.PrintContext ctx);

	/**
	 * Visit a parse tree produced by {@link Lang2Parser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(@NotNull Lang2Parser.CompilationUnitContext ctx);

	/**
	 * Visit a parse tree produced by {@link Lang2Parser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(@NotNull Lang2Parser.VariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link Lang2Parser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull Lang2Parser.ValueContext ctx);
}