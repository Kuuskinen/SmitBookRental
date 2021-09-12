RAKENDUSE "CAROLINA JA GUSTAVI HAMBAARSTID OÜ" DOKUMENTATSIOON

SISUKORD:

   * Rakenduse käivitamine - info, aadressid
   * Koodi dokumentatsioon - kirjeldused, kuidas rakenduse protsessid töötavad

========================== 1. RAKENDUSE KÄIVITAMINE ==========================

Rakendus on programmeeritud IntelliJ IDEAga ning see peaks töötama peaaegu kõigis brauserites.

Rakendus käivitub localhostis, kasutades porti 8080. Backend on Spring Boot ning frontend Vue.js + Typescript, mis asub Spring Booti sees. Andmebaasiks valisin mäluga liidestatud andmebaasi H2, mille keeleks on SQL ning andmebaasiga suhtlevad interface'id extendivad JpaRepository't. 

Rakenduse andmebaasi JDBC url on jdbc:h2:./resources/data

Rakenduse saab käivitada terminalis käsuga mvn spring-boot:run. Juhul kui käsk ei toimi, võib enne seda jooksutada veel kolme käsku:

1) cd src/frontend
2) npm run build
3) cd ../../
4) mvn spring-boot:run

========================== 2. KOODI DOKUMENTATSIOON ==========================

Raamatu lisamine (aadress: localhost:8080/add_book)

Vormis saab kasutaja lisada raamatu nime, autori ning määrata, kas raamat on laenutatav või mitte. Viimane määrab, kas raamat muutub kohe pärast lisamist laenajatele laenutatavaks. Pärast raamatu lisamist, lehekülg värskendatakse.

Vormi salvestamisel saadetakse raamatu andmed BookControllerisse, kus meetod addBook kasutab BookRequesti klassi et andmed vastu võtta. Andmed lisatakse seejärel BookEntity külge ning meetod kutsub välja bookService'i meetodi addBooki, mis omakorda kutsub välja BookRepository addBook meetodi, mis salvestab BookEntity andmebaasi.


Raamatute vaatamine (aadress: localhost:8080/books)

Lehekülje laadimisel küsitakse raamatud rentalService'i meetodiga getBooks, mis pöördub BookControlleri meetodi getBooks poole. Meetod küsib BookService'ilt kõik raamatud ning sorteerib välja raamatud, mille laenutamine on lubatud ja mille staatus on "Available". Kasutajad saavad raamatu broneerida, vajutades kirje kõrval olevat nuppu "Broneeri", mis viib broneerimise leheküljele.


Broneerimine

Broneerimise leheküljel kuvatakse kasutajale valitud raamatu nimi ja autor ning vorm, kus kasutaja saab valida laenutuse lõppkuupäeva. Laenutuse alguskuupäev on alati broneeringu tegemise päev. Pärast muudatuste salvestamist, võtab POST-päringu vastu ReservationControlleri addNewReservation, mis moodustab ReservationEntity ja edastab selle ReservationService'i meetodile addReservation, mis omakorda edastab selle reservationRepository meetodile save, mis salvestab ReservationEntity andmebaasi. Eduka salvestamise järel kutsutakse välja BookService'i meetod changeStatus, millele antakse kaasa uus staatus ja raamatu id. Meetod muudab raamatu staatuse "Available" asemel "Reserved"'i, misjärel ei kuvata seda enam saadaolevate raamatute lehel.

Broneeringu kustutamine

Omaniku ja laenaja poolel saab broneeringut kustutada. Omaniku poolel kustutatakse päring meetodiga deleteReservation, mis pöördub ReservationControlleri meetodi deleteReservation poole ning annab kaasa broneeringu id. DeleteReservation edastab kustutatava broneeringu id ReservationService'ile. ReservationService edastab selle ReservationRepository meetodile deleteReservation. Eduka kustutamise korral kutsub ReservationService välja meetodi changeStatus, mis muudab raamatu staatuse "Available"'iks.

Laenaja saab samuti broneeringu kustutada. Laenaja poolel broneeringut kustutades, kutsutakse välja meetod "deleteBookReservation", millele antakse kaasa raamatu id. BookControlleris tegeleb sellega meetod "deleteBorrowedBook", mis kutsub välja ReservationService'i meetodi deleteReservationByBookId. Kuna katsed seda teistmoodi teha ebaõnnestusid, küsitakse meetodis raamatud id-d kasutades esmalt ReservationEntity ning seejärel kustutatakse reserveeringu id-d kasutades reserveering ning muudetakse raamatu staatus BookService'i meetodiga changeStatus ümber "Available"'ks.


Omaniku raamatud ja raamatu andmete muutmine (aadress: http://localhost:8080/#/books_out, "Minu (O) raamatud")

Omanikule kuuluvate raamatute lehel kuvatakse kõik omanikule kuuluvad raamatud. Lehekülje laadimisel küsitakse rentalService'i meetodiga getOwnerBooks kõik raamatud, mis kuuluvad etteantud id'ga kasutajale. Meetod pöördub BookControlleri meetodi getOwnerBooks poole, mis tagastab kõik antud omaniku raamatud. Juhul kui omaniku raamat on vahepeal reserveeritud, kuvatakse seda halli taustaga. Omanik saab raamatu andmeid ja broneeringuid vaadata vajutades nuppu "Muuda raamatut".

Raamatu muutmislehel saab muuta raamatu andmeid nagu autor ja pealkiri ning vaadata broneeringuid. Raamatu andmete muutmisel submititakse vorm BookControlleri meetodile changeBook, mis annab saadud andmed BookService'i meetodile changeBook. ChangeBook küsib BookRepository'lt vastava BookEntity ning muudab selle andmed, kasutades etteantud parameetreid. Seejärel salvestatakse raamat BookRepository meetodiga save andmebaasi. 


========================== 3. ÜLDINE ==========================

Broneeringute puhul arvestatakse, et ühte raamatut saab broneerida üks kasutaja ning raamatut saab uuesti broneerida kui see on eelnevast broneeringust vabanenud. Ettetellimist ei ole.

Kahjuks ei õnnestunud realiseerida Spring Security't ning loobusin sellest kui mul ei õnnestunud pääseda mööda HTTP 401 veast. IntelliJ keeldus koodi kompileerimast kuni ma polnud Security'ga seotud koodi eemaldanud, mistõttu pole seda ka siin näha. Security ebaõnnestunud implementeerimise tõttu kaotasin sedavõrd palju aega, et enam ei õnnestunud ka teste kirjutada. 
