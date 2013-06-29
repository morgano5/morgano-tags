package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.math.BigInteger;

public class POSS extends Frame {

	TimestampFormat format;
	BigInteger position;

	public POSS(FrameHeader header) {
		super(header);
	}

	public TimestampFormat getFormat() {
		return format;
	}

	public void setFormat(TimestampFormat format) {
		this.format = format;
	}

	public BigInteger getPosition() {
		return position;
	}

	public void setPosition(BigInteger position) {
		this.position = position;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		format = TimestampFormat.toTimestampFormat(reader.readBodyByte());
		position = new BigInteger(reader.readRestOfFrame());
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", format: ").append(format).append(", position: ")
				.append(position).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Position synchronisation frame";
	}
}
