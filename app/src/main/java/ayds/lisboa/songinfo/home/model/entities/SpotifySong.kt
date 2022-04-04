package ayds.lisboa.songinfo.home.model.entities

import java.text.DateFormatSymbols

interface Song {
    val id: String
    val songName: String
    val artistName: String
    val albumName: String
    val releaseDate: String
    val spotifyUrl: String
    val imageUrl: String
    val releaseDatePrecision: String
    var isLocallyStored: Boolean
}

data class SpotifySong(
  override val id: String,
  override val songName: String,
  override val artistName: String,
  override val albumName: String,
  override val releaseDate: String,
  override val spotifyUrl: String,
  override val imageUrl: String,
  override val releaseDatePrecision: String,
  override var isLocallyStored: Boolean = false
) : Song {
    val writeReleaseDatePrecision: String = when(releaseDatePrecision){
        "day" -> releaseDate.split("-").last() + "/" + releaseDate.split("-")[1] + "/" + releaseDate.split("-").first()
        "month" -> DateFormatSymbols().months[releaseDate.split("-").last().toInt() - 1] + ", " + releaseDate.split("-").first()
        else -> releaseDate.split("-").first() + if(esBisiesto(releaseDate.split("-").first().toInt())) " (leap year)" else " (not a leap year)"
    }
    
    fun esBisiesto(n: Int) = (n % 4 == 0) && (n % 100 != 0 || n % 400 == 0)
}

object EmptySong : Song {
    override val id: String = ""
    override val songName: String = ""
    override val artistName: String = ""
    override val albumName: String = ""
    override val releaseDate: String = ""
    override val spotifyUrl: String = ""
    override val imageUrl: String = ""
    override val releaseDatePrecision: String = ""
    override var isLocallyStored: Boolean = false
}