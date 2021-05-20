package hw8;

//import test.DynPxy;
//import test.MyService;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class CacheProxy implements InvocationHandler {

    private final Map<Object, Object> resultByArg = new HashMap<>();
    private Service delegate = null;
    private String rootDir;

    private CacheProxy(Service delegate, String rootDir) {
        this.delegate = delegate;
        this.rootDir = rootDir;
    }

    public CacheProxy(String rootDir) {
        this.delegate = null;
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
        if (cacheAn == null) return invoke(method, args);
        //2
        List<Object> argsToCache = new ArrayList<>();
        Annotation[][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; ++i) {
            for (Annotation a : pa[i]) {
                if (a instanceof Ignore)
                    continue;
            }
            argsToCache.add(args[i]);
        }

        Object invokeOut = null;
        if (cacheAn.cacheType() == CacheType.FILE) {
            String path = "";
            File file = new File(path);
            if (file.exists()) {
                invokeOut = readFromFile(path, cacheAn.zip());
            } else {
                invokeOut = invoke(method, args);
                writeToFile(invokeOut, path, cacheAn.zip());
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


    private Object readFromFile(String path, boolean zip) {
    /*
        byte[] stream = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            stream = baos.toByteArray();
        }

        if (stream != null) {
            path += (zip) ? ".zip" : ".ser";
            if (zip) {//5
                try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(path))) {
                    ZipEntry zipEntry = new ZipEntry(path + ".bin");
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(stream);
                }

            } else {
                try (FileOutputStream outputStream = new FileOutputStream(path)) {
                    outputStream.write(stream);
                }
            }
        }
        */
        return null;
    }

    private void writeToFile(Object obj, String path, boolean zip) throws IOException {

        byte[] stream = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            stream = baos.toByteArray();
        }

        if (stream != null) {
            path += (zip) ? ".zip" : ".ser";
            if (zip) {//5
                try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(path))) {
                    ZipEntry zipEntry = new ZipEntry(path + ".bin");
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(stream);
                }

            } else {
                try (FileOutputStream outputStream = new FileOutputStream(path)) {
                    outputStream.write(stream);
                }
            }
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
