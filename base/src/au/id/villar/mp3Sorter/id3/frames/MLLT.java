package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3BitReader;
import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MLLT extends Frame {

	private int framesBetweenRef;
	private int bytesBetweenRef;
	private int millisBetweenRef;
	private int bitsForDeviation;
	private int bitsForMillisDev;
	private List<Deviation> deviations;

	public MLLT(FrameHeader header) {
		super(header);
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		ID3BitReader bitReader = new ID3BitReader(reader);
		Deviation deviation;

		framesBetweenRef = reader.read2Bytes();
		bytesBetweenRef = reader.read3Bytes();
		millisBetweenRef = reader.read3Bytes();
		bitsForDeviation = reader.readBodyByte();
		bitsForMillisDev = reader.readBodyByte();
		deviations = new ArrayList<>();
		while(reader.getFrameCounter() > 0) {
			deviation = new Deviation();
			deviation.devInBytes = readBitsToLong(bitReader, bitsForDeviation);
			deviation.devInMillis = readBitsToLong(bitReader, bitsForMillisDev);
			deviations.add(deviation);
		}
	}

	public int getBitsForDeviation() {
		return bitsForDeviation;
	}

	public void setBitsForDeviation(int bitsForDeviation) {
		this.bitsForDeviation = bitsForDeviation;
	}

	public int getBitsForMillisDev() {
		return bitsForMillisDev;
	}

	public void setBitsForMillisDev(int bitsForMillisDev) {
		this.bitsForMillisDev = bitsForMillisDev;
	}

	public int getBytesBetweenRef() {
		return bytesBetweenRef;
	}

	public void setBytesBetweenRef(int bytesBetweenRef) {
		this.bytesBetweenRef = bytesBetweenRef;
	}

	public int getFramesBetweenRef() {
		return framesBetweenRef;
	}

	public void setFramesBetweenRef(int framesBetweenRef) {
		this.framesBetweenRef = framesBetweenRef;
	}

	public int getMillisBetweenRef() {
		return millisBetweenRef;
	}

	public void setMillisBetweenRef(int millisBetweenRef) {
		this.millisBetweenRef = millisBetweenRef;
	}

	public List<Deviation> getDeviations() {
		return deviations;
	}

	public void setDeviations(List<Deviation> deviations) {
		this.deviations = deviations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", Frames between ref: ").append(framesBetweenRef)
				.append(", Bytes between ref: ").append(bytesBetweenRef)
				.append(", Milliseconds between ref: ").append(millisBetweenRef)
				.append(", Bits for deviation: ").append(bitsForDeviation)
				.append(", Bits for milliseconds deviation: ").append(bitsForMillisDev);
		for(Deviation dev: deviations) {
			builder.append("\n deviation: in bytes: ").append(dev.devInBytes)
					.append(", in millis: ").append(dev.devInMillis);
		}
		return builder.toString();
	}

	private long readBitsToLong(ID3BitReader bitReader, int numBits) throws IOException, InvalidTagException {
		long value = 0;
		int toRead;
		while(numBits > 0) {
			toRead = (numBits > 8)? 8: numBits;
			numBits -= 8;
			value <<= toRead;
			value |= (0xFF & bitReader.readBits(toRead));
		}
		return value;
	}

	public static class Deviation {

		private long devInBytes;
		private long devInMillis;

		public long getDevInBytes() { return devInBytes; }
		public void setDevInBytes(long devInBytes) { this.devInBytes = devInBytes; }
		public long getDevInMillis() { return devInMillis; }
		public void setDevInMillis(long devInMillis) { this.devInMillis = devInMillis; }
	}

	@Override
	public String getFrameDescription() {
		return "MPEG location lookup table";
	}
}
