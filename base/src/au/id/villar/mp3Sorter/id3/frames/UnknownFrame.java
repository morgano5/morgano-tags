package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class UnknownFrame extends Frame {

	private byte[] data;

	public UnknownFrame(FrameHeader header) {
		super(header);
	}

	public byte[] getData() {
		return data;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		data = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(header.getId()).append(" (UNKNOWN), data: ");
		for(byte b: data) {
			if(b < 0x20 || b > 0x7E)
				builder.append(String.format("[%02X]", b & 0xFF));
			else
				builder.append(String.format("[ %c]", (char)b));
		}
		return builder.toString();
	}

	@Override
	String getFrameDescription() {
		return "Unknown frame";
	}

}
