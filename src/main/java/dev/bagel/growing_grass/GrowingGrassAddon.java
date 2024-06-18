package dev.bagel.growing_grass;

import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.Block;
import net.minecraft.src.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrowingGrassAddon extends BTWAddon {
    public static double grassGrowingChance;
    public static int grassGrowingTime;
    public static boolean enableGrowingTallGrass;
    public static boolean enableGrowingRoses;
    public static boolean enableGrowingDandelions;
    public static List<Tuple> enabledPlants = new ArrayList<>();

    @Override
    public void preInitialize() {
        this.registerProperty("grassGrowingTime", "20", "Amount of ticks to wait before attempting to grow plants. Default is 100, or once every 5 seconds.");
        this.registerProperty("grassGrowingChance", "0.5", "Chance (in percentage) a piece of grass will grow each second. Default is 0.5% chance.");
        this.registerProperty("enableGrowingTallGrass", "True", "True if tall grass is randomly grown. Default is True");
        this.registerProperty("enableGrowingRoses", "True", "True if roses are randomly grown. Default is True");
        this.registerProperty("enableGrowingDandelions", "True", "True if dandelions are randomly grown. Default is True");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }


    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        grassGrowingTime = Integer.parseInt(propertyValues.get("grassGrowingTime"));
        grassGrowingChance = Double.parseDouble(propertyValues.get("grassGrowingChance"));
        enableGrowingTallGrass = Boolean.parseBoolean(propertyValues.get("enableGrowingTallGrass"));
        enableGrowingRoses = Boolean.parseBoolean(propertyValues.get("enableGrowingRoses"));
        enableGrowingDandelions = Boolean.parseBoolean(propertyValues.get("enableGrowingDandelions"));
        if (enableGrowingTallGrass) {
            enabledPlants.add(new Tuple(Block.tallGrass.blockID, 1));
        }
        if (enableGrowingRoses) {
            enabledPlants.add(new Tuple(Block.plantRed.blockID, 0));
        }
        if (enableGrowingDandelions) {
            enabledPlants.add(new Tuple(Block.plantYellow.blockID, 0));
        }
    }

    @Override
    public String getName() {
        return "Growing Grass Addon";
    }
}