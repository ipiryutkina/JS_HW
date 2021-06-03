package hw8;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CacheProxyTest {


    public static void main(String[] args) throws Exception {
        CacheProxy cacheProxy = new CacheProxy("d:\\out_");
        Service service = cacheProxy.cache(new ServiceImpl());
        //service.doHardWork("Aaaa",10);
        service.run("Aaaa", 0.5, new Date(12));
        //service.work("Aaaa");
    }
    /*
    public static void main(String[] args) throws IOException, IOException {
        ZipFile zipFile = new ZipFile("d:\\iiiiii.zip");
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while(((Enumeration<?>) entries).hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            System.out.println(entry.getName());
            InputStream stream = zipFile.getInputStream(entry);

            //For characters
            //BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            BufferedInputStream reader = new BufferedInputStream(stream);
            //For line reading
            //System.out.println(reader.readLine());
            System.out.println(entry.getSize());
            byte[] bytes=new byte[(int)entry.getSize()];
            reader.read(bytes);
            for(byte b:bytes){
                System.out.println(b);
            }

            int byteRead = reader.read();
            while(byteRead != -1) {
                //System.out.println(byteRead);
                byteRead = reader.read();
            }

        }

    }
    */
}
