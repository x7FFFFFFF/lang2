package my.lang2;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created on 16.03.2018.
 */
public class CompilerTest {
    @Test
    public void main() throws Exception {

        final Path path = Paths.get("src/test/resources/test.l2");


      Compiler compiler = new Compiler();
       // String script = "var numVar=1\n   print numVar \n";*/
        compiler.compile(new String[]{path.toAbsolutePath().toString()});


    }

}