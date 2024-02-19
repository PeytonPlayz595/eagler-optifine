package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.util;

import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

import static net.lax1dude.eaglercraft.v1_8.sp.server.classes.ContextUtil.__checkIntegratedContextValid;

import net.lax1dude.eaglercraft.v1_8.futures.ExecutionException;
import net.lax1dude.eaglercraft.v1_8.futures.FutureTask;

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
public class Util {

	static {
		__checkIntegratedContextValid("net/minecraft/util/Util");
	}

	public static Util.EnumOS getOSType() {
		String s = System.getProperty("os.name").toLowerCase();
		return s.contains("win") ? Util.EnumOS.WINDOWS
				: (s.contains("mac") ? Util.EnumOS.OSX
						: (s.contains("solaris") ? Util.EnumOS.SOLARIS
								: (s.contains("sunos") ? Util.EnumOS.SOLARIS
										: (s.contains("linux") ? Util.EnumOS.LINUX
												: (s.contains("unix") ? Util.EnumOS.LINUX : Util.EnumOS.UNKNOWN)))));
	}

	public static <V> V func_181617_a(FutureTask<V> parFutureTask, Logger parLogger) {
		try {
			parFutureTask.run();
			return (V) parFutureTask.get();
		} catch (ExecutionException executionexception) {
			parLogger.fatal("Error executing task", executionexception);
		} catch (InterruptedException interruptedexception) {
			parLogger.fatal("Error executing task", interruptedexception);
		}

		return (V) null;
	}

	public static enum EnumOS {
		LINUX, SOLARIS, WINDOWS, OSX, UNKNOWN;
	}
}