package net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.entity.ai.attributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.entity.ai.attributes.AttributeModifier;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.entity.ai.attributes.IAttribute;
import net.lax1dude.eaglercraft.v1_8.sp.server.classes.net.minecraft.entity.ai.attributes.IAttributeInstance;

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
public class ModifiableAttributeInstance implements IAttributeInstance {

	static {
		__checkIntegratedContextValid("net/minecraft/entity/ai/attributes/ModifiableAttributeInstance");
	}

	private final BaseAttributeMap attributeMap;
	private final IAttribute genericAttribute;
	private final Map<Integer, Set<AttributeModifier>> mapByOperation = Maps.newHashMap();
	private final Map<String, Set<AttributeModifier>> mapByName = Maps.newHashMap();
	private final Map<EaglercraftUUID, AttributeModifier> mapByUUID = Maps.newHashMap();
	private double baseValue;
	private boolean needsUpdate = true;
	private double cachedValue;

	public ModifiableAttributeInstance(BaseAttributeMap attributeMapIn, IAttribute genericAttributeIn) {
		this.attributeMap = attributeMapIn;
		this.genericAttribute = genericAttributeIn;
		this.baseValue = genericAttributeIn.getDefaultValue();

		for (int i = 0; i < 3; ++i) {
			this.mapByOperation.put(Integer.valueOf(i), Sets.newHashSet());
		}

	}

	/**+
	 * Get the Attribute this is an instance of
	 */
	public IAttribute getAttribute() {
		return this.genericAttribute;
	}

	public double getBaseValue() {
		return this.baseValue;
	}

	public void setBaseValue(double d0) {
		if (d0 != this.getBaseValue()) {
			this.baseValue = d0;
			this.flagForUpdate();
		}
	}

	public Collection<AttributeModifier> getModifiersByOperation(int i) {
		return (Collection) this.mapByOperation.get(Integer.valueOf(i));
	}

	public Collection<AttributeModifier> func_111122_c() {
		HashSet hashset = Sets.newHashSet();

		for (int i = 0; i < 3; ++i) {
			hashset.addAll(this.getModifiersByOperation(i));
		}

		return hashset;
	}

	/**+
	 * Returns attribute modifier, if any, by the given UUID
	 */
	public AttributeModifier getModifier(EaglercraftUUID uuid) {
		return (AttributeModifier) this.mapByUUID.get(uuid);
	}

	public boolean hasModifier(AttributeModifier attributemodifier) {
		return this.mapByUUID.get(attributemodifier.getID()) != null;
	}

	public void applyModifier(AttributeModifier attributemodifier) {
		if (this.getModifier(attributemodifier.getID()) != null) {
			throw new IllegalArgumentException("Modifier is already applied on this attribute!");
		} else {
			Object object = (Set) this.mapByName.get(attributemodifier.getName());
			if (object == null) {
				object = Sets.newHashSet();
				this.mapByName.put(attributemodifier.getName(), (Set<AttributeModifier>) object);
			}

			((Set) this.mapByOperation.get(Integer.valueOf(attributemodifier.getOperation()))).add(attributemodifier);
			((Set) object).add(attributemodifier);
			this.mapByUUID.put(attributemodifier.getID(), attributemodifier);
			this.flagForUpdate();
		}
	}

	protected void flagForUpdate() {
		this.needsUpdate = true;
		this.attributeMap.func_180794_a(this);
	}

	public void removeModifier(AttributeModifier attributemodifier) {
		for (int i = 0; i < 3; ++i) {
			Set set = (Set) this.mapByOperation.get(Integer.valueOf(i));
			set.remove(attributemodifier);
		}

		Set set1 = (Set) this.mapByName.get(attributemodifier.getName());
		if (set1 != null) {
			set1.remove(attributemodifier);
			if (set1.isEmpty()) {
				this.mapByName.remove(attributemodifier.getName());
			}
		}

		this.mapByUUID.remove(attributemodifier.getID());
		this.flagForUpdate();
	}

	public void removeAllModifiers() {
		Collection collection = this.func_111122_c();
		if (collection != null) {
			for (AttributeModifier attributemodifier : (Collection<AttributeModifier>) Lists.newArrayList(collection)) {
				this.removeModifier(attributemodifier);
			}
		}
	}

	public double getAttributeValue() {
		if (this.needsUpdate) {
			this.cachedValue = this.computeValue();
			this.needsUpdate = false;
		}

		return this.cachedValue;
	}

	private double computeValue() {
		double d0 = this.getBaseValue();

		for (AttributeModifier attributemodifier : this.func_180375_b(0)) {
			d0 += attributemodifier.getAmount();
		}

		double d1 = d0;

		for (AttributeModifier attributemodifier1 : this.func_180375_b(1)) {
			d1 += d0 * attributemodifier1.getAmount();
		}

		for (AttributeModifier attributemodifier2 : this.func_180375_b(2)) {
			d1 *= 1.0D + attributemodifier2.getAmount();
		}

		return this.genericAttribute.clampValue(d1);
	}

	private Collection<AttributeModifier> func_180375_b(int parInt1) {
		HashSet hashset = Sets.newHashSet(this.getModifiersByOperation(parInt1));

		for (IAttribute iattribute = this.genericAttribute.func_180372_d(); iattribute != null; iattribute = iattribute
				.func_180372_d()) {
			IAttributeInstance iattributeinstance = this.attributeMap.getAttributeInstance(iattribute);
			if (iattributeinstance != null) {
				hashset.addAll(iattributeinstance.getModifiersByOperation(parInt1));
			}
		}

		return hashset;
	}
}