package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

/**
 * This should represent the smallest unit translated to JImGui
 * This should map to JImGui#methodNameHere when created
 */
public interface JaweDrawable {
    void draw(JImGui imGui, World world);
}