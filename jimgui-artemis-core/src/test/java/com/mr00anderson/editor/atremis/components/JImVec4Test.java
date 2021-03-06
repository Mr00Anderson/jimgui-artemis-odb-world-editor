package com.mr00anderson.editor.atremis.components;

import org.ice1000.jimgui.JImVec4;
import org.ice1000.jimgui.util.JniLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JImVec4Test {
	private static float randX;
	private static float randY;
	private static float randZ;
	private static float randW;
	private static JImVec4 vec1;
	private static JImVec4 vec2;

	@BeforeAll
	public static void loadJni() {
		JniLoader.load();
		randX = (float) Math.random();
		randY = (float) Math.random();
		randZ = (float) Math.random();
		randW = (float) Math.random();
		vec1 = new JImVec4();
		vec2 = new JImVec4(randX, randY, randZ, randW);
	}

	@Test
	public void getW() {
		assertEquals(randW, vec2.getW(), 0.0001f);
		assertEquals(0, vec1.getW(), 0.0001f);
	}

	@Test
	public void getX() {
		assertEquals(randX, vec2.getX(), 0.0001f);
		assertEquals(0, vec1.getX(), 0.0001f);
	}

	@Test
	public void getY() {
		assertEquals(randY, vec2.getY(), 0.0001f);
		assertEquals(0, vec1.getY(), 0.0001f);
	}

	@Test
	public void getZ() {
		assertEquals(randZ, vec2.getZ(), 0.0001f);
		assertEquals(0, vec1.getZ(), 0.0001f);
	}

	@AfterAll
	public static void deallocate() {
		if (vec1 != null) {
			vec1.close();
			vec2.close();
		}
	}
}