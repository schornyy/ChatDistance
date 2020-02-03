package de.schornyy.chatdistance.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class MessageBuilder {

    private TextComponent textComponent;

    public MessageBuilder(String text) {
        textComponent = new TextComponent(text);
    }

    public MessageBuilder addHover(String hover) {
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        return this;
    }

    public MessageBuilder addClick(ClickEvent.Action clickEvent, String value) {
        textComponent.setClickEvent(new ClickEvent(clickEvent, value));
        return this;
    }

    public TextComponent getTextComponent() {
        return textComponent;
    }
}
