package net.worldwizard.fantastle5;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JMenu;

public class PluginLoader {
    public static Object loadPlugin(final String name) {
        try {
            final URL[] loadPath = {
                    new URL("jar:file:./plugin/" + name + ".jar!/") };
            try (final URLClassLoader instance = URLClassLoader
                    .newInstance(loadPath)) {
                return instance.loadClass("net.worldwizard.fantastle5." + name)
                        .getConstructor().newInstance();
            }
        } catch (final Exception e) {
            return null;
        }
    }

    public static void addPluginMenus(final Object plugin) {
        if (plugin != null) {
            try {
                final Method menuMethod = plugin.getClass()
                        .getDeclaredMethod("getNewMenus");
                final JMenu[] newMenus = (JMenu[]) menuMethod.invoke(plugin);
                final MenuManager mm = Fantastle5.getApplication()
                        .getMenuManager();
                for (final JMenu newMenu : newMenus) {
                    mm.getMainMenuBar().add(newMenu);
                }
            } catch (final Exception e) {
                e.printStackTrace();
                // Do nothing
            }
        }
    }
}