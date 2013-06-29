package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class MCDI extends Frame {

	private byte[] data;

	public MCDI(FrameHeader header) {
		super(header);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		data = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", [");
		for(byte b: data)
			builder.append(String.format("%02X", b & 0xFF));
		builder.append("]");
		return builder.toString();
	}

	@Override
	public String getFrameDescription() {
		return "Music CD identifier";
	}
}
