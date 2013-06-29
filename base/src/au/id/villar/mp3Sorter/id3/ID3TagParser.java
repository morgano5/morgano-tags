package au.id.villar.mp3Sorter.id3;

import au.id.villar.mp3Sorter.FileComponentParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ID3TagParser implements FileComponentParser<ID3Tag> {

	private byte[] buffer = new byte[270];
	private byte[] altBuffer = new byte[30];

	private static final String CHARSET_NAME = "ISO8859_1";
	@Override
	public ID3Tag tryParseComponent(BufferedInputStream stream) throws IOException, InvalidTagException {
		ID3Tag tag;
		boolean extended = false;
		int index;

		stream.mark(5);
		stream.read(buffer, 0, 4);
		if(buffer[0] != 'T' || buffer[1] != 'A' || buffer[2] != 'G') {
			stream.reset();
			return null;
		}
		tag = new ID3Tag();
		if(buffer[3] == '+') {
			extended = true;
			read(stream, buffer, 30, 60);
			read(stream, buffer, 120, 60);
			read(stream, buffer, 210, 60);
			index = read(stream);
			if(index < 0 || index >= ID3Tag.Speed.values().length)
				throw new InvalidTagException("Invalid value for speed: " + index);
			tag.setSpeed(ID3Tag.Speed.values()[index]);
			read(stream, altBuffer, 0, 30);
			tag.setStrGenre(convertToString(altBuffer, 0, 30));
			read(stream, altBuffer, 0, 6);
			tag.setStartTime(new String(altBuffer, 0, 6));
			read(stream, altBuffer, 0, 6);
			tag.setEndTime(new String(altBuffer, 0, 6));
			read(stream, buffer, 0, 3);
			read(stream, buffer, 0, 1);
		} else {
			buffer[0] = buffer[3];
		}
		read(stream, buffer, 1, 29);
		read(stream, buffer, 90, 30);
		read(stream, buffer, 180, 30);
		tag.setTitle(convertToString(buffer, 0, extended ? 90 : 30));
		tag.setArtist(convertToString(buffer, 90, extended ? 90 : 30));
		tag.setAlbum(convertToString(buffer, 180, extended ? 90 : 30));
		read(stream, altBuffer, 0, 4);
//		if(altBuffer[0] < '0' || altBuffer[0] > '9' || altBuffer[1] < '0' || altBuffer[1] > '9'
//				|| altBuffer[2] < '0' || altBuffer[2] > '9' || altBuffer[3] < '0' || altBuffer[3] > '9') {
//			throw new InvalidTagException("Invalid value for year: " + new String(altBuffer, 0, 4));
//		}
		tag.setYear((altBuffer[0] & 0xF) * 1000 + (altBuffer[1] & 0xF) * 100
				+ (altBuffer[2] & 0xF) * 10 + (altBuffer[3] & 0xF));
		read(stream, altBuffer, 0, 30);
		tag.setComment(convertToString(altBuffer, 0, (altBuffer[28] == 0)? 28: 30));
		tag.setTrackNumber((altBuffer[28] == 0)? (altBuffer[29] & 0xFF): 0);
		index = read(stream);
		if(index != 255 && index >= ID3Tag.Genre.values().length - 1)
			throw new InvalidTagException("Invalid value for genre: " + index);
		index = (index == 255)? 0: index + 1;
		tag.setGenre(ID3Tag.Genre.values()[index]);
		return tag;
	}

	private int read(InputStream stream) throws IOException, InvalidTagException {
		int readByte = stream.read();
		if(readByte == -1)
			throw new InvalidTagException("End of stream reached");
		return readByte;
	}

	private void read(InputStream stream, byte[] buffer, int offset, int length) throws IOException, InvalidTagException {
		int read = stream.read(buffer, offset, length);
		if(read < length)
			throw new InvalidTagException("End of stream reached");
	}

	private String convertToString(byte[] buffer, int offset, int length) throws IOException {
		int lastPos = offset + length;
		lastPos = lastPos >= buffer.length? buffer.length - 1: lastPos;
		while (lastPos > offset && buffer[lastPos - 1] == 0)
			lastPos--;
		return new String(buffer, offset, lastPos - offset, CHARSET_NAME);
	}
}
