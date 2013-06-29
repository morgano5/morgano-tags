package au.id.villar.mp3Sorter.id3;

import java.io.BufferedInputStream;
import java.io.IOException;

public class ID3Tag {

	private String title;
	private String artist;
	private String album;
	private int year;
	private String comment;
	private int trackNumber;
	private Genre genre;

	private Speed speed;
	private String strGenre;
	private String startTime;
	private String endTime;

	@Override
	public String toString() {
		return "ID3Tag{" +
				"title='" + title + '\'' +
				", artist='" + artist + '\'' +
				", album='" + album + '\'' +
				", year=" + year +
				", comment='" + comment + '\'' +
				", trackNumber=" + trackNumber +
				", genre=" + genre +
				", speed=" + speed +
				", strGenre='" + strGenre + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				'}';
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public String getStrGenre() {
		return strGenre;
	}

	public void setStrGenre(String strGenre) {
		this.strGenre = strGenre;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/*
	ID3v1: 128 bytes
	Field 	Length 	Description
	header 	3 	"TAG"
	title 	30 	30 characters of the title
	artist 	30 	30 characters of the artist name
	album 	30 	30 characters of the album name
	year 	4 	A four-digit year
	comment 	28[3] or 30 	The comment.
	zero-byte[3] 	1 	If a track number is stored, this byte contains a binary 0.
	track[3] 	1 	The number of the track on the album, or 0. Invalid, if previous byte is not a binary 0.
	genre 	1 	Index in a list of genres, or 255

	Extended tag (placed before the ID3v1 tag): 227 bytes
	Field 	Length 	Description
	header 	4 	"TAG+"
	title 	60 	Next 60 characters of the title (90 characters total)
	artist 	60 	Next 60 characters of the artist name
	album 	60 	Next 60 characters of the album name
	speed 	1 	0=unset, 1=slow, 2= medium, 3=fast, 4=hardcore
	genre 	30 	A free-text field for the genre
	start-time 	6 	the start of the music as mmm:ss
	end-time 	6 	the end of the music as mmm:ss
	*/


	public ID3Tag() {
		// TODO Auto-generated constructor stub
	}


	public static enum Speed {
		UNSET,
		SLOW,
		MEDIUM,
		FAST,
		HARDCORE
	}

	public static enum Genre {
		UNKNOWN,
		BLUES,
		CLASSIC_ROCK,
		COUNTRY,
		DANCE,
		DISCO,
		FUNK,
		GRUNGE,
		HIP_HOP,
		JAZZ,
		METAL,
		NEW_AGE,
		OLDIES,
		OTHER,
		POP,
		R_AND_B,
		RAP,
		REGGAE,
		ROCK,
		TECHNO,
		INDUSTRIAL,
		ALTERNATIVE,
		SKA,
		DEATH_METAL,
		PRANKS,
		SOUNDTRACK,
		EURO_TECHNO,
		AMBIENT,
		TRIP_HOP,
		VOCAL,
		JAZZ_PLUS_FUNK,
		FUSION,
		TRANCE,
		CLASSICAL,
		INSTRUMENTAL,
		ACID,
		HOUSE,
		GAME,
		SOUND_CLIP,
		GOSPEL,
		NOISE,
		ALTERNATIVE_ROCK,
		BASS,
		SOUL,
		PUNK,
		SPACE,
		MEDITATIVE,
		INSTRUMENTAL_POP,
		INSTRUMENTAL_ROCK,
		ETHNIC,
		GOTHIC,
		DARKWAVE,
		TECHNO_INDUSTRIAL,
		ELECTRONIC,
		POP_FOLK,
		EURODANCE,
		DREAM,
		SOUTHERN_ROCK,
		COMEDY,
		CULT,
		GANGSTA,
		TOP_40,
		CHRISTIAN_RAP,
		POP_FUNK,
		JUNGLE,
		NATIVE_US,
		CABARET,
		NEW_WAVE,
		PSYCHADELIC,
		RAVE,
		SHOWTUNES,
		TRAILER,
		LO_FI,
		TRIBAL,
		ACID_PUNK,
		ACID_JAZZ,
		POLKA,
		RETRO,
		MUSICAL,
		ROCK_AND_ROLL,
		HARD_ROCK,
		FOLK,
		FOLK_ROCK,
		NATIONAL_FOLK,
		SWING,
		FAST_FUSION,
		BEBOB,
		LATIN,
		REVIVAL,
		CELTIC,
		BLUEGRASS,
		AVANTGARDE,
		GOTHIC_ROCK,
		PROGRESSIVE_ROCK,
		PSYCHEDELIC_ROCK,
		SYMPHONIC_ROCK,
		SLOW_ROCK,
		BIG_BAND,
		CHORUS,
		EASY_LISTENING,
		ACOUSTIC,
		HUMOUR,
		SPEECH,
		CHANSON,
		OPERA,
		CHAMBER_MUSIC,
		SONATA,
		SYMPHONY,
		BOOTY_BASS,
		PRIMUS,
		PORN_GROOVE,
		SATIRE,
		SLOW_JAM,
		CLUB,
		TANGO,
		SAMBA,
		FOLKLORE,
		BALLAD,
		POWER_BALLAD,
		RHYTHMIC_SOUL,
		FREESTYLE,
		DUET,
		PUNK_ROCK,
		DRUM_SOLO,
		ACAPELLA,
		EURO_HOUSE,
		DANCE_HALL,
		GOA,
		DRUM_AND_BASS,
		CLUB_HOUSE,
		HARDCORE,
		TERROR,
		INDIE,
		BRITPOP,
		NEGERPUNK,
		POLSK_PUNK,
		BEAT,
		CHRISTIAN_GANGSTA_RAP,
		HEAVY_METAL,
		BLACK_METAL,
		CROSSOVER,
		CONTEMPORARY_CHRISTIAN,
		CHRISTIAN_ROCK,
		MERENGUE,
		SALSA,
		THRASH_METAL,
		ANIME,
		JPOP,
		SYNTHPOP
	};


}


