package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class TXXX extends Frame {

	private String description;
	private String value;

	TXXX(FrameHeader header) {
		super(header);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode;

		unicode = reader.readBodyByte() == 1;
		description = reader.readString(unicode);
		value = reader.readString(unicode);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", description: ").append(description)
				.append(", value: ").append(value).toString();
	}

	@Override
	public String getFrameDescription() {
		return "User defined text information frame";
	}
}
