package au.id.villar.mp3Sorter.id3;

import java.io.IOException;
import java.io.InputStream;

public abstract class ID3v2Tag {

	private int version;

	protected ID3v2Tag(int version) {
		this.version = version;
	}

	public final int getVersion() {
		return version;
	}

	abstract void completeFromStream(ID3Reader reader) throws IOException, InvalidTagException;

}
