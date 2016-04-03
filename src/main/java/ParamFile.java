import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParamFile {

    private static final Log log = LogFactory.getLog(ParamFile.class);

    public static <T> T getParamFile(String path, JavaType javaType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getParamFile(path), javaType);
    }

    public static <T> T getParamFileFromContent(String content, JavaType javaType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, javaType);
    }
    
    public static String getParamFile(String path) {
        try {
            if (path.startsWith("file") || path.startsWith("pathb")) {
                return readFile(path);
            }
            if (path.startsWith("http") || path.startsWith("https")) {
                return readUrl(path);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static String readFile(String path) throws IOException, URISyntaxException {
        URI uri = new URI(path);
        byte[] encoded = Files.readAllBytes(Paths.get(uri));
        return new String(encoded, StandardCharsets.UTF_8);
    }
    
    public static String readUrl(String url) throws IOException {
        InputStream in = new URL(url).openStream();

        try {
            return IOUtils.toString( in );
        } finally {
            IOUtils.closeQuietly(in, log);
        }
    }
}
