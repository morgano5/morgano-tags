package au.id.villar.mp3Sorter.id3.frames;

public enum TimestampFormat {
	MPEG_FRAMES("MPEG frames"),
	MILLISECONDS("milliseconds");

	private String description;

	TimestampFormat(String description) {
		this.description = description;
	}

	public static TimestampFormat toTimestampFormat(int byteFormat) {
		switch(byteFormat) {
		case 1: return TimestampFormat.MPEG_FRAMES;
		case 2: return TimestampFormat.MILLISECONDS;
		default: throw new IllegalArgumentException("format code not valid: " + byteFormat);
		}
	}

	@Override
	public String toString() {
		return description;
	}
}

