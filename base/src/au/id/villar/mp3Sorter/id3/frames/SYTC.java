package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SYTC extends Frame {

	private TimestampFormat timestampFormat;
	private List<TempoCode> tempoCodes;

	public SYTC(FrameHeader header) {
		super(header);
	}

	public TimestampFormat getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(TimestampFormat timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public List<TempoCode> getTempoCodes() {
		return tempoCodes;
	}

	public void setTempoCodes(List<TempoCode> tempoCodes) {
		this.tempoCodes = tempoCodes;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		int readByte = reader.readBodyByte();
		int timestamp;
		int bitsPerMinute = 0;

		this.timestampFormat = TimestampFormat.toTimestampFormat(readByte);
		tempoCodes = new ArrayList<>();
		while(reader.getFrameCounter() > 0) {
			readByte = reader.readBodyByte();
			if(readByte == -1) {
				readByte = reader.readBodyByte();
				bitsPerMinute = 256;
			}
			bitsPerMinute += readByte;
			timestamp = reader.readInt();
			tempoCodes.add(new TempoCode(bitsPerMinute, timestamp));
		}
	}

	@Override
	public String toString() {
		return  new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", Timestamp format: ").append(timestampFormat)
				.append(", TempoCodes: ").append(tempoCodes).toString();
	}

	public static class TempoCode {
		private int bitsPerMinute;
		private int timestamp;

		public TempoCode(int bitsPerMinute, int timestamp) {
			this.bitsPerMinute = bitsPerMinute;
			this.timestamp = timestamp;
		}

		public int getBitsPerMinute() { return bitsPerMinute; }
		public int getTimestamp() { return timestamp; }

		@Override
		public String toString() {
			return new StringBuilder().append("[BPM: ").append(bitsPerMinute).append(", timestamp: ")
					.append(timestamp).append("]").toString();
		}
	}

	@Override
	public String getFrameDescription() {
		return "Synchronized tempo codes";
	}
}
