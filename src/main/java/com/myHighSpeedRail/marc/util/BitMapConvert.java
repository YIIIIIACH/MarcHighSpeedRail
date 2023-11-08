package com.myHighSpeedRail.marc.util;

import java.util.BitSet;

public class BitMapConvert {
	public static BitSet convert(long value) {
		BitSet res = new BitSet();
		int pos=0;
		while( value>0) {
			if( value%2L==1L) {
				res.set(pos);
			}
			pos++;
			value= value>>>1;
		}
		System.out.println( res.size());
		return res;
	}
	
	public static long convert(BitSet bs) {
		long res = 0L;
		for( int i=0; i< bs.length(); i++) {
			res += (bs.get(i))? 1L<<i:0L;
		}
		return res;
	}
}
