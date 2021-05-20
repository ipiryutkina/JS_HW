package hw8;

public class CacheProxyTest {


    public static void main(String[] args) throws Exception {
        CacheProxy cacheProxy = new CacheProxy("");
        Service service = cacheProxy.cache(new ServiceImpl());
        //Loader loader = cacheProxy.cache(new LoaderImpl());


    }

}
