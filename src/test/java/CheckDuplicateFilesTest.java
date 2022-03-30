

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;


public class CheckDuplicateFilesTest {
    private final static Logger log = Logger.getLogger("CheckDuplicateFilesTest");
    FindDuplicateFiles handler;
    File[] root;

    @Before
    public void setup() throws Exception {
        //to find the system's root path
        root = File.listRoots();
    }

    @Test
    public void testDuplicateFilesFinder() throws Exception{

        ExecutorService executorServiceThreadpool = Executors.newFixedThreadPool(100);
        List<Future> futures = new ArrayList<>();

        for (File file : root){
            File[] directories = file.listFiles(File::isDirectory);

            for (File fileDir : directories){
                Future futureThread = executorServiceThreadpool.submit(new FindDuplicateFiles(root));
                futures.add(futureThread);
            }

            ConcurrentHashMap< String, List < String >> resultSet = new ConcurrentHashMap<>();
                //giving each thread a max of 100
            int maxChecks = futures.size() * 120;
            while(!futures.isEmpty() && maxChecks>0){
               log.info("Thread size = " + futures.size());
               for (Future future : futures) {
                   if(future.isDone()){
                    try {
                        resultSet.get(( Vector< List < String >> ) future.get());
                    }catch (Exception e){
                        log.warning(e.getMessage());
                        e.printStackTrace();
                    }
                    //log.info(future.get());
                    futures.remove(future);
                    break;
                }
            }
            //sleep 0.5 second
            Thread.sleep(500);
            maxChecks--;
        }
                executorServiceThreadpool.shutdown();
                log.info("END TIME Date() = " + new Date());

            }
        }

    }


