package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable

data class Praia(
    val pesquisar: String
) : Parcelable {

    // Construtor primário (necessário para Parcelable)
    constructor(parcel: Parcel) : this(parcel.readString().orEmpty())

    // Escreva os campos da sua classe para o Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pesquisar)
    }

    // Descreva a classe para o Parcel
    override fun describeContents(): Int {
        return 0
    }

    // Companion object para criar instâncias da classe a partir do Parcel
    companion object CREATOR : Parcelable.Creator<Praia> {
        override fun createFromParcel(parcel: Parcel): Praia {
            return Praia(parcel)
        }

        override fun newArray(size: Int): Array<Praia?> {
            return arrayOfNulls(size)
        }
    }
}
