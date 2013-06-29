package au.id.villar.mp3Sorter;

import au.id.villar.mp3Sorter.id3.*;
import au.id.villar.mp3Sorter.id3.frames.Frame;
import au.id.villar.mp3Sorter.mp3.MP3Header;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Date: 31/10/11
 * Time: 8:28 PM
 */
public class Prueba {


	public static void main(String ... args) {
		
	}

	public static void main3(String[] args) {
		try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(args[0]))) {
			int readByte = 0;
			MP3Header mp3Header = new MP3Header();
			byte[] bitsMp3Header = new byte[4];

			boolean hasMoreBytes = true;
			while(hasMoreBytes) {
				stream.mark(5);

				if(readByte == 4) {
					mp3Header.setRawHeader(bitsMp3Header);
					if(mp3Header.hasSyncBits()) {
						System.out.print("X");
						continue;
					}
				}
				stream.reset();
				readByte = stream.read();
				if(readByte != -1) {
//					System.out.print("_");
					continue;
				}
				hasMoreBytes = false;




			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public static void main2(String[] args) {


		try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(args[0]))) {
			int readByte = 0;
			ID3TagParser parser = new ID3TagParser();
			ID3Tag tag;
			ID3v2Parser v2Parser = new ID3v2Parser();
			ID3v2Tag v2Tag;
			MP3Header mp3Header = new MP3Header();
			byte[] bitsMp3Header = new byte[4];
			boolean hasMoreBytes = true;

			while(hasMoreBytes) {
				tag = parser.tryParseComponent(stream);
				if(tag != null) {
					System.out.println(tag);
					continue;
				}
				v2Tag = v2Parser.tryParseComponent(stream);
				if(v2Tag != null) {
					if(v2Tag instanceof ID3v22Tag) {
						ID3v22Tag _22tag = (ID3v22Tag)v2Tag;
						for(Frame frame: _22tag.getFrames()) {
							System.out.println(frame);
						}
					}
					if(v2Tag instanceof ID3v23Tag) {
						ID3v23Tag _23tag = (ID3v23Tag)v2Tag;
						for(Frame frame: _23tag.getFrames()) {
							System.out.println(frame);
						}
					}
					continue;
				}
				stream.mark(5);
				readByte = stream.read(bitsMp3Header);
				if(readByte == 4) {
					mp3Header.setRawHeader(bitsMp3Header);
					if(mp3Header.hasSyncBits()) {

						int length = mp3Header.getFrameLength();
//System.out.println(">>> " + String.format("%02X", bitsMp3Header[0]) + String.format("%02X", bitsMp3Header[1]) + String.format("%02X", bitsMp3Header[2]) + String.format("%02X", bitsMp3Header[3]));
length -= 4;
while(length >= 4) {
	stream.read(bitsMp3Header);
//	System.out.print("---" + String.format("%02X", bitsMp3Header[0]) + String.format("%02X", bitsMp3Header[1]) + String.format("%02X", bitsMp3Header[2]) + String.format("%02X", bitsMp3Header[3]));
	length -= 4;
}
while(length > 0) {
	readByte = stream.read();
//	System.out.print("-" + String.format("%02X", readByte));
	length--;
}
//System.out.println();
						continue;
					}
				}
				stream.reset();

//System.out.println(">>> " + String.format("%c", bitsMp3Header[0] & 0xFF) + String.format("%c", bitsMp3Header[1] & 0xFF) + String.format("%c", bitsMp3Header[2] & 0xFF) + String.format("%c", bitsMp3Header[3] & 0xFF));
//System.exit(-1);
				readByte = stream.read();
				if(readByte != -1) {
//System.out.print((readByte < 0x20 || readByte > 0x7E)? String.format("[%02X]", readByte): (char)readByte);
					continue;
				}
				hasMoreBytes = false;
			}

		} catch(IOException | InvalidTagException e) {
			e.printStackTrace();
		}

	}

}
