package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SYLT extends Frame {

	private String language;
	private TimestampFormat timestampFormat;
	private ContentType contentType;
	private List<SyncText> syncTexts;

	public SYLT(FrameHeader header) {
		super(header);
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		String text;
		int timestamp;

		boolean unicode = reader.readBodyByte() != 0;
		language = reader.readLanguage();
		timestampFormat = TimestampFormat.toTimestampFormat(reader.readBodyByte());
		contentType = ContentType.toContentType(reader.readBodyByte());
		syncTexts = new ArrayList<>();
		while(reader.getFrameCounter() > 0) {
			text = reader.readString(unicode);
			timestamp = reader.readInt();
			syncTexts.add(new SyncText(timestamp, text));
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", Lang: ").append(language).append(", timestamp format: ")
				.append(timestampFormat).append(", content type: ").append(contentType).append(", sync texts: ")
				.append(syncTexts).toString();
	}

	public static enum ContentType {
		OTHER,
		LYRICS,
		TRANSCRIPTION,
		PART_NAME,
		EVENTS,
		CHORD,
		TRIVIAL;

		public static ContentType toContentType(int intContentType) {
			ContentType[] values = ContentType.values();
			if(intContentType >= values.length)
				throw new IllegalArgumentException("value not recognized for a ContentType: " + intContentType);
			return values[intContentType];
		}
	}

	public static class SyncText {
		private int timestamp;
		private String text;

		public SyncText(int timestamp, String text) {
			this.timestamp = timestamp;
			this.text = text;
		}

		public int getTimestamp() { return timestamp; }
		public String getText() { return text; }

		@Override
		public String toString() {
			return new StringBuilder().append("[timestamp: ").append(timestamp).append(", text: ")
					.append(text.replaceAll("\n", "|")).append("]").toString();
		}
	}

	@Override
	public String getFrameDescription() {
		return "Synchronized lyric/text";
	}
}
