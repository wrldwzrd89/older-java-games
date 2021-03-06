/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.resourcemanagers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.widgetwarren.generic.MazeObject;

public class ObjectImageManager {
    private static final String DEFAULT_LOAD_PATH = "/com/puttysoftware/widgetwarren/resources/images/objects/";
    private static String LOAD_PATH = ObjectImageManager.DEFAULT_LOAD_PATH;
    private static Class<?> LOAD_CLASS = ObjectImageManager.class;
    private static final Color TRANSPARENT = new Color(200, 100, 100);
    private static Color REPLACE = new Color(223, 223, 223);

    public static BufferedImageIcon getTransformedImage(final MazeObject obj,
            final boolean game) {
        try {
            final BufferedImageIcon icon = ObjectImageCache
                    .getCachedObjectImage(obj, game);
            if (icon != null) {
                final BufferedImageIcon result = new BufferedImageIcon(icon);
                for (int x = 0; x < ImageConstants.getScaledImageSize(); x++) {
                    for (int y = 0; y < ImageConstants
                            .getScaledImageSize(); y++) {
                        final int pixel = icon.getRGB(x, y);
                        final Color c = new Color(pixel);
                        if (c.equals(ObjectImageManager.TRANSPARENT)) {
                            result.setRGB(x, y,
                                    ObjectImageManager.REPLACE.getRGB());
                        }
                    }
                }
                return result;
            } else {
                return null;
            }
        } catch (final NullPointerException np) {
            return null;
        } catch (final IllegalArgumentException ia) {
            return null;
        }
    }

    public static BufferedImageIcon getObjectImage(final MazeObject obj,
            final boolean game) {
        // Get it from the cache
        return ObjectImageCache.getCachedObjectImage(obj, game);
    }

    static BufferedImageIcon getUncachedObjectImage(final MazeObject obj,
            final boolean game) {
        try {
            String name;
            if (game) {
                name = obj.gameRenderHook().getGameName();
            } else {
                name = obj.getName();
            }
            final String normalName = ObjectImageManager.normalizeName(name);
            final URL url = ObjectImageManager.LOAD_CLASS.getResource(
                    ObjectImageManager.LOAD_PATH + normalName + ".png");
            final BufferedImage image = ImageIO.read(url);
            final BufferedImageIcon icon = new BufferedImageIcon(image);
            return ImageScaleManager.getScaledImage(icon);
        } catch (final IOException ie) {
            return null;
        } catch (final NullPointerException np) {
            return null;
        } catch (final IllegalArgumentException ia) {
            return null;
        }
    }

    private static String normalizeName(final String name) {
        final StringBuilder sb = new StringBuilder(name);
        for (int x = 0; x < sb.length(); x++) {
            if (!Character.isLetter(sb.charAt(x))
                    && !Character.isDigit(sb.charAt(x))) {
                sb.setCharAt(x, '_');
            }
        }
        return sb.toString().toLowerCase();
    }
}
