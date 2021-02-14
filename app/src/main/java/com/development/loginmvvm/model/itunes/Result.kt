package com.development.loginmvvm.model.itunes


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Result(

    @ColumnInfo
    @SerializedName("artistId")
    var artistId: Int?,

    @ColumnInfo
    @SerializedName("artistName")
    var artistName: String?,

    @ColumnInfo
    @SerializedName("artistViewUrl")
    var artistViewUrl: String?,

    @ColumnInfo
    @SerializedName("artworkUrl100")
    var artworkUrl100: String?,

    @ColumnInfo
    @SerializedName("artworkUrl30")
    var artworkUrl30: String?,

    @ColumnInfo
    @SerializedName("artworkUrl60")
    var artworkUrl60: String?,


    @ColumnInfo
    @SerializedName("collectionArtistId")
    var collectionArtistId: Int?,

    @ColumnInfo
    @SerializedName("collectionArtistName")
    var collectionArtistName: String?,

    @ColumnInfo
    @SerializedName("collectionArtistViewUrl")
    var collectionArtistViewUrl: String?,

    @ColumnInfo
    @SerializedName("collectionCensoredName")
    var collectionCensoredName: String?,

    @ColumnInfo
    @SerializedName("collectionExplicitness")
    var collectionExplicitness: String?,

    @ColumnInfo
    @SerializedName("collectionId")
    var collectionId: Int?,

    @ColumnInfo
    @SerializedName("collectionName")
    var collectionName: String?,

    @ColumnInfo
    @SerializedName("collectionPrice")
    var collectionPrice: Double?,

    @ColumnInfo
    @SerializedName("collectionViewUrl")
    var collectionViewUrl: String?,

    @ColumnInfo
    @SerializedName("country")
    var country: String?,

    @ColumnInfo
    @SerializedName("currency")
    var currency: String?,

    @ColumnInfo
    @SerializedName("discCount")
    var discCount: Int?,

    @ColumnInfo
    @SerializedName("discNumber")
    var discNumber: Int?,

    @ColumnInfo
    @SerializedName("isStreamable")
    var isStreamable: Boolean?,

    @ColumnInfo
    @SerializedName("kind")
    var kind: String?,

    @ColumnInfo
    @SerializedName("previewUrl")
    var previewUrl: String?,

    @ColumnInfo
    @SerializedName("primaryGenreName")
    var primaryGenreName: String?,

    @ColumnInfo
    @SerializedName("releaseDate")
    var releaseDate: String?,

    @ColumnInfo
    @SerializedName("trackCensoredName")
    var trackCensoredName: String?,

    @ColumnInfo
    @SerializedName("trackCount")
    var trackCount: Int?,

    @ColumnInfo
    @SerializedName("trackExplicitness")
    var trackExplicitness: String?,


    @PrimaryKey
    @ColumnInfo
    @SerializedName("trackId")
    var trackId: Int?,

    @ColumnInfo
    @SerializedName("trackName")
    var trackName: String?,

    @ColumnInfo
    @SerializedName("trackNumber")
    var trackNumber: Int?,

    @ColumnInfo
    @SerializedName("trackPrice")
    var trackPrice: Double?,

    @ColumnInfo
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: Int?,

    @ColumnInfo
    @SerializedName("trackViewUrl")
    var trackViewUrl: String?,

    @ColumnInfo
    @SerializedName("wrapperType")
    var wrapperType: String?,

    @ColumnInfo
    @Transient
    var isVisited:Boolean=false

): Parcelable