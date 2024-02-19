package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.network;

import static net.lax1dude.eaglercraft.v1_8.sp.server.classes.ContextUtil.__checkIntegratedContextValid;

import java.io.IOException;

import net.lax1dude.eaglercraft.v1_8.netty.ByteBuf;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.item.Item;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.BlockPos;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.IChatComponent;

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
public class PacketBuffer extends net.minecraft.network.PacketBuffer {

	static {
		__checkIntegratedContextValid("net/minecraft/network/PacketBuffer");
	}

	public PacketBuffer(ByteBuf wrapped) {
		super(wrapped);
	}

	public BlockPos readBlockPos_server() {
		return BlockPos.fromLong(this.readLong());
	}

	public void writeBlockPos_server(BlockPos pos) {
		this.writeLong(pos.toLong());
	}

	public IChatComponent readChatComponent_server() throws IOException {
		return IChatComponent.Serializer.jsonToComponent(this.readStringFromBuffer(32767));
	}

	public void writeChatComponent_server(IChatComponent component) throws IOException {
		this.writeString(IChatComponent.Serializer.componentToJson(component));
	}

	public void writeItemStackToBuffer_server(ItemStack stack) {
		if (stack == null) {
			this.writeShort(-1);
		} else {
			this.writeShort(Item.getIdFromItem(stack.getItem()));
			this.writeByte(stack.stackSize);
			this.writeShort(stack.getMetadata());
			NBTTagCompound nbttagcompound = null;
			if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
				nbttagcompound = stack.getTagCompound();
			}

			this.writeNBTTagCompoundToBuffer(nbttagcompound);
		}

	}

	public ItemStack readItemStackFromBuffer_server() throws IOException {
		ItemStack itemstack = null;
		short short1 = this.readShort();
		if (short1 >= 0) {
			byte b0 = this.readByte();
			short short2 = this.readShort();
			itemstack = new ItemStack(Item.getItemById(short1), b0, short2);
			itemstack.setTagCompound(this.readNBTTagCompoundFromBuffer());
		}

		return itemstack;
	}

}