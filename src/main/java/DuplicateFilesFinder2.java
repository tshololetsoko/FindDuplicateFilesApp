import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TSHOLO LETSOKO
 */

public class DuplicateFilesFinder2 implements Callable {

    File [] rootFilePath;

    public DuplicateFilesFinder2(File [] rootFilePath) {
        this.rootFilePath = rootFilePath;
    }

    public Vector<List<String>> findDuplicate(String[] paths) {
        ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

        for (String path : paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                String[] content = values[i].split("\\[]");
                content[1] = content[1].replace("]", "");
                //adding the path to the  content as the
                List<String> list = map.getOrDefault(content[1], new ArrayList<String>());
                list.add(values[0] + "/" + content[0]);
                map.put(content[1], list);
            }
        }

        //to store the result list which is the absolute path of the file....
        Vector <List< String >> res = new Vector <> ();
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                res.add(map.get(key));
            }
        }
        return res;
    }

    @Override
    public Object call() throws Exception {
        Vector<List<String>> results;

        ////File[] directories = rootFilePath.listFiles(File::isDirectory);

       / results = findDuplicate(paths);

        return true;
    }

}