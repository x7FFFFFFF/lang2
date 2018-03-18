package my.lang2;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 16.03.2018.
 */
public class CompilerTest {
    @Test
    public void main() throws Exception {
        final String classPath = "src/test/resources/test.class";
        delete(classPath);

        final Path path = Paths.get("src/test/resources/test.l2");


       Compiler compiler = new Compiler();

        compiler.compile(new String[]{path.toAbsolutePath().toString()});

        final Class<?> testClass = load(classPath, "test");
        final Method mainMethod = testClass.getMethod("main", String[].class);
        String[] params = null;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        mainMethod.invoke(null, (Object)params);


        Assert.assertEquals("1\r\n" +
                "\"test\"\r\n",  outContent.toString());
        //System.setOut(System.out);

        //final Object instance = testClass.newInstance();

        //System.out.println("instance = " + instance);

    }

    private Class<?> load(String path, String className) throws MalformedURLException, ClassNotFoundException {
        final Path pathClss = Paths.get(path);
        if (Files.exists(pathClss)){
            final URL url = pathClss.toUri().toURL();
            ClassLoader cl = new URLClassLoader(new URL[]{url});
            return cl.loadClass(className);
        }
        throw new IllegalArgumentException(String.format("'%s'  not found",path));
    }

    private void delete(String path) throws IOException {
        final Path pathClss = Paths.get(path);
        if (Files.exists(pathClss)){
            Files.delete(pathClss);
        }
    }


}