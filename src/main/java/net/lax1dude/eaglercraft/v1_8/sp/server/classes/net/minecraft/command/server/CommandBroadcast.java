package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.command.server;

import java.util.List;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.command.CommandBase;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.command.CommandException;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.command.ICommandSender;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.command.WrongUsageException;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.server.MinecraftServer;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.BlockPos;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.ChatComponentText;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.ChatComponentTranslation;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.IChatComponent;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util.StringUtils;

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
public class CommandBroadcast extends CommandBase {

	static {
		__checkIntegratedContextValid("net/minecraft/command/server/CommandBroadcast");
	}

	/**+
	 * Gets the name of the command
	 */
	public String getCommandName() {
		return "say";
	}

	/**+
	 * Return the required permission level for this command.
	 */
	public int getRequiredPermissionLevel() {
		return 1;
	}

	/**+
	 * Gets the usage string for the command.
	 */
	public String getCommandUsage(ICommandSender var1) {
		return "commands.say.usage";
	}

	/**+
	 * Callback when the command is invoked
	 */
	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length > 0 && parArrayOfString[0].length() > 0) {
			IChatComponent ichatcomponent = getChatComponentFromNthArg(parICommandSender, parArrayOfString, 0, true);
			if (MinecraftServer.getServer().worldServers[0].getWorldInfo().getGameRulesInstance()
					.getBoolean("colorCodes")) {
				ichatcomponent = new ChatComponentText(
						StringUtils.translateControlCodesAlternate(ichatcomponent.getFormattedText()));
			}
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation(
					"chat.type.announcement", new Object[] { parICommandSender.getDisplayName(), ichatcomponent }));
		} else {
			throw new WrongUsageException("commands.say.usage", new Object[0]);
		}
	}

	/**+
	 * Return a list of options when the user types TAB
	 */
	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length >= 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: null;
	}
}