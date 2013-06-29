package au.id.villar.mp3Sorter.id3;

import java.io.IOException;

public class ID3BitReader {

	short buffer;
	int bufferSize;
	ID3Reader reader;

	public ID3BitReader(ID3Reader reader) {
		if(reader == null)
			throw new NullPointerException("reader mustn't be null");
		this.reader = reader;
	}

	public byte readBits(int numBits) throws IOException, InvalidTagException {
		byte bits;

		if(numBits < 0 || numBits > 8)
			throw new IllegalArgumentException("numBits must be between 0 and 8");
		if(numBits > bufferSize) {
			buffer <<= 8;
			buffer |= reader.readBodyByte();
			bufferSize += 8;
		}
		bits = (byte)(buffer >>> (bufferSize - numBits));
		bits &= 0xFF >>> (8 - numBits);
		bufferSize -= numBits;
		return bits;
	}

	public byte readRest() {
		byte bits;

		bits = (byte)(buffer & (0xFF >>> (8 - bufferSize)));
		bufferSize = 0;
		return bits;
	}

	public int getRest() {
		return bufferSize;
	}
}
