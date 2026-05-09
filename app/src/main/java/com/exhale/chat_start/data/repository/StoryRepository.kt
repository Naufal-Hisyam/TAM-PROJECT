package com.exhale.chat_start.data.repository

import com.exhale.chat_start.data.model.Choice
import com.exhale.chat_start.data.model.StoryNode

class StoryRepository {
    private val storyGraph = mapOf(
        "node_start" to StoryNode(
            id = "node_start",
            npcText = "Badai salju semakin gila. Aku sudah melewati pagar pembatas Voss City. Kau masih memantauku?",
            choices = listOf(
                Choice("Lanjut, hati-hati.", "node_2", ptModifier = 2),
                Choice("Kenapa kau nekat?", "node_2", ecModifier = 2)
            )
        ),
        "node_2" to StoryNode(
            id = "node_2",
            npcText = "Sinyal radio mati, tapi teks terenkripsi ini masih tembus. Kota ini amat terasa mati.",
            choices = listOf(
                Choice("Ikuti koordinat suar.", "node_3", ptModifier = 1),
                Choice("Cari tempat berlindung.", "node_3", pdModifier = 1)
            )
        ),
        "node_3" to StoryNode(
            id = "node_3",
            npcText = "Dara mengirimiku pesan tiga kata sebelum sinyalnya hilang. Dia bilang dia masih di sini.",
            choices = listOf(
                Choice("Siapa maksudnya?", "node_4", fsModifier = 1),
                Choice("Tetap waspada, Elias.", "node_4", ptModifier = 2)
            )
        ),
        "node_4" to StoryNode(
            id = "node_4",
            npcText = "Aku tidak tahu pasti. Ingatanku tentang kasus sebelas tahun lalu masih terasa sangat kabur.",
            choices = listOf(
                Choice("Fokus pada misimu.", "node_5", ptModifier = 1),
                Choice("Ingatanmu akan pulih.", "node_5", ecModifier = 1)
            )
        ),
        "node_5" to StoryNode(
            id = "node_5",
            npcText = "Pesan Dara menyuruhku mengikuti jejak lentera kuning. Aku melihat satu di bekas jendela hotel.",
            choices = listOf(
                Choice("Dekati lentera perlahan.", "node_6", ptModifier = 2),
                Choice("Periksa sekelilingnya dahulu.", "node_6", pdModifier = 1)
            )
        ),
        "node_6" to StoryNode(
            id = "node_6",
            npcText = "Lentera ini menggunakan baterai. Cahayanya membentuk kotak kuning di atas tumpukan salju yang tebal.",
            choices = listOf(
                Choice("Ada petunjuk lain?", "node_7", fsModifier = 1),
                Choice("Lanjut ke berikutnya.", "node_7", ptModifier = 1)
            )
        ),
        "node_7" to StoryNode(
            id = "node_7",
            npcText = "Cahaya lentera ini membimbingku menghindari jalan raya utama. Aldric pasti memantau semua jalan besar.",
            choices = listOf(
                Choice("Terus ikuti cahayanya.", "node_8", ptModifier = 2),
                Choice("Jangan sampai terlihat.", "node_8", pdModifier = 2)
            )
        ),
        "node_8" to StoryNode(
            id = "node_8",
            npcText = "Tidak ada petunjuk lain. Tapi aku melihat lentera kedua di gedung farmasi seberang jalan.",
            choices = listOf(
                Choice("Cepat bergerak kesana.", "node_9", pdModifier = 1),
                Choice("Awas ada jebakan.", "node_9", ptModifier = 1)
            )
        ),
        "node_9" to StoryNode(
            id = "node_9",
            npcText = "Aku di dalam farmasi sekarang. Angin di luar melolong seperti menyimpan dendam yang lama.",
            choices = listOf(
                Choice("Cari lentera ketiga.", "node_10", pdModifier = 1),
                Choice("Hangatkan dirimu sejenak.", "node_10", ecModifier = 2)
            )
        ),
        "node_10" to StoryNode(
            id = "node_10",
            npcText = "Aku tidak boleh berhenti. Lentera ketiga ada di perpustakaan kota. Pintunya sudah hancur lebur.",
            choices = listOf(
                Choice("Masuk dan periksa.", "node_11", fsModifier = 1),
                Choice("Siapkan senjata selalu.", "node_11", ptModifier = 2)
            )
        ),
        "node_11" to StoryNode(
            id = "node_11",
            npcText = "Salju menumpuk di antara rak buku. Jarak pandangku sangat terbatas. Badai menelan jejakku sendiri.",
            choices = listOf(
                Choice("Terus ikuti lenteranya.", "node_12", ptModifier = 1),
                Choice("Dengarkan suara sekitarmu.", "node_12", fsModifier = 2)
            )
        ),
        "node_12" to StoryNode(
            id = "node_12",
            npcText = "Jejak ini mengarah ke gedung pengadilan pusat. Pintunya terkunci rantai, tapi pintu sampingnya rusak.",
            choices = listOf(
                Choice("Masuk lewat sana.", "node_13", pdModifier = 1),
                Choice("Cari jalan lain.", "node_13", ptModifier = 2)
            )
        ),
        "node_13" to StoryNode(
            id = "node_13",
            npcText = "Kayu di sekitar kunci pintu samping ini baru saja dihancurkan. Seseorang masuk belum lama.",
            choices = listOf(
                Choice("Nyalakan senter masuk.", "node_14", pdModifier = 1),
                Choice("Masuk dalam kegelapan.", "node_14", ptModifier = 2)
            )
        ),
        "node_14" to StoryNode(
            id = "node_14",
            npcText = "Suhu di dalam gedung ini sangat dingin. Namun aku mencium aroma pelumas mesin baru.",
            choices = listOf(
                Choice("Itu tanda aktivitas.", "node_15", fsModifier = 1),
                Choice("Pelumas dari senjata?", "node_15", pdModifier = 2)
            )
        ),
        "node_15" to StoryNode(
            id = "node_15",
            npcText = "Lobi pengadilan hancur berantakan. Debu tebal menutupi segalanya. Tapi ada sesuatu yang sangat aneh.",
            choices = listOf(
                Choice("Apa yang kulihat?", "node_16", ecModifier = 1),
                Choice("Jelaskan secara detail.", "node_16", ptModifier = 2)
            )
        ),
        "node_16" to StoryNode(
            id = "node_16",
            npcText = "Ruangan ini terasa hangat. Bukan hangat alami, tapi ada sesuatu yang menghasilkan panas buatan.",
            choices = listOf(
                Choice("Cari sumber panasnya.", "node_17", ptModifier = 2),
                Choice("Pantau dari lobi.", "node_17", fsModifier = 1)
            )
        ),
        "node_17" to StoryNode(
            id = "node_17",
            npcText = "Aku menemukan generator tua berdetak pelan di tangga bawah tanah. Seseorang menyalurkan listrik kemari.",
            choices = listOf(
                Choice("Turun ke bawah.", "node_18", pdModifier = 2),
                Choice("Matikan generator itu.", "node_18", ptModifier = 1)
            )
        ),
        "node_18" to StoryNode(
            id = "node_18",
            npcText = "Aku tidak akan mematikannya. Aku harus melihat apa yang mereka sembunyikan di bawah sana.",
            choices = listOf(
                Choice("Baiklah, tetap waspada.", "node_19", ptModifier = 2),
                Choice("Berjalan tanpa suara.", "node_19", fsModifier = 1)
            )
        ),
        "node_19" to StoryNode(
            id = "node_19",
            npcText = "Ini bekas ruang penyimpanan catatan. Pintunya diganjal batu bata. Aku melihat sesuatu di dalam.",
            choices = listOf(
                Choice("Apa isi ruangannya?", "node_20", ecModifier = 1),
                Choice("Jangan langsung masuk.", "node_20", ptModifier = 2)
            )
        ),
        "node_20" to StoryNode(
            id = "node_20",
            npcText = "Dindingnya penuh dengan ribuan foto. Semuanya adalah foto diriku dari sebelas tahun yang lalu.",
            choices = listOf(
                Choice("Siapa yang memotretnya?", "node_21", fsModifier = 2),
                Choice("Ini ancaman. Keluar!", "node_21", pdModifier = 3)
            )
        ),
        "node_21" to StoryNode(
            id = "node_21",
            npcText = "Ada foto lamaku di depan makam Dara Solano. Dia memalsukan kematiannya selama sebelas tahun.",
            choices = listOf(
                Choice("Periksa lebih teliti.", "node_22", ptModifier = 1),
                Choice("Fokus cari bukti.", "node_22", pdModifier = 1)
            )
        ),
        "node_22" to StoryNode(
            id = "node_22",
            npcText = "Kutemukan kotak kayu dengan pemutar kaset dan sebuah catatan yang ditulis dengan sangat cepat.",
            choices = listOf(
                Choice("Apa isi catatannya?", "node_23", ecModifier = 1),
                Choice("Putar kaset itu.", "node_23", ptModifier = 2)
            )
        ),
        "node_23" to StoryNode(
            id = "node_23",
            npcText = "Catatan ini dari Dara. Dia bilang Aldric Vane ada di kota ini untuk membunuhku.",
            choices = listOf(
                Choice("Putar kasetnya sekarang.", "node_24", ptModifier = 1),
                Choice("Kita harus bergerak.", "node_24", pdModifier = 2)
            )
        ),
        "node_24" to StoryNode(
            id = "node_24",
            npcText = "Suara Dara di kaset menjelaskan semuanya. Bencana kota ini adalah rekayasa untuk menutupi korupsi.",
            choices = listOf(
                Choice("Apa yang disembunyikan?", "node_25", fsModifier = 2),
                Choice("Hubungannya dengan ingatanmu?", "node_25", ptModifier = 2)
            )
        ),
        "node_25" to StoryNode(
            id = "node_25",
            npcText = "Dara bilang aku tahu segalanya sebelum evakuasi. Aldric melakukan sesuatu yang menghapus semua ingatanku.",
            choices = listOf(
                Choice("Cari bukti aslinya.", "node_26", ptModifier = 2),
                Choice("Kendalikan emosimu, Elias.", "node_26", ecModifier = 3)
            )
        ),
        "node_26" to StoryNode(
            id = "node_26",
            npcText = "Bukti kejahatan itu ada di ruang arsip tua di belakang kabinet delapan puluh tujuh.",
            choices = listOf(
                Choice("Ke ruang arsip.", "node_27", pdModifier = 1),
                Choice("Amankan posisi dahulu.", "node_27", ptModifier = 2)
            )
        ),
        "node_27" to StoryNode(
            id = "node_27",
            npcText = "Aku mematikan senter. Ada suara langkah kaki yang berat dari lantai atas gedung ini.",
            choices = listOf(
                Choice("Sembunyi tahan napasmu.", "node_28", ptModifier = 2),
                Choice("Siapkan senjata menyergap.", "node_28", pdModifier = 2)
            )
        ),
        "node_28" to StoryNode(
            id = "node_28",
            npcText = "Langkah kaki itu semakin dekat. Aku harus menahan napas agar dia tidak menyadari keberadaanku.",
            choices = listOf(
                Choice("Tetap tidak bergerak.", "node_29", fsModifier = 2),
                Choice("Mundur perlahan kebelakang.", "node_29", ptModifier = 1)
            )
        ),
        "node_29" to StoryNode(
            id = "node_29",
            npcText = "Aku bergerak meraba dinding menuju arsip. Ada palka lantai mengarah ke ruang bawah tanah.",
            choices = listOf(
                Choice("Turun ke palka.", "node_30", pdModifier = 1),
                Choice("Periksa palka aman.", "node_30", ptModifier = 2)
            )
        ),
        "node_30" to StoryNode(
            id = "node_30",
            npcText = "Ruang bawah tanah ini berbau kimia tua. Aku berhasil menemukan kabinet delapan puluh tujuh.",
            choices = listOf(
                Choice("Geser kabinet itu.", "node_31", pdModifier = 1),
                Choice("Cari mekanisme pintu.", "node_31", ptModifier = 2)
            )
        ),
        "node_31" to StoryNode(
            id = "node_31",
            npcText = "Di baliknya ada ruang rahasia. Kutemukan koper besi dengan kunci kombinasi angka yang usang.",
            choices = listOf(
                Choice("Tebak angka kombinasinya.", "node_32", ptModifier = 2),
                Choice("Paksa dengan alat.", "node_32", pdModifier = 2)
            )
        ),
        "node_32" to StoryNode(
            id = "node_32",
            npcText = "Jariku bergerak otomatis menekan angka ulang tahun ibuku. Kopernya terbuka. Ingatanku perlahan mulai kembali.",
            choices = listOf(
                Choice("Apa isi kopernya?", "node_33", ecModifier = 1),
                Choice("Simpan dan bersiaplah.", "node_33", ptModifier = 2)
            )
        ),
        "node_33" to StoryNode(
            id = "node_33",
            npcText = "Semua bukti korupsi Aldric ada di sini. Ada juga foto lamaku bersama Dara tersenyum.",
            choices = listOf(
                Choice("Ambil dan kabur.", "node_34", pdModifier = 1),
                Choice("Dengar suara langkah?", "node_34", ptModifier = 2)
            )
        ),
        "node_34" to StoryNode(
            id = "node_34",
            npcText = "Palka terbuka di atasku. Cahaya senter menyapu ruangan gelap. Aldric Vane berdiri di sana.",
            choices = listOf(
                Choice("Sembunyi di kabinet.", "node_35", fsModifier = 1),
                Choice("Tantang secara terang.", "node_35", pdModifier = 2)
            )
        ),
        "node_35" to StoryNode(
            id = "node_35",
            npcText = "Aldric tertawa sinis. Dia bilang ingatanku dihapus karena aku terlalu hebat dalam pekerjaanku dulu.",
            choices = listOf(
                Choice("Ajak bicara mengulur.", "node_36", ptModifier = 2),
                Choice("Tembak tanpa ragu.", "node_36", pdModifier = 3)
            )
        ),
        "node_36" to StoryNode(
            id = "node_36",
            npcText = "Aku menodongkan senjata padanya. Aku bilang Dara yang membimbingku kemari. Dia meremehkan nama Dara.",
            choices = listOf(
                Choice("Ini akan menghancurkanmu.", "node_37", ecModifier = 1),
                Choice("Kau takkan lolos.", "node_37", ptModifier = 1)
            )
        ),
        "node_37" to StoryNode(
            id = "node_37",
            npcText = "Aldric mengancam bahwa anak buahnya menguasai rute keluar. Salju di luar setinggi dua kaki.",
            choices = listOf(
                Choice("Aku bertahan disini.", "node_38", pdModifier = 2),
                Choice("Aku menembus barisan.", "node_38", fsModifier = 2)
            )
        ),
        "node_38" to StoryNode(
            id = "node_38",
            npcText = "Mendadak terdengar suara langkah kaki lain bergerak sangat cepat keluar gedung. Itu Dara kabur!",
            choices = listOf(
                Choice("Gunakan kesempatan menyerang!", "node_39", pdModifier = 3),
                Choice("Lari ke pintu!", "node_39", fsModifier = 2)
            )
        ),
        "node_39" to StoryNode(
            id = "node_39",
            npcText = "Perhatian Aldric terpecah sepersekian detik. Aku melompat dan menendang senternya hingga hancur berkeping keping.",
            choices = listOf(
                Choice("Pukul hingga pingsan.", "node_40", pdModifier = 2),
                Choice("Tembak kedua kakinya.", "node_40", ptModifier = 1)
            )
        ),
        "node_40" to StoryNode(
            id = "node_40",
            npcText = "Aldric tersungkur di lantai. Koper bukti ada di tanganku. Keputusan akhir ada padaku sekarang.",
            choices = listOf(
                Choice("Berikan pada jurnalis.", "node_41", ecModifier = 3, isMajorChoice = true),
                Choice("Serahkan pada kepolisian.", "node_42", ptModifier = 3, isMajorChoice = true),
                Choice("Bakar dan peras.", "node_43", pdModifier = 4, isMajorChoice = true)
            )
        ),
        "node_41" to StoryNode(
            id = "node_41",
            npcText = "Aku menyerahkan semuanya pada media. Skandal ini menghancurkan Aldric. Aku akhirnya mendapatkan hidupku kembali.",
            choices = emptyList()
        ),
        "node_42" to StoryNode(
            id = "node_42",
            npcText = "Polisi menangkap Aldric. Namun sistem hukum terlalu korup. Setidaknya aku mengingat siapa diriku sebenarnya.",
            choices = emptyList()
        ),
        "node_43" to StoryNode(
            id = "node_43",
            npcText = "Aku memeras Aldric dengan bukti ini. Uangnya memberiku hidup baru, tapi jiwaku selamanya mati.",
            choices = emptyList()
        )
    )

    fun getNode(nodeId: String): StoryNode? {
        return storyGraph[nodeId]
    }
}