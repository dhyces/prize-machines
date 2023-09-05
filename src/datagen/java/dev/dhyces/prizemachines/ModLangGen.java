package dev.dhyces.prizemachines;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Consumer;

public class ModLangGen extends LanguageProvider {
    private final Consumer<ModLangGen> languageProviderConsumer;

    public ModLangGen(PackOutput output, String locale, Consumer<ModLangGen> languageProviderConsumer) {
        super(output, PrizeMachines.MODID, locale);
        this.languageProviderConsumer = languageProviderConsumer;
    }

    @Override
    protected void addTranslations() {
        languageProviderConsumer.accept(this);
    }

    public void add(CreativeModeTab creativeModeTab, String translation) {
        if (!(creativeModeTab.getDisplayName().getContents() instanceof TranslatableContents translatableContents)) {
            throw new IllegalArgumentException("CreativeModeTab must have a translatable title Component.");
        }
        add(translatableContents.getKey(), translation);
    }
}
