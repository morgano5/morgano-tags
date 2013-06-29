package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ETCO extends Frame {

	public static final byte PADDING = 0x00;              // padding (has no meaning)
	public static final byte END_INIT_SILENCE = 0x01;     // end of initial silence
	public static final byte INTRO_START = 0x02;          // intro start
	public static final byte MAINPART_START = 0x03;       // mainpart start
	public static final byte OUTRO_START = 0x04;          // outro start
	public static final byte OUTRO_END = 0x05;            // outro end
	public static final byte VERSE_START = 0x06;          // verse start
	public static final byte REFRAIN_START = 0x07;        // refrain start
	public static final byte INTERLUDE_START = 0x08;      // interlude start
	public static final byte THEME_START = 0x09;          // theme start
	public static final byte VARIATION_START = 0x0A;      //  variation start
	public static final byte KEY_CHANGE = 0x0B;           // key change
	public static final byte TIME_CHANGE = 0x0C;          // time change
	public static final byte MOMENTARY_NOISE = 0x0D;      // momentary unwanted noise (Snap, Crackle & Pop)
	public static final byte SUSTAINED_NOISE = 0x0E;      // sustained noise
	public static final byte SUSTAINED_NOISE_END = 0x0F;  // sustained noise end
	public static final byte INTRO_END = 0x10;            // intro end
	public static final byte MAINPART_END = 0x11;         // mainpart end
	public static final byte VERSE_END = 0x12;            // verse end
	public static final byte REFRAIN_END = 0x13;          // refrain end
	public static final byte THEME_END = 0x14;            // theme end
	public static final byte AUDIO_END = (byte)0xFD;      // audio end (start of silence)
	public static final byte AUDIO_FILE_END = (byte)0xFE; // audio file ends
	public static final byte ONE_MORE = (byte)0xFF;       // one more byte of events follows (all the following bytes
	                                                      //    with the value $FF have the same function)

	public static boolean isReserved(int key) {
		return (key >= 0x15 && key <= 0xDF) || (key >= 0xF0 && key <= 0xFC);
	}

	public static boolean isNotPredefinedSync(int key) {
		return (key >= 0xE0 && key <= 0xEF);
	}

	private TimestampFormat timestampFormat;
	private List<Event> events;

	public ETCO(FrameHeader header) {
		super(header);
	}

	public TimestampFormat getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(TimestampFormat timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		int readByte = reader.readBodyByte();
		int eventKey;
		int eventTimestamp;
		Event event;

		this.timestampFormat = TimestampFormat.toTimestampFormat(readByte);
		events = new ArrayList<>();
		while(reader.getFrameCounter() > 0) {
			readByte = reader.readBodyByte();
			switch(readByte) {
			case PADDING:
			case ONE_MORE:
				break;
			default:
				eventKey = readByte;
				eventTimestamp = reader.readInt();
				event = new Event(eventKey, eventTimestamp);
				events.add(event);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", Timestamp format: ")
				.append(this.timestampFormat).append(" as unit, {");
		for(Event event: events) {
			builder.append(" [").append(Integer.toHexString(event.key)).append(", ")
					.append(event.timestamp).append("]");
		}
		builder.append(" }");
		return builder.toString();
	}

	public static class Event {
		private int key;
		private int timestamp;

		Event(int key, int timestamp) { this.key = key; this.timestamp = timestamp; }

		public int getKey() { return key; }
		public int getTimestamp() { return timestamp; }
	}

	@Override
	public String getFrameDescription() {
		return "Event timing codes";
	}
}

