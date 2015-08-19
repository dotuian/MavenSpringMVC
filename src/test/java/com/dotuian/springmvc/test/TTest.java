package com.dotuian.springmvc.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TTest {

	@Test
	public void test() {
		assertEquals(1, 1);
		
		assertThat(1, is(1));
	}

}
