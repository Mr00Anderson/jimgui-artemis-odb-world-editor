package com.mr00anderson.editor.atremis.components;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import org.ice1000.jimgui.JImGui;

public class MainMenuBarComponent implements JImGuiDrawable {

    // Link List of Menu Items or array


    @Override
    public void draw(JImGui imGui, World world) {
        if(imGui.beginMainMenuBar()){

        }
    }

    @Override
    public void dispose() {

    }

}