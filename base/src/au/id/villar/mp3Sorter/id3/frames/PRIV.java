package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class PRIV extends Frame {

	private String owner;
	private byte[] data;

	public PRIV(FrameHeader header) {
		super(header);
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

		owner = reader.readString(false);
		data = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", owner: \"").append(owner).append('"').toString();
	}

	@Override
	public String getFrameDescription() {
		return "Private frame";
	}
}
