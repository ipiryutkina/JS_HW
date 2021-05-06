package hw7.tsk1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class PluginManager {
    private final String pluginRootDirectory;
    private HashMap<String, PluginLoader> pluginLoaders;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        PluginLoader loader = pluginLoaders.get(pluginName);

        if (loader == null) {
            Path path = Paths.get(pluginRootDirectory, pluginName);
            if (Files.exists(path)) {
                loader = new PluginLoader(path.toFile(), null);
                pluginLoaders.put(pluginName, loader);
            }
        }

        Plugin instance = null;
        if (loader != null) {
            Class<?> cls = loader.loadClass(pluginClassName);
            instance = (Plugin) cls.newInstance();
        }

        return instance;
    }
}
