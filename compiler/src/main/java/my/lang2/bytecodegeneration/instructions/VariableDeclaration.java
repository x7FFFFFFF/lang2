package my.lang2.bytecodegeneration.instructions;


import my.lang2.gen.Lang2Lexer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import my.lang2.parsing.domain.Variable;


public class VariableDeclaration implements Instruction,Opcodes {
    Variable variable;

    public VariableDeclaration(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void apply(MethodVisitor mv) {
        final int type = variable.getType();
        if(type == Lang2Lexer.NUMBER) {
            int val = Integer.valueOf(variable.getValue());
            mv.visitIntInsn(BIPUSH,val);
            mv.visitVarInsn(ISTORE,variable.getId());
        } else if(type == Lang2Lexer.STRING) {
            mv.visitLdcInsn(variable.getValue());
            mv.visitVarInsn(ASTORE,variable.getId());
        }
    }
}
