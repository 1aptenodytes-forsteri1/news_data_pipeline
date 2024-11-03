package com.newsdatapipeline.backend.Services;

import jakarta.annotation.PreDestroy;
import org.newspipeline.Article;
import org.newspipeline.Location;
import org.newspipeline.WebsitePlugin;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

@Service
public class PluginService {
    private static final String PLUGIN_DIR = "libs";
    ServiceLoader<WebsitePlugin> loader;
    URLClassLoader urlClassLoader;

    PluginService() throws IOException {
        loader = loadPlugins();
    }
    public Flux<Article> getWebsitesArticles(Location location){
        return Flux.fromIterable(loader)
                .flatMap(element -> element.getArticles(location))
                .subscribeOn(Schedulers.boundedElastic());

    }

    private ServiceLoader<WebsitePlugin> loadPlugins() throws IOException {
        File pluginDir = new File(PLUGIN_DIR);
        File[] jarFiles = pluginDir.listFiles((dir, name) -> name.endsWith(".jar"));

        if (jarFiles != null) {
            URL[] jarUrls = new URL[jarFiles.length];
            for (int i = 0; i < jarFiles.length; i++) {
                jarUrls[i] = jarFiles[i].toURI().toURL();
            }

            urlClassLoader = new URLClassLoader(jarUrls, PluginService.class.getClassLoader());
                ServiceLoader<WebsitePlugin> serviceLoader = ServiceLoader.load(WebsitePlugin.class, urlClassLoader);
                return serviceLoader;

        } else {
            System.out.println("No plugins found in " + PLUGIN_DIR);
        }
        return null;
    }

    @PreDestroy
    public void closeClassLoader() throws IOException {
        if (urlClassLoader != null) {
            urlClassLoader.close();
        }
    }

}


