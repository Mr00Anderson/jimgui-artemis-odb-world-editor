package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
public class JaweButton implements JaweDrawable {

    public String label;
    public int width;
    public int height;

    /**
     * This will be triggered when selected or deselected
     */
    public ActivationHandler<JaweButton> onActivation = imGuiDrawable -> {};

    public JaweButton() {
    }

    public JaweButton(String label) {
        this.label = label;
    }

    @Override
    public void draw(JImGui imGui) {
        //
        if(imGui.button(label, width, height)){
            onActivation.handle(this);
        }
    }
}
