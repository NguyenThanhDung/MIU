import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        WordCount wordCount = new WordCount(3, 4);
        wordCount.Run();
    }
}