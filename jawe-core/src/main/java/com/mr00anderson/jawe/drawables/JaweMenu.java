package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweMenu extends AbstractJaweDrawable {

    // This class probably should be a any menu builder, essentially a linked list od drawables

    @Override
    public void draw(JImGui imGui, World world) {

    }

    @Override
    public void dispose() {

    }

    public static final class JaweMenuBuilder {


        private JaweMenuBuilder() {
        }

        public static JaweMenu.JaweMenuBuilder aJaweWindow() {
            return new JaweMenu.JaweMenuBuilder();
        }
    }

}
