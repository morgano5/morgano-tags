package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class WXXX extends Frame {

	private String description;
	private String url;

	WXXX(FrameHeader header) {
		super(header);
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode;

		unicode = reader.readBodyByte() != 0;
		description = reader.readString(unicode);
		url = reader.readString(false);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", description: \"").append(description)
				.append("\", url: \"").append(url).append("\"").toString();
	}

	@Override
	public String getFrameDescription() {
		return "User defined URL link frame";
	}
}
