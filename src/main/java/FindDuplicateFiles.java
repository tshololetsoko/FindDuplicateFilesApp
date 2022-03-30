import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TSHOLO LETSOKO
 */

public class FindDuplicateFiles implements Callable {

    File [] rootFilePath;
    ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
    Vector <List< String >> res = new Vector <> ();

    public FindDuplicateFiles(File [] rootFilePath) {
        this.rootFilePath = rootFilePath;
    }
    public FindDuplicateFiles() {

    }

    public Vector<List<String>> findDuplicate(String[] paths) {

        for (String path : paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                //adding the path to the  content as the
                List<String> list = map.getOrDefault(values[2], new ArrayList<String>());
                list.add(values[0] + "/" + values[1]);
                map.put(values[1], list);
            }
        }

        //to store the result list which is the absolute path of the file....
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

       //results = findDuplicate(paths);

        return true;
    }

}