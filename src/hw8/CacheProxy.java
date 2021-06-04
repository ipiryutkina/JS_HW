package hw8;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class CacheProxy implements InvocationHandler {

    private final Map<Object, Object> resultByArg = new ConcurrentHashMap<>();
    private Service delegate = null;
    private final String rootDir;

    private CacheProxy(Service delegate, String rootDir) {
        this.delegate = delegate;
        this.rootDir = rootDir;
    }

    public CacheProxy(String rootDir) {
        this.rootDir = rootDir;
    }

    public Service cache(Service delegate) {
        return (Service) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheProxy(delegate, rootDir)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Cache cacheAn = method.getAnnotation(Cache.class);
        if (cacheAn == null)
            return invoke(method, args);
        //2
        List<Object> argsToCache = new ArrayList<>();
        Annotation[][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; ++i) {
            boolean fl = true;
            for (Annotation a : pa[i]) {
                if (a instanceof Ignore)
                    fl = false;
                break;
            }
            if (fl) argsToCache.add(args[i]);
        }

        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(argsToCache));

        Object invokeOut = null;
        String name = cacheAn.fileNamePrefix() + Integer.toString(key.hashCode());
        if (cacheAn.cacheType() == CacheType.FILE) {

            File f = new File(rootDir, name + (cacheAn.zip() ? ".zip" : ".bin"));

            if (f.exists()) {
                invokeOut = readFromFile(name, cacheAn.zip());
            } else {
                invokeOut = invoke(method, args);
                writeToFile(invokeOut, name, cacheAn.zip());
            }

        } else if (cacheAn.cacheType() == CacheType.IN_MEMORY) {
            Object currKey = key(method, argsToCache.toArray());
            if (!resultByArg.containsKey(currKey)) {
                invokeOut = invoke(method, args);
                resultByArg.put(currKey, invokeOut);
            } else {
                invokeOut = resultByArg.get(currKey);
            }

        }
        return invokeOut;
    }


    private Object readFromFile(String name, boolean zip) throws IOException, ClassNotFoundException {

        byte[] bytes;
        InputStream is = null;
        File f = new File(rootDir, name + (zip?".zip":".bin"));
        if (zip) {
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ZipInputStream stream = new ZipInputStream(bis);
            ZipEntry entry = stream.getNextEntry();
            ZipFile zipFile = new ZipFile(f);
            is = zipFile.getInputStream(entry);
        }
        else{
              is =  new FileInputStream(f);
        }

        //byte[] buffer = new byte[is.available()];
        //is.read(buffer);
        ObjectInputStream ois = new ObjectInputStream(is);
        Object out = ois.readObject();

        return out;
    }

    private void writeToFile(Object obj, String name, boolean zip) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        byte[] stream = baos.toByteArray();

        File f = new File(rootDir, name + ".bin");

        FileOutputStream os = new FileOutputStream(f);
        os.write(stream);
        os.close();

        if (zip) {

            File zi = new File(rootDir, name + ".zip");
            FileOutputStream fos = new FileOutputStream(zi);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(f.getName()));

            byte[] bytes = Files.readAllBytes(Paths.get(f.getPath()));
            zos.write(bytes, 0, bytes.length);

            zos.closeEntry();
            zos.close();
            fos.close();
            f.delete();

        }

    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            Object out = method.invoke(delegate, args);
            //3
            Cache cacheAn = method.getAnnotation(Cache.class);
            if (cacheAn == null || !method.getReturnType().isInstance(List.class))
                return out;
            int size = Math.min(cacheAn.maxListSize(), ((List) out).size());
            return ((List) out).subList(0, size);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }
}
