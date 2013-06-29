package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class COMM extends Frame {

	private String lang;
	private String description;
	private String text;

	public COMM(FrameHeader header) {
		super(header);
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		lang = reader.readLanguage();
		description = reader.readString(unicode);
		text = reader.readString(unicode);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", lang: ").append(lang).append(", desc: \"")
				.append(description).append("\", text: \"").append(text).append('"').toString();
	}

	@Override
	public String getFrameDescription() {
		return "Comments";
	}
}
