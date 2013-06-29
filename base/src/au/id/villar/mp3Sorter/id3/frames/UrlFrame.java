package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

abstract class UrlFrame extends Frame {

	protected String url;

	UrlFrame(FrameHeader header) {
		super(header);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String value) {
		this.url = value;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		url = reader.readString(false);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"")
				.append(", url: \"").append(url).append("\"").toString();
	}
}
