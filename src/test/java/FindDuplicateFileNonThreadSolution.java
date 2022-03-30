import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class FindDuplicateFileNonThreadSolution {
    private final static Logger log = Logger.getLogger("FindDuplicateFileNonThreadSolution");
    FindDuplicateFiles handler;
    File[] root;
    List <File[]> newFile = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        //to find the system's root path
        File rootPth = new File("C:\\Users\\OOL\\CheckDuplicateFilesApplication\\src\\test\\TestFiles\\");

        if (rootPth.list().length > 0){
            newFile.add(rootPth.listFiles());
        }

    }

    @Test
    public void testDuplicateFilesFinder() throws Exception {

        //for test case we assume that root== "TestFiles" folder
        Vector<List<String>> findDuplicate  =new Vector<>();
        for (File[] files : newFile) {
            FindDuplicateFiles duplicateFilesFinder = new FindDuplicateFiles(files);
            for (File f : files) {
                if (!f.isDirectory()) {

                    String[] s = {f.getPath() + " " + f.getName() + "(" +
                            Files.readAllBytes(Paths.get(f.getPath())) + ")"};
                    findDuplicate = duplicateFilesFinder.findDuplicate(s);
                    Files.lines(Paths.get(f.getPath()),StandardCharsets.UTF_8);//.forEach(System.out::printlnFiles.lines(Paths.get(f.getPath()),StandardCharsets.UTF_8).forEach(System.out::println);
                }
                else{
                    for (File f2: f.listFiles()){
                        String[] s = {f2.getPath() + " " + f2.getName() + "(" + Files.readAllBytes(Paths.get(f2.getPath())) + ")"};
                        findDuplicate = duplicateFilesFinder.findDuplicate(s);
                    }
                }
            }
            assertTrue(findDuplicate.isEmpty());

        }
    }



}
