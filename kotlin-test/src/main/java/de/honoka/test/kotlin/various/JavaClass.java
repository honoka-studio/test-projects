package de.honoka.test.kotlin.various;

import org.junit.jupiter.api.Test;

public class JavaClass {
	
	@Test
	public void test1() {
		AllOpenClass obj = new AllOpenClass();
		AllOpenClass.Companion.say();
		AllOpenClass.jvmStaticMethod();
		obj.dynamicMethod("hey!");
		System.out.println(AllOpenClass.staticField);
		obj.inlineMethod(integer -> integer);
        //noinspection InstantiatingAThreadWithDefaultRunMethod
        new Thread().interrupt();
		//obj.bindedMethodInClass("");
		//float f = 1.0F;
	}
}
