# ISA-Projekat

Tehnologije: Angular, Spring Boot, PostgreSQL

Frontend se pokreće tako što je prvo potrebno uraditi komandu npm install. Nakon toga se komandom npm start pokreće frontend deo aplikacije.

Backend se pokreće pozivom main funkcije u klasi IsaProjekatApplication.

Ime baze podataka je isadb2 a ostale informacije o bazi mogu se naći u fajlu application.properties.

Baza se puni podacima pri pokretanju aplikacije. Za ovaj zadatak je zadužena funkcija run u klasi IsaProjekatApplication.

Šifra za klijenta je 123 a email je isaprojmejl2@gmail.com. Šifre se hešuju pre čuvanja u bazi. Preporučujem registraciju sa Vašom email adresom zbog funkcionalnosti koje šalju email obaveštenje.

Inicijalizovano je nekoliko avantura, brodova, vikendica, rezervacija i brzih rezervacija radi lakšeg testiranja aplikacije. Sve informacije o inicijalizovanim entitetima nalaze se u funkciji run u klasi IsaProjekatApplication.

