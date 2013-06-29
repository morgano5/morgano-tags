package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class UFID extends Frame {

	private String owner;
	private byte[] identifier;

	UFID(FrameHeader header) {
		super(header);
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public byte[] getIdentifier() {
		return identifier;
	}

	public void setIdentifier(byte[] identifier) {
		this.identifier = identifier;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {

		owner = reader.readString(false);
		identifier = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", owner: ").append(owner).append(", id: [ ");
		for(byte b: identifier)
			builder.append(String.format("%02X", b & 0xFF));
		builder.append(" ]");
		return builder.toString();
	}

	@Override
	public String getFrameDescription() {
		return "Unique file identifier";
	}
}
