package au.id.villar.mp3Sorter.id3.frames;

public class TEXT extends TextFrame {

	TEXT(FrameHeader header) {
		super(header);
	}

	@Override
	public String getFrameDescription() {
		return "Lyricist/Text writer";
	}
}
