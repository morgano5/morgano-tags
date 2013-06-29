package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class V3FrameHeader extends FrameHeader {

	protected boolean tagAlter;
	protected boolean fileAlter;
	protected boolean readOnly;
	protected boolean compressed;
	protected boolean encrypted;
	protected boolean groupId;

	public boolean isTagAlter() {
		return tagAlter;
	}

	public boolean isFileAlter() {
		return fileAlter;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isCompressed() {
		return compressed;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public boolean isGroupId() {
		return groupId;
	}

	@Override
	public Frame readFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		Frame frame;
		byte[] bytesId;
		int size;
		int b;

		reader.mark(4);
		bytesId = new byte[4];
		bytesId[0] = (byte)reader.readBodyByte();
		if(bytesId[0] == 0) {
			reader.reset();
			return null;
		}
		bytesId[1] = (byte)reader.readBodyByte();
		bytesId[2] = (byte)reader.readBodyByte();
		bytesId[3] = (byte)reader.readBodyByte();
		id = new String(bytesId);
		size = (reader.readBodyByte() << 24) | (reader.readBodyByte() << 16)
				| (reader.readBodyByte() << 8) | reader.readBodyByte();
		b = reader.readBodyByte();
		tagAlter = ((b & 0x80) != 0);
		fileAlter = ((b & 0x40) != 0);
		readOnly = ((b & 0x20) != 0);
		b = reader.readBodyByte();
		compressed = ((b & 0x80) != 0);
		encrypted = ((b & 0x40) != 0);
		groupId = ((b & 0x20) != 0);
		reader.setFrameCounter(size);
		switch(id) {
		case "AENC": frame = new AENC(this); break;
		case "APIC": frame = new APIC(this); break;
		case "COMM": frame = new COMM(this); break;
		case "COMR": frame = new COMR(this); break;
		case "ENCR": frame = new ENCR(this); break;
		case "EQUA": frame = new EQUA(this); break;
		case "ETCO": frame = new ETCO(this); break;
		case "GEOB": frame = new GEOB(this); break;
		case "GRID": frame = new GRID(this); break;
		case "IPLS": frame = new IPLS(this); break;
		case "LINK": frame = new LINK(this); break;
		case "MCDI": frame = new MCDI(this); break;
		case "MLLT": frame = new MLLT(this); break;
		case "OWNE": frame = new OWNE(this); break;
		case "PRIV": frame = new PRIV(this); break;
		case "PCNT": frame = new PCNT(this); break;
		case "POPM": frame = new POPM(this); break;
		case "POSS": frame = new POSS(this); break;
		case "RBUF": frame = new RBUF(this); break;
		case "RVAD": frame = new RVAD(this); break;
		case "RVRB": frame = new RVRB(this); break;
		case "SYLT": frame = new SYLT(this); break;
		case "SYTC": frame = new SYTC(this); break;
		case "TALB": frame = new TALB(this); break;
		case "TBPM": frame = new TBPM(this); break;
		case "TCOM": frame = new TCOM(this); break;
		case "TCON": frame = new TCON(this); break;
		case "TCOP": frame = new TCOP(this); break;
		case "TDAT": frame = new TDAT(this); break;
		case "TDLY": frame = new TDLY(this); break;
		case "TENC": frame = new TENC(this); break;
		case "TEXT": frame = new TEXT(this); break;
		case "TFLT": frame = new TFLT(this); break;
		case "TIME": frame = new TIME(this); break;
		case "TIT1": frame = new TIT1(this); break;
		case "TIT2": frame = new TIT2(this); break;
		case "TIT3": frame = new TIT3(this); break;
		case "TKEY": frame = new TKEY(this); break;
		case "TLAN": frame = new TLAN(this); break;
		case "TLEN": frame = new TLEN(this); break;
		case "TMED": frame = new TMED(this); break;
		case "TOAL": frame = new TOAL(this); break;
		case "TOFN": frame = new TOFN(this); break;
		case "TOLY": frame = new TOLY(this); break;
		case "TOPE": frame = new TOPE(this); break;
		case "TORY": frame = new TORY(this); break;
		case "TOWN": frame = new TOWN(this); break;
		case "TPE1": frame = new TPE1(this); break;
		case "TPE2": frame = new TPE2(this); break;
		case "TPE3": frame = new TPE3(this); break;
		case "TPE4": frame = new TPE4(this); break;
		case "TPOS": frame = new TPOS(this); break;
		case "TPUB": frame = new TPUB(this); break;
		case "TRCK": frame = new TRCK(this); break;
		case "TRDA": frame = new TRDA(this); break;
		case "TRSN": frame = new TRSN(this); break;
		case "TRSO": frame = new TRSO(this); break;
		case "TSIZ": frame = new TSIZ(this); break;
		case "TSRC": frame = new TSRC(this); break;
		case "TSSE": frame = new TSSE(this); break;
		case "TYER": frame = new TYER(this); break;
		case "TXXX": frame = new TXXX(this); break;
		case "UFID": frame = new UFID(this); break;
		case "USER": frame = new USER(this); break;
		case "USLT": frame = new USLT(this); break;
		case "WCOM": frame = new WCOM(this); break;
		case "WCOP": frame = new WCOP(this); break;
		case "WOAF": frame = new WOAF(this); break;
		case "WOAR": frame = new WOAR(this); break;
		case "WOAS": frame = new WOAS(this); break;
		case "WORS": frame = new WORS(this); break;
		case "WPAY": frame = new WPAY(this); break;
		case "WPUB": frame = new WPUB(this); break;
		case "WXXX": frame = new WXXX(this); break;
		default: frame = new UnknownFrame(this);
		}
		frame.readBodyFromStream(reader);
		return frame;
	}
}
