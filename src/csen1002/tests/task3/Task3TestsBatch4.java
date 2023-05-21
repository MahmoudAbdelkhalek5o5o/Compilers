package csen1002.tests.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task3.FallbackDfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task3TestsBatch4 {

	@Test
	public void testFallbackDfa1String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#k;t#0,k,6;0,t,11;1,k,2;1,t,11;2,k,7;2,t,5;3,k,12;3,t,10;4,k,0;4,t,9;5,k,7;5,t,9;6,k,3;6,t,4;7,k,0;7,t,6;8,k,1;8,t,9;9,k,7;9,t,5;10,k,11;10,t,4;11,k,3;11,t,8;12,k,9;12,t,9#10#2;4;6;8");
		assertEquals("kttttkkkt,4;t,4;t,4;t,4;t,4;t,4;t,4", fallbackDfa.run("kttttkkkttttttt"));
	}

	@Test
	public void testFallbackDfa1String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#k;t#0,k,6;0,t,11;1,k,2;1,t,11;2,k,7;2,t,5;3,k,12;3,t,10;4,k,0;4,t,9;5,k,7;5,t,9;6,k,3;6,t,4;7,k,0;7,t,6;8,k,1;8,t,9;9,k,7;9,t,5;10,k,11;10,t,4;11,k,3;11,t,8;12,k,9;12,t,9#10#2;4;6;8");
		assertEquals("ttkkttttkkttkkkkkt,4;t,4;t,4;t,4;t,4", fallbackDfa.run("ttkkttttkkttkkkkkttttt"));
	}

	@Test
	public void testFallbackDfa1String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#k;t#0,k,6;0,t,11;1,k,2;1,t,11;2,k,7;2,t,5;3,k,12;3,t,10;4,k,0;4,t,9;5,k,7;5,t,9;6,k,3;6,t,4;7,k,0;7,t,6;8,k,1;8,t,9;9,k,7;9,t,5;10,k,11;10,t,4;11,k,3;11,t,8;12,k,9;12,t,9#10#2;4;6;8");
		assertEquals("tkktttkttktkttkk,6;ktkk,2;t,4;k,11", fallbackDfa.run("tkktttkttktkttkkktkktk"));
	}

	@Test
	public void testFallbackDfa1String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#k;t#0,k,6;0,t,11;1,k,2;1,t,11;2,k,7;2,t,5;3,k,12;3,t,10;4,k,0;4,t,9;5,k,7;5,t,9;6,k,3;6,t,4;7,k,0;7,t,6;8,k,1;8,t,9;9,k,7;9,t,5;10,k,11;10,t,4;11,k,3;11,t,8;12,k,9;12,t,9#10#2;4;6;8");
		assertEquals("ttkkttttkkttkk,2;t,4;t,4;t,4;tkk,6", fallbackDfa.run("ttkkttttkkttkkttttkk"));
	}

	@Test
	public void testFallbackDfa1String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#k;t#0,k,6;0,t,11;1,k,2;1,t,11;2,k,7;2,t,5;3,k,12;3,t,10;4,k,0;4,t,9;5,k,7;5,t,9;6,k,3;6,t,4;7,k,0;7,t,6;8,k,1;8,t,9;9,k,7;9,t,5;10,k,11;10,t,4;11,k,3;11,t,8;12,k,9;12,t,9#10#2;4;6;8");
		assertEquals("ttttkkkt,4;t,4;t,4;k,11", fallbackDfa.run("ttttkkktttk"));
	}

	@Test
	public void testFallbackDfa2String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#d;l;s;t;x#0,d,3;0,l,13;0,s,6;0,t,2;0,x,11;1,d,5;1,l,11;1,s,13;1,t,7;1,x,13;2,d,6;2,l,10;2,s,7;2,t,1;2,x,10;3,d,10;3,l,3;3,s,7;3,t,7;3,x,6;4,d,14;4,l,7;4,s,11;4,t,8;4,x,7;5,d,10;5,l,8;5,s,13;5,t,1;5,x,2;6,d,11;6,l,5;6,s,8;6,t,8;6,x,5;7,d,2;7,l,14;7,s,12;7,t,9;7,x,13;8,d,9;8,l,5;8,s,3;8,t,11;8,x,5;9,d,9;9,l,2;9,s,0;9,t,7;9,x,3;10,d,11;10,l,1;10,s,6;10,t,13;10,x,3;11,d,9;11,l,1;11,s,0;11,t,5;11,x,8;12,d,8;12,l,4;12,s,10;12,t,12;12,x,5;13,d,13;13,l,5;13,s,8;13,t,6;13,x,3;14,d,0;14,l,11;14,s,12;14,t,4;14,x,12#3#0;7;8;9");
		assertEquals("ldddldtds,0;s,7;xdtl,8;s,7", fallbackDfa.run("ldddldtdssxdtls"));
	}

	@Test
	public void testFallbackDfa2String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#d;l;s;t;x#0,d,3;0,l,13;0,s,6;0,t,2;0,x,11;1,d,5;1,l,11;1,s,13;1,t,7;1,x,13;2,d,6;2,l,10;2,s,7;2,t,1;2,x,10;3,d,10;3,l,3;3,s,7;3,t,7;3,x,6;4,d,14;4,l,7;4,s,11;4,t,8;4,x,7;5,d,10;5,l,8;5,s,13;5,t,1;5,x,2;6,d,11;6,l,5;6,s,8;6,t,8;6,x,5;7,d,2;7,l,14;7,s,12;7,t,9;7,x,13;8,d,9;8,l,5;8,s,3;8,t,11;8,x,5;9,d,9;9,l,2;9,s,0;9,t,7;9,x,3;10,d,11;10,l,1;10,s,6;10,t,13;10,x,3;11,d,9;11,l,1;11,s,0;11,t,5;11,x,8;12,d,8;12,l,4;12,s,10;12,t,12;12,x,5;13,d,13;13,l,5;13,s,8;13,t,6;13,x,3;14,d,0;14,l,11;14,s,12;14,t,4;14,x,12#3#0;7;8;9");
		assertEquals("dxt,7;s,7;s,7;l,3", fallbackDfa.run("dxtssl"));
	}

	@Test
	public void testFallbackDfa2String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#d;l;s;t;x#0,d,3;0,l,13;0,s,6;0,t,2;0,x,11;1,d,5;1,l,11;1,s,13;1,t,7;1,x,13;2,d,6;2,l,10;2,s,7;2,t,1;2,x,10;3,d,10;3,l,3;3,s,7;3,t,7;3,x,6;4,d,14;4,l,7;4,s,11;4,t,8;4,x,7;5,d,10;5,l,8;5,s,13;5,t,1;5,x,2;6,d,11;6,l,5;6,s,8;6,t,8;6,x,5;7,d,2;7,l,14;7,s,12;7,t,9;7,x,13;8,d,9;8,l,5;8,s,3;8,t,11;8,x,5;9,d,9;9,l,2;9,s,0;9,t,7;9,x,3;10,d,11;10,l,1;10,s,6;10,t,13;10,x,3;11,d,9;11,l,1;11,s,0;11,t,5;11,x,8;12,d,8;12,l,4;12,s,10;12,t,12;12,x,5;13,d,13;13,l,5;13,s,8;13,t,6;13,x,3;14,d,0;14,l,11;14,s,12;14,t,4;14,x,12#3#0;7;8;9");
		assertEquals("tttxtllttxtlsxs,0;s,7;xt,8;xt,8", fallbackDfa.run("tttxtllttxtlsxssxtxt"));
	}

	@Test
	public void testFallbackDfa2String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#d;l;s;t;x#0,d,3;0,l,13;0,s,6;0,t,2;0,x,11;1,d,5;1,l,11;1,s,13;1,t,7;1,x,13;2,d,6;2,l,10;2,s,7;2,t,1;2,x,10;3,d,10;3,l,3;3,s,7;3,t,7;3,x,6;4,d,14;4,l,7;4,s,11;4,t,8;4,x,7;5,d,10;5,l,8;5,s,13;5,t,1;5,x,2;6,d,11;6,l,5;6,s,8;6,t,8;6,x,5;7,d,2;7,l,14;7,s,12;7,t,9;7,x,13;8,d,9;8,l,5;8,s,3;8,t,11;8,x,5;9,d,9;9,l,2;9,s,0;9,t,7;9,x,3;10,d,11;10,l,1;10,s,6;10,t,13;10,x,3;11,d,9;11,l,1;11,s,0;11,t,5;11,x,8;12,d,8;12,l,4;12,s,10;12,t,12;12,x,5;13,d,13;13,l,5;13,s,8;13,t,6;13,x,3;14,d,0;14,l,11;14,s,12;14,t,4;14,x,12#3#0;7;8;9");
		assertEquals("dsttlsxtds,7;s,7;s,7;s,7", fallbackDfa.run("dsttlsxtdssss"));
	}

	@Test
	public void testFallbackDfa2String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#d;l;s;t;x#0,d,3;0,l,13;0,s,6;0,t,2;0,x,11;1,d,5;1,l,11;1,s,13;1,t,7;1,x,13;2,d,6;2,l,10;2,s,7;2,t,1;2,x,10;3,d,10;3,l,3;3,s,7;3,t,7;3,x,6;4,d,14;4,l,7;4,s,11;4,t,8;4,x,7;5,d,10;5,l,8;5,s,13;5,t,1;5,x,2;6,d,11;6,l,5;6,s,8;6,t,8;6,x,5;7,d,2;7,l,14;7,s,12;7,t,9;7,x,13;8,d,9;8,l,5;8,s,3;8,t,11;8,x,5;9,d,9;9,l,2;9,s,0;9,t,7;9,x,3;10,d,11;10,l,1;10,s,6;10,t,13;10,x,3;11,d,9;11,l,1;11,s,0;11,t,5;11,x,8;12,d,8;12,l,4;12,s,10;12,t,12;12,x,5;13,d,13;13,l,5;13,s,8;13,t,6;13,x,3;14,d,0;14,l,11;14,s,12;14,t,4;14,x,12#3#0;7;8;9");
		assertEquals("xssdts,8;tt,9;xxl,8;s,7;xdl,1", fallbackDfa.run("xssdtsttxxlsxdl"));
	}

}