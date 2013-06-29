package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class USLT extends Frame {

	private String language;
	private String descriptor;
	private String text;

	public USLT(FrameHeader header) {
		super(header);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
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
		descriptor = reader.readString(unicode);
		text = reader.readString(unicode);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", lang: ").append(language)
		.append(", descriptor: ").append(descriptor)
		.append(", text").append(text.replaceAll("\n", "|")).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Unsychronized lyric/text transcription";
	}
}
