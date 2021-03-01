/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Parcel
import android.os.Parcelable

enum class Genre {
    FEMALE, MALE
}

data class PuppyModel(

    val name: String,
    val image: String,
    val age: Int,
    val genre: Genre,
    val breed: String,
    val location: String,
    val contactInfo: String,
    val description: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString() as Genre,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name).toString()
        parcel.writeString(image).toString()
        parcel.writeInt(age)
        parcel.writeValue(genre) as Genre
        parcel.writeString(breed).toString()
        parcel.writeString(location).toString()
        parcel.writeString(contactInfo).toString()
        parcel.writeString(description).toString()
    }

    companion object CREATOR : Parcelable.Creator<PuppyModel> {
        override fun createFromParcel(parcel: Parcel): PuppyModel {
            return PuppyModel(parcel)
        }

        override fun newArray(size: Int): Array<PuppyModel?> {
            return arrayOfNulls(size)
        }
    }
}

val puppyDummy: List<PuppyModel> = listOf(
    PuppyModel(name = "Fluffy", image = "https://www.thesprucepets.com/thmb/MJ--9BJULRDvodDrmrqCxzYYIy4=/3504x2336/filters:fill(auto,1)/Pomeranian-GettyImages-1014940472-a6ba0030958a4bbba0eee3e982ee9bc6.jpg", age = 2, genre = Genre.FEMALE, breed = "Pomeranian", location = "Berlin", contactInfo = "+494443367586", description = "The Pomeranian is a breed of dog of the Spitz type that is named for the Pomerania region in north-west Poland and north-east Germany in Central Europe. Classed as a toy dog breed because of its small size, the Pomeranian is descended from larger Spitz-type dogs, specifically the German Spitz."),
    PuppyModel(name = "Leo", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Akita_Inu_dog.jpg/1200px-Akita_Inu_dog.jpg", age = 3, genre = Genre.MALE, breed = "Akita", location = "Tokyo", contactInfo = "+03-3224-9999", description = "The Akita is a large breed of dog originating from the mountainous regions of northern Japan. There are two separate varieties of Akita: a Japanese strain, commonly called Akita Inu or Japanese Akita, and an American strain, known as the Akita or American Akita."),
    PuppyModel(name = "Ava", image = "https://www.offlinepost.gr/wp-content/uploads/2019/11/PYGAKI.jpg", age = 1, genre = Genre.FEMALE, breed = "Pug", location = "New York", contactInfo = "+16465567479", description = "The pug is a breed of dog with physically distinctive features of a wrinkly, short-muzzled face, and curled tail. The breed has a fine, glossy coat that comes in a variety of colours, most often light brown or black, and a compact, square body with well-developed muscles."),
    PuppyModel(name = "Malin", image = "https://keyassets.timeincuk.net/inspirewp/live/wp-content/uploads/sites/8/2020/09/GettyImages-1021895664-920x745.jpg", age = 1, genre = Genre.FEMALE, breed = "Terrier", location = "San Diego", contactInfo = "+619-336-6330", description = "Terrier is a type of dog originally bred to hunt vermin. A terrier is a dog of any one of many breeds or landraces of the terrier type, which are typically small, wiry, game, and fearless. Terrier breeds vary greatly in size from just 1 kg to over 32 kg and are usually categorized by size or function.")
)
