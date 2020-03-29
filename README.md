# BankSystem
Banko sistemos kūrimas mokantis sql DB


Dar neimplementuota kreditų sistema. Visos sąskaitos gali eit į minusą.
____________________
Padaryta:
1. Suprojektuoti duomenų bazę;
2. Sukurti lenteles ir įdėti įrašus;
3. Parašyti JAVA aplikaciją, kuri leistų: 
  a. Užsiregistuoti/prisijungti vartotojui
    i. Peržiūrėti savo sąsakaitas ir balansus
    ii. Tranzakcijų istoriją
    iii. Įnešti pinigų
    iv. Išsiimti pinigus
    v. Padaryti pavedimą
    vi. Išeksportuoti transakcijų istoriją už norimą datą į failą (t.y. Visas
tranzakcijas atliktas 2020-01-20 - 2020-02-02)
____________________

Reikia dar preventinti debetų transakcijas jei balansas gaunasi minusinis.
Kreditų acc lentelėje fiksuoti datą kada pirmą kartą balansas patapo minusinis ir ištrinti iš duombazės kreditų acc lentelės, kai minusinė 
suma padengiama.

Na, ir žinoma, daug refaktorinti.
