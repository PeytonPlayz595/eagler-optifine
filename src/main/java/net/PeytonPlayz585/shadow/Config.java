package net.PeytonPlayz585.shadow;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.PeytonPlayz585.shadow.reflections.Reflector;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.ResourcePackRepository.Entry;

import net.lax1dude.eaglercraft.v1_8.internal.vfs.SYS;

public class Config {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static DefaultResourcePack defaultResourcePackLazy = null;
	
	//Chunk Loading Fix
	public static boolean chunkFix = true;
	public static boolean chunkFixNether = false;
	public static boolean chunkFixEnd = false;
	public static WorldClient worldClient = null;
	public static boolean waterOpacityChanged = false;
	
	public static boolean isAnimatedWater() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedWater != 2;
    }
	
	public static boolean isAnimatedPortal() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedPortal;
    }

    public static boolean isAnimatedLava() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedLava != 2;
    }
    
    public static boolean isAnimatedFire() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedFire;
    }

    public static boolean isAnimatedRedstone() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedRedstone;
    }

    public static boolean isAnimatedExplosion() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedExplosion;
    }

    public static boolean isAnimatedFlame() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedFlame;
    }

    public static boolean isAnimatedSmoke() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedSmoke;
    }

    public static boolean isVoidParticles() {
        return Minecraft.getMinecraft().gameSettings.ofVoidParticles;
    }

    public static boolean isWaterParticles() {
        return Minecraft.getMinecraft().gameSettings.ofWaterParticles;
    }
    
    public static boolean isPortalParticles() {
        return Minecraft.getMinecraft().gameSettings.ofPortalParticles;
    }

    public static boolean isPotionParticles() {
        return Minecraft.getMinecraft().gameSettings.ofPotionParticles;
    }

    public static boolean isFireworkParticles() {
        return Minecraft.getMinecraft().gameSettings.ofFireworkParticles;
    }
    
    public static boolean isAnimatedTerrain() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedTerrain;
    }

    public static boolean isAnimatedTextures() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedTextures;
    }
    
    public static boolean isDrippingWaterLava() {
        return Minecraft.getMinecraft().gameSettings.ofDrippingWaterLava;
    }
    
    public static float getAmbientOcclusionLevel() {
        return isShaders() ? 1.0F : Minecraft.getMinecraft().gameSettings.ofAoLevel;
    }
    
    public static boolean isCustomSky() {
        return Minecraft.getMinecraft().gameSettings.ofCustomSky;
    }

    public static boolean isClearWater() {
        return Minecraft.getMinecraft().gameSettings.ofClearWater;
    }

    public static boolean isBetterGrass() {
        return Minecraft.getMinecraft().gameSettings.ofBetterGrass != 3;
    }

    public static boolean isBetterGrassFancy() {
        return Minecraft.getMinecraft().gameSettings.ofBetterGrass == 2;
    }

    public static boolean isBetterSnow() {
        return Minecraft.getMinecraft().gameSettings.ofBetterSnow;
    }
    
    public static boolean isFogFancy() {
        return Minecraft.getMinecraft().gameSettings.ofFogType == 2;
    }

    public static boolean isFogFast() {
        return Minecraft.getMinecraft().gameSettings.ofFogType == 1;
    }

    public static boolean isFogOff() {
        return Minecraft.getMinecraft().gameSettings.ofFogType == 3;
    }
    
    public static float getFogStart() {
        return Minecraft.getMinecraft().gameSettings.ofFogStart;
    }
    
    public static int getUpdatesPerFrame() {
        return Minecraft.getMinecraft().gameSettings.ofChunkUpdates;
    }
    
    public static boolean isCloudsFancy() {
        return Minecraft.getMinecraft().gameSettings.ofClouds != 0 ? Minecraft.getMinecraft().gameSettings.ofClouds == 2 : Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

    public static boolean isCloudsOff() {
        return Minecraft.getMinecraft().gameSettings.ofClouds != 0 ? Minecraft.getMinecraft().gameSettings.ofClouds == 3 : false;
    }
    
    public static boolean isRainFancy() {
        return Minecraft.getMinecraft().gameSettings.ofRain == 0 ? Minecraft.getMinecraft().gameSettings.fancyGraphics : Minecraft.getMinecraft().gameSettings.ofRain == 2;
    }

    public static boolean isRainOff() {
        return Minecraft.getMinecraft().gameSettings.ofRain == 3;
    }
    
    public static boolean isRainSplash() {
        return Minecraft.getMinecraft().gameSettings.ofRainSplash;
    }
    
    public static boolean isTreesFancy() {
        return Minecraft.getMinecraft().gameSettings.ofTrees == 0 ? Minecraft.getMinecraft().gameSettings.fancyGraphics : Minecraft.getMinecraft().gameSettings.ofTrees != 1;
    }

    public static boolean isTreesSmart() {
        return Minecraft.getMinecraft().gameSettings.ofTrees == 4;
    }

    public static boolean isCullFacesLeaves() {
        return Minecraft.getMinecraft().gameSettings.ofTrees == 0 ? !Minecraft.getMinecraft().gameSettings.fancyGraphics : Minecraft.getMinecraft().gameSettings.ofTrees == 4;
    }
    
    public static boolean isSkyEnabled() {
        return Minecraft.getMinecraft().gameSettings.ofSky;
    }
    
    public static boolean isSunMoonEnabled() {
        return Minecraft.getMinecraft().gameSettings.ofSunMoon;
    }
    
    public static boolean isStarsEnabled() {
        return Minecraft.getMinecraft().gameSettings.ofStars;
    }
    
    public static boolean isShowCapes() {
        return Minecraft.getMinecraft().gameSettings.ofShowCapes;
    }
    
    public static boolean isTranslucentBlocksFancy() {
        return Minecraft.getMinecraft().gameSettings.ofTranslucentBlocks == 0 ? Minecraft.getMinecraft().gameSettings.fancyGraphics : Minecraft.getMinecraft().gameSettings.ofTranslucentBlocks == 2;
    }
    
    public static boolean isDroppedItemsFancy() {
        return Minecraft.getMinecraft().gameSettings.ofDroppedItems == 0 ? Minecraft.getMinecraft().gameSettings.fancyGraphics : Minecraft.getMinecraft().gameSettings.ofDroppedItems == 2;
    }
    
    public static boolean isVignetteEnabled() {
        return Minecraft.getMinecraft().gameSettings.ofVignette == 0 ? Minecraft.getMinecraft().gameSettings.fancyGraphics : Minecraft.getMinecraft().gameSettings.ofVignette == 2;
    }
    
    public static boolean isDynamicFov() {
        return Minecraft.getMinecraft().gameSettings.ofDynamicFov;
    }

    public static boolean isPasswordHidden() {
        return Minecraft.getMinecraft().gameSettings.hidePassword;
    }

    public static int getMipmapType() {
        switch (Minecraft.getMinecraft().gameSettings.ofMipmapType) {
            case 0:
                return 9986;

            case 1:
                return 9986;

            case 2:
                return 9985;

            case 3:
                return 9987;

            default:
                return 9986;
        }
    }

    public static boolean isCustomFonts() {
        return Minecraft.getMinecraft().gameSettings.ofCustomFonts;
    }
    
    public static boolean isCustomColors() {
        return Minecraft.getMinecraft().gameSettings.ofCustomColors;
    }
    
    public static boolean isSwampColors() {
        return Minecraft.getMinecraft().gameSettings.ofSwampColors;
    }
    
    public static boolean isSmoothBiomes() {
        return Minecraft.getMinecraft().gameSettings.ofSmoothBiomes;
    }

    public static boolean isCustomItems() {
        return Minecraft.getMinecraft().gameSettings.ofCustomItems;
    }

	public static int limit(int p_limit_0_, int p_limit_1_, int p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static float limit(float p_limit_0_, float p_limit_1_, float p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static double limit(double p_limit_0_, double p_limit_2_, double p_limit_4_) {
        return p_limit_0_ < p_limit_2_ ? p_limit_2_ : (p_limit_0_ > p_limit_4_ ? p_limit_4_ : p_limit_0_);
    }

    public static float limitTo1(float p_limitTo1_0_) {
        return p_limitTo1_0_ < 0.0F ? 0.0F : (p_limitTo1_0_ > 1.0F ? 1.0F : p_limitTo1_0_);
    }
    
    public static Object[] addObjectToArray(Object[] p_addObjectToArray_0_, Object p_addObjectToArray_1_) {
        if (p_addObjectToArray_0_ == null) {
            throw new NullPointerException("The given array is NULL");
        } else {
            int i = p_addObjectToArray_0_.length;
            int j = i + 1;
            Object[] aobject = (Object[])((Object[])Array.newInstance(p_addObjectToArray_0_.getClass().getComponentType(), j));
            System.arraycopy(p_addObjectToArray_0_, 0, aobject, 0, i);
            aobject[i] = p_addObjectToArray_1_;
            return aobject;
        }
    }

    public static Object[] addObjectToArray(Object[] p_addObjectToArray_0_, Object p_addObjectToArray_1_, int p_addObjectToArray_2_) {
        List<Object> list = new ArrayList<Object>(Arrays.asList(p_addObjectToArray_0_));
        list.add(p_addObjectToArray_2_, p_addObjectToArray_1_);
        Object[] aobject = (Object[])((Object[])Array.newInstance(p_addObjectToArray_0_.getClass().getComponentType(), list.size()));
        return list.toArray(aobject);
    }

    public static Object[] addObjectsToArray(Object[] p_addObjectsToArray_0_, Object[] p_addObjectsToArray_1_) {
        if (p_addObjectsToArray_0_ == null) {
            throw new NullPointerException("The given array is NULL");
        } else if (p_addObjectsToArray_1_.length == 0) {
            return p_addObjectsToArray_0_;
        } else {
            int i = p_addObjectsToArray_0_.length;
            int j = i + p_addObjectsToArray_1_.length;
            Object[] aobject = (Object[])((Object[])Array.newInstance(p_addObjectsToArray_0_.getClass().getComponentType(), j));
            System.arraycopy(p_addObjectsToArray_0_, 0, aobject, 0, i);
            System.arraycopy(p_addObjectsToArray_1_, 0, aobject, i, p_addObjectsToArray_1_.length);
            return aobject;
        }
    }
    
    public static int[] addIntToArray(int[] p_addIntToArray_0_, int p_addIntToArray_1_) {
        return addIntsToArray(p_addIntToArray_0_, new int[] {p_addIntToArray_1_});
    }
    
    public static int[] addIntsToArray(int[] p_addIntsToArray_0_, int[] p_addIntsToArray_1_) {
        if (p_addIntsToArray_0_ != null && p_addIntsToArray_1_ != null) {
            int i = p_addIntsToArray_0_.length;
            int j = i + p_addIntsToArray_1_.length;
            int[] aint = new int[j];
            System.arraycopy(p_addIntsToArray_0_, 0, aint, 0, i);

            for (int k = 0; k < p_addIntsToArray_1_.length; ++k) {
                aint[k + i] = p_addIntsToArray_1_[k];
            }

            return aint;
        } else {
            throw new NullPointerException("The given array is NULL");
        }
    }
    
    public static String arrayToString(Object[] p_arrayToString_0_) {
        if (p_arrayToString_0_ == null) {
            return "";
        } else {
            StringBuffer stringbuffer = new StringBuffer(p_arrayToString_0_.length * 5);

            for (int i = 0; i < p_arrayToString_0_.length; ++i) {
                Object object = p_arrayToString_0_[i];

                if (i > 0) {
                    stringbuffer.append(", ");
                }

                stringbuffer.append(String.valueOf(object));
            }

            return stringbuffer.toString();
        }
    }

    public static String arrayToString(int[] p_arrayToString_0_) {
        if (p_arrayToString_0_ == null) {
            return "";
        } else {
            StringBuffer stringbuffer = new StringBuffer(p_arrayToString_0_.length * 5);

            for (int i = 0; i < p_arrayToString_0_.length; ++i) {
                int j = p_arrayToString_0_[i];

                if (i > 0) {
                    stringbuffer.append(", ");
                }

                stringbuffer.append(String.valueOf(j));
            }

            return stringbuffer.toString();
        }
    }

    public static boolean parseBoolean(String p_parseBoolean_0_, boolean p_parseBoolean_1_) {
        try {
            if (p_parseBoolean_0_ == null) {
                return p_parseBoolean_1_;
            } else {
                p_parseBoolean_0_ = p_parseBoolean_0_.trim();
                return Boolean.parseBoolean(p_parseBoolean_0_);
            }
        } catch (NumberFormatException var3) {
            return p_parseBoolean_1_;
        }
    }

    public static String[] tokenize(String p_tokenize_0_, String p_tokenize_1_) {
        StringTokenizer stringtokenizer = new StringTokenizer(p_tokenize_0_, p_tokenize_1_);
        List<String> list = new ArrayList<String>();

        while (stringtokenizer.hasMoreTokens()) {
            String s = stringtokenizer.nextToken();
            list.add(s);
        }

        String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
        return astring;
    }
    
    public static int parseInt(String p_parseInt_0_, int p_parseInt_1_) {
        try {
            if (p_parseInt_0_ == null) {
                return p_parseInt_1_;
            } else {
                p_parseInt_0_ = p_parseInt_0_.trim();
                return Integer.parseInt(p_parseInt_0_);
            }
        } catch (NumberFormatException var3) {
            return p_parseInt_1_;
        }
    }
    
    public static float parseFloat(String p_parseFloat_0_, float p_parseFloat_1_) {
        try {
            if (p_parseFloat_0_ == null) {
                return p_parseFloat_1_;
            } else {
                p_parseFloat_0_ = p_parseFloat_0_.trim();
                return Float.parseFloat(p_parseFloat_0_);
            }
        } catch (NumberFormatException var3) {
            return p_parseFloat_1_;
        }
    }
    
    public static void dbg(String p_dbg_0_) {
        LOGGER.info("[Shadow Client] " + p_dbg_0_);
    }

    public static void warn(String p_warn_0_) {
        LOGGER.warn("[Shadow Client] " + p_warn_0_);
    }

    public static void error(String p_error_0_) {
        LOGGER.error("[Shadow Client] " + p_error_0_);
    }
    
    public static void log(String p_log_0_) {
        dbg(p_log_0_);
    }
    
    public static boolean hasResource(ResourceLocation p_hasResource_0_) {
        for (IResourcePack resourcePack : getResourcePacks()) {
            if (resourcePack.resourceExists(p_hasResource_0_)) {
                return true;
            }
        }

        return false;
    }
    
    public static IResourcePack getDefiningResourcePack(ResourceLocation p_getDefiningResourcePack_0_) {
        ResourcePackRepository resourcepackrepository = Minecraft.getMinecraft().getResourcePackRepository();
        IResourcePack iresourcepack = resourcepackrepository.getResourcePackInstance();

        if (iresourcepack != null && iresourcepack.resourceExists(p_getDefiningResourcePack_0_)) {
            return iresourcepack;
        } else {
            List<ResourcePackRepository.Entry> list = (List)Reflector.getFieldValue(resourcepackrepository, Reflector.ResourcePackRepository_repositoryEntries);

            if (list != null) {
                for (int i = list.size() - 1; i >= 0; --i) {
                    ResourcePackRepository.Entry resourcepackrepository$entry = (ResourcePackRepository.Entry)list.get(i);
                    IResourcePack iresourcepack1 = resourcepackrepository$entry.getResourcePack();

                    if (iresourcepack1.resourceExists(p_getDefiningResourcePack_0_)) {
                        return iresourcepack1;
                    }
                }
            }

            return getDefaultResourcePack().resourceExists(p_getDefiningResourcePack_0_) ? getDefaultResourcePack() : null;
        }
    }
    
    public static DefaultResourcePack getDefaultResourcePack() {
        if (defaultResourcePackLazy == null) {
            Minecraft minecraft = Minecraft.getMinecraft();
            defaultResourcePackLazy = (DefaultResourcePack)Reflector.getFieldValue(minecraft, Reflector.Minecraft_defaultResourcePack);

            if (defaultResourcePackLazy == null) {
                ResourcePackRepository resourcepackrepository = minecraft.getResourcePackRepository();

                if (resourcepackrepository != null) {
                    defaultResourcePackLazy = (DefaultResourcePack)resourcepackrepository.rprDefaultResourcePack;
                }
            }
        }

        return defaultResourcePackLazy;
    }
    
    public static IResourcePack[] getResourcePacks() {
        ResourcePackRepository resourcepackrepository = Minecraft.getMinecraft().getResourcePackRepository();
        List list = resourcepackrepository.getRepositoryEntries();
        List list1 = new ArrayList();

        for (Object resourcepackrepository$entry : list) {
            list1.add(((Entry) resourcepackrepository$entry).getResourcePack());
        }

        if (resourcepackrepository.getResourcePackInstance() != null) {
            list1.add(resourcepackrepository.getResourcePackInstance());
        }

        IResourcePack[] airesourcepack = (IResourcePack[])((IResourcePack[])list1.toArray(new IResourcePack[list1.size()]));
        return airesourcepack;
    }
    
    public static InputStream getResourceStream(ResourceLocation p_getResourceStream_1_) throws IOException {
        IResourceManager p_getResourceStream_0_ = Minecraft.getMinecraft().getResourceManager();
    
        IResource defaultResource = p_getResourceStream_0_.getResource(p_getResourceStream_1_);
        if (defaultResource != null) {
            return defaultResource.getInputStream();
        }
    
        Set<String> resourceDomains = p_getResourceStream_0_.getResourceDomains();
        for (String resourceDomain : resourceDomains) {
            IResource resource = p_getResourceStream_0_.getResource(new ResourceLocation(resourceDomain, p_getResourceStream_1_.getResourcePath()));
            if (resource != null) {
                return resource.getInputStream();
            }
        }
    
        return null;
    }
    
    public static int intHash(int p_intHash_0_) {
        p_intHash_0_ = p_intHash_0_ ^ 61 ^ p_intHash_0_ >> 16;
        p_intHash_0_ = p_intHash_0_ + (p_intHash_0_ << 3);
        p_intHash_0_ = p_intHash_0_ ^ p_intHash_0_ >> 4;
        p_intHash_0_ = p_intHash_0_ * 668265261;
        p_intHash_0_ = p_intHash_0_ ^ p_intHash_0_ >> 15;
        return p_intHash_0_;
    }
    
    public static boolean isShaders() {
    	return Minecraft.getMinecraft().gameSettings.shaders;
    }

	public static GameSettings getGameSettings() {
		return Minecraft.getMinecraft().gameSettings;
	}
	
	public static TextureManager getTextureManager() {
        return Minecraft.getMinecraft().getTextureManager();
    }
	
	public static void fixChunkLoading() {
		if (chunkFix) {
            if (worldClient != null) {
                Minecraft.getMinecraft().renderGlobal.loadRenderers();
                Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(worldClient);
                worldClient.updateBlocks();
                chunkFix = false;
            }
        }

        if (chunkFixNether) {
            if (worldClient != null) {
                Minecraft.getMinecraft().renderGlobal.loadRenderers();
                Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(worldClient);
                worldClient.updateBlocks();
                chunkFixNether = false;
            }
        }

        if (chunkFixEnd) {
            if (worldClient != null) {
                Minecraft.getMinecraft().renderGlobal.loadRenderers();
                Minecraft.getMinecraft().renderGlobal.setWorldAndLoadRenderers(worldClient);
                worldClient.updateBlocks();
                chunkFixEnd = false;
            }
        }
	}
	
	public static boolean equals(Object p_equals_0_, Object p_equals_1_) {
        return p_equals_0_ == p_equals_1_ ? true : (p_equals_0_ == null ? false : p_equals_0_.equals(p_equals_1_));
    }
	
	public static boolean isDynamicLights() {
        return Minecraft.getMinecraft().gameSettings.ofDynamicLights != 3;
    }

    public static boolean isDynamicLightsFast() {
        return Minecraft.getMinecraft().gameSettings.ofDynamicLights == 1;
    }
    
    public static boolean isDynamicHandLight() {
        return !isDynamicLights() ? false : true;
    }

    public static ModelManager getModelManager() {
        return Minecraft.getMinecraft().getRenderItem().modelManager;
    }
}
