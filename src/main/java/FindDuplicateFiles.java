import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TSHOLO LETSOKO
 * March 2022
 */

public class FindDuplicateFiles {

    File rootFilePath;
    ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
    LinkedList <List< String >> resultSet = new LinkedList<>();

    public FindDuplicateFiles(File rootFilePath) {
        this.rootFilePath = rootFilePath;
    }

    public LinkedList<List<String>> findDuplicate(File[] paths) throws Exception{
        for (File path : paths) {
            if (path.isDirectory())
                findDuplicate(path.listFiles());
            else {
                String content = readFileContent(path);
                //adding the path to the  content as the
                List<String> list = map.getOrDefault(content, new LinkedList<>());
                list.add(path.getAbsolutePath());

                map.put(content, list);
                if (list.size() ==2) {
                    resultSet.add(list); //reference object -if map is updated then list is also update
                }
            }
        }

        return resultSet;
    }

    public String readFileContent(File file) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        StringBuilder sbf = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sbf.append(line);
        }
        reader.close();
        return sbf.toString();
    }

}