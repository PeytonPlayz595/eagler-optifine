package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.gen.layer;

import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.biome.BiomeGenBase;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.gen.layer.GenLayer;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.world.gen.layer.IntCache;

import static net.lax1dude.eaglercraft.v1_8.sp.server.classes.ContextUtil.__checkIntegratedContextValid;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files (c) 2022-2024 lax1dude, ayunami2000. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class GenLayerRiver extends GenLayer {

	static {
		__checkIntegratedContextValid("net/minecraft/world/gen/layer/GenLayerRiver");
	}

	public GenLayerRiver(long parLong1, GenLayer parGenLayer) {
		super(parLong1);
		super.parent = parGenLayer;
	}

	/**+
	 * Returns a list of integer values generated by this layer.
	 * These may be interpreted as temperatures, rainfall amounts,
	 * or biomeList[] indices based on the particular GenLayer
	 * subclass.
	 */
	public int[] getInts(int i, int j, int k, int l) {
		int i1 = i - 1;
		int j1 = j - 1;
		int k1 = k + 2;
		int l1 = l + 2;
		int[] aint = this.parent.getInts(i1, j1, k1, l1);
		int[] aint1 = IntCache.getIntCache(k * l);

		for (int i2 = 0; i2 < l; ++i2) {
			for (int j2 = 0; j2 < k; ++j2) {
				int k2 = this.func_151630_c(aint[j2 + 0 + (i2 + 1) * k1]);
				int l2 = this.func_151630_c(aint[j2 + 2 + (i2 + 1) * k1]);
				int i3 = this.func_151630_c(aint[j2 + 1 + (i2 + 0) * k1]);
				int j3 = this.func_151630_c(aint[j2 + 1 + (i2 + 2) * k1]);
				int k3 = this.func_151630_c(aint[j2 + 1 + (i2 + 1) * k1]);
				if (k3 == k2 && k3 == i3 && k3 == l2 && k3 == j3) {
					aint1[j2 + i2 * k] = -1;
				} else {
					aint1[j2 + i2 * k] = BiomeGenBase.river.biomeID;
				}
			}
		}

		return aint1;
	}

	private int func_151630_c(int parInt1) {
		return parInt1 >= 2 ? 2 + (parInt1 & 1) : parInt1;
	}
}