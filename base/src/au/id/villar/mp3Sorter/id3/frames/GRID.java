package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class GRID extends Frame {

	private String ownerId;
	private int groupSymbol;
	private byte[] data;

	public GRID(FrameHeader header) {
		super(header);
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getGroupSymbol() {
		return groupSymbol;
	}

	public void setGroupSymbol(int groupSymbol) {
		this.groupSymbol = groupSymbol;
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
		ownerId = reader.readString(false);
		groupSymbol = reader.readBodyByte();
		if(reader.getFrameCounter() > 0) {
			data = reader.readRestOfFrame();
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", owner id: ").append(ownerId)
				.append(", groupSymbol: ").append(groupSymbol).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Group identification registration";
	}
}
