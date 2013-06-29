package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.math.BigInteger;

public class PCNT extends Frame {

	private BigInteger counter;

	public PCNT(FrameHeader header) {
		super(header);
	}

	public BigInteger getCounter() {
		return counter;
	}

	public void setCounter(BigInteger counter) {
		this.counter = counter;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		counter = new BigInteger(reader.readRestOfFrame());
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", counter: ").append(counter).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Play counter";
	}
}
