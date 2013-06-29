package au.id.villar.mp3Sorter.id3;

import au.id.villar.mp3Sorter.FileComponentParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ID3v2Parser implements FileComponentParser<ID3v2Tag> {

	@Override
	public ID3v2Tag tryParseComponent(BufferedInputStream stream) throws IOException, InvalidTagException {
		int readByte, version = -1;
		long size;
		byte[] buffer = new byte[10];
		boolean stillValid = true;

		stream.mark(11);
		readByte = stream.read(buffer);
		if(readByte < 10 || buffer[0] != 'I' || buffer[1] != 'D' || buffer[2] != '3'
				|| (buffer[3] != 2 && buffer[3] != 3 && buffer[3] != 4) || buffer[4] != 0
				|| buffer[6] < 0 || buffer[7] < 0 || buffer[8] < 0 || buffer[9] < 0) {
			stream.reset();
			return null;
		}
		version = buffer[3];
		ID3Reader reader = new ID3Reader(stream);
		reader.setUnsynchronized((buffer[5] & 0x80) != 0);
		size = (buffer[6] & 0xFF) << 21;
		size += (buffer[7] & 0xFF) << 14;
		size += (buffer[8] & 0xFF) << 7;
		size += (buffer[9] & 0xFF);
		reader.setCounter(size);

		switch(version) {
		case 2:
			ID3v22Tag id3v22header = new ID3v22Tag(buffer[5]);
			id3v22header.completeFromStream(reader);
			return id3v22header;
		case 3:
			ID3v23Tag id3v23header = new ID3v23Tag(buffer[5]);
			id3v23header.completeFromStream(reader);
			return id3v23header;
		case 4:
		default:
			System.out.println("header version: " + version);
			throw new RuntimeException("Not yet implemented"); // TODO
		}
	}
}
