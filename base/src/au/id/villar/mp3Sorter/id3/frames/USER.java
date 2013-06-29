package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class USER extends Frame {

	private String language;
	private String text;

	public USER(FrameHeader header) {
		super(header);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode = reader.readBodyByte() != 0;
		language = reader.readLanguage();
		text = reader.readString(unicode);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", language: ").append(language)
				.append(", text: ").append(text).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Terms of use";
	}
}
