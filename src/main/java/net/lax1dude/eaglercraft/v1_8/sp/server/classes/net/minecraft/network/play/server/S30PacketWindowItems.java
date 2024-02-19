package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.item.ItemStack;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.network.Packet;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.network.PacketBuffer;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.network.play.INetHandlerPlayClient;

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
public class S30PacketWindowItems implements Packet<INetHandlerPlayClient> {

	static {
		__checkIntegratedContextValid("net/minecraft/network/play/server/S30PacketWindowItems");
	}

	private int windowId;
	private ItemStack[] itemStacks;

	public S30PacketWindowItems() {
	}

	public S30PacketWindowItems(int windowIdIn, List<ItemStack> parList) {
		this.windowId = windowIdIn;
		this.itemStacks = new ItemStack[parList.size()];

		for (int i = 0; i < this.itemStacks.length; ++i) {
			ItemStack itemstack = (ItemStack) parList.get(i);
			this.itemStacks[i] = itemstack == null ? null : itemstack.copy();
		}

	}

	/**+
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.windowId = parPacketBuffer.readUnsignedByte();
		short short1 = parPacketBuffer.readShort();
		this.itemStacks = new ItemStack[short1];

		for (int i = 0; i < short1; ++i) {
			this.itemStacks[i] = parPacketBuffer.readItemStackFromBuffer_server();
		}

	}

	/**+
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.windowId);
		parPacketBuffer.writeShort(this.itemStacks.length);

		for (ItemStack itemstack : this.itemStacks) {
			parPacketBuffer.writeItemStackToBuffer_server(itemstack);
		}

	}

	/**+
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleWindowItems(this);
	}

	public int func_148911_c() {
		return this.windowId;
	}

	public ItemStack[] getItemStacks() {
		return this.itemStacks;
	}
}