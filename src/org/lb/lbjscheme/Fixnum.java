// lbjScheme
// An experimental Scheme subset interpreter in Java, based on SchemeNet.cs
// Copyright (c) 2013, Leif Bruder <leifbruder@gmail.com>
//
// Permission to use, copy, modify, and/or distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

package org.lb.lbjscheme;

public final class Fixnum extends SchemeNumber {
	private final int _value;

	public Fixnum(String value, int base) {
		_value = Integer.parseInt(value, base);
	}

	public Fixnum(int value) {
		_value = value;
	}

	public int getValue() {
		return _value;
	}

	@Override
	public int getLevel() {
		return 1; // 1 = Fixnum, 2 = Bignum, 3 = Rational, 4 = Real, 5 = Complex
	}

	@Override
	public String toString(boolean forDisplay, int base) {
		return Integer.toString(_value, base);
	}

	@Override
	public SchemeNumber promote() {
		return new Bignum(_value);
	}

	private static SchemeNumber valueOf(long value) {
		if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE)
			return new Bignum(value);
		return new Fixnum((int) value);
	}

	@Override
	protected SchemeNumber doAdd(SchemeNumber other) {
		final long v = (long) _value + (long) ((Fixnum) other)._value;
		return valueOf(v);
	}

	@Override
	public SchemeNumber doSub(SchemeNumber other) {
		final long v = (long) _value - (long) ((Fixnum) other)._value;
		return valueOf(v);
	}

	@Override
	public SchemeNumber doMul(SchemeNumber other) {
		final long v = (long) _value * (long) ((Fixnum) other)._value;
		return valueOf(v);
	}

	@Override
	public SchemeNumber doDiv(SchemeNumber other) {
		final long v = (long) _value / (long) ((Fixnum) other)._value;
		return valueOf(v);
	}

	@Override
	public SchemeNumber doIdiv(SchemeNumber other) {
		final long v = (long) _value / (long) ((Fixnum) other)._value;
		return valueOf(v);
	}

	@Override
	public SchemeNumber doMod(SchemeNumber other) {
		final long v = (long) _value % (long) ((Fixnum) other)._value;
		return valueOf(v);
	}

	@Override
	public boolean doEq(SchemeNumber other) {
		return _value == ((Fixnum) other)._value;
	}

	@Override
	public boolean doLt(SchemeNumber other) {
		return _value < ((Fixnum) other)._value;
	}

	@Override
	public boolean doLe(SchemeNumber other) {
		return _value <= ((Fixnum) other)._value;
	}

	@Override
	public boolean doGt(SchemeNumber other) {
		return _value > ((Fixnum) other)._value;
	}

	@Override
	public boolean doGe(SchemeNumber other) {
		return _value >= ((Fixnum) other)._value;
	}
}