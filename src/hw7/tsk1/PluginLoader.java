package hw7.tsk1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PluginLoader extends ClassLoader {

    private final File dir;

    public PluginLoader(File dir, ClassLoader parent) {
        super(parent);
        this.dir = dir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] bar = loadClassFromFile(name);
        return defineClass(name, bar, 0, bar.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        File file = new File(dir + "\\" + fileName.replace('.', File.separatorChar) + ".class");
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                //long t0 = System.nanoTime();
                try {
                /*
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                  */
                    c = findClass(name);
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }
                /*
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                */
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

}
