import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReadService {
    public String readFileString(String fileName){
        Path filePath = Path.of(fileName);
        String content;
        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
            content = "";
        }
        return content;
    }
}