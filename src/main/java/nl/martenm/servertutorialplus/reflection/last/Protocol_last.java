package nl.martenm.servertutorialplus.reflection.last;

import nl.martenm.servertutorialplus.helpers.Color;
import nl.martenm.servertutorialplus.reflection.IProtocol;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author MartenM
 * @since 27-8-2018.
 */
public class Protocol_last implements IProtocol {

    private static Class<?> dustOptions;
    private static Constructor<?> dustConstructor;

    @Override
    public void playRedstoneParticle(Player player, Location location, Color color) {
        if(dustConstructor == null) {
            try {
                getClassesAndMethods();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Object[] dustParam = {org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()), 1};

        try {
            Object dust = dustConstructor.newInstance(dustParam);
            player.spawnParticle(Particle.DUST, location, 1, dust);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*
        Get all the classes and constructors once.
     */
    private static void getClassesAndMethods() throws NoSuchMethodException, ClassNotFoundException {
        dustOptions = Class.forName("org.bukkit.Particle$DustOptions");
        dustConstructor = dustOptions.getConstructor(org.bukkit.Color.class, float.class);
    }
}

