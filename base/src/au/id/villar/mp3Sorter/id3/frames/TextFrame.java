package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

abstract class TextFrame extends Frame {

	protected String value;

	protected TextFrame(FrameHeader header) {
		super(header);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		boolean unicode;

		unicode = reader.readBodyByte() == 1;
		value = reader.readString(unicode);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"")
				.append(", value: \"").append(value).append('"').toString();
	}
}
