# TAM-PROJECT: Chat Application

Aplikasi obrolan (*chatting*) berbasis Android yang dibangun menggunakan teknologi modern Android Development. Proyek ini dikembangkan sebagai salah satu pemenuhan tugas akhir (UAS) untuk mata kuliah Teknologi Antarmuka Modern (TAM).

## 🚀 Fitur Utama

*   **Autentikasi Pengguna:** Sistem registrasi dan login yang aman bagi pengguna sebelum masuk ke dalam ruang obrolan.
*   **Real-time Chatting:** Antarmuka pengiriman dan penerimaan pesan yang responsif, dilengkapi dengan komponen *Chat Bubble* yang dinamis.
*   **Indikator Mengetik:** Fitur visual *Typing Indicator* untuk memberikan pengalaman interaksi obrolan yang lebih hidup.
*   **Manajemen Sesi:** Penyimpanan sesi pengguna secara lokal agar tidak perlu login kembali setiap kali membuka aplikasi.
*   **Sistem Keputusan & Progres:** Modul khusus yang mendukung penentuan alur cerita atau pengambilan keputusan berdasarkan riwayat interaksi pengguna.

## 🛠️ Tech Stack & Library

Aplikasi ini dikembangkan dengan memanfaatkan ekosistem modern dari Google dan pustaka pihak ketiga terbaik:

*   **User Interface:** [Jetpack Compose](https://developer.android.com/compose) (Deklaratif UI modern untuk Android)
*   **Arsitektur:** MVVM (Model-View-ViewModel) dengan komponen `ViewModel` dan `AppNavigation` untuk manajemen *routing* halaman.
*   **Bahasa Pemrograman:** Kotlin Script (KTS) untuk konfigurasi build sistem.
*   **Dependency Management:** Gradle Version Catalog (`libs.versions.toml`).

## 📁 Struktur Proyek

Arsitektur kode di dalam folder `app/src/main/java/com/exhale/chat_start/` disusun secara modular berdasarkan fungsinya:

```text
├── data/
│   ├── local/        # Manajemen penyimpanan sesi lokal (SessionManager)
│   ├── model/        # Struktur data (User, Message, StoryNode, Choice, dll.)
│   └── repository/   # Sumber data pusat untuk Autentikasi dan Alur Cerita
├── ui/
│   ├── navigation/   # Pengaturan rute halaman (ScreenRoutes, AppNavigation)
│   ├── screens/      # Implementasi halaman (Auth, Chat, Home, Profile, Stats, Splash)
│   └── theme/        # Konfigurasi desain global (Color, Theme, Type)
└── viewmodel/        # Logika bisnis aplikasi (AuthViewModel, GameViewModel)
