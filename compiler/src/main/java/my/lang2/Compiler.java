package my.lang2;

import my.lang2.bytecodegeneration.BytecodeGenerator;
import my.lang2.bytecodegeneration.instructions.Instruction;
import my.lang2.parsing.SyntaxTreeTraverser;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;

public class Compiler implements Opcodes {

    public static void main(String[] args) throws Exception {
        new Compiler().compile(args);
    }

    public void compile(String[] args) throws Exception {
        final ARGUMENT_ERRORS argumentsErrors = getArgumentValidationErrors(args);
        if(argumentsErrors != ARGUMENT_ERRORS.NONE) {
            System.out.println(argumentsErrors.getMessage());
            return;
        }
        final File l2File = new File(args[0]);
        String fileName = l2File.getName();
        String fileAbsolutePath = l2File.getAbsolutePath();
        String className = fileName.replace(".l2", "");
        final Queue<Instruction> instructionsQueue = new SyntaxTreeTraverser().getInstructionsFromFile(fileAbsolutePath);
        final byte[] byteCode = new BytecodeGenerator().generateBytecode(instructionsQueue, className);
        saveBytecodeToClassFile(fileAbsolutePath, byteCode);
    }
    public void compile(String script, String fileName) throws Exception {
        String className = fileName.replace(".l2", "");
        final Queue<Instruction> instructionsQueue = new SyntaxTreeTraverser().getInstructionsFromString(script);
        final byte[] byteCode = new BytecodeGenerator().generateBytecode(instructionsQueue, className);
        saveBytecodeToClassFile(fileName, byteCode);

    }

    private ARGUMENT_ERRORS getArgumentValidationErrors(String[] args) {
        if(args.length != 1) {
            return ARGUMENT_ERRORS.NO_FILE;
        }
        String filePath=args[0];
        if (!filePath.endsWith(".l2")) {
            return ARGUMENT_ERRORS.BAD_FILE_EXTENSION;
        }
        return ARGUMENT_ERRORS.NONE;
    }

    private static void saveBytecodeToClassFile(String fileName, byte[] byteCode) throws IOException {
        final String classFile = fileName.replace(".l2", ".class");
        OutputStream os = new FileOutputStream(classFile);
        os.write(byteCode);
        os.close();
    }
}
