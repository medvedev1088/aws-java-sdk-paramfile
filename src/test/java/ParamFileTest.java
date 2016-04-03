import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Test;

public class ParamFileTest {

    @Test
    public void testGetParamFile() throws Exception {
        String content = ParamFile.getParamFile("http://www.google.co.th/?hl=en&gws_rd=cr,ssl&ei=iGL_VqWpE8OwuQSg75rACA#newwindow=1&safe=off&hl=en&q=url+of+file");

        System.out.println(content);
    }

    @Test
    public void testGetParamFile2() throws Exception {
        String input = readFileAsString("testParse.json");
        ParamFile.getParamFileFromContent(input, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Parameter.class));
    }

    public String readFileAsString(String fileName) throws IOException {
        InputStream stream = ParamFileTest.class.getResourceAsStream(fileName);
        String result;
        try {
            result = IOUtils.toString(stream);
        } finally {
            stream.close();
        }

        return result;
    }
}
