package hw7.tsk2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.io.*;

public class EncryptedClassLoader extends ClassLoader {
    private final byte key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = Byte.parseByte(key, 2);
        this.dir = dir;
    }

    protected Class<?> findClass(String name){
        byte[] bar = loadClassFromFile(name);
        return defineClass(name, bar, 0, bar.length);
    }

    private byte[] loadClassFromFile(String fileName) {

        File file = new File(dir + "\\" + fileName.replace('.', File.separatorChar) + ".class");
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());
            for (int i = 0; i < fileContent.length; ++i)
                fileContent[i] ^= key;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
/*
    protected Class<?> loadClass(String name, boolean resolve) {

    }
*/
    /*
    public Class<?> loadClass(String name) {

    }

    protected Class<?> loadClass(String name, boolean resolve) {

    }

    protected final Class<?> findLoadedClass(String name) {

    }

    public final ClassLoader getParent() {

    }


    protected final void resolveClass(Class<?> c) {

    }
    */
}
