package me.sria.srias_flowers.mixin;

import com.mojang.serialization.*;
import net.minecraft.world.gen.treedecorator.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeMixin {
    
    @Invoker("register")
    static <P extends TreeDecorator> TreeDecoratorType<P> invokeRegister(String id, Codec<P> codec) {
        return null;
    }
    
}