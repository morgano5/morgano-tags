package au.id.villar.mp3Sorter.id3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class ID3Reader {

	private long counter;
	private int frameCounter;
	private boolean unsync;
	BufferedInputStream is;

	public ID3Reader(BufferedInputStream is) {
		this.is = is;
	}

	public void mark(int readLimit) {
		is.mark(readLimit);
	}

	public void reset() throws IOException {
		is.reset();
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public boolean isUnsync() {
		return unsync;
	}

	public int getFrameCounter() {
		return frameCounter;
	}

	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
	}

	public void setUnsynchronized(boolean unsync) {
		this.unsync = unsync;
	}

	public int readBodyByte()
			throws IOException, InvalidTagException {
		int readByte;

		if(counter <= 0)
			throw new InvalidTagException();
		readByte = is.read();
		if(readByte == -1)
			throw new InvalidTagException();
		counter--;
		frameCounter--;
		if(unsync && readByte == 0xFF) {
			if(counter <= 0)
				throw new InvalidTagException();
			is.read();
			counter--;
		}
		return readByte;
	}

	public String readString(boolean unicode) throws IOException,
			InvalidTagException {
		int readByte;
		StringBuilder builder;
		boolean littleEndian = false;

		char ch;

		if(frameCounter == 0)
			return "";
		builder = new StringBuilder();
		readByte = readBodyByte();

		switch(readByte) {
		case 0:
			if(unicode)
				readBodyByte();
			return "";
		case 0xFF:
			littleEndian = true;
		case 0xFE:
			readBodyByte();
			unicode = true;
			break;
		}
		if(unicode) {
			while(frameCounter > 0) {
				ch = (char)(readBodyByte() << (littleEndian? 0: 8));
				ch |= (char)(readBodyByte() << (littleEndian? 8: 0));
				if(ch == 0)
					break;
				builder.append(ch);
			}
		} else {
			builder.append((char)readByte);
			while(frameCounter > 0) {
				readByte = readBodyByte();
				if(readByte == 0)
					break;
				builder.append((char)readByte);
			}
		}
		return builder.toString();
	}

	public byte[] readRestOfFrame() throws IOException, InvalidTagException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while(frameCounter > 0) {
			baos.write(readBodyByte());
		}
		baos.close();
		return baos.toByteArray();
	}

	public BigInteger readBigInteger(int numBytes, boolean minus) throws IOException, InvalidTagException {
		BigInteger number = BigInteger.ZERO;
		BigInteger multiplier = BigInteger.valueOf(256);
		for(int x = 0; x < numBytes; x++) {
			number = number.multiply(multiplier).add(BigInteger.valueOf(readBodyByte()));
		}
		if(minus)
			number = number.multiply(BigInteger.valueOf(-1));
		return number;
	}

	public String readLanguage() throws IOException, InvalidTagException {
		byte[] lang = new byte[3];
		lang[0] = (byte)readBodyByte();
		lang[1] = (byte)readBodyByte();
		lang[2] = (byte)readBodyByte();
		return new String(lang, Charset.forName("ASCII"));
	}

	public int read2Bytes() throws IOException, InvalidTagException {
		return (readBodyByte() << 8) | readBodyByte();
	}

	public int read3Bytes() throws IOException, InvalidTagException {
		return (readBodyByte() << 16) | (readBodyByte() << 8) | readBodyByte();
	}

	public int readInt() throws IOException, InvalidTagException {
		return (readBodyByte() << 24) | (readBodyByte() << 16) | (readBodyByte() << 8) | readBodyByte();
	}

	public static int readByte(BufferedInputStream is) throws IOException, InvalidTagException {
		int readByte;

		readByte = is.read();
		if(readByte == -1)
			throw new InvalidTagException();
		return readByte;
	}

}
