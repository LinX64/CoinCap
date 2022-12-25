package com.client.data.model.local_rates

import com.google.gson.annotations.SerializedName

data class LocalRateResponse(
    @SerializedName("rates")
    val rates: List<Rate>
)

data class Rate(
    @SerializedName("aed")
    val aed: Aed,
    @SerializedName("afn")
    val afn: Afn,
    @SerializedName("amd")
    val amd: Amd,
    @SerializedName("aud")
    val aud: Aud,
    @SerializedName("azn")
    val azn: Azn,
    @SerializedName("bhd")
    val bhd: Bhd,
    @SerializedName("cad")
    val cad: Cad,
    @SerializedName("chf")
    val chf: Chf,
    @SerializedName("cny")
    val cny: Cny,
    @SerializedName("dkk")
    val dkk: Dkk,
    @SerializedName("eur")
    val eur: Eur,
    @SerializedName("gbp")
    val gbp: Gbp,
    @SerializedName("hkd")
    val hkd: Hkd,
    @SerializedName("inr")
    val inr: Inr,
    @SerializedName("iqd")
    val iqd: Iqd,
    @SerializedName("jpy")
    val jpy: Jpy,
    @SerializedName("kwd")
    val kwd: Kwd,
    @SerializedName("myr")
    val myr: Myr,
    @SerializedName("nok")
    val nok: Nok,
    @SerializedName("omr")
    val omr: Omr,
    @SerializedName("qar")
    val qar: Qar,
    @SerializedName("rub")
    val rub: Rub,
    @SerializedName("sar")
    val sar: Sar,
    @SerializedName("sek")
    val sek: Sek,
    @SerializedName("sgd")
    val sgd: Sgd,
    @SerializedName("thb")
    val thb: Thb,
    @SerializedName("try")
    val tryX: Try,
    @SerializedName("usd")
    val usd: Usd
)

data class Aed(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Afn(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Amd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Aud(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Bhd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Azn(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Cad(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Chf(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Cny(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Dkk(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Eur(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Gbp(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Iqd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Inr(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Hkd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Jpy(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Myr(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Nok(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Kwd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Rub(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Sar(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Sgd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Sek(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Thb(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Try(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Usd(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Omr(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)

data class Qar(
    @SerializedName("buy")
    val buy: Int,
    @SerializedName("sell")
    val sell: Int
)