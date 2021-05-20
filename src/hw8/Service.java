package hw8;

import java.util.Date;
import java.util.List;

public interface Service {

    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = true/*identityBy = {0,1}*/)
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.IN_MEMORY, maxListSize = 100_000)
    List<String> work(String item);

    void doHardWork(String s, @Ignore int i);
}
