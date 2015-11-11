# Sajam-knjiga
Web application done in with JSP technology as practice for a school project



NNapraviti Internet aplikaciju „Sajam knjiga“ za pregled i rezervaciju knjigu, sa MVC
arhitekturom koristeći JSP/Servlet tehnologiju.
Korisnici aplikacije su posetioci sajma koji unapred žele da pregledaju naslove knjiga i detalje o
knjigama i da ih rezervišu. Na početnoj strani aplikacije, korisnik treba da ima mogućnost
unošenja korisničkog imena i lozinke i u slučaju ispravno unetih podataka, omogućava rad sa
ostatkom sistema. Ukoliko korisnik ne unese neki od podataka ili unese pogrešne podatke,
potrebno je ispisati odgovarajuće poruke crvenim slovima sa mogućnošću ispravljanja greške,
uz zadržavanje korektno unetog korisničkog imena. Ukoliko korisnik pogreši tri uzastopna puta
lozinku, blokira mu se korisnički nalog.
Registrovani korisnik, sa svoje stranice, može izvršiti:
 Pretragu knjiga i obrada rezervacije knjige
Opis:
Korisnik preko forme može uneti naziv knjige, autora ili/i izdavača (može uneti jedan,
dva ili sva tri parametra pretrage), nakon čega dobija rezultat izvršavanja pretrage za te
unešene parametre. Kada izabere neku knjigu, korisnik dobija više detalja o knjizi, cenu
knjige i mogućnost da iz padajuće liste izabere broj primeraka knjige koji želi da rezerviše
(MAX=3). Nakon što korisnik izabere broj primeraka, i potvrdi rezervaciju, izračunava mu
se cena te rezervacije, pri čemu ako je korisnik student treba mu uračunati popust od
5%.
 Pregled svojih rezervacija i otkazivanje
Opis:
Korisnik ima mogućnost da pregleda sve svoje knjige koje je rezervisao (zaključno sa
današnjim danom). Knjige se prikazuju u jednoj tabeli, sortirane abecedno po nazivu
knjige. U svakom redu, uz svaki naziv knjige, treba da postoji i link “OTKAŽI”, koji preko
GET vrednosti parametra poziva servlet koji će otkazati tu rezervaciju, odnosno taj
naslov.
Prilikom izrade ove mini-aplikacije potrebno je koristiti relacionu bazu podataka
sajamknjiga2014.mysql (priloženu uz zadatak).
